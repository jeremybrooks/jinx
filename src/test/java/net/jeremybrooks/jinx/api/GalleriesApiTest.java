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

package net.jeremybrooks.jinx.api;

import net.jeremybrooks.jinx.Jinx;
import net.jeremybrooks.jinx.JinxConstants;
import net.jeremybrooks.jinx.JinxUtils;
import net.jeremybrooks.jinx.OAuthAccessToken;
import net.jeremybrooks.jinx.response.Response;
import net.jeremybrooks.jinx.response.common.PrimaryPhotoExtras;
import net.jeremybrooks.jinx.response.galleries.Gallery;
import net.jeremybrooks.jinx.response.galleries.GalleryInfo;
import net.jeremybrooks.jinx.response.galleries.GalleryList;
import net.jeremybrooks.jinx.response.photos.Photo;
import net.jeremybrooks.jinx.response.photos.Photos;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;
import java.util.Properties;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertEquals;

/**
 * @author Jeremy Brooks
 */
public class GalleriesApiTest {

	private static GalleriesApi galleriesApi;

	private static final String TEST_CHINESE_NEON_GALLERY_ID = "124834485-72157644721099547";
	private static final String TEST_PHOTO_IN_GALLERY = "3931840935";
	private static final String USER_ID = "124857539@N03";    // jinxlib

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

		galleriesApi = new GalleriesApi(new Jinx(p.getProperty("flickr.key"), p.getProperty("flickr.secret"), oAuthAccessToken));
	}

	@Test
	public void testCreateAndAddAndEdit() throws Exception {
		String primaryPhoto = "14413967864";    // https://www.flickr.com/photos/jeremybrooks/14413967864/
		String addedPhoto = "14375440841";        // https://www.flickr.com/photos/jeremybrooks/14375440841/
		GalleryInfo galleryInfo = galleriesApi.create("Test Gallery",
				"Created by Jinx automated tests.",
				primaryPhoto,
				true);
		assertNotNull(galleryInfo);
		Gallery g = galleryInfo.getGallery();
		assertNotNull(g);
		assertEquals(primaryPhoto, g.getPrimaryPhotoId());

		// add
		Response r = galleriesApi.addPhoto(g.getGalleryId(), addedPhoto, "A photo added by jinx automated tests.");
		assertNotNull(r);
		assertEquals("ok", r.getStat());
		assertEquals(0, r.getCode());

		// edit
		List<String> photoIds = new ArrayList<String>();
		photoIds.add(addedPhoto);
		photoIds.add(primaryPhoto);
		r = galleriesApi.editPhotos(g.getGalleryId(), addedPhoto, photoIds);
		assertNotNull(r);
		assertEquals("ok", r.getStat());
		assertEquals(0, r.getCode());
	}


	@Test
	public void testEditMeta() throws Exception {
		Date d = new Date();
		Response r = galleriesApi.editMeta(TEST_CHINESE_NEON_GALLERY_ID,
				"Vintage Chinese Neon Signs (" + JinxUtils.formatDateAsUnixTimestamp(d) + ")",
				"A jinx test gallery. Test last run: " + JinxUtils.formatDateAsMySqlTimestamp(d));
		assertNotNull(r);
		assertEquals("ok", r.getStat());
		assertEquals(0, r.getCode());
	}


	@Test
	public void testEditPhoto() throws Exception {
		Date d = new Date();
		Response r = galleriesApi.editPhoto(TEST_CHINESE_NEON_GALLERY_ID,
				TEST_PHOTO_IN_GALLERY,
				"Comment updated by jinx automated test on " + JinxUtils.formatDateAsMySqlTimestamp(d));
		assertNotNull(r);
		assertEquals("ok", r.getStat());
		assertEquals(0, r.getCode());
	}

	@Test
	public void testGetInfo() throws Exception {
		GalleryInfo info = galleriesApi.galleryInfo(TEST_CHINESE_NEON_GALLERY_ID);
		assertNotNull(info);
		assertEquals("ok", info.getStat());
		assertEquals(0, info.getCode());
		Gallery g = info.getGallery();
		assertNotNull(g);
		assertEquals(TEST_CHINESE_NEON_GALLERY_ID, g.getGalleryId());
		assertTrue(g.getCountPhotos() > 0);
		assertTrue(g.getCountViews() > 0);
	}

	@Test
	public void testGetList() throws Exception {
		GalleryList list = galleriesApi.getList(USER_ID, 0, 0, null);
		assertNotNull(list);
		assertEquals("ok", list.getStat());
		assertEquals(0, list.getCode());
		assertNotNull(list.getGalleryList());
		assertTrue(list.getGalleryList().size() > 0);

		list = galleriesApi.getList(USER_ID, 10, 1, EnumSet.of(JinxConstants.PhotoExtras.date_taken, JinxConstants.PhotoExtras.url_sq));
		assertNotNull(list);
		assertEquals("ok", list.getStat());
		assertEquals(0, list.getCode());
		assertNotNull(list.getGalleryList());
		assertTrue(list.getGalleryList().size() > 0);
		Gallery g = list.getGalleryList().get(0);
		PrimaryPhotoExtras ppe = g.getPrimaryPhotoExtras();
		assertNotNull(ppe.getDateTaken());
		assertNotNull(ppe.getUrlSq());
	}

	@Test
	public void testGetListForPhoto() throws Exception {
		GalleryList list = galleriesApi.getListForPhoto(TEST_PHOTO_IN_GALLERY, 0, 0);
		assertNotNull(list);
		assertEquals("ok", list.getStat());
		assertEquals(0, list.getCode());
		assertNotNull(list.getGalleryList());
		assertTrue(list.getGalleryList().size() > 0);
	}

	@Test
	public void testGetPhotos() throws Exception {
		Photos p = galleriesApi.getPhotos(TEST_CHINESE_NEON_GALLERY_ID, null, 0, 0);
		assertNotNull(p);
		assertEquals("ok", p.getStat());
		assertEquals(0, p.getCode());
		assertNotNull(p.getPhotoList());
		assertTrue(p.getPhotoList().size() > 0);

		p = galleriesApi.getPhotos(TEST_CHINESE_NEON_GALLERY_ID, EnumSet.of(JinxConstants.PhotoExtras.date_taken, JinxConstants.PhotoExtras.license), 10, 1);
		assertNotNull(p);
		assertEquals("ok", p.getStat());
		assertEquals(0, p.getCode());
		assertNotNull(p.getPhotoList());
		assertTrue(p.getPhotoList().size() > 0);
//		assertEquals(10, p.getPhotoList().size()); // TODO: this is broken in the Flickr API. It has been reported. Test again when it's fixed.
		for (Photo photo : p.getPhotoList()) {
			assertNotNull(photo.getDateTaken());
			assertNotNull(photo.getLicense());
		}
	}

}
