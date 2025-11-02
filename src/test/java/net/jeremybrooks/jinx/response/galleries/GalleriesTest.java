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

package net.jeremybrooks.jinx.response.galleries;

import com.google.gson.Gson;
import net.jeremybrooks.jinx.response.activity.ActivityResponseTest;
import net.jeremybrooks.jinx.response.common.PrimaryPhotoExtras;
import net.jeremybrooks.jinx.response.photos.Photo;
import net.jeremybrooks.jinx.response.photos.Photos;
import org.junit.Test;

import java.io.InputStreamReader;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * @author Jeremy Brooks
 */
public class GalleriesTest {
	@Test
	public void testParse() throws Exception {
		InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/galleries/sample_create.json"));
		GalleryInfo galleryInfo = new Gson().fromJson(reader, GalleryInfo.class);
		reader.close();
		assertNotNull(galleryInfo);
		assertEquals("ok", galleryInfo.getStat());
		assertEquals(0, galleryInfo.getCode());
		Gallery g = galleryInfo.getGallery();
		assertNotNull(g);
		assertEquals("124834485-72157644749801090", g.getGalleryId());
		assertEquals("https://www.flickr.com/photos/jinxlib/galleries/72157644749801090", g.getUrl());
		assertEquals("124857539@N03", g.getOwner());
		assertEquals("jinxlib", g.getUsername());
		assertEquals("5538", g.getIconServer());
		assertEquals("6", g.getIconFarm());
		assertEquals("", g.getPrimaryPhotoId());
		assertEquals("1402704503", g.getDateCreate());
		assertEquals("1402704503", g.getDateUpdate());
		assertEquals(0, (int) g.getCountPhotos());
		assertEquals(2, (int) g.getCountVideos());
		assertEquals(3, (int) g.getCountViews());
		assertEquals(4, (int) g.getCountComments());
		assertEquals("Test Gallery", g.getTitle());
		assertEquals("Just a test.", g.getDescription());
	}

	@Test
	public void testParseList() throws Exception {
		InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/galleries/sample_get_list.json"));
		GalleryList galleryList = new Gson().fromJson(reader, GalleryList.class);
		reader.close();
		assertNotNull(galleryList);
		assertEquals("ok", galleryList.getStat());
		assertEquals(0, galleryList.getCode());
		List<Gallery> list = galleryList.getGalleryList();
		assertNotNull(list);
		assertTrue(list.size() > 0);
		Gallery g = list.get(1);
		assertNotNull(g);
		assertEquals("124834485-72157644721099547", g.getGalleryId());
		assertEquals("https://www.flickr.com/photos/jinxlib/galleries/72157644721099547", g.getUrl());
		assertEquals("124857539@N03", g.getOwner());
		assertEquals("jinxlib", g.getUsername());
		assertEquals("5538", g.getIconServer());
		assertEquals("6", g.getIconFarm());
		assertEquals("8413449964", g.getPrimaryPhotoId());
		assertEquals("1402548358", g.getDateCreate());
		assertEquals("1402548730", g.getDateUpdate());
		assertEquals(13, (int) g.getCountPhotos());
		assertEquals(0, (int) g.getCountVideos());
		assertEquals(1, (int) g.getCountViews());
		assertEquals(0, (int) g.getCountComments());
		assertEquals("Vintage Chinese Restaurant Neon", g.getTitle());
		assertEquals("Vintage Chinese restaurant neon signs.", g.getDescription());
		assertEquals("8473", g.getPrimaryPhotoServer());
		assertEquals(9, (int) g.getPrimaryPhotoFarm());
		assertEquals("edd91a1d17", g.getPrimaryPhotoSecret());
		PrimaryPhotoExtras ppe = g.getPrimaryPhotoExtras();
		assertNotNull(ppe);
		assertEquals(2, (int) ppe.getLicense());
		assertEquals("5616", ppe.getoWidth());
		assertEquals("3744", ppe.getoHeight());
		assertEquals("1359088926", ppe.getDateUpload());
		assertEquals("1401872481", ppe.getLastUpdate());
		assertEquals("2012-01-23 12:53:18", ppe.getDateTaken());
		assertEquals(0, (int) ppe.getDateTakenGranularity());
		assertEquals("Jeremy Brooks", ppe.getOwnerName());
		assertEquals("5332", ppe.getIconServer());
		assertEquals("6", ppe.getIconFarm());
		assertEquals(197, (int) ppe.getViews());
		assertEquals("california usa vintage restaurant neon chinese blogged sancarlos sanmateocounty", ppe.getTags());
		assertEquals("", ppe.getMachineTags());
		assertEquals("c46dbb2782", ppe.getOriginalSecret());
		assertEquals("jpg", ppe.getOriginalFormat());
		assertEquals(Float.valueOf(0.0f), ppe.getLatitude());
		assertEquals(Float.valueOf(0.0f), ppe.getLongitude());
		assertEquals(0, (int) ppe.getAccuracy());
		assertEquals(0, (int) ppe.getContext());
		assertEquals("photo", ppe.getMedia());
		assertEquals("ready", ppe.getMediaStatus());
		assertEquals("https://farm9.staticflickr.com/8473/8413449964_edd91a1d17_s.jpg", ppe.getUrlSq());
		assertEquals(75, (int) ppe.getHeightSq());
		assertEquals(75, (int) ppe.getWidthSq());
		assertEquals("https://farm9.staticflickr.com/8473/8413449964_edd91a1d17_t.jpg", ppe.getUrlT());
		assertEquals(67, (int) ppe.getHeightT());
		assertEquals(100, (int) ppe.getWidthT());
		assertEquals("https://farm9.staticflickr.com/8473/8413449964_edd91a1d17_m.jpg", ppe.getUrlS());
		assertEquals(160, (int) ppe.getHeightS());
		assertEquals(240, (int) ppe.getWidthS());
		assertEquals("https://farm9.staticflickr.com/8473/8413449964_edd91a1d17.jpg", ppe.getUrlM());
		assertEquals(333, (int) ppe.getHeightM());
		assertEquals(500, (int) ppe.getWidthM());
		assertEquals("https://farm9.staticflickr.com/8473/8413449964_c46dbb2782_o.jpg", ppe.getUrlO());
		assertEquals(3744, (int) ppe.getHeightO());
		assertEquals(5616, (int) ppe.getWidthO());
		assertEquals("jeremybrooks", ppe.getPathAlias());
	}

	@Test
	public void parseGetPhotos() throws Exception {
		InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/galleries/sample_get_photos.json"));
		Photos photos = new Gson().fromJson(reader, Photos.class);
		reader.close();
		assertNotNull(photos);
		assertEquals("ok", photos.getStat());
		assertEquals(0, photos.getCode());
		assertEquals(1, (int) photos.getPage());
		assertEquals(1, (int) photos.getPages());
		assertEquals(500, (int) photos.getPerPage());
		assertEquals(13, (int) photos.getTotal());
		assertNotNull(photos.getPhotoList());
		assertEquals(13, photos.getPhotoList().size());
		Photo p = photos.getPhotoList().get(0);
		assertEquals("8413449964", p.getPhotoId());
		assertEquals("85853333@N00", p.getOwner());
		assertEquals("edd91a1d17", p.getSecret());
		assertEquals("8473", p.getServer());
		assertEquals("9", p.getFarm());
		assertEquals("China Chef", p.getTitle());
		assertTrue(p.isPublic());
		assertFalse(p.isFriend());
		assertFalse(p.isFamily());
		assertTrue(p.isPrimary());
		assertFalse(p.isHasComment());
	}
}
