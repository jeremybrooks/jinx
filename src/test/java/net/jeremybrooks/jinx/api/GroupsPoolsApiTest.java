/*
 * Jinx is Copyright 2010-2020 by Jeremy Brooks and Contributors
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
import net.jeremybrooks.jinx.response.groups.GroupSearch;
import net.jeremybrooks.jinx.response.photos.Photo;
import net.jeremybrooks.jinx.response.photos.Photos;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Test the group pools api methods.
 *
 * Created by jeremyb on 7/9/14.
 */
public class GroupsPoolsApiTest {
    private static final String PUBLIC_GROUP_ID = "2723340@N22";
    private static final String PRIVATE_GROUP_ID = "2638254@N20";
    private static final String PHOTO_ID = "14089987020";
    private static final String PHOTO_ID_IN_POOL = "14090270537";
    private static final String PHOTO_ID_IN_PRIVATE_POOL = "14276354684";
    private static final String PHOTO_ID_PREV = "14090247837";
    private static final String PHOTO_ID_NEXT = "14253693206";

    private static GroupsPoolsApi groupsPoolsApi = null;

    @BeforeClass
    public static void beforeClass() throws Exception {
        groupsPoolsApi = new GroupsPoolsApi(JinxApiTestCommon.getJinx());
    }

    @Test
    public void testAddRemove() throws Exception {
        Response response = groupsPoolsApi.add(PHOTO_ID, PUBLIC_GROUP_ID);
        assertNotNull(response);
        assertEquals("ok", response.getStat());
        assertEquals(0, response.getCode());

        response = groupsPoolsApi.remove(PHOTO_ID, PUBLIC_GROUP_ID);
        assertNotNull(response);
        assertEquals("ok", response.getStat());
        assertEquals(0, response.getCode());
    }

    @Test
    public void testGetContextNotSigned() throws Exception {
        Context context = groupsPoolsApi.getContext(PHOTO_ID_IN_POOL, PUBLIC_GROUP_ID, false);
        assertNotNull(context);
        assertEquals("ok", context.getStat());
        assertEquals(0, context.getCode());
        assertNotNull(context.getPrevPhoto());
        assertNotNull(context.getNextPhoto());
        assertEquals(PHOTO_ID_PREV, context.getPrevPhoto().getPhotoId());
        assertEquals(PHOTO_ID_NEXT, context.getNextPhoto().getPhotoId());
    }

    @Test
    public void testGetContextSigned() throws Exception {
        Context context = groupsPoolsApi.getContext(PHOTO_ID_IN_PRIVATE_POOL, PRIVATE_GROUP_ID, true);
        assertNotNull(context);
        assertEquals("ok", context.getStat());
        assertEquals(0, context.getCode());
    }


    @Test
    public void testGetGroups() throws Exception {
        GroupSearch groupSearch = groupsPoolsApi.getGroups(0, 0);
        assertNotNull(groupSearch);
        assertEquals("ok", groupSearch.getStat());
        assertEquals(0, groupSearch.getCode());
        assertNotNull(groupSearch.getGroupList());
        assertTrue(groupSearch.getGroupList().size() > 0);
    }

    @Test
    public void testGetPhotosNotSigned() throws Exception {
        Photos photos = groupsPoolsApi.getPhotos(PUBLIC_GROUP_ID, null, null, EnumSet.of(JinxConstants.PhotoExtras.media), 0, 0, false);
        assertNotNull(photos);
        assertEquals("ok", photos.getStat());
        assertEquals(0, photos.getCode());
        assertNotNull(photos.getPhotoList());
        for (Photo p : photos.getPhotoList()) {
            assertNotNull(p.getMedia());
        }
        int size = photos.getPhotoList().size();

        // try with a tag
        List<String> tags = new ArrayList<String>();
        tags.add("iceland");
        photos = groupsPoolsApi.getPhotos(PUBLIC_GROUP_ID, tags, null, EnumSet.of(JinxConstants.PhotoExtras.date_upload), 0, 0, false);
        assertNotNull(photos);
        assertEquals("ok", photos.getStat());
        assertEquals(0, photos.getCode());
        assertNotNull(photos.getPhotoList());
        for (Photo p : photos.getPhotoList()) {
            assertNotNull(p.getDateUpload());
        }
        assertFalse(size == photos.getPhotoList().size());
    }

    @Test
    public void testGetPhotosSigned() throws Exception {
        Photos photos = groupsPoolsApi.getPhotos(PRIVATE_GROUP_ID, null, null, EnumSet.of(JinxConstants.PhotoExtras.media), 0, 0, true);
        assertNotNull(photos);
        assertEquals("ok", photos.getStat());
        assertEquals(0, photos.getCode());
        assertNotNull(photos.getPhotoList());
        for (Photo p : photos.getPhotoList()) {
            assertNotNull(p.getMedia());
        }
    }

}
