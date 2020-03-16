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

import net.jeremybrooks.jinx.response.Response;

import java.io.Serializable;
import java.util.List;

/**
 * Represents data returned from the Flickr reflection API, specifically from calling
 * {@link net.jeremybrooks.jinx.api.ReflectionApi#getMethodInfo(String)}.
 *
 * @author Jeremy Brooks
 */
public class MethodInfo extends Response {
  private static final long serialVersionUID = 6353927417846805740L;
  private Method method;
  private _Arguments arguments;
  private _Errors errors;
  public Method getMethod() {return method;}
  public List<Argument> getArguments() { return arguments == null ? null : arguments.argument; }
  public List<Error> getErrors() { return errors == null ? null : errors.error; }

  private class _Arguments implements Serializable {
    private static final long serialVersionUID = -8455799530846184936L;
    private List<Argument> argument;
  }

  private class _Errors implements Serializable {
    private static final long serialVersionUID = -145141055689912292L;
    private List<Error> error;
  }

  @Override
  public String toString() {
    return "net.jeremybrooks.jinx.response.reflection.MethodInfo{" +
        "method=" + method +
        ", arguments=" + getArguments() +
        ", errors=" + getErrors() +
        '}';
  }
}
