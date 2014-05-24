package net.jeremybrooks.jinx.api;

import net.jeremybrooks.jinx.Jinx;
import net.jeremybrooks.jinx.JinxConstants;
import net.jeremybrooks.jinx.JinxException;
import net.jeremybrooks.jinx.JinxUtils;
import net.jeremybrooks.jinx.response.Response;
import net.jeremybrooks.jinx.response.common.Context;
import net.jeremybrooks.jinx.response.photosets.PhotosetInfo;
import net.jeremybrooks.jinx.response.photosets.PhotosetList;

import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Jeremy Brooks
 */
public class PhotosetsApi {
	private Jinx jinx;

	public PhotosetsApi(Jinx jinx) {
		this.jinx = jinx;
	}

	/**
	 * Add a photo to the end of an existing photoset.
	 * <p/>
	 * This method requires authentication with 'write' permission.
	 * <p/>
	 * This method has no specific response - It returns an empty success response if it completes without error.
	 *
	 * @param photosetId id of the photoset to add a photo to. Required.
	 * @param photoId    id of the photo to add to the set. Required.
	 * @return an empty success response if it completes without error.
	 * @throws JinxException if there are any errors.
	 */
	public Response addPhoto(String photosetId, String photoId) throws JinxException {
		JinxUtils.validateParams(photosetId, photoId);
		Map<String, String> params = new TreeMap<String, String>();
		params.put("method", "flickr.photosets.addPhoto");
		params.put("photoset_id", photosetId);
		params.put("photo_id", photoId);
		return this.jinx.flickrPost(params, Response.class);
	}


	/**
	 * Create a new photoset for the calling user.
	 * Authentication
	 * <p/>
	 * This method requires authentication with 'write' permission.
	 * <p/>
	 * New photosets are automatically put first in the photoset ordering for the user.
	 * Use orderSets if you don't want the new set to appear first on the user's photoset list.
	 *
	 * @param title          title for the photoset. Required.
	 * @param description    description of the photoset. Optional.
	 * @param primaryPhotoId id of the photo to represent this set. The photo must belong to the calling user. Required.
	 * @return instances of {@link net.jeremybrooks.jinx.response.photosets.PhotosetInfo} with only the photosetId and url
	 * fields set.
	 * @throws JinxException if required parameters are null, or if there are any errors.
	 */
	public PhotosetInfo create(String title, String description, String primaryPhotoId) throws JinxException {
		JinxUtils.validateParams(title, primaryPhotoId);
		Map<String, String> params = new TreeMap<String, String>();
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
	 * <p/>
	 * This method requires authentication with 'write' permission.
	 * <p/>
	 * This method has no specific response - It returns an empty success response if it completes without error.
	 *
	 * @param photosetId id of the photoset to delete. It must be owned by the calling user. Required.
	 * @return success response if it completes without error.
	 * @throws JinxException if required parameters are null, or if there are any errors.
	 */
	public Response delete(String photosetId) throws JinxException {
		JinxUtils.validateParams(photosetId);
		Map<String, String> params = new TreeMap<String, String>();
		params.put("method", "flickr.photosets.delete");
		params.put("photoset_id", photosetId);
		return jinx.flickrPost(params, Response.class);
	}

	/**
	 * Modify the meta-data for a photoset.
	 * <p/>
	 * This method requires authentication with 'write' permission.
	 * <p/>
	 * This method has no specific response - It returns an empty success response if it completes without error.
	 *
	 * @param photosetId  id of the photoset to modify. Required.
	 * @param title       new title for the photoset. Required.
	 * @param description description of the photoset. May contain limited html.
	 * @return success response if it completes without error.
	 * @throws JinxException if required parameters are null, or if there are any errors.
	 */
	public Response editMeta(String photosetId, String title, String description) throws JinxException {
		JinxUtils.validateParams(photosetId, title);
		Map<String, String> params = new TreeMap<String, String>();
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
	 * <p/>
	 * This method requires authentication with 'write' permission.
	 * <p/>
	 * This method has no specific response - It returns an empty success response if it completes without error.
	 *
	 * @param photosetId     id of the photoset to modify. The photoset must belong to the calling user. Required.
	 * @param primaryPhotoId id of the photo to use as the 'primary' photo for the set. This id must also be passed along in photo_ids list argument. Required.
	 * @param photoIds       list of photo ids to include in the set. They will appear in the set in the order sent. This list must contain the primary photo id. All photos must belong to the owner of the set. This list of photos replaces the existing list. Call addPhoto to append a photo to a set. Required.
	 * @return success response if it completes without error.
	 * @throws JinxException if required parameters are null, or if there are any errors.
	 */
	public Response editPhotos(String photosetId, String primaryPhotoId, List<String> photoIds) throws JinxException {
		JinxUtils.validateParams(photosetId, primaryPhotoId, photoIds);
		Map<String, String> params = new TreeMap<String, String>();
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
	 * <p/>
	 * This method does not require authentication.
	 * <p/>
	 * photo_id (Required)
	 * The id of the photo to fetch the context for.
	 * photoset_id (Required)
	 * The id of the photoset for which to fetch the photo's context.
	 *
	 * @param photoId    id of the photo to fetch the context for. Required.
	 * @param photosetId id of the photoset for which to fetch the photo's context. Required.
	 * @return {@link net.jeremybrooks.jinx.response.common.Context} object with the photo context.
	 * @throws JinxException if required parameters are null, or if there are any errors.
	 */
	public Context getContext(String photoId, String photosetId) throws JinxException {
		JinxUtils.validateParams(photoId, photosetId);
		Map<String, String> params = new TreeMap<String, String>();
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
	 * @throws JinxException
	 */
	public PhotosetInfo getInfo(String photosetId) throws JinxException {
		JinxUtils.validateParams(photosetId);
		Map<String, String> params = new TreeMap<String, String>();
		params.put("method", "flickr.photosets.getInfo");
		params.put("photoset_id", photosetId);
		return jinx.flickrGet(params, PhotosetInfo.class);
	}

	/**
	 * flickr.photosets.getList
	 * <p/>
	 * Returns the photosets belonging to the specified user.
	 * <p/>
	 * This method does not require authentication.
	 *
	 * @param userId      Optional. The NSID of the user to get a photoset list for. If none is specified, the calling user is assumed.
	 * @param page        Optional. The page of results to get. Currently, if this is omitted, all sets are returned, but this behaviour may change in future. A value of zero will cause the parameter to be ignored.
	 * @param perPage     Optional. The number of sets to get per page. If paging is enabled, the maximum number of sets per page is 500. A value of zero will cause the parameter to be ignored.
	 * @param photoExtras Optional. A list of extra information to fetch for the primary photo. Currently supported fields are:
	 *                    license, date_upload, date_taken, owner_name, icon_server, original_format, last_update,
	 *                    geo, tags, machine_tags, o_dims, views, media, path_alias, url_sq, url_t, url_s, url_m, url_o
	 * @return
	 * @throws JinxException
	 */
	public PhotosetList getList(String userId, int page, int perPage, EnumSet<JinxConstants.PhotoExtras> photoExtras) throws JinxException {
		Map<String, String> params = new TreeMap<String, String>();
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
		if (! JinxUtils.isNullOrEmpty(photoExtras)) {
			params.put("photo_extras", JinxUtils.buildCommaDelimitedList(photoExtras));
		}
		return jinx.flickrGet(params, PhotosetList.class);
	}

}
