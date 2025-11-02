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

package net.jeremybrooks.jinx.response.groups.members;

import com.google.gson.Gson;
import net.jeremybrooks.jinx.JinxConstants;
import net.jeremybrooks.jinx.response.activity.ActivityResponseTest;
import org.junit.Test;

import java.io.InputStreamReader;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

/**
 * Created by jeremyb on 7/9/14.
 */
public class GroupsMembersTest {
    @Test
    public void testParseGetList() throws Exception {
        InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/groups/members/sample_get_list.json"));
        Members members = new Gson().fromJson(reader, Members.class);
        reader.close();
        assertNotNull(members);
        assertEquals("ok", members.getStat());
        assertEquals(0, members.getCode());
        assertEquals(Integer.valueOf(1), members.getPage());
        assertEquals(Integer.valueOf(12655), members.getPages());
        assertEquals(Integer.valueOf(10), members.getPerPage());
        assertEquals(Integer.valueOf(126546), members.getTotal());
        List<Members.Member> list = members.getMemberList();
        assertNotNull(list);
        assertEquals(10, list.size());
        Members.Member m = list.get(0);
        assertEquals("117607063@N05", m.getUserId());
        assertEquals("Sebastain Trif", m.getUserName());
        assertEquals("0", m.getIconServer());
        assertEquals("0", m.getIconFarm());
        assertEquals(JinxConstants.MemberType.member, m.getMemberType());
        assertEquals("Sebastian Trif", m.getRealName());
    }
}
