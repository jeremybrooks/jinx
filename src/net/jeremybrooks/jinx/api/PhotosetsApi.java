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
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import net.jeremybrooks.jinx.Jinx;
import net.jeremybrooks.jinx.JinxException;
import net.jeremybrooks.jinx.JinxUtils;
import net.jeremybrooks.jinx.dto.Context;
import net.jeremybrooks.jinx.dto.Photo;
import net.jeremybrooks.jinx.dto.Photos;
import net.jeremybrooks.jinx.dto.Photoset;
import net.jeremybrooks.jinx.dto.PhotosetInfo;
import net.jeremybrooks.jinx.dto.Photosets;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author jeremyb
 */
public class PhotosetsApi {

    /** Instance of this class. */
    private static PhotosetsApi instance;

    private PhotosetsApi () {
    }

    /**
     * Gets a reference to the only instance of PhotosetsApi.
     *
     * @return instance of PhotosetsApi.
     */
    public static PhotosetsApi getInstance() {
	if (instance == null) {
	    instance = new PhotosetsApi();
	}

	return instance;
    }


    /**
     * Add a photo to the end of an existing photoset.
     *
     * This method requires authentication with 'write' permission.
     *
     * Note: This method requires an HTTP POST request.
     *
     * @param photosetId id of the photoset to add a photo to.
     * @param photoId id of the photo to add to the set.
     * @throws JinxException if there are any errors.
     */
    public void addPhoto(String photosetId, String photoId) throws JinxException {
	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.photosets.addPhoto");
	params.put("api_key", Jinx.getInstance().getApiKey());
	params.put("photoset_id", photosetId);
	params.put("photo_id", photoId);

	Jinx.getInstance().callFlickr(params, true, true);
    }


    
    /**
     * Create a new photoset for the calling user.
     *
     * This method requires authentication with 'write' permission.
     *
     * Note: This method requires an HTTP POST request.
     *
     * The returned Photoset object will only have the id and url populated.
     *
     * @param title a title for the photoset.
     * @param description a description of the photoset. May contain limited html.
     * @param primaryPhotoId the id of the photo to represent this set. The photo
     *        must belong to the calling user.
     * @return object representing the newly created photoset.
     * @throws JinxException if there are any errors.
     */
    public Photoset create(String title, String description, String primaryPhotoId) throws JinxException {
	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.photosets.create");
	params.put("api_key", Jinx.getInstance().getApiKey());
	params.put("title", title);
	if (! JinxUtils.isEmpty(description)) {
	    params.put("description", description);
	}
	params.put("primary_photo_id", primaryPhotoId);

	Document doc = Jinx.getInstance().callFlickr(params, true, true);

	Photoset p = new Photoset();

	/*
	 <?xml version="1.0" encoding="utf-8" ?>
	 <rsp stat="ok">
	    <photoset id="72157624182259464" url="http://www.flickr.com/photos/jeremybrooks/sets/72157624182259464/" />
	 </rsp>
	 */

	p.setId(JinxUtils.getValueByXPath(doc, "/rsp/photoset/@id"));
	p.setUrl(JinxUtils.getValueByXPath(doc, "/rsp/photoset/@url"));

	return p;
    }


    
    /**
     * Delete a photoset.
     *
     * This method requires authentication with 'write' permission.
     *
     * Note: This method requires an HTTP POST request.
     *
     * @param photosetId id of the photoset to delete. It must be owned by the
     *        calling user.
     * @throws JinxException if there are any errors.
     */
    public void delete(String photosetId) throws JinxException {
	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.photosets.delete");
	params.put("api_key", Jinx.getInstance().getApiKey());
	params.put("photoset_id", photosetId);

	Jinx.getInstance().callFlickr(params, true, true);
    }


    
    /**
     * Modify the meta-data for a photoset.
     *
     * This method requires authentication with 'write' permission.
     *
     * Note: This method requires an HTTP POST request.
     *
     * @param photosetId id of the photoset to modify.
     * @param title new title for the photoset.
     * @param description description of the photoset. May contain limited html.
     * @throws JinxException if there are any errors.
     */
    public void editMeta(String photosetId, String title, String description) throws JinxException {
	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.photosets.editMeta");
	params.put("api_key", Jinx.getInstance().getApiKey());
	params.put("photoset_id", photosetId);
	params.put("title", title);
	if (! JinxUtils.isEmpty(description)) {
	    params.put("description", description);
	}

	Jinx.getInstance().callFlickr(params, true, true);
    }


    /**
     * Modify the photos in a photoset. Use this method to add, remove and re-order photos.
     * 
     * This method requires authentication with 'write' permission.
     * 
     * Note: This method requires an HTTP POST request.
     * 
     * Photos will appear in the set in the order of the list. The photo list
     * must contain the primary photo id. All photos must belong to the owner
     * of the set. This list of photos replaces the existing list. Call 
     * PhotosetsApi.getInstance().addPhoto to append a photo to a set.
     * 
     * @param photosetId if of the photoset to modify. The photoset must belong
     *        to the calling user.
     * @param primaryPhotoId id of the photo to use as the primary photo for the
     *        set. This id must also be passed along in the photos list argument.
     * @param photos list of photo ids to include in the set.
     * @throws JinxException if there are any errors.
     */
    public void editPhotos(String photosetId, String primaryPhotoId, List<String>photos) throws JinxException {
	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.photosets.editPhotos");
	params.put("api_key", Jinx.getInstance().getApiKey());
	params.put("photoset_id", photosetId);
	params.put("primary_photo_id", primaryPhotoId);
	
	// Make a comma separated list of photo ids
	StringBuilder sb = new StringBuilder();
	for (String s : photos) {
	    sb.append(s.trim()).append(',');
	}
	sb.deleteCharAt(sb.lastIndexOf(","));

	params.put("photo_ids", sb.toString());
	
	Jinx.getInstance().callFlickr(params, true, true);
    }


    
    /**
     * Returns next and previous photos for a photo in a set.
     *
     * This method does not require authentication.
     *
     * @param photoId the id of the photo to fetch the context for.
     * @param photosetId the id of the photoset for which to fetch the photos context.
     * @return object with the context information.
     * @throws JinxException if there are any errors.
     */
    public Context getContext(String photoId, String photosetId) throws JinxException {
	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.photosets.getContext");
	params.put("api_key", Jinx.getInstance().getApiKey());
	params.put("photo_id", photoId);
	params.put("photoset_id", photosetId);

	Document doc = Jinx.getInstance().callFlickr(params, false);
	Context c = new Context();

	Photo prevPhoto = new Photo();
	Photo nextPhoto = new Photo();

	/*
	 <?xml version="1.0" encoding="utf-8" ?>
	 <rsp stat="ok">
	    <count>544</count>
	    <prevphoto id="3228417721" secret="44ac2151f2" server="3373" farm="4" title="Un-Real Time, Plate 1" url="/photos/jeremybrooks/3228417721/in/set-72157623970374210/" thumb="http://farm4.static.flickr.com/3373/3228417721_44ac2151f2_s.jpg" media="photo" />
	    <nextphoto id="2235828830" secret="0dce00fc48" server="2339" farm="3" title="The Horizon Bleeds And Sucks Its Thumb" url="/photos/jeremybrooks/2235828830/in/set-72157623970374210/" thumb="http://farm3.static.flickr.com/2339/2235828830_0dce00fc48_s.jpg" media="photo" />
	 </rsp>
	 */

	c.setCount(JinxUtils.getValueByXPathAsInt(doc, "/rsp/count"));

	prevPhoto.setId(JinxUtils.getValueByXPath(doc, "/rsp/prevphoto/@id"));
	prevPhoto.setSecret(JinxUtils.getValueByXPath(doc, "/rsp/prevphoto/@secret"));
	prevPhoto.setServer(JinxUtils.getValueByXPath(doc, "/rsp/prevphoto/@server"));
	prevPhoto.setFarm(JinxUtils.getValueByXPath(doc, "/rsp/prevphoto/@farm"));
	prevPhoto.setTitle(JinxUtils.getValueByXPath(doc, "/rsp/prevphoto/@title"));
	prevPhoto.setUrl(JinxUtils.getValueByXPath(doc, "/rsp/prevphoto/@url"));
	prevPhoto.setThumb(JinxUtils.getValueByXPath(doc, "/rsp/prevphoto/@thumb"));
	prevPhoto.setMedia(JinxUtils.getValueByXPath(doc, "/rsp/prevphoto/@media"));

	nextPhoto.setId(JinxUtils.getValueByXPath(doc, "/rsp/nextphoto/@id"));
	nextPhoto.setSecret(JinxUtils.getValueByXPath(doc, "/rsp/nextphoto/@secret"));
	nextPhoto.setServer(JinxUtils.getValueByXPath(doc, "/rsp/nextphoto/@server"));
	nextPhoto.setFarm(JinxUtils.getValueByXPath(doc, "/rsp/nextphoto/@farm"));
	nextPhoto.setTitle(JinxUtils.getValueByXPath(doc, "/rsp/nextphoto/@title"));
	nextPhoto.setUrl(JinxUtils.getValueByXPath(doc, "/rsp/nextphoto/@url"));
	nextPhoto.setThumb(JinxUtils.getValueByXPath(doc, "/rsp/nextphoto/@thumb"));
	nextPhoto.setMedia(JinxUtils.getValueByXPath(doc, "/rsp/nextphoto/@media"));

	c.setPreviousPhoto(prevPhoto);
	c.setNextPhoto(nextPhoto);

	return c;
    }


    /**
     * Gets information about a photoset.
     *
     * This method does not require authentication.
     *
     * @param photosetId the id of the photoset to fetch information for.
     * @return photoset information.
     * @throws JinxException if there are any errors.
     */
    public PhotosetInfo getInfo(String photosetId) throws JinxException {
	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.photosets.getInfo");
	params.put("api_key", Jinx.getInstance().getApiKey());
	params.put("photoset_id", photosetId);

	Document doc = Jinx.getInstance().callFlickr(params, false);

	PhotosetInfo pi = new PhotosetInfo();

	/*
	<?xml version="1.0" encoding="utf-8" ?>
	<rsp stat="ok">
	    <photoset id="72157624018791194" owner="85853333@N00" primary="4558055354" secret="f70b49f720" server="4058" farm="5" photos="72">
		<title>35mm</title>
		<description>Scans of prints from 35mm film. Remember film?</description>
	    </photoset>
	</rsp>
	 */

	pi.setId(JinxUtils.getValueByXPath(doc, "/rsp/photoset/@id"));
	pi.setOwner(JinxUtils.getValueByXPath(doc, "/rsp/photoset/@owner"));
	pi.setPrimary(JinxUtils.getValueByXPath(doc, "/rsp/photoset/@primary"));
	pi.setSecret(JinxUtils.getValueByXPath(doc, "/rsp/photoset/@secret"));
	pi.setServer(JinxUtils.getValueByXPath(doc, "/rsp/photoset/@server"));
	pi.setFarm(JinxUtils.getValueByXPath(doc, "/rsp/photoset/@farm"));
	pi.setPhotos(JinxUtils.getValueByXPathAsInt(doc, "/rsp/photoset/@photos"));
	pi.setTitle(JinxUtils.getValueByXPath(doc, "/rsp/photoset/title"));
	pi.setDescription(JinxUtils.getValueByXPath(doc, "/rsp/photoset/description"));

	return pi;
    }



    /**
     * Returns the photosets belonging to the specified user.
     *
     * This method does not require authentication.
     *
     * If the nsid parameter is null or empty, the currently authenticated user's
     * nsid (as determined by Jinx.getInstance().getToken().getNsid()) will
     * be used.
     *
     * @param nsid NSID of the user to get a photoset list for.
     * @param auth determines if this call is authenticated. If you want to
     *        retrieve sets that are private/family/friends, you must authenticate.
     * @return list of photoset objects in a photosets object.
     * @throws JinxException if there are any errors.
     */
    public Photosets getList(String nsid, boolean auth) throws JinxException  {
	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.photosets.getList");
	params.put("api_key", Jinx.getInstance().getApiKey());
	if (JinxUtils.isEmpty(nsid)) {
	    nsid = Jinx.getInstance().getToken().getNsid();
	}
	params.put("user_id", nsid);
	

	Document doc = Jinx.getInstance().callFlickr(params, auth);
	Photosets p = new Photosets();

	p.setCanCreate(JinxUtils.getValueByXPathAsBoolean(doc, "/rsp/photosets/@cancreate"));
	List<Photoset> photosetList = new ArrayList<Photoset>();

	/*
	<?xml version="1.0" encoding="utf-8" ?>
	<rsp stat="ok">
	    <photosets cancreate="1">
		<photoset id="72157624018791194" primary="4558055354" secret="f70b49f720" server="4058" farm="5" photos="72" videos="0">
		    <title>35mm</title>
		    <description>Scans of prints from 35mm film. Remember film?</description>
		</photoset>
		<photoset id="72157623993195318" primary="4579566822" secret="121ee2c1ea" server="4008" farm="5" photos="5" videos="0">
		    <title>Accident: Market &amp; Montgomery</title>
		    <description>One car upside down, Market Street at Montgomery.</description>
		</photoset>
	    </photosets>
	 </rsp>
	 */
	
	NodeList photosetNodes = doc.getElementsByTagName("photoset");
	if (photosetNodes != null) {
	    for (int i = 0; i < photosetNodes.getLength(); i++) {
		Node node = photosetNodes.item(i);
		if (node != null) {
		    Photoset pset = new Photoset();
		    NamedNodeMap attrs = node.getAttributes();
		    pset.setId(JinxUtils.getAttribute(attrs, "id"));
		    pset.setPrimary(JinxUtils.getAttribute(attrs, "primary"));
		    pset.setSecret(JinxUtils.getAttribute(attrs, "secret"));
		    pset.setServer(JinxUtils.getAttribute(attrs, "server"));
		    pset.setFarm(JinxUtils.getAttribute(attrs, "farm"));
		    pset.setPhotos(JinxUtils.getAttributeAsInt(attrs, "photos"));
		    pset.setVideos(JinxUtils.getAttributeAsInt(attrs, "videos"));
		    pset.setTitle(JinxUtils.getNamedChildTextContent(node, "title"));
		    pset.setDescription(JinxUtils.getNamedChildTextContent(node, "description"));

		    // URL pattern:
		    // http://www.flickr.com/photos/{user-id}/sets/{photoset-id}
		    pset.setUrl("http://www.flickr.com/photos/" +
			    nsid + "/sets/" + pset.getId());

		    photosetList.add(pset);
		}
	    }
	}

	p.setPhotosetList(photosetList);

	return p;
    }


    /*
     * flickr.photosets.getPhotos

Get the list of photos in a set.
Authentication

This method does not require authentication.
privacy_filter (Optional)
Return photos only matching a certain privacy level. This only applies when making an authenticated call to view a photoset you own. Valid values are:
1 public photos
2 private photos visible to friends
3 private photos visible to family
4 private photos visible to friends & family
5 completely private photos
per_page (Optional)
Number of photos to return per page. If this argument is omitted, it defaults to 500. The maximum allowed value is 500.
page (Optional)
The page of results to return. If this argument is omitted, it defaults to 1.
media (Optional)
Filter results by media type. Possible values are all (default), photos or videos
     */
    /**
     * Get the list of photos in a set.
     *
     * This method does not require authentication unless you are requesting
     * photos filtered by privacy settings.
     *
     * The supported extra information is:
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
     * The available privacy filters are:
     * <ul>
     * <li>PRIVACY_PUBLIC</li>
     * <li>PRIVACY_FRIENDS</li>
     * <li>PRIVACY_FAMILY</li>
     * <li>PRIVACY_FRIENDS_FAMILY</li>
     * <li>PRIVACY_PRIVATE</li>
     * </ul>
     *
     * The available media types are:
     * <ul>
     * <li>MEDIA_ALL</li>
     * <li>MEDIA_PHOTOS</li>
     * <li>MEDIA_VIDEOS</li>
     * </ul>
     *
     * @param photosetId id of the photoset to return the photos for.
     * @param extras comma-delimited list of extra information to fetch for each
     *        returned record.
     * @param privacyFilter return photos only matching a certain privacy level.
     * @param perPage number of photos to return per page. If this argument is
     *        less than 1, it defaults to 500. The maximum allowed value is 500.
     * @param page the page of results to return. If this argument is less than
     *        1, it defaults to 1.
     * @param mediaType filter results by media type.
     * @param authenticated if true, the call will be authenticated. This is
     *        required when requesting photos filtered by privacy settings.
     * @return photos contained in the photoset.
     * @throws JinxException if there are any errors.
     */
    public Photos getPhotos(String photosetId, String extras, String privacyFilter, int perPage, int page, String mediaType, boolean authenticated) throws JinxException {
	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.photosets.getPhotos");
	params.put("api_key", Jinx.getInstance().getApiKey());
	params.put("photoset_id", photosetId);
	if (! JinxUtils.isEmpty(extras)) {
	    params.put("extras", extras);
	}
	if (! JinxUtils.isEmpty(privacyFilter)) {
	    params.put("privacy_filter", privacyFilter);
	}
	if (perPage > 0) {
	    params.put("per_page", Integer.toString(perPage));
	}
	if (page > 0) {
	    params.put("page", Integer.toString(page));
	}
	if (! JinxUtils.isEmpty(mediaType)) {
	    params.put("media", mediaType);
	}

	Document doc = Jinx.getInstance().callFlickr(params, authenticated);
	Photos photos = new Photos();

	/*
	<?xml version="1.0" encoding="utf-8" ?>
	<rsp stat="ok">
	    <photoset id="72157624018791194" primary="4558055354" owner="85853333@N00" ownername="Jeremy Brooks" page="1" per_page="500" perpage="500" pages="1" total="72">
		<photo id="4558055354" secret="f70b49f720" server="4058" farm="5"
		       title="Balmy In The Mission" isprimary="1" license="2"
		       dateupload="1272380620" datetaken="2010-04-18 13:29:28"
		       datetakengranularity="0" ownername="Jeremy Brooks" iconserver="128"
		       iconfarm="1" originalsecret="a114aec525" originalformat="jpg"
		       lastupdate="1272661485" latitude="0" longitude="0" accuracy="0"
		       tags="sanfrancisco california usa tree film 35mm palm photowalk missiondistrict themission balmy 135mmf28 sanfranciscocounty photowalking vivitar250sl calibersf041810 calibersfpw041810"
		       machine_tags="" o_width="2376" o_height="3577" views="49" media="photo"
		       media_status="ready" pathalias="jeremybrooks"
		       url_sq="http://farm5.static.flickr.com/4058/4558055354_f70b49f720_s.jpg"
		       height_sq="75" width_sq="75"
		       url_t="http://farm5.static.flickr.com/4058/4558055354_f70b49f720_t.jpg"
		       height_t="100" width_t="66"
		       url_s="http://farm5.static.flickr.com/4058/4558055354_f70b49f720_m.jpg"
		       height_s="240" width_s="159"
		       url_m="http://farm5.static.flickr.com/4058/4558055354_f70b49f720.jpg"
		       height_m="500" width_m="332"
		       url_o="http://farm5.static.flickr.com/4058/4558055354_a114aec525_o.jpg"
		       height_o="3577" width_o="2376" />
	    </photoset>
	</rsp>
	 */
	photos.setPhotosetId(JinxUtils.getValueByXPath(doc, "/rsp/photoset/@id"));
	photos.setPrimaryPhotoId(JinxUtils.getValueByXPath(doc, "/rsp/photoset/@primary"));
	photos.setOwner(JinxUtils.getValueByXPath(doc, "/rsp/photoset/@owner"));
	photos.setOwnerName(JinxUtils.getValueByXPath(doc, "/rsp/photoset/@ownername"));
	photos.setPage(JinxUtils.getValueByXPathAsInt(doc, "/rsp/photoset/@page"));
	photos.setPerPage(JinxUtils.getValueByXPathAsInt(doc, "/rsp/photoset/@perpage"));
	photos.setPages(JinxUtils.getValueByXPathAsInt(doc, "/rsp/photoset/@pages"));
	photos.setTotal(JinxUtils.getValueByXPathAsInt(doc, "/rsp/photoset/@total"));

	List<Photo> photoList = new ArrayList<Photo>();

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
	    p.setViews(JinxUtils.getAttributeAsInt(map, "views"));
	    p.setDescription(JinxUtils.getFirstChildTextContent(photoNode));

	    photoList.add(p);
	}

	photos.setPhotos(photoList);


	return photos;
    }


    /**
     * Set the order of photosets for the calling user.
     *
     * This method requires authentication with 'write' permission.
     *
     * Note: This method requires an HTTP POST request.
     *
     * The photosets will be ordered by the order of the list. Any photosets
     * that are not included in the list will display at the end of the list
     * ordered by their id's.
     *
     * @param photosetList a list of photoset Ids.
     * @throws JinxException if there are any errors.
     */
    public void orderSets(List<String> photosetList) throws JinxException {
	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.photosets.orderSets");
	params.put("api_key", Jinx.getInstance().getApiKey());

	// Make a comma separated list of photoset ids
	StringBuilder sb = new StringBuilder();
	for (String s : photosetList) {
	    sb.append(s.trim()).append(',');
	}
	sb.deleteCharAt(sb.lastIndexOf(","));

	params.put("photoset_ids", sb.toString());

	Jinx.getInstance().callFlickr(params, true, true);
    }


    
    /**
     * Remove a photo from a photoset.
     *
     * This method requires authentication with 'write' permission.
     *
     * Note: This method requires an HTTP POST request.
     *
     * @param photosetId id of the photoset to remove a photo from.
     * @param photoId id of the photo to remove from the set.
     * @throws JinxException if there are any errors.
     */
    public void removePhoto(String photosetId, String photoId) throws JinxException {
	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.photosets.removePhoto");
	params.put("api_key", Jinx.getInstance().getApiKey());
	params.put("photoset_id", photosetId);
	params.put("photo_id", photoId);

	Jinx.getInstance().callFlickr(params, true, true);
    }

}
