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
import net.jeremybrooks.jinx.response.Response;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jeremy Brooks
 */
public class PlaceTypes extends Response {
  private static final long serialVersionUID = 1971601098472609751L;
  @SerializedName("place_types")
  private _PlaceTypes placeTypes;

  public List<PlaceType> getPlaceTypes() {
    return this.placeTypes == null ? null : this.placeTypes.placeTypeList;
  }

  private class _PlaceTypes implements Serializable {
    private static final long serialVersionUID = 3450557415256012576L;
    @SerializedName("place_type")
    private List<PlaceType> placeTypeList;
  }

  public class PlaceType implements Serializable {
    private static final long serialVersionUID = -5723824425418000168L;
    @SerializedName("id")
    private Integer placeTypeId;
    @SerializedName("_content")
    private String typeName;

    public Integer getPlaceTypeId() {
      return placeTypeId;
    }

    public String getTypeName() {
      return typeName;
    }

    @Override
    public String toString() {
      return "net.jeremybrooks.jinx.response.places.PlaceTypes.PlaceType{" +
          "placeTypeId=" + placeTypeId +
          ", typeName='" + typeName + '\'' +
          '}';
    }
  }
}
