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

package net.jeremybrooks.jinx.response.reflection;

import com.google.gson.annotations.SerializedName;
import net.jeremybrooks.jinx.JinxUtils;

import java.io.Serializable;

/**
 * @author Jeremy Brooks
 */
public class Argument implements Serializable {
  private static final long serialVersionUID = -3146529687355192029L;
  private String name;
  private Integer optional; // return as Boolean
  @SerializedName("_content")
  private String content;

  public String getName() { return name; }

  public Boolean isOptional() { return JinxUtils.flickrBooleanToBoolean(optional); }

  public String getContent() { return content; }

  @Override
  public String toString() {
    return "net.jeremybrooks.jinx.response.reflection.Argument{" +
        "name='" + name + '\'' +
        ", optional=" + isOptional() +
        ", content='" + content + '\'' +
        '}';
  }
}
