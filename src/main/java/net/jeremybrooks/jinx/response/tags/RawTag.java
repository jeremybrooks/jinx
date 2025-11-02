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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jeremy Brooks
 */
public class RawTag implements Serializable {
  private static final long serialVersionUID = -8383727066021602923L;
  @SerializedName("clean")
  private String cleanTag;
  @SerializedName("raw")
  private List<_Raw> rawList;

  public String getCleanTag() {
    return cleanTag;
  }

  public List<String> getRawTags() {
    List<String> list = new ArrayList<>();
    for (_Raw raw : rawList) {
      list.add(raw._content);
    }
    return list;
  }

  private class _Raw implements Serializable {
    private static final long serialVersionUID = -6962313484447782727L;
    private String _content;
  }

  @Override
  public String toString() {
    return "net.jeremybrooks.jinx.response.tags.RawTag{" +
        "cleanTag='" + cleanTag + '\'' +
        ", rawTags=" + getRawTags() +
        '}';
  }
}
