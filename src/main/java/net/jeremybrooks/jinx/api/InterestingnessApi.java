/*
 * Jinx is Copyright 2011 by Jeremy Brooks
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

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import net.jeremybrooks.jinx.Jinx;
import net.jeremybrooks.jinx.JinxException;
import net.jeremybrooks.jinx.JinxUtils;
import net.jeremybrooks.jinx.dto.Photos;
import org.w3c.dom.Document;

/**
 *
 * @author jeremyb
 */
public class InterestingnessApi {


    private static InterestingnessApi instance = null;


    private InterestingnessApi() {
    }


    /**
     * Get a reference to the InterestingnessApi class.
     *
     * @return instance of this class.
     */
    public static InterestingnessApi getInstance() {
	if (InterestingnessApi.instance == null) {
	    InterestingnessApi.instance = new InterestingnessApi();
	}

	return InterestingnessApi.instance;
    }


    /**
     * Returns the list of interesting photos for the most recent day or a
     * user-specified date.
     *
     * This method does not require authentication.
     *
     * This method is equivalent to <code>getList(null, null, 0, 0)</code>.
     *
     * @return data about the interesting photos.
     * @throws JinxException if there are any errors.
     * @see http://www.flickr.com/services/api/flickr.interestingness.getList.html
     */
    public Photos getList() throws JinxException {
	return this.getList(null, null, 0, 0);
    }


    /**
     * Returns the list of interesting photos for the most recent day or a
     * user-specified date.
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
     * EXTRAS_URL_SQ
     * EXTRAS_URL_T
     * EXTRAS_URL_S
     * EXTRAS_URL_M
     * EXTRAS_URL_Z
     * EXTRAS_URL_L
     * EXTRAS_URL_O
     *
     * @param date (optional) a specific date to return interesting photos for.
     * @param extras a list of extras to fetch for every returned record. Can be null
     *        or empty.
     * @param page page of results to return. If this argument is less than 1,
     *        it defaults to 1.
     * @param perPage number of photos to return per page. If this argument is
     *        less than 1, it defaults to 100. The maximum allowed value is 500.
     * @return data about the interesting photos.
     * @throws JinxException if there are any errors.
     * @see http://www.flickr.com/services/api/flickr.interestingness.getList.html
     */
    public Photos getList(Date date, List<String> extras, int page, int perPage) throws JinxException {
	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.interestingness.getList");
	params.put("api_key", Jinx.getInstance().getApiKey());
	if (date != null) {
	    params.put("date", JinxUtils.formatDateAsYMD(date));
	}
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

	Document doc = Jinx.getInstance().callFlickr(params, false);

	/*
	<rsp stat="ok">
	    <photos page="1" pages="5" perpage="100" total="500">
		<photo id="5703384792" owner="26063706@N04" secret="16a4bf87aa" server="5263"
		    farm="6" title="beyond the sands" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="5703665673" owner="31348155@N03" secret="99dd4c1090" server="2517"
		    farm="3" title="Libby &amp; Austin | The Pub" ispublic="1" isfriend="0" isfamily="0" />
		<photo id="5702240287" owner="14576317@N07" secret="b9004a98c4" server="5102"
		    farm="6" title="lost light" ispublic="1" isfriend="0" isfamily="0" />
	 *  </photos>
	</rsp>
	 */

	return PhotosApi.getInstance().parsePhotosXml(doc);
    }
}
