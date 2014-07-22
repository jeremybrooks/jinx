/*
 * Jinx is Copyright 2010-2014 by Jeremy Brooks and Contributors
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
import net.jeremybrooks.jinx.response.photos.licenses.Licenses;

import java.util.Map;
import java.util.TreeMap;

/**
 *
 * Created by jeremyb on 7/21/14.
 */
public class PhotosLicensesApi {
    private Jinx jinx;

    public PhotosLicensesApi(Jinx jinx) {
        this.jinx = jinx;
    }


    /**
     * <a href="https://www.flickr.com/services/api/flickr.photos.licenses.getInfo.html">flickr.photos.licenses.getInfo</a>
     * <p/>
     * Fetches a list of available photo licenses for Flickr.
     * <p/>
     * This method does not require authentication.
     *
     * @return object containing a list of currently available licenses.
     * @throws JinxException if there are any errors.
     */
    public Licenses getInfo() throws JinxException {
        Map<String, String> params = new TreeMap<String, String>();
        params.put("method", "flickr.photos.licenses.getInfo");
        return jinx.flickrGet(params, Licenses.class, false);
    }


    /**
     * <a href="https://www.flickr.com/services/api/flickr.photos.licenses.setLicense.html">flickr.photos.licenses.setLicense</a>
     * <p/>
     * Sets the license for a photo.
     * <p/>
     * This method requires authentication with 'write' permission.
     *
     * @param photoId   (Required) The photo to update the license for.
     * @param licenseId (Required) The license to apply, or 0 (zero) to remove the current license.
     *                  Note: as of this writing the "no known copyright restrictions" license (7) is not a valid argument.
     * @return object with the status of the requested operation.
     * @throws JinxException if required parameters are missing, or if there are any errors.
     */
    public Response setLicense(String photoId, Integer licenseId) throws JinxException {
        JinxUtils.validateParams(photoId, licenseId);
        Map<String, String> params = new TreeMap<String, String>();
        params.put("method", "flickr.photos.licenses.setLicense");
        params.put("photo_id", photoId);
        params.put("license_id", licenseId.toString());
        return jinx.flickrPost(params, Response.class);
    }
}
