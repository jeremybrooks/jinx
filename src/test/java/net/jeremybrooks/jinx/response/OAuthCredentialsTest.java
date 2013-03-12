/*
 * Jinx is Copyright 2010-2013 by Jeremy Brooks and Contributors
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
package net.jeremybrooks.jinx.response;

import com.google.gson.Gson;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * @author Jeremy Brooks
 */
public class OAuthCredentialsTest {

	@Test
		public void testFromJson() throws Exception {
			Gson gson = new Gson();
			String json = "{ \"oauth\": { \n" +
					"\t    \"token\": { \"_content\": \"72157632940881881-6db7bec3c46b67b2\" }, \n" +
					"\t    \"perms\": { \"_content\": \"delete\" }, \n" +
					"\t    \"user\": { \"nsid\": \"85853333@N00\", \"username\": \"Jeremy Brooks\", \"fullname\": \"Jeremy Brooks\" } }, \"stat\": \"ok\" }";
		System.out.println(json);
			OAuthCredentials oAuthCredentials = gson.fromJson(json, OAuthCredentials.class);

			assertEquals("ok", oAuthCredentials.getStat());
			assertEquals("72157632940881881-6db7bec3c46b67b2", oAuthCredentials.getOauthToken());
			assertEquals("delete", oAuthCredentials.getPerms());
			assertEquals("85853333@N00", oAuthCredentials.getNsid());
			assertEquals("Jeremy Brooks", oAuthCredentials.getUsername());
			assertEquals("Jeremy Brooks", oAuthCredentials.getFullname());
		}
}
