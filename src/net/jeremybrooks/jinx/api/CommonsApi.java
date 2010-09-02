package net.jeremybrooks.jinx.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import net.jeremybrooks.jinx.Jinx;
import net.jeremybrooks.jinx.JinxException;
import net.jeremybrooks.jinx.JinxUtils;
import net.jeremybrooks.jinx.dto.Institution;
import net.jeremybrooks.jinx.dto.Url;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author jeremyb
 */
public class CommonsApi {

    private static CommonsApi instance = null;

    private CommonsApi() {

    }

    public static CommonsApi getInstance() {
	if (CommonsApi.instance == null) {
	    CommonsApi.instance = new CommonsApi();
	}

	return CommonsApi.instance;
    }



    

    /**
     * Retrieves a list of the current Commons institutions.
     *
     * This method does not require authentication.
     * 
     * @return list of Institution objects.
     * @throws JinxException if there are any errors.
     */
    public List<Institution> getInstitutions() throws JinxException {
	List<Institution> list = new ArrayList<Institution>();

	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.commons.getInstitutions");
	params.put("api_key", Jinx.getInstance().getApiKey());

	/*
	<rsp stat="ok">
	    <institutions>
		<institution nsid="123456@N01" date_launch="1232000000">
		    <name>Institution</name>
		    <urls>
			<url type="site">http://example.com/</url>
			<url type="license">http://example.com/commons/license</url>
			<url type="flickr">http://flickr.com/photos/institution</url>
		    </urls>
		</institution>
	    </institutions>
	</rsp>
	*/
	
	Document doc = Jinx.getInstance().callFlickr(params, false);

	NodeList instNodes = doc.getElementsByTagName("institution");
	for (int i = 0; i < instNodes.getLength(); i++) {
	    Institution inst = new Institution();
	    Node instNode = instNodes.item(i);
	    NamedNodeMap attrs = instNode.getAttributes();
	    inst.setNsid(JinxUtils.getAttribute(attrs, "nsid"));
	    inst.setDateLaunch(JinxUtils.parseTimestampToDate(JinxUtils.getAttribute(attrs, "date_launch")));
	    inst.setName(JinxUtils.getNamedChildTextContent(instNode, "name"));

	    List<Url> urlList = new ArrayList<Url>();
	    NodeList children = instNode.getChildNodes();
	    for (int j = 0; j < children.getLength(); j++) {
		Node child = children.item(j);
		if (child.getNodeName().equals("urls")) {
		    NodeList urlNodes = child.getChildNodes();
		    for (int k = 0; k < urlNodes.getLength(); k++) {
			Node urlNode = urlNodes.item(k);
			if (urlNode.getNodeName().equals("url")) {
			    Url url = new Url();
			    NamedNodeMap urlAttrs = urlNode.getAttributes();
			    url.setType(JinxUtils.getAttribute(urlAttrs, "type"));
			    url.setUrl(JinxUtils.getFirstChildTextContent(urlNode));
			    urlList.add(url);
			}
		    }
		}
	    }

	    inst.setUrlList(urlList);

	    list.add(inst);
	}

	return list;
    }

}
