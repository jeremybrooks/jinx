package net.jeremybrooks.jinx.response.push;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author Jeremy Brooks
 */
public class Topic implements Serializable {
  private static final long serialVersionUID = 5023752769353675119L;
  private String name;
  @SerializedName("display_name")
  private String displayName;


  public String getName() {
    return name;
  }

  public String getDisplayName() {
    return displayName;
  }

  @Override
  public String toString() {
    return "net.jeremybrooks.jinx.response.push.Topic{" +
        "name='" + name + '\'' +
        ", displayName='" + displayName + '\'' +
        '}';
  }
}
