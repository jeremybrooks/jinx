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

package net.jeremybrooks.jinx.api;

import net.jeremybrooks.jinx.Jinx;
import net.jeremybrooks.jinx.JinxConstants;
import net.jeremybrooks.jinx.JinxException;
import net.jeremybrooks.jinx.JinxUtils;
import net.jeremybrooks.jinx.response.photos.Photos;

import java.util.EnumSet;
import java.util.Map;
import java.util.TreeMap;

/**
 * Provides access to the flickr.interestingness API methods.
 *
 * @author Jeremy Brooks
 * @see <a href="https://www.flickr.com/services/api/">Flickr API documentation</a> for more details.
 */
public class InterestingnessApi {
  private Jinx jinx;

  public InterestingnessApi(Jinx jinx) {
    this.jinx = jinx;
  }


  /**
   * Returns the list of interesting photos for the most recent day or a user-specified date.
   * <br>
   * This method does not require authentication.
   *
   * @param date    (Optional) A specific date, formatted as YYYY-MM-DD, to return interesting photos for.
   * @param extras  (Optional) extra information to fetch for each returned record.
   * @param perPage Number of photos to return per page. If this argument is less than 1, it defaults to 100. The maximum allowed value is 500.
   * @param page    The page of results to return. If this argument is less than 1, it defaults to 1.
   * @param sign    if true, the request will be signed.
   * @return photos object with the requested photos.
   * @throws JinxException if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.interestingness.getList.html">flickr.interestingness.getList</a>
   */
  public Photos getList(String date, EnumSet<JinxConstants.PhotoExtras> extras, int perPage, int page, boolean sign) throws JinxException {
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.interestingness.getList");
    if (!JinxUtils.isNullOrEmpty(date)) {
      params.put("date", date);
    }
    if (!JinxUtils.isNullOrEmpty(extras)) {
      params.put("extras", JinxUtils.buildCommaDelimitedList(extras));
    }
    if (page > 0) {
      params.put("page", Integer.toString(page));
    }
    if (perPage > 0) {
      params.put("per_page", Integer.toString(perPage));
    }
    return jinx.flickrGet(params, Photos.class, sign);
  }
}
