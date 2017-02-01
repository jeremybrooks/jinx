package net.jeremybrooks.jinx.response.tags;

import com.google.gson.annotations.SerializedName;
import net.jeremybrooks.jinx.response.Response;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jeremy Brooks
 */
public class Clusters extends Response {
  private static final long serialVersionUID = 726767733841190347L;
  private _Clusters clusters;

  public String getSource() { return clusters == null ? null : clusters.source; }
  public Integer getTotal() { return clusters == null ? null : clusters.total; }

  public List<Cluster> getClusterList() { return clusters == null ? null : clusters.clusterList; }

  private class _Clusters implements Serializable {
    private static final long serialVersionUID = -1670166607335945401L;
    private String source;
    private Integer total;
    @SerializedName("cluster")
    private List<Cluster> clusterList;

  }

  @Override
  public String toString() {
    return "net.jeremybrooks.jinx.response.tags.Clusters{" +
        "source='" + getSource() + '\'' +
        ", total=" + getTotal() +
        ", clusterList=" + getClusterList() +
        '}';
  }
}
