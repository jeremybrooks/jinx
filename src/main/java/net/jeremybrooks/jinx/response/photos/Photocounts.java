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

package net.jeremybrooks.jinx.response.photos;

import com.google.gson.annotations.SerializedName;
import net.jeremybrooks.jinx.response.Response;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jeremy Brooks
 */
public class Photocounts extends Response {
	private static final long serialVersionUID = 4040965951675682956L;

	@SerializedName("photocounts")
	private _Photocounts photocounts;

	public List<Photocount> getPhotocountList() {
		return photocounts == null ? null : photocounts.photocount;
	}


	private class _Photocounts implements Serializable {
		private static final long serialVersionUID = -8055262696697874459L;
		private List<Photocount> photocount;
	}

	public class Photocount implements Serializable {
		private static final long serialVersionUID = 642648439801824176L;
		private Integer count;
		@SerializedName("fromdate")
		private String fromDate;
		@SerializedName("todate")
		private String toDate;

		public Integer getCount() {
			return count;
		}

		public String getFromDate() {
			return fromDate;
		}

		public String getToDate() {
			return toDate;
		}
	}
}
