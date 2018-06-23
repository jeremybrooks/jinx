/*
 * Jinx is Copyright 2010-2018 by Jeremy Brooks and Contributors
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

package net.jeremybrooks.jinx.response.photos;

import com.google.gson.Gson;
import net.jeremybrooks.jinx.JinxConstants;
import net.jeremybrooks.jinx.JinxUtils;
import net.jeremybrooks.jinx.response.activity.ActivityResponseTest;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * @author Jeremy Brooks
 */
public class PhotosTest {
	@Test
	public void testParseAddTags() throws Exception {
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
	public void testParseAllContexts() throws Exception {
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
		assertEquals("4", set.getFarm());
		assertEquals(2, (int) set.getViewCount());
		assertEquals(1, (int) set.getCommentCount());
		assertEquals(17, (int) set.getCountPhoto());
		assertEquals(0, (int) set.getCountVideo());

		AllContexts.Pool pool = contexts.getPoolList().get(0);
		assertEquals("Private Jinx Test Group", pool.getTitle());
		assertEquals("/groups/2638254@N20/pool/", pool.getUrl());
		assertEquals("2638254@N20", pool.getPoolId());
		assertEquals("2934", pool.getIconServer());
		assertEquals("0", pool.getIconFarm());
		assertEquals(1, (int) pool.getMembers());
		assertEquals(1, (int) pool.getPoolCount());
	}


	@Test
	public void testParseGetPhotocounts() throws Exception {
		InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/photos/sample_get_counts.json"));
		Photocounts photocounts = new Gson().fromJson(reader, Photocounts.class);
		reader.close();
		assertNotNull(photocounts);
		assertEquals("ok", photocounts.getStat());
		assertEquals(0, photocounts.getCode());
		assertNotNull(photocounts.getPhotocountList());
		Photocounts.Photocount photocount = photocounts.getPhotocountList().get(0);
		assertEquals(10, (int) photocount.getCount());
		assertEquals("1093566950", photocount.getFromDate());
		assertEquals("1093567000", photocount.getToDate());
	}

	@Test
	public void testParseGetExif() throws Exception {
		InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/photos/sample_exif.json"));
		ExifData exifData = new Gson().fromJson(reader, ExifData.class);
		reader.close();
		assertNotNull(exifData);
		assertEquals("ok", exifData.getStat());
		assertEquals(0, exifData.getCode());
		assertEquals("14101229538", exifData.getPhotoId());
		assertEquals("12fabee863", exifData.getSecret());
		assertEquals("5570", exifData.getServer());
		assertEquals("6", exifData.getFarm());
		assertEquals("Canon EOS 5D Mark II", exifData.getCamera());

		List<ExifData.Exif> exifList = exifData.getExifList();
		assertNotNull(exifList);
		assertEquals(4, exifList.size());
		ExifData.Exif exif = exifList.get(2);
		assertEquals("IFD0", exif.getTagSpace());
		assertEquals(0, (int) exif.getTagSpaceId());
		assertEquals("XResolution", exif.getTag());
		assertEquals("X-Resolution", exif.getLabel());
		assertEquals("72", exif.getRaw());
		assertEquals("72 dpi", exif.getClean());
	}

	@Test
	public void testParseGetFavorites() throws Exception {
		InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/photos/sample_get_favorites.json"));
		Favorites favorites = new Gson().fromJson(reader, Favorites.class);
		reader.close();
		assertNotNull(favorites);
		assertEquals("ok", favorites.getStat());
		assertEquals(0, favorites.getCode());
		assertEquals("14258801982", favorites.getPhotoId());
		assertEquals("43d6f39123", favorites.getSecret());
		assertEquals("3795", favorites.getServer());
		assertEquals("4", favorites.getFarm());
		assertEquals(1, (int) favorites.getPage());
		assertEquals(1, (int) favorites.getPages());
		assertEquals(10, (int) favorites.getPerPage());
		assertEquals(5, (int) favorites.getTotal());

		List<Favorites.Person> list = favorites.getPersonList();
		assertNotNull(list);
		assertEquals(5, list.size());
		Favorites.Person p = list.get(0);
		assertEquals("28087751@N06", p.getUserId());
		assertEquals("Freeze Time Digital", p.getUsername());
		assertEquals("Jason Ogulnik", p.getRealName());
		assertEquals("1401061945", p.getFaveDate());
		assertEquals("5234", p.getIconServer());
		assertEquals("6", p.getIconFarm());
		assertTrue(p.isContact());
		assertFalse(p.isFriend());
		assertFalse(p.isFamily());
	}

	@Test
	public void testParseGetPerms() throws Exception {
		InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/photos/sample_get_perms.json"));
		PhotoPerms perms = new Gson().fromJson(reader, PhotoPerms.class);
		reader.close();
		assertNotNull(perms);
		assertEquals("ok", perms.getStat());
		assertEquals(0, perms.getCode());
		assertEquals("14264974026", perms.getPhotoId());
		assertTrue(perms.isPublic());
		assertFalse(perms.isFriend());
		assertFalse(perms.isFamily());
		assertEquals(3, JinxUtils.permsToFlickrPermsId(perms.getPermComment()));
		assertEquals(2, JinxUtils.permsToFlickrPermsId(perms.getPermAddMeta()));
		assertEquals(JinxConstants.Perms.everybody, perms.getPermComment());
		assertEquals(JinxConstants.Perms.contacts, perms.getPermAddMeta());
	}

	@Test
	public void testParseGetSizes() throws Exception {
		InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/photos/sample_photo_sizes.json"));
		PhotoSizes sizes = new Gson().fromJson(reader, PhotoSizes.class);
		reader.close();
		assertNotNull(sizes);
		assertEquals("ok", sizes.getStat());
		assertEquals(0, sizes.getCode());
		assertTrue(sizes.isCanBlog());
		assertTrue(sizes.isCanDownload());
		assertTrue(sizes.isCanPrint());
		List<PhotoSizes.Size> sizeList = sizes.getSizeList();
		assertNotNull(sizeList);
		assertEquals(12, sizeList.size());
		PhotoSizes.Size size = sizeList.get(2);
		assertEquals("Thumbnail", size.getLabel());
		assertEquals(100, (int) size.getWidth());
		assertEquals(67, (int) size.getHeight());
		assertEquals("https://farm3.staticflickr.com/2918/14104674717_aa73bc9851_t.jpg", size.getSource());
		assertEquals("https://www.flickr.com/photos/jeremybrooks/14104674717/sizes/t/", size.getUrl());
		assertEquals("photo", size.getMedia());
	}

	public void testParseSetPerms() throws Exception {
		InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/photos/sample_set_perms.json"));
		PermsSetResponse psr = new Gson().fromJson(reader, PermsSetResponse.class);
		reader.close();
		assertNotNull(psr);
		assertEquals("ok", psr.getStat());
		assertEquals(0, psr.getCode());
		assertEquals("14100868708", psr.getPhotoId());
		assertEquals("3efe11f872", psr.getSecret());
		assertEquals("dfsddf", psr.getOriginalSecret());
	}

    /*
     * Read sample that comes back with bad data.
     * Then fix the data and try again.
     */
    public void testParseGetInfoError() throws Exception {
        InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/photos/sample_get_info_error.json"));
        BufferedReader br = new BufferedReader(reader);
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line).append('\n');
        }
        reader.close();
        String json = sb.toString();
        try {
            PhotoInfo photoInfo = new Gson().fromJson(json, PhotoInfo.class);
            fail();
        } catch (Exception e) {
            json = json.replace(":false", ":0");
            PhotoInfo photoInfo = new Gson().fromJson(json, PhotoInfo.class);
            assertNotNull(photoInfo);
        }
    }


	public void testParseGetInfo() throws Exception {
		InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/photos/sample_get_info.json"));
		PhotoInfo photoInfo = new Gson().fromJson(reader, PhotoInfo.class);
		reader.close();
		assertNotNull(photoInfo);
		assertEquals("ok", photoInfo.getStat());
		assertEquals(0, photoInfo.getCode());
		assertEquals("14118630850", photoInfo.getPhotoId());
		assertEquals("02b4c5b056", photoInfo.getSecret());
		assertEquals("5482", photoInfo.getServer());
		assertEquals("6", photoInfo.getFarm());
		assertEquals("1401457031", photoInfo.getDateUploaded());
		assertFalse(photoInfo.isFavorite());
		assertEquals(2, (int)photoInfo.getLicense());
		assertEquals(JinxConstants.SafetyLevel.safe, photoInfo.getSafetyLevel());
		assertEquals(0, (int)photoInfo.getRotation());
		assertEquals("24f8c12962", photoInfo.getOriginalSecret());
		assertEquals("jpg", photoInfo.getOriginalFormat());
		assertEquals("photo", photoInfo.getMedia());
		assertEquals(47, (int)photoInfo.getViews());

		assertEquals("85853333@N00", photoInfo.getOwnerUserId());
		assertEquals("Jeremy Brooks", photoInfo.getOwnerUsername());
		assertEquals("Jeremy Brooks", photoInfo.getOwnerRealName());
		assertEquals("", photoInfo.getOwnerLocation());
		assertEquals("5332", photoInfo.getOwnerIconServer());
		assertEquals("6", photoInfo.getOwnerIconFarm());
		assertEquals("jeremybrooks", photoInfo.getOwnerPathAlias());

		assertEquals("Transamerica View 20140530", photoInfo.getTitle());
		assertEquals("During 2014, I will be shooting the view from the building I work in. One photo per day, each day I am in the office. \n\nAll the images in this series can be seen <a href=\"https://www.flickr.com/photos/jeremybrooks/sets/72157639332256903/\">here</a>.", photoInfo.getDescription());
		assertTrue(photoInfo.isPublic());
		assertFalse(photoInfo.isFriend());
		assertFalse(photoInfo.isFamily());

		assertEquals("1401457031", photoInfo.getDatePosted());
		assertEquals("2014-05-30 06:31:07", photoInfo.getDateTaken());
		assertEquals(0, (int)photoInfo.getDateTakenGranularity());
		assertEquals("1401464981", photoInfo.getDateLastUpdate());

		assertEquals(3, JinxUtils.permsToFlickrPermsId(photoInfo.getPermComment()));
		assertEquals(2, JinxUtils.permsToFlickrPermsId(photoInfo.getPermAddMeta()));
		assertEquals(JinxConstants.Perms.everybody, photoInfo.getPermComment());
		assertEquals(JinxConstants.Perms.contacts, photoInfo.getPermAddMeta());

		assertTrue(photoInfo.isCanComment());
		assertTrue(photoInfo.isCanAddMeta());
		assertTrue(photoInfo.isPublicCanComment());
		assertFalse(photoInfo.isPublicCanAddMeta());
		assertTrue(photoInfo.isCanDownload());
		assertTrue(photoInfo.isCanBlog());
		assertTrue(photoInfo.isCanPrint());
		assertTrue(photoInfo.isCanShare());

		assertEquals(1, (int)photoInfo.getComments());

		assertNotNull(photoInfo.getNotes());
		assertEquals(1, photoInfo.getNotes().size());
		PhotoInfo.Note note = photoInfo.getNotes().get(0);
		assertEquals("72157644968346143", note.getNoteId());
		assertEquals("85853333@N00", note.getAuthor());
		assertEquals("Jeremy Brooks", note.getAuthorName());
		assertEquals(245, (int)note.getX());
		assertEquals(78, (int)note.getY());
		assertEquals(29, (int)note.getW());
		assertEquals(95, (int)note.getH());
		assertEquals("Pointy!", note.getNote());

		assertTrue(photoInfo.isHasPeople());

		assertNotNull(photoInfo.getTags());
		assertEquals(9, photoInfo.getTags().size());
		Tag tag = photoInfo.getTags().get(0);
		assertEquals("4956757-14118630850-50", tag.getTagId());
		assertEquals("85853333@N00", tag.getAuthor());
		assertEquals("Jeremy Brooks", tag.getAuthorName());
		assertEquals("California", tag.getRaw());
		assertEquals("california", tag.getTag());
		assertFalse(tag.isMachineTag());

		assertEquals(Float.valueOf(37.789899f), photoInfo.getLatitude());
		assertEquals(Float.valueOf(-122.401921f), photoInfo.getLongitude());
		assertEquals(15, (int)photoInfo.getAccuracy());
		assertEquals(0, (int)photoInfo.getContext());
		assertEquals("GddgqTpTUb8LgT93hw", photoInfo.getPlaceId());
		assertEquals("23512022", photoInfo.getWoeId());
		assertEquals("Financial District", photoInfo.getNeighbourhoodName());
		assertEquals("GddgqTpTUb8LgT93hw", photoInfo.getNeighbourhoodPlaceId());
		assertEquals("23512022", photoInfo.getNeighbourhoodWoeId());
		assertEquals("San Francisco", photoInfo.getLocalityName());
		assertEquals("7.MJR8tTVrIO1EgB", photoInfo.getLocalityPlaceId());
		assertEquals("2487956", photoInfo.getLocalityWoeId());
		assertEquals("San Francisco", photoInfo.getCountyName());
		assertEquals(".7sOmlRQUL9nK.kMzA", photoInfo.getCountyPlaceId());
		assertEquals("12587707", photoInfo.getCountyWoeId());
		assertEquals("California", photoInfo.getRegionName());
		assertEquals("NsbUWfBTUb4mbyVu", photoInfo.getRegionPlaceId());
		assertEquals("2347563", photoInfo.getRegionWoeId());
		assertEquals("United States", photoInfo.getCountryName());
		assertEquals("nz.gsghTUb4c2WAecA", photoInfo.getCountryPlaceId());
		assertEquals("23424977", photoInfo.getCountryWoeId());

		assertTrue(photoInfo.isGeoIsPublic());
		assertFalse(photoInfo.isGeoIsContact());
		assertFalse(photoInfo.isGeoIsFriend());
		assertFalse(photoInfo.isGeoIsFamily());

		assertNotNull(photoInfo.getUrls());
		assertEquals(1, photoInfo.getUrls().size());
		PhotoInfo.Url url = photoInfo.getUrls().get(0);
		assertEquals("photopage", url.getType());
		assertEquals("https://www.flickr.com/photos/jeremybrooks/14118630850/", url.getUrl());

        // test case where true/false is returned rather than 1/0
        tag = photoInfo.getTags().get(1);
        assertFalse(tag.isMachineTag());
    }



	//
	// the rest of this class tests responses that return Photos data - the tests are all very similar to each other
	//
	@Test
	public void testParseGetContactsPhotos() throws Exception {
		InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/photos/sample_get_contacts_photos.json"));
		Photos photosResponse = new Gson().fromJson(reader, Photos.class);
		reader.close();
		assertNotNull(photosResponse);
		assertEquals("ok", photosResponse.getStat());
		assertEquals(0, photosResponse.getCode());
		assertEquals(10556, (int) photosResponse.getTotal());
		assertEquals(1, (int) photosResponse.getPage());
		assertEquals(5, (int) photosResponse.getPerPage());
		assertEquals(2111, (int) photosResponse.getPages());

		List<Photo> photos = photosResponse.getPhotoList();
		assertNotNull(photos);
		assertTrue(photos.size() > 0);
		Photo p = photos.get(0);
		assertEquals("14284071805", p.getPhotoId());
		assertEquals("fcebcdf5c1", p.getSecret());
		assertEquals("3716", p.getServer());
		assertEquals("4", p.getFarm());
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
		assertEquals("32", p.getIconServer());
		assertEquals("1", p.getIconFarm());
		assertEquals(0, (int) p.getViews());
		assertEquals("station belgrave", p.getTags());
		assertEquals("", p.getMachineTags());
		assertEquals("9d4016832e", p.getOriginalSecret());
		assertEquals("jpg", p.getOriginalFormat());
		assertEquals(Float.valueOf(-37.909196f), p.getLatitude());
		assertEquals(Float.valueOf(145.35505f), p.getLongitude());
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
	public void testParseGetContactsPublicPhotos() throws Exception {
		InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/photos/sample_get_contacts_public_photos.json"));
		Photos photosResponse = new Gson().fromJson(reader, Photos.class);
		reader.close();
		assertNotNull(photosResponse);
		assertEquals("ok", photosResponse.getStat());
		assertEquals(0, photosResponse.getCode());
		assertEquals(799, (int) photosResponse.getTotal());
		assertEquals(1, (int) photosResponse.getPage());
		assertEquals(10, (int) photosResponse.getPerPage());
		assertEquals(80, (int) photosResponse.getPages());
		List<Photo> photos = photosResponse.getPhotoList();
		assertNotNull(photos);
		assertTrue(photos.size() > 0);
		Photo p = photos.get(0);
		assertEquals("14097029867", p.getPhotoId());
		assertEquals("d31e42a8fa", p.getSecret());
		assertEquals("5232", p.getServer());
		assertEquals("6", p.getFarm());
		assertEquals("65609008@N00", p.getOwner());
		assertEquals("the waving cat", p.getUsername());
		assertEquals("Muir Woods", p.getTitle());
		assertTrue(p.isPublic());
		assertFalse(p.isFriend());
		assertFalse(p.isFamily());
		assertEquals(1, (int) p.getLicense());
		assertEquals("San Francisco, May 2014.\n\nMuir Woords", p.getDescription());
		assertEquals("5472", p.getoWidth());
		assertEquals("3648", p.getoHeight());
		assertEquals("1401203955", p.getDateUpload());
		assertEquals("1401203964", p.getLastUpdate());
		assertEquals("2014-05-25 23:11:14", p.getDateTaken());
		assertEquals(0, (int) p.getDateTakenGranularity());
		assertEquals("the waving cat", p.getOwnerName());
		assertEquals("26", p.getIconServer());
		assertEquals("1", p.getIconFarm());
		assertEquals(2, (int) p.getViews());
		assertEquals("sanfrancisco travel usa muirwoods", p.getTags());
		assertEquals("", p.getMachineTags());
		assertEquals("c9a3f76573", p.getOriginalSecret());
		assertEquals("jpg", p.getOriginalFormat());
		assertEquals(Float.valueOf(0.0f), p.getLatitude());
		assertEquals(Float.valueOf(0.0f), p.getLongitude());
		assertEquals(0, (int) p.getAccuracy());
		assertEquals(0, (int) p.getContext());
		assertNull(p.getPlaceId());
		assertNull(p.getWoeId());
		assertNull(p.isGeoIsFamily());
		assertNull(p.isGeoIsFriend());
		assertNull(p.isGeoIsContact());
		assertNull(p.isGeoIsPublic());
		assertEquals("photo", p.getMedia());
		assertEquals("ready", p.getMediaStatus());
		assertEquals("https://farm6.staticflickr.com/5232/14097029867_d31e42a8fa_s.jpg", p.getUrlSq());
		assertEquals(75, (int) p.getHeightSq());
		assertEquals(75, (int) p.getWidthSq());
		assertEquals("https://farm6.staticflickr.com/5232/14097029867_d31e42a8fa_t.jpg", p.getUrlT());
		assertEquals(67, (int) p.getHeightT());
		assertEquals(100, (int) p.getWidthT());
		assertEquals("https://farm6.staticflickr.com/5232/14097029867_d31e42a8fa_m.jpg", p.getUrlS());
		assertEquals(160, (int) p.getHeightS());
		assertEquals(240, (int) p.getWidthS());
		assertEquals("https://farm6.staticflickr.com/5232/14097029867_d31e42a8fa_q.jpg", p.getUrlQ());
		assertEquals(150, (int) p.getHeightQ());
		assertEquals(150, (int) p.getWidthQ());
		assertEquals("https://farm6.staticflickr.com/5232/14097029867_d31e42a8fa.jpg", p.getUrlM());
		assertEquals(333, (int) p.getHeightM());
		assertEquals(500, (int) p.getWidthM());
		assertEquals("https://farm6.staticflickr.com/5232/14097029867_d31e42a8fa_n.jpg", p.getUrlN());
		assertEquals(213, (int) p.getHeightN());
		assertEquals(320, (int) p.getWidthN());
		assertEquals("https://farm6.staticflickr.com/5232/14097029867_d31e42a8fa_z.jpg", p.getUrlZ());
		assertEquals(427, (int) p.getHeightZ());
		assertEquals(640, (int) p.getWidthZ());
		assertEquals("https://farm6.staticflickr.com/5232/14097029867_d31e42a8fa_c.jpg", p.getUrlC());
		assertEquals(534, (int) p.getHeightC());
		assertEquals(800, (int) p.getWidthC());
		assertEquals("https://farm6.staticflickr.com/5232/14097029867_d31e42a8fa_b.jpg", p.getUrlL());
		assertEquals(683, (int) p.getHeightL());
		assertEquals(1024, (int) p.getWidthL());
		assertEquals("https://farm6.staticflickr.com/5232/14097029867_c9a3f76573_o.jpg", p.getUrlO());
		assertEquals(3648, (int) p.getHeightO());
		assertEquals(5472, (int) p.getWidthO());
		assertEquals("thewavingcat", p.getPathAlias());
	}

	@Test
	public void testParseGetNotInSet() throws Exception {
		InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/photos/sample_get_not_in_set.json"));
		Photos photosResponse = new Gson().fromJson(reader, Photos.class);
		reader.close();
		assertNotNull(photosResponse);
		assertEquals("ok", photosResponse.getStat());
		assertEquals(0, photosResponse.getCode());
		assertEquals(167, (int) photosResponse.getTotal());
		assertEquals(1, (int) photosResponse.getPage());
		assertEquals(5, (int) photosResponse.getPerPage());
		assertEquals(34, (int) photosResponse.getPages());

		List<Photo> photos = photosResponse.getPhotoList();
		assertNotNull(photos);
		assertTrue(photos.size() > 0);
		Photo p = photos.get(0);
		assertEquals("13939880800", p.getPhotoId());
		assertEquals("85853333@N00", p.getOwner());
		assertEquals("d46dd50329", p.getSecret());
		assertEquals("5273", p.getServer());
		assertEquals("6", p.getFarm());
		assertEquals("Hotel Ambassador", p.getTitle());
		assertNull(p.getUsername());
		assertTrue(p.isPublic());
		assertFalse(p.isFriend());
		assertFalse(p.isFamily());
		assertEquals(2, (int) p.getLicense());
		assertEquals("", p.getDescription());
		assertEquals("3744", p.getoWidth());
		assertEquals("5616", p.getoHeight());
		assertEquals("1399422719", p.getDateUpload());
		assertEquals("1399426876", p.getLastUpdate());
		assertEquals("2014-02-10 18:38:54", p.getDateTaken());
		assertEquals(0, (int) p.getDateTakenGranularity());
		assertEquals("Jeremy Brooks", p.getOwnerName());
		assertEquals("5332", p.getIconServer());
		assertEquals("6", p.getIconFarm());
		assertEquals(70, (int) p.getViews());
		assertEquals("sanfrancisco california blackandwhite bw usa hotel blackwhite neon sanfranciscocounty", p.getTags());
		assertEquals("", p.getMachineTags());
		assertEquals("72f2fd3ff2", p.getOriginalSecret());
		assertEquals("jpg", p.getOriginalFormat());
		assertEquals(Float.valueOf(0.0f), p.getLatitude());
		assertEquals(Float.valueOf(0.0f), p.getLongitude());
		assertEquals(0, (int) p.getAccuracy());
		assertEquals(0, (int) p.getContext());
		assertNull(p.getPlaceId());
		assertNull(p.getWoeId());
		assertNull(p.isGeoIsFamily());
		assertNull(p.isGeoIsFriend());
		assertNull(p.isGeoIsContact());
		assertNull(p.isGeoIsPublic());
		assertEquals("photo", p.getMedia());
		assertEquals("ready", p.getMediaStatus());

		assertEquals("https://farm6.staticflickr.com/5273/13939880800_d46dd50329_s.jpg", p.getUrlSq());
		assertEquals(75, (int) p.getHeightSq());
		assertEquals(75, (int) p.getWidthSq());

		assertEquals("https://farm6.staticflickr.com/5273/13939880800_d46dd50329_t.jpg", p.getUrlT());
		assertEquals(100, (int) p.getHeightT());
		assertEquals(67, (int) p.getWidthT());

		assertEquals("https://farm6.staticflickr.com/5273/13939880800_d46dd50329_m.jpg", p.getUrlS());
		assertEquals(240, (int) p.getHeightS());
		assertEquals(160, (int) p.getWidthS());

		assertEquals("https://farm6.staticflickr.com/5273/13939880800_d46dd50329_q.jpg", p.getUrlQ());
		assertEquals(150, (int) p.getHeightQ());
		assertEquals(150, (int) p.getWidthQ());

		assertEquals("https://farm6.staticflickr.com/5273/13939880800_d46dd50329.jpg", p.getUrlM());
		assertEquals(500, (int) p.getHeightM());
		assertEquals(333, (int) p.getWidthM());

		assertEquals("https://farm6.staticflickr.com/5273/13939880800_d46dd50329_n.jpg", p.getUrlN());
		assertEquals(320, (int) p.getHeightN());
		assertEquals(213, (int) p.getWidthN());

		assertEquals("https://farm6.staticflickr.com/5273/13939880800_d46dd50329_z.jpg", p.getUrlZ());
		assertEquals(640, (int) p.getHeightZ());
		assertEquals(427, (int) p.getWidthZ());

		assertEquals("https://farm6.staticflickr.com/5273/13939880800_d46dd50329_c.jpg", p.getUrlC());
		assertEquals(800, (int) p.getHeightC());
		assertEquals(534, (int) p.getWidthC());

		assertEquals("https://farm6.staticflickr.com/5273/13939880800_d46dd50329_b.jpg", p.getUrlL());
		assertEquals(1024, (int) p.getHeightL());
		assertEquals(683, (int) p.getWidthL());

		assertEquals("https://farm6.staticflickr.com/5273/13939880800_72f2fd3ff2_o.jpg", p.getUrlO());
		assertEquals(5616, (int) p.getHeightO());
		assertEquals(3744, (int) p.getWidthO());

		assertEquals("jeremybrooks", p.getPathAlias());
	}

	@Test
	public void testParseGetRecent() throws Exception {
		InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/photos/sample_get_recent.json"));
		Photos photosResponse = new Gson().fromJson(reader, Photos.class);
		reader.close();
		assertNotNull(photosResponse);
		assertEquals("ok", photosResponse.getStat());
		assertEquals(0, photosResponse.getCode());
		assertEquals(1000, (int) photosResponse.getTotal());
		assertEquals(1, (int) photosResponse.getPage());
		assertEquals(5, (int) photosResponse.getPerPage());
		assertEquals(200, (int) photosResponse.getPages());

		List<Photo> photos = photosResponse.getPhotoList();
		assertNotNull(photos);
		assertTrue(photos.size() > 0);
		Photo p = photos.get(0);
		assertEquals("14097596867", p.getPhotoId());
		assertEquals("100065775@N04", p.getOwner());
		assertEquals("df2b332d3c", p.getSecret());
		assertEquals("2930", p.getServer());
		assertEquals("3", p.getFarm());
		assertEquals("Sea of Galilee", p.getTitle());
		assertNull(p.getUsername());
		assertTrue(p.isPublic());
		assertFalse(p.isFriend());
		assertFalse(p.isFamily());
		assertEquals(0, (int) p.getLicense());
		assertEquals("", p.getDescription());
		assertNull(p.getoWidth());
		assertNull(p.getoHeight());
		assertEquals("1401208865", p.getDateUpload());
		assertEquals("1401208867", p.getLastUpdate());
		assertEquals("2013-01-14 13:15:37", p.getDateTaken());
		assertEquals(0, (int) p.getDateTakenGranularity());
		assertEquals("Sojourner 212", p.getOwnerName());
		assertEquals("3272", p.getIconServer());
		assertEquals("4", p.getIconFarm());
		assertEquals(0, (int) p.getViews());
		assertEquals("", p.getTags());
		assertEquals("", p.getMachineTags());
		assertEquals("2e05eb741c", p.getOriginalSecret());
		assertEquals("jpg", p.getOriginalFormat());
		assertEquals(Float.valueOf(0.0f), p.getLatitude());
		assertEquals(Float.valueOf(0.0f), p.getLongitude());
		assertEquals(0, (int) p.getAccuracy());
		assertEquals(0, (int) p.getContext());
		assertNull(p.getPlaceId());
		assertNull(p.getWoeId());
		assertNull(p.isGeoIsFamily());
		assertNull(p.isGeoIsFriend());
		assertNull(p.isGeoIsContact());
		assertNull(p.isGeoIsPublic());
		assertEquals("photo", p.getMedia());
		assertEquals("ready", p.getMediaStatus());

		assertEquals("https://farm3.staticflickr.com/2930/14097596867_df2b332d3c_s.jpg", p.getUrlSq());
		assertEquals(75, (int) p.getHeightSq());
		assertEquals(75, (int) p.getWidthSq());

		assertEquals("https://farm3.staticflickr.com/2930/14097596867_df2b332d3c_t.jpg", p.getUrlT());
		assertEquals(75, (int) p.getHeightT());
		assertEquals(100, (int) p.getWidthT());

		assertEquals("https://farm3.staticflickr.com/2930/14097596867_df2b332d3c_m.jpg", p.getUrlS());
		assertEquals(180, (int) p.getHeightS());
		assertEquals(240, (int) p.getWidthS());

		assertEquals("https://farm3.staticflickr.com/2930/14097596867_df2b332d3c_q.jpg", p.getUrlQ());
		assertEquals(150, (int) p.getHeightQ());
		assertEquals(150, (int) p.getWidthQ());

		assertEquals("https://farm3.staticflickr.com/2930/14097596867_df2b332d3c.jpg", p.getUrlM());
		assertEquals(375, (int) p.getHeightM());
		assertEquals(500, (int) p.getWidthM());

		assertEquals("https://farm3.staticflickr.com/2930/14097596867_df2b332d3c_n.jpg", p.getUrlN());
		assertEquals(240, (int) p.getHeightN());
		assertEquals(320, (int) p.getWidthN());

		assertEquals("https://farm3.staticflickr.com/2930/14097596867_df2b332d3c_z.jpg", p.getUrlZ());
		assertEquals(480, (int) p.getHeightZ());
		assertEquals(640, (int) p.getWidthZ());

		assertEquals("https://farm3.staticflickr.com/2930/14097596867_df2b332d3c_c.jpg", p.getUrlC());
		assertEquals(600, (int) p.getHeightC());
		assertEquals(800, (int) p.getWidthC());

		assertEquals("https://farm3.staticflickr.com/2930/14097596867_df2b332d3c_b.jpg", p.getUrlL());
		assertEquals(768, (int) p.getHeightL());
		assertEquals(1024, (int) p.getWidthL());

		assertEquals("https://farm3.staticflickr.com/2930/14097596867_2e05eb741c_o.jpg", p.getUrlO());
		assertEquals(768, (int) p.getHeightO());
		assertEquals(1024, (int) p.getWidthO());

		assertEquals("swanst01", p.getPathAlias());
	}

	@Test
	public void testParseGetUntagged() throws Exception {
		InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/photos/sample_get_untagged.json"));
		Photos photosResponse = new Gson().fromJson(reader, Photos.class);
		reader.close();
		assertNotNull(photosResponse);
		assertEquals("ok", photosResponse.getStat());
		assertEquals(0, photosResponse.getCode());
		assertEquals(21, (int) photosResponse.getTotal());
		assertEquals(1, (int) photosResponse.getPage());
		assertEquals(5, (int) photosResponse.getPerPage());
		assertEquals(5, (int) photosResponse.getPages());
		List<Photo> photos = photosResponse.getPhotoList();
		assertNotNull(photos);
		assertTrue(photos.size() > 0);
		Photo p = photos.get(0);
		assertEquals("9941510135", p.getPhotoId());
		assertEquals("85853333@N00", p.getOwner());
		assertEquals("f76a4e122e", p.getSecret());
		assertEquals("2855", p.getServer());
		assertEquals("3", p.getFarm());
		assertEquals("Testing sharing from new version of Interlacer", p.getTitle());
		assertNull(p.getUsername());
		assertTrue(p.isPublic());
		assertFalse(p.isFriend());
		assertFalse(p.isFamily());
		assertEquals(2, (int) p.getLicense());
		assertEquals("", p.getDescription());
		assertEquals("1836", p.getoWidth());
		assertEquals("1836", p.getoHeight());
		assertEquals("1380150334", p.getDateUpload());
		assertEquals("1380218137", p.getLastUpdate());
		assertEquals("2013-09-25 16:05:34", p.getDateTaken());
		assertEquals(0, (int) p.getDateTakenGranularity());
		assertEquals("Jeremy Brooks", p.getOwnerName());
		assertEquals("5332", p.getIconServer());
		assertEquals("6", p.getIconFarm());
		assertEquals(383, (int) p.getViews());
		assertEquals("", p.getTags());
		assertEquals("", p.getMachineTags());
		assertEquals("720b85cdbe", p.getOriginalSecret());
		assertEquals("jpg", p.getOriginalFormat());
		assertEquals(Float.valueOf(0.0f), p.getLatitude());
		assertEquals(Float.valueOf(0.0f), p.getLongitude());
		assertEquals(0, (int) p.getAccuracy());
		assertEquals(0, (int) p.getContext());
		assertNull(p.getPlaceId());
		assertNull(p.getWoeId());
		assertNull(p.isGeoIsFamily());
		assertNull(p.isGeoIsFriend());
		assertNull(p.isGeoIsContact());
		assertNull(p.isGeoIsPublic());
		assertEquals("photo", p.getMedia());
		assertEquals("ready", p.getMediaStatus());

		assertEquals("https://farm3.staticflickr.com/2855/9941510135_f76a4e122e_s.jpg", p.getUrlSq());
		assertEquals(75, (int) p.getHeightSq());
		assertEquals(75, (int) p.getWidthSq());

		assertEquals("https://farm3.staticflickr.com/2855/9941510135_f76a4e122e_t.jpg", p.getUrlT());
		assertEquals(100, (int) p.getHeightT());
		assertEquals(100, (int) p.getWidthT());

		assertEquals("https://farm3.staticflickr.com/2855/9941510135_f76a4e122e_m.jpg", p.getUrlS());
		assertEquals(240, (int) p.getHeightS());
		assertEquals(240, (int) p.getWidthS());

		assertEquals("https://farm3.staticflickr.com/2855/9941510135_f76a4e122e_q.jpg", p.getUrlQ());
		assertEquals(150, (int) p.getHeightQ());
		assertEquals(150, (int) p.getWidthQ());

		assertEquals("https://farm3.staticflickr.com/2855/9941510135_f76a4e122e.jpg", p.getUrlM());
		assertEquals(500, (int) p.getHeightM());
		assertEquals(500, (int) p.getWidthM());

		assertEquals("https://farm3.staticflickr.com/2855/9941510135_f76a4e122e_n.jpg", p.getUrlN());
		assertEquals(320, (int) p.getHeightN());
		assertEquals(320, (int) p.getWidthN());

		assertEquals("https://farm3.staticflickr.com/2855/9941510135_f76a4e122e_z.jpg", p.getUrlZ());
		assertEquals(640, (int) p.getHeightZ());
		assertEquals(640, (int) p.getWidthZ());

		assertEquals("https://farm3.staticflickr.com/2855/9941510135_f76a4e122e_c.jpg", p.getUrlC());
		assertEquals(800, (int) p.getHeightC());
		assertEquals(800, (int) p.getWidthC());

		assertEquals("https://farm3.staticflickr.com/2855/9941510135_f76a4e122e_b.jpg", p.getUrlL());
		assertEquals(1024, (int) p.getHeightL());
		assertEquals(1024, (int) p.getWidthL());

		assertEquals("https://farm3.staticflickr.com/2855/9941510135_720b85cdbe_o.jpg", p.getUrlO());
		assertEquals(1836, (int) p.getHeightO());
		assertEquals(1836, (int) p.getWidthO());

		assertEquals("jeremybrooks", p.getPathAlias());
	}

	@Test
	public void testParseRecentlyUpdated() throws Exception {
		InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/photos/sample_recently_updated.json"));
		Photos photosResponse = new Gson().fromJson(reader, Photos.class);
		reader.close();
		assertNotNull(photosResponse);
		assertEquals("ok", photosResponse.getStat());
		assertEquals(0, photosResponse.getCode());
		assertEquals(17, (int) photosResponse.getTotal());
		assertEquals(1, (int) photosResponse.getPage());
		assertEquals(5, (int) photosResponse.getPerPage());
		assertEquals(4, (int) photosResponse.getPages());
		List<Photo> photos = photosResponse.getPhotoList();
		assertNotNull(photos);
		assertTrue(photos.size() > 0);
		Photo p = photos.get(0);

		assertEquals("14090003790", p.getPhotoId());
		assertEquals("85853333@N00", p.getOwner());
		assertEquals("27b86fe896", p.getSecret());
		assertEquals("3730", p.getServer());
		assertEquals("4", p.getFarm());
		assertEquals("550 540", p.getTitle());
		assertNull(p.getUsername());
		assertTrue(p.isPublic());
		assertFalse(p.isFriend());
		assertFalse(p.isFamily());
		assertEquals(2, (int) p.getLicense());
		assertEquals("", p.getDescription());
		assertEquals("2448", p.getoWidth());
		assertEquals("2448", p.getoHeight());
		assertEquals("1401130664", p.getDateUpload());
		assertEquals("1401200322", p.getLastUpdate());
		assertEquals("2013-09-17 16:37:16", p.getDateTaken());
		assertEquals(0, (int) p.getDateTakenGranularity());
		assertEquals("Jeremy Brooks", p.getOwnerName());
		assertEquals("5332", p.getIconServer());
		assertEquals("6", p.getIconFarm());
		assertEquals(32, (int) p.getViews());
		assertEquals("sanfrancisco california street door usa storefront address iphone sanfranciscocounty", p.getTags());
		assertEquals("", p.getMachineTags());
		assertEquals("c57e4fb02e", p.getOriginalSecret());
		assertEquals("jpg", p.getOriginalFormat());
		assertEquals(Float.valueOf(0.0f), p.getLatitude());
		assertEquals(Float.valueOf(0.0f), p.getLongitude());
		assertEquals(0, (int) p.getAccuracy());
		assertEquals(0, (int) p.getContext());
		assertNull(p.getPlaceId());
		assertNull(p.getWoeId());
		assertNull(p.isGeoIsFamily());
		assertNull(p.isGeoIsFriend());
		assertNull(p.isGeoIsContact());
		assertNull(p.isGeoIsPublic());
		assertEquals("photo", p.getMedia());
		assertEquals("ready", p.getMediaStatus());

		assertEquals("https://farm4.staticflickr.com/3730/14090003790_27b86fe896_s.jpg", p.getUrlSq());
		assertEquals(75, (int) p.getHeightSq());
		assertEquals(75, (int) p.getWidthSq());

		assertEquals("https://farm4.staticflickr.com/3730/14090003790_27b86fe896_t.jpg", p.getUrlT());
		assertEquals(100, (int) p.getHeightT());
		assertEquals(100, (int) p.getWidthT());

		assertEquals("https://farm4.staticflickr.com/3730/14090003790_27b86fe896_m.jpg", p.getUrlS());
		assertEquals(240, (int) p.getHeightS());
		assertEquals(240, (int) p.getWidthS());

		assertEquals("https://farm4.staticflickr.com/3730/14090003790_27b86fe896_q.jpg", p.getUrlQ());
		assertEquals(150, (int) p.getHeightQ());
		assertEquals(150, (int) p.getWidthQ());

		assertEquals("https://farm4.staticflickr.com/3730/14090003790_27b86fe896.jpg", p.getUrlM());
		assertEquals(500, (int) p.getHeightM());
		assertEquals(500, (int) p.getWidthM());

		assertEquals("https://farm4.staticflickr.com/3730/14090003790_27b86fe896_n.jpg", p.getUrlN());
		assertEquals(320, (int) p.getHeightN());
		assertEquals(320, (int) p.getWidthN());

		assertEquals("https://farm4.staticflickr.com/3730/14090003790_27b86fe896_z.jpg", p.getUrlZ());
		assertEquals(640, (int) p.getHeightZ());
		assertEquals(640, (int) p.getWidthZ());

		assertEquals("https://farm4.staticflickr.com/3730/14090003790_27b86fe896_c.jpg", p.getUrlC());
		assertEquals(800, (int) p.getHeightC());
		assertEquals(800, (int) p.getWidthC());

		assertEquals("https://farm4.staticflickr.com/3730/14090003790_27b86fe896_b.jpg", p.getUrlL());
		assertEquals(1024, (int) p.getHeightL());
		assertEquals(1024, (int) p.getWidthL());

		assertEquals("https://farm4.staticflickr.com/3730/14090003790_c57e4fb02e_o.jpg", p.getUrlO());
		assertEquals(2448, (int) p.getHeightO());
		assertEquals(2448, (int) p.getWidthO());

		assertEquals("jeremybrooks", p.getPathAlias());
	}

	@Test
	public void testParseSearch() throws Exception {
		InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/photos/sample_search.json"));
		Photos photosResponse = new Gson().fromJson(reader, Photos.class);
		reader.close();
		assertNotNull(photosResponse);
		assertEquals("ok", photosResponse.getStat());
		assertEquals(0, photosResponse.getCode());
		assertEquals(799, (int) photosResponse.getTotal());
		assertEquals(1, (int) photosResponse.getPage());
		assertEquals(5, (int) photosResponse.getPerPage());
		assertEquals(160, (int) photosResponse.getPages());
		List<Photo> photos = photosResponse.getPhotoList();
		assertNotNull(photos);
		assertTrue(photos.size() > 0);
		Photo p = photos.get(0);

		assertEquals("14256698876", p.getPhotoId());
		assertEquals("21945957@N00", p.getOwner());
		assertEquals("2b00fa5537", p.getSecret());
		assertEquals("3744", p.getServer());
		assertEquals("4", p.getFarm());
		assertEquals("San Francisco Spirit", p.getTitle());
		assertNull(p.getUsername());
		assertTrue(p.isPublic());
		assertFalse(p.isFriend());
		assertFalse(p.isFamily());
		assertEquals(0, (int) p.getLicense());
		assertEquals("San Francisco", p.getDescription());
		assertEquals("1098", p.getoWidth());
		assertEquals("1333", p.getoHeight());
		assertEquals("1401160084", p.getDateUpload());
		assertEquals("1401160649", p.getLastUpdate());
		assertEquals("2014-05-23 00:00:03", p.getDateTaken());
		assertEquals(0, (int) p.getDateTakenGranularity());
		assertEquals("a.k.a. Flash", p.getOwnerName());
		assertEquals("8555", p.getIconServer());
		assertEquals("9", p.getIconFarm());
		assertEquals(49, (int) p.getViews());
		assertEquals("bw film polaroid 600 impossible 2014 roidweek2014", p.getTags());
		assertEquals("", p.getMachineTags());
		assertEquals("f985684cf1", p.getOriginalSecret());
		assertEquals("jpg", p.getOriginalFormat());
		assertEquals(Float.valueOf(0.0f), p.getLatitude());
		assertEquals(Float.valueOf(0.0f), p.getLongitude());
		assertEquals(0, (int) p.getAccuracy());
		assertEquals(0, (int) p.getContext());
		assertNull(p.getPlaceId());
		assertNull(p.getWoeId());
		assertNull(p.isGeoIsFamily());
		assertNull(p.isGeoIsFriend());
		assertNull(p.isGeoIsContact());
		assertNull(p.isGeoIsPublic());
		assertEquals("photo", p.getMedia());
		assertEquals("ready", p.getMediaStatus());

		assertEquals("https://farm4.staticflickr.com/3744/14256698876_2b00fa5537_s.jpg", p.getUrlSq());
		assertEquals(75, (int) p.getHeightSq());
		assertEquals(75, (int) p.getWidthSq());

		assertEquals("https://farm4.staticflickr.com/3744/14256698876_2b00fa5537_t.jpg", p.getUrlT());
		assertEquals(100, (int) p.getHeightT());
		assertEquals(82, (int) p.getWidthT());

		assertEquals("https://farm4.staticflickr.com/3744/14256698876_2b00fa5537_m.jpg", p.getUrlS());
		assertEquals(240, (int) p.getHeightS());
		assertEquals(198, (int) p.getWidthS());

		assertEquals("https://farm4.staticflickr.com/3744/14256698876_2b00fa5537_q.jpg", p.getUrlQ());
		assertEquals(150, (int) p.getHeightQ());
		assertEquals(150, (int) p.getWidthQ());

		assertEquals("https://farm4.staticflickr.com/3744/14256698876_2b00fa5537.jpg", p.getUrlM());
		assertEquals(500, (int) p.getHeightM());
		assertEquals(412, (int) p.getWidthM());

		assertEquals("https://farm4.staticflickr.com/3744/14256698876_2b00fa5537_n.jpg", p.getUrlN());
		assertEquals(320, (int) p.getHeightN());
		assertEquals(263, (int) p.getWidthN());

		assertEquals("https://farm4.staticflickr.com/3744/14256698876_2b00fa5537_z.jpg", p.getUrlZ());
		assertEquals(640, (int) p.getHeightZ());
		assertEquals(527, (int) p.getWidthZ());

		assertEquals("https://farm4.staticflickr.com/3744/14256698876_2b00fa5537_c.jpg", p.getUrlC());
		assertEquals(800, (int) p.getHeightC());
		assertEquals(659, (int) p.getWidthC());

		assertEquals("https://farm4.staticflickr.com/3744/14256698876_2b00fa5537_b.jpg", p.getUrlL());
		assertEquals(1024, (int) p.getHeightL());
		assertEquals(843, (int) p.getWidthL());

		assertEquals("https://farm4.staticflickr.com/3744/14256698876_f985684cf1_o.jpg", p.getUrlO());
		assertEquals(1333, (int) p.getHeightO());
		assertEquals(1098, (int) p.getWidthO());

		assertEquals("mcordell", p.getPathAlias());
	}

	@Test
	public void TestParseWithGeoData() throws Exception {
		InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/photos/sample_with_geo_data.json"));
		Photos photosResponse = new Gson().fromJson(reader, Photos.class);
		reader.close();
		assertNotNull(photosResponse);
		assertEquals("ok", photosResponse.getStat());
		assertEquals(0, photosResponse.getCode());
		assertEquals(5089, (int) photosResponse.getTotal());
		assertEquals(1, (int) photosResponse.getPage());
		assertEquals(5, (int) photosResponse.getPerPage());
		assertEquals(1018, (int) photosResponse.getPages());
		List<Photo> photos = photosResponse.getPhotoList();
		assertNotNull(photos);
		assertTrue(photos.size() > 0);
		Photo p = photos.get(0);
		assertEquals("10586602103", p.getPhotoId());
		assertEquals("85853333@N00", p.getOwner());
		assertEquals("57a1a211dc", p.getSecret());
		assertEquals("3823", p.getServer());
		assertEquals("4", p.getFarm());
		assertEquals("Computer Repair Fax Center", p.getTitle());
		assertNull(p.getUsername());
		assertTrue(p.isPublic());
		assertFalse(p.isFriend());
		assertFalse(p.isFamily());
		assertEquals(2, (int) p.getLicense());
		assertEquals("", p.getDescription());
		assertEquals("5616", p.getoWidth());
		assertEquals("3744", p.getoHeight());
		assertEquals("1383195989", p.getDateUpload());
		assertEquals("1383250399", p.getLastUpdate());
		assertEquals("2010-10-14 21:05:22", p.getDateTaken());
		assertEquals(0, (int) p.getDateTakenGranularity());
		assertEquals("Jeremy Brooks", p.getOwnerName());
		assertEquals("5332", p.getIconServer());
		assertEquals("6", p.getIconFarm());
		assertEquals(604, (int) p.getViews());
		assertEquals("california ca usa computer mouse neon unitedstates alameda alamedacounty fax vision:text=0652 vision:sky=0706 vision:outdoor=064 vision:dark=08", p.getTags());
		assertEquals("", p.getMachineTags());
		assertEquals("568a3f2614", p.getOriginalSecret());
		assertEquals("jpg", p.getOriginalFormat());
		assertEquals(Float.valueOf(37.765819f), p.getLatitude());
		assertEquals(Float.valueOf(-122.242912f), p.getLongitude());
		assertEquals(16, (int) p.getAccuracy());
		assertEquals(0, (int) p.getContext());
		assertEquals("FmOEZR5UV7P7KfBdxg", p.getPlaceId());
		assertEquals("55969039", p.getWoeId());
		assertFalse(p.isGeoIsFamily());
		assertFalse(p.isGeoIsFriend());
		assertFalse(p.isGeoIsContact());
		assertTrue(p.isGeoIsPublic());
		assertEquals("photo", p.getMedia());
		assertEquals("ready", p.getMediaStatus());

		assertEquals("https://farm4.staticflickr.com/3823/10586602103_57a1a211dc_s.jpg", p.getUrlSq());
		assertEquals(75, (int) p.getHeightSq());
		assertEquals(75, (int) p.getWidthSq());

		assertEquals("https://farm4.staticflickr.com/3823/10586602103_57a1a211dc_t.jpg", p.getUrlT());
		assertEquals(67, (int) p.getHeightT());
		assertEquals(100, (int) p.getWidthT());

		assertEquals("https://farm4.staticflickr.com/3823/10586602103_57a1a211dc_m.jpg", p.getUrlS());
		assertEquals(160, (int) p.getHeightS());
		assertEquals(240, (int) p.getWidthS());

		assertEquals("https://farm4.staticflickr.com/3823/10586602103_57a1a211dc_q.jpg", p.getUrlQ());
		assertEquals(150, (int) p.getHeightQ());
		assertEquals(150, (int) p.getWidthQ());

		assertEquals("https://farm4.staticflickr.com/3823/10586602103_57a1a211dc.jpg", p.getUrlM());
		assertEquals(333, (int) p.getHeightM());
		assertEquals(500, (int) p.getWidthM());

		assertEquals("https://farm4.staticflickr.com/3823/10586602103_57a1a211dc_n.jpg", p.getUrlN());
		assertEquals(213, (int) p.getHeightN());
		assertEquals(320, (int) p.getWidthN());

		assertEquals("https://farm4.staticflickr.com/3823/10586602103_57a1a211dc_z.jpg", p.getUrlZ());
		assertEquals(427, (int) p.getHeightZ());
		assertEquals(640, (int) p.getWidthZ());

		assertEquals("https://farm4.staticflickr.com/3823/10586602103_57a1a211dc_c.jpg", p.getUrlC());
		assertEquals(534, (int) p.getHeightC());
		assertEquals(800, (int) p.getWidthC());

		assertEquals("https://farm4.staticflickr.com/3823/10586602103_57a1a211dc_b.jpg", p.getUrlL());
		assertEquals(683, (int) p.getHeightL());
		assertEquals(1024, (int) p.getWidthL());

		assertEquals("https://farm4.staticflickr.com/3823/10586602103_568a3f2614_o.jpg", p.getUrlO());
		assertEquals(3744, (int) p.getHeightO());
		assertEquals(5616, (int) p.getWidthO());

		assertEquals("jeremybrooks", p.getPathAlias());
	}

	@Test
	public void TestParseWithoutGeoData() throws Exception {
		InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/photos/sample_without_geo_data.json"));
		Photos photosResponse = new Gson().fromJson(reader, Photos.class);
		reader.close();
		assertNotNull(photosResponse);
		assertEquals("ok", photosResponse.getStat());
		assertEquals(0, photosResponse.getCode());
		assertEquals(18115, (int) photosResponse.getTotal());
		assertEquals(1, (int) photosResponse.getPage());
		assertEquals(5, (int) photosResponse.getPerPage());
		assertEquals(3623, (int) photosResponse.getPages());
		List<Photo> photos = photosResponse.getPhotoList();
		assertNotNull(photos);
		assertTrue(photos.size() > 0);
		Photo p = photos.get(0);
		assertEquals("14255762626", p.getPhotoId());
		assertEquals("85853333@N00", p.getOwner());
		assertEquals("590f6b72ae", p.getSecret());
		assertEquals("3722", p.getServer());
		assertEquals("4", p.getFarm());
		assertEquals("Sleeping With Eyes Open", p.getTitle());
		assertNull(p.getUsername());
		assertTrue(p.isPublic());
		assertFalse(p.isFriend());
		assertFalse(p.isFamily());
		assertEquals(2, (int) p.getLicense());
		assertEquals("<a href=\"http://photochallenge.org/2013/12/13/2014-will-be/\" rel=\"nofollow\">2014 Challenge</a>, Week 20/52: LANDSCAPE - MINIMALISM", p.getDescription());
		assertEquals("3657", p.getoWidth());
		assertEquals("5485", p.getoHeight());
		assertEquals("1401150705", p.getDateUpload());
		assertEquals("1401198012", p.getLastUpdate());
		assertEquals("2014-05-24 17:11:13", p.getDateTaken());
		assertEquals(0, (int) p.getDateTakenGranularity());
		assertEquals("Jeremy Brooks", p.getOwnerName());
		assertEquals("5332", p.getIconServer());
		assertEquals("6", p.getIconFarm());
		assertEquals(189, (int) p.getViews());
		assertEquals("california sky usa water landscape bay richmond minimalism photochallenge contracostacounty 2014challenge", p.getTags());
		assertEquals("", p.getMachineTags());
		assertEquals("80846b32e2", p.getOriginalSecret());
		assertEquals("jpg", p.getOriginalFormat());
		assertEquals(Float.valueOf(0.0f), p.getLatitude());
		assertEquals(Float.valueOf(0.0f), p.getLongitude());
		assertEquals(0, (int) p.getAccuracy());
		assertEquals(0, (int) p.getContext());
		assertNull(p.getPlaceId());
		assertNull(p.getWoeId());
		assertNull(p.isGeoIsFamily());
		assertNull(p.isGeoIsFriend());
		assertNull(p.isGeoIsContact());
		assertNull(p.isGeoIsPublic());
		assertEquals("photo", p.getMedia());
		assertEquals("ready", p.getMediaStatus());

		assertEquals("https://farm4.staticflickr.com/3722/14255762626_590f6b72ae_s.jpg", p.getUrlSq());
		assertEquals(75, (int) p.getHeightSq());
		assertEquals(75, (int) p.getWidthSq());

		assertEquals("https://farm4.staticflickr.com/3722/14255762626_590f6b72ae_t.jpg", p.getUrlT());
		assertEquals(100, (int) p.getHeightT());
		assertEquals(67, (int) p.getWidthT());

		assertEquals("https://farm4.staticflickr.com/3722/14255762626_590f6b72ae_m.jpg", p.getUrlS());
		assertEquals(240, (int) p.getHeightS());
		assertEquals(160, (int) p.getWidthS());

		assertEquals("https://farm4.staticflickr.com/3722/14255762626_590f6b72ae_q.jpg", p.getUrlQ());
		assertEquals(150, (int) p.getHeightQ());
		assertEquals(150, (int) p.getWidthQ());

		assertEquals("https://farm4.staticflickr.com/3722/14255762626_590f6b72ae.jpg", p.getUrlM());
		assertEquals(500, (int) p.getHeightM());
		assertEquals(333, (int) p.getWidthM());

		assertEquals("https://farm4.staticflickr.com/3722/14255762626_590f6b72ae_n.jpg", p.getUrlN());
		assertEquals(320, (int) p.getHeightN());
		assertEquals(213, (int) p.getWidthN());

		assertEquals("https://farm4.staticflickr.com/3722/14255762626_590f6b72ae_z.jpg", p.getUrlZ());
		assertEquals(640, (int) p.getHeightZ());
		assertEquals(427, (int) p.getWidthZ());

		assertEquals("https://farm4.staticflickr.com/3722/14255762626_590f6b72ae_c.jpg", p.getUrlC());
		assertEquals(800, (int) p.getHeightC());
		assertEquals(534, (int) p.getWidthC());

		assertEquals("https://farm4.staticflickr.com/3722/14255762626_590f6b72ae_b.jpg", p.getUrlL());
		assertEquals(1024, (int) p.getHeightL());
		assertEquals(683, (int) p.getWidthL());

		assertEquals("https://farm4.staticflickr.com/3722/14255762626_80846b32e2_o.jpg", p.getUrlO());
		assertEquals(5485, (int) p.getHeightO());
		assertEquals(3657, (int) p.getWidthO());

		assertEquals("jeremybrooks", p.getPathAlias());
	}
}
