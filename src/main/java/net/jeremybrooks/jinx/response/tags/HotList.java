package net.jeremybrooks.jinx.response.tags;

import com.google.gson.annotations.SerializedName;
import net.jeremybrooks.jinx.response.Response;
import net.jeremybrooks.jinx.response.photos.Tag;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jeremy Brooks
 */
public class HotList extends Response {
  private static final long serialVersionUID = -7273220486247137861L;
  private _Hottags hottags;

  public String getPeriod() { return hottags == null ? null : hottags.period; }
  public Integer getCount() { return hottags == null ? null : hottags.count; }
  public List<Tag> getTagList() { return hottags == null ? null : hottags.tagList; }

  private class _Hottags implements Serializable {
    private static final long serialVersionUID = -1233872999023010296L;
    private String period;
    private Integer count;
    @SerializedName("tag")
    private List<Tag> tagList;
  }

  @Override
  public String toString() {
    return "net.jeremybrooks.jinx.response.tags.HotList{" +
        "period='" + getPeriod() + '\'' +
        ", count=" + getCount() +
        ", tagList=" + getTagList() +
        '}';
  }
}
