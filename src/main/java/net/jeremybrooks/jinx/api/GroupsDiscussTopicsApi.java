/*
 * Jinx is Copyright 2010-2023 by Jeremy Brooks and Contributors
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
import net.jeremybrooks.jinx.response.groups.discuss.topics.TopicInfo;
import net.jeremybrooks.jinx.response.groups.discuss.topics.Topics;

import java.util.Map;
import java.util.TreeMap;

/**
 * Provides access to the flickr.groups.discuss.topics API methods.
 *
 * @author Jeremy Brooks
 * @see <a href="https://www.flickr.com/services/api/">Flickr API documentation</a> for more details.
 */
public class GroupsDiscussTopicsApi {
  private Jinx jinx;

  public GroupsDiscussTopicsApi(Jinx jinx) {
    this.jinx = jinx;
  }


  /**
   * Post a new discussion topic to a group.
   * <br>
   * This method requires authentication with 'write' permission.
   *
   * @param groupId (Required) The NSID of the group to add a topic to.
   * @param subject (Required) The topic subject.
   * @param message (Required) The topic message.
   * @return object with response from Flickr indicating ok or fail.
   * @throws JinxException if required parameters are missing, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.groups.discuss.topics.add.html">flickr.groups.discuss.topics.add</a>
   */
  public Response add(String groupId, String subject, String message) throws JinxException {
    JinxUtils.validateParams(groupId, subject, message);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.groups.discuss.topics.add");
    params.put("group_id", groupId);
    params.put("subject", subject);
    params.put("message", message);
    return jinx.flickrPost(params, Response.class);
  }

  /**
   * Get information about a group discussion topic.
   * <br>
   * This method does not require authentication. Unsigned requests can only see public topics.
   *
   * @param topicId (Required) The ID for the topic to get info for.
   * @param sign    if true, the request will be signed.
   * @return information about the topic.
   * @throws JinxException if required parameters are missing, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.groups.discuss.topics.getInfo.html">flickr.groups.discuss.topics.getInfo</a>
   */
  public TopicInfo getInfo(String topicId, boolean sign) throws JinxException {
    JinxUtils.validateParams(topicId);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.groups.discuss.topics.getInfo");
    params.put("topic_id", topicId);
    return jinx.flickrGet(params, TopicInfo.class, sign);
  }


  /**
   * Get a list of discussion topics in a group.
   * <br>
   * This method does not require authentication. Unsigned requests can only see public topics.
   *
   * @param groupId (Required) The NSID of the group to fetch information for.
   * @param perPage Number of photos to return per page. If this argument is less than 1, it defaults to 100.
   *                The maximum allowed value is 500.
   * @param page    The page of results to return. If this argument is less than 1, it defaults to 1.
   * @param sign    if true, the request will be signed.
   * @return object with topic metadata and a list of topics.
   * @throws JinxException if required parameters are missing, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.groups.discuss.topics.getList.html">flickr.groups.discuss.topics.getList</a>
   */
  public Topics getList(String groupId, int perPage, int page, boolean sign) throws JinxException {
    JinxUtils.validateParams(groupId);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.groups.discuss.topics.getList");
    params.put("group_id", groupId);
    if (perPage > 0) {
      params.put("per_page", Integer.toString(perPage));
    }
    if (page > 0) {
      params.put("page", Integer.toString(page));
    }
    return jinx.flickrGet(params, Topics.class, sign);
  }
}
