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
import net.jeremybrooks.jinx.response.photos.Photos;
import net.jeremybrooks.jinx.response.photos.comments.Comment;
import net.jeremybrooks.jinx.response.photos.comments.Comments;

import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by jeremyb on 7/16/14.
 */
public class PhotosCommentsApi {
    private Jinx jinx;

    public PhotosCommentsApi(Jinx jinx) {
        this.jinx = jinx;
    }

    /**
     * <a href=" https://www.flickr.com/services/api/flickr.photos.comments.addComment.html">flickr.photos.comments.addComment</a>
     * <p/>
     * Add comment to a photo as the currently authenticated user.
     * <p/>
     * This method requires authentication with 'write' permission.
     *
     * @param photoId     (Required) The id of the photo to add a comment to.
     * @param commentText (Required) Text of the comment.
     * @return object containing information about the newly added comment.
     * @throws JinxException if required parameters are missing, or if there are any errors.
     */
    public Comment addComment(String photoId, String commentText) throws JinxException {
        JinxUtils.validateParams(photoId, commentText);
        Map<String, String> params = new TreeMap<String, String>();
        params.put("method", "flickr.photos.comments.addComment");
        params.put("photo_id", photoId);
        params.put("comment_text", commentText);
        return jinx.flickrPost(params, Comment.class);
    }

    /**
     * <a href="https://www.flickr.com/services/api/flickr.photos.comments.deleteComment.html">flickr.photos.comments.deleteComment</a>
     * <p/>
     * <p/>
     * Delete a comment as the currently authenticated user.
     * <p/>
     * This method requires authentication with 'write' permission.
     *
     * @param commentId (Required) The id of the comment to delete.
     * @return object with the status of the delete operation.
     * @throws JinxException if required parameters are missing, or if there are any errors.
     */
    public Response deleteComment(String commentId) throws JinxException {
        JinxUtils.validateParams(commentId);
        Map<String, String> params = new TreeMap<String, String>();
        params.put("method", "flickr.photos.comments.deleteComment");
        params.put("comment_id", commentId);
        return jinx.flickrPost(params, Response.class);
    }

    /**
     * <a href="https://www.flickr.com/services/api/flickr.photos.comments.editComment.html">flickr.photos.comments.editComment</a>
     * <p/>
     * Edit the text of a comment as the currently authenticated user.
     * <p/>
     * This method requires authentication with 'write' permission.
     *
     * @param commentId   (Required) The id of the comment to edit.
     * @param commentText (Required) Update the comment to this text.
     * @return object with the status of the edit operation.
     * @throws JinxException if required parameters are missing, or if there are any errors.
     */
    public Response editComment(String commentId, String commentText) throws JinxException {
        JinxUtils.validateParams(commentId, commentText);
        Map<String, String> params = new TreeMap<String, String>();
        params.put("method", "flickr.photos.comments.editComment");
        params.put("comment_id", commentId);
        params.put("comment_text", commentText);
        return jinx.flickrPost(params, Response.class);
    }


    /**
     * <a href="https://www.flickr.com/services/api/flickr.photos.comments.getList.html">flickr.photos.comments.getList</a>
     * <p/>
     * Returns the comments for a photo
     * <p/>
     * This method does not require authentication. You can choose to sign the call by passing true or false
     * as the value of the sign parameter.
     * <p/>
     *
     * @param photoId        (Required) The id of the photo to fetch comments for.
     * @param minCommentDate (Optional) Minimum date that a a comment was added. The date should be in the form of a unix timestamp.
     * @param maxCommentDate (Optional) Maximum date that a comment was added. The date should be in the form of a unix timestamp.
     * @param sign           if true, the request will be signed.
     * @return object with a list of comments for the specified photo.
     * @throws JinxException if required parameters are missing, or if there are any errors.
     */
    public Comments getList(String photoId, String minCommentDate, String maxCommentDate, boolean sign) throws JinxException {
        JinxUtils.validateParams(photoId);
        Map<String, String> params = new TreeMap<String, String>();
        params.put("method", "flickr.photos.comments.getList");
        params.put("photo_id", photoId);
        if (!JinxUtils.isNullOrEmpty(minCommentDate)) {
            params.put("min_comment_date", minCommentDate);
        }
        if (!JinxUtils.isNullOrEmpty(maxCommentDate)) {
            params.put("max_comment_date", maxCommentDate);
        }
        return jinx.flickrGet(params, Comments.class, sign);
    }

    /*

     */

    /**
     * <a href="https://www.flickr.com/services/api/flickr.photos.comments.getRecentForContacts.html">flickr.photos.comments.getRecentForContacts</a>
     * <p/>
     * Return the list of photos belonging to your contacts that have been commented on recently.
     * <p/>
     * This method requires authentication with 'read' permission.
     *
     * @param dateLastComment (Optional) Limits the results to photos that have been commented on since this date.
     *                        The date should be in the form of a Unix timestamp.
     *                        The default, and maximum, offset is one hour.
     * @param contactsFilter  (Optional) A list of user id's to limit the scope of the query to.
     * @param extras          (Optional) Extra information to fetch for each returned record.
     * @param perPage         Number of photos to return per page. If this argument is less than 1, it defaults to 100. The maximum allowed value is 500.
     * @param page            The page of results to return. If this argument is less than 1, it defaults to 1.
     * @return photos object with list of photos from your contacts that have been commented on recently.
     * @throws JinxException if required parameters are missing, or if there are any errors.
     */
    public Photos getRecentForContacts(String dateLastComment, List<String> contactsFilter, EnumSet<JinxConstants.PhotoExtras> extras, int perPage, int page) throws JinxException {
        Map<String, String> params = new TreeMap<String, String>();
        params.put("method", "flickr.photos.comments.getRecentForContacts");
        if (!JinxUtils.isNullOrEmpty(dateLastComment)) {
            params.put("date_lastcomment", dateLastComment);
        }
        if (!JinxUtils.isNullOrEmpty(contactsFilter)) {
            params.put("contacts_filter", JinxUtils.buildCommaDelimitedList(contactsFilter));
        }
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
