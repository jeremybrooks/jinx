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

package net.jeremybrooks.jinx.response.groups;

import com.google.gson.Gson;
import net.jeremybrooks.jinx.response.activity.ActivityResponseTest;
import org.junit.Test;

import java.io.InputStreamReader;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

/**
 * Created by jeremyb on 7/9/14.
 */
public class GroupsTest {
    @Test
    public void testParseGetInfo() throws Exception {
        InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/groups/sample_get_info.json"));
        GroupInfo groupInfo = new Gson().fromJson(reader, GroupInfo.class);
        reader.close();
        assertNotNull(groupInfo);
        assertEquals("ok", groupInfo.getStat());
        assertEquals(0, groupInfo.getCode());
        assertEquals("67877485@N00", groupInfo.getGroupId());
        assertEquals("sfbas", groupInfo.getPathAlias());
        assertEquals("52", groupInfo.getIconServer());
        assertEquals("1", groupInfo.getIconFarm());
        assertEquals("SF Bay Area Shooters (sfbas)", groupInfo.getName());
        assertEquals("Share tips, photo locations, and organize photoshoots with San Francisco Bay Area photographers!\n\nCheck out our official SFBAS Shoot pictures:\n<a href=\"http://flickr.com/photos/tags/sfbas/clusters/\">flickr.com/photos/tags/sfbas/clusters/</a>\n\nCheck out our SFBAS Group Pictures:\n<a href=\"http://www.flickr.com/groups/sfbas/discuss/72157602621370134/\">www.flickr.com/groups/sfbas/discuss/72157602621370134/</a>\n\nAnd please visit our master shoot location list for upcoming photo shoots!\n<a href=\"http://flickr.com/groups/sfbas/discuss/72157594179606707/\">flickr.com/groups/sfbas/discuss/72157594179606707/</a>\n\n<a href=\"http://www.flickr.com/groups/sfbas/discuss/72157594501754869/\">Thank your for using caution when using the SFBAS tag</a>\n\n", groupInfo.getDescription());
        assertEquals("", groupInfo.getRules());
        assertEquals(Integer.valueOf(4409), groupInfo.getMembers());
        assertEquals(Integer.valueOf(145199), groupInfo.getPoolCount());
        assertEquals(Integer.valueOf(848), groupInfo.getTopicCount());
        assertEquals("3", groupInfo.getPrivacy());
        assertEquals("", groupInfo.getLang());
        assertFalse(groupInfo.isPoolModerated());
        assertNotNull(groupInfo.getRoles());
        assertEquals("Member", groupInfo.getRoles().getMember());
        assertEquals("Moderator", groupInfo.getRoles().getModerator());
        assertEquals("Administrator", groupInfo.getRoles().getAdmin());
        assertTrue(groupInfo.isMember());
        assertFalse(groupInfo.isModerator());
        assertFalse(groupInfo.isAdmin());
        assertNotNull(groupInfo.getBlast());
        assertEquals("We are now also on Google+! <a href=\"https://plus.google.com/u/0/105043012447601369073/posts\" rel=\"nofollow\">Click here</a> | Upcoming SFBAS Events: <a href=\"http://www.flickr.com/groups/sfbas/discuss/72157629107751111/\">SF Venue Shoot - February 25th, 2012</a> | <a href=\"http://flickr.com/groups/sfbas/discuss/72157605474119242/\">SFBAS Charity Gallery Event</a> | <a href=\"http://www.flickr.com/groups/sfbas/discuss/72157594179606707/\"><b>See all shoots</b></a>", groupInfo.getBlast().getContent());
        assertEquals("1327974979", groupInfo.getBlast().getDateBlastAdded());
        assertEquals("642072", groupInfo.getBlast().getUserId());
        assertNotNull(groupInfo.getThrottle());
        assertEquals(Integer.valueOf(5), groupInfo.getThrottle().getCount());
        assertEquals("day", groupInfo.getThrottle().getMode());
        assertEquals(Integer.valueOf(4), groupInfo.getThrottle().getRemaining());
        assertNotNull(groupInfo.getRestrictions());
        assertTrue(groupInfo.getRestrictions().isPhotosOk());
        assertTrue(groupInfo.getRestrictions().isVideosOk());
        assertTrue(groupInfo.getRestrictions().isImagesOk());
        assertTrue(groupInfo.getRestrictions().isScreensOk());
        assertTrue(groupInfo.getRestrictions().isArtOk());
        assertTrue(groupInfo.getRestrictions().isSafeOk());
        assertTrue(groupInfo.getRestrictions().isModerateOk());
        assertFalse(groupInfo.getRestrictions().isRestrictedOk());
        assertFalse(groupInfo.getRestrictions().isHasGeo());
    }

    @Test
    public void testParseGroupSearch() throws Exception {
        InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/groups/sample_search.json"));
        GroupSearch groupSearch = new Gson().fromJson(reader, GroupSearch.class);
        reader.close();
        assertNotNull(groupSearch);
        assertEquals("ok", groupSearch.getStat());
        assertEquals(0, groupSearch.getCode());
        assertEquals(Integer.valueOf(1), groupSearch.getPage());
        assertEquals(Integer.valueOf(500), groupSearch.getPages());
        assertEquals(Integer.valueOf(10), groupSearch.getPerPage());
        assertEquals(Integer.valueOf(4998), groupSearch.getTotal());
        assertNotNull(groupSearch.getGroupList());
        assertEquals(10, groupSearch.getGroupList().size());
        GroupSearch.Group g = groupSearch.getGroupList().get(0);
        assertEquals("1026413@N24", g.getGroupId());
        assertEquals("Neon Arrows", g.getName());
        assertFalse(g.isEighteenPlus());
        assertEquals("3455", g.getIconServer());
        assertEquals("4", g.getIconFarm());
        assertEquals(Integer.valueOf(68), g.getMemberCount());
        assertEquals(Integer.valueOf(733), g.getPoolCount());
        assertEquals(Integer.valueOf(1), g.getTopicCount());
    }
}
