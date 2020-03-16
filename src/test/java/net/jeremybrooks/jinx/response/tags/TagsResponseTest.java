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

package net.jeremybrooks.jinx.response.tags;

import com.google.gson.Gson;
import net.jeremybrooks.jinx.response.photos.Photo;
import net.jeremybrooks.jinx.response.photos.Photos;
import net.jeremybrooks.jinx.response.photos.Tag;
import net.jeremybrooks.jinx.response.stats.StatsTest;
import org.junit.Test;

import java.io.InputStreamReader;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 */
public class TagsResponseTest {

  @Test
  public void testGetClusterPhotos() throws Exception {
    InputStreamReader reader = new InputStreamReader(StatsTest.class.getResourceAsStream("/response/tags/sample_get_cluster_photos.json"));
    Photos photos = new Gson().fromJson(reader, Photos.class);
    assertNotNull(photos);
    assertNotNull(photos.getPhotoList());
    Photo photo = photos.getPhotoList().get(0);
    assertEquals("4089606804", photo.getPhotoId());
    assertEquals("f678776d60", photo.getSecret());
    assertEquals("2726", photo.getServer());
    assertEquals("3", photo.getFarm());
    assertEquals("28537042@N06", photo.getOwner());
    assertEquals("John in Mich", photo.getUsername());
    assertEquals("Late Nite Liquor & Lotto", photo.getTitle());
    assertTrue(photo.isPublic());
    assertFalse(photo.isFriend());
    assertFalse(photo.isFamily());
  }

  @Test
  public void testGetClusters() throws Exception {
    InputStreamReader reader = new InputStreamReader(StatsTest.class.getResourceAsStream("/response/tags/sample_get_clusters.json"));
    Clusters clusters = new Gson().fromJson(reader, Clusters.class);
    assertNotNull(clusters);
    assertEquals("neon", clusters.getSource());
    assertEquals(Integer.valueOf(4), clusters.getTotal());
    assertNotNull(clusters.getClusterList());
    Cluster cluster = clusters.getClusterList().get(1);
    assertNotNull(cluster);
    assertEquals(Integer.valueOf(6), cluster.getTotal());
    assertNotNull(cluster.getTagList());
    assertEquals("newyork", cluster.getTagList().get(0).getTag());
  }

  @Test
  public void testGetHotlist() throws Exception {
    InputStreamReader reader = new InputStreamReader(StatsTest.class.getResourceAsStream("/response/tags/sample_get_hotlist.json"));
    HotList hotlist = new Gson().fromJson(reader, HotList.class);
    assertNotNull(hotlist);
    assertEquals("day", hotlist.getPeriod());
    assertEquals(Integer.valueOf(20), hotlist.getCount());
    assertNotNull(hotlist.getTagList());
    Tag tag = hotlist.getTagList().get(0);
    assertEquals(Integer.valueOf(100), tag.getScore());
    assertEquals("feb23", tag.getTag());
  }

  @Test
  public void testGetListPhoto() throws Exception {
    InputStreamReader reader = new InputStreamReader(StatsTest.class.getResourceAsStream("/response/tags/sample_get_list_photo.json"));
    PhotoTagList photoTagList = new Gson().fromJson(reader, PhotoTagList.class);
    assertNotNull(photoTagList);
    assertEquals("32084267640", photoTagList.getPhotoId());
    assertNotNull(photoTagList.getTagList());
    Tag tag = photoTagList.getTagList().get(0);
    assertNotNull(tag);
    assertEquals("4956757-32084267640-11082", tag.getTagId());
    assertEquals( "85853333@N00", tag.getAuthor());
    assertEquals("Jeremy Brooks", tag.getAuthorName());
    assertEquals("Bus Stop", tag.getRaw());
    assertEquals("busstop", tag.getTag());
    assertFalse(tag.isMachineTag());
  }

  @Test
  public void testGetListUserPopular() throws Exception {
    InputStreamReader reader = new InputStreamReader(StatsTest.class.getResourceAsStream("/response/tags/sample_get_list_user_popular.json"));
    TagsForUser popularTagsForUser = new Gson().fromJson(reader, TagsForUser.class);
    assertNotNull(popularTagsForUser);
    assertEquals("85853333@N00", popularTagsForUser.getUserId());
    assertNotNull(popularTagsForUser.getTagList());
    Tag tag = popularTagsForUser.getTagList().get(0);
    assertNotNull(tag);
    assertEquals(Integer.valueOf(5171), tag.getCount());
    assertEquals("blackandwhite", tag.getTag());
  }

  @Test
  public void testGetListUserRaw() throws Exception {
    InputStreamReader reader = new InputStreamReader(StatsTest.class.getResourceAsStream("/response/tags/sample_get_list_user_raw.json"));
    RawTagsForUser rawTags = new Gson().fromJson(reader, RawTagsForUser.class);
    assertNotNull(rawTags);
    assertEquals("85853333@N00", rawTags.getUserId());
    assertNotNull(rawTags.getRawTagList());
    RawTag rawTag = rawTags.getRawTagList().get(0);
    assertEquals("blackandwhite", rawTag.getCleanTag());
    assertNotNull(rawTag.getRawTags());
    List<String> tags = rawTag.getRawTags();
    assertEquals("blackandwhite", tags.get(0));
    assertEquals("Black And White", tags.get(1));
    assertEquals("Black and White", tags.get(2));
  }

  @Test
  public void testGetListUser() throws Exception {
    InputStreamReader reader = new InputStreamReader(StatsTest.class.getResourceAsStream("/response/tags/sample_get_list_user.json"));
    TagsForUser tagsForUser = new Gson().fromJson(reader, TagsForUser.class);
    assertNotNull(tagsForUser);
    assertEquals("124857539@N03", tagsForUser.getUserId());
    assertNotNull(tagsForUser.getTagList());
    Tag tag = tagsForUser.getTagList().get(0);
    assertNotNull(tag);
    assertEquals("44montgomery", tag.getTag());
  }

  @Test
  public void testGetMostFrequentlyUsed() throws Exception {
    InputStreamReader reader = new InputStreamReader(StatsTest.class.getResourceAsStream("/response/tags/sample_get_most_frequently_used.json"));
    TagsForUser tagsForUser = new Gson().fromJson(reader, TagsForUser.class);
    assertNotNull(tagsForUser);
    assertEquals("124857539@N03", tagsForUser.getUserId());
    assertNotNull(tagsForUser.getTagList());
    Tag tag = tagsForUser.getTagList().get(0);
    assertNotNull(tag);
    assertEquals("44montgomery", tag.getTag());
    assertEquals(Integer.valueOf(2), tag.getCount());
  }

  @Test
  public void testGetRelated() throws Exception {
    InputStreamReader reader = new InputStreamReader(StatsTest.class.getResourceAsStream("/response/tags/sample_get_related.json"));
    RelatedTags relatedTags = new Gson().fromJson(reader, RelatedTags.class);
    assertNotNull(relatedTags);
    assertEquals("neon", relatedTags.getSource());
    assertNotNull(relatedTags.getTagList());
    assertEquals("sign", relatedTags.getTagList().get(0).getTag());
  }
}
