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
import net.jeremybrooks.jinx.response.photos.upload.CheckTicketsResponse;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by jeremyb on 8/1/14.
 */
public class PhotosUploadApi {
    private Jinx jinx;

    private PhotosUploadApi() {
    }

    public PhotosUploadApi(Jinx jinx) {
        this.jinx = jinx;
    }

    /**
     * <a href="https://www.flickr.com/services/api/flickr.photos.upload.checkTickets.html">flickr.photos.upload.checkTickets</a>
     * <p/>
     * Checks the status of one or more asynchronous photo upload tickets.
     * <p/>
     * This method does not require authentication.
     *
     * @param tickets (Required) list of ticket id's.
     * @return object with status of each ticket.
     * @throws JinxException if required parameters are missing, or if there are any errors.
     */
    public CheckTicketsResponse checkTickets(List<String> tickets) throws JinxException {
        JinxUtils.validateParams(tickets);
        Map<String, String> params = new TreeMap<String, String>();
        params.put("method", "flickr.photos.upload.checkTickets");
        params.put("tickets", JinxUtils.buildCommaDelimitedList(tickets));
        return jinx.flickrGet(params, CheckTicketsResponse.class, false);
    }
}
