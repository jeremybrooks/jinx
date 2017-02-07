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
import net.jeremybrooks.jinx.JinxException;
import net.jeremybrooks.jinx.JinxUtils;
import net.jeremybrooks.jinx.response.collections.CollectionInfo;
import net.jeremybrooks.jinx.response.collections.CollectionTree;

import java.util.Map;
import java.util.TreeMap;

/**
 * Provides access to the flickr.collections API methods.
 *
 * @author Jeremy Brooks
 * @see <a href="https://www.flickr.com/services/api/">Flickr API documentation</a> for more details.
 */
public class CollectionsApi {
  private Jinx jinx;

  public CollectionsApi(Jinx jinx) {
    this.jinx = jinx;
  }


  /**
   * Returns information for a single collection. Currently can only be called by the collection owner, this may change.
   * <br>
   * This method requires authentication with 'read' permission.
   *
   * @param collectionId Required. The id of the collection to fetch information for.
   * @return information about the collection.
   * @throws JinxException if required parameters are null or empty, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.collections.getInfo.html">flickr.collections.getInfo</a>
   */
  public CollectionInfo getInfo(String collectionId) throws JinxException {
    JinxUtils.validateParams(collectionId);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.collections.getInfo");
    params.put("collection_id", collectionId);
    return jinx.flickrGet(params, CollectionInfo.class);
  }


  /**
   * Returns a tree (or sub tree) of collections belonging to a given user.
   * <br>
   * This method does not require authentication.
   *
   * @param collectionId Optional. The ID of the collection to fetch a tree for, or zero to fetch the root collection. Defaults to zero.
   * @param userId       Optional. The ID of the account to fetch the collection tree for. Deafults to the calling user.
   * @return nested tree of collections, and the collections and sets they contain.
   * @throws JinxException if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.collections.getTree.html">flickr.collections.getTree</a>
   */
  public CollectionTree getTree(String collectionId, String userId) throws JinxException {
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.collections.getTree");
    if (!JinxUtils.isNullOrEmpty(collectionId)) {
      params.put("collection_id", collectionId);
    }
    if (!JinxUtils.isNullOrEmpty(userId)) {
      params.put("user_id", userId);
    }
    return jinx.flickrGet(params, CollectionTree.class);
  }
}
