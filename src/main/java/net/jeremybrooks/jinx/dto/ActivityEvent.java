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

/**
 * NOTE: This class name may be changed in the future if other API's can use
 *       it.
 * 
 * @author jeremyb
 */
public class ActivityEvent implements Serializable {
    private String type;
    private String user;
    private String userName;
    private Date dateAdded;
    private String commentId;
    private String eventText;


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
     * @return the user
     */
    public String getUser() {
	return user;
    }


    /**
     * @param user the user to set
     */
    public void setUser(String user) {
	this.user = user;
    }


    /**
     * @return the userName
     */
    public String getUserName() {
	return userName;
    }


    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
	this.userName = userName;
    }


    /**
     * @return the dateAdded
     */
    public Date getDateAdded() {
	return dateAdded;
    }


    /**
     * @param dateAdded the dateAdded to set
     */
    public void setDateAdded(Date dateAdded) {
	this.dateAdded = dateAdded;
    }


    /**
     * @return the commentId
     */
    public String getCommentId() {
	return commentId;
    }


    /**
     * @param commentId the commentId to set
     */
    public void setCommentId(String commentId) {
	this.commentId = commentId;
    }


    /**
     * @return the eventText
     */
    public String getEventText() {
	return eventText;
    }


    /**
     * @param eventText the eventText to set
     */
    public void setEventText(String eventText) {
	this.eventText = eventText;
    }

    
    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder(this.getClass().getName());
	sb.append(":[ ");
	
	sb.append("type=").append(this.type).append(" | ");
	sb.append("user=").append(this.user).append(" | ");
	sb.append("userName=").append(this.userName).append(" | ");
	sb.append("dateAdded=").append(this.dateAdded).append(" | ");
	sb.append("commentId=").append(this.commentId).append(" | ");
	sb.append("eventText=").append(this.eventText);
	sb.append(" ]");
	return sb.toString();
    }
}
