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
	public Integer getFarm() {
		return photo == null ? null : photo.farm;
	}
	public String getCamera() {
		return photo == null ? null : photo.camera;
	}
	public List<Exif> getExifList() {
		return photo == null ? null : photo.exifList;
	}



	@SerializedName("photo")
	private PhotoInfo photo;


	private class PhotoInfo implements Serializable {
		private static final long serialVersionUID = -5107803922027598893L;
		@SerializedName("id")
		private String photoId;
		private String secret;
		private String server;
		private Integer farm;
		private String camera;
		@SerializedName("exif")
		private List<Exif> exifList;
	}
	private class Raw implements Serializable {
		private static final long serialVersionUID = 4863783717672653029L;
		private String _content;
	}
	private class Clean implements Serializable {
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
		private Raw raw;
		private Clean clean;

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
