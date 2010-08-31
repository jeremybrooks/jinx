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
package net.jeremybrooks.jinx.api;

import java.util.Map;
import java.util.TreeMap;
import net.jeremybrooks.jinx.JinxConstants;
import net.jeremybrooks.jinx.JinxException;
import net.jeremybrooks.jinx.Jinx;
import net.jeremybrooks.jinx.JinxUtils;
import net.jeremybrooks.jinx.dto.Frob;
import net.jeremybrooks.jinx.dto.Token;
import org.w3c.dom.Document;

/**
 * Access the Jinx Auth API methods.
 *
 *
 *
 * @author jeremyb
 */
public class AuthApi {

    /** Reference to the instance of AuthApi. */
    private static AuthApi instance = null;

    /**
     * Singleton pattern; no public instantiation.
     */
    private AuthApi() {
    }


    /**
     * Gets the reference to the only instance of this class.
     *
     * @return reference to the only instance of AuthApi.
     */
    public static AuthApi getInstance() {
	if (AuthApi.instance == null) {
	    AuthApi.instance = new AuthApi();
	}
	
	return AuthApi.instance;
    }

    
    /**
     * Returns a frob to be used during authentication.
     *
     * @param perms the permission level to request.
     * @return frob object.
     * @throws JinxException if there are any errors.
     */
    public Frob getFrob(String perms) throws JinxException {
	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.auth.getFrob");
	params.put("perms", perms);
	params.put("api_key", Jinx.getInstance().getApiKey());

	Document doc = Jinx.getInstance().callFlickr(params, true);
	
	Frob frob = new Frob();
	
	frob.setFrob(JinxUtils.getValueByXPath(doc, "/rsp/frob"));

	// build the login URL
	// looks like this:
	// http://flickr.com/services/auth/?api_key=apikey&api_sig=apisig&perms=perms&frob=frob

	params.clear();
	params.put("api_key", Jinx.getInstance().getApiKey());
	params.put("perms", perms.toString());
	params.put("frob", frob.getFrob());

	String apiSig = Jinx.getInstance().sign(params);

	StringBuilder sb = new StringBuilder(JinxConstants.AUTH_ENDPOINT);
	sb.append('?');
	for (String key : params.keySet()) {
	    sb.append(key).append('=').append(params.get(key)).append('&');
	}

	sb.append("api_sig=").append(apiSig);
	frob.setLoginUrl(sb.toString());
	
	return frob;
    }


    /**
     * Returns the auth token for the given frob.
     *
     * @param frob the frob obtained from calling getFrob.
     * @return auth token object associated with the frob.
     * @throws JinxException if there are any errors.
     */
    public Token getToken(Frob frob) throws JinxException {
	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.auth.getToken");
	params.put("api_key", Jinx.getInstance().getApiKey());
	params.put("frob", frob.getFrob());
	
	Document doc = Jinx.getInstance().callFlickr(params, true);

	Token token = new Token();
	this.parseToken(doc, token);

	return token;
    }


    /**
     * Updates the credentials attached to an authentication token.
     *
     * @param token the token to check.
     * @throws JinxException if there are any errors.
     */
    public void checkToken(Token token) throws JinxException {
	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.auth.checkToken");
	params.put("api_key", Jinx.getInstance().getApiKey());
	params.put("auth_token", token.getToken());

	Document doc = Jinx.getInstance().callFlickr(params, true);

	this.parseToken(doc, token);
    }


    /**
     * Get the full authentication token for a mini-token.
     *
     * @param miniToken the mini token to exchange for a full token.
     * @return token object containing auth data.
     * @throws JinxException if there are any errors.
     */
    public Token getFullToken(String miniToken) throws JinxException {
	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.auth.getFullToken");
	params.put("api_key", Jinx.getInstance().getApiKey());
	params.put("mini_token", miniToken);

	Document doc = Jinx.getInstance().callFlickr(params, true);

	Token token = new Token();
	this.parseToken(doc, token);

	return token;
    }


    /**
     * Parse the auth XML element and update the token object.
     *
     * Auth xml document looks like this:
     *
     * <rsp stat=\"ok\">
     * 	    <auth>
     *		<token>976598454353455</token>
     * 		<perms>write</perms>
     * 		<user nsid="12037949754@N01" username="Bees" fullname="Cal H" />
     * 	    </auth>
     * </rsp>
     *
     * @param authXml the XML document containing the auth element.
     * @param t the token object to update with contents of the XML document.
     * @throws JinxException if there are any errors.
     */
    private void parseToken(Document doc, Token token) throws JinxException {
	token.setToken(JinxUtils.getValueByXPath(doc, "/rsp/auth/token"));
	token.setPerms(JinxUtils.getValueByXPath(doc, "/rsp/auth/perms"));
	token.setNsid(JinxUtils.getValueByXPath(doc, "/rsp/auth/user/@nsid"));
	token.setUsername(JinxUtils.getValueByXPath(doc, "/rsp/auth/user/@username"));
	token.setFullname(JinxUtils.getValueByXPath(doc, "/rsp/auth/user/@fullname"));
    }

}
