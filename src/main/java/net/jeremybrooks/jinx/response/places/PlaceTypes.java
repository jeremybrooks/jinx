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
