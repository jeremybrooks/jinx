/*
 * Jinx is Copyright 2010-2013 by Jeremy Brooks and Contributors
 *
 * This file is part of Jinx.
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
package net.jeremybrooks.jinx;

import com.google.gson.Gson;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.basic.DefaultOAuthConsumer;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import static net.jeremybrooks.jinx.JinxConstants.Method;

/**
 * This class contains the information needed to make calls to Flickr.
 * <p/>
 * <p>
 * Jinx uses OAuth to sign requests. Your application must have a valid OAuth token.
 * </p>
 * <p/>
 * <p>
 * If you have used Jinx in the past and have a legacy access token, you can convert it to
 * an OAuth access token as follows:
 * <code>
 * // initialize Jinx and an OAuthApi instance
 * Jinx jinx = new Jinx(API_KEY, API_SECRET);
 * <p/>
 * // this is where we have saved the legacy access token
 * InputStream in = new FileInputStream(new File(filename));
 * <p/>
 * // convert old token to OAuth token
 * OAuthApi oauthApi = new OAuthApi(jinx);
 * OAuthAccessToken oAuthToken = oauthApi.getAccessToken(in);
 * <p/>
 * // save the oauth token for next time
 * oAuthToken.store(new FileOutputStream(new File(filename)));
 * </code>
 * </p>
 * <p/>
 * <p>
 * If you need to get a new OAuth access token, you must get a request token, send the user to the URL to
 * grant permission to your application, then use the returned access code to get the access token.
 * This example shows how a desktop app would get an access token. Notice that the user is prompted for
 * the access code, since desktop apps do not have callback URL's:
 * <code>
 * // initialize Jinx and an OAuthApi instance
 * Jinx jinx = new Jinx(API_KEY, API_SECRET);
 * OAuthApi oAuthApi = new OAuthApi(jinx);
 * <p/>
 * // get a request token, direct the user to the URL, and prompt them for the validation code
 * String url = oAuthApi.getOAuthRequestToken(null);
 * String verificationCode = JOptionPane.showInputDialog("Authorize at \n " + url + "\nand then enter the validation code.");
 * <p/>
 * // exchange the validation code for an access token
 * OAuthAccessToken oAuthToken = oAuthApi.getOAuthAccessToken(verificationCode);
 * <p/>
 * // save it somewhere for next time
 * oAuthToken.store(new FileOutputStream(new File(filename)));
 * </code>
 * </p>
 * <p/>
 * <p>
 * Once you have an access token, you can use it the next time. Assuming you have stored it somewhere,
 * using it again is simple:
 * <code>
 * OAuthAccessToken oAuthToken = new OAuthAccessToken();
 * oAuthToken.load(new FileInputStream(new File(filename)));
 * <p/>
 * Jinx jinx = new Jinx(API_KEY, API_SECRET, oAuthToken);
 * </code>
 * </p>
 *
 * @author jeremyb
 */
public class Jinx {

	/**
	 * The Jinx API key to use.
	 */
	private String apiKey;

	/**
	 * The Jinx API secret to use.
	 */
	private String apiSecret;

	private OAuthAccessToken oAuthAccessToken;

	private Gson gson;

	private OAuthConsumer consumer;


	private Jinx() {
		// Jinx must be created with a key and secret.
	}

	/**
	 * Initialize the Jinx class instance with an API key and secret.
	 * <p/>
	 * You must call one of the init methods before using this class.
	 * <p/>
	 * This method is sufficient when you do not have an access token to use yet,
	 * such as before you have authenticated your user. Once you have an access
	 * token, you must call setAccessToken before making calls that require
	 * authentication.
	 *
	 * @param apiKey    the API key to use.
	 * @param apiSecret the API secret to use.
	 */
	public Jinx(String apiKey, String apiSecret) {
		this(apiKey, apiSecret, null);
	}


	public Jinx(String apiKey, String apiSecret, OAuthAccessToken oAuthAccessToken) {
		this.apiKey = apiKey;
		this.apiSecret = apiSecret;
		this.oAuthAccessToken = oAuthAccessToken;

		this.gson = new Gson();

		this.consumer = new DefaultOAuthConsumer(apiKey, apiSecret);
		if (oAuthAccessToken != null) {
			consumer.setTokenWithSecret(oAuthAccessToken.getOauthToken(), oAuthAccessToken.getOauthTokenSecret());
		}
	}


	public OAuthConsumer getConsumer() {
		return this.consumer;
	}


	/**
	 * Get the API key.
	 *
	 * @return API key.
	 */
	public String getApiKey() throws JinxException {
		if (this.apiKey == null) {
			throw new JinxException("Missing API key. Please initialize Jinx.");
		}
		return this.apiKey;
	}


	/**
	 * Get the API secret.
	 *
	 * @return API secret.
	 */
	public String getApiSecret() throws JinxException {
		if (this.apiSecret == null) {
			throw new JinxException("Missing API secret. Please initialize Jinx.");
		}
		return this.apiSecret;
	}

	public OAuthAccessToken oAuthAccessToken() {
		return this.oAuthAccessToken;
	}

	public void setoAuthAccessToken(OAuthAccessToken oAuthAccessToken) {
		this.oAuthAccessToken = oAuthAccessToken;
		this.consumer.setTokenWithSecret(oAuthAccessToken.getOauthToken(), oAuthAccessToken.getOauthTokenSecret());
	}


	/**
	 * Call Flickr, returning the specified class deserialized from the Flickr response.
	 * <p/>
	 * This will make a signed call to Flickr using http GET.
	 *
	 * @param params
	 * @param tClass
	 * @param <T>
	 * @return
	 * @throws JinxException
	 */
	public <T> T flickrGet(Map<String, String> params, Class<T> tClass) throws JinxException {
		return callFlickr(params, Method.GET, tClass, true);
	}

	/**
	 * Call Flickr, returning the specified class deserialized from the Flickr response.
	 * <p/>
	 * This will make a call to Flickr using http GET. The caller can specify if the request should be signed.
	 *
	 * @param params
	 * @param tClass
	 * @param sign
	 * @param <T>
	 * @return
	 * @throws JinxException
	 */
	public <T> T flickrGet(Map<String, String> params, Class<T> tClass, boolean sign) throws JinxException {
		return callFlickr(params, Method.GET, tClass, sign);
	}


	/**
	 * Call Flickr, returning the specified class deserialized from the Flickr response.
	 * <p/>
	 * This will make a signed call to Flickr using http POST.
	 *
	 * @param params
	 * @param tClass
	 * @param <T>
	 * @return
	 * @throws JinxException
	 */
	public <T> T flickrPost(Map<String, String> params, Class<T> tClass) throws JinxException {
		return callFlickr(params, Method.POST, tClass, true);
	}


	/**
	 * Call Flickr, returning the specified class deserialized from the Flickr response.
	 * <p/>
	 * This will make a call to Flickr using http GET. The caller can specify if the request should be signed.
	 *
	 * @param params
	 * @param tClass
	 * @param sign
	 * @param <T>
	 * @return
	 * @throws JinxException
	 */
	public <T> T flickrPost(Map<String, String> params, Class<T> tClass, boolean sign) throws JinxException {
		return callFlickr(params, Method.POST, tClass, sign);
	}


	/**
	 * @param params
	 * @param method
	 * @param tClass
	 * @param sign
	 * @param <T>
	 * @return
	 * @throws JinxException
	 */
	protected <T> T callFlickr(Map<String, String> params, Method method, Class<T> tClass, boolean sign) throws JinxException {
		if (this.oAuthAccessToken == null) {
			throw new JinxException("Jinx has not been configured with an OAuth Access Token.");
		}
		String json;
		params.put("format", "json");
		params.put("nojsoncallback", "1");
		params.put("api_key", getApiKey());


		StringBuilder sb = new StringBuilder();
		for (String key : params.keySet()) {
			try {
				// TODO: LOGGING
				sb.append(URLEncoder.encode(key, "UTF-8")).append('=').append(URLEncoder.encode(params.get(key), "UTF-8")).append('&');
			} catch (Exception e) {
				throw new JinxException("Error encoding.", e);
			}
		}
		sb.deleteCharAt(sb.lastIndexOf("&"));

		if (method == Method.POST) {
//			Map<String, String> encodedParams = new HashMap<String, String>();
//			for (String key : params.keySet()) {
//				try {
//					encodedParams.put(URLEncoder.encode(key, UTF8), URLEncoder.encode(params.get(key), UTF8));
//				} catch (Exception e) {
//					throw new JinxException("Error encoding.", e);
//				}
//			}
			json = this.doPost(JinxConstants.REST_ENDPOINT, sb.toString(), sign);
		} else if (method == Method.GET) {
			System.out.println(sb.toString());
			json = this.doGet(JinxConstants.REST_ENDPOINT, sb.toString(), sign);
		} else {
			throw new JinxException("Unsupported method: " + method);
		}

		if (json == null) {
			throw new JinxException("Null return from call to Flickr.");
		}
		System.out.println(json);
		return gson.fromJson(json, tClass);
	}


	protected String doGet(String endpoint, String requestParameters, boolean sign) throws JinxException {
		String result = null;
		BufferedReader reader = null;
		if (endpoint.startsWith("https://")) {
			try {
				String urlStr = endpoint;
				if (requestParameters != null && requestParameters.length() > 0) {
					urlStr += "?" + requestParameters;
				}
				System.out.println("sendGetRequest URL is " + urlStr);
//				JinxLogger.getLogger().log("sendGetRequest URL is " + urlStr);

				URL url = new URL(urlStr);
				HttpURLConnection request = (HttpURLConnection) url.openConnection();

				if (sign) {
					this.consumer.sign(request);
				}

				request.connect();

				// Get the response
				reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
				StringBuilder sb = new StringBuilder();
				String line;
				while ((line = reader.readLine()) != null) {
					sb.append(line);
				}
				result = sb.toString();
			} catch (Exception e) {
				throw new JinxException("Error while performing https GET operation.", e);
			} finally {
				JinxUtils.close(reader);
			}
		}
		return result;
	}

	protected String doPost(String endpoint, String requestParameters, boolean sign) throws JinxException {
		DataOutputStream out = null;
		BufferedReader in = null;
		StringBuilder sb = new StringBuilder();

		try {

			// Send data

//			//----
//			StringBuilder sb2 = new StringBuilder(endpoint).append('?');
//									for (String key : encodedParams.keySet()) {
//										sb2.append(key).append('=').append(encodedParams.get(key)).append('&');
//									}
//									sb2.deleteCharAt(sb2.lastIndexOf("&"));
//			//----
//
//
//			URL url = new URL(sb2.toString());
//			HttpURLConnection request = (HttpURLConnection) url.openConnection();
//
////			for (String key : encodedParams.keySet()) {
////				request.addRequestProperty(key, encodedParams.get(key));
////			}

			String urlStr = endpoint;
							if (requestParameters != null && requestParameters.length() > 0) {
								urlStr += "?" + requestParameters;
							}
			//					JinxLogger.getLogger().log("sendGetRequest URL is " + urlStr);

							URL url = new URL(urlStr);
							HttpURLConnection request = (HttpURLConnection) url.openConnection();


			request.setDoInput(true);
			request.setDoOutput(true);
			request.setUseCaches(false);
			request.setConnectTimeout(30000);
			request.setReadTimeout(600000);    // 10 minutes

			if (sign) {
				this.consumer.sign(request);
			}

			request.connect();

//			out = new DataOutputStream(request.getOutputStream());
//			out.writeBytes(data);
//			out.flush();

			// Get the response
			in = new BufferedReader(new InputStreamReader(request.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				sb.append(line);
			}

		} catch (Exception e) {
			throw new JinxException("Error while performing http POST operation.", e);
		} finally {
			JinxUtils.close(out);
			JinxUtils.close(in);
		}

		return sb.toString();
	}
}
