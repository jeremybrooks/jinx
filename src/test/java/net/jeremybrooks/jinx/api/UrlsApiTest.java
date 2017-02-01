package net.jeremybrooks.jinx.api;

import net.jeremybrooks.jinx.Jinx;
import net.jeremybrooks.jinx.OAuthAccessToken;
import net.jeremybrooks.jinx.logger.JinxLogger;
import net.jeremybrooks.jinx.logger.StdoutLogger;
import net.jeremybrooks.jinx.response.galleries.Gallery;
import net.jeremybrooks.jinx.response.galleries.GalleryInfo;
import net.jeremybrooks.jinx.response.urls.GroupUrls;
import net.jeremybrooks.jinx.response.urls.UserUrls;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * @author Jeremy Brooks
 */
public class UrlsApiTest {
  private static UrlsApi urlsApi;

  private String groupId = "2684447@N23";
  private String groupUrl = "https://www.flickr.com/groups/2684447@N23/";
  private String userId = "85853333@N00";
  private String userPhotosUrl = "https://www.flickr.com/photos/jeremybrooks/";
  private String userProfileUrl = "https://www.flickr.com/people/jeremybrooks/";
  private String galleryUrl = "https://www.flickr.com/photos/jinxlib/galleries/72157644721099547";

  @BeforeClass
  public static void beforeClass() throws Exception {
    Properties p = new Properties();
    p.load(UrlsApiTest.class.getResourceAsStream("/response/auth/secret.properties"));
    String filename = p.getProperty("path.to.pro.oauth.token");
    assertNotNull(filename);
    File file = new File(filename);
    assertTrue(file.exists());
    OAuthAccessToken oAuthAccessToken = new OAuthAccessToken();
    oAuthAccessToken.load(new FileInputStream(file));
    assertNotNull(oAuthAccessToken);
    Jinx jinx = new Jinx(p.getProperty("flickr.key"), p.getProperty("flickr.secret"), oAuthAccessToken);
//        jinx.setVerboseLogging(true);
    JinxLogger.setLogger(new StdoutLogger());
    urlsApi = new UrlsApi(jinx);
  }

  @Test
  public void testGetGroup() throws Exception {
    GroupUrls groupUrls = urlsApi.getGroup(groupId);
    assertNotNull(groupUrls);
    assertEquals(groupId, groupUrls.getGroupId());
    assertEquals(groupUrl, groupUrls.getUrl());
  }

  @Test
  public void testLookupGroup() throws Exception {
    GroupUrls groupUrls = urlsApi.lookupGroup(groupUrl);
    assertNotNull(groupUrls);
    assertEquals(groupId, groupUrls.getGroupId());
    assertEquals("Jinx Test Group", groupUrls.getGroupName());
  }

  @Test
  public void testGetUserPhotos() throws Exception {
    UserUrls userUrls = urlsApi.getUserPhotos(null);
    assertNotNull(userUrls);
    userUrls = urlsApi.getUserPhotos(userId);
    assertNotNull(userUrls);
    assertEquals(userId, userUrls.getUserId());
    assertEquals(userPhotosUrl, userUrls.getUrl());
  }

  @Test
  public void testGetUserProfile() throws Exception {
    UserUrls userUrls = urlsApi.getUserProfile(null);
    assertNotNull(userUrls);
    userUrls = urlsApi.getUserProfile(userId);
    assertEquals(userId, userUrls.getUserId());
    assertEquals(userProfileUrl, userUrls.getUrl());
  }

  @Test
  public void testLookupUser() throws Exception {
    UserUrls userUrls = urlsApi.lookupUser(userPhotosUrl);
    assertNotNull(userUrls);
    assertEquals(userId, userUrls.getUserId());
    assertEquals("Jeremy Brooks", userUrls.getUsername());

    userUrls = urlsApi.lookupUser(userProfileUrl);
    assertNotNull(userUrls);
    assertEquals(userId, userUrls.getUserId());
    assertEquals("Jeremy Brooks", userUrls.getUsername());
  }

  @Test
  public void testLookupGallery() throws Exception {
    GalleryInfo info = urlsApi.lookupGallery(galleryUrl);
    assertNotNull(info);
    Gallery gallery = info.getGallery();
    assertEquals(galleryUrl, gallery.getUrl());
    assertEquals("jinxlib", gallery.getUsername());
  }
}
