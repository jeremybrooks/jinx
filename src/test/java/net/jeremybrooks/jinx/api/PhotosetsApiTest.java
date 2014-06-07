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
import net.jeremybrooks.jinx.OAuthAccessToken;
import net.jeremybrooks.jinx.response.Response;
import net.jeremybrooks.jinx.response.common.Context;
import net.jeremybrooks.jinx.response.photos.Photo;
import net.jeremybrooks.jinx.response.photosets.Photoset;
import net.jeremybrooks.jinx.response.photosets.PhotosetInfo;
import net.jeremybrooks.jinx.response.photosets.PhotosetList;
import net.jeremybrooks.jinx.response.photosets.PhotosetPhotos;
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
import static org.junit.Assert.assertNull;

public class PhotosetsApiTest {

	private static PhotosetsApi photosetsApi;
	private static PhotosetInfo photosetResponse;
	private static String photo0 = "14089985200";
	private static String photo1 = "14089938519";
	private static String photo2 = "14296772893";
	private static String photo3 = "14253479216";
	private static String photo4 = "14089987020";
	private static String photo5 = "14069360219";
	private static String photo6 = "14276190763";

	private static String photoset0 = "72157644903065293";
	private static String photoset1 = "72157644861154354";
	private static String photoset2 = "72157644807061466";


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

		photosetsApi = new PhotosetsApi(new Jinx(p.getProperty("flickr.key"), p.getProperty("flickr.secret"), oAuthAccessToken));

	}

	/**
	 * This test case tests several parts of the Photosets API.
	 * These tests all operate on a photoset created for the test, and deleted after the test.
	 * These tests are in a single method to ensure the order of execution is correct.
	 * @throws Exception
	 */
	@Test
	public void testCreateAndModifyMethods() throws Exception {
		String createdPhotosetId;
		String title = "Test Photoest";
		String description = "Created by jinx for testing.";
		photosetResponse = photosetsApi.create(title, description, photo0);
		assertNotNull(photosetResponse);
		Photoset p = photosetResponse.getPhotoset();
		assertNotNull(p);
		assertNotNull(p.getPhotosetId());
		createdPhotosetId = p.getPhotosetId();
		assertNotNull(p.getUrl());


		/* testAddPhoto() */
		Response response = photosetsApi.addPhoto(createdPhotosetId, photo1);
		assertNotNull(response);
		assertEquals(0, response.getCode());
		assertEquals("ok", response.getStat());


		/* testGetContext() */
		photosetsApi.addPhoto(createdPhotosetId, photo2);
		photosetsApi.addPhoto(createdPhotosetId, photo3);
		Context context = photosetsApi.getContext(photo1, createdPhotosetId);
		assertEquals(photo0, context.getPrevphoto().getPhotoId());
		assertEquals(photo2, context.getNextphoto().getPhotoId());


		/* testEditPhotos() */
		List<String> photos = new ArrayList<String>();
		photos.add(photo4);
		photos.add(photo5);
		photos.add(photo6);
		photos.add(photo0);
		photos.add(photo1);
		photos.add(photo2);
		photos.add(photo3);
		photosetsApi.editPhotos(createdPhotosetId, photo5, photos);
		context = photosetsApi.getContext(photo5, createdPhotosetId);
		assertEquals(photo4, context.getPrevphoto().getPhotoId());
		assertEquals(photo6, context.getNextphoto().getPhotoId());


		/* testReorderPhotos() */
		List<String> reorderPhotos = new ArrayList<String>();
		reorderPhotos.add(photo5);
		reorderPhotos.add(photo3);
		reorderPhotos.add(photo1);
		reorderPhotos.add(photo6);
		reorderPhotos.add(photo0);
		reorderPhotos.add(photo4);
		reorderPhotos.add(photo2);
		Response reorderResponse = photosetsApi.reorderPhotos(createdPhotosetId, reorderPhotos);
		assertNotNull(reorderResponse);
		assertEquals(0, reorderResponse.getCode());
		assertEquals("ok", reorderResponse.getStat());


		/* testEditMeta() */
		Response editMetaResponse = photosetsApi.editMeta(createdPhotosetId, "New Title", "New Description");
		assertNotNull(editMetaResponse);
		assertEquals(0, editMetaResponse.getCode());
		assertEquals("ok", editMetaResponse.getStat());


		/* testGetInfo */
		PhotosetInfo info = photosetsApi.getInfo(createdPhotosetId);
		assertNotNull(info);
		Photoset getInfoPhotoset = info.getPhotoset();
		assertNotNull(getInfoPhotoset);
		assertEquals(createdPhotosetId, getInfoPhotoset.getPhotosetId());
		assertEquals(photo5, getInfoPhotoset.getPrimary());
		assertEquals("New Title", getInfoPhotoset.getTitle());


		/* testSetPrimaryPhoto() */
		Response setPrimaryResponse = photosetsApi.setPrimaryPhoto(createdPhotosetId, photo6);
		assertNotNull(setPrimaryResponse);
		assertEquals(0, setPrimaryResponse.getCode());
		assertEquals("ok", setPrimaryResponse.getStat());
		PhotosetInfo spInfo = photosetsApi.getInfo(createdPhotosetId);
		assertEquals(photo6, spInfo.getPhotoset().getPrimary());


		/* testGetPhotos() */
		PhotosetPhotos photosetPhotos = photosetsApi.getPhotos(createdPhotosetId, null, null, 0, 0, null);
		assertNotNull(photosetPhotos);
		List<Photo> getPhotosList = photosetPhotos.getPhotoList();
		assertNotNull(getPhotosList);
		assertEquals(7, getPhotosList.size());
		for (Photo photo : getPhotosList) {
			assertNotNull(photo);
		}


		/* testGetPhotosWithExtras() */
		EnumSet<JinxConstants.PhotoExtras> extras = EnumSet.of(JinxConstants.PhotoExtras.date_upload, JinxConstants.PhotoExtras.owner_name);
		PhotosetPhotos extraPhotosetPhotos = photosetsApi.getPhotos(createdPhotosetId, extras, JinxConstants.PrivacyFilter.privacyPublic, 0, 0, JinxConstants.MediaType.all);
		assertNotNull(extraPhotosetPhotos);
		List<Photo> extrasPhotos = extraPhotosetPhotos.getPhotoList();
		assertNotNull(extrasPhotos);
		assertEquals(7, extrasPhotos.size());
		for (Photo photo : extrasPhotos) {
			assertNotNull(photo);
			assertNotNull(photo.getDateUpload());
			assertEquals("jinxlib", photo.getOwnerName());
		}


		/* testRemovePhoto() */
		Response removeResponse = photosetsApi.removePhoto(createdPhotosetId, photo6);
		assertNotNull(removeResponse);
		assertEquals(0, removeResponse.getCode());
		assertEquals("ok", removeResponse.getStat());


		/* testRemovePhotos() */
		List<String> list = new ArrayList<String>();
		list.add(photo0);
		list.add(photo1);
		Response removePhotoResponse = photosetsApi.removePhotos(createdPhotosetId, list);
		assertNotNull(removePhotoResponse);
		assertEquals(0, removePhotoResponse.getCode());
		assertEquals("ok", removePhotoResponse.getStat());


		/* testDelete() */
		Response deleteResponse = photosetsApi.delete(createdPhotosetId);
		assertNotNull(deleteResponse);
		assertEquals(0, deleteResponse.getCode());
		assertEquals("ok", deleteResponse.getStat());
	}

	@Test
	public void testGetList() throws Exception {
		PhotosetList list = photosetsApi.getList(null, 0, 0, null);
		assertNotNull(list);
		assertNotNull(list.getPhotosets());
		assertTrue(list.getPhotosets().getPhotosetList().size() > 0);
		assertNull(list.getPhotosets().getPhotosetList().get(0).getPrimaryPhotoExtras());
	}

	@Test
	public void testGetListPaged() throws Exception {
		PhotosetList list = photosetsApi.getList(null, 2, 5, null);
		assertNotNull(list);
		assertNotNull(list.getPhotosets());
		assertEquals(5, list.getPhotosets().getPhotosetList().size());
		assertNull(list.getPhotosets().getPhotosetList().get(0).getPrimaryPhotoExtras());
	}

	@Test
	public void testGetListWithAllExtras() throws Exception {
		EnumSet<JinxConstants.PhotoExtras> extras = EnumSet.allOf(JinxConstants.PhotoExtras.class);
		PhotosetList list = photosetsApi.getList(null, 2, 5, extras);
		assertNotNull(list.getPhotosets());
		assertEquals(5, list.getPhotosets().getPhotosetList().size());
		assertNotNull(list.getPhotosets().getPhotosetList().get(0).getPrimaryPhotoExtras());
	}

	@Test
	public void testGetListWithSomeExtras() throws Exception {
		EnumSet<JinxConstants.PhotoExtras> extras = EnumSet.of(JinxConstants.PhotoExtras.owner_name);
		PhotosetList list = photosetsApi.getList(null, 2, 5, extras);
		assertNotNull(list.getPhotosets());
		assertEquals(5, list.getPhotosets().getPhotosetList().size());
		assertNotNull(list.getPhotosets().getPhotosetList().get(0).getPrimaryPhotoExtras());
	}

	@Test
	public void testGetListFromUser() throws Exception {
		PhotosetList list = photosetsApi.getList("51035555243@N01", 2, 5, null);
		assertNotNull(list);
		assertNotNull(list.getPhotosets());
		assertEquals(5, list.getPhotosets().getPhotosetList().size());
		assertNull(list.getPhotosets().getPhotosetList().get(0).getPrimaryPhotoExtras());
	}

	@Test
	public void testOrderSets() throws Exception {
		List<String> list = new ArrayList<String>();
		list.add(photoset0);
		list.add(photoset1);
		list.add(photoset2);
		Response response = photosetsApi.orderSets(list);
		assertNotNull(response);
		assertEquals("ok", response.getStat());
		assertEquals(0, response.getCode());
	}
}