package net.jeremybrooks.jinx.response.tags;

import com.google.gson.annotations.SerializedName;
import net.jeremybrooks.jinx.response.photos.Tag;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jeremy Brooks
 */
public class Cluster implements Serializable {
  private static final long serialVersionUID = -5069792369625996467L;
  private Integer total;
  @SerializedName("tag")
  private List<Tag> tagList;

  public Integer getTotal() { return total; }

  public List<Tag> getTagList() { return tagList; }

  @Override
  public String toString() {
    return "net.jeremybrooks.jinx.response.tags.Cluster{" +
        "total=" + total +
        ", tagList=" + tagList +
        '}';
  }
}
