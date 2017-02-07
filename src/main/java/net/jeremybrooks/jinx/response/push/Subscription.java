package net.jeremybrooks.jinx.response.push;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author Jeremy Brooks
 */
public class Subscription implements Serializable {
  private static final long serialVersionUID = 7910161546789424689L;
  private String topic;
  private String callback;
  private Integer pending;
  @SerializedName("date_create")
  private Long dateCreate;
  @SerializedName("lease_seconds")
  private Integer leaseSeconds;
  private Long expiry;
  @SerializedName("verify_attempts")
  private Integer verifyAttempts;


  public String getTopic() {
    return topic;
  }

  public String getCallback() {
    return callback;
  }

  public Boolean getPending() {
    return pending != 0;
  }

  public Long getDateCreate() {
    return dateCreate;
  }

  public Integer getLeaseSeconds() {
    return leaseSeconds;
  }

  public Long getExpiry() {
    return expiry;
  }

  public Integer getVerifyAttempts() {
    return verifyAttempts;
  }

  @Override
  public String toString() {
    return "net.jeremybrooks.jinx.response.push.Subscription{" +
        "topic='" + topic + '\'' +
        ", callback='" + callback + '\'' +
        ", pending=" + pending +
        ", dateCreate=" + dateCreate +
        ", leaseSeconds=" + leaseSeconds +
        ", expiry=" + expiry +
        ", verifyAttempts=" + verifyAttempts +
        '}';
  }
}
