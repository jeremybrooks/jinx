package net.jeremybrooks.jinx.response.stats;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author Jeremy Brooks
 */
public class Referrer implements Serializable {
  private static final long serialVersionUID = 287354812907645565L;
  private String url;
  private Integer views;
  @SerializedName("searchterm")
  private String searchTerm;


  public String getUrl() {
    return url;
  }

  public Integer getViews() {
    return views;
  }

  public String getSearchTerm() {
    return searchTerm;
  }

  @Override
  public String toString() {
    return "net.jeremybrooks.jinx.response.stats.Referrer{" +
        "url='" + url + '\'' +
        ", views=" + views +
        ", searchTerm='" + searchTerm + '\'' +
        '}';
  }
}
