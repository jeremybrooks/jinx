package net.jeremybrooks.jinx.api;

import net.jeremybrooks.jinx.Jinx;
import net.jeremybrooks.jinx.JinxConstants;
import net.jeremybrooks.jinx.OAuthAccessToken;
import net.jeremybrooks.jinx.logger.JinxLogger;
import net.jeremybrooks.jinx.logger.StdoutLogger;
import net.jeremybrooks.jinx.response.photos.Photos;
import net.jeremybrooks.jinx.response.tags.Clusters;
import net.jeremybrooks.jinx.response.tags.HotList;
import net.jeremybrooks.jinx.response.tags.PhotoTagList;
import net.jeremybrooks.jinx.response.tags.RawTagsForUser;
import net.jeremybrooks.jinx.response.tags.RelatedTags;
import net.jeremybrooks.jinx.response.tags.TagsForUser;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

/**
 * @author Jeremy Brooks
 */
public class TagsApiTest {
  private static TagsApi tagsApi;
  private static String jinxlibUserId = "124857539@N03";    // jinxlib
  @BeforeClass
  public static void beforeClass() throws Exception {
    Properties p = new Properties();
    p.load(TagsApiTest.class.getResourceAsStream("/response/auth/secret.properties"));
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
    tagsApi = new TagsApi(jinx);
  }

  @Test
  public void testGetClusterPhotos() throws Exception {
    Photos photos = tagsApi.getClusterPhotos("neon", "sign-night-light");
    assertNotNull(photos);
    assertNotNull(photos.getPhotoList());
    assertTrue(photos.getPhotoList().size() > 0);
  }

  @Test
  public void testGetClusterPhotosStringList() throws Exception {
    List<String> list = new ArrayList<>();
    list.add("sign");
    list.add("night");
    list.add("light");
    Photos photos = tagsApi.getClusterPhotos("neon", list);
    assertNotNull(photos);
    assertNotNull(photos.getPhotoList());
    assertTrue(photos.getPhotoList().size() > 0);
  }

  @Test
  public void testGetClusters() throws Exception {
    Clusters clusters = tagsApi.getClusters("neon");
    assertNotNull(clusters);
    assertNotNull(clusters.getClusterList());
    assertTrue(clusters.getClusterList().size() > 0);
  }

  @Test
  public void testGetHotList() throws Exception {
    HotList hotList = tagsApi.getHotList(null, null);
    assertNotNull(hotList);
    assertNotNull(hotList.getTagList());
    assertTrue(hotList.getTagList().size() > 0);
    hotList = tagsApi.getHotList(JinxConstants.Period.week, null);
    assertNotNull(hotList);
    assertNotNull(hotList.getTagList());
    assertTrue(hotList.getTagList().size() > 0);
    hotList = tagsApi.getHotList(JinxConstants.Period.day, 3);
    assertNotNull(hotList);
    assertNotNull(hotList.getTagList());
    assertTrue(hotList.getTagList().size() > 0);
  }

  @Test
  public void getListPhoto() throws Exception {
    PhotoTagList photoTagList = tagsApi.getListPhoto("14090247837");
    assertNotNull(photoTagList);
    assertNotNull(photoTagList.getTagList());
    assertTrue(photoTagList.getTagList().size() > 0);
  }

  @Test
  public void testGetListUser() throws Exception {
    TagsForUser tagsForUser = tagsApi.getListUser(jinxlibUserId);
    assertNotNull(tagsForUser);
    assertNotNull(tagsForUser.getTagList());
    assertTrue(tagsForUser.getTagList().size() > 0);
  }

  @Test
  public void testGetListUserPopular() throws Exception {
    TagsForUser tagsForUser = tagsApi.getListUserPopular(jinxlibUserId, null);
    assertNotNull(tagsForUser);
    assertNotNull(tagsForUser.getTagList());
    assertTrue(tagsForUser.getTagList().size() > 0);
  }

  @Test
  public void testGetRawTagsForUser() throws Exception {
    RawTagsForUser rawTagsForUser = tagsApi.getListUserRaw("blackandwhite");
    assertNotNull(rawTagsForUser);
    assertNotNull(rawTagsForUser.getRawTagList());
  }

  @Test
  public void testGetMostFrequentlyUsed() throws Exception {
    TagsForUser tagsForUser = tagsApi.getMostFrequentlyUsed();
    assertNotNull(tagsForUser);
    assertNotNull(tagsForUser.getTagList());
    assertTrue(tagsForUser.getTagList().size() > 0);
  }

  @Test
  public void testGetRelated() throws Exception {
    RelatedTags relatedTags = tagsApi.getRelated("neon");
    assertNotNull(relatedTags);
    assertNotNull(relatedTags.getTagList());
    assertTrue(relatedTags.getTagList().size() > 0);
    assertEquals("neon", relatedTags.getSource());
  }
}
