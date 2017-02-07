package net.jeremybrooks.jinx.api;

import net.jeremybrooks.jinx.Jinx;
import net.jeremybrooks.jinx.OAuthAccessToken;
import net.jeremybrooks.jinx.logger.JinxLogger;
import net.jeremybrooks.jinx.logger.StdoutLogger;
import net.jeremybrooks.jinx.response.people.Person;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Jeremy Brooks
 */
public class PrefsApiTest {
  private static PrefsApi prefsApi;

  private static String testUserId = "124857539@N03";

  @BeforeClass
  public static void beforeClass() throws Exception {
    Properties p = new Properties();
    p.load(PrefsApiTest.class.getResourceAsStream("/response/auth/secret.properties"));
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
    prefsApi = new PrefsApi(jinx);
  }

  @Test
  public void testGetContentType() throws Exception {
    Person person = prefsApi.getContentType();
    assertNotNull(person);
    assertEquals(testUserId, person.getUserId());
    assertEquals(new Integer(1), person.getContentType());
  }

  @Test
  public void testGetGeoPerms() throws Exception {
    Person person = prefsApi.getGeoPerms();
    assertNotNull(person);
    assertEquals(testUserId, person.getUserId());
    assertEquals(new Integer(1), person.getGeoPerms());
    assertEquals(new Integer(1), person.getImportGeoExif());
  }

  @Test
  public void testGetHidden() throws Exception {
    Person person = prefsApi.getHidden();
    assertNotNull(person);
    assertEquals(testUserId, person.getUserId());
    assertEquals(new Integer(1), person.getHidden());
  }

  @Test
  public void testGetPrivacy() throws Exception {
    Person person = prefsApi.getPrivacy();
    assertNotNull(person);
    assertEquals(testUserId, person.getUserId());
    assertEquals(new Integer(1), person.getPrivacy());
  }

  @Test
  public void testGetSafetyLevel() throws Exception {
    Person person = prefsApi.getSafetyLevel();
    assertNotNull(person);
    assertEquals(testUserId, person.getUserId());
    assertEquals(new Integer(1), person.getSafetyLevel());
  }
}
