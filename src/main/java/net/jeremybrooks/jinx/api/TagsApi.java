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
import net.jeremybrooks.jinx.JinxConstants;
import net.jeremybrooks.jinx.JinxException;
import net.jeremybrooks.jinx.JinxUtils;
import net.jeremybrooks.jinx.response.photos.Photos;
import net.jeremybrooks.jinx.response.tags.Clusters;
import net.jeremybrooks.jinx.response.tags.HotList;
import net.jeremybrooks.jinx.response.tags.PhotoTagList;
import net.jeremybrooks.jinx.response.tags.RawTagsForUser;
import net.jeremybrooks.jinx.response.tags.RelatedTags;
import net.jeremybrooks.jinx.response.tags.TagsForUser;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Provides access to the flickr.tags API methods.
 *
 * @author Jeremy Brooks
 * @see <a href="https://www.flickr.com/services/api/">Flickr API documentation</a> for more details.
 */
public class TagsApi {
  private Jinx jinx;

  private TagsApi() {
  }

  public TagsApi(Jinx jinx) {
    this.jinx = jinx;
  }

  /**
   * Returns the first 24 photos for a given tag cluster
   *
   * This method does not require authentication.
   *
   * @param tag       the tag that the cluster belongs to. Required.
   * @param clusterId top three tags for the cluster, separated by dashes. Required.
   * @return first 24 photos for a given tag cluster.
   * @throws JinxException if required parameters are missing, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.tags.getClusterPhotos.html">flickr.tags.getClusterPhotos</a>
   */
  public Photos getClusterPhotos(String tag, String clusterId) throws JinxException {
    JinxUtils.validateParams(tag, clusterId);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.tags.getClusterPhotos");
    params.put("tag", tag);
    params.put("cluster_id", clusterId);
    return jinx.flickrGet(params, Photos.class, false);
  }

  /**
   * Returns the first 24 photos for a given tag cluster
   *
   * This method does not require authentication.
   *
   * This method will combine the Strings in the clusterId list.
   *
   * @param tag       the tag that the cluster belongs to. Required.
   * @param clusterId top three tags for the cluster. Required.
   * @return first 24 photos for a given tag cluster.
   * @throws JinxException if required parameters are missing, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.tags.getClusterPhotos.html">flickr.tags.getClusterPhotos</a>
   */
  public Photos getClusterPhotos(String tag, List<String> clusterId) throws JinxException {
    JinxUtils.validateParams(tag, clusterId);
    StringBuilder sb = new StringBuilder();
    for (String s : clusterId) {
      sb.append(s).append("-");
    }
    if (sb.length() > 0) {
      sb.deleteCharAt(sb.length() - 1);
    }
    return getClusterPhotos(tag, sb.toString());
  }

  /**
   * Gives you a list of tag clusters for the given tag.
   *
   * This method does not require authentication.
   *
   * @param tag the tag to fetch clusters for. Required.
   * @return tag clusters for the given tag.
   * @throws JinxException if required parameters are missing, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.tags.getClusters.html">flickr.tags.getClusters</a>
   */
  public Clusters getClusters(String tag) throws JinxException {
    JinxUtils.validateParams(tag);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.tags.getClusters");
    params.put("tag", tag);
    return jinx.flickrGet(params, Clusters.class, false);
  }

  /**
   * Returns a list of hot tags for the given period.
   *
   * This method does not require authentication.
   *
   * @param period period for which to fetch hot tags. Optional.
   * @param count  number of tags to return. Defaults to 20. Maximum allowed value is 200. Optional.
   * @return hot tags for the given period.
   * @throws JinxException if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.tags.getHotList.html">flickr.tags.getHotList</a>
   */
  public HotList getHotList(JinxConstants.Period period, Integer count) throws JinxException {
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.tags.getHotList");
    if (period != null) {
      params.put("period", period.toString());
    }
    if (count != null && count > 0) {
      params.put("count", count.toString());
    }
    return jinx.flickrGet(params, HotList.class, false);
  }

  /**
   * Get the tag list for a given photo.
   *
   * This method does not require authentication.
   *
   * @return tags list for the photo. Required.
   * @throws JinxException if required parameter is missing, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.tags.getListPhoto.html">flickr.tags.getListPhoto</a>
   */
  public PhotoTagList getListPhoto(String photoId) throws JinxException {
    JinxUtils.validateParams(photoId);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.tags.getListPhoto");
    params.put("photo_id", photoId);
    return jinx.flickrGet(params, PhotoTagList.class, false);
  }


  /**
   * Get the tag list for a given user (or the currently logged in user).
   *
   * This method does not require authentication.
   *
   * @param userId NSID of the user to fetch the tag list for. If this argument is not
   *               specified, the currently logged in user (if any) is assumed. Optional.
   * @return tag list for given user.
   * @throws JinxException if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.tags.getListUser.html">flickr.tags.getListUser</a>
   */
  public TagsForUser getListUser(String userId) throws JinxException {
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.tags.getListUser");
    if (!JinxUtils.isNullOrEmpty(userId)) {
      params.put("user_id", userId);
    }
    return jinx.flickrGet(params, TagsForUser.class);
  }

  /**
   * Get the popular tags for a given user (or the currently logged in user).
   *
   * This method does not require authentication.
   *
   * @param userId NSID of the user to fetch the tag list for. If this argument is not
   *               specified, the currently logged in user (if any) is assumed. Optional.
   * @param count  number of popular tags to return. defaults to 10 when this argument is not present. Optional.
   * @return popular tags for the given user.
   * @throws JinxException if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.tags.getListUserPopular.html">flickr.tags.getListUserPopular</a>
   */
  public TagsForUser getListUserPopular(String userId, Integer count) throws JinxException {
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.tags.getListUserPopular");
    if (!JinxUtils.isNullOrEmpty(userId)) {
      params.put("user_id", userId);
    }
    if (count != null) {
      params.put("count", count.toString());
    }
    return jinx.flickrGet(params, TagsForUser.class);
  }

  /**
   * Get the raw versions of a given tag (or all tags) for the currently logged-in user.
   *
   * This method does not require authentication.
   *
   * @param tag tag you want to retrieve all raw versions for. Optional.
   * @return raw versions of a tag for the given tag, or all tags for the currently logged in user.
   * @throws JinxException if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.tags.getListUserRaw.html">flickr.tags.getListUserRaw</a>
   */
  public RawTagsForUser getListUserRaw(String tag) throws JinxException {
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.tags.getListUserRaw");
    if (tag != null) {
      params.put("tag", tag);
    }
    return jinx.flickrGet(params, RawTagsForUser.class);
  }

  /**
   * Returns a list of most frequently used tags for a user.
   *
   * This method requires authentication with 'read' permission.
   *
   * @return most frequently used tags for the calling user.
   * @throws JinxException if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.tags.getMostFrequentlyUsed.html">flickr.tags.getMostFrequentlyUsed</a>
   */
  public TagsForUser getMostFrequentlyUsed() throws JinxException {
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.tags.getMostFrequentlyUsed");
    return jinx.flickrGet(params, TagsForUser.class);
  }


  /**
   * Returns a list of tags 'related' to the given tag, based on clustered usage analysis.
   *
   * This method does not require authentication.
   *
   * @param tag tag to fetch related tags for. Required.
   * @return related tags.
   * @throws JinxException if required parameters are missing, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.tags.getRelated.html">flickr.tags.getRelated</a>
   */
  public RelatedTags getRelated(String tag) throws JinxException {
    JinxUtils.validateParams(tag);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.tags.getRelated");
    params.put("tag", tag);
    return jinx.flickrGet(params, RelatedTags.class);
  }
}
