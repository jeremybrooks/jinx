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
		assertEquals(1, JinxUtils.toFlickrPrivacy(JinxConstants.PrivacyFilter.privacyPublic));
		assertEquals(2, JinxUtils.toFlickrPrivacy(JinxConstants.PrivacyFilter.privacyFriends));
		assertEquals(3, JinxUtils.toFlickrPrivacy(JinxConstants.PrivacyFilter.privacyFamily));
		assertEquals(4, JinxUtils.toFlickrPrivacy(JinxConstants.PrivacyFilter.privacyFriendsAndFamily));
		assertEquals(5, JinxUtils.toFlickrPrivacy(JinxConstants.PrivacyFilter.privacyPrivate));
	}
}