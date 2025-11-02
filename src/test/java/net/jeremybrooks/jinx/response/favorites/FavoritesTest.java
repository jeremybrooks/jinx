/*
 * Jinx is Copyright 2010-2025 by Jeremy Brooks and Contributors
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

package net.jeremybrooks.jinx.response.favorites;

import com.google.gson.Gson;
import net.jeremybrooks.jinx.response.activity.ActivityResponseTest;
import net.jeremybrooks.jinx.response.common.Context;
import net.jeremybrooks.jinx.response.photos.Photo;
import net.jeremybrooks.jinx.response.photos.Photos;
import org.junit.Test;

import java.io.InputStreamReader;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Jeremy Brooks
 */
public class FavoritesTest {

	@Test
	public void testParseContext() throws Exception {
		InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/favorites/sample_get_context.json"));
		Context context = new Gson().fromJson(reader, Context.class);
		reader.close();

		assertNotNull(context);
		assertEquals(15, (int) context.getCount());
		Context.PrevPhoto prevPhoto = context.getPrevPhoto();
		assertEquals("14389333104", prevPhoto.getPhotoId());
		assertEquals("51035555243@N01", prevPhoto.getOwner());
		assertEquals("a927c370e0", prevPhoto.getSecret());
		assertEquals("3881", prevPhoto.getServer());
		assertEquals("4", prevPhoto.getFarm());
		assertEquals("A New Type of Jesus", prevPhoto.getTitle());
		assertEquals("/photos/thomashawk/14389333104/in/faves-jinxlib/", prevPhoto.getUrl());
		assertEquals("https://farm4.staticflickr.com/3881/14389333104_a927c370e0_s.jpg", prevPhoto.getThumb());
		assertEquals(2, (int) prevPhoto.getLicense());
		assertEquals("photo", prevPhoto.getMedia());
		assertTrue(prevPhoto.isFaved());

		Context.NextPhoto nextPhoto = context.getNextPhoto();
		assertEquals("14335914741", nextPhoto.getPhotoId());
		assertEquals("42961457@N04", nextPhoto.getOwner());
		assertEquals("3df684a505", nextPhoto.getSecret());
		assertEquals("2940", nextPhoto.getServer());
		assertEquals("3", nextPhoto.getFarm());
		assertEquals("", nextPhoto.getTitle());
		assertEquals("/photos/troyholden/14335914741/in/faves-jinxlib/", nextPhoto.getUrl());
		assertEquals("https://farm3.staticflickr.com/2940/14335914741_3df684a505_s.jpg", nextPhoto.getThumb());
		assertEquals(0, (int) nextPhoto.getLicense());
		assertEquals("photo", nextPhoto.getMedia());
		assertTrue(nextPhoto.isFaved());
	}

	@Test
	public void testParseGetList() throws Exception {
		InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/favorites/sample_get_list.json"));
		Photos photos = new Gson().fromJson(reader, Photos.class);
		reader.close();
		assertNotNull(photos);
		assertEquals("ok", photos.getStat());
		assertEquals(0, photos.getCode());
		assertEquals(1, (int) photos.getPage());
		assertEquals(1, (int) photos.getPages());
		assertEquals(100, (int) photos.getPerPage());
		assertEquals(15, (int) photos.getTotal());
		List<Photo> list = photos.getPhotoList();
		assertNotNull(list);
		assertEquals(15, list.size());
		Photo p = list.get(0);
		assertNotNull(p);
		assertEquals("13246387263", p.getPhotoId());
		assertEquals("42961457@N04", p.getOwner());
		assertEquals("33a8769e2e", p.getSecret());
		assertEquals("7297", p.getServer());
		assertEquals("8", p.getFarm());
		assertEquals("", p.getTitle());
		assertTrue(p.isPublic());
		assertFalse(p.isFriend());
		assertFalse(p.isFamily());
		assertEquals(0, (int) p.getLicense());
		assertEquals("Civic Center | San Francisco", p.getDescription());
		assertEquals("1024", p.getoWidth());
		assertEquals("683", p.getoHeight());
		assertEquals("1395161051", p.getDateUpload());
		assertEquals("1401348174", p.getLastUpdate());
		assertEquals("2014-03-18 09:43:39", p.getDateTaken());
		assertEquals(0, (int) p.getDateTakenGranularity());
		assertEquals("Troy Holden", p.getOwnerName());
		assertEquals("3833", p.getIconServer());
		assertEquals("4", p.getIconFarm());
		assertEquals(12405, (int) p.getViews());
		assertEquals("sanfrancisco civiccenter sfist oldmint vision:text=0867 vision:outdoor=0944 oldmint1 sidewalkszine", p.getTags());
		assertEquals("c86887e911", p.getOriginalSecret());
		assertEquals("jpg", p.getOriginalFormat());
		assertEquals("1402501796", p.getDateFaved());
		assertEquals(0.0, p.getLatitude(), 0.0);
		assertEquals(0.0, p.getLongitude(), 0.0);
		assertEquals(0, (int) p.getAccuracy());
		assertEquals(0, (int) p.getContext());
		assertEquals("photo", p.getMedia());
		assertEquals("ready", p.getMediaStatus());
		assertEquals("https://farm8.staticflickr.com/7297/13246387263_33a8769e2e_s.jpg", p.getUrlSq());
		assertEquals(75, (int) p.getHeightSq());
		assertEquals(75, (int) p.getWidthSq());
		assertEquals("https://farm8.staticflickr.com/7297/13246387263_33a8769e2e_t.jpg", p.getUrlT());
		assertEquals(67, (int) p.getHeightT());
		assertEquals(100, (int) p.getWidthT());
		assertEquals("https://farm8.staticflickr.com/7297/13246387263_33a8769e2e_m.jpg", p.getUrlS());
		assertEquals(160, (int) p.getHeightS());
		assertEquals(240, (int) p.getWidthS());
		assertEquals("https://farm8.staticflickr.com/7297/13246387263_33a8769e2e_q.jpg", p.getUrlQ());
		assertEquals(150, (int) p.getHeightQ());
		assertEquals(150, (int) p.getWidthQ());
		assertEquals("https://farm8.staticflickr.com/7297/13246387263_33a8769e2e.jpg", p.getUrlM());
		assertEquals(333, (int) p.getHeightM());
		assertEquals(500, (int) p.getWidthM());
		assertEquals("https://farm8.staticflickr.com/7297/13246387263_33a8769e2e_n.jpg", p.getUrlN());
		assertEquals(213, (int) p.getHeightN());
		assertEquals(320, (int) p.getWidthN());
		assertEquals("https://farm8.staticflickr.com/7297/13246387263_33a8769e2e_z.jpg", p.getUrlZ());
		assertEquals(427, (int) p.getHeightZ());
		assertEquals(640, (int) p.getWidthZ());
		assertEquals("https://farm8.staticflickr.com/7297/13246387263_33a8769e2e_c.jpg", p.getUrlC());
		assertEquals(534, (int) p.getHeightC());
		assertEquals(800, (int) p.getWidthC());
		assertEquals("https://farm8.staticflickr.com/7297/13246387263_33a8769e2e_b.jpg", p.getUrlL());
		assertEquals(683, (int) p.getHeightL());
		assertEquals(1024, (int) p.getWidthL());
		assertEquals("https://farm8.staticflickr.com/7297/13246387263_c86887e911_o.jpg", p.getUrlO());
		assertEquals(683, (int) p.getHeightO());
		assertEquals(1024, (int) p.getWidthO());
		assertEquals("troyholden", p.getPathAlias());
	}

	@Test
		public void testParseGetPublicList() throws Exception {
			InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/favorites/sample_get_public_list.json"));
			Photos photos = new Gson().fromJson(reader, Photos.class);
			reader.close();
			assertNotNull(photos);
			assertEquals("ok", photos.getStat());
			assertEquals(0, photos.getCode());
			assertEquals(1, (int) photos.getPage());
			assertEquals(1, (int) photos.getPages());
			assertEquals(100, (int) photos.getPerPage());
			assertEquals(15, (int) photos.getTotal());
			List<Photo> list = photos.getPhotoList();
			assertNotNull(list);
			assertEquals(15, list.size());
			Photo p = list.get(0);
			assertNotNull(p);
			assertEquals("13246387263", p.getPhotoId());
			assertEquals("42961457@N04", p.getOwner());
			assertEquals("33a8769e2e", p.getSecret());
			assertEquals("7297", p.getServer());
			assertEquals("8", p.getFarm());
			assertEquals("", p.getTitle());
			assertTrue(p.isPublic());
			assertFalse(p.isFriend());
			assertFalse(p.isFamily());
			assertEquals(0, (int) p.getLicense());
			assertEquals("Civic Center | San Francisco", p.getDescription());
			assertEquals("1024", p.getoWidth());
			assertEquals("683", p.getoHeight());
			assertEquals("1395161051", p.getDateUpload());
			assertEquals("1401348174", p.getLastUpdate());
			assertEquals("2014-03-18 09:43:39", p.getDateTaken());
			assertEquals(0, (int) p.getDateTakenGranularity());
			assertEquals("Troy Holden", p.getOwnerName());
			assertEquals("3833", p.getIconServer());
			assertEquals("4", p.getIconFarm());
			assertEquals(12407, (int) p.getViews());
			assertEquals("sanfrancisco civiccenter sfist oldmint vision:text=0867 vision:outdoor=0944 oldmint1 sidewalkszine", p.getTags());
			assertEquals("c86887e911", p.getOriginalSecret());
			assertEquals("jpg", p.getOriginalFormat());
			assertEquals("1402501796", p.getDateFaved());
			assertEquals(0.0, p.getLatitude(), 0.0);
			assertEquals(0.0, p.getLongitude(), 0.0);
			assertEquals(0, (int) p.getAccuracy());
			assertEquals(0, (int) p.getContext());
			assertEquals("photo", p.getMedia());
			assertEquals("ready", p.getMediaStatus());
			assertEquals("https://farm8.staticflickr.com/7297/13246387263_33a8769e2e_s.jpg", p.getUrlSq());
			assertEquals(75, (int) p.getHeightSq());
			assertEquals(75, (int) p.getWidthSq());
			assertEquals("https://farm8.staticflickr.com/7297/13246387263_33a8769e2e_t.jpg", p.getUrlT());
			assertEquals(67, (int) p.getHeightT());
			assertEquals(100, (int) p.getWidthT());
			assertEquals("https://farm8.staticflickr.com/7297/13246387263_33a8769e2e_m.jpg", p.getUrlS());
			assertEquals(160, (int) p.getHeightS());
			assertEquals(240, (int) p.getWidthS());
			assertEquals("https://farm8.staticflickr.com/7297/13246387263_33a8769e2e_q.jpg", p.getUrlQ());
			assertEquals(150, (int) p.getHeightQ());
			assertEquals(150, (int) p.getWidthQ());
			assertEquals("https://farm8.staticflickr.com/7297/13246387263_33a8769e2e.jpg", p.getUrlM());
			assertEquals(333, (int) p.getHeightM());
			assertEquals(500, (int) p.getWidthM());
			assertEquals("https://farm8.staticflickr.com/7297/13246387263_33a8769e2e_n.jpg", p.getUrlN());
			assertEquals(213, (int) p.getHeightN());
			assertEquals(320, (int) p.getWidthN());
			assertEquals("https://farm8.staticflickr.com/7297/13246387263_33a8769e2e_z.jpg", p.getUrlZ());
			assertEquals(427, (int) p.getHeightZ());
			assertEquals(640, (int) p.getWidthZ());
			assertEquals("https://farm8.staticflickr.com/7297/13246387263_33a8769e2e_c.jpg", p.getUrlC());
			assertEquals(534, (int) p.getHeightC());
			assertEquals(800, (int) p.getWidthC());
			assertEquals("https://farm8.staticflickr.com/7297/13246387263_33a8769e2e_b.jpg", p.getUrlL());
			assertEquals(683, (int) p.getHeightL());
			assertEquals(1024, (int) p.getWidthL());
			assertEquals("https://farm8.staticflickr.com/7297/13246387263_c86887e911_o.jpg", p.getUrlO());
			assertEquals(683, (int) p.getHeightO());
			assertEquals(1024, (int) p.getWidthO());
			assertEquals("troyholden", p.getPathAlias());
		}
}
