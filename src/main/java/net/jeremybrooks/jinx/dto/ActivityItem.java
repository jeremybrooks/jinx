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
import java.util.List;

/**
 * NOTE: This class name may be changed in the future if other API's can use
 *       it.
 *
 * @author jeremyb
 */
public class ActivityItem implements Serializable {

    private String type;
    private String id;
    private String owner;
    private String ownerName;
    private String secret;
    private String server;
    private String farm;
    private int comments;
    private int notes;
    private int views;
    private int faves;
    private String title;

    private  List<ActivityEvent> eventList;


    /**
     * @return the type
     */
    public String getType() {
	return type;
    }


    /**
     * @param type the type to set
     */
    public void setType(String type) {
	this.type = type;
    }


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
     * @return the owner
     */
    public String getOwner() {
	return owner;
    }


    /**
     * @param owner the owner to set
     */
    public void setOwner(String owner) {
	this.owner = owner;
    }


    /**
     * @return the ownerName
     */
    public String getOwnerName() {
	return ownerName;
    }


    /**
     * @param ownerName the ownerName to set
     */
    public void setOwnerName(String ownerName) {
	this.ownerName = ownerName;
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
     * @return the comments
     */
    public int getComments() {
	return comments;
    }


    /**
     * @param comments the comments to set
     */
    public void setComments(int comments) {
	this.comments = comments;
    }


    /**
     * @return the notes
     */
    public int getNotes() {
	return notes;
    }


    /**
     * @param notes the notes to set
     */
    public void setNotes(int notes) {
	this.notes = notes;
    }


    /**
     * @return the views
     */
    public int getViews() {
	return views;
    }


    /**
     * @param views the views to set
     */
    public void setViews(int views) {
	this.views = views;
    }


    /**
     * @return the faves
     */
    public int getFaves() {
	return faves;
    }


    /**
     * @param faves the faves to set
     */
    public void setFaves(int faves) {
	this.faves = faves;
    }


    /**
     * @return the title
     */
    public String getTitle() {
	return title;
    }


    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
	this.title = title;
    }


    /**
     * @return the eventList
     */
    public List<ActivityEvent> getEventList() {
	return eventList;
    }


    /**
     * @param eventList the eventList to set
     */
    public void setEventList(List<ActivityEvent> eventList) {
	this.eventList = eventList;
    }

    
    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder(this.getClass().getName());
	sb.append(":[ ");

	sb.append("type=").append(this.type).append(" | ");
	sb.append("id=").append(this.id).append(" | ");
	sb.append("owner=").append(this.owner).append(" | ");
	sb.append("ownerName=").append(this.ownerName).append(" | ");
	sb.append("secret=").append(this.secret).append(" | ");
	sb.append("server=").append(this.server).append(" | ");
	sb.append("farm=").append(this.farm).append(" | ");
	sb.append("comments=").append(this.comments).append(" | ");
	sb.append("notes=").append(this.notes).append(" | ");
	sb.append("views=").append(this.views).append(" | ");
	sb.append("faves=").append(this.faves).append(" | ");
	sb.append("title=").append(this.title).append(" | ");
	sb.append("eventList=");
	if (this.eventList == null) {
	    sb.append("null");
	} else {
	    for (ActivityEvent event : this.eventList) {
		sb.append("<").append(event.toString()).append(">");
	    }
	}
	sb.append(" ]");
	return sb.toString();
    }

}
