package net.jeremybrooks.jinx.response.photos;

import com.google.gson.annotations.SerializedName;
import net.jeremybrooks.jinx.response.Response;

import java.util.List;

/**
 * @author Jeremy Brooks
 */
public class Photos extends Response {
	private static final long serialVersionUID = -5432339685020968718L;

	private _Photos photos;

	public String getTotal() { return photos == null ? null : photos.total; }
	public Integer getPage() { return photos == null ? 0 : photos.page; }
	public String getPages() { return photos == null ? null : photos.pages; }

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
		private String total;
		private Integer page;
		private String pages;
		@SerializedName("per_page")
		private Integer perPage;
		@SerializedName("perpage")
		private Integer perpage;
		@SerializedName("photo")
		private List<Photo> photoList;
	}


	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("net.jeremybrooks.jinx.response.photos.PhotosResponse");
		sb.append("{page=").append(getPage());
		sb.append(" | total='").append(getTotal()).append('\'');
		sb.append(" | perPage=").append(getPerPage()).append('\'');
		sb.append(" | pages='").append(getPages()).append('\'');
		sb.append(" | photoList='").append(getPhotoList() == null ? "null" : getPhotoList().size()).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
