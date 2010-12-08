/*
 * Jinx is Copyright 2010 by Jeremy Brooks
 *
 * This file is part of Jinx.
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

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import net.jeremybrooks.jinx.Jinx;
import net.jeremybrooks.jinx.JinxConstants;
import net.jeremybrooks.jinx.JinxException;
import net.jeremybrooks.jinx.JinxUtils;
import net.jeremybrooks.jinx.dto.Photos;
import org.w3c.dom.Document;


/**
 *
 * @author jeremyb
 */
public class FavoritesApi {

    private static FavoritesApi instance = null;


    private FavoritesApi() {
    }


    public static FavoritesApi getInstance() {
	if (FavoritesApi.instance == null) {
	    FavoritesApi.instance = new FavoritesApi();
	}

	return FavoritesApi.instance;
    }


    /**
     * Adds a photo to a user's favorites list.
     * 
     * This method requires authentication with 'write' permission.
     * 
     * Note: This method requires an HTTP POST request.
     * 
     * @param photoId the photo to add to the users favorites.
     * @throws JinxException if the photoId is null or empty, or if there are
     *         any errors.
     * @see http://www.flickr.com/services/api/flickr.favorites.add.html
     */
    public void add(String photoId) throws JinxException {
	if (photoId == null || photoId.trim().isEmpty()) {
	    throw new JinxException("Parameter photoId cannot be null or empty.");
	}


	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.favorites.add");
	params.put("api_key", Jinx.getInstance().getApiKey());
	params.put("photo_id", photoId);

	Jinx.getInstance().callFlickr(params, true, true);
    }
    

    /**
     * Returns a list of the user's favorite photos.
     *
     * Only photos which the calling user has permission to see are returned.
     *
     * This method requires authentication with 'read' permission.
     *
     * Extras:
     * You can include extras from JinxConstants.EXTRAS*
     * Currently supported fields include:
     * EXTRAS_DESCRIPTION
     * EXTRAS_LICENSE
     * EXTRAS_DATE_UPLOAD
     * EXTRAS_DATE_TAKEN
     * EXTRAS_OWNER_NAME
     * EXTRAS_ICON_SERVER
     * EXTRAS_ORIGINAL_FORMAT
     * EXTRAS_LAST_UPDATE
     * EXTRAS_GEO
     * EXTRAS_TAGS
     * EXTRAS_MACHINE_TAGS
     * EXTRAS_O_DIMS
     * EXTRAS_VIEWS
     * EXTRAS_MEDIA
     * EXTRAS_PATH_ALIAS
     * EXTRAX_URL_SQ
     * EXTRAS_URL_T
     * EXTRAS_URL_S
     * EXTRAS_URL_M
     * EXTRAS_URL_Z
     * EXTRAS_URL_L
     * EXTRAS_URL_O
     *
     *
     * @param userId the NSID of the user to fetch the favorites list for.
     *		     If this argument is omitted, the favorites list for the
     *		     calling user is returned.
     * @param minFaveDate minimum date that a photo was favorited on.
     * @param maxFaveDate maximum date that a photo was favorited on.
     * @param extras a list of extra information to fetch for each returned record.
     * @param perPage number of photos to return per page. If this argument is
     *	      zero, it defaults to 100. If this argument is > 500, it defaults
     *        to 500.
     * @param page the page of results to return. If this argument is zero, it
     *        defaults to 1.
     * @return photos object representing the users favorites.
     * @throws JinxException if there are any errors.
     * @see http://www.flickr.com/services/api/flickr.favorites.getList.html
     */
    public Photos getList(String userId, Date minFaveDate, Date maxFaveDate,
	    List<String> extras, int perPage, int page) throws JinxException {

	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.favorites.getList");
	params.put("api_key", Jinx.getInstance().getApiKey());
	if (userId != null && !userId.trim().isEmpty()) {
	    params.put("user_id", userId);
	}
	if (minFaveDate != null) {
	    params.put("min_fave_date", JinxUtils.formatDateAsUnixTimestamp(minFaveDate));
	}
	if (maxFaveDate != null) {
	    params.put("max_fave_date", JinxUtils.formatDateAsUnixTimestamp(maxFaveDate));
	}
	if (extras != null && extras.size() > 0) {
	    StringBuilder sb = new StringBuilder();
	    for (String s : extras) {
		if (s != null && s.trim().length() > 0) {
		    sb.append(s.trim()).append(',');
		}
	    }
	    sb.deleteCharAt(sb.lastIndexOf(","));

	    params.put("extras", sb.toString());
	}
	if (perPage > 0) {
	    if (perPage > 500) {
		perPage = 500;
	    }
	    params.put("per_page", Integer.toString(perPage));
	}
	if (page > 0) {
	    params.put("page", Integer.toString(page));
	}

	Document doc = Jinx.getInstance().callFlickr(params);

	return PhotosApi.getInstance().parsePhotosXml(doc);
    }


    /**
     * Returns a list of favorite public photos for the given user.
     *
     * This method does not require authentication.
     *
     * Extras:
     * You can include extras from JinxConstants.EXTRAS*
     * Currently supported fields include:
     * EXTRAS_DESCRIPTION
     * EXTRAS_LICENSE
     * EXTRAS_DATE_UPLOAD
     * EXTRAS_DATE_TAKEN
     * EXTRAS_OWNER_NAME
     * EXTRAS_ICON_SERVER
     * EXTRAS_ORIGINAL_FORMAT
     * EXTRAS_LAST_UPDATE
     * EXTRAS_GEO
     * EXTRAS_TAGS
     * EXTRAS_MACHINE_TAGS
     * EXTRAS_O_DIMS
     * EXTRAS_VIEWS
     * EXTRAS_MEDIA
     * EXTRAS_PATH_ALIAS
     * EXTRAX_URL_SQ
     * EXTRAS_URL_T
     * EXTRAS_URL_S
     * EXTRAS_URL_M
     * EXTRAS_URL_Z
     * EXTRAS_URL_L
     * EXTRAS_URL_O
     *
     * @param userId the NSID of the user to fetch the favorites list for.
     *               This argument is required.
     * @param minFaveDate minimum date that a photo was favorited on.
     * @param maxFaveDate maximum date that a photo was favorited on.
     * @param extras a list of extra information to fetch for each returned record.
     * @param perPage number of photos to return per page. If this argument is
     *	      zero, it defaults to 100. If this argument is > 500, it defaults
     *        to 500.
     * @param page the page of results to return. If this argument is zero, it
     *        defaults to 1.
     * @return photos object representing the users favorites.
     * @throws JinxException if the userId is null or empty, or if there are any errors.
     * @see http://www.flickr.com/services/api/flickr.favorites.getPublicList.html
     */
    public Photos getPublicList(String userId, Date minFaveDate, Date maxFaveDate,
	    List<String> extras, int perPage, int page) throws JinxException {
	if (userId == null || userId.trim().isEmpty()) {
	    throw new JinxException("Parameter userId is required.");
	}

	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.favorites.getPublicList");
	params.put("api_key", Jinx.getInstance().getApiKey());
	params.put("user_id", userId);
	if (minFaveDate != null) {
	    params.put("min_fave_date", JinxUtils.formatDateAsUnixTimestamp(minFaveDate));
	}
	if (maxFaveDate != null) {
	    params.put("max_fave_date", JinxUtils.formatDateAsUnixTimestamp(maxFaveDate));
	}
	if (extras != null && extras.size() > 0) {
	    StringBuilder sb = new StringBuilder();
	    for (String s : extras) {
		if (s != null && s.trim().length() > 0) {
		    sb.append(s.trim()).append(',');
		}
	    }
	    sb.deleteCharAt(sb.lastIndexOf(","));

	    params.put("extras", sb.toString());
	}
	if (perPage > 0) {
	    if (perPage > 500) {
		perPage = 500;
	    }
	    params.put("per_page", Integer.toString(perPage));
	}
	if (page > 0) {
	    params.put("page", Integer.toString(page));
	}

	Document doc = Jinx.getInstance().callFlickr(params, false);

	return PhotosApi.getInstance().parsePhotosXml(doc);
    }

    
    /**
     * Removes a photo from a user's favorites list.
     *
     * This method requires authentication with 'write' permission.
     *
     * Note: This method requires an HTTP POST request.
     *
     * @param photoId the photo to remove from the user's favorites.
     * @throws JinxException if the photoId parameter is missing, or if there
     *         are any errors.
     * @see http://www.flickr.com/services/api/flickr.favorites.remove.html
     */
    public void remove(String photoId) throws JinxException {
	if (photoId == null || photoId.trim().isEmpty()) {
	    throw new JinxException("Parameter photoId cannot be null or empty.");
	}

	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.favorites.remove");
	params.put("api_key", Jinx.getInstance().getApiKey());
	params.put("photo_id", photoId);

	Jinx.getInstance().callFlickr(params, true, true);
    }
}
