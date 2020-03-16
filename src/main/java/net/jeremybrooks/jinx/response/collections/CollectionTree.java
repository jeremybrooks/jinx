/*
 * Jinx is Copyright 2010-2020 by Jeremy Brooks and Contributors
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
import net.jeremybrooks.jinx.response.Response;

import java.io.Serializable;
import java.util.List;

/**
 * Represents data returned by <a href="https://www.flickr.com/services/api/flickr.collections.getTree.html">flickr.collections.getTree</a>
 *
 * @author Jeremy Brooks
 */
public class CollectionTree extends Response {
	private static final long serialVersionUID = 4161752403540557211L;

	private _Collections collections;

	public List<Collection> getCollectionList() {
		return collections == null ? null : collections.collectionList;
	}

	private class _Collections implements Serializable {
		private static final long serialVersionUID = -96689176191660822L;
		@SerializedName("collection")
		private List<Collection> collectionList;
	}

	public class Collection implements Serializable {
		private static final long serialVersionUID = 3025493502862467077L;
		@SerializedName("id")
		private String collectionId;
		private String title;
		private String description;
		@SerializedName("iconlarge")
		private String iconLarge;
		@SerializedName("iconsmall")
		private String iconSmall;
		@SerializedName("set")
		private List<Set> setList;

		public String getCollectionId() {
			return collectionId;
		}
		public String getTitle() {
			return title;
		}
		public String getDescription() {
			return description;
		}
		public String getIconLarge() {
			return iconLarge;
		}
		public String getIconSmall() {
			return iconSmall;
		}
		public List<Set> getSetList() {
			return setList;
		}

		public class Set implements Serializable {
			private static final long serialVersionUID = -6888266293650650649L;
			@SerializedName("id")
			private String photosetId;
			private String title;
			private String description;

			public String getPhotosetId() {
				return photosetId;
			}

			public String getTitle() {
				return title;
			}

			public String getDescription() {
				return description;
			}

			@Override
			public String toString() {
				final StringBuilder sb = new StringBuilder();
				sb.append("net.jeremybrooks.jinx.response.collections.Collections.Collection.Set");
				sb.append("{photosetId='").append(photosetId).append('\'');
				sb.append(" | title='").append(title).append('\'');
				sb.append(" | description='").append(description).append('\'');
				sb.append('}');
				return sb.toString();
			}
		}


		@Override
		public String toString() {
			final StringBuilder sb = new StringBuilder();
			sb.append("net.jeremybrooks.jinx.response.collections.Collections.Collection");
			sb.append("{collectionId='").append(collectionId).append('\'');
			sb.append(" | title='").append(title).append('\'');
			sb.append(" | description='").append(description).append('\'');
			sb.append(" | iconLarge='").append(iconLarge).append('\'');
			sb.append(" | iconSmall='").append(iconSmall).append('\'');
			sb.append(" | setList=").append(setList == null ? "null" : setList.size());
			sb.append('}');
			return sb.toString();
		}
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
					sb.append("net.jeremybrooks.jinx.response.collections.Collections");
		sb.append("{collectionList=").append(collections.collectionList == null ? "null" : collections.collectionList.size());
		sb.append('}');
		return sb.toString();
	}
}
