package net.jeremybrooks.jinx.response.photosets;

import com.google.gson.annotations.SerializedName;
import net.jeremybrooks.jinx.response.Response;

import java.util.List;

/**
 * Contains the data returned by {@link net.jeremybrooks.jinx.api.PhotosetsApi#getPhotos(String, java.util.EnumSet, net.jeremybrooks.jinx.JinxConstants.PrivacyFilter, int, int, net.jeremybrooks.jinx.JinxConstants.MediaType)}
 *
 * The photo data is returned as a list of {@link PhotosetPhoto} objects.
 *
 * @author Jeremy Brooks
 */
public class PhotosetPhotos extends Response {

	@SerializedName("photoset")
	private PhotosetMetadata photoset;

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

	public List<PhotosetPhoto> getPhotoList() { return photoset == null ? null : photoset.photoList; }

	private class PhotosetMetadata {
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
		private List<PhotosetPhoto> photoList;


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
