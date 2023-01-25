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
import net.jeremybrooks.jinx.response.Response;

import java.io.Serializable;

/**
 * Context can be returned from several methods:
 * <br>
 * flickr.favorites.getContext
 * flickr.groups.pools.getContext
 * flickr.photos.getContext
 * flickr.photosets.getContext ({@link net.jeremybrooks.jinx.api.PhotosetsApi#getContext(String, String)})
 *
 * @author Jeremy Brooks
 */
public class Context extends Response {
	private static final long serialVersionUID = -938218649798494740L;
	private _Count count;
	private PrevPhoto prevphoto;
	private NextPhoto nextphoto;

	public Integer getCount() {
		return count.count;
	}

	public PrevPhoto getPrevPhoto() {
		return prevphoto;
	}

	public NextPhoto getNextPhoto() {
		return nextphoto;
	}


	private class _Count implements Serializable {
    private static final long serialVersionUID = 2244002449621461488L;
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


	public class PrevPhoto implements Serializable {
		private static final long serialVersionUID = 8291352009160659374L;
		@SerializedName("id")
		private String photoId;
		private String owner;
		private String secret;
		private String server;
		private String farm;
		private String title;
		private String url;
		private String thumb;
		private Integer license;
		private String media;
		@SerializedName("is_faved")
		private String isFaved;    // return as Boolean

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

		public Boolean isFaved() {
			return JinxUtils.flickrBooleanToBoolean(isFaved);
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

	public class NextPhoto implements Serializable {
		private static final long serialVersionUID = 179339426853292258L;
		@SerializedName("id")
		private String photoId;
		private String owner;
		private String secret;
		private String server;
		private String farm;
		private String title;
		private String url;
		private String thumb;
		private Integer license;
		private String media;
		@SerializedName("is_faved")
		private String isFaved;    // return as Boolean

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

		public Boolean isFaved() {
			return JinxUtils.flickrBooleanToBoolean(isFaved);
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
