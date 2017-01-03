/*
 * Jinx is Copyright 2010-2017 by Jeremy Brooks and Contributors
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
import net.jeremybrooks.jinx.response.contacts.Contacts;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Jeremy Brooks
 */
public class ContactsApi {
	private Jinx jinx;

	public ContactsApi(Jinx jinx) {
		this.jinx = jinx;
	}


	/**
	 * Get a list of contacts for the calling user.
	 * <br>
	 * This method requires authentication with 'read' permission.
	 *
	 * @param filter      Optional. Filter the results that are returned.
	 * @param page        Optional. The page of results to return. If this argument is zero, it defaults to 1.
	 * @param perPage     Optional. Number of photos to return per page. If this argument is zero, it defaults to 1000. The maximum allowed value is 1000.
	 * @param contactSort Optional. The order in which to sort the returned contacts. If this argument is null, defaults to name.
	 * @return object containing contacts matching the parameters.
	 * @throws JinxException if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.contacts.getList.html">flickr.contacts.getList</a>
	 */
	public Contacts getList(JinxConstants.ContactFilter filter, int page, int perPage, JinxConstants.ContactSort contactSort) throws JinxException {
		Map<String, String> params = new TreeMap<String, String>();
		params.put("method", "flickr.contacts.getList");
		if (filter != null) {
			params.put("filter", filter.toString());
		}
		if (page > 0) {
			params.put("page", Integer.toString(page));
		}
		if (perPage > 0) {
			params.put("per_page", Integer.toString(perPage));
		}
		if (contactSort != null) {
			params.put("sort", contactSort.toString());
		}
		return jinx.flickrGet(params, Contacts.class);
	}


	/**
	 * Return a list of contacts for a user who have recently uploaded photos along with the total count of photos uploaded.
	 * <br>
	 * Flickr documentation states:
	 * "This method is still considered experimental. We don't plan for it to change or to go away but so long as this notice
	 * is present you should write your code accordingly."
	 * <br>
	 * This method requires authentication with 'read' permission.
	 * <br>
	 *
	 * @param dateLastUpload Optional. Limits the resultset to contacts that have uploaded photos since this date. The default offset is (1) hour and the maximum (24) hours.
	 * @param filter         Optional. Limit the result set to all contacts or only those who are friends or family.
	 * @return object containing contacts matching the parameters.
	 * @throws JinxException if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.contacts.getListRecentlyUploaded.html">flickr.contacts.getListRecentlyUploaded</a>
	 */
	public Contacts getListRecentlyUploaded(Date dateLastUpload, JinxConstants.Contacts filter) throws JinxException {
		Map<String, String> params = new TreeMap<String, String>();
		params.put("method", "flickr.contacts.getListRecentlyUploaded");
		if (dateLastUpload != null) {
			params.put("date_lastupload", JinxUtils.formatDateAsUnixTimestamp(dateLastUpload));
		}
		if (filter != null) {
			params.put("filter", filter.toString());
		}
		return jinx.flickrGet(params, Contacts.class);
	}

	/**
	 * Get the contact list for a user.
	 * <br>
	 * This method does not require authentication.
	 *
	 * @param userId  Required. The NSID of the user to fetch the contact list for.
	 * @param page    Optional. The page of results to return. If this argument is &le;= zero, it defaults to 1.
	 * @param perPage Optional. Number of photos to return per page. If this argument is &le;= zero, it defaults to 1000.
	 *                The maximum allowed value is 1000.
	 * @return object containing contacts matching the parameters.
	 * @throws JinxException if required parameters are null or empty, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.contacts.getPublicList.html">flickr.contacts.getPublicList</a>
	 */
	public Contacts getPublicList(String userId, int page, int perPage) throws JinxException {
		JinxUtils.validateParams(userId);
		Map<String, String> params = new TreeMap<String, String>();
		params.put("method", "flickr.contacts.getPublicList");
		params.put("user_id", userId);
		if (page > 0) {
			params.put("page", Integer.toString(page));
		}
		if (perPage > 0) {
			params.put("per_page", Integer.toString(perPage));
		}
		return jinx.flickrGet(params, Contacts.class);
	}


	/**
	 * Get suggestions for tagging people in photos based on the calling user's contacts.
	 * <br>
	 * This method requires authentication with 'read' permission.
	 *
	 * @param page    Optional. The page of results to return. If this argument is &le;= zero, it defaults to 1.
	 * @param perPage Optional. Number of contacts to return per page. If this argument is &le;= 0, all contacts will be returned.
	 * @return object containing contacts matching the parameters.
	 * @throws JinxException if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.contacts.getTaggingSuggestions.html">flickr.contacts.getTaggingSuggestions</a>
	 */
	public Contacts getTaggingSuggestions(int page, int perPage) throws JinxException {
		Map<String, String> params = new TreeMap<String, String>();
		params.put("method", "flickr.contacts.getTaggingSuggestions");
		if (page > 0) {
			params.put("page", Integer.toString(page));
		}
		if (perPage > 0) {
			params.put("per_page", Integer.toString(perPage));
		}
		return jinx.flickrGet(params, Contacts.class);
	}
}
