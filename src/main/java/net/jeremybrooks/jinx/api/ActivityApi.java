package net.jeremybrooks.jinx.api;

import net.jeremybrooks.jinx.Jinx;
import net.jeremybrooks.jinx.JinxException;
import net.jeremybrooks.jinx.response.ActivityResponse;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author Jeremy Brooks
 */
public class ActivityApi {

	private Jinx jinx;

	public ActivityApi(Jinx jinx) {
		this.jinx = jinx;
	}


	/**
	 * Returns a list of recent activity on photos commented on by the calling user.
	 * Do not poll this method more than once an hour.
	 * <p/>
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
			http://api.flickr.com/services/rest/?method=flickr.activity.userComments&api_key=be21c1db5ab3dee07f7907de26d1e9be&per_page=10&page=1&format=json&nojsoncallback=1&auth_token=72157632971491247-fb7b422f825749fb&api_sig=9e917303e6bef519ba28f6913a433734
		*/
		Map<String, String> params = new TreeMap<String, String>();
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
	 * <p/>
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
			http://api.flickr.com/services/rest/?method=flickr.activity.userPhotos&api_key=be21c1db5ab3dee07f7907de26d1e9be&timeframe=2d&per_page=10&page=1&format=json&nojsoncallback=1&auth_token=72157632971491247-fb7b422f825749fb&api_sig=0e1e87e75be6915b6ba4c93ad8bae934
		*/

		Map<String, String> params = new TreeMap<String, String>();
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