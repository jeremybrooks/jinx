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

package net.jeremybrooks.jinx.api;

import net.jeremybrooks.jinx.Jinx;
import net.jeremybrooks.jinx.JinxException;
import net.jeremybrooks.jinx.JinxUtils;
import net.jeremybrooks.jinx.response.Response;
import net.jeremybrooks.jinx.response.groups.GroupInfo;
import net.jeremybrooks.jinx.response.groups.GroupSearch;

import java.util.Map;
import java.util.TreeMap;

/**
 * Provides access to the flickr.groups API methods.
 *
 * @author Jeremy Brooks
 * @see <a href="https://www.flickr.com/services/api/">Flickr API documentation</a> for more details.
 */
public class GroupsApi {
  private Jinx jinx;

  public GroupsApi(Jinx jinx) {
    this.jinx = jinx;
  }


  /**
   * This is a legacy method and will not return anything useful.
   *
   * @param categoryId (Optional) Category id to fetch a list of groups and sub-categories for.
   *                   If not specified, it defaults to zero, the root of the category tree.
   * @return object with response from Flickr indicating ok or fail.
   * @throws JinxException if required parameters are missing, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.groups.browse.html">flickr.groups.browse</a>
   * @deprecated this is a legacy method, and will not return anything useful.
   */
  @Deprecated
  public Response browse(String categoryId) throws JinxException {
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.groups.browse");
    return jinx.flickrGet(params, Response.class);
  }


  /**
   * Get information about a group.
   * <br>
   * This method does not require authentication.
   * <br>
   *
   * @param groupId        (Optional) The NSID of the group to fetch information for. One of this or the groupPathAlias is required.
   * @param groupPathAlias (Optional) The path alias of the group. One of this or the groupId parameter is required.
   * @param lang           (Optional) The language of the group name and description to fetch.
   *                       If the language is not found, the primary language of the group will be returned.
   *                       Valid values are the same as in <a href="https://www.flickr.com/services/feeds/">feeds.</a>
   * @return information about the group.
   * @throws JinxException if required parameters are missing, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.groups.getInfo.html">flickr.groups.getInfo</a>
   */
  public GroupInfo getInfo(String groupId, String groupPathAlias, String lang) throws JinxException {
    if (JinxUtils.isNullOrEmpty(groupId)) {
      JinxUtils.validateParams(groupPathAlias);
    } else {
      JinxUtils.validateParams(groupId);
    }
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.groups.getInfo");
    if (!JinxUtils.isNullOrEmpty(groupId)) {
      params.put("group_id", groupId);
    } else {
      params.put("group_path_alias", groupPathAlias);
    }
    if (!JinxUtils.isNullOrEmpty(lang)) {
      params.put("lang", lang);
    }
    return jinx.flickrGet(params, GroupInfo.class, false);
  }


  /**
   * Join a public group as a member.
   * <br>
   * This method requires authentication with 'write' permission.
   * <br>
   *
   * @param groupId     (Required) The NSID of the group to join.
   * @param acceptRules If the group has rules, they must be displayed to the user prior to joining.
   *                    Passing a true value for this argument specifies that the application has displayed the group
   *                    rules to the user, and that the user has agreed to them.
   * @return object with response from Flickr indicating ok or fail.
   * @throws JinxException if required parameters are missing, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.groups.join.html">flickr.groups.join</a>
   */
  public Response join(String groupId, boolean acceptRules) throws JinxException {
    JinxUtils.validateParams(groupId);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.groups.join");
    params.put("group_id", groupId);
    if (acceptRules) {
      params.put("accept_rules", "true");
    }
    return jinx.flickrPost(params, Response.class);
  }


  /**
   * Request to join a group that is invitation-only.
   * <br>
   * This method requires authentication with 'write' permission.
   * <br>
   *
   * @param groupId     (Required) The NSID of the group to request joining.
   * @param message     (Required) Message to the group administrators.
   * @param acceptRules If the group has rules, they must be displayed to the user prior to joining.
   *                    Passing a true value for this argument specifies that the application has displayed
   *                    the group rules to the user, and that the user has agreed to them.
   * @return object with response from Flickr indicating ok or fail.
   * @throws JinxException if required parameters are missing, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.groups.joinRequest.html">flickr.groups.joinRequest</a>
   */
  public Response joinRequest(String groupId, String message, boolean acceptRules) throws JinxException {
    JinxUtils.validateParams(groupId, message);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.groups.joinRequest");
    params.put("group_id", groupId);
    params.put("message", message);
    if (acceptRules) {
      params.put("accept_rules", "true");
    }
    return jinx.flickrPost(params, Response.class);
  }


  /**
   * Leave a group.
   * <br>
   * If the user is the only administrator left, and there are other members,
   * the oldest member will be promoted to administrator.
   * <br>
   * If the user is the last person in the group, the group will be deleted.
   * <br>
   * This method requires authentication with 'delete' permission.
   * <br>
   *
   * @param groupId      (Required) The NSID of the Group to leave.
   * @param deletePhotos Delete all photos by this user from the group.
   * @return object with response from Flickr indicating ok or fail.
   * @throws JinxException if required parameters are missing, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.groups.leave.html">flickr.groups.leave</a>
   */
  public Response leave(String groupId, boolean deletePhotos) throws JinxException {
    JinxUtils.validateParams(groupId);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.groups.leave");
    params.put("group_id", groupId);
    if (deletePhotos) {
      params.put("delete_photos", "true");
    }
    return jinx.flickrPost(params, Response.class);
  }


  /**
   * Search for groups. 18+ groups will only be returned for authenticated calls where the authenticated user is over 18.
   * <br>
   * This method does not require authentication.
   * <br>
   *
   * @param text    (Required) The text to search for.
   * @param perPage (Optional) Number of groups to return per page. If this argument is less than 1, it defaults to 100.
   *                The maximum allowed value is 500.
   * @param page    (Optional) The page of results to return. If this argument is less than 1, it defaults to 1.
   * @return group search results.
   * @throws JinxException if required parameters are missing, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.groups.search.html">flickr.groups.search</a>
   */
  public GroupSearch search(String text, int perPage, int page) throws JinxException {
    JinxUtils.validateParams(text);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.groups.search");
    params.put("text", text);
    if (perPage > 0) {
      params.put("per_page", Integer.toString(perPage));
    }
    if (page > 0) {
      params.put("page", Integer.toString(page));
    }
    return jinx.flickrGet(params, GroupSearch.class, false);
  }
}

