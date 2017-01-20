package net.jeremybrooks.jinx.response.reflection;

import com.google.gson.annotations.SerializedName;
import net.jeremybrooks.jinx.JinxUtils;

import java.io.Serializable;

/**
 * @author Jeremy Brooks
 */
public class Argument implements Serializable {
  private static final long serialVersionUID = -3146529687355192029L;
  private String name;
  private Integer optional; // return as Boolean
  @SerializedName("_content")
  private String content;

  public String getName() { return name; }

  public Boolean isOptional() { return JinxUtils.flickrBooleanToBoolean(optional); }

  public String getContent() { return content; }

  @Override
  public String toString() {
    return "net.jeremybrooks.jinx.response.reflection.Argument{" +
        "name='" + name + '\'' +
        ", optional=" + isOptional() +
        ", content='" + content + '\'' +
        '}';
  }
}
