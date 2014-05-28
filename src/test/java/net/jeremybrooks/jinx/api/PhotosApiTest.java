package net.jeremybrooks.jinx.api;

import net.jeremybrooks.jinx.Jinx;
import net.jeremybrooks.jinx.JinxConstants;
import net.jeremybrooks.jinx.OAuthAccessToken;
import net.jeremybrooks.jinx.response.Response;
import net.jeremybrooks.jinx.response.photos.AddTags;
import net.jeremybrooks.jinx.response.photos.AllContexts;
import net.jeremybrooks.jinx.response.photos.Photo;
import net.jeremybrooks.jinx.response.photos.PhotosResponse;
import net.jeremybrooks.jinx.response.photos.Tag;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.EnumSet;
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
	private static List<Tag> tagList;
	private static String photoId = "14276354684";
	private static String deletePhotoId = null; // set this to run the delete photo test

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
		tagList = tags.getTagList();
		assertEquals(2, tagList.size());
	}

	@Test
	public void testRemoveTag() throws Exception {
		Response response = photosApi.removeTag(tagList.get(0).getFullTagId());
		assertNotNull(response);
		assertEquals("ok", response.getStat());
		assertEquals(0, response.getCode());
	}

	@Test
	public void testSetTags() throws Exception {
		List<String> tags = new ArrayList<String>();
		tags.add("Tag Added by Jinx tests");
		tags.add("jinxtest");
		tags.add("jinx:type=test");
		Response response = photosApi.setTags(photoId, null);
		assertNotNull(response);
		assertEquals("ok", response.getStat());
		assertEquals(0, response.getCode());

		response = photosApi.setTags(photoId, tags);
		assertNotNull(response);
		assertEquals("ok", response.getStat());
		assertEquals(0, response.getCode());
	}

	@Test
	public void testDelete() throws Exception {
		if (deletePhotoId != null) {
			Response response = photosApi.delete(deletePhotoId);
			assertNotNull(response);
			assertEquals("ok", response.getStat());
			assertEquals(0, response.getCode());
		}
	}

	@Test
	public void testGetAllContexts() throws Exception {
		AllContexts contexts = photosApi.getAllContexts(photoId);
		assertNotNull(contexts);
		assertEquals("ok", contexts.getStat());
		assertEquals(0, contexts.getCode());
		assertNotNull(contexts.getSetList());
		assertNotNull(contexts.getPoolList());
	}

	@Test
	public void testGetContactsPhotos() throws Exception {
		PhotosResponse photosResponse = photosApi.getContactsPhotos(5, false, false, false, null);
		assertNotNull(photosResponse);
		assertNotNull(photosResponse.getPhotoList());
		assertEquals(5, photosResponse.getPhotoList().size());

		photosResponse = photosApi.getContactsPhotos(5, false, false, false, EnumSet.of(JinxConstants.PhotoExtras.date_taken, JinxConstants.PhotoExtras.url_sq));
		assertNotNull(photosResponse);
		assertNotNull(photosResponse.getPhotoList());
		assertEquals(5, photosResponse.getPhotoList().size());
		for (Photo p : photosResponse.getPhotoList()) {
			assertNotNull(p.getDateTaken());
			assertNotNull(p.getUrlSq());
		}
	}
}
