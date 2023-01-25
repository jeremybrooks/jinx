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

package net.jeremybrooks.jinx.response.photosets;

import com.google.gson.annotations.SerializedName;
import net.jeremybrooks.jinx.JinxUtils;
import net.jeremybrooks.jinx.response.Response;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jeremy Brooks
 */
public class PhotosetList extends Response {
	private static final long serialVersionUID = -2815200964947537180L;
	private _Photosets photosets;

	public Boolean isCanCreate() {
		return photosets == null ? null : JinxUtils.flickrBooleanToBoolean(photosets.isCanCreate);
	}

	public Integer getPage() {
		return photosets == null ? null : photosets.page;
	}
	public Integer getPages() {
		return photosets == null ? null : photosets.pages;
	}

	public Integer getPerPage() {
		return photosets == null ? null : photosets.perPage;
	}

	public Integer getTotal() {
		return photosets == null ? null : photosets.total;
	}

	public List<Photoset> getPhotosetList() {
		return photosets == null ? null : photosets.photosetList;
	}



	private class _Photosets implements Serializable {
		private static final long serialVersionUID = -3710302042846416278L;
		@SerializedName("cancreate")
		private String isCanCreate; // return as Boolean
		private Integer page;
		private Integer pages;
		@SerializedName("perpage")
		private Integer perPage;
		private Integer total;
		@SerializedName("photoset")
		private List<Photoset> photosetList;
	}

		@Override
		public String toString() {
			final StringBuilder sb = new StringBuilder();
			sb.append("net.jeremybrooks.jinx.response.photosets.PhotosetList");
			sb.append("{isCanCreate=").append(isCanCreate());
			sb.append(" | page=").append(getPage());
			sb.append(" | pages=").append(getPages());
			sb.append(" | perPage=").append(getPerPage());
			sb.append(" | total=").append(getTotal());
			sb.append(" | photosetList=[").append(getPhotosetList() == null ? "null" : getPhotosetList().size()).append(']');
			sb.append('}');
			return sb.toString();
	}
}

