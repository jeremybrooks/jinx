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
