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
 */
package net.jeremybrooks.jinx.api;

import java.util.Map;
import java.util.TreeMap;
import net.jeremybrooks.jinx.Jinx;
import net.jeremybrooks.jinx.JinxException;
import net.jeremybrooks.jinx.JinxUtils;
import net.jeremybrooks.jinx.dto.Blast;
import net.jeremybrooks.jinx.dto.Category;
import net.jeremybrooks.jinx.dto.Group;
import net.jeremybrooks.jinx.dto.Groups;
import net.jeremybrooks.jinx.dto.Restrictions;
import net.jeremybrooks.jinx.dto.Subcat;
import net.jeremybrooks.jinx.dto.Throttle;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author jeremyb
 */
public class GroupsApi {


    /**
     * Browse the group category tree, finding groups and sub-categories.
     * 
     * This method requires authentication with 'read' permission.
     * 
     * @param categoryId the category id to fetch a list of groups and
     *	      sub-categories for. If not specified, it defaults to zero,
     *        the root of the category tree.
     * @return category object containing information about the category tree,
     *         groups, and sub-categories.
     * @throws JinxException if there are any errors.
     * @see http://www.flickr.com/services/api/flickr.groups.browse.html
     */
    public static Category browse(String categoryId) throws JinxException {
	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.groups.browse");
	params.put("api_key", Jinx.getInstance().getApiKey());
	if (categoryId != null && categoryId.trim().length() > 0) {
	    params.put("cat_id", categoryId);
	}

	Document doc = Jinx.getInstance().callFlickr(params, true);

	/*
	 * <rsp>
	 <category name="Alt" path="/Alt" pathids="/63">
	   <subcat id="80" name="18+" count="0" />
	   <subcat id="82" name="Absurd" count="4" />
	   <group nsid="34955637532@N01" name="Cal's Public Test Group"
		members="13" online="1" chatnsid="34955637533@N01" inchat="0" />
	   <group nsid="34158032587@N01" name="Eric's Alt Group Test"
		members="3" online="0" chatnsid="34158032588@N01" inchat="0" />
         </category>
	 * </rsp>
	 */

	 Category cat = new Category();
	 cat.setName(JinxUtils.getValueByXPath(doc, "/rsp/category/@name"));
	 cat.setPath(JinxUtils.getValueByXPath(doc, "/rsp/category/@path"));
	 cat.setPathId(JinxUtils.getValueByXPath(doc, "/rsp/category/@pathids"));

	 NodeList subcatNodes = doc.getElementsByTagName("subcat");
	 if (subcatNodes != null) {
	     for (int i = 0; i < subcatNodes.getLength(); i++) {
		 Node subcatNode = subcatNodes.item(i);
		 Subcat subcat = new Subcat();
		 NamedNodeMap attrs = subcatNode.getAttributes();
		 subcat.setId(JinxUtils.getAttribute(attrs, "id"));
		 subcat.setName(JinxUtils.getAttribute(attrs, "name"));
		 subcat.setCount(JinxUtils.getAttribute(attrs, "count"));
		 cat.addSubcat(subcat);
	     }
	 }

	 NodeList groupNodes = doc.getElementsByTagName("group");
	 if (groupNodes != null) {
	     for (int i = 0; i < groupNodes.getLength(); i++) {
		 Node groupNode = groupNodes.item(i);
		 Group group = new Group();
		 NamedNodeMap attrs = groupNode.getAttributes();
		 group.setId(JinxUtils.getAttribute(attrs, "nsid"));
		 group.setName(JinxUtils.getAttribute(attrs, "name"));
		 group.setMembers(JinxUtils.getAttributeAsInt(attrs, "members"));
		 group.setOnline(JinxUtils.getAttribute(attrs, "online"));
		 group.setChatnsid(JinxUtils.getAttribute(attrs, "chatnsid"));
		 group.setInchat(JinxUtils.getAttribute(attrs, "inchat"));
		 cat.addGroup(group);
	     }
	 }
	 return cat;

    }

    
    /**
     * Get information about a group.
     * This method does not require authentication.
     *
     * @param groupId The NSID of the group to fetch information for. Required.
     * @param lang The language of the group name and description to fetch.
     *        If the language is not found, the primary language of the group
     *        will be returned. Valid values are the same as in feeds.
     * @return information about the group.
     * @throws JinxException if groupId is null or empty, or if there are any errors.
     * @see http://www.flickr.com/services/api/flickr.groups.getInfo.html
     */
    public static Group getInfo(String groupId, String lang) throws JinxException {
	if (groupId == null || groupId.isEmpty()) {
	    throw new JinxException("Parameter groupId is required.");
	}

	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.groups.getInfo");
	params.put("api_key", Jinx.getInstance().getApiKey());
	params.put("group_id", groupId);
	if (lang != null && lang.trim().length() > 0) {
	    params.put("lang", lang);
	}

	Document doc = Jinx.getInstance().callFlickr(params, true);

	/*
	 <?xml version="1.0" encoding="utf-8" ?>
	 <rsp stat="ok">
	   <group id="11947580@N00" iconserver="4" iconfarm="1" lang="" ispoolmoderated="1">
	     <name>Night Images</name>
	     <description>This is the place to showcase your images taken in the dark of night.</description>
	     <members>86983</members>
	     <privacy>3</privacy>
	     <blast date_blast_added="1302759833" user_id="1754771">Please review the group rules noted below.</blast>
	     <throttle count="1" mode="day" remaining="1" />
	     <restrictions photos_ok="1" videos_ok="1" images_ok="1" screens_ok="1"
	                   art_ok="1" safe_ok="1" moderate_ok="0" restricted_ok="0" has_geo="0" />
           </group>
         </rsp>
	 */

	Group group = new Group();
	group.setId(JinxUtils.getValueByXPath(doc, "/rsp/group/@id"));
	group.setIconserver(JinxUtils.getValueByXPath(doc, "/rsp/group/@iconserver"));
	group.setIconfarm(JinxUtils.getValueByXPath(doc, "/rsp/group/@iconfarm"));
	group.setLang(JinxUtils.getValueByXPath(doc, "/rsp/group/lang"));
	group.setPoolModerated(JinxUtils.getValueByXPathAsBoolean(doc, "/rsp/group/ispoolmoderated"));
	group.setName(JinxUtils.getValueByXPath(doc, "/rsp/group/name"));
	group.setDescription(JinxUtils.getValueByXPath(doc, "/rsp/group/description"));
	group.setMembers(JinxUtils.getValueByXPathAsInt(doc, "/rsp/group/members"));
	group.setPrivacy(JinxUtils.getValueByXPath(doc, "/rsp/group/privacy"));

	Blast blast = new Blast();
	blast.setDateBlastAdded(JinxUtils.getValueByXPath(doc, "/rsp/group/blast/@date_blast_added"));
	blast.setUserId(JinxUtils.getValueByXPath(doc, "/rsp/group/blast/@user_id"));
	blast.setText(JinxUtils.getValueByXPath(doc, "/rsp/group/blast"));
	group.setBlast(blast);

	Throttle throttle = new Throttle();
	throttle.setCount(JinxUtils.getValueByXPathAsInt(doc, "/rsp/group/throttle/@count"));
	throttle.setMode(JinxUtils.getValueByXPath(doc, "/rsp/group/throttle/@mode"));
	throttle.setRemaining(JinxUtils.getValueByXPathAsInt(doc, "/rsp/group/throttle/@remaining"));
	group.setThrottle(throttle);

	Restrictions r = new Restrictions();
	r.setPhotosOk(JinxUtils.getValueByXPathAsBoolean(doc, "/rsp/group/restrictions/@photos_ok"));
	r.setVideosOk(JinxUtils.getValueByXPathAsBoolean(doc, "/rsp/group/restrictions/@videos_ok"));
	r.setImagesOk(JinxUtils.getValueByXPathAsBoolean(doc, "/rsp/group/restrictions/@images_ok"));
	r.setScreensOk(JinxUtils.getValueByXPathAsBoolean(doc, "/rsp/group/restrictions/@screens_ok"));
	r.setArtOk(JinxUtils.getValueByXPathAsBoolean(doc, "/rsp/group/restrictions/@art_ok"));
	r.setSafeOk(JinxUtils.getValueByXPathAsBoolean(doc, "/rsp/group/restrictions/@safe_ok"));
	r.setModerateOk(JinxUtils.getValueByXPathAsBoolean(doc, "/rsp/group/restrictions/@moderate_ok"));
	r.setRestrictedOk(JinxUtils.getValueByXPathAsBoolean(doc, "/rsp/group/restrictions/@restricted_ok"));
	r.setHasGeo(JinxUtils.getValueByXPathAsBoolean(doc, "/rsp/group/restrictions/@has_geo"));


	return group;
    }


    /**
     * Search for groups. 18+ groups will only be returned for authenticated
     * calls where the authenticated user is over 18.
     * This method does not require authentication.
     *
     * This method is equivalent to calling 
     * <code>GroupsApi.search(text, 0, 0);</code>
     * 
     * @param text the text to search for (required).
     * @return list of groups that match the search.
     * @throws JinxException if text is null or empty, or if there are any errors.
     * @see http://www.flickr.com/services/api/flickr.groups.search.html
     */
    public static Groups search(String text) throws JinxException {
	return GroupsApi.search(text, 0, 0);
    }


    /**
     * Search for groups. 18+ groups will only be returned for authenticated
     * calls where the authenticated user is over 18.
     * This method does not require authentication.
     *
     * @param text the text to search for (required).
     * @param perPage number of groups to return per page.
     *        If this argument is zero, it defaults to 100.
     *        The maximum allowed value is 500.
     * @param page page of results to return.
     *        If this argument is zero, it defaults to 1.
     * @return list of groups that match the search.
     * @throws JinxException if text is null or empty, or if there are any errors.
     * @see http://www.flickr.com/services/api/flickr.groups.search.html
     */
    public static Groups search(String text, int perPage, int page) throws JinxException {
	if (text == null || text.isEmpty()) {
	    throw new JinxException("Parameter text is required.");
	}

	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.groups.search");
	params.put("api_key", Jinx.getInstance().getApiKey());
	params.put("text", text);
	if (perPage > 0) {
	    params.put("per_page", Integer.toString(perPage));
	}
	if (page > 0) {
	    params.put("page", Integer.toString(page));
	}

	Document doc = Jinx.getInstance().callFlickr(params, true);

	/*
	<rsp stat="ok">
	    <groups page="1" pages="14" perpage="5" total="67">
		<group nsid="3000@N02"
		name="Frito's Test Group" eighteenplus="0" />
		<group nsid="32825757@N00"
		name="Free for All" eighteenplus="0" />
		<group nsid="33335981560@N01"
		name="joly's mothers" eighteenplus="0" />
		<group nsid="33853651681@N01"
		name="Wintermute tower" eighteenplus="0" />
		<group nsid="33853651696@N01"
		name="Art and Literature Hoedown" eighteenplus="0" />
	    </groups>
	 </rs/>
        */
	Groups groups = new Groups();
	groups.setPage(JinxUtils.getValueByXPathAsInt(doc, "/rsp/groups/@page"));
	groups.setPages(JinxUtils.getValueByXPathAsInt(doc, "/rsp/groups/@pages"));
	groups.setPerPage(JinxUtils.getValueByXPathAsInt(doc, "/rsp/groups/@perpage"));
	groups.setTotal(JinxUtils.getValueByXPathAsInt(doc, "/rsp/groups/@total"));

	NodeList groupNodes = doc.getElementsByTagName("group");
	if (groupNodes != null) {
	    for (int i = 0; i < groupNodes.getLength(); i++) {
		NamedNodeMap attrs =  groupNodes.item(i).getAttributes();
		Group g = new Group();
		g.setId(JinxUtils.getAttribute(attrs, "nsid"));
		g.setName(JinxUtils.getAttribute(attrs, "name"));
		g.setEighteenPlus(JinxUtils.getAttributeAsBoolean(attrs, "eighteenplus"));

		groups.addGroup(g);
	    }
	}

	return groups;
    }


}
