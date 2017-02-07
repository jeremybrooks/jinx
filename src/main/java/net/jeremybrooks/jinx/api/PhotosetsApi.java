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
import net.jeremybrooks.jinx.response.Response;
import net.jeremybrooks.jinx.response.common.Context;
import net.jeremybrooks.jinx.response.photosets.PhotosetInfo;
import net.jeremybrooks.jinx.response.photosets.PhotosetList;
import net.jeremybrooks.jinx.response.photosets.PhotosetPhotos;

import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Provides access to the flickr.photosets API methods.
 *
 * @author Jeremy Brooks
 * @see <a href="https://www.flickr.com/services/api/">Flickr API documentation</a> for more details.
 */
public class PhotosetsApi {
  private Jinx jinx;

  public PhotosetsApi(Jinx jinx) {
    this.jinx = jinx;
  }

  /**
   * Add a photo to the end of an existing photoset.
   * <br>
   * This method requires authentication with 'write' permission.
   * <br>
   * This method has no specific response - It returns an empty success response if it completes without error.
   *
   * @param photosetId id of the photoset to add a photo to. Required.
   * @param photoId    id of the photo to add to the set. Required.
   * @return an empty success response if it completes without error.
   * @throws JinxException if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.photosets.addPhoto.html">flickr.photosets.addPhoto</a>
   */
  public Response addPhoto(String photosetId, String photoId) throws JinxException {
    JinxUtils.validateParams(photosetId, photoId);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.photosets.addPhoto");
    params.put("photoset_id", photosetId);
    params.put("photo_id", photoId);
    return this.jinx.flickrPost(params, Response.class);
  }


  /**
   * Create a new photoset for the calling user.
   * Authentication
   * <br>
   * This method requires authentication with 'write' permission.
   * <br>
   * New photosets are automatically put first in the photoset ordering for the user.
   * Use orderSets if you don't want the new set to appear first on the user's photoset list.
   *
   * @param title          title for the photoset. Required.
   * @param description    description of the photoset. Optional.
   * @param primaryPhotoId id of the photo to represent this set. The photo must belong to the calling user. Required.
   * @return instances of {@link net.jeremybrooks.jinx.response.photosets.PhotosetInfo} with only the photosetId and url
   * fields set.
   * @throws JinxException if required parameters are null, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.photosets.create.html">flickr.photosets.create</a>
   */
  public PhotosetInfo create(String title, String description, String primaryPhotoId) throws JinxException {
    JinxUtils.validateParams(title, primaryPhotoId);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.photosets.create");
    params.put("title", title);
    params.put("primary_photo_id", primaryPhotoId);
    if (description != null) {
      params.put("description", description);
    }

    return jinx.flickrPost(params, PhotosetInfo.class);
  }


  /**
   * Delete a photoset.
   * <br>
   * This method requires authentication with 'write' permission.
   * <br>
   * This method has no specific response - It returns an empty success response if it completes without error.
   *
   * @param photosetId id of the photoset to delete. It must be owned by the calling user. Required.
   * @return success response if it completes without error.
   * @throws JinxException if required parameters are null, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.photosets.delete.html">flickr.photosets.delete</a>
   */
  public Response delete(String photosetId) throws JinxException {
    JinxUtils.validateParams(photosetId);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.photosets.delete");
    params.put("photoset_id", photosetId);
    return jinx.flickrPost(params, Response.class);
  }

  /**
   * Modify the meta-data for a photoset.
   * <br>
   * This method requires authentication with 'write' permission.
   * <br>
   * This method has no specific response - It returns an empty success response if it completes without error.
   *
   * @param photosetId  id of the photoset to modify. Required.
   * @param title       new title for the photoset. Required.
   * @param description description of the photoset. May contain limited html.
   * @return success response if it completes without error.
   * @throws JinxException if required parameters are null, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.photosets.editMeta.html">flickr.photosets.editMeta</a>
   */
  public Response editMeta(String photosetId, String title, String description) throws JinxException {
    JinxUtils.validateParams(photosetId, title);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.photosets.editMeta");
    params.put("photoset_id", photosetId);
    params.put("title", title);
    if (description != null) {
      params.put("description", description);
    }
    return jinx.flickrPost(params, Response.class);
  }

  /**
   * Modify the photos in a photoset. Use this method to add, remove and re-order photos.
   * <br>
   * This method requires authentication with 'write' permission.
   * <br>
   * This method has no specific response - It returns an empty success response if it completes without error.
   *
   * @param photosetId     id of the photoset to modify. The photoset must belong to the calling user. Required.
   * @param primaryPhotoId id of the photo to use as the 'primary' photo for the set. This id must also be passed along in photo_ids list argument. Required.
   * @param photoIds       list of photo ids to include in the set. They will appear in the set in the order sent. This list must contain the primary photo id. All photos must belong to the owner of the set. This list of photos replaces the existing list. Call addPhoto to append a photo to a set. Required.
   * @return success response if it completes without error.
   * @throws JinxException if required parameters are null, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.photosets.editPhotos.html">flickr.photosets.editPhotos</a>
   */
  public Response editPhotos(String photosetId, String primaryPhotoId, List<String> photoIds) throws JinxException {
    JinxUtils.validateParams(photosetId, primaryPhotoId, photoIds);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.photosets.editPhotos");
    params.put("photoset_id", photosetId);
    params.put("primary_photo_id", primaryPhotoId);
    StringBuilder builder = new StringBuilder();
    for (String id : photoIds) {
      builder.append(id).append(',');
    }
    builder.deleteCharAt(builder.length() - 1);
    params.put("photo_ids", builder.toString());

    return jinx.flickrPost(params, Response.class);
  }


  /**
   * Returns next and previous photos for a photo in a set.
   * <br>
   * This method does not require authentication.
   * <br>
   * photo_id (Required)
   * The id of the photo to fetch the context for.
   * photoset_id (Required)
   * The id of the photoset for which to fetch the photo's context.
   *
   * @param photoId    id of the photo to fetch the context for. Required.
   * @param photosetId id of the photoset for which to fetch the photo's context. Required.
   * @return {@link net.jeremybrooks.jinx.response.common.Context} object with the photo context.
   * @throws JinxException if required parameters are null, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.photosets.getContext.html">flickr.photosets.getContext</a>
   */
  public Context getContext(String photoId, String photosetId) throws JinxException {
    JinxUtils.validateParams(photoId, photosetId);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.photosets.getContext");
    params.put("photo_id", photoId);
    params.put("photoset_id", photosetId);
    return jinx.flickrGet(params, Context.class);
  }


  /**
   * Gets information about a photoset.
   * This method does not require authentication.
   *
   * @param photosetId the id of the photoset to fetch information for.
   * @return instance of {@link net.jeremybrooks.jinx.response.photosets.PhotosetInfo} with data returned by
   * the getInfo method. The url is not returned, and some of the counts are not returned by this method.
   * @throws JinxException if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.photosets.getInfo.html">flickr.photosets.getInfo</a>
   */
  public PhotosetInfo getInfo(String photosetId) throws JinxException {
    JinxUtils.validateParams(photosetId);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.photosets.getInfo");
    params.put("photoset_id", photosetId);
    return jinx.flickrGet(params, PhotosetInfo.class);
  }

  /**
   * Returns the photosets belonging to the specified user.
   * <br>
   * This method does not require authentication.
   *
   * @param userId      Optional. The NSID of the user to get a photoset list for. If none is specified, the calling user is assumed.
   * @param page        Optional. The page of results to get. Currently, if this is omitted, all sets are returned, but this behaviour may change in future. A value of zero will cause the parameter to be ignored.
   * @param perPage     Optional. The number of sets to get per page. If paging is enabled, the maximum number of sets per page is 500. A value of zero will cause the parameter to be ignored.
   * @param photoExtras Optional. A list of extra information to fetch for the primary photo.
   * @return list of photosets belonging to the specified user.
   * @throws JinxException if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.photosets.getList.html">flickr.photosets.getList</a>
   */
  public PhotosetList getList(String userId, int page, int perPage, EnumSet<JinxConstants.PhotoExtras> photoExtras) throws JinxException {
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.photosets.getList");
    if (!JinxUtils.isNullOrEmpty(userId)) {
      params.put("user_id", userId);
    }
    if (page > 0) {
      params.put("page", Integer.toString(page));
    }
    if (perPage > 0) {
      params.put("per_page", Integer.toString(perPage));
    }
    if (!JinxUtils.isNullOrEmpty(photoExtras)) {
      params.put("primary_photo_extras", JinxUtils.buildCommaDelimitedList(photoExtras));
    }
    return jinx.flickrGet(params, PhotosetList.class);
  }


  /**
   * Get the list of photos in a set.
   * <br>
   * This method does not require authentication.
   *
   * @param photosetId    id of the photoset to return photos for. Required.
   * @param photoExtras   Optional. A list of extra information to fetch for the primary photo. Currently supported fields are:
   *                      license, date_upload, date_taken, owner_name, icon_server, original_format, last_update,
   *                      geo, tags, machine_tags, o_dims, views, media, path_alias, url_sq, url_t, url_s, url_m, url_o
   * @param privacyFilter Optional. Return photos only matching a certain privacy level. This only applies when making an authenticated call to view a photoset you own.
   * @param perPage       Optional. Number of photos to return per page. If this argument is zero, it defaults to 500. The maximum allowed value is 500.
   * @param page          Optional. The page of results to return. If this argument is zero, it defaults to 1.
   * @param mediaType     Optional. Filter results by media type.
   * @return object containing some basic photoset metadata information, along with a list of photos in the photoset.
   * @throws JinxException if required parameters are null or empty, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.photosets.getPhotos.html">flickr.photosets.getPhotos</a>
   */
  public PhotosetPhotos getPhotos(String photosetId, EnumSet<JinxConstants.PhotoExtras> photoExtras, JinxConstants.PrivacyFilter privacyFilter,
                                  int perPage, int page, JinxConstants.MediaType mediaType) throws JinxException {
    JinxUtils.validateParams(photosetId);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.photosets.getPhotos");
    params.put("photoset_id", photosetId);
    if (!JinxUtils.isNullOrEmpty(photoExtras)) {
      params.put("extras", JinxUtils.buildCommaDelimitedList(photoExtras));
    }
    if (privacyFilter != null) {
      params.put("privacy_filter", Integer.toString(JinxUtils.privacyFilterToFlickrPrivacyFilterId(privacyFilter)));
    }
    if (perPage > 0) {
      params.put("per_page", Integer.toString(perPage));
    }
    if (page > 0) {
      params.put("page", Integer.toString(page));
    }
    if (mediaType != null) {
      params.put("media", mediaType.toString());
    }

    return jinx.flickrGet(params, PhotosetPhotos.class);
  }


  /**
   * Set the order of photosets for the calling user.
   * <br>
   * This method requires authentication with 'write' permission.
   * <br>
   * Note: This method requires an HTTP POST request.
   *
   * @param photosetIds a list containing photoset IDs, ordered with the set to show first, first in the list. Any set IDs
   *                    not given in the list will be set to appear at the end of the list, ordered by their IDs.
   * @return an empty success response if it completes without error.
   * @throws JinxException if the list of photoset id's is null or empty, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.photosets.orderSets.html">flickr.photosets.orderSets</a>
   */
  public Response orderSets(List<String> photosetIds) throws JinxException {
    JinxUtils.validateParams(photosetIds);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.photosets.orderSets");
    params.put("photoset_ids", JinxUtils.buildCommaDelimitedList(photosetIds));
    return jinx.flickrPost(params, Response.class);
  }


  /**
   * Remove a photo from a photoset.
   * <br>
   * This method requires authentication with 'write' permission.
   * <br>
   * Note: This method requires an HTTP POST request.
   *
   * @param photosetId id of the photoset to remove a photo from.
   * @param photoId    id of the photo to remove from the set.
   * @return an empty success response if it completes without error.
   * @throws JinxException if any parameter is null or empty, or if there are other errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.photosets.removePhoto.html">flickr.photosets.removePhoto</a>
   */
  public Response removePhoto(String photosetId, String photoId) throws JinxException {
    JinxUtils.validateParams(photosetId, photoId);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.photosets.removePhoto");
    params.put("photoset_id", photosetId);
    params.put("photo_id", photoId);
    return jinx.flickrPost(params, Response.class);
  }


  /**
   * Remove multiple photos from a photoset.
   * <br>
   * This method requires authentication with 'write' permission.
   * <br>
   * Note: This method requires an HTTP POST request.
   *
   * @param photosetId id of the photoset to remove a photo from.
   * @param photoIds   list of photo id's to remove from the photoset.
   * @return an empty success response if it completes without error.
   * @throws JinxException if any parameter is null or empty, or if there are other errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.photosets.removePhotos.html">flickr.photosets.removePhotos</a>
   */
  public Response removePhotos(String photosetId, List<String> photoIds) throws JinxException {
    JinxUtils.validateParams(photosetId, photoIds);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.photosets.removePhotos");
    params.put("photoset_id", photosetId);
    params.put("photo_ids", JinxUtils.buildCommaDelimitedList(photoIds));
    return jinx.flickrPost(params, Response.class);
  }


  /**
   * Reorder photos in an existing photoset.
   * This method requires authentication with 'write' permission.
   * Note: This method requires an HTTP POST request.
   *
   * @param photosetId id of the photoset to remove a photo from.
   * @param photoIds   ordered list of photo id's. Photos that are not in the list will keep their original order.
   * @return an empty success response if it completes without error.
   * @throws JinxException if any parameter is null or empty, or if there are other errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.photosets.reorderPhotos.html">flickr.photosets.reorderPhotos</a>
   */
  public Response reorderPhotos(String photosetId, List<String> photoIds) throws JinxException {
    JinxUtils.validateParams(photosetId, photoIds);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.photosets.reorderPhotos");
    params.put("photoset_id", photosetId);
    params.put("photo_ids", JinxUtils.buildCommaDelimitedList(photoIds));
    return jinx.flickrPost(params, Response.class);
  }


  /**
   * Set photoset primary photo.
   * <br>
   * This method requires authentication with 'write' permission.
   * <br>
   * Note: This method requires an HTTP POST request.
   * <br>
   * If the photo is not already in the set, nothing will happen, and the method will report success.
   *
   * @param photosetId id of the photoset to set primary photo of.
   * @param photoId    id of the photo set set as primary.
   * @return an empty success response if it completes without error.
   * @throws JinxException if any parameter is null or empty, or if there are other errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.photosets.setPrimaryPhoto.html">flickr.photosets.setPrimaryPhoto</a>
   */
  public Response setPrimaryPhoto(String photosetId, String photoId) throws JinxException {
    JinxUtils.validateParams(photosetId, photoId);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.photosets.setPrimaryPhoto");
    params.put("photoset_id", photosetId);
    params.put("photo_id", photoId);
    return jinx.flickrPost(params, Response.class);
  }
}
