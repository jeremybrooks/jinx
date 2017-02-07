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
import net.jeremybrooks.jinx.JinxException;
import net.jeremybrooks.jinx.JinxUtils;
import net.jeremybrooks.jinx.response.Response;
import net.jeremybrooks.jinx.response.photos.people.People;

import java.util.Map;
import java.util.TreeMap;

/**
 * Provides access to the flickr.photos.people API methods.
 *
 * @author Jeremy Brooks
 * @see <a href="https://www.flickr.com/services/api/">Flickr API documentation</a> for more details.
 */
public class PhotosPeopleApi {
  private Jinx jinx;

  private PhotosPeopleApi() {
  }

  public PhotosPeopleApi(Jinx jinx) {
    this.jinx = jinx;
  }


  /**
   * Add a person to a photo. Coordinates and sizes of boxes are optional; they are measured in pixels, based on the 500px image size shown on individual photo pages.
   * <br>
   * This method requires authentication with 'write' permission.
   *
   * @param photoId (Required) The id of the photo to add a person to.
   * @param userId  (Required) The id of the user to add to the photo.
   * @param x       (Optional) The left-most pixel co-ordinate of the box around the person.
   * @param y       (Optional) The top-most pixel co-ordinate of the box around the person.
   * @param width   (Optional) The width (in pixels) of the box around the person.
   * @param height  (Optional) The height (in pixels) of the box around the person.
   * @return object with the status of the requested operation.
   * @throws JinxException if any required parameters are missing, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.photos.people.add.html">flickr.photos.people.add</a>
   */
  public Response add(String photoId, String userId, Integer x, Integer y, Integer width, Integer height) throws JinxException {
    JinxUtils.validateParams(photoId, userId);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.photos.people.add");
    params.put("photo_id", photoId);
    params.put("user_id", userId);
    if (x != null) {
      params.put("person_x", x.toString());
    }
    if (y != null) {
      params.put("person_y", y.toString());
    }
    if (width != null) {
      params.put("person_w", width.toString());
    }
    if (height != null) {
      params.put("person_h", height.toString());
    }
    return jinx.flickrPost(params, Response.class);
  }

  /**
   * Remove a person from a photo.
   * <br>
   * This method requires authentication with 'write' permission.
   * <br>
   * The NSID of the person to remove from the photo.
   *
   * @param photoId (Required) The id of the photo to remove a person from.
   * @param userId  (Required) The user id of the person to remove from the photo.
   * @return object with the status of the requested operation.
   * @throws JinxException if any required parameters are missing, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.photos.people.delete.html">flickr.photos.people.delete</a>
   */
  public Response delete(String photoId, String userId) throws JinxException {
    JinxUtils.validateParams(photoId, userId);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.photos.people.delete");
    params.put("photo_id", photoId);
    params.put("user_id", userId);
    return jinx.flickrPost(params, Response.class);
  }


  /**
   * Remove the bounding box from a person in a photo
   * <br>
   * This method requires authentication with 'write' permission.
   *
   * @param photoId (Required) The id of the photo to edit a person in.
   * @param userId  (Required) The user id of the person whose bounding box you want to remove.
   * @return object with the status of the requested operation.
   * @throws JinxException if any required parameters are missing, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.photos.people.deleteCoords.html">flickr.photos.people.deleteCoords</a>
   */
  public Response deleteCoords(String photoId, String userId) throws JinxException {
    JinxUtils.validateParams(photoId, userId);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.photos.people.deleteCoords");
    params.put("photo_id", photoId);
    params.put("user_id", userId);
    return jinx.flickrPost(params, Response.class);
  }

  /**
   * Edit the bounding box of an existing person on a photo.
   * <br>
   * This method requires authentication with 'write' permission.
   *
   * @param photoId (Required) The id of the photo to edit a person in.
   * @param userId  (Required) The user id of the person to edit in a photo.
   * @param x       (Required) The left-most pixel co-ordinate of the box around the person.
   * @param y       (Required) The top-most pixel co-ordinate of the box around the person.
   * @param width   (Required) The width (in pixels) of the box around the person.
   * @param height  (Required) The height (in pixels) of the box around the person.
   * @return object with the status of the requested operation.
   * @throws JinxException if any required parameters are missing, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.photos.people.editCoords.html">flickr.photos.people.editCoords</a>
   */
  public Response editCoords(String photoId, String userId, Integer x, Integer y, Integer width, Integer height) throws JinxException {
    JinxUtils.validateParams(photoId, userId, x, y, width, height);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.photos.people.editCoords");
    params.put("photo_id", photoId);
    params.put("user_id", userId);
    params.put("person_x", x.toString());
    params.put("person_y", y.toString());
    params.put("person_w", width.toString());
    params.put("person_h", height.toString());
    return jinx.flickrPost(params, Response.class);
  }

  /**
   * Get a list of people in a given photo.
   * <br>
   * This method does not require authentication.
   * <br>
   *
   * @param photoId (Required) The id of the photo to get a list of people for.
   * @return list of people in the photo.
   * @throws JinxException if any required parameters are missing, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.photos.people.getList.html">flickr.photos.people.getList</a>
   */
  public People getList(String photoId) throws JinxException {
    JinxUtils.validateParams(photoId);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.photos.people.getList");
    params.put("photo_id", photoId);
    return jinx.flickrGet(params, People.class, false);
  }
}
