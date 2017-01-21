package net.jeremybrooks.jinx.api;

import net.jeremybrooks.jinx.Jinx;
import net.jeremybrooks.jinx.JinxConstants;
import net.jeremybrooks.jinx.OAuthAccessToken;
import net.jeremybrooks.jinx.logger.JinxLogger;
import net.jeremybrooks.jinx.logger.StdoutLogger;
import net.jeremybrooks.jinx.response.photos.Photos;
import net.jeremybrooks.jinx.response.stats.Domains;
import net.jeremybrooks.jinx.response.stats.Referrers;
import net.jeremybrooks.jinx.response.stats.Stats;
import net.jeremybrooks.jinx.response.stats.TotalViews;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.Properties;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

/**
 * Test for stats require a pro account. To test these methods,
 * authorize Jinx on a pro account, then add the path to the token to the secrets.properties file,
 * with the property path.to.pro.oauth.token.
 *
 * You will also need to update the variables used by the test to match ID's on the pro account.
 *
 * @author Jeremy Brooks
 */
public class StatsApiTest {
  private static final String COLLECTION_ID = "72157616485685998";
  private static final String PHOTO_ID = "32412821155";
  private static final String PHOTOSET_ID = "72157676685668922";

  private static StatsApi statsApi;

  @BeforeClass
  public static void beforeClass() throws Exception {
    Properties p = new Properties();
    p.load(StatsApiTest.class.getResourceAsStream("/response/auth/secret.properties"));
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
    statsApi = new StatsApi(jinx);
  }

  @Test
  public void testGetCollectionDomains() throws Exception {
    Domains domains = statsApi.getCollectionDomains(new Date(), null, null, null);
    assertNotNull(domains);
    assertEquals("ok", domains.getStat());
  }

  @Test
  public void testGetCollectionReferrers() throws Exception {
    Referrers referrers = statsApi.getCollectionReferrers(new Date(), "flickr.com", null, null, null);
    assertNotNull(referrers);
    assertEquals("ok", referrers.getStat());
  }

  @Test
  public void testGetCollectionStats() throws Exception {
    Stats stats = statsApi.getCollectionStats(new Date(), COLLECTION_ID);
    assertNotNull(stats);
    assertEquals("ok", stats.getStat());
  }

  @Test
  public void testGetPhotoDomains() throws Exception {
    Domains domains = statsApi.getPhotoDomains(new Date(), null, null, null);
    assertNotNull(domains);
    assertEquals("ok", domains.getStat());
  }

  @Test
  public void testGetPhotoReferrers() throws Exception {
    Referrers referrers = statsApi.getPhotoReferrers(new Date(), "flickr.com", null, null, null);
    assertNotNull(referrers);
    assertEquals("ok", referrers.getStat());
  }

  @Test
  public void testGetPhotoStats() throws Exception {
    Stats stats = statsApi.getPhotoStats(new Date(), PHOTO_ID);
    assertNotNull(stats);
    assertEquals("ok", stats.getStat());
  }

  @Test
  public void testGetPhotosetDomains() throws Exception {
    Domains domains = statsApi.getPhotosetDomains(new Date(), null, null, null);
    assertNotNull(domains);
    assertEquals("ok", domains.getStat());
  }

  @Test
  public void testGetPhotosetReferrers() throws Exception {
    Referrers referrers = statsApi.getPhotosetReferrers(new Date(), "flickr.com", null, null, null);
    assertNotNull(referrers);
    assertEquals("ok", referrers.getStat());
  }

  @Test
  public void testGetPhotosetStats() throws Exception {
    Stats stats = statsApi.getPhotosetStats(new Date(), PHOTOSET_ID);
    assertNotNull(stats);
    assertEquals("ok", stats.getStat());
  }

  @Test
  public void testGetPhotostreamDomains() throws Exception {
    Domains domains = statsApi.getPhotostreamDomains(new Date(), null, null);
    assertNotNull(domains);
    assertEquals("ok", domains.getStat());
  }

  @Test
  public void testGetPhotostreamReferrers() throws Exception {
    Referrers referrers = statsApi.getPhotostreamReferrers(new Date(), "flickr.com", null, null);
    assertNotNull(referrers);
    assertEquals("ok", referrers.getStat());
  }

  @Test
  public void testGetPhotostreamStats() throws Exception {
    Stats stats = statsApi.getPhotostreamStats(new Date());
    assertNotNull(stats);
    assertEquals("ok", stats.getStat());
  }

  @Test
  public void testGetCSVFiles() throws Exception {
    Stats stats = statsApi.getCSVFiles();
    assertNotNull(stats);
    assertEquals("ok", stats.getStat());
    assertNotNull(stats.getCsvFiles());
    assertTrue(stats.getCsvFiles().size() > 0);
  }

  @Test
  public void testGetPopularPhotos() throws Exception {
    Photos photos = statsApi.getPopularPhotos(new Date(), JinxConstants.PopularPhotoSort.favorites, null, null);
    assertNotNull(photos);
    assertEquals("ok", photos.getStat());
    assertNotNull(photos.getPhotoList());
    assertTrue(photos.getPhotoList().size() > 0);
  }

  @Test
  public void testGetTotalViews() throws Exception {
    TotalViews totalViews = statsApi.getTotalViews(new Date());
    assertNotNull(totalViews);
    assertEquals("ok", totalViews.getStat());
  }
}
