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

package net.jeremybrooks.jinx.response.people;

import com.google.gson.Gson;
import net.jeremybrooks.jinx.JinxUtils;
import net.jeremybrooks.jinx.response.activity.ActivityResponseTest;
import net.jeremybrooks.jinx.response.groups.GroupInfo;
import net.jeremybrooks.jinx.response.groups.Groups;
import net.jeremybrooks.jinx.response.photos.Photo;
import net.jeremybrooks.jinx.response.photos.Photos;
import org.junit.Test;

import java.io.InputStreamReader;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by jeremyb on 7/14/14.
 */
public class PeopleTest {
    @Test
    public void testParseGetInfo() throws Exception {
        InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/people/sample_get_info.json"));
        Person person = new Gson().fromJson(reader, Person.class);
        reader.close();
        assertNotNull(person);
        assertEquals("ok", person.getStat());
        assertEquals(0, person.getCode());
        assertEquals("124857539@N03", person.getUserId());
        assertFalse(person.isPro());
        assertEquals("5538", person.getIconServer());
        assertEquals("6", person.getIconFarm());
        assertEquals("jinxlib", person.getPathAlias());
        assertEquals("X", person.getGender());
        assertFalse(person.isIgnored());
        assertFalse(person.isContact());
        assertFalse(person.isFriend());
        assertFalse(person.isFamily());
        assertTrue(person.isRevContact());
        assertFalse(person.isRevFamily());
        assertFalse(person.isRevFriend());
        assertEquals("jinxlib", person.getUsername());
        assertEquals("Jinx Library", person.getRealname());
        assertEquals("", person.getLocation());
        assertEquals("Pacific Time (US & Canada); Tijuana", person.getTimezoneLabel());
        assertEquals("-08:00", person.getTimezoneOffset());
        assertEquals("This is the test account for the Jinx library. \n\nJinx is a Java library for the Flickr API. It is written and maintained by <a href=\"https://www.flickr.com/photos/jeremybrooks/\">Jeremy Brooks</a>. Photos on this account are by Jeremy Brooks.\n\nThis library is used by <a href=\"http://jeremybrooks.net/suprsetr/\" rel=\"nofollow\">SuprSetr</a>, a program that makes managing your Flickr albums easy.", person.getDescription());
        assertEquals("https://www.flickr.com/photos/jinxlib/", person.getPhotosUrl());
        assertEquals("https://www.flickr.com/people/jinxlib/", person.getProfileUrl());
        assertEquals("https://m.flickr.com/photostream.gne?id=124834485", person.getMobileUrl());
        assertEquals("2008-07-08 16:48:54", person.getPhotosFirstDateTaken());
        assertEquals("1400909358", person.getPhotosFirstDate());
        assertEquals(new Integer(45), person.getPhotosCount());
    }

    @Test
    public void testParseFindByEmail() throws Exception {
        InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/people/sample_find_by_email.json"));
        Person person = new Gson().fromJson(reader, Person.class);
        reader.close();
        assertNotNull(person);
        assertEquals("ok", person.getStat());
        assertEquals(0, person.getCode());
        assertEquals("85853333@N00", person.getUserId());
        assertEquals("Jeremy Brooks", person.getUsername());
    }

    @Test
    public void testParseFindByUsername() throws Exception {
        InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/people/sample_find_by_username.json"));
        Person person = new Gson().fromJson(reader, Person.class);
        reader.close();
        assertNotNull(person);
        assertEquals("ok", person.getStat());
        assertEquals(0, person.getCode());
        assertEquals("85853333@N00", person.getUserId());
        assertEquals("Jeremy Brooks", person.getUsername());
    }

    @Test
    public void testParseGetGroups() throws Exception {
        InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/people/sample_get_groups.json"));
        Groups groups = new Gson().fromJson(reader, Groups.class);
        reader.close();
        assertNotNull(groups);
        assertEquals("ok", groups.getStat());
        assertEquals(0, groups.getCode());
        assertNotNull(groups.getGroupList());
        assertEquals(5, groups.getGroupList().size());
        Groups.Group g = groups.getGroupList().get(0);
        assertNotNull(g);
        assertEquals("11947580@N00", g.getGroupId());
        assertEquals("Night Images", g.getName());
        assertEquals("6", g.getIconFarm());
        assertEquals("5491", g.getIconServer());
        assertFalse(g.isAdmin());
        assertFalse(g.isEighteenPlus());
        assertFalse(g.isInvitationOnly());
        assertEquals(new Integer(126776), g.getMembers());
        assertEquals(new Integer(965541), g.getPoolCount());
        assertEquals(JinxUtils.privacyIdToGroupPrivacyEnum(3), g.getGroupPrivacy());
        assertNotNull(g.getThrottle());
        assertEquals(new Integer(1), g.getThrottle().getCount());
        assertEquals("day", g.getThrottle().getMode());
        GroupInfo.Restrictions r = g.getRestrictions();
        assertNotNull(r);
        assertTrue(r.isPhotosOk());
        assertTrue(r.isVideosOk());
        assertTrue(r.isImagesOk());
        assertFalse(r.isScreensOk());
        assertFalse(r.isArtOk());
        assertTrue(r.isSafeOk());
        assertFalse(r.isModerateOk());
        assertFalse(r.isRestrictedOk());
        assertFalse(r.isHasGeo());
    }

    @Test
    public void testParseGetLimits() throws Exception {
        InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/people/sample_get_limits.json"));
        Limits limits = new Gson().fromJson(reader, Limits.class);
        reader.close();
        assertNotNull(limits);
        assertEquals("ok", limits.getStat());
        assertEquals(0, limits.getCode());
        assertEquals("124857539@N03", limits.getUserId());
        assertEquals("1024", limits.getMaxPhotoDisplayPx());
        assertEquals("209715200", limits.getMaxPhotoUpload());
        assertEquals("180", limits.getMaxVideoDuration());
        assertEquals("1073741824", limits.getMaxVideoUpload());
    }

    @Test
    public void testParseGetPhotos() throws Exception {
        InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/people/sample_get_photos.json"));
        Photos photos = new Gson().fromJson(reader, Photos.class);
        reader.close();
        assertNotNull(photos);
        assertEquals("ok", photos.getStat());
        assertEquals(0, photos.getCode());
        assertEquals(new Integer(2), photos.getPage());
        assertEquals(new Integer(5), photos.getPages());
        assertEquals(new Integer(10), photos.getPerPage());
        assertEquals(new Integer(45), photos.getTotal());
        assertNull(photos.isHasNextPage());
        assertNotNull(photos.getPhotoList());
        assertEquals(10, photos.getPhotoList().size());
        Photo p = photos.getPhotoList().get(0);
        assertEquals("14090163898", p.getPhotoId());
        assertEquals("124857539@N03", p.getOwner());
        assertEquals("bf06719947", p.getSecret());
        assertEquals("3797", p.getServer());
        assertEquals(new Integer(4), p.getFarm());
        assertEquals("La Sombra Rotoporky", p.getTitle());
        assertTrue(p.isPublic());
        assertFalse(p.isFriend());
        assertFalse(p.isFamily());
        assertEquals(new Integer(0), p.getLicense());
        assertEquals("", p.getDescription());
        assertEquals("1401132433", p.getDateUpload());
        assertEquals("1401132439", p.getLastUpdate());
        assertEquals("2013-03-22 21:36:50", p.getDateTaken());
        assertEquals(new Integer(0), p.getDateTakenGranularity());
        assertEquals("jinxlib", p.getOwnerName());
        assertEquals("5538", p.getIconServer());
        assertEquals(new Integer(6), p.getIconFarm());
        assertEquals(new Integer(14), p.getViews());
        assertEquals("blackandwhite bw poster blackwhite iceland interior room reykjavik luchadores", p.getTags());
        assertEquals("2e60b8888e", p.getOriginalSecret());
        assertEquals("jpg", p.getOriginalFormat());
        assertEquals(new Float(0.0), p.getLatitude());
        assertEquals(new Float(0.0), p.getLongitude());
        assertEquals(new Integer(0), p.getAccuracy());
        assertEquals(new Integer(0), p.getContext());
        assertEquals("photo", p.getMedia());
        assertEquals("ready", p.getMediaStatus());
        assertEquals("https://farm4.staticflickr.com/3797/14090163898_bf06719947_s.jpg", p.getUrlSq());
        assertEquals(new Integer(75), p.getHeightSq());
        assertEquals(new Integer(75), p.getWidthSq());
        assertEquals("https://farm4.staticflickr.com/3797/14090163898_bf06719947_t.jpg", p.getUrlT());
        assertEquals(new Integer(67), p.getHeightT());
        assertEquals(new Integer(100), p.getWidthT());
        assertEquals("https://farm4.staticflickr.com/3797/14090163898_bf06719947_m.jpg", p.getUrlS());
        assertEquals(new Integer(160), p.getHeightS());
        assertEquals(new Integer(240), p.getWidthS());
        assertEquals("https://farm4.staticflickr.com/3797/14090163898_bf06719947_q.jpg", p.getUrlQ());
        assertEquals(new Integer(150), p.getHeightQ());
        assertEquals(new Integer(150), p.getWidthQ());
        assertEquals("https://farm4.staticflickr.com/3797/14090163898_bf06719947.jpg", p.getUrlM());
        assertEquals(new Integer(333), p.getHeightM());
        assertEquals(new Integer(500), p.getWidthM());
        assertEquals("https://farm4.staticflickr.com/3797/14090163898_bf06719947_n.jpg", p.getUrlN());
        assertEquals(new Integer(213), p.getHeightN());
        assertEquals(new Integer(320), p.getWidthN());
        assertEquals("https://farm4.staticflickr.com/3797/14090163898_bf06719947_z.jpg", p.getUrlZ());
        assertEquals(new Integer(427), p.getHeightZ());
        assertEquals(new Integer(640), p.getWidthZ());
        assertEquals("https://farm4.staticflickr.com/3797/14090163898_bf06719947_c.jpg", p.getUrlC());
        assertEquals(new Integer(534), p.getHeightC());
        assertEquals(new Integer(800), p.getWidthC());
        assertEquals("https://farm4.staticflickr.com/3797/14090163898_bf06719947_b.jpg", p.getUrlL());
        assertEquals(new Integer(683), p.getHeightL());
        assertEquals(new Integer(1024), p.getWidthL());
        assertEquals("https://farm4.staticflickr.com/3797/14090163898_2e60b8888e_o.jpg", p.getUrlO());
        assertEquals(new Integer(3702), p.getHeightO());
        assertEquals(new Integer(5553), p.getWidthO());
        assertEquals("jinxlib", p.getPathAlias());
    }

    @Test
    public void testParseGetPhotosOf() throws Exception {
        InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/people/sample_get_photos_of.json"));
        Photos photos = new Gson().fromJson(reader, Photos.class);
        reader.close();
        assertNotNull(photos);
        assertEquals("ok", photos.getStat());
        assertEquals(0, photos.getCode());
        assertEquals(new Integer(1), photos.getPage());
        assertEquals(new Integer(14), photos.getPages());
        assertEquals(new Integer(10), photos.getPerPage());
        assertEquals(new Integer(132), photos.getTotal());
        assertTrue(photos.isHasNextPage());
        assertNotNull(photos.getPhotoList());
        assertEquals(10, photos.getPhotoList().size());
        Photo p = photos.getPhotoList().get(0);
        assertEquals("8716439701", p.getPhotoId());
        assertEquals("63849524@N02", p.getOwner());
        assertEquals("4dcc2cb357", p.getSecret());
        assertEquals("7448", p.getServer());
        assertEquals(new Integer(8), p.getFarm());
        assertEquals("Happy Moment", p.getTitle());
        assertTrue(p.isPublic());
        assertFalse(p.isFriend());
        assertFalse(p.isFamily());
        assertEquals(new Integer(0), p.getLicense());
        assertEquals("                               ", p.getDescription());
        assertEquals("1367929437", p.getDateUpload());
        assertEquals("1398774329", p.getLastUpdate());
        assertEquals("2013-05-07 01:18:43", p.getDateTaken());
        assertEquals(new Integer(0), p.getDateTakenGranularity());
        assertEquals("1367983810", p.getDatePersonAdded());
        assertEquals("Ahmed Eather", p.getOwnerName());
        assertEquals("8270", p.getIconServer());
        assertEquals(new Integer(9), p.getIconFarm());
        assertEquals(new Integer(134), p.getViews());

        assertEquals("", p.getTags());
        assertEquals("", p.getMachineTags());
        assertEquals(new Float(0.0), p.getLatitude());
        assertEquals(new Float(0.0), p.getLongitude());
        assertEquals(new Integer(0), p.getAccuracy());
        assertEquals(new Integer(0), p.getContext());
        assertEquals("photo", p.getMedia());
        assertEquals("ready", p.getMediaStatus());

        assertEquals("https://farm8.staticflickr.com/7448/8716439701_4dcc2cb357_s.jpg", p.getUrlSq());
        assertEquals(new Integer(75), p.getHeightSq());
        assertEquals(new Integer(75), p.getWidthSq());
        assertEquals("https://farm8.staticflickr.com/7448/8716439701_4dcc2cb357_t.jpg", p.getUrlT());
        assertEquals(new Integer(69), p.getHeightT());
        assertEquals(new Integer(100), p.getWidthT());
        assertEquals("https://farm8.staticflickr.com/7448/8716439701_4dcc2cb357_m.jpg", p.getUrlS());
        assertEquals(new Integer(167), p.getHeightS());
        assertEquals(new Integer(240), p.getWidthS());
        assertEquals("https://farm8.staticflickr.com/7448/8716439701_4dcc2cb357_q.jpg", p.getUrlQ());
        assertEquals(new Integer(150), p.getHeightQ());
        assertEquals(new Integer(150), p.getWidthQ());
        assertEquals("https://farm8.staticflickr.com/7448/8716439701_4dcc2cb357.jpg", p.getUrlM());
        assertEquals(new Integer(347), p.getHeightM());
        assertEquals(new Integer(500), p.getWidthM());
        assertEquals("https://farm8.staticflickr.com/7448/8716439701_4dcc2cb357_n.jpg", p.getUrlN());
        assertEquals(new Integer(222), p.getHeightN());
        assertEquals(new Integer(320), p.getWidthN());
        assertEquals("https://farm8.staticflickr.com/7448/8716439701_4dcc2cb357_z.jpg", p.getUrlZ());
        assertEquals(new Integer(444), p.getHeightZ());
        assertEquals(new Integer(640), p.getWidthZ());
        assertEquals("https://farm8.staticflickr.com/7448/8716439701_4dcc2cb357_c.jpg", p.getUrlC());
        assertEquals(new Integer(555), p.getHeightC());
        assertEquals(new Integer(800), p.getWidthC());
        assertEquals("https://farm8.staticflickr.com/7448/8716439701_4dcc2cb357_b.jpg", p.getUrlL());
        assertEquals(new Integer(711), p.getHeightL());
        assertEquals(new Integer(1024), p.getWidthL());
        assertEquals("eatherahmed", p.getPathAlias());
    }

    @Test
    public void testParseGetPublicGroups() throws Exception {
        InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/people/sample_get_public_groups.json"));
        Groups groups = new Gson().fromJson(reader, Groups.class);
        reader.close();
        assertNotNull(groups);
        assertEquals("ok", groups.getStat());
        assertEquals(0, groups.getCode());
        assertNotNull(groups.getGroupList());
        assertEquals(5, groups.getGroupList().size());
        Groups.Group g = groups.getGroupList().get(0);
        assertNotNull(g);
        assertEquals("11947580@N00", g.getGroupId());
        assertEquals("Night Images", g.getName());
        assertEquals("6", g.getIconFarm());
        assertEquals("5491", g.getIconServer());
        assertNull(g.isAdmin());
        assertFalse(g.isEighteenPlus());
        assertFalse(g.isInvitationOnly());
        assertEquals(new Integer(126776), g.getMembers());
        assertEquals(new Integer(1031480), g.getPoolCount());
        assertNull(g.getGroupPrivacy());
    }
    
    @Test
    public void testParseGetPublicPhotos() throws Exception {
        InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/people/sample_get_public_photos.json"));
        Photos photos = new Gson().fromJson(reader, Photos.class);
        reader.close();
        assertNotNull(photos);
        assertEquals("ok", photos.getStat());
        assertEquals(0, photos.getCode());
        assertEquals(new Integer(5), photos.getPage());
        assertEquals(new Integer(9526), photos.getPages());
        assertEquals(new Integer(10), photos.getPerPage());
        assertEquals(new Integer(95252), photos.getTotal());
        assertNull(photos.isHasNextPage());
        assertNotNull(photos.getPhotoList());
        assertEquals(10, photos.getPhotoList().size());
        Photo p = photos.getPhotoList().get(0);
        assertEquals("14667771243", p.getPhotoId());
        assertEquals("51035555243@N01", p.getOwner());
        assertEquals("11dfac3eb8", p.getSecret());
        assertEquals("3862", p.getServer());
        assertEquals(new Integer(4), p.getFarm());
        assertEquals("Eddie Gaia", p.getTitle());
        assertTrue(p.isPublic());
        assertFalse(p.isFriend());
        assertFalse(p.isFamily());
        assertEquals(new Integer(2), p.getLicense());
        assertEquals("", p.getDescription());
        assertEquals("5218", p.getoWidth());
        assertEquals("3479", p.getoHeight());
        assertEquals("1405293680", p.getDateUpload());
        assertEquals("1405310177", p.getLastUpdate());
        assertEquals("2010-05-18 15:51:20", p.getDateTaken());
        assertEquals(new Integer(0), p.getDateTakenGranularity());
        assertEquals("Thomas Hawk", p.getOwnerName());
        assertEquals("7370", p.getIconServer());
        assertEquals(new Integer(8), p.getIconFarm());
        assertEquals(new Integer(245), p.getViews());
        assertEquals("sanfrancisco california usa graffiti unitedstates unitedstatesofamerica eddie gaia tenderloin tenderloindistrict eddiecola", p.getTags());
        assertEquals("", p.getMachineTags());
        assertEquals("1a5b9045ac", p.getOriginalSecret());
        assertEquals("jpg", p.getOriginalFormat());
        assertEquals(new Float(0.0), p.getLatitude());
        assertEquals(new Float(0.0), p.getLongitude());
        assertEquals(new Integer(0), p.getAccuracy());
        assertEquals(new Integer(0), p.getContext());
        assertEquals("photo", p.getMedia());
        assertEquals("ready", p.getMediaStatus());

        assertEquals("https://farm4.staticflickr.com/3862/14667771243_11dfac3eb8_s.jpg", p.getUrlSq());
        assertEquals(new Integer(75), p.getHeightSq());
        assertEquals(new Integer(75), p.getWidthSq());
        assertEquals("https://farm4.staticflickr.com/3862/14667771243_11dfac3eb8_t.jpg", p.getUrlT());
        assertEquals(new Integer(67), p.getHeightT());
        assertEquals(new Integer(100), p.getWidthT());
        assertEquals("https://farm4.staticflickr.com/3862/14667771243_11dfac3eb8_m.jpg", p.getUrlS());
        assertEquals(new Integer(160), p.getHeightS());
        assertEquals(new Integer(240), p.getWidthS());
        assertEquals("https://farm4.staticflickr.com/3862/14667771243_11dfac3eb8_q.jpg", p.getUrlQ());
        assertEquals(new Integer(150), p.getHeightQ());
        assertEquals(new Integer(150), p.getWidthQ());
        assertEquals("https://farm4.staticflickr.com/3862/14667771243_11dfac3eb8.jpg", p.getUrlM());
        assertEquals(new Integer(333), p.getHeightM());
        assertEquals(new Integer(500), p.getWidthM());
        assertEquals("https://farm4.staticflickr.com/3862/14667771243_11dfac3eb8_n.jpg", p.getUrlN());
        assertEquals(new Integer(213), p.getHeightN());
        assertEquals(new Integer(320), p.getWidthN());
        assertEquals("https://farm4.staticflickr.com/3862/14667771243_11dfac3eb8_z.jpg", p.getUrlZ());
        assertEquals(new Integer(427), p.getHeightZ());
        assertEquals(new Integer(640), p.getWidthZ());
        assertEquals("https://farm4.staticflickr.com/3862/14667771243_11dfac3eb8_c.jpg", p.getUrlC());
        assertEquals(new Integer(534), p.getHeightC());
        assertEquals(new Integer(800), p.getWidthC());
        assertEquals("https://farm4.staticflickr.com/3862/14667771243_11dfac3eb8_b.jpg", p.getUrlL());
        assertEquals(new Integer(683), p.getHeightL());
        assertEquals(new Integer(1024), p.getWidthL());
        assertEquals("https://farm4.staticflickr.com/3862/14667771243_1a5b9045ac_o.jpg", p.getUrlO());
        assertEquals(new Integer(3479), p.getHeightO());
        assertEquals(new Integer(5218), p.getWidthO());
        assertEquals("thomashawk", p.getPathAlias());
    }

    @Test
    public void testParseUploadStatus() throws Exception {
        InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/people/sample_get_upload_status.json"));
        UploadStatus uploadStatus = new Gson().fromJson(reader, UploadStatus.class);
        reader.close();
        assertNotNull(uploadStatus);
        assertEquals("ok", uploadStatus.getStat());
        assertEquals(0, uploadStatus.getCode());
        assertEquals("124857539@N03", uploadStatus.getUserId());
        assertFalse(uploadStatus.isPro());
        assertEquals("jinxlib", uploadStatus.getUsername());
        assertEquals("2147483648", uploadStatus.getBandwidthMax());
        assertEquals(new Integer(0), uploadStatus.getBandwidthUsed());
        assertEquals("214748364800", uploadStatus.getBandwidthMaxBytes());
        assertEquals(new Integer(0), uploadStatus.getBandwidthUsedBytes());
        assertEquals("214748364800", uploadStatus.getBandwidthRemainingBytes());
        assertEquals("214748364", uploadStatus.getBandwidthMaxKb());
        assertEquals(new Integer(0), uploadStatus.getBandwidthUsedKb());
        assertEquals("214748364", uploadStatus.getBandwidthRemainingKb());
        assertTrue(uploadStatus.isBandwidthUnlimited());
        assertEquals("209715200", uploadStatus.getFilesizeMax());
        assertEquals("209715200", uploadStatus.getFilesizeMaxBytes());
        assertEquals("204800", uploadStatus.getFilesizeMaxKb());
        assertEquals("200", uploadStatus.getFilesizeMaxMb());
        assertEquals(new Integer(18), uploadStatus.getSetsCreated());
        assertEquals("lots", uploadStatus.getSetsRemaining());
        assertEquals("1073741824", uploadStatus.getVideosizeMaxBytes());
        assertEquals("1048576", uploadStatus.getVideosizeMaxKb());
        assertEquals("1024", uploadStatus.getVideosizeMaxMb());
        assertEquals(new Integer(0), uploadStatus.getVideosUploaded());
        assertEquals("lots", uploadStatus.getVideosRemaining());
    }
}
