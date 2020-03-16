/*
 * Jinx is Copyright 2010-2020 by Jeremy Brooks and Contributors
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

import net.jeremybrooks.jinx.response.galleries.Gallery;
import net.jeremybrooks.jinx.response.galleries.GalleryInfo;
import net.jeremybrooks.jinx.response.urls.GroupUrls;
import net.jeremybrooks.jinx.response.urls.UserUrls;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
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
    urlsApi = new UrlsApi(JinxApiTestCommon.getJinx());
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
