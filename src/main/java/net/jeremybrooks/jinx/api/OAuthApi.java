/*
 * Jinx is Copyright 2010-2014 by Jeremy Brooks and Contributors
 *
 * Jinx is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jinx is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Jinx.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.jeremybrooks.jinx.api;

import com.google.gson.Gson;
import net.jeremybrooks.jinx.Jinx;
import net.jeremybrooks.jinx.JinxConstants;
import net.jeremybrooks.jinx.JinxException;
import net.jeremybrooks.jinx.JinxUtils;
import net.jeremybrooks.jinx.OAuthAccessToken;
import net.jeremybrooks.jinx.response.auth.oauth.OAuthCredentials;
import net.jeremybrooks.jinx.response.auth.oauth.OAuthExchangedToken;
import oauth.signpost.OAuth;
import oauth.signpost.commonshttp.CommonsHttpOAuthProvider;
import oauth.signpost.http.HttpParameters;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.util.Map;
import java.util.Properties;
import java.util.SortedSet;
import java.util.TreeMap;

/**
 * Provides access to the Flickr OAuth API.
 * <p>
 *     This class is used when getting OAuth tokens, querying the oauth api, and converting legacy tokens to oauth tokens.
 *     See {@link Jinx} documentation for examples of how to use this class.
 * </p>
 *
 * @author Jeremy Brooks
 * @see <a href="http://www.flickr.com/services/api/">Flickr Services</a>
 */
public class OAuthApi {

	private Jinx jinx;

	private CommonsHttpOAuthProvider provider;

	/**
	 * Create a new instance of OAuthApi.
	 * <p>
	 * You must provide a jinx instance for this class to use.
	 * </p>
	 *
	 * @param jinx jinx instance used when making API requests.
	 */
	public OAuthApi(Jinx jinx) {
		this.jinx = jinx;
	}


	/**
	 * <a href="https://www.flickr.com/services/api/flickr.auth.oauth.checkToken.html">flickr.auth.oauth.checkToken</a>
	 *
	 * Returns the credentials attached to an OAuth authentication token.
	 *
	 * @param oauthToken the oauth token.
	 * @return credentials attached to the oauth token.
	 * @throws JinxException if there are any errors, or if the oauthToken is null.
	 */
	public OAuthCredentials checkToken(String oauthToken) throws JinxException {
		JinxUtils.validateParams(oauthToken);

		Map<String, String> params = new TreeMap<String, String>();
		params.put("method", "flickr.auth.oauth.checkToken");
		params.put("oauth_token", oauthToken);

		return jinx.flickrGet(params, OAuthCredentials.class);
	}


	/**
	 * <a href="https://www.flickr.com/services/api/flickr.auth.oauth.getAccessToken.html">flickr.auth.oauth.getAccessToken</a>
	 *
	 * Exchange an auth token from the old Authentication API, to an OAuth access token.
	 * Calling this method will delete the auth token used to make the request.
	 * <p/>
	 * This method should be called when upgrading your app to use OAuth. Call this method prior to initializing
	 * Jinx, and then use the OAuthExchangedToken to initialize Jinx.
	 *
	 * @param inputStream stream to the legacy token properties.
	 * @return object containing the OAuth Access Token data.
	 * @throws JinxException if any parameter is null, or if there are any errors.
	 * @see <a href="http://www.flickr.com/services/api/flickr.auth.oauth.getAccessToken.html">flickr.auth.oauth.getAccessToken</a>
	 */
	public OAuthAccessToken getAccessToken(InputStream inputStream) throws JinxException {
		JinxUtils.validateParams(inputStream);
		Properties legacyTokenProperties = loadLegacyTokenProperties(inputStream);
		Map<String, String> params = new TreeMap<String, String>();
		params.put("method", "flickr.auth.oauth.getAccessToken");
		params.put("api_key", jinx.getApiKey());
		params.put("auth_token", legacyTokenProperties.getProperty("token"));
		params.put("format", "json");
		params.put("nojsoncallback", "1");

		params.put("api_sig", sign(params, jinx.getApiSecret()));

		StringBuilder sb = new StringBuilder(JinxConstants.REST_ENDPOINT).append('?');
		for (String key : params.keySet()) {
			sb.append(key).append('=').append(params.get(key)).append('&');
		}
		sb.deleteCharAt(sb.lastIndexOf("&"));

		BufferedReader in = null;
		StringBuilder json = new StringBuilder();
		try {
			HttpURLConnection request = (HttpURLConnection) new URL(sb.toString()).openConnection();
			request.connect();

			in = new BufferedReader(new InputStreamReader(request.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				json.append(line);
			}
		} catch (Exception e) {
			throw new JinxException("Error when converting legacy token to OAuth token.", e);
		} finally {
			JinxUtils.close(in);
		}

		OAuthExchangedToken exchangedToken = new Gson().fromJson(json.toString(), OAuthExchangedToken.class);
		if (!exchangedToken.getStat().equals("ok")) {
			throw new JinxException("Flickr reported an error.", null, exchangedToken);
		}

		OAuthAccessToken oAuthAccessToken = new OAuthAccessToken();
		oAuthAccessToken.setOauthToken(exchangedToken.getOAuthToken());
		oAuthAccessToken.setOauthTokenSecret(exchangedToken.getOAuthTokenSecret());
		oAuthAccessToken.setUsername(legacyTokenProperties.getProperty("username"));
		oAuthAccessToken.setFullname(legacyTokenProperties.getProperty("fullname"));
		oAuthAccessToken.setNsid(legacyTokenProperties.getProperty("nsid"));

		return oAuthAccessToken;
	}


	/**
	 * Obtain an OAuth request token.
	 * <p>
	 * This method is not, strictly speaking, part of the Flickr API. However, it is part of the process to
	 * obtain an OAuth token, so it is included in the OAuthApi class.
	 * </p>
	 * <p/>
	 * This will create a new OAuth provider object and use it to obtain a request token. The user should be directed
	 * to the returned URL so they can authorized your application. If this is a desktop application, or if there is
	 * no callback URL for Flickr to send the user to after they authorize your application, pass "null". The user
	 * will then get a message that they need to enter a validation code. Your application must prompt them for the
	 * validation code, which you will pass to the getAccessToken method to obtain your access token.
	 * <p/>
	 * Before attempting to get a request token, you must have called init with a valid api key and secret.
	 *
	 * @param callbackUrl the callback URL that the user should be directed to after authorizing the application.
	 * @return URL that the user should be directed to in order to authorize the application.
	 * @throws JinxException if init has not been called, or if there are any errors.
	 */
	public String getOAuthRequestToken(String callbackUrl) throws JinxException {
		String url;
		this.provider = new CommonsHttpOAuthProvider(
				JinxConstants.OAUTH_REQUEST_TOKEN_ENDPOINT_URL,
				JinxConstants.OAUTH_ACCESS_TOKEN_ENDPOINT_URL,
				JinxConstants.OAUTH_AUTHORIZE_WEBSITE_URL);

		if (callbackUrl == null) {
			callbackUrl = OAuth.OUT_OF_BAND;
		}

		try {
			url = provider.retrieveRequestToken(this.jinx.getConsumer(), callbackUrl);
		} catch (Exception e) {
			throw new JinxException("Unable to get request token from Flickr.", e);
		}

		return url;
	}

	/**
	 * Get an OAuth access token.
	 * <p>
	 * This method is not, strictly speaking, part of the Flickr API. However, it is part of the process to
	 * obtain an OAuth token, so it is included in the OAuthApi class.
	 * </p>
	 * <p>
	 * Once you have this access token, it can be saved and used along with your application API Key and API Secret
	 * in the future to initialize Jinx. The OAuth access token will be set in the Jinx instance associated with
	 * this object instance. You do not have to set it after calling this method.
	 * </p>
	 * <p>
	 * You must call getOAuthRequestToken prior to calling this method.
	 * </p>
	 *
	 * @param verificationCode the verification code that Flickr presented after the user authorized the application.
	 * @return access token containing the OAuth token and token secret.
	 * @throws JinxException if the getOAuthRequestToken method has not been called, or if there are any errors.
	 */
	public OAuthAccessToken getOAuthAccessToken(String verificationCode) throws JinxException {
		if (this.provider == null) {
			throw new JinxException("You cannot request the access token until you have called getOAuthRequestToken.");
		}
		OAuthAccessToken oAuthAccessToken = new OAuthAccessToken();
		try {
			this.provider.retrieveAccessToken(this.jinx.getConsumer(), verificationCode);
			HttpParameters parameters = this.provider.getResponseParameters();

			oAuthAccessToken.setOauthToken(this.jinx.getConsumer().getToken());
			oAuthAccessToken.setOauthTokenSecret(this.jinx.getConsumer().getTokenSecret());
			for (Map.Entry<String, SortedSet<String>> entry : parameters.entrySet()) {
				if (entry.getKey().equals("fullname")) {
					oAuthAccessToken.setFullname(entry.getValue().first());
				} else if (entry.getKey().equals("user_nsid")) {
					oAuthAccessToken.setNsid(entry.getValue().first());
				} else if (entry.getKey().equals("username")) {
					oAuthAccessToken.setUsername(entry.getValue().first());
				}
			}

		} catch (Exception e) {
			throw new JinxException("Unable to get access token from Flickr.", e);
		}

		this.jinx.setoAuthAccessToken(oAuthAccessToken);
		return oAuthAccessToken;
	}

	/*
	 * Compute the signature for the API call.
	 * <p/>
	 * This computes a signature for the legacy Flickr auth API. The only time we should need to call this
	 * is when converting a legacy access token to an OAuth access token.
	 * <p/>
	 * Legacy auth spec: http://secure.flickr.com/services/api/auth.spec.html
	 *
	 * @param params    key/value map of parameters. Must be in alphabetic order to
	 *                  conform to signing rules.
	 * @param apiSecret application API secret.
	 * @return MD5 hash for the parameters.
	 * @throws JinxException if there are any errors.
	 */
	private String sign(Map<String, String> params, String apiSecret) throws JinxException {
		StringBuilder authSb = new StringBuilder(apiSecret);
		for (String key : params.keySet()) {
			authSb.append(key).append(params.get(key));
		}

		String apiSig;
		try {
			MessageDigest m = MessageDigest.getInstance("MD5");
			m.reset();
			m.update(authSb.toString().getBytes("UTF-8"));
			byte[] digest = m.digest();
			apiSig = JinxUtils.toHexString(digest);
		} catch (Exception e) {
			throw new JinxException("Error computing MD5 value.", e);
		}

		return apiSig;
	}


	/*
	 * Load legacy properties.
	 * The legacy token object stored its data as an XML properties file. Given the input stream to that file,
	 * we load a Properties object and return it.
	 */
	private Properties loadLegacyTokenProperties(InputStream inputStream) throws JinxException {
		Properties p = new Properties();
		try {
			p.loadFromXML(inputStream);
		} catch (Exception e) {
			throw new JinxException("Unable to load data from input stream.", e);
		}
		return p;
	}
}
