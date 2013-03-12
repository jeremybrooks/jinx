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
public class OAuthExchangedTokenTest {

	@Test
		public void testFromJson() throws Exception {
			Gson gson = new Gson();
			String json = "{ \"auth\": { \n" +
					"    \"access_token\": { \"oauth_token\": \"72157632940281881-6db7bec1c46b67b2\", \"oauth_token_secret\": \"7267af804d085953\" } }, \"stat\": \"ok\" }";
			OAuthExchangedToken auth = gson.fromJson(json, OAuthExchangedToken.class);

			assertEquals("ok", auth.getStat());
			assertEquals("72157632940281881-6db7bec1c46b67b2", auth.getOAuthToken());
			assertEquals("7267af804d085953", auth.getOAuthTokenSecret());
		}
}
