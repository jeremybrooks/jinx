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

package net.jeremybrooks.jinx.api;

import net.jeremybrooks.jinx.Jinx;
import net.jeremybrooks.jinx.JinxConstants;
import net.jeremybrooks.jinx.OAuthAccessToken;
import net.jeremybrooks.jinx.response.groups.members.Members;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.EnumSet;
import java.util.Properties;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by jeremyb on 7/9/14.
 */
public class GroupsMembersApiTest {
    private static GroupsMembersApi groupsMembersApi = null;
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
        groupsMembersApi = new GroupsMembersApi(new Jinx(p.getProperty("flickr.key"), p.getProperty("flickr.secret"), oAuthAccessToken));
    }

    @Test
    public void testGetList() throws Exception {
        Members members = groupsMembersApi.getList("11947580@N00", null, 10, 1);
        assertNotNull(members);
        assertEquals("ok", members.getStat());
        assertEquals(0, members.getCode());
        assertEquals(Integer.valueOf(10), members.getPerPage());
        assertNotNull(members.getMemberList());
        assertEquals(10, members.getMemberList().size());
    }

    @Test
    public void testGetListWithTypes() throws Exception {
        Members members = groupsMembersApi.getList("11947580@N00", EnumSet.of(JinxConstants.MemberType.admin, JinxConstants.MemberType.moderator), 10, 1);
        assertNotNull(members);
        assertEquals("ok", members.getStat());
        assertEquals(0, members.getCode());
        assertEquals(Integer.valueOf(10), members.getPerPage());
        assertNotNull(members.getMemberList());
    }
}
