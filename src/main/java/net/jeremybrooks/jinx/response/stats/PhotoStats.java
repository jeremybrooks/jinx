package net.jeremybrooks.jinx.response.stats;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Encapsulates the stats that are included with photos when calling
 * flickr.stats.getPopularPhotos.
 *
 * @author Jeremy Brooks
 */
public class PhotoStats implements Serializable {
  private static final long serialVersionUID = -2712106931342770445L;
  private Integer views;
  private Integer comments;
  private Integer favorites;
  @SerializedName("total_views")
  private Integer totalViews;
  @SerializedName("total_comments")
  private Integer totalComments;
  @SerializedName("total_favorites")
  private Integer totalFavorites;


  public Integer getViews() {
    return views;
  }

  public Integer getComments() {
    return comments;
  }

  public Integer getFavorites() {
    return favorites;
  }

  public Integer getTotalViews() {
    return totalViews;
  }

  public Integer getTotalComments() {
    return totalComments;
  }

  public Integer getTotalFavorites() {
    return totalFavorites;
  }

  @Override
  public String toString() {
    return "net.jeremybrooks.jinx.response.stats.PhotoStats{" +
        "views=" + views +
        ", comments=" + comments +
        ", favorites=" + favorites +
        ", totalViews=" + totalViews +
        ", totalComments=" + totalComments +
        ", totalFavorites=" + totalFavorites +
        '}';
  }
}
