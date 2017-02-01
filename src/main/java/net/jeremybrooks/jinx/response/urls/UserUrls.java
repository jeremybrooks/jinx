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
