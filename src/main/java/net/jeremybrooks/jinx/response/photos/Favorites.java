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

import com.google.gson.annotations.SerializedName;
import net.jeremybrooks.jinx.JinxUtils;
import net.jeremybrooks.jinx.response.Response;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jeremy Brooks
 */
public class Favorites extends Response {
	private static final long serialVersionUID = 1506197108392075716L;

	private _Photo photo;

	public String getPhotoId() {
		return photo == null ? null : photo.photoId;
	}

	public String getSecret() {
		return photo == null ? null : photo.secret;
	}

	public String getServer() {
		return photo == null ? null : photo.server;
	}

	public Integer getFarm() {
		return photo == null ? null : photo.farm;
	}

	public Integer getPage() {
		return photo == null ? null : photo.page;
	}

	public Integer getPages() {
		return photo == null ? null : photo.pages;
	}

	public Integer getPerPage() {
		return photo == null ? null : photo.perPage;
	}

	public Integer getTotal() {
		return photo == null ? null : photo.total;
	}

	public List<Person> getPersonList() {
		return photo == null ? null : photo.personList;
	}


	public class Person implements Serializable {
		private static final long serialVersionUID = 679457305977974427L;
		private String nsid;
		private String username;
		@SerializedName("realname")
		private String realName;
		@SerializedName("favedate")
		private String faveDate;
		@SerializedName("iconserver")
		private String iconServer;
		@SerializedName("iconfarm")
		private Integer iconFarm;
		private String contact;     // return as Boolean
		private String friend;      // return as Boolean
		private String family;      // return as Boolean

		public String getNsid() {
			return nsid;
		}

		public String getUsername() {
			return username;
		}

		public String getRealName() {
			return realName;
		}

		public String getFaveDate() {
			return faveDate;
		}

		public String getIconServer() {
			return iconServer;
		}

		public Integer getIconFarm() {
			return iconFarm;
		}

		public boolean isContact() {
			return JinxUtils.flickrBooleanToBoolean(contact);
		}

		public boolean isFriend() {
			return JinxUtils.flickrBooleanToBoolean(friend);
		}

		public boolean isFamily() {
			return JinxUtils.flickrBooleanToBoolean(family);
		}


		@Override
		public String toString() {
			final StringBuilder sb = new StringBuilder();
			sb.append("net.jeremybrooks.jinx.response.photos.Favorites.Person");
			sb.append("{nsid='").append(nsid).append('\'');
			sb.append(" | username='").append(username).append('\'');
			sb.append(" | realName='").append(realName).append('\'');
			sb.append(" | faveDate='").append(faveDate).append('\'');
			sb.append(" | iconServer='").append(iconServer).append('\'');
			sb.append(" | iconFarm=").append(iconFarm);
			sb.append(" | isContact=").append(isContact());
			sb.append(" | isFriend=").append(isFriend());
			sb.append(" | isFamily=").append(isFamily());
			sb.append('}');
			return sb.toString();
		}
	}

	private class _Photo implements Serializable {
		private static final long serialVersionUID = 3416240867161422494L;
		@SerializedName("id")
		private String photoId;
		private String secret;
		private String server;
		private Integer farm;
		private Integer page;
		private Integer pages;
		@SerializedName("perpage")
		private Integer perPage;
		private Integer total;
		@SerializedName("person")
		private List<Person> personList;


	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("net.jeremybrooks.jinx.response.photos.Favorites._Photo");
		sb.append("{photoId='").append(getPhotoId()).append('\'');
		sb.append(" | secret='").append(getSecret()).append('\'');
		sb.append(" | server='").append(getServer()).append('\'');
		sb.append(" | farm=").append(getFarm());
		sb.append(" | page=").append(getPage());
		sb.append(" | pages=").append(getPages());
		sb.append(" | perPage=").append(getPerPage());
		sb.append(" | total=").append(getTotal());
		sb.append(" | personList=").append(getPersonList() == null ? "null" : getPersonList().size());
		sb.append('}');
		return sb.toString();
	}
}
