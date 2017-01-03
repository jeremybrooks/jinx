/*
 * Jinx is Copyright 2010-2017 by Jeremy Brooks and Contributors
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

package net.jeremybrooks.jinx.response.blogs;

import com.google.gson.annotations.SerializedName;
import net.jeremybrooks.jinx.response.Response;

import java.io.Serializable;
import java.util.List;

/**
 * Response from the Flickr blogs.getServices API request.
 *
 * @author Jeremy Brooks
 */
public class BlogServices extends Response {
	private static final long serialVersionUID = 5614928721323005674L;

	private _Services services;


	/**
	 * Get the list of services.
	 *
	 * @return list of services, or null if nothing was found.
	 */
	public List<Service> getServiceList() {
		return services.service == null ? null : services.service;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("classname=").append(this.getClass().getName());
		sb.append(",services=[");
		for (Service service : this.getServiceList()) {
			sb.append(service.toString());
		}
		sb.append("],");
		sb.append(super.toString());
		return sb.toString();
	}


	private class _Services implements Serializable {
		private List<Service> service;
	}

	/**
	 * Represents a blogging service.
	 * Each service has an ID and a Name.
	 */
	public class Service implements Serializable {
		private String id;
		@SerializedName("_content")
		private String name;

		/**
		 * Get the ID of the service.
		 * @return id of the service.
		 */
		public String getId() {
			return id;
		}

		/**
		 * Get the name of the service.
		 * Note: this field is called "_content" in the response document. We call it name here.
		 * @return name of the service.
		 */
		public String getName() {
			return name;
		}


		@Override
		public String toString() {
			final StringBuilder sb = new StringBuilder();
			sb.append("{id=\"").append(id).append('\"');
			sb.append(",name=\"").append(name).append('\"').append('}');
			return sb.toString();
		}
	}
}
