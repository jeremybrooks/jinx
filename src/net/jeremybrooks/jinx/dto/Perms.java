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
 * Data representing the perms for a given photo.
 *
 * Values for permComment and permAddMeta can be found in the JinxConstants
 * class.
 * 
 * @author jeremyb
 */
public class Perms implements Serializable {


    private String id;
    private boolean isPublic;
    private boolean isFriend;
    private boolean isFamily;
    private String permComment;
    private String permAddMeta;


    /**
     * Get the id of the photo.
     * 
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
     * @return the isPublic
     */
    public boolean isIsPublic() {
	return isPublic;
    }


    /**
     * @param isPublic the isPublic to set
     */
    public void setIsPublic(boolean isPublic) {
	this.isPublic = isPublic;
    }


    /**
     * @return the isFriend
     */
    public boolean isIsFriend() {
	return isFriend;
    }


    /**
     * @param isFriend the isFriend to set
     */
    public void setIsFriend(boolean isFriend) {
	this.isFriend = isFriend;
    }


    /**
     * @return the isFamily
     */
    public boolean isIsFamily() {
	return isFamily;
    }


    /**
     * @param isFamily the isFamily to set
     */
    public void setIsFamily(boolean isFamily) {
	this.isFamily = isFamily;
    }


    /**
     * @return the permComment
     */
    public String getPermComment() {
	return permComment;
    }


    /**
     * Who can add comments to the photo and its notes. One of:
     * <ul>
     * <li>PERM_COMMENT_NOBODY</li>
     * <li>PERM_COMMENT_FRIENDS_AND_FAMILY</li>
     * <li>PERM_COMMENT_CONTACTS</li>
     * <li>PERM_COMMENT_EVERYBODY</li>
     * </ul>
     * @param permComment the permComment to set
     */
    public void setPermComment(String permComment) {
	this.permComment = permComment;
    }


    /**
     * @return the permAddMeta
     */
    public String getPermAddMeta() {
	return permAddMeta;
    }


    /**
     * Who can add notes and tags to the photo. One of:
     *
     * <ul>
     * <li>PERM_ADD_META_NOBODY</li>
     * <li>PERM_ADD_META_FRIENDS_AND_FAMILY</li>
     * <li>PERM_ADD_META_CONTACTS</li>
     * <li>PERM_ADD_META_EVERYBODY</li>
     * </ul>
     * @param permAddMeta the permAddMeta to set
     */
    public void setPermAddMeta(String permAddMeta) {
	this.permAddMeta = permAddMeta;
    }


    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder("Perms [ ");

	sb.append("id=").append(this.id).append(" | ");
	sb.append("isPublic=").append(this.isPublic).append(" | ");
	sb.append("isFriend=").append(this.isFriend).append(" | ");
	sb.append("isFamily=").append(this.isFamily).append(" | ");
	sb.append("permComment=").append(this.permComment).append(" | ");
	sb.append("permAddMeta=").append(this.permAddMeta);
	
	sb.append(" ]");
	return sb.toString();
    }
}
