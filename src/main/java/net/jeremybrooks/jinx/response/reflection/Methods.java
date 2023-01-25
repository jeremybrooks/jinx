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

package net.jeremybrooks.jinx.response.reflection;

import com.google.gson.annotations.SerializedName;
import net.jeremybrooks.jinx.response.Response;

import java.io.Serializable;
import java.util.List;

/**
 * Represents all available methods in the Flickr API.
 *
 * The list of Method objects will only have the name parameter set. To get additional
 * information about a methods, pass the name to {@link net.jeremybrooks.jinx.api.ReflectionApi#getMethodInfo(String)}.
 *
 * @author Jeremy Brooks
 */
public class Methods extends Response {

  private static final long serialVersionUID = 4068557208094758599L;

  private _Methods methods;

  public List<Method> getMethods() { return methods == null ? null : methods.methodList; }

  private class _Methods implements Serializable {
    private static final long serialVersionUID = 3544122628752907404L;
    @SerializedName("method")
    private List<Method> methodList;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("net.jeremybrooks.jinx.response.reflection.Methods{");
    sb.append("methods=[");
    if (getMethods() == null) {
      sb.append("null");
    } else {
      for (Method method : getMethods()) {
        sb.append('\'').append(method.getName()).append("',");
      }
      sb.deleteCharAt(sb.length() - 1);
    }
    sb.append("]}");
    return sb.toString();
  }
}
