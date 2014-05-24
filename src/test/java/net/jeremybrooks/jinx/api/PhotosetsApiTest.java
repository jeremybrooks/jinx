package net.jeremybrooks.jinx.api;

import net.jeremybrooks.jinx.Jinx;
import net.jeremybrooks.jinx.JinxConstants;
import net.jeremybrooks.jinx.OAuthAccessToken;
import net.jeremybrooks.jinx.response.Response;
import net.jeremybrooks.jinx.response.common.Context;
import net.jeremybrooks.jinx.response.photosets.Photoset;
import net.jeremybrooks.jinx.response.photosets.PhotosetInfo;
import net.jeremybrooks.jinx.response.photosets.PhotosetList;
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
	private static String photosetId;

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

	@Test
	public void testCreate() throws Exception {
		photosetResponse = photosetsApi.create("testTitle", "test description", "13955051520");
		assertNotNull(photosetResponse);
		Photoset p = photosetResponse.getPhotoset();
		assertNotNull(p);
		assertNotNull(p.getPhotosetId());
		photosetId = p.getPhotosetId();
		assertNotNull(p.getUrl());
	}

	@Test
	public void testAddPhoto() throws Exception {
		Response response = photosetsApi.addPhoto(photosetId, "13955031078");
		assertNotNull(response);
		assertEquals(0, response.getCode());
		assertEquals("ok", response.getStat());
	}


	@Test
	public void testGetContext() throws Exception {
		photosetsApi.addPhoto(photosetId, "14242563335");
		photosetsApi.addPhoto(photosetId, "14055925910");
		Context context = photosetsApi.getContext("14242563335", photosetId);
		assertEquals("13955031078", context.getPrevphoto().getPhotoId());
		assertEquals("14055925910", context.getNextphoto().getPhotoId());
	}

	@Test
	public void testEditPhotos() throws Exception {
		List<String> photos = new ArrayList<String>();
		photos.add("14258171393");
		photos.add("14048127127");
		photos.add("14232356582");
		photosetsApi.editPhotos(photosetId, "14232356582", photos);

		Context context = photosetsApi.getContext("14048127127", photosetId);
		assertEquals("14258171393", context.getPrevphoto().getPhotoId());
		assertEquals("14232356582", context.getNextphoto().getPhotoId());
	}


	@Test
	public void editMeta() throws Exception {
		Response response = photosetsApi.editMeta(photosetId, "New Title", "New Description");
		assertNotNull(response);
		assertEquals(0, response.getCode());
		assertEquals("ok", response.getStat());
	}

	@Test
	public void testGetInfo() throws Exception {
		PhotosetInfo info = photosetsApi.getInfo(photosetId);
		assertNotNull(info);
		Photoset p = info.getPhotoset();
		assertNotNull(p);
		assertEquals(photosetId, p.getPhotosetId());
		assertEquals("14232356582", p.getPrimary());
		assertEquals("New Title", p.getTitle());
	}

	@Test
	public void testDelete() throws Exception {
		Response response = photosetsApi.delete(photosetId);
		assertNotNull(response);
		assertEquals(0, response.getCode());
		assertEquals("ok", response.getStat());
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
}