/*
 * Jinx is Copyright 2010-2018 by Jeremy Brooks and Contributors
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

package net.jeremybrooks.jinx.response.contacts;

import com.google.gson.Gson;
import net.jeremybrooks.jinx.response.activity.ActivityResponseTest;
import org.junit.Test;

import java.io.InputStreamReader;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Jeremy Brooks
 */
public class ContactsTest {

	@Test
	public void testParseGetList() throws Exception {
		InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/contacts/sample_get_list.json"));
		Contacts contacts = new Gson().fromJson(reader, Contacts.class);
		reader.close();
		assertNotNull(contacts);
		assertEquals("ok", contacts.getStat());
		assertEquals(0, contacts.getCode());
		assertEquals(1, (int) contacts.getPage());
		assertEquals(1, (int) contacts.getPages());
		assertEquals(1000, (int) contacts.getPerPage());
		assertEquals(4, (int) contacts.getTotal());
		List<Contacts.Contact> contactList = contacts.getContactList();
		assertNotNull(contactList);
		assertEquals(4, contactList.size());
		Contacts.Contact c = contactList.get(0);
		assertNotNull(c);
		assertEquals("38039613@N08", c.getUserId());
		assertEquals("dovetaildw", c.getUsername());
		assertEquals("3557", c.getIconServer());
		assertEquals("4", c.getIconFarm());
		assertFalse(c.isIgnored());
		assertFalse(c.isRevIgnored());
		assertEquals("Dave Williams", c.getRealName());
		assertFalse(c.isFriend());
		assertFalse(c.isFamily());
		assertEquals("", c.getPathAlias());
		assertEquals("Northborough, MA", c.getLocation());
		assertNull(c.getPhotosUploaded());
	}

	@Test
	public void testParseGetPublicList() throws Exception {
		InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/contacts/sample_get_public_list.json"));
		Contacts contacts = new Gson().fromJson(reader, Contacts.class);
		reader.close();
		assertNotNull(contacts);
		assertEquals("ok", contacts.getStat());
		assertEquals(0, contacts.getCode());
		assertEquals(1, (int) contacts.getPage());
		assertEquals(219, (int) contacts.getPages());
		assertEquals(10, (int) contacts.getPerPage());
		assertEquals(2185, (int) contacts.getTotal());
		List<Contacts.Contact> contactList = contacts.getContactList();
		assertNotNull(contactList);
		assertEquals(10, contactList.size());
		Contacts.Contact c = contactList.get(0);
		assertNotNull(c);
		assertEquals("34350870@N03", c.getUserId());
		assertEquals("- POD -", c.getUsername());
		assertEquals("3706", c.getIconServer());
		assertEquals("4", c.getIconFarm());
		assertFalse(c.isIgnored());
		assertFalse(c.isRevIgnored());
		assertNull(c.getRealName());
		assertNull(c.isFriend());
		assertNull(c.isFamily());
		assertNull(c.getPathAlias());
		assertNull(c.getLocation());
		assertNull(c.getPhotosUploaded());
	}

	@Test
	public void testParseGetTaggingSuggestions() throws Exception {
		InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/contacts/sample_get_tagging_suggestions.json"));
		Contacts contacts = new Gson().fromJson(reader, Contacts.class);
		reader.close();
		assertNotNull(contacts);
		assertEquals("ok", contacts.getStat());
		assertEquals(0, contacts.getCode());
		assertEquals(1, (int) contacts.getPage());
		assertEquals(1, (int) contacts.getPages());
		assertEquals(6, (int) contacts.getPerPage());
		assertEquals(3, (int) contacts.getTotal());
		List<Contacts.Contact> contactList = contacts.getContactList();
		assertNotNull(contactList);
		assertEquals(3, contactList.size());
		Contacts.Contact c = contactList.get(0);
		assertNotNull(c);
		assertEquals("40962351@N00", c.getUserId());
		assertEquals("lomokev", c.getUsername());
		assertEquals("8556", c.getIconServer());
		assertEquals("9", c.getIconFarm());
		assertNull(c.isIgnored());
		assertNull(c.isRevIgnored());
		assertEquals("Kevin Meredith", c.getRealName());
		assertFalse(c.isFriend());
		assertFalse(c.isFamily());
		assertEquals("lomokev", c.getPathAlias());
		assertNull(c.getLocation());
		assertNull(c.getPhotosUploaded());
	}

	@Test
	public void testParseGetRecentlyUploaded() throws Exception {
		InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/contacts/sample_get_list_recently_uploaded.json"));
		Contacts contacts = new Gson().fromJson(reader, Contacts.class);
		reader.close();
		assertNotNull(contacts);
		assertEquals("ok", contacts.getStat());
		assertEquals(0, contacts.getCode());
		assertNull(contacts.getPage());
		assertNull(contacts.getPages());
		assertNull(contacts.getPerPage());
		assertEquals(16, (int) contacts.getTotal());
		List<Contacts.Contact> contactList = contacts.getContactList();
		assertNotNull(contactList);
		assertEquals(16, contactList.size());
		Contacts.Contact c = contactList.get(0);
		assertNotNull(c);

		assertEquals("51035555243@N01", c.getUserId());
		assertEquals("Thomas Hawk", c.getUsername());
		assertEquals("7370", c.getIconServer());
		assertEquals("8", c.getIconFarm());
		assertNull(c.isIgnored());
		assertNull(c.isRevIgnored());
		assertEquals("Thomas Hawk", c.getRealName());
		assertTrue(c.isFriend());
		assertFalse(c.isFamily());
		assertEquals("thomashawk", c.getPathAlias());
		assertNull(c.getLocation());
		assertEquals(1, (int) c.getPhotosUploaded());
	}
}
