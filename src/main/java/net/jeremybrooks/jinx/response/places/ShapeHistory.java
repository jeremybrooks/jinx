/*
 * Jinx is Copyright 2010-2025 by Jeremy Brooks and Contributors
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
import net.jeremybrooks.jinx.response.Response;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jeremy Brooks
 */
public class ShapeHistory extends Response {
  private static final long serialVersionUID = -259016134452176084L;
  private _Shapes shapes;

  public Integer getTotal() {
    return shapes == null ? null : shapes.total;
  }

  public String getWoeId() {
    return shapes == null ? null : shapes.woeId;
  }

  public String getPlaceId() {
    return shapes == null ? null : shapes.placeId;
  }

  public String getPlaceType() {
    return shapes == null ? null : shapes.placeType;
  }

  public Integer getPlaceTypeId() {
    return shapes == null ? null : shapes.placeTypeId;
  }

  public List<Shape> getShapeList() {
    return shapes == null ? null : shapes.shapeList;
  }

  private class _Shapes implements Serializable {
    private static final long serialVersionUID = -6781504735534809972L;
    private Integer total;
    @SerializedName("woe_id")
    private String woeId;
    @SerializedName("place_id")
    private String placeId;
    @SerializedName("place_type")
    private String placeType;
    @SerializedName("place_type_id")
    private Integer placeTypeId;
    @SerializedName("shape")
    private List<Shape> shapeList;
  }

  @Override
  public String toString() {
    return "net.jeremybrooks.jinx.response.places.ShapeHistory.Shapes{" +
        "total=" + getTotal() +
        ", woeId='" + getWoeId() + '\'' +
        ", placeId='" + getPlaceId() + '\'' +
        ", placeType='" + getPlaceType() + '\'' +
        ", placeTypeId=" + getPlaceTypeId() +
        ", shapeList=" + getShapeList() +
        '}';
  }
}
