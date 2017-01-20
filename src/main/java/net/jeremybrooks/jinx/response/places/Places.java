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
    @SerializedName("date_start")
    private Long dateStart;
    @SerializedName("date_stop")
    private Long dateStop;
    @SerializedName("place_type")
    private String placeType;
    private Integer pages;
    private Integer page;
    @SerializedName("bbox")
    private String boundingBox;
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

  public Long getDateStart() {
    return places == null ? null : places.dateStart;
  }

  public Long getDateStop() {
    return places == null ? null : places.dateStop;
  }

  public String getPlaceType() {
    return places == null ? null : places.placeType;
  }

  public Integer getPages() {
    return places == null ? null : places.pages;
  }

  public Integer getPage() {
    return places == null ? null : places.page;
  }

  public String getBoundingBox() {
    return places == null ? null : places.boundingBox;
  }

  @Override
  public String toString() {
    return "net.jeremybrooks.jinx.response.places.Places._Places{" +
        "places=[" + getPlaces().size() + ']' +
        ", query='" + getQuery() + '\'' +
        ", total=" + getTotal() +
        ", latitude=" + getLatitude() +
        ", longitude=" + getLongitude() +
        ", accuracy=" + getAccuracy() +
        ", dateStart=" + getDateStart() +
        ", dateStop=" + getDateStop() +
        ", placeType='" + getPlaceType() + '\'' +
        ", pages=" + getPages() +
        ", page=" + getPage() +
        ", boundingBox='" + getBoundingBox() + '\'' +
        '}';
  }
}
