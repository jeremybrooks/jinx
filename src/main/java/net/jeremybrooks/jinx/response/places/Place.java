package net.jeremybrooks.jinx.response.places;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Information returned about places.
 *
 * <p>Not all information is returned for every call to places. The returned data depends on which
 * call is made, and which data Flickr returns for a Place.</p>
 *
 * @author Jeremy Brooks
 */
public class Place {
  private static final long serialVersionUID = 298484144422932838L;
  @SerializedName("place_id")
  private String placeId;
  @SerializedName("woeid")
  private String woeId;
  private Float latitude;
  private Float longitude;
  @SerializedName("place_url")
  private String placeUrl;
  @SerializedName("place_type")
  private String placeType;
  @SerializedName("place_type_id")
  private Integer placeTypeId;
  private String timezone;
  private String name;
  @SerializedName("woe_name")
  private String woeName;
  @SerializedName("has_shapedata")
  private Integer hasShapeData;
  private Region region;
  private Locality locality;
  private County county;
  private Country country;
  @SerializedName("shapedata")
  private ShapeData shapeData;
  @SerializedName("_content")
  private String content;
  @SerializedName("photo_count")
  private Integer photoCount;

  public String getPlaceId() {
    return placeId;
  }

  public String getWoeId() {
    return woeId;
  }

  public Float getLatitude() {
    return latitude;
  }

  public Float getLongitude() {
    return longitude;
  }

  public String getPlaceUrl() {
    return placeUrl;
  }

  public String getPlaceType() {
    return placeType;
  }

  public Integer getPlaceTypeId() {
    return placeTypeId;
  }

  public String getTimezone() {
    return timezone;
  }

  public String getName() {
    return name;
  }

  public String getWoeName() {
    return woeName;
  }

  public Boolean isHasShapeData() {
    return hasShapeData == null ? null : hasShapeData == 1;
  }

  public String getContent() {
    return content;
  }

  public Integer getPhotoCount() {
    return photoCount;
  }

  public Region getRegion() {
    return region;
  }

  public Locality getLocality() {
    return locality;
  }

  public County getCounty() {
    return county;
  }

  public Country getCountry() {
    return country;
  }

  public ShapeData getShapeData() {
    return shapeData;
  }

  public class Locality implements Serializable {
    private static final long serialVersionUID = -119455789568528171L;
    @SerializedName("_content")
    private String content;
    @SerializedName("place_id")
    private String placeId;
    @SerializedName("woeid")
    private String woeId;
    private Float latitude;
    private Float longitude;
    @SerializedName("place_url")
    private String placeUrl;

    public String getContent() {
      return content;
    }

    public String getPlaceId() {
      return placeId;
    }

    public String getWoeId() {
      return woeId;
    }

    public Float getLatitude() {
      return latitude;
    }

    public Float getLongitude() {
      return longitude;
    }

    public String getPlaceUrl() {
      return placeUrl;
    }
  }

  public class County implements Serializable {
    private static final long serialVersionUID = -4782469316426633857L;
    @SerializedName("_content")
    private String content;
    @SerializedName("place_id")
    private String placeId;
    @SerializedName("woeid")
    private String woeId;
    private Float latitude;
    private Float longitude;
    @SerializedName("place_url")
    private String placeUrl;

    public String getContent() {
      return content;
    }

    public String getPlaceId() {
      return placeId;
    }

    public String getWoeId() {
      return woeId;
    }

    public Float getLatitude() {
      return latitude;
    }

    public Float getLongitude() {
      return longitude;
    }

    public String getPlaceUrl() {
      return placeUrl;
    }
  }

  public class Region implements Serializable {
    private static final long serialVersionUID = -1830471385777462158L;
    @SerializedName("_content")
    private String content;
    @SerializedName("place_id")
    private String placeId;
    @SerializedName("woeid")
    private String woeId;
    private Float latitude;
    private Float longitude;
    @SerializedName("place_url")
    private String placeUrl;

    public String getContent() {
      return content;
    }

    public String getPlaceId() {
      return placeId;
    }

    public String getWoeId() {
      return woeId;
    }

    public Float getLatitude() {
      return latitude;
    }

    public Float getLongitude() {
      return longitude;
    }

    public String getPlaceUrl() {
      return placeUrl;
    }
  }

  public class Country implements Serializable {
    private static final long serialVersionUID = 6015885880256190118L;
    @SerializedName("_content")
    private String content;
    @SerializedName("place_id")
    private String placeId;
    @SerializedName("woeid")
    private String woeId;
    private Float latitude;
    private Float longitude;
    @SerializedName("place_url")
    private String placeUrl;

    public String getContent() {return this.content;}

    public String getPlaceId() {
      return placeId;
    }

    public String getWoeId() {
      return woeId;
    }

    public Float getLatitude() {
      return latitude;
    }

    public Float getLongitude() {
      return longitude;
    }

    public String getPlaceUrl() {
      return placeUrl;
    }
  }

  public class ShapeData implements Serializable {
    private static final long serialVersionUID = 3363034428349359679L;
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
  }
}
