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

package net.jeremybrooks.jinx;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;


/**
 * This class contains public static methods that perform common functionality
 * for other parts of Jinx.
 * <p/>
 * Document objects are created here, and there are methods for common XML
 * parsing operations here as well.
 *
 * @author jeremyb
 */
public class JinxUtils {

	static {
		formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ymdFormatter = new SimpleDateFormat("yyyy-MM-dd");

	}


	/* Dates look like this: 2004-11-29 16:01:26 */
	private static SimpleDateFormat formatter;

	/* Formatter for YYYY-MM-DD dates. */
	private static SimpleDateFormat ymdFormatter;


	/**
	 * Format a date in MySQL format.
	 * <p/>
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
	 * <p/>
	 * The MySql datetime should look something like this: 2004-11-29 16:01:26
	 *
	 * @param datetime the datetime to convert to a Java date object.
	 * @return date represented by the datetime, or null if it is not a valid
	 * format.
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
	 * <p/>
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
	 * Parse a string in the format yyyy-MM-dd to a Date object.
	 *
	 * @param dateString string to parse.
	 * @return date string as a date object.
	 * @throws ParseException if the date is not in the correct yyyy-MM-dd format.
	 */
	public static Date parseYMDToDate(String dateString) throws ParseException {
		return ymdFormatter.parse(dateString);
	}


	/**
	 * Create a Date object from a Unix timestamp.
	 * <p/>
	 * A Unix timestamp is is the number of seconds that have elapsed since
	 * January 1, 1970. This value is converted into milliseconds, then used
	 * to create a Date object.
	 * <p/>
	 * If the timestamp is not a valid long, this method will return null.
	 *
	 * @param timestamp Unix timestamp to convert to a Date.
	 * @return the timestamp as a Java Date object, or null if the timestamp is
	 * invalid.
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
	 * <p/>
	 * If the date object is null or invalid, this method will return an empty
	 * String.
	 *
	 * @param date the date to convert.
	 * @return date as a Unix timestamp, represented as a String, or an empty
	 * String if the date object is invalid.
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

	public static void validateParams(Object... params) throws JinxException {
		for (Object o : params) {
			if (o == null) {
				throw new JinxException("Parameters cannot be null.");
			} else if (o instanceof List) {
				for (Object listObject : (List) o) {
					if (listObject == null) {
						throw new JinxException("Objects in list cannot be null.");
					}
				}
			} else if (o instanceof String) {
				if ( ((String)o).length() == 0) {
					throw new JinxException("String cannot be empty.");
				}
			}
		}
	}

	public static boolean isNullOrEmpty(String s) {
		return s == null || s.trim().length() == 0;
	}
	public static boolean isNullOrEmpty(Collection collection) {
		return collection == null || collection.size() == 0;
	}

	public static String buildCommaDelimitedList(Collection collection) {
		if (isNullOrEmpty(collection)) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		for (Object o : collection) {
			sb.append(o.toString().trim()).append(',');
		}
		sb.deleteCharAt(sb.length()-1);
		return sb.toString();
	}

	public static int toFlickrPrivacy(JinxConstants.PrivacyFilter privacyFilter) {
		int level = 0;
		switch (privacyFilter) {
			case privacyPublic:
				level = 1;
				break;
			case privacyFriends:
				level = 2;
				break;
			case privacyFamily:
				level = 3;
				break;
			case privacyFriendsAndFamily:
				level = 4;
				break;
			case privacyPrivate:
				level = 5;
				break;
			default:
				level = 0;
				break;
		}
		return level;
	}

		//TODO write test
	public static JinxConstants.Perms flickrPermsIdToPerms(int id) {
		JinxConstants.Perms perms = null;
		switch(id) {
			case 0:
				perms = JinxConstants.Perms.nobody;
				break;
			case 1:
				perms = JinxConstants.Perms.friendsAndFamily;
				break;
			case 2:
				perms = JinxConstants.Perms.contacts;
				break;
			case 3:
				perms = JinxConstants.Perms.everybody;
				break;
			default:
				perms = null;
				break;
		}
		return perms;
	}


	// TODO write test
	public static int permsToFlickrPermsId(JinxConstants.Perms perms) {
		int id = 0;
		switch(perms) {
			case nobody:
				id = 0;
				break;
			case friendsAndFamily:
				id = 1;
				break;
			case contacts:
				id = 2;
				break;
			case everybody:
				id = 3;
				break;
			default:
				id = 0;
				break;
		}
		return id;
	}

	//TODO write test
	public static String sortOrderToString(JinxConstants.SortOrder sortOrder) {
		return sortOrder.toString().replaceAll("_", "-");
	}

	//TODO write test
	public static JinxConstants.SortOrder stringToSortOrder(String sortOrderString) {
		JinxConstants.SortOrder retVal = null;
		String s = sortOrderString.toLowerCase().trim().replaceAll("-", "_");
		for (JinxConstants.SortOrder sortOrder : JinxConstants.SortOrder.values()) {
			if (sortOrder.toString().equals(s)) {
				retVal = sortOrder;
				break;
			}
		}
		return retVal;
	}

	// TODO write test
	public static int contentTypeToFlickrContentTypeId(JinxConstants.ContentType contentType) {
		int ret = 0;
		switch (contentType) {
			case photo:
				ret = 1;
				break;
			case screenshot:
				ret = 2;
				break;
			case other:
				ret = 3;
				break;
			default:
				ret = 0;
				break;
		}
		return ret;
	}
	// TODO write test
	public static JinxConstants.ContentType flickrContentTypeIdToContentType(int type) {
		JinxConstants.ContentType ret = null;
		switch(type) {
			case 1:
				ret = JinxConstants.ContentType.photo;
				break;
			case 2:
				ret = JinxConstants.ContentType.screenshot;
				break;
			case 3:
				ret = JinxConstants.ContentType.other;
				break;
			default:
				ret = null;
				break;
		}
		return ret;
	}

	// TODO write test
	public static int safetyLevelToFlickrSafteyLevelId(JinxConstants.SafetyLevel safetyLevel) {
		int ret = 0;
		switch (safetyLevel) {
			case safe:
				ret = 1;
				break;
			case moderate:
				ret = 2;
				break;
			case restricted:
				ret = 3;
				break;
			default:
				ret = 0;
				break;
		}
		return ret;
	}

	// TODO write test
	public static JinxConstants.SafetyLevel flickrSafetyLevelIdToSafetyLevel(int level) {
		JinxConstants.SafetyLevel ret = null;
		switch (level) {
			case 1:
				ret = JinxConstants.SafetyLevel.safe;
				break;
			case 2:
				ret = JinxConstants.SafetyLevel.moderate;
				break;
			case 3:
				ret = JinxConstants.SafetyLevel.restricted;
				break;
			default:
				ret = null;
				break;
		}
		return  ret;
	}


	public static void close(InputStream in) {
		if (in != null) {
			try {
				in.close();
			} catch (Exception e) {
				// ignore
			}
		}
	}

	public static void close(Reader in) {
		if (in != null) {
			try {
				in.close();
			} catch (Exception e) {
				// ignore
			}
		}
	}

	public static void close(OutputStream out) {
		if (out != null) {
			try {
				out.close();
			} catch (Exception e) {
				// ignore
			}
		}
	}

	public static void close(Writer writer) {
		if (writer != null) {
			try {
				writer.close();
			} catch (Exception e) {
				// ignore
			}
		}
	}
}
