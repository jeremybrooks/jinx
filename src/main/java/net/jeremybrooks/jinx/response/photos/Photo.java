/*
 * Jinx is Copyright 2010-2014 by Jeremy Brooks and Contributors
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

package net.jeremybrooks.jinx.response.photos;

import com.google.gson.annotations.SerializedName;
import net.jeremybrooks.jinx.JinxUtils;

import java.io.Serializable;

/**
 * @author Jeremy Brooks
 */
public class Photo implements Serializable {
	private static final long serialVersionUID = -7437988440115270607L;

	@SerializedName("id")
	private String photoId;
	private String secret;
	private String server;
	private Integer farm;
	private String owner;
	private String username;
	private String title;

	@SerializedName("ispublic")
	private String isPublic;        // return as Boolean
	@SerializedName("isfriend")
	private String isFriend;        // return as Boolean
	@SerializedName("isfamily")
	private String isFamily;        // return as Boolean

	private Integer license;
	private _DescriptionClass description;

	@SerializedName("o_width")
	private String oWidth;
	@SerializedName("o_height")
	private String oHeight;

	@SerializedName("dateupload")
	private String dateUpload;
	@SerializedName("lastupdate")
	private String lastUpdate;
	@SerializedName("datetaken")
	private String dateTaken;
	@SerializedName("datetakengranularity")
	private Integer dateTakenGranularity;

	@SerializedName("ownername")
	private String ownerName;
	@SerializedName("iconserver")
	private String iconServer;
	@SerializedName("iconfarm")
	private Integer iconFarm;
	private Integer views;
	private String tags;
	@SerializedName("machine_tags")
	private String machineTags;

	@SerializedName("originalsecret")
	private String originalSecret;
	@SerializedName("originalformat")
	private String originalFormat;
	@SerializedName("date_faved")
	private String dateFaved;
	private Float latitude;
	private Float longitude;
	private Integer accuracy;
	private Integer context;
	@SerializedName("place_id")
	private String placeId;
	@SerializedName("woeid")
	private String woeId;

	private String media;
	@SerializedName("media_status")
	private String mediaStatus;

	@SerializedName("url_sq")
	private String urlSq;
	@SerializedName("height_sq")
	private Integer heightSq;
	@SerializedName("width_sq")
	private Integer widthSq;

	@SerializedName("url_t")
	private String urlT;
	@SerializedName("height_t")
	private Integer heightT;
	@SerializedName("width_t")
	private Integer widthT;

	@SerializedName("url_s")
	private String urlS;
	@SerializedName("height_s")
	private Integer heightS;
	@SerializedName("width_s")
	private Integer widthS;

	@SerializedName("url_q")
	private String urlQ;
	@SerializedName("height_q")
	private Integer heightQ;
	@SerializedName("width_q")
	private Integer widthQ;


	@SerializedName("url_m")
	private String urlM;
	@SerializedName("height_m")
	private Integer heightM;
	@SerializedName("width_m")
	private Integer widthM;

	@SerializedName("url_n")
	private String urlN;
	@SerializedName("height_n")
	private Integer heightN;
	@SerializedName("width_n")
	private Integer widthN;

	@SerializedName("url_z")
	private String urlZ;
	@SerializedName("height_z")
	private Integer heightZ;
	@SerializedName("width_z")
	private Integer widthZ;

	@SerializedName("url_c")
	private String urlC;
	@SerializedName("height_c")
	private Integer heightC;
	@SerializedName("width_c")
	private Integer widthC;

	@SerializedName("url_l")
	private String urlL;
	@SerializedName("height_l")
	private Integer heightL;
	@SerializedName("width_l")
	private Integer widthL;

	@SerializedName("url_o")
	private String urlO;
	@SerializedName("height_o")
	private Integer heightO;
	@SerializedName("width_o")
	private Integer widthO;

	@SerializedName("pathalias")
	private String pathAlias;


	@SerializedName("is_primary")
	private String primary;     // return as Boolean
	@SerializedName("isprimary")
	private String primaryNoUnderscore; // return as Boolean

	@SerializedName("geo_is_family")
	private String geoIsFamily;     // return as Boolean
	@SerializedName("geo_is_friend")
	private String geoIsFriend;     // return as Boolean
	@SerializedName("geo_is_contact")
	private String geoIsContact;    // return as Boolean
	@SerializedName("geo_is_public")
	private String geoIsPublic;     // return as Boolean

	@SerializedName("has_comment")
	private String hasComment;      // return as Boolean

    @SerializedName("dateadded")
    private String dateAdded;

    public String getPhotoId() {
		return photoId;
	}

	public String getSecret() {
		return secret;
	}

	public String getServer() {
		return server;
	}

	public Integer getFarm() {
		return farm;
	}

	public String getOwner() {
		return owner;
	}

	public String getUsername() {
		return username;
	}

	public String getTitle() {
		return title;
	}

	public Boolean isPublic() {
		return JinxUtils.flickrBooleanToBoolean(isPublic);
	}

	public Boolean isFriend() {
		return JinxUtils.flickrBooleanToBoolean(isFriend);
	}

	public Boolean isFamily() {
		return JinxUtils.flickrBooleanToBoolean(isFamily);
	}

	public Integer getLicense() {
		return license;
	}

	public String getDescription() {
		return description.description;
	}

	public String getoWidth() {
		return oWidth;
	}

	public String getoHeight() {
		return oHeight;
	}

	public String getDateUpload() {
		return dateUpload;
	}

	public String getLastUpdate() {
		return lastUpdate;
	}

	public String getDateTaken() {
		return dateTaken;
	}

	public Integer getDateTakenGranularity() {
		return dateTakenGranularity;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public String getIconServer() {
		return iconServer;
	}

	public Integer getIconFarm() {
		return iconFarm;
	}

	public Integer getViews() {
		return views;
	}

	public String getTags() {
		return tags;
	}

	public String getMachineTags() {
		return machineTags;
	}

	public String getOriginalSecret() {
		return originalSecret;
	}

	public String getOriginalFormat() {
		return originalFormat;
	}

	public String getDateFaved() { return dateFaved; };

	public Float getLatitude() {
		return latitude;
	}

	public Float getLongitude() {
		return longitude;
	}

	public Integer getAccuracy() {
		return accuracy;
	}

	public Integer getContext() {
		return context;
	}

	public String getPlaceId() {
		return placeId;
	}

	public String getWoeId() {
		return woeId;
	}

	public String getMedia() {
		return media;
	}

	public String getMediaStatus() {
		return mediaStatus;
	}

	public String getUrlSq() {
		return urlSq;
	}

	public Integer getHeightSq() {
		return heightSq;
	}

	public Integer getWidthSq() {
		return widthSq;
	}

	public String getUrlT() {
		return urlT;
	}

	public Integer getHeightT() {
		return heightT;
	}

	public Integer getWidthT() {
		return widthT;
	}

	public String getUrlS() {
		return urlS;
	}

	public Integer getHeightS() {
		return heightS;
	}

	public Integer getWidthS() {
		return widthS;
	}

	public String getUrlQ() {
		return urlQ;
	}

	public Integer getHeightQ() {
		return heightQ;
	}

	public Integer getWidthQ() {
		return widthQ;
	}

	public String getUrlM() {
		return urlM;
	}

	public Integer getHeightM() {
		return heightM;
	}

	public Integer getWidthM() {
		return widthM;
	}

	public String getUrlN() {
		return urlN;
	}

	public Integer getHeightN() {
		return heightN;
	}

	public Integer getWidthN() {
		return widthN;
	}

	public String getUrlZ() {
		return urlZ;
	}

	public Integer getHeightZ() {
		return heightZ;
	}

	public Integer getWidthZ() {
		return widthZ;
	}

	public String getUrlC() {
		return urlC;
	}

	public Integer getHeightC() {
		return heightC;
	}

	public Integer getWidthC() {
		return widthC;
	}

	public String getUrlL() {
		return urlL;
	}

	public Integer getHeightL() {
		return heightL;
	}

	public Integer getWidthL() {
		return widthL;
	}

	public String getUrlO() {
		return urlO;
	}

	public Integer getHeightO() {
		return heightO;
	}

	public Integer getWidthO() {
		return widthO;
	}

	public String getPathAlias() {
		return pathAlias;
	}

	public Boolean isPrimary() {
		// some methods return a photo object with "isprimary" and some "is_primary". Handle both.
		if (primary == null && primaryNoUnderscore == null) {
			return null;
		} else if (primary != null) {
			return JinxUtils.flickrBooleanToBoolean(primary);
		} else {
			return JinxUtils.flickrBooleanToBoolean(primaryNoUnderscore);
		}
	}

	public Boolean isGeoIsFamily() {
		return JinxUtils.flickrBooleanToBoolean(geoIsFamily);
	}

	public Boolean isGeoIsFriend() {
		return JinxUtils.flickrBooleanToBoolean(geoIsFriend);
	}

	public Boolean isGeoIsContact() {
		return JinxUtils.flickrBooleanToBoolean(geoIsContact);
	}

	public Boolean isGeoIsPublic() {
		return JinxUtils.flickrBooleanToBoolean(geoIsPublic);
	}

	public Boolean isHasComment() { return JinxUtils.flickrBooleanToBoolean(hasComment); }

    /**
     * Part of the return from flickr.groups.pools.getPhotos
     * @return date the photo was added to the pool.
     */
    public String getDateAdded() {return dateAdded;}

	private class _DescriptionClass implements Serializable {
		private static final long serialVersionUID = 8223398980226083052L;
		@SerializedName("_content")
		private String description;
	}


	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("net.jeremybrooks.jinx.response.photos.Photo");
		sb.append("{photoId='").append(photoId).append('\'');
		sb.append(" | secret='").append(secret).append('\'');
		sb.append(" | server='").append(server).append('\'');
		sb.append(" | farm=").append(farm);
		sb.append(" | owner='").append(owner).append('\'');
		sb.append(" | username='").append(username).append('\'');
		sb.append(" | title='").append(title).append('\'');
		sb.append(" | isPublic=").append(isPublic);
		sb.append(" | isFriend=").append(isFriend);
		sb.append(" | isFamily=").append(isFamily);
		sb.append(" | license=").append(license);
		sb.append(" | description=").append(getDescription());
		sb.append(" | oWidth='").append(oWidth).append('\'');
		sb.append(" | oHeight='").append(oHeight).append('\'');
		sb.append(" | dateUpload='").append(dateUpload).append('\'');
		sb.append(" | lastUpdate='").append(lastUpdate).append('\'');
		sb.append(" | dateTaken='").append(dateTaken).append('\'');
		sb.append(" | dateTakenGranularity=").append(dateTakenGranularity);
        sb.append(" | dateAdded='").append(dateAdded).append('\'');
        sb.append(" | ownerName='").append(ownerName).append('\'');
		sb.append(" | iconServer=").append(iconServer);
		sb.append(" | iconFarm=").append(iconFarm);
		sb.append(" | views=").append(views);
		sb.append(" | tags='").append(tags).append('\'');
		sb.append(" | machineTags='").append(machineTags).append('\'');
		sb.append(" | originalSecret='").append(originalSecret).append('\'');
		sb.append(" | originalFormat='").append(originalFormat).append('\'');
		sb.append(" | dateFaved='").append(dateFaved).append('\'');
		sb.append(" | latitude=").append(latitude);
		sb.append(" | longitude=").append(longitude);
		sb.append(" | accuracy=").append(accuracy);
		sb.append(" | context=").append(context);
		sb.append(" | placeId='").append(placeId).append('\'');
		sb.append(" | woeId='").append(woeId).append('\'');
		sb.append(" | media='").append(media).append('\'');
		sb.append(" | mediaStatus='").append(mediaStatus).append('\'');
		sb.append(" | urlSq='").append(urlSq).append('\'');
		sb.append(" | heightSq=").append(heightSq);
		sb.append(" | widthSq=").append(widthSq);
		sb.append(" | urlT='").append(urlT).append('\'');
		sb.append(" | heightT=").append(heightT);
		sb.append(" | widthT=").append(widthT);
		sb.append(" | urlS='").append(urlS).append('\'');
		sb.append(" | heightS=").append(heightS);
		sb.append(" | widthS=").append(widthS);
		sb.append(" | urlQ='").append(urlQ).append('\'');
		sb.append(" | heightQ=").append(heightQ);
		sb.append(" | widthQ=").append(widthQ);
		sb.append(" | urlM='").append(urlM).append('\'');
		sb.append(" | heightM=").append(heightM);
		sb.append(" | widthM=").append(widthM);
		sb.append(" | urlN='").append(urlN).append('\'');
		sb.append(" | heightN=").append(heightN);
		sb.append(" | widthN=").append(widthN);
		sb.append(" | urlZ='").append(urlZ).append('\'');
		sb.append(" | heightZ=").append(heightZ);
		sb.append(" | widthZ=").append(widthZ);
		sb.append(" | urlC='").append(urlC).append('\'');
		sb.append(" | heightC=").append(heightC);
		sb.append(" | widthC=").append(widthC);
		sb.append(" | urlL='").append(urlL).append('\'');
		sb.append(" | heightL=").append(heightL);
		sb.append(" | widthL=").append(widthL);
		sb.append(" | urlO='").append(urlO).append('\'');
		sb.append(" | heightO=").append(heightO);
		sb.append(" | widthO=").append(widthO);
		sb.append(" | pathAlias='").append(pathAlias).append('\'');
		sb.append(" | primary=").append(primary);
		sb.append(" | geoIsFamily=").append(isGeoIsFamily());
		sb.append(" | geoIsFriend=").append(isGeoIsFriend());
		sb.append(" | geoIsContact=").append(isGeoIsContact());
		sb.append(" | geoIsPublic=").append(isGeoIsPublic());
		sb.append(" | hasComment=").append(isHasComment());
		sb.append('}');
		return sb.toString();
	}
}
