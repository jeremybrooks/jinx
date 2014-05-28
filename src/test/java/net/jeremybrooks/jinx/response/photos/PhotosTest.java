package net.jeremybrooks.jinx.response.photos;

import com.google.gson.Gson;
import net.jeremybrooks.jinx.response.activity.ActivityResponseTest;
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
public class PhotosTest {
	@Test
	public void testAddTags() throws Exception {
		InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/photos/sample_add_tags.json"));
		AddTags tags = new Gson().fromJson(reader, AddTags.class);
		reader.close();
		assertNotNull(tags);
		assertNotNull(tags.getTagList());
		assertEquals(3, tags.getTagList().size());
		Tag tag = tags.getTagList().get(0);
		assertEquals("124857539@N03", tag.getAuthor());
		assertEquals("location:country=Iceland", tag.getRaw());
		assertFalse(tag.isMachineTag());
		assertEquals("locationcountryiceland", tag.getTag());
		assertEquals("jinxlib", tag.getAuthorName());
		assertEquals("124834485-14276354684-57115208", tag.getFullTagId());
		assertEquals("57115208", tag.getTagId());
	}

	@Test
	public void testAllContexts() throws Exception {
		InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/photos/sample_all_contexts.json"));
		AllContexts contexts = new Gson().fromJson(reader, AllContexts.class);
		reader.close();
		assertNotNull(contexts);
		assertEquals("ok", contexts.getStat());
		assertEquals(0, contexts.getCode());
		assertNotNull(contexts.getSetList());
		assertNotNull(contexts.getPoolList());
		AllContexts.Set set = contexts.getSetList().get(0);
		assertEquals("Iceland", set.getTitle());
		assertEquals("72157644903065293", set.getPhotosetId());
		assertEquals("14090247837", set.getPrimary());
		assertEquals("a00c1c8c01", set.getSecret());
		assertEquals("3745", set.getServer());
		assertEquals(4, (int) set.getFarm());
		assertEquals(2, (int) set.getViewCount());
		assertEquals(1, (int) set.getCommentCount());
		assertEquals(17, (int) set.getCountPhoto());
		assertEquals(0, (int) set.getCountVideo());

		AllContexts.Pool pool = contexts.getPoolList().get(0);
		assertEquals("Private Jinx Test Group", pool.getTitle());
		assertEquals("/groups/2638254@N20/pool/", pool.getUrl());
		assertEquals("2638254@N20", pool.getPoolId());
		assertEquals("2934", pool.getIconServer());
		assertEquals(0, (int) pool.getIconFarm());
		assertEquals(1, (int) pool.getMembers());
		assertEquals(1, (int) pool.getPoolCount());
	}

	@Test
	public void testGetContactsPhotos() throws Exception {
		InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/photos/sample_get_contacts_photos.json"));
		PhotosResponse photosResponse = new Gson().fromJson(reader, PhotosResponse.class);
		reader.close();
		assertNotNull(photosResponse);
		assertEquals("ok", photosResponse.getStat());
		assertEquals(0, photosResponse.getCode());
		assertEquals("10556", photosResponse.getTotal());
		assertEquals(1, (int) photosResponse.getPage());
		assertEquals(5, (int) photosResponse.getPerPage());
		assertEquals("2111", photosResponse.getPages());

		List<Photo> photos = photosResponse.getPhotoList();
		assertNotNull(photos);
		assertTrue(photos.size() > 0);
		Photo p = photos.get(0);
		assertEquals("14284071805", p.getPhotoId());
		assertEquals("fcebcdf5c1", p.getSecret());
		assertEquals("3716", p.getServer());
		assertEquals(4, (int) p.getFarm());
		assertEquals("30265340@N00", p.getOwner());
		assertEquals("yewenyi", p.getUsername());
		assertEquals("Public Telephone", p.getTitle());
		assertTrue(p.isPublic());
		assertFalse(p.isFriend());
		assertFalse(p.isFamily());
		assertEquals(2, (int) p.getLicense());
		assertEquals("Description of this photo.", p.getDescription());
		assertEquals("3264", p.getoWidth());
		assertEquals("2448", p.getoHeight());
		assertEquals("1401208163", p.getDateUpload());
		assertEquals("1401208182", p.getLastUpdate());
		assertEquals("2014-05-26 18:23:44", p.getDateTaken());
		assertEquals(0, (int) p.getDateTakenGranularity());
		assertEquals("yewenyi", p.getOwnerName());
		assertEquals(32, (int) p.getIconServer());
		assertEquals(1, (int) p.getIconFarm());
		assertEquals(0, (int) p.getViews());
		assertEquals("station belgrave", p.getTags());
		assertEquals("", p.getMachineTags());
		assertEquals("9d4016832e", p.getOriginalSecret());
		assertEquals("jpg", p.getOriginalFormat());
		assertEquals(-37.909196, p.getLatitude());
		assertEquals(145.35505, p.getLongitude());
		assertEquals(16, (int) p.getAccuracy());
		assertEquals(0, (int) p.getContext());
		assertEquals("W9QPztNQUrMj2JPq", p.getPlaceId());
		assertEquals("1094328", p.getWoeId());
		assertFalse(p.isGeoIsFamily());
		assertFalse(p.isGeoIsFriend());
		assertFalse(p.isGeoIsContact());
		assertTrue(p.isGeoIsPublic());
		assertEquals("photo", p.getMedia());
		assertEquals("ready", p.getMediaStatus());

		assertEquals("https://farm4.staticflickr.com/3716/14284071805_fcebcdf5c1_s.jpg", p.getUrlSq());
		assertEquals(75, (int) p.getHeightSq());
		assertEquals(75, (int) p.getWidthSq());

		assertEquals("https://farm4.staticflickr.com/3716/14284071805_fcebcdf5c1_t.jpg", p.getUrlT());
		assertEquals(100, (int) p.getHeightT());
		assertEquals(75, (int) p.getWidthT());

		assertEquals("https://farm4.staticflickr.com/3716/14284071805_fcebcdf5c1_m.jpg", p.getUrlS());
		assertEquals(240, (int) p.getHeightS());
		assertEquals(180, (int) p.getWidthS());

		assertEquals("https://farm4.staticflickr.com/3716/14284071805_fcebcdf5c1_q.jpg", p.getUrlQ());
		assertEquals(150, (int) p.getHeightQ());
		assertEquals(150, (int) p.getWidthQ());

		assertEquals("https://farm4.staticflickr.com/3716/14284071805_fcebcdf5c1.jpg", p.getUrlM());
		assertEquals(500, (int) p.getHeightM());
		assertEquals(375, (int) p.getWidthM());

		assertEquals("https://farm4.staticflickr.com/3716/14284071805_fcebcdf5c1_n.jpg", p.getUrlN());
		assertEquals(320, (int) p.getHeightN());
		assertEquals(240, (int) p.getWidthN());

		assertEquals("https://farm4.staticflickr.com/3716/14284071805_fcebcdf5c1_z.jpg", p.getUrlZ());
		assertEquals(640, (int) p.getHeightZ());
		assertEquals(480, (int) p.getWidthZ());

		assertEquals("https://farm4.staticflickr.com/3716/14284071805_fcebcdf5c1_c.jpg", p.getUrlC());
		assertEquals(800, (int) p.getHeightC());
		assertEquals(600, (int) p.getWidthC());

		assertEquals("https://farm4.staticflickr.com/3716/14284071805_fcebcdf5c1_b.jpg", p.getUrlL());
		assertEquals(1024, (int) p.getHeightL());
		assertEquals(768, (int) p.getWidthL());

		assertEquals("https://farm4.staticflickr.com/3716/14284071805_9d4016832e_o.jpg", p.getUrlO());
		assertEquals(2448, (int) p.getHeightO());
		assertEquals(3264, (int) p.getWidthO());

		assertEquals("yewenyi", p.getPathAlias());
	}

	@Test
	public void testGetPhotocounts() throws Exception {
		InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/photos/sample_get_counts.json"));
		Photocounts photocounts = new Gson().fromJson(reader, Photocounts.class);
		reader.close();
		assertNotNull(photocounts);
		assertEquals("ok", photocounts.getStat());
		assertEquals(0, photocounts.getCode());
		assertNotNull(photocounts.getPhotocountList());
		Photocounts.Photocount photocount = photocounts.getPhotocountList().get(0);
		assertEquals(10, (int)photocount.getCount());
		assertEquals("1093566950", photocount.getFromDate());
		assertEquals("1093567000", photocount.getToDate());
	}
}
