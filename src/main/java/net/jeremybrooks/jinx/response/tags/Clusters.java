/*
 * Jinx is Copyright 2010-2020 by Jeremy Brooks and Contributors
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
