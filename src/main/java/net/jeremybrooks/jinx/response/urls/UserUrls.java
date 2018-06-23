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

package net.jeremybrooks.jinx.response.urls;

import net.jeremybrooks.jinx.response.Response;

import java.io.Serializable;

/**
 * @author Jeremy Brooks
 */
public class UserUrls extends Response {
  private static final long serialVersionUID = 3535311359584500689L;
  private _User user;

  /**
   * This parameter is set by the methods {@link net.jeremybrooks.jinx.api.UrlsApi#getUserPhotos(String)} and
   * {@link net.jeremybrooks.jinx.api.UrlsApi#getUserProfile(String)}.
   *
   * @return the user url.
   */
  public String getUrl() { return user == null ? null : user.url; }

  /**
   * This parameter is set by the method {@link net.jeremybrooks.jinx.api.UrlsApi#lookupUser(String)},
   * {@link net.jeremybrooks.jinx.api.UrlsApi#getUserPhotos(String)} and
   * {@link net.jeremybrooks.jinx.api.UrlsApi#getUserProfile(String)}.
   *
   * @return the user id, AKD NSID.
   */
  public String getUserId() {
    if (user == null) {
      return null;
    } else {
      return user.id == null ? user.nsid : user.id;
    }
  }

  /**
   * This parameter is set by the method {@link net.jeremybrooks.jinx.api.UrlsApi#lookupUser(String)}
   *
   * @return the username.
   */
  public String getUsername() { return user == null ? null : user.getUsername(); }

  private class _User implements Serializable {
    private static final long serialVersionUID = 1872450179773700478L;
    private String nsid;
    private String url;
    private String id;
    private _Username username;
    private String getUsername() { return username == null ? null : username._content; }
  }

  private class _Username implements Serializable {
    private static final long serialVersionUID = -6469739116058454445L;
    private String _content;
  }

  @Override
  public String toString() {
    return "net.jeremybrooks.jinx.response.urls.UserUrls{" +
        "userId=" + getUserId() +
        "url=" + getUrl() +
        "username=" + getUsername() +
        '}';
  }
}
