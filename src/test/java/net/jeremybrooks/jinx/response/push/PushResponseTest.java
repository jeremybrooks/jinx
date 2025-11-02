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

package net.jeremybrooks.jinx.response.push;

import com.google.gson.Gson;
import org.junit.Test;

import java.io.InputStreamReader;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.assertNotNull;

/**
 * @author Jeremy Brooks
 */
public class PushResponseTest {

  @Test
  public void testGetSubscriptions() throws Exception {
    InputStreamReader reader = new InputStreamReader(PushResponseTest.class.getResourceAsStream("/response/push/sample_get_subscriptions.json"));
    Subscriptions subscriptions = new Gson().fromJson(reader, Subscriptions.class);
    assertNotNull(subscriptions);
    assertNotNull(subscriptions.getSubscriptionList());
    Subscription subscription = subscriptions.getSubscriptionList().get(0);
    assertNotNull(subscription);
    assertEquals("contacts_photos", subscription.getTopic());
    assertEquals("https://evening-wildwood-37988.herokuapp.com/streams.php", subscription.getCallback());
    assertTrue(subscription.getPending());
    assertEquals(Long.valueOf(1486432778), subscription.getDateCreate());
    assertNull(subscription.getLeaseSeconds());
    assertEquals(Long.valueOf(0), subscription.getExpiry());
    assertEquals(Integer.valueOf(1), subscription.getVerifyAttempts());
  }

  @Test
  public void testGetTopics() throws Exception {
    InputStreamReader reader = new InputStreamReader(PushResponseTest.class.getResourceAsStream("/response/push/sample_get_topics.json"));
    Topics topics = new Gson().fromJson(reader, Topics.class);
    assertNotNull(topics);
    assertNotNull(topics.getTopicList());
    Topic topic = topics.getTopicList().get(0);
    assertNotNull(topic);
    assertEquals("contacts_photos", topic.getName());
    assertEquals("photos from your contacts", topic.getDisplayName());
  }
}
