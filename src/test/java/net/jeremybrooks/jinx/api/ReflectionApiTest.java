package net.jeremybrooks.jinx.api;

import net.jeremybrooks.jinx.Jinx;
import net.jeremybrooks.jinx.OAuthAccessToken;
import net.jeremybrooks.jinx.logger.JinxLogger;
import net.jeremybrooks.jinx.logger.StdoutLogger;
import net.jeremybrooks.jinx.response.reflection.MethodInfo;
import net.jeremybrooks.jinx.response.reflection.Methods;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

/**
 * @author Jeremy Brooks
 */
public class ReflectionApiTest {
  private static ReflectionApi reflectionApi;

  @BeforeClass
  public static void beforeClass() throws Exception {
    Properties p = new Properties();
    p.load(ReflectionApiTest.class.getResourceAsStream("/response/auth/secret.properties"));
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
    reflectionApi = new ReflectionApi(jinx);
  }

  @Test
  public void testGetMethods() throws Exception {
    Methods methods = reflectionApi.getMethods();
    assertNotNull(methods);
    assertNotNull(methods.getMethods());
    assertTrue(methods.getMethods().size() > 0);
  }

  @Test
  public void getGetMethodInfo() throws Exception {
    MethodInfo methodInfo = reflectionApi.getMethodInfo("flickr.photos.search");
    assertNotNull(methodInfo);
    assertNotNull(methodInfo.getMethod());
    assertEquals("flickr.photos.search", methodInfo.getMethod().getName());
    assertNotNull(methodInfo.getArguments());
    assertTrue(methodInfo.getArguments().size() > 0);
    assertNotNull(methodInfo.getErrors());
    assertTrue(methodInfo.getErrors().size() > 0);
  }
}
