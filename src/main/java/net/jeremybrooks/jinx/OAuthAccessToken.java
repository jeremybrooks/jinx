/*
 * Jinx is Copyright 2010-2018 by Jeremy Brooks and Contributors
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
