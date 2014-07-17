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

package net.jeremybrooks.jinx.response.commons;

import com.google.gson.annotations.SerializedName;
import net.jeremybrooks.jinx.response.Response;
import net.jeremybrooks.jinx.response.photos.PhotoInfo;

import java.io.Serializable;
import java.util.List;

/**
 * Represents data returned from <a href="https://www.flickr.com/services/api/flickr.commons.getInstitutions.html">flickr.commons.getInstitutions</a>
 *
 * @author Jeremy Brooks
 */
public class Institutions extends Response {

	private static final long serialVersionUID = 2536848647563859996L;
	private _Institutions institutions;

	public List<Institution> getInstitutionList() {
		return institutions == null ? null : institutions.institutionList;
	}


	private class _Institutions implements Serializable {
		private static final long serialVersionUID = -5729230786906878058L;
		@SerializedName("institution")
		private List<Institution> institutionList;
	}

	public class Institution implements Serializable {
		private static final long serialVersionUID = 5918710496801933243L;
        @SerializedName("nsid")
		private String institutionId;
		@SerializedName("date_launch")
		private String dateLaunch;
		private _Name name;
		private _Urls urls;


		public String getInstitutionId() {
			return institutionId;
		}
		public String getDateLaunch() {
			return dateLaunch;
		}
		public String getName() {
			return name == null ? null : name._content;
		}
		public List<PhotoInfo.Url> getUrlList() {
			return urls == null ? null : urls.urlList;
		}

		private class _Name implements Serializable {
			private static final long serialVersionUID = 4415446808172702635L;
			private String _content;
		}
		private class _Urls implements Serializable {
			private static final long serialVersionUID = -3681081818589443047L;
			@SerializedName("url")
			private List<PhotoInfo.Url> urlList;
		}


		@Override
		public String toString() {
			final StringBuilder sb = new StringBuilder();
			sb.append("net.jeremybrooks.jinx.response.commons.Institutions.Institution");
			sb.append("{institutionId='").append(institutionId).append('\'');
			sb.append(" | dateLaunch='").append(dateLaunch).append('\'');
			sb.append(" | name=").append(name);
			sb.append(" | urlList=").append(getUrlList() == null ? "null" : getUrlList().size() + " elements");
			sb.append('}');
			return sb.toString();
		}
	}
}
