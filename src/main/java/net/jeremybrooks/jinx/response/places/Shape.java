/*
 * Jinx is Copyright 2010-2023 by Jeremy Brooks and Contributors
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

package net.jeremybrooks.jinx.response.places;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jeremy Brooks
 */
public class Shape implements Serializable {
  private static final long serialVersionUID = 7988468025481065037L;
  private Long created;
  private Float alpha;
  @SerializedName("count_points")
  private Integer countPoints;
  @SerializedName("count_edges")
  private Integer countEdges;
  @SerializedName("has_donuthole")
  private Integer hasDonuthole;
  @SerializedName("is_donuthole")
  private Integer isDonuthole;
  private _Polylines polylines;
  private _Urls urls;

  public Long getCreated() {
    return created;
  }

  public Float getAlpha() {
    return alpha;
  }

  public Integer getCountPoints() {
    return countPoints;
  }

  public Integer getCountEdges() {
    return countEdges;
  }

  public Boolean isHasDonuthole() {
    return hasDonuthole == 1;
  }

  public Boolean isDonuthole() {
    return isDonuthole == 1;
  }

  public String getShapefileUrl() {
    return this.urls == null ? null : urls.getShapefileUrl();
  }

  public List<Polyline> getPolylines() {
    return polylines.polyline;
  }


  private class _Polylines implements Serializable {
    private static final long serialVersionUID = -7660816301023392300L;
    private List<Polyline> polyline;

    @Override
    public String toString() {
      return "_Polylines{" +
          "polyline=" + polyline +
          '}';
    }
  }

  public class Polyline implements Serializable {
    private static final long serialVersionUID = 5451915709105068994L;
    @SerializedName("_content")
    private String content;

    public String getContent() {
      return content;
    }

    @Override
    public String toString() {
      return "_Polyline{" +
          "content='" + content + '\'' +
          '}';
    }
  }

  private class _Urls implements Serializable {
    private static final long serialVersionUID = -3185675538734551898L;
    private _Shapefile shapefile;

    private String getShapefileUrl() {
      return shapefile == null ? null : shapefile._content;
    }

    @Override
    public String toString() {
      return "_Urls{" +
          "shapefile=" + shapefile +
          '}';
    }
  }

  private class _Shapefile implements Serializable {
    private static final long serialVersionUID = -435215436901995832L;
    private String _content;

    @Override
    public String toString() {
      return "_Shapefile{" +
          "_content='" + _content + '\'' +
          '}';
    }
  }

  @Override
  public String toString() {
    return "net.jeremybrooks.jinx.response.places.Shape{" +
        "created=" + created +
        ", alpha=" + alpha +
        ", countPoints=" + countPoints +
        ", countEdges=" + countEdges +
        ", hasDonuthole=" + hasDonuthole +
        ", isDonuthole=" + isDonuthole +
        ", polylines=" + polylines +
        ", urls=" + urls +
        '}';
  }
}
