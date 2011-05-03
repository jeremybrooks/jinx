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
 * Represents a set of photos on Flickr.
 * 
 * @author jeremyb
 */
public class Photoset implements Serializable {

    private String id;
    private String primary;
    private String secret;
    private String server;
    private int photos;
    private int	videos;
    private String farm;
    private String title;
    private String description;
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
     * @return the primary
     */
    public String getPrimary() {
	return primary;
    }


    /**
     * @param primary the primary to set
     */
    public void setPrimary(String primary) {
	this.primary = primary;
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
     * @return the photos
     */
    public int getPhotos() {
	return photos;
    }


    /**
     * @param photos the photos to set
     */
    public void setPhotos(int photos) {
	this.photos = photos;
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
     * @return the videos
     */
    public int getVideos() {
	return videos;
    }


    /**
     * @param videos the videos to set
     */
    public void setVideos(int videos) {
	this.videos = videos;
    }


    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder("Photoset [ ");

	sb.append("id=").append(this.id).append(" | ");
	sb.append("primary=").append(this.primary).append(" | ");
	sb.append("secret=").append(this.secret).append(" | ");
	sb.append("server=").append(this.server).append(" | ");
	sb.append("farm=").append(this.farm).append(" | ");
	sb.append("photos=").append(this.photos).append(" | ");
	sb.append("videos=").append(this.videos).append(" | ");
	sb.append("title=").append(this.title).append(" | ");
	sb.append("description=").append(this.description).append(" | ");
	sb.append("url=").append(this.getUrl());

	sb.append(" ]");

	return sb.toString();
    }

}
