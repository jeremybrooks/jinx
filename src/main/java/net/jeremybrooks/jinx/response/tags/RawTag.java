package net.jeremybrooks.jinx.response.tags;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jeremy Brooks
 */
public class RawTag implements Serializable {
  private static final long serialVersionUID = -8383727066021602923L;
  @SerializedName("clean")
  private String cleanTag;
  @SerializedName("raw")
  private List<_Raw> rawList;

  public String getCleanTag() {
    return cleanTag;
  }

  public List<String> getRawTags() {
    List<String> list = new ArrayList<>();
    for (_Raw raw : rawList) {
      list.add(raw._content);
    }
    return list;
  }

  private class _Raw implements Serializable {
    private static final long serialVersionUID = -6962313484447782727L;
    private String _content;
  }

  @Override
  public String toString() {
    return "net.jeremybrooks.jinx.response.tags.RawTag{" +
        "cleanTag='" + cleanTag + '\'' +
        ", rawTags=" + getRawTags() +
        '}';
  }
}
