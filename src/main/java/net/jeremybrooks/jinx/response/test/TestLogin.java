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

