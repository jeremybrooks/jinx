/*
 * Jinx is Copyright 2010-2017 by Jeremy Brooks and Contributors
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
import net.jeremybrooks.jinx.response.groups.discuss.replies.Replies;
import net.jeremybrooks.jinx.response.groups.discuss.replies.ReplyInfo;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author Jeremy Brooks
 */
public class GroupsDiscussRepliesApi {
    private Jinx jinx;

    public GroupsDiscussRepliesApi(Jinx jinx) {
        this.jinx = jinx;
    }

    /**
     * Post a new reply to a group discussion topic.
     * <br>
     * This method requires authentication with 'write' permission.
     *
     * @param topicId (Required) The ID of the topic to post a comment to.
     * @param message (Required) The message to post to the topic.
     * @return object with response from Flickr indicating ok or fail.
     * @throws JinxException if required parameters are missing, or if there are any errors.
     * @see <a href="https://www.flickr.com/services/api/flickr.groups.discuss.replies.add.html">flickr.groups.discuss.replies.add</a>
     */
    public Response add(String topicId, String message) throws JinxException {
        JinxUtils.validateParams(topicId, message);
        Map<String, String> params = new TreeMap<String, String>();
        params.put("method", "flickr.groups.discuss.replies.add");
        params.put("topic_id", topicId);
        params.put("message", message);
        return jinx.flickrPost(params, Response.class);
    }

    /**
     * Delete a reply from a group topic.
     * <br>
     * This method requires authentication with 'delete' permission.
     *
     * @param topicId (Required) The ID of the topic the post is in.
     * @param replyId (Required) The ID of the reply to delete.
     * @return object with response from Flickr indicating ok or fail.
     * @throws JinxException if required parameters are missing, or if there are any errors.
     * @see <a href="https://www.flickr.com/services/api/flickr.groups.discuss.replies.delete.html">flickr.groups.discuss.replies.delete</a>
     */
    public Response delete(String topicId, String replyId) throws JinxException {
        JinxUtils.validateParams(topicId, replyId);
        Map<String, String> params = new TreeMap<String, String>();
        params.put("method", "flickr.groups.discuss.replies.delete");
        params.put("topic_id", topicId);
        params.put("reply_id", replyId);
        return jinx.flickrPost(params, Response.class);
    }

    /**
     * Edit a topic reply.
     * <br>
     * This method requires authentication with 'write' permission.
     *
     * @param topicId (Required) The ID of the topic the post is in.
     * @param replyId (Required) The ID of the reply post to edit.
     * @param message (Required) The message to edit the post with.
     * @return object with response from Flickr indicating ok or fail.
     * @throws JinxException if required parameters are missing, or if there are any errors.
     * @see <a href="https://www.flickr.com/services/api/flickr.groups.discuss.replies.edit.html">flickr.groups.discuss.replies.edit</a>
     */
    public Response edit(String topicId, String replyId, String message) throws JinxException {
        JinxUtils.validateParams(topicId, replyId, message);
        Map<String, String> params = new TreeMap<String, String>();
        params.put("method", "flickr.groups.discuss.replies.edit");
        params.put("topic_id", topicId);
        params.put("reply_id", replyId);
        params.put("message", message);
        return jinx.flickrPost(params, Response.class);
    }

    /**
     * Get information on a group topic reply.
     * <br>
     *
     * @param topicId (Required) The ID of the topic the post is in.
     * @param replyId (Required) The ID of the reply to fetch.
     * @return reply information.
     * @throws JinxException if required parameters are missing, or if there are any errors.
     * @see <a href="https://www.flickr.com/services/api/flickr.groups.discuss.replies.getInfo.html">flickr.groups.discuss.replies.getInfo</a>
     */
    public ReplyInfo getInfo(String topicId, String replyId) throws JinxException {
        JinxUtils.validateParams(topicId, replyId);
        Map<String, String> params = new TreeMap<String, String>();
        params.put("method", "flickr.groups.discuss.replies.getInfo");
        params.put("topic_id", topicId);
        params.put("reply_id", replyId);
        return jinx.flickrGet(params, ReplyInfo.class);
    }


    /**
     * Get a list of replies from a group discussion topic.
     * <br>
     * This method does not require authentication. Unsigned requests can only get public replies.
     *
     * @param topicId (Required) The ID of the topic to fetch replies for.
     * @param perPage Number of photos to return per page. If this argument is less than 1, it defaults to 100.
     *                The maximum allowed value is 500.
     * @param page    The page of results to return. If this argument is less than 1, it defaults to 1.
     * @param sign    if true, the request will be signed.
     * @return object with the topic information and a list of replies.
     * @throws JinxException if required parameters are missing, or if there are any errors.
     * @see <a href="https://www.flickr.com/services/api/flickr.groups.discuss.replies.getList.html">flickr.groups.discuss.replies.getList</a>
     */
    public Replies getList(String topicId, int perPage, int page, boolean sign) throws JinxException {
        JinxUtils.validateParams(topicId);
        Map<String, String> params = new TreeMap<String, String>();
        params.put("method", "flickr.groups.discuss.replies.getList");
        params.put("topic_id", topicId);
        if (perPage > 0) {
            params.put("per_page", Integer.toString(perPage));
        }
        if (page > 0) {
            params.put("page", Integer.toString(page));
        }
        return jinx.flickrGet(params, Replies.class, sign);
    }
}
