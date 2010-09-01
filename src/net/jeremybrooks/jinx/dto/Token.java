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
package net.jeremybrooks.jinx.dto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.Properties;

/**
 * The auth token for a given frob. 
 * 
 * The auth token is the end result of the authentication process. Once an auth
 * token has been obtained, it can be used to sign future calls to the Flickr
 * API. The auth token is valid until the user revokes permission from their
 * account. 
 * 
 * Applications should save the auth token using the store method, and load it
 * using the load method to avoid having to go through the authentication
 * process every time the application is used.
 *
 * @author jeremyb
 */
public class Token implements Serializable {

    private String token;
    private String perms;
    private String nsid;
    private String username;
    private String fullname;


    /**
     * @return the token
     */
    public String getToken() {
	return token;
    }


    /**
     * @param token the token to set
     */
    public void setToken(String token) {
	this.token = token;
    }


    /**
     * @return the perms
     */
    public String getPerms() {
	return perms;
    }


    /**
     * @param perms the perms to set
     */
    public void setPerms(String perms) {
	this.perms = perms;
    }


    /**
     * @return the nsid
     */
    public String getNsid() {
	return nsid;
    }


    /**
     * @param nsid the nsid to set
     */
    public void setNsid(String nsid) {
	this.nsid = nsid;
    }


    /**
     * @return the username
     */
    public String getUsername() {
	return username;
    }


    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
	this.username = username;
    }


    /**
     * @return the fullname
     */
    public String getFullname() {
	return fullname;
    }


    /**
     * @param fullname the fullname to set
     */
    public void setFullname(String fullname) {
	this.fullname = fullname;
    }
    

    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder("[Token: ");

	sb.append("token=").append(token);
	sb.append(" | perms=").append(perms);
	sb.append(" | nsid=").append(nsid);
	sb.append(" | username=").append(username);
	sb.append(" | fullname=").append(fullname);
	sb.append(" ]");

	return sb.toString();
    }



    public void store(File file) throws Exception {
	Properties p = new Properties();
	p.setProperty("fullname", this.fullname);
	p.setProperty("nsid", this.nsid);
	p.setProperty("perms", this.perms);
	p.setProperty("token", this.token);
	p.setProperty("username", this.username);

	FileOutputStream out = null;
	try {
	    out = new FileOutputStream(file);
	    p.storeToXML(out, "Jinx token saved " + new Date());

	} catch (Exception e) {
	    throw e;
	} finally {
	    if (out != null) {
		try {
		    out.close();
		} catch (Exception e) {
		    // ignore
		}
	    }
	}
    }


    public void load(File file) throws Exception {
	Properties p = new Properties();
	FileInputStream in = null;

	try {
	    in = new FileInputStream(file);
	    p.loadFromXML(in);

	    this.setFullname(p.getProperty("fullname"));
	    this.setNsid(p.getProperty("nsid"));
	    this.setPerms(p.getProperty("perms"));
	    this.setToken(p.getProperty("token"));
	    this.setUsername(p.getProperty("username"));

	} catch (Exception e) {
	    throw e;
	} finally {
	    if (in != null) {
		try {
		    in.close();
		} catch (Exception e) {
		    // ignore
		}
	    }
	}
    }

    
}
