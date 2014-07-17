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

package net.jeremybrooks.jinx.response.photos;

import com.google.gson.annotations.SerializedName;
import net.jeremybrooks.jinx.JinxUtils;
import net.jeremybrooks.jinx.response.Response;

import java.util.List;

/**
 * @author Jeremy Brooks
 */
public class Photos extends Response {
	private static final long serialVersionUID = -5432339685020968718L;

	private _Photos photos;

	public Integer getTotal() { return photos == null ? null : photos.total; }
	public Integer getPage() { return photos == null ? 0 : photos.page; }
	public Integer getPages() { return photos == null ? null : photos.pages; }

    /**
     * This value is not returned in most of the Photo data structures.
     *
     * @return value of has_next_page as a Boolean; null if the value was not returned by Flickr.
     */
    public Boolean isHasNextPage() { return photos == null ? null : JinxUtils.flickrBooleanToBoolean(photos.hasNextPage); }

	/**
	 * Get the number of photos per page.
	 * This value is returned by flickr as "per_page" and "perpage", depending on the method that was called.
	 * This class can parse either value, and will return whichever one was found.
	 *
	 * @return number of photos per page.
	 */
	public Integer getPerPage() {
		int ret = 0;
		if (photos != null) {
			int pp;
			if (photos.perpage == null) {
				pp = 0;
			} else {
				pp = photos.perpage;
			}

			int pP;
			if (photos.perPage == null ) {
				pP = 0;
			} else {
				pP = photos.perPage;
			}

			if (pp > pP) {
				ret = pp;
			} else {
				ret = pP;
			}
		}
		return ret;
	}

	public List<Photo> getPhotoList() { return photos == null ? null : photos.photoList; }

	private class _Photos {
		private Integer total;
		private Integer page;
		private Integer pages;
		@SerializedName("per_page")
		private Integer perPage;
		@SerializedName("perpage")
		private Integer perpage;
		@SerializedName("photo")
		private List<Photo> photoList;
        @SerializedName("has_next_page")
        private String hasNextPage;     // return as Boolean
	}


	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("net.jeremybrooks.jinx.response.photos.PhotosResponse");
		sb.append("{page=").append(getPage());
		sb.append(" | total='").append(getTotal()).append('\'');
		sb.append(" | perPage=").append(getPerPage()).append('\'');
		sb.append(" | pages='").append(getPages()).append('\'');
        sb.append(" | hasNextPage=").append(isHasNextPage());
		sb.append(" | photoList='").append(getPhotoList() == null ? "null" : getPhotoList().size()).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
