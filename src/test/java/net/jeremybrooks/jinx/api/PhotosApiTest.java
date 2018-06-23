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
import net.jeremybrooks.jinx.JinxConstants;
import net.jeremybrooks.jinx.JinxUtils;
import net.jeremybrooks.jinx.OAuthAccessToken;
import net.jeremybrooks.jinx.logger.JinxLogger;
import net.jeremybrooks.jinx.logger.StdoutLogger;
import net.jeremybrooks.jinx.response.Response;
import net.jeremybrooks.jinx.response.common.Context;
import net.jeremybrooks.jinx.response.photos.AddTags;
import net.jeremybrooks.jinx.response.photos.AllContexts;
import net.jeremybrooks.jinx.response.photos.ExifData;
import net.jeremybrooks.jinx.response.photos.Favorites;
import net.jeremybrooks.jinx.response.photos.PermsSetResponse;
import net.jeremybrooks.jinx.response.photos.Photo;
import net.jeremybrooks.jinx.response.photos.PhotoInfo;
import net.jeremybrooks.jinx.response.photos.PhotoPerms;
import net.jeremybrooks.jinx.response.photos.PhotoSizes;
import net.jeremybrooks.jinx.response.photos.Photocounts;
import net.jeremybrooks.jinx.response.photos.Photos;
import net.jeremybrooks.jinx.response.photos.SearchParameters;
import net.jeremybrooks.jinx.response.photos.Tag;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;
import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Jeremy Brooks
 */
public class PhotosApiTest {
  private static PhotosApi photosApi;
  private static String photoId = "14276832885";
  private static String deletePhotoId = null; // set this to run the delete photo test
  private static String userId = "124857539@N03";    // jinxlib

  @BeforeClass
  public static void beforeClass() throws Exception {
    Properties p = new Properties();
    p.load(OAuthApiTest.class.getResourceAsStream("/response/auth/secret.properties"));
    String filename = p.getProperty("path.to.oauth.token");
    assertNotNull(filename);
    File file = new File(filename);
    assertTrue(file.exists());
    OAuthAccessToken oAuthAccessToken = new OAuthAccessToken();
    oAuthAccessToken.load(new FileInputStream(file));
    assertNotNull(oAuthAccessToken);
    Jinx jinx = new Jinx(p.getProperty("flickr.key"), p.getProperty("flickr.secret"), oAuthAccessToken);
//        JinxLogger.setLogger(new StdoutLogger());
//        jinx.setVerboseLogging(true);
    photosApi = new PhotosApi(jinx);
  }


  /**
   * Tests for add and remove tags are in a single method to ensure the correct order of execution.
   *
   * @throws Exception
   */
  @Test
  public void testAddAndRemoveTags() throws Exception {
    List<String> newTags = new ArrayList<String>();
    newTags.add("tag" + System.currentTimeMillis());
    newTags.add("multiword tag  " + System.currentTimeMillis());
    AddTags tags = photosApi.addTags(photoId, newTags);
    assertNotNull(tags);
    assertEquals("ok", tags.getStat());
    assertEquals(0, tags.getCode());
    assertNotNull(tags.getTagList());
    List<Tag> tagList = tags.getTagList();
    assertEquals(2, tagList.size());


		/* testRemoveTag() */
    Response response = photosApi.removeTag(tagList.get(0).getFullTagId());
    assertNotNull(response);
    assertEquals("ok", response.getStat());
    assertEquals(0, response.getCode());
  }

  @Test
  public void testSetTags() throws Exception {
    List<String> tags = new ArrayList<String>();
    tags.add("Tag Added by Jinx tests");
    tags.add("jinxtest");
    tags.add("jinx:type=test");
    Response response = photosApi.setTags(photoId, null);
    assertNotNull(response);
    assertEquals("ok", response.getStat());
    assertEquals(0, response.getCode());

    response = photosApi.setTags(photoId, tags);
    assertNotNull(response);
    assertEquals("ok", response.getStat());
    assertEquals(0, response.getCode());
  }

  @Test
  public void testDelete() throws Exception {
    if (deletePhotoId != null) {
      Response response = photosApi.delete(deletePhotoId);
      assertNotNull(response);
      assertEquals("ok", response.getStat());
      assertEquals(0, response.getCode());
    }
  }

  @Test
  public void testGetAllContexts() throws Exception {
    AllContexts contexts = photosApi.getAllContexts(photoId);
    assertNotNull(contexts);
    assertEquals("ok", contexts.getStat());
    assertEquals(0, contexts.getCode());
    assertNotNull(contexts.getSetList());
  }

  @Test
  public void testGetContactsPhotos() throws Exception {
    Photos photos = photosApi.getContactsPhotos(5, false, false, false, null);
    assertNotNull(photos);
    assertEquals("ok", photos.getStat());
    assertEquals(0, photos.getCode());
    assertNotNull(photos.getPhotoList());
    assertEquals(5, photos.getPhotoList().size());

    photos = photosApi.getContactsPhotos(5, false, false, false, EnumSet.of(JinxConstants.PhotoExtras.date_taken, JinxConstants.PhotoExtras.url_sq));
    assertNotNull(photos);
    assertEquals("ok", photos.getStat());
    assertEquals(0, photos.getCode());
    assertNotNull(photos.getPhotoList());
    assertEquals(5, photos.getPhotoList().size());
    for (Photo p : photos.getPhotoList()) {
      assertNotNull(p.getDateTaken());
      assertNotNull(p.getUrlSq());
    }
  }

  @Test
  public void testGetContactsPublicPhotos() throws Exception {
    Photos photos = photosApi.getContactsPublicPhotos(userId, 5, true, false, true, null);
    assertNotNull(photos);
    assertEquals("ok", photos.getStat());
    assertEquals(0, photos.getCode());
    assertNotNull(photos.getPhotoList());
    assertEquals(5, photos.getPhotoList().size());
    photos = photosApi.getContactsPublicPhotos(userId, 5, false, false, false, EnumSet.of(JinxConstants.PhotoExtras.date_taken, JinxConstants.PhotoExtras.url_sq));
    assertNotNull(photos);
    assertEquals("ok", photos.getStat());
    assertEquals(0, photos.getCode());
    assertNotNull(photos.getPhotoList());
    assertEquals(5, photos.getPhotoList().size());
    for (Photo p : photos.getPhotoList()) {
      assertNotNull(p.getDateTaken());
      assertNotNull(p.getUrlSq());
    }
  }

  @Test
  public void testGetContext() throws Exception {
    Context context = photosApi.getContext(photoId);
    assertNotNull(context);
    assertEquals("ok", context.getStat());
    assertEquals(0, context.getCode());
    assertNotNull(context.getNextPhoto());
    assertNotNull(context.getPrevPhoto());
  }

  @Test
  public void testGetCounts() throws Exception {
    List<Date> dates = new ArrayList<Date>();
    dates.add(new Date());
    dates.add(JinxUtils.parseYMDToDate("2013-01-01"));
    dates.add(JinxUtils.parseYMDToDate("2012-01-01"));
    dates.add(JinxUtils.parseYMDToDate("2014-01-01"));
    Photocounts photocounts = photosApi.getCounts(dates, null);
    assertEquals("ok", photocounts.getStat());
    assertEquals(0, photocounts.getCode());
    assertNotNull(photocounts.getPhotocountList());
    assertEquals(3, photocounts.getPhotocountList().size());

    photocounts = photosApi.getCounts(null, dates);
    assertEquals("ok", photocounts.getStat());
    assertEquals(0, photocounts.getCode());
    assertNotNull(photocounts.getPhotocountList());
    assertEquals(3, photocounts.getPhotocountList().size());
  }

  @Test
  public void testGetExif() throws Exception {
    ExifData exifData = photosApi.getExif(photoId, null);
    assertNotNull(exifData);
    assertEquals("ok", exifData.getStat());
    assertEquals(0, exifData.getCode());
    assertEquals(photoId, exifData.getPhotoId());
    List<ExifData.Exif> exifList = exifData.getExifList();
    assertNotNull(exifList);
    assertTrue(exifList.size() > 0);
  }

  @Test
  public void testGetFavorites() throws Exception {
    Favorites favorites = photosApi.getFavorites("14253719326", 0, 0);
    assertNotNull(favorites);
    assertEquals("ok", favorites.getStat());
    assertEquals(0, favorites.getCode());
    assertEquals("14253719326", favorites.getPhotoId());
    assertNotNull(favorites.getPersonList());
    assertTrue(favorites.getPersonList().size() > 0);
  }

  @Test
  public void testGetInfo() throws Exception {
    PhotoInfo photoInfo = photosApi.getInfo(photoId, null);
    assertNotNull(photoInfo);
    assertEquals("ok", photoInfo.getStat());
    assertEquals(0, photoInfo.getCode());
    assertEquals(photoId, photoInfo.getPhotoId());
  }

  @Test
  public void testGetNotInSet() throws Exception {
    Photos photos = photosApi.getNotInSet(null, null, null, null, null, null, null, 0, 0);
    assertNotNull(photos);
    assertEquals("ok", photos.getStat());
    assertEquals(0, photos.getCode());
    assertNotNull(photos.getPhotoList());
  }

  @Test
  public void testGetPerms() throws Exception {
    PhotoPerms perms = photosApi.getPerms(photoId);
    assertNotNull(perms);
    assertEquals("ok", perms.getStat());
    assertEquals(0, perms.getCode());
    assertEquals(photoId, perms.getPhotoId());
    assertNotNull(perms.getPermAddMeta());
    assertNotNull(perms.getPermComment());
  }

  @Test
  public void testGetRecent() throws Exception {
    Photos photos = photosApi.getRecent(null, 0, 0);
    assertNotNull(photos);
    assertEquals("ok", photos.getStat());
    assertEquals(0, photos.getCode());
    assertNotNull(photos.getPhotoList());
  }

  @Test
  public void testGetSizes() throws Exception {
    PhotoSizes sizes = photosApi.getSizes(photoId);
    assertNotNull(sizes);
    assertEquals("ok", sizes.getStat());
    assertEquals(0, sizes.getCode());
    assertNotNull(sizes.getSizeList());
    assertTrue(sizes.getSizeList().size() > 0);
  }

  @Test
  public void testGetUntagged() throws Exception {
    Photos photos = photosApi.getUntagged(null, null, null, null, null, null, null, 0, 0);
    assertNotNull(photos);
    assertEquals("ok", photos.getStat());
    assertEquals(0, photos.getCode());
    assertNotNull(photos.getPhotoList());
  }


  @Test
  public void testGetWithGeoData() throws Exception {
    Photos photos = photosApi.getWithGeoData(null, null, null, null, null, null, null, null, 0, 0);
    assertNotNull(photos);
    assertEquals("ok", photos.getStat());
    assertEquals(0, photos.getCode());
    assertNotNull(photos.getPhotoList());
  }

  @Test
  public void testGetWithoutGeoData() throws Exception {
    Photos photos = photosApi.getWithoutGeoData(null, null, null, null, null, null, null, null, 0, 0);
    assertNotNull(photos);
    assertEquals("ok", photos.getStat());
    assertEquals(0, photos.getCode());
    assertNotNull(photos.getPhotoList());
  }

  @Test
  public void testRecentlyUpdated() throws Exception {
    Date date = new Date(System.currentTimeMillis() - 86400);
    Photos photos = photosApi.recentlyUpdated(date, null, 0, 0);
    assertNotNull(photos);
    assertEquals("ok", photos.getStat());
    assertEquals(0, photos.getCode());
    assertNotNull(photos.getPhotoList());
  }

  @Test
  public void testMachineTagWithSpaceSearch() throws Exception {
    List<String> machineTags = new ArrayList<String>();
    machineTags.add("camera:model=5d 2");
    SearchParameters sp = new SearchParameters();
    sp.setUserId(userId);
    sp.setMachineTags(machineTags);
    Photos photos = photosApi.search(sp);
    assertNotNull(photos);
    assertEquals("ok", photos.getStat());
    assertEquals(0, photos.getCode());
    assertNotNull(photos.getPhotoList());
    assertTrue(photos.getPhotoList().size() == 1);
    assertEquals("14276194043", photos.getPhotoList().get(0).getPhotoId());
  }

  @Test
  public void testMultipleMachineTagsSearch() throws Exception {
    List<String> machineTags = new ArrayList<String>();
    machineTags.add("camera:model=5d 2");
    machineTags.add("camera:model=5d2");
    SearchParameters sp = new SearchParameters();
    sp.setUserId(userId);
    sp.setMachineTags(machineTags);
    sp.setMachineTagMode(JinxConstants.TagMode.any);
    Photos photos = photosApi.search(sp);
    assertNotNull(photos);
    assertEquals("ok", photos.getStat());
    assertEquals(0, photos.getCode());
    assertNotNull(photos.getPhotoList());
    assertTrue(photos.getPhotoList().size() == 2);
  }

  @Test
  public void testSearch() throws Exception {
    List<String> tags = new ArrayList<String>();
    tags.add("Nature");
    List<String> machineTags = new ArrayList<String>();
    machineTags.add("sign:type=neon");

    SearchParameters sp = new SearchParameters();
    sp.setUserId(userId);
    sp.setTags(tags);
    Photos photos = photosApi.search(sp);
    assertNotNull(photos);
    assertEquals("ok", photos.getStat());
    assertEquals(0, photos.getCode());
    assertNotNull(photos.getPhotoList());
    assertTrue(photos.getPhotoList().size() > 0);

    sp.setMachineTags(machineTags);
    sp.setTags(null);
    photos = photosApi.search(sp);
    assertNotNull(photos);
    assertEquals("ok", photos.getStat());
    assertEquals(0, photos.getCode());
    assertNotNull(photos.getPhotoList());
    assertTrue(photos.getPhotoList().size() > 0);

    sp.setMachineTags(null);
    sp.setTags(tags);
    sp.setExtras(EnumSet.of(JinxConstants.PhotoExtras.date_taken, JinxConstants.PhotoExtras.url_sq));
    photos = photosApi.search(sp);
    assertNotNull(photos);
    assertEquals("ok", photos.getStat());
    assertEquals(0, photos.getCode());
    assertNotNull(photos.getPhotoList());
    assertTrue(photos.getPhotoList().size() > 0);
    for (Photo p : photos.getPhotoList()) {
      assertNotNull(p.getDateTaken());
      assertNotNull(p.getUrlSq());
    }
  }

  @Test
  public void testNegativeTagSearch() throws Exception {
    List<String> tags = new ArrayList<String>();
    tags.add("istanbul");
    tags.add("soda");
    SearchParameters sp = new SearchParameters();
    sp.setUserId(userId);
    sp.setTags(tags);
    Photos photos = photosApi.search(sp);
    assertNotNull(photos);
    assertEquals("ok", photos.getStat());
    assertEquals(0, photos.getCode());
    assertNotNull(photos.getPhotoList());
    assertTrue(photos.getPhotoList().size() > 0);
    int size = photos.getPhotoList().size();

    tags.clear();
    tags.add("istanbul");
    tags.add("-soda");
    sp.setTagMode(JinxConstants.TagMode.all);
    sp.setTags(tags);
    photos = photosApi.search(sp);
    assertNotNull(photos);
    assertEquals("ok", photos.getStat());
    assertEquals(0, photos.getCode());
    assertNotNull(photos.getPhotoList());
    assertTrue(photos.getPhotoList().size() > 0);
    assertEquals(size - 2, photos.getPhotoList().size());
  }

  @Test
  public void testSetContentType() throws Exception {
    Response response = photosApi.setContentType(photoId, JinxConstants.ContentType.other);
    assertNotNull(response);
    assertEquals("ok", response.getStat());
    assertEquals(0, response.getCode());
    response = photosApi.setContentType(photoId, JinxConstants.ContentType.photo);
    assertNotNull(response);
    assertEquals("ok", response.getStat());
    assertEquals(0, response.getCode());
  }

  @Test
  public void testSetDates() throws Exception {
    Response response = photosApi.setDates(photoId, new Date(), new Date(), 0);
    assertNotNull(response);
    assertEquals("ok", response.getStat());
    assertEquals(0, response.getCode());
  }

  @Test
  public void testSetMeta() throws Exception {
    Response response = photosApi.setMeta(photoId, "Test New Title", "This is a test description.");
    assertNotNull(response);
    assertEquals("ok", response.getStat());
    assertEquals(0, response.getCode());
  }

  @Test
  public void testSetPerms() throws Exception {
    PermsSetResponse psr = photosApi.setPerms(photoId, false, false, true, JinxConstants.Perms.contacts, JinxConstants.Perms.nobody);
    assertNotNull(psr);
    assertEquals("ok", psr.getStat());
    assertEquals(0, psr.getCode());
    psr = photosApi.setPerms(photoId, true, false, false, JinxConstants.Perms.everybody, JinxConstants.Perms.everybody);
    assertNotNull(psr);
    assertEquals("ok", psr.getStat());
    assertEquals(0, psr.getCode());
  }

  @Test
  public void testSetSafetyLevel() throws Exception {
    Response response = photosApi.setSafetyLevel(photoId, null, false);
    assertNotNull(response);
    assertEquals("ok", response.getStat());
    assertEquals(0, response.getCode());

    response = photosApi.setSafetyLevel(photoId, JinxConstants.SafetyLevel.moderate, true);
    assertNotNull(response);
    assertEquals("ok", response.getStat());
    assertEquals(0, response.getCode());

    response = photosApi.setSafetyLevel(photoId, JinxConstants.SafetyLevel.safe, false);
    assertNotNull(response);
    assertEquals("ok", response.getStat());
    assertEquals(0, response.getCode());
  }

  @Test
  public void testSearchOrientation() throws Exception {
    SearchParameters sp = new SearchParameters();
    sp.setUserId(userId);
    sp.setOrientations(EnumSet.of(JinxConstants.Orientation.square));

    Photos photos = photosApi.search(sp);
    assertNotNull(photos);
    assertEquals("ok", photos.getStat());
    assertEquals(0, photos.getCode());
    assertNotNull(photos.getPhotoList());
    assertTrue(photos.getPhotoList().size() > 0);

    sp.setOrientations(EnumSet.of(JinxConstants.Orientation.portrait, JinxConstants.Orientation.landscape));
    photos = photosApi.search(sp);
    assertNotNull(photos);
    assertEquals("ok", photos.getStat());
    assertEquals(0, photos.getCode());
    assertNotNull(photos.getPhotoList());
    assertTrue(photos.getPhotoList().size() > 0);
  }

  @Test
  public void testSearchColors() throws Exception {
    SearchParameters sp = new SearchParameters();
    sp.setUserId(userId);
    sp.setColorCodes(EnumSet.of(JinxConstants.ColorCode.orange));

    Photos photos = photosApi.search(sp);
    assertNotNull(photos);
    assertEquals("ok", photos.getStat());
    assertEquals(0, photos.getCode());
    assertNotNull(photos.getPhotoList());
    assertTrue(photos.getPhotoList().size() > 0);

    sp.setColorCodes(EnumSet.of(JinxConstants.ColorCode.green, JinxConstants.ColorCode.black));
    photos = photosApi.search(sp);
    assertNotNull(photos);
    assertEquals("ok", photos.getStat());
    assertEquals(0, photos.getCode());
    assertNotNull(photos.getPhotoList());
    assertTrue(photos.getPhotoList().size() > 0);
  }

  @Test
  public void testSearchStyle() throws Exception {
    SearchParameters sp = new SearchParameters();
    sp.setUserId(userId);
    sp.setPictureStyles(EnumSet.of(JinxConstants.PictureStyle.blackandwhite));

    Photos photos = photosApi.search(sp);
    assertNotNull(photos);
    assertEquals("ok", photos.getStat());
    assertEquals(0, photos.getCode());
    assertNotNull(photos.getPhotoList());
    assertTrue(photos.getPhotoList().size() > 0);
  }
}
