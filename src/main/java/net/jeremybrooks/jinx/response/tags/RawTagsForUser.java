package net.jeremybrooks.jinx.response.tags;

import com.google.gson.annotations.SerializedName;
import net.jeremybrooks.jinx.response.Response;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jeremy Brooks
 */
public class RawTagsForUser extends Response {
  private static final long serialVersionUID = -2260013691769712937L;
  private _Who who;


  public String getUserId() {
    return who == null ? null : who.id;
  }

  public List<RawTag> getRawTagList() {
    return who == null ? null : who.getRawTagList();
  }

  private class _Who implements Serializable {
    private static final long serialVersionUID = -1479674865641641720L;
    private String id;
    private _Tags tags;

    private List<RawTag> getRawTagList() {
      return tags == null ? null : tags.tagList;
    }
  }

  private class _Tags implements Serializable {
    private static final long serialVersionUID = -5525685363800730864L;
    @SerializedName("tag")
    private List<RawTag> tagList;
  }

  @Override
  public String toString() {
    return "net.jeremybrooks.jinx.response.tags.RawTagsForUser{" +
        "userId='" + getUserId() + '\'' +
        ", rawTags=" + getRawTagList() +
        '}';
  }
}
