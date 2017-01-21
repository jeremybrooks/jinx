package net.jeremybrooks.jinx.response.stats;

import java.io.Serializable;

/**
 * @author Jeremy Brooks
 */
public class CSV implements Serializable {
  private static final long serialVersionUID = 1889373102051315075L;
  private String href;
  private String type;
  private String date;

  public String getHref() {
    return href;
  }

  public String getType() {
    return type;
  }

  public String getDate() {
    return date;
  }
}
