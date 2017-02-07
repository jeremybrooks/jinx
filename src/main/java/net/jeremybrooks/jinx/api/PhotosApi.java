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
import net.jeremybrooks.jinx.response.photos.AddTags;
import net.jeremybrooks.jinx.response.photos.AllContexts;
import net.jeremybrooks.jinx.response.photos.ExifData;
import net.jeremybrooks.jinx.response.photos.Favorites;
import net.jeremybrooks.jinx.response.photos.PermsSetResponse;
import net.jeremybrooks.jinx.response.photos.PhotoInfo;
import net.jeremybrooks.jinx.response.photos.PhotoPerms;
import net.jeremybrooks.jinx.response.photos.PhotoSizes;
import net.jeremybrooks.jinx.response.photos.Photocounts;
import net.jeremybrooks.jinx.response.photos.Photos;
import net.jeremybrooks.jinx.response.photos.SearchParameters;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Provides access to the flickr.photos API methods.
 *
 * @author Jeremy Brooks
 * @see <a href="https://www.flickr.com/services/api/">Flickr API documentation</a> for more details.
 */
public class PhotosApi {

  private Jinx jinx;

  public PhotosApi(Jinx jinx) {
    this.jinx = jinx;
  }


  /**
   * Add tags to a photo.
   * <br>
   * This method requires authentication with 'write' permission.
   * <br>
   * Each tag in the list will be treated as a single tag. This method will automatically add quotation marks as
   * needed so that multi-word tags will be treated correctly by Flickr. This method can also be used to add
   * machine tags.
   *
   * @param photoId the id of the photo to add tags to.
   * @param tags    tags to add to the photo.
   * @return response with the result of the operation.
   * @throws JinxException if parameters are null or empty, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.photos.addTags.html">flickr.photos.addTags</a>
   */
  public AddTags addTags(String photoId, List<String> tags) throws JinxException {
    JinxUtils.validateParams(photoId, tags);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.photos.addTags");
    params.put("photo_id", photoId);
    StringBuilder sb = new StringBuilder();
    for (String tag : tags) {
      if (tag.contains(" ")) {
        sb.append('"').append(tag).append('"');
      } else {
        sb.append(tag);
      }
      sb.append(' ');
    }
    sb.deleteCharAt(sb.length() - 1);
    params.put("tags", sb.toString());
    return this.jinx.flickrPost(params, AddTags.class);
  }


  /**
   * Delete a photo from Flickr.
   * <br>
   * This method requires authentication with 'delete' permission.
   *
   * @param photoId id of the photo to delete.
   * @return response object with the results of the requested operation.
   * @throws JinxException if the parameter is null or empty, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.photos.delete.html">flickr.photos.delete</a>
   */
  public Response delete(String photoId) throws JinxException {
    JinxUtils.validateParams(photoId);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.photos.delete");
    params.put("photo_id", photoId);
    return jinx.flickrPost(params, Response.class);
  }


  /**
   * Returns all visible sets and pools the photo belongs to.
   * <br>
   * This method does not require authentication.
   *
   * @param photoId photo id to find contexts for.
   * @return object with a list of all sets and pools the photo is in.
   * @throws JinxException if the photo id is null or empty, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.photos.getAllContexts.html">flickr.photos.getAllContexts</a>
   */
  public AllContexts getAllContexts(String photoId) throws JinxException {
    JinxUtils.validateParams(photoId);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.photos.getAllContexts");
    params.put("photo_id", photoId);
    return jinx.flickrGet(params, AllContexts.class);
  }


  /**
   * Fetch a list of recent photos from the calling users' contacts.
   * <br>
   * This method requires authentication with 'read' permission.
   *
   * @param count       Number of photos to return. If zero, defaults to 10, maximum 50. This is only used if singlePhoto is not true.
   * @param justFriends if true, only show photos from friends and family (excluding regular contacts).
   * @param singlePhoto if true, only fetch one photo (the latest) per contact, instead of all photos in chronological order.
   * @param includeSelf if true, include photos from the calling user.
   * @param extras      set of extra information to fetch for each returned record.
   * @return object containing data about the photos returned, and a list of photos.
   * @throws JinxException if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.photos.getContactsPhotos.html">flickr.photos.getContactsPhotos</a>
   */
  public Photos getContactsPhotos(int count, boolean justFriends, boolean singlePhoto, boolean includeSelf,
                                  EnumSet<JinxConstants.PhotoExtras> extras) throws JinxException {
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.photos.getContactsPhotos");
    if (count > 0) {
      params.put("count", Integer.toString(count));
    }
    if (justFriends) {
      params.put("just_friends", "1");
    }
    if (singlePhoto) {
      params.put("single_photo", "1");
    }
    if (includeSelf) {
      params.put("include_self", "1");
    }
    if (!JinxUtils.isNullOrEmpty(extras)) {
      params.put("extras", JinxUtils.buildCommaDelimitedList(extras));
    }
    return jinx.flickrGet(params, Photos.class);
  }


  /**
   * Fetch a list of recent public photos from a users' contacts.
   * <br>
   * This method does not require authentication.
   * <br>
   * Arguments
   *
   * @param userId      Required. The NSID of the user to fetch photos for.
   * @param count       Number of photos to return. If zero, defaults to 10, maximum 50. This is only used if singlePhoto is not true.
   * @param justFriends if true, only show photos from friends and family (excluding regular contacts).
   * @param singlePhoto if true, only fetch one photo (the latest) per contact, instead of all photos in chronological order.
   * @param includeSelf if true, include photos from the user specified by user_id.
   * @param extras      set of extra information to fetch for each returned record.
   * @return object containing data about the photos returned, and a list of photos.
   * @throws JinxException if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.photos.getContactsPublicPhotos.html">flickr.photos.getContactsPublicPhotos</a>
   */
  public Photos getContactsPublicPhotos(String userId, int count, boolean justFriends,
                                        boolean singlePhoto, boolean includeSelf,
                                        EnumSet<JinxConstants.PhotoExtras> extras)
      throws JinxException {
    JinxUtils.validateParams(userId);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.photos.getContactsPublicPhotos");
    params.put("user_id", userId);
    if (count > 0) {
      params.put("count", Integer.toString(count));
    }
    if (justFriends) {
      params.put("just_friends", "1");
    }
    if (singlePhoto) {
      params.put("single_photo", "1");
    }
    if (includeSelf) {
      params.put("include_self", "1");
    }
    if (!JinxUtils.isNullOrEmpty(extras)) {
      params.put("extras", JinxUtils.buildCommaDelimitedList(extras));
    }
    return jinx.flickrGet(params, Photos.class);
  }

  /**
   * Returns next and previous photos for a photo in a photostream.
   * <br>
   * This method does not require authentication.
   *
   * @param photoId Required. The id of the photo to fetch the context for.
   * @return object with the context of the photo.
   * @throws JinxException if the photo id is null or empty, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.photos.getContext.html">flickr.photos.getContext</a>
   */
  public Context getContext(String photoId) throws JinxException {
    JinxUtils.validateParams(photoId);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.photos.getContext");
    params.put("photo_id", photoId);
    return jinx.flickrGet(params, Context.class);
  }

  /**
   * Gets a list of photo counts for the given date ranges for the calling user.
   * <br>
   * This method requires authentication with 'read' permission.
   * <br>
   * You must provide either dates or takenDates parameters. Flickr may not return correct results if you specify both.
   *
   * @param dates      a list of dates denoting the periods to return counts for. They should be specified smallest first.
   * @param takenDates a list of dates denoting the periods to return counts for. They should be specified smallest first.
   * @return object containing a list of counts for the specified dates.
   * @throws JinxException if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.photos.getCounts.html">flickr.photos.getCounts</a>
   */
  public Photocounts getCounts(List<Date> dates, List<Date> takenDates) throws JinxException {
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.photos.getCounts");
    if (!JinxUtils.isNullOrEmpty(dates)) {
      Collections.sort(dates);
      List<String> formattedDates = new ArrayList<String>();
      for (Date date : dates) {
        formattedDates.add(JinxUtils.formatDateAsUnixTimestamp(date));
      }
      params.put("dates", JinxUtils.buildCommaDelimitedList(formattedDates));
    }
    if (!JinxUtils.isNullOrEmpty(takenDates)) {
      List<String> formattedDates = new ArrayList<String>();
      for (Date date : takenDates) {
        formattedDates.add(JinxUtils.formatDateAsMySqlTimestamp(date));
      }
      params.put("taken_dates", JinxUtils.buildCommaDelimitedList(formattedDates));
    }
    return jinx.flickrGet(params, Photocounts.class);
  }


  /**
   * <br>
   * Retrieves a list of EXIF/TIFF/GPS tags for a given photo. The calling user must have permission to view the photo.
   * <br>
   * This method does not require authentication.
   *
   * @param photoId Required. The id of the photo to fetch information for.
   * @param secret  Optional. The secret for the photo. If the correct secret is passed then permissions checking is skipped.
   *                This enables the 'sharing' of individual photos by passing around the id and secret.
   * @return object containing limited information about the photo, and a list of Exif data.
   * @throws JinxException if there are ay errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.photos.getExif.html">flickr.photos.getExif</a>
   */
  public ExifData getExif(String photoId, String secret) throws JinxException {
    JinxUtils.validateParams(photoId);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.photos.getExif");
    params.put("photo_id", photoId);
    if (!JinxUtils.isNullOrEmpty(secret)) {
      params.put("secret", secret);
    }
    return jinx.flickrGet(params, ExifData.class);
  }


  /**
   * Returns the list of people who have favorited a given photo.
   * <br>
   * This method does not require authentication.
   *
   * @param photoId Required. The id of the photo to fetch information for.
   * @param page    page of results to return. If this argument is zero, it defaults to 1.
   * @param perPage number of people to return per page. If this argument is zero, it defaults to 10. The maximum allowed value is 50.
   * @return object containing limited information about the photo, and a list of people who have favorited the photo.
   * @throws JinxException if required parameters are null or empty, or if there are errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.photos.getFavorites.html">flickr.photos.getFavorites</a>
   */
  public Favorites getFavorites(String photoId, int page, int perPage) throws JinxException {
    JinxUtils.validateParams(photoId);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.photos.getFavorites");
    params.put("photo_id", photoId);
    if (page > 0) {
      params.put("page", Integer.toString(page));
    }
    if (perPage > 0) {
      params.put("per_page", Integer.toString(perPage));
    }
    return jinx.flickrGet(params, Favorites.class);
  }


  /**
   * Get information about a photo. The calling user must have permission to view the photo.
   * <br>
   * This method does not require authentication.
   *
   * @param photoId Required. The id of the photo to get information for.
   * @param secret  Optional. The secret for the photo. If the correct secret is passed then permissions checking is skipped.
   *                This enables the 'sharing' of individual photos by passing around the id and secret.
   * @return object with available information for the photo.
   * @throws JinxException if required parameters are null or empty, or if there are errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.photos.getInfo.html">flickr.photos.getInfo</a>
   */
  public PhotoInfo getInfo(String photoId, String secret) throws JinxException {
    JinxUtils.validateParams(photoId);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.photos.getInfo");
    params.put("photo_id", photoId);
    if (!JinxUtils.isNullOrEmpty(secret)) {
      params.put("secret", secret);
    }
    return jinx.flickrGet(params, PhotoInfo.class);
    // sometimes Flickr sends back responses with "machine_tag":false rather than "machine_tag":0
    // so we need to work around this by fixing up the response
    // if the response is not fixed up, Gson cannot parse it
//        String json = jinx.callFlickr(params, JinxConstants.Method.GET, true);
//        json = json.replace(":false", ":0");
//        return jinx.jsonToClass(json, PhotoInfo.class);
  }


  /**
   * Returns a list of your photos that are not part of any sets.
   * <br>
   * This method requires authentication with 'read' permission.
   *
   * @param minUploadDate Optional. Minimum upload date. Photos with an upload date greater than or equal to this value will be returned.
   * @param maxUploadDate Optional. Maximum upload date. Photos with an upload date less than or equal to this value will be returned.
   * @param minTakenDate  Optional. Minimum taken date. Photos with an taken date greater than or equal to this value will be returned.
   * @param maxTakenDate  Optional. Maximum taken date. Photos with an taken date less than or equal to this value will be returned.
   * @param privacyFilter Optional. Return photos only matching a certain privacy level.
   * @param mediaType     Optional. Filter results by media type.
   * @param extras        Optional. Extra information to fetch for each returned record.
   * @param perPage       Optional. Number of photos to return per page. If this argument is zero, it defaults to 100. The maximum allowed value is 500.
   * @param page          Optional. The page of results to return. If this argument is zero, it defaults to 1.
   * @return photos object.
   * @throws JinxException if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.photos.getNotInSet.html">flickr.photos.getNotInSet</a>
   */
  public Photos getNotInSet(Date minUploadDate, Date maxUploadDate, Date minTakenDate, Date maxTakenDate,
                            JinxConstants.PrivacyFilter privacyFilter,
                            JinxConstants.MediaType mediaType, EnumSet<JinxConstants.PhotoExtras> extras,
                            int perPage, int page) throws JinxException {
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.photos.getNotInSet");
    if (minUploadDate != null) {
      params.put("min_upload_date", JinxUtils.formatDateAsUnixTimestamp(minUploadDate));
    }
    if (maxUploadDate != null) {
      params.put("max_upload_date", JinxUtils.formatDateAsUnixTimestamp(maxUploadDate));
    }
    if (minTakenDate != null) {
      params.put("min_taken_date", JinxUtils.formatDateAsUnixTimestamp(minTakenDate));
    }
    if (maxTakenDate != null) {
      params.put("max_taken_date", JinxUtils.formatDateAsUnixTimestamp(maxTakenDate));
    }
    if (privacyFilter != null) {
      params.put("privacy_filter", Integer.toString(JinxUtils.privacyFilterToFlickrPrivacyFilterId(privacyFilter)));
    }
    if (mediaType != null) {
      params.put("media", mediaType.toString());
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
   * Get permissions for a photo.
   * <br>
   * This method requires authentication with 'read' permission.
   *
   * @param photoId Required. The id of the photo to fetch permissions for.
   * @return object with permissions for the photo.
   * @throws JinxException if the photo id is null or empty, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.photos.getPerms.html">flickr.photos.getPerms</a>
   */
  public PhotoPerms getPerms(String photoId) throws JinxException {
    JinxUtils.validateParams(photoId);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.photos.getPerms");
    params.put("photo_id", photoId);
    return jinx.flickrGet(params, PhotoPerms.class);
  }


  /**
   * Returns a list of the latest public photos uploaded to flickr.
   * <br>
   * This method does not require authentication.
   *
   * @param extras  Optional. Extra information to fetch for each returned record.
   * @param perPage Optional. Number of photos to return per page. If this argument is zero, it defaults to 100. The maximum allowed value is 500.
   * @param page    Optional. The page of results to return. If this argument is zero, it defaults to 1.
   * @return photos object.
   * @throws JinxException if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.photos.getRecent.html">flickr.photos.getRecent</a>
   */
  public Photos getRecent(EnumSet<JinxConstants.PhotoExtras> extras, int perPage, int page) throws JinxException {
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.photos.getRecent");
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
   * Returns the available sizes for a photo. The calling user must have permission to view the photo.
   * <br>
   * This method does not require authentication.
   *
   * @param photoId Required. The id of the photo to fetch permissions for.
   * @return object with available size information.
   * @throws JinxException if the photo id is null or empty, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.photos.getSizes.html">flickr.photos.getSizes</a>
   */
  public PhotoSizes getSizes(String photoId) throws JinxException {
    JinxUtils.validateParams(photoId);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.photos.getSizes");
    params.put("photo_id", photoId);
    return jinx.flickrGet(params, PhotoSizes.class);
  }


  /**
   * Returns a list of your photos with no tags.
   * <br>
   * This method requires authentication with 'read' permission.
   *
   * @param minUploadDate Optional. Minimum upload date. Photos with an upload date greater than or equal to this value will be returned.
   * @param maxUploadDate Optional. Maximum upload date. Photos with an upload date less than or equal to this value will be returned.
   * @param minTakenDate  Optional. Minimum taken date. Photos with an taken date greater than or equal to this value will be returned.
   * @param maxTakenDate  Optional. Maximum taken date. Photos with an taken date less than or equal to this value will be returned.
   * @param privacyFilter Optional. Return photos only matching a certain privacy level.
   * @param mediaType     Optional. Filter results by media type.
   * @param extras        Optional. Extra information to fetch for each returned record.
   * @param perPage       Optional. Number of photos to return per page. If this argument is zero, it defaults to 100. The maximum allowed value is 500.
   * @param page          Optional. The page of results to return. If this argument is zero, it defaults to 1.
   * @return photos object.
   * @throws JinxException if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.photos.getUntagged.html">flickr.photos.getUntagged</a>
   */
  public Photos getUntagged(Date minUploadDate, Date maxUploadDate, Date minTakenDate, Date maxTakenDate,
                            JinxConstants.PrivacyFilter privacyFilter,
                            JinxConstants.MediaType mediaType, EnumSet<JinxConstants.PhotoExtras> extras,
                            int perPage, int page) throws JinxException {
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.photos.getUntagged");
    if (minUploadDate != null) {
      params.put("min_upload_date", JinxUtils.formatDateAsUnixTimestamp(minUploadDate));
    }
    if (maxUploadDate != null) {
      params.put("max_upload_date", JinxUtils.formatDateAsUnixTimestamp(maxUploadDate));
    }
    if (minTakenDate != null) {
      params.put("min_taken_date", JinxUtils.formatDateAsUnixTimestamp(minTakenDate));
    }
    if (maxTakenDate != null) {
      params.put("max_taken_date", JinxUtils.formatDateAsUnixTimestamp(maxTakenDate));
    }
    if (privacyFilter != null) {
      params.put("privacy_filter", Integer.toString(JinxUtils.privacyFilterToFlickrPrivacyFilterId(privacyFilter)));
    }
    if (mediaType != null) {
      params.put("media", mediaType.toString());
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
   * Returns a list of your geo-tagged photos.
   * <br>
   * This method requires authentication with 'read' permission.
   *
   * @param minUploadDate Optional. Minimum upload date. Photos with an upload date greater than or equal to this value will be returned.
   * @param maxUploadDate Optional. Maximum upload date. Photos with an upload date less than or equal to this value will be returned.
   * @param minTakenDate  Optional. Minimum taken date. Photos with an taken date greater than or equal to this value will be returned.
   * @param maxTakenDate  Optional. Maximum taken date. Photos with an taken date less than or equal to this value will be returned.
   * @param privacyFilter Optional. Return photos only matching a certain privacy level.
   * @param sortOrder     Optional. The order in which to sort returned photos. If null, defaults to {@link net.jeremybrooks.jinx.JinxConstants.SortOrder#date_posted_desc}.
   * @param mediaType     Optional. Filter results by media type.
   * @param extras        Optional. Extra information to fetch for each returned record.
   * @param perPage       Optional. Number of photos to return per page. If this argument is zero, it defaults to 100. The maximum allowed value is 500.
   * @param page          Optional. The page of results to return. If this argument is zero, it defaults to 1.
   * @return photos object.
   * @throws JinxException if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.photos.getWithGeoData.html">flickr.photos.getWithGeoData</a>
   */
  public Photos getWithGeoData(Date minUploadDate, Date maxUploadDate, Date minTakenDate, Date maxTakenDate,
                               JinxConstants.PrivacyFilter privacyFilter, JinxConstants.SortOrder sortOrder,
                               JinxConstants.MediaType mediaType, EnumSet<JinxConstants.PhotoExtras> extras,
                               int perPage, int page) throws JinxException {
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.photos.getWithGeoData");
    if (minUploadDate != null) {
      params.put("min_upload_date", JinxUtils.formatDateAsUnixTimestamp(minUploadDate));
    }
    if (maxUploadDate != null) {
      params.put("max_upload_date", JinxUtils.formatDateAsUnixTimestamp(maxUploadDate));
    }
    if (minTakenDate != null) {
      params.put("min_taken_date", JinxUtils.formatDateAsUnixTimestamp(minTakenDate));
    }
    if (maxTakenDate != null) {
      params.put("max_taken_date", JinxUtils.formatDateAsUnixTimestamp(maxTakenDate));
    }
    if (privacyFilter != null) {
      params.put("privacy_filter", Integer.toString(JinxUtils.privacyFilterToFlickrPrivacyFilterId(privacyFilter)));
    }
    if (sortOrder != null) {
      params.put("sort", JinxUtils.sortOrderToString(sortOrder));
    }
    if (mediaType != null) {
      params.put("media", mediaType.toString());
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
   * Returns a list of your photos which haven't been geo-tagged.
   * <br>
   * This method requires authentication with 'read' permission.
   *
   * @param minUploadDate Optional. Minimum upload date. Photos with an upload date greater than or equal to this value will be returned.
   * @param maxUploadDate Optional. Maximum upload date. Photos with an upload date less than or equal to this value will be returned.
   * @param minTakenDate  Optional. Minimum taken date. Photos with an taken date greater than or equal to this value will be returned.
   * @param maxTakenDate  Optional. Maximum taken date. Photos with an taken date less than or equal to this value will be returned.
   * @param privacyFilter Optional. Return photos only matching a certain privacy level.
   * @param sortOrder     Optional. The order in which to sort returned photos. If null, defaults to {@link net.jeremybrooks.jinx.JinxConstants.SortOrder#date_posted_desc}.
   * @param mediaType     Optional. Filter results by media type.
   * @param extras        Optional. Extra information to fetch for each returned record.
   * @param perPage       Optional. Number of photos to return per page. If this argument is zero, it defaults to 100. The maximum allowed value is 500.
   * @param page          Optional. The page of results to return. If this argument is zero, it defaults to 1.
   * @return photos object.
   * @throws JinxException if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.photos.getWithoutGeoData.html">flickr.photos.getWithoutGeoData</a>
   */
  public Photos getWithoutGeoData(Date minUploadDate, Date maxUploadDate, Date minTakenDate, Date maxTakenDate,
                                  JinxConstants.PrivacyFilter privacyFilter, JinxConstants.SortOrder sortOrder,
                                  JinxConstants.MediaType mediaType, EnumSet<JinxConstants.PhotoExtras> extras,
                                  int perPage, int page) throws JinxException {
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.photos.getWithoutGeoData");
    if (minUploadDate != null) {
      params.put("min_upload_date", JinxUtils.formatDateAsUnixTimestamp(minUploadDate));
    }
    if (maxUploadDate != null) {
      params.put("max_upload_date", JinxUtils.formatDateAsUnixTimestamp(maxUploadDate));
    }
    if (minTakenDate != null) {
      params.put("min_taken_date", JinxUtils.formatDateAsUnixTimestamp(minTakenDate));
    }
    if (maxTakenDate != null) {
      params.put("max_taken_date", JinxUtils.formatDateAsUnixTimestamp(maxTakenDate));
    }
    if (privacyFilter != null) {
      params.put("privacy_filter", Integer.toString(JinxUtils.privacyFilterToFlickrPrivacyFilterId(privacyFilter)));
    }
    if (sortOrder != null) {
      params.put("sort", JinxUtils.sortOrderToString(sortOrder));
    }
    if (mediaType != null) {
      params.put("media", mediaType.toString());
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
   * Return a list of your photos that have been recently created or which have been recently modified.
   * <br>
   * Recently modified may mean that the photo's metadata (title, description, tags) may have been changed or a comment has been added (or just modified somehow :-)
   * <br>
   * This method requires authentication with 'read' permission.
   * <br>
   * Photos are sorted by their date updated timestamp, in descending order.
   *
   * @param minDate Required. The date from which modifications should be compared.
   * @param extras  Optional. Extra information to fetch for each returned record.
   * @param perPage Optional. Number of photos to return per page. If this argument is zero, it defaults to 100. The maximum allowed value is 500.
   * @param page    Optional. The page of results to return. If this argument is zero, it defaults to 1.
   * @return photos object.
   * @throws JinxException if required parameters are null or empty, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.photos.recentlyUpdated.html">flickr.photos.recentlyUpdated</a>
   */
  public Photos recentlyUpdated(Date minDate, Set<JinxConstants.PhotoExtras> extras, int perPage, int page) throws JinxException {
    JinxUtils.validateParams(minDate);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.photos.recentlyUpdated");
    params.put("min_date", JinxUtils.formatDateAsUnixTimestamp(minDate));
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
   * Remove a tag from a photo.
   * <br>
   * This method requires authentication with 'write' permission.
   * <br>
   * The tagId parameter must be the full tag id. Methods such as photos.getInfo will return the full tag id as the
   * "tagId" parameter; other methods, such as addTags, will return the full tag id in the "fullTagId" parameter. If
   * this method does not seem to work, make sure you are passing the correct tag id.
   *
   * @param tagId tag to remove from the photo. This parameter should contain the full tag id.
   * @return response object indicating the result of the requested operation.
   * @throws JinxException if the parameter is null or empty, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.photos.removeTag.html">flickr.photos.removeTag</a>
   */
  public Response removeTag(String tagId) throws JinxException {
    JinxUtils.validateParams(tagId);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.photos.removeTag");
    params.put("tag_id", tagId);
    return jinx.flickrPost(params, Response.class);
  }


  /**
   * Return a list of photos matching some criteria.
   * <br>
   * Only photos visible to the calling user will be returned. To return private or semi-private photos, the caller
   * must be authenticated with 'read' permissions, and have permission to view the photos.
   * Unauthenticated calls will only return public photos.
   * <br>
   * This method does not require authentication.
   * <br>
   * The search parameters are defined by an instance of {@link net.jeremybrooks.jinx.response.photos.SearchParameters}
   * For details on each parameter, see the documentation of the Search Parameters class, or the documentation on the
   * Flickr API site.
   * <br>
   * Generally, if a value in the search parameters object is null or zero, it is ignored and is not passed on to the
   * Flickr API.
   *
   * @param searchParameters Required. Object defining the parameters for the search.
   * @return photos matching the search parameters.
   * @throws JinxException if required parameters are null or empty, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.photos.search.html">flickr.photos.search</a>
   */
  public Photos search(SearchParameters searchParameters) throws JinxException {
    JinxUtils.validateParams(searchParameters);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.photos.search");
    if (!JinxUtils.isNullOrEmpty(searchParameters.getUserId())) {
      params.put("user_id", searchParameters.getUserId());
    }

    if (!JinxUtils.isNullOrEmpty(searchParameters.getTags())) {
      params.put("tags", JinxUtils.buildCommaDelimitedList(JinxUtils.normalizeTagsForSearch(searchParameters.getTags())));
      if (searchParameters.getTagMode() != null) {
        params.put("tag_mode", searchParameters.getTagMode().toString());
      }
    }

    if (!JinxUtils.isNullOrEmpty(searchParameters.getText())) {
      params.put("text", searchParameters.getText());
    }

    if (searchParameters.getMinUploadDate() != null) {
      params.put("min_upload_date", JinxUtils.formatDateAsUnixTimestamp(searchParameters.getMinUploadDate()));
    }
    if (searchParameters.getMaxUploadDate() != null) {
      params.put("max_upload_date", JinxUtils.formatDateAsUnixTimestamp(searchParameters.getMaxUploadDate()));
    }
    if (searchParameters.getMinTakenDate() != null) {
      params.put("min_taken_date", JinxUtils.formatDateAsUnixTimestamp(searchParameters.getMinTakenDate()));
    }
    if (searchParameters.getMaxTakenDate() != null) {
      params.put("max_taken_date", JinxUtils.formatDateAsUnixTimestamp(searchParameters.getMaxTakenDate()));
    }

    if (!JinxUtils.isNullOrEmpty(searchParameters.getLicenses())) {
      params.put("license", JinxUtils.buildCommaDelimitedList(searchParameters.getLicenses()));
    }

    if (searchParameters.getSort() != null) {
      params.put("sort", JinxUtils.sortOrderToString(searchParameters.getSort()));
    }

    if (searchParameters.getPrivacyFilter() != null) {
      params.put("privacy_filter", Integer.toString(JinxUtils.privacyFilterToFlickrPrivacyFilterId(searchParameters.getPrivacyFilter())));
    }

    if (searchParameters.getBoundingBox() != null) {
      params.put("bbox", searchParameters.getBoundingBox().toParameterString());
    }

    if (searchParameters.getAccuracy() != null) {
      params.put("accuracy", Integer.toString(searchParameters.getAccuracy()));
    }

    if (searchParameters.getSafetyLevel() != null) {
      params.put("safe_search", Integer.toString(JinxUtils.safetyLevelToFlickrSafteyLevelId(searchParameters.getSafetyLevel())));
    }

    if (searchParameters.getContentType() != null) {
      params.put("content_type", Integer.toString(JinxUtils.contentTypeToFlickrContentTypeId(searchParameters.getContentType())));
    }

    if (!JinxUtils.isNullOrEmpty(searchParameters.getMachineTags())) {
      params.put("machine_tags", JinxUtils.buildCommaDelimitedList(JinxUtils.normalizeMachineTagsForSearch(searchParameters.getMachineTags())));
      if (searchParameters.getMachineTagMode() != null) {
        params.put("machine_tag_mode", searchParameters.getMachineTagMode().toString());
      }
    }

    if (!JinxUtils.isNullOrEmpty(searchParameters.getGroupId())) {
      params.put("group_id", searchParameters.getGroupId());
    }

    if (searchParameters.getContacts() != null) {
      params.put("contacts", searchParameters.getContacts().toString());
    }

    if (!JinxUtils.isNullOrEmpty(searchParameters.getWoeId())) {
      params.put("woe_id", searchParameters.getWoeId());
    }

    if (!JinxUtils.isNullOrEmpty(searchParameters.getPlaceId())) {
      params.put("place_id", searchParameters.getPlaceId());
    }
    if (searchParameters.getMediaType() != null) {
      params.put("media", searchParameters.getMediaType().toString());
    }

    if (searchParameters.isHasGeo() != null) {
      params.put("has_geo", searchParameters.isHasGeo() ? "1" : "0");
    }

    if (searchParameters.getGeoContext() != null) {
      params.put("geo_context", Integer.toString(JinxUtils.geoContextToFlickrContextId(searchParameters.getGeoContext())));
    }

    if (searchParameters.getLatitude() != null) {
      params.put("lat", Float.toString(searchParameters.getLatitude()));
    }

    if (searchParameters.getLongitude() != null) {
      params.put("lon", Float.toString(searchParameters.getLongitude()));
    }

    if (searchParameters.getRadius() != null) {
      params.put("radius", Float.toString(searchParameters.getRadius()));
    }

    if (searchParameters.getRadiusUnits() != null) {
      params.put("radius_units", searchParameters.getRadiusUnits().toString());
    }

    if (searchParameters.isCommons() != null) {
      params.put("is_commons", searchParameters.isCommons() ? "1" : "0");
    }

    if (searchParameters.isInGallery() != null) {
      params.put("in_gallery", searchParameters.isInGallery() ? "1" : "0");
    }

    if (searchParameters.isGetty() != null) {
      params.put("is_getty", searchParameters.isGetty() ? "1" : "0");
    }

    if (!JinxUtils.isNullOrEmpty(searchParameters.getExtras())) {
      params.put("extras", JinxUtils.buildCommaDelimitedList(searchParameters.getExtras()));
    }

    if (searchParameters.getPerPage() > 0) {
      params.put("per_page", Integer.toString(searchParameters.getPerPage()));
    }

    if (searchParameters.getPage() > 0) {
      params.put("page", Integer.toString(searchParameters.getPage()));
    }

    if (!JinxUtils.isNullOrEmpty(searchParameters.getColorCodes())) {
      params.put("color_codes", JinxUtils.buildCommaDelimitedList(searchParameters.getColorCodes()));
    }

    if (!JinxUtils.isNullOrEmpty(searchParameters.getOrientations())) {
      // if there are four parameters, do nothing; that is the same as selecting all four
      if (searchParameters.getOrientations().size() != 4) {
        params.put("orientation", JinxUtils.buildCommaDelimitedList(searchParameters.getOrientations()));
      }
    }

    if (!JinxUtils.isNullOrEmpty(searchParameters.getPictureStyles())) {
      params.put("styles", JinxUtils.buildCommaDelimitedList(searchParameters.getPictureStyles()));
    }

    return jinx.flickrGet(params, Photos.class);
  }


  /**
   * Set the content type of a photo.
   * <br>
   * This method requires authentication with 'write' permission.
   *
   * @param photoId     Required. The id of the photo to set the content of.
   * @param contentType Required. Content type of the photo.
   * @return response object with the result of the requested operation.
   * @throws JinxException if required parameters are null or empty, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.photos.setContentType.html">flickr.photos.setContentType</a>
   */
  public Response setContentType(String photoId, JinxConstants.ContentType contentType) throws JinxException {
    JinxUtils.validateParams(photoId, contentType);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.photos.setContentType");
    params.put("photo_id", photoId);
    params.put("content_type", Integer.toString(JinxUtils.contentTypeToFlickrContentTypeId(contentType)));
    return jinx.flickrPost(params, Response.class);
  }

  /**
   * Set one or both of the dates for a photo.
   * <br>
   * This method requires authentication with 'write' permission.
   * <br>
   * One or both dates can be set. If no dates are set, nothing will happen.
   * <br>
   * Taken dates also have a 'granularity' - the accuracy to which we know the date to be true.
   * At present, the following granularities are used:
   * <ul>
   * <li>0 Y-m-d H:i:s</li>
   * <li>4 Y-m</li>
   * <li>6 Y</li>
   * <li>8 Circa...</li>
   * </ul>
   *
   * @param photoId              Required. The id of the photo to change dates for.
   * @param datePosted           Optional. date the photo was uploaded to flickr
   * @param dateTaken            Optional. date the photo was taken.
   * @param dateTakenGranularity Optional. granularity of the date the photo was taken.
   * @return response object with the result of the requested operation.
   * @throws JinxException if required parameters are null or empty, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.photos.setDates.html">flickr.photos.setDates</a>
   */
  public Response setDates(String photoId, Date datePosted, Date dateTaken, int dateTakenGranularity) throws JinxException {
    JinxUtils.validateParams(photoId);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.photos.setDates");
    params.put("photo_id", photoId);
    if (datePosted != null) {
      params.put("date_posted", JinxUtils.formatDateAsUnixTimestamp(datePosted));
    }
    if (dateTaken != null) {
      params.put("date_taken", JinxUtils.formatDateAsMySqlTimestamp(dateTaken));
    }
    if (dateTakenGranularity == 0 || dateTakenGranularity == 4 || dateTakenGranularity == 6 || dateTakenGranularity == 8) {
      params.put("date_taken_granularity", Integer.toString(dateTakenGranularity));
    }
    return jinx.flickrPost(params, Response.class);
  }

  /**
   * Set the meta information for a photo.
   * <br>
   * This method requires authentication with 'write' permission.
   *
   * @param photoId     Required. The id of the photo to set metadata for.
   * @param title       Required. Title for the photo.
   * @param description Required. Description for the photo.
   * @return response object with the result of the requested operation.
   * @throws JinxException if required parameters are null or empty, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.photos.setMeta.html">flickr.photos.setMeta</a>
   */
  public Response setMeta(String photoId, String title, String description) throws JinxException {
    JinxUtils.validateParams(photoId, title, description);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.photos.setMeta");
    params.put("photo_id", photoId);
    params.put("title", title);
    params.put("description", description);
    return jinx.flickrPost(params, Response.class);
  }

  /**
   * Set permissions for a photo.
   * <br>
   * This method requires authentication with 'write' permission.
   *
   * @param photoId     Required. The id of the photo to set permissions for.
   * @param isPublic    Required. True to set the photo to public, false to set it to private.
   * @param isFriend    Required. True to make the photo visible to friends when private, false to not.
   * @param isFamily    Required. True to make the photo visible to family when private, false to not.
   * @param permComment Required. Who can add comments to the photo and it's notes.
   * @param permAddMeta Required. Who can add notes and tags to the photo.
   * @return object with the results.
   * @throws JinxException if required parameters are null or empty, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.photos.setPerms.html">flickr.photos.setPerms</a>
   */
  public PermsSetResponse setPerms(String photoId, boolean isPublic, boolean isFriend, boolean isFamily,
                                   JinxConstants.Perms permComment, JinxConstants.Perms permAddMeta)
      throws JinxException {
    JinxUtils.validateParams(photoId, permComment, permAddMeta);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.photos.setPerms");
    params.put("photo_id", photoId);
    params.put("is_public", isPublic ? "1" : "0");
    params.put("is_friend", isFriend ? "1" : "0");
    params.put("is_family", isFamily ? "1" : "0");
    params.put("perm_comment", Integer.toString(JinxUtils.permsToFlickrPermsId(permComment)));
    params.put("perm_addmeta", Integer.toString(JinxUtils.permsToFlickrPermsId(permAddMeta)));
    return jinx.flickrPost(params, PermsSetResponse.class);
  }

  /**
   * Set the safety level of a photo.
   * <br>
   * This method requires authentication with 'write' permission.
   *
   * @param photoId     Required. The id of the photo to set the adultness of.
   * @param safetyLevel Optional. Safely level of the photo.
   * @param hidden      Whether or not to additionally hide the photo from public searches.
   * @return object with the result of the requested operation.
   * @throws JinxException if required parameters are null or empty, or if there are errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.photos.setSafetyLevel.html">flickr.photos.setSafetyLevel</a>
   */
  public Response setSafetyLevel(String photoId, JinxConstants.SafetyLevel safetyLevel, boolean hidden) throws JinxException {
    JinxUtils.validateParams(photoId);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.photos.setSafetyLevel");
    params.put("photo_id", photoId);
    if (safetyLevel != null) {
      params.put("safety_level", Integer.toString(JinxUtils.safetyLevelToFlickrSafteyLevelId(safetyLevel)));
    }
    params.put("hidden", hidden ? "1" : "0");
    return jinx.flickrPost(params, Response.class);
  }

  /**
   * Set the tags for a photo.
   * <br>
   * This method requires authentication with 'write' permission.
   * <br>
   * Each tag in the list will be treated as a single tag. This method will automatically add quotation marks as
   * needed so that multi-word tags will be treated correctly by Flickr. This method can also be used to add
   * machine tags.
   * <br>
   * This list of tags will replace the tags that currently exist on the photo.
   * <br>
   * If the tag list is null or empty, all tags will be removed from the photo.
   *
   * @param photoId id of the photo to set tags for.
   * @param tags    all tags for the photo, one tag per list element. If this parameter is null or empty, all tags
   *                will be removed from the photo.
   * @return response object with status of the requested operation.
   * @throws JinxException if photo id is null or empty, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.photos.setTags.html">flickr.photos.setTags</a>
   */
  public Response setTags(String photoId, List<String> tags) throws JinxException {
    JinxUtils.validateParams(photoId);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.photos.setTags");
    params.put("photo_id", photoId);
    if (tags == null || tags.size() == 0) {
      params.put("tags", "");
    } else {
      StringBuilder sb = new StringBuilder();
      for (String tag : tags) {
        if (tag.contains(" ")) {
          sb.append('"').append(tag).append('"');
        } else {
          sb.append(tag);
        }
        sb.append(' ');
      }
      sb.deleteCharAt(sb.length() - 1);
      params.put("tags", sb.toString());
    }
    return this.jinx.flickrPost(params, Response.class);
  }
}
