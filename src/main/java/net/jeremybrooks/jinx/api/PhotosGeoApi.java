/*
 * Jinx is Copyright 2010-2017 by Jeremy Brooks and Contributors
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
import net.jeremybrooks.jinx.response.Response;
import net.jeremybrooks.jinx.response.photos.Photos;
import net.jeremybrooks.jinx.response.photos.geo.GeoPerms;
import net.jeremybrooks.jinx.response.photos.geo.Location;

import java.util.EnumSet;
import java.util.Map;
import java.util.TreeMap;

/**
 * Provides access to the flickr.photos.geo API methods.
 *
 * @author Jeremy Brooks
 * @see <a href="https://www.flickr.com/services/api/">Flickr API documentation</a> for more details.
 */
public class PhotosGeoApi {
  private Jinx jinx;

  public PhotosGeoApi(Jinx jinx) {
    this.jinx = jinx;
  }

  /**
   * Correct the places hierarchy for all the photos for a user at a given latitude, longitude and accuracy.
   * <br>
   * Batch corrections are processed in a delayed queue so it may take a few minutes before the changes are reflected in a user's photos.
   * <br>
   * This method requires authentication with 'write' permission.
   * <br>
   * Note: This method requires an HTTP POST request.
   *
   * @param lat      (Required) The latitude of the photos to be update whose valid range is -90 to 90. Anything more than 6 decimal places will be truncated.
   * @param lon      (Required) The longitude of the photos to be updated whose valid range is -180 to 180. Anything more than 6 decimal places will be truncated.
   * @param accuracy (Required) Recorded accuracy level of the photos to be updated. World level is 1, Country is ~3, Region ~6, City ~11, Street ~16. Current range is 1-16.
   * @param placeId  A Flickr Places ID. (While optional, you must pass either a valid Places ID or a WOE ID.)
   * @param woeId    A Where On Earth (WOE) ID. (While optional, you must pass either a valid Places ID or a WOE ID.)
   * @return object with the status of the requested operation.
   * @throws JinxException if required parameters are missing, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.photos.geo.batchCorrectLocation.html">flickr.photos.geo.batchCorrectLocation</a>
   */
  public Response batchCorrectLocation(Float lat, Float lon, Integer accuracy, String placeId, String woeId) throws JinxException {
    JinxUtils.validateParams(lat, lon, accuracy);
    if (JinxUtils.isNullOrEmpty(placeId)) {
      JinxUtils.validateParams(woeId);
    }
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.photos.geo.batchCorrectLocation");
    params.put("lat", lat.toString());
    params.put("lon", lon.toString());
    params.put("accuracy", accuracy.toString());
    if (!JinxUtils.isNullOrEmpty(placeId)) {
      params.put("place_id", placeId);
    }
    if (!JinxUtils.isNullOrEmpty(woeId)) {
      params.put("woe_id", woeId);
    }
    return jinx.flickrPost(params, Response.class);
  }

  /**
   * This method requires authentication with 'write' permission.
   *
   * @param photoId      (Required) The ID of the photo whose WOE location is being corrected.
   * @param placeId      A Flickr Places ID. (While optional, you must pass either a valid Places ID or a WOE ID.)
   * @param woeId        A Where On Earth (WOE) ID. (While optional, you must pass either a valid Places ID or a WOE ID.)
   * @param foursquareId The venue ID for a Foursquare location. (If not passed in with correction, any existing foursquare venue will be removed).
   * @return object with the status of the requested operation.
   * @throws JinxException if required parameters are missing, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.photos.geo.correctLocation.html">flickr.photos.geo.correctLocation</a>
   */
  public Response correctLocation(String photoId, String placeId, String woeId, String foursquareId) throws JinxException {
    JinxUtils.validateParams(photoId);
    if (JinxUtils.isNullOrEmpty(placeId)) {
      JinxUtils.validateParams(woeId);
    }
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.photos.geo.correctLocation");
    params.put("photo_id", photoId);
    if (!JinxUtils.isNullOrEmpty(placeId)) {
      params.put("place_id", placeId);
    }
    if (!JinxUtils.isNullOrEmpty(woeId)) {
      params.put("woe_id", woeId);
    }
    if (!JinxUtils.isNullOrEmpty(foursquareId)) {
      params.put("foursquare_id", foursquareId);
    }
    return jinx.flickrPost(params, Response.class);
  }


  /**
   * Get the geo data (latitude and longitude and the accuracy level) for a photo.
   * <br>
   * This method does not require authentication.
   *
   * @param photoId (Required) The id of the photo you want to retrieve location data for.
   * @param sign    if true, the request will be signed.
   * @return location data for the specified photo.
   * @throws JinxException if required parameters are missing, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.photos.geo.getLocation.html">flickr.photos.geo.getLocation</a>
   */
  public Location getLocation(String photoId, boolean sign) throws JinxException {
    JinxUtils.validateParams(photoId);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.photos.geo.getLocation");
    params.put("photo_id", photoId);
    return jinx.flickrGet(params, Location.class);
  }


  /**
   * Get permissions for who may view geo data for a photo.
   * <br>
   * This method requires authentication with 'read' permission.
   *
   * @param photoId (Required) The id of the photo to get permissions for.
   * @return object with the geo permissions for the specified photo.
   * @throws JinxException if required parameters are missing, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.photos.geo.getPerms.html">flickr.photos.geo.getPerms</a>
   */
  public GeoPerms getGeoPerms(String photoId) throws JinxException {
    JinxUtils.validateParams(photoId);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.photos.geo.getPerms");
    params.put("photo_id", photoId);
    return jinx.flickrGet(params, GeoPerms.class);
  }

  /**
   * Return a list of photos for the calling user at a specific latitude, longitude and accuracy
   * <br>
   * This method requires authentication with 'read' permission.
   *
   * @param lat      (Required) The latitude whose valid range is -90 to 90. Anything more than 6 decimal places will be truncated.
   * @param lon      (Required) The longitude whose valid range is -180 to 180. Anything more than 6 decimal places will be truncated.
   * @param accuracy (Optional) Recorded accuracy level of the location information. World level is 1, Country is ~3, Region ~6, City ~11, Street ~16. Current range is 1-16. Defaults to 16 if not specified.
   * @param extras   (Optional) extra information to fetch for each returned photo.
   * @param perPage  Number of photos to return per page. If this argument is less than 1, it defaults to 100. The maximum allowed value is 500.
   * @param page     The page of results to return. If this argument is less than 1, it defaults to 1.
   * @return photos object with photos in the requested range.
   * @throws JinxException if required parameters are missing, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.photos.geo.photosForLocation.html">flickr.photos.geo.photosForLocation</a>
   */
  public Photos photosForLocation(Float lat, Float lon, Integer accuracy, EnumSet<JinxConstants.PhotoExtras> extras, int perPage, int page) throws JinxException {
    JinxUtils.validateParams(lat, lon);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.photos.geo.photosForLocation");
    params.put("lat", lat.toString());
    params.put("lon", lon.toString());
    if (accuracy != null) {
      params.put("accuracy", accuracy.toString());
    }
    if (!JinxUtils.isNullOrEmpty(extras)) {
      params.put("extras", JinxUtils.buildCommaDelimitedList(extras));
    }
    if (perPage > 0) {
      params.put("per_page", Integer.toString(perPage));
    }
    if (page > 0) {
      params.put("page", Integer.toString(page));
    }
    return jinx.flickrGet(params, Photos.class);
  }

  /**
   * Removes the geo data associated with a photo.
   * <br>
   * This method requires authentication with 'write' permission.
   *
   * @param photoId (Required) The id of the photo you want to remove location data from.
   * @return object with the status of the requested operation.
   * @throws JinxException if required parameters are missing, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.photos.geo.removeLocation.html">flickr.photos.geo.removeLocation</a>
   */
  public Response removeLocation(String photoId) throws JinxException {
    JinxUtils.validateParams(photoId);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.photos.geo.removeLocation");
    params.put("photo_id", photoId);
    return jinx.flickrPost(params, Response.class);
  }


  /**
   * Indicate the state of a photo's geotagginess beyond latitude and longitude.
   * <br>
   * Note : photos passed to this method must already be geotagged.
   * <br>
   * This method requires authentication with 'write' permission.
   *
   * @param photoId (Required) The id of the photo to set context data for.
   * @param context (Required) The photo's geotagginess beyond latitude and longitude.
   * @return object with the status of the requested operation.
   * @throws JinxException if required parameters are missing, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.photos.geo.setContext.html">flickr.photos.geo.setContext</a>
   */
  public Response setContext(String photoId, JinxConstants.GeoContext context) throws JinxException {
    JinxUtils.validateParams(photoId);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.photos.geo.setContext");
    params.put("photo_id", photoId);
    params.put("context", Integer.toString(JinxUtils.geoContextToFlickrContextId(context)));
    return jinx.flickrPost(params, Response.class);
  }


  /**
   * Sets the geo data (latitude and longitude and, optionally, the accuracy level) for a photo.
   * Before users may assign location data to a photo they must define who, by default, may view that information.
   * Users can edit this preference at http://www.flickr.com/account/geo/privacy/. If a user has not set this preference,
   * the API method will return an error.
   * <br>
   * This method requires authentication with 'write' permission.
   * <br>
   * <br>
   * The default context for geotagged photos is 0, or "not defined"
   *
   * @param photoId  (Required) The id of the photo to set location data for.
   * @param lat      (Required) The latitude whose valid range is -90 to 90. Anything more than 6 decimal places will be truncated.
   * @param lon      (Required) The longitude whose valid range is -180 to 180. Anything more than 6 decimal places will be truncated.
   * @param accuracy (Optional) Recorded accuracy level of the location information. World level is 1, Country is ~3, Region ~6, City ~11, Street ~16. Current range is 1-16. Defaults to 16 if not specified.
   * @param context  (Optional) The photo's geotagginess beyond latitude and longitude.
   * @return object with the status of the requested operation.
   * @throws JinxException if required parameters are missing, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.photos.geo.setLocation.html">flickr.photos.geo.setLocation</a>
   */
  public Response setLocation(String photoId, Float lat, Float lon, Integer accuracy, JinxConstants.GeoContext context) throws JinxException {
    JinxUtils.validateParams(photoId, lat, lon);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.photos.geo.setLocation");
    params.put("photo_id", photoId);
    params.put("lat", lat.toString());
    params.put("lon", lon.toString());
    if (accuracy != null) {
      params.put("accuracy", accuracy.toString());
    }
    if (context != null) {
      params.put("context", Integer.toString(JinxUtils.geoContextToFlickrContextId(context)));
    }
    return jinx.flickrPost(params, Response.class);
  }

  /**
   * Set the permission for who may view the geo data associated with a photo.
   * <br>
   * This method requires authentication with 'write' permission.
   *
   * @param photoId   (Required) The id of the photo to set permissions for.
   * @param isPublic  viewing permissions for the photo location data to public.
   * @param isContact viewing permissions for the photo location data to contacts.
   * @param isFriend  viewing permissions for the photo location data to friends.
   * @param isFamily  viewing permissions for the photo location data to family.
   * @return object with the status of the requested operation.
   * @throws JinxException if required parameters are missing, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.photos.geo.setPerms.html">flickr.photos.geo.setPerms</a>
   */
  public Response setPerms(String photoId, boolean isPublic, boolean isContact, boolean isFriend, boolean isFamily) throws JinxException {
    JinxUtils.validateParams(photoId);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.photos.geo.setPerms");
    params.put("photo_id", photoId);
    params.put("is_public", isPublic ? "1" : "0");
    params.put("is_contact", isContact ? "1" : "0");
    params.put("is_friend", isFriend ? "1" : "0");
    params.put("is_family", isFamily ? "1" : "0");
    return jinx.flickrPost(params, Response.class);
  }
}
