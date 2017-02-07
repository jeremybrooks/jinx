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
    assertEquals(new Long(1486432778), subscription.getDateCreate());
    assertNull(subscription.getLeaseSeconds());
    assertEquals(new Long(0), subscription.getExpiry());
    assertEquals(new Integer(1), subscription.getVerifyAttempts());
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
