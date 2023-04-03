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
import net.jeremybrooks.jinx.JinxConstants;
import net.jeremybrooks.jinx.JinxException;
import net.jeremybrooks.jinx.JinxUtils;
import net.jeremybrooks.jinx.response.groups.members.Members;

import java.util.EnumSet;
import java.util.Map;
import java.util.TreeMap;

/**
 * Provides access to the flickr.groups.members API methods.
 *
 * @author Jeremy Brooks
 * @see <a href="https://www.flickr.com/services/api/">Flickr API documentation</a> for more details.
 */
public class GroupsMembersApi {
  private final Jinx jinx;

  public GroupsMembersApi(Jinx jinx) {
    this.jinx = jinx;
  }

  /**
   * <br>
   * Get a list of the members of a group. The call must be signed on behalf of a Flickr member,
   * and the ability to see the group membership will be determined by the Flickr member's group privileges.
   * <br>
   * This method requires authentication with 'read' permission.
   *
   * @param groupId     (Required) Return a list of members for this group. The group must be viewable by the Flickr member on whose behalf the API call is made.
   * @param memberTypes (Optional) Return only these member types. If null, return all member types.
   *                    (Returning super rare member type "1: narwhal" isn't supported by this API method)
   * @param perPage     number of members to return per page. If this argument is less than 1, it defaults to 100. The maximum allowed value is 500.
   * @param page        page of results to return. If this argument is less than 1, it defaults to 1.
   * @return members object containing metadata and a list of members.
   * @throws JinxException if required parameters are null or empty, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.groups.members.getList.html">flickr.groups.members.getList</a>
   */
  public Members getList(String groupId, EnumSet<JinxConstants.MemberType> memberTypes, int perPage, int page) throws JinxException {
    JinxUtils.validateParams(groupId);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.groups.members.getList");
    params.put("group_id", groupId);
    if (memberTypes != null) {
      StringBuilder sb = new StringBuilder();
      for (JinxConstants.MemberType type : memberTypes) {
        sb.append(JinxUtils.memberTypeToMemberTypeId(type)).append(',');
      }
      params.put("membertypes", sb.deleteCharAt(sb.length() - 1).toString());
    }
    if (perPage > 0) {
      params.put("per_page", Integer.toString(perPage));
    }
    if (page > 0) {
      params.put("page", Integer.toString(page));
    }
    return jinx.flickrGet(params, Members.class);
  }
}
