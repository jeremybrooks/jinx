package net.jeremybrooks.jinx.response.tags;

import com.google.gson.annotations.SerializedName;
import net.jeremybrooks.jinx.response.Response;
import net.jeremybrooks.jinx.response.photos.Tag;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jeremy Brooks
 */
public class RelatedTags extends Response {
  private static final long serialVersionUID = 5658491723712745142L;
  private _Tags tags;

  public String getSource() { return tags == null ? null : tags.source; }
  public List<Tag> getTagList() { return tags == null ? null : tags.tagList; }

  private class _Tags implements Serializable {
    private static final long serialVersionUID = -6094206514951625772L;
    private String source;
    @SerializedName("tag")
    private List<Tag> tagList;
  }

  @Override
  public String toString() {
    return "net.jeremybrooks.jinx.response.tags.RelatedTags._Tags{" +
        "source='" + getSource() + '\'' +
        ", tagList=" + getTagList() +
        '}';
  }
}
