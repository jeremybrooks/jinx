/*
 * Jinx is Copyright 2010 by Jeremy Brooks
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import net.jeremybrooks.jinx.JinxException;
import net.jeremybrooks.jinx.Jinx;
import net.jeremybrooks.jinx.JinxUtils;
import net.jeremybrooks.jinx.dto.AllContexts;
import net.jeremybrooks.jinx.dto.Context;
import net.jeremybrooks.jinx.dto.Exif;
import net.jeremybrooks.jinx.dto.ExifElement;
import net.jeremybrooks.jinx.dto.Favorites;
import net.jeremybrooks.jinx.dto.Note;
import net.jeremybrooks.jinx.dto.Perms;
import net.jeremybrooks.jinx.dto.Person;
import net.jeremybrooks.jinx.dto.Photo;
import net.jeremybrooks.jinx.dto.PhotoId;
import net.jeremybrooks.jinx.dto.PhotoInfo;
import net.jeremybrooks.jinx.dto.Photocounts;
import net.jeremybrooks.jinx.dto.Photos;
import net.jeremybrooks.jinx.dto.Photoset;
import net.jeremybrooks.jinx.dto.Pool;
import net.jeremybrooks.jinx.dto.SearchParameters;
import net.jeremybrooks.jinx.dto.Sizes;
import net.jeremybrooks.jinx.dto.Tag;
import net.jeremybrooks.jinx.dto.Url;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 *
 * @author jeremyb
 */
public class PhotosApi {


    private static PhotosApi instance = null;


    private PhotosApi() {
    }


    public static PhotosApi getInstance() {
	if (PhotosApi.instance == null) {
	    PhotosApi.instance = new PhotosApi();
	}

	return PhotosApi.instance;
    }


    public Photos search(SearchParameters searchParameters) throws JinxException {
	return this.search(searchParameters, true);
    }


    public Photos search(SearchParameters searchParameters, boolean signCall) throws JinxException {
	Photos photos = null;

	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.photos.search");
	params.put("api_key", Jinx.getInstance().getApiKey());

	if (!JinxUtils.isEmpty(searchParameters.getUserId())) {
	    params.put("user_id", searchParameters.getUserId());
	}
	if (!JinxUtils.isEmpty(searchParameters.getTags())) {
	    params.put("tags", searchParameters.getTags());
	}
	if (!JinxUtils.isEmpty(searchParameters.getTagMode())) {
	    params.put("tag_mode", searchParameters.getTagMode());
	}
	if (!JinxUtils.isEmpty(searchParameters.getText())) {
	    params.put("text", searchParameters.getText());
	}
	if (searchParameters.getMinUploadDate() != null) {
	    params.put("min_upload_date", JinxUtils.formatDateAsMySqlTimestamp(searchParameters.getMinUploadDate()));
	}
	if (searchParameters.getMaxUploadDate() != null) {
	    params.put("max_upload_date", JinxUtils.formatDateAsMySqlTimestamp(searchParameters.getMaxUploadDate()));
	}
	if (searchParameters.getMinTakenDate() != null) {
	    params.put("min_taken_date", JinxUtils.formatDateAsMySqlTimestamp(searchParameters.getMinTakenDate()));
	}
	if (searchParameters.getMaxTakenDate() != null) {
	    params.put("max_taken_date", JinxUtils.formatDateAsMySqlTimestamp(searchParameters.getMaxTakenDate()));
	}
	if (!JinxUtils.isEmpty(searchParameters.getLicense())) {
	    params.put("license", searchParameters.getLicense());
	}
	if (!JinxUtils.isEmpty(searchParameters.getSort())) {
	    params.put("sort", searchParameters.getSort());
	}
	if (!JinxUtils.isEmpty(searchParameters.getPrivacyFilter())) {
	    params.put("privacy_filter", searchParameters.getPrivacyFilter());
	}
	if (!JinxUtils.isEmpty(searchParameters.getBbox())) {
	    params.put("bbox", searchParameters.getBbox());
	}
	if (!JinxUtils.isEmpty(searchParameters.getAccuracy())) {
	    params.put("accuracy", searchParameters.getAccuracy());
	}
	if (!JinxUtils.isEmpty(searchParameters.getSafeSearch())) {
	    params.put("safe_search", searchParameters.getSafeSearch());
	}
	if (!JinxUtils.isEmpty(searchParameters.getContentType())) {
	    params.put("content_type", searchParameters.getContentType());
	}
	if (!JinxUtils.isEmpty(searchParameters.getMachineTags())) {
	    params.put("machine_tags", searchParameters.getMachineTags());
	}
	if (!JinxUtils.isEmpty(searchParameters.getMachineTagMode())) {
	    params.put("machine_tag_mode", searchParameters.getMachineTagMode());
	}
	if (!JinxUtils.isEmpty(searchParameters.getGroupId())) {
	    params.put("group_id", searchParameters.getGroupId());
	}
	if (!JinxUtils.isEmpty(searchParameters.getContacts())) {
	    params.put("contacts", searchParameters.getContacts());
	}
	if (!JinxUtils.isEmpty(searchParameters.getWoeId())) {
	    params.put("woe_id", searchParameters.getWoeId());
	}
	if (!JinxUtils.isEmpty(searchParameters.getPlaceId())) {
	    params.put("place_id", searchParameters.getPlaceId());
	}
	if (!JinxUtils.isEmpty(searchParameters.getMedia())) {
	    params.put("media", searchParameters.getMedia());
	}
	if (!JinxUtils.isEmpty(searchParameters.getHasGeo())) {
	    params.put("has_geo", searchParameters.getHasGeo());
	}
	if (!JinxUtils.isEmpty(searchParameters.getGeoContext())) {
	    params.put("geo_context", searchParameters.getGeoContext());
	}
	if (!JinxUtils.isEmpty(searchParameters.getLat())) {
	    params.put("lat", searchParameters.getLat());
	}
	if (!JinxUtils.isEmpty(searchParameters.getLon())) {
	    params.put("lon", searchParameters.getLon());
	}
	if (!JinxUtils.isEmpty(searchParameters.getRadius())) {
	    params.put("radius", searchParameters.getRadius());
	}
	if (!JinxUtils.isEmpty(searchParameters.getRadiusUnits())) {
	    params.put("radius_units", searchParameters.getRadiusUnits());
	}
	if (searchParameters.isIsCommons()) {
	    params.put("is_commons", "1");
	}
	if (searchParameters.isInGallery()) {
	    params.put("in_gallery", "1");
	}
	if (searchParameters.isIsGetty()) {
	    params.put("is_getty", "1");
	}
	if (!JinxUtils.isEmpty(searchParameters.getExtras())) {
	    params.put("extras", searchParameters.getExtras());
	}
	if (searchParameters.getPerPage() > 0) {
	    params.put("per_page", Integer.toString(searchParameters.getPerPage()));
	}
	if (searchParameters.getPage() > 0) {
	    params.put("page", Integer.toString(searchParameters.getPage()));
	}


	Document doc = Jinx.getInstance().callFlickr(params, signCall);

	photos = this.parsePhotosXml(doc);
	
	return photos;
    }


    /**
     * Add tags to a photo.
     *
     * <p>The tags parameter can have multiple tags, but the tags must be
     * comma separated.</p>
     *
     * @param photoId the ID of the photo to add tags to
     * @param tags the tags to add (comma separated)
     * @throws JinxException if the call fails
     */
    public void addTags(String photoId, String tags) throws JinxException {
	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.photos.addTags");
	params.put("api_key", Jinx.getInstance().getApiKey());
	params.put("photo_id", photoId);
	params.put("tags", tags);

	Jinx.getInstance().callFlickr(params, true, true);
    }

    /**
     * Add tags to a photo.
     *
     * @param photoId the ID of the photo to add tags to
     * @param tags a List of tags to add to the photo
     * @throws JinxException if the call fails
     */
    public void addTags(String photoId, List<String> tags) throws JinxException {
	StringBuilder sb = new StringBuilder();
	for (String tag : tags) {
	    sb.append(tag).append(',');
	}
	sb.deleteCharAt(sb.lastIndexOf(","));

	this.addTags(photoId, sb.toString());
    }



    /**
     * Delete a photo from flickr.
     *
     * This method requires authentication with 'delete' permission.
     *
     * Note: This method requires an HTTP POST request.
     *
     * @param photoId the ID of the photo to delete.
     * @throws JinxException if there are any errors.
     */
    public void delete(String photoId) throws JinxException {
	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.photos.delete");
	params.put("api_key", Jinx.getInstance().getApiKey());
	params.put("photo_id", photoId);

	Jinx.getInstance().callFlickr(params, true, true);
    }


    /**
     * Returns all visible sets and pools the photo belongs to.
     *
     * This method does not require authentication.
     *
     * The response XML looks like this:
     *
     * <?xml version="1.0" encoding="utf-8" ?>
     *	<rsp stat="ok">
     *	    <set id="72157624018791194" title="35mm" />
     *	    <set id="72157623909778432" title="Caliber Photowalks The Mission, 04-18-10" />
     *	    <set id="72157623661070332" title="San Francisco 2010" />
     *	    <set id="72157604332672483" title="City By The Bay" />
     *	    <pool id="96305381@N00" title="Mission District" />
     *	    <pool id="42642220@N00" title="SFist" />
     *	    <pool id="35907091@N00" title="Curbed SF" />
     *	    <pool id="1056659@N24" title="7x7 Magazine - Daily Hot Shot!" />
     *	    <pool id="1080712@N20" title="7x7 Magazine - SF Neighborhoods - Get Published!" />
     *	    <pool id="1159826@N23" title="sfweekly" />
     *	</rsp>
     *
     * @param photoId the photo to return information for.
     * @return all contexts object with list of sets and pools that the photo is in.
     * @throws JinxException if there are any errors.
     */
    public AllContexts getAllContexts(String photoId) throws JinxException {
	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.photos.getAllContexts");
	params.put("api_key", Jinx.getInstance().getApiKey());
	params.put("photo_id", photoId);

	Document doc = Jinx.getInstance().callFlickr(params, false);

	AllContexts c = new AllContexts();
	c.setId(photoId);

	List<Photoset> sets = new ArrayList<Photoset>();
	List<Pool> pools = new ArrayList<Pool>();

	NodeList setNodes = doc.getElementsByTagName("set");
	if (setNodes != null) {
	    for (int i = 0; i < setNodes.getLength(); i++) {
		Photoset set = new Photoset();
		Node setNode = setNodes.item(i);
		if (setNode != null) {
		    NamedNodeMap attrs = setNode.getAttributes();
		    set.setId(JinxUtils.getAttribute(attrs, "id"));
		    set.setTitle(JinxUtils.getAttribute(attrs, "title"));
		}
		sets.add(set);
	    }
	}

	NodeList poolNodes = doc.getElementsByTagName("pool");
	if (poolNodes != null) {
	    for (int i = 0; i < poolNodes.getLength(); i++) {
		Pool pool = new Pool();
		Node poolNode = poolNodes.item(i);
		if (poolNode != null) {
		    NamedNodeMap attrs = poolNode.getAttributes();
		    pool.setId(JinxUtils.getAttribute(attrs, "id"));
		    pool.setTitle(JinxUtils.getAttribute(attrs, "title"));
		}
		pools.add(pool);
	    }
	}

	c.setSets(sets);
	c.setPools(pools);

	return c;
    }


    /**
     * Fetch a list of recent photos from the calling users' contacts.
     *
     * This method requires authentication with 'read' permission.
     * 
     * If you specify a value less than 1 for the count, the default of 10 will
     * be used. The maxiumum allowed is 50. This value will only be used if
     * singlePhoto is false.
     * 
     * Extras:
     * You can include extras from JinxConstants.EXTRAS*
     * Currently supported fields include:
     * EXTRAS_LICENSE
     * EXTRAS_DATE_UPLOAD
     * EXTRAS_DATE_TAKEN
     * EXTRAS_OWNER_NAME
     * EXTRAS_ICON_SERVER
     * EXTRAS_ORIGINAL_FORMAT
     * EXTRAS_LAST_UPDATE
     *
     * @param count number of photos to return.
     * @param justFriends if true, only photos from friends and family will be returned.
     * @param singlePhoto if true, only one photo (the latest) per contact will
     *        be returned, instead of all photos in chronological order.
     * @param includeSelf if true, include photos from the calling user.
     * @param extras a list of extra information to fetch for each photo.
     * @return recent photos from calling users contacts.
     * @throws JinxException if there are any errors.
     */
    public Photos getContactsPhotos(int count, boolean justFriends, boolean singlePhoto,
	    boolean includeSelf, List<String> extras) throws JinxException {
	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.photos.getContactsPhotos");
	params.put("api_key", Jinx.getInstance().getApiKey());
	if (count > 0) {
	    params.put("count", Integer.toString(count));
	}
	if (justFriends) {
	    params.put("just_friends", "1");
	}
	if (singlePhoto) {
	    params.put("single_photo", "1");
	}
	if (includeSelf) {
	    params.put("include_self", "1");
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

	Document doc = Jinx.getInstance().callFlickr(params, true);
	Photos p = this.parsePhotosXml(doc);

	return p;
    }


    /**
     * Fetch a list of recent public photos from a users' contacts.
     *
     * This method does not require authentication.
     *
     * If you specify a value less than 1 for the count, the default of 10 will
     * be used. The maxiumum allowed is 50. This value will only be used if
     * singlePhoto is false.
     *
     * Extras:
     * You can include extras from JinxConstants.EXTRAS*
     * Currently supported fields include:
     * EXTRAS_LICENSE
     * EXTRAS_DATE_UPLOAD
     * EXTRAS_DATE_TAKEN
     * EXTRAS_OWNER_NAME
     * EXTRAS_ICON_SERVER
     * EXTRAS_ORIGINAL_FORMAT
     * EXTRAS_LAST_UPDATE
     *
     * @param userId The NSID of the user to fetch photos for.
     * @param count number of photos to return.
     * @param justFriends if true, only photos from friends and family will be returned.
     * @param singlePhoto if true, only one photo (the latest) per contact will
     *        be returned, instead of all photos in chronological order.
     * @param includeSelf if true, include photos from the calling user.
     * @param extras a list of extra information to fetch for each photo.
     * @return recent public photos from a users' contacts.
     * @throws JinxException
     */
    public Photos getContactsPublicPhotos(String userId, int count, boolean justFriends,
	    boolean singlePhoto, boolean includeSelf, List<String>extras) throws JinxException {
	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.photos.getContactsPublicPhotos");
	params.put("api_key", Jinx.getInstance().getApiKey());
	params.put("user_id", userId);
	if (count > 0) {
	    params.put("count", Integer.toString(count));
	}
	if (justFriends) {
	    params.put("just_friends", "1");
	}
	if (singlePhoto) {
	    params.put("single_photo", "1");
	}
	if (includeSelf) {
	    params.put("include_self", "1");
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

	Document doc = Jinx.getInstance().callFlickr(params, false);
	Photos p = parsePhotosXml(doc);

	return p;
    }


    /**

     * Returns next and previous photos for a photo in a photostream.
     * This method does not require authentication.
     *
     * NOTE: The Context object reutrned contains Photo objects representing
     * the previous and next photos, but these Photo objects do NOT have all
     * of their fields populated. The populated fields will be:
     * <ul>
     * <li>id</li>
     * <li>secret</li>
     * <li>server</li>
     * <li>farm</li>
     * <li>title</li>
     * <li>url</li>
     * <li>thumb</li>
     * <li>media</li>     *
     * </ul>
     *
     * @param photoId the id of the photo to fetch the context for.
     * @return context of the photo.
     * @throws JinxException if there are any errors.
     */
    public Context getContext(String photoId) throws JinxException {
	
	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.photos.getContext");
	params.put("api_key", Jinx.getInstance().getApiKey());
	params.put("photo_id", photoId);

	Document doc = Jinx.getInstance().callFlickr(params, false);

	/*
	 * Response XML looks like this:
	 <?xml version="1.0" encoding="utf-8" ?>
	 <rsp stat="ok">
	    <count>13413</count>
	    <prevphoto id="4626237448" secret="6ee2e78237" server="3378" farm="4" title="Fourbell" url="/photos/jeremybrooks/4626237448/in/photostream/" thumb="http://farm4.static.flickr.com/3378/4626237448_6ee2e78237_s.jpg" media="photo" />
	    <nextphoto id="4625632867" secret="17b59e6967" server="4042" farm="5" title="Dining Down" url="/photos/jeremybrooks/4625632867/in/photostream/" thumb="http://farm5.static.flickr.com/4042/4625632867_17b59e6967_s.jpg" media="photo" />
	 </rsp>
	 */
	Context c = new Context();
	Photo prev = new Photo();
	Photo next = new Photo();

	
	prev.setId(JinxUtils.getValueByXPath(doc, "/rsp/prevphoto/@id"));
	prev.setSecret(JinxUtils.getValueByXPath(doc, "/rsp/prevphoto/@secret"));
	prev.setServer(JinxUtils.getValueByXPath(doc, "/rsp/prevphoto/@server"));
	prev.setFarm(JinxUtils.getValueByXPath(doc, "/rsp/prevphoto/@farm"));
	prev.setTitle(JinxUtils.getValueByXPath(doc, "/rsp/prevphoto/@title"));
	prev.setUrl(JinxUtils.getValueByXPath(doc, "/rsp/prevphoto/@url"));
	prev.setThumb(JinxUtils.getValueByXPath(doc, "/rsp/prevphoto/@thumb"));
	prev.setMedia(JinxUtils.getValueByXPath(doc, "/rsp/prevphoto/@media"));

	next.setId(JinxUtils.getValueByXPath(doc, "/rsp/nextphoto/@id"));
	next.setSecret(JinxUtils.getValueByXPath(doc, "/rsp/nextphoto/@secret"));
	next.setServer(JinxUtils.getValueByXPath(doc, "/rsp/nextphoto/@server"));
	next.setFarm(JinxUtils.getValueByXPath(doc, "/rsp/nextphoto/@farm"));
	next.setTitle(JinxUtils.getValueByXPath(doc, "/rsp/nextphoto/@title"));
	next.setUrl(JinxUtils.getValueByXPath(doc, "/rsp/nextphoto/@url"));
	next.setThumb(JinxUtils.getValueByXPath(doc, "/rsp/nextphoto/@thumb"));
	next.setMedia(JinxUtils.getValueByXPath(doc, "/rsp/nextphoto/@media"));

	c.setCount(JinxUtils.getValueByXPathAsInt(doc, "/rsp/count"));
	c.setPreviousPhoto(prev);
	c.setNextPhoto(next);
	
	return c;
    }

  
    
    /**
     * Gets a list of photo counts for the given date ranges for the calling user.
     * This method requires authentication with 'read' permission.
     *
     * @param dates list of dates to get counts for. They must be in order,
     *        smallest first.
     * @return object with counts for each of the date ranges.
     * @throws JinxException if there are any errors..
     */
    public Photocounts getCounts(List<Date> dates) throws JinxException {
	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.photos.getCounts");
	params.put("api_key", Jinx.getInstance().getApiKey());
	if (dates != null && dates.size() > 0) {
	    StringBuilder sb = new StringBuilder();
	    for (Date d : dates) {
		sb.append(JinxUtils.formatDateAsUnixTimestamp(d)).append(',');
	    }
	    sb.deleteCharAt(sb.lastIndexOf(","));
	    params.put("dates", sb.toString());

	} else {
	    throw new JinxException("You must provide one parameter.");
	}

	/*
	    <?xml version="1.0" encoding="utf-8" ?>
	    <rsp stat="ok">
		<photocounts>
		    <photocount count="31" fromdate="1230883200" todate="1230969600" />
		</photocounts>
	    </rsp>
	 */
	Document doc = Jinx.getInstance().callFlickr(params);
	Photocounts p = new Photocounts();

	NodeList counts = doc.getElementsByTagName("photocount");
	if (counts != null) {
	    for (int i = 0; i < counts.getLength(); i++) {
		Node countNode = counts.item(i);
		NamedNodeMap attrs = countNode.getAttributes();
		int count = JinxUtils.getAttributeAsInt(attrs, "count");
		Date fromDate = JinxUtils.parseTimestampToDate(JinxUtils.getAttribute(attrs, "fromdate"));
		Date toDate = JinxUtils.parseTimestampToDate(JinxUtils.getAttribute(attrs, "todate"));

		p.addPhotocount(count, fromDate, toDate);
	    }
	}

	return p;
    }


    /**
     * Retrieves a list of EXIF/TIFF/GPS tags for a given photo.
     *
     * The calling user must have permission to view the photo.
     *
     * This method does not require authentication.
     *
     * @param photoId The id of the photo to fetch information for.
     * @param secret Optional. The secret for the photo. If the correct secret
     *	      is passed then permissions checking is skipped. This enables the
     *	      'sharing' of individual photos by passing around the id and secret.
     * @return exif object containing the exif and photo data.
     * @throws JinxException if there are any errors.
     */
    public Exif getExif(String photoId, String secret) throws JinxException {
	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.photos.getExif");
	params.put("api_key", Jinx.getInstance().getApiKey());
	params.put("photo_id", photoId);
	if (secret != null && secret.trim().length() > 0) {
	    params.put("secret", secret);
	}

	Document doc = Jinx.getInstance().callFlickr(params, false);

	/* The response looks like this:
	 <?xml version="1.0" encoding="utf-8" ?>
	 <rsp stat="ok">
	    <photo id="4424" secret="06b8e43bc7" server="2">
		<exif tagspace="TIFF" tagspaceid="1" tag="271" label="Manufacturer">
			<raw>Canon</raw>
		</exif>
		<exif tagspace="EXIF" tagspaceid="0" tag="33437" label="Aperture">
			<raw>90/10</raw>
			<clean>f/9</clean>
		</exif>
		<exif tagspace="GPS" tagspaceid="3" tag="4" label="Longitude">
			<raw>64/1, 42/1, 4414/100</raw>
			<clean>64° 42' 44.14"</clean>
		</exif>
	    </photo>
	 </rsp>
	 */

	Exif exif = new Exif();
	List<ExifElement> exifList = new ArrayList<ExifElement>();

	exif.setId(JinxUtils.getValueByXPath(doc, "/rsp/photo/@id"));
	exif.setSecret(JinxUtils.getValueByXPath(doc, "/rsp/photo/@secret"));
	exif.setServer(JinxUtils.getValueByXPath(doc, "/rsp/photo/@server"));
	exif.setFarm(JinxUtils.getValueByXPath(doc, "/rsp/photo/@farm"));

	NodeList exifNodes = doc.getElementsByTagName("exif");

	if (exifNodes != null) {
	    for(int i = 0; i < exifNodes.getLength(); i++) {
		ExifElement ee = new ExifElement();
		Node exifNode = exifNodes.item(i);
		NamedNodeMap attrs = exifNode.getAttributes();
		ee.setLabel(JinxUtils.getAttribute(attrs, "label"));
		ee.setTag(JinxUtils.getAttribute(attrs, "tag"));
		ee.setTagspace(JinxUtils.getAttribute(attrs, "tagspace"));
		ee.setTagspaceid(JinxUtils.getAttribute(attrs, "tagspaceid"));

		NodeList nl = exifNode.getChildNodes();
		if (nl != null) {
		    for (int j = 0; j < nl.getLength(); j++) {
			Node n = nl.item(j);
			if (n.getNodeName().equals("raw")) {
			    ee.setRaw(n.getTextContent());
			} else if (n.getNodeName().equals("clean")) {
			    ee.setClean(n.getTextContent());
			}
		    }
		}

		exifList.add(ee);
	    }
	}

	exif.setExifList(exifList);

	return exif;
    }


    /**
     * Returns the people who have favorited a given photo.
     *
     * This method does not require authentication.
     *
     * @param photoId the ID of the photo to fetch the favoriters list for.
     * @param page The page of results to return. If this argument is < 1, it defaults to 1.
     * @param perPage Number of users to return per page. If this argument is
     *	      < 1, it defaults to 10. The maximum allowed value is 50.
     * @return <code>Favorites</code> object for the specified photo.
     * @throws JinxException if there are any errors.
     */
    public Favorites getFavorites(String photoId, int page, int perPage) throws JinxException {
	return getFavorites(photoId, page, perPage, false);
    }



    /**
     * Returns the people who have favorited a given photo.
     *
     * This method does not require authentication. However, if you need to
     * return information about a non-public photo, the call must be authenticated.
     *
     * @param photoId the ID of the photo to fetch the favoriters list for.
     * @param page The page of results to return. If this argument is < 1, it defaults to 1.
     * @param perPage Number of users to return per page. If this argument is
     *	      < 1, it defaults to 10. The maximum allowed value is 50.
     * @param auth if true, this call will be authenticated.
     * @return <code>Favorites</code> object for the specified photo.
     * @throws JinxException if there are any errors.
     */
    public Favorites getFavorites(String photoId, int page, int perPage, boolean auth) throws JinxException {
	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.photos.getFavorites");
	params.put("api_key", Jinx.getInstance().getApiKey());
	params.put("photo_id", photoId);
	if (page > 0) {
	    params.put("page", Integer.toString(page));
	}
	if (perPage > 0) {
	    params.put("perpage", Integer.toString(perPage));
	}

	Document doc = Jinx.getInstance().callFlickr(params, auth);

	Favorites f = new Favorites();
	List<Person> personList = new ArrayList<Person>();

	/*
	 <rsp stat="ok">
	    <photo id="4625632867" secret="17b59e6967" server="4042" farm="5" page="1" pages="1" perpage="10" total="5">
		<person nsid="34658352@N04" username="CurseoftheWolf" favedate="1274680584" />
		<person nsid="28959636@N02" username="blackbird1948" favedate="1274663826" />
		<person nsid="54815374@N00" username="samwibatt" favedate="1274540459" />
		<person nsid="42961457@N04" username="Troy Holden" favedate="1274425201" />
		<person nsid="30484944@N00" username="Maltphoto" favedate="1274419042" />
	    </photo>
	  </rsp>
	 */
	f.setId(JinxUtils.getValueByXPath(doc, "/rsp/photo/@id"));
	f.setSecret(JinxUtils.getValueByXPath(doc, "/rsp/photo/@secret"));
	f.setServer(JinxUtils.getValueByXPath(doc, "/rsp/photo/@server"));
	f.setFarm(JinxUtils.getValueByXPath(doc, "/rsp/photo/@farm"));
	f.setPage(JinxUtils.getValueByXPathAsInt(doc, "/rsp/photo/@page"));
	f.setPages(JinxUtils.getValueByXPathAsInt(doc, "/rsp/photo/@pages"));
	f.setPerPage(JinxUtils.getValueByXPathAsInt(doc, "/rsp/photo/@perpage"));
	f.setTotal(JinxUtils.getValueByXPathAsInt(doc, "/rsp/photo/@total"));

	NodeList people = doc.getElementsByTagName("person");
	if (people != null) {
	    for(int i = 0; i < people.getLength(); i++) {
		Person p = new Person();
		NamedNodeMap attrs = people.item(i).getAttributes();

		p.setNsid(JinxUtils.getAttribute(attrs, "nsid"));
		p.setUsername(JinxUtils.getAttribute(attrs, "username"));
		String timestamp = JinxUtils.getAttribute(attrs, "favedate");
		p.setFaveDate(JinxUtils.parseTimestampToDate(timestamp));

		personList.add(p);
	    }
	}

	f.setPersonList(personList);

	return f;
    }


    
    /**
     * Get information about a photo.
     * The calling user must have permission to view the photo.
     * This method does not require authentication.
     *
     * The secret parameter is not required. If you do not have the secret,
     * use null.
     *
     * @param photoId the id of the photo to get information for.
     * @param secret the secret for the photo. If the correct secret is passed
     *        then permissions checking is skipped. This enables the 'sharing'
     *        of individual photos by passing around the id and secret.
     * @param auth if true, the call will be authenticated. This is required if
     *        you want to get information about a private photo.
     * @return object containing information about the photo.
     * @throws JinxException if there are any errors.
     */
    public PhotoInfo getInfo(String photoId, String secret, boolean auth) throws JinxException {
	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.photos.getInfo");
	params.put("api_key", Jinx.getInstance().getApiKey());
	params.put("photo_id", photoId);
	if (secret != null && secret.trim().length() > 0) {
	    params.put("secret", secret);
	}
	
	Document doc = Jinx.getInstance().callFlickr(params, auth);

	PhotoInfo info = new PhotoInfo();

	List<Note> noteList = new ArrayList<Note>();
	List<Tag> tagList = new ArrayList<Tag>();
	List<Url> urlList = new ArrayList<Url>();

	/*
	 * Response looks like this:
	 <?xml version="1.0" encoding="utf-8" ?>
	 <rsp stat="ok">
	    <photo id="4666667622" secret="3bf74aeb69" server="4055" farm="5" dateuploaded="1275579415" isfavorite="0" license="2" rotation="0" originalsecret="57b3945a6a" originalformat="jpg" views="24" media="photo">
		<owner nsid="85853333@N00" username="Jeremy Brooks" realname="First Last" location="" />
		<title>Not Listening</title>
		<description />
		<visibility ispublic="1" isfriend="0" isfamily="0" />
		<dates posted="1275579415" taken="2010-06-01 11:41:26" takengranularity="0" lastupdate="1275624861" />
		<editability cancomment="0" canaddmeta="0" />
		<usage candownload="1" canblog="0" canprint="0" canshare="0" />
		<comments>1</comments>
		<notes>
		    <note id="72157624078918391" author="85853333@N00" authorname="Jeremy Brooks" x="299" y="241" w="81" h="70">Muni train.</note>
		    <note id="72157624203320554" author="85853333@N00" authorname="Jeremy Brooks" x="254" y="121" w="55" h="68">Walkway</note>
		</notes>
		<tags>
		    <tag id="4956757-4666667622-50" author="85853333@N00" raw="California" machine_tag="0">california</tag>
		    <tag id="4956757-4666667622-147919" author="85853333@N00" raw="F-Line" machine_tag="0">fline</tag>
		    <tag id="4956757-4666667622-11670" author="85853333@N00" raw="Financial District" machine_tag="0">financialdistrict</tag>
		    <tag id="4956757-4666667622-2664" author="85853333@N00" raw="High" machine_tag="0">high</tag>
		    <tag id="4956757-4666667622-28535" author="85853333@N00" raw="Market Street" machine_tag="0">marketstreet</tag>
		    <tag id="4956757-4666667622-46" author="85853333@N00" raw="San Francisco" machine_tag="0">sanfrancisco</tag>
		    <tag id="4956757-4666667622-983245" author="85853333@N00" raw="San Francisco County" machine_tag="0">sanfranciscocounty</tag>
		    <tag id="4956757-4666667622-38668573" author="85853333@N00" raw="ShakeItPhoto" machine_tag="0">shakeitphoto</tag>
		    <tag id="4956757-4666667622-85" author="85853333@N00" raw="Street" machine_tag="0">street</tag>
		    <tag id="4956757-4666667622-351" author="85853333@N00" raw="USA" machine_tag="0">usa</tag>
		    <tag id="4956757-4666667622-176591" author="85853333@N00" raw="iPhone" machine_tag="0">iphone</tag>
		</tags>
		<urls>
		    <url type="photopage">http://www.flickr.com/photos/jeremybrooks/4666667622/</url>
		</urls>
	    </photo>
	  </rsp>
	 */

	info.setId(JinxUtils.getValueByXPath(doc, "/rsp/photo/@id"));
	info.setSecret(JinxUtils.getValueByXPath(doc, "/rsp/photo/@secret"));
	info.setServer(JinxUtils.getValueByXPath(doc, "/rsp/photo/@server"));
	info.setFarm(JinxUtils.getValueByXPath(doc, "/rsp/photo/@farm"));
	info.setDateUploaded(JinxUtils.parseTimestampToDate(JinxUtils.getValueByXPath(doc, "/rsp/photo/@dateuploaded")));
	info.setIsFavorite(JinxUtils.getValueByXPath(doc, "/rsp/photo/@isfavorite").equals("1"));
	info.setLicense(JinxUtils.getValueByXPath(doc, "/rsp/photo/@license"));
	info.setRotation(JinxUtils.getValueByXPathAsInt(doc, "/rsp/photo/@rotation"));
	info.setOriginalSecret(JinxUtils.getValueByXPath(doc, "/rsp/photo/@originalsecret"));
	info.setOriginalFormat(JinxUtils.getValueByXPath(doc, "/rsp/photo/@originalformat"));
	info.setViews(JinxUtils.getValueByXPathAsInt(doc, "/rsp/photo/@views"));
	info.setMedia(JinxUtils.getValueByXPath(doc, "/rsp/photo/@media"));

	info.setOwnerNsid(JinxUtils.getValueByXPath(doc, "/rsp/photo/owner/@nsid"));
	info.setOwnerUsername(JinxUtils.getValueByXPath(doc, "/rsp/photo/owner/@username"));
	info.setOwnerRealname(JinxUtils.getValueByXPath(doc, "/rsp/photo/owner/@realname"));
	info.setOwnerLocation(JinxUtils.getValueByXPath(doc, "/rsp/photo/owner/@location"));

	info.setTitle(JinxUtils.getValueByXPath(doc, "/rsp/photo/title"));
	info.setDescription(JinxUtils.getValueByXPath(doc, "/rsp/photo/description"));

	info.setIsPublic(JinxUtils.getValueByXPath(doc, "/rsp/photo/visibility/@ispublic").equals("1"));
	info.setIsFriend(JinxUtils.getValueByXPath(doc, "/rsp/photo/visibility/@isfriend").equals("1"));
	info.setIsFamily(JinxUtils.getValueByXPath(doc, "/rsp/photo/visibility/@isfamily").equals("1"));

	info.setDatePosted(JinxUtils.parseTimestampToDate(JinxUtils.getValueByXPath(doc, "/rsp/photo/dates/@posted")));
	info.setDateTaken(JinxUtils.parseMySqlDatetimeToDate(JinxUtils.getValueByXPath(doc, "/rsp/photo/dates/@taken")));
	info.setDateTakenGranularity(JinxUtils.getValueByXPath(doc, "/rsp/photo/dates/@takengranularity"));
	info.setDateLastUpdate(JinxUtils.parseTimestampToDate(JinxUtils.getValueByXPath(doc, "/rsp/photo/dates/@lastupdate")));

	info.setPermComment(JinxUtils.getValueByXPath(doc, "/rsp/photo/permissions/@permcomment"));
	info.setPermAddMeta(JinxUtils.getValueByXPath(doc, "/rsp/photo/permissions/@permaddmeta"));

	info.setCanComment(JinxUtils.getValueByXPath(doc, "/rsp/photo/editability/@cancomment").equals("1"));
	info.setCanAddMeta(JinxUtils.getValueByXPath(doc, "/rsp/photo/editability/@canaddmeta").equals("1"));

	info.setCanDownload(JinxUtils.getValueByXPath(doc, "/rsp/photo/usage/@candownload").equals("1"));
	info.setCanBlog(JinxUtils.getValueByXPath(doc, "/rsp/photo/usage/@canblog").equals("1"));
	info.setCanPrint(JinxUtils.getValueByXPath(doc, "/rsp/photo/usage/@canprint").equals("1"));
	info.setCanShare(JinxUtils.getValueByXPath(doc, "/rsp/photo/usage/@canshare").equals("1"));

	info.setComments(JinxUtils.getValueByXPathAsInt(doc, "/rsp/photo/comments"));



	NodeList notes = doc.getElementsByTagName("note");
	if (notes != null) {
	    for (int i = 0; i < notes.getLength(); i++) {
		Node note = notes.item(i);
		if (note != null) {
		    NamedNodeMap attrs = note.getAttributes();
		    Note n = new Note();
		    n.setId(JinxUtils.getAttribute(attrs, "id"));
		    n.setAuthor(JinxUtils.getAttribute(attrs, "author"));
		    n.setAuthorName(JinxUtils.getAttribute(attrs, "authorname"));
		    n.setX(JinxUtils.getAttributeAsInt(attrs, "x"));
		    n.setY(JinxUtils.getAttributeAsInt(attrs, "y"));
		    n.setW(JinxUtils.getAttributeAsInt(attrs, "w"));
		    n.setH(JinxUtils.getAttributeAsInt(attrs, "h"));

		    n.setText(note.getTextContent());

		    noteList.add(n);
		}
	    }
	}

	NodeList tags = doc.getElementsByTagName("tag");
	if (tags != null) {
	    for (int i = 0; i < tags.getLength(); i++) {
		Node tag = tags.item(i);
		if (tag != null) {
		    NamedNodeMap attrs = tag.getAttributes();
		    Tag t = new Tag();
		    t.setId(JinxUtils.getAttribute(attrs, "id"));
		    t.setAuthor(JinxUtils.getAttribute(attrs, "author"));
		    t.setRaw(JinxUtils.getAttribute(attrs, "raw"));
		    t.setMachineTag(JinxUtils.getAttributeAsBoolean(attrs, "machine_tag"));
		    t.setText(tag.getTextContent());

		    tagList.add(t);
		}
	    }
	}

	NodeList urls = doc.getElementsByTagName("url");
	if (urls != null) {
	    for (int i = 0; i < urls.getLength(); i++) {
		Node url = urls.item(i);
		if (url != null) {
		    NamedNodeMap attrs = url.getAttributes();
		    Url u = new Url();
		    u.setType(JinxUtils.getAttribute(attrs, "type"));

		    u.setUrl(url.getTextContent());

		    urlList.add(u);
		}
	    }
	}

	info.setNoteList(noteList);
	info.setTagList(tagList);
	info.setUrlList(urlList);

	return info;
    }



    /**
     * Returns a list of your photos that are not part of any sets.
     *
     * This method requires authentication with 'read' permission.
     *
     * This method uses the SearchParameters object to define which photos to
     * return that are not in any set. Not all search parameters are valid for
     * this method call. The only supported search parameters are:
     *
     * <ul>
     * <li>minUploadDate</li>
     * <li>maxUploadDate</li>
     * <li>minTakenDate</li>
     * <li>maxTakenDate</li>
     * <li>privacyFilter</li>
     * <li>media</li>
     * <li>extras</li>
     * </ul>
     *
     * All other attributes of the search parameters object will be ignored for
     * this method call.
     *
     * NOTE: Dates seem to be not working.
     *
     * @param searchParameters defines which photos not in any set to return.
     * @param perPage Number of photos to return per page. If this argument is
     *        less than 1, it defaults to 100. The maximum allowed value is 500.

     * @param page the page of results to return. If this argument is less than
     *        1, it defaults to 1.
     * @return photos object with the results of the method call.
     * @throws JinxException
     */
    public Photos getNotInSet(SearchParameters searchParameters, int perPage, int page) throws JinxException {
	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.photos.getNotInSet");
	params.put("api_key", Jinx.getInstance().getApiKey());
	if (searchParameters != null) {
	    if (searchParameters.getMinUploadDate() != null) {
		params.put("min_upload_date", JinxUtils.formatDateAsUnixTimestamp(searchParameters.getMinUploadDate()));
	    }
	    if (searchParameters.getMaxUploadDate() != null) {
		params.put("max_upload_date", JinxUtils.formatDateAsUnixTimestamp(searchParameters.getMaxUploadDate()));
	    }
	    if (searchParameters.getMinTakenDate() != null) {
		params.put("min_taken_date", JinxUtils.formatDateAsMySqlTimestamp(searchParameters.getMinTakenDate()));
	    }
	    if (searchParameters.getMaxTakenDate() != null) {
		params.put("max_taken_date", JinxUtils.formatDateAsMySqlTimestamp(searchParameters.getMaxTakenDate()));
	    }
	    if (! JinxUtils.isEmpty(searchParameters.getPrivacyFilter())) {
		params.put("privacy_filter", searchParameters.getPrivacyFilter());
	    }
	    if (! JinxUtils.isEmpty(searchParameters.getMedia())) {
		params.put("media", searchParameters.getMedia());
	    }
	    if (! JinxUtils.isEmpty(searchParameters.getExtras())) {
		params.put("extras", searchParameters.getExtras());
	    }
	}
	if (perPage > 0) {
	    params.put("per_page", Integer.toString(perPage));
	}
	if (page > 0) {
	    params.put("page", Integer.toString(page));
	}

	Document doc = Jinx.getInstance().callFlickr(params);

	return this.parsePhotosXml(doc);
    }



    /**
     * Get permissions for a photo.
     *
     * This method requires authentication with 'read' permission.
     *
     * @param photoId the id of the photo to get permissions for.
     * @return permissions for the photo.
     * @throws JinxException if there are any errors
     */
    public Perms getPerms(String photoId) throws JinxException {
	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.photos.getPerms");
	params.put("api_key", Jinx.getInstance().getApiKey());
	params.put("photo_id", photoId);

	Document doc = Jinx.getInstance().callFlickr(params);
	Perms p = new Perms();

	/*
	 <?xml version="1.0" encoding="utf-8" ?>
	 <rsp stat="ok">
	    <perms id="4634128194" ispublic="1" isfriend="0" isfamily="0"
		permcomment="3" permaddmeta="2" />
	 </rsp>
	 */

	p.setId(JinxUtils.getValueByXPath(doc, "/rsp/perms/@id"));
	p.setIsPublic(JinxUtils.getValueByXPath(doc, "/rsp/perms/@ispublic").equals("1"));
	p.setIsFriend(JinxUtils.getValueByXPath(doc, "/rsp/perms/@isfriend").equals("1"));
	p.setIsFamily(JinxUtils.getValueByXPath(doc, "/rsp/perms/@isfamily").equals("1"));
	p.setPermComment(JinxUtils.getValueByXPath(doc, "/rsp/perms/@permcomment"));
	p.setPermAddMeta(JinxUtils.getValueByXPath(doc, "/rsp/perms/@permaddmeta"));

	return p;
    }


    /**
     * Returns a list of the latest public photos uploaded to flickr.
     *
     * This method does not require authentication.
     *
     * Extras should be a comma separated list of extra information to retrieve
     * for each photo, or null if you do not want any extra information. The
     * currently supported values are:
     *
     * <ul>
     * <li>EXTRAS_DESCRIPTION</li>
     * <li>EXTRAS_LICENSE</li>
     * <li>EXTRAS_DATE_UPLOAD</li>
     * <li>EXTRAS_DATE_TAKEN</li>
     * <li>EXTRAS_OWNER_NAME</li>
     * <li>EXTRAS_ICON_SERVER</li>
     * <li>EXTRAS_ORIGINAL_FORMAT</li>
     * <li>EXTRAS_LAST_UPDATE</li>
     * <li>EXTRAS_GEO</li>
     * <li>EXTRAS_TAGS</li>
     * <li>EXTRAS_MACHINE_TAGS</li>
     * <li>EXTRAS_O_DIMS</li>
     * <li>EXTRAS_VIEWS</li>
     * <li>EXTRAS_MEDIA</li>
     * <li>EXTRAS_PATH_ALIAS</li>
     * <li>EXTRAS_URL_SQ</li>
     * <li>EXTRAS_URL_T</li>
     * <li>EXTRAS_URL_S</li>
     * <li>EXTRAS_URL_M</li>
     * <li>EXTRAS_URL_O</li>
     * </ul>
     *
     * @param extras comma separated list of extra information to retrieve for
     *        each photo.
     * @param perPage number of photos to return per page. If this argument is
     *        less than 1, it defaults to 100. The maximum allowed value is 500.
     * @param page the page of results to return. If this argument is
     *        less than 1, it defaults to 1
     * @return photos object containing the most recently uploaded photos.
     * @throws JinxException if there are any errors.
     */
    public Photos getRecent(String extras, int perPage, int page) throws JinxException {
	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.photos.getRecent");
	params.put("api_key", Jinx.getInstance().getApiKey());
	if (! JinxUtils.isEmpty(extras)) {
	    params.put("extras", extras);
	}
	if (perPage > 0) {
	    params.put("per_page", Integer.toString(perPage));
	}
	if (page > 0) {
	    params.put("page", Integer.toString(page));
	}

	Document doc = Jinx.getInstance().callFlickr(params, false);

	return this.parsePhotosXml(doc);
    }


    
    
    /**
     * Returns the available sizes for a photo.
     * The calling user must have permission to view the photo.
     *
     * This method does not require authentication.
     *

     * @param photoId id of the photo to fetch size information for.
     * @return available sizes for a photo.
     * @throws JinxException if there are any errors.
     */
    public Sizes getSizes(String photoId) throws JinxException {
	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.photos.getSizes");
	params.put("api_key", Jinx.getInstance().getApiKey());
	params.put("photo_id", photoId);

	/*
	<?xml version="1.0" encoding="utf-8" ?>
	<rsp stat="ok">
	    <sizes canblog="0" canprint="0" candownload="1">
		<size label="Square" width="75" height="75" source="http://farm5.static.flickr.com/4002/4634128194_ed83a858d6_s.jpg" url="http://www.flickr.com/photos/jeremybrooks/4634128194/sizes/sq/" media="photo" />
		<size label="Thumbnail" width="100" height="100" source="http://farm5.static.flickr.com/4002/4634128194_ed83a858d6_t.jpg" url="http://www.flickr.com/photos/jeremybrooks/4634128194/sizes/t/" media="photo" />
		<size label="Small" width="240" height="240" source="http://farm5.static.flickr.com/4002/4634128194_ed83a858d6_m.jpg" url="http://www.flickr.com/photos/jeremybrooks/4634128194/sizes/s/" media="photo" />
		<size label="Medium" width="500" height="500" source="http://farm5.static.flickr.com/4002/4634128194_ed83a858d6.jpg" url="http://www.flickr.com/photos/jeremybrooks/4634128194/sizes/m/" media="photo" />
		<size label="Large" width="1024" height="1024" source="http://farm5.static.flickr.com/4002/4634128194_ed83a858d6_b.jpg" url="http://www.flickr.com/photos/jeremybrooks/4634128194/sizes/l/" media="photo" />
		<size label="Original" width="3744" height="3744" source="http://farm5.static.flickr.com/4002/4634128194_533350d34f_o.jpg" url="http://www.flickr.com/photos/jeremybrooks/4634128194/sizes/o/" media="photo" />
	    </sizes>
	</rsp>
	*/

	Document doc = Jinx.getInstance().callFlickr(params, false);
	Sizes s = new Sizes();
	s.setId(photoId);

	s.setCanBlog(JinxUtils.getValueByXPath(doc, "/rsp/sizes/@canblog").equals("1"));
	s.setCanPrint(JinxUtils.getValueByXPath(doc, "/rsp/sizes/@canprint").equals("1"));
	s.setCanDownload(JinxUtils.getValueByXPath(doc, "/rsp/sizes/@candownload").equals("1"));

	NodeList sizeList = doc.getElementsByTagName("size");
	if (sizeList != null) {
	    for (int i = 0; i < sizeList.getLength(); i++) {
		Node size = sizeList.item(i);
		NamedNodeMap attrs = size.getAttributes();
		String label = JinxUtils.getAttribute(attrs, "label");
		int width = JinxUtils.getAttributeAsInt(attrs, "width");
		int height = JinxUtils.getAttributeAsInt(attrs, "height");
		String source = JinxUtils.getAttribute(attrs, "source");
		String url = JinxUtils.getAttribute(attrs, "url");
		String media = JinxUtils.getAttribute(attrs, "media");

		s.addSize(label, width, height, source, url, media);
	    }
	}
	
	return s;
    }



    /**
     * Returns a list of your photos with no tags.
     *
     * This method requires authentication with 'read' permission.
     *
     * This method uses the SearchParameters object to define which photos to
     * return that do not have tags. Not all search parameters are valid for
     * this method call. The only supported search parameters are:
     *
     * <ul>
     * <li>minUploadDate</li>
     * <li>maxUploadDate</li>
     * <li>minTakenDate</li>
     * <li>maxTakenDate</li>
     * <li>privacyFilter</li>
     * <li>media</li>
     * <li>extras</li>
     * </ul>
     *
     * The extras parameters that are supported include:
     *
     * <ul>
     * <li>EXTRAS_DESCRIPTION</li>
     * <li>EXTRAS_LICENSE</li>
     * <li>EXTRAS_DATE_UPLOAD</li>
     * <li>EXTRAS_DATE_TAKEN</li>
     * <li>EXTRAS_OWNER_NAME</li>
     * <li>EXTRAS_ICON_SERVER</li>
     * <li>EXTRAS_ORIGINAL_FORMAT</li>
     * <li>EXTRAS_LAST_UPDATE</li>
     * <li>EXTRAS_GEO</li>
     * <li>EXTRAS_TAGS</li>
     * <li>EXTRAS_MACHINE_TAGS</li>
     * <li>EXTRAS_O_DIMS</li>
     * <li>EXTRAS_VIEWS</li>
     * <li>EXTRAS_MEDIA</li>
     * <li>EXTRAS_PATH_ALIAS</li>
     * <li>EXTRAS_URL_SQ</li>
     * <li>EXTRAS_URL_T</li>
     * <li>EXTRAS_URL_S</li>
     * <li>EXTRAS_URL_M</li>
     * <li>EXTRAS_URL_O</li>
     * </ul>
     *
     * All other attributes of the search parameters object will be ignored for
     * this method call.
     *
     * @param searchParameters defines which photos not in any set to return.
     * @param perPage Number of photos to return per page. If this argument is
     *        less than 1, it defaults to 100. The maximum allowed value is 500.

     * @param page the page of results to return. If this argument is less than
     *        1, it defaults to 1.
     * @return photos object with the results of the method call.
     * @throws JinxException
     */
    public Photos getUntagged(SearchParameters searchParameters, int perPage, int page) throws JinxException {
	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.photos.getUntagged");
	params.put("api_key", Jinx.getInstance().getApiKey());
	if (searchParameters != null) {
	    if (searchParameters.getMinUploadDate() != null) {
		params.put("min_upload_date", JinxUtils.formatDateAsMySqlTimestamp(searchParameters.getMinUploadDate()));
	    }
	    if (searchParameters.getMaxUploadDate() != null) {
		params.put("max_upload_date", JinxUtils.formatDateAsMySqlTimestamp(searchParameters.getMaxUploadDate()));
	    }
	    if (searchParameters.getMinTakenDate() != null) {
		params.put("min_taken_date", JinxUtils.formatDateAsMySqlTimestamp(searchParameters.getMinTakenDate()));
	    }
	    if (searchParameters.getMaxTakenDate() != null) {
		params.put("max_taken_date", JinxUtils.formatDateAsMySqlTimestamp(searchParameters.getMaxTakenDate()));
	    }
	    if (! JinxUtils.isEmpty(searchParameters.getPrivacyFilter())) {
		params.put("privacy_filter", searchParameters.getPrivacyFilter());
	    }
	    if (! JinxUtils.isEmpty(searchParameters.getMedia())) {
		params.put("media", searchParameters.getMedia());
	    }
	    if (! JinxUtils.isEmpty(searchParameters.getExtras())) {
		params.put("extras", searchParameters.getExtras());
	    }
	}
	if (perPage > 0) {
	    params.put("per_page", Integer.toString(perPage));
	}
	if (page > 0) {
	    params.put("page", Integer.toString(page));
	}

	Document doc = Jinx.getInstance().callFlickr(params);

	return this.parsePhotosXml(doc);
    }



    /**
     * Returns a list of your geo-tagged photos.
     *
     * This method requires authentication with 'read' permission.
     *
     * This method uses the SearchParameters object to define which photos to
     * return that do not have tags. Not all search parameters are valid for
     * this method call. The only supported search parameters are:
     *
     * <ul>
     * <li>minUploadDate</li>
     * <li>maxUploadDate</li>
     * <li>minTakenDate</li>
     * <li>maxTakenDate</li>
     * <li>privacyFilter</li>
     * <li>sort</li>
     * <li>media</li>
     * <li>extras</li>
     * </ul>
     *
     * The extras parameters that are supported include:
     *
     * <ul>
     * <li>EXTRAS_DESCRIPTION</li>
     * <li>EXTRAS_LICENSE</li>
     * <li>EXTRAS_DATE_UPLOAD</li>
     * <li>EXTRAS_DATE_TAKEN</li>
     * <li>EXTRAS_OWNER_NAME</li>
     * <li>EXTRAS_ICON_SERVER</li>
     * <li>EXTRAS_ORIGINAL_FORMAT</li>
     * <li>EXTRAS_LAST_UPDATE</li>
     * <li>EXTRAS_GEO</li>
     * <li>EXTRAS_TAGS</li>
     * <li>EXTRAS_MACHINE_TAGS</li>
     * <li>EXTRAS_O_DIMS</li>
     * <li>EXTRAS_VIEWS</li>
     * <li>EXTRAS_MEDIA</li>
     * <li>EXTRAS_PATH_ALIAS</li>
     * <li>EXTRAS_URL_SQ</li>
     * <li>EXTRAS_URL_T</li>
     * <li>EXTRAS_URL_S</li>
     * <li>EXTRAS_URL_M</li>
     * <li>EXTRAS_URL_O</li>
     * </ul>
     *
     * All other attributes of the search parameters object will be ignored for
     * this method call.
     *
     * @param searchParameters defines which photos not in any set to return.
     * @param perPage Number of photos to return per page. If this argument is
     *        less than 1, it defaults to 100. The maximum allowed value is 500.

     * @param page the page of results to return. If this argument is less than
     *        1, it defaults to 1.
     * @return photos object with the results of the method call.
     * @throws JinxException
     */
    public Photos getWithGeoData(SearchParameters searchParameters, int perPage, int page) throws JinxException {
	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.photos.getWithGeoData");
	params.put("api_key", Jinx.getInstance().getApiKey());
	if (searchParameters != null) {
	    if (searchParameters.getMinUploadDate() != null) {
		params.put("min_upload_date", JinxUtils.formatDateAsMySqlTimestamp(searchParameters.getMinUploadDate()));
	    }
	    if (searchParameters.getMaxUploadDate() != null) {
		params.put("max_upload_date", JinxUtils.formatDateAsMySqlTimestamp(searchParameters.getMaxUploadDate()));
	    }
	    if (searchParameters.getMinTakenDate() != null) {
		params.put("min_taken_date", JinxUtils.formatDateAsMySqlTimestamp(searchParameters.getMinTakenDate()));
	    }
	    if (searchParameters.getMaxTakenDate() != null) {
		params.put("max_taken_date", JinxUtils.formatDateAsMySqlTimestamp(searchParameters.getMaxTakenDate()));
	    }
	    if (! JinxUtils.isEmpty(searchParameters.getPrivacyFilter())) {
		params.put("privacy_filter", searchParameters.getPrivacyFilter());
	    }
	    if (! JinxUtils.isEmpty(searchParameters.getSort())) {
		params.put("sort", searchParameters.getSort());
	    }
	    if (! JinxUtils.isEmpty(searchParameters.getMedia())) {
		params.put("media", searchParameters.getMedia());
	    }
	    if (! JinxUtils.isEmpty(searchParameters.getExtras())) {
		params.put("extras", searchParameters.getExtras());
	    }
	}
	if (perPage > 0) {
	    params.put("per_page", Integer.toString(perPage));
	}
	if (page > 0) {
	    params.put("page", Integer.toString(page));
	}

	Document doc = Jinx.getInstance().callFlickr(params);

	return this.parsePhotosXml(doc);
    }


    /**
     * Returns a list of your photos which haven't been geo-tagged.
     *
     * This method requires authentication with 'read' permission.
     *
     * This method uses the SearchParameters object to define which photos to
     * return that do not have tags. Not all search parameters are valid for
     * this method call. The only supported search parameters are:
     *
     * <ul>
     * <li>minUploadDate</li>
     * <li>maxUploadDate</li>
     * <li>minTakenDate</li>
     * <li>maxTakenDate</li>
     * <li>privacyFilter</li>
     * <li>sort</li>
     * <li>media</li>
     * <li>extras</li>
     * </ul>
     *
     * The extras parameters that are supported include:
     *
     * <ul>
     * <li>EXTRAS_DESCRIPTION</li>
     * <li>EXTRAS_LICENSE</li>
     * <li>EXTRAS_DATE_UPLOAD</li>
     * <li>EXTRAS_DATE_TAKEN</li>
     * <li>EXTRAS_OWNER_NAME</li>
     * <li>EXTRAS_ICON_SERVER</li>
     * <li>EXTRAS_ORIGINAL_FORMAT</li>
     * <li>EXTRAS_LAST_UPDATE</li>
     * <li>EXTRAS_GEO</li>
     * <li>EXTRAS_TAGS</li>
     * <li>EXTRAS_MACHINE_TAGS</li>
     * <li>EXTRAS_O_DIMS</li>
     * <li>EXTRAS_VIEWS</li>
     * <li>EXTRAS_MEDIA</li>
     * <li>EXTRAS_PATH_ALIAS</li>
     * <li>EXTRAS_URL_SQ</li>
     * <li>EXTRAS_URL_T</li>
     * <li>EXTRAS_URL_S</li>
     * <li>EXTRAS_URL_M</li>
     * <li>EXTRAS_URL_O</li>
     * </ul>
     *
     * All other attributes of the search parameters object will be ignored for
     * this method call.
     *
     * @param searchParameters defines which photos not in any set to return.
     * @param perPage Number of photos to return per page. If this argument is
     *        less than 1, it defaults to 100. The maximum allowed value is 500.

     * @param page the page of results to return. If this argument is less than
     *        1, it defaults to 1.
     * @return photos object with the results of the method call.
     * @throws JinxException
     */
    public Photos getWithoutGeoData(SearchParameters searchParameters, int perPage, int page) throws JinxException {
	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.photos.getWithoutGeoData");
	params.put("api_key", Jinx.getInstance().getApiKey());
	if (searchParameters != null) {
	    if (searchParameters.getMinUploadDate() != null) {
		params.put("min_upload_date", JinxUtils.formatDateAsMySqlTimestamp(searchParameters.getMinUploadDate()));
	    }
	    if (searchParameters.getMaxUploadDate() != null) {
		params.put("max_upload_date", JinxUtils.formatDateAsMySqlTimestamp(searchParameters.getMaxUploadDate()));
	    }
	    if (searchParameters.getMinTakenDate() != null) {
		params.put("min_taken_date", JinxUtils.formatDateAsMySqlTimestamp(searchParameters.getMinTakenDate()));
	    }
	    if (searchParameters.getMaxTakenDate() != null) {
		params.put("max_taken_date", JinxUtils.formatDateAsMySqlTimestamp(searchParameters.getMaxTakenDate()));
	    }
	    if (! JinxUtils.isEmpty(searchParameters.getPrivacyFilter())) {
		params.put("privacy_filter", searchParameters.getPrivacyFilter());
	    }
	    if (! JinxUtils.isEmpty(searchParameters.getSort())) {
		params.put("sort", searchParameters.getSort());
	    }
	    if (! JinxUtils.isEmpty(searchParameters.getMedia())) {
		params.put("media", searchParameters.getMedia());
	    }
	    if (! JinxUtils.isEmpty(searchParameters.getExtras())) {
		params.put("extras", searchParameters.getExtras());
	    }
	}
	if (perPage > 0) {
	    params.put("per_page", Integer.toString(perPage));
	}
	if (page > 0) {
	    params.put("page", Integer.toString(page));
	}

	Document doc = Jinx.getInstance().callFlickr(params);

	return this.parsePhotosXml(doc);
    }


    
    /**
     * Return a list of your photos that have been recently created or which
     * have been recently modified.
     *
     * Recently modified may mean that the photo's metadata (title, description,
     * tags) may have been changed or a comment has been added (or just
     * modified somehow :-)
     *
     * This method requires authentication with 'read' permission.
     *
     * The extras parameter must be null or a comma-separated list of extras to be
     * be returned. The extras parameters that are supported include:
     *
     * <ul>
     * <li>EXTRAS_DESCRIPTION</li>
     * <li>EXTRAS_LICENSE</li>
     * <li>EXTRAS_DATE_UPLOAD</li>
     * <li>EXTRAS_DATE_TAKEN</li>
     * <li>EXTRAS_OWNER_NAME</li>
     * <li>EXTRAS_ICON_SERVER</li>
     * <li>EXTRAS_ORIGINAL_FORMAT</li>
     * <li>EXTRAS_LAST_UPDATE</li>
     * <li>EXTRAS_GEO</li>
     * <li>EXTRAS_TAGS</li>
     * <li>EXTRAS_MACHINE_TAGS</li>
     * <li>EXTRAS_O_DIMS</li>
     * <li>EXTRAS_VIEWS</li>
     * <li>EXTRAS_MEDIA</li>
     * <li>EXTRAS_PATH_ALIAS</li>
     * <li>EXTRAS_URL_SQ</li>
     * <li>EXTRAS_URL_T</li>
     * <li>EXTRAS_URL_S</li>
     * <li>EXTRAS_URL_M</li>
     * <li>EXTRAS_URL_O</li>
     * </ul>
     *
     * @param minDate the date from which modifications should be compared.
     * @param extras comma-delimited list of extra information to fetch for each
     *        returned record.
     * @param perPage Number of photos to return per page. If this argument is
     *        less than 1, it defaults to 100. The maximum allowed value is 500.

     * @param page the page of results to return. If this argument is less than
     *        1, it defaults to 1.
     * @return photos recently created or modified.
     * @throws JinxException if there are any errors.
     */
    public Photos recentlyUpdated(Date minDate, String extras, int perPage, int page) throws JinxException {
	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.photos.recentlyUpdated");
	params.put("api_key", Jinx.getInstance().getApiKey());
	params.put("min_date", JinxUtils.formatDateAsUnixTimestamp(minDate));
	if (! JinxUtils.isEmpty(extras)) {
	    params.put("extras", extras);
	}
	if (perPage > 0) {
	    params.put("per_page", Integer.toString(perPage));
	}
	if (page > 0) {
	    params.put("page", Integer.toString(page));
	}

	Document doc = Jinx.getInstance().callFlickr(params);

	return this.parsePhotosXml(doc);
    }


    
    /**
     * Remove a tag from a photo.
     *
     * This method requires authentication with 'write' permission.
     *
     * This parameter should contain a tag id, as returned by
     * flickr.photos.getInfo.
     *
     * @param tagId the tag to remove from the photo.
     * @throws JinxException if there are any errors.
     */
    public void removeTag(String tagId) throws JinxException {
	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.photos.removeTag");
	params.put("api_key", Jinx.getInstance().getApiKey());
	params.put("tag_id", tagId);

	Jinx.getInstance().callFlickr(params);
    }



    /**
     * Set the content type of a photo.
     *
     * This method requires authentication with 'write' permission.
     *
     * The contentType parameter must be one of these constants from the
     * JinxConstants class:
     * 
     * <ul>
     * <li>CONTENT_PHOTOS</li>
     * <li>CONTENT_SCREENSHOTS</li>
     * <li>CONTENT_OTHER</li>
     * </ul>
     *
     * @param photoId id of the photo to set the content type of.
     * @param contentType content type of the photo.
     * @throws JinxException if there are any errors.
     */
    public void setContentType(String photoId, String contentType) throws JinxException {
	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.photos.setContentType");
	params.put("api_key", Jinx.getInstance().getApiKey());
	params.put("photo_id", photoId);
	params.put("content_type", contentType);

	// Call as POST request
	Jinx.getInstance().callFlickr(params, true, true);
    }


    /**
     * Set one or both of the dates for a photo.
     *
     * This method requires authentication with 'write' permission.
     *
     * Taken dates also have a 'granularity' - the accuracy to which we know
     * the date to be true. At present, only four granularities are used:
     * <ul>
     * <li>DATE_TAKEN_GRANULARITY_DATE_TIME</li>
     * <li>DATE_TAKEN_GRANULARITY_DATE</li>
     * <li>DATE_TAKEN_GRANULARITY_YEAR</li>
     * <li>DATE_TAKEN_GRANULARITY_CIRCA</li>
     * </ul>
     *
     * @param photoId id of the photo to edit dates for.
     * @param datePosted date the photo was uploaded to Jinx.
     * @param dateTaken date the photo was taken.
     * @param dateTakenGranularity granularity of the date the photo was taken.
     * @throws JinxException if there are any errors.
     */
    public void setDates(String photoId, Date datePosted, Date dateTaken, String dateTakenGranularity) throws JinxException {
	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.photos.setDates");
	params.put("api_key", Jinx.getInstance().getApiKey());
	params.put("photo_id", photoId);
	if (datePosted != null) {
	    params.put("date_posted", JinxUtils.formatDateAsUnixTimestamp(datePosted));
	}
	if (dateTaken != null) {
	    params.put("date_taken", JinxUtils.formatDateAsMySqlTimestamp(dateTaken));
	}
	if (! JinxUtils.isEmpty(dateTakenGranularity)) {
	    params.put("date_taken_granularity", dateTakenGranularity);
	}

	Jinx.getInstance().callFlickr(params, true, true);
    }



    
    /**
     * Set the meta information for a photo.
     *
     * This method requires authentication with 'write' permission.
     *
     * Note: This method requires an HTTP POST request.
     * 
     * @param photoId the id of the photo to set information for.
     * @param title the title for the photo
     * @param description the description for the photo.
     * @throws JinxException if there are any errors.
     */
    public void setMeta(String photoId, String title, String description) throws JinxException {
	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.photos.setMeta");
	params.put("api_key", Jinx.getInstance().getApiKey());
	params.put("photo_id", photoId);
	if (! JinxUtils.isEmpty(title)) {
	    params.put("title", title);
	}
	if (! JinxUtils.isEmpty(description)) {
	    params.put("description", description);
	}
	Jinx.getInstance().callFlickr(params, true, true);
    }


    
    /**
     *
     * @param perms
     * @return
     * @throws JinxException
     */
    public PhotoId setPerms(Perms perms) throws JinxException {
	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.photos.setPerms");
	params.put("api_key", Jinx.getInstance().getApiKey());
	params.put("photo_id", perms.getId());
	params.put("is_public", JinxUtils.booleanToString(perms.isIsPublic()));
	params.put("is_friend", JinxUtils.booleanToString(perms.isIsFriend()));
	params.put("is_family", JinxUtils.booleanToString(perms.isIsFamily()));
	if (! JinxUtils.isEmpty(perms.getPermComment())) {
	    params.put("perm_comment", perms.getPermComment());
	}
	if (! JinxUtils.isEmpty(perms.getPermAddMeta())) {
	    params.put("perm_addmeta", perms.getPermAddMeta());
	}

	Document doc = Jinx.getInstance().callFlickr(params, true, true);
	PhotoId p = new PhotoId();

	/*
	<?xml version="1.0" encoding="utf-8" ?>
	<rsp stat="ok">
	    <photoid secret="ed83a858d6" originalsecret="533350d34f">4634128194</photoid>
	</rsp>
	*/

	p.setSecret(JinxUtils.getValueByXPath(doc, "/rsp/photoid/@secret"));
	p.setOriginalSecret(JinxUtils.getValueByXPath(doc, "/rsp/photoid/@originalsecret"));
	p.setId(JinxUtils.getValueByXPath(doc, "/rsp/photoid"));

	return p;
    }


    
    /**
     * Set the safety level of a photo.
     *
     * This method requires authentication with 'write' permission.
     *
     * Note: This method requires an HTTP POST request.
     *
     * The safety level parameter can be:
     * <ul>
     * <li>SAFE_SEARCH_SAFE</li>
     * <li>SAFE_SEARCH_MODERATE</li>
     * <li>SAFE_SEARCH_RESTRICTED</li>
     * </ul>
     *
     * @param photoId id of the photo to set the safety of.
     * @param safetyLevel safety level for the photo.
     * @param hidden whether or not to hide the photo from public searches.
     * @throws JinxException if there are any errors.
     */
    public void setSafetyLevel(String photoId, String safetyLevel, boolean hidden) throws JinxException {
	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.photos.setSafetyLevel");
	params.put("api_key", Jinx.getInstance().getApiKey());
	params.put("photo_id", photoId);
	if (! JinxUtils.isEmpty(safetyLevel)) {
	    params.put("safety_level", safetyLevel);
	}
	params.put("hidden", JinxUtils.booleanToString(hidden));

	Jinx.getInstance().callFlickr(params, true, true);
    }
    
    
    /**
     * Set the tags for a photo.
     *
     * This method requires authentication with 'write' permission.
     *
     * Note: This method requires an HTTP POST request.
     *
     * @param photoId id of the photo to set tags for.
     * @param tagList list of all tags for the photo.
     * @throws JinxException if there are any errors.
     */
    public void setTags(String photoId, List<String> tagList) throws JinxException {
	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.photos.setTags");
	params.put("api_key", Jinx.getInstance().getApiKey());
	params.put("photo_id", photoId);

	// Make a single space delimited string from the list
	// Each tag is enclosed in quotes to preserve spaces
	StringBuilder sb = new StringBuilder();
	for (String s : tagList) {
	    sb.append('"');
	    sb.append(s.trim());
	    sb.append('"');
	    sb.append(' ');
	}
	params.put("tags", sb.toString().trim());

	Jinx.getInstance().callFlickr(params, true, true);
    }


    /*
     * Several of the Photos methods return a standard block of XML containing
     * photo data. This method will parse that standard block and return a
     * Photos object with a list of Photos.
     *
     * @param xml the XML containing the photos xml.
     * @return Photos object
     * @throws JinxException if there are any errors.
     */
    Photos parsePhotosXml(Document doc) throws JinxException {
	Photos photos = new Photos();
	List<Photo> photoList = new ArrayList<Photo>();

	photos.setPage(JinxUtils.getValueByXPathAsInt(doc, "/rsp/photos/@page"));
	photos.setPages(JinxUtils.getValueByXPathAsInt(doc, "/rsp/photos/@pages"));
	photos.setPerPage(JinxUtils.getValueByXPathAsInt(doc, "/rsp/photos/@perpage"));
	photos.setTotal(JinxUtils.getValueByXPathAsInt(doc, "/rsp/photos/@total"));

	// Get all the photo nodes
	NodeList nodeList = doc.getElementsByTagName("photo");
	for (int i = 0; i < nodeList.getLength(); i++) {
	    Photo p = new Photo();
	    Node photoNode = nodeList.item(i);
	    NamedNodeMap map = photoNode.getAttributes();
	    p.setId(JinxUtils.getAttribute(map, "id"));
	    p.setOwnerNsid(JinxUtils.getAttribute(map, "owner"));
	    p.setSecret(JinxUtils.getAttribute(map, "secret"));
	    p.setServer(JinxUtils.getAttribute(map, "server"));
	    p.setFarm(JinxUtils.getAttribute(map, "farm"));
	    p.setTitle(JinxUtils.getAttribute(map, "title"));
	    p.setIsPublic(JinxUtils.getAttribute(map, "ispublic").equals("1"));
	    p.setIsFriend(JinxUtils.getAttribute(map, "isfriend").equals("1"));
	    p.setIsFamily(JinxUtils.getAttribute(map, "isfamily").equals("1"));
	    p.setDateTaken(JinxUtils.parseMySqlDatetimeToDate(JinxUtils.getAttribute(map, "datetaken")));
	    p.setDateTakenGranularity(JinxUtils.getAttribute(map, "datetakengranularity"));
	    p.setDateUploaded(JinxUtils.parseTimestampToDate(JinxUtils.getAttribute(map, "dateupload")));
	    p.setLatitude(JinxUtils.getAttribute(map, "latitude"));
	    p.setLongitude(JinxUtils.getAttribute(map, "longitude"));
	    p.setAccuracy(JinxUtils.getAttribute(map, "accuracy"));
	    p.setIconServer(JinxUtils.getAttribute(map, "iconserver"));
	    p.setIconFarm(JinxUtils.getAttribute(map, "iconfarm"));
	    p.setDateLastUpdate(JinxUtils.parseTimestampToDate(JinxUtils.getAttribute(map, "lastupdate")));
	    p.setLicense(JinxUtils.getAttribute(map, "license"));
	    p.setMachineTags(JinxUtils.getAttribute(map, "machine_tags"));
	    p.setMedia(JinxUtils.getAttribute(map, "media"));
	    p.setMediaStatus(JinxUtils.getAttribute(map, "media_status"));
	    p.setOriginalSecret(JinxUtils.getAttribute(map, "originalsecret"));
	    p.setOriginalFormat(JinxUtils.getAttribute(map, "originalformat"));
	    p.setOwnerUsername(JinxUtils.getAttribute(map, "ownername"));
	    p.setoWidth(JinxUtils.getAttributeAsInt(map, "o_width"));
	    p.setoHeight(JinxUtils.getAttributeAsInt(map, "o_height"));
	    p.setPathAlias(JinxUtils.getAttribute(map, "pathalias"));
	    p.setTags(JinxUtils.getAttribute(map, "tags"));
	    p.setUrlM(JinxUtils.getAttribute(map, "url_m"));
	    p.setHeightM(JinxUtils.getAttributeAsInt(map, "height_m"));
	    p.setWidthM(JinxUtils.getAttributeAsInt(map, "width_m"));
	    p.setUrlO(JinxUtils.getAttribute(map, "url_o"));
	    p.setHeightO(JinxUtils.getAttributeAsInt(map, "height_o"));
	    p.setWidthO(JinxUtils.getAttributeAsInt(map, "width_o"));
	    p.setUrlS(JinxUtils.getAttribute(map, "url_s"));
	    p.setHeightS(JinxUtils.getAttributeAsInt(map, "height_s"));
	    p.setWidthS(JinxUtils.getAttributeAsInt(map, "width_s"));
	    p.setUrlSq(JinxUtils.getAttribute(map, "url_sq"));
	    p.setHeightSq(JinxUtils.getAttributeAsInt(map, "height_sq"));
	    p.setWidthSq(JinxUtils.getAttributeAsInt(map, "width_sq"));
	    p.setUrlT(JinxUtils.getAttribute(map, "url_t"));
	    p.setHeightT(JinxUtils.getAttributeAsInt(map, "height_t"));
	    p.setWidthT(JinxUtils.getAttributeAsInt(map, "width_t"));
	    p.setUrlZ(JinxUtils.getAttribute(map, "url_z"));
	    p.setHeightZ(JinxUtils.getAttributeAsInt(map, "height_z"));
	    p.setWidthZ(JinxUtils.getAttributeAsInt(map, "width_z"));
	    p.setViews(JinxUtils.getAttributeAsInt(map, "views"));
	    p.setHasComment(JinxUtils.getAttributeAsBoolean(map, "has_comment"));
	    p.setDescription(JinxUtils.getNamedChildTextContent(photoNode, "description"));
	    //p.setDescription(JinxUtils.getFirstChildTextContent(photoNode));
	    p.setComment(JinxUtils.getNamedChildTextContent(photoNode, "comment"));

	    photoList.add(p);
	}

	photos.setPhotos(photoList);

	return photos;
    }

}
