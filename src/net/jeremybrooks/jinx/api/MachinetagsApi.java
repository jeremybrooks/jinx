package net.jeremybrooks.jinx.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import net.jeremybrooks.jinx.Jinx;
import net.jeremybrooks.jinx.JinxException;
import net.jeremybrooks.jinx.JinxUtils;
import net.jeremybrooks.jinx.dto.Value;
import net.jeremybrooks.jinx.dto.Values;
import net.jeremybrooks.jinx.logger.JinxLogger;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * http://code.flickr.com/blog/2008/12/15/machine-tag-hierarchies/
 * @author assure
 *
 */
public class MachinetagsApi {

    private static MachinetagsApi instance = null;


    private MachinetagsApi() {
    }


    public static MachinetagsApi getInstance() {
	if (MachinetagsApi.instance == null) {
		MachinetagsApi.instance = new MachinetagsApi();
	}

	return MachinetagsApi.instance;
    }
    
    /**
     * http://www.flickr.com/services/api/flickr.machinetags.getNamespaces.html
     * @param predicate
     * @throws JinxException
     */
    public void getNamespaces(String predicate) throws JinxException
    {
    	Map<String, String> params = new TreeMap<String, String>();
    	params.put("method", "flickr.machinetags.getNamespaces");
    	params.put("api_key", Jinx.getInstance().getApiKey());
    	params.put("predicate", predicate);
    	System.out.println(params.toString());
    	
    	JinxLogger.getLogger().log(params.toString());
    	
    	Document doc = Jinx.getInstance().callFlickr(params,false,false);
    	System.out.println(doc);
    	
    }
    
    /**
     * http://www.flickr.com/services/api/flickr.machinetags.getPredicates.html
     */
    public void getPredicates()
    {
    	
    }
    
    /**
     * http://www.flickr.com/services/api/explore/?method=flickr.machinetags.getRecentValues
     */
    public void getRecentValues()
    {
    	
    }
    
    /**
     * http://www.flickr.com/services/api/flickr.machinetags.getValues.html
     */
    public Values getValues(String namespace, String predicate) throws JinxException
    {
    	Map<String, String> params = new TreeMap<String, String>();
    	params.put("method", "flickr.machinetags.getValues");
    	params.put("api_key", Jinx.getInstance().getApiKey());
    	params.put("namespace", namespace);
    	params.put("predicate", predicate);
    	System.out.println(params.toString());
    	Document doc = Jinx.getInstance().callFlickr(params,false,false);
    	System.out.println(doc);
    	return parseValuesXml(doc);
    }
    
    private Values parseValuesXml(Document doc) throws JinxException {
    	Values values = new Values();
    	List<Value> valueList = new ArrayList<Value>();

    	values.setPage(JinxUtils.getValueByXPathAsInt(doc, "/rsp/values/@page"));
    	values.setPages(JinxUtils.getValueByXPathAsInt(doc, "/rsp/values/@pages"));
    	values.setPerpage(JinxUtils.getValueByXPathAsInt(doc, "/rsp/values/@perpage"));
    	values.setTotal(JinxUtils.getValueByXPathAsInt(doc, "/rsp/values/@total"));

    	// Get all the photo nodes
    	NodeList nodeList = doc.getElementsByTagName("value");
    	for (int i = 0; i < nodeList.getLength(); i++) {
    		Value value = new Value();
    	    Node node = nodeList.item(i);
    	    NamedNodeMap attrs = node.getAttributes();
    	    value.setUsage(JinxUtils.getAttributeAsInt(attrs, "usage"));
    	    value.setValue(JinxUtils.getFirstChildTextContent(node));
    	    valueList.add(value);
    	}
    	values.setValues(valueList);
    	return values;
    }
    
    /**
     * http://www.flickr.com/services/api/flickr.machinetags.getPairs.html
     */
    public void getPairs()
    {}
}
