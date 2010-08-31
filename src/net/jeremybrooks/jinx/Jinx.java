/*
 * Jinx is Copyright 2010 by Jeremy Brooks
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

import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.Map;
import net.jeremybrooks.jinx.dto.Token;
import net.jeremybrooks.jinx.HTTPRequestPoster;
import org.w3c.dom.Document;

/**
 * This class contains the information needed to make calls to Flickr.
 *
 * Before you can make any calls to the API, you need to initialize this class
 * with at least the API key and secret for your application:
 * <code>
 * Jinx f = Jinx.getInstance();
 * f.init(API_KEY, API_SECRET);
 * </code>
 *
 * If you have obtained an auth token previously, you can initialize this class
 * with the token at the same time:
 * <code>
 * Jinx f = Jinx.getInstance();
 * f.init(API_KEY, API_SECRET, token);
 * </code>
 *
 * If you do not have a token, you will need to obtain one by going through the
 * authorization process with the user, then set the token. This can be done
 * as follows:
 * <code>
 * Jinx f = Jinx.getInstance();
 * f.init(API_KEY, API_SECRET);
 *
 * // permissions are PERMS_READ, PERMS_WRITE, or PERMS_DELETE
 * // it is best to obtain the minimum permission level that your application
 * // will need.
 * Frob frob = AuthApi.getInstance().getFrob(JinxConstants.PERMS_READ);
 *
 * // direct the user to the login url so they can authorize your application
 * // depending on your application, you may want to do this in a GUI dialog
 * System.out.println("Go to this URL to authorize the application:");
 * System.out.println(frob.getLoginUrl());
 *
 * System.out.print("When you have authorized the app, press Enter to continue...");
 * System.in.read();
 *
 * Token token = AuthApi.getInstance().getToken(frob);
 * if (token.getStat().equals("ok")) {
 *     System.out.println("Authorization successful.");
 *     File file = new File(path_to_store_auth_token);
 *     token.store(file);
 *
 *     // put the token in the Jinx class instance
 *     f.setToken(token);
 *
 *     // now you can do all the cool stuff....
 * 
 *  } else {
 *     System.out.println("Authorization failed.");
 *     System.out.println("Error code: " + token.getErrorCode() +
 *         " - " + token.getErrorMessage());
 *     System.exit(1);
 *  }
 * </code>
 *
 * @author jeremyb
 */
public class Jinx {

    /** Reference to the only instance of this class. */
    private static Jinx instance = null;

    /** The Jinx API key to use. */
    private String apiKey;

    /** The Jinx API secret to use. */
    private String apiSecret;

    /** The auth token to use when making authenticated calls. */
    private Token token;


    /**
     * Private constructor.
     */
    private Jinx() {
    }


    /**
     * Get a reference to the only instance of this class.
     *
     * @return reference to the only instance of this class.
     */
    public static Jinx getInstance() {
	if (instance == null) {
	    instance = new Jinx();
	}

	return instance;
    }


    /**
     * Initialize the Jinx class instance with an API key and secret.
     *
     * You must call one of the init methods before using this class.
     *
     * This method is sufficient when you do not have an auth token to use yet,
     * such as before you have authenticated your user. Once you have an auth
     * token, you must call setToken before making calls that require
     * authentication.
     *
     * @param apiKey the API key to use.
     * @param apiSecret the API secret to use.
     */
    public void init(String apiKey, String apiSecret) {
	this.apiKey = apiKey;
	this.apiSecret = apiSecret;
    }

    /**
     * Initialize the Jinx class instance with an API key and secret.
     *
     * You must call one of the init methods before using this class.
     *
     * This method can be called to initialize all the required parameters at
     * once. If you have loaded an auth token from the filesystem or from other
     * persistent storage, you can pass it in here.
     *
     * @param apiKey the API key to use.
     * @param apiSecret the API secret to use.
     * @param token the auth token object to use.
     */
    public void init(String apiKey, String apiSecret, Token token) {
	this.apiKey = apiKey;
	this.apiSecret = apiSecret;
	this.token = token;
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


    /**
     * Make a Jinx API call.
     *
     * The call will be made using HTTP GET, the auth token will be included
     * in the parameters, and the call will be signed.
     *
     * @param params the parameters to use for the API call.
     * @return results of the API call.
     * @throws JinxException if there are any errors.
     */
    public Document callFlickr(Map<String, String> params) throws JinxException {
	return this.callFlickr(params, true, false);

    }

    /**
     * Make a Jinx API call.
     *
     * The call will be made using HTTP GET.
     * 
     * @param params the parameters to use for the API call.
     * @param auth if true, the auth token parameter will be added to the call,
     *        and the call will be signed.
     * @return results of the API call.
     * @throws JinxException if there are any errors.
     */
    public Document callFlickr(Map<String, String> params, boolean auth) throws JinxException {
	return this.callFlickr(params, auth, false);

    }

    /**
     * Make a Jinx API call.
     * 
     * @param params the parameters to use for the API call.
     * @param auth if true, the auth token parameter will be added to the call,
     *        and the call will be signed.
     * @param post if true, the call will be made using HTTP POST, otherwise
     *        the call will be made using HTTP GET.
     * @return results of the API call.
     * @throws JinxException if there are any errors.
     */
    public Document callFlickr(Map<String, String> params, boolean auth, boolean post) throws JinxException {
	String sig = null;
	String response = null;

	if (auth) {
	    // If we have a valid token, add the auth_token parameter
	    // This should be the case except when doing the initial auth
	    if (Jinx.getInstance().getToken() != null) {
		params.put("auth_token", Jinx.getInstance().getToken().getToken());
	    }
	    
	    sig = sign(params);
	}

	StringBuilder sb = new StringBuilder();
	for (String key : params.keySet()) {
	    //sb.append(key).append('=').append(params.get(key)).append('&');
	    try {
//		System.out.println("*********************************");
//		System.out.println("Encoding key '" + key + "' and value '" + params.get(key) + "'");
		sb.append(URLEncoder.encode(key, "UTF-8")).append('=').append(URLEncoder.encode(params.get(key), "UTF-8")).append('&');
	    } catch (Exception e) {
		throw new JinxException("Error encoding.", e);
	    }
	}
	if (auth) {
	    sb.append("api_sig=").append(sig);
	} else {
	    sb.deleteCharAt(sb.lastIndexOf("&"));
	}

	String args = sb.toString();

	if (post) {
	    response = HTTPRequestPoster.post(JinxConstants.REST_ENDPOINT, args);
	} else {
	    response = HTTPRequestPoster.sendGetRequest(JinxConstants.REST_ENDPOINT, args);
	}

//System.out.println(response);
	return JinxUtils.parseStatus(response);
    }


    /**
     * Compute the MD5 hash of the parameter string.
     *
     * @param params the parameter string to compute the hash for.
     * @return MD5 hash for the parameter string.
     * @throws JinxException if there are any errors.
     */
    public String sign(String params) throws JinxException {
	String apiSig = null;
	try {
	    MessageDigest m = MessageDigest.getInstance("MD5");
	    m.reset();
	    m.update(params.getBytes("UTF-8"));
	    byte[] digest = m.digest();
	    apiSig = JinxUtils.toHexString(digest);
	} catch (Exception e) {
	    throw new JinxException("Error computing MD5 value.", e);
	}

	return apiSig;
    }


    /**
     * Compute the MD5 sum for the named parameters.
     *
     * The parameters are first appended to each other as keyvaluekeyvalue....
     * then the MD5 hash is calculated.
     * 
     * @param params key/value map of parameters. Must be in alphabetic order to
     *        conform to Jinx signing rules.
     * @return MD5 hash for the parameters.
     * @throws JinxException if there are any errors.
     */
    public String sign(Map<String, String> params) throws JinxException {
	StringBuilder authSb = new StringBuilder(Jinx.getInstance().getApiSecret());
	for (String key : params.keySet()) {
	    authSb.append(key).append(params.get(key));
	}

	return sign(authSb.toString());
    }


    /**
     * Get the auth token.
     *
     * @return the auth token.
     */
    public Token getToken() {
	return token;
    }


    /**
     * Set the auth token.
     *
     * @param token the auth token to set
     */
    public void setToken(Token token) {
	this.token = token;
    }


    

}
