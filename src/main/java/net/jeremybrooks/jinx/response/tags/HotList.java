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

package net.jeremybrooks.jinx.response.tags;

import com.google.gson.annotations.SerializedName;
import net.jeremybrooks.jinx.response.Response;
import net.jeremybrooks.jinx.response.photos.Tag;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jeremy Brooks
 */
public class HotList extends Response {
  private static final long serialVersionUID = -7273220486247137861L;
  private _Hottags hottags;

  public String getPeriod() { return hottags == null ? null : hottags.period; }
  public Integer getCount() { return hottags == null ? null : hottags.count; }
  public List<Tag> getTagList() { return hottags == null ? null : hottags.tagList; }

  private class _Hottags implements Serializable {
    private static final long serialVersionUID = -1233872999023010296L;
    private String period;
    private Integer count;
    @SerializedName("tag")
    private List<Tag> tagList;
  }

  @Override
  public String toString() {
    return "net.jeremybrooks.jinx.response.tags.HotList{" +
        "period='" + getPeriod() + '\'' +
        ", count=" + getCount() +
        ", tagList=" + getTagList() +
        '}';
  }
}
