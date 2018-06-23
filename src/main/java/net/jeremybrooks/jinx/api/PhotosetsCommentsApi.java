/*
 * Jinx is Copyright 2010-2018 by Jeremy Brooks and Contributors
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
import net.jeremybrooks.jinx.JinxException;
import net.jeremybrooks.jinx.JinxUtils;
import net.jeremybrooks.jinx.response.Response;
import net.jeremybrooks.jinx.response.photosets.comments.CommentAdd;
import net.jeremybrooks.jinx.response.photosets.comments.CommentList;

import java.util.Map;
import java.util.TreeMap;

/**
 * Provides access to the flickr.photosets.comments API methods.
 *
 * @author Jeremy Brooks
 * @see <a href="https://www.flickr.com/services/api/">Flickr API documentation</a> for more details.
 */
public class PhotosetsCommentsApi {
  private Jinx jinx;

  public PhotosetsCommentsApi(Jinx jinx) {
    this.jinx = jinx;
  }


  /**
   * Add a comment to a photoset.
   * <br>
   * This method requires authentication with 'write' permission.
   *
   * @param photosetId id of the photoset to add a comment to.
   * @param comment    text of the comment.
   * @return object with the newly created comment's ID.
   * @throws JinxException if parameters are null or empty, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.photosets.comments.addComment.html">flickr.photosets.comments.addComment</a>
   */
  public CommentAdd addComment(String photosetId, String comment) throws JinxException {
    JinxUtils.validateParams(photosetId, comment);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.photosets.comments.addComment");
    params.put("photoset_id", photosetId);
    params.put("comment_text", comment);
    return jinx.flickrPost(params, CommentAdd.class);
  }


  /**
   * Delete a photoset comment as the currently authenticated user.
   * <br>
   * This method requires authentication with 'write' permission.
   *
   * @param commentId id of the comment to delete from a photoset.
   * @return response object with status and any error messages.
   * @throws JinxException if parameters are null or empty, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.photosets.comments.deleteComment.html">flickr.photosets.comments.deleteComment</a>
   */
  public Response deleteComment(String commentId) throws JinxException {
    JinxUtils.validateParams(commentId);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.photosets.comments.deleteComment");
    params.put("comment_id", commentId);
    return jinx.flickrPost(params, Response.class);
  }


  /**
   * Edit the text of a comment as the currently authenticated user.
   * <br>
   * This method requires authentication with 'write' permission.
   *
   * @param commentId id of the comment to edit.
   * @param comment   text of the updated comment.
   * @return response object with status and any error messages.
   * @throws JinxException if parameters are null or empty, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.photosets.comments.editComment.html">flickr.photosets.comments.editComment</a>
   */
  public Response editComment(String commentId, String comment) throws JinxException {
    JinxUtils.validateParams(commentId, comment);
    Map<String, String> params = new TreeMap<>();
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
   * @see <a href="https://www.flickr.com/services/api/flickr.photosets.comments.getList.html">flickr.photosets.comments.getList</a>
   */
  public CommentList getList(String photosetId) throws JinxException {
    JinxUtils.validateParams(photosetId);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.photosets.comments.getList");
    params.put("photoset_id", photosetId);
    return jinx.flickrGet(params, CommentList.class);
  }
}
