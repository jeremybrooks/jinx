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
public class Domains extends Response {
  private static final long serialVersionUID = -5793008895732548616L;
  private _Domains domains;

  public Integer getPage() {
    return domains == null ? null : domains.page;
  }

  public Integer getPerPage() {
    return domains == null ? null : domains.perPage;
  }

  public Integer getPages() {
    return domains == null ? null : domains.pages;
  }

  public Integer getTotal() {
    return domains == null ? null : domains.total;
  }

  public List<Domain> getDomainList() {
    return domains == null ? null : domains.domainList;
  }

  private class _Domains implements Serializable {
    private static final long serialVersionUID = -6252834672527831890L;
    private Integer page;
    @SerializedName("perpage")
    private Integer perPage;
    private Integer pages;
    private Integer total;
    @SerializedName("domain")
    private List<Domain> domainList;
  }

  @Override
  public String toString() {
    return "net.jeremybrooks.jinx.response.stats.Domains{" +
        "page=" + getPage() +
        ", perPage=" + getPerPage() +
        ", pages=" + getPages() +
        ", total=" + getTotal() +
        ", domainList=" + getDomainList() +
        '}';
  }

}
