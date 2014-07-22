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

package net.jeremybrooks.jinx.response.groups.pools;

import com.google.gson.Gson;
import net.jeremybrooks.jinx.JinxConstants;
import net.jeremybrooks.jinx.response.activity.ActivityResponseTest;
import net.jeremybrooks.jinx.response.common.Context;
import net.jeremybrooks.jinx.response.groups.GroupSearch;
import net.jeremybrooks.jinx.response.photos.Photo;
import net.jeremybrooks.jinx.response.photos.Photos;
import org.junit.Test;

import java.io.InputStreamReader;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by jeremyb on 7/9/14.
 */
public class GroupsPoolsTest {
    @Test
    public void testParseGetContext() throws Exception {
        InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/groups/pools/sample_get_context.json"));
        Context context = new Gson().fromJson(reader, Context.class);
        reader.close();
        assertNotNull(context);
        assertEquals("ok", context.getStat());
        assertEquals(0, context.getCode());
        assertEquals(new Integer(5), context.getCount());
        Context.PrevPhoto prev = context.getPrevPhoto();
        assertNotNull(prev);
        assertEquals("14090247837", prev.getPhotoId());
        assertEquals("124857539@N03", prev.getOwner());
        assertEquals("a00c1c8c01", prev.getSecret());
        assertEquals("3745", prev.getServer());
        assertEquals("4", prev.getFarm());
        assertEquals("Constantly", prev.getTitle());
        assertEquals("/photos/jinxlib/14090247837/in/pool-2723340@N22/", prev.getUrl());
        assertEquals("https://farm4.staticflickr.com/3745/14090247837_a00c1c8c01_s.jpg", prev.getThumb());
        assertEquals(new Integer(0), prev.getLicense());
        assertEquals("photo", prev.getMedia());
        assertFalse(prev.isFaved());

        Context.NextPhoto next = context.getNextPhoto();
        assertNotNull(next);
        assertEquals("14253693206", next.getPhotoId());
        assertEquals("124857539@N03", next.getOwner());
        assertEquals("62bbd1113a", next.getSecret());
        assertEquals("3777", next.getServer());
        assertEquals("4", next.getFarm());
        assertEquals("Hallgrímskirkja", next.getTitle());
        assertEquals("/photos/jinxlib/14253693206/in/pool-2723340@N22/", next.getUrl());
        assertEquals("https://farm4.staticflickr.com/3777/14253693206_62bbd1113a_s.jpg", next.getThumb());
        assertEquals(new Integer(0), next.getLicense());
        assertEquals("photo", next.getMedia());
        assertFalse(next.isFaved());
    }

    @Test
    public void testParseGetGroups() throws Exception {
        InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/groups/pools/sample_get_groups.json"));
        GroupSearch groupSearch = new Gson().fromJson(reader, GroupSearch.class);
        reader.close();
        assertNotNull(groupSearch);
        assertEquals("ok", groupSearch.getStat());
        assertEquals(0, groupSearch.getCode());
        assertEquals(new Integer(1), groupSearch.getPage());
        assertEquals(new Integer(1), groupSearch.getPages());
        assertEquals(new Integer(400), groupSearch.getPerPage());
        assertEquals(new Integer(2), groupSearch.getTotal());
        List<GroupSearch.Group> list = groupSearch.getGroupList();
        assertNotNull(list);
        assertEquals(2, list.size());
        GroupSearch.Group group = list.get(0);
        assertEquals("2638254@N20", group.getGroupId());
        assertEquals("Private Jinx Test Group", group.getName());
        assertTrue(group.isMember());
        assertFalse(group.isModerator());
        assertTrue(group.isAdmin());
        assertEquals(JinxConstants.GroupPrivacy.group_private, group.getPrivacy());
        assertEquals(new Integer(1), group.getPhotos());
        assertEquals("2934", group.getIconServer());
        assertEquals("3", group.getIconFarm());
        assertEquals(new Integer(1), group.getMemberCount());
        assertEquals(new Integer(7), group.getTopicCount());
        assertEquals(new Integer(1), group.getPoolCount());
    }

    @Test
    public void testParseGetPhotos() throws Exception {
        InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/groups/pools/sample_get_photos.json"));
        Photos photos = new Gson().fromJson(reader, Photos.class);
        reader.close();
        assertNotNull(photos);
        assertEquals("ok", photos.getStat());
        assertEquals(0, photos.getCode());
        assertEquals(new Integer(1), photos.getPage());
        assertEquals(new Integer(1), photos.getPages());
        assertEquals(new Integer(100), photos.getPerPage());
        assertEquals(new Integer(5), photos.getTotal());
        assertNotNull(photos.getPhotoList());
        Photo p = photos.getPhotoList().get(0);
        assertEquals("14253711336", p.getPhotoId());
        assertEquals("124857539@N03", p.getOwner());
        assertEquals("772cec7714", p.getSecret());
        assertEquals("3714", p.getServer());
        assertEquals("4", p.getFarm());
        assertEquals("Spent All Day", p.getTitle());
        assertTrue(p.isPublic());
        assertFalse(p.isFriend());
        assertFalse(p.isFamily());
        assertEquals("jinxlib", p.getOwnerName());
        assertEquals("1404957118", p.getDateAdded());
    }
}
