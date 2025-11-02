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
public class Tags extends Response {
  private static final long serialVersionUID = 2581978509099588056L;
  private _Tags tags;

  public Integer getTotal() { return tags == null ? null : tags.total; }
  public List<Tag> getTagList() { return tags == null ? null : tags.tagList; }

  private class _Tags implements Serializable {
    private static final long serialVersionUID = 2577194200262588504L;
    private Integer total;
    @SerializedName("tag")
    private List<Tag> tagList;
  }
}
