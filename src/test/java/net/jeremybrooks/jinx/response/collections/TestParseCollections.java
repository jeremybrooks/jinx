/*
 * Jinx is Copyright 2010-2014 by Jeremy Brooks and Contributors
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

package net.jeremybrooks.jinx.response.collections;

import com.google.gson.Gson;
import net.jeremybrooks.jinx.response.activity.ActivityResponseTest;
import org.junit.Test;

import java.io.InputStreamReader;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Jeremy Brooks
 */
public class TestParseCollections {

	@Test
	public void testParseGetInfo() throws Exception {
		InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/collections/sample_get_info.json"));
		CollectionInfo info = new Gson().fromJson(reader, CollectionInfo.class);
		reader.close();
		assertNotNull(info);
		assertEquals("ok", info.getStat());
		assertEquals(0, info.getCode());
		assertEquals("124834485-72157645054050881", info.getCollectionId());
		assertEquals("A Collection", info.getTitle());
		assertEquals("Just a collection for testing.", info.getDescription());
		assertEquals(2, (int)info.getChildCount());
		assertEquals("1402198715", info.getDateCreate());
		assertEquals("https://farm3.staticflickr.com/2901/cols/72157645054050881_23068610a5_l.jpg", info.getIconLarge());
		assertEquals("https://farm3.staticflickr.com/2901/cols/72157645054050881_23068610a5_s.jpg", info.getIconSmall());
		assertEquals("2901", info.getServer());
		assertEquals("23068610a5", info.getSecret());
		List<CollectionInfo.IconPhoto> iconPhotos = info.getIconPhotoList();
		assertNotNull(iconPhotos);
		assertEquals(12, iconPhotos.size());
		CollectionInfo.IconPhoto p = iconPhotos.get(0);
		assertEquals("14296965463", p.getPhotoId());
		assertEquals("124857539@N03", p.getOwner());
		assertEquals("683489a38a", p.getSecret());
		assertEquals("5575", p.getServer());
		assertEquals(6, (int)p.getFarm());
		assertEquals("Might Not Happen Soon", p.getTitle());
		assertTrue(p.isPublic());
		assertFalse(p.isFriend());
		assertFalse(p.isFamily());
	}

	@Test
	public void testParseGetTree() throws Exception {
		InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/collections/sample_get_tree.json"));
		CollectionTree collections = new Gson().fromJson(reader, CollectionTree.class);
		reader.close();
		assertNotNull(collections);
		System.out.println(collections.toString());
		assertEquals("ok", collections.getStat());
		assertEquals(0, collections.getCode());
		assertNotNull(collections.getCollectionList());
		List<CollectionTree.Collection> list = collections.getCollectionList();
		assertEquals(1, list.size());
		CollectionTree.Collection collection = list.get(0);
		assertEquals("124834485-72157645054050881", collection.getCollectionId());
		assertEquals("A Collection", collection.getTitle());
		assertEquals("Just a collection for testing.", collection.getDescription());
		assertEquals("https://farm3.staticflickr.com/2901/cols/72157645054050881_23068610a5_l.jpg", collection.getIconLarge());
		assertEquals("https://farm3.staticflickr.com/2901/cols/72157645054050881_23068610a5_s.jpg", collection.getIconSmall());
		List<CollectionTree.Collection.Set> setList = collection.getSetList();
		assertNotNull(setList);
		assertEquals(2, setList.size());
		CollectionTree.Collection.Set set = setList.get(0);
		assertEquals("72157644447148480", set.getPhotosetId());
		assertEquals("Istanbul", set.getTitle());
		assertEquals("Photos from Istanbul, Turkey.", set.getDescription());
	}
}
