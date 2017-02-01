package net.jeremybrooks.jinx.response.test;

import net.jeremybrooks.jinx.response.Response;

import java.io.Serializable;

/**
 * @author Jeremy Brooks
 */
public class TestLogin extends Response {
  private static final long serialVersionUID = -2916042767056149853L;
  private _User user;

  public String getUserId() { return user == null ? null : user.id; }
  public String getUsername() { return user == null ? null : user.getUsername(); }


  private class _User implements Serializable {
    private static final long serialVersionUID = -1777006091203596357L;
    private String id;
    private _Username username;
    private String getUsername() { return username == null ? null : username._content; }
  }
  private class _Username implements Serializable {
    private static final long serialVersionUID = -3992356742991303975L;
    private String _content;
  }
}

