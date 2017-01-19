package net.jeremybrooks.jinx.response.tags;

import com.google.gson.annotations.SerializedName;
import net.jeremybrooks.jinx.response.Response;
import net.jeremybrooks.jinx.response.photos.Tag;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jeremy Brooks
 */
public class Tags extends Response {
  private static final long serialVersionUID = 2581978509099588056L;
  private _Tags tags;

  public Integer getTotal() { return tags == null ? null : tags.total; }
  public List<Tag> getTagList() { return tags == null ? null : tags.tagList; }

  private class _Tags implements Serializable {
    private static final long serialVersionUID = 2577194200262588504L;
    private Integer total;
    @SerializedName("tag")
    private List<Tag> tagList;
  }
}
