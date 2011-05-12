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
package net.jeremybrooks.jinx;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * This class contains public static methods that perform common functionality
 * for other parts of Jinx.
 *
 * Document objects are created here, and there are methods for common XML
 * parsing operations here as well.
 * 
 * @author jeremyb
 */
public class JinxUtils {

    static {
	documentBuilderFactory = DocumentBuilderFactory.newInstance();
	xPathFactory = XPathFactory.newInstance();
	formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	ymdFormatter = new SimpleDateFormat("yyyy-MM-dd");

    }

    /* Instance of the document builder factory. */
    private static DocumentBuilderFactory documentBuilderFactory;

    /* Instance of the xpath factory. */
    private static XPathFactory xPathFactory;

    /* Dates look like this: 2004-11-29 16:01:26 */
    private static SimpleDateFormat formatter;

    /* Formatter for YYYY-MM-DD dates. */
    private static SimpleDateFormat ymdFormatter;

    /**
     * Parses the status from an xml document.
     *
     * The XML response from Flickr has "stat", "err", and "msg"
     * attributes which indicate the status of the request and any error
     * codes and messages. An attribute value of "fail" for the attribute "stat"
     * indicates that there was an error. If there was an error, an instance
     * of JinxException is thrown with the error code and error message.
     *
     * If there were no errors, the Document object is returned so that callers
     * can parse the payload.
     *
     * @param xml the xml to parse the status from.
     * @return document object created from the xml.
     * @throws JinxException if there are any errors.
     */
    public static Document parseStatus(String xml) throws JinxException {
	InputStream in = null;
	Document xmlDoc = getDocument(xml);
	
	String stat;
	int errorCode;
	String errorMessage;

	try {
	    stat = getValueByXPath(xmlDoc, "/rsp/@stat");

	    if (stat.equals("fail")) {
		errorCode = getValueByXPathAsInt(xmlDoc, "/rsp/err/@code");
		errorMessage = getValueByXPath(xmlDoc, "/rsp/err/@msg");

		throw new JinxException("Call to Flickr failed with error code " +
			errorCode + ":" + errorMessage + ".", null, errorCode, errorMessage);
	    }

	} catch (JinxException fe) {
	    throw fe;
	    
	} catch (Exception e) {
	    throw new JinxException("Unexpected error while parsing status from XML. " +
		    "XML was '" + xml + "'", e);
	} finally {
	    if (in != null) {
		try {
		    in.close();
		} catch (Exception e) {
		    // ignore
		}
	    }
	}

	return xmlDoc;
    }


    /**
     * Get a value by xpath.
     *
     * This method will return an empty string if there are any errors, such
     * as an invalid xpath or an invalid document object.
     *
     * @param document xml document to apply xpath to.
     * @param xpath the xpath to get the value from.
     * @return value from the xpath, or an empty string.
     */
    public static String getValueByXPath(Document document, String xpath) {
	String value = "";
	try {
	    value = getxPath().evaluate(xpath, document).trim();
	} catch (Exception e) {
	    // ignore; will return empty string
	}

	return value;
    }


    /**
     * Get a value by xpath and return as an int.
     *
     * @param document xml document to apply xpath to.
     * @param xpath the xpath to get the value from.
     * @return value contained at the xpath, or 0 if parsing fails.
     */
    public static int getValueByXPathAsInt(Document document, String xpath) {
	int x = 0;

	try {
	    x = Integer.parseInt(getValueByXPath(document, xpath));
	} catch (Exception e) {
	    // ignore, will return 0
	}
	return x;
    }


    /**
     * Get a value by xpath and return as a boolean.
     *
     * @param document xml document to apply xpath to.
     * @param xpath the xpath to get the value from.
     * @return true if the value is "1", false otherwise.
     */
    public static boolean getValueByXPathAsBoolean(Document document, String xpath) {
	boolean b = false;
	try {
	    b = getValueByXPath(document, xpath).equals("1");
	} catch (Exception e) {
	    // ignore; will return false
	}

	return b;
    }


    /**
     * Format a date in MySQL format.
     *
     * If the date is null, this method will return an empty string.
     *
     * @param date the date to format.
     * @return formatted date.
     */
    public static String formatDateAsMySqlTimestamp(Date date) {
	String retString = "";
	if (date != null) {
	    retString = JinxUtils.formatter.format(date);
	}
	return retString;
    }


    /**
     * Convert a MySql datetime to a Java date.
     *
     * The MySql datetime should look something like this: 2004-11-29 16:01:26
     *
     * @param datetime the datetime to convert to a Java date object.
     * @return date represented by the datetime, or null if it is not a valid
     *         format.
     */
    public static Date parseMySqlDatetimeToDate(String datetime) {
	Date date = null;
	try {
	    date = JinxUtils.formatter.parse(datetime);
	} catch (Exception e) {
	    // will return null
	}
	return date;
    }


    /**
     * Format a date in YYYY-MM-DD format.
     *
     * If the date is null, this method will return an empty string.
     *
     * @param date the date to format.
     * @return formatted date.
     */
    public static String formatDateAsYMD(Date date) {
	String retString = "";
	if (date != null) {
	    retString = JinxUtils.ymdFormatter.format(date);
	}
	return retString;
    }

    /**
     * Get a named value from the NamedNodeMap.
     *
     * If the value does not exist, or if there is an error getting data from
     * the map, an empty string will be returned.
     *
     * @param map the NamedNodeMap to get a value from.
     * @param name the name of the attribute to find.
     * @return value of the named attribute from the map, or an empty String.
     */
    public static String getAttribute(NamedNodeMap map, String name) {
	String value = "";

	try {
	    Node node = map.getNamedItem(name);
	    if (node != null) {
		value = node.getNodeValue().trim();
	    }
	} catch (Exception e) {
	    // ignore; will return empty string
	}

	return value;
    }


    /**
     * Get a named value from the NamedNodeMap as an int.
     *
     * If the value does not exist, or if there is an error getting data from
     * the map, 0 will be returned.
     *
     * @param map the NamedNodeMap to get a value from.
     * @param name the name of the attribute to find.
     * @return value of the named attribute from the map as an int.
     */
    public static int getAttributeAsInt(NamedNodeMap map, String name) {
	int value = 0;

	try {
	    value = Integer.parseInt(getAttribute(map, name));
	} catch (Exception e) {
	    // will return 0
	}

	return value;
    }

    /**
     * Get a named value from the NamedNodeMap as a boolean.
     *
     * Returns true only if the attribute is "1".
     * 
     * If the value does not exist, or if there is an error getting data from
     * the map, false will be returned.
     *
     * @param map the NamedNodeMap to get a value from.
     * @param name the name of the attribute to find.
     * @return value of the named attribute from the map as a boolean.
     */
    public static boolean getAttributeAsBoolean(NamedNodeMap map, String name) {
	boolean value = false;

	try {
	    value = (getAttribute(map, name)).equals("1");
	} catch (Exception e) {
	    // will return false
	}

	return value;
    }



    public static String getFirstChildTextContent(Node node) {
	String content = "";

	if (node != null) {
	    try {
		Node n = node.getFirstChild();
		if (n != null) {
		    content = n.getTextContent();
		}
	    } catch (Exception e) {
		// will return empty string
	    }
	}

	return content.trim();
    }

    public static String getNamedChildTextContent(Node node, String name) {
	String content = "";

	if (node != null) {
	    try {
		NodeList nodes = node.getChildNodes();
		if (nodes != null) {
		    for (int i = 0; i < nodes.getLength(); i++) {
			Node child = nodes.item(i);
			if (child.getNodeName().equals(name)) {
			    content = child.getTextContent();
			    break;
			}
		    }
		}
	    } catch (Exception e) {
		// ignore, will return empty string
	    }
	}

	return content.trim();
    }


    /**
     * Determine if the String object is null or empty.
     *
     * @param string the string to check.
     * @return true if the string is null or empty.
     */
    public static boolean isEmpty(String string) {
	return (string == null || string.trim().length() == 0);
    }


    /**
     * Create a Date object from a Unix timestamp.
     *
     * A Unix timestamp is is the number of seconds that have elapsed since
     * January 1, 1970. This value is converted into milliseconds, then used
     * to create a Date object.
     *
     * If the timestamp is not a valid long, this method will return null.
     *
     * @param timestamp Unix timestamp to convert to a Date.
     * @return the timestamp as a Java Date object, or null if the timestamp is
     *         invalid.
     */
    public static Date parseTimestampToDate(String timestamp) {
	Date d = null;
	try {
	    long millis = Long.parseLong(timestamp) * 1000;
	    d = new Date(millis);
	} catch (Exception e) {
	    // ignore; will return null
	}

	return d;
    }


    /**
     * Convert a Date to a Unix timestamp.
     *
     * If the date object is null or invalid, this method will return an empty
     * String.
     *
     * @param date the date to convert.
     * @return date as a Unix timestamp, represented as a String, or an empty
     *         String if the date object is invalid.
     */
    public static String formatDateAsUnixTimestamp(Date date) {
	String timestamp = "";
	try {
	    timestamp = Long.toString(date.getTime() / 1000L);
	} catch (Exception e) {
	    // ignore; will return empty string
	}

	return timestamp;
    }


    /**
     * Return the Flickr representation of the boolean value.
     *
     * @param value the boolean value.
     * @return "1" for true, "0" for false.
     */
    public static String booleanToString(boolean value) {
	return value ? "1" : "0";
    }



    /**
     *
     * @param xml 
     * @return
     * @throws JinxException
     */
    private static Document getDocument(String xml) throws JinxException {
	Document retDoc = null;
	InputStream in = null;
	try {
	    in = new ByteArrayInputStream(xml.getBytes("UTF-8"));
	    retDoc = documentBuilderFactory.newDocumentBuilder().parse(in);
	} catch (Exception e) {
	    throw new JinxException("Unable to create Document.", e);
	} finally {
	    if (in != null) {
		try {
		    in.close();
		} catch (Exception e) {
		    // ignore
		}
	    }
	}

	return retDoc;
    }


    /**
     * @return an XPath ready to use.
     */
    private static XPath getxPath() {
	return xPathFactory.newXPath();
    }

static char[] hexChar = {
        '0', '1', '2', '3',
        '4', '5', '6', '7',
        '8', '9', 'a', 'b',
        'c', 'd', 'e', 'f'};

    /**
     * Convert a byte array to a hex string.
     *
     * @param b The byte array
     * @return The hex String
     */
    public static String toHexString(byte[] b) {
        StringBuilder sb = new StringBuilder(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            // look up high nibble char
            sb.append(hexChar[(b[i] & 0xf0) >>> 4]);

            // look up low nibble char
            sb.append(hexChar[b[i] & 0x0f]);
        }
        return sb.toString();
    }
    
}
