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

package net.jeremybrooks.jinx.response.galleries;

import com.google.gson.annotations.SerializedName;
import net.jeremybrooks.jinx.response.Response;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jeremy Brooks
 */
public class GalleryList extends Response {
	private static final long serialVersionUID = -6129462473008668754L;

	private _Galleries galleries;


	public Integer getTotal() {
		return galleries == null ? null : galleries.total;
	}

	public Integer getPage() {
		return galleries == null ? null : galleries.page;
	}

	public Integer getPages() {
		return galleries == null ? null : galleries.pages;
	}

	public Integer getPerPage() {
		return galleries == null ? null : galleries.perPage;
	}

	public String getUserId() {
		return galleries == null ? null : galleries.userId;
	}

	public List<Gallery> getGalleryList() {
		return galleries == null ? null : galleries.galleryList;
	}

	private class _Galleries implements Serializable {
		private Integer total;
		private Integer page;
		private Integer pages;
		@SerializedName("perpage")
		private Integer perPage;
		@SerializedName("user_id")
		private String userId;
		@SerializedName("gallery")
		private List<Gallery> galleryList;
	}
}
