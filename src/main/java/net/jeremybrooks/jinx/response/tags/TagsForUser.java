package net.jeremybrooks.jinx.response.tags;

import com.google.gson.annotations.SerializedName;
import net.jeremybrooks.jinx.response.Response;
import net.jeremybrooks.jinx.response.photos.Tag;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jeremy Brooks
 */
public class TagsForUser extends Response {
  private static final long serialVersionUID = -1306708135816750039L;
  private _Who who;

  public String getUserId() { return who == null ? null : who.id; }
  public List<Tag> getTagList() { return who == null ? null : who.getTagList(); }

  private class _Who implements Serializable {
    private static final long serialVersionUID = 761607652829295444L;
    private String id;
    private _Tags tags;
    private List<Tag> getTagList() { return tags == null ? null : tags.tagList; }

  }

  private class _Tags implements Serializable {
    private static final long serialVersionUID = 5700546996165453771L;
    @SerializedName("tag")
    private List<Tag> tagList;
  }

  @Override
  public String toString() {
    return "net.jeremybrooks.jinx.response.tags.TagsForUser{" +
        "userId='" + getUserId() + '\'' +
        ", tags=" + getTagList() +
        '}';
  }
}
