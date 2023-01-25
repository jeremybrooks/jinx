/*
 * Jinx is Copyright 2010-2023 by Jeremy Brooks and Contributors
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

import net.jeremybrooks.jinx.JinxConstants;
import net.jeremybrooks.jinx.response.Response;
import net.jeremybrooks.jinx.response.common.Context;
import net.jeremybrooks.jinx.response.photos.Photo;
import net.jeremybrooks.jinx.response.photos.Photos;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.EnumSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Jeremy Brooks
 */
public class FavoritesApiTest {

	private static FavoritesApi favoritesApi;

	private static String photoId = "14192441117";
	private static String photoIdIsFavorite = "31681278290";
	private static String userId = "124857539@N03";

	@BeforeClass
	public static void beforeClass() throws Exception {
		favoritesApi = new FavoritesApi(JinxApiTestCommon.getJinx());
	}


	@Test
	public void testAddAndRemove() throws Exception {
		testAdd();
		testRemove();
	}


	private void testAdd() throws Exception {
		Response response = favoritesApi.add(photoId);
		assertNotNull(response);
		assertEquals("ok", response.getStat());
		assertEquals(0, response.getCode());
	}

	private void testRemove() throws Exception {
		Response response = favoritesApi.remove(photoId);
		assertNotNull(response);
		assertEquals("ok", response.getStat());
		assertEquals(0, response.getCode());
	}


	@Test
	public void testGetContext() throws Exception {
		Context context = favoritesApi.getContext(photoIdIsFavorite, userId);
		assertNotNull(context);
		assertNotNull(context.getNextPhoto());
		assertNotNull(context.getPrevPhoto());
	}

	@Test
	public void testGetList() throws Exception {
		Photos photos = favoritesApi.getList(null, null, null, null, 0, 0);
		assertNotNull(photos);
		assertNotNull(photos.getPhotoList());
		assertTrue(photos.getPhotoList().size() > 0);

		photos = favoritesApi.getList(null, null, null, EnumSet.of(JinxConstants.PhotoExtras.date_taken, JinxConstants.PhotoExtras.owner_name), 5, 1);
		assertNotNull(photos);
		assertNotNull(photos.getPhotoList());
		assertEquals(1, (int) photos.getPage());
		assertEquals(5, (int) photos.getPerPage());
		assertEquals(5, photos.getPhotoList().size());
		for (Photo p : photos.getPhotoList()) {
			assertNotNull(p.getDateTaken());
			assertNotNull(p.getOwnerName());
		}
	}

	@Test
	public void testGetPublicList() throws Exception {
		Photos photos = favoritesApi.getPublicList(userId, null, null, null, 0, 0);
		assertNotNull(photos);
		assertNotNull(photos.getPhotoList());
		assertTrue(photos.getPhotoList().size() > 0);

		photos = favoritesApi.getPublicList(userId, null, null, EnumSet.of(JinxConstants.PhotoExtras.date_taken, JinxConstants.PhotoExtras.owner_name), 5, 1);
		assertNotNull(photos);
		assertNotNull(photos.getPhotoList());
		assertEquals(1, (int) photos.getPage());
		assertEquals(5, (int) photos.getPerPage());
		assertEquals(5, photos.getPhotoList().size());
		for (Photo p : photos.getPhotoList()) {
			assertNotNull(p.getDateTaken());
			assertNotNull(p.getOwnerName());
		}
	}
}
