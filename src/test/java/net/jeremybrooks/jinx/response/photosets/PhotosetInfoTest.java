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

package net.jeremybrooks.jinx.response.photosets;

import com.google.gson.Gson;
import net.jeremybrooks.jinx.response.activity.ActivityResponseTest;
import net.jeremybrooks.jinx.response.photos.Photo;
import org.junit.Test;

import java.io.InputStreamReader;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

/**
 * @author Jeremy Brooks
 */
public class PhotosetInfoTest {

	@Test
	public void testParsePhotosetCreate() throws Exception {
		InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/photosets/sample_photoset_create.json"));
		PhotosetInfo info = new Gson().fromJson(reader, PhotosetInfo.class);
		reader.close();
		assertNotNull(info);
		assertNotNull(info.getPhotoset());
		assertEquals("ok", info.getStat());
		assertEquals("72157644586245015", info.getPhotoset().getPhotosetId());
		assertEquals("https://www.flickr.com/photos/jeremybrooks/sets/72157644586245015/", info.getPhotoset().getUrl());
	}

	@Test
	public void testParsePhotosetInfo() throws Exception {
		InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/photosets/sample_photoset_info.json"));
		PhotosetInfo info = new Gson().fromJson(reader, PhotosetInfo.class);
		reader.close();
		assertNotNull(info);
		assertEquals("ok", info.getStat());
		Photoset p = info.getPhotoset();
		assertNotNull(p);
		assertEquals("72157644326967779", p.getPhotosetId());
		assertEquals("85853333@N00", p.getOwner());
		assertEquals("Jeremy Brooks", p.getUsername());
		assertEquals("2472223927", p.getPrimary());
		assertEquals("7081aec34e", p.getSecret());
		assertEquals("2026", p.getServer());
		assertEquals(3, (int) p.getFarm());
		assertEquals(87, (int) p.getPhotos());
		assertEquals(10, (int) p.getCountViews());
		assertEquals(20, (int) p.getCountComments());
		assertEquals(87, (int) p.getCountPhotos());
		assertEquals(1, (int) p.getCountVideos());
		assertEquals("Neon of Nebraska", p.getTitle());
		assertEquals("Neon signs in Nebraska.\n\nThis set is managed by <a href=\"http://www.jeremybrooks.net/suprsetr\" rel=\"nofollow\">SuprSetr</a>", p.getDescription());
		assertTrue(p.isCanComment());
		assertEquals("1400476343", p.getDateCreate());
		assertEquals("1400476349", p.getDateUpdate());
		assertEquals(0, (int) p.getCoverphotoServer());
		assertEquals(0, (int) p.getCoverphotoFarm());
	}


	@Test
	public void testParseGetPhotosetList() throws Exception {
		InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/photosets/sample_photoset_list.json"));
		PhotosetList list = new Gson().fromJson(reader, PhotosetList.class);
		reader.close();
		assertNotNull(list);
		assertEquals("ok", list.getStat());
		PhotosetList.Photosets photosets = list.getPhotosets();
		assertNotNull(photosets);
		assertTrue(photosets.getIsCanCreate());
		assertEquals(1, (int) photosets.getPage());
		assertEquals(45, (int) photosets.getPages());
		assertEquals(10, (int) photosets.getPerPage());
		assertEquals(447, (int) photosets.getTotal());
		assertNotNull(photosets.getPhotosetList());
		assertEquals(10, photosets.getPhotosetList().size());

		// test the first photoset in the list
		Photoset p = photosets.getPhotosetList().get(0);
		assertEquals("72157644326967779", p.getPhotosetId());
		assertEquals("2472223927", p.getPrimary());
		assertEquals("7081aec34e", p.getSecret());
		assertEquals("2026", p.getServer());
		assertEquals(3, (int) p.getFarm());
		assertEquals(87, (int) p.getPhotos());
		assertEquals(0, (int) p.getVideos());

		assertEquals(0, (int) p.getCountViews());
		assertEquals(0, (int) p.getCountComments());

		assertEquals("Neon of Nebraska", p.getTitle());
		assertEquals("Neon signs in Nebraska.\n\nThis set is managed by <a href=\"http://www.jeremybrooks.net/suprsetr\" rel=\"nofollow\">SuprSetr</a>", p.getDescription());
		assertFalse(p.getIsNeedsInterstitial());
		assertTrue(p.getIsVisibilityCanSeeSet());

		assertTrue(p.isCanComment());
		assertEquals("1400476343", p.getDateCreate());
		assertEquals("1400476349", p.getDateUpdate());

		// primary photo extras
		PrimaryPhotoExtras ppe = p.getPrimaryPhotoExtras();
		assertNotNull(ppe);
		assertEquals(2, (int) ppe.getLicense());
		assertEquals("3888", ppe.getoWidth());
		assertEquals("2588", ppe.getoHeight());
		assertEquals("1210135559", ppe.getDateUpload());
		assertEquals("1385342902", ppe.getLastUpdate());
		assertEquals("2008-05-03 08:34:09", ppe.getDateTaken());
		assertEquals(1, (int) ppe.getDateTakenGranularity());
		assertEquals("Jeremy Brooks", ppe.getOwnerName());
		assertEquals("5332", ppe.getIconServer());
		assertEquals(6, (int) ppe.getIconFarm());
		assertEquals("1084", ppe.getViews());
		assertEquals("food restaurant nebraska neon day unitedstates meat steak lucky omaha", ppe.getTags());
		assertEquals("sign:type=neon", ppe.getMachineTags());
		assertEquals("e64cd9f5d9", ppe.getOriginalSecret());
		assertEquals("jpg", ppe.getOriginalFormat());
		assertEquals(41.248833, ppe.getLatitude());
		assertEquals(-95.929666, ppe.getLongitude());
		assertEquals(16, (int) ppe.getAccuracy());
		assertEquals(0, (int) ppe.getContext());
		assertEquals("gNIcefxTWrgrNXMkFQ", ppe.getPlaceId());
		assertEquals("28288811", ppe.getWoeId());
		assertFalse(ppe.isGeoIsFamily());
		assertFalse(ppe.isGeoIsFriend());
		assertFalse(ppe.isGeoIsContact());
		assertTrue(ppe.isGeoIsPublic());
		assertEquals("photo", ppe.getMedia());
		assertEquals("ready", ppe.getMediaStatus());
		assertEquals("https://farm3.staticflickr.com/2026/2472223927_7081aec34e_s.jpg", ppe.getUrlSq());
		assertEquals(75, (int) ppe.getHeightSq());
		assertEquals(75, (int) ppe.getWidthSq());
		assertEquals("https://farm3.staticflickr.com/2026/2472223927_7081aec34e_t.jpg", ppe.getUrlT());
		assertEquals(67, (int) ppe.getHeightT());
		assertEquals(100, (int) ppe.getWidthT());

		assertEquals("https://farm3.staticflickr.com/2026/2472223927_7081aec34e_m.jpg", ppe.getUrlS());
		assertEquals(160, (int) ppe.getHeightS());
		assertEquals(240, (int) ppe.getWidthS());

		assertEquals("https://farm3.staticflickr.com/2026/2472223927_7081aec34e.jpg", ppe.getUrlM());
		assertEquals(333, (int) ppe.getHeightM());
		assertEquals(500, (int) ppe.getWidthM());

		assertEquals("https://farm3.staticflickr.com/2026/2472223927_e64cd9f5d9_o.jpg", ppe.getUrlO());
		assertEquals(2588, (int) ppe.getHeightO());
		assertEquals(3888, (int) ppe.getWidthO());
		assertEquals("jeremybrooks", ppe.getPathAlias());
	}

	@Test
	public void testParseGetPhotos() throws Exception {
		InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/photosets/sample_photosets_get_photos.json"));
		PhotosetPhotos photosetPhotos = new Gson().fromJson(reader, PhotosetPhotos.class);
		reader.close();
		assertNotNull(photosetPhotos);
		assertEquals("ok", photosetPhotos.getStat());
		List<Photo> photoList = photosetPhotos.getPhotoList();
		assertNotNull(photoList);
		assertEquals( 6, photoList.size());

		assertEquals("72157644807061466", photosetPhotos.getPhotosetId());
		assertEquals("14296772893", photosetPhotos.getPrimary());
		assertEquals("124857539@N03", photosetPhotos.getOwner());
		assertEquals("jinxlib", photosetPhotos.getOwnerName());
		assertEquals(1, (int)photosetPhotos.getPage());
		assertEquals("500", photosetPhotos.getPerPage());
		assertEquals(1, (int)photosetPhotos.getPages());
		assertEquals(6, (int)photosetPhotos.getTotal());
		assertEquals("Neon Signs", photosetPhotos.getTitle());

		Photo photo = photosetPhotos.getPhotoList().get(0);
		assertNotNull(photo);
		assertEquals("14296772893", photo.getPhotoId());
		assertEquals("270d516d2b", photo.getSecret());
		assertEquals("3688", photo.getServer());
		assertEquals(4, (int)photo.getFarm());
		assertEquals("Right Outside", photo.getTitle());
		assertTrue(photo.isPrimary());
		assertEquals(0, (int)photo.getLicense());
		assertEquals("1401130554", photo.getDateUpload());
		assertEquals("1401138922", photo.getLastUpdate());
		assertEquals("2010-08-27 12:17:49", photo.getDateTaken());
		assertEquals(0, (int)photo.getDateTakenGranularity());
		assertEquals("jinxlib", photo.getOwnerName());
		assertEquals("0", photo.getIconServer());
		assertEquals(0, (int)photo.getIconFarm());
		assertEquals(1, (int)photo.getViews());
		assertEquals("sanfrancisco california usa bar vintage neon chinatown day arrow cocktails lipo sanfranciscocounty sign:type=neon", photo.getTags());
		assertEquals("sign:type=neon", photo.getMachineTags());
		assertEquals("ae304bdb6c", photo.getOriginalSecret());
		assertEquals("jpg", photo.getOriginalFormat());
		assertEquals(37.795387, photo.getLatitude());
		assertEquals(-122.406486, photo.getLongitude());
		assertEquals(16, (int)photo.getAccuracy());
		assertEquals(0, (int)photo.getContext());
		assertEquals("iIt1rq9TUb1LDiek", photo.getPlaceId());
		assertEquals("2379855", photo.getWoeId());
		assertFalse(photo.isGeoIsFamily());
		assertFalse(photo.isGeoIsFriend());
		assertFalse(photo.isGeoIsContact());
		assertTrue(photo.isGeoIsPublic());
		assertEquals("photo", photo.getMedia());
		assertEquals("ready", photo.getMediaStatus());
		assertEquals("", photo.getPathAlias());

		assertEquals("https://farm4.staticflickr.com/3688/14296772893_270d516d2b_s.jpg", photo.getUrlSq());
		assertEquals(75, (int)photo.getHeightSq());
		assertEquals(75, (int)photo.getWidthSq());
		assertEquals("https://farm4.staticflickr.com/3688/14296772893_270d516d2b_t.jpg", photo.getUrlT());
		assertEquals(67, (int)photo.getHeightT());
		assertEquals(100, (int)photo.getWidthT());
		assertEquals("https://farm4.staticflickr.com/3688/14296772893_270d516d2b_m.jpg", photo.getUrlS());
		assertEquals(160, (int)photo.getHeightS());
		assertEquals(240, (int)photo.getWidthS());
		assertEquals("https://farm4.staticflickr.com/3688/14296772893_270d516d2b.jpg", photo.getUrlM());
		assertEquals(333, (int)photo.getHeightM());
		assertEquals(500, (int)photo.getWidthM());

		assertEquals("https://farm4.staticflickr.com/3688/14296772893_ae304bdb6c_o.jpg", photo.getUrlO());
		assertEquals(3744, (int)photo.getHeightO());
		assertEquals(5616, (int)photo.getWidthO());
	}
}
