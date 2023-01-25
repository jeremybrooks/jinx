/*
 * Jinx is Copyright 2010-2023 by Jeremy Brooks and Contributors
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

package net.jeremybrooks.jinx.response.push;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author Jeremy Brooks
 */
public class Topic implements Serializable {
  private static final long serialVersionUID = 5023752769353675119L;
  private String name;
  @SerializedName("display_name")
  private String displayName;


  public String getName() {
    return name;
  }

  public String getDisplayName() {
    return displayName;
  }

  @Override
  public String toString() {
    return "net.jeremybrooks.jinx.response.push.Topic{" +
        "name='" + name + '\'' +
        ", displayName='" + displayName + '\'' +
        '}';
  }
}
