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
import java.util.ArrayList;
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


	private static SimpleDateFormat formatter;
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
		for (byte aByte : b) {
			// look up high nibble char
			sb.append(hexChar[(aByte & 0xf0) >>> 4]);

			// look up low nibble char
			sb.append(hexChar[aByte & 0x0f]);
		}
		return sb.toString();
	}


	/**
	 * Validate parameters.
	 *
	 * Objects:
	 * <ul>
	 *     <li>Must not be null</li>
	 *     <li>List objects must not contain null</li>
	 *     <li>Strings cannot be empty</li>
	 * </ul>
	 *
	 * @param params variable list of objects to check.
	 * @throws JinxException if any parameter fails validation.
	 */
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
				if (((String) o).trim().length() == 0) {
					throw new JinxException("String cannot be empty.");
				}
			}
		}
	}


	/**
	 * Test a string for empty or null.
	 *
	 * @param s string to test.
	 * @return true if the string is null or has a trimmed length of 0.
	 */
	public static boolean isNullOrEmpty(String s) {
		return s == null || s.trim().length() == 0;
	}

	/**
	 * Test a Collection for empty or null.
	 *
	 * @param collection collection object to test.
	 * @return true if the collection is null or if the collection has no objects.
	 */
	public static boolean isNullOrEmpty(Collection collection) {
		return collection == null || collection.size() == 0;
	}

	/**
	 * Convert a collection into a comma delimited String.
	 *
	 * Each object in the collection will be converted to a trimmed String, and commas will be used to
	 * separate each object.
	 *
	 * @param collection collection to convert.
	 * @return comma delimited string, or null if the collection is null or empty.
	 */
	public static String buildCommaDelimitedList(Collection collection) {
		if (isNullOrEmpty(collection)) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		for (Object o : collection) {
			sb.append(o.toString().trim()).append(',');
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}

	/**
	 * Normalize a List of Flickr tags.
	 *
	 * This method takes a list of tags and converts them to the normalized representation that Flickr uses (at least,
	 * as close to the normalized version as could be figured out).
	 *
	 * Each String in the list will have the following characters removed: !#$%&'() *+.,';
	 * The String is then converted to lowercase and added to a new List.
	 *
	 * Normalization is done primarily so that tags containing spaces do not have to be surrounded with double quotes
	 * before sending to the Flickr API. It is easier to normalize than to surround everything with double quotes, and
	 * this also results is slightly smaller (and more readable) calls to the Flickr API.
	 *
	 * @param list Flickr tags to normalize.
	 * @return new List containing normalized tags.
	 */
	public static List<String> normalizeTags(List<String> list) {
		if (isNullOrEmpty(list)) {
			return null;
		}
		List<String> tmp = new ArrayList<String>();
		for (String s : list) {
			tmp.add(s.replaceAll("[!#$%&'()*+\\.,'; ]", "").toLowerCase());
		}
		return tmp;
	}

	/**
	 * Convert a {@link net.jeremybrooks.jinx.JinxConstants.PrivacyFilter} enum into the corresponding Flickr
	 * privacy filter id.
	 *
	 * @param privacyFilter privacy filter enum to convert.
	 * @return Flickr privacy filter id, or 0 if argument is null.
	 */
	public static int privacyFilterToFlickrPrivacyFilterId(JinxConstants.PrivacyFilter privacyFilter) {
		if (privacyFilter == null) {
			return -1;
		}
		int level;
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
				level = -1;
				break;
		}
		return level;
	}

	/**
	 * Convert a numeric Flickr privacy filter into the corresponding {@link net.jeremybrooks.jinx.JinxConstants.PrivacyFilter}
	 *
	 * @param privacyFilterId numeric Flickr privacy filter id.
	 * @return corresponding PrivacyFilter enum, or null if the argument is not a valid privacy filter id.
	 */
	public static JinxConstants.PrivacyFilter flickrPrivacyFilterIdToPrivacyFilter(int privacyFilterId) {
		JinxConstants.PrivacyFilter ret;
		switch (privacyFilterId) {
			case 1:
				ret = JinxConstants.PrivacyFilter.privacyPublic;
				break;
			case 2:
				ret = JinxConstants.PrivacyFilter.privacyFriends;
				break;
			case 3:
				ret = JinxConstants.PrivacyFilter.privacyFamily;
				break;
			case 4:
				ret = JinxConstants.PrivacyFilter.privacyFriendsAndFamily;
				break;
			case 5:
				ret = JinxConstants.PrivacyFilter.privacyPrivate;
				break;
			default:
				ret = null;
				break;
		}
		return ret;
	}

	/**
	 * Convert a numeric Flickr permission id to corresponding {@link net.jeremybrooks.jinx.JinxConstants.Perms} enum.
	 *
	 * @param id Flickr permission id.
	 * @return corresponding Perms, or null if the parameter is not a valid Flickr perms id.
	 */
	public static JinxConstants.Perms flickrPermsIdToPerms(int id) {
		JinxConstants.Perms perms;
		switch (id) {
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


	/**
	 * Convert a {@link net.jeremybrooks.jinx.JinxConstants.Perms} enum to the corresponding Flickr perms id.
	 *
	 * @param perms Perms enum to convert.
	 * @return Flickr perms id, or -1 if the parameter is null.
	 */
	public static int permsToFlickrPermsId(JinxConstants.Perms perms) {
		if (perms == null) {
			return -1;
		}
		int id;
		switch (perms) {
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
				id = -1;
				break;
		}
		return id;
	}

	/**
	 * Convert a {@link net.jeremybrooks.jinx.JinxConstants.SortOrder} enum to the equivalent Flickr sort order string.
	 *
	 * @param sortOrder sort order to convert.
	 * @return equivalent Flickr sort order string, or null if the parameter is null.
	 */
	public static String sortOrderToString(JinxConstants.SortOrder sortOrder) {
		if (sortOrder == null) {
			return null;
		}
		return sortOrder.toString().replaceAll("_", "-");
	}

	/**
	 * Convert a Flickr sort order string to the equivalent {@link net.jeremybrooks.jinx.JinxConstants.SortOrder} enum.
	 *
	 * @param sortOrderString Flickr sort order string.
	 * @return SortOrder enum, or null if the parameter is null.
	 */
	public static JinxConstants.SortOrder stringToSortOrder(String sortOrderString) {
		if (JinxUtils.isNullOrEmpty(sortOrderString)) {
			return null;
		}
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

	/**
	 * Convert  {@link net.jeremybrooks.jinx.JinxConstants.ContentType} enum to the equivalent Flickr content type id.
	 *
	 * @param contentType ContentType enum to convert.
	 * @return equivalent Flickr content type id, or -1 if the parameter is null.
	 */
	public static int contentTypeToFlickrContentTypeId(JinxConstants.ContentType contentType) {
		if (contentType == null) {
			return -1;
		}
		int ret;
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
			case photos_and_screenshots:
				ret = 4;
				break;
			case screenshots_and_other:
				ret = 5;
				break;
			case photos_and_other:
				ret = 6;
				break;
			case all:
				ret = 7;
				break;
			default:
				ret = -1;
				break;
		}
		return ret;
	}

	/**
	 * Convert a Flickr content type id to the eqivalent {@link net.jeremybrooks.jinx.JinxConstants.ContentType} enum.
	 *
	 * @param type Flickr type id to convert.
	 * @return equivalent ContentType enum, or null if the parameter is not a valid Flickr content type id.
	 */
	public static JinxConstants.ContentType flickrContentTypeIdToContentType(int type) {
		JinxConstants.ContentType ret;
		switch (type) {
			case 1:
				ret = JinxConstants.ContentType.photo;
				break;
			case 2:
				ret = JinxConstants.ContentType.screenshot;
				break;
			case 3:
				ret = JinxConstants.ContentType.other;
				break;
			case 4:
				ret = JinxConstants.ContentType.photos_and_screenshots;
				break;
			case 5:
				ret = JinxConstants.ContentType.screenshots_and_other;
				break;
			case 6:
				ret = JinxConstants.ContentType.photos_and_other;
				break;
			case 7:
				ret = JinxConstants.ContentType.all;
				break;
			default:
				ret = null;
				break;
		}
		return ret;
	}

	/**
	 * Convert a {@link net.jeremybrooks.jinx.JinxConstants.SafetyLevel} enum to the corresponding Flickr safety level id.
	 *
	 * @param safetyLevel safety level to convert.
	 * @return corresponding Flickr safety level id, or -1 if the parameter is null.
	 */
	public static int safetyLevelToFlickrSafteyLevelId(JinxConstants.SafetyLevel safetyLevel) {
		if (safetyLevel == null) {
			return -1;
		}
		int ret;
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
				ret = -1;
				break;
		}
		return ret;
	}

	/**
	 * Convert a Flickr safety level id to the corresponding {@link net.jeremybrooks.jinx.JinxConstants.SafetyLevel} enum.
	 *
	 * @param level Flickr safety level id to convert.
	 * @return corresponding SafetyLevel enum, or null if the parameter is not a valid Flickr safety level id.
	 */
	public static JinxConstants.SafetyLevel flickrSafetyLevelIdToSafetyLevel(int level) {
		JinxConstants.SafetyLevel ret;
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
		return ret;
	}

	/**
	 * Convert a {@link net.jeremybrooks.jinx.JinxConstants.GeoContext} to the corresponding Flickr geo context id.
	 *
	 * @param geoContext GeoContext enum to convert.
	 * @return corresponding Flickr geo context id, or -1 if the parameter is null.
	 */
	public static int geoContextToFlickrContextId(JinxConstants.GeoContext geoContext) {
		if (geoContext == null) {
			return -1;
		}
		int ret;
		switch (geoContext) {
			case not_defined:
				ret = 0;
				break;
			case indoors:
				ret = 1;
				break;
			case outdoors:
				ret = 2;
				break;
			default:
				ret = -1;
				break;
		}
		return ret;
	}

	/**
	 * Convert a Flickr geo context id to the corresponding {@link net.jeremybrooks.jinx.JinxConstants.GeoContext} enum.
	 *
	 * @param contextId Flickr geo context id to convert.
	 * @return corresponding GeoContext enum, or null if the parameter is not a valid Flickr geo context id.
	 */
	public static JinxConstants.GeoContext flickrContextIdToGeoContext(int contextId) {
		JinxConstants.GeoContext ret;
		switch (contextId) {
			case 0:
				ret = JinxConstants.GeoContext.not_defined;
				break;
			case 1:
				ret = JinxConstants.GeoContext.indoors;
				break;
			case 2:
				ret = JinxConstants.GeoContext.outdoors;
				break;
			default:
				ret = null;
				break;
		}
		return ret;
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
