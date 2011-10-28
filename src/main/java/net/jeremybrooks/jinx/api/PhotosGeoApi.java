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
import net.jeremybrooks.jinx.JinxConstants;
import net.jeremybrooks.jinx.JinxException;
import net.jeremybrooks.jinx.JinxUtils;
import net.jeremybrooks.jinx.dto.Location;
import net.jeremybrooks.jinx.dto.Perms;
import net.jeremybrooks.jinx.dto.Photos;
import org.w3c.dom.Document;

/**
 *
 * @author jeremyb
 */
public class PhotosGeoApi {

    private static PhotosGeoApi instance = null;
    
    private PhotosGeoApi() {}
    
    public static PhotosGeoApi getInstance() {
	if (PhotosGeoApi.instance == null) {
	    PhotosGeoApi.instance = new PhotosGeoApi();
	}
	
	return PhotosGeoApi.instance;
    }
    
    

    /**
     * Correct the places hierarchy for all the photos for a user at a given
     * latitude, longitude and accuracy.
     *
     * You must pass either a placeId or a woeId.
     *
     * Batch corrections are processed in a delayed queue so it may take a few
     * minutes before the changes are reflected in a user's photos.
     *
     * This method requires authentication with 'write' permission.
     *
     * Note: This method requires an HTTP POST request.
     *
     * @param lat latitude of the photos to be update whose valid range
     *        is -90 to 90. Anything more than 6 decimal places will be truncated.
     * @param lon longitude of the photos to be updated whose valid range
     *        is -180 to 180. Anything more than 6 decimal places will be truncated.
     * @param accuracy accuracy level of the photos to be updated. World
     *        level is 1, Country is ~3, Region ~6, City ~11, Street ~16.
     *        Current range is 1-16.
     * @param placeId a Flickr places ID.
     * @param woeId a Flickr Where On Earth (WOE) ID.
     * @throws JinxException if a required parameter is missing, invalid, or
     *         if there are any errors.
     */
    public void batchCorrectLocation(float lat, float lon, int accuracy, String placeId, String woeId)
	    throws JinxException {
	if (lat < -90 || lat > 90) {
	    throw new JinxException("Invalid lat: Must be between -90 and 90.");
	}
	if (lon < -180 || lat > 180) {
	    throw new JinxException("Invalid lon: Must be between -180 and 180.");
	}
	if (accuracy < 1 || accuracy > 16) {
	    throw new JinxException("Invalid accuracy: Must be between 1 and 16.");
	}
	if ((placeId == null || placeId.trim().isEmpty()) &&
	    (woeId == null || woeId.trim().isEmpty())) {
	    throw new JinxException("Either a placeId or a woeId is required.");
	}

	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.photos.geo.batchCorrectLocation");
	params.put("api_key", Jinx.getInstance().getApiKey());
	params.put("lat", Float.toString(lat));
	params.put("lon", Float.toString(lon));
	params.put("accuracy", Integer.toString(accuracy));
	if (placeId != null && placeId.trim().length() > 0) {
	    params.put("place_id", placeId);
	}
	if (woeId != null && woeId.trim().length() > 0) {
	    params.put("woe_id", woeId);
	}

	Jinx.getInstance().callFlickr(params, true, true);
    }


    /**
     * You must pass either a placeId or a woeId.
     *
     * This method requires authentication with 'write' permission.
     *
     * Note: This method requires an HTTP POST request.
     *
     * @param photoId id of the photos whose location is being corrected.
     * @param placeId Flickr Place ID.
     * @param woeId Flickr Where On Earth (WOE) ID.
     * @throws JinxException if a required parameter is missing, or if there are
     *         any errors.
     */
    public void correctLocation(String photoId, String placeId, String woeId) throws JinxException {
	if (photoId == null || photoId.trim().isEmpty()) {
	    throw new JinxException("Photo ID is required.");
	}
	if ((placeId == null || placeId.trim().isEmpty()) &&
	    (woeId == null || woeId.trim().isEmpty())) {
	    throw new JinxException("Either a placeId or a woeId is required.");
	}

	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.photos.geo.correctLocation");
	params.put("api_key", Jinx.getInstance().getApiKey());
	params.put("photo_id", photoId);
	if (placeId != null && placeId.trim().length() > 0) {
	    params.put("place_id", placeId);
	}
	if (woeId != null && woeId.trim().length() > 0) {
	    params.put("woe_id", woeId);
	}

	Jinx.getInstance().callFlickr(params, true, true);
    }


    /**
     * Get the geo data (latitude and longitude and the accuracy level) for a photo.
     *
     * This method does not require authentication.
     *
     * @param photoId the photo to fetch geo location data for.
     * @return location information about the photo.
     * @throws JinxException if photoId is null or empty, or if there are any errors.
     */
    public Location getLocation(String photoId) throws JinxException {
	if (photoId == null || photoId.trim().isEmpty()) {
	    throw new JinxException("Photo ID is required.");
	}

	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.photos.geo.getLocation");
	params.put("api_key", Jinx.getInstance().getApiKey());
	params.put("photo_id", photoId);

	Document doc = Jinx.getInstance().callFlickr(params);

	/*
	 <?xml version="1.0" encoding="utf-8" ?>
	 <rsp stat="ok">
	   <photo id="2333837307">
	     <location latitude="37.472833" longitude="-122.217" accuracy="16"
	 context="0" place_id="LrVuUf9TVr36PcaA" woeid="2479714">
               <locality place_id="LrVuUf9TVr36PcaA" woeid="2479714">Redwood City</locality>
               <county place_id="nJQqFhtQUL9qTY2Saw" woeid="12587710">San Mateo</county>
               <region place_id="NsbUWfBTUb4mbyVu" woeid="2347563">California</region>
               <country place_id="nz.gsghTUb4c2WAecA" woeid="23424977">United States</country>
             </location>
           </photo>
         </rsp>
	 */

	Location location = new Location();

	location.setPhotoId(JinxUtils.getValueByXPath(doc, "/rsp/photo/@id"));
	location.setLatitude(JinxUtils.getValueByXPathAsLong(doc, "/rsp/photo/location/@latitude"));
	location.setLongitude(JinxUtils.getValueByXPathAsLong(doc, "/rsp/photo/location/@longitude"));
	location.setAccuracy(JinxUtils.getValueByXPathAsInt(doc, "/rsp/photo/location/@accuracy"));
	location.setContext(JinxUtils.getValueByXPathAsInt(doc, "/rsp/photo/location/@context"));
	location.setPlaceId(JinxUtils.getValueByXPath(doc, "/rsp/photo/location/@place_id"));
	location.setWoeId(JinxUtils.getValueByXPath(doc, "/rsp/photo/location/@woeid"));

	location.setLocalityPlaceId(JinxUtils.getValueByXPath(doc, "/rsp/photo/location/locality/@place_id"));
	location.setLocalityWoeId(JinxUtils.getValueByXPath(doc, "/rsp/photo/location/locality/@woeid"));
	location.setLocality(JinxUtils.getValueByXPath(doc, "/rsp/photo/location/locality"));

	location.setCountyPlaceId(JinxUtils.getValueByXPath(doc, "/rsp/photo/location/county/@place_id"));
	location.setCountyWoeId(JinxUtils.getValueByXPath(doc, "/rsp/photo/location/county/@woeid"));
	location.setCounty(JinxUtils.getValueByXPath(doc, "/rsp/photo/location/county"));

	location.setRegionPlaceId(JinxUtils.getValueByXPath(doc, "/rsp/photo/location/region/@place_id"));
	location.setRegionWoeId(JinxUtils.getValueByXPath(doc, "/rsp/photo/location/region/@woeid"));
	location.setRegion(JinxUtils.getValueByXPath(doc, "/rsp/photo/location/region"));

	location.setCountryPlaceId(JinxUtils.getValueByXPath(doc, "/rsp/photo/location/country/@place_id"));
	location.setCountryWoeId(JinxUtils.getValueByXPath(doc, "/rsp/photo/location/country/@woeid"));
	location.setCountry(JinxUtils.getValueByXPath(doc, "/rsp/photo/location/country"));

	return location;
    }


    /**
     * Get permissions for who may view geo data for a photo.
     *
     * This method requires authentication with 'read' permission.
     *
     * @param photoId id of the photo to get permissions for.
     * @return permissions for geo data for the photo.
     * @throws JinxException if photoId is missing or invalid, or if there are any errors.
     */
    public Perms getPerms(String photoId) throws JinxException {
	if (photoId == null || photoId.trim().isEmpty()) {
	    throw new JinxException("Photo ID is required.");
	}

	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.photos.geo.getPerms");
	params.put("api_key", Jinx.getInstance().getApiKey());
	params.put("photo_id", photoId);

	Document doc = Jinx.getInstance().callFlickr(params);

	/*
	 <?xml version="1.0" encoding="utf-8" ?>
	 <rsp stat="ok">
	   <perms id="2333837307" ispublic="1" iscontact="0" isfriend="0" isfamily="0" />
	 </rsp>
	 */

	Perms perms = new Perms();
	perms.setId(JinxUtils.getValueByXPath(doc, "/rsp/perms/@id"));
	perms.setIsPublic(JinxUtils.getValueByXPathAsBoolean(doc, "/rsp/perms/@ispublic"));
	perms.setIsContact(JinxUtils.getValueByXPathAsBoolean(doc, "/rsp/perms/@iscontact"));
	perms.setIsFriend(JinxUtils.getValueByXPathAsBoolean(doc, "/rsp/perms/@isfriend"));
	perms.setIsFamily(JinxUtils.getValueByXPathAsBoolean(doc, "/rsp/perms/@isfamily"));

	return perms;
    }


    /**
     * Return a list of photos for the calling user at a specific latitude and
     * longitude.
     *
     * This is equivalen to <code>getPhotosForLocation(lat, lon, 16, null, 0, 0)</code>.
     *
     * @param lat latitude whose valid range is -90 to 90. Anything more than 6
     *        decimal places will be truncated.
     * @param lon longitude whose valid range is -180 to 180. Anything more
     *        than 6 decimal places will be truncated.
     * @return photos for the location.
     * @throws JinxException if latitude or longitude are invalid, or if there
     *         are any errors.
     */
    public Photos getPhotosForLocation(float lat, float lon) throws JinxException {
	return this.getPhotosForLocation(lat, lon, 16, null, 0, 0);
    }


    /**
     * Return a list of photos for the calling user at a specific latitude,
     * longitude and accuracy.
     *
     * This method requires authentication with 'read' permission.
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
     * @param lat latitude whose valid range is -90 to 90. Anything more than 6
     *        decimal places will be truncated.
     * @param lon longitude whose valid range is -180 to 180. Anything more
     *        than 6 decimal places will be truncated.
     * @param accuracy recorded accuracy level of the location information.
     *        World level is 1, Country is ~3, Region ~6, City ~11, Street ~16.
     *        Current range is 1-16. Defaults to 16 if not specified.
     * @param extras a list of extra information to return for each photo.
     * @param page page of results to return. If this argument is less than 1,
     *        it defaults to 1.
     * @param perPage number of photos to return per page. If this argument is
     *        less than 1, it defaults to 100. The maximum allowed value is 500.
     * @return photos for the specified location.
     * @throws JinxException if parameters are invalid, or if there are any errors.
     */
    public Photos getPhotosForLocation(float lat, float lon, int accuracy, List<String> extras,
	    int page, int perPage) throws JinxException {
	if (lat < -90 || lat > 90) {
	    throw new JinxException("Invalid lat: Must be between -90 and 90.");
	}
	if (lon < -180 || lat > 180) {
	    throw new JinxException("Invalid lon: Must be between -180 and 180.");
	}
	if (accuracy < 1 || accuracy > 16) {
	    throw new JinxException("Invalid accuracy: Must be between 1 and 16.");
	}

	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.photos.geo.photosForLocation");
	params.put("api_key", Jinx.getInstance().getApiKey());
	params.put("lat", Float.toString(lat));
	params.put("lon", Float.toString(lon));
	params.put("accuracy", Integer.toString(accuracy));
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

	Document doc = Jinx.getInstance().callFlickr(params);

	return PhotosApi.getInstance().parsePhotosXml(doc);
    }

    
    /**
     * Removes the geo data associated with a photo.
     *
     * This method requires authentication with 'write' permission.
     * Note: This method requires an HTTP POST request.
     *
     * @param photoId id of the photo you want to remove location data from.
     * @throws JinxException if the photoId is missing or empty, or if there
     *         are any errors.
     */
    public void removeLocation(String photoId) throws JinxException {
	if (photoId == null || photoId.trim().isEmpty()) {
	    throw new JinxException("Photo ID is required.");
	}

	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.photos.geo.removeLocation");
	params.put("api_key", Jinx.getInstance().getApiKey());
	params.put("photo_id", photoId);

	Jinx.getInstance().callFlickr(params, true, true);
    }

    
    /**
     * Indicate the state of a photo's geotagginess beyond latitude and longitude.
     *
     * Note: Photos passed to this method must already be geotagged
     * (using the PhotosGeoApi.setLocation method).
     *
     * This method requires authentication with 'write' permission.
     *
     * Note: This method requires an HTTP POST request.
     *
     * @param photoId id of the photo to set context data for.
     * @param context one of JinxConstants.GEO_CONTEXT_NOT_DEFINED, JinxConstants.GEO_CONTEXT_INDOORS,
     *        or JinxConstants.GEO_CONTEXT_INDOORS.
     * @throws JinxException
     */
    public void setContext(String photoId, String context) throws JinxException {
	if (photoId == null || photoId.trim().isEmpty()) {
	    throw new JinxException("Photo ID is required.");
	}
	if (context == null || context.trim().isEmpty()) {
	    throw new JinxException("Context is required.");
	}
	if (!context.equals(JinxConstants.GEO_CONTEXT_INDOORS) &&
	    !context.equals(JinxConstants.GEO_CONTEXT_NOT_DEFINED) &&
	    !context.equals(JinxConstants.GEO_CONTEXT_OUTDOORS)) {
	    throw new JinxException("Invalid context.");
	}

	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.photos.geo.setContext");
	params.put("api_key", Jinx.getInstance().getApiKey());
	params.put("photo_id", photoId);
	params.put("context", context);

	Jinx.getInstance().callFlickr(params, true, true);
    }

    
    /**
     * Sets the geo data (latitude and longitude and, optionally, the accuracy level)
     * for a photo. Before users may assign location data to a photo they must
     * define who, by default, may view that information. Users can edit this
     * preference at http://www.flickr.com/account/geo/privacy/. If a user has not
     * set this preference, the API method will return an error.
     *
     * This method requires authentication with 'write' permission.
     *
     * Note: This method requires an HTTP POST request.
     *
     * @param photoId id of the photo to set location data for.
     * @param lat latitude whose valid range is -90 to 90. Anything more than
     *        6 decimal places will be truncated.
     * @param lon longitude whose valid range is -180 to 180. Anything more than
     *        6 decimal places will be truncated.
     * @param accuracy recorded accuracy level of the location information.
     *        World level is 1, Country is ~3, Region ~6, City ~11, Street ~16.
     *        Current range is 1-16. Defaults to 16 if out of this range.
     * @param context one of JinxConstants.GEO_CONTEXT_NOT_DEFINED, JinxConstants.GEO_CONTEXT_INDOORS,
     *        or JinxConstants.GEO_CONTEXT_INDOORS. If this parameter is null,
     *        it will default to GEO_CONTEXT_NOT_DEFINED.
     * @throws JinxException if parameters are missing or invalid, or if there
     *         are any errors.
     */
    public void setLocation(String photoId, float lat, float lon, int accuracy, String context)
	    throws JinxException {
	if (photoId == null || photoId.trim().isEmpty()) {
	    throw new JinxException("Photo ID is required.");
	}
	if (lat < -90 || lat > 90) {
	    throw new JinxException("Invalid lat: Must be between -90 and 90.");
	}
	if (lon < -180 || lat > 180) {
	    throw new JinxException("Invalid lon: Must be between -180 and 180.");
	}
	if (accuracy < 1 || accuracy > 16) {
	    accuracy = 16;
	}
	if (context != null &&
	    !context.equals(JinxConstants.GEO_CONTEXT_INDOORS) &&
	    !context.equals(JinxConstants.GEO_CONTEXT_NOT_DEFINED) &&
	    !context.equals(JinxConstants.GEO_CONTEXT_OUTDOORS)) {
	    context = JinxConstants.GEO_CONTEXT_NOT_DEFINED;
	}

	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.photos.geo.setLocation");
	params.put("api_key", Jinx.getInstance().getApiKey());
	params.put("photo_id", photoId);
	params.put("lat", Float.toString(lat));
	params.put("lon", Float.toString(lon));
	params.put("accuracy", Integer.toString(accuracy));
	if (context != null) {
	    params.put("context", context);
	}

	Jinx.getInstance().callFlickr(params, true, true);
    }

    
    /**
     * Set the permission for who may view the geo data associated with a photo.
     *
     * This method requires authentication with 'write' permission.
     *
     * Note: This method requires an HTTP POST request.
     *
     * @param photoId id of the photo to set permissions for.
     * @param isPublic viewing permissions for the photo's location data to public.
     * @param isContact viewing permissions for the photo's location data to contacts.
     * @param isFriend viewing permissions for the photo's location data to friends.
     * @param isFamily viewing permissions for the photo's location data to family.
     * @throws JinxException i the photoId is null or missing, or if there are any errors.
     */
    public void setPerms(String photoId, boolean isPublic, boolean isContact, boolean isFriend,
	    boolean isFamily) throws JinxException {
	if (photoId == null || photoId.trim().isEmpty()) {
	    throw new JinxException("Photo ID is required.");
	}
	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.photos.geo.setPerms");
	params.put("api_key", Jinx.getInstance().getApiKey());
	params.put("photo_id", photoId);
	params.put("is_public", JinxUtils.booleanToString(isPublic));
	params.put("is_contact", JinxUtils.booleanToString(isContact));
	params.put("is_friend", JinxUtils.booleanToString(isFriend));
	params.put("is_family", JinxUtils.booleanToString(isFamily));

	Jinx.getInstance().callFlickr(params, true, true);
    }
}