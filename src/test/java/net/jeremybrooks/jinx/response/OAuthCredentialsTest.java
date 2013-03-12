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
