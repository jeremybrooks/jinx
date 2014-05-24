package net.jeremybrooks.jinx.response.photosets;

import com.google.gson.Gson;
import net.jeremybrooks.jinx.response.activity.ActivityResponseTest;
import org.junit.Test;

import java.io.InputStreamReader;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

/**
 * @author Jeremy Brooks
 */
public class PhotosetInfoTest {

	/*
	{ "photoset": {
	    "id": "72157644586245015",
	    "url": "https:\/\/www.flickr.com\/photos\/jeremybrooks\/sets\/72157644586245015\/"
	}, "stat": "ok" }
	 */
	@Test
	public void testPhotosetCreate() throws Exception {
		InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/photosets/sample_photoset_create.json"));
		PhotosetInfo info = new Gson().fromJson(reader, PhotosetInfo.class);
		reader.close();
		assertNotNull(info);
		assertNotNull(info.getPhotoset());
		assertEquals("ok", info.getStat());
		assertEquals("72157644586245015", info.getPhotoset().getPhotosetId());
		assertEquals("https://www.flickr.com/photos/jeremybrooks/sets/72157644586245015/", info.getPhotoset().getUrl());
	}

	/*
	{ "photoset": {
	    "id": "72157644326967779",
	    "owner": "85853333@N00",
	    "username": "Jeremy Brooks",
	    "primary": "2472223927",
	    "secret": "7081aec34e",
	    "server": "2026",
	    "farm": 3,
	    "photos": 87,
	    "count_views": 10,
	    "count_comments": 20,
	    "count_photos": 87,
	    "count_videos": 1,
	    "title": {
	        "_content": "Neon of Nebraska"
	    },
	    "description": {
	        "_content": "Neon signs in Nebraska.\n\nThis set is managed by <a href=\"http:\/\/www.jeremybrooks.net\/suprsetr\" rel=\"nofollow\">SuprSetr<\/a>"
	    },
	    "can_comment": 1,
	    "date_create": "1400476343",
	    "date_update": "1400476349",
	    "coverphoto_server": 0,
	    "coverphoto_farm": 0
	}, "stat": "ok" }
	 */
	@Test
	public void testPhotosetInfo() throws Exception {
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

	/*
	{ "photosets": {
	    "cancreate": 1,
	    "page": 1,
	    "pages": 45,
	    "perpage": 10,
	    "total": "447",
	    "photoset": [
	        {
	            "id": "72157644326967779",
	            "primary": "2472223927",
	            "secret": "7081aec34e",
	            "server": "2026",
	            "farm": 3,
	            "photos": 87,
	            "videos": 0,
	            "title": {
	                "_content": "Neon of Nebraska"
	            },
	            "description": {
	                "_content": "Neon signs in Nebraska.\n\nThis set is managed by <a href=\"http:\/\/www.jeremybrooks.net\/suprsetr\" rel=\"nofollow\">SuprSetr<\/a>"
	            },
	            "needs_interstitial": 0,
	            "visibility_can_see_set": 1,
	            "count_views": 0,
	            "count_comments": 0,
	            "can_comment": 1,
	            "date_create": "1400476343",
	            "date_update": "1400476349",
	            "primary_photo_extras": {
	                "license": 2,
	                "o_width": "3888",
	                "o_height": "2588",
	                "dateupload": "1210135559",
	                "lastupdate": "1385342902",
	                "datetaken": "2008-05-03 08:34:09",
	                "datetakengranularity": 0,
	                "ownername": "Jeremy Brooks",
	                "iconserver": "5332",
	                "iconfarm": 6,
	                "views": "1084",
	                "tags": "food restaurant nebraska neon day unitedstates meat steak lucky omaha",
	                "machine_tags": "",
	                "originalsecret": "e64cd9f5d9",
	                "originalformat": "jpg",
	                "latitude": 41.248833,
	                "longitude": -95.929666,
	                "accuracy": 16,
	                "context": 0,
	                "place_id": "gNIcefxTWrgrNXMkFQ",
	                "woeid": "28288811",
	                "geo_is_family": 0,
	                "geo_is_friend": 0,
	                "geo_is_contact": 0,
	                "geo_is_public": 1,
	                "media": "photo",
	                "media_status": "ready",
	                "url_sq": "https:\/\/farm3.staticflickr.com\/2026\/2472223927_7081aec34e_s.jpg",
	                "height_sq": 75,
	                "width_sq": 75,
	                "url_t": "https:\/\/farm3.staticflickr.com\/2026\/2472223927_7081aec34e_t.jpg",
	                "height_t": 67,
	                "width_t": 100,
	                "url_s": "https:\/\/farm3.staticflickr.com\/2026\/2472223927_7081aec34e_m.jpg",
	                "height_s": "160",
	                "width_s": "240",
	                "url_m": "https:\/\/farm3.staticflickr.com\/2026\/2472223927_7081aec34e.jpg",
	                "height_m": "333",
	                "width_m": "500",
	                "url_o": "https:\/\/farm3.staticflickr.com\/2026\/2472223927_e64cd9f5d9_o.jpg",
	                "height_o": "2588",
	                "width_o": "3888",
	                "pathalias": "jeremybrooks"
	            }
	        },
	        ... 9 more ...
	        }
	    ]
	}, "stat": "ok" }

	 */
	@Test
	public void testGetPhotosetList() throws Exception {
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
}
