/*
 * Jinx is Copyright 2010-2023 by Jeremy Brooks and Contributors
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

package net.jeremybrooks.jinx.response.common;

import com.google.gson.annotations.SerializedName;
import net.jeremybrooks.jinx.JinxUtils;

import java.io.Serializable;

/**
 * @author Jeremy Brooks
 */
public class PrimaryPhotoExtras implements Serializable {
	private static final long serialVersionUID = -1915040017261347562L;
	private Integer license;
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
	private String iconFarm;
	private Integer views;
	private String tags;
	@SerializedName("machine_tags")
	private String machineTags;
	@SerializedName("originalsecret")
	private String originalSecret;
	@SerializedName("originalformat")
	private String originalFormat;
	private Float latitude;
	private Float longitude;
	private Integer accuracy;
	private Integer context;
	@SerializedName("place_id")
	private String placeId;
	@SerializedName("woeid")
	private String woeId;
	@SerializedName("geo_is_family")
	private String isGeoIsFamily;       // return as Boolean
	@SerializedName("geo_is_friend")
	private String isGeoIsFriend;       // return as Boolean
	@SerializedName("geo_is_contact")
	private String isGeoIsContact;      // return as Boolean
	@SerializedName("geo_is_public")
	private String isGeoIsPublic;       // return as Boolean
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
	@SerializedName("url_m")
	private String urlM;
	@SerializedName("height_m")
	private Integer heightM;
	@SerializedName("width_m")
	private Integer widthM;
	@SerializedName("url_o")
	private String urlO;
	@SerializedName("height_o")
	private Integer heightO;
	@SerializedName("width_o")
	private Integer widthO;
	@SerializedName("pathalias")
	private String pathAlias;


	public Integer getLicense() {
		return license;
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

	public String getIconFarm() {
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

	public Boolean isGeoIsFamily() {
		return JinxUtils.flickrBooleanToBoolean(isGeoIsFamily);
	}

	public Boolean isGeoIsFriend() { return JinxUtils.flickrBooleanToBoolean(isGeoIsFriend);
	}

	public Boolean isGeoIsContact() {
		return JinxUtils.flickrBooleanToBoolean(isGeoIsContact);
	}

	public Boolean isGeoIsPublic() {
		return JinxUtils.flickrBooleanToBoolean(isGeoIsPublic);
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

	public String getUrlM() {
		return urlM;
	}

	public Integer getHeightM() {
		return heightM;
	}

	public Integer getWidthM() {
		return widthM;
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


	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("net.jeremybrooks.jinx.response.photosets.PrimaryPhotoExtras");
		sb.append("{license=").append(license);
		sb.append(" | oWidth='").append(oWidth).append('\'');
		sb.append(" | oHeight='").append(oHeight).append('\'');
		sb.append(" | dateUpload='").append(dateUpload).append('\'');
		sb.append(" | lastUpdate='").append(lastUpdate).append('\'');
		sb.append(" | dateTaken='").append(dateTaken).append('\'');
		sb.append(" | dateTakenGranularity=").append(dateTakenGranularity);
		sb.append(" | ownerName='").append(ownerName).append('\'');
		sb.append(" | iconServer='").append(iconServer).append('\'');
		sb.append(" | iconFarm='").append(iconFarm).append('\'');
		sb.append(" | views='").append(views).append('\'');
		sb.append(" | tags='").append(tags).append('\'');
		sb.append(" | machineTags='").append(machineTags).append('\'');
		sb.append(" | originalSecret='").append(originalSecret).append('\'');
		sb.append(" | originalFormat='").append(originalFormat).append('\'');
		sb.append(" | latitude=").append(latitude);
		sb.append(" | longitude=").append(longitude);
		sb.append(" | accuracy=").append(accuracy);
		sb.append(" | context=").append(context);
		sb.append(" | placeId='").append(placeId).append('\'');
		sb.append(" | woeId='").append(woeId).append('\'');
		sb.append(" | isGeoIsFamily=").append(isGeoIsFamily);
		sb.append(" | isGeoIsFriend=").append(isGeoIsFriend);
		sb.append(" | isGeoIsContact=").append(isGeoIsContact);
		sb.append(" | isGeoIsPublic=").append(isGeoIsPublic);
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
		sb.append(" | urlM='").append(urlM).append('\'');
		sb.append(" | heightM=").append(heightM);
		sb.append(" | widthM=").append(widthM);
		sb.append(" | urlO='").append(urlO).append('\'');
		sb.append(" | heightO=").append(heightO);
		sb.append(" | widthO=").append(widthO);
		sb.append(" | pathAlias='").append(pathAlias).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
