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

import net.jeremybrooks.jinx.JinxConstants;
import net.jeremybrooks.jinx.response.photos.Photos;
import net.jeremybrooks.jinx.response.tags.Clusters;
import net.jeremybrooks.jinx.response.tags.HotList;
import net.jeremybrooks.jinx.response.tags.PhotoTagList;
import net.jeremybrooks.jinx.response.tags.RawTagsForUser;
import net.jeremybrooks.jinx.response.tags.RelatedTags;
import net.jeremybrooks.jinx.response.tags.TagsForUser;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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
    tagsApi = new TagsApi(JinxApiTestCommon.getJinx());
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
