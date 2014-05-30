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

import net.jeremybrooks.jinx.response.Response;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jeremy Brooks
 */
public class PhotoSizes extends Response {
	private static final long serialVersionUID = 2262040853836524178L;

	private _Sizes sizes;

	public boolean isCanBlog() {
		return sizes != null && sizes.canblog == 1;
	}

	public boolean isCanPrint() {
		return sizes != null && sizes.canprint == 1;
	}

	public boolean isCanDownload() {
		return sizes != null && sizes.candownload == 1;
	}

	public List<Size> getSizeList() {
		return sizes == null ? null : sizes.size;
	}

	public class Size implements Serializable {
		private static final long serialVersionUID = 3742856917947029698L;
		private String label;
		private Integer width;
		private Integer height;
		private String source;
		private String url;
		private String media;

		public String getLabel() {
			return label;
		}

		public Integer getWidth() {
			return width;
		}

		public Integer getHeight() {
			return height;
		}

		public String getSource() {
			return source;
		}

		public String getUrl() {
			return url;
		}

		public String getMedia() {
			return media;
		}

		@Override
		public String toString() {
			final StringBuilder sb = new StringBuilder();
			sb.append("net.jeremybrooks.jinx.response.photos.PhotoSizes.Size");
			sb.append("{label='").append(label).append('\'');
			sb.append(" | width=").append(width);
			sb.append(" | height=").append(height);
			sb.append(" | source='").append(source).append('\'');
			sb.append(" | url='").append(url).append('\'');
			sb.append(" | media='").append(media).append('\'');
			sb.append('}');
			return sb.toString();
		}
	}

	private class _Sizes implements Serializable {
		private static final long serialVersionUID = -5462995391373686811L;
		private Integer canblog;
		private Integer canprint;
		private Integer candownload;
		private List<Size> size;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("net.jeremybrooks.jinx.response.photos.PhotoSizes");
		sb.append("{canBlog=").append(isCanBlog());
		sb.append(" | canPrint=").append(isCanPrint());
		sb.append(" | canDownload=").append(isCanDownload());
		sb.append(" | sizeList=").append(getSizeList() == null ? "null" : getSizeList().size());
		sb.append('}');
		return sb.toString();
	}
}
