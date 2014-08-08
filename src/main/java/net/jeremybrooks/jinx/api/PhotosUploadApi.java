/*
 * Jinx is Copyright 2010-2014 by Jeremy Brooks and Contributors
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
import net.jeremybrooks.jinx.response.photos.upload.UploadResponse;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * Created by jeremyb on 8/1/14.
 */
public class PhotosUploadApi {
    private Jinx jinx;

    private PhotosUploadApi() {
    }

    public PhotosUploadApi(Jinx jinx) {
        this.jinx = jinx;
    }

    /**
     * <a href="https://www.flickr.com/services/api/flickr.photos.upload.checkTickets.html">flickr.photos.upload.checkTickets</a>
     * <p/>
     * Checks the status of one or more asynchronous photo upload tickets.
     * <p/>
     * This method does not require authentication.
     *
     * @param tickets (Required) list of ticket id's.
     * @return object with status of each ticket.
     * @throws JinxException if required parameters are missing, or if there are any errors.
     */
    public CheckTicketsResponse checkTickets(List<String> tickets) throws JinxException {
        JinxUtils.validateParams(tickets);
        Map<String, String> params = new TreeMap<String, String>();
        params.put("method", "flickr.photos.upload.checkTickets");
        params.put("tickets", JinxUtils.buildCommaDelimitedList(tickets));
        return jinx.flickrGet(params, CheckTicketsResponse.class, false);
    }


    /**
     * Upload a photo to Flickr using the synchronous upload API.
     *
     * This method requires authentication with 'write' permission.
     *
     * If the title parameter is null, the filename will be used as the title.
     *
     * For more details, see the <a href="https://www.flickr.com/services/api/upload.api.html">Flickr photo upload</a>
     * documentation.
     *
     * @param photo (Required) the photo file to upload.
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
     * @return object with the results of the upload. If successful, the photo id will be included.
     * @throws JinxException if required parameters are missing, or if there are any errors.
     */
    public UploadResponse upload(File photo, String title, String description, List<String> tags, Boolean isPublic,
                                 Boolean isFriend, Boolean isFamily, JinxConstants.SafetyLevel safetyLevel,
                                 JinxConstants.ContentType contentType, Boolean hidden) throws JinxException {
        JinxUtils.validateParams(photo);
        byte[] photoData = new byte[(int) photo.length()];
        FileInputStream in = null;
        try {
            in = new FileInputStream(photo);
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
        } finally {
            JinxUtils.close(in);
        }
        return upload(photoData, title, description, tags, isPublic, isFriend, isFamily, safetyLevel, contentType, hidden);
    }

    /**
     * Upload a photo to Flickr using the synchronous upload API.
     *
     * This method requires authentication with 'write' permission.
     *
     * For more details, see the <a href="https://www.flickr.com/services/api/upload.api.html">Flickr photo upload</a>
     * documentation.
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
     * @return object with the results of the upload. If successful, the photo id will be included.
     * @throws JinxException if required parameters are missing, or if there are any errors.
     */
    public UploadResponse upload(byte[] photoData, String title, String description, List<String> tags, Boolean isPublic,
                                 Boolean isFriend, Boolean isFamily, JinxConstants.SafetyLevel safetyLevel,
                                 JinxConstants.ContentType contentType, Boolean hidden) throws JinxException {
        JinxUtils.validateParams(photoData);
        Map<String, String> params = new TreeMap<String, String>();
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
     * Upload a photo to Flickr using the asynchronous upload API.
     *
     * This method requires authentication with 'write' permission.
     *
     * For more information, see the <a href="https://www.flickr.com/services/api/upload.async.html">Flickr async upload</a>
     * documentation.
     *
     * @param photo
     * @param title
     * @param description
     * @param tags
     * @param isPublic
     * @param isFriend
     * @param isFamily
     * @param safetyLevel
     * @param contentType
     * @param hidden
     * @return
     * @throws JinxException
     */
    public UploadResponse uploadAsync(File photo, String title, String description, List<String> tags, Boolean isPublic,
                                 Boolean isFriend, Boolean isFamily, JinxConstants.SafetyLevel safetyLevel,
                                 JinxConstants.ContentType contentType, Boolean hidden) throws JinxException {
        JinxUtils.validateParams(photo);
        byte[] photoData = new byte[(int) photo.length()];
        FileInputStream in = null;
        try {
            in = new FileInputStream(photo);
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
        } finally {
            JinxUtils.close(in);
        }
        return uploadAsync(photoData, title, description, tags, isPublic, isFriend, isFamily, safetyLevel, contentType, hidden);
    }


    /**
     * Upload a photo to Flickr using the asynchronous upload API.
     *
     * This method requires authentication with 'write' permission.
     *
     * For more information, see the <a href="https://www.flickr.com/services/api/upload.async.html">Flickr async upload</a>
     * documentation.
     *
     * @param photoData
     * @param title
     * @param description
     * @param tags
     * @param isPublic
     * @param isFriend
     * @param isFamily
     * @param safetyLevel
     * @param contentType
     * @param hidden
     * @return
     * @throws JinxException
     */
    public UploadResponse uploadAsync(byte[] photoData, String title, String description, List<String> tags, Boolean isPublic,
                                 Boolean isFriend, Boolean isFamily, JinxConstants.SafetyLevel safetyLevel,
                                 JinxConstants.ContentType contentType, Boolean hidden) throws JinxException {
        JinxUtils.validateParams(photoData);
        Map<String, String> params = new TreeMap<String, String>();
        params.put("async", "1");
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
}
