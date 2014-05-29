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
import net.jeremybrooks.jinx.response.common.Context;
import net.jeremybrooks.jinx.response.photos.AddTags;
import net.jeremybrooks.jinx.response.photos.AllContexts;
import net.jeremybrooks.jinx.response.photos.ExifData;
import net.jeremybrooks.jinx.response.photos.Favorites;
import net.jeremybrooks.jinx.response.photos.Photocounts;
import net.jeremybrooks.jinx.response.photos.Photos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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
	 * @param count       Number of photos to return. If zero, defaults to 10, maximum 50. This is only used if singlePhoto is not true.
	 * @param justFriends if true, only show photos from friends and family (excluding regular contacts).
	 * @param singlePhoto if true, only fetch one photo (the latest) per contact, instead of all photos in chronological order.
	 * @param includeSelf if true, include photos from the calling user.
	 * @param extras      set of extra information to fetch for each returned record.
	 * @return object containing data about the photos returned, and a list of photos.
	 * @throws JinxException if there are any errors.
	 */
	public Photos getContactsPhotos(int count, boolean justFriends, boolean singlePhoto, boolean includeSelf,
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
		return jinx.flickrGet(params, Photos.class);
	}


	/**
	 * Fetch a list of recent public photos from a users' contacts.
	 * <p/>
	 * This method does not require authentication.
	 * <p/>
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
	 */
	public Photos getContactsPublicPhotos(String userId, int count, boolean justFriends,
										  boolean singlePhoto, boolean includeSelf,
										  EnumSet<JinxConstants.PhotoExtras> extras)
			throws JinxException {
		JinxUtils.validateParams(userId);
		Map<String, String> params = new TreeMap<String, String>();
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
	 * <p/>
	 * This method does not require authentication.
	 *
	 * @param photoId Required. The id of the photo to fetch the context for.
	 * @return object with the context of the photo.
	 * @throws JinxException if the photo id is null or empty, or if there are any errors.
	 */
	public Context getContext(String photoId) throws JinxException {
		JinxUtils.validateParams(photoId);
		Map<String, String> params = new TreeMap<String, String>();
		params.put("method", "flickr.photos.getContext");
		params.put("photo_id", photoId);
		return jinx.flickrGet(params, Context.class);
	}

	/**
	 * Gets a list of photo counts for the given date ranges for the calling user.
	 * <p/>
	 * This method requires authentication with 'read' permission.
	 * <p/>
	 * You must provide either dates or takenDates parameters. Flickr may not return correct results if you specify both.
	 *
	 * @param dates      a list of dates denoting the periods to return counts for. They should be specified smallest first.
	 * @param takenDates a list of dates denoting the periods to return counts for. They should be specified smallest first.
	 * @return object containing a list of counts for the specified dates.
	 * @throws JinxException if there are any errors.
	 */
	public Photocounts getCounts(List<Date> dates, List<Date> takenDates) throws JinxException {
		Map<String, String> params = new TreeMap<String, String>();
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
	 * flickr.photos.getExif
	 * <p/>
	 * Retrieves a list of EXIF/TIFF/GPS tags for a given photo. The calling user must have permission to view the photo.
	 * <p/>
	 * This method does not require authentication.
	 *
	 * @param photoId Required. The id of the photo to fetch information for.
	 * @param secret  Optional. The secret for the photo. If the correct secret is passed then permissions checking is skipped.
	 *                This enables the 'sharing' of individual photos by passing around the id and secret.
	 * @return object containing limited information about the photo, and a list of Exif data.
	 * @throws JinxException
	 */
	public ExifData getExif(String photoId, String secret) throws JinxException {
		JinxUtils.validateParams(photoId);
		Map<String, String> params = new TreeMap<String, String>();
		params.put("method", "flickr.photos.getExif");
		params.put("photo_id", photoId);
		if (!JinxUtils.isNullOrEmpty(secret)) {
			params.put("secret", secret);
		}
		return jinx.flickrGet(params, ExifData.class);
	}


	/**
	 * Returns the list of people who have favorited a given photo.
	 * <p/>
	 * This method does not require authentication.
	 *
	 * @param photoId Required. The id of the photo to fetch information for.
	 * @param page    page of results to return. If this argument is zero, it defaults to 1.
	 * @param perPage number of people to return per page. If this argument is zero, it defaults to 10. The maximum allowed value is 50.
	 * @return object containing limited information about the photo, and a list of people who have favorited the photo.
	 * @throws JinxException if required parameters are null or empty, or if there are errors.
	 */
	public Favorites getFavorites(String photoId, int page, int perPage) throws JinxException {
		JinxUtils.validateParams(photoId);
		Map<String, String> params = new TreeMap<String, String>();
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
