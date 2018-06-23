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
import net.jeremybrooks.jinx.OAuthAccessToken;
import net.jeremybrooks.jinx.logger.JinxLogger;
import net.jeremybrooks.jinx.logger.StdoutLogger;
import net.jeremybrooks.jinx.response.push.Topics;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Jeremy Brooks
 */
public class PushApiTest {
  private static PushApi pushApi;

  @BeforeClass
  public static void beforeClass() throws Exception {
    Properties p = new Properties();
    p.load(PushApi.class.getResourceAsStream("/response/auth/secret.properties"));
    String filename = p.getProperty("path.to.oauth.token");
    assertNotNull(filename);
    File file = new File(filename);
    assertTrue(file.exists());
    OAuthAccessToken oAuthAccessToken = new OAuthAccessToken();
    oAuthAccessToken.load(new FileInputStream(file));
    assertNotNull(oAuthAccessToken);
    Jinx jinx = new Jinx(p.getProperty("flickr.key"), p.getProperty("flickr.secret"), oAuthAccessToken);
//        jinx.setVerboseLogging(true);
    JinxLogger.setLogger(new StdoutLogger());
    pushApi = new PushApi(jinx);
  }

  @Test
  public void testGetTopics() throws Exception {
    Topics topics = pushApi.getTopics();
    assertNotNull(topics);
    assertNotNull(topics.getTopicList());
    assertTrue(topics.getTopicList().size() > 0);
  }
}
