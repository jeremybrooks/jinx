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
import net.jeremybrooks.jinx.response.places.PlaceInfo;
import net.jeremybrooks.jinx.response.places.PlaceTypes;
import net.jeremybrooks.jinx.response.places.Places;
import net.jeremybrooks.jinx.response.places.ShapeHistory;
import net.jeremybrooks.jinx.response.tags.Tags;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Jeremy Brooks
 */
public class PlacesApi {
  private Jinx jinx;

  private PlacesApi() {
  }

  public PlacesApi(Jinx jinx) {
    this.jinx = jinx;
  }

  /**
   * Return a list of place IDs for a query string.
   * <p>
   * The flickr.places.find method is not a geocoder. It will round up to the nearest place type to which place
   * IDs apply. For example, if you pass it a street level address it will return the city that contains the
   * address rather than the street, or building, itself.
   *
   * Authentication
   * <p>
   * This method does not require authentication.
   *
   * @param query query string to user for place lookup. Required.
   * @return places that match the query string.
   * @throws JinxException if required parameters are missing, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.places.find.html">flickr.places.find</a>
   */
  public Places find(String query) throws JinxException {
    JinxUtils.validateParams(query);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.places.find");
    params.put("query", query);
    return jinx.flickrGet(params, Places.class, false);
  }

  /**
   * Return a place ID for a latitude, longitude and accuracy triple.
   * <p>
   * The flickr.places.findByLatLon method is not meant to be a (reverse) geocoder in the
   * traditional sense. It is designed to allow users to find photos for "places" and will
   * round up to the nearest place type to which corresponding place IDs apply.
   * <p>
   * For example, if you pass it a street level coordinate it will return the city that contains
   * the point rather than the street, or building, itself.
   * <p>
   * It will also truncate latitudes and longitudes to three decimal points.
   * Authentication
   * <p>
   * This method does not require authentication.
   *
   * @param latitude  the latitude whose valid range is -90 to 90. Anything more than 4 decimal
   *                  places will be truncated. (Required)
   * @param longitude the longitude whose valid range is -180 to 180. Anything more than 4 decimal
   *                  places will be truncated. (Required)
   * @param accuracy  Recorded accuracy level of the location information.
   *                  World level is 1, Country is ~3, Region ~6, City ~11, Street ~16.
   *                  Current range is 1-16. The default is 16. (Optional)
   * @return places that match the location criteria.
   * @throws JinxException if required parameters are missing, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.places.findByLatLon.html">flickr.places.findByLatLon</a>
   */
  public Places findByLatLon(Float latitude, Float longitude, Integer accuracy) throws JinxException {
    JinxUtils.validateParams(latitude, longitude);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.places.findByLatLon");
    params.put("lat", latitude.toString());
    params.put("lon", longitude.toString());
    if (accuracy != null) {
      params.put("accuracy", accuracy.toString());
    }
    return jinx.flickrGet(params, Places.class, false);
  }

  /**
   * Return a list of locations with public photos that are parented by a Where on Earth (WOE) or Places ID.
   * Authentication
   * <p>
   * This method does not require authentication.
   * <p>
   * You must provide a valid placesId or woeId. If you provide both, the placesId will be used.
   * <p>
   *
   * @param placeId a Flickr places Id.
   * @param woeId   a Where On Earth (WOE) id.
   * @return places with public photos in the specified area.
   * @throws JinxException if required parameters are missing, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.places.getChildrenWithPhotosPublic.html">flickr.places.getChildrenWithPhotosPublic</a>
   */
  public Places getChildrenWithPhotosPublic(String placeId, String woeId) throws JinxException {
    if (JinxUtils.isNullOrEmpty(placeId)) {
      JinxUtils.validateParams(woeId);
    }
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.places.getChildrenWithPhotosPublic");
    if (JinxUtils.isNullOrEmpty(placeId)) {
      params.put("woe_id", woeId);
    } else {
      params.put("place_id", placeId);
    }
    return jinx.flickrGet(params, Places.class, false);
  }

  /**
   * Get information about a place.
   * Authentication
   * This method does not require authentication.
   * You must provide a valid placesId or woeId. If you provide both, the placesId will be used.
   *
   * @param placeId 4yya valid Flickr place id.
   * @param woeId   a Where On Earth (WOE) id.
   * @return information about the specified place.
   * @throws JinxException if required parameters are missing, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.places.getInfo.html">flickr.places.getInfo</a>
   */
  public PlaceInfo getInfo(String placeId, String woeId) throws JinxException {
    if (JinxUtils.isNullOrEmpty(placeId)) {
      JinxUtils.validateParams(woeId);
    }
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.places.getInfo");
    if (JinxUtils.isNullOrEmpty(placeId)) {
      params.put("woe_id", woeId);
    } else {
      params.put("place_id", placeId);
    }
    return jinx.flickrGet(params, PlaceInfo.class, false);
  }

  /**
   * Lookup information about a place, by its flickr.com/places URL.
   *
   * Authentication
   *
   * This method does not require authentication.
   *
   * @param url a flickr.com/places URL in the form of /country/region/city.
   *            For example: /Canada/Quebec/Montreal. Required.
   * @return information about the place defined by the URL.
   * @throws JinxException if required parameters are missing, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.places.getInfoByUrl.html">flickr.places.getInfoByUrl</a>
   */
  public PlaceInfo getInfoByUrl(String url) throws JinxException {
    JinxUtils.validateParams(url);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.places.getInfoByUrl");
    params.put("url", url);
    return jinx.flickrGet(params, PlaceInfo.class, false);
  }

  /**
   * Fetches a list of available place types for Flickr.
   *
   * Authentication
   *
   * This method does not require authentication.
   *
   * @return available place types for Flickr.
   * @throws JinxException if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.places.getPlaceTypes.html">flickr.places.getPlaceTypes</a>
   */
  public PlaceTypes getPlaceTypes() throws JinxException {
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.places.getPlaceTypes");
    return jinx.flickrGet(params, PlaceTypes.class, false);
  }

  /**
   * Return an historical list of all the shape data generated for a Places or Where on Earth (WOE) ID.
   *
   * <p>Authentication</p>
   *
   * <p>This method does not require authentication.</p>
   *
   * @param placeId 4yya valid Flickr place id.
   * @param woeId   a Where On Earth (WOE) id.
   * @return information about the specified place.
   * @throws JinxException if required parameters are missing, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.places.getShapeHistory.html">flickr.places.getShapeHistory</a>
   */
  public ShapeHistory getShapeHistory(String placeId, String woeId) throws JinxException {
    if (JinxUtils.isNullOrEmpty(placeId)) {
      JinxUtils.validateParams(woeId);
    }
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.places.getShapeHistory");
    if (JinxUtils.isNullOrEmpty(placeId)) {
      params.put("woe_id", woeId);
    } else {
      params.put("place_id", placeId);
    }
    return jinx.flickrGet(params, ShapeHistory.class, false);
  }

  /**
   * Return the top 100 most geotagged places for a day.
   *
   * Authentication
   *
   * This method does not require authentication.
   *
   * @param placeTypeId The type ID for a specific place type to cluster photos by. (Required)
   * @param date        a valid date in YYYY-MM-DD format. The default is yesterday. (Optional)
   * @param placeId     limit your query to only those top places belonging to a specific Flickr
   *                    Places identifier. (Optional)
   * @param woeId       limit your query to only those top places belonging to a specific
   *                    Where on Earth (WOE) identifier. (Optional)
   * @return top geotagged places for the date.
   * @throws JinxException if required parameters are missing, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.places.getTopPlacesList.html">flickr.places.getTopPlacesList</a>
   */
  public Places getTopPlaces(JinxConstants.PlaceTypeId placeTypeId, Date date, String placeId, String woeId) throws JinxException {
    JinxUtils.validateParams(placeTypeId);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.places.getTopPlacesList");
    params.put("place_type_id", placeTypeId.getTypeId().toString());
    if (date != null) {
      params.put("date", JinxUtils.formatDateAsYMD(date));
    }
    if (!JinxUtils.isNullOrEmpty(placeId)) {
      params.put("place_id", placeId);
    }
    if (!JinxUtils.isNullOrEmpty(woeId)) {
      params.put("woe_id", woeId);
    }
    return jinx.flickrGet(params, Places.class, false);
  }

  /**
   * Return all the locations of a matching place type for a bounding box.
   *
   * <p>The maximum allowable size of a bounding box (the distance between the SW and NE corners)
   * is governed by the place type you are requesting. Allowable sizes are as follows:</p>
   * <ul>
   * <li>neighbourhood: 3km (1.8mi)</li>
   * <li>locality: 7km (4.3mi)</li>
   * <li>county: 50km (31mi)</li>
   * <li>region: 200km (124mi)</li>
   * <li>country: 500km (310mi)</li>
   * <li>continent: 1500km (932mi)</li>
   * </ul>
   *
   * Authentication
   *
   * This method does not require authentication.
   *
   * @param boundingBox a comma-delimited list of 4 values defining the Bounding Box of the area
   *                    that will be searched. The 4 values represent the bottom-left corner of the
   *                    box and the top-right corner, minimum_longitude, minimum_latitude,
   *                    maximum_longitude, maximum_latitude. Required.
   * @param placeTypeId id for a specific place to cluster photos by. Required.
   * @return places matching the bounding box.
   * @throws JinxException if required parameters are missing, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.places.placesForBoundingBox.html">flickr.places.placesForBoundingBox</a>
   */
  public Places getPlacesForBoundingBox(String boundingBox, JinxConstants.PlaceTypeId placeTypeId) throws JinxException {
    JinxUtils.validateParams(boundingBox, placeTypeId);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.places.placesForBoundingBox");
    params.put("bbox", boundingBox);
    params.put("place_type_id", placeTypeId.getTypeId().toString());
    return jinx.flickrGet(params, Places.class, false);
  }

  /**
   * Return a list of the top 100 unique places clustered by a given placetype for a user's contacts.
   *
   * Authentication
   *
   * This method requires authentication with 'read' permission.
   *
   * @param placeTypeId       ID for a specific place type to cluster photos by. Required.
   * @param placeId           a Flickr Places identifier to use to filter photo clusters.
   *                          You must pass a placesId or woeId.
   * @param woeId             a Where on Earth identifier to use to filter photo clusters.
   *                          You must pass a placesId or woeId.
   * @param threshold         the minimum number of photos that a place type must have to be included.
   *                          If the number of photos is lowered then the parent place type for that
   *                          place will be used. Optional.
   * @param contacts          which contacts to search. Default is all. Optional.
   * @param minimumUploadDate Minimum upload date. Photos with an upload date greater than or
   *                          equal to this value will be returned. Optional.
   * @param maximumUploadDate Maximum upload date. Photos with an upload date less than or equal
   *                          to this value will be returned. Optional.
   * @param minimumTakenDate  Minimum taken date. Photos with an taken date greater than or equal to
   *                          this value will be returned. Optional.
   *                          The date should be in the form of a mysql datetime.
   * @param maximumTakenDate  Maximum taken date. Photos with an taken date less than or equal to
   *                          this value will be returned. Optional.
   *                          The date should be in the form of a mysql datetime.
   * @return places for users contacts.
   * @throws JinxException if required parameters are missing, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.places.placesForContacts.html">flickr.places.placesForContacts</a>
   */
  public Places getPlacesForContacts(JinxConstants.PlaceTypeId placeTypeId, String placeId, String woeId,
                                     Integer threshold, JinxConstants.Contacts contacts,
                                     Date minimumUploadDate, Date maximumUploadDate,
                                     Date minimumTakenDate, Date maximumTakenDate) throws JinxException {
    JinxUtils.validateParams(placeTypeId);
    if (JinxUtils.isNullOrEmpty(placeId)) {
      JinxUtils.validateParams(woeId);
    }
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.places.placesForContacts");
    params.put("place_type_id", placeTypeId.getTypeId().toString());
    if (JinxUtils.isNullOrEmpty(placeId)) {
      params.put("woe_id", woeId);
    } else {
      params.put("place_id", placeId);
    }
    if (threshold != null) {
      params.put("threshold", threshold.toString());
    }
    if (contacts != null) {
      params.put("contacts", contacts.toString());
    }
    if (minimumUploadDate != null) {
      params.put("min_upload_date", JinxUtils.formatDateAsUnixTimestamp(minimumUploadDate));
    }
    if (maximumUploadDate != null) {
      params.put("max_upload_date", JinxUtils.formatDateAsUnixTimestamp(maximumUploadDate));
    }
    if (minimumTakenDate != null) {
      params.put("min_taken_date", JinxUtils.formatDateAsMySqlTimestamp(minimumTakenDate));
    }
    if (maximumTakenDate != null) {
      params.put("max_taken_date", JinxUtils.formatDateAsMySqlTimestamp(maximumTakenDate));
    }
    return jinx.flickrGet(params, Places.class);
  }

  /**
   * Return a list of the top 100 unique places clustered by a given placetype for set of tags or machine tags.
   *
   * Authentication
   *
   * This method does not require authentication.
   *
   * @param placeTypeId       ID for a specific place type to cluster photos by. Required.
   * @param placeId           a Flickr Places identifier to use to filter photo clusters.
   *                          You must pass a placesId or woeId.
   * @param woeId             a Where on Earth identifier to use to filter photo clusters.
   *                          You must pass a placesId or woeId.
   * @param threshold         the minimum number of photos that a place type must have to be included.
   *                          If the number of photos is lowered then the parent place type
   *                          for that place will be used. Optional.
   * @param tags              a list of tags. Photos with one or more of the tags listed will be returned.
   *                          Must specify tags or machine tags.
   * @param tagMode           Either 'any' for an OR combination of tags, or 'all' for an AND combination.
   *                          Defaults to 'any' if not specified. Optional.
   * @param machineTags       list of machine tags to find. Must specify tags or machine tags.
   *                          The number of machine tags you can pass in a single query depends on
   *                          the tag mode (AND or OR) that you are querying with. "AND" queries are limited
   *                          to 16 machine tags. "OR" queries are limited to 8.
   * @param machineTagMode    Either 'any' for an OR combination of tags, or 'all' for an AND combination. Defaults to 'any' if not specified. Optional.
   * @param minimumUploadDate Minimum upload date. Photos with an upload date greater than or
   *                          equal to this value will be returned. Optional.
   * @param maximumUploadDate Maximum upload date. Photos with an upload date less than or equal
   *                          to this value will be returned. Optional.
   * @param minimumTakenDate  Minimum taken date. Photos with an taken date greater than or equal to
   *                          this value will be returned. Optional.
   *                          The date should be in the form of a mysql datetime.
   * @param maximumTakenDate  Maximum taken date. Photos with an taken date less than or equal to
   *                          this value will be returned. Optional.
   *                          The date should be in the form of a mysql datetime.
   * @return places for specified tags.
   * @throws JinxException if required parameters are missing, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.places.placesForTags.html">flickr.places.placesForTags</a>
   */
  public Places getPlacesForTags(JinxConstants.PlaceTypeId placeTypeId, String placeId, String woeId,
                                 Integer threshold, List<String> tags, JinxConstants.TagMode tagMode,
                                 List<String> machineTags, JinxConstants.TagMode machineTagMode,
                                 Date minimumUploadDate, Date maximumUploadDate,
                                 Date minimumTakenDate, Date maximumTakenDate) throws JinxException {
    JinxUtils.validateParams(placeTypeId);
    if (JinxUtils.isNullOrEmpty(placeId)) {
      JinxUtils.validateParams(woeId);
    }
    if (JinxUtils.isNullOrEmpty(tags)) {
      JinxUtils.validateParams(machineTags);
    }
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.places.placesForTags");
    params.put("place_type_id", placeTypeId.getTypeId().toString());
    if (JinxUtils.isNullOrEmpty(placeId)) {
      params.put("woe_id", woeId);
    } else {
      params.put("place_id", placeId);
    }
    if (threshold != null) {
      params.put("threshold", threshold.toString());
    }
    if (!JinxUtils.isNullOrEmpty(tags)) {
      params.put("tags", JinxUtils.buildCommaDelimitedList(JinxUtils.normalizeTagsForSearch(tags)));
    }
    if (tagMode != null) {
      params.put("tag_mode", tagMode.toString());
    }
    if (!JinxUtils.isNullOrEmpty(machineTags)) {
      params.put("machine_tags", JinxUtils.buildCommaDelimitedList(JinxUtils.normalizeMachineTagsForSearch(machineTags)));
    }
    if (machineTagMode != null) {
      params.put("machine_tag_mode", machineTagMode.toString());
    }
    if (minimumUploadDate != null) {
      params.put("min_upload_date", JinxUtils.formatDateAsUnixTimestamp(minimumUploadDate));
    }
    if (maximumUploadDate != null) {
      params.put("max_upload_date", JinxUtils.formatDateAsUnixTimestamp(maximumUploadDate));
    }
    if (minimumTakenDate != null) {
      params.put("min_taken_date", JinxUtils.formatDateAsMySqlTimestamp(minimumTakenDate));
    }
    if (maximumTakenDate != null) {
      params.put("max_taken_date", JinxUtils.formatDateAsMySqlTimestamp(maximumTakenDate));
    }
    return jinx.flickrGet(params, Places.class, false);
  }


  /**
   * Return a list of the top 100 unique places clustered by a given placetype for a user.
   *
   * Authentication
   *
   * This method requires authentication with 'read' permission.
   *
   * @param placeTypeId       ID for a specific place type to cluster photos by.
   * @param placeId           a Flickr Places identifier to use to filter photo clusters.
   *                          You must pass a placesId or woeId.
   * @param woeId             a Where on Earth identifier to use to filter photo clusters.
   *                          You must pass a placesId or woeId.
   * @param threshold         the minimum number of photos that a place type must have to be included.
   *                          If the number of photos is lowered then the parent place type for
   *                          that place will be used. Optional.
   * @param minimumUploadDate Minimum upload date. Photos with an upload date greater than or
   *                          equal to this value will be returned. Optional.
   * @param maximumUploadDate Maximum upload date. Photos with an upload date less than or equal
   *                          to this value will be returned. Optional.
   * @param minimumTakenDate  Minimum taken date. Photos with an taken date greater than or equal to
   *                          this value will be returned. Optional.
   *                          The date should be in the form of a mysql datetime.
   * @param maximumTakenDate  Maximum taken date. Photos with an taken date less than or equal to
   *                          this value will be returned. Optional.
   *                          The date should be in the form of a mysql datetime.
   * @return places for users contacts.
   * @throws JinxException if required parameters are missing, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.places.placesForUser.html">flickr.places.placesForUser</a>
   */
  public Places getPlacesForUser(JinxConstants.PlaceTypeId placeTypeId, String placeId, String woeId,
                                 Integer threshold, Date minimumUploadDate, Date maximumUploadDate,
                                 Date minimumTakenDate, Date maximumTakenDate) throws JinxException {
    JinxUtils.validateParams(placeTypeId);
    if (JinxUtils.isNullOrEmpty(placeId)) {
      JinxUtils.validateParams(woeId);
    }
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.places.placesForUser");
    params.put("place_type_id", placeTypeId.getTypeId().toString());
    if (JinxUtils.isNullOrEmpty(placeId)) {
      params.put("woe_id", woeId);
    } else {
      params.put("place_id", placeId);
    }
    if (threshold != null) {
      params.put("threshold", threshold.toString());
    }
    if (minimumUploadDate != null) {
      params.put("min_upload_date", JinxUtils.formatDateAsUnixTimestamp(minimumUploadDate));
    }
    if (maximumUploadDate != null) {
      params.put("max_upload_date", JinxUtils.formatDateAsUnixTimestamp(maximumUploadDate));
    }
    if (minimumTakenDate != null) {
      params.put("min_taken_date", JinxUtils.formatDateAsMySqlTimestamp(minimumTakenDate));
    }
    if (maximumTakenDate != null) {
      params.put("max_taken_date", JinxUtils.formatDateAsMySqlTimestamp(maximumTakenDate));
    }
    return jinx.flickrGet(params, Places.class);
  }

  /**
   * Find Flickr Places information by Place ID.
   *
   * <p>The Flickr method has been deprecated. This call is delegated to {@link #getInfo(String, String)}.</p>
   *
   * Authentication
   *
   * This method does not require authentication.
   *
   * @param placeId a Flickr Places ID. Required.
   * @return place info for the place id.
   * @throws JinxException if required parameters are missing, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.places.resolvePlaceId.html">flickr.places.resolvePlaceId</a>
   */
  public PlaceInfo resolvePlaceId(String placeId) throws JinxException {
    return getInfo(placeId, null);
  }

  /**
   * Find Flickr Places information by Place URL.
   *
   * <p>The Flickr method has been deprecated. This call is delegated to {@link #getInfoByUrl(String)}.</p>
   *
   * Authentication
   *
   * This method does not require authentication.
   *
   * @param placeUrl a Flickr Places URL. Flickr Place URLs are of the form /country/region/city
   * @return place info for the place url.
   * @throws JinxException if required parameters are missing, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.places.resolvePlaceURL.html">flickr.places.resolvePlaceURL</a>
   */
  public PlaceInfo resolvePlaceUrl(String placeUrl) throws JinxException {
    return getInfoByUrl(placeUrl);
  }

  /**
   * Return a list of the top 100 unique tags for a Flickr Places or Where on Earth (WOE) ID.
   *
   * Authentication
   *
   * This method does not require authentication.
   *
   * @param placeId           a Flickr Places identifier to use to filter photo clusters.
   *                          You must pass a placesId or woeId.
   * @param woeId             a Where on Earth identifier to use to filter photo clusters.
   *                          You must pass a placesId or woeId.
   * @param minimumUploadDate Minimum upload date. Photos with an upload date greater than or
   *                          equal to this value will be returned. Optional.
   * @param maximumUploadDate Maximum upload date. Photos with an upload date less than or equal
   *                          to this value will be returned. Optional.
   * @param minimumTakenDate  Minimum taken date. Photos with an taken date greater than or equal to
   *                          this value will be returned. Optional.
   *                          The date should be in the form of a mysql datetime.
   * @param maximumTakenDate  Maximum taken date. Photos with an taken date less than or equal to
   *                          this value will be returned. Optional.
   *                          The date should be in the form of a mysql datetime.
   * @return top 100 unique tags for the place.
   * @throws JinxException if required parameters are missing, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.places.tagsForPlace.html">flickr.places.tagsForPlace</a>
   */
  public Tags getTagsForPlace(String placeId, String woeId, Date minimumUploadDate, Date maximumUploadDate,
                              Date minimumTakenDate, Date maximumTakenDate) throws JinxException {
    if (JinxUtils.isNullOrEmpty(placeId)) {
      JinxUtils.validateParams(woeId);
    }
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.places.tagsForPlace");
    if (JinxUtils.isNullOrEmpty(placeId)) {
      params.put("woe_id", woeId);
    } else {
      params.put("place_id", placeId);
    }
    if (minimumUploadDate != null) {
      params.put("min_upload_date", JinxUtils.formatDateAsUnixTimestamp(minimumUploadDate));
    }
    if (maximumUploadDate != null) {
      params.put("max_upload_date", JinxUtils.formatDateAsUnixTimestamp(maximumUploadDate));
    }
    if (minimumTakenDate != null) {
      params.put("min_taken_date", JinxUtils.formatDateAsMySqlTimestamp(minimumTakenDate));
    }
    if (maximumTakenDate != null) {
      params.put("max_taken_date", JinxUtils.formatDateAsMySqlTimestamp(maximumTakenDate));
    }
    return jinx.flickrGet(params, Tags.class, false);
  }
}
