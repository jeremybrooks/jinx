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

package net.jeremybrooks.jinx.response.galleries;

import com.google.gson.annotations.SerializedName;
import net.jeremybrooks.jinx.response.common.PrimaryPhotoExtras;

import java.io.Serializable;

/**
 * @author Jeremy Brooks
 */
public class Gallery implements Serializable {
	private static final long serialVersionUID = 7111217438038161732L;
	@SerializedName("id")
	private String galleryId;
	private String url;
	private String owner;
	private String username;
	@SerializedName("iconserver")
	private String iconServer;
	@SerializedName("iconfarm")
	private Integer iconFarm;
	@SerializedName("primary_photo_id")
	private String primaryPhotoId;
	@SerializedName("date_create")
	private String dateCreate;
	@SerializedName("date_update")
	private String dateUpdate;
	@SerializedName("count_photos")
	private Integer countPhotos;
	@SerializedName("count_videos")
	private Integer countVideos;
	@SerializedName("count_views")
	private Integer countViews;
	@SerializedName("count_comments")
	private Integer countComments;
	private _Title title;
	private _Description description;
	@SerializedName("primary_photo_server")
	private String primaryPhotoServer;
	@SerializedName("primary_photo_farm")
	private Integer primaryPhotoFarm;
	@SerializedName("primary_photo_secret")
	private String primaryPhotoSecret;
	@SerializedName("primary_photo_extras")
	private PrimaryPhotoExtras primaryPhotoExtras;

	public String getGalleryId() {
		return galleryId;
	}

	public String getUrl() {
		return url;
	}

	public String getOwner() {
		return owner;
	}

	public String getUsername() {
		return username;
	}

	public String getIconServer() {
		return iconServer;
	}

	public Integer getIconFarm() {
		return iconFarm;
	}

	public String getPrimaryPhotoId() {
		return primaryPhotoId;
	}

	public String getDateCreate() {
		return dateCreate;
	}

	public String getDateUpdate() {
		return dateUpdate;
	}

	public Integer getCountPhotos() {
		return countPhotos;
	}

	public Integer getCountVideos() {
		return countVideos;
	}

	public Integer getCountViews() {
		return countViews;
	}

	public Integer getCountComments() {
		return countComments;
	}


	public String getTitle() {
		return title == null ? null : title._content;
	}

	public String getDescription() {
		return description == null ? null : description._content;
	}

	public String getPrimaryPhotoServer() {
		return primaryPhotoServer;
	}

	public String getPrimaryPhotoSecret() {
		return primaryPhotoSecret;
	}

	public Integer getPrimaryPhotoFarm() {
		return primaryPhotoFarm;
	}

	public PrimaryPhotoExtras getPrimaryPhotoExtras() {
		return primaryPhotoExtras;
	}

	private class _Title implements Serializable {
		private static final long serialVersionUID = -6037755083595411320L;
		private String _content;
	}

	private class _Description implements Serializable {
		private static final long serialVersionUID = -213498842073345138L;
		private String _content;
	}
}
