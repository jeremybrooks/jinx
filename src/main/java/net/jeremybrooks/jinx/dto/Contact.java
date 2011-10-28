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

/**
 * This class represents a contact element, as returned by the Contact API
 * methods.
 *
 * Some of the fields may or may not be populated, depending on the method
 * that was called.
 * 
 * @author jeremyb
 */
public class Contact implements Serializable {
    private String nsid;
    private String username;
    private String iconServer;
    private String iconFarm;
    private boolean ignored = false;
    private String realName;
    private boolean friend = false;
    private boolean family = false;
    private String pathAlias;
    private String location;
    
    private int photosUploaded;

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
     * @return the iconServer
     */
    public String getIconServer() {
	return iconServer;
    }


    /**
     * @param iconServer the iconServer to set
     */
    public void setIconServer(String iconServer) {
	this.iconServer = iconServer;
    }


    /**
     * @return the iconFarm
     */
    public String getIconFarm() {
	return iconFarm;
    }


    /**
     * @param iconFarm the iconFarm to set
     */
    public void setIconFarm(String iconFarm) {
	this.iconFarm = iconFarm;
    }


    /**
     * @return the ignored
     */
    public boolean isIgnored() {
	return ignored;
    }


    /**
     * @param ignored the ignored to set
     */
    public void setIgnored(boolean ignored) {
	this.ignored = ignored;
    }


    /**
     * @return the realName
     */
    public String getRealName() {
	return realName;
    }


    /**
     * @param realName the realName to set
     */
    public void setRealName(String realName) {
	this.realName = realName;
    }


    /**
     * @return the friend
     */
    public boolean isFriend() {
	return friend;
    }


    /**
     * @param friend the friend to set
     */
    public void setFriend(boolean friend) {
	this.friend = friend;
    }


    /**
     * @return the family
     */
    public boolean isFamily() {
	return family;
    }


    /**
     * @param family the family to set
     */
    public void setFamily(boolean family) {
	this.family = family;
    }


    /**
     * @return the pathAlias
     */
    public String getPathAlias() {
	return pathAlias;
    }


    /**
     * @param pathAlias the pathAlias to set
     */
    public void setPathAlias(String pathAlias) {
	this.pathAlias = pathAlias;
    }


    /**
     * @return the location
     */
    public String getLocation() {
	return location;
    }


    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
	this.location = location;
    }


    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder(this.getClass().getName());

	sb.append(" [ ");
	sb.append("nsid=").append(this.nsid).append(" | ");
	sb.append("username=").append(this.username).append(" | ");
	sb.append("iconServer=").append(this.iconServer).append(" | ");
	sb.append("iconFarm=").append(this.iconFarm).append(" | ");
	sb.append("ignored=").append(this.ignored).append(" | ");
	sb.append("realName=").append(this.realName).append(" | ");
	sb.append("friend=").append(this.friend).append(" | ");
	sb.append("family=").append(this.family).append(" | ");
	sb.append("pathAlias=").append(this.pathAlias).append(" | ");
	sb.append("location=").append(this.location).append(" | ");
	sb.append("photosUploaded=").append(this.getPhotosUploaded());

	sb.append(" ]");
	return sb.toString();
    }


    /**
     * @return the photosUploaded
     */
    public int getPhotosUploaded() {
	return photosUploaded;
    }


    /**
     * @param photosUploaded the photosUploaded to set
     */
    public void setPhotosUploaded(int photosUploaded) {
	this.photosUploaded = photosUploaded;
    }
}
