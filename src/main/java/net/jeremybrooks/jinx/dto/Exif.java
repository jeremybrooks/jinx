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

import java.io.Serializable;
import java.util.List;


/**
 * 
 * A list of EXIF/TIFF/GPS tags for a given photo.
 *
 * @author jeremyb
 */
public class Exif implements Serializable {

    /** The id of the photo. */
    private String id;

    /** The secret of the photo. */
    private String secret;

    /** The server. */
    private String server;

    /** The farm. */
    private String farm;

    /** List of ExifElements for the photo. */
    private List<ExifElement> exifList;


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
     * @return the secret
     */
    public String getSecret() {
	return secret;
    }


    /**
     * @param secret the secret to set
     */
    public void setSecret(String secret) {
	this.secret = secret;
    }


    /**
     * @return the server
     */
    public String getServer() {
	return server;
    }


    /**
     * @param server the server to set
     */
    public void setServer(String server) {
	this.server = server;
    }


    /**
     * @return the farm
     */
    public String getFarm() {
	return farm;
    }


    /**
     * @param farm the farm to set
     */
    public void setFarm(String farm) {
	this.farm = farm;
    }


    /**
     * @return the exifList
     */
    public List<ExifElement> getExifList() {
	return exifList;
    }


    /**
     * @param exifList the exifList to set
     */
    public void setExifList(List<ExifElement> exifList) {
	this.exifList = exifList;
    }

    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder("Exif [ ");

	sb.append("id=").append(this.id).append(" | ");
	sb.append("secret=").append(this.secret).append(" | ");
	sb.append("server=").append(this.server).append(" | ");
	sb.append("farm=").append(this.farm).append(" | ");
	sb.append("exifList=");
	if (this.exifList == null) {
	    sb.append("null");
	} else {
	    for (ExifElement ee : this.exifList) {
		sb.append('<').append(ee.toString()).append('>');
	    }
	}

	sb.append(" ]");
	return sb.toString();
    }
}
