package net.jeremybrooks.jinx.api;

import net.jeremybrooks.jinx.Jinx;
import net.jeremybrooks.jinx.OAuthAccessToken;
import net.jeremybrooks.jinx.response.ActivityResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

/**
 * @author Jeremy Brooks
 */
public class ActivityApiTest {
	private static ActivityApi activityApi;

	@BeforeClass
	public static void beforeClass() throws Exception {
		Properties p = new Properties();
		p.load(OAuthApiTest.class.getResourceAsStream("/secret.properties"));

		String filename = p.getProperty("path.to.oauth.token");
		assertNotNull(filename);

		File file = new File(filename);
		assertTrue(file.exists());

		OAuthAccessToken oAuthAccessToken = new OAuthAccessToken();
		oAuthAccessToken.load(new FileInputStream(file));

		assertNotNull(oAuthAccessToken);

		activityApi = new ActivityApi(new Jinx(p.getProperty("flickr.key"), p.getProperty("flickr.secret"), oAuthAccessToken));
	}

	@Test
	public void testUserComments() throws Exception {
		ActivityResponse response = activityApi.userComments(10, 1);
		assertNotNull(response);
		assertEquals("ok", response.getStat());
	}

	@Test
	public void testUserPhotos() throws Exception {
		ActivityResponse response = activityApi.userPhotos("2d", 10, 1);
		assertNotNull(response);
		assertEquals("ok", response.getStat());
	}
}
