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
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import net.jeremybrooks.jinx.Jinx;
import net.jeremybrooks.jinx.JinxException;
import net.jeremybrooks.jinx.JinxUtils;
import net.jeremybrooks.jinx.dto.Group;
import net.jeremybrooks.jinx.dto.Groups;
import net.jeremybrooks.jinx.dto.Person;
import net.jeremybrooks.jinx.dto.Photos;
import net.jeremybrooks.jinx.dto.User;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

/**
 *
 * @author jeremyb
 */
public class PeopleApi {

    private static PeopleApi instance = null;


    private PeopleApi() {
    }


    public static PeopleApi getInstance() {
	if (PeopleApi.instance == null) {
	    PeopleApi.instance = new PeopleApi();
	}

	return PeopleApi.instance;
    }


    /**
     * Return a user's NSID, given their email address.
     *
     * This method does not require authentication.
     *
     * @param (required) email the email address of the user to find.
     * @return object with user information.
     * @throws JinxException if email is null or if there are any errors.
     * @see http://www.flickr.com/services/api/flickr.people.findByEmail.html
     */
    public User findByEmail(String email) throws JinxException {
	if (email == null || email.trim().length() == 0) {
	    throw new JinxException("Parameter email is required.");
	}

	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.people.findByEmail");
	params.put("api_key", Jinx.getInstance().getApiKey());
	params.put("find_email", email);

	Document doc = Jinx.getInstance().callFlickr(params);

	/*
	<rsp stat="ok">
	    <user id="85853333@N00" nsid="85853333@N00">
		<username>Jeremy Brooks</username>
	    </user>
	</rsp>
	 */

	User user = new User();

	user.setId(JinxUtils.getValueByXPath(doc, "/rsp/user/@id"));
	user.setNsid(JinxUtils.getValueByXPath(doc, "/rsp/user/@nsid"));
	user.setUsername(JinxUtils.getValueByXPath(doc, "/rsp/user/username"));
	
	return user;
    }


    /**
     * Return a user's NSID, given their username.
     *
     * This method does not require authentication.
     *
     * @param (required) username the username of the user to find.
     * @return object with user information.
     * @throws JinxException if username is null or if there are any errors.
     * @see http://www.flickr.com/services/api/flickr.people.findByUsername.html
     */
    public User findByUsername(String username) throws JinxException {
	if (username == null || username.trim().length() == 0) {
	    throw new JinxException("Parameter username is required.");
	}

	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.people.findByUsername");
	params.put("api_key", Jinx.getInstance().getApiKey());
	params.put("username", username);

	Document doc = Jinx.getInstance().callFlickr(params);

	/*
	<rsp stat="ok">
	    <user id="85853333@N00" nsid="85853333@N00">
		<username>Jeremy Brooks</username>
	    </user>
	</rsp>
	 */

	User user = new User();

	user.setId(JinxUtils.getValueByXPath(doc, "/rsp/user/@id"));
	user.setNsid(JinxUtils.getValueByXPath(doc, "/rsp/user/@nsid"));
	user.setUsername(JinxUtils.getValueByXPath(doc, "/rsp/user/username"));

	return user;
    }


    /**
     * Get information about a user.
     *
     * This method does not require authentication.
     *
     * The firstDate field of the Person object contains the date of the first
     * photo uploaded by the user.
     *
     * The firstDateTaken field of the Person object contains the date of the
     * first photo taken by the user.
     *
     * The iconServer field is used to build the URL to the users' buddy icon.
     * For more information please read the buddy icon guide:
     * http://www.flickr.com/services/api/misc.buddyicons.html
     *
     * If the API call is authenticated contact information will also be returned
     * as fields in the Person class. isContact, isFriend, and isFamily are
     * boolean flags describing the relationship between the authenticated user,
     * and the person currently being inspected. isRevContact, isRevFriend, and
     * isRevFamily is the reciprocal relationship.
     *
     * @param userId (required) the NSID of the user to fetch information about.
     * @return information about the user.
     * @throws JinxException if userId is null or empty, or if there are any errors.
     * @see http://www.flickr.com/services/api/flickr.people.getInfo.html
     */
    public Person getInfo(String userId) throws JinxException {
	if (userId == null || userId.trim().length() == 0) {
	    throw new JinxException("Parameter userId is required.");
	}

	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.people.getInfo");
	params.put("api_key", Jinx.getInstance().getApiKey());
	params.put("user_id", userId);

	Document doc = Jinx.getInstance().callFlickr(params, true);

	/*
	<rsp stat="ok">
	    <person id="51035555243@N01" nsid="51035555243@N01" ispro="1"
		iconserver="1" iconfarm="1" path_alias="thomashawk" gender="M"
		ignored="0" contact="1" friend="1" family="0" revcontact="1"
		revfriend="1" revfamily="0">
		<username>Thomas Hawk</username>
		<realname>Thomas Hawk</realname>
		<location>San Francisco, USA</location>
		<photosurl>http://www.flickr.com/photos/thomashawk/</photosurl>
		<profileurl>http://www.flickr.com/people/thomashawk/</profileurl>
		<mobileurl>http://m.flickr.com/photostream.gne?id=44088</mobileurl>
		<photos>
		    <firstdatetaken>1999-12-31 08:02:28</firstdatetaken>
		    <firstdate>1104703805</firstdate>
		    <count>56583</count>
		</photos>
	    </person>
	</rsp>
	 */

	Person person = new Person();

	person.setId(JinxUtils.getValueByXPath(doc, "/rsp/person/@id"));
	person.setNsid(JinxUtils.getValueByXPath(doc, "/rsp/person/@nsid"));
	person.setPro(JinxUtils.getValueByXPathAsBoolean(doc, "/rsp/person/@ispro"));
	person.setIconServer(JinxUtils.getValueByXPath(doc, "/rsp/person/@iconserver"));
	person.setIconFarm(JinxUtils.getValueByXPath(doc, "/rsp/person/@iconfarm"));
	person.setPathAlias(JinxUtils.getValueByXPath(doc, "/rsp/person/@path_alias"));
	person.setGender(JinxUtils.getValueByXPath(doc, "/rsp/person/@gender"));
	person.setIgnored(JinxUtils.getValueByXPathAsBoolean(doc, "/rsp/person/@ignored"));
	person.setContact(JinxUtils.getValueByXPathAsBoolean(doc, "/rsp/person/@contact"));
	person.setFriend(JinxUtils.getValueByXPathAsBoolean(doc, "/rsp/person/@friend"));
	person.setFamily(JinxUtils.getValueByXPathAsBoolean(doc, "/rsp/person/@family"));
	person.setRevContact(JinxUtils.getValueByXPathAsBoolean(doc, "/rsp/person/@revcontact"));
	person.setRevFriend(JinxUtils.getValueByXPathAsBoolean(doc, "/rsp/person/@revfriend"));
	person.setRevFamily(JinxUtils.getValueByXPathAsBoolean(doc, "/rsp/person/@revfamily"));
	person.setUsername(JinxUtils.getValueByXPath(doc, "/rsp/person/username"));
	person.setRealname(JinxUtils.getValueByXPath(doc, "/rsp/person/realname"));
	person.setLocation(JinxUtils.getValueByXPath(doc, "/rsp/person/location"));
	person.setPhotosUrl(JinxUtils.getValueByXPath(doc, "/rsp/person/photosurl"));
	person.setProfileUrl(JinxUtils.getValueByXPath(doc, "/rsp/person/profileurl"));
	person.setMobileUrl(JinxUtils.getValueByXPath(doc, "/rsp/person/mobileurl"));
	String date = JinxUtils.getValueByXPath(doc, "/rsp/person/photos/firstdatetaken");
	person.setFirstDateTaken(JinxUtils.parseMySqlDatetimeToDate(date));
	date = JinxUtils.getValueByXPath(doc, "/rsp/person/photos/firstdate");
	person.setFirstDate(JinxUtils.parseTimestampToDate(date));
	person.setCount(JinxUtils.getValueByXPathAsInt(doc, "/rsp/person/photos/count"));

	return person;
    }


    /**
     * Return photos from the given user's photostream.
     * Only photos visible to the calling user will be returned.
     * This method must be authenticated; to return public photos for a user,
     * use PeopleApi.getInstance().getPublicPhotos().
     *
     * This method requires authentication with 'read' permission.
     * 
     * This method is equivalent to 
     * <code>getPhotos(userId, null, null, null, null, null, null, null, null, 0, 0)</code>
     * 
     * @param userId (required) NSID of the user who's photos to return. A value of "me"
     *	      will return the calling user's photos.
     * @return photos from the specified users photostream.
     * @throws JinxException if userId is null or empty, or if there are any errors.
     * @see http://www.flickr.com/services/api/flickr.people.getPhotos.html
     */
    public Photos getPhotos(String userId) throws JinxException {
	return this.getPhotos(userId, null, null, null, null, null, null, null, null, 0, 0);
    }


    /**
     * Return photos from the given user's photostream.
     * Only photos visible to the calling user will be returned.
     * This method must be authenticated; to return public photos for a user,
     * use PeopleApi.getInstance().getPublicPhotos().
     *
     * This method requires authentication with 'read' permission.
     *
     * @param userId (required) NSID of the user who's photos to return. A value of "me"
     *	      will return the calling user's photos.
     * @param safeSearch safeSearch setting, from JinxConstants.SAFE_SEARCH_* values. Can be
     *        null.
     * @param minUploadDate return photos with an upload date greater than or equal to
     *        this value. Can be null.
     * @param maxUploadDate return photos with an upload date less than or equal to
     *        this value. Can be null.
     * @param minTakenDate return photos with a taken date greater than or equal to
     *        this value. Can be null.
     * @param maxTakenDate return photos with a taken date less than or equal to
     *        this value. Can be null.
     * @param contentType content type to return, from JinxConstants.CONTENT_* values.
     *        Can be null.
     * @param privacyFilter return photos only matching a certain privacy level, from
     *        JinxConstants.PRIVACY_* values. Can be null.
     * @param extras list of extras to return, from JinxConstants.EXTRAS_* values.
     *        Can be null.
     * @param perPage number of photos to return per page. If this argument is
     *        less than 1, it defaults to 100. The maximum allowed value is 500.
     * @param page the page of results to return. If this argument is less than 1,
     *        it defaults to 1.
     * @return photos from the specified users photostream.
     * @throws JinxException if userId is null or empty, or if there are any errors.
     * @see http://www.flickr.com/services/api/flickr.people.getPhotos.html
     */
    public Photos getPhotos(String userId, String safeSearch, Date minUploadDate,
	    Date maxUploadDate, Date minTakenDate, Date maxTakenDate,
	    String contentType, String privacyFilter, List<String> extras,
	    int perPage, int page) throws JinxException {

	if (userId == null || userId.trim().length() == 0) {
	    throw new JinxException("Parameter userId is required.");
	}

	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.people.getPhotos");
	params.put("api_key", Jinx.getInstance().getApiKey());
	params.put("user_id", userId);
	if (safeSearch != null) {
	    params.put("safe_search", safeSearch);
	}
	if (minUploadDate != null) {
	    params.put("min_upload_date", JinxUtils.formatDateAsMySqlTimestamp(minUploadDate));
	}
	if (maxUploadDate != null) {
	    params.put("max_upload_date", JinxUtils.formatDateAsMySqlTimestamp(maxUploadDate));
	}
	if (minTakenDate != null) {
	    params.put("min_taken_date", JinxUtils.formatDateAsMySqlTimestamp(minTakenDate));
	}
	if (maxTakenDate != null) {
	    params.put("max_taken_date", JinxUtils.formatDateAsMySqlTimestamp(maxTakenDate));
	}
	if (contentType != null) {
	    params.put("content_type", contentType);
	}
	if (privacyFilter != null) {
	    params.put("privacy_filter", privacyFilter);
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

	return PhotosApi.getInstance().parsePhotosXml(Jinx.getInstance().callFlickr(params, true));
    }


    /**
     * Returns a list of photos containing a particular Flickr member.
     *
     * This method does not require authentication.
     *
     * This method returns the standard Photos object, but with an important
     * difference. For queries about a member other than the currently
     * authenticated one, pagination data ("total" and "pages" attributes)
     * will not be available. Instead, the boolean value 'hasNextPage' will tell
     * you whether or not there are more photos to fetch.
     *
     * This method is equivalent to
     * <code>getPhotosOf(userId, null, null, 0, 0)</null>
     * 
     * @param userId (required) the NSID of the user you want to find photos of.
     *        A value of "me" will search against photos of the calling user,
     *        for authenticated calls.
     * @return photos of the specified Flickr member.
     * @throws JinxException if userId is null or empty, or if there are any errors.
     * @see http://www.flickr.com/services/api/flickr.people.getPhotosOf.html
     */
    public Photos getPhotosOf(String userId) throws JinxException {
	return this.getPhotosOf(userId, null, null, 0, 0);
    }


    /**
     * Returns a list of photos containing a particular Flickr member.
     *
     * This method does not require authentication.
     *
     * This method returns the standard Photos object, but with an important
     * difference. For queries about a member other than the currently
     * authenticated one, pagination data ("total" and "pages" attributes)
     * will not be available. Instead, the boolean value 'hasNextPage' will tell
     * you whether or not there are more photos to fetch.
     *
     * @param userId (required) the NSID of the user you want to find photos of.
     *        A value of "me" will search against photos of the calling user,
     *        for authenticated calls.
     * @param ownerId an NSID of a Flickr member. This will restrict the list of
     *        photos to those taken by that member. Can be null.
     * @param extras list of extras to return, from JinxConstants.EXTRAS_* values.
     *        Can be null.
     * @param perPage number of photos to return per page. If this argument is
     *        less than 1, it defaults to 100. The maximum allowed value is 500.
     * @param page the page of results to return. If this argument is less than 1,
     *        it defaults to 1.
     * @return photos of the specified Flickr member.
     * @throws JinxException if userId is null or empty, or if there are any errors.
     * @see http://www.flickr.com/services/api/flickr.people.getPhotosOf.html
     */
    public Photos getPhotosOf(String userId, String ownerId, List<String> extras,
	    int perPage, int page) throws JinxException {
	if (userId == null || userId.trim().length() == 0) {
	    throw new JinxException("Parameter userId is required.");
	}

	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.people.getPhotosOf");
	params.put("api_key", Jinx.getInstance().getApiKey());
	params.put("user_id", userId);
	if (ownerId != null) {
	    params.put("owner_id", ownerId);
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

	return PhotosApi.getInstance().parsePhotosXml(Jinx.getInstance().callFlickr(params, true));
    }


    /**
     * Returns the list of public groups a user is a member of.
     * 
     * This method does not require authentication.
     * 
     * @param userId the NSID of the user to fetch groups for.
     * @return information about the groups for the specified user.
     * @throws JinxException if userId is null or empty, or if there are any errors.
     * @see http://www.flickr.com/services/api/flickr.people.getPublicGroups.html
     */
    public Groups getPublicGroups(String userId) throws JinxException {
	if (userId == null || userId.trim().length() == 0) {
	    throw new JinxException("Parameter userId is required.");
	}

	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.people.getPublicGroups");
	params.put("api_key", Jinx.getInstance().getApiKey());
	params.put("user_id", userId);

	Document doc = Jinx.getInstance().callFlickr(params, true);

	/*
	<rsp stat="ok">
	    <groups>
		<group nsid="11947580@N00" name="Night Images" admin="0" eighteenplus="0" />
		<group nsid="13378274@N00" name="FLOWERS" admin="0" eighteenplus="0" />
		<group nsid="16316141@N00" name="Graves, Tombs And Cemeteries" admin="0" eighteenplus="0" />
	    </groups>
	</rsp>
	 */

	Groups groups = new Groups();

	// Looks like these values are not returned, but we will try to parse
	// them anyway. It won't hurt to try, and if Flickr ever changes the API
	// to include this info, it will just work.
	groups.setPage(JinxUtils.getValueByXPathAsInt(doc, "/rsp/groups/@page"));
	groups.setPages(JinxUtils.getValueByXPathAsInt(doc, "/rsp/groups/@pages"));
	groups.setPerPage(JinxUtils.getValueByXPathAsInt(doc, "/rsp/groups/@perpage"));
	groups.setTotal(JinxUtils.getValueByXPathAsInt(doc, "/rsp/groups/@total"));

	NodeList groupNodes = doc.getElementsByTagName("group");
	if (groupNodes != null) {
	    for (int i = 0; i < groupNodes.getLength(); i++) {
		NamedNodeMap attrs =  groupNodes.item(i).getAttributes();
		Group g = new Group();
		g.setNsid(JinxUtils.getAttribute(attrs, "nsid"));
		g.setName(JinxUtils.getAttribute(attrs, "name"));
		g.setEighteenPlus(JinxUtils.getAttributeAsBoolean(attrs, "eighteenplus"));
		g.setAdmin(JinxUtils.getAttributeAsBoolean(attrs, "admin"));
		groups.addGroup(g);
	    }
	}

	return groups;
    }


    /**
     * Get a list of public photos for the given user.
     *
     * This method does not require authentication.
     *
     * This method is equivalent to
     * <code>getPublicPhotos(userId, null, null, 0, 0)</code>
     *
     * @param userId (required) NSID of the user who's photos to return. A value of "me"
     *	      will return the calling user's photos.
     * @return photos from the specified users photostream.
     * @throws JinxException if userId is null or empty, or if there are any errors.
     * @see http://www.flickr.com/services/api/flickr.people.getPublicPhotos.html
     */
    public Photos getPublicPhotos(String userId) throws JinxException {
	return this.getPublicPhotos(userId, null, null, 0, 0);
    }


    /**
     * Get a list of public photos for the given user.
     *
     * This method does not require authentication.
     *
     * @param userId (required) NSID of the user who's photos to return. A value of "me"
     *	      will return the calling user's photos.
     * @param safeSearch safeSearch setting, from JinxConstants.SAFE_SEARCH_* values. Can be
     *        null.
     * @param extras list of extras to return, from JinxConstants.EXTRAS_* values.
     *        Can be null.
     * @param perPage number of photos to return per page. If this argument is
     *        less than 1, it defaults to 100. The maximum allowed value is 500.
     * @param page the page of results to return. If this argument is less than 1,
     *        it defaults to 1.
     * @return photos from the specified users photostream.
     * @throws JinxException if userId is null or empty, or if there are any errors.
     * @see http://www.flickr.com/services/api/flickr.people.getPublicPhotos.html
     */
    public Photos getPublicPhotos(String userId, String safeSearch, List<String> extras,
	    int perPage, int page) throws JinxException {
	if (userId == null || userId.trim().length() == 0) {
	    throw new JinxException("Parameter userId is required.");
	}

	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.people.getPublicPhotos");
	params.put("api_key", Jinx.getInstance().getApiKey());
	params.put("user_id", userId);
	if (safeSearch != null) {
	    params.put("safe_search", safeSearch);
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

	return PhotosApi.getInstance().parsePhotosXml(Jinx.getInstance().callFlickr(params, true));
    }


    /**
     * Returns information for the calling user related to photo uploads.
     *
     * This method requires authentication with 'read' permission.
     *
     * Bandwidth and filesize numbers are provided in bytes and kilobytes.
     * Bandwidth is specified in bytes/kb per month.
     * All accounts display "lots" for the number of remaining sets, but remains
     * in the response for backwards compatibility.
     *
     * Pro accounts display "lots" for the number of remaining videos, while free
     * users will display 0, 1, or 2.
     *
     * @return user object with upload status information.
     * @throws JinxException if there are any errors.
     * @see http://www.flickr.com/services/api/flickr.people.getUploadStatus.html
     */
    public User getUploadStatus() throws JinxException {
	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.people.getUploadStatus");
	params.put("api_key", Jinx.getInstance().getApiKey());

	Document doc = Jinx.getInstance().callFlickr(params, true);

	/*
	<rsp stat="ok">
	    <user id="85853333@N00" ispro="1">
		<username>Jeremy Brooks</username>
		<bandwidth max="2147483648" used="0" maxbytes="2147483648"
		    usedbytes="0" remainingbytes="2147483648" maxkb="2097152"
		    usedkb="0" remainingkb="2097152" unlimited="1" />
		<filesize max="20971520" maxbytes="20971520" maxkb="20480" maxmb="20" />
		<sets created="" remaining="lots" />
		<videosize maxbytes="524288000" maxkb="512000" maxmb="500" />
		<videos uploaded="0" remaining="lots" />
	    </user>
	 </rsp>
	 */
	User user = new User();

	user.setId(JinxUtils.getValueByXPath(doc, "/rsp/user/@id"));
	user.setNsid(JinxUtils.getValueByXPath(doc, "/rsp/user/@id"));
	user.setPro(JinxUtils.getValueByXPathAsBoolean(doc, "/rsp/user/@ispro"));

	user.setUsername(JinxUtils.getValueByXPath(doc, "/rsp/user/username"));
	user.setBandwidthMax(JinxUtils.getValueByXPath(doc, "/rsp/user/bandwidth/@max"));
	user.setBandwidthUsed(JinxUtils.getValueByXPath(doc, "/rsp/user/bandwidth/@used"));
	user.setBandwidthMaxBytes(JinxUtils.getValueByXPath(doc, "/rsp/user/bandwidth/@maxbytes"));
	user.setBandwidthUsedBytes(JinxUtils.getValueByXPath(doc, "/rsp/user/bandwidth/@usedbytes"));
	user.setBandwidthRemainingBytes(JinxUtils.getValueByXPath(doc, "/rsp/user/bandwidth/@remainingbytes"));
	user.setBandwidthMaxKb(JinxUtils.getValueByXPath(doc, "/rsp/user/bandwidth/@maxkb"));
	user.setBandwidthUsedKb(JinxUtils.getValueByXPath(doc, "/rsp/user/bandwidth/@usedkb"));
	user.setBandwidthRemainingKb(JinxUtils.getValueByXPath(doc, "/rsp/user/bandwidth/@remainingkb"));
	user.setBandwidthUnlimited(JinxUtils.getValueByXPathAsBoolean(doc, "/rsp/user/bandwidth/@unlimited"));

	user.setFilesizeMax(JinxUtils.getValueByXPath(doc, "/rsp/user/filesize/@max"));
	user.setFilesizeMaxBytes(JinxUtils.getValueByXPath(doc, "/rsp/user/filesize/@maxbytes"));
	user.setFilesizeMaxKb(JinxUtils.getValueByXPath(doc, "/rsp/user/filesize/@maxkb"));
	user.setFilesizeMaxMb(JinxUtils.getValueByXPath(doc, "/rsp/user/filesize/@maxmb"));

	user.setSetsCreated(JinxUtils.getValueByXPath(doc, "/rsp/user/sets/@created"));
	user.setSetsRemaining(JinxUtils.getValueByXPath(doc, "/rsp/user/sets/@remaining"));

	user.setVideoSizeMaxBytes(JinxUtils.getValueByXPath(doc, "/rsp/user/videosize/@maxbytes"));
	user.setVideoSizeMaxKb(JinxUtils.getValueByXPath(doc, "/rsp/user/videosize/@maxkb"));
	user.setVideoSizeMaxMb(JinxUtils.getValueByXPath(doc, "/rsp/user/videosize/@maxmb"));

	user.setVideosUploaded(JinxUtils.getValueByXPath(doc, "/rsp/user/videos/@uploaded"));
	user.setVideosRemaining(JinxUtils.getValueByXPath(doc, "/rsp/user/videos/@remaining"));
	
	return user;
    }
}
