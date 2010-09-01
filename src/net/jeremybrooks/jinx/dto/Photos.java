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
 * This class is returned by many of the API methods.
 *
 * Instances of this class are returned by many of the API methods. This class
 * represents a "page" of images in a given result set. The number of Photo
 * objects in the list will be less than or equal to the value of perPage.
 *
 * Some fields may or may not be set, depending on how the photos were returend.
 * For example, getting photos via PhotosetsApi.getPhotos will cause the
 * photosetId field to be populated, while getting photos via PhotosApi.search
 * will not.
 *
 * @author jeremyb
 */
public class Photos implements Serializable {



    private String photosetId;
    private String primaryPhotoId;
    private String owner;
    private String ownerName;

    private int page;
    private int pages;
    private int perPage;
    private int total;


    private List<Photo> photos;


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
     * @return the photos
     */
    public List<Photo> getPhotos() {
	return photos;
    }


    /**
     * @param photos the photos to set
     */
    public void setPhotos(List<Photo> photos) {
	this.photos = photos;
    }


    /**
     * @return the photosetId
     */
    public String getPhotosetId() {
	return photosetId;
    }


    /**
     * @param photosetId the photosetId to set
     */
    public void setPhotosetId(String photosetId) {
	this.photosetId = photosetId;
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


    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder("Photos [ ");

	sb.append("photosetId=").append(this.photosetId).append(" | ");
	sb.append("primaryPhotoId=").append(this.primaryPhotoId).append(" | ");
	sb.append("owner=").append(this.owner).append(" | ");
	sb.append("ownerName=").append(this.ownerName).append(" | ");
	sb.append("page=").append(this.page).append(" | ");
	sb.append("pages=").append(this.pages).append(" | ");
	sb.append("perPage=").append(this.perPage).append(" | ");
	sb.append("total=").append(this.total).append(" | ");

	if (this.photos == null) {
	    sb.append("photos=null");
	} else {
	    sb.append("photos=").append(this.photos.size()).append(" elements");
	}

	sb.append(" ]");

	return sb.toString();
    }

}
