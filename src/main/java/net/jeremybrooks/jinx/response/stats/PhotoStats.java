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
