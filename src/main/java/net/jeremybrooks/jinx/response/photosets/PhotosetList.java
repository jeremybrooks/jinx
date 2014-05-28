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

