package net.jeremybrooks.jinx.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import net.jeremybrooks.jinx.Jinx;
import net.jeremybrooks.jinx.JinxException;
import net.jeremybrooks.jinx.JinxUtils;
import net.jeremybrooks.jinx.dto.Namespace;
import net.jeremybrooks.jinx.dto.Namespaces;
import net.jeremybrooks.jinx.dto.Predicate;
import net.jeremybrooks.jinx.dto.Predicates;
import net.jeremybrooks.jinx.dto.Value;
import net.jeremybrooks.jinx.dto.Values;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * http://code.flickr.com/blog/2008/12/15/machine-tag-hierarchies/
 * @author emeraldjava
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
     * Return a list of unique namespaces, optionally limited by a given predicate, in alphabetical order.
     * http://www.flickr.com/services/api/flickr.machinetags.getNamespaces.html
     * @param predicate String optional
     * @param per_page String optional
     * @param page String optional	
     */
    public Namespaces getNamespaces(String predicate,String per_page,String page) throws JinxException
    {
    	Map<String, String> params = new TreeMap<String, String>();
    	params.put("method", "flickr.machinetags.getNamespaces");
    	params.put("api_key", Jinx.getInstance().getApiKey());
    	if (predicate != null && !predicate.trim().isEmpty())
    		params.put("predicate", predicate);
    	if (per_page != null && !per_page.trim().isEmpty())
    		params.put("per_page", per_page);
   		if (page != null && !page.trim().isEmpty())
   			params.put("page", page);
    	Document doc = Jinx.getInstance().callFlickr(params,false,false);
    	return parseNamespacesXml(doc);
    }
    
    private Namespaces parseNamespacesXml(Document doc) throws JinxException {
    	Namespaces namespaces = new Namespaces();
    	List<Namespace> namespaceList = new ArrayList<Namespace>();

    	namespaces.setPage(JinxUtils.getValueByXPathAsInt(doc, "/rsp/values/@page"));
    	namespaces.setPages(JinxUtils.getValueByXPathAsInt(doc, "/rsp/values/@pages"));
    	namespaces.setPerpage(JinxUtils.getValueByXPathAsInt(doc, "/rsp/values/@perpage"));
    	namespaces.setTotal(JinxUtils.getValueByXPathAsInt(doc, "/rsp/values/@total"));

    	// Get all the predicate nodes
    	NodeList nodeList = doc.getElementsByTagName("namespace");
    	for (int i = 0; i < nodeList.getLength(); i++) {
    		Namespace namespace = new Namespace();
    	    Node node = nodeList.item(i);
    	    NamedNodeMap attrs = node.getAttributes();
    	    namespace.setUsage(JinxUtils.getAttributeAsInt(attrs, "usage"));
    	    namespace.setPredicates(JinxUtils.getAttributeAsInt(attrs, "predicates"));
    	    namespace.setValue(JinxUtils.getFirstChildTextContent(node));
    	    namespaceList.add(namespace);
    	}
    	namespaces.setNamespaces(namespaceList);
    	return namespaces;
    }
    
    /**
     * Return a list of unique predicates, optionally limited by a given namespace.
     * http://www.flickr.com/services/api/flickr.machinetags.getPredicates.html
     * @param namespace String optional
     * @param per_page String optional
     * @param page String optional		
     */
    public Predicates getPredicates(String namespace,String per_page,String page) throws JinxException
    {
    	Map<String, String> params = new TreeMap<String, String>();
    	params.put("method", "flickr.machinetags.getPredicates");
    	params.put("api_key", Jinx.getInstance().getApiKey());
    	if (namespace != null && !namespace.trim().isEmpty())
    		params.put("namespace", namespace);
    	if (per_page != null && !per_page.trim().isEmpty())
    		params.put("per_page", per_page);
    	if (page != null && !page.trim().isEmpty())
    		params.put("page", page);
    	Document doc = Jinx.getInstance().callFlickr(params,false,false);
    	return parsePredicatesXml(doc);
    }
    
    private Predicates parsePredicatesXml(Document doc) throws JinxException {
    	Predicates predicates = new Predicates();
    	List<Predicate> predicateList = new ArrayList<Predicate>();

    	predicates.setPage(JinxUtils.getValueByXPathAsInt(doc, "/rsp/values/@page"));
    	predicates.setPerpage(JinxUtils.getValueByXPathAsInt(doc, "/rsp/values/@perpage"));
    	predicates.setTotal(JinxUtils.getValueByXPathAsInt(doc, "/rsp/values/@total"));

    	// Get all the predicate nodes
    	NodeList nodeList = doc.getElementsByTagName("predicate");
    	for (int i = 0; i < nodeList.getLength(); i++) {
    		Predicate predicate = new Predicate();
    	    Node node = nodeList.item(i);
    	    NamedNodeMap attrs = node.getAttributes();
    	    predicate.setUsage(JinxUtils.getAttributeAsInt(attrs, "usage"));
    	    predicate.setNamespaces(JinxUtils.getAttributeAsInt(attrs, "namespaces"));
    	    predicate.setValue(JinxUtils.getFirstChildTextContent(node));
    	    predicateList.add(predicate);
    	}
    	predicates.setPredicates(predicateList);
    	return predicates;
    }
    
    /**
     * Fetch recently used (or created) machine tags values.
     * http://www.flickr.com/services/api/explore/?method=flickr.machinetags.getRecentValues
     * @param namespace String optional
     * @param predicate String optional
     * @param added_since String optional
     */
    public void getRecentValues(String namespace,String predicate,String added_since) throws JinxException
    {
    	throw new JinxException("",new UnsupportedOperationException("getRecentValues()"));
    }
    
    /**
     * Return a list of unique values for a namespace and predicate.
     * http://www.flickr.com/services/api/flickr.machinetags.getValues.html
     * @param namespace String required
     * @param predicate String required
     * @param per_page String optional
     * @param page String optional	
     */
    public Values getValues(String namespace,String predicate,String per_page,String page) throws JinxException
    {
    	Map<String, String> params = new TreeMap<String, String>();
    	params.put("method", "flickr.machinetags.getValues");
    	params.put("api_key", Jinx.getInstance().getApiKey());
    	
    	if (namespace == null || namespace.trim().isEmpty()) {
    	    throw new JinxException("Parameter namespace is required.");
    	}
   		params.put("namespace", namespace);
    	
    	if (predicate == null || predicate.trim().isEmpty()) {
    	    throw new JinxException("Parameter predicate is required.");
    	}
   		params.put("predicate", predicate);
    	
    	if (per_page != null && !per_page.trim().isEmpty())
    		params.put("per_page", per_page);
    	if (page != null && !page.trim().isEmpty())
    		params.put("page", page);
    	Document doc = Jinx.getInstance().callFlickr(params,false,false);
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
    public void getPairs() throws JinxException
    {
    	throw new JinxException("",new UnsupportedOperationException("getPairs()"));
    }
}