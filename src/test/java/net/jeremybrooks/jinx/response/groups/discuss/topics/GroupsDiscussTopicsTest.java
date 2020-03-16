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

package net.jeremybrooks.jinx.response.groups.discuss.topics;

import com.google.gson.Gson;
import net.jeremybrooks.jinx.response.activity.ActivityResponseTest;
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
public class GroupsDiscussTopicsTest {
    @Test
    public void testParseGetInfo() throws Exception {
        InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/groups/discuss/topics/sample_get_info.json"));
        TopicInfo topicInfo = new Gson().fromJson(reader, TopicInfo.class);
        reader.close();
        assertNotNull(topicInfo);
        assertEquals("ok", topicInfo.getStat());
        assertEquals(0, topicInfo.getCode());
        Topic t = topicInfo.getTopic();
        assertNotNull(t);
        assertEquals("72157645141651797", t.getTopicId());
        assertEquals("flickr4java - unable to obtain DELETE permission", t.getSubject());
        assertEquals("I'm using flickr4java v2.8 in my application that downloads photos.\r\n\r\nI can authenticate successfully, and get lists of my photo albums etc., but am getting:\r\n\r\ncom.flickr4java.flickr.FlickrException: 99: Insufficient permissions. Method requires read privileges; none granted.\r\n\r\nwhen attempting to download photos. I have set \r\n\r\nauthInterface.getAuthorizationUrl(rtoken, Permission.DELETE);\r\n\r\n(I need access to delete photos as well as download them) which I assumed also allows read and write access, as well as delete.\r\n\r\nShould I instead by ORing all Permissions I need together?\r\n\r\nThanks for any advice!", t.getMessage());
        assertEquals("10385775@N00", t.getAuthor());
        assertEquals("snigfargle", t.getAuthorName());
        assertFalse(t.isPro());
        assertEquals("member", t.getRole());
        assertEquals("7344", t.getIconServer());
        assertEquals("8", t.getIconFarm());
        assertEquals(Integer.valueOf(1), t.getCountReplies());
        assertFalse(t.isCanEdit());
        assertFalse(t.isCanDelete());
        assertFalse(t.isCanReply());
        assertFalse(t.isSticky());
        assertFalse(t.isLocked());
        assertEquals("1404713682", t.getDateCreate());
        assertEquals("1404762423", t.getDateLastPost());
        assertEquals("72157645513187066", t.getLastReply());
    }

    @Test
    public void testParseGetList() throws Exception {
        InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/groups/discuss/topics/sample_get_list.json"));
        Topics topics = new Gson().fromJson(reader, Topics.class);
        reader.close();
        assertNotNull(topics);
        assertEquals("ok", topics.getStat());
        assertEquals(0, topics.getCode());
        assertEquals("51035612836@N01", topics.getGroupId());
        assertEquals("api", topics.getPathAlias());
        assertEquals("3686", topics.getIconServer());
        assertEquals("4", topics.getIconFarm());
        assertEquals("Flickr API", topics.getName());
        assertEquals(Integer.valueOf(14376), topics.getMembers());
        assertEquals("3", topics.getPrivacy());
        assertEquals("en-us", topics.getLang());
        assertTrue(topics.isPoolModerated());
        assertEquals(Integer.valueOf(3630), topics.getTotal());
        assertEquals(Integer.valueOf(1), topics.getPage());
        assertEquals(Integer.valueOf(10), topics.getPerPage());
        assertEquals(Integer.valueOf(363), topics.getPages());
        List<Topic> list = topics.getTopicList();
        assertNotNull(list);
        assertEquals(10, list.size());
        Topic t = list.get(0);
        assertNotNull(t);
        assertEquals("72157645583073954", t.getTopicId());
        assertEquals("API Request Token unauthorized", t.getSubject());
        assertEquals("When i click on this link:\r\n<a href=\"https://www.flickr.com/services/oauth/request_token?oauth_callback=http%3A%2F%2Fwww.markriemann.com%2Fflickr&amp;oauth_consumer_key=ec61d7a4be3e3814b4b4baca653c8a45&amp;oauth_nonce=0462df92491c433cbc0711ad41b73ae6&amp;oauth_signature_method=HMAC-SHA1&amp;oauth_timestamp=1404850686&amp;oauth_version=1.0&amp;oauth_signature=101750ae4f0bdab8d93b262aa3c0e775d1068f26\">www.flickr.com/services/oauth/request_token?oauth_callbac...</a>\r\n\r\nthe debug message says:\r\noauth_problem=signature_invalid&amp;debug_sbs=GET&amp;https%3A%2F%2Fwww.flickr.com%2Fservices%2Foauth%2Frequest_token&amp;oauth_callback%3Dhttp%253A%252F%252Fwww.markriemann.com%252Fflickr%26oauth_consumer_key%3Dec61d7a4be3e3814b4b4baca653c8a45%26oauth_nonce%3D0462df92491c433cbc0711ad41b73ae6%26oauth_signature_method%3DHMAC-SHA1%26oauth_timestamp%3D1404850686%26oauth_version%3D1.0\r\n\r\n\r\nI believe my signature was done correctly. Key was my app secret + &quot;&amp;&quot; and the basestring was \r\n\r\nGET&amp;https%3A%2F%2Fwww.flickr.com%2Fservices%2Foauth%2Frequest_token&amp;oauth_callback%3Dhttp%253A%252F%252Fwww.markriemann.com%252Fflickr%26oauth_consumer_key%3Dec61d7a4be3e3814b4b4baca653c8a45%26oauth_nonce%3D0462df92491c433cbc0711ad41b73ae6%26oauth_signature_method%3DHMAC-SHA1%26oauth_timestamp%3D1404850686%26oauth_version%3D1.0\r\n\r\nSo what am I missing?\r\n\r\nThanks,\r\nMark", t.getMessage());
        assertEquals("125854486@N03", t.getAuthor());
        assertEquals("mer919mo", t.getAuthorName());
        assertEquals("", t.getAuthorPathAlias());
        assertFalse(t.isPro());
        assertEquals("member", t.getRole());
        assertEquals("0", t.getIconServer());
        assertEquals("0", t.getIconFarm());
        assertEquals(Integer.valueOf(1), t.getCountReplies());
        assertFalse(t.isCanEdit());
        assertFalse(t.isCanDelete());
        assertFalse(t.isCanReply());
        assertFalse(t.isSticky());
        assertFalse(t.isLocked());
        assertEquals("1404850797", t.getDateCreate());
        assertEquals("1404853429", t.getDateLastPost());
        assertEquals("72157645532296196", t.getLastReply());
    }
}
