/*
 * Jinx is Copyright 2010-2025 by Jeremy Brooks and Contributors
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
import net.jeremybrooks.jinx.response.photos.suggestions.Suggestions;

import java.util.Map;
import java.util.TreeMap;

/**
 * Provides access to the {@code flickr.photos.suggestions} API methods.
 *
 * @author Jeremy Brooks
 * @see <a href="https://www.flickr.com/services/api/">Flickr API documentation</a> for more details.
 */
public class PhotosSuggestionsApi {
  private final Jinx jinx;

  public PhotosSuggestionsApi(Jinx jinx) {
    this.jinx = jinx;
  }

  /**
   * Approve a suggestion for a photo.
   * <br>
   * This method requires authentication with 'write' permission.
   *
   * @param suggestionId (Required) The unique ID for the location suggestion to approve.
   * @return object with the result of the requested operation.
   * @throws JinxException if required parameters are missing or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.photos.suggestions.approveSuggestion.html">flickr.photos.suggestions.approveSuggestion</a>
   */
  public Response approveSuggestion(String suggestionId) throws JinxException {
    JinxUtils.validateParams(suggestionId);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.photos.suggestions.approveSuggestion");
    params.put("suggestion_id", suggestionId);
    return jinx.flickrPost(params, Response.class);
  }

  /**
   * Return a list of suggestions for a user that are pending approval.
   * <br>
   * This method requires authentication with 'read' permission.
   *
   * @param photoId (Optional) Only show suggestions for this photo.
   * @param status  (Optional) Only show suggestions with a given status. If this is null, the default is pending.
   * @return object with a list of the suggestions.
   * @throws JinxException if required parameters are missing or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.photos.suggestions.getList.html">flickr.photos.suggestions.getList</a>
   */
  public Suggestions getList(String photoId, JinxConstants.SuggestionStatus status) throws JinxException {
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.photos.suggestions.getList");
    if (!JinxUtils.isNullOrEmpty(photoId)) {
      params.put("photo_id", photoId);
    }
    if (status != null) {
      params.put("status_id", JinxUtils.suggestionStatusToFlickrSuggestionStatusId(status).toString());
    }
    return jinx.flickrPost(params, Suggestions.class);
  }


  /**
   * Reject a suggestion for a photo.
   * <br>
   * This method requires authentication with 'write' permission.
   *
   * @param suggestionId (Required) The unique ID of the suggestion to reject.
   * @return object with the result of the requested operation.
   * @throws JinxException if required parameters are missing or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.photos.suggestions.rejectSuggestion.html">flickr.photos.suggestions.rejectSuggestion</a>
   */
  public Response rejectSuggestion(String suggestionId) throws JinxException {
    JinxUtils.validateParams(suggestionId);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.photos.suggestions.rejectSuggestion");
    params.put("suggestion_id", suggestionId);
    return jinx.flickrPost(params, Response.class);
  }


  /**
   * Remove a suggestion, made by the calling user, from a photo.
   * <br>
   * This method requires authentication with 'write' permission.
   *
   * @param suggestionId (Required) The unique ID for the location suggestion to approve.
   * @return object with the result of the requested operation.
   * @throws JinxException if required parameters are missing or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.photos.suggestions.removeSuggestion.html">flickr.photos.suggestions.removeSuggestion</a>
   */
  public Response removeSuggestion(String suggestionId) throws JinxException {
    JinxUtils.validateParams(suggestionId);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.photos.suggestions.removeSuggestion");
    params.put("suggestion_id", suggestionId);
    return jinx.flickrPost(params, Response.class);
  }

  /**
   * Suggest a geotagged location for a photo.
   * <br>
   * This method requires authentication with 'write' permission.
   * <p>
   * The returned {@link net.jeremybrooks.jinx.response.photos.suggestions.Suggestions} object has only one
   * Suggestion object in the list, and that Suggestion object has only the suggestionId set. All other fields
   * will be null.
   *
   * @param photoId  (Required) The photo whose location you are suggesting.
   * @param lat      (Required) The latitude whose valid range is -90 to 90. Anything more than 6 decimal places will be truncated.
   * @param lon      (Required) The longitude whose valid range is -180 to 180. Anything more than 6 decimal places will be truncated.
   * @param accuracy (Optional) Recorded accuracy level of the location information. World level is 1, Country is ~3, Region ~6, City ~11, Street ~16. Current range is 1-16. Defaults to 16 if not specified.
   * @param woeId    (Optional) The WOE ID of the location used to build the location hierarchy for the photo.
   * @param placeId  (Optional) The Flickr Places ID of the location used to build the location hierarchy for the photo.
   * @param note     (Optional) A short note or history to include with the suggestion.
   * @return suggestions object with a single suggestion, with only the id set.
   * @throws JinxException if required parameters are missing or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.photos.suggestions.suggestLocation.html">flickr.photos.suggestions.suggestLocation</a>
   */
  public Suggestions suggestLocation(String photoId, Float lat, Float lon, Integer accuracy, String woeId, String placeId, String note) throws JinxException {
    JinxUtils.validateParams(photoId, lat, lon);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.photos.suggestions.suggestLocation");
    params.put("photo_id", photoId);
    params.put("lat", lat.toString());
    params.put("lon", lon.toString());
    if (accuracy != null) {
      params.put("accuracy", accuracy.toString());
    }
    if (!JinxUtils.isNullOrEmpty(woeId)) {
      params.put("woe_id", woeId);
    }
    if (!JinxUtils.isNullOrEmpty(placeId)) {
      params.put("place_id", placeId);
    }
    if (!JinxUtils.isNullOrEmpty(note)) {
      params.put("note", note);
    }
    return jinx.flickrPost(params, Suggestions.class);
  }

}
