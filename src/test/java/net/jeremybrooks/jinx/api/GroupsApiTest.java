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

package net.jeremybrooks.jinx.api;

import net.jeremybrooks.jinx.Jinx;
import net.jeremybrooks.jinx.OAuthAccessToken;
import net.jeremybrooks.jinx.response.Response;
import net.jeremybrooks.jinx.response.groups.GroupInfo;
import net.jeremybrooks.jinx.response.groups.GroupSearch;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by jeremyb on 7/8/14.
 */
public class GroupsApiTest {

    private static GroupsApi groupsApi;

    private static final String PUBLIC_GROUP_NSID = "2684447@N23";
    private static final String PRIVATE_GROUP_NSID = "2658788@N24";

    @BeforeClass
    public static void beforeClass() throws Exception {
        Properties p = new Properties();
        p.load(OAuthApiTest.class.getResourceAsStream("/response/auth/secret.properties"));

        String filename = p.getProperty("path.to.oauth.token");
        assertNotNull(filename);

        File file = new File(filename);
        assertTrue(file.exists());

        OAuthAccessToken oAuthAccessToken = new OAuthAccessToken();
        oAuthAccessToken.load(new FileInputStream(file));

        assertNotNull(oAuthAccessToken);

        groupsApi = new GroupsApi(new Jinx(p.getProperty("flickr.key"), p.getProperty("flickr.secret"), oAuthAccessToken));
    }

    /**
     * This is testing a method that is pretty much useless.
     * It is here mostly to detect if Flickr removes this method.
     * @throws Exception
     */
    @Test
    public void testBrowse() throws Exception {
        Response response = groupsApi.browse(null);
        assertNotNull(response);
        assertEquals("ok", response.getStat());
        assertEquals(0, response.getCode());
    }

    @Test
    public void testGetInfo() throws Exception {
        GroupInfo groupInfo = groupsApi.getInfo(PUBLIC_GROUP_NSID, null, null);
        assertNotNull(groupInfo);
        assertEquals(PUBLIC_GROUP_NSID, groupInfo.getGroupId());
        assertEquals("Jinx Test Group", groupInfo.getName());
        assertFalse(groupInfo.isPoolModerated());
        assertNotNull(groupInfo.getRoles());
        assertNotNull(groupInfo.getThrottle());
        assertEquals("none", groupInfo.getThrottle().getMode());
        assertNull(groupInfo.getThrottle().getCount());
        assertNull(groupInfo.getThrottle().getRemaining());
        assertNotNull(groupInfo.getRestrictions());
        assertTrue(groupInfo.getRestrictions().isPhotosOk());
        assertTrue(groupInfo.getRestrictions().isVideosOk());
        assertTrue(groupInfo.getRestrictions().isImagesOk());
        assertTrue(groupInfo.getRestrictions().isScreensOk());
        assertTrue(groupInfo.getRestrictions().isArtOk());
        assertTrue(groupInfo.getRestrictions().isSafeOk());
        assertFalse(groupInfo.getRestrictions().isRestrictedOk());
        assertFalse(groupInfo.getRestrictions().isModerateOk());
        assertFalse(groupInfo.getRestrictions().isHasGeo());

        groupInfo = groupsApi.getInfo(null, "neon", null);
        assertNotNull(groupInfo);
        assertEquals("neon", groupInfo.getPathAlias());
    }

    @Test
    public void testJoinAndLeave() throws Exception {
        Response response = groupsApi.join(PUBLIC_GROUP_NSID, false);
        assertNotNull(response);
        assertEquals("ok", response.getStat());
        assertEquals(0, response.getCode());

        response = groupsApi.leave(PUBLIC_GROUP_NSID, false);
        assertNotNull(response);
        assertEquals("ok", response.getStat());
        assertEquals(0, response.getCode());
    }

    @Test
    public void testJoinRequest() throws Exception {
        Response response = groupsApi.joinRequest(PRIVATE_GROUP_NSID, "Jinx test - please decline this request.", true);
        assertNotNull(response);
        assertEquals("ok", response.getStat());
        assertEquals(0, response.getCode());
    }

    @Test
    public void testSearch() throws Exception {
        GroupSearch groupSearch = groupsApi.search("neon", 10, 1);
        assertNotNull(groupSearch);
        assertEquals(new Integer(1), groupSearch.getPage());
        assertNotNull(groupSearch.getGroupList());
        assertEquals(10, groupSearch.getGroupList().size());
    }
}
