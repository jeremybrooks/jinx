/*
 * Jinx is Copyright 2010-2023 by Jeremy Brooks and Contributors
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

package net.jeremybrooks.jinx.response.urls;

import com.google.gson.Gson;
import net.jeremybrooks.jinx.response.galleries.Gallery;
import net.jeremybrooks.jinx.response.galleries.GalleryInfo;
import org.junit.Test;

import java.io.InputStreamReader;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

/**
 * @author Jeremy Brooks
 */
public class UrlsTestResponse {

  @Test
  public void testGetGroup() throws Exception {
    InputStreamReader reader = new InputStreamReader(UrlsTestResponse.class.getResourceAsStream("/response/urls/sample_get_group.json"));
    GroupUrls groupUrls = new Gson().fromJson(reader, GroupUrls.class);
    assertNotNull(groupUrls);
    assertEquals("2684447@N23", groupUrls.getGroupId());
    assertEquals("https://www.flickr.com/groups/2684447@N23/", groupUrls.getUrl());
  }

  @Test
  public void testLookupGroup() throws Exception {
    InputStreamReader reader = new InputStreamReader(UrlsTestResponse.class.getResourceAsStream("/response/urls/sample_lookup_group.json"));
    GroupUrls groupUrls = new Gson().fromJson(reader, GroupUrls.class);
    assertNotNull(groupUrls);
    assertEquals("2723340@N22", groupUrls.getGroupId());
    assertEquals("Public Jinx Test Group", groupUrls.getGroupName());
  }

  @Test
  public void testGetUserPhotos() throws Exception {
    InputStreamReader reader = new InputStreamReader(UrlsTestResponse.class.getResourceAsStream("/response/urls/sample_get_user_photos.json"));
    UserUrls userUrls = new Gson().fromJson(reader, UserUrls.class);
    assertNotNull(userUrls);
    assertEquals("85853333@N00", userUrls.getUserId());
    assertEquals("https://www.flickr.com/photos/jeremybrooks/", userUrls.getUrl());
  }

  @Test
  public void testGetUserProfile() throws Exception {
    InputStreamReader reader = new InputStreamReader(UrlsTestResponse.class.getResourceAsStream("/response/urls/sample_get_user_profile.json"));
    UserUrls userUrls = new Gson().fromJson(reader, UserUrls.class);
    assertNotNull(userUrls);
    assertEquals("85853333@N00", userUrls.getUserId());
    assertEquals("https://www.flickr.com/people/jeremybrooks/", userUrls.getUrl());
  }

  @Test
  public void testLookupUser() throws Exception {
    InputStreamReader reader = new InputStreamReader(UrlsTestResponse.class.getResourceAsStream("/response/urls/sample_lookup_user.json"));
    UserUrls userUrls = new Gson().fromJson(reader, UserUrls.class);
    assertNotNull(userUrls);
    assertEquals("124857539@N03", userUrls.getUserId());
    assertEquals("jinxlib", userUrls.getUsername());
  }


  @Test
  public void testLookupGallery() throws Exception {
    InputStreamReader reader = new InputStreamReader(UrlsTestResponse.class.getResourceAsStream("/response/urls/sample_lookup_gallery.json"));
    GalleryInfo info = new Gson().fromJson(reader, GalleryInfo.class);
    assertNotNull(info);
    assertNotNull(info.getGallery());
    Gallery gallery = info.getGallery();
    assertEquals("124834485-72157644721099547", gallery.getGalleryId());
    assertEquals("https://www.flickr.com/photos/jinxlib/galleries/72157644721099547", gallery.getUrl());
    assertEquals("124857539@N03", gallery.getOwner());
    assertEquals("jinxlib", gallery.getUsername());
    assertEquals("5538", gallery.getIconServer());
    assertEquals("6", gallery.getIconFarm());
    assertEquals("8413449964", gallery.getPrimaryPhotoId());
    assertEquals("1402548358", gallery.getDateCreate());
    assertEquals("1484094962", gallery.getDateUpdate());
    assertEquals(Integer.valueOf(13), gallery.getCountPhotos());
    assertEquals(Integer.valueOf(0), gallery.getCountVideos());
    assertEquals(Integer.valueOf(6), gallery.getCountViews());
    assertEquals(Integer.valueOf(0), gallery.getCountComments());
    assertEquals("Vintage Chinese Neon Signs (1484094961)", gallery.getTitle());
    assertEquals("A jinx test gallery. Test last run: 2017-01-10 16:36:01", gallery.getDescription());
    assertEquals("8473", gallery.getPrimaryPhotoServer());
    assertEquals(Integer.valueOf(9), gallery.getPrimaryPhotoFarm());
    assertEquals("edd91a1d17", gallery.getPrimaryPhotoSecret());
  }
}
