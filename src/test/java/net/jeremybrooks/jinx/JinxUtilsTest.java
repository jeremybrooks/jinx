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

import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

public class JinxUtilsTest {

    @Test
    public void testValidateParams() throws Exception {
        String x = "whatever";
        List<String> list = new ArrayList<String>();
        list.add(x);
        JinxUtils.validateParams(x);
        JinxUtils.validateParams(list);
        JinxUtils.validateParams(x, list);
        Exception exception = null;
        Exception exception1 = null;

        try {
            JinxUtils.validateParams("", null);
            fail();
        } catch (Exception e) {
            exception = e;
        }
        try {
            list.add(null);
            JinxUtils.validateParams(list);
            fail();
        } catch (Exception e) {
            exception1 = e;
        }
        assertNotNull(exception);
        assertNotNull(exception1);
    }

    @Test
    public void testToFlickrPrivacy() throws Exception {
        assertEquals(1, JinxUtils.privacyFilterToFlickrPrivacyFilterId(JinxConstants.PrivacyFilter.privacyPublic));
        assertEquals(2, JinxUtils.privacyFilterToFlickrPrivacyFilterId(JinxConstants.PrivacyFilter.privacyFriends));
        assertEquals(3, JinxUtils.privacyFilterToFlickrPrivacyFilterId(JinxConstants.PrivacyFilter.privacyFamily));
        assertEquals(4, JinxUtils.privacyFilterToFlickrPrivacyFilterId(JinxConstants.PrivacyFilter.privacyFriendsAndFamily));
        assertEquals(5, JinxUtils.privacyFilterToFlickrPrivacyFilterId(JinxConstants.PrivacyFilter.privacyPrivate));
    }

    @Test
    public void testBuildCommaDelimitedList() throws Exception {
        List<String> tags = new ArrayList<String>();
        tags.add("california");
        tags.add("sanfrancisco");
        assertEquals("california,sanfrancisco", JinxUtils.buildCommaDelimitedList(tags));

        tags.clear();
        tags.add("1");
        tags.add("100");
        assertEquals("1,100", JinxUtils.buildCommaDelimitedList(tags));
    }

    @Test
    public void testNormalizeTags() throws Exception {
        List<String> tags = new ArrayList<String>();
        tags.add("California");
        tags.add("San Francisco");
        tags.add("neon");
        tags.add("Joe's Crab Shack");
        tags.add("Joe's Crab.Shack");
        tags.add("Joe's Cra...b.Sh''''ack");
        tags.add("special !#$;%&'()*+ chars");

        List<String> result = JinxUtils.normalizeTags(tags);
        assertEquals("california", result.get(0));
        assertEquals("sanfrancisco", result.get(1));
        assertEquals("neon", result.get(2));
        assertEquals("joescrabshack", result.get(3));
        assertEquals("joescrabshack", result.get(4));
        assertEquals("joescrabshack", result.get(5));
        assertEquals("specialchars", result.get(6));
    }

    /*
	1 public photos
	2 private photos visible to friends
	3 private photos visible to family
	4 private photos visible to friends & family
	5 completely private photos
	 */
    @Test
    public void testPrivacyFilterToFlickrPrivacyFilterId() throws Exception {
        assertEquals(1, JinxUtils.privacyFilterToFlickrPrivacyFilterId(JinxConstants.PrivacyFilter.privacyPublic));
        assertEquals(2, JinxUtils.privacyFilterToFlickrPrivacyFilterId(JinxConstants.PrivacyFilter.privacyFriends));
        assertEquals(3, JinxUtils.privacyFilterToFlickrPrivacyFilterId(JinxConstants.PrivacyFilter.privacyFamily));
        assertEquals(4, JinxUtils.privacyFilterToFlickrPrivacyFilterId(JinxConstants.PrivacyFilter.privacyFriendsAndFamily));
        assertEquals(5, JinxUtils.privacyFilterToFlickrPrivacyFilterId(JinxConstants.PrivacyFilter.privacyPrivate));
        assertEquals(-1, JinxUtils.privacyFilterToFlickrPrivacyFilterId(null));
    }

    @Test
    public void testFlickrPrivacyFilterIdToPrivacyFilter() throws Exception {
        assertEquals(JinxConstants.PrivacyFilter.privacyPublic, JinxUtils.flickrPrivacyFilterIdToPrivacyFilter(1));
        assertEquals(JinxConstants.PrivacyFilter.privacyFriends, JinxUtils.flickrPrivacyFilterIdToPrivacyFilter(2));
        assertEquals(JinxConstants.PrivacyFilter.privacyFamily, JinxUtils.flickrPrivacyFilterIdToPrivacyFilter(3));
        assertEquals(JinxConstants.PrivacyFilter.privacyFriendsAndFamily, JinxUtils.flickrPrivacyFilterIdToPrivacyFilter(4));
        assertEquals(JinxConstants.PrivacyFilter.privacyPrivate, JinxUtils.flickrPrivacyFilterIdToPrivacyFilter(5));
        assertNull(JinxUtils.flickrPrivacyFilterIdToPrivacyFilter(9));
    }

    @Test
    public void testFlickrPermsIdToPerms() throws Exception {
        assertEquals(JinxConstants.Perms.nobody, JinxUtils.flickrPermsIdToPerms(0));
        assertEquals(JinxConstants.Perms.friendsAndFamily, JinxUtils.flickrPermsIdToPerms(1));
        assertEquals(JinxConstants.Perms.contacts, JinxUtils.flickrPermsIdToPerms(2));
        assertEquals(JinxConstants.Perms.everybody, JinxUtils.flickrPermsIdToPerms(3));
        assertNull(JinxUtils.flickrPermsIdToPerms(5));
    }

    @Test
    public void testPermsToFlickrPermsId() throws Exception {
        assertEquals(0, JinxUtils.permsToFlickrPermsId(JinxConstants.Perms.nobody));
        assertEquals(1, JinxUtils.permsToFlickrPermsId(JinxConstants.Perms.friendsAndFamily));
        assertEquals(2, JinxUtils.permsToFlickrPermsId(JinxConstants.Perms.contacts));
        assertEquals(3, JinxUtils.permsToFlickrPermsId(JinxConstants.Perms.everybody));
        assertEquals(-1, JinxUtils.permsToFlickrPermsId(null));
    }

    @Test
    public void testSortOrderToString() throws Exception {
        assertEquals(null, JinxUtils.sortOrderToString(null));
        assertEquals("date-posted-asc", JinxUtils.sortOrderToString(JinxConstants.SortOrder.date_posted_asc));
        assertEquals("date-posted-desc", JinxUtils.sortOrderToString(JinxConstants.SortOrder.date_posted_desc));
        assertEquals("date-taken-asc", JinxUtils.sortOrderToString(JinxConstants.SortOrder.date_taken_asc));
        assertEquals("date-taken-desc", JinxUtils.sortOrderToString(JinxConstants.SortOrder.date_taken_desc));
        assertEquals("interestingness-asc", JinxUtils.sortOrderToString(JinxConstants.SortOrder.interestingness_asc));
        assertEquals("interestingness-desc", JinxUtils.sortOrderToString(JinxConstants.SortOrder.interestingness_desc));
        assertEquals("relevance", JinxUtils.sortOrderToString(JinxConstants.SortOrder.relevance));
    }

    @Test
    public void testStringToSortOrder() throws Exception {
        assertEquals(null, JinxUtils.stringToSortOrder(null));
        assertEquals(JinxConstants.SortOrder.date_posted_asc, JinxUtils.stringToSortOrder("date-posted-asc"));
        assertEquals(JinxConstants.SortOrder.date_posted_desc, JinxUtils.stringToSortOrder("date-posted-desc"));
        assertEquals(JinxConstants.SortOrder.date_taken_asc, JinxUtils.stringToSortOrder("date-taken-asc"));
        assertEquals(JinxConstants.SortOrder.date_taken_desc, JinxUtils.stringToSortOrder("date-taken-desc"));
        assertEquals(JinxConstants.SortOrder.interestingness_asc, JinxUtils.stringToSortOrder("interestingness-asc"));
        assertEquals(JinxConstants.SortOrder.interestingness_desc, JinxUtils.stringToSortOrder("interestingness-desc"));
        assertEquals(JinxConstants.SortOrder.relevance, JinxUtils.stringToSortOrder("relevance"));
    }

    /*
	Content Type setting:
	    1 for photos only.
	    2 for screenshots only.
	    3 for 'other' only.
	    4 for photos and screenshots.
	    5 for screenshots and 'other'.
	    6 for photos and 'other'.
	    7 for photos, screenshots, and 'other' (all).
	*/
    @Test
    public void testContentTypeToFlickrContentTypeId() throws Exception {
        assertEquals(1, JinxUtils.contentTypeToFlickrContentTypeId(JinxConstants.ContentType.photo));
        assertEquals(2, JinxUtils.contentTypeToFlickrContentTypeId(JinxConstants.ContentType.screenshot));
        assertEquals(3, JinxUtils.contentTypeToFlickrContentTypeId(JinxConstants.ContentType.other));
        assertEquals(4, JinxUtils.contentTypeToFlickrContentTypeId(JinxConstants.ContentType.photos_and_screenshots));
        assertEquals(5, JinxUtils.contentTypeToFlickrContentTypeId(JinxConstants.ContentType.screenshots_and_other));
        assertEquals(6, JinxUtils.contentTypeToFlickrContentTypeId(JinxConstants.ContentType.photos_and_other));
        assertEquals(7, JinxUtils.contentTypeToFlickrContentTypeId(JinxConstants.ContentType.all));
        assertEquals(-1, JinxUtils.contentTypeToFlickrContentTypeId(null));
    }

    @Test
    public void testFlickrContentTypeIdToContentType() throws Exception {
        assertEquals(JinxConstants.ContentType.photo, JinxUtils.flickrContentTypeIdToContentType(1));
        assertEquals(JinxConstants.ContentType.screenshot, JinxUtils.flickrContentTypeIdToContentType(2));
        assertEquals(JinxConstants.ContentType.other, JinxUtils.flickrContentTypeIdToContentType(3));
        assertEquals(JinxConstants.ContentType.photos_and_screenshots, JinxUtils.flickrContentTypeIdToContentType(4));
        assertEquals(JinxConstants.ContentType.screenshots_and_other, JinxUtils.flickrContentTypeIdToContentType(5));
        assertEquals(JinxConstants.ContentType.photos_and_other, JinxUtils.flickrContentTypeIdToContentType(6));
        assertEquals(JinxConstants.ContentType.all, JinxUtils.flickrContentTypeIdToContentType(7));
        assertNull(JinxUtils.flickrContentTypeIdToContentType(33));
    }

    /*
	1 for safe.
    2 for moderate.
    3 for restricted.
	 */
    @Test
    public void testSafetyLevelToFlickrSafteyLevelId() throws Exception {
        assertEquals(1, JinxUtils.safetyLevelToFlickrSafteyLevelId(JinxConstants.SafetyLevel.safe));
        assertEquals(2, JinxUtils.safetyLevelToFlickrSafteyLevelId(JinxConstants.SafetyLevel.moderate));
        assertEquals(3, JinxUtils.safetyLevelToFlickrSafteyLevelId(JinxConstants.SafetyLevel.restricted));
        assertEquals(-1, JinxUtils.safetyLevelToFlickrSafteyLevelId(null));
    }

    @Test
    public void testFlickrSafetyLevelIdToSafetyLevel() throws Exception {
        assertEquals(JinxConstants.SafetyLevel.safe, JinxUtils.flickrSafetyLevelIdToSafetyLevel(1));
        assertEquals(JinxConstants.SafetyLevel.moderate, JinxUtils.flickrSafetyLevelIdToSafetyLevel(2));
        assertEquals(JinxConstants.SafetyLevel.restricted, JinxUtils.flickrSafetyLevelIdToSafetyLevel(3));
        assertNull(JinxUtils.flickrSafetyLevelIdToSafetyLevel(0));
    }


    /*
    0, not defined.
    1, indoors.
    2, outdoors.
	 */
    @Test
    public void testGeoContextToFlickrContextId() throws Exception {
        assertEquals(0, JinxUtils.geoContextToFlickrContextId(JinxConstants.GeoContext.not_defined));
        assertEquals(1, JinxUtils.geoContextToFlickrContextId(JinxConstants.GeoContext.indoors));
        assertEquals(2, JinxUtils.geoContextToFlickrContextId(JinxConstants.GeoContext.outdoors));
        assertEquals(-1, JinxUtils.geoContextToFlickrContextId(null));
    }

    @Test
    public void testFlickContextIdToGeoContext() throws Exception {
        assertEquals(JinxConstants.GeoContext.not_defined, JinxUtils.flickrContextIdToGeoContext(0));
        assertEquals(JinxConstants.GeoContext.indoors, JinxUtils.flickrContextIdToGeoContext(1));
        assertEquals(JinxConstants.GeoContext.outdoors, JinxUtils.flickrContextIdToGeoContext(2));
        assertNull(JinxUtils.flickrContextIdToGeoContext(33));
    }

    @Test
    public void testParseMySqlDatetimeToDate() throws Exception {
        Date d = JinxUtils.parseMySqlDatetimeToDate("2004-11-29 16:01:26");
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(d);
        assertEquals(2004, cal.get(Calendar.YEAR));
        assertEquals(10, cal.get(Calendar.MONTH));    // Calendar.MONTH starts at 0; 10 is November
        assertEquals(29, cal.get(Calendar.DAY_OF_MONTH));
        assertEquals(16, cal.get(Calendar.HOUR_OF_DAY));
        assertEquals(1, cal.get(Calendar.MINUTE));
        assertEquals(26, cal.get(Calendar.SECOND));

        assertNull(JinxUtils.parseMySqlDatetimeToDate(null));
    }

    @Test
    public void testFormatDateAsYMD() throws Exception {
        assertEquals("", JinxUtils.formatDateAsYMD(null));

        GregorianCalendar cal = new GregorianCalendar();
        cal.set(Calendar.YEAR, 1999);
        cal.set(Calendar.MONTH, 3);        // Calendar.MONTH starts at 0; 3 is April
        cal.set(Calendar.DAY_OF_MONTH, 3);

        assertEquals("1999-04-03", JinxUtils.formatDateAsYMD(cal.getTime()));
    }

    @Test
    public void testFormatDateAsUnixTimestamp() throws Exception {
        GregorianCalendar cal = new GregorianCalendar();
        cal.clear();
        cal.set(Calendar.YEAR, 1999);
        cal.set(Calendar.MONTH, 3);        // Calendar.MONTH starts at 0; 3 is April
        cal.set(Calendar.DAY_OF_MONTH, 3);
        cal.set(Calendar.HOUR_OF_DAY, 19);
        cal.set(Calendar.MINUTE, 33);
        cal.set(Calendar.SECOND, 44);

        assertEquals("923196824", JinxUtils.formatDateAsUnixTimestamp(cal.getTime()));

        assertEquals("", JinxUtils.formatDateAsUnixTimestamp(null));
    }

    @Test
    public void testParseTimestampToDate() throws Exception {
        String timestamp = "923196824";
        Date d = JinxUtils.parseTimestampToDate(timestamp);
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(d);
        assertEquals(1999, cal.get(Calendar.YEAR));
        assertEquals(3, cal.get(Calendar.MONTH));        // Calendar.MONTH starts at 0; 3 is April
        assertEquals(3, cal.get(Calendar.DAY_OF_MONTH));
        assertEquals(19, cal.get(Calendar.HOUR_OF_DAY));
        assertEquals(33, cal.get(Calendar.MINUTE));
        assertEquals(44, cal.get(Calendar.SECOND));

        assertNull(JinxUtils.parseTimestampToDate(null));
        assertNull(JinxUtils.parseTimestampToDate("fjsadkfjka"));
        assertNull(JinxUtils.parseTimestampToDate(""));
    }

    @Test
    public void testFlickrBooleanToBooleanInteger() {
        Integer i = null;
        assertNull(JinxUtils.flickrBooleanToBoolean(i));
        assertTrue(JinxUtils.flickrBooleanToBoolean(new Integer(1)));
        assertFalse(JinxUtils.flickrBooleanToBoolean(new Integer(0)));
        assertFalse(JinxUtils.flickrBooleanToBoolean(new Integer(-1)));
    }

    @Test
    public void testFlickrBooleanToBooleanString() {
        String s = null;
        assertNull(JinxUtils.flickrBooleanToBoolean(s));
        assertTrue(JinxUtils.flickrBooleanToBoolean("true"));
        assertTrue(JinxUtils.flickrBooleanToBoolean("1"));
        assertTrue(JinxUtils.flickrBooleanToBoolean("TRUE"));
        assertTrue(JinxUtils.flickrBooleanToBoolean("trUe"));
        assertTrue(JinxUtils.flickrBooleanToBoolean(" trUe "));
        assertTrue(JinxUtils.flickrBooleanToBoolean("trUe "));
        assertFalse(JinxUtils.flickrBooleanToBoolean(""));
        assertFalse(JinxUtils.flickrBooleanToBoolean("false"));
        assertFalse(JinxUtils.flickrBooleanToBoolean("0"));
        assertFalse(JinxUtils.flickrBooleanToBoolean("FALSE"));
        assertFalse(JinxUtils.flickrBooleanToBoolean("xxxx"));
        assertFalse(JinxUtils.flickrBooleanToBoolean("   "));
        assertFalse(JinxUtils.flickrBooleanToBoolean("1123"));
    }

    @Test
    public void testFlickrMemberTypeIdToEnum() {
        assertEquals(JinxConstants.MemberType.narwhal, JinxUtils.typeIdToMemberType(1));
        assertEquals(JinxConstants.MemberType.member, JinxUtils.typeIdToMemberType(2));
        assertEquals(JinxConstants.MemberType.moderator, JinxUtils.typeIdToMemberType(3));
        assertEquals(JinxConstants.MemberType.admin, JinxUtils.typeIdToMemberType(4));
        assertNull(JinxUtils.typeIdToMemberType(0));
        assertNull(JinxUtils.typeIdToMemberType(null));
    }

    @Test
    public void testMemberTypeEnumToId() {
        assertEquals(new Integer(1), JinxUtils.memberTypeToMemberTypeId(JinxConstants.MemberType.narwhal));
        assertEquals(new Integer(2), JinxUtils.memberTypeToMemberTypeId(JinxConstants.MemberType.member));
        assertEquals(new Integer(3), JinxUtils.memberTypeToMemberTypeId(JinxConstants.MemberType.moderator));
        assertEquals(new Integer(4), JinxUtils.memberTypeToMemberTypeId(JinxConstants.MemberType.admin));
        assertNull(JinxUtils.memberTypeToMemberTypeId(null));
    }

    @Test
    public void testFlickrGroupPrivacyIdToEnum() {
        assertEquals(JinxConstants.GroupPrivacy.group_private, JinxUtils.privacyIdToGroupPrivacyEnum(1));
        assertEquals(JinxConstants.GroupPrivacy.group_invite_only_public, JinxUtils.privacyIdToGroupPrivacyEnum(2));
        assertEquals(JinxConstants.GroupPrivacy.group_open_public, JinxUtils.privacyIdToGroupPrivacyEnum(3));
        assertNull(JinxUtils.privacyIdToGroupPrivacyEnum(5));
        assertNull(JinxUtils.privacyIdToGroupPrivacyEnum(null));
    }

    @Test
    public void testGroupPrivacyEnumToId() {
        assertEquals(new Integer(1), JinxUtils.groupPrivacyEnumToPrivacyId(JinxConstants.GroupPrivacy.group_private));
        assertEquals(new Integer(2), JinxUtils.groupPrivacyEnumToPrivacyId(JinxConstants.GroupPrivacy.group_invite_only_public));
        assertEquals(new Integer(3), JinxUtils.groupPrivacyEnumToPrivacyId(JinxConstants.GroupPrivacy.group_open_public));
        assertNull(JinxUtils.groupPrivacyEnumToPrivacyId(null));
    }
}