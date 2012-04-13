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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author jeremyb
 */
public class Galleries implements Serializable {
/*
<?xml version="1.0" encoding="utf-8" ?>
<rsp stat="ok">
    <galleries total="6" page="1" pages="1" per_page="100" user_id="85853333@N00">
	<gallery id="4956757-72157625363950230" url="http://www.flickr.com/photos/jeremybrooks/galleries/72157625363950230" owner="85853333@N00" primary_photo_id="3928411827" date_create="1289512766" date_update="1289513364" count_photos="12" count_videos="0" primary_photo_server="3003" primary_photo_farm="4" primary_photo_secret="6f707a6e6c">
	    <title>leesure</title>
	    <description>Some of my favorite photos from leesure's photostream.</description>
	</gallery>
    </galleries>
 </rsp>
 */
    private int total;
    private int page;
    private int pages;
    private int perPage;
    private String userId;
    private List<Gallery> galleryList;

    public Galleries() {
	this.galleryList = new ArrayList<Gallery>();
    }


    


    /**
     * @return the total
     */
    public int getTotal() {
	return total;
    }


    /**
     * @param total the total to set
     */
    public void setTotal(int total) {
	this.total = total;
    }


    /**
     * @return the page
     */
    public int getPage() {
	return page;
    }


    /**
     * @param page the page to set
     */
    public void setPage(int page) {
	this.page = page;
    }


    /**
     * @return the pages
     */
    public int getPages() {
	return pages;
    }


    /**
     * @param pages the pages to set
     */
    public void setPages(int pages) {
	this.pages = pages;
    }


    /**
     * @return the perPage
     */
    public int getPerPage() {
	return perPage;
    }


    /**
     * @param perPage the perPage to set
     */
    public void setPerPage(int perPage) {
	this.perPage = perPage;
    }


    /**
     * @return the userId
     */
    public String getUserId() {
	return userId;
    }


    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
	this.userId = userId;
    }


    /**
     * @return the galleryList
     */
    public List<Gallery> getGalleryList() {
	return galleryList;
    }


    /**
     * @param galleryList the galleryList to set
     */
    public void setGalleryList(List<Gallery> galleryList) {
	this.galleryList = galleryList;
    }

    /**
     * Add a gallery to the list.
     * @param gallery the gallery to add to the list.
     */
    public void addGallery(Gallery gallery) {
	this.galleryList.add(gallery);
    }


    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder(this.getClass().getName());

	sb.append(" [ ");
	sb.append("total=").append(this.getTotal()).append(" | ");
	sb.append("page=").append(this.getPage()).append(" | ");
	sb.append("pages=").append(this.getPages()).append(" | ");
	sb.append("perPage=").append(this.getPerPage()).append(" | ");
	sb.append("userId=").append(this.getUserId()).append(" | ");
	sb.append("galleryList=");
	if (this.getGalleryList() != null) {
	    for (Gallery g : this.getGalleryList()) {
		sb.append('<').append(g.toString()).append('>');
	    }
	}

	sb.append(" ]");
	return sb.toString();
    }
}
