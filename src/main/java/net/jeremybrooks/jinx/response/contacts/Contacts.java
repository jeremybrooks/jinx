/*
 * Jinx is Copyright 2010-2018 by Jeremy Brooks and Contributors
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

package net.jeremybrooks.jinx.response.contacts;

import com.google.gson.annotations.SerializedName;
import net.jeremybrooks.jinx.JinxUtils;
import net.jeremybrooks.jinx.response.Response;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jeremy Brooks
 */
public class Contacts extends Response {
	private static final long serialVersionUID = -180728369656713947L;
	private _Contacts contacts;

	public Integer getPage() {
		return contacts == null ? null : contacts.page;
	}

	public Integer getPages() {
		return contacts == null ? null : contacts.pages;
	}

	public Integer getPerPage() {
		return contacts == null ? null : contacts.perPage;
	}

	public Integer getTotal() {
		return contacts == null ? null : contacts.total;
	}

	public List<Contact> getContactList() {
		return contacts == null ? null : contacts.contactList;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("net.jeremybrooks.jinx.response.contacts.Contacts");
		sb.append("{page=").append(getPage());
		sb.append(" | pages=").append(getPages());
		sb.append(" | perPage=").append(getPerPage());
		sb.append(" | total=").append(getTotal());
		sb.append(" | contactList=").append(getContactList() == null ? "null" : getContactList().size() + " contacts");
		sb.append('}');
		return sb.toString();
	}


	private class _Contacts implements Serializable {
		private static final long serialVersionUID = 2013519539177909509L;
		private Integer page;
		private Integer pages;
		@SerializedName("perpage")
		private Integer perPage;
		private Integer total;
		@SerializedName("contact")
		private List<Contact> contactList;
	}


	public class Contact implements Serializable {
		private static final long serialVersionUID = 4048260869881726082L;
        @SerializedName("nsid")
		private String userId;
		private String username;
		@SerializedName("iconserver")
		private String iconServer;
		@SerializedName("iconfarm")
		private String iconFarm;
		private String ignored;      // return as Boolean
		@SerializedName("rev_ignored")
		private String revIgnored;   // return as Boolean
		@SerializedName("realname")
		private String realName;
		private String friend;       // return as Boolean
		private String family;       // return as Boolean
		@SerializedName("path_alias")
		private String pathAlias;
		private String location;
		@SerializedName("photos_uploaded")
		private Integer photosUploaded;


		public String getUserId() {
			return userId;
		}

		public String getUsername() {
			return username;
		}

		public String getIconServer() {
			return iconServer;
		}

		public String getIconFarm() {
			return iconFarm;
		}

		public Boolean isIgnored() {
			return JinxUtils.flickrBooleanToBoolean(ignored);
		}

		public Boolean isRevIgnored() {
			return JinxUtils.flickrBooleanToBoolean(revIgnored);
		}

		public String getRealName() {
			return realName;
		}

		public Boolean isFriend() {
			return JinxUtils.flickrBooleanToBoolean(friend);
		}

		public Boolean isFamily() {
			return JinxUtils.flickrBooleanToBoolean(family);
		}

		public String getPathAlias() {
			return pathAlias;
		}

		public String getLocation() {
			return location;
		}

		public Integer getPhotosUploaded() {
			return photosUploaded;
		}

		@Override
		public String toString() {
			final StringBuilder sb = new StringBuilder();
			sb.append("net.jeremybrooks.jinx.response.contacts.Contacts.Contact");
			sb.append("{userId='").append(userId).append('\'');
			sb.append(" | username='").append(username).append('\'');
			sb.append(" | iconServer='").append(iconServer).append('\'');
			sb.append(" | iconFarm=").append(iconFarm);
			sb.append(" | ignored=").append(ignored);
			sb.append(" | revIgnored=").append(revIgnored);
			sb.append(" | realName='").append(realName).append('\'');
			sb.append(" | friend=").append(friend);
			sb.append(" | family=").append(family);
			sb.append(" | pathAlias='").append(pathAlias).append('\'');
			sb.append(" | location='").append(location).append('\'');
			sb.append(" | photosUploaded=").append(photosUploaded);
			sb.append('}');
			return sb.toString();
		}
	}
}
