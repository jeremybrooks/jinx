/*
 * Jinx is Copyright 2010-2018 by Jeremy Brooks and Contributors
 *
 * Jinx is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jinx is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Jinx.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.jeremybrooks.jinx.response.push;

import com.google.gson.annotations.SerializedName;
import net.jeremybrooks.jinx.JinxUtils;

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
    return JinxUtils.flickrBooleanToBoolean(pending);
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
