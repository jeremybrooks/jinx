package net.jeremybrooks.jinx.response.push;

import com.google.gson.annotations.SerializedName;
import net.jeremybrooks.jinx.response.Response;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jeremy Brooks
 */
public class Topics extends Response {
  private static final long serialVersionUID = -2509756704055116533L;
  private _Topics topics;

  public List<Topic> getTopicList() { return topics == null ? null : topics.topicList; }

  private class _Topics implements Serializable {
    private static final long serialVersionUID = 2391898605208304028L;
    @SerializedName("topic")
    private List<Topic> topicList;
  }
}
