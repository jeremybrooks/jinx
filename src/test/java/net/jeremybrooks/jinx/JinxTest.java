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

package net.jeremybrooks.jinx;

import com.github.scribejava.core.model.OAuth1RequestToken;
import net.jeremybrooks.jinx.api.OAuthApiTest;
import net.jeremybrooks.jinx.api.PhotosApi;
import net.jeremybrooks.jinx.response.photos.PhotoInfo;
import org.junit.Test;

import javax.swing.JOptionPane;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URLDecoder;
import java.util.Properties;
import java.util.StringTokenizer;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Most of the Jinx class gets tested by the Api tests.
 * This class allows testing of specific behavior.
 *
 * @author Jeremy Brooks
 */
public class JinxTest {


  @Test
  public void testFlickrErrorTrue() throws Exception {
    Properties p = new Properties();
    p.load(OAuthApiTest.class.getResourceAsStream("/response/auth/secret.properties"));

    String filename = p.getProperty("path.to.oauth.token");
    assertNotNull(filename);

    File file = new File(filename);
    assertTrue(file.exists());

    OAuthAccessToken oAuthAccessToken = new OAuthAccessToken();
    oAuthAccessToken.load(new FileInputStream(file));

    assertNotNull(oAuthAccessToken);

    Jinx jinx = new Jinx(p.getProperty("flickr.key"), p.getProperty("flickr.secret"), oAuthAccessToken, JinxConstants.OAuthPermissions.delete);
    PhotosApi photosApi = new PhotosApi(jinx);

    try {
      PhotoInfo info = photosApi.getInfo("nosuchphoto", null);
      // if we get here, there was no exception, so fail
      fail();
    } catch (JinxException je) {
      assertNotNull(je);
      assertNotEquals(0, je.getFlickrErrorCode());
      assertNotNull(je.getFlickrErrorMessage());
    }
  }

  @Test
  public void testFlickrErrorFalse() throws Exception {
    Properties p = new Properties();
    p.load(OAuthApiTest.class.getResourceAsStream("/response/auth/secret.properties"));

    String filename = p.getProperty("path.to.oauth.token");
    assertNotNull(filename);

    File file = new File(filename);
    assertTrue(file.exists());

    OAuthAccessToken oAuthAccessToken = new OAuthAccessToken();
    oAuthAccessToken.load(new FileInputStream(file));

    assertNotNull(oAuthAccessToken);

    Jinx jinx = new Jinx(p.getProperty("flickr.key"), p.getProperty("flickr.secret"), oAuthAccessToken,
        JinxConstants.OAuthPermissions.delete);
    jinx.setFlickrErrorThrowsException(false);
    PhotosApi photosApi = new PhotosApi(jinx);

    PhotoInfo info = photosApi.getInfo("nosuchphoto", null);
    assertNotEquals(0, info.getCode());
  }


  @Test
  public void testSetProxy() throws Exception {
    Properties p = new Properties();
    p.load(OAuthApiTest.class.getResourceAsStream("/response/auth/secret.properties"));

    String filename = p.getProperty("path.to.oauth.token");
    assertNotNull(filename);

    File file = new File(filename);
    assertTrue(file.exists());

    OAuthAccessToken oAuthAccessToken = new OAuthAccessToken();
    oAuthAccessToken.load(new FileInputStream(file));

    assertNotNull(oAuthAccessToken);

    Jinx jinx = new Jinx(p.getProperty("flickr.key"), p.getProperty("flickr.secret"), oAuthAccessToken,
        JinxConstants.OAuthPermissions.delete);
    assertFalse(jinx.isUseProxy());
    jinx.setProxy(new JinxProxy("fake.com", 1234, "proxyuser", "proxypass".toCharArray()));
    assertTrue(jinx.isUseProxy());
    jinx.setProxy(null);
    assertFalse(jinx.isUseProxy());
  }

  @Test
  public void testParseOauthResponse() throws Exception {
    String response = "fullname=Jeremy%20Brooks&oauth_token=72157632924311715-b9b1f0bf94982fba&oauth_token_secret=d25a16fa2e923649&user_nsid=85853333%40N00&username=Jeremy%20Brooks\n";
    StringTokenizer tok = new StringTokenizer(response, "&");
    while (tok.hasMoreTokens()) {
      String token = tok.nextToken();
                /*
                fullname=Jeremy%20Brooks&oauth_token=72157632924811715-b9b1f0bf94982fba&oauth_token_secret=d25a168a2e923649&user_nsid=85853333%40N00&username=Jeremy%20Brooks)
                 */
      int index = token.indexOf("=");
      String key = token.substring(0, index);
      String value = URLDecoder.decode(token.substring(index + 1), "UTF-8").trim();
      switch (key) {
        case "fullname":
          assertEquals("Jeremy Brooks", value);
          break;
        case "user_nsid":
          assertEquals("85853333@N00", value);
          break;
        case "username":
          assertEquals("Jeremy Brooks", value);
          break;
      }
    }
  }

  @Test
  public void testOauthAccessWorkflow() throws Exception {
//        testGetOauthAccessToken();
  }

  /*
   * Test the oAuth workflow
   */
  private void testGetOauthAccessToken() throws Exception {
    Properties p = new Properties();
    p.load(OAuthApiTest.class.getResourceAsStream("/response/auth/secret.properties"));

    String filename = p.getProperty("path.to.oauth.token");

    Jinx jinx = new Jinx(p.getProperty("flickr.key"), p.getProperty("flickr.secret"),
        JinxConstants.OAuthPermissions.delete);

    // step 1
    OAuth1RequestToken requestToken = jinx.getRequestToken();
    assertNotNull(requestToken);

    // step 2
    String url = jinx.getAuthorizationUrl(requestToken);
    assertNotNull(url);

    System.out.println(url);
    String verificationCode = JOptionPane.showInputDialog("Authorize at \n " + url + "\nand then enter the validation code.");
    assertNotNull(verificationCode);

    // step 3
    OAuthAccessToken accessToken = jinx.getAccessToken(requestToken, verificationCode);
    assertNotNull(accessToken);

    accessToken.store(new FileOutputStream(new File(filename)));
  }
}