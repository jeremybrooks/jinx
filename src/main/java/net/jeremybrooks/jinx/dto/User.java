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

/**
 *
 * @author jeremyb
 */
public class User {


    private String id;
    private String nsid;
    private String username;
    private boolean pro;
    
    private String bandwidthMax;
    private String bandwidthUsed;
    private String bandwidthMaxBytes;
    private String bandwidthUsedBytes;
    private String bandwidthRemainingBytes;
    private String bandwidthMaxKb;
    private String bandwidthUsedKb;
    private String bandwidthRemainingKb;
    private boolean bandwidthUnlimited;

    private String filesizeMax;
    private String filesizeMaxBytes;
    private String filesizeMaxKb;
    private String filesizeMaxMb;

    private String setsCreated;
    private String setsRemaining;

    private String videoSizeMaxBytes;
    private String videoSizeMaxKb;
    private String videoSizeMaxMb;
    private String videosUploaded;
    private String videosRemaining;


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
     * @return the pro
     */
    public boolean isPro() {
	return pro;
    }


    /**
     * @param pro the pro to set
     */
    public void setPro(boolean pro) {
	this.pro = pro;
    }


    /**
     * @return the bandwidthMax
     */
    public String getBandwidthMax() {
	return bandwidthMax;
    }


    /**
     * @param bandwidthMax the bandwidthMax to set
     */
    public void setBandwidthMax(String bandwidthMax) {
	this.bandwidthMax = bandwidthMax;
    }


    /**
     * @return the bandwidthUsed
     */
    public String getBandwidthUsed() {
	return bandwidthUsed;
    }


    /**
     * @param bandwidthUsed the bandwidthUsed to set
     */
    public void setBandwidthUsed(String bandwidthUsed) {
	this.bandwidthUsed = bandwidthUsed;
    }


    /**
     * @return the bandwidthMaxBytes
     */
    public String getBandwidthMaxBytes() {
	return bandwidthMaxBytes;
    }


    /**
     * @param bandwidthMaxBytes the bandwidthMaxBytes to set
     */
    public void setBandwidthMaxBytes(String bandwidthMaxBytes) {
	this.bandwidthMaxBytes = bandwidthMaxBytes;
    }


    /**
     * @return the bandwidthUsedBytes
     */
    public String getBandwidthUsedBytes() {
	return bandwidthUsedBytes;
    }


    /**
     * @param bandwidthUsedBytes the bandwidthUsedBytes to set
     */
    public void setBandwidthUsedBytes(String bandwidthUsedBytes) {
	this.bandwidthUsedBytes = bandwidthUsedBytes;
    }


    /**
     * @return the bandwidthRemainingBytes
     */
    public String getBandwidthRemainingBytes() {
	return bandwidthRemainingBytes;
    }


    /**
     * @param bandwidthRemainingBytes the bandwidthRemainingBytes to set
     */
    public void setBandwidthRemainingBytes(String bandwidthRemainingBytes) {
	this.bandwidthRemainingBytes = bandwidthRemainingBytes;
    }


    /**
     * @return the bandwidthMaxKb
     */
    public String getBandwidthMaxKb() {
	return bandwidthMaxKb;
    }


    /**
     * @param bandwidthMaxKb the bandwidthMaxKb to set
     */
    public void setBandwidthMaxKb(String bandwidthMaxKb) {
	this.bandwidthMaxKb = bandwidthMaxKb;
    }


    /**
     * @return the bandwidthUsedKb
     */
    public String getBandwidthUsedKb() {
	return bandwidthUsedKb;
    }


    /**
     * @param bandwidthUsedKb the bandwidthUsedKb to set
     */
    public void setBandwidthUsedKb(String bandwidthUsedKb) {
	this.bandwidthUsedKb = bandwidthUsedKb;
    }


    /**
     * @return the bandwidthRemainingKb
     */
    public String getBandwidthRemainingKb() {
	return bandwidthRemainingKb;
    }


    /**
     * @param bandwidthRemainingKb the bandwidthRemainingKb to set
     */
    public void setBandwidthRemainingKb(String bandwidthRemainingKb) {
	this.bandwidthRemainingKb = bandwidthRemainingKb;
    }


    /**
     * @return the bandwidthUnlimited
     */
    public boolean isBandwidthUnlimited() {
	return bandwidthUnlimited;
    }


    /**
     * @param bandwidthUnlimited the bandwidthUnlimited to set
     */
    public void setBandwidthUnlimited(boolean bandwidthUnlimited) {
	this.bandwidthUnlimited = bandwidthUnlimited;
    }


    /**
     * @return the filesizeMax
     */
    public String getFilesizeMax() {
	return filesizeMax;
    }


    /**
     * @param filesizeMax the filesizeMax to set
     */
    public void setFilesizeMax(String filesizeMax) {
	this.filesizeMax = filesizeMax;
    }


    /**
     * @return the filesizeMaxBytes
     */
    public String getFilesizeMaxBytes() {
	return filesizeMaxBytes;
    }


    /**
     * @param filesizeMaxBytes the filesizeMaxBytes to set
     */
    public void setFilesizeMaxBytes(String filesizeMaxBytes) {
	this.filesizeMaxBytes = filesizeMaxBytes;
    }


    /**
     * @return the filesizeMaxKb
     */
    public String getFilesizeMaxKb() {
	return filesizeMaxKb;
    }


    /**
     * @param filesizeMaxKb the filesizeMaxKb to set
     */
    public void setFilesizeMaxKb(String filesizeMaxKb) {
	this.filesizeMaxKb = filesizeMaxKb;
    }


    /**
     * @return the filesizeMaxMb
     */
    public String getFilesizeMaxMb() {
	return filesizeMaxMb;
    }


    /**
     * @param filesizeMaxMb the filesizeMaxMb to set
     */
    public void setFilesizeMaxMb(String filesizeMaxMb) {
	this.filesizeMaxMb = filesizeMaxMb;
    }


    /**
     * @return the setsCreated
     */
    public String getSetsCreated() {
	return setsCreated;
    }


    /**
     * @param setsCreated the setsCreated to set
     */
    public void setSetsCreated(String setsCreated) {
	this.setsCreated = setsCreated;
    }


    /**
     * @return the setsRemaining
     */
    public String getSetsRemaining() {
	return setsRemaining;
    }


    /**
     * @param setsRemaining the setsRemaining to set
     */
    public void setSetsRemaining(String setsRemaining) {
	this.setsRemaining = setsRemaining;
    }


    /**
     * @return the videoSizeMaxBytes
     */
    public String getVideoSizeMaxBytes() {
	return videoSizeMaxBytes;
    }


    /**
     * @param videoSizeMaxBytes the videoSizeMaxBytes to set
     */
    public void setVideoSizeMaxBytes(String videoSizeMaxBytes) {
	this.videoSizeMaxBytes = videoSizeMaxBytes;
    }


    /**
     * @return the videoSizeMaxKb
     */
    public String getVideoSizeMaxKb() {
	return videoSizeMaxKb;
    }


    /**
     * @param videoSizeMaxKb the videoSizeMaxKb to set
     */
    public void setVideoSizeMaxKb(String videoSizeMaxKb) {
	this.videoSizeMaxKb = videoSizeMaxKb;
    }


    /**
     * @return the videoSizeMaxMb
     */
    public String getVideoSizeMaxMb() {
	return videoSizeMaxMb;
    }


    /**
     * @param videoSizeMaxMb the videoSizeMaxMb to set
     */
    public void setVideoSizeMaxMb(String videoSizeMaxMb) {
	this.videoSizeMaxMb = videoSizeMaxMb;
    }


    /**
     * @return the videosUploaded
     */
    public String getVideosUploaded() {
	return videosUploaded;
    }


    /**
     * @param videosUploaded the videosUploaded to set
     */
    public void setVideosUploaded(String videosUploaded) {
	this.videosUploaded = videosUploaded;
    }


    /**
     * @return the videosRemaining
     */
    public String getVideosRemaining() {
	return videosRemaining;
    }


    /**
     * @param videosRemaining the videosRemaining to set
     */
    public void setVideosRemaining(String videosRemaining) {
	this.videosRemaining = videosRemaining;
    }



    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder(this.getClass().getName());

	sb.append(" [ ");
	sb.append("id=").append(this.getId()).append(" | ");
	sb.append("nsid=").append(this.getNsid()).append(" | ");
	sb.append("username=").append(this.getUsername()).append(" | ");
	sb.append("pro=").append(this.isPro()).append(" | ");

	sb.append("bandwidthMax=").append(this.getBandwidthMax()).append(" | ");
	sb.append("bandwidthUsed=").append(this.getBandwidthUsed()).append(" | ");
	sb.append("bandwidthMaxBytes=").append(this.getBandwidthMaxBytes()).append(" | ");
	sb.append("bandwidthUsedBytes=").append(this.getBandwidthUsedBytes()).append(" | ");
	sb.append("bandwidthRemainingBytes=").append(this.getBandwidthRemainingBytes()).append(" | ");
	sb.append("bandwidthMaxKb=").append(this.getBandwidthMaxKb()).append(" | ");
	sb.append("bandwidthUsedKb=").append(this.getBandwidthUsedKb()).append(" | ");
	sb.append("bandwidthRemainingKb=").append(this.getBandwidthRemainingKb()).append(" | ");
	sb.append("bandwidthUnlimited=").append(this.isBandwidthUnlimited()).append(" | ");

	sb.append("filesizeMax=").append(this.getFilesizeMax()).append(" | ");
	sb.append("filesizeMaxBytes=").append(this.getFilesizeMaxBytes()).append(" | ");
	sb.append("filesizeMaxKb=").append(this.getFilesizeMaxKb()).append(" | ");
	sb.append("filesizeMaxMb=").append(this.getFilesizeMaxMb()).append(" | ");

	sb.append("setsCreated=").append(this.getSetsCreated()).append(" | ");
	sb.append("setsRemaining=").append(this.getSetsRemaining()).append(" | ");

	sb.append("videoSizeMaxBytes=").append(this.getVideoSizeMaxBytes()).append(" | ");
	sb.append("videoSizeMaxKb=").append(this.getVideoSizeMaxKb()).append(" | ");
	sb.append("videoSizeMaxMb=").append(this.getVideoSizeMaxMb()).append(" | ");
	sb.append("videosUploaded=").append(this.getVideosUploaded()).append(" | ");
	sb.append("videosRemaining=").append(this.getVideosRemaining());

	sb.append(" ]");
	return sb.toString();
    }
}
