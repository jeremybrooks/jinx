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
import net.jeremybrooks.jinx.response.photos.Photo;

import java.io.Serializable;
import java.util.List;

/**
 * Contains the data returned by {@link net.jeremybrooks.jinx.api.PhotosetsApi#getPhotos(String, java.util.EnumSet, net.jeremybrooks.jinx.JinxConstants.PrivacyFilter, int, int, net.jeremybrooks.jinx.JinxConstants.MediaType)}
 *
 * The photo data is returned as a list of {@link net.jeremybrooks.jinx.response.photos.Photo} objects.
 *
 * @author Jeremy Brooks
 */
public class PhotosetPhotos extends Response {

	@SerializedName("photoset")
	private _PhotosetMetadata photoset;

	public String getPhotosetId() {
		return photoset == null ? null : photoset.photosetId;
	}
	public String getPrimary () {
		return photoset == null ? null : photoset.primary;
	}
	public String getOwner() {
		return photoset == null ? null : photoset.owner;
	}
	public String getOwnerName() {
		return photoset == null ? null : photoset.ownerName;
	}
	public Integer getPage() {
		return photoset == null ? null : photoset.page;
	}
	public String getPerPage() {
		return photoset == null ? null : photoset.perPage;
	}
	public Integer getPages() {
		return photoset == null ? null : photoset.pages;
	}
	public Integer getTotal() {
		return photoset == null ? null : photoset.total;
	}
	public String getTitle() {
		return photoset == null ? null : photoset.title;
	}

	public List<Photo> getPhotoList() { return photoset == null ? null : photoset.photoList; }

	private class _PhotosetMetadata implements Serializable {
		private static final long serialVersionUID = 3447953142745693617L;
		@SerializedName("id")
		private String photosetId;
		private String primary;
		private String owner;
		@SerializedName("ownername")
		private String ownerName;
		private Integer page;
		@SerializedName("per_page")
		private String perPage;
		private Integer pages;
		private Integer total;
		private String title;

		@SerializedName("photo")
		private List<Photo> photoList;


		@Override
		public String toString() {
			final StringBuilder sb = new StringBuilder();
			sb.append("net.jeremybrooks.jinx.response.photosets.PhotosetPhotos.PhotosetMetadata");
			sb.append("{photosetId='").append(photosetId).append('\'');
			sb.append(" | primary='").append(primary).append('\'');
			sb.append(" | owner='").append(owner).append('\'');
			sb.append(" | ownerName='").append(ownerName).append('\'');
			sb.append(" | page=").append(page);
			sb.append(" | perPage='").append(perPage).append('\'');
			sb.append(" | pages=").append(pages);
			sb.append(" | total=").append(total);
			sb.append(" | title='").append(title).append('\'');
			sb.append(" | photoList=").append(photoList == null ? "null" : photoList.size());
			sb.append('}');
			return sb.toString();
		}
	}


	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("net.jeremybrooks.jinx.response.photosets.PhotosetPhotos");
		sb.append("{photoset=").append(photoset);
		sb.append('}');
		return sb.toString();
	}
}
