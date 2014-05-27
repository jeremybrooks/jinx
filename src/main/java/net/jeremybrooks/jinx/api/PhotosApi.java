package net.jeremybrooks.jinx.api;

import net.jeremybrooks.jinx.Jinx;
import net.jeremybrooks.jinx.JinxException;
import net.jeremybrooks.jinx.JinxUtils;
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
	 *
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
}
