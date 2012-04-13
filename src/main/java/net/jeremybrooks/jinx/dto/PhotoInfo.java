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
 * Information about a photo.
 *
 * @author jeremyb
 */
public class PhotoInfo extends PhotoBase implements Serializable {

    private boolean isFavorite;
    private int rotation;
    private String ownerRealname;
    private String ownerLocation;

    private Date datePosted;

    private String permComment;
    private String permAddMeta;

    private boolean canComment;
    private boolean canAddMeta;

    private boolean canDownload;
    private boolean canBlog;
    private boolean canPrint;
    private boolean canShare;

    private int comments;


    private List<Note> noteList;

    private List<Tag> tagList;

    private List<Url> urlList;


    


    /**
     * @return the isFavorite
     */
    public boolean isIsFavorite() {
	return isFavorite;
    }


    /**
     * @param isFavorite the isFavorite to set
     */
    public void setIsFavorite(boolean isFavorite) {
	this.isFavorite = isFavorite;
    }


    /**
     * @return the rotation
     */
    public int getRotation() {
	return rotation;
    }


    /**
     * @param rotation the rotation to set
     */
    public void setRotation(int rotation) {
	this.rotation = rotation;
    }


   


    /**
     * @return the ownerRealname
     */
    public String getOwnerRealname() {
	return ownerRealname;
    }


    /**
     * @param ownerRealname the ownerRealname to set
     */
    public void setOwnerRealname(String ownerRealname) {
	this.ownerRealname = ownerRealname;
    }


    /**
     * @return the ownerLocation
     */
    public String getOwnerLocation() {
	return ownerLocation;
    }


    /**
     * @param ownerLocation the ownerLocation to set
     */
    public void setOwnerLocation(String ownerLocation) {
	this.ownerLocation = ownerLocation;
    }


    

    


    /**
     * @return the datePosted
     */
    public Date getDatePosted() {
	return datePosted;
    }


    /**
     * @param datePosted the datePosted to set
     */
    public void setDatePosted(Date datePosted) {
	this.datePosted = datePosted;
    }


    


    /**
     * @return the permComment
     */
    public String getPermComment() {
	return permComment;
    }


    /**
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
     * @param permAddMeta the permAddMeta to set
     */
    public void setPermAddMeta(String permAddMeta) {
	this.permAddMeta = permAddMeta;
    }


    /**
     * @return the canComment
     */
    public boolean isCanComment() {
	return canComment;
    }


    /**
     * @param canComment the canComment to set
     */
    public void setCanComment(boolean canComment) {
	this.canComment = canComment;
    }


    /**
     * @return the canAddMeta
     */
    public boolean isCanAddMeta() {
	return canAddMeta;
    }


    /**
     * @param canAddMeta the canAddMeta to set
     */
    public void setCanAddMeta(boolean canAddMeta) {
	this.canAddMeta = canAddMeta;
    }


    /**
     * @return the canDownload
     */
    public boolean isCanDownload() {
	return canDownload;
    }


    /**
     * @param canDownload the canDownload to set
     */
    public void setCanDownload(boolean canDownload) {
	this.canDownload = canDownload;
    }


    /**
     * @return the canBlog
     */
    public boolean isCanBlog() {
	return canBlog;
    }


    /**
     * @param canBlog the canBlog to set
     */
    public void setCanBlog(boolean canBlog) {
	this.canBlog = canBlog;
    }


    /**
     * @return the canPrint
     */
    public boolean isCanPrint() {
	return canPrint;
    }


    /**
     * @param canPrint the canPrint to set
     */
    public void setCanPrint(boolean canPrint) {
	this.canPrint = canPrint;
    }


    /**
     * @return the canShare
     */
    public boolean isCanShare() {
	return canShare;
    }


    /**
     * @param canShare the canShare to set
     */
    public void setCanShare(boolean canShare) {
	this.canShare = canShare;
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
     * @return the noteList
     */
    public List<Note> getNoteList() {
	return noteList;
    }


    /**
     * @param noteList the noteList to set
     */
    public void setNoteList(List<Note> noteList) {
	this.noteList = noteList;
    }


    /**
     * @return the tagList
     */
    public List<Tag> getTagList() {
	return tagList;
    }


    /**
     * @param tagList the tagList to set
     */
    public void setTagList(List<Tag> tagList) {
	this.tagList = tagList;
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
	StringBuilder sb = new StringBuilder("PhotoInfo [ ");

	sb.append("id=").append(super.getId()).append(" | ");
	sb.append("secret=").append(super.getSecret()).append(" | ");
	sb.append("server=").append(super.getServer()).append(" | ");
	sb.append("farm=").append(super.getFarm()).append(" | ");
	sb.append("dateUploaded=").append(super.getDateUploaded()).append(" | ");
	sb.append("isFavorite=").append(this.isFavorite).append(" | ");
	sb.append("license=").append(super.getLicense()).append(" | ");
	sb.append("rotation=").append(this.rotation).append(" | ");
	sb.append("originalSecret=").append(super.getOriginalSecret()).append(" | ");
	sb.append("originalFormat=").append(super.getOriginalFormat()).append(" | ");
	sb.append("views=").append(super.getViews()).append(" | ");
	sb.append("media=").append(super.getMedia()).append(" | ");
	sb.append("ownerNsid=").append(super.getOwnerNsid()).append(" | ");
	sb.append("ownerUsername=").append(super.getOwnerUsername()).append(" | ");
	sb.append("ownerRealname=").append(this.ownerRealname).append(" | ");
	sb.append("ownerLocation=").append(this.ownerLocation).append(" | ");
	sb.append("title=").append(super.getTitle()).append(" | ");
	sb.append("description=").append(super.getDescription()).append(" | ");
	sb.append("isPublic=").append(super.isIsPublic()).append(" | ");
	sb.append("isFriend=").append(super.isIsFriend()).append(" | ");
	sb.append("isFamily=").append(super.isIsFamily()).append(" | ");
	sb.append("datePosted=").append(this.datePosted).append(" | ");
	sb.append("dateTaken=").append(super.getDateTaken()).append(" | ");
	sb.append("dateTakenGranularity=").append(super.getDateTakenGranularity()).append(" | ");
	sb.append("dateLastUpdate=").append(super.getDateLastUpdate()).append(" | ");
	sb.append("permComment=").append(this.permComment).append(" | ");
	sb.append("permAddMeta=").append(this.permAddMeta).append(" | ");
	sb.append("canComment=").append(this.canComment).append(" | ");
	sb.append("canAddMeta=").append(this.canAddMeta).append(" | ");
	sb.append("canDownload=").append(this.canDownload).append(" | ");
	sb.append("canBlog=").append(this.canBlog).append(" | ");
	sb.append("canPrint=").append(this.canPrint).append(" | ");
	sb.append("canShare=").append(this.canShare).append(" | ");
	sb.append("comments=").append(this.comments).append(" | ");
	if (this.noteList == null) {
	    sb.append("noteList=null");
	} else {
	    sb.append("noteList=").append(this.noteList.size()).append(" items | ");
	}
	if (this.tagList == null) {
	    sb.append("tagList=null");
	} else {
	    sb.append("tagList=").append(this.tagList.size()).append(" items | ");
	}
	if (this.urlList == null) {
	    sb.append("urlList=null");
	} else {
	    sb.append("urlList=").append(this.urlList.size()).append(" items");
	}

	sb.append(" ]");

	return sb.toString();
    }
}
