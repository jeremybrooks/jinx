/*
 * Jinx is Copyright 2010-2014 by Jeremy Brooks and Contributors
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
import net.jeremybrooks.jinx.response.Response;
import net.jeremybrooks.jinx.response.common.Context;
import net.jeremybrooks.jinx.response.photos.Photos;

import java.util.Date;
import java.util.EnumSet;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Jeremy Brooks
 */
public class FavoritesApi {
	private Jinx jinx;

	public FavoritesApi(Jinx jinx) {
		this.jinx = jinx;
	}


	/**
	 * <a href="https://www.flickr.com/services/api/flickr.favorites.add.html">flickr.favorites.add</a>
	 * <p/>
	 * Adds a photo to a user's favorites list.
	 * <p/>
	 * This method requires authentication with 'write' permission.
	 *
	 * @param photoId Required. The id of the photo to add to the user's favorites.
	 * @return object with the result of the requested operation.
	 * @throws JinxException if required parameters are null or empty, or if there are any errors.
	 */
	public Response add(String photoId) throws JinxException {
		JinxUtils.validateParams(photoId);
		Map<String, String> params = new TreeMap<String, String>();
		params.put("method", "flickr.favorites.add");
		params.put("photo_id", photoId);
		return jinx.flickrPost(params, Response.class);
	}


	/**
	 * <a href="https://www.flickr.com/services/api/flickr.favorites.getContext.htm">flickr.favorites.getContext</a>
	 * <p/>
	 * Returns next and previous favorites for a photo in a user's favorites.
	 * <p/>
	 * This method does not require authentication.
	 *
	 * @param photoId Required. The id of the photo to fetch the context for.
	 * @param userId  Required. The user who counts the photo as a favorite.
	 * @return object with context information.
	 * @throws JinxException if required parameters are null or empty, or if there are any errors.
	 */
	public Context getContext(String photoId, String userId) throws JinxException {
		JinxUtils.validateParams(photoId, userId);
		Map<String, String> params = new TreeMap<String, String>();
		params.put("method", "flickr.favorites.getContext");
		params.put("photo_id", photoId);
		params.put("user_id", userId);
		return jinx.flickrPost(params, Context.class);
	}

	/**
	 * <a href="https://www.flickr.com/services/api/flickr.favorites.getList.html>flickr.favorites.getList</a>
	 * <p/>
	 * Returns a list of the user's favorite photos. Only photos which the calling user has permission to see are returned.
	 * <p/>
	 * This method requires authentication with 'read' permission.
	 *
	 * @param userId      Optional. The NSID of the user to fetch the favorites list for. If this argument is null, the favorites list for the calling user is returned.
	 * @param minFaveDate Optional. Minimum date that a photo was favorited on.
	 * @param maxFaveDate Optional. Maximum date that a photo was favorited on.
	 * @param extras      Optional. Extra information to return for each photo.
	 * @param perPage     Optional. Number of photos to return per page. If this argument is <= 0, it defaults to 100. The maximum allowed value is 500.
	 * @param page        Optional. The page of results to return. If this argument is <= 0, it defaults to 1.
	 * @return object with photos and metadata matching the query.
	 * @throws JinxException if there are any errors.
	 */
	public Photos getList(String userId, Date minFaveDate, Date maxFaveDate, EnumSet<JinxConstants.PhotoExtras> extras, int perPage, int page) throws JinxException {
		Map<String, String> params = new TreeMap<String, String>();
		params.put("method", "flickr.favorites.getList");
		if (!JinxUtils.isNullOrEmpty(userId)) {
			params.put("user_id", userId);
		}
		if (minFaveDate != null) {
			params.put("min_fave_date", JinxUtils.formatDateAsUnixTimestamp(minFaveDate));
		}
		if (maxFaveDate != null) {
			params.put("max_fave_date", JinxUtils.formatDateAsUnixTimestamp(maxFaveDate));
		}
		if (!JinxUtils.isNullOrEmpty(extras)) {
			params.put("extras", JinxUtils.buildCommaDelimitedList(extras));
		}
		if (perPage > 0) {
			params.put("per_page", Integer.toString(perPage));
		}
		if (page > 0) {
			params.put("page", Integer.toString(page));
		}
		return jinx.flickrGet(params, Photos.class);
	}

	/**
	 * <a href="https://www.flickr.com/services/api/flickr.favorites.getPublicList.html">flickr.favorites.getPublicList</a>
	 * <p/>
	 * Returns a list of favorite public photos for the given user.
	 * <p/>
	 * This method does not require authentication.
	 * <p/>
	 *
	 * @param userId      Required. The NSID of the user to fetch the favorites list for. If this argument is null, the favorites list for the calling user is returned.
	 * @param minFaveDate Optional. Minimum date that a photo was favorited on.
	 * @param maxFaveDate Optional. Maximum date that a photo was favorited on.
	 * @param extras      Optional. Extra information to return for each photo.
	 * @param perPage     Optional. Number of photos to return per page. If this argument is <= 0, it defaults to 100. The maximum allowed value is 500.
	 * @param page        Optional. The page of results to return. If this argument is <= 0, it defaults to 1.
	 * @return object with photos and metadata matching the query.
	 * @throws JinxException if required parameter is missing or empty, or if there are any errors.
	 */
	public Photos getPublicList(String userId, Date minFaveDate, Date maxFaveDate, EnumSet<JinxConstants.PhotoExtras> extras, int perPage, int page) throws JinxException {
		JinxUtils.validateParams(userId);
		Map<String, String> params = new TreeMap<String, String>();
		params.put("method", "flickr.favorites.getList");
		params.put("user_id", userId);
		if (minFaveDate != null) {
			params.put("min_fave_date", JinxUtils.formatDateAsUnixTimestamp(minFaveDate));
		}
		if (maxFaveDate != null) {
			params.put("max_fave_date", JinxUtils.formatDateAsUnixTimestamp(maxFaveDate));
		}
		if (!JinxUtils.isNullOrEmpty(extras)) {
			params.put("extras", JinxUtils.buildCommaDelimitedList(extras));
		}
		if (perPage > 0) {
			params.put("per_page", Integer.toString(perPage));
		}
		if (page > 0) {
			params.put("page", Integer.toString(page));
		}
		return jinx.flickrGet(params, Photos.class);
	}


	/**
	 * <a href="https://www.flickr.com/services/api/flickr.favorites.remove.html">flickr.favorites.remove</a>
	 * <p/>
	 * Removes a photo from a user's favorites list.
	 * <p/>
	 * This method requires authentication with 'write' permission.
	 *
	 * @param photoId Required. The id of the photo to add to the user's favorites.
	 * @return object with the result of the requested operation.
	 * @throws JinxException if required parameters are null or empty, or if there are any errors.
	 */
	public Response remove(String photoId) throws JinxException {
		JinxUtils.validateParams(photoId);
		Map<String, String> params = new TreeMap<String, String>();
		params.put("method", "flickr.favorites.remove");
		params.put("photo_id", photoId);
		return jinx.flickrPost(params, Response.class);
	}

}
