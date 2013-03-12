package net.jeremybrooks.jinx.response;

import com.google.gson.annotations.SerializedName;

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
