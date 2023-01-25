/*
 * Jinx is Copyright 2010-2023 by Jeremy Brooks and Contributors
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
import net.jeremybrooks.jinx.response.groups.Groups;
import net.jeremybrooks.jinx.response.people.Limits;
import net.jeremybrooks.jinx.response.people.Person;
import net.jeremybrooks.jinx.response.people.UploadStatus;
import net.jeremybrooks.jinx.response.photos.Photos;

import java.util.EnumSet;
import java.util.Map;
import java.util.TreeMap;

/**
 * Provides access to the flickr.people API methods.
 *
 * @author Jeremy Brooks
 * @see <a href="https://www.flickr.com/services/api/">Flickr API documentation</a> for more details.
 */
public class PeopleApi {
  private Jinx jinx;

  public PeopleApi(Jinx jinx) {
    this.jinx = jinx;
  }

  /**
   * Return a user's id and username, given their email address
   * <br>
   * This method does not require authentication.
   * <br>
   * This returns the Person object, but only the userId and username will be populated.
   *
   * @param email (Required) The email address of the user to find (may be primary or secondary).
   * @return object with information about the person.
   * @throws JinxException if required parameters are missing, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.people.findByEmail.html">flickr.people.findByEmail</a>
   */
  public Person findByEmail(String email) throws JinxException {
    JinxUtils.validateParams(email);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.people.findByEmail");
    params.put("find_email", email);
    return jinx.flickrGet(params, Person.class, false);
  }

  /**
   * Return a user's id and username, given their username.
   * <br>
   * This method does not require authentication.
   * <br>
   * This returns the Person object, but only the userId and username will be populated.
   *
   * @param username (Required) The username of the user to lookup.
   * @return object with information about the person.
   * @throws JinxException if required parameters are missing, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.people.findByUsername.html">flickr.people.findByUsername</a>
   */
  public Person findByUsername(String username) throws JinxException {
    JinxUtils.validateParams(username);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.people.findByUsername");
    params.put("username", username);
    return jinx.flickrGet(params, Person.class, false);
  }


  /**
   * Returns the list of groups a user is a member of.
   * <br>
   * This method requires authentication with 'read' permission.
   * <br>
   * Flickr does not return pagination information for this call. The returned object will not have any values set for
   * pagination, and the the getPage(), getPages(), getPerPage(), or getTotal() methods will return null.
   *
   * @param userId (Required) The user id of the user to fetch groups for.
   * @param extras extra information to fetch for each returned record.
   * @return object with information about the groups the user is a member of.
   * @throws JinxException if required parameters are missing, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.people.getGroups.html">flickr.people.getGroups</a>
   */
  public Groups getGroups(String userId, EnumSet<JinxConstants.GroupExtras> extras) throws JinxException {
    JinxUtils.validateParams(userId);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.people.getGroups");
    params.put("user_id", userId);
    if (!JinxUtils.isNullOrEmpty(extras)) {
      params.put("extras", JinxUtils.buildCommaDelimitedList(extras));
    }
    return jinx.flickrGet(params, Groups.class);
  }


  /**
   * Get information about a user.
   * <br>
   * This method does not require authentication.
   *
   * @param userId (Required) The user id of the user to fetch information about.
   * @param sign   if true, the request will be signed.
   * @return object with information about the user.
   * @throws JinxException if required parameters are missing, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.people.getInfo.html">flickr.people.getInfo</a>
   */
  public Person getInfo(String userId, boolean sign) throws JinxException {
    JinxUtils.validateParams(userId);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.people.getInfo");
    params.put("user_id", userId);
    return jinx.flickrGet(params, Person.class, sign);
  }

  /**
   * Returns the photo and video limits that apply to the calling user account.
   * <br>
   * This method requires authentication with 'read' permission.
   *
   * @return object with information about limits for the calling user account.
   * @throws JinxException if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.people.getLimits.html">flickr.people.getLimits</a>
   */
  public Limits getLimits() throws JinxException {
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.people.getLimits");
    return jinx.flickrGet(params, Limits.class);
  }

  /**
   * Return photos from the given user's photostream. Only photos visible to the calling user will be returned.
   * <br>
   * This method does not require authentication.
   *
   * @param userId        (Required) The userId of the user who's photos to return. A value of "me" will return the calling user's photos.
   * @param safetyLevel   (Optional) safe search level of photos to return. Unsigned calls can only see safe content.
   * @param minUploadDate (Optional) Minimum upload date. Photos with an upload date greater than or equal to this value will be returned. The date should be in the form of a unix timestamp.
   * @param maxUploadDate (Optional) Maximum upload date. Photos with an upload date less than or equal to this value will be returned. The date should be in the form of a unix timestamp.
   * @param minTakenDate  (Optional) Minimum taken date. Photos with an taken date greater than or equal to this value will be returned. The date should be in the form of a mysql datetime.
   * @param maxTakenDate  (Optional) Maximum taken date. Photos with an taken date less than or equal to this value will be returned. The date should be in the form of a mysql datetime.
   * @param contentType   (Optional) return photos matching a specific content type.
   * @param privacyFilter (Optional) Return photos only matching a certain privacy level. This only applies when making an authenticated call to view photos you own.
   * @param extras        (Optional) extra information to fetch for each returned photo.
   * @param perPage       Number of photos to return per page. If this argument is less than 1, it defaults to 100. The maximum allowed value is 500.
   * @param page          The page of results to return. If this argument is less than 1, it defaults to 1.
   * @param sign          if true, the request will be signed.
   * @return object with photos for the specified user.
   * @throws JinxException if required parameters are missing, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.people.getPhotos.html">flickr.people.getPhotos</a>
   */
  public Photos getPhotos(String userId, JinxConstants.SafetyLevel safetyLevel, String minUploadDate,
                          String maxUploadDate, String minTakenDate, String maxTakenDate, JinxConstants.ContentType contentType,
                          JinxConstants.PrivacyFilter privacyFilter, EnumSet<JinxConstants.PhotoExtras> extras,
                          int perPage, int page, boolean sign) throws JinxException {
    JinxUtils.validateParams(userId);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.people.getPhotos");
    params.put("user_id", userId);
    if (safetyLevel != null) {
      params.put("safe_search", Integer.toString(JinxUtils.safetyLevelToFlickrSafteyLevelId(safetyLevel)));
    }
    if (!JinxUtils.isNullOrEmpty(minUploadDate)) {
      params.put("min_upload_date", minUploadDate);
    }
    if (!JinxUtils.isNullOrEmpty(maxUploadDate)) {
      params.put("max_upload_date", maxUploadDate);
    }
    if (!JinxUtils.isNullOrEmpty(minTakenDate)) {
      params.put("min_taken_date", minTakenDate);
    }
    if (!JinxUtils.isNullOrEmpty(maxTakenDate)) {
      params.put("max_taken_date", maxTakenDate);
    }
    if (contentType != null) {
      params.put("content_type", Integer.toString(JinxUtils.contentTypeToFlickrContentTypeId(contentType)));
    }
    if (privacyFilter != null) {
      params.put("privacy_filter", Integer.toString(JinxUtils.privacyFilterToFlickrPrivacyFilterId(privacyFilter)));
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
    return jinx.flickrGet(params, Photos.class, sign);
  }


  /**
   * Returns a list of photos containing a particular Flickr member.
   * <br>
   * This method does not require authentication.
   *
   * @param userId  (Required) The userId of the user you want to find photos of. A value of "me" will search against photos of the calling user, for authenticated calls.
   * @param ownerId (Optional) A userId of a Flickr member. This will restrict the list of photos to those taken by that member.
   * @param extras  (Optional) extra information to return for each photo.
   * @param perPage Number of photos to return per page. If this argument is less than 1, it defaults to 100. The maximum allowed value is 500.
   * @param page    The page of results to return. If this argument is less than 1, it defaults to 1.
   * @param sign    if true, the request will be signed.
   * @return photos of the specified Flickr member.
   * @throws JinxException if required parameters are missing, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.people.getPhotosOf.html">flickr.people.getPhotosOf</a>
   */
  public Photos getPhotosOf(String userId, String ownerId, EnumSet<JinxConstants.PhotoExtras> extras,
                            int perPage, int page, boolean sign) throws JinxException {
    JinxUtils.validateParams(userId);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.people.getPhotosOf");
    params.put("user_id", userId);
    if (!JinxUtils.isNullOrEmpty(ownerId)) {
      params.put("owner_id", ownerId);
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
    return jinx.flickrGet(params, Photos.class, sign);
  }


  /**
   * Returns the list of public groups a user is a member of.
   * <br>
   * This method does not require authentication.
   *
   * @param userId         (Required) The userId of the user to fetch groups for.
   * @param invitationOnly (Optional) Include public groups that require an invitation or administrator approval to join.
   * @param sign           if true, the request will be signed.
   * @return object with the public groups the user is a member of.
   * @throws JinxException if required parameters are missing, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.people.getPublicGroups.html">flickr.people.getPublicGroups</a>
   */
  public Groups getPublicGroups(String userId, Boolean invitationOnly, boolean sign) throws JinxException {
    JinxUtils.validateParams(userId);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.people.getPublicGroups");
    params.put("user_id", userId);
    if (invitationOnly != null) {
      params.put("invitation_only", invitationOnly ? "1" : "0");
    }
    return jinx.flickrGet(params, Groups.class, sign);
  }

  /**
   * Get a list of public photos for the given user.
   * <br>
   * This method does not require authentication.
   *
   * @param userId      (Required) The userId of the user who's photos to return.
   * @param safetyLevel (Optional) safe search level of photos to return. Unsigned calls can only see safe content.
   * @param extras      (Optional) extra information to return for each photo.
   * @param perPage     Number of photos to return per page. If this argument is less than 1, it defaults to 100. The maximum allowed value is 500.
   * @param page        The page of results to return. If this argument is less than 1, it defaults to 1.
   * @param sign        if true, the request will be signed.
   * @return public photos for the given user.
   * @throws JinxException if required parameters are missing, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.people.getPublicPhotos.html">flickr.people.getPublicPhotos</a>
   */
  public Photos getPublicPhotos(String userId, JinxConstants.SafetyLevel safetyLevel, EnumSet<JinxConstants.PhotoExtras> extras,
                                int perPage, int page, boolean sign) throws JinxException {
    JinxUtils.validateParams(userId);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.people.getPublicPhotos");
    params.put("user_id", userId);
    if (safetyLevel != null) {
      params.put("safe_search", Integer.toString(JinxUtils.safetyLevelToFlickrSafteyLevelId(safetyLevel)));
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
    return jinx.flickrGet(params, Photos.class, sign);
  }

  /**
   * Returns information for the calling user related to photo uploads.
   * <br>
   * This method requires authentication with 'read' permission.
   *
   * @return upload status information for the calling user.
   * @throws JinxException if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.people.getUploadStatus.html">flickr.people.getUploadStatus</a>
   */
  public UploadStatus getUploadStatus() throws JinxException {
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.people.getUploadStatus");
    return jinx.flickrGet(params, UploadStatus.class);
  }

}
