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

package net.jeremybrooks.jinx.response.tags;

import com.google.gson.annotations.SerializedName;
import net.jeremybrooks.jinx.response.Response;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jeremy Brooks
 */
public class RawTagsForUser extends Response {
  private static final long serialVersionUID = -2260013691769712937L;
  private _Who who;


  public String getUserId() {
    return who == null ? null : who.id;
  }

  public List<RawTag> getRawTagList() {
    return who == null ? null : who.getRawTagList();
  }

  private class _Who implements Serializable {
    private static final long serialVersionUID = -1479674865641641720L;
    private String id;
    private _Tags tags;

    private List<RawTag> getRawTagList() {
      return tags == null ? null : tags.tagList;
    }
  }

  private class _Tags implements Serializable {
    private static final long serialVersionUID = -5525685363800730864L;
    @SerializedName("tag")
    private List<RawTag> tagList;
  }

  @Override
  public String toString() {
    return "net.jeremybrooks.jinx.response.tags.RawTagsForUser{" +
        "userId='" + getUserId() + '\'' +
        ", rawTags=" + getRawTagList() +
        '}';
  }
}
