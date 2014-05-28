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
