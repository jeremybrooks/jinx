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

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import net.jeremybrooks.jinx.Jinx;
import net.jeremybrooks.jinx.JinxException;
import net.jeremybrooks.jinx.JinxUtils;
import net.jeremybrooks.jinx.dto.Contact;
import net.jeremybrooks.jinx.dto.Contacts;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 *
 * @author jeremyb
 */
public class ContactsApi {

    public enum ContactListFilter {
	friends, family, both, neither;
    }

    public enum ContactListUploadedFilter {
	friendsAndFamily, all;
    }

    private static ContactsApi instance = null;


    private ContactsApi() {
    }


    public static ContactsApi getInstance() {
	if (ContactsApi.instance == null) {
	    ContactsApi.instance = new ContactsApi();
	}

	return ContactsApi.instance;
    }

    
    /**
     * Get a list of contacts for the calling user.
     * This method requires authentication with 'read' permission.
     *
     * @param filter optional filter of the results.
     * @param page the page of results to return. If this argument is zero, it
     *        defaults to 1.
     * @param perPage number of photos to return per page. If this argument is
     *        zero, it defaults to 1000. If this argument is > 1000, it defaults
     *        to 1000.
     * @return contacts object with the results of the call.
     * @throws JinxException if there are any errors.
     * @see http://www.flickr.com/services/api/flickr.contacts.getList.html
     */
    public Contacts getList(ContactListFilter filter, int page, int perPage) throws JinxException {
	Contacts contacts = new Contacts();

	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.contacts.getList");
	params.put("api_key", Jinx.getInstance().getApiKey());

	if (filter != null) {
	    params.put("filter", filter.toString());
	}
	if (page > 0) {
	    params.put("page", Integer.toString(page));
	}
	if (perPage > 0) {
	    if (perPage > 1000) {
		perPage = 1000;
	    }
	    params.put("per_page", Integer.toString(perPage));
	}


	/*
	    <?xml version="1.0" encoding="utf-8" ?>
	    <rsp stat="ok">
		<contacts page="1" pages="3" per_page="1000" perpage="1000" total="2140">
		    <contact nsid="7905214@N04" username="&quot;Cisco Kid&quot;"
			iconserver="2659" iconfarm="3" ignored="0" realname="Greg/Starr"
			friend="0" family="0" path_alias="g-starr"
			location="San Francisco  California, United States" />
		    <contact nsid="85433961@N00" username="$A®0 ®0©K"
			iconserver="4084" iconfarm="5" ignored="0" realname="Oras"
			friend="0" family="0" path_alias="sarorockschicago"
			location="chicago, red white and blue" />
		</contacts>
	    </rsp>
	 */
	
	Document doc = Jinx.getInstance().callFlickr(params);

	contacts.setPage(JinxUtils.getValueByXPathAsInt(doc, "/rsp/contacts/@page"));
	contacts.setPages(JinxUtils.getValueByXPathAsInt(doc, "/rsp/contacts/@pages"));
	contacts.setPerPage(JinxUtils.getValueByXPathAsInt(doc, "/rsp/contacts/@per_page"));
	contacts.setTotal(JinxUtils.getValueByXPathAsInt(doc, "/rsp/contacts/@total"));

	NodeList contactNodes = doc.getElementsByTagName("contact");
	for (int i = 0; i < contactNodes.getLength(); i++) {
	    Contact c = new Contact();
	    Node cNode = contactNodes.item(i);
	    NamedNodeMap attrs = cNode.getAttributes();
	    c.setNsid(JinxUtils.getAttribute(attrs, "nsid"));
	    c.setUsername(JinxUtils.getAttribute(attrs, "username"));
	    c.setIconServer(JinxUtils.getAttribute(attrs, "iconserver"));
	    c.setIconFarm(JinxUtils.getAttribute(attrs, "iconfarm"));
	    c.setIgnored(JinxUtils.getAttributeAsBoolean(attrs, "ignored"));
	    c.setRealName(JinxUtils.getAttribute(attrs, "realname"));
	    c.setFriend(JinxUtils.getAttributeAsBoolean(attrs, "friend"));
	    c.setFamily(JinxUtils.getAttributeAsBoolean(attrs, "family"));
	    c.setPathAlias(JinxUtils.getAttribute(attrs, "path_alias"));
	    c.setLocation(JinxUtils.getAttribute(attrs, "location"));

	    contacts.addContact(c);
	}

	return contacts;
    }


    /**
     * Return a list of contacts for a user who have recently uploaded photos
     * along with the total count of photos uploaded.
     *
     * From the Flickr documentation:
     * This method is still considered experimental. We don't plan for it to
     * change or to go away but so long as this notice is present you should
     * write your code accordingly.
     *
     * This method requires authentication with 'read' permission.
     * 
     * @param filter limit results to all contacts, or only friends and family.
     *        If this parameter is null, it will default to all.
     * @param lastUpload limits the results to contacts that have uploaded photos
     *        since this date. If this parameter is null, it will default to
     *        one hour. The maximum allowed is 24 hours.
     * @return contacts object with the results of the call.
     * @throws JinxException if there are any errors.
     * @see http://www.flickr.com/services/api/flickr.contacts.getListRecentlyUploaded.html
     */
    public Contacts getContactsRecentlyUploaded(ContactListUploadedFilter filter, Date lastUpload) throws JinxException {
	Contacts contacts = new Contacts();

	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.contacts.getListRecentlyUploaded");
	params.put("api_key", Jinx.getInstance().getApiKey());

	if (filter != null) {
	    if (filter == ContactListUploadedFilter.friendsAndFamily) {
		params.put("filter", "ff");
	    } else {
		params.put("filter", filter.toString());
	    }
	}
	if (lastUpload != null) {
	    params.put("date_lastupload", JinxUtils.formatDateAsUnixTimestamp(lastUpload));
	}


	/*
	    <?xml version="1.0" encoding="utf-8" ?>
	    <rsp stat="ok">
		<contacts total="8">
		    <contact nsid="36521976686@N01" username="twob"
			iconserver="2" iconfarm="1" realname="Björn Rixman"
			friend="0" family="0" path_alias="twob" photos_uploaded="1" />
		    <contact nsid="53611153@N00" username="Darwin Bell"
			iconserver="36" iconfarm="1" realname="darwin Bell"
			friend="0" family="0" path_alias="darwinbell" photos_uploaded="1" />
		</contacts>
	    </rsp>
	 */
	Document doc = Jinx.getInstance().callFlickr(params);

	contacts.setTotal(JinxUtils.getValueByXPathAsInt(doc, "/rsp/contacts/@total"));

	NodeList contactNodes = doc.getElementsByTagName("contact");
	for (int i = 0; i < contactNodes.getLength(); i++) {
	    Contact c = new Contact();
	    Node cNode = contactNodes.item(i);
	    NamedNodeMap attrs = cNode.getAttributes();
	    c.setNsid(JinxUtils.getAttribute(attrs, "nsid"));
	    c.setUsername(JinxUtils.getAttribute(attrs, "username"));
	    c.setIconServer(JinxUtils.getAttribute(attrs, "iconserver"));
	    c.setIconFarm(JinxUtils.getAttribute(attrs, "iconfarm"));
	    c.setRealName(JinxUtils.getAttribute(attrs, "realname"));
	    c.setFriend(JinxUtils.getAttributeAsBoolean(attrs, "friend"));
	    c.setFamily(JinxUtils.getAttributeAsBoolean(attrs, "family"));
	    c.setPathAlias(JinxUtils.getAttribute(attrs, "path_alias"));
	    c.setPhotosUploaded(JinxUtils.getAttributeAsInt(attrs, "photos_uploaded"));

	    contacts.addContact(c);
	}


	return contacts;
    }


    /**
     * Get the contact list for a user.
     *
     * This method does not require authentication.
     *
     * @param userId the NSID of the user to fetch the contact list for.
     *        This parameter is required.
     * @param page optional page of the results to return. If this argument is
     *        zero, it defaults to 1.
     * @param perPage optional number of photos to return per page. If this
     *        argument is zero, it defaults to 1000. If this argument is > 1000,
     *        it defaults to 1000.
     * @return contacts object with the results of the call.
     * @throws JinxException if the userId is null or empty, or if there are
     *         any errors.
     * @see http://www.flickr.com/services/api/flickr.contacts.getPublicList.html
     */
    public Contacts getPublicList(String userId, int page, int perPage) throws JinxException {
	if (userId == null || userId.trim().isEmpty()) {
	    throw new JinxException("getPublicList: Parameter userId is required.");
	}
	
	Contacts contacts = new Contacts();

	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.contacts.getPublicList");
	params.put("api_key", Jinx.getInstance().getApiKey());
	params.put("user_id", userId);
	if (page > 0) {
	    params.put("page", Integer.toString(page));
	}
	if (perPage > 0) {
	    if (perPage > 1000) {
		perPage = 1000;
	    }
	    params.put("per_page", Integer.toString(perPage));
	}


	/*
	    <?xml version="1.0" encoding="utf-8" ?>
	    <rsp stat="ok">
		<contacts page="1" pages="1" per_page="1000" perpage="1000" total="114">
		    <contact nsid="37451064@N00" username="*Cinnamon"
			iconserver="33" iconfarm="1" ignored="0" />
		    <contact nsid="35882055@N03" username="*sage*"
			iconserver="2565" iconfarm="3" ignored="0" />
		</contacts>
	    </rsp>
	 */

	Document doc = Jinx.getInstance().callFlickr(params, false);

	contacts.setPage(JinxUtils.getValueByXPathAsInt(doc, "/rsp/contacts/@page"));
	contacts.setPages(JinxUtils.getValueByXPathAsInt(doc, "/rsp/contacts/@pages"));
	contacts.setPerPage(JinxUtils.getValueByXPathAsInt(doc, "/rsp/contacts/@per_page"));
	contacts.setTotal(JinxUtils.getValueByXPathAsInt(doc, "/rsp/contacts/@total"));

	NodeList contactNodes = doc.getElementsByTagName("contact");
	for (int i = 0; i < contactNodes.getLength(); i++) {
	    Contact c = new Contact();
	    Node cNode = contactNodes.item(i);
	    NamedNodeMap attrs = cNode.getAttributes();
	    c.setNsid(JinxUtils.getAttribute(attrs, "nsid"));
	    c.setUsername(JinxUtils.getAttribute(attrs, "username"));
	    c.setIconServer(JinxUtils.getAttribute(attrs, "iconserver"));
	    c.setIconFarm(JinxUtils.getAttribute(attrs, "iconfarm"));
	    c.setIgnored(JinxUtils.getAttributeAsBoolean(attrs, "ignored"));

	    contacts.addContact(c);
	}

	return contacts;
    }
}
