package net.jeremybrooks.jinx;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Properties;

/**
 * @author Jeremy Brooks
 */
public class OAuthAccessToken {
	private String oauthToken;
	private String oauthTokenSecret;
	private String nsid;
	private String username;
	private String fullname;

	public String getOauthToken() {
		return oauthToken;
	}

	public void setOauthToken(String oauthToken) {
		this.oauthToken = oauthToken;
	}

	public String getOauthTokenSecret() {
		return oauthTokenSecret;
	}

	public void setOauthTokenSecret(String oauthTokenSecret) {
		this.oauthTokenSecret = oauthTokenSecret;
	}

	public String getNsid() {
		return nsid;
	}

	public void setNsid(String nsid) {
		this.nsid = nsid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}


	public void store(OutputStream outputStream) throws Exception {
		Properties p = new Properties();
		p.setProperty("oauthToken", this.getOauthToken());
		p.setProperty("oauthTokenSecret", this.getOauthTokenSecret());
		p.setProperty("fullname", this.getFullname());
		p.setProperty("nsid", this.getNsid());
		p.setProperty("username", this.getUsername());

		p.storeToXML(outputStream, "Jinx OAuth Token saved " + new Date());
	}


	public void load(InputStream inputStream) throws Exception {
		Properties p = new Properties();
		p.loadFromXML(inputStream);
		this.setOauthToken(p.getProperty("oauthToken"));
		this.setOauthTokenSecret(p.getProperty("oauthTokenSecret"));
		this.setFullname(p.getProperty("fullname"));
		this.setNsid(p.getProperty("nsid"));
		this.setUsername(p.getProperty("username"));
}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("classname=").append(this.getClass().getName());
		sb.append(",oauthToken=\"").append(oauthToken).append('\"');
		sb.append(",oauthTokenSecret=\"").append(oauthTokenSecret).append('\"');
		sb.append(",nsid=\"").append(nsid).append('\"');
		sb.append(",username=\"").append(username).append('\"');
		sb.append(",fullname=\"").append(fullname).append('\"');
		return sb.toString();
	}


}
