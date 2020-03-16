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

package net.jeremybrooks.jinx.response.urls;

import net.jeremybrooks.jinx.response.Response;

import java.io.Serializable;

/**
 * @author Jeremy Brooks
 */
public class GroupUrls extends Response {
  private static final long serialVersionUID = 6489445780324370573L;
  private _Group group;

  /**
   * This parameter is set by the methods {@link net.jeremybrooks.jinx.api.UrlsApi#lookupGroup(String)} and
   * {@link net.jeremybrooks.jinx.api.UrlsApi#getGroup(String)}
   *
   * @return the group id, AKA NSID.
   */
  public String getGroupId() {
    if (group == null ) {
      return null;
    } else {
      return group.id == null ? group.nsid : group.id;
    }
  }

  /**
   * This parameter is set by the method {@link net.jeremybrooks.jinx.api.UrlsApi#getGroup(String)}
   *
   * @return group url.
   */
  public String getUrl() {
    return group == null ? null : group.url;
  }

  /**
   * This parameter is set by the method {@link net.jeremybrooks.jinx.api.UrlsApi#lookupGroup(String)}
   *
   * @return group name.
   */
  public String getGroupName() {
    return group == null ? null : group.getGroupName();
  }

  private class _Group implements Serializable {
    private static final long serialVersionUID = -7602859901866419043L;
    private String nsid;
    private String url;
    private String id;
    private _Groupname groupname;

    private String getGroupName() {
      return groupname == null ? null : groupname._content;
    }
  }

  private class _Groupname implements Serializable {
    private static final long serialVersionUID = -3529943154246503790L;
    private String _content;
  }

  @Override
  public String toString() {
    return "net.jeremybrooks.jinx.response.urls.GroupUrls{" +
        "groupId=" + getGroupId() +
        "url=" + getUrl() +
        "groupName=" + getGroupName() +
        '}';
  }
}
