package net.jeremybrooks.jinx.response.stats;

import com.google.gson.Gson;
import net.jeremybrooks.jinx.response.photos.Photo;
import net.jeremybrooks.jinx.response.photos.Photos;
import org.junit.Test;

import java.io.InputStreamReader;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

/**
 flickr.stats.getTotalViews
 * @author Jeremy Brooks
 */
public class StatsTest {

  /**
   * Test populating a Domains object from a known json file.
   *
   * The domains response looks the same regardless of which stats were asked for, so we
   * only test creating Domains from one sample file.
   *
   * @throws Exception if there are any errors.
   */
  @Test
  public void testGetDomains() throws Exception {
    InputStreamReader reader = new InputStreamReader(StatsTest.class.getResourceAsStream("/response/stats/sample_get_photo_domains.json"));
    Domains domains = new Gson().fromJson(reader, Domains.class);
    assertNotNull(domains);
    assertEquals(new Integer(1), domains.getPage());
    assertEquals(new Integer(25), domains.getPerPage());
    assertEquals(new Integer(1), domains.getPages());
    assertEquals(new Integer(7), domains.getTotal());
    assertNotNull(domains.getDomainList());
    Domain domain = domains.getDomainList().get(0);
    assertNotNull(domain);
    assertEquals("flickr.com", domain.getName());
    assertEquals(new Integer(3486), domain.getViews());
  }

  /**
   * Test populating a Referrers object from a known json file.
   *
   * The referrers response looks the same regardless of which stats were asked for, so we
   * only test creating Referrers from one sample file.
   *
   * @throws Exception if there are any errors.
   */
  @Test
  public void testGetReferrers() throws Exception {
    InputStreamReader reader = new InputStreamReader(StatsTest.class.getResourceAsStream("/response/stats/sample_get_photo_referrers.json"));
    Referrers referrers = new Gson().fromJson(reader, Referrers.class);
    assertNotNull(referrers);
    assertEquals("flickr.com", referrers.getName());
    assertEquals(new Integer(1), referrers.getPage());
    assertEquals(new Integer(25), referrers.getPerPage());
    assertEquals(new Integer(57), referrers.getPages());
    assertEquals(new Integer(1420), referrers.getTotal());
    assertNotNull(referrers.getReferrerList());
    Referrer referrer = referrers.getReferrerList().get(1);
    assertNotNull(referrer);
    assertEquals("http://flickr.com/search/?q=contra+costa+county", referrer.getUrl());
    assertEquals(new Integer(172), referrer.getViews());
    assertEquals("contra costa county", referrer.getSearchTerm());
  }

  /**
   * Test populating a Stats object from a known json file.
   *
   * The stats response looks the same regardless of which stats were asked for, so we
   * only test creating Stats from one sample file.
   *
   * @throws Exception if there are any errors.
   */
  @Test
  public void testGetStats() throws Exception {
    InputStreamReader reader = new InputStreamReader(StatsTest.class.getResourceAsStream("/response/stats/sample_get_photo_stats.json"));
    Stats stats = new Gson().fromJson(reader, Stats.class);
    assertNotNull(stats);
    assertEquals(new Integer(12), stats.getViews());
    assertEquals(new Integer(5), stats.getComments());
    assertEquals(new Integer(50), stats.getFavorites());
  }

  @Test
  public void testGetCSVFiles() throws Exception {
    InputStreamReader reader = new InputStreamReader(StatsTest.class.getResourceAsStream("/response/stats/sample_get_csv_files.json"));
    Stats stats = new Gson().fromJson(reader, Stats.class);
    assertNotNull(stats);
    assertNotNull(stats.getCsvFiles());
    CSV csv = stats.getCsvFiles().get(0);
    assertEquals("https://farm5.staticflickr.com/4067/stats/72157624027090616_2aa4.csv", csv.getHref());
    assertEquals("daily", csv.getType());
    assertEquals("2010-04-01", csv.getDate());
  }

  @Test
  public void testGetPopularPhotos() throws Exception {
    InputStreamReader reader = new InputStreamReader(StatsTest.class.getResourceAsStream("/response/stats/sample_get_popular_photos.json"));
    Photos photos = new Gson().fromJson(reader, Photos.class);
    assertNotNull(photos);
    assertEquals(new Integer(1), photos.getPage());
    assertEquals(new Integer(385), photos.getPages());
    assertEquals(new Integer(5), photos.getPerPage());
    assertEquals(new Integer(1921), photos.getTotal());
    assertNotNull(photos.getPhotoList());
    Photo photo = photos.getPhotoList().get(0);
    assertEquals("2349210861", photo.getPhotoId());
    assertEquals("85853333@N00", photo.getOwner());
    assertEquals("a5b4d7bfc5", photo.getSecret());
    assertEquals("2265", photo.getServer());
    assertEquals("3", photo.getFarm());
    assertEquals("Elliot, plate 2", photo.getTitle());
    assertTrue(photo.isPublic());
    assertFalse(photo.isFriend());
    assertFalse(photo.isFamily());
    assertNotNull(photo.getPhotoStats());
    PhotoStats stats = photo.getPhotoStats();
    assertEquals(new Integer(61), stats.getViews());
    assertEquals(new Integer(0), stats.getComments());
    assertEquals(new Integer(0), stats.getFavorites());
    assertEquals(new Integer(1296), stats.getTotalViews());
    assertEquals(new Integer(1), stats.getTotalComments());
    assertEquals(new Integer(2), stats.getTotalFavorites());
  }

  @Test
  public void testGetTotalViews() throws Exception {
    InputStreamReader reader = new InputStreamReader(StatsTest.class.getResourceAsStream("/response/stats/sample_get_total_views.json"));
    TotalViews totalViews = new Gson().fromJson(reader, TotalViews.class);
    assertNotNull(totalViews);
    assertEquals(new Integer(1277), totalViews.getTotalViews());
    assertEquals(new Integer(1276), totalViews.getPhotosViews());
    assertEquals(new Integer(1), totalViews.getPhotostreamViews());
    assertEquals(new Integer(0), totalViews.getSetsViews());
    assertEquals(new Integer(0), totalViews.getCollectionsViews());
    assertEquals(new Integer(0), totalViews.getGalleriesViews());
  }
}
