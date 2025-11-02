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

package net.jeremybrooks.jinx.api;

import net.jeremybrooks.jinx.JinxConstants;
import net.jeremybrooks.jinx.response.groups.Groups;
import net.jeremybrooks.jinx.response.people.Limits;
import net.jeremybrooks.jinx.response.people.Person;
import net.jeremybrooks.jinx.response.people.UploadStatus;
import net.jeremybrooks.jinx.response.photos.Photo;
import net.jeremybrooks.jinx.response.photos.Photos;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.EnumSet;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 *
 * Created by jeremyb on 7/15/14.
 */
public class PeopleApiTest {
    private static PeopleApi peopleApi;
    private static String jinxlibUserId = "124857539@N03";    // jinxlib
    private static String jeremybrooksUserId = "85853333@N00";

    @BeforeClass
    public static void beforeClass() throws Exception {
        peopleApi = new PeopleApi(JinxApiTestCommon.getJinx());
    }

    @Test
    public void testFindByEmail() throws Exception {
        Person p = peopleApi.findByEmail("whirljackk@yahoo.com");
        assertNotNull(p);
        assertEquals("ok", p.getStat());
        assertEquals(0, p.getCode());
        assertEquals(jeremybrooksUserId, p.getUserId());
        assertEquals("Jeremy Brooks", p.getUsername());
    }

    @Test
    public void testFindByUsername() throws Exception {
        Person p = peopleApi.findByUsername("Jeremy Brooks");
        assertNotNull(p);
        assertEquals("ok", p.getStat());
        assertEquals(0, p.getCode());
        assertEquals(jeremybrooksUserId, p.getUserId());
        assertEquals("Jeremy Brooks", p.getUsername());
    }

    @Test
    public void testGetGroups() throws Exception {
        Groups groups = peopleApi.getGroups(jinxlibUserId, null);
        assertNotNull(groups);
        assertEquals("ok", groups.getStat());
        assertEquals(0, groups.getCode());
        assertNotNull(groups.getGroupList());
        assertTrue(groups.getGroupList().size() > 0);
        groups = peopleApi.getGroups(jinxlibUserId, EnumSet.of(JinxConstants.GroupExtras.privacy));
        assertNotNull(groups);
        assertEquals("ok", groups.getStat());
        assertEquals(0, groups.getCode());
        assertNotNull(groups.getGroupList());
        assertTrue(groups.getGroupList().size() > 0);
        for (Groups.Group g : groups.getGroupList()) {
            assertNotNull(g.getGroupPrivacy());
        }
    }

    @Test
    public void testGetInfo() throws Exception {
        Person p = peopleApi.getInfo(jeremybrooksUserId, false);
        assertNotNull(p);
        assertEquals("ok", p.getStat());
        assertEquals(0, p.getCode());
        assertEquals(jeremybrooksUserId, p.getUserId());
        assertEquals("Jeremy Brooks", p.getUsername());

        p = peopleApi.getInfo(jeremybrooksUserId, true);
        assertNotNull(p);
        assertEquals("ok", p.getStat());
        assertEquals(0, p.getCode());
        assertEquals(jeremybrooksUserId, p.getUserId());
        assertEquals("Jeremy Brooks", p.getUsername());
    }

    @Test
    public void testGetLimits() throws Exception {
        Limits limits = peopleApi.getLimits();
        assertNotNull(limits);
        assertEquals("ok", limits.getStat());
        assertEquals(0, limits.getCode());
        assertEquals(jinxlibUserId, limits.getUserId());
    }

    @Test
    public void testGetPhotos() throws Exception {
        Photos photos = peopleApi.getPhotos(jeremybrooksUserId, null, null, null, null, null, null, null, null, 10, 0, false);
        assertNotNull(photos);
        assertEquals("ok", photos.getStat());
        assertEquals(0, photos.getCode());
        assertNotNull(photos.getPhotoList());
        assertEquals(10, photos.getPhotoList().size());

        photos = peopleApi.getPhotos(jeremybrooksUserId, null, null, null, null, null, null, null, EnumSet.of(JinxConstants.PhotoExtras.media), 10, 0, true);
        assertNotNull(photos);
        assertEquals("ok", photos.getStat());
        assertEquals(0, photos.getCode());
        assertNotNull(photos.getPhotoList());
        for (Photo p : photos.getPhotoList()) {
            assertNotNull(p.getMedia());
        }
    }

    @Test
    public void testGetPhotosOf() throws Exception {
        Photos photos = peopleApi.getPhotosOf(jeremybrooksUserId, null, null, 10, 0, false);
        assertNotNull(photos);
        assertEquals("ok", photos.getStat());
        assertEquals(0, photos.getCode());
        assertNotNull(photos.getPhotoList());
        assertEquals(10, photos.getPhotoList().size());

        photos = peopleApi.getPhotosOf(jeremybrooksUserId, null, EnumSet.of(JinxConstants.PhotoExtras.date_upload), 10, 0, true);
        assertNotNull(photos);
        assertEquals("ok", photos.getStat());
        assertEquals(0, photos.getCode());
        assertNotNull(photos.getPhotoList());
        assertEquals(10, photos.getPhotoList().size());
        for (Photo p : photos.getPhotoList()) {
            assertNotNull(p.getDateUpload());
        }
    }

    @Test
    public void testGetPublicGroups() throws Exception {
        Groups groups = peopleApi.getPublicGroups(jinxlibUserId, null, false);
        assertNotNull(groups);
        assertEquals("ok", groups.getStat());
        assertEquals(0, groups.getCode());
        assertNotNull(groups.getGroupList());
        assertTrue(groups.getGroupList().size() > 0);
    }

    @Test
    public void testGetPublicPhotos() throws Exception {
        Photos photos = peopleApi.getPublicPhotos(jeremybrooksUserId, null, null, 10, 0, false);
        assertNotNull(photos);
        assertEquals("ok", photos.getStat());
        assertEquals(0, photos.getCode());
        assertNotNull(photos.getPhotoList());
        assertEquals(10, photos.getPhotoList().size());

        photos = peopleApi.getPublicPhotos(jeremybrooksUserId, null, EnumSet.of(JinxConstants.PhotoExtras.tags), 10, 3, true);
        assertNotNull(photos);
        assertEquals("ok", photos.getStat());
        assertEquals(0, photos.getCode());
        assertNotNull(photos.getPhotoList());
        assertEquals(Integer.valueOf(3), photos.getPage());
        assertEquals(10, photos.getPhotoList().size());
        for (Photo p : photos.getPhotoList()) {
            assertNotNull(p.getTags());
        }
    }

    @Test
    public void testGetUploadStatus() throws Exception {
        UploadStatus uploadStatus = peopleApi.getUploadStatus();
        assertNotNull(uploadStatus);
        assertEquals("ok", uploadStatus.getStat());
        assertEquals(0, uploadStatus.getCode());
        assertFalse(uploadStatus.isPro());
        assertEquals(jinxlibUserId, uploadStatus.getUserId());
    }
}
