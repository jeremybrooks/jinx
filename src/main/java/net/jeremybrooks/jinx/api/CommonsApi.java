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

package net.jeremybrooks.jinx.api;

import net.jeremybrooks.jinx.Jinx;
import net.jeremybrooks.jinx.JinxException;
import net.jeremybrooks.jinx.response.commons.Institutions;

import java.util.Map;
import java.util.TreeMap;

/**
 * Provides access to the {@code flickr.commons} API methods.
 *
 * @author Jeremy Brooks
 * @see <a href="https://www.flickr.com/services/api/">Flickr API documentation</a> for more details.
 */
public class CommonsApi {
  private final Jinx jinx;

  public CommonsApi(Jinx jinx) {
    this.jinx = jinx;
  }

  /**
   * Retrieves a list of the current Commons institutions.
   * <br>
   * This method does not require authentication.
   *
   * @return list of the current Commons institutions.
   * @throws JinxException if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.commons.getInstitutions.html">flickr.commons.getInstitutions</a>
   */
  public Institutions getInstitutions() throws JinxException {
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.commons.getInstitutions");
    return jinx.flickrGet(params, Institutions.class);
  }
}
