/*
 * Jinx is Copyright 2010 by Jeremy Brooks
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

import java.util.Map;
import java.util.TreeMap;
import net.jeremybrooks.jinx.Jinx;
import net.jeremybrooks.jinx.JinxException;
import net.jeremybrooks.jinx.JinxUtils;
import org.w3c.dom.Document;

/**
 *
 * @author jeremyb
 */
public class ActivityApi {

    private static ActivityApi instance;

    private ActivityApi() {

    }

    public static ActivityApi getInstance() {
	if (instance == null) {
	    instance = new ActivityApi();
	}
	return instance;
    }


    
    /**
     * Returns a list of recent activity on photos commented on by the calling user.
     *
     * <b>Do not poll this method more than once an hour.</b>
     *
     * perPage (Optional): Number of items to return per page. If this argument
     * is less than 1, it defaults to 10. The maximum allowed value is 50.
     *
     * page (Optional): The page of results to return. If this argument is
     * less than 1, it defaults to 1.
     *
     * @param perPage the number of photos to return per page.
     * @param page the page of results to return.
     * @throws JinxException if there are any errors.
     */
    public void userComments(int perPage, int page) throws JinxException {
	/*
	 <?xml version="1.0" encoding="utf-8" ?>
	 <rsp stat="ok">
	   <items page="1" pages="1" perpage="10" total="5">
	     <item type="photo" id="4317730840" owner="7262596@N08" ownername="emma.c"
	       secret="6125a2313a" server="4028" farm="5" comments="45" notes="0"
	       views="553" faves="89">
	       <title />
	       <activity>
	         <event type="comment" user="45710177@N05" username="Jaleeesa"
	           dateadded="1277316113" commentid="72157624216618193">Aahhh I love this car
	         </event>
	       </activity>
	     </item>
	     <item type="photo" id="4723647710" owner="19180182@N07" ownername="Generik11"
	       secret="4d9335dcfa" server="1366" farm="2" comments="5" notes="0" views="25"
	       faves="1">
	       <title>I Feel Terrible About Taking This</title>
	       <activity>
	         <event type="comment" user="19180182@N07" username="Generik11"
	           dateadded="1277246685" commentid="72157624210919911">
	           Well, it certainly does bother me that some people live this way,
	           and obviously I didn't feel bad enough about the image to not post it, but
	           I do appreciate the encouraging words. Thanks.
	         </event>
	         <event type="comment" user="50815861@N00" username="Dollar Bin"
	           dateadded="1277251123"  commentid="72157624211333427">
	           This is great art, so I'm glad you posted it,
	           and I can appreciate your qualms about doing so.
	         </event>
	         <event type="comment" user="19180182@N07" username="Generik11"
	           dateadded="1277301660"  commentid="72157624339835962">
	           Thanks, Tom.
	         </event>
	         <event type="comment" user="51372874@N00" username="wawrus"
	           dateadded="1277303623"  commentid="72157624340025568">
	           Don't feel bad. This sort of thing needs to be photographed,
	           documented. Far too many people would just choose to look the
	           other way and deny that folks
	           do indeed live like this.
	         </event>
   	       </activity>
	     </item>
	 </items>
	 </rsp>
	 */
	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.activity.userComments");
	params.put("api_key", Jinx.getInstance().getApiKey());
	if (perPage > 0) {
	    params.put("per_page", Integer.toString(perPage));
	}
	if (page > 0) {
	    params.put("page", Integer.toString(page));
	}

	Document doc = Jinx.getInstance().callFlickr(params);


	
    }


    /**
     * Returns a list of recent activity on photos belonging to the calling user.
     *
     * <b>Do not poll this method more than once an hour.</b>
     *
     * This method requires authentication with 'read' permission.
     *
     * timeframe (Optional): The timeframe in which to return updates for.
     * This can be specified in days ('2d') or hours ('4h').
     * The default behavoir is to return changes since the beginning of the
     * previous user session.
     *
     * perPage (Optional): Number of items to return per page. If this argument
     * is less than 1, it defaults to 10. The maximum allowed value is 50.
     *
     * page (Optional): The page of results to return. If this argument is
     * less than 1, it defaults to 1.
     *
     * @param timeframe the timeframe in which to return updates for.
     * @param perPage number of items to return per page.
     * @param page the page of results to return.
     * @throws JinxException
     */
    public void userPhotos(String timeframe, int perPage, int page) throws JinxException {
	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.activity.userPhotos");
	if (! JinxUtils.isEmpty(timeframe)) {
	    params.put("timeframe", timeframe);
	}
	params.put("api_key", Jinx.getInstance().getApiKey());
	if (perPage > 0) {
	    params.put("per_page", Integer.toString(perPage));
	}
	if (page > 0) {
	    params.put("page", Integer.toString(page));
	}

	Document doc = Jinx.getInstance().callFlickr(params);
    }
}
