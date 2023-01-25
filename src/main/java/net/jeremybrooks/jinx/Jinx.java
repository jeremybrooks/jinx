/*
 * Jinx is Copyright 2010-2023 by Jeremy Brooks and Contributors
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

import com.github.scribejava.apis.FlickrApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.httpclient.multipart.FileByteArrayBodyPartPayload;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuth1RequestToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth10aService;
import com.google.gson.Gson;
import net.jeremybrooks.jinx.logger.JinxLogger;
import net.jeremybrooks.jinx.response.Response;

import java.io.IOException;
import java.net.Authenticator;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.concurrent.ExecutionException;

import static net.jeremybrooks.jinx.JinxConstants.*;

/**
 * This class contains the information needed to make calls to Flickr.
 * <p>Jinx uses OAuth to sign requests. Your application must have a valid OAuth token. * </p>
 * <p>If you have used Jinx in the past and have a legacy access token, you can convert it to
 * an OAuth access token as follows:
 * <code>
 * // initialize Jinx and an OAuthApi instance
 * Jinx jinx = new Jinx(API_KEY, API_SECRET);
 * <br>
 * // this is where we have saved the legacy access token
 * InputStream in = new FileInputStream(new File("/path/to/legacy/access/token"));
 * <br>
 * // convert old token to OAuth token
 * OAuthApi oauthApi = new OAuthApi(jinx);
 * OAuthAccessToken oAuthToken = oauthApi.getAccessToken(in);
 * <br>
 * // save the oauth token for next time
 * oAuthToken.store(new FileOutputStream(new File("/path/to/oauth/token")));
 * </code>
 * </p>
 * <br>
 * <p>
 * If you need to get a new OAuth access token, you must get a request token, send the user to the URL to
 * grant permission to your application, then use the returned access code to get the access token.
 * This example shows how a desktop app would get an access token. Notice that the user is prompted for
 * the access code, since desktop apps do not have callback URL's:
 * <code>
 * // initialize Jinx with your API key and secret
 * Jinx jinx = new Jinx(API_KEY, API_SECRET);
 * <br>
 * // get a request token, direct the user to the URL, and prompt them for the validation code
 * Token requestToken = jinx.getRequestToken();
 * String url = jinx.getAuthorizationUrl(requestToken, JinxConstants.OAuthPermissions.write);
 * String verificationCode = JOptionPane.showInputDialog("Authorize at \n " + url + "\nand then enter the validation code.");
 * <br>
 * // after the user enters the verification code, exchange for an access token
 * OAuthAccessToken accessToken = oAuthApi.getAccessToken(requestToken, verificationCode);
 * <br>
 * // save it somewhere for next time
 * accessToken.store(new FileOutputStream(new File("/path/to/oauth/token")));
 * </code>
 * </p>
 * <br>
 * <p>
 * Once you have an access token, you can use it the next time. Assuming you have stored it somewhere,
 * using it again is simple:
 * <code>
 * OAuthAccessToken accessToken = new OAuthAccessToken();
 * accessToken.load(new FileInputStream(new File("/path/to/oauth/token)));
 * <br>
 * Jinx jinx = new Jinx(API_KEY, API_SECRET, accessToken);
 * </code>
 * </p>
 * <br>
 * By default, Jinx will check the return code and throw a {@link net.jeremybrooks.jinx.JinxException} if it is non-zero.
 * If you wish to disable exceptions on non-zero return codes, you can call this method:
 * {@code
 * jinx.setFlickrErrorThrowsException(false);
 * }
 * If you disable this feature, Jinx will still throw a JinxException for other errors, such as network problems or
 * invalid parameters, but you will have to check returned objects to know if Flickr reported an error.
 * <br>
 * If you wish to see the parameters, request URL's, and responses from Flickr, you can enable verbose logging and
 * set a JinxLogger:
 * {@code
 * JinxLogger.setLogger(new StdoutLogger());
 * jinx.setVerboseLogging(true);
 * }
 * The default logger will not do anything, so you must set a logger. You can use the {@link net.jeremybrooks.jinx.logger.StdoutLogger}
 * to log to stdout, or you can implement your own logger. Loggers must implement the {@link net.jeremybrooks.jinx.logger.LogInterface}
 * <br>
 * <p>If you are trying to troubleshoot photo upload problems and need to see the content of the multipart request that is used
 * for photo uploads, you must set a JinxLogger, enable verbose logging, and enable multipart logging.</p>
 * * @author Jeremy Brooks
 */
public class Jinx {

  /* The Jinx API key to use. */
  private String apiKey;

  /* The Jinx API secret to use. */
  private String apiSecret;

  private OAuthAccessToken oAuthAccessToken;

  private Gson gson;

  private OAuth10aService oAuthService;
  private OAuth1AccessToken accessToken;

  private boolean flickrErrorThrowsException;

  private boolean verboseLogging;

  private boolean multipartLogging;

  private Proxy proxy;

  private Jinx() {
    // Jinx must be created with a key and secret.
  }

  /**
   * Initialize the Jinx class instance with an API key and secret.
   * <br>
   * This method is sufficient when you do not have an access token to use yet,
   * such as before you have authenticated your user.
   *
   * @param apiKey      the API key to use.
   * @param apiSecret   the API secret to use.
   * @param permissions the requested Flickr permissions.
   */
  public Jinx(String apiKey, String apiSecret, JinxConstants.OAuthPermissions permissions) {
    this(apiKey, apiSecret, null, permissions);
  }

  /**
   * Create an instance of Jinx with an API key, secret, and access token.
   * <br>
   * This method is used when you already have an access token. This is the
   * method you will use when you have previously saved an access token, and
   * do not need to walk the user through authorization again.
   *
   * @param apiKey           the API key to use.
   * @param apiSecret        the API secret to use.
   * @param oAuthAccessToken the oauth access token.
   * @param permissions      the requested Flickr permissions.
   */
  public Jinx(String apiKey, String apiSecret, OAuthAccessToken oAuthAccessToken, JinxConstants.OAuthPermissions permissions) {
    this.apiKey = apiKey;
    this.apiSecret = apiSecret;
    this.oAuthAccessToken = oAuthAccessToken;
    this.flickrErrorThrowsException = true;
    this.setVerboseLogging(false);
    this.setMultipartLogging(false);
    this.gson = new Gson();
    FlickrApi.FlickrPerm flickrPerm;
    switch (permissions) {
      case delete:
        flickrPerm = FlickrApi.FlickrPerm.DELETE;
        break;
      case write:
        flickrPerm = FlickrApi.FlickrPerm.WRITE;
        break;
      case read:
      default:
        flickrPerm = FlickrApi.FlickrPerm.READ;
    }
    this.oAuthService = new ServiceBuilder(apiKey)
        .apiSecret(apiSecret)
        .build(FlickrApi.instance(flickrPerm));

    if (oAuthAccessToken != null) {
      this.accessToken = new OAuth1AccessToken(
          oAuthAccessToken.getOauthToken(), oAuthAccessToken.getOauthTokenSecret());
    }

    this.proxy = Proxy.NO_PROXY;
  }


  /**
   * Set the proxy configuration for Jinx.
   * <br>
   * By default, Jinx will not use a proxy.
   * <br>
   * If there is a proxy configuration set, Jinx will use the proxy for all network operations. If the proxy
   * configuration is null, Jinx will not attempt to use a proxy.
   *
   * @param proxyConfig network proxy configuration, or null to indicate no proxy.
   */
  public void setProxy(final JinxProxy proxyConfig) {
    if (proxyConfig == null) {
      System.clearProperty("http.proxyHost");
      System.clearProperty("http.proxyPort");
      System.clearProperty("https.proxyHost");
      System.clearProperty("https.proxyPort");
      this.proxy = Proxy.NO_PROXY;
    } else if (!JinxUtils.isNullOrEmpty(proxyConfig.getProxyHost())) {
      System.setProperty("http.proxyHost", proxyConfig.getProxyHost());
      System.setProperty("http.proxyPort", Integer.toString(proxyConfig.getProxyPort()));
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
    return !this.proxy.equals(Proxy.NO_PROXY);
  }

  /**
   * OAuth workflow, step one: Get a request token.
   * <br>
   * Once a request token is retrieved, you can get the authorization URL
   * by passing the request token to the {@link #getAuthorizationUrl} method.
   *
   * @return oauth request token.
   * @throws InterruptedException from the OAuth Service.
   * @throws ExecutionException from the OAuth Service.
   * @throws IOException from the OAuth Service.
   */
  public OAuth1RequestToken getRequestToken() throws InterruptedException, ExecutionException, IOException {
    return this.oAuthService.getRequestToken();
  }

  /**
   * OAuth workflow, step two: Get an authorization URL.
   * <br>
   * Once you have a request token pass it to this method to get the authorization URL.
   * The user should be directed to the returned URL, where they can authorize your
   * application. Once they have authorized your application, they will receive a
   * verification code, which should be passed to the {@link #getAccessToken(OAuth1RequestToken, String)} method.
   *
   * @param requestToken the request token.
   * @return authorization URL that the user should be directed to.
   * @throws JinxException if any parameter is null.
   */
  public String getAuthorizationUrl(OAuth1RequestToken requestToken) throws JinxException {
    JinxUtils.validateParams(requestToken);
    return this.oAuthService.getAuthorizationUrl(requestToken);
  }

  /**
   * OAuth workflow, step three: Exchange request token for an access token.
   * <br>
   * After getting the verification code from the user, call this method to exchange the request token for
   * an access token. The returned access token can be saved and used until the user revokes access.
   *
   * @param requestToken     request token to exchange for access token.
   * @param verificationCode the verification code.
   * @return access token.
   * @throws JinxException if any parameter is null, or if there are any errors.
   */
  public OAuthAccessToken getAccessToken(OAuth1RequestToken requestToken, String verificationCode) throws JinxException {
    JinxUtils.validateParams(requestToken, verificationCode);
    try {
      OAuth1AccessToken accessToken = this.oAuthService.getAccessToken(requestToken, verificationCode);
      if (accessToken != null) {
        this.oAuthAccessToken = new OAuthAccessToken();
        this.oAuthAccessToken.setOauthToken(accessToken.getToken());
        this.oAuthAccessToken.setOauthTokenSecret(accessToken.getTokenSecret());

        // parse the raw response to get nsid, username, fullname
        // flickr response looks like this:
        // fullname=Jeremy%20Brooks&oauth_token=72157632924811715-b9b1f0bf94982fba&oauth_token_secret=d25a168a2e923649&user_nsid=85853333%40N00&username=Jeremy%20Brooks
        StringTokenizer tok = new StringTokenizer(accessToken.getRawResponse(), "&");
        while (tok.hasMoreTokens()) {
          String token = tok.nextToken();
          int index = token.indexOf("=");
          String key = token.substring(0, index);
          String value = URLDecoder.decode(token.substring(index + 1), StandardCharsets.UTF_8).trim();
          switch (key) {
            case "fullname":
              this.oAuthAccessToken.setFullname(value);
              break;
            case "user_nsid":
              this.oAuthAccessToken.setNsid(value);
              break;
            case "username":
              this.oAuthAccessToken.setUsername(value);
              break;
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
   * @throws JinxException if the api key is null.
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
   * @throws JinxException if the api secret is null.
   */
  public String getApiSecret() throws JinxException {
    if (this.apiSecret == null) {
      throw new JinxException("Missing API secret. Please initialize Jinx.");
    }
    return this.apiSecret;
  }

  /**
   * Get the oauth access token.
   *
   * @return oauth access token.
   */
  public OAuthAccessToken getoAuthAccessToken() {
    return this.oAuthAccessToken;
  }

  /**
   * Set the oauth access token.
   *
   * @param oAuthAccessToken the oauth access token.
   */
  public void setoAuthAccessToken(OAuthAccessToken oAuthAccessToken) {
    this.oAuthAccessToken = oAuthAccessToken;
    this.accessToken = new OAuth1AccessToken(oAuthAccessToken.getOauthToken(), oAuthAccessToken.getOauthTokenSecret());
  }

  /**
   * Indicates if Flickr API errors will cause a JinxException to be thrown.
   * <br>
   * This is true by default, since you will almost always want to know when Flickr thinks something went wrong.
   *
   * @return true if Flickr API errors will cause a JinxException.
   */
  public boolean isFlickrErrorThrowsException() {
    return flickrErrorThrowsException;
  }

  /**
   * Set the Jinx behavior when there are Flickr API errors.
   *
   * @param flickrErrorThrowsException if true, a Flickr API error will cause a JinxException to be thrown.
   */
  public void setFlickrErrorThrowsException(boolean flickrErrorThrowsException) {
    this.flickrErrorThrowsException = flickrErrorThrowsException;
  }

  /**
   * Indicates if verbose logging is enabled.
   * <br>
   * By default, this is false. If you want verbose logging, you need to set this to true and set a
   * {@link net.jeremybrooks.jinx.logger.JinxLogger} that is used to do the logging.
   * <br>
   * Verbose logging will cause the GET and POST parameters to be logged, as well as the response from Flickr.
   * If you want to see the multipart POST body (used when uploading and replacing photos), you should also set a
   * special system property:
   * <br>
   * {@code System.setProperty(JinxConstants.JINX_LOG_MULTIPART, "true");}
   *
   * @return true if verbose logging is enabled.
   */
  public boolean isVerboseLogging() {
    return verboseLogging;
  }

  /**
   * Indicates if multipart logging is enabled.
   * <br>
   * By default, this is false. If you want to see multipart body content logging, you need to set this
   * to true and set a {@link net.jeremybrooks.jinx.logger.JinxLogger} that is used to do the logging.
   * <br>
   * Multipart logging will cause the multipart POST body to be logged. You must also set verbose logging
   * to true.
   *
   * @return true if verbose logging is enabled.
   */
  public boolean isMultipartLogging() {
    return this.multipartLogging;
  }

  /**
   * Set the verbose logging flag.
   * <p>
   * You must set a {@link JinxLogger} before setting this flag to {@code true};
   *
   * @param verboseLogging true to enable verbose logging.
   */
  public void setVerboseLogging(boolean verboseLogging) {
    this.verboseLogging = verboseLogging;
  }

  /**
   * Set the multipart body logging flag.
   * <p>
   * You must set a {@link JinxLogger} before setting this flag to {@code true};
   *
   * @param multipartLogging true to enable multipart body logging.
   */
  public void setMultipartLogging(boolean multipartLogging) {
    this.multipartLogging = multipartLogging;
  }

  /**
   * Call Flickr, returning the specified class deserialized from the Flickr response.
   * <br>
   * This will make a signed call to Flickr using http GET.
   * <p>
   * Do not call this method directly. The classes in the net.jeremybrooks.jinx.api package will call this.
   *
   * @param params request parameters.
   * @param tClass the class that will be returned.
   * @param <T>    type of the class returned.
   * @return an instance of the specified class containing data from Flickr.
   * @throws JinxException if there are any errors.
   */
  public <T> T flickrGet(Map<String, String> params, Class<T> tClass) throws JinxException {
    return callFlickr(params, Method.GET, tClass, true);
  }

  /**
   * Call Flickr, returning the specified class deserialized from the Flickr response.
   * <br>
   * This will make a call to Flickr using http GET. The caller can specify if the request should be signed.
   * <p>
   * Do not call this method directly. The classes in the net.jeremybrooks.jinx.api package will call this.
   *
   * @param params request parameters.
   * @param tClass the class that will be returned.
   * @param <T>    type of the class returned.
   * @param sign   if true the request will be signed.
   * @return an instance of the specified class containing data from Flickr.
   * @throws JinxException if there are any errors.
   */
  public <T> T flickrGet(Map<String, String> params, Class<T> tClass, boolean sign) throws JinxException {
    return callFlickr(params, Method.GET, tClass, sign);
  }


  /**
   * Call Flickr, returning the specified class deserialized from the Flickr response.
   * <br>
   * This will make a signed call to Flickr using http POST.
   * <p>
   * Do not call this method directly. The classes in the net.jeremybrooks.jinx.api package will call this.
   *
   * @param params request parameters.
   * @param tClass the class that will be returned.
   * @param <T>    type of the class returned.
   * @return an instance of the specified class containing data from Flickr.
   * @throws JinxException if there are any errors.
   */
  public <T> T flickrPost(Map<String, String> params, Class<T> tClass) throws JinxException {
    return callFlickr(params, Method.POST, tClass, true);
  }


  /**
   * Call Flickr, returning the specified class deserialized from the Flickr response.
   * <br>
   * This will make a call to Flickr using http POST. The caller can specify if the request should be signed.
   * <p>
   * Do not call this method directly. The classes in the net.jeremybrooks.jinx.api package will call this.
   *
   * @param params request parameters.
   * @param tClass the class that will be returned.
   * @param <T>    type of the class returned.
   * @param sign   if true the request will be signed.
   * @return an instance of the specified class containing data from Flickr.
   * @throws JinxException if there are any errors.
   */
  public <T> T flickrPost(Map<String, String> params, Class<T> tClass, boolean sign) throws JinxException {
    return callFlickr(params, Method.POST, tClass, sign);
  }


  /**
   * Do the actual GET or POST request.
   * <br>
   * flickrGet and flickrPost methods delegate work to this method.
   *
   * @param params request parameters.
   * @param method http method to use. Method.GET and Method. POST are the only valid choices.
   * @param tClass the class that will be returned.
   * @param <T>    type of the class returned.
   * @param sign   if true the request will be signed.
   * @return an instance of the specified class containing data from Flickr.
   * @throws JinxException if there are any errors.
   */
  protected <T> T callFlickr(Map<String, String> params, Method method, Class<T> tClass, boolean sign) throws JinxException {
    if (this.oAuthAccessToken == null) {
      throw new JinxException("Jinx has not been configured with an OAuth Access Token.");
    }
    params.put("format", "json");
    params.put("nojsoncallback", "1");
    params.put("api_key", getApiKey());

    com.github.scribejava.core.model.Response flickrResponse;
    T fromJson;
    try {
      if (method == Method.GET) {
        OAuthRequest request = new OAuthRequest(Verb.GET, JinxConstants.REST_ENDPOINT);
        for (String key : params.keySet()) {
          request.addQuerystringParameter(key, params.get(key));
          if (verboseLogging) {
            JinxLogger.getLogger().log(String.format("Added query parameter %s=%s", key, params.get(key)));
          }
        }
        if (sign) {
          this.oAuthService.signRequest(this.accessToken, request);
        }
        flickrResponse = this.oAuthService.execute(request);
      } else if (method == Method.POST) {
        OAuthRequest request = new OAuthRequest(Verb.POST, JinxConstants.REST_ENDPOINT);
        for (String key : params.keySet()) {
          request.addBodyParameter(key, params.get(key));
          if (verboseLogging) {
            JinxLogger.getLogger().log(String.format("Added body parameter %s=%s", key, params.get(key)));
          }
        }
        if (sign) {
          this.oAuthService.signRequest(this.accessToken, request);
        }
        flickrResponse = this.oAuthService.execute(request);
      } else {
        throw new JinxException("Unsupported method: " + method.toString());
      }

      if (flickrResponse == null || flickrResponse.getBody() == null) {
        throw new JinxException("Null return from call to Flickr.");
      }
      if (verboseLogging) {
        JinxLogger.getLogger().log("RESPONSE is " + flickrResponse.getBody());
      }

      fromJson = gson.fromJson(flickrResponse.getBody(), tClass);

      if (this.flickrErrorThrowsException && ((Response) fromJson).getCode() != 0) {
        Response r = (Response) fromJson;
        throw new JinxException("Flickr returned non-zero status.", null, r);
      }
    } catch (JinxException je) {
      throw je;
    } catch (Exception e) {
      throw new JinxException(String.format("Error executing %s.", method), e);
    }
    return fromJson;
  }


  /**
   * Upload a photo or video to Flickr.
   * <br>
   * Do not call this directly. Use the {@link net.jeremybrooks.jinx.api.PhotosUploadApi} class.
   *
   * @param params    request parameters.
   * @param photoData photo or video data to upload.
   * @param tClass    the class that will be returned.
   * @param <T>       type of the class returned.
   * @return an instance of the specified class containing data from Flickr.
   * @throws JinxException if there are any errors.
   */
  public <T> T flickrUpload(Map<String, String> params, byte[] photoData, Class<T> tClass) throws JinxException {
    if (this.oAuthAccessToken == null) {
      throw new JinxException("Jinx has not been configured with an OAuth Access Token.");
    }
    params.put("api_key", getApiKey());
    return uploadOrReplace(params, photoData, tClass, new OAuthRequest(Verb.POST, FLICKR_PHOTO_UPLOAD_URL));
  }

  /**
   * Replace a photo or video on Flickr.
   * <br>
   * Do not call this directly. Use the {@link net.jeremybrooks.jinx.api.PhotosUploadApi} class.
   *
   * @param params    request parameters.
   * @param photoData photo or video data to replace existing data.
   * @param tClass    the class that will be returned.
   * @param <T>       type of the class returned.
   * @return an instance of the specified class containing data from Flickr.
   * @throws JinxException if there are any errors.
   */
  public <T> T flickrReplace(Map<String, String> params, byte[] photoData, Class<T> tClass) throws JinxException {
    if (this.oAuthAccessToken == null) {
      throw new JinxException("Jinx has not been configured with an OAuth Access Token.");
    }
    params.put("api_key", getApiKey());
    return uploadOrReplace(params, photoData, tClass, new OAuthRequest(Verb.POST, FLICKR_PHOTO_REPLACE_URL));
  }


  /**
   * Handle Flickr upload and replace API calls.
   * <br>
   * The action taken will depend on the OAuth request object that is passed in. The upload and replace methods
   * delegate to this method. This method does the work to ensure that the OAuth signature is generated correctly
   * according to Flickr's requirements for uploads.
   *
   * @param params    request parameters.
   * @param photoData the data to send to Flickr.
   * @param tClass    the class that will be returned.
   * @param <T>       type of the class returned.
   * @param request   the OAuthRequest object to use.
   * @return an instance of the specified class containing data from Flickr.
   * @throws JinxException if there are any errors.
   */
  protected <T> T uploadOrReplace(Map<String, String> params, byte[] photoData, Class<T> tClass, OAuthRequest request) throws JinxException {
    String boundary = JinxUtils.generateBoundary();
    request.initMultipartPayload(boundary);

    request.addHeader("Content-Type", "multipart/form-data; boundary=" + boundary);
    params.forEach(request::addQuerystringParameter);
    this.oAuthService.signRequest(this.accessToken, request);
    request.addBodyPartPayloadInMultipartPayload(new FileByteArrayBodyPartPayload(
            "form-data", photoData, "photo",
            params.getOrDefault("filename", "photo.jpg")));

    T fromJson;
    try {
      com.github.scribejava.core.model.Response flickrResponse = this.oAuthService.execute(request);

      if (flickrResponse == null || flickrResponse.getBody() == null) {
        throw new JinxException("Null return from call to Flickr.");
      }
      if (verboseLogging) {
        JinxLogger.getLogger().log("RESPONSE is " + flickrResponse.getBody());
      }

      // upload returns XML, so convert to json
      String json = JinxUtils.xml2json(flickrResponse.getBody());

      fromJson = gson.fromJson(json, tClass);
      if (this.flickrErrorThrowsException && ((Response) fromJson).getCode() != 0) {
        Response r = (Response) fromJson;
        throw new JinxException("Flickr returned non-zero status.", null, r);
      }
    } catch (JinxException je) {
      throw je;
    } catch (Exception e) {
      throw new JinxException("Error executing upload/replace.", e);
    }
    return fromJson;
  }
}
