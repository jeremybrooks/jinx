/*
 * Jinx is Copyright 2010-2023 by Jeremy Brooks and Contributors
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

package net.jeremybrooks.jinx.response.groups.discuss.replies;

import com.google.gson.Gson;
import net.jeremybrooks.jinx.response.activity.ActivityResponseTest;
import net.jeremybrooks.jinx.response.groups.discuss.topics.Topic;
import org.junit.Test;

import java.io.InputStreamReader;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;

/**
 * Created by jeremyb on 7/9/14.
 */
public class GroupsDiscussRepliesTest {
    @Test
    public void testParseGetInfo() throws Exception {
        InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/groups/discuss/replies/sample_get_info.json"));
        ReplyInfo replyInfo = new Gson().fromJson(reader, ReplyInfo.class);
        reader.close();
        assertNotNull(replyInfo);
        assertEquals("ok", replyInfo.getStat());
        assertEquals(0, replyInfo.getCode());
        assertNotNull(replyInfo.getReply());
        Reply r = replyInfo.getReply();
        assertEquals("72157645513187066", r.getReplyId());
        assertEquals("Update - the application started working correctly: I guess I must have had an older auth token stored that I was inadvertently using.", r.getMessage());
        assertEquals("10385775@N00", r.getAuthor());
        assertEquals("snigfargle", r.getAuthorName());
        assertFalse(r.isPro());
        assertEquals("member", r.getRole());
        assertEquals("7344", r.getIconServer());
        assertEquals("8", r.getIconFarm());
        assertFalse(r.isCanEdit());
        assertFalse(r.isCanDelete());
        assertEquals("1404762423", r.getDateCreate());
        assertEquals("0", r.getLastEdit());
    }

    @Test
    public void testParseGetList() throws Exception {
        InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/groups/discuss/replies/sample_get_list.json"));
        Replies replies = new Gson().fromJson(reader, Replies.class);
        reader.close();
        assertNotNull(replies);
        assertEquals("ok", replies.getStat());
        assertEquals(0, replies.getCode());
        Topic t = replies.getTopic();
        assertNotNull(t);
        assertEquals("72157645141651797", t.getTopicId());
        assertEquals("flickr4java - unable to obtain DELETE permission", t.getSubject());
        assertEquals("I'm using flickr4java v2.8 in my application that downloads photos.\r\n\r\nI can authenticate successfully, and get lists of my photo albums etc., but am getting:\r\n\r\ncom.flickr4java.flickr.FlickrException: 99: Insufficient permissions. Method requires read privileges; none granted.\r\n\r\nwhen attempting to download photos. I have set \r\n\r\nauthInterface.getAuthorizationUrl(rtoken, Permission.DELETE);\r\n\r\n(I need access to delete photos as well as download them) which I assumed also allows read and write access, as well as delete.\r\n\r\nShould I instead by ORing all Permissions I need together?\r\n\r\nThanks for any advice!", t.getMessage());
        assertEquals("51035612836@N01", t.getGroupId());
        assertEquals("3686", t.getIconServer());
        assertEquals("4", t.getIconFarm());
        assertEquals("Flickr API", t.getName());
        assertEquals("10385775@N00", t.getAuthor());
        assertEquals("snigfargle", t.getAuthorName());
        assertFalse(t.isPro());
        assertEquals("member", t.getRole());
        assertEquals("7344", t.getAuthorIconServer());
        assertEquals("8", t.getAuthorIconFarm());
        assertFalse(t.isCanEdit());
        assertFalse(t.isCanDelete());
        assertFalse(t.isCanReply());
        assertFalse(t.isSticky());
        assertNull(t.isLocked());
        assertEquals("1404713682", t.getDateCreate());
        assertEquals("1404762423", t.getDateLastPost());
        assertEquals(Integer.valueOf(1), t.getTotal());
        assertEquals(Integer.valueOf(1), t.getPage());
        assertEquals(Integer.valueOf(100), t.getPerPage());
        assertEquals(Integer.valueOf(1), t.getPages());

        List<Reply> list = replies.getReplyList();
        assertNotNull(list);
        assertEquals(1, list.size());
        Reply r = list.get(0);
        assertEquals("72157645513187066", r.getReplyId());
        assertEquals("Update - the application started working correctly: I guess I must have had an older auth token stored that I was inadvertently using.", r.getMessage());
        assertEquals("10385775@N00", r.getAuthor());
        assertEquals("snigfargle", r.getAuthorName());
        assertFalse(r.isPro());
        assertEquals("member", r.getRole());
        assertEquals("7344", r.getIconServer());
        assertEquals("8", r.getIconFarm());
        assertFalse(r.isCanEdit());
        assertFalse(r.isCanDelete());
        assertEquals("1404762423", r.getDateCreate());
        assertEquals("0", r.getLastEdit());
    }
}
