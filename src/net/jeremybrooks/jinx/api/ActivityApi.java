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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import net.jeremybrooks.jinx.Jinx;
import net.jeremybrooks.jinx.JinxException;
import net.jeremybrooks.jinx.JinxUtils;
import net.jeremybrooks.jinx.dto.ActivityEvent;
import net.jeremybrooks.jinx.dto.ActivityItem;
import net.jeremybrooks.jinx.dto.ActivityItems;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


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
    public ActivityItems userComments(int perPage, int page) throws JinxException {
	/*
	<?xml version="1.0" encoding="utf-8" ?>
	<rsp stat="ok">
	<items page="1" pages="1" perpage="10" total="6">
	<item type="photo" id="4949725413" owner="9571747@N04"
	ownername="The Real Devil Doll" secret="573682e936" server="4116"
	farm="5" comments="4" notes="0" views="9" faves="3">
	<title>Wendell's Drive-In</title>
	<activity>
	<event type="comment" user="85853333@N00" username="Jeremy Brooks"
	dateadded="1283399462"
	commentid="72157624860585562">You really did find some great signs down there.</event>
	</activity>
	</item>
	<item type="photo" id="4946877125" owner="9571747@N04" ownername="The Real Devil Doll" secret="9c7b89152a" server="4088" farm="5" comments="10" notes="0" views="29" faves="5">
	<title>I drove many miles, whilst falling asleep at the wheel, to photograph this sign. It was worth it.</title>
	<activity>
	<event type="comment" user="30115839@N06" username="TooMuchFire" dateadded="1283389567" commentid="72157624859786432">Definitely worth it!  Nice one.</event>
	<event type="comment" user="35528040@N04" username="Denver Pam" dateadded="1283397920" commentid="72157624860470418">It definitely was worth it</event>
	</activity>
	</item>
	<item type="photo" id="4934061330" owner="8171213@N03" ownername="Dave van Hulsteyn" secret="31c49fae9b" server="4097" farm="5" comments="13" notes="0" views="43" faves="9">
	<title>Oh, see!</title>
	<activity>
	<event type="comment" user="35528040@N04" username="Denver Pam" dateadded="1283395565" commentid="72157624735834109">stunning</event>
	</activity>
	</item>
	<item type="photo" id="4948695452" owner="8171213@N03" ownername="Dave van Hulsteyn" secret="88f11a012d" server="4144" farm="5" comments="7" notes="0" views="20" faves="7">
	<title>Uh oh, this doesn't look good.</title>
	<activity>
	<event type="comment" user="8171213@N03" username="Dave van Hulsteyn" dateadded="1283385325" commentid="72157624734996469">Oh, cool, thanks! So it's from a chocolate shop? I wouldn't have remembered that. Yeah, it's from way back then. That was a fun day, huh? Maybe we can organize another such outing in, say, October, the one month out of the year in San Francisco when I don't have to dress like I'm going on an expedition to the North Pole.</event>
	<event type="comment" user="93277896@N00" username="Joanne Dale" dateadded="1283393693" commentid="72157624735683651">*snicker*</event>
	<event type="comment" user="85853333@N00" username="Jeremy Brooks" dateadded="1283393765" commentid="72157624735689155">&lt;a href=&quot;/photos/8171213@N03/&quot;&gt;&lt;img class=&quot;notsowide personmenu-trigger&quot; src=&quot;http://farm1.static.flickr.com/192/buddyicons/8171213@N03.jpg?1244960366#8171213@N03&quot; alt=&quot;&quot; width=&quot;24&quot; height=&quot;24&quot; border=&quot;0&quot; class=&quot;BuddyIconX&quot;&gt;&lt;/a&gt; Yeah, chocolate, candy, and goofy toys, such as what they show in the window. I'm up for another expedition for sure!</event>
	<event type="comment" user="35528040@N04" username="Denver Pam" dateadded="1283395529" commentid="72157624735831245">lovin' it</event>
	</activity>
	</item>
	<item type="photo" id="4947160444" owner="33766795@N05" ownername="Travis Jensen SF" secret="08cf3cf33c" server="4122" farm="5" comments="3" notes="0" views="39" faves="2">
	<title>Kids</title>
	<activity>
	<event type="comment" user="53337180@N00" username="elee1147" dateadded="1283392070" commentid="72157624859982556">nice...agree with jeremy....first kiss?
	: )</event>
	</activity>
	</item>
	<item type="photo" id="4946901537" owner="95409971@N00" ownername="Let There Be More Light" secret="3846ac9b35" server="4093" farm="5" comments="14" notes="0" views="58" faves="6">
	<title>A Couple Of Years Later</title>
	<activity>
	<event type="comment" user="20905078@N04" username="patrickjoust" dateadded="1283386572" commentid="72157624735097381">Impressive perspective! Great capture!</event>
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

	return parseDoc(Jinx.getInstance().callFlickr(params));

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
    public ActivityItems userPhotos(String timeframe, int perPage, int page) throws JinxException {
	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.activity.userPhotos");
	if (!JinxUtils.isEmpty(timeframe)) {
	    params.put("timeframe", timeframe);
	}
	params.put("api_key", Jinx.getInstance().getApiKey());
	if (perPage > 0) {
	    params.put("per_page", Integer.toString(perPage));
	}
	if (page > 0) {
	    params.put("page", Integer.toString(page));
	}

	return parseDoc(Jinx.getInstance().callFlickr(params));

    }

    private ActivityItems parseDoc(Document doc) {

	/*
	 * The items element looks like this:
	 <?xml version="1.0" encoding="utf-8" ?>
	 <rsp stat="ok">
	    <items page="1" pages="1" perpage="10" total="6">
		<item .....
	 */
	ActivityItems ai = new ActivityItems();
	ai.setPage(JinxUtils.getValueByXPathAsInt(doc, "/rsp/items/@page"));
	ai.setPages(JinxUtils.getValueByXPathAsInt(doc, "/rsp/items/@pages"));
	ai.setPerPage(JinxUtils.getValueByXPathAsInt(doc, "/rsp/items/@perpage"));
	ai.setTotal(JinxUtils.getValueByXPathAsInt(doc, "/rsp/items/@total"));

	/*
	 * An item looks like this
	<item type="photo" id="4949725413" owner="9571747@N04"
	    ownername="The Real Devil Doll" secret="573682e936" server="4116"
	    farm="5" comments="4" notes="0" views="9" faves="3">
	    <title>Wendell's Drive-In</title>
	    <activity>
		<event type="comment" user="85853333@N00" username="Jeremy Brooks"
		    dateadded="1283399462"
		    commentid="72157624860585562">You really did find some great signs down there.</event>
	    </activity>
	</item>
	 */
	List<ActivityItem> itemList = new ArrayList<ActivityItem>();
	NodeList itemNodes = doc.getElementsByTagName("item");
	if (itemNodes != null) {
	    for (int i = 0; i < itemNodes.getLength(); i++) {
		ActivityItem item = new ActivityItem();
		Node itemNode = itemNodes.item(i);
		NamedNodeMap attrs = itemNode.getAttributes();
		item.setType(JinxUtils.getAttribute(attrs, "type"));
		item.setId(JinxUtils.getAttribute(attrs, "id"));
		item.setOwner(JinxUtils.getAttribute(attrs, "owner"));
		item.setOwnerName(JinxUtils.getAttribute(attrs, "ownername"));
		item.setSecret(JinxUtils.getAttribute(attrs, "secret"));
		item.setServer(JinxUtils.getAttribute(attrs, "server"));
		item.setFarm(JinxUtils.getAttribute(attrs, "farm"));
		item.setComments(JinxUtils.getAttributeAsInt(attrs, "comments"));
		item.setNotes(JinxUtils.getAttributeAsInt(attrs, "notes"));
		item.setViews(JinxUtils.getAttributeAsInt(attrs, "views"));
		item.setFaves(JinxUtils.getAttributeAsInt(attrs, "faves"));
		item.setTitle(JinxUtils.getNamedChildTextContent(itemNode, "title"));

		List<ActivityEvent> eventList = new ArrayList<ActivityEvent>();
		NodeList itemChildren = itemNode.getChildNodes();
		if (itemChildren != null) {
		    for (int j = 0; j < itemChildren.getLength(); j++) {
			Node n = itemChildren.item(j);
			if (n.getNodeName().equals("activity")) {
			    NodeList eventNodes = n.getChildNodes();
			    for (int k = 0; k < eventNodes.getLength(); k++) {
				Node eventNode = eventNodes.item(k);
				if (eventNode.getNodeName().equals("event")) {
				    ActivityEvent event = new ActivityEvent();
				    NamedNodeMap eventAttrs = eventNode.getAttributes();
				    event.setType(JinxUtils.getAttribute(eventAttrs, "type"));
				    event.setUser(JinxUtils.getAttribute(eventAttrs, "user"));
				    event.setUserName(JinxUtils.getAttribute(eventAttrs, "username"));
				    event.setDateAdded(JinxUtils.parseTimestampToDate(JinxUtils.getAttribute(eventAttrs, "dateadded")));
				    event.setCommentId(JinxUtils.getAttribute(eventAttrs, "commentid"));
				    event.setEventText(JinxUtils.getFirstChildTextContent(eventNode));

				    eventList.add(event);
				}
			    }

			}
		    }
		}
		item.setEventList(eventList);

		itemList.add(item);
	    }
	}
	ai.setItemList(itemList);

	return ai;
    }


    

}
