package net.jeremybrooks.jinx.api;

import net.jeremybrooks.jinx.Jinx;
import net.jeremybrooks.jinx.JinxException;
import net.jeremybrooks.jinx.JinxUtils;
import net.jeremybrooks.jinx.response.Response;
import net.jeremybrooks.jinx.response.photosets.comments.CommentAdd;
import net.jeremybrooks.jinx.response.photosets.comments.CommentList;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author Jeremy Brooks
 */
public class PhotosetsCommentsApi {
	private Jinx jinx;

	public PhotosetsCommentsApi(Jinx jinx) {
		this.jinx = jinx;
	}


	/**
	 * Add a comment to a photoset.
	 * <p/>
	 * This method requires authentication with 'write' permission.
	 *
	 * @param photosetId id of the photoset to add a comment to.
	 * @param comment    text of the comment.
	 * @return object with the newly created comment's ID.
	 * @throws JinxException if parameters are null or empty, or if there are any errors.
	 */
	public CommentAdd addComment(String photosetId, String comment) throws JinxException {
		JinxUtils.validateParams(photosetId, comment);
		Map<String, String> params = new TreeMap<String, String>();
		params.put("method", "flickr.photosets.comments.addComment");
		params.put("photoset_id", photosetId);
		params.put("comment_text", comment);
		return jinx.flickrPost(params, CommentAdd.class);
	}


	/**
	 * Delete a photoset comment as the currently authenticated user.
	 * <p/>
	 * This method requires authentication with 'write' permission.
	 *
	 * @param commentId id of the comment to delete from a photoset.
	 * @return response object with status and any error messages.
	 * @throws JinxException if parameters are null or empty, or if there are any errors.
	 */
	public Response deleteComment(String commentId) throws JinxException {
		JinxUtils.validateParams(commentId);
		Map<String, String> params = new TreeMap<String, String>();
		params.put("method", "flickr.photosets.comments.deleteComment");
		params.put("comment_id", commentId);
		return jinx.flickrPost(params, Response.class);
	}


	/**
	 * Edit the text of a comment as the currently authenticated user.
	 * <p/>
	 * This method requires authentication with 'write' permission.
	 *
	 * @param commentId id of the comment to edit.
	 * @param comment   text of the updated comment.
	 * @return response object with status and any error messages.
	 * @throws JinxException if parameters are null or empty, or if there are any errors.
	 *                       \
	 */
	public Response editComment(String commentId, String comment) throws JinxException {
		JinxUtils.validateParams(commentId, comment);
		Map<String, String> params = new TreeMap<String, String>();
		params.put("method", "flickr.photosets.comments.editComment");
		params.put("comment_id", commentId);
		params.put("comment_text", comment);
		return jinx.flickrPost(params, Response.class);
	}

	/**
	 * Returns the comments for a photoset.
	 *
	 * @param photosetId id of the photoset to fetch comments for.
	 * @return comment list object.
	 * @throws JinxException if the parameter is null or empty, or if there are any errors.
	 */
	public CommentList getList(String photosetId) throws JinxException {
		JinxUtils.validateParams(photosetId);
		Map<String, String> params = new TreeMap<String, String>();
		params.put("method", "flickr.photosets.comments.getList");
		params.put("photoset_id", photosetId);
		return jinx.flickrGet(params, CommentList.class);
	}
}
