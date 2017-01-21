package net.jeremybrooks.jinx.response.stats;

import java.io.Serializable;

/**
 * @author Jeremy Brooks
 */
public class Domain implements Serializable {
  private static final long serialVersionUID = 417723339251274562L;
  private String name;
  private Integer views;

  public String getName() {
    return this.name;
  }

  public Integer getViews() {
    return this.views;
  }

  @Override
  public String toString() {
    return "net.jeremybrooks.jinx.response.stats.Domain{" +
        "name='" + name + '\'' +
        ", views=" + views +
        '}';
  }
}
