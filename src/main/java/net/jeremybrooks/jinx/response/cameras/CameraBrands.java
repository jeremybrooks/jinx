
/*
 * Jinx is Copyright 2010-2020 by Jeremy Brooks and Contributors
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

import net.jeremybrooks.jinx.response.Response;

import java.io.Serializable;
import java.util.List;

/**
 * Returned from the {@link net.jeremybrooks.jinx.api.CamerasApi#getBrands()} method.
 *
 * @author Jeremy Brooks
 */
public class CameraBrands extends Response {

	private static final long serialVersionUID = 7434360333589257680L;
	private _Brands brands;

	/**
	 * Get a list of all the brands of cameras that Flickr knows about.
	 *
	 * @return list of brands.
	 */
	public List<Brand> getBrandList() {
		return brands.brand == null ? null : brands.brand;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("classname=").append(this.getClass().getName());
		sb.append(",brands=[");
		for (Brand brand : this.getBrandList()) {
			sb.append(brand.toString());
		}
		sb.append("],");
		sb.append(super.toString());
		return sb.toString();
	}


	private class _Brands implements Serializable {
		private static final long serialVersionUID = 4908547188546356637L;
		private List<Brand> brand;
	}

	/**
	 * Represents a brand of camera.
	 */
	public class Brand implements Serializable {
		private static final long serialVersionUID = -6969647627745634478L;
		private String id;
		private String name;

		/**
		 * Get the brand Id.
		 *
		 * @return brand Id.
		 */
		public String getId() {
			return id;
		}

		/**
		 * Get the brand name.
		 *
		 * @return brand name.
		 */
		public String getName() {
			return name;
		}

		@Override
		public String toString() {
			final StringBuilder sb = new StringBuilder();
			sb.append("{id=\"").append(id).append('\"');
			sb.append(",name=\"").append(name).append('\"');
			sb.append('}');
			return sb.toString();
		}
	}
}
