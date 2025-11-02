/*
 * Jinx is Copyright 2010-2025 by Jeremy Brooks and Contributors
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

package net.jeremybrooks.jinx.response.stats;

import com.google.gson.annotations.SerializedName;
import net.jeremybrooks.jinx.response.Response;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jeremy Brooks
 */
public class Referrers extends Response {
  private static final long serialVersionUID = 5877948390058504734L;
  @SerializedName("domain")
  private _Referrers referrers;

  public String getName() {
    return referrers == null ? null : referrers.name;
  }

  public Integer getPage() {
    return referrers == null ? null : referrers.page;
  }

  public Integer getPages() {
    return referrers == null ? null : referrers.pages;
  }

  public Integer getPerPage() {
    return referrers == null ? null : referrers.perPage;
  }

  public Integer getTotal() {
    return referrers == null ? null : referrers.total;
  }

  public List<Referrer> getReferrerList() {
    return referrers == null ? null : referrers.referrerList;
  }

  private class _Referrers implements Serializable {
    private static final long serialVersionUID = -8970633498262220394L;
    private String name;
    private Integer page;
    private Integer pages;
    @SerializedName("perpage")
    private Integer perPage;
    private Integer total;
    @SerializedName("referrer")
    private List<Referrer> referrerList;
  }

  @Override
  public String toString() {
    return "net.jeremybrooks.jinx.response.stats.Referrers{" +
        "name='" + getName() + '\'' +
        ", page=" + getPage() +
        ", pages=" + getPages() +
        ", perPage=" + getPerPage() +
        ", total=" + getTotal() +
        ", referrerList=" + getReferrerList() +
        '}';
  }
}
