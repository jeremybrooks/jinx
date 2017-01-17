package net.jeremybrooks.jinx.response.places;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

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
  private Shape shape;
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

  public Shape getShape() {
    return shape;
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
}
