/*
 * Jinx is Copyright 2010-2013 by Jeremy Brooks and Contributors
 *
 * This file is part of Jinx.
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

package net.jeremybrooks.jinx.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author Jeremy Brooks
 */
public class CameraModels extends Response {
	private static final long serialVersionUID = 5336235134861435473L;
	private Cameras cameras;

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

	private class Cameras {
		String brand;
		List<Camera> camera;
	}

	public class Camera {
		private String id;
		private Name name;
		private Details details;
		private Images images;

		public String getId() {
			return id;
		}

		public String getName() {
			return name == null ? null : name.content;
		}

		public double getMegapixels() {
			return details == null ? 0.0 : details.getMegapixels();
		}

		public double getLcdScreenSize() {
			return details == null ? 0.0 : details.getLcdScreenSize();
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

	private class Name {
		@SerializedName("_content")
		private String content;
	}

	private class Details {
		private Megapixels megapixels;
		@SerializedName("lcd_screen_size")
		private LcdScreenSize lcdScreenSize;
		@SerializedName("memory_type")
		private MemoryType memoryType;

		private double getMegapixels() {
			return megapixels == null ? 0.0 : megapixels.content;
		}

		private double getLcdScreenSize() {
			return lcdScreenSize == null ? 0.0 : lcdScreenSize.content;
		}

		private String getMemoryType() {
			return memoryType == null ? null : memoryType.content;
		}
	}

	private class Images {
		private Small small;
		private Large large;

		private String getSmall() {
			return small == null ? null : small.content;
		}

		private String getLarge() {
			return large == null ? null : large.content;
		}
	}

	private class Megapixels {
		@SerializedName("_content")
		private double content;
	}

	private class LcdScreenSize {
		@SerializedName("_content")
		private double content;
	}

	private class MemoryType {
		@SerializedName("_content")
		private String content;
	}

	private class Small {
		@SerializedName("_content")
		String content;
	}

	private class Large {
		@SerializedName("_content")
		String content;
	}
}
