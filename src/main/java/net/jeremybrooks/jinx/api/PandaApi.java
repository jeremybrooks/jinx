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
import net.jeremybrooks.jinx.JinxConstants;
import net.jeremybrooks.jinx.JinxException;
import net.jeremybrooks.jinx.JinxUtils;
import net.jeremybrooks.jinx.response.panda.Pandas;
import net.jeremybrooks.jinx.response.photos.Photos;

import java.util.EnumSet;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by jeremyb on 7/14/14.
 */
public class PandaApi {
    private Jinx jinx;

    public PandaApi(Jinx jinx) {
        this.jinx = jinx;
    }

    /**
     * <a href="https://www.flickr.com/services/api/flickr.panda.getList.html">flickr.panda.getList</a>
     * <p/>
     * Return a list of Flickr pandas, from whom you can request photos using the {@link #getPhotos(String, java.util.EnumSet, int, int, boolean)} API method.
     * <p/>
     * This method does not require authentication.
     * <p/>
     * More information about the pandas can be found on the
     * <a href="http://code.flickr.com/blog/2009/03/03/panda-tuesday-the-history-of-the-panda-new-apis-explore-and-you/">dev blog</a>.
     *
     * @return object with a list of Flickr Pandas, and information about the success or failure of the call.
     * @throws JinxException if there are any errors.
     */
    public Pandas getList() throws JinxException {
        Map<String, String> params = new TreeMap<String, String>();
        params.put("method", "flickr.panda.getList");
        return jinx.flickrGet(params, Pandas.class, false);
    }


    /**
     * <a href="https://www.flickr.com/services/api/flickr.panda.getPhotos.html">flickr.panda.getPhotos</a>
     * <p/>
     * Ask the Flickr Pandas for a list of recent public (and "safe") photos.
     * <p/>
     * More information about the pandas can be found on the
     * <a href="http://code.flickr.com/blog/2009/03/03/panda-tuesday-the-history-of-the-panda-new-apis-explore-and-you/">dev blog</a>.     Authentication
     * <p/>
     * This method does not require authentication.
     * <p/>
     * You can fetch a list of all the current pandas using the {@link #getList()} API method.
     *
     * @param pandaName (Required) The name of the panda to ask for photos from.
     * @param extras    extra information to fetch for each returned record.
     * @param perPage   Number of photos to return per page. If this argument is less than 1, it defaults to 100. The maximum allowed value is 500.
     * @param page      The page of results to return. If this argument is less than 1, it defaults to 1.
     * @param sign      if true, the request will be signed.
     * @return photos object from the panda.
     * @throws Exception if required parameter is missing, or if there are any errors.
     */
    public Photos getPhotos(String pandaName, EnumSet<JinxConstants.PhotoExtras> extras, int perPage, int page, boolean sign) throws Exception {
        JinxUtils.validateParams(pandaName);
        Map<String, String> params = new TreeMap<String, String>();
        params.put("method", "flickr.panda.getPhotos");
        params.put("panda_name", pandaName);
        if (!JinxUtils.isNullOrEmpty(extras)) {
            params.put("extras", JinxUtils.buildCommaDelimitedList(extras));
        }
        if (perPage > 0) {
            params.put("per_page", Integer.toString(perPage));
        }
        if (page > 0) {
            params.put("page", Integer.toString(page));
        }
        return jinx.flickrGet(params, Photos.class, sign);
    }
}
