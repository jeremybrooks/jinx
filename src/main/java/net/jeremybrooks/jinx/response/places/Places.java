package net.jeremybrooks.jinx.response.places;

import com.google.gson.annotations.SerializedName;
import net.jeremybrooks.jinx.response.Response;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jeremy Brooks
 */
public class Places extends Response {
  private static final long serialVersionUID = -3122953061632103445L;
  @SerializedName("places")
  private _Places places;


  private class _Places implements Serializable {
    private static final long serialVersionUID = -488392634418571329L;
    @SerializedName("place")
    private List<Place> places;
    private String query;
    private Integer total;
    private Float latitude;
    private Float longitude;
    private Integer accuracy;
  }

  public List<Place> getPlaces() {
    return places == null ? null : places.places;
  }

  public String getQuery() {
    return places == null ? null : places.query;
  }

  public Integer getTotal() {
    return places == null ? null : places.total;
  }

  public Float getLatitude() {
    return places == null ? null : places.latitude;
  }

  public Float getLongitude() {
    return places == null ? null : places.longitude;
  }

  public Integer getAccuracy() {
    return places == null ? null : places.accuracy;
  }
}
