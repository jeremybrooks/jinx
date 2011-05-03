/*
 * Jinx is Copyright 2011 by Jeremy Brooks
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

/**
 *
 * @author jeremyb
 */
public class Restrictions {

    private boolean photosOk;
    private boolean videosOk;
    private boolean imagesOk;
    private boolean screensOk;
    private boolean artOk;
    private boolean safeOk;
    private boolean moderateOk;
    private boolean restrictedOk;
    private boolean hasGeo;


    /**
     * @return the photosOk
     */
    public boolean isPhotosOk() {
	return photosOk;
    }


    /**
     * @param photosOk the photosOk to set
     */
    public void setPhotosOk(boolean photosOk) {
	this.photosOk = photosOk;
    }


    /**
     * @return the videosOk
     */
    public boolean isVideosOk() {
	return videosOk;
    }


    /**
     * @param videosOk the videosOk to set
     */
    public void setVideosOk(boolean videosOk) {
	this.videosOk = videosOk;
    }


    /**
     * @return the imagesOk
     */
    public boolean isImagesOk() {
	return imagesOk;
    }


    /**
     * @param imagesOk the imagesOk to set
     */
    public void setImagesOk(boolean imagesOk) {
	this.imagesOk = imagesOk;
    }

    /**
     * @return the screensOk
     */
    public boolean isScreensOk() {
	return screensOk;
    }


    /**
     * @param screensOk the screensOk to set
     */
    public void setScreensOk(boolean screensOk) {
	this.screensOk = screensOk;
    }

    /**
     * @return the artOk
     */
    public boolean isArtOk() {
	return artOk;
    }


    /**
     * @param artOk the artOk to set
     */
    public void setArtOk(boolean artOk) {
	this.artOk = artOk;
    }


    /**
     * @return the safeOk
     */
    public boolean isSafeOk() {
	return safeOk;
    }


    /**
     * @param safeOk the safeOk to set
     */
    public void setSafeOk(boolean safeOk) {
	this.safeOk = safeOk;
    }


    /**
     * @return the moderateOk
     */
    public boolean isModerateOk() {
	return moderateOk;
    }


    /**
     * @param moderateOk the moderateOk to set
     */
    public void setModerateOk(boolean moderateOk) {
	this.moderateOk = moderateOk;
    }


    /**
     * @return the restrictedOk
     */
    public boolean isRestrictedOk() {
	return restrictedOk;
    }


    /**
     * @param restrictedOk the restrictedOk to set
     */
    public void setRestrictedOk(boolean restrictedOk) {
	this.restrictedOk = restrictedOk;
    }


    /**
     * @return the hasGeo
     */
    public boolean isHasGeo() {
	return hasGeo;
    }


    /**
     * @param hasGeo the hasGeo to set
     */
    public void setHasGeo(boolean hasGeo) {
	this.hasGeo = hasGeo;
    }


    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder(this.getClass().getName());

	sb.append(" [ ");
	sb.append("photosOk=").append(this.isPhotosOk()).append(" | ");
	sb.append("videosOk=").append(this.isVideosOk()).append(" | ");
	sb.append("imagesOk=").append(this.isImagesOk()).append(" | ");
	sb.append("screensOk=").append(this.isScreensOk()).append(" | ");
	sb.append("artOk=").append(this.isArtOk()).append(" | ");
	sb.append("safeOk=").append(this.isSafeOk()).append(" | ");
	sb.append("moderatedOk=").append(this.isModerateOk()).append(" | ");
	sb.append("restrictedOk=").append(this.isRestrictedOk()).append(" | ");
	sb.append("hasGeo=").append(this.isHasGeo());

	sb.append(" ]");
	return sb.toString();
    }


    
    
}
