package net.jeremybrooks.jinx.api;

import net.jeremybrooks.jinx.Jinx;
import net.jeremybrooks.jinx.OAuthAccessToken;
import net.jeremybrooks.jinx.response.photos.AddTags;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

/**
 * @author Jeremy Brooks
 */
public class PhotosApiTest {
	private static PhotosApi photosApi;
	private static String photoId = "14276354684";

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
		photosApi = new PhotosApi(new Jinx(p.getProperty("flickr.key"), p.getProperty("flickr.secret"), oAuthAccessToken));
	}


	@Test
	public void testAddTags() throws Exception {
		List<String> newTags = new ArrayList<String>();
		newTags.add("tag" + System.currentTimeMillis());
		newTags.add("multiword tag  " + System.currentTimeMillis());
		AddTags tags = photosApi.addTags(photoId, newTags);
		assertNotNull(tags);
		assertEquals("ok", tags.getStat());
		assertEquals(0, tags.getCode());
		assertNotNull(tags.getTagList());
		assertEquals(2, tags.getTagList().size());
	}
}
