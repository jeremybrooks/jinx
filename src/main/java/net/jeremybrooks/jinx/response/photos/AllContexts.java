package net.jeremybrooks.jinx.response.photos;

import com.google.gson.annotations.SerializedName;
import net.jeremybrooks.jinx.response.Response;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jeremy Brooks
 */
public class AllContexts extends Response {
	private static final long serialVersionUID = 6289347803471707851L;

	@SerializedName("set")
	private List<Set> setList;

	@SerializedName("pool")
	private List<Pool> poolList;

	public List<Set> getSetList() { return setList; }
	public List<Pool> getPoolList() { return poolList; }


	public class Set implements Serializable {
		private static final long serialVersionUID = -4783797055775417857L;
		private String title;
		@SerializedName("id")
		private String photosetId;
		private String primary;
		private String secret;
		private String server;
				private Integer farm;
		@SerializedName("view_count")
		private Integer viewCount;
		@SerializedName("comment_count")
		private Integer commentCount;
		@SerializedName("count_photo")
		private Integer countPhoto;
		@SerializedName("count_video")
		private Integer countVideo;

		public String getTitle() {
			return title;
		}

		public String getPhotosetId() {
			return photosetId;
		}

		public String getPrimary() {
			return primary;
		}

		public String getSecret() {
			return secret;
		}

		public String getServer() {
			return server;
		}

		public Integer getFarm() {
			return farm;
		}

		public Integer getViewCount() {
			return viewCount;
		}

		public Integer getCommentCount() {
			return commentCount;
		}

		public Integer getCountPhoto() {
			return countPhoto;
		}

		public Integer getCountVideo() {
			return countVideo;
		}
	}

	public class Pool implements Serializable {
		private static final long serialVersionUID = 8209986304662477656L;
		private String title;
		private String url;
		@SerializedName("id")
		private String poolId;
		@SerializedName("iconserver")
		private String iconServer;
		@SerializedName("iconfarm")
		private Integer iconFarm;
		private Integer members;
		@SerializedName("pool_count")
		private Integer poolCount;

		public String getTitle() {
			return title;
		}

		public String getUrl() {
			return url;
		}

		public String getPoolId() {
			return poolId;
		}

		public String getIconServer() {
			return iconServer;
		}

		public Integer getIconFarm() {
			return iconFarm;
		}

		public Integer getMembers() {
			return members;
		}

		public Integer getPoolCount() {
			return poolCount;
		}
	}
}
