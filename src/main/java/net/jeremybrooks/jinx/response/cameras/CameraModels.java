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

package net.jeremybrooks.jinx.response.cameras;

import com.google.gson.annotations.SerializedName;
import net.jeremybrooks.jinx.response.Response;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jeremy Brooks
 */
public class CameraModels extends Response {
	private static final long serialVersionUID = 5336235134861435473L;
	private _Cameras cameras;

	public String getBrand() {
		return cameras == null ? null : cameras.brand;
	}

	public List<Camera> getCameraList() {
		return cameras == null ? null : cameras.camera;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("classname=").append(this.getClass().getName());
		sb.append(",brand=\"").append(getBrand()).append('\"');
		sb.append(",cameras=[");
		for (Camera camera : this.getCameraList()) {
			sb.append(camera.toString());
		}
		sb.append("],");
		sb.append(super.toString());
		return sb.toString();
	}

	private class _Cameras implements Serializable {
		private static final long serialVersionUID = 6110608884472691417L;
		String brand;
		List<Camera> camera;
	}

	public class Camera implements Serializable {
		private static final long serialVersionUID = 3370720188127822663L;
		private String id;
		private _Name name;
		private _Details details;
		private _Images images;

		public String getId() {
			return id;
		}

		public String getName() {
			return name == null ? null : name.content;
		}

		public Float getMegapixels() {
			return details == null ? null : details.getMegapixels();
		}

		public Float getLcdScreenSize() {
			return details == null ? null : details.getLcdScreenSize();
		}

		public String getMemoryType() {
			return details == null ? null : details.getMemoryType();
		}

		public String getSmallImageUrl() {
			return images == null ? null : images.getSmall();
		}

		public String getLargeImageUrl() {
			return images == null ? null : images.getLarge();
		}

		@Override
		public String toString() {
			final StringBuilder sb = new StringBuilder();
			sb.append("{id=\"").append(id).append('\"');
			sb.append(",name=\"").append(getName()).append('\"');
			sb.append(",megapixels=").append(getMegapixels());
			sb.append(",lcdScreenSize=").append(getLcdScreenSize());
			sb.append(",memoryType=\"").append(getMemoryType()).append('\"');
			sb.append(",smallImageUrl=\"").append(getSmallImageUrl()).append('\"');
			sb.append(",largeImageUrl=\"").append(getLargeImageUrl()).append('\"');
			sb.append('}');
			return sb.toString();
		}
	}

	private class _Name implements Serializable {
		private static final long serialVersionUID = -8395818191890522521L;
		@SerializedName("_content")
		private String content;
	}

	private class _Details implements Serializable {
		private static final long serialVersionUID = -4375235958392485757L;
		private _Megapixels megapixels;
		@SerializedName("lcd_screen_size")
		private _LcdScreenSize lcdScreenSize;
		@SerializedName("memory_type")
		private _MemoryType memoryType;

		private Float getMegapixels() {
			return megapixels == null ? null : megapixels.content;
		}

		private Float getLcdScreenSize() {
			return lcdScreenSize == null ? null : lcdScreenSize.content;
		}

		private String getMemoryType() {
			return memoryType == null ? null : memoryType.content;
		}
	}

	private class _Images implements Serializable {
		private static final long serialVersionUID = -3487997356225262846L;
		private _Small small;
		private _Large large;

		private String getSmall() {
			return small == null ? null : small.content;
		}

		private String getLarge() {
			return large == null ? null : large.content;
		}
	}

	private class _Megapixels implements Serializable {
		private static final long serialVersionUID = -5094835629273842630L;
		@SerializedName("_content")
		private Float content;
	}

	private class _LcdScreenSize implements Serializable {
		private static final long serialVersionUID = -2163124030788373598L;
		@SerializedName("_content")
		private Float content;
	}

	private class _MemoryType implements Serializable {
		private static final long serialVersionUID = -6977235775115994046L;
		@SerializedName("_content")
		private String content;
	}

	private class _Small implements Serializable {
		private static final long serialVersionUID = -951231015663898513L;
		@SerializedName("_content")
		String content;
	}

	private class _Large implements Serializable {
		private static final long serialVersionUID = -6767373169949956301L;
		@SerializedName("_content")
		String content;
	}
}
