package net.jeremybrooks.jinx.response.common;

import com.google.gson.annotations.SerializedName;
import net.jeremybrooks.jinx.response.Response;

/**
 * Context can be returned from several methods:
 *
 * flickr.favorites.getContext
 * flickr.groups.pools.getContext
 * flickr.photos.getContext
 * flickr.photosets.getContext ({@link net.jeremybrooks.jinx.api.PhotosetsApi#getContext(String, String)})
 *
 * @author Jeremy Brooks
 */
public class Context extends Response {
	private static final long serialVersionUID = -938218649798494740L;
	private Count count;
	private Prevphoto prevphoto;
	private Nextphoto nextphoto;

	public Integer getCount() {
		return count.count;
	}
	public Prevphoto getPrevphoto() {
		return prevphoto;
	}
	public Nextphoto getNextphoto() {
		return nextphoto;
	}


	private class Count {
		@SerializedName("_content")
		private Integer count;

		@Override
		public String toString() {
			final StringBuilder sb = new StringBuilder();
			sb.append("net.jeremybrooks.jinx.response.Context.Count");
			sb.append("{count=").append(count);
			sb.append('}');
			return sb.toString();
		}
	}


	public class Prevphoto {
		@SerializedName("id")
		private String photoId;
		private String owner;
		private String secret;
		private String server;
		private Integer farm;
		private String title;
		private String url;
		private String thumb;
		private Integer license;
		private String media;
		@SerializedName("is_faved")
		private Integer isFaved;

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

		public Integer getFarm() {
			return farm;
		}

		public String getTitle() {
			return title;
		}

		public String getUrl() {
			return url;
		}

		public String getThumb() {
			return thumb;
		}

		public Integer getLicense() {
			return license;
		}

		public String getMedia() {
			return media;
		}

		public boolean getIsFaved() {
			return isFaved == 1 ? true : false;
		}

		@Override
		public String toString() {
			final StringBuilder sb = new StringBuilder();
			sb.append("net.jeremybrooks.jinx.response.Context.PrevPhoto");
			sb.append("{photoId='").append(photoId).append('\'');
			sb.append(" | owner='").append(owner).append('\'');
			sb.append(" | secret='").append(secret).append('\'');
			sb.append(" | server='").append(server).append('\'');
			sb.append(" | farm='").append(farm).append('\'');
			sb.append(" | title='").append(title).append('\'');
			sb.append(" | url='").append(url).append('\'');
			sb.append(" | thumb='").append(thumb).append('\'');
			sb.append(" | license=").append(license);
			sb.append(" | media='").append(media).append('\'');
			sb.append(" | isFaved=").append(isFaved);
			sb.append('}');
			return sb.toString();
		}
	}

	public class Nextphoto {
		@SerializedName("id")
		private String photoId;
		private String owner;
		private String secret;
		private String server;
		private Integer farm;
		private String title;
		private String url;
		private String thumb;
		private Integer license;
		private String media;
		@SerializedName("is_faved")
		private Integer isFaved;

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

		public Integer getFarm() {
			return farm;
		}

		public String getTitle() {
			return title;
		}

		public String getUrl() {
			return url;
		}

		public String getThumb() {
			return thumb;
		}

		public Integer getLicense() {
			return license;
		}

		public String getMedia() {
			return media;
		}

		public boolean getIsFaved() {
			return isFaved == 1 ? true : false;
		}

		@Override
		public String toString() {
			final StringBuilder sb = new StringBuilder();
			sb.append("net.jeremybrooks.jinx.response.Context.Nextphoto");
			sb.append("{photoId='").append(photoId).append('\'');
			sb.append(" | owner='").append(owner).append('\'');
			sb.append(" | secret='").append(secret).append('\'');
			sb.append(" | server='").append(server).append('\'');
			sb.append(" | farm='").append(farm).append('\'');
			sb.append(" | title='").append(title).append('\'');
			sb.append(" | url='").append(url).append('\'');
			sb.append(" | thumb='").append(thumb).append('\'');
			sb.append(" | license=").append(license);
			sb.append(" | media='").append(media).append('\'');
			sb.append(" | isFaved=").append(isFaved);
			sb.append('}');
			return sb.toString();
		}
	}


	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("net.jeremybrooks.jinx.response.Context");
		sb.append("{count=").append(count);
		sb.append(" | prevphoto=").append(prevphoto);
		sb.append(" | nextphoto=").append(nextphoto);
		sb.append('}');
		return sb.toString();
	}
}
