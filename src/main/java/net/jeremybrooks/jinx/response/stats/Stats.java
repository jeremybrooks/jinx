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
import net.jeremybrooks.jinx.response.Response;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jeremy Brooks
 */
public class Stats extends Response {
  private static final long serialVersionUID = -1445993126049287486L;
  private _Stats stats;

  /**
   * @return number of views for the collection, photo, photoset, or photostream.
   */
  public Integer getViews() {
    return stats == null ? null : stats.views;
  }

  /**
   * @return number of comments for the photo or photoset.
   */
  public Integer getComments() {
    return stats == null ? null : stats.comments;
  }

  /**
   * @return number of favorites for the photo.
   */
  public Integer getFavorites() {
    return stats == null ? null : stats.favorites;
  }

  /**
   * Returned as part of the Stats inside a Photo object.
   *
   * @return total favorites for the photo.
   */
  public Integer getTotalViews() { return stats == null ? null : stats.totalViews;}

  /**
   * Returned as part of the Stats inside a photo object.
   *
   * @return total comments for the photo.
   */
  public Integer getTotalComments() {return stats == null ? null : stats.totalComments;}

  /**
   * Returned as part of the Stats for a photo object.
   *
   * @return total favorites for the photo.
   */
  public Integer getTotalFavorites() {return stats == null ? null : stats.totalFavorites;}

  /**
   * @return list of CSV files.
   */
  public List<CSV> getCsvFiles() {
    return stats == null ? null : stats.csvfiles == null ? null : stats.csvfiles.csvList;
  }

  private class _Stats implements Serializable {
    private static final long serialVersionUID = -8877659244590488305L;
    private Integer views;
    private Integer comments;
    private Integer favorites;
    @SerializedName("total_views")
    private Integer totalViews;
    @SerializedName("total_comments")
    private Integer totalComments;
    @SerializedName("total_favorites")
    private Integer totalFavorites;
    private _Csvfiles csvfiles;

    private class _Csvfiles implements Serializable {
      private static final long serialVersionUID = -1848477047710870496L;
      @SerializedName("csv")
      private List<CSV> csvList;
    }
  }

  @Override
  public String toString() {
    return "net.jeremybrooks.jinx.response.stats.Stats{" +
        "views=" + getViews() +
        ", comments=" + getComments() +
        ", favorites=" + getFavorites() +
        ", csvFiles=" + getCsvFiles() +
        '}';
  }
}
