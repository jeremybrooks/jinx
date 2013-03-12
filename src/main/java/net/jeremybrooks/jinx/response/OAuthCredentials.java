package net.jeremybrooks.jinx.response;

import com.google.gson.annotations.SerializedName;

/**
 * Encapsulates OAuth credentials.
 * This is similar to, but not the same as, the OAuthAccessToken class. This class encapsulates returns from the
 * Flickr API. The OAuthAccessToken class encapsulates similar data, but is designed for the convenience of Jinx users.
 * Jinx users will use the OAuthAccessToken class to save access token data, load the data, and initialize Jinx.
 *
 * @author Jeremy Brooks
 */
public class OAuthCredentials extends Response {
	private static final long serialVersionUID = 6461908435424201577L;

	/**
	 * Get the oauth token.
	 * @return oauth token, or null if the token was not found.
	 */
	public String getOauthToken() {
		return oauth == null ? null : oauth.getToken();
	}

	/**
	 * Get permissions.
	 * @return permissions for the token, or null if not found.
	 */
	public String getPerms() {
		return oauth == null ? null : oauth.getPerms();
	}

	/**
	 * Get the NSID associated with the token.
	 * @return nsid of the user assocaited with the token, or null if not found.
	 */
	public String getNsid() {
		return oauth == null ? null : oauth.getNsid();
	}

	/**
	 * Get the Flickr username of the user associated with the token.
	 * @return username of the user associated with the token, or null if not found.
	 */
	public String getUsername() {
		return oauth == null ? null : oauth.getUsername();
	}

	/**
	 * Get the full name of the user associated with the token.
	 * @return full name of the user associated with the token, or null if not found.
	 */
	public String getFullname() {
		return oauth == null ? null : oauth.getFullName();
	}





	/*
	{ "oauth": {
	    "token": { "_content": "72157632940581881-6db7bec1c46b67b2" },
	    "perms": { "_content": "delete" },
	    "user": { "nsid": "85853333@N00", "username": "Jeremy Brooks", "fullname": "Jeremy Brooks" } }, "stat": "ok" }
	 */
	private OAuth oauth;
	private class OAuth {
		private Token token;
		private Perms perms;
		private User user;
		String getToken() {
			return token == null ? null : token.content;
		}
		String getPerms() {
			return perms == null ? null : perms.content;
		}
		String getNsid() {
			return user == null ? null : user.nsid;
		}
		String getUsername() {
			return user == null ? null : user.username;
		}
		String getFullName() {
			return user == null ? null : user.fullname;
		}
	}
	private class Token {
		@SerializedName("_content")
		private String content;
	}
	private class Perms {
		@SerializedName("_content")
		private String content;
	}
	private class User {
		private String nsid;
		private String username;
		private String fullname;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("classname=").append(this.getClass().getName());
		sb.append(",oauthToken=").append(getOauthToken());
		sb.append(",perms=").append(getPerms());
		sb.append(",nsid=").append(getNsid());
		sb.append(",username=").append(getUsername());
		sb.append(",fullname=").append(getFullname());

		return sb.toString();
	}
}
