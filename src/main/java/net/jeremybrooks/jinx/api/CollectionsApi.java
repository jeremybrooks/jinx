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
import org.w3c.dom.Document;
import net.jeremybrooks.jinx.Jinx;
import net.jeremybrooks.jinx.JinxException;
import net.jeremybrooks.jinx.JinxUtils;
import net.jeremybrooks.jinx.dto.FlickrCollection;
import net.jeremybrooks.jinx.dto.FlickrCollectionInfo;
import net.jeremybrooks.jinx.dto.Photo;
import net.jeremybrooks.jinx.dto.Photoset;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * This class provides access to the Flickr Blogs API.
 *
 * @see http://www.flickr.com/services/api/
 * @author jeremyb
 */
public class CollectionsApi {
    
    private static CollectionsApi instance = null;


    private CollectionsApi() {
    }


    /**
     * Get a reference to the only instance of the CollectionsApi class.
     *
     * @return reference to the instance of this class.
     */
    public static CollectionsApi getInstance() {
	if (CollectionsApi.instance == null) {
	    CollectionsApi.instance = new CollectionsApi();
	}

	return CollectionsApi.instance;
    }


    /**
     * Returns information for a single collection.
     * Currently can only be called by the collection owner, this may change.
     *
     * This method requires authentication with 'read' permission.
     *
     * @param collectionId (Required) The ID of the collection to fetch
     *	      information for.
     * @throws JinxException
     */
    public FlickrCollectionInfo getInfo(String collectionId) throws JinxException {
	if (JinxUtils.isEmpty(collectionId)) {
	    throw new JinxException("Parameter 'collectionId' is required.");
	}

	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.collections.getInfo");
	params.put("api_key", Jinx.getInstance().getApiKey());
	params.put("collection_id", collectionId);


	/*
	<?xml version="1.0" encoding="utf-8" ?>
	<rsp stat="ok">
	    <collection id="4956757-72157624706855334" child_count="5" datecreate="1281582807" iconlarge="http://farm5.static.flickr.com/4116/cols/72157624706855334_0538d98d22_l.jpg" iconsmall="http://farm5.static.flickr.com/4116/cols/72157624706855334_0538d98d22_s.jpg" server="4116" secret="0538d98d22">
		<title>San Francisco Neighborhoods</title>
		<description>The neighborhoods of San Francisco define the personality of the city.</description>
		<iconphotos>
		    <photo id="3596925966" owner="85853333@N00" secret="dd7af725f4" server="3326" farm="4" title="Before The Shift" ispublic="1" isfriend="0" isfamily="0" />
		    <photo id="2179268318" owner="85853333@N00" secret="7d70f9372a" server="2278" farm="3" title="Don't Point That Cleaver At Me" ispublic="1" isfriend="0" isfamily="0" />
		    <photo id="605433203" owner="85853333@N00" secret="11513f6489" server="1149" farm="2" title="I'm Wondering" ispublic="1" isfriend="0" isfamily="0" />
		    <photo id="3729926926" owner="85853333@N00" secret="562db8e1ab" server="2575" farm="3" title="Cyberpunk Baby" ispublic="1" isfriend="0" isfamily="0" />
		    <photo id="4523531516" owner="85853333@N00" secret="9257efe4bc" server="2766" farm="3" title="Palm Lights" ispublic="1" isfriend="0" isfamily="0" />
		    <photo id="3527708995" owner="85853333@N00" secret="a03df3a752" server="2178" farm="3" title="An Era Of Daring" ispublic="1" isfriend="0" isfamily="0" />
		    <photo id="3227241485" owner="85853333@N00" secret="50f1ba3f75" server="3413" farm="4" title="Extort" ispublic="1" isfriend="0" isfamily="0" />
		    <photo id="3663864689" owner="85853333@N00" secret="c6f7208683" server="3329" farm="4" title="Friday Lunch" ispublic="1" isfriend="0" isfamily="0" />
		    <photo id="3730781749" owner="85853333@N00" secret="7b76620ff7" server="2479" farm="3" title="Garden" ispublic="1" isfriend="0" isfamily="0" />
		    <photo id="3768398567" owner="85853333@N00" secret="6c025d2945" server="3462" farm="4" title="Can't Have It All" ispublic="1" isfriend="0" isfamily="0" />
		    <photo id="2679314356" owner="85853333@N00" secret="98be8a8834" server="3110" farm="4" title="Pseudo Rustic" ispublic="1" isfriend="0" isfamily="0" />
		    <photo id="2545610482" owner="85853333@N00" secret="066c6677f7" server="3051" farm="4" title="Now I've Come Back Again" ispublic="1" isfriend="0" isfamily="0" />
		</iconphotos>
	    </collection>
	</rsp>
	 */
	Document doc = Jinx.getInstance().callFlickr(params, true);
	FlickrCollectionInfo fc = new FlickrCollectionInfo();
	fc.setId(JinxUtils.getValueByXPath(doc, "/rsp/collection/@id"));
	fc.setChildCount(JinxUtils.getValueByXPathAsInt(doc, "/rsp/collection/@child_count"));
	fc.setDateCreate(JinxUtils.parseTimestampToDate(JinxUtils.getValueByXPath(doc, "/rsp/collection/@datecreate")));
	fc.setIconLarge(JinxUtils.getValueByXPath(doc, "/rsp/collection/@iconlarge"));
	fc.setIconSmall(JinxUtils.getValueByXPath(doc, "/rsp/collection/@iconsmall"));
	fc.setServer(JinxUtils.getValueByXPath(doc, "/rsp/collection/@server"));
	fc.setSecret(JinxUtils.getValueByXPath(doc, "/rsp/collection/@secret"));
	fc.setTitle(JinxUtils.getValueByXPath(doc, "/rsp/collection/title"));
	fc.setDescription(JinxUtils.getValueByXPath(doc, "/rsp/collection/description"));

	List<Photo> photoList = new ArrayList<Photo>();

	NodeList photoNodes = doc.getElementsByTagName("photo");
	if (photoNodes != null) {
	    for (int i = 0; i < photoNodes.getLength(); i++) {
		Photo p = new Photo();
		Node node = photoNodes.item(i);
		NamedNodeMap attrs = node.getAttributes();
		p.setId(JinxUtils.getAttribute(attrs, "id"));
		p.setOwnerNsid(JinxUtils.getAttribute(attrs, "owner"));
		p.setSecret(JinxUtils.getAttribute(attrs, "secret"));
		p.setServer(JinxUtils.getAttribute(attrs, "server"));
		p.setFarm(JinxUtils.getAttribute(attrs, "farm"));
		p.setTitle(JinxUtils.getAttribute(attrs, "title"));
		p.setIsFamily(JinxUtils.getAttributeAsBoolean(attrs, "isfamily"));
		p.setIsFriend(JinxUtils.getAttributeAsBoolean(attrs, "isfriend"));
		p.setIsPublic(JinxUtils.getAttributeAsBoolean(attrs, "ispublic"));

		photoList.add(p);
	    }
	}

	fc.setIconPhotoList(photoList);
	return fc;
    }
    
    
    /**
     * Returns a tree (or sub tree) of collections belonging to a given user.
     *
     * This method does not require authentication.
     *
     * @param collectionId The ID of the collection to fetch a tree for, or zero
     *        to fetch the root collection. Defaults to zero.
     * @param nsid ID of the account to fetch the collection tree for. Defaults
     *        to the calling user.
     * @param auth make an authenticated call, even though it is not required.
     * @throws JinxException if there are any errors.
     */
    public List<FlickrCollection> getTree(String collectionId, String nsid, boolean auth) throws JinxException {

	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.collections.getTree");
	params.put("api_key", Jinx.getInstance().getApiKey());

	if (!JinxUtils.isEmpty(collectionId)) {
	    params.put("collection_id", collectionId.trim());
	}
	if (!JinxUtils.isEmpty(nsid)) {
	    params.put("user_id", nsid.trim());
	}

	/*
	 <?xml version="1.0" encoding="utf-8" ?>
	 <rsp stat="ok">
	    <collections>
		<collection id="4956757-72157624706855334"
		    title="San Francisco Neighborhoods"
		    description="The neighborhoods of San Francisco define the personality of the city."
		    iconlarge="http://farm5.static.flickr.com/4116/cols/72157624706855334_0538d98d22_l.jpg"
		    iconsmall="http://farm5.static.flickr.com/4116/cols/72157624706855334_0538d98d22_s.jpg">
			<set id="72157624582280657"
			    title="San Francisco: The Tenderloin"
			    description="Photos in San Francisco's Tenderloin District." />
			<set id="72157604332778185"
			    title="San Francisco: Chinatown"
			    description="Images of the Chinatown neighborhood in San Francisco.

			    This set is managed by &lt;a href=&quot;http://www.jeremybrooks.net/suprsetr&quot; rel=&quot;nofollow&quot;&gt;SuprSetr&lt;/a&gt;" />
			<set id="72157623905840010"
			    title="San Francisco: The Haight"
			    description="Photos taken in San Francisco's Haight-Ashbury neighborhood." />
			<set id="72157604328188708"
			    title="San Francisco: North Beach"
			    description="Images of the North Beach neighborhood of San Francisco. This set is managed by &lt;a href=&quot;http://www.jeremybrooks.net/suprsetr&quot; rel=&quot;nofollow&quot;&gt;SuprSetr&lt;/a&gt;" />
			<set id="72157623741315543"
			    title="San Francisco: SoMa"
			    description="Photos taken in San Francisco's South of Market neighborhood." />
		</collection>
		<collection id="4956757-72157600050348508"
		    title="It's Only Natural"
		    description=""
		    iconlarge="http://farm1.static.flickr.com/185/cols/72157600050348508_846ed6b428_l.jpg"
		    iconsmall="http://farm1.static.flickr.com/185/cols/72157600050348508_846ed6b428_s.jpg">
		    <set id="72157594570222805"
			title="Flowers and Plants"
			description="Flowers, plants, stuff that is growing and/or blossoming. This set is managed by &lt;a href=&quot;http://www.jeremybrooks.net/suprsetr&quot; rel=&quot;nofollow&quot;&gt;SuprSetr&lt;/a&gt;" />
		</collection>
	    </collections>
	 </rsp>
	 */

	Document doc = Jinx.getInstance().callFlickr(params, auth);
	List<FlickrCollection> list = new ArrayList<FlickrCollection>();
	
	NodeList cNodes = doc.getElementsByTagName("collection");
	if (cNodes != null) {
	    for (int i = 0; i < cNodes.getLength(); i++) {
		FlickrCollection fc = new FlickrCollection();
		Node cNode = cNodes.item(i);
		NamedNodeMap attrs = cNode.getAttributes();
		fc.setId(JinxUtils.getAttribute(attrs, "id"));
		fc.setTitle(JinxUtils.getAttribute(attrs, "title"));
		fc.setDescription(JinxUtils.getAttribute(attrs, "description"));
		fc.setIconLarge(JinxUtils.getAttribute(attrs, "iconlarge"));
		fc.setIconSmall(JinxUtils.getAttribute(attrs, "iconsmall"));

		NodeList setNodes = cNode.getChildNodes();
		if (setNodes != null) {
		    List<Photoset> setList = new ArrayList<Photoset>();
		    for (int j = 0; j < setNodes.getLength(); j++) {
			
			Node setNode = setNodes.item(j);
			if (setNode.getNodeName().equals("set")) {
			    Photoset p = new Photoset();
			    attrs = setNode.getAttributes();
			    p.setId(JinxUtils.getAttribute(attrs, "id"));
			    p.setTitle(JinxUtils.getAttribute(attrs, "title"));
			    p.setDescription(JinxUtils.getAttribute(attrs, "description"));

			    setList.add(p);
			}
		    }

		    fc.setSetList(setList);
		}

		list.add(fc);
	    }
	}

	return list;
    }
}
