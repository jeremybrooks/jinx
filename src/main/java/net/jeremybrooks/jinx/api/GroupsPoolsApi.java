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
import net.jeremybrooks.jinx.JinxConstants;
import net.jeremybrooks.jinx.JinxException;
import net.jeremybrooks.jinx.JinxUtils;
import net.jeremybrooks.jinx.response.Response;
import net.jeremybrooks.jinx.response.common.Context;
import net.jeremybrooks.jinx.response.groups.GroupSearch;
import net.jeremybrooks.jinx.response.photos.Photos;

import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Provides access to the {@code flickr.groups.pools} API methods.
 *
 * @author Jeremy Brooks
 * @see <a href="https://www.flickr.com/services/api/">Flickr API documentation</a> for more details.
 */
public class GroupsPoolsApi {
  private final Jinx jinx;

  public GroupsPoolsApi(Jinx jinx) {
    this.jinx = jinx;
  }

  /**
   * Add a photo to a group's pool.
   * <br>
   * This method requires authentication with 'write' permission.
   *
   * @param photoId (Required) The id of the photo to add to the group pool. The photo must belong to the calling user.
   * @param groupId (Required) The group id of the group whose pool the photo is to be added to.
   * @return object with response from Flickr indicating ok or fail.
   * @throws JinxException if required parameters are missing, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.groups.pools.add.html">flickr.groups.pools.add</a>
   */
  public Response add(String photoId, String groupId) throws JinxException {
    JinxUtils.validateParams(photoId, groupId);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.groups.pools.add");
    params.put("group_id", groupId);
    params.put("photo_id", photoId);
    return jinx.flickrPost(params, Response.class);
  }

  /**
   * Returns next and previous photos for a photo in a group pool.
   * <br>
   * This method does not require authentication. Unsigned requests may not be able to see photos in some groups.
   *
   * @param photoId (Required) The id of the photo to fetch the context for.
   * @param groupId (Required) The group id of the group whose pool to fetch the photo's context for.
   * @param sign    if true, the request will be signed.
   * @return context for the specified photo in the specified group.
   * @throws JinxException if required parameters are missing, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.groups.pools.getContext.html">flickr.groups.pools.getContext</a>
   */
  public Context getContext(String photoId, String groupId, boolean sign) throws JinxException {
    JinxUtils.validateParams(photoId, groupId);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.groups.pools.getContext");
    params.put("group_id", groupId);
    params.put("photo_id", photoId);
    return jinx.flickrGet(params, Context.class, sign);
  }

  /**
   * Returns a list of groups to which you can add photos.
   * <br>
   * This method requires authentication with 'read' permission.
   *
   * @param page    The page of results to return. If this argument is less than 1, it defaults to 1.
   * @param perPage Number of groups to return per page. If this argument is less than 1, it defaults to 400. The maximum allowed value is 400.
   * @return object with the groups to which you can add photos.
   * @throws JinxException if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.groups.pools.getGroups.html">flickr.groups.pools.getGroups</a>
   */
  public GroupSearch getGroups(int page, int perPage) throws JinxException {
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.groups.pools.getGroups");
    if (page > 0) {
      params.put("page", Integer.toString(page));
    }
    if (perPage > 0) {
      params.put("per_page", Integer.toString(perPage));
    }
    return jinx.flickrGet(params, GroupSearch.class);
  }

  /**
   * Returns a list of pool photos for a given group, based on the permissions of the group and the user logged in (if any).
   * <br>
   * This method does not require authentication. Unsigned requests may not be able to retrieve photos from some groups.
   *
   * @param groupId (Required) The id of the group whose pool you which to get the photo list for.
   * @param tags    (Optional) A list of tags to filter the pool with. At the moment only one tag at a time is supported. The first tag in the list will be used.
   * @param userId  (Optional) The nsid of a user. Specifying this parameter will retrieve for you only those photos that the user has contributed to the group pool.
   * @param extras  (Optional) extra information to fetch for each returned record.
   * @param perPage Number of photos to return per page. If this argument is less than 1, it defaults to 100. The maximum allowed value is 500.
   * @param page    The page of results to return. If this argument is less than 1, it defaults to 1.
   * @param sign    if true, the request will be signed.
   * @return list of photos.
   * @throws JinxException if required parameters are missing, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.groups.pools.getPhotos.html">flickr.groups.pools.getPhotos</a>
   */
  public Photos getPhotos(String groupId, List<String> tags, String userId, EnumSet<JinxConstants.PhotoExtras> extras, int perPage, int page, boolean sign) throws JinxException {
    JinxUtils.validateParams(groupId);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.groups.pools.getPhotos");
    params.put("group_id", groupId);
    if (!JinxUtils.isNullOrEmpty(tags)) {
      params.put("tags", tags.get(0));
    }
    if (!JinxUtils.isNullOrEmpty(userId)) {
      params.put("user_id", userId);
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

  /**
   * Remove a photo from a group pool.
   * <br>
   * This method requires authentication with 'write' permission.
   *
   * @param photoId (Required) The id of the photo to remove from the group pool. The photo must either be owned by the calling user of the calling user must be an administrator of the group.
   * @param groupId (Required) The group id of the group whose pool the photo is to be removed from.
   * @return object with response from Flickr indicating ok or fail.
   * @throws JinxException if required parameters are missing, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.groups.pools.remove.html">flickr.groups.pools.remove</a>
   */
  public Response remove(String photoId, String groupId) throws JinxException {
    JinxUtils.validateParams(photoId, groupId);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.groups.pools.remove");
    params.put("group_id", groupId);
    params.put("photo_id", photoId);
    return jinx.flickrPost(params, Response.class);
  }
}
