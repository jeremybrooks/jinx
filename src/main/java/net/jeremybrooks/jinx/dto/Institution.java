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
import java.util.Date;
import java.util.List;

/**
 * This class represents an element returned by the Flickr Commons API.
 * 
 * @author jeremyb
 */
public class Institution implements Serializable {

    private String nsid;
    private Date dateLaunch;
    private String name;
    private List<Url> urlList;


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
     * @return the dateLaunch
     */
    public Date getDateLaunch() {
	return dateLaunch;
    }


    /**
     * @param dateLaunch the dateLaunch to set
     */
    public void setDateLaunch(Date dateLaunch) {
	this.dateLaunch = dateLaunch;
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
     * @return the urlList
     */
    public List<Url> getUrlList() {
	return urlList;
    }


    /**
     * @param urlList the urlList to set
     */
    public void setUrlList(List<Url> urlList) {
	this.urlList = urlList;
    }


    
    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder(this.getClass().getName());
	sb.append("[ ");
	sb.append("nsid=").append(this.nsid).append(" | ");
	sb.append("dateLaunch=").append(this.dateLaunch).append(" | ");
	sb.append("name=").append(this.name).append(" | ");
	sb.append("urlList=");
	if (this.urlList == null) {
	    sb.append("null");
	} else {
	    for (Url url : this.urlList) {
		sb.append("<");
		sb.append(url.toString()).append(">");
	    }
	}
	sb.append(" ]");
	return sb.toString();
    }
}
