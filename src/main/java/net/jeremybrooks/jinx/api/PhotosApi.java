package net.jeremybrooks.jinx.api;

import net.jeremybrooks.jinx.Jinx;
import net.jeremybrooks.jinx.JinxConstants;
import net.jeremybrooks.jinx.JinxException;
import net.jeremybrooks.jinx.JinxUtils;
import net.jeremybrooks.jinx.response.Response;
import net.jeremybrooks.jinx.response.photos.AddTags;
import net.jeremybrooks.jinx.response.photos.AllContexts;
import net.jeremybrooks.jinx.response.photos.PhotosResponse;

import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Jeremy Brooks
 */
public class PhotosApi {

	private Jinx jinx;

	public PhotosApi(Jinx jinx) {
		this.jinx = jinx;
	}


	/**
	 * Add tags to a photo.
	 * <p/>
	 * This method requires authentication with 'write' permission.
	 * <p/>
	 * Each tag in the list will be treated as a single tag. This method will automatically add quotation marks as
	 * needed so that multi-word tags will be treated correctly by Flickr. This method can also be used to add
	 * machine tags.
	 *
	 * @param photoId the id of the photo to add tags to.
	 * @param tags    tags to add to the photo.
	 * @return response with the result of the operation.
	 * @throws JinxException if parameters are null or empty, or if there are any errors.
	 */
	public AddTags addTags(String photoId, List<String> tags) throws JinxException {
		JinxUtils.validateParams(photoId, tags);
		Map<String, String> params = new TreeMap<String, String>();
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
	 * Delete a photo from flickr.
	 * <p/>
	 * This method requires authentication with 'delete' permission.
	 *
	 * @param photoId id of the photo to delete.
	 * @return response object with the results of the requested operation.
	 * @throws JinxException if the parameter is null or empty, or if there are any errors.
	 */
	public Response delete(String photoId) throws JinxException {
		JinxUtils.validateParams(photoId);
		Map<String, String> params = new TreeMap<String, String>();
		params.put("method", "flickr.photos.delete");
		params.put("photo_id", photoId);
		return jinx.flickrPost(params, Response.class);
	}


	/**
	 * Returns all visible sets and pools the photo belongs to.
	 * <p/>
	 * This method does not require authentication.
	 *
	 * @param photoId photo id to find contexts for.
	 * @return object with a list of all sets and pools the photo is in.
	 * @throws JinxException if the photo id is null or empty, or if there are any errors.
	 */
	public AllContexts getAllContexts(String photoId) throws JinxException {
		JinxUtils.validateParams(photoId);
		Map<String, String> params = new TreeMap<String, String>();
		params.put("method", "flickr.photos.getAllContexts");
		params.put("photo_id", photoId);
		return jinx.flickrGet(params, AllContexts.class);
	}


	/**
	 * Fetch a list of recent photos from the calling users' contacts.
	 * <p/>
	 * This method requires authentication with 'read' permission.
	 *
	 * @param count       Optional. Number of photos to return. Defaults to 10, maximum 50. This is only used if singlePhoto is not true.
	 * @param justFriends if true, only show photos from friends and family (excluding regular contacts).
	 * @param singlePhoto if true, only fetch one photo (the latest) per contact, instead of all photos in chronological order.
	 * @param includeSelf if true, include photos from the calling user.
	 * @param extras      set of extra information to fetch for each returned record.
	 * @return object containing data about the photos returned, and a list of photos.
	 * @throws JinxException if there are any errors.
	 */
	public PhotosResponse getContactsPhotos(int count, boolean justFriends, boolean singlePhoto, boolean includeSelf,
											EnumSet<JinxConstants.PhotoExtras> extras) throws JinxException {
		Map<String, String> params = new TreeMap<String, String>();
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
		return jinx.flickrGet(params, PhotosResponse.class);
	}


//  flickr.photos.getContactsPublicPhotos


//  flickr.photos.getContext


//  flickr.photos.getCounts


//  flickr.photos.getExif


//  flickr.photos.getFavorites


//  flickr.photos.getInfo


//  flickr.photos.getNotInSet


//  flickr.photos.getPerms


//  flickr.photos.getRecent


//  flickr.photos.getSizes


//  flickr.photos.getUntagged


//  flickr.photos.getWithGeoData


//  flickr.photos.getWithoutGeoData


//  flickr.photos.recentlyUpdated


	/**
	 * Remove a tag from a photo.
	 * <p/>
	 * This method requires authentication with 'write' permission.
	 * <p/>
	 * The tagId parameter must be the full tag id. Methods such as photos.getInfo will return the full tag id as the
	 * "tagId" parameter; other methods, such as addTags, will return the full tag id in the "fullTagId" parameter. If
	 * this method does not seem to work, make sure you are passing the correct tag id.
	 *
	 * @param tagId tag to remove from the photo. This parameter should contain the full tag id.
	 * @return response object indicating the result of the requested operation.
	 * @throws JinxException if the parameter is null or empty, or if there are any errors.
	 */
	public Response removeTag(String tagId) throws JinxException {
		JinxUtils.validateParams(tagId);
		Map<String, String> params = new TreeMap<String, String>();
		params.put("method", "flickr.photos.removeTag");
		params.put("tag_id", tagId);
		return jinx.flickrPost(params, Response.class);
	}


//  flickr.photos.search


//  flickr.photos.setContentType


//  flickr.photos.setDates


//  flickr.photos.setMeta


//  flickr.photos.setPerms


//  flickr.photos.setSafetyLevel


	/**
	 * Set the tags for a photo.
	 * <p/>
	 * This method requires authentication with 'write' permission.
	 * <p/>
	 * Each tag in the list will be treated as a single tag. This method will automatically add quotation marks as
	 * needed so that multi-word tags will be treated correctly by Flickr. This method can also be used to add
	 * machine tags.
	 * <p/>
	 * This list of tags will replace the tags that currently exist on the photo.
	 * <p/>
	 * If the tag list is null or empty, all tags will be removed from the photo.
	 *
	 * @param photoId id of the photo to set tags for.
	 * @param tags    all tags for the photo, one tag per list element. If this parameter is null or empty, all tags
	 *                will be removed from the photo.
	 * @return response object with status of the requested operation.
	 * @throws JinxException if photo id is null or empty, or if there are any errors.
	 */
	public Response setTags(String photoId, List<String> tags) throws JinxException {
		JinxUtils.validateParams(photoId);
		Map<String, String> params = new TreeMap<String, String>();
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
