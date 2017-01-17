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
}
