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
import java.util.Date;

/*
<?xml version="1.0" encoding="utf-8" ?>
<rsp stat="ok">
    <galleries total="6" page="1" pages="1" per_page="100" user_id="85853333@N00">
	<gallery id="4956757-72157625363950230"
	    url="http://www.flickr.com/photos/jeremybrooks/galleries/72157625363950230"
	    owner="85853333@N00" primary_photo_id="3928411827" date_create="1289512766"
	    date_update="1289513364" count_photos="12" count_videos="0"
	    primary_photo_server="3003" primary_photo_farm="4" primary_photo_secret="6f707a6e6c">
	    <title>leesure</title>
	    <description>Some of my favorite photos from leesure's photostream.</description>
	</gallery>
    </galleries>
 </rsp>
 */

public class Gallery implements	Serializable {

    private String id;
    private String url;
    private String owner;
    private String primaryPhotoId;
    private Date dateCreate;
    private Date dateUpdate;
    private int countPhotos;
    private int countVideos;
    private String primaryPhotoServer;
    private String primaryPhotoFarm;
    private String primaryPhotoSecret;
    private String title;
    private String description;

    

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
     * @return the primaryPhotoId
     */
    public String getPrimaryPhotoId() {
	return primaryPhotoId;
    }


    /**
     * @param primaryPhotoId the primaryPhotoId to set
     */
    public void setPrimaryPhotoId(String primaryPhotoId) {
	this.primaryPhotoId = primaryPhotoId;
    }


    /**
     * @return the dateCreate
     */
    public Date getDateCreate() {
	return dateCreate;
    }


    /**
     * @param dateCreate the dateCreate to set
     */
    public void setDateCreate(Date dateCreate) {
	this.dateCreate = dateCreate;
    }


    /**
     * @return the dateUpdate
     */
    public Date getDateUpdate() {
	return dateUpdate;
    }


    /**
     * @param dateUpdate the dateUpdate to set
     */
    public void setDateUpdate(Date dateUpdate) {
	this.dateUpdate = dateUpdate;
    }


    /**
     * @return the countPhotos
     */
    public int getCountPhotos() {
	return countPhotos;
    }


    /**
     * @param countPhotos the countPhotos to set
     */
    public void setCountPhotos(int countPhotos) {
	this.countPhotos = countPhotos;
    }


    /**
     * @return the countVideos
     */
    public int getCountVideos() {
	return countVideos;
    }


    /**
     * @param countVideos the countVideos to set
     */
    public void setCountVideos(int countVideos) {
	this.countVideos = countVideos;
    }


    /**
     * @return the primaryPhotoServer
     */
    public String getPrimaryPhotoServer() {
	return primaryPhotoServer;
    }


    /**
     * @param primaryPhotoServer the primaryPhotoServer to set
     */
    public void setPrimaryPhotoServer(String primaryPhotoServer) {
	this.primaryPhotoServer = primaryPhotoServer;
    }


    /**
     * @return the primaryPhotoFarm
     */
    public String getPrimaryPhotoFarm() {
	return primaryPhotoFarm;
    }


    /**
     * @param primaryPhotoFarm the primaryPhotoFarm to set
     */
    public void setPrimaryPhotoFarm(String primaryPhotoFarm) {
	this.primaryPhotoFarm = primaryPhotoFarm;
    }


    /**
     * @return the primaryPhotoSecret
     */
    public String getPrimaryPhotoSecret() {
	return primaryPhotoSecret;
    }


    /**
     * @param primaryPhotoSecret the primaryPhotoSecret to set
     */
    public void setPrimaryPhotoSecret(String primaryPhotoSecret) {
	this.primaryPhotoSecret = primaryPhotoSecret;
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
     * @return the description
     */
    public String getDescription() {
	return description;
    }


    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
	this.description = description;
    }


    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder(this.getClass().getName());

	sb.append(" [ ");
	sb.append("id=").append(this.getId()).append(" | ");
	sb.append("url=").append(this.getUrl()).append(" | ");
	sb.append("owner=").append(this.getOwner()).append(" | ");
	sb.append("primaryPhotoId=").append(this.getPrimaryPhotoId()).append(" | ");
	sb.append("dateCreate=").append(this.getDateCreate()).append(" | ");
	sb.append("dateUpdate=").append(this.getDateUpdate()).append(" | ");
	sb.append("countPhotos=").append(this.getCountPhotos()).append(" | ");
	sb.append("countVideos=").append(this.getCountVideos()).append(" | ");
	sb.append("primaryPhotoServer=").append(this.getPrimaryPhotoServer()).append(" | ");
	sb.append("primaryPhotoFarm=").append(this.getPrimaryPhotoFarm()).append(" | ");
	sb.append("primaryPhotoSecret=").append(this.getPrimaryPhotoSecret()).append(" | ");
	sb.append("title=").append(this.getTitle()).append(" | ");
	sb.append("description=").append(this.getDescription());

	sb.append(" ]");
	return sb.toString();
    }


    
}
