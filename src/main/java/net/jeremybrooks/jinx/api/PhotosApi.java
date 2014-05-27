package net.jeremybrooks.jinx.api;

import net.jeremybrooks.jinx.Jinx;
import net.jeremybrooks.jinx.JinxException;
import net.jeremybrooks.jinx.JinxUtils;
import net.jeremybrooks.jinx.response.Response;
import net.jeremybrooks.jinx.response.photos.AddTags;

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


//  flickr.photos.getAllContexts


//  flickr.photos.getContactsPhotos


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
