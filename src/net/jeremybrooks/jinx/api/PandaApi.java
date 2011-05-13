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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import net.jeremybrooks.jinx.Jinx;
import net.jeremybrooks.jinx.JinxException;
import net.jeremybrooks.jinx.JinxUtils;
import net.jeremybrooks.jinx.dto.Panda;
import net.jeremybrooks.jinx.dto.Photos;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author jeremyb
 */
public class PandaApi {

    private static PandaApi instance = null;


    private PandaApi() {
    }


    public static PandaApi getInstance() {
	if (PandaApi.instance == null) {
	    PandaApi.instance = new PandaApi();
	}

	return PandaApi.instance;
    }

    
    /**
     * Return a list of Flickr pandas, from whom you can request photos using
     * the PandaApi getPhotos method.
     *
     * This method does not require authentication.
     *
     * @return list of Flickr pandas.
     * @throws JinxException if there are any errors.
     */
    public List<Panda> getList() throws JinxException {
	List<Panda> list = new ArrayList<Panda>();

	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.panda.getList");
	params.put("api_key", Jinx.getInstance().getApiKey());

	Document doc = Jinx.getInstance().callFlickr(params);

	/*
	<rsp stat="ok">
	    <pandas>
		<panda>ling ling</panda>
		<panda>hsing hsing</panda>
		<panda>wang wang</panda>
	    </pandas>
	</rsp>
	 */
	NodeList nodes = doc.getElementsByTagName("panda");
	if (nodes != null) {
	    for (int i = 0; i < nodes.getLength(); i++) {
		Node node = nodes.item(i);
		Panda panda = new Panda();
		panda.setName(JinxUtils.getFirstChildTextContent(node));
		list.add(panda);
	    }
	}
	return list;
    }


    /**
     * Ask the Flickr Pandas for a list of recent public (and "safe") photos.
     *
     * This method does not require authentication.
     * 
     * This method is equivalent to calling <code>getPhotos(panda, null, 0, 0);</code>
     *
     * @param panda (required) the panda to ask for photos from.
     * @return photos from the specified panda.
     * @throws JinxException if panda is null or if there are any errors.
     */
    public Photos getPhotos(Panda panda) throws JinxException {
	return this.getPhotos(panda, null, 0, 0);
    }


    /**
     * Ask the Flickr Pandas for a list of recent public (and "safe") photos.
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
     * @param panda (required) the panda to ask for photos from.
     * @param extras a list of extras to fetch for every returned record. Can be null
     *        or empty.
     * @param page page of results to return. If this argument is less than 1,
     *        it defaults to 1.
     * @param perPage number of photos to return per page. If this argument is
     *        less than 1, it defaults to 100. The maximum allowed value is 500.
     * @return photos from the specified panda.
     * @throws JinxException if panda is null or if there are any errors.
     */
    public Photos getPhotos(Panda panda, List<String> extras, int page, int perPage) throws JinxException {
	if (panda == null) {
	    throw new JinxException("Parameter panda is required.");
	}

	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.panda.getPhotos");
	params.put("api_key", Jinx.getInstance().getApiKey());
	params.put("panda_name", panda.getName());
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

	 /*
	<rsp stat="ok">
	    <photos page="1" pages="64822" perpage="3" total="194466">
		<photo id="5687835617" owner="33715381@N03" secret="f1ea66f5f3" server="5105" farm="6" title="18/52" ispublic="1" isfriend="0" isfamily="0" ownername="ChrisSinn" dateadded="1304544877" />
		<photo id="5684540481" owner="52559257@N02" secret="885962c5ce" server="5189" farm="6" title="2043" ispublic="1" isfriend="0" isfamily="0" ownername="benbobjr" dateadded="1304544501" />
		<photo id="5687771715" owner="55289364@N06" secret="2b850df31c" server="5308" farm="6" title="resting place" ispublic="1" isfriend="0" isfamily="0" ownername="joann13833" dateadded="1304541840" />
	    </photos>
	</rsp>
	 */

	return PhotosApi.getInstance().parsePhotosXml(doc);
    }
}
