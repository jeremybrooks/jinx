/*
 * Jinx is Copyright 2010-2023 by Jeremy Brooks and Contributors
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
import net.jeremybrooks.jinx.response.photos.transform.TransformResult;

import java.util.Map;
import java.util.TreeMap;

/**
 * Provides access to the flickr.photos.transform API methods.
 *
 * @author Jeremy Brooks
 * @see <a href="https://www.flickr.com/services/api/">Flickr API documentation</a> for more details.
 */
public class PhotosTransformApi {
  private Jinx jinx;

  private PhotosTransformApi() {
  }

  public PhotosTransformApi(Jinx jinx) {
    this.jinx = jinx;
  }


  /**
   * Rotate a photo.
   * <br>
   * This method requires authentication with 'write' permission.
   *
   * @param photoId (Required) The id of the photo to rotate.
   * @param degrees (Required) The amount of degrees by which to rotate the photo (clockwise) from it's current orientation.
   * @return object with the photo id and secrets.
   * @throws JinxException if required parameters are missing, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.photos.transform.rotate.html">flickr.photos.transform.rotate</a>
   */
  public TransformResult rotate(String photoId, JinxConstants.RotateDegrees degrees) throws JinxException {
    JinxUtils.validateParams(photoId, degrees);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.photos.transform.rotate");
    params.put("photo_id", photoId);
    params.put("degrees", degrees.toString().replace("_", "")); // pull off the leading underscore
    return jinx.flickrPost(params, TransformResult.class);
  }
}
