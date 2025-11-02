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

import net.jeremybrooks.jinx.response.push.Topics;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Jeremy Brooks
 */
public class PushApiTest {
  private static PushApi pushApi;

  @BeforeClass
  public static void beforeClass() throws Exception {
    pushApi = new PushApi(JinxApiTestCommon.getJinx());
  }

  @Test
  public void testGetTopics() throws Exception {
    Topics topics = pushApi.getTopics();
    assertNotNull(topics);
    assertNotNull(topics.getTopicList());
    assertTrue(topics.getTopicList().size() > 0);
  }
}
