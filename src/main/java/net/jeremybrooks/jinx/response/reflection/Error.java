package net.jeremybrooks.jinx.response.reflection;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author Jeremy Brooks
 */
public class Error implements Serializable {
  private static final long serialVersionUID = 3149639294386911931L;
  private Integer code;
  private String message;
  @SerializedName("_content")
  private String content;

  public Integer getCode() { return code; }
  public String getMessage() { return message; }
  public String getContent() { return content; }

  @Override
  public String toString() {
    return "net.jeremybrooks.jinx.response.reflection.Error{" +
        "code=" + code +
        ", message='" + message + '\'' +
        ", content='" + content + '\'' +
        '}';
  }
}
