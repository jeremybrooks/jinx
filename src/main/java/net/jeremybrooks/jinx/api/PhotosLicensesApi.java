/*
 * Jinx is Copyright 2010-2012 by Jeremy Brooks and Contributors
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
import net.jeremybrooks.jinx.dto.License;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;


/**
 *
 * @author jeremyb
 */
public class PhotosLicensesApi {

    private static PhotosLicensesApi instance = null;


    private PhotosLicensesApi() {
    }


    public static PhotosLicensesApi getInstance() {
	if (PhotosLicensesApi.instance == null) {
	    PhotosLicensesApi.instance = new PhotosLicensesApi();
	}

	return PhotosLicensesApi.instance;
    }


    /**
     * Get a list of the available licenses.
     *
     * @return list of licenses.
     * @throws JinxException if there are any errors.
     */
    public List<License> getInfo() throws JinxException {
	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.photos.licenses.getInfo");
	params.put("api_key", Jinx.getInstance().getApiKey());

	Document doc = Jinx.getInstance().callFlickr(params);

	/*
	<?xml version="1.0" encoding="utf-8" ?>
	<rsp stat="ok">
	<licenses>
	<license id="0" name="All Rights Reserved" url="" />
	<license id="4" name="Attribution License" url="http://creativecommons.org/licenses/by/2.0/" />
	<license id="6" name="Attribution-NoDerivs License" url="http://creativecommons.org/licenses/by-nd/2.0/" />
	<license id="3" name="Attribution-NonCommercial-NoDerivs License" url="http://creativecommons.org/licenses/by-nc-nd/2.0/" />
	<license id="2" name="Attribution-NonCommercial License" url="http://creativecommons.org/licenses/by-nc/2.0/" />
	<license id="1" name="Attribution-NonCommercial-ShareAlike License" url="http://creativecommons.org/licenses/by-nc-sa/2.0/" />
	<license id="5" name="Attribution-ShareAlike License" url="http://creativecommons.org/licenses/by-sa/2.0/" />
	<license id="7" name="No known copyright restrictions" url="http://www.flickr.com/commons/usage/" />
	<license id="8" name="United States Government Work" url="http://www.usa.gov/copyright.shtml" />
	</licenses>
	</rsp>
	 */
	List<License> licenses = new ArrayList<License>();
	NodeList nodes = doc.getElementsByTagName("license");
	if (nodes != null) {
	    for (int i = 0; i < nodes.getLength(); i++) {
		NamedNodeMap attrs = nodes.item(i).getAttributes();
		License l = new License();
		l.setId(JinxUtils.getAttribute(attrs, "id"));
		l.setName(JinxUtils.getAttribute(attrs, "name"));
		l.setUrl(JinxUtils.getAttribute(attrs, "url"));

		licenses.add(l);
	    }
	}


	return licenses;
    }
    

    /**
     * Sets the license for a photo.
     *
     * This method requires authentication with 'write' permission.
     *
     * Note: This method requires an HTTP POST request.
     *
     * @param photoId photo to update the license for.
     * @param licenseId license to apply, or 0 (zero) to remove the current license.
     *        Flickr currently states that the "no known copyright restrictions" license
     *        (7) is not valid. Check the Flickr API for more information.
     * @throws JinxException if any parameter is null or empty, or if there are
     *         any errors.
     */
    public void setLicense(String photoId, String licenseId) throws JinxException {
	if (photoId == null || photoId.trim().isEmpty()) {
	    throw new JinxException("Photo ID is required.");
	}
	if (licenseId == null || licenseId.trim().isEmpty()) {
	    throw new JinxException("License ID is required.");
	}

	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.photos.licenses.setLicense");
	params.put("api_key", Jinx.getInstance().getApiKey());
	params.put("photo_id", photoId);
	params.put("licnse_id", licenseId);

	Jinx.getInstance().callFlickr(params, true, true);
    }

}
