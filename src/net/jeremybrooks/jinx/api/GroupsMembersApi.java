/*
 * Jinx is Copyright 2011 by Jeremy Brooks
 *
 * This file is part of Jinx.
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
 */package net.jeremybrooks.jinx.api;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import net.jeremybrooks.jinx.Jinx;
import net.jeremybrooks.jinx.JinxConstants.GroupMemberType;
import net.jeremybrooks.jinx.JinxException;
import net.jeremybrooks.jinx.JinxUtils;
import net.jeremybrooks.jinx.dto.Member;
import net.jeremybrooks.jinx.dto.Members;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

/**
 *
 * @author jeremyb
 */
public class GroupsMembersApi {


    private static GroupsMembersApi instance = null;


    private GroupsMembersApi() {
    }


    public static GroupsMembersApi getInstance() {
	if (GroupsMembersApi.instance == null) {
	    GroupsMembersApi.instance = new GroupsMembersApi();
	}

	return GroupsMembersApi.instance;
    }


    /**
     * Get a list of the members of a group.
     *
     * This method is equivalent to calling
     * <code>getList(groupId, null, 0, 0);</code>
     *
     * @param groupId (required) id of the group to get a list of members for. The group
     *        must be viewable by the Flickr member on whose behalf the API call is made.
     * @return members in the group.
     * @throws JinxException if the groupId is null or empty, or if there are any errors.
     * @see http://www.flickr.com/services/api/flickr.groups.members.getList.html
     */
    public Members getList(String groupId) throws JinxException {
	return this.getList(groupId, null, 0, 0);
    }

    
    /**
     * Get a list of the members of a group. The call must be signed on behalf
     * of a Flickr member, and the ability to see the group membership will be
     * determined by the Flickr member's group privileges.
     *
     * This method requires authentication with 'read' permission.
     *
     * @param groupId (required) id of the group to get a list of members for. The group
     *        must be viewable by the Flickr member on whose behalf the API call is made.
     * @param memberTypes the member types to return. If this parameter is null,
     *        all member types will be returned.
     * @param perPage number of members to return per page. If this argument is
     *        zero, it defaults to 100. The maximum allowed value is 500.
     * @param page The page of results to return. If this argument is zero, it defaults to 1.
     * @return members in the group.
     * @throws JinxException if the groupId is null or empty, or if there are any errors.
     * @see http://www.flickr.com/services/api/flickr.groups.members.getList.html
     */
    public Members getList(String groupId, Set<GroupMemberType> memberTypes, int perPage, int page)
    throws JinxException {
	if (groupId == null || groupId.isEmpty()) {
	    throw new JinxException("Parameter groupId is required.");
	}

	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.groups.members.getList");
	params.put("api_key", Jinx.getInstance().getApiKey());
	params.put("group_id", groupId);

	// Implementation note:
	// membertypes is a comma separated list of member types;
	// Flickr expects the value to be numeric, as follows:
	// 2: member
	// 3: moderator
	// 4: admin
	if (memberTypes != null) {
	    StringBuilder sb = new StringBuilder();
	    for (GroupMemberType gmt : memberTypes) {
		switch(gmt) {
		    case member:
			sb.append("2,");
			break;
		    case moderator:
			sb.append("3,");
			break;
		    case admin:
			sb.append("4,");
			break;
		}
	    }
	    if (sb.lastIndexOf(",") != -1) {
		sb.deleteCharAt(sb.lastIndexOf(","));
	    }
	    params.put("membertypes", sb.toString());
	}
	if (perPage > 0) {
	    params.put("per_page", Integer.toString(perPage));
	}
	if (page > 0) {
	    params.put("page", Integer.toString(page));
	}

	Document doc = Jinx.getInstance().callFlickr(params, true);


	/*
	<rsp stat="ok">
	    <members page="1" pages="870" perpage="100" total="86989">
		<member nsid="58302714@N07" username="Architecture Charlatan" iconserver="5081" iconfarm="6" membertype="2" />
		<member nsid="34307183@N06" username="brecklundin" iconserver="4119" iconfarm="5" membertype="2" />
		<member nsid="54218845@N07" username="my_adamira" iconserver="4152" iconfarm="5" membertype="2" />
	    </members>
	</rsp>
	 */

	Members members = new Members();
	members.setPage(JinxUtils.getValueByXPathAsInt(doc, "/rsp/members/@page"));
	members.setPages(JinxUtils.getValueByXPathAsInt(doc, "/rsp/members/@pages"));
	members.setPerPage(JinxUtils.getValueByXPathAsInt(doc, "/rsp/members/@perpage"));
	members.setTotal(JinxUtils.getValueByXPathAsInt(doc, "/rsp/members/@total"));

	NodeList memberNodes = doc.getElementsByTagName("member");
	if (memberNodes != null) {
	    for (int i = 0; i < memberNodes.getLength(); i++) {
		NamedNodeMap attrs = memberNodes.item(i).getAttributes();
		Member m = new Member();
		m.setNsid(JinxUtils.getAttribute(attrs, "nsid"));
		m.setUsername(JinxUtils.getAttribute(attrs, "username"));
		m.setIconserver(JinxUtils.getAttribute(attrs, "iconserver"));
		m.setIconfarm(JinxUtils.getAttribute(attrs, "iconfarm"));
		int type = JinxUtils.getAttributeAsInt(attrs, "membertype");
		switch (type) {
		    case 2:
			m.setMemberType(GroupMemberType.member);
			break;
		    case 3:
			m.setMemberType(GroupMemberType.moderator);
			break;
		    case 4:
			m.setMemberType(GroupMemberType.admin);
			break;
		}
		members.addMember(m);
	    }
	}

	return members;
    }
}
