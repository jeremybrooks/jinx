package net.jeremybrooks.jinx.api;

import net.jeremybrooks.jinx.Jinx;
import net.jeremybrooks.jinx.JinxConstants;
import net.jeremybrooks.jinx.JinxUtils;
import net.jeremybrooks.jinx.OAuthAccessToken;
import net.jeremybrooks.jinx.response.Response;
import net.jeremybrooks.jinx.response.common.Context;
import net.jeremybrooks.jinx.response.photos.AddTags;
import net.jeremybrooks.jinx.response.photos.AllContexts;
import net.jeremybrooks.jinx.response.photos.ExifData;
import net.jeremybrooks.jinx.response.photos.Photo;
import net.jeremybrooks.jinx.response.photos.Photocounts;
import net.jeremybrooks.jinx.response.photos.Photos;
import net.jeremybrooks.jinx.response.photos.Tag;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
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
		Photos photos = photosApi.getContactsPhotos(5, false, false, false, null);
		assertNotNull(photos);
		assertEquals("ok", photos.getStat());
		assertEquals(0, photos.getCode());
		assertNotNull(photos.getPhotoList());
		assertEquals(5, photos.getPhotoList().size());

		photos = photosApi.getContactsPhotos(5, false, false, false, EnumSet.of(JinxConstants.PhotoExtras.date_taken, JinxConstants.PhotoExtras.url_sq));
		assertNotNull(photos);
		assertEquals("ok", photos.getStat());
		assertEquals(0, photos.getCode());
		assertNotNull(photos.getPhotoList());
		assertEquals(5, photos.getPhotoList().size());
		for (Photo p : photos.getPhotoList()) {
			assertNotNull(p.getDateTaken());
			assertNotNull(p.getUrlSq());
		}
	}

	@Test
	public void testGetContactsPublicPhotos() throws Exception {
		Photos photos = photosApi.getContactsPublicPhotos(userId, 5, true, false, true, null);
		assertNotNull(photos);
		assertEquals("ok", photos.getStat());
		assertEquals(0, photos.getCode());
		assertNotNull(photos.getPhotoList());
		assertEquals(5, photos.getPhotoList().size());
		photos = photosApi.getContactsPublicPhotos(userId, 5, false, false, false, EnumSet.of(JinxConstants.PhotoExtras.date_taken, JinxConstants.PhotoExtras.url_sq));
		assertNotNull(photos);
		assertEquals("ok", photos.getStat());
		assertEquals(0, photos.getCode());
		assertNotNull(photos.getPhotoList());
		assertEquals(5, photos.getPhotoList().size());
		for (Photo p : photos.getPhotoList()) {
			assertNotNull(p.getDateTaken());
			assertNotNull(p.getUrlSq());
		}
	}

	@Test
	public void testGetContext() throws Exception {
		Context context = photosApi.getContext(photoId);
		assertNotNull(context);
		assertEquals("ok", context.getStat());
		assertEquals(0, context.getCode());
		assertNotNull(context.getNextphoto());
		assertNotNull(context.getPrevphoto());
	}

	@Test
	public void testGetCounts() throws Exception {
		List<Date> dates = new ArrayList<Date>();
		dates.add(new Date());
		dates.add(JinxUtils.parseYMDToDate("2013-01-01"));
		dates.add(JinxUtils.parseYMDToDate("2012-01-01"));
		dates.add(JinxUtils.parseYMDToDate("2014-01-01"));
		Photocounts photocounts = photosApi.getCounts(dates, null);
		assertEquals("ok", photocounts.getStat());
		assertEquals(0, photocounts.getCode());
		assertNotNull(photocounts.getPhotocountList());
		assertEquals(3, photocounts.getPhotocountList().size());

		photocounts = photosApi.getCounts(null, dates);
		assertEquals("ok", photocounts.getStat());
		assertEquals(0, photocounts.getCode());
		assertNotNull(photocounts.getPhotocountList());
		assertEquals(3, photocounts.getPhotocountList().size());
	}

	@Test
	public void testGetExif() throws Exception {
		ExifData exifData = photosApi.getExif(photoId, null);
		assertNotNull(exifData);
		assertEquals("ok", exifData.getStat());
		assertEquals(0, exifData.getCode());
		assertEquals(photoId, exifData.getPhotoId());
		List<ExifData.Exif> exifList = exifData.getExifList();
		assertNotNull(exifList);
		assertTrue(exifList.size() > 0);
	}
}
