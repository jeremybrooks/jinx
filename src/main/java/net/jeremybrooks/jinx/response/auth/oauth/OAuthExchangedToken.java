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
package net.jeremybrooks.jinx.response.auth.oauth;

import com.google.gson.annotations.SerializedName;
import net.jeremybrooks.jinx.response.Response;

/**
 * Used when exchanging a legacy Flickr access token for an OAuth access token.
 * Used internally; users of Jinx will likely not have to worry about this class.
 *
 * @author Jeremy Brooks
 */
public class OAuthExchangedToken extends Response {

	private static final long serialVersionUID = 902314330359961160L;

	/**
	 * Get the oauth token.
	 * @return oauth token, or null if no token is found.
	 */
	public String getOAuthToken() {
		return auth == null ? null : auth.getoAuthToken();
	}

	/**
	 * Get the oauth token secret.
	 * @return oauth token secret, or null if no secret is found.
	 */
	public String getOAuthTokenSecret() {
		return auth == null ? null : auth.getoAuthTokenSecret();
	}




	/*
	{
		"auth": {
	    	"access_token": {
	    		"oauth_token": "72157632940581881-6db7bec1c46b67b2", "oauth_token_secret": "7267af304d035953"
	    	}
		}, "stat": "ok"
	}
	*/

	private Auth auth;

	private class Auth {
		@SerializedName("access_token")
		private AccessToken accessToken;

		String getoAuthToken() {
			return accessToken == null ? null : accessToken.oauthToken;
		}

		String getoAuthTokenSecret() {
			return accessToken == null ? null : accessToken.oauthTokenSecret;
		}
	}

	private class AccessToken {
		@SerializedName("oauth_token")
		String oauthToken;
		@SerializedName("oauth_token_secret")
		String oauthTokenSecret;
	}


	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("classname=").append(this.getClass().getName());
		sb.append(",oauthToken=").append(getOAuthToken());
		sb.append(",oauthTokenSecret=").append(getOAuthTokenSecret());
		return sb.toString();
	}
}
