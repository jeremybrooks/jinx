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
import net.jeremybrooks.jinx.response.activity.ActivityResponse;

import java.util.Map;
import java.util.TreeMap;

/**
 * Provides access to the flickr.activity API methods.
 *
 * @author Jeremy Brooks
 * @see <a href="https://www.flickr.com/services/api/">Flickr API documentation</a> for more details.
 */
public class ActivityApi {

	private final Jinx jinx;

	public ActivityApi(Jinx jinx) {
		this.jinx = jinx;
	}


	/**
	 * Returns a list of recent activity on photos commented on by the calling user.
	 * Do not poll this method more than once an hour.
	 * <br>
	 * This method requires authentication with 'read' permission.
	 *
	 * @param perPage (Optional) Number of items to return per page. If this argument is null or less than 1, it defaults to 10.
	 *                If this argument is greater than 50, it defaults to 50.
	 * @param page    (Optional) The page of results to return. If this argument is null or less than 1, it defaults to 1.
	 * @return object encapsulating the user photo activity.
	 * @throws JinxException if there are any errors.
	 * @see <a href="http://www.flickr.com/services/api/flickr.activity.userComments.html">flickr.activity.useComments</a>
	 */
	public ActivityResponse userComments(Integer perPage, Integer page) throws JinxException {
		/* sample URL:
			https://api.flickr.com/services/rest/?method=flickr.activity.userComments&api_key=be21c1db5ab3dee07f7907de26d1e9be&per_page=10&page=1&format=json&nojsoncallback=1&auth_token=72157632971491247-fb7b422f825749fb&api_sig=9e917303e6bef519ba28f6913a433734
		*/
		Map<String, String> params = new TreeMap<>();
		params.put("method", "flickr.activity.userComments");

		if (perPage != null) {
			if (perPage < 1) {
				perPage = 10;
			} else if (perPage > 50) {
				perPage = 50;
			}
			params.put("per_page", perPage.toString());
		}
		if (page != null) {
			if (page < 1) {
				page = 1;
			}
			params.put("page", page.toString());
		}

		return this.jinx.flickrGet(params, ActivityResponse.class);
	}


	/**
	 * Returns a list of recent activity on photos belonging to the calling user.
	 * Do not poll this method more than once an hour.
	 * <br>
	 * This method requires authentication with 'read' permission.
	 *
	 * @param timeframe (Optional) The timeframe in which to return updates for.
	 *                  This can be specified in days ('2d') or hours ('4h'). If this argument is null, the behavior is to return
	 *                  changes since the beginning of the previous user session.
	 * @param perPage   (Optional) Number of items to return per page. If this argument is null or less than one, it defaults to 10.
	 *                  If this argument is greater than 50, it defaults to 50.
	 * @param page      (Optional) The page of results to return. If this argument is null or less than 1, it defaults to 1.
	 * @return object encapsulating the user photo activity.
	 * @throws JinxException if there are any errors.
	 * @see <a href="http://www.flickr.com/services/api/flickr.activity.userPhotos.html">flickr.activity.userPhotos</a>
	 */
	public ActivityResponse userPhotos(String timeframe, Integer perPage, Integer page) throws JinxException {
		/* sample URL for userPhotos
			https://api.flickr.com/services/rest/?method=flickr.activity.userPhotos&api_key=be21c1db5ab3dee07f7907de26d1e9be&timeframe=2d&per_page=10&page=1&format=json&nojsoncallback=1&auth_token=72157632971491247-fb7b422f825749fb&api_sig=0e1e87e75be6915b6ba4c93ad8bae934
		*/

		Map<String, String> params = new TreeMap<>();
		params.put("method", "flickr.activity.userPhotos");
		if (timeframe != null) {
			params.put("timeframe", timeframe);
		}
		if (perPage != null) {
			if (perPage < 1) {
				perPage = 10;
			} else if (perPage > 50) {
				perPage = 50;
			}
			params.put("per_page", perPage.toString());
		}
		if (page != null) {
			if (page < 1) {
				page = 1;
			}
			params.put("page", page.toString());
		}

		return this.jinx.flickrGet(params, ActivityResponse.class);
	}

}
