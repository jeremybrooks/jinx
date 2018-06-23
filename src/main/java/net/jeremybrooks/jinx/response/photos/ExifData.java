/*
 * Jinx is Copyright 2010-2018 by Jeremy Brooks and Contributors
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
import net.jeremybrooks.jinx.response.Response;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jeremy Brooks
 */
public class ExifData extends Response {
	private static final long serialVersionUID = -3977724207910787105L;

	public String getPhotoId() {
		return photo == null ? null : photo.photoId;
	}
	public String getSecret() {
		return photo == null ? null : photo.secret;
	}
	public String getServer() {
		return photo == null ? null : photo.server;
	}
	public String getFarm() {
		return photo == null ? null : photo.farm;
	}
	public String getCamera() {
		return photo == null ? null : photo.camera;
	}
	public List<Exif> getExifList() {
		return photo == null ? null : photo.exifList;
	}



	@SerializedName("photo")
	private _PhotoInfo photo;


	private class _PhotoInfo implements Serializable {
		private static final long serialVersionUID = -5107803922027598893L;
		@SerializedName("id")
		private String photoId;
		private String secret;
		private String server;
		private String farm;
		private String camera;
		@SerializedName("exif")
		private List<Exif> exifList;
	}
	private class _Raw implements Serializable {
		private static final long serialVersionUID = 4863783717672653029L;
		private String _content;
	}
	private class _Clean implements Serializable {
		private static final long serialVersionUID = -1572246827198673080L;
		private String _content;
	}

	public class Exif implements Serializable {
		private static final long serialVersionUID = -7612860741103855493L;
		@SerializedName("tagspace")
		private String tagSpace;
		@SerializedName("tagspaceid")
		private Integer tagSpaceId;
		private String tag;
		private String label;
		private _Raw raw;
		private _Clean clean;

		public String getTagSpace() {
			return tagSpace;
		}

		public Integer getTagSpaceId() {
			return tagSpaceId;
		}

		public String getTag() {
			return tag;
		}

		public String getLabel() {
			return label;
		}

		public String getRaw() {
			return raw._content;
		}

		/**
		 *
		 * @return a pretty formatted version of the tag, where available.
		 */
		public String getClean() {
			return clean._content;
		}
	}
}
