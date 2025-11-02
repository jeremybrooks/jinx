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

import net.jeremybrooks.jinx.response.Response;
import net.jeremybrooks.jinx.response.groups.discuss.replies.Replies;
import net.jeremybrooks.jinx.response.groups.discuss.replies.Reply;
import net.jeremybrooks.jinx.response.groups.discuss.replies.ReplyInfo;
import net.jeremybrooks.jinx.response.groups.discuss.topics.Topic;
import net.jeremybrooks.jinx.response.groups.discuss.topics.TopicInfo;
import net.jeremybrooks.jinx.response.groups.discuss.topics.Topics;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class GroupsDiscussApiTest {
    private static GroupsDiscussRepliesApi groupsDiscussRepliesApi;
    private static GroupsDiscussTopicsApi groupsDiscussTopicsApi;

    private static final String PRIVATE_GROUP_NSID = "2638254@N20";

    private static final String PUBLIC_GROUP_NSID = "2723340@N22";

    @BeforeClass
    public static void beforeClass() throws Exception {
        groupsDiscussRepliesApi = new GroupsDiscussRepliesApi(JinxApiTestCommon.getJinx());
        groupsDiscussTopicsApi = new GroupsDiscussTopicsApi(JinxApiTestCommon.getJinx());
    }

    /**
     * The group discussion reply/topic API's are somewhat dependant on each other,
     * so this test exercises all of the group discussion API calls in one place.
     * This tests signed calls to a private group.
     * @throws Exception
     */
    @Test
    public void testGroupsDiscussApiSignedRequests() throws Exception {
        String subject = "Jinx unit test " + System.currentTimeMillis();
        String message = "This is an automated test of Jinx. Generated at " + new Date();

        // add
        Response response = groupsDiscussTopicsApi.add(PRIVATE_GROUP_NSID, subject, message);
        assertNotNull(response);
        assertEquals("ok", response.getStat());
        assertEquals(0, response.getCode());

        // list
        Topics topics = groupsDiscussTopicsApi.getList(PRIVATE_GROUP_NSID, 0, 0, true);
        assertNotNull(topics);
        assertEquals("ok", topics.getStat());
        assertEquals(0, topics.getCode());
        assertEquals(PRIVATE_GROUP_NSID, topics.getGroupId());
        List<Topic> list = topics.getTopicList();
        assertNotNull(list);
        // find our new topic in the list
        Topic myTopic = null;
        for (Topic t : list) {
            if (t.getSubject().equals(subject)) {
                myTopic = t;
            }
        }
        assertNotNull(myTopic);

        // info
        TopicInfo topicInfo = groupsDiscussTopicsApi.getInfo(myTopic.getTopicId(), true);
        assertNotNull(topicInfo);
        assertEquals("ok", topicInfo.getStat());
        assertEquals(0, topicInfo.getCode());
        assertEquals(myTopic.getTopicId(), topicInfo.getTopic().getTopicId());


        // now test reply api using the new topic
        String replyMessage = "This reply is from the Jinx unit tests. Generated at " + new Date();
        // add
        response = groupsDiscussRepliesApi.add(myTopic.getTopicId(), replyMessage);
        assertNotNull(response);
        assertEquals("ok", response.getStat());
        assertEquals(0, response.getCode());

        // get list
        Replies replies = groupsDiscussRepliesApi.getList(myTopic.getTopicId(), 0, 0, true);
        assertNotNull(replies);
        assertEquals("ok", replies.getStat());
        assertEquals(0, replies.getCode());
        assertNotNull(replies.getTopic());
        assertEquals(myTopic.getTopicId(), replies.getTopic().getTopicId());
        assertNotNull(replies.getReplyList());
        assertTrue(replies.getReplyList().size() > 0);
        // find my reply in the list
        Reply myReply = null;
        for (Reply reply : replies.getReplyList()) {
            if (reply.getMessage().equals(replyMessage)) {
                myReply = reply;
            }
        }
        assertNotNull(myReply);

        // edit
        String newReplyMessage = "This is an edited reply for replyId " + myReply.getReplyId();
        response = groupsDiscussRepliesApi.edit(myTopic.getTopicId(), myReply.getReplyId(), newReplyMessage);
        assertNotNull(response);
        assertEquals("ok", response.getStat());
        assertEquals(0, response.getCode());

        // get info
        ReplyInfo replyInfo = groupsDiscussRepliesApi.getInfo(myTopic.getTopicId(), myReply.getReplyId());
        assertNotNull(replyInfo);
        assertEquals("ok", replyInfo.getStat());
        assertEquals(0, replyInfo.getCode());
        assertNotNull(replyInfo.getReply());
        assertEquals(myReply.getReplyId(), replyInfo.getReply().getReplyId());

        // delete
        response = groupsDiscussRepliesApi.delete(myTopic.getTopicId(), myReply.getReplyId());
        assertNotNull(response);
        assertEquals("ok", response.getStat());
        assertEquals(0, response.getCode());
    }


    /**
     * This tests the group discussion methods that do not require authentication.
     * The test target group must be public, and the user must be in the group.
     * @throws Exception
     */
    @Test
    public void testGroupsDiscussApiUnsignedRequests() throws Exception {
        // list
        Topics topics = groupsDiscussTopicsApi.getList(PUBLIC_GROUP_NSID, 0, 0, false);
        assertNotNull(topics);
        assertEquals("ok", topics.getStat());
        assertEquals(0, topics.getCode());
        assertEquals(PUBLIC_GROUP_NSID, topics.getGroupId());
        List<Topic> list = topics.getTopicList();
        assertNotNull(list);

        // info
        String topicId = list.get(0).getTopicId();
        TopicInfo topicInfo = groupsDiscussTopicsApi.getInfo(topicId, false);
        assertNotNull(topicInfo);
        assertEquals("ok", topicInfo.getStat());
        assertEquals(0, topicInfo.getCode());
        assertEquals(topicId, topicInfo.getTopic().getTopicId());

        // get list
        Replies replies = groupsDiscussRepliesApi.getList(topicId, 0, 0, false);
        assertNotNull(replies);
        assertEquals("ok", replies.getStat());
        assertEquals(0, replies.getCode());
        assertNotNull(replies.getTopic());
        assertEquals(topicId, replies.getTopic().getTopicId());
        assertNotNull(replies.getReplyList());
        assertTrue(replies.getReplyList().size() > 0);
    }

}