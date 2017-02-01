package net.jeremybrooks.jinx.response.test;

import com.google.gson.Gson;
import net.jeremybrooks.jinx.response.Response;
import org.junit.Test;

import java.io.InputStreamReader;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

/**
 * @author Jeremy Brooks
 */
public class TestResponseTest {

  @Test
  public void testEcho() throws Exception {
    InputStreamReader reader = new InputStreamReader(TestResponseTest.class.getResourceAsStream("/response/test/sample_test_echo.json"));
    TestEcho testEcho = new Gson().fromJson(reader, TestEcho.class);
    assertNotNull(testEcho);
    assertEquals("flickr.test.echo", testEcho.getMethod());
    assertEquals("5f33b2ecea05f95495fe5d584a621496", testEcho.getApiKey());
    assertEquals("json", testEcho.getFormat());
    assertEquals("1", testEcho.getNoJsonCallback());
    assertEquals("72157677991872651-690be8504e985bb0", testEcho.getAuthToken());
    assertEquals("65c031c322b42fb7803c8ed68cbbcd23", testEcho.getApiSig());
    assertEquals("ok", testEcho.getStat());
  }

  @Test
  public void testLogin() throws Exception {
    InputStreamReader reader = new InputStreamReader(TestResponseTest.class.getResourceAsStream("/response/test/sample_test_login.json"));
    TestLogin testLogin = new Gson().fromJson(reader, TestLogin.class);
    assertNotNull(testLogin);
    assertEquals("85853333@N00", testLogin.getUserId());
    assertEquals("Jeremy Brooks", testLogin.getUsername());
    assertEquals("ok", testLogin.getStat());
  }

  @Test
  public void testNull() throws Exception {
    InputStreamReader reader = new InputStreamReader(TestResponseTest.class.getResourceAsStream("/response/test/sample_test_null.json"));
    Response response = new Gson().fromJson(reader, Response.class);
    assertEquals("ok", response.getStat());
  }
}
