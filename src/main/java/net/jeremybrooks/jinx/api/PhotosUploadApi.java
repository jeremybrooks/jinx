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
import net.jeremybrooks.jinx.response.photos.upload.CheckTicketsResponse;
import net.jeremybrooks.jinx.response.photos.upload.ReplaceResponse;
import net.jeremybrooks.jinx.response.photos.upload.UploadResponse;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Provides access to the flickr.photos.upload API methods.
 *
 * @author Jeremy Brooks
 * @see <a href="https://www.flickr.com/services/api/">Flickr API documentation</a> for more details.
 */
public class PhotosUploadApi {
  private Jinx jinx;

  private PhotosUploadApi() {
  }

  public PhotosUploadApi(Jinx jinx) {
    this.jinx = jinx;
  }

  /**
   * Checks the status of one or more asynchronous photo upload tickets.
   * <br>
   * This method does not require authentication.
   *
   * @param tickets (Required) list of ticket id's.
   * @return object with status of each ticket.
   * @throws JinxException if required parameters are missing, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.photos.upload.checkTickets.html">flickr.photos.upload.checkTickets</a>
   */
  public CheckTicketsResponse checkTickets(List<String> tickets) throws JinxException {
    JinxUtils.validateParams(tickets);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.photos.upload.checkTickets");
    params.put("tickets", JinxUtils.buildCommaDelimitedList(tickets));
    return jinx.flickrGet(params, CheckTicketsResponse.class, false);
  }


  /**
   * Upload a photo or video to Flickr.
   * <br>
   * This method requires authentication with 'write' permission.
   * <br>
   * If the title parameter is null, the filename will be used as the title.
   *
   * @param photo       (Required) the photo or video file to upload.
   * @param title       (Optional) the title of the photo or video.
   * @param description (Optional) the description of the photo or video.
   * @param tags        (Optional) list of tags to apply to the photo or video.
   * @param isPublic    (Optional) is photo/video visible to everyone. This is the default if none of isPublic, isFriends, or
   *                    isFamily is specified.
   * @param isFriend    (Optional) is photo/video visible only to friends.
   * @param isFamily    (Optional) is photo/video visible only to family.
   * @param safetyLevel (Optional) safety level of the photo or video.
   * @param contentType (Optional) content type of the upload.
   * @param hidden      (Optional) if true, photo/video will be hidden from public searches.
   *                    If false or null, it will be included in public searches.
   * @param async       if true, the photo/video will be uploaded using the Flickr async API.
   * @return object with the results of the upload. Successful synchronous uploads will contain a photo id; successful
   * asynchronous uploads will include a ticket id.
   * @throws JinxException if required parameters are missing, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/upload.api.html">Flickr photo upload documentation</a>
   */
  public UploadResponse upload(File photo, String title, String description, List<String> tags, Boolean isPublic,
                               Boolean isFriend, Boolean isFamily, JinxConstants.SafetyLevel safetyLevel,
                               JinxConstants.ContentType contentType, Boolean hidden, Boolean async) throws JinxException {
    JinxUtils.validateParams(photo);
    byte[] photoData = new byte[(int) photo.length()];
    try (FileInputStream in = new FileInputStream(photo)) {
      in.read(photoData);

      if (JinxUtils.isNullOrEmpty(title)) {
        int index = photo.getName().indexOf('.');
        if (index > 0) {
          title = photo.getName().substring(0, index);
        } else {
          title = photo.getName();
        }
      }
    } catch (Exception e) {
      throw new JinxException("Unable to load data from photo " + photo.getAbsolutePath(), e);
    }
    return upload(photoData, title, description, tags, isPublic, isFriend, isFamily, safetyLevel, contentType, hidden, async);
  }

  /**
   * Upload a photo to Flickr.
   * <br>
   * This method requires authentication with 'write' permission.
   *
   * @param photoData   (Required) the photo data to upload.
   * @param title       (Optional) the title of the photo.
   * @param description (Optional) the description of the photo.
   * @param tags        (Optional) list of tags to apply to the photo.
   * @param isPublic    (Optional) is photo visible to everyone. This is the default if none of isPublic, isFriends, or
   *                    isFamily is specified.
   * @param isFriend    (Optional) is photo visible only to friends.
   * @param isFamily    (Optional) is photo visible only to family.
   * @param safetyLevel (Optional) safety level of the photo.
   * @param contentType (Optional) content type of the upload.
   * @param hidden      (Optional) if true, photo will be hidden from public searches.
   *                    If false or null, it will be included in public searches.
   * @param async       if true, the photo will be uploaded using the Flickr async API.
   * @return object with the results of the upload. Successful synchronous uploads will contain a photo id; successful
   * asynchronous uploads will include a ticket id.
   * @throws JinxException if required parameters are missing, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/upload.api.html">Flickr photo upload documentation</a>
   */
  public UploadResponse upload(byte[] photoData, String title, String description, List<String> tags, Boolean isPublic,
                               Boolean isFriend, Boolean isFamily, JinxConstants.SafetyLevel safetyLevel,
                               JinxConstants.ContentType contentType, Boolean hidden, Boolean async) throws JinxException {
    if (photoData == null || photoData.length == 0) {
      throw new JinxException("Photo data cannot be null or empty.");
    }
    Map<String, String> params = new TreeMap<>();
    if (async != null && async) {
      params.put("async", "1");
    }
    if (!JinxUtils.isNullOrEmpty(title)) {
      params.put("title", title);
    }
    if (!JinxUtils.isNullOrEmpty(description)) {
      params.put("description", description);
    }
    if (!JinxUtils.isNullOrEmpty(tags)) {
      List<String> tagList = JinxUtils.normalizeTagsForUpload(tags);
      params.put("tags", JinxUtils.buildCommaDelimitedList(tagList));
    }
    if (isPublic != null) {
      params.put("is_public", isPublic ? "1" : "0");
    }
    if (isFriend != null) {
      params.put("is_friend", isFriend ? "1" : "0");
    }
    if (isFamily != null) {
      params.put("is_family", isFamily ? "1" : "0");
    }
    if (safetyLevel != null) {
      params.put("safety_level", Integer.toString(JinxUtils.safetyLevelToFlickrSafteyLevelId(safetyLevel)));
    }
    if (contentType != null) {
      params.put("content_type", Integer.toString(JinxUtils.contentTypeToFlickrContentTypeId(contentType)));
    }
    if (hidden != null) {
      params.put("hidden", hidden ? "1" : "0");
    }
    return jinx.flickrUpload(params, photoData, UploadResponse.class);
  }


  /**
   * Replace a photo on Flickr.
   * <br>
   * This method requires authentication with 'write' permission.
   *
   * @param photo   (Required) the photo or video file to replace existing content.
   * @param photoId (Required) the id of the existing photo or video to be replaced.
   * @param async   if true, the photo will be uploaded using the Flickr async API.
   * @return object with the results of the upload. Successful synchronous uploads will contain a photo id; successful
   * asynchronous uploads will include a ticket id.
   * @throws JinxException if required parameters are missing, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/upload.api.html">Flickr photo upload documentation</a>
   */
  public ReplaceResponse replace(File photo, String photoId, Boolean async) throws JinxException {
    JinxUtils.validateParams(photo, photoId);
    byte[] photoData = new byte[(int) photo.length()];
    try (FileInputStream in = new FileInputStream(photo)) {
      in.read(photoData);
    } catch (Exception e) {
      throw new JinxException("Unable to load data from photo " + photo.getAbsolutePath(), e);
    }
    return replace(photoData, photoId, async);
  }


  /**
   * Replace a photo on Flickr.
   * <br>
   * This method requires authentication with 'write' permission.
   *
   * @param photoData (Required) the photo or video data to replace existing content.
   * @param photoId   (Required) the id of the existing photo or video to be replaced.
   * @param async     if true, the photo will be uploaded using the Flickr async API.
   * @return object with the results of the upload. Successful synchronous uploads will contain a photo id; successful
   * asynchronous uploads will include a ticket id.
   * @throws JinxException if required parameters are missing, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/upload.api.html">Flickr photo upload documentation</a>
   */
  public ReplaceResponse replace(byte[] photoData, String photoId, Boolean async) throws JinxException {
    JinxUtils.validateParams(photoData, photoId);
    Map<String, String> params = new TreeMap<>();
    params.put("photo_id", photoId);
    if (async != null && async) {
      params.put("async", "1");
    }
    return jinx.flickrReplace(params, photoData, ReplaceResponse.class);
  }
}
