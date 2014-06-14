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
import net.jeremybrooks.jinx.response.Response;
import net.jeremybrooks.jinx.response.galleries.GalleryInfo;
import net.jeremybrooks.jinx.response.galleries.GalleryList;
import net.jeremybrooks.jinx.response.photos.Photos;

import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Jeremy Brooks
 */
public class GalleriesApi {
	private Jinx jinx;

	public GalleriesApi(Jinx jinx) {
		this.jinx = jinx;
	}


	/**
	 * <a href="https://www.flickr.com/services/api/flickr.galleries.addPhoto.html">flickr.galleries.addPhoto</a>
	 * <p/>
	 * Add a photo to a gallery.
	 * <p/>
	 * This method requires authentication with 'write' permission.
	 *
	 * @param galleryId Required. The ID of the gallery to add a photo to. Note: this is the compound ID returned in methods like flickr.galleries.getList, and flickr.galleries.getListForPhoto.
	 * @param photoId   Required. The photo ID to add to the gallery.
	 * @param comment   Optional. A short comment or story to accompany the photo.
	 * @return object with response from Flickr indicating ok or fail.
	 * @throws JinxException if required parameters are null or empty, or if there are any errors.
	 */
	public Response addPhoto(String galleryId, String photoId, String comment) throws JinxException {
		JinxUtils.validateParams(galleryId, photoId);
		Map<String, String> params = new TreeMap<String, String>();
		params.put("method", "flickr.galleries.addPhoto");
		params.put("gallery_id", galleryId);
		params.put("photo_id", photoId);
		if (!JinxUtils.isNullOrEmpty(comment)) {
			params.put("comment", comment);
		}
		return jinx.flickrPost(params, Response.class);
	}

	/**
	 * <a href="https://www.flickr.com/services/api/flickr.galleries.create.html">flickr.galleries.create</a>
	 * <p/>
	 * <p/>
	 * Create a new gallery for the calling user.
	 * <p/>
	 * This method requires authentication with 'write' permission.
	 *
	 * @param title          Required. The name of the gallery.
	 * @param description    Required. A short description for the gallery.
	 * @param primaryPhotoId Optiona. The first photo to add to your gallery.
	 * @param fullResult     Get the result in the same format as galleries.getList
	 * @return information about the created gallery.
	 * @throws JinxException if required parameters are null or empty, or if there are any errors.
	 */
	public GalleryInfo create(String title, String description, String primaryPhotoId, boolean fullResult) throws JinxException {
		JinxUtils.validateParams(title, description);
		Map<String, String> params = new TreeMap<String, String>();
		params.put("method", "flickr.galleries.create");
		params.put("title", title);
		params.put("description", description);
		if (!JinxUtils.isNullOrEmpty(primaryPhotoId)) {
			params.put("primary_photo_id", primaryPhotoId);
		}
		if (fullResult) {
			params.put("full_result", "1");
		}
		return jinx.flickrPost(params, GalleryInfo.class);
	}

	/**
	 * <a href="https://www.flickr.com/services/api/flickr.galleries.editMeta.html">flickr.galleries.editMeta</a>
	 * <p/>
	 * Modify the meta-data for a gallery.
	 * <p/>
	 * This method requires authentication with 'write' permission.
	 *
	 * @param galleryId   Required. The gallery ID to update.
	 * @param title       Required. The new title for the gallery.
	 * @param description Optional. The new description for the gallery.
	 * @return object with response from Flickr indicating ok or fail.
	 * @throws JinxException if required parameters are null or empty, or if there are any errors.
	 */
	public Response editMeta(String galleryId, String title, String description) throws JinxException {
		JinxUtils.validateParams(galleryId, title);
		Map<String, String> params = new TreeMap<String, String>();
		params.put("method", "flickr.galleries.editMeta");
		params.put("gallery_id", galleryId);
		params.put("title", title);
		if (!JinxUtils.isNullOrEmpty(description)) {
			params.put("description", description);
		}
		return jinx.flickrPost(params, Response.class);
	}

	/**
	 * <a href="https://www.flickr.com/services/api/flickr.galleries.editPhoto.html">flickr.galleries.editPhoto</a>
	 * <p/>
	 * Edit the comment for a gallery photo.
	 * <p/>
	 * This method requires authentication with 'write' permission.
	 *
	 * @param galleryId Required. The ID of the gallery containing the photo. Note: this is the compound ID returned in methods like flickr.galleries.getList, and flickr.galleries.getListForPhoto.
	 * @param photoId   Required. The photo ID in the gallery whose comment to edit.
	 * @param comment   Required. The updated comment for the photo.
	 * @return object with response from Flickr indicating ok or fail.
	 * @throws JinxException if required parameters are null or empty, or if there are any errors.
	 */
	public Response editPhoto(String galleryId, String photoId, String comment) throws JinxException {
		JinxUtils.validateParams(galleryId, photoId, comment);
		Map<String, String> params = new TreeMap<String, String>();
		params.put("method", "flickr.galleries.editPhoto");
		params.put("gallery_id", galleryId);
		params.put("photo_id", photoId);
		params.put("comment", comment);
		return jinx.flickrPost(params, Response.class);
	}

	/**
	 * <a href="https://www.flickr.com/services/api/flickr.galleries.editPhotos.html">flickr.galleries.editPhotos</a>
	 * <p/>
	 * Modify the photos in a gallery. Use this method to add, remove and re-order photos.
	 * <p/>
	 * This method requires authentication with 'write' permission.
	 *
	 * @param galleryId      Required. The id of the gallery to modify. The gallery must belong to the calling user.
	 * @param primaryPhotoId Required. The id of the photo to use as the 'primary' photo for the gallery. This id must also be passed along in photo_ids list argument.
	 * @param photoIds       Required. A list of photo ids to include in the gallery. They will appear in the set in the order sent. This list must contain the primary photo id. This list of photos replaces the existing list.
	 * @return object with response from Flickr indicating ok or fail.
	 * @throws JinxException if required parameters are null or empty, or if there are any errors.
	 */
	public Response editPhotos(String galleryId, String primaryPhotoId, List<String> photoIds) throws JinxException {
		JinxUtils.validateParams(galleryId, primaryPhotoId, photoIds);
		Map<String, String> params = new TreeMap<String, String>();
		params.put("method", "flickr.galleries.editPhotos");
		params.put("gallery_id", galleryId);
		params.put("primary_photo_id", primaryPhotoId);
		params.put("photo_ids", JinxUtils.buildCommaDelimitedList(photoIds));
		return jinx.flickrPost(params, Response.class);
	}

	/**
	 * <a href="https://www.flickr.com/services/api/flickr.galleries.getInfo.html">flickr.galleries.getInfo</a>
	 * <p/>
	 * This method does not require authentication.
	 *
	 * @param galleryId Required. The gallery ID you are requesting information for.
	 * @return information about the gallery.
	 * @throws JinxException if required parameters are null or empty, or if there are any errors.
	 */
	public GalleryInfo galleryInfo(String galleryId) throws JinxException {
		JinxUtils.validateParams(galleryId);
		Map<String, String> params = new TreeMap<String, String>();
		params.put("method", "flickr.galleries.getInfo");
		params.put("gallery_id", galleryId);
		return jinx.flickrPost(params, GalleryInfo.class);
	}

	/**
	 * <a href="https://www.flickr.com/services/api/flickr.galleries.getList.html">flickr.galleries.getList</a>
	 * <p/>
	 * Return the list of galleries created by a user. Sorted from newest to oldest.
	 * <p/>
	 * This method does not require authentication.
	 *
	 * @param userId  Required. The NSID of the user to get a galleries list for. If none is specified, the calling user is assumed.
	 * @param perPage Optional. Number of galleries to return per page. If this argument is <= 0, it defaults to 100. The maximum allowed value is 500.
	 * @param page    Optional. The page of results to return. If this argument is <= 0, it defaults to 1.
	 * @param extras  Optional. Extra information to fetch for the primary photo.
	 * @return list of galleries for the user.
	 * @throws JinxException if required parameters are null or empty, or if there are any errors.
	 */
	public GalleryList getList(String userId, int perPage, int page, EnumSet<JinxConstants.PhotoExtras> extras) throws JinxException {
		JinxUtils.validateParams(userId);
		Map<String, String> params = new TreeMap<String, String>();
		params.put("method", "flickr.galleries.getList");
		params.put("user_id", userId);
		if (perPage > 0) {
			params.put("per_page", Integer.toString(perPage));
		}
		if (page > 0) {
			params.put("page", Integer.toString(page));
		}
		if (!JinxUtils.isNullOrEmpty(extras)) {
			params.put("primary_photo_extras", JinxUtils.buildCommaDelimitedList(extras));
		}
		return jinx.flickrGet(params, GalleryList.class);
	}

	/**
	 * <a href="https://www.flickr.com/services/api/flickr.galleries.getListForPhoto.html">flickr.galleries.getListForPhoto</a>
	 * <p/>
	 * Return the list of galleries to which a photo has been added. Galleries are returned sorted by date which the photo was added to the gallery.
	 * <p/>
	 * This method does not require authentication.
	 *
	 * @param photoId Required. The ID of the photo to fetch a list of galleries for.
	 * @param perPage Optional. Number of galleries to return per page. If this argument is <= 0, it defaults to 100. The maximum allowed value is 500.
	 * @param page    Optional. The page of results to return. If this argument is <= 0, it defaults to 1.
	 * @return list of galleries to which a photo has been added.
	 * @throws JinxException if required parameters are null or empty, or if there are any errors.
	 */
	public GalleryList getListForPhoto(String photoId, int perPage, int page) throws JinxException {
		JinxUtils.validateParams(photoId);
		Map<String, String> params = new TreeMap<String, String>();
		params.put("method", "flickr.galleries.getListForPhoto");
		params.put("photo_id", photoId);
		if (perPage > 0) {
			params.put("per_page", Integer.toString(perPage));
		}
		if (page > 0) {
			params.put("page", Integer.toString(page));
		}
		return jinx.flickrGet(params, GalleryList.class);
	}

	/**
	 * <a href="https://www.flickr.com/services/api/flickr.galleries.getPhotos.html">flickr.galleries.getPhotos</a>
	 * <p/>
	 * Return the list of photos for a gallery
	 * <p/>
	 * This method does not require authentication.
	 * <p/>
	 * @param galleryId Required. The ID of the gallery of photos to return.
	 * @param extras    Optional. Extra information to fetch for the primary photo.
	 * @param perPage   Optional. Number of galleries to return per page. If this argument is <= 0, it defaults to 100. The maximum allowed value is 500.
	 * @param page      Optional. The page of results to return. If this argument is <= 0, it defaults to 1.
	 * @return photos in the gallery.
	 * @throws JinxException if required parameters are null or empty, or if there are any errors.
	 */
	public Photos getPhotos(String galleryId, EnumSet<JinxConstants.PhotoExtras> extras, int perPage, int page) throws JinxException {
		JinxUtils.validateParams(galleryId);
		Map<String, String> params = new TreeMap<String, String>();
		params.put("method", "flickr.galleries.getPhotos");
		params.put("gallery_id", galleryId);
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
}
