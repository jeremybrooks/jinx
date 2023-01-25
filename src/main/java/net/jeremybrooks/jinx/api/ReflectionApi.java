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

package net.jeremybrooks.jinx.api;

import net.jeremybrooks.jinx.Jinx;
import net.jeremybrooks.jinx.JinxException;
import net.jeremybrooks.jinx.JinxUtils;
import net.jeremybrooks.jinx.response.reflection.MethodInfo;
import net.jeremybrooks.jinx.response.reflection.Methods;

import java.util.Map;
import java.util.TreeMap;

/**
 * Provides access to the flickr.reflection API methods.
 *
 * @author Jeremy Brooks
 * @see <a href="https://www.flickr.com/services/api/">Flickr API documentation</a> for more details.
 */
public class ReflectionApi {
  private Jinx jinx;

  private ReflectionApi() {
  }

  public ReflectionApi(Jinx jinx) {
    this.jinx = jinx;
  }

  /**
   * Returns a list of available Flickr API methods.
   *
   * Authentication
   *
   * This method does not require authentication.
   *
   * @return available Flickr API methods.
   * @throws JinxException if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.reflection.getMethods.html">flickr.reflection.getMethods</a>
   */
  public Methods getMethods() throws JinxException {
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.reflection.getMethods");
    return jinx.flickrGet(params, Methods.class, false);
  }

  /**
   * Returns information for a given Flickr API method.
   *
   * Authentication
   *
   * This method does not require authentication.
   *
   * @param methodName the name of the method to fetch information for. Required.
   * @return information about the method.
   * @throws JinxException if required parameters are missing, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.reflection.getMethodInfo.html">flickr.reflection.getMethodInfo</a>
   */
  public MethodInfo getMethodInfo(String methodName) throws JinxException {
    JinxUtils.validateParams(methodName);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.reflection.getMethodInfo");
    params.put("method_name", methodName);
    return jinx.flickrGet(params, MethodInfo.class, false);
  }
}
