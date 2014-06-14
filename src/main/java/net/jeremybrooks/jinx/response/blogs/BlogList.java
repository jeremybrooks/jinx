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

package net.jeremybrooks.jinx.response.blogs;

import com.google.gson.annotations.SerializedName;
import net.jeremybrooks.jinx.JinxUtils;
import net.jeremybrooks.jinx.response.Response;

import java.io.Serializable;
import java.util.List;

/**
 * Response from the flickr.blogs.getList API request.
 *
 * @author Jeremy Brooks
 */
public class BlogList extends Response {
	private static final long serialVersionUID = -3559245797002820510L;
	private _Blogs blogs;

	/**
	 * Get the list of blogs.
	 *
	 * @return list of blogs, or null if no blogs were found.
	 */
	public List<Blog> getBlogList() {
		return blogs == null ? null : blogs.blog;
	}


	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("classname=").append(this.getClass().getName());
		sb.append(",blogs=[");
		for (Blog blog : this.getBlogList()) {
			sb.append(blog.toString());
		}
		sb.append("],");
		sb.append(super.toString());
		return sb.toString();
	}



	private class _Blogs implements Serializable {
		private static final long serialVersionUID = -7237564663251703852L;
		private List<Blog> blog;
	}

	public class Blog implements Serializable {
		private static final long serialVersionUID = -898285969764745893L;
		private String id;
		private String name;
		private String service;
		@SerializedName("needspassword")
		private int needsPassword;
		private String url;

		/**
		 * Get the URL of the blog.
		 * @return url of the blog.
		 */
		public String getUrl() {
			return url;
		}

		/**
		 * Get the ID of the blog.
		 * @return id of the blog.
		 */
		public String getId() {
			return id;
		}

		/**
		 * Get the name of the blog.
		 *
		 * @return blog name.
		 */
		public String getName() {
			return name;
		}

		/**
		 * Get the service.
		 * @return service.
		 */
		public String getService() {
			return service;
		}

		/**
		 * Indicates whether a call to flickr.blogs.postPhoto for this blog will require a password to be sent.
		 * When flickr has a password already stored, returns false.
		 * Note: Flickr represents this as a 1 or 0. Jinx will return true or false.
		 *
		 * @return true if postPhoto requires a password to be sent, false if Flickr has a password stored.
		 */
		public Boolean isNeedsPassword() {
			return JinxUtils.flickrBooleanToBoolean(needsPassword);
		}

		@Override
		public String toString() {
			final StringBuilder sb = new StringBuilder();
			sb.append("{id=\"").append(id).append('\"');
			sb.append(",name=\"").append(name).append('\"');
			sb.append(",service=\"").append(service).append('\"');
			sb.append(",needsPassword=").append(this.isNeedsPassword());
			sb.append(",url=\"").append(url).append('\"');
			sb.append('}');
			return sb.toString();
		}
	}
}
