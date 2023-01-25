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

package net.jeremybrooks.jinx.response.collections;

import com.google.gson.annotations.SerializedName;
import net.jeremybrooks.jinx.JinxUtils;
import net.jeremybrooks.jinx.response.Response;

import java.io.Serializable;
import java.util.List;

/**
 * Represents data returned by <a href="https://www.flickr.com/services/api/flickr.collections.getInfo.html">flickr.collections.getInfo</a>
 *
 * @author Jeremy Brooks
 */
public class CollectionInfo extends Response {
	private static final long serialVersionUID = 8604729523042617746L;

	private _Collection collection;

	public String getCollectionId() {
		return collection == null ? null : collection.collectionId;
	}

	public String getTitle() {
		return collection == null ? null : collection.getTitle();
	}

	public String getDescription() {
		return collection == null ? null : collection.getDescription();
	}

	public Integer getChildCount() {
		return collection == null ? null : collection.child_count;
	}

	public String getDateCreate() {
		return collection == null ? null : collection.datecreate;
	}

	public String getIconLarge() {
		return collection == null ? null : collection.iconlarge;
	}

	public String getIconSmall() {
		return collection == null ? null : collection.iconsmall;
	}

	public String getServer() {
		return collection == null ? null : collection.server;
	}

	public String getSecret() {
		return collection == null ? null : collection.secret;
	}

	public List<IconPhoto> getIconPhotoList() {
		return collection == null ? null : collection.getIconPhotoList();
	}

	private class _Collection implements Serializable {
		private static final long serialVersionUID = -2561638069804054888L;
		@SerializedName("id")
		private String collectionId;
		private _Title title;
		private _Description description;
		private Integer child_count;
		private String datecreate;
		private String iconlarge;
		private String iconsmall;
		private String server;
		private String secret;
		private _IconPhotos iconphotos;

		String getTitle() {
			return title == null ? null : title._content;
		}

		String getDescription() {
			return description == null ? null : description._content;
		}

		List<IconPhoto> getIconPhotoList() {
			return iconphotos == null ? null : iconphotos.iconPhotoList;
		}

		private class _Title implements Serializable {
			private static final long serialVersionUID = 8146454087470891541L;
			private String _content;
		}

		private class _Description implements Serializable {
			private static final long serialVersionUID = 2992558754343600894L;
			private String _content;
		}

		private class _IconPhotos implements Serializable {
			private static final long serialVersionUID = 1188335218447290550L;
			@SerializedName("photo")
			private List<IconPhoto> iconPhotoList;
		}

	}

	public class IconPhoto implements Serializable {
		private static final long serialVersionUID = 1079649382389624280L;
		@SerializedName("id")
		private String photoId;
		private String owner;
		private String secret;
		private String server;
		private String farm;
		private String title;
		private String ispublic;    // return as Boolean
		private String isfriend;    // return as Boolean
		private String isfamily;    // return as Boolean

		public String getPhotoId() {
			return photoId;
		}

		public String getOwner() {
			return owner;
		}

		public String getSecret() {
			return secret;
		}

		public String getServer() {
			return server;
		}

		public String getFarm() {
			return farm;
		}

		public String getTitle() {
			return title;
		}

		public Boolean isPublic() {
			return JinxUtils.flickrBooleanToBoolean(ispublic);
		}

		public Boolean isFriend() {
			return JinxUtils.flickrBooleanToBoolean(isfriend);
		}

		public Boolean isFamily() {
			return JinxUtils.flickrBooleanToBoolean(isfamily);
		}
	}
}
