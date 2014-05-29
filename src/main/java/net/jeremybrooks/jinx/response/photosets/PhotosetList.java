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

package net.jeremybrooks.jinx.response.photosets;

import com.google.gson.annotations.SerializedName;
import net.jeremybrooks.jinx.response.Response;

import java.util.List;

/**
 * @author Jeremy Brooks
 */
public class PhotosetList extends Response {
	private static final long serialVersionUID = -2815200964947537180L;
	private Photosets photosets;
	public Photosets getPhotosets() {return photosets; };


	public class Photosets {
		@SerializedName("cancreate")
		private Integer isCanCreate;
		private Integer page;
		private Integer pages;
		@SerializedName("perpage")
		private Integer perPage;
		private Integer total;
		@SerializedName("photoset")
		private List<Photoset> photosetList;

		@Override
		public String toString() {
			final StringBuilder sb = new StringBuilder();
			sb.append("net.jeremybrooks.jinx.response.photosets.PhotosetList");
			sb.append("{isCanCreate=").append(getIsCanCreate());
			sb.append(" | page=").append(getPage());
			sb.append(" | pages=").append(getPages());
			sb.append(" | perPage=").append(getPerPage());
			sb.append(" | total=").append(getTotal());
			sb.append(" | photosetList=[").append(getPhotosetList() == null ? "null" : getPhotosetList().size()).append(']');
			sb.append('}');
			return sb.toString();
		}

		public boolean getIsCanCreate() {
			return isCanCreate != null && isCanCreate == 1;
		}

		public Integer getPage() {
			return page;
		}

		public Integer getPages() {
			return pages;
		}

		public Integer getPerPage() {
			return perPage;
		}

		public Integer getTotal() {
			return total;
		}

		public List<Photoset> getPhotosetList() {
			return photosetList;
		}
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("net.jeremybrooks.jinx.response.photosets.PhotosetList");
		sb.append("{photosets=").append(photosets == null ? "null" : photosets);
		sb.append('}');
		return sb.toString();
	}
}

