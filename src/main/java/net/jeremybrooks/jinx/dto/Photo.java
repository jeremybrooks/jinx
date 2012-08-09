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

import net.jeremybrooks.jinx.Jinx;
import net.jeremybrooks.jinx.JinxUtils;

import java.io.Serializable;

/**
 * Information about a photo.
 *
 * Many of the values are represented as constants in the JinxConstants
 * class.
 *
 * Instances of this class are returned by many of the API methods. The populated
 * fields depend on the method and the arguments that are supplied to the
 * method. Do not count on all these fields being populated just because you
 * have an instance of this class.
 * 
 * @author jeremyb
 */
public class Photo extends PhotoBase implements Serializable {


    private String latitude;
    private String longitude;
    private String accuracy;
    private String iconServer;
    private String iconFarm;
    private String machineTags;
    private String mediaStatus;
    private int oWidth;
    private int oHeight;
    private String pathAlias;
    private String tags;
    private String urlM;
    private int heightM;
    private int widthM;
    private String urlO;
    private int heightO;
    private int widthO;
    private String urlS;
    private int heightS;
    private int widthS;
    private String urlSq;
    private int heightSq;
    private int widthSq;
    private String urlT;
    private int heightT;
    private int widthT;
    private String urlZ;
    private int heightZ;
    private int widthZ;
    private String urlQ;
    private int heightQ;
    private int widthQ;
    private String urlN;
    private int heightN;
    private int widthN;
    private String urlC;
    private int heightC;
    private int widthC;
    private String urlL;
    private int heightL;
    private int widthL;
	private String urlH;
	private int heightH;
	private int widthH;
	private String urlK;
	private int heightK;
	private int widthK;


    private String url;
    private String thumb;

    // may be returned by methods in the Gallery API
    private boolean hasComment;
    private String comment;

    private boolean faved;


    /**
     * @return the iconServer
     */
    public String getIconServer() {
	return iconServer;
    }


    /**
     * @param iconServer the iconServer to set
     */
    public void setIconServer(String iconServer) {
	this.iconServer = iconServer;
    }


    /**
     * @return the tags
     */
    public String getTags() {
	return tags;
    }


    /**
     * @param tags the tags to set
     */
    public void setTags(String tags) {
	this.tags = tags;
    }


    /**
     * @return the machineTags
     */
    public String getMachineTags() {
	return machineTags;
    }


    /**
     * @param machineTags the machineTags to set
     */
    public void setMachineTags(String machineTags) {
	this.machineTags = machineTags;
    }


    /**
     * @return the pathAlias
     */
    public String getPathAlias() {
	return pathAlias;
    }


    /**
     * @param pathAlias the pathAlias to set
     */
    public void setPathAlias(String pathAlias) {
	this.pathAlias = pathAlias;
    }


    /**
     * @return the urlSq
     */
    public String getUrlSq() {
	return urlSq;
    }


    /**
     * @param urlSq the urlSq to set
     */
    public void setUrlSq(String urlSq) {
	this.urlSq = urlSq;
    }


    /**
     * @return the urlT
     */
    public String getUrlT() {
	return urlT;
    }


    /**
     * @param urlT the urlT to set
     */
    public void setUrlT(String urlT) {
	this.urlT = urlT;
    }


    /**
     * @return the urlS
     */
    public String getUrlS() {
	return urlS;
    }


    /**
     * @param urlS the urlS to set
     */
    public void setUrlS(String urlS) {
	this.urlS = urlS;
    }


    /**
     * @return the urlM
     */
    public String getUrlM() {
	return urlM;
    }


    /**
     * @param urlM the urlM to set
     */
    public void setUrlM(String urlM) {
	this.urlM = urlM;
    }


    /**
     * @return the urlO
     */
    public String getUrlO() {
	return urlO;
    }


    /**
     * @param urlO the urlO to set
     */
    public void setUrlO(String urlO) {
	this.urlO = urlO;
    }


    /**
     * @return the latitude
     */
    public String getLatitude() {
	return latitude;
    }


    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(String latitude) {
	this.latitude = latitude;
    }


    /**
     * @return the longitude
     */
    public String getLongitude() {
	return longitude;
    }


    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(String longitude) {
	this.longitude = longitude;
    }


    /**
     * @return the accuracy
     */
    public String getAccuracy() {
	return accuracy;
    }


    /**
     * @param accuracy the accuracy to set
     */
    public void setAccuracy(String accuracy) {
	this.accuracy = accuracy;
    }


    /**
     * @return the iconFarm
     */
    public String getIconFarm() {
	return iconFarm;
    }


    /**
     * @param iconFarm the iconFarm to set
     */
    public void setIconFarm(String iconFarm) {
	this.iconFarm = iconFarm;
    }


    /**
     * @return the mediaStatus
     */
    public String getMediaStatus() {
	return mediaStatus;
    }


    /**
     * @param mediaStatus the mediaStatus to set
     */
    public void setMediaStatus(String mediaStatus) {
	this.mediaStatus = mediaStatus;
    }


    /**
     * @return the oWidth
     */
    public int getoWidth() {
	return oWidth;
    }


    /**
     * @param oWidth the oWidth to set
     */
    public void setoWidth(int oWidth) {
	this.oWidth = oWidth;
    }


    /**
     * @return the oHeight
     */
    public int getoHeight() {
	return oHeight;
    }


    /**
     * @param oHeight the oHeight to set
     */
    public void setoHeight(int oHeight) {
	this.oHeight = oHeight;
    }


    /**
     * @return the heightM
     */
    public int getHeightM() {
	return heightM;
    }


    /**
     * @param heightM the heightM to set
     */
    public void setHeightM(int heightM) {
	this.heightM = heightM;
    }


    /**
     * @return the widthM
     */
    public int getWidthM() {
	return widthM;
    }


    /**
     * @param widthM the widthM to set
     */
    public void setWidthM(int widthM) {
	this.widthM = widthM;
    }


    /**
     * @return the heightO
     */
    public int getHeightO() {
	return heightO;
    }


    /**
     * @param heightO the heightO to set
     */
    public void setHeightO(int heightO) {
	this.heightO = heightO;
    }


    /**
     * @return the widthO
     */
    public int getWidthO() {
	return widthO;
    }


    /**
     * @param widthO the widthO to set
     */
    public void setWidthO(int widthO) {
	this.widthO = widthO;
    }


    /**
     * @return the heightS
     */
    public int getHeightS() {
	return heightS;
    }


    /**
     * @param heightS the heightS to set
     */
    public void setHeightS(int heightS) {
	this.heightS = heightS;
    }


    /**
     * @return the widthS
     */
    public int getWidthS() {
	return widthS;
    }


    /**
     * @param widthS the widthS to set
     */
    public void setWidthS(int widthS) {
	this.widthS = widthS;
    }


    /**
     * @return the heightSq
     */
    public int getHeightSq() {
	return heightSq;
    }


    /**
     * @param heightSq the heightSq to set
     */
    public void setHeightSq(int heightSq) {
	this.heightSq = heightSq;
    }


    /**
     * @return the widthSq
     */
    public int getWidthSq() {
	return widthSq;
    }


    /**
     * @param widthSq the widthSq to set
     */
    public void setWidthSq(int widthSq) {
	this.widthSq = widthSq;
    }


    /**
     * @return the heightT
     */
    public int getHeightT() {
	return heightT;
    }


    /**
     * @return the widthT
     */
    public int getWidthT() {
	return widthT;
    }


    /**
     * @param heightT the heightT to set
     */
    public void setHeightT(int heightT) {
	this.heightT = heightT;
    }


    /**
     * @param widthT the widthT to set
     */
    public void setWidthT(int widthT) {
	this.widthT = widthT;
    }


    /**
     * Get the photo page URL for this photo.
     *
     * Photo URL's are in the format
     * http://www.flickr.com/photos/{user-id}/{photo-id}
     *
     * If the URL is not set, it will be built.
     *
     * @return the url
     */
    public String getUrl() {
	if (JinxUtils.isEmpty(this.url)) {
	    StringBuilder sb = new StringBuilder("http://www.flickr.com/photos/");
	    if (JinxUtils.isEmpty(super.getOwnerNsid())) {
		sb.append(Jinx.getInstance().getToken().getNsid());
	    } else {
		sb.append(super.getOwnerNsid());
	    }
	    sb.append('/').append(super.getId());

	    this.url = sb.toString();
	}
	
	return url;
    }


    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
	this.url = url;
    }


    /**
     * @return the thumb
     */
    public String getThumb() {
	return thumb;
    }


    /**
     * @param thumb the thumb to set
     */
    public void setThumb(String thumb) {
	this.thumb = thumb;
    }


    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder("Photo [ ");
	sb.append("id=").append(super.getId()).append(" | ");
	sb.append("ownerNsid=").append(super.getOwnerNsid()).append(" | ");
	sb.append("secret=").append(super.getSecret()).append(" | ");
	sb.append("server=").append(super.getServer()).append(" | ");
	sb.append("farm=").append(super.getFarm()).append(" | ");
	sb.append("title=").append(super.getTitle()).append(" | ");
	sb.append("isPublic=").append(super.isIsPublic()).append(" | ");
	sb.append("isFriend=").append(super.isIsFriend()).append(" | ");
	sb.append("isFamily=").append(super.isIsFamily()).append(" | ");
	sb.append("dateTaken=").append(super.getDateTaken()).append(" | ");
	sb.append("dateTakenGranularity=").append(super.getDateTakenGranularity()).append(" | ");
	sb.append("dateUpload=").append(super.getDateUploaded()).append(" | ");
	sb.append("latitude=").append(this.latitude).append(" | ");
	sb.append("longitude=").append(this.longitude).append(" | ");
	sb.append("accuracy=").append(this.accuracy).append(" | ");
	sb.append("iconServer=").append(this.iconServer).append(" | ");
	sb.append("iconFarm=").append(this.iconFarm).append(" | ");
	sb.append("lastUpdate=").append(super.getDateLastUpdate()).append(" | ");
	sb.append("license=").append(super.getLicense()).append(" | ");
	sb.append("machineTags=").append(this.machineTags).append(" | ");
	sb.append("media=").append(super.getMedia()).append(" | ");
	sb.append("mediaStatus=").append(this.mediaStatus).append(" | ");
	sb.append("originalSecret=").append(super.getOriginalSecret()).append(" | ");
	sb.append("originalFormat=").append(super.getOriginalFormat()).append(" | ");
	sb.append("ownerUsername=").append(super.getOwnerUsername()).append(" | ");
	sb.append("oWidth=").append(this.oWidth).append(" | ");
	sb.append("oHeight=").append(this.oHeight).append(" | ");
	sb.append("pathAlias=").append(this.pathAlias).append(" | ");
	sb.append("tags=").append(this.tags).append(" | ");
	sb.append("urlM=").append(this.urlM).append(" | ");
	sb.append("heightM=").append(this.heightM).append(" | ");
	sb.append("widthM=").append(this.widthM).append(" | ");
	sb.append("urlO=").append(this.urlO).append(" | ");
	sb.append("heightO=").append(this.heightO).append(" | ");
	sb.append("widthO=").append(this.widthO).append(" | ");
	sb.append("urlS=").append(this.urlS).append(" | ");
	sb.append("heightS=").append(this.heightS).append(" | ");
	sb.append("widthS=").append(this.widthS).append(" | ");
	sb.append("urlSq=").append(this.urlSq).append(" | ");
	sb.append("heightSq=").append(this.heightSq).append(" | ");
	sb.append("widthSq=").append(this.widthSq).append(" | ");
	sb.append("urlT=").append(this.urlT).append(" | ");
	sb.append("heightT=").append(this.heightT).append(" | ");
	sb.append("widthT=").append(this.widthT).append(" | ");
	sb.append("urlZ=").append(this.urlZ).append(" | ");
        sb.append("heightZ=").append(this.heightZ).append(" | ");
        sb.append("widthZ=").append(this.widthZ).append(" | ");
        sb.append("urlQ=").append(this.getUrlQ()).append(" | ");
        sb.append("heightQ=").append(this.getHeightQ()).append(" | ");
        sb.append("widthQ=").append(this.getWidthQ()).append(" | ");
        sb.append("urlN=").append(this.getUrlN()).append(" | ");
        sb.append("heightN=").append(this.getHeightN()).append(" | ");
        sb.append("widthN=").append(this.getWidthN()).append(" | ");
        sb.append("urlC=").append(this.getUrlC()).append(" | ");
        sb.append("heightC=").append(this.getHeightC()).append(" | ");
        sb.append("widthC=").append(this.getWidthC()).append(" | ");
        sb.append("urlL=").append(this.getUrlL()).append(" | ");
        sb.append("heightL=").append(this.getHeightL()).append(" | ");
        sb.append("widthL=").append(this.getWidthL()).append(" | ");
		sb.append("urlH=").append(this.getUrlH()).append(" | ");
		sb.append("heightH=").append(this.getHeightH()).append(" | ");
		sb.append("widthH=").append(this.getWidthH()).append(" | ");
		sb.append("urlK=").append(this.getUrlK()).append(" | ");
		sb.append("heightK=").append(this.getHeightK()).append(" | ");
		sb.append("widthK=").append(this.getWidthK()).append(" | ");
        sb.append("views=").append(super.getViews()).append(" | ");
        sb.append("description=").append(super.getDescription()).append(" | ");
	sb.append("url=").append(this.url).append(" | ");
	sb.append("thumb=").append(this.thumb).append(" | ");
	sb.append("faved=").append(this.isFaved()).append(" | ");
	sb.append("hasComment=").append(this.hasComment).append(" | ");
	sb.append("comment=").append(this.comment);

	sb.append(" ]");

	return sb.toString();
    }


    /**
     * @return the urlZ
     */
    public String getUrlZ() {
	return urlZ;
    }


    /**
     * @param urlZ the urlZ to set
     */
    public void setUrlZ(String urlZ) {
	this.urlZ = urlZ;
    }


    /**
     * @return the heightZ
     */
    public int getHeightZ() {
	return heightZ;
    }


    /**
     * @param heightZ the heightZ to set
     */
    public void setHeightZ(int heightZ) {
	this.heightZ = heightZ;
    }


    /**
     * @return the widthZ
     */
    public int getWidthZ() {
	return widthZ;
    }


    /**
     * @param widthZ the widthZ to set
     */
    public void setWidthZ(int widthZ) {
	this.widthZ = widthZ;
    }


    /**
     * Note: This refers to the comment left on a photo that is in a gallery,
     *       not comments left on the photo in a user's photostream.
     * 
     * @return the hasComment
     */
    public boolean isHasComment() {
	return hasComment;
    }


    /**
     * @param hasComment the hasComment to set
     */
    public void setHasComment(boolean hasComment) {
	this.hasComment = hasComment;
    }


    /**
     * Note: This refers to the comment left on a photo that is in a gallery,
     *       not comments left on the photo in a user's photostream.
     * 
     * @return the comment
     */
    public String getComment() {
	return comment;
    }


    /**
     * @param comment the comment to set
     */
    public void setComment(String comment) {
	this.comment = comment;
    }


    /**
     * @return the faved
     */
    public boolean isFaved() {
	return faved;
    }


    /**
     * @param faved the faved to set
     */
    public void setFaved(boolean faved) {
	this.faved = faved;
    }

    public String getUrlQ() {
        return urlQ;
    }

    public void setUrlQ(String urlQ) {
        this.urlQ = urlQ;
    }

    public int getHeightQ() {
        return heightQ;
    }

    public void setHeightQ(int heightQ) {
        this.heightQ = heightQ;
    }

    public int getWidthQ() {
        return widthQ;
    }

    public void setWidthQ(int widthQ) {
        this.widthQ = widthQ;
    }

    public String getUrlN() {
        return urlN;
    }

    public void setUrlN(String urlN) {
        this.urlN = urlN;
    }

    public int getHeightN() {
        return heightN;
    }

    public void setHeightN(int heightN) {
        this.heightN = heightN;
    }

    public int getWidthN() {
        return widthN;
    }

    public void setWidthN(int widthN) {
        this.widthN = widthN;
    }

    public String getUrlC() {
        return urlC;
    }

    public void setUrlC(String urlC) {
        this.urlC = urlC;
    }

    public int getHeightC() {
        return heightC;
    }

    public void setHeightC(int heightC) {
        this.heightC = heightC;
    }

    public int getWidthC() {
        return widthC;
    }

    public void setWidthC(int widthC) {
        this.widthC = widthC;
    }

    public String getUrlL() {
        return urlL;
    }

    public void setUrlL(String urlL) {
        this.urlL = urlL;
    }

    public int getHeightL() {
        return heightL;
    }

    public void setHeightL(int heightL) {
        this.heightL = heightL;
    }

    public int getWidthL() {
        return widthL;
    }

    public void setWidthL(int widthL) {
        this.widthL = widthL;
    }

	public String getUrlH() {
	        return urlH;
	    }

	    public void setUrlH(String urlH) {
	        this.urlH = urlH;
	    }

	    public int getHeightH() {
	        return heightH;
	    }

	    public void setHeightH(int heightH) {
	        this.heightH = heightH;
	    }

	    public int getWidthH() {
	        return widthH;
	    }

	    public void setWidthH(int widthH) {
	        this.widthH = widthH;
	    }

	public String getUrlK() {
	        return urlK;
	    }

	    public void setUrlK(String urlK) {
	        this.urlK = urlK;
	    }

	    public int getHeightK() {
	        return heightK;
	    }

	    public void setHeightK(int heightK) {
	        this.heightK = heightK;
	    }

	    public int getWidthK() {
	        return widthK;
	    }

	    public void setWidthK(int widthK) {
	        this.widthK = widthK;
	    }
}