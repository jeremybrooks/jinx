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

package net.jeremybrooks.jinx;

import com.google.gson.Gson;
import net.jeremybrooks.jinx.logger.JinxLogger;
import net.jeremybrooks.jinx.response.Response;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.FlickrApi;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

import java.net.Authenticator;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.URLDecoder;
import java.util.Map;
import java.util.StringTokenizer;

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
 * InputStream in = new FileInputStream(new File("/path/to/legacy/access/token"));
 * <p/>
 * // convert old token to OAuth token
 * OAuthApi oauthApi = new OAuthApi(jinx);
 * OAuthAccessToken oAuthToken = oauthApi.getAccessToken(in);
 * <p/>
 * // save the oauth token for next time
 * oAuthToken.store(new FileOutputStream(new File("/path/to/oauth/token")));
 * </code>
 * </p>
 * <p/>
 * <p>
 * If you need to get a new OAuth access token, you must get a request token, send the user to the URL to
 * grant permission to your application, then use the returned access code to get the access token.
 * This example shows how a desktop app would get an access token. Notice that the user is prompted for
 * the access code, since desktop apps do not have callback URL's:
 * <code>
 * // initialize Jinx
 * Jinx jinx = new Jinx(API_KEY, API_SECRET);
 * <p/>
 * // get a request token, direct the user to the URL, and prompt them for the validation code
 * Token requestToken = jinx.getRequestToken();
 * String url = jinx.getAuthorizationUrl(requestToken, JinxConstants.OAuthPermissions.write);
 * String verificationCode = JOptionPane.showInputDialog("Authorize at \n " + url + "\nand then enter the validation code.");
 * <p/>
 * // exchange the validation code for an access token
 * OAuthAccessToken accessToken = oAuthApi.getAccessToken(requestToken, verificationCode);
 * <p/>
 * // save it somewhere for next time
 * accessToken.store(new FileOutputStream(new File("/path/to/oauth/token")));
 * </code>
 * </p>
 * <p/>
 * <p>
 * Once you have an access token, you can use it the next time. Assuming you have stored it somewhere,
 * using it again is simple:
 * <code>
 * OAuthAccessToken accessToken = new OAuthAccessToken();
 * accessToken.load(new FileInputStream(new File("/path/to/oauth/token)));
 * <p/>
 * Jinx jinx = new Jinx(API_KEY, API_SECRET, accessToken);
 * </code>
 * </p>
 * <p/>
 * By default, Jinx will check the return code and throw a {@link net.jeremybrooks.jinx.JinxException} if it is non-zero.
 * If you wish to disable exceptions on non-zero return codes, you can call this method:
 * <code>
 * jinx.setFlickrErrorThrowsException(false);
 * </code>
 * If you disable this feature, Jinx will still throw a JinxException for other errors, such as network problems or
 * invalid parameters, but you will have to check returned objects to know if Flickr reported an error.
 * <p/>
 * If you wish to see the parameters, request URL's, and responses from Flickr, you can enable verbose logging and
 * set a JinxLogger:
 * <code>
 * JinxLogger.setLogger(new StdoutLogger());
 * jinx.setVerboseLogging(true);
 * </code>
 * The default logger will not do anything, so you must set a logger. You can use the {@link net.jeremybrooks.jinx.logger.StdoutLogger}
 * to log to stdout, or you can implement your own logger. Loggers must implement the {@link net.jeremybrooks.jinx.logger.LogInterface}
 * <p/>
 * * @author jeremyb
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

    private OAuthService oAuthService;
    private Token accessToken;

    private boolean flickrErrorThrowsException;

    private boolean verboseLogging;

    private Proxy proxy;

    private Jinx() {
        // Jinx must be created with a key and secret.
    }

    /**
     * Initialize the Jinx class instance with an API key and secret.
     * <p/>
     * This method is sufficient when you do not have an access token to use yet,
     * such as before you have authenticated your user.
     *
     * @param apiKey    the API key to use.
     * @param apiSecret the API secret to use.
     */
    public Jinx(String apiKey, String apiSecret) {
        this(apiKey, apiSecret, null);
    }

    /**
     * Create an instance of Jinx with an API key, secret, and access token.
     *
     * This method is used when you already have an access token. This is the
     * method you will use when you have previously saved an access token, and
     * do not need to walk the user through authorization again.
     *
     * @param apiKey the API key to use.
     * @param apiSecret the API secret to use.
     * @param oAuthAccessToken the oauth access token.
     */
    public Jinx(String apiKey, String apiSecret, OAuthAccessToken oAuthAccessToken) {
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
        this.oAuthAccessToken = oAuthAccessToken;
        this.flickrErrorThrowsException = true;
        this.setVerboseLogging(false);
        this.gson = new Gson();

        this.oAuthService = new ServiceBuilder().provider(FlickrApi.class).apiKey(apiKey).apiSecret(apiSecret).build();

        if (oAuthAccessToken != null) {
            this.accessToken = new Token(oAuthAccessToken.getOauthToken(), oAuthAccessToken.getOauthTokenSecret());
        }

        this.proxy = Proxy.NO_PROXY;
    }


    /**
     * Set the proxy configuration for Jinx.
     *
     * By default, Jinx will not use a proxy.
     *
     * If there is a proxy configuration set, Jinx will use the proxy for all network operations. If the proxy
     * configuration is null, Jinx will not attempt to use a proxy.
     *
     * @param proxyConfig network proxy configuration, or null to indicate no proxy.
     */
    public void setProxy(final JinxProxy proxyConfig) {
        if (proxyConfig == null) {
            System.clearProperty("https.proxyHost");
            System.clearProperty("https.proxyPort");
            this.proxy = Proxy.NO_PROXY;
        } else if (! JinxUtils.isNullOrEmpty(proxyConfig.getProxyHost())) {
            System.setProperty("https.proxyHost", proxyConfig.getProxyHost());
            System.setProperty("https.proxyPort", Integer.toString(proxyConfig.getProxyPort()));
            this.proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyConfig.getProxyHost(), proxyConfig.getProxyPort()));
            if (!JinxUtils.isNullOrEmpty(proxyConfig.getProxyUser())) {
                Authenticator authenticator = new Authenticator() {
                    public PasswordAuthentication getPasswordAuthentication() {
                        return (new PasswordAuthentication(proxyConfig.getProxyUser(), proxyConfig.getProxyPassword()));
                    }
                };
                Authenticator.setDefault(authenticator);
            }
        }
    }


    /**
     * Determine if Jinx is using a network proxy.
     *
     * @return true if Jinx is using a network proxy, false otherwise.
     */
    public boolean isUseProxy() {
        if (this.proxy.equals(Proxy.NO_PROXY)) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * OAuth workflow, step one: Get a request token.
     *
     * Once a request token is retrieved, you can get the authorization URL
     * by passing the request token to the {@link #getAuthorizationUrl(org.scribe.model.Token, net.jeremybrooks.jinx.JinxConstants.OAuthPermissions)} method.
     *
     * @return oauth request token.
     */
    public Token getRequestToken() {
        return this.oAuthService.getRequestToken();
    }

    /**
     * OAuth workflow, step two: Get an authorization URL.
     *
     * Once you have a request token pass it to this method to get the authorization URL.
     * The user should be directed to the returned URL, where they can authorize your
     * application. Once they have authorized your application, they will receive a
     * verification code, which should be passed to the
     * {@link #getAccessToken(org.scribe.model.Token, String)} method.
     *
     * @param requestToken the request token.
     * @param permissions the permissions your application is requesting.
     * @return authorization URL that the user should be directed to.
     * @throws JinxException if any parameter is null.
     */
    public String getAuthorizationUrl(Token requestToken, JinxConstants.OAuthPermissions permissions) throws JinxException {
        JinxUtils.validateParams(requestToken, permissions);
        String url = this.oAuthService.getAuthorizationUrl(requestToken);
        return url + "&perms=" + permissions.toString();
    }

    /**
     * OAuth workflow, step three: Exchange request token for an access token.
     *
     * After getting the verification code from the user, call this method to exchange the request token for
     * an access token. The returned access token can be saved and used until the user revokes access.
     *
     * @param requestToken request token to exchange for access token.
     * @param verificationCode the verification code.
     * @return access token.
     * @throws JinxException if any parameter is null, or if there are any errors.
     */
    public OAuthAccessToken getAccessToken(Token requestToken, String verificationCode) throws JinxException {
        JinxUtils.validateParams(requestToken, verificationCode);
        try {
            Verifier verifier = new Verifier(verificationCode);
            Token accessToken = this.oAuthService.getAccessToken(requestToken, verifier);
            if (accessToken != null) {
                this.oAuthAccessToken = new OAuthAccessToken();
                this.oAuthAccessToken.setOauthToken(accessToken.getToken());
                this.oAuthAccessToken.setOauthTokenSecret(accessToken.getSecret());

                // parse the raw response to get nsid, username, fullname
                // flickr response looks like this:
                // fullname=Jeremy%20Brooks&oauth_token=72157632924811715-b9b1f0bf94982fba&oauth_token_secret=d25a168a2e923649&user_nsid=85853333%40N00&username=Jeremy%20Brooks
                StringTokenizer tok = new StringTokenizer(accessToken.getRawResponse(), "&");
                while (tok.hasMoreTokens()) {
                    String token = tok.nextToken();
                    int index = token.indexOf("=");
                    String key = token.substring(0, index);
                    String value = URLDecoder.decode(token.substring(index+1), "UTF-8").trim();
                    if (key.equals("fullname")) {
                        this.oAuthAccessToken.setFullname(value);
                    } else if (key.equals("user_nsid")) {
                        this.oAuthAccessToken.setNsid(value);
                    } else if (key.equals("username")) {
                        this.oAuthAccessToken.setUsername(value);
                    }
                }
            }
        } catch (Exception e) {
            throw new JinxException("Error while getting access token.", e);
        }
        return this.oAuthAccessToken;
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

    public OAuthAccessToken getoAuthAccessToken() {
        return this.oAuthAccessToken;
    }

    public void setoAuthAccessToken(OAuthAccessToken oAuthAccessToken) {
        this.oAuthAccessToken = oAuthAccessToken;
        this.accessToken = new Token(oAuthAccessToken.getOauthToken(), oAuthAccessToken.getOauthTokenSecret());
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
        params.put("format", "json");
        params.put("nojsoncallback", "1");
        params.put("api_key", getApiKey());


        org.scribe.model.Response flickrResponse;

        if (method == Method.GET) {
            OAuthRequest request = new OAuthRequest(Verb.GET, JinxConstants.REST_ENDPOINT);
            for (String key : params.keySet()) {
                request.addQuerystringParameter(key, params.get(key));
            }
            if (sign) {
                this.oAuthService.signRequest(this.accessToken, request);
            }
            flickrResponse = request.send();
        } else if (method == Method.POST) {
            OAuthRequest request = new OAuthRequest(Verb.POST, JinxConstants.REST_ENDPOINT);
            for (String key : params.keySet()) {
                request.addBodyParameter(key, params.get(key));
            }
            if (sign) {
                this.oAuthService.signRequest(this.accessToken, request);
            }
            flickrResponse = request.send();
        } else {
            throw new JinxException("Unsupported method: " + method.toString());
        }

        if (flickrResponse == null || flickrResponse.getBody() == null) {
            throw new JinxException("Null return from call to Flickr.");
        }
        if (verboseLogging) {
            JinxLogger.getLogger().log("RESPONSE is " + flickrResponse);
        }

        T fromJson = gson.fromJson(flickrResponse.getBody(), tClass);

        if (this.flickrErrorThrowsException && ((Response) fromJson).getCode() != 0) {
            Response r = (Response) fromJson;
            throw new JinxException("Flickr returned non-zero status.", null, r);
        }
        return fromJson;
    }


    public boolean isFlickrErrorThrowsException() {
        return flickrErrorThrowsException;
    }

    public void setFlickrErrorThrowsException(boolean flickrErrorThrowsException) {
        this.flickrErrorThrowsException = flickrErrorThrowsException;
    }

    public boolean isVerboseLogging() {
        return verboseLogging;
    }

    public void setVerboseLogging(boolean verboseLogging) {
        this.verboseLogging = verboseLogging;
    }
}
