/*
 * Jinx is Copyright 2010-2012 by Jeremy Brooks and Contributors
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

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import net.jeremybrooks.jinx.Jinx;
import net.jeremybrooks.jinx.JinxException;
import net.jeremybrooks.jinx.JinxUtils;
import net.jeremybrooks.jinx.dto.Context;
import net.jeremybrooks.jinx.dto.Group;
import net.jeremybrooks.jinx.dto.Groups;
import net.jeremybrooks.jinx.dto.Photo;
import net.jeremybrooks.jinx.dto.Photos;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

/**
 *
 * @author jeremyb
 */
public class GroupsPoolsApi {


    private static GroupsPoolsApi instance = null;


    private GroupsPoolsApi() {
    }


    public static GroupsPoolsApi getInstance() {
	if (GroupsPoolsApi.instance == null) {
	    GroupsPoolsApi.instance = new GroupsPoolsApi();
	}

	return GroupsPoolsApi.instance;
    }

    /**
     * Add a photo to a group's pool.
     *
     * This method requires authentication with 'write' permission.
     *
     * Note: This method uses an HTTP POST request.
     *
     * @param photoId (required) id of the photo to add to the group pool. The photo must
     *        belong to the calling user.
     * @param groupNsid (required) nsid of the group who's pool the photo is to be added to.
     * @throws JinxException if the parameters are null or empty or if there are any errors.
     */
    public void add(String photoId, String groupNsid) throws JinxException {
	if (photoId == null || photoId.isEmpty()) {
	    throw new JinxException("Parameter photoId is required.");
	}
	if (groupNsid == null || groupNsid.isEmpty()) {
	    throw new JinxException("Parameter groupNsid is required.");
	}

	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.groups.pools.add");
	params.put("api_key", Jinx.getInstance().getApiKey());
	params.put("group_id", groupNsid);
	params.put("photo_id", photoId);

	Jinx.getInstance().callFlickr(params, true, true);
    }


    /**
     * Returns next and previous photos for a photo in a group pool.
     *
     * This method does not require authentication.
     *
     * @param photoId (required) id of the photo to fetch context for.
     * @param groupNsid (required) nsid of the group who's pool to fetch the photo's
     *        context for.
     * @return object with the previous and next photo.
     * @throws JinxException if the parameters are null or empty or if there are any errors.
     */
    public Context getContext(String photoId, String groupNsid) throws JinxException {
	if (photoId == null || photoId.isEmpty()) {
	    throw new JinxException("Parameter photoId is required.");
	}
	if (groupNsid == null || groupNsid.isEmpty()) {
	    throw new JinxException("Parameter groupNsid is required.");
	}

	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.groups.pools.getContext");
	params.put("api_key", Jinx.getInstance().getApiKey());
	params.put("group_id", groupNsid);
	params.put("photo_id", photoId);

	Document doc = Jinx.getInstance().callFlickr(params, true);

	/*
	<rsp stat="ok">
	    <count>954944</count>
	    <prevphoto id="4441878573" secret="5765b1909a" server="4016" farm="5"
		title="Back Yard Wild Flowers"
		url="/photos/julio1989/4441878573/in/pool-florus/"
		thumb="http://farm5.static.flickr.com/4016/4441878573_5765b1909a_s.jpg"
		media="photo" license="0" is_faved="0" />
	    <nextphoto id="4707652527" secret="2ee5fec8e7" server="4020" farm="5"
		title="Shelter My Soul, Oh My Love..."
		url="/photos/rejik/4707652527/in/pool-florus/"
		thumb="http://farm5.static.flickr.com/4020/4707652527_2ee5fec8e7_s.jpg"
		media="photo" license="0" is_faved="0" />
	</rsp>
	 */
	Context context = new Context();
	context.setCount(JinxUtils.getValueByXPathAsInt(doc, "/rsp/count"));
	Photo prev = new Photo();
	prev.setId(JinxUtils.getValueByXPath(doc, "/rsp/prevphoto/@id"));
	prev.setSecret(JinxUtils.getValueByXPath(doc, "/rsp/prevphoto/@secret"));
	prev.setServer(JinxUtils.getValueByXPath(doc, "/rsp/prevphoto/@server"));
	prev.setFarm(JinxUtils.getValueByXPath(doc, "/rsp/prevphoto/@farm"));
	prev.setTitle(JinxUtils.getValueByXPath(doc, "/rsp/prevphoto/@title"));
	prev.setUrl(JinxUtils.getValueByXPath(doc, "/rsp/prevphoto/@url"));
	prev.setThumb(JinxUtils.getValueByXPath(doc, "/rsp/prevphoto/@thumb"));
	prev.setMedia(JinxUtils.getValueByXPath(doc, "/rsp/prevphoto/@media"));
	prev.setLicense(JinxUtils.getValueByXPath(doc, "/rsp/prevphoto/@license"));
	prev.setFaved(JinxUtils.getValueByXPathAsBoolean(doc, "/rsp/prevphoto/@is_faved"));

	context.setPreviousPhoto(prev);

	Photo next = new Photo();
	next.setId(JinxUtils.getValueByXPath(doc, "/rsp/nextphoto/@id"));
	next.setSecret(JinxUtils.getValueByXPath(doc, "/rsp/nextphoto/@secret"));
	next.setServer(JinxUtils.getValueByXPath(doc, "/rsp/nextphoto/@server"));
	next.setFarm(JinxUtils.getValueByXPath(doc, "/rsp/nextphoto/@farm"));
	next.setTitle(JinxUtils.getValueByXPath(doc, "/rsp/nextphoto/@title"));
	next.setUrl(JinxUtils.getValueByXPath(doc, "/rsp/nextphoto/@url"));
	next.setThumb(JinxUtils.getValueByXPath(doc, "/rsp/nextphoto/@thumb"));
	next.setMedia(JinxUtils.getValueByXPath(doc, "/rsp/nextphoto/@media"));
	next.setLicense(JinxUtils.getValueByXPath(doc, "/rsp/nextphoto/@license"));
	next.setFaved(JinxUtils.getValueByXPathAsBoolean(doc, "/rsp/nextphoto/@is_faved"));

	context.setNextPhoto(next);

	return context;
    }

    
    /**
     * Returns a list of groups to which you can add photos.
     *
     * This method requires authentication with 'read' permission.
     * This method is equivalent to calling <code>getGroups(0, 0);</code>
     * 
     * @return object with a list of groups to which the user can add photos.
     * @throws JinxException if there are any errors.
     */
    public Groups getGroups() throws JinxException {
	return this.getGroups(0, 0);
    }


    /**
     * Returns a list of groups to which you can add photos.
     *
     * This method requires authentication with 'read' permission.
     *
     * @param page the page of results to return. If this argument is less than
     *        1, it defaults to 1.
     * @param perPage number of groups to return per page. If this argument is
     *        less than 1, it defaults to 400. The maximum allowed value is 400.
     * @return object with a list of groups to which the user can add photos.
     * @throws JinxException if there are any errors.
     */
    public Groups getGroups(int page, int perPage) throws JinxException {
	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.groups.pools.getGroups");
	params.put("api_key", Jinx.getInstance().getApiKey());
	if (page > 0) {
	    params.put("page", Integer.toString(page));
	}
	if (perPage > 0) {
	    params.put("per_page", Integer.toString(perPage));
	}

	Document doc = Jinx.getInstance().callFlickr(params, true);

	/*
	<rsp stat="ok">
	    <groups page="1" pages="1" per_page="400" total="155">
		<group nsid="700404@N25" id="700404@N25" name=" *atomic age*"
		    admin="0" privacy="3" photos="6018" iconserver="3282" iconfarm="4" />
		<group nsid="71332142@N00" id="71332142@N00" name=" Catchy Colors"
		    admin="0" privacy="3" photos="2526444" iconserver="12" iconfarm="1" />
		<group nsid="895725@N22" id="895725@N22" name="$2 Portraits"
		    admin="0" privacy="3" photos="638" iconserver="3177" iconfarm="4" />
		<group nsid="1593121@N24" id="1593121@N24" name="-The Hot Box-"
		    admin="0" privacy="2" photos="834" iconserver="5012" iconfarm="6" />
	    </groups>
	</rsp>
	 */
	Groups groups = new Groups();

	groups.setPage(JinxUtils.getValueByXPathAsInt(doc, "/rsp/gruops/@page"));
	groups.setPages(JinxUtils.getValueByXPathAsInt(doc, "/rsp/gruops/@pages"));
	groups.setPerPage(JinxUtils.getValueByXPathAsInt(doc, "/rsp/gruops/@per_page"));
	groups.setTotal(JinxUtils.getValueByXPathAsInt(doc, "/rsp/gruops/@total"));

	NodeList nodes = doc.getElementsByTagName("group");
	if (nodes != null) {
	    for (int i = 0; i < nodes.getLength(); i++) {
		NamedNodeMap attrs = nodes.item(i).getAttributes();
		Group g = new Group();
		g.setNsid(JinxUtils.getAttribute(attrs, "nsid"));
		g.setId(JinxUtils.getAttribute(attrs, "id"));
		g.setName(JinxUtils.getAttribute(attrs, "name"));
		g.setAdmin(JinxUtils.getAttributeAsBoolean(attrs, "admin"));
		g.setPrivacy(JinxUtils.getAttribute(attrs, "privacy"));
		g.setPhotos(JinxUtils.getAttributeAsInt(attrs, "photos"));
		g.setIconserver(JinxUtils.getAttribute(attrs, "iconserver"));
		g.setIconfarm(JinxUtils.getAttribute(attrs, "iconfarm"));
		
		groups.addGroup(g);
	    }
	}

	return groups;
    }


    /**
     * Returns a list of pool photos for a given group, based on the permissions
     * of the group and the user logged in (if any).
     *
     * This method does not require authentication.
     *
     * This method is equivalent to calling <code>getPhotos(groupNsid, null, null, null, 0, 0);</code>.
     *
     * @param groupNsid (required) nsid of the group who's pool you which to get the photo list for.
     * @return data about the photos in the group.
     * @throws JinxException if groupNsid is null or empty, or if there are any errors.
     */
    public Photos getPhotos(String groupNsid) throws
	    JinxException {
	return this.getPhotos(groupNsid, null, null, null, 0, 0);
    }

    
    /**
     * Returns a list of pool photos for a given group, based on the permissions
     * of the group and the user logged in (if any).
     *
     * This method does not require authentication.
     *
     * Extras:
     * You can include extras from JinxConstants.EXTRAS*
     * Currently supported fields include:
     * EXTRAS_DESCRIPTION
     * EXTRAS_LICENSE
     * EXTRAS_DATE_UPLOAD
     * EXTRAS_DATE_TAKEN
     * EXTRAS_OWNER_NAME
     * EXTRAS_ICON_SERVER
     * EXTRAS_ORIGINAL_FORMAT
     * EXTRAS_LAST_UPDATE
     * EXTRAS_GEO
     * EXTRAS_TAGS
     * EXTRAS_MACHINE_TAGS
     * EXTRAS_O_DIMS
     * EXTRAS_VIEWS
     * EXTRAS_MEDIA
     * EXTRAS_PATH_ALIAS
     * EXTRAS_URL_SQ
     * EXTRAS_URL_T
     * EXTRAS_URL_S
     * EXTRAS_URL_M
     * EXTRAS_URL_Z
     * EXTRAS_URL_L
     * EXTRAS_URL_O
     *
     * @param groupNsid (required) nsid of the group who's pool you which to get the photo list for.
     * @param tag a tag to filter the pool with. At the moment only one tag at
     *        a time is supported. Pass in null if you do not want to filter by tag.
     * @param userNsid nsid of a user. Specifying this parameter will retrieve
     *       only those photos that the user has contributed to the group pool. Pass
     *       in null if you want photos by all users.
     * @param extras a list of extras to fetch for every returned record. Can be null
     *        or empty.
     * @param page page of results to return. If this argument is less than 1, it defaults to 1.

     * @param perPage number of photos to return per page. If this argument is
     *        less than 1, it defaults to 100. The maximum allowed value is 500.
     * @return data about the photos in the group.
     * @throws JinxException if groupNsid is null or empty, or if there are any errors.
     */
    public Photos getPhotos(String groupNsid, String tag, String userNsid,
	    List<String> extras, int page, int perPage) throws JinxException {

	if (groupNsid == null || groupNsid.isEmpty()) {
	    throw new JinxException("Parameter groupNsid is required.");
	}

	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.groups.pools.getPhotos");
	params.put("api_key", Jinx.getInstance().getApiKey());
	params.put("group_id", groupNsid);
	if (tag != null && tag.trim().length() > 0) {
	    params.put("tags", tag);
	}
	if (userNsid != null && userNsid.trim().length() > 0) {
	    params.put("user_id", userNsid);
	}
	if (extras != null && extras.size() > 0) {
	    StringBuilder sb = new StringBuilder();
	    for (String s : extras) {
		if (s != null && s.trim().length() > 0) {
		    sb.append(s.trim()).append(',');
		}
	    }
	    sb.deleteCharAt(sb.lastIndexOf(","));

	    params.put("extras", sb.toString());
	}
	if (page > 0) {
	    params.put("page", Integer.toString(page));
	}
	if (perPage > 0) {
	    params.put("per_page", Integer.toString(perPage));
	}

	Document doc = Jinx.getInstance().callFlickr(params, true);

	/*
	<rsp stat="ok">
	    <photos page="1" pages="64822" perpage="3" total="194466">
		<photo id="5687835617" owner="33715381@N03" secret="f1ea66f5f3" server="5105" farm="6" title="18/52" ispublic="1" isfriend="0" isfamily="0" ownername="ChrisSinn" dateadded="1304544877" />
		<photo id="5684540481" owner="52559257@N02" secret="885962c5ce" server="5189" farm="6" title="2043" ispublic="1" isfriend="0" isfamily="0" ownername="benbobjr" dateadded="1304544501" />
		<photo id="5687771715" owner="55289364@N06" secret="2b850df31c" server="5308" farm="6" title="resting place" ispublic="1" isfriend="0" isfamily="0" ownername="joann13833" dateadded="1304541840" />
	    </photos>
	</rsp>
	 */

	return PhotosApi.getInstance().parsePhotosXml(doc);
    }
    

    /**
     * Remove a photo from a group pool.
     *
     * This method requires authentication with 'write' permission.
     *
     * Note: This method uses an HTTP POST request.
     *
     * @param photoId (required) id of the photo to remove from the group pool.
     *        The photo must either be owned by the calling user of the calling
     *        user must be an administrator of the group.
     * @param groupNsid (required) the nsid of the group who's pool the photo is to be removed from.
     * @throws JinxException if any parameter is null or empty, or if there are any errors.
     */
    public void remove(String photoId, String groupNsid) throws JinxException {
	if (photoId == null || photoId.isEmpty()) {
	    throw new JinxException("Parameter photoId is required.");
	}
	if (groupNsid == null || groupNsid.isEmpty()) {
	    throw new JinxException("Parameter groupNsid is required.");
	}

	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.groups.pools.remove");
	params.put("api_key", Jinx.getInstance().getApiKey());
	params.put("group_id", groupNsid);
	params.put("photo_id", photoId);

	Jinx.getInstance().callFlickr(params, true, true);
    }
}
