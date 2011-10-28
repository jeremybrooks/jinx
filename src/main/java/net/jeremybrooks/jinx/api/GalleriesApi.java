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

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import net.jeremybrooks.jinx.Jinx;
import net.jeremybrooks.jinx.JinxException;
import net.jeremybrooks.jinx.JinxUtils;
import net.jeremybrooks.jinx.dto.Galleries;
import net.jeremybrooks.jinx.dto.Gallery;
import net.jeremybrooks.jinx.dto.Photos;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 *
 * @author jeremyb
 */
public class GalleriesApi {

    private static GalleriesApi instance = null;


    private GalleriesApi() {
    }


    public static GalleriesApi getInstance() {
	if (GalleriesApi.instance == null) {
	    GalleriesApi.instance = new GalleriesApi();
	}

	return GalleriesApi.instance;
    }


    /**
     * Add a photo to a gallery.
     *
     * This method requires authentication with 'write' permission.
     *
     * Note: This method requires an HTTP POST request.
     *
     * Note: The galleryId is the compound ID returned in methods like
     * GalleriesApi.getList, and GalleriesApi.getListForPhoto.
     *
     * @param galleryId the ID of the gallery to add a photo to. Required.
     * @param photoId the id of the photo to add to the gallery. Required.
     * @param comment an optional short comment or story to accompany the photo.
     * @throws JinxException if the galleryId or photoId is missing, or if there
     *         are any errors.
     * @see http://www.flickr.com/services/api/flickr.galleries.addPhoto.html
     */
    public void addPhoto(String galleryId, String photoId, String comment) throws JinxException {
	if (galleryId == null || galleryId.trim().isEmpty()) {
	    throw new JinxException("Parameter galleryId is required.");
	}
	if (photoId == null || photoId.trim().isEmpty()) {
	    throw new JinxException("Parameter photoId is required.");
	}

	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.galleries.addPhoto");
	params.put("api_key", Jinx.getInstance().getApiKey());
	params.put("gallery_id", galleryId);
	params.put("photo_id", photoId);
	if (comment != null && !comment.trim().isEmpty()) {
	    params.put("comment", comment);
	}

	Jinx.getInstance().callFlickr(params, true, true);
    }


    /**
     * Create a new gallery for the calling user.
     *
     * This method requires authentication with 'write' permission.
     *
     * Note: This method requires an HTTP POST request.
     *
     * @param title the name of the gallery. Required.
     * @param description a short description for the gallery. Required.
     * @param primaryPhotoId the first photo to add to the new gallery. Optional.
     * @return object representing the newly created gallery.
     * @throws JinxException if the title or description are missing, or if there
     *         are any errors.
     * @see http://www.flickr.com/services/api/flickr.galleries.create.html
     */
    public Gallery create(String title, String description, String primaryPhotoId) throws JinxException {
	if (title == null || title.trim().isEmpty()) {
	    throw new JinxException("Parameter title is required.");
	}
	if (description == null || description.trim().isEmpty()) {
	    throw new JinxException("Parameter description is required.");
	}

	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.galleries.create");
	params.put("api_key", Jinx.getInstance().getApiKey());
	params.put("title", title);
	params.put("description", description);
	if (primaryPhotoId != null && !primaryPhotoId.trim().isEmpty()) {
	    params.put("primary_photo_id", primaryPhotoId);
	}

	Document doc = Jinx.getInstance().callFlickr(params, true, true);

	/*
	<?xml version="1.0" encoding="utf-8" ?>
	<rsp stat="ok">
	<gallery id="4956757-72157625433304825"
	url="/photos/jeremybrooks/galleries/72157625433304825"
	primary="5241624133" secret="cb3f3b7deb" server="5164" farm="6" />
	</rsp>
	 */

	Gallery g = new Gallery();
	g.setId(JinxUtils.getValueByXPath(doc, "/rsp/gallery/@id"));
	g.setUrl(JinxUtils.getValueByXPath(doc, "/rsp/gallery/@url"));
	g.setPrimaryPhotoId(JinxUtils.getValueByXPath(doc, "/rsp/gallery/@primary"));
	g.setPrimaryPhotoSecret(JinxUtils.getValueByXPath(doc, "/rsp/gallery/@secret"));
	g.setPrimaryPhotoServer(JinxUtils.getValueByXPath(doc, "/rsp/gallery/@server"));
	g.setPrimaryPhotoFarm(JinxUtils.getValueByXPath(doc, "/rsp/gallery/@farm"));

	return g;
    }


    /**
     * Modify the meta-data for a gallery.
     *
     * This method requires authentication with 'write' permission.
     *
     * Note: This method requires an HTTP POST request.
     *
     * @param galleryId the gallery ID to update. Required.
     * @param title the new title for the gallery. Required.
     * @param description the new description for the gallery. Optional.
     * @throws JinxException if the galleryId or title are missing, or if there
     *         are any errors.
     * @see http://www.flickr.com/services/api/flickr.galleries.editMeta.html
     */
    public void editMeta(String galleryId, String title, String description) throws JinxException {
	if (title == null || title.trim().isEmpty()) {
	    throw new JinxException("Parameter title is required.");
	}
	if (galleryId == null || galleryId.trim().isEmpty()) {
	    throw new JinxException("Parameter galleryId is required.");
	}

	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.galleries.editMeta");
	params.put("api_key", Jinx.getInstance().getApiKey());
	params.put("gallery_id", galleryId);
	params.put("title", title);
	if (description != null && !description.trim().isEmpty()) {
	    params.put("description", description);
	}

	Jinx.getInstance().callFlickr(params, true, true);
    }


    /**
     * Edit the comment for a gallery photo.
     *
     * This method requires authentication with 'write' permission.
     *
     * Note: This method requires an HTTP POST request.
     *
     * @param galleryId the id of the gallery to edit the photo in.
     * @param photoId the id of the photo to edit.
     * @param comment the updated comment for the photo.
     * @throws JinxException if any parameters are missing, or if there are any
     *         errors.
     * @see http://www.flickr.com/services/api/flickr.galleries.editPhoto.html
     */
    public void editPhoto(String galleryId, String photoId, String comment) throws JinxException {
	if (comment == null || comment.trim().isEmpty()) {
	    throw new JinxException("Parameter comment is required.");
	}
	if (galleryId == null || galleryId.trim().isEmpty()) {
	    throw new JinxException("Parameter galleryId is required.");
	}
	if (photoId == null || photoId.trim().isEmpty()) {
	    throw new JinxException("Parameter photoId is required.");
	}

	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.galleries.editPhoto");
	params.put("api_key", Jinx.getInstance().getApiKey());
	params.put("gallery_id", galleryId);
	params.put("photo_id", photoId);
	params.put("comment", comment);

	Jinx.getInstance().callFlickr(params, true, true);
    }


    /**
     * Modify the photos in a gallery. Use this method to add, remove and re-order photos.
     *
     * This method requires authentication with 'write' permission.
     *
     * Note: This method requires an HTTP POST request.
     *
     * The primary photo id must be in the list of photo id's. The photos in the
     * gallery will appear in the order that they are in the list. The list of
     * photos replaces the existing list.
     *
     * @param galleryId the id of the gallery to modify.
     * @param primaryPhotoId the id of the photo to use as the primary photo for the gallery.
     * @param photoIds list of photo Id's to include in the gallery.
     * @throws JinxException if any parameters are missing, or if there are any errors.
     * @see http://www.flickr.com/services/api/flickr.galleries.editPhotos.html
     */
    public void editPhotos(String galleryId, String primaryPhotoId, List<String> photoIds) throws JinxException {
	if (primaryPhotoId == null || primaryPhotoId.trim().isEmpty()) {
	    throw new JinxException("Parameter primaryPhotoId is required.");
	}
	if (galleryId == null || galleryId.trim().isEmpty()) {
	    throw new JinxException("Parameter galleryId is required.");
	}
	if (photoIds == null || photoIds.isEmpty()) {
	    throw new JinxException("Parameter photoIds is required.");
	}

	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.galleries.editPhotos");
	params.put("api_key", Jinx.getInstance().getApiKey());
	params.put("gallery_id", galleryId);
	params.put("primary_photo_id", primaryPhotoId);
	StringBuilder sb = new StringBuilder();
	for (String s : photoIds) {
	    if (s != null && s.trim().length() > 0) {
		sb.append(s.trim()).append(',');
	    }
	}
	sb.deleteCharAt(sb.lastIndexOf(","));

	params.put("photo_ids", sb.toString());


	Jinx.getInstance().callFlickr(params, true, true);
    }

    
    /**
     * Get gallery information.
     *
     * This method does not require authentication.
     *
     * @param galleryId the gallery Id you are requesting information for. Required.
     * @return gallery information.
     * @throws JinxException if the gallery Id is missing, or if there are any errors.
     * @see http://www.flickr.com/services/api/flickr.galleries.getInfo.html
     */
    public Gallery getInfo(String galleryId) throws JinxException {
	if (galleryId == null || galleryId.trim().isEmpty()) {
	    throw new JinxException("Parameter galleryId is required.");
	}

	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.galleries.getInfo");
	params.put("api_key", Jinx.getInstance().getApiKey());
	params.put("gallery_id", galleryId);

	Document doc = Jinx.getInstance().callFlickr(params, false);

	/*
	<?xml version="1.0" encoding="utf-8" ?>
	<rsp stat="ok">
	    <gallery id="6065-72157617483228192"
		url="http://www.flickr.com/photos/straup/galleries/72157617483228192"
		owner="35034348999@N01" primary_photo_id="292882708" date_create="1241028772"
		date_update="1288288281" count_photos="18" count_videos="0" primary_photo_server="112"
		primary_photo_farm="1" primary_photo_secret="7f29861bc4">
		<title>Cat Pictures I've Sent To Kevin Collins</title>
		<description />
	    </gallery>
	</rsp>
	 */

	NodeList nodes = doc.getElementsByTagName("gallery");
	return parseGalleryNode(nodes.item(0));
    }


    /**
     * Return the list of galleries created by a user, sorted from newest to oldest.
     *
     * This method does not require authentication.
     *
     * @param userId NSID of the user to get a galleries list for. Required.
     * @param perPage number of galleries to return per page. If this argument is
     *        zero, it defaults to 100. If this argument is > 500, it defaults to
     *        500.
     * @param page the page of results to return. If this argument is zero, it
     *        defaults to 1.
     * @return object representing the galleries for the user.
     * @throws JinxException if there are any errors.
     * @see http://www.flickr.com/services/api/flickr.galleries.getList.html
     */
    public Galleries getList(String userId, int perPage, int page) throws JinxException {
	if (userId == null || userId.trim().isEmpty()) {
	    throw new JinxException("Parameter userId is required.");
	}

	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.galleries.getList");
	params.put("api_key", Jinx.getInstance().getApiKey());
        params.put("user_id", userId);
	if (perPage > 0) {
	    if (perPage > 500) {
		perPage = 500;
	    }
	    params.put("per_page", Integer.toString(perPage));
	}
	if (page > 0) {
	    params.put("page", Integer.toString(page));
	}

	Document doc = Jinx.getInstance().callFlickr(params, false);

	/*
	<?xml version="1.0" encoding="utf-8" ?>
	<rsp stat="ok">
	    <galleries total="6" page="1" pages="1" per_page="100" user_id="85853333@N00">
		<gallery id="4956757-72157625363950230"
		    url="http://www.flickr.com/photos/jeremybrooks/galleries/72157625363950230"
		    owner="85853333@N00" primary_photo_id="3928411827" date_create="1289512766"
		    date_update="1289513364" count_photos="12" count_videos="0"
		    primary_photo_server="3003" primary_photo_farm="4"
		    primary_photo_secret="6f707a6e6c">
		    <title>leesure</title>
		    <description>Some of my favorite photos from leesure's photostream.</description>
		</gallery>
	    </galleries>
	 </rsp>
	 */

	Galleries galleries = new Galleries();
	galleries.setTotal(JinxUtils.getValueByXPathAsInt(doc, "/rsp/galleries/@total"));
	galleries.setPage(JinxUtils.getValueByXPathAsInt(doc, "/rsp/galleries/@page"));
	galleries.setPages(JinxUtils.getValueByXPathAsInt(doc, "/rsp/galleries/@pages"));
	galleries.setPerPage(JinxUtils.getValueByXPathAsInt(doc, "/rsp/galleries/@per_page"));
	galleries.setUserId(JinxUtils.getValueByXPath(doc, "/rsp/galleries/@user_id"));

	NodeList gNodes = doc.getElementsByTagName("gallery");
	for (int i = 0; i < gNodes.getLength(); i++) {
	    galleries.addGallery(parseGalleryNode(gNodes.item(i)));
	}

	return galleries;
    }


    /**
     * Return the list of galleries to which a photo has been added.
     * Galleries are returned sorted by date which the photo was added to the gallery.
     *
     * This method does not require authentication.
     *
     * @param photoId the ID of the photo to fetch a list of galleries for. Required.
     * @param perPage number of galleries to return per page. If this argument is
     *        zero, it defaults to 100. If this argument is > 500, it defaults to
     *        500.
     * @param page the page of results to return. If this argument is zero, it
     *        defaults to 1.
     * @return object representing the galleries for the user.
     * @throws JinxException if there are any errors.
     * @see http://www.flickr.com/services/api/flickr.galleries.getListForPhoto.html
     */
    public Galleries getListForPhoto(String photoId, int perPage, int page) throws JinxException {
	if (photoId == null || photoId.trim().isEmpty()) {
	    throw new JinxException("Parameter photoId is required.");
	}

	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.galleries.getListForPhoto");
	params.put("api_key", Jinx.getInstance().getApiKey());
	params.put("photo_id", photoId);
	if (perPage > 0) {
	    if (perPage > 500) {
		perPage = 500;
	    }
	    params.put("per_page", Integer.toString(perPage));
	}
	if (page > 0) {
	    params.put("page", Integer.toString(page));
	}

	Document doc = Jinx.getInstance().callFlickr(params, false);

	/*
	<?xml version="1.0" encoding="utf-8" ?>
	<rsp stat="ok">
	    <galleries total="6" page="1" pages="1" per_page="100" user_id="85853333@N00">
		<gallery id="4956757-72157625363950230"
		    url="http://www.flickr.com/photos/jeremybrooks/galleries/72157625363950230"
		    owner="85853333@N00" primary_photo_id="3928411827" date_create="1289512766"
		    date_update="1289513364" count_photos="12" count_videos="0"
		    primary_photo_server="3003" primary_photo_farm="4"
		    primary_photo_secret="6f707a6e6c">
		    <title>leesure</title>
		    <description>Some of my favorite photos from leesure's photostream.</description>
		</gallery>
	    </galleries>
	 </rsp>
	 */

	Galleries galleries = new Galleries();
	galleries.setTotal(JinxUtils.getValueByXPathAsInt(doc, "/rsp/galleries/@total"));
	galleries.setPage(JinxUtils.getValueByXPathAsInt(doc, "/rsp/galleries/@page"));
	galleries.setPages(JinxUtils.getValueByXPathAsInt(doc, "/rsp/galleries/@pages"));
	galleries.setPerPage(JinxUtils.getValueByXPathAsInt(doc, "/rsp/galleries/@per_page"));
	galleries.setUserId(JinxUtils.getValueByXPath(doc, "/rsp/galleries/@user_id"));

	NodeList gNodes = doc.getElementsByTagName("gallery");
	for (int i = 0; i < gNodes.getLength(); i++) {
	    galleries.addGallery(parseGalleryNode(gNodes.item(i)));
	}

	return galleries;
    }


    /**
     * Return the list of photos for a gallery.
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
     * EXTRAX_URL_SQ
     * EXTRAS_URL_T
     * EXTRAS_URL_S
     * EXTRAS_URL_M
     * EXTRAS_URL_Z
     * EXTRAS_URL_L
     * EXTRAS_URL_O
     *
     * @param galleryId the ID of the gallery of photos to return. Required.
     * @param extras list of JinxConstants.EXTRAS* indicating the extras to
     *        fetch for each returned photo.
     * @param perPage number of galleries to return per page. If this argument is
     *        zero, it defaults to 100. If this argument is > 500, it defaults to
     *        500.
     * @param page the page of results to return. If this argument is zero, it
     *        defaults to 1.
     * @return object representing the photos in the gallery.
     * @throws JinxException if the gallery id is missing, or if there are any errors.
     * @see http://www.flickr.com/services/api/flickr.galleries.getPhotos.html
     */
    public Photos getPhotos(String galleryId, List<String>extras, int perPage, int page) throws JinxException {
	if (galleryId == null || galleryId.trim().isEmpty()) {
	    throw new JinxException("Parameter galleryId is required.");
	}

	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.galleries.getPhotos");
	params.put("api_key", Jinx.getInstance().getApiKey());
	params.put("gallery_id", galleryId);
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
	if (perPage > 0) {
	    if (perPage > 500) {
		perPage = 500;
	    }
	    params.put("per_page", Integer.toString(perPage));
	}
	if (page > 0) {
	    params.put("page", Integer.toString(page));
	}

	Document doc = Jinx.getInstance().callFlickr(params, false);

	return PhotosApi.getInstance().parsePhotosXml(doc);
    }


    /*
     * Parse a gallery XML node into a Gallery object.
     *
     * Gallery nodes look like this:
       <gallery id="6065-72157617483228192"
	  url="http://www.flickr.com/photos/straup/galleries/72157617483228192"
	  owner="35034348999@N01" primary_photo_id="292882708" date_create="1241028772"
	  date_update="1288288281" count_photos="18" count_videos="0" primary_photo_server="112"
	  primary_photo_farm="1" primary_photo_secret="7f29861bc4">
	  <title>Cat Pictures I've Sent To Kevin Collins</title>
	  <description />
	</gallery>
     */
    private Gallery parseGalleryNode(Node node) throws JinxException {
	Gallery gallery = new Gallery();

	NamedNodeMap attrs = node.getAttributes();
	gallery.setId(JinxUtils.getAttribute(attrs, "id"));
	gallery.setUrl(JinxUtils.getAttribute(attrs, "url"));
	gallery.setOwner(JinxUtils.getAttribute(attrs, "owner"));
	gallery.setPrimaryPhotoId(JinxUtils.getAttribute(attrs, "primary_photo_id"));
	gallery.setDateCreate(JinxUtils.parseTimestampToDate(JinxUtils.getAttribute(attrs, "date_create")));
	gallery.setDateUpdate(JinxUtils.parseTimestampToDate(JinxUtils.getAttribute(attrs, "date_update")));
	gallery.setCountPhotos(JinxUtils.getAttributeAsInt(attrs, "count_photos"));
	gallery.setCountVideos(JinxUtils.getAttributeAsInt(attrs, "count_videos"));
	gallery.setPrimaryPhotoServer(JinxUtils.getAttribute(attrs, "primary_photo_server"));
	gallery.setPrimaryPhotoFarm(JinxUtils.getAttribute(attrs, "primary_photo_farm"));
	gallery.setPrimaryPhotoSecret(JinxUtils.getAttribute(attrs, "primary_photo_secret"));
	gallery.setTitle(JinxUtils.getNamedChildTextContent(node, "title"));
	gallery.setDescription(JinxUtils.getNamedChildTextContent(node, "description"));

	return gallery;
    }
}
