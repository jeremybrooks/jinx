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
