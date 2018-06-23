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

package net.jeremybrooks.jinx.api;

import net.jeremybrooks.jinx.Jinx;
import net.jeremybrooks.jinx.JinxConstants;
import net.jeremybrooks.jinx.OAuthAccessToken;
import net.jeremybrooks.jinx.response.contacts.Contacts;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Jeremy Brooks
 */
public class ContactsApiTest {
	private static ContactsApi contactsApi;

	private static String userId = "85853333@N00";

	@BeforeClass
	public static void beforeClass() throws Exception {
		Properties p = new Properties();
		p.load(OAuthApiTest.class.getResourceAsStream("/response/auth/secret.properties"));

		String filename = p.getProperty("path.to.oauth.token");
		assertNotNull(filename);

		File file = new File(filename);
		assertTrue(file.exists());

		OAuthAccessToken oAuthAccessToken = new OAuthAccessToken();
		oAuthAccessToken.load(new FileInputStream(file));

		assertNotNull(oAuthAccessToken);

		contactsApi = new ContactsApi(new Jinx(p.getProperty("flickr.key"), p.getProperty("flickr.secret"), oAuthAccessToken));
	}


	@Test
	public void testGetList() throws Exception {
		Contacts contacts = contactsApi.getList(null, 0, 0, JinxConstants.ContactSort.time);
		assertNotNull(contacts);
		assertNotNull(contacts.getContactList());
		assertTrue(contacts.getContactList().size() > 0);
		// Jeremy Brooks should be in there somewhere...
		boolean found = false;
		for (Contacts.Contact c : contacts.getContactList()) {
			if (c.getRealName().equalsIgnoreCase("Jeremy Brooks")) {
				found = true;
			}
		}
		assertTrue(found);
	}

	@Test
	public void testGetListRecentlyUploaded() throws Exception {
		Contacts contacts = contactsApi.getListRecentlyUploaded(null, null);
		assertNotNull(contacts);
		// not much else to test
		// this method can return empty pretty often, depending on when people uploaded photos
	}

	@Test
	public void testGetPublicList() throws Exception {
		Contacts contacts = contactsApi.getPublicList(userId, 1, 10);
		assertNotNull(contacts);
		assertNotNull(contacts.getContactList());
		assertEquals(10, contacts.getContactList().size());
	}

	@Test
	public void testGetTaggingSuggestions() throws Exception {
		Contacts contacts = contactsApi.getPublicList(userId, 1, 10);
		assertNotNull(contacts);
		assertNotNull(contacts.getContactList());
		assertTrue(contacts.getContactList().size() > 0);
	}
}
