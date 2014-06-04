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
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.fail;
import static org.junit.Assert.assertNull;

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
			Object o = null;
			JinxUtils.validateParams("", o);
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
}