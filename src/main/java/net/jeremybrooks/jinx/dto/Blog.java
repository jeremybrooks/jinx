/*
 * Jinx is Copyright 2010-2012 by Jeremy Brooks and Contributors
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

import java.io.Serializable;

/**
 * This class represents a blog as returned by the BlogsAPI.getList method.
 *
 * This class implements its own version of equals() and hashCode(). If the
 * id's of two instances of this class are the same, the classes are considered
 * equal.
 * 
 * @author jeremyb
 */
public class Blog implements Serializable {
    private String id;
    private String name;
    private String service;
    private boolean needsPassword;
    private String url;


    /**
     * @return the id
     */
    public String getId() {
	return id;
    }


    /**
     * @param id the id to set
     */
    public void setId(String id) {
	this.id = id;
    }


    /**
     * @return the name
     */
    public String getName() {
	return name;
    }


    /**
     * @param name the name to set
     */
    public void setName(String name) {
	this.name = name;
    }


    /**
     * @return the service
     */
    public String getService() {
	return service;
    }


    /**
     * @param service the service to set
     */
    public void setService(String service) {
	this.service = service;
    }


    /**
     * @return the needsPassword
     */
    public boolean isNeedsPassword() {
	return needsPassword;
    }


    /**
     * @param needsPassword the needsPassword to set
     */
    public void setNeedsPassword(boolean needsPassword) {
	this.needsPassword = needsPassword;
    }


    /**
     * @return the url
     */
    public String getUrl() {
	return url;
    }


    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
	this.url = url;
    }


    @Override
    public boolean equals(Object obj) {
	if (obj == null) {
	    return false;
	}
	if (getClass() != obj.getClass()) {
	    return false;
	}
	final Blog other = (Blog) obj;
	if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
	    return false;
	}
	return true;
    }


    @Override
    public int hashCode() {
	int hash = 7;
	hash = 41 * hash + (this.id != null ? this.id.hashCode() : 0);
	return hash;
    }



    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder("Blog [ ");

	sb.append("id=").append(this.id).append(" | ");
	sb.append("name=").append(this.name).append(" | ");
	sb.append("service=").append(this.service).append(" | ");
	sb.append("needsPassword=").append(this.needsPassword).append(" | ");
	sb.append("url=").append(this.url);

	sb.append(" ]");
	return sb.toString();
    }
}
