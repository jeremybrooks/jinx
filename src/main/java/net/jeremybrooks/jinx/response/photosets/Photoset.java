package net.jeremybrooks.jinx.response.photosets;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Encapsulates data about a photoset.
 *
 * This class is returned as a member of {@link net.jeremybrooks.jinx.response.photosets.PhotosetInfo} and
 * {@link net.jeremybrooks.jinx.response.photosets.PhotosetList}
 *
 * Not every field will contain data. Different API calls will return different pieces of data.
 *
 * @author Jeremy Brooks
 */
public class Photoset implements Serializable {
	private static final long serialVersionUID = -3849197843508752543L;
	@SerializedName("id")
	private String photosetId;
	private String url;
	private String owner;
	private String username;
	private String primary;
	private String secret;
	private String server;
	private Integer farm;
	private Integer photos;
	private Integer videos;
	@SerializedName("count_views")
	private Integer countViews;
	@SerializedName("count_comments")
	private Integer countComments;
	@SerializedName("count_photos")
	private Integer countPhotos;
	@SerializedName("count_videos")
	private Integer countVideos;
	private Title title;
	private Description description;

	@SerializedName("needs_interstitial")
	private Integer isNeedsInterstitial;
	@SerializedName("visibility_can_see_set")
	private Integer isVisibilityCanSeeSet;

	@SerializedName("can_comment")
	private Integer isCanComment;
	@SerializedName("date_create")
	private String dateCreate;
	@SerializedName("date_update")
	private String dateUpdate;
	@SerializedName("coverphoto_server")
	private Integer coverphotoServer;
	@SerializedName("coverphoto_farm")
	private Integer coverphotoFarm;

	@SerializedName("primary_photo_extras")
	private PrimaryPhotoExtras primaryPhotoExtras;

	/**
	 * This is only returned for photosets in the photoset list returned by the getList method.
	 * @return class containing primary photo extras data.
	 */
	public PrimaryPhotoExtras getPrimaryPhotoExtras() {
		return primaryPhotoExtras;
	}
	public String getPhotosetId() {
		return photosetId;
	}

	/**
	 * This data is only returned when a photoset is created.
	 * @return url to the newly created photoset.
	 */
	public String getUrl() {
		return url;
	}

	public String getOwner() {
		return owner;
	}

	public String getUsername() {
		return username;
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

	public Integer getPhotos() {
		return photos;
	}

	public Integer getCountViews() {
		return countViews;
	}

	public Integer getCountComments() {
		return countComments;
	}

	public Integer getCountPhotos() {
		return countPhotos;
	}

	public Integer getCountVideos() {
		return countVideos;
	}

	public String getTitle() {
		return title.getTitle();
	}

	public String getDescription() {
		return description.getDescription();
	}

	public boolean isCanComment() {
		return isCanComment != null && isCanComment == 1;
	}

	public String getDateCreate() {
		return dateCreate;
	}

	public String getDateUpdate() {
		return dateUpdate;
	}

	public Integer getCoverphotoServer() {
		return coverphotoServer;
	}

	public Integer getCoverphotoFarm() {
		return coverphotoFarm;
	}

	/**
	 * This data is only returned from the getList method.
	 *
	 * @return number of videos in the photoset.
	 */
	public Integer getVideos() {
		return videos;
	}

	/**
	 * This data is only returned from the getList method.
	 * @return value of the is_needs_interstitial data returned from Flickr.
	 */
	public boolean getIsNeedsInterstitial() {
		return isNeedsInterstitial != null && isNeedsInterstitial == 1;
	}

	/**
	 * This data is only returned from the getList method.
	 * @return value of the is_visibility_can_see_set data returned from Flickr.
	 */
	public boolean getIsVisibilityCanSeeSet() {
		return isVisibilityCanSeeSet != null && isVisibilityCanSeeSet == 1;
	}

	private class Title {
		@SerializedName("_content")
		private String title;

		String getTitle() {
			return title;
		}
	}

	private class Description {
		@SerializedName("_content")
		private String description;

		String getDescription() {
			return description;
		}
	}


	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("net.jeremybrooks.jinx.response.photosets.Photoset");
		sb.append("{photosetId='").append(photosetId).append('\'');
		sb.append(" | url='").append(url).append('\'');
		sb.append(" | owner='").append(owner).append('\'');
		sb.append(" | username='").append(username).append('\'');
		sb.append(" | primary='").append(primary).append('\'');
		sb.append(" | secret='").append(secret).append('\'');
		sb.append(" | server='").append(server).append('\'');
		sb.append(" | farm=").append(farm);
		sb.append(" | photos=").append(photos);
		sb.append(" | videos=").append(videos);
		sb.append(" | countViews=").append(countViews);
		sb.append(" | countComments=").append(countComments);
		sb.append(" | countPhotos=").append(countPhotos);
		sb.append(" | countVideos=").append(countVideos);
		sb.append(" | title=").append(title);
		sb.append(" | description=").append(description);
		sb.append(" | isNeedsInterstitial=").append(isNeedsInterstitial);
		sb.append(" | isVisibilityCanSeeSet=").append(isVisibilityCanSeeSet);
		sb.append(" | isCanComment=").append(isCanComment);
		sb.append(" | dateCreate='").append(dateCreate).append('\'');
		sb.append(" | dateUpdate='").append(dateUpdate).append('\'');
		sb.append(" | coverphotoServer=").append(coverphotoServer);
		sb.append(" | coverphotoFarm=").append(coverphotoFarm);
		sb.append(" | primaryPhotoExtras=").append(primaryPhotoExtras == null ? "null" : primaryPhotoExtras);
		sb.append('}');
		return sb.toString();
	}
}
