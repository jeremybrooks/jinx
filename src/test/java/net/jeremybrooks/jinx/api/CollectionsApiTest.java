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
import net.jeremybrooks.jinx.OAuthAccessToken;
import net.jeremybrooks.jinx.response.collections.CollectionInfo;
import net.jeremybrooks.jinx.response.collections.CollectionTree;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class CollectionsApiTest {

	private static CollectionsApi collectionsApi;

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

		collectionsApi = new CollectionsApi(new Jinx(p.getProperty("flickr.key"), p.getProperty("flickr.secret"), oAuthAccessToken));
	}

	@Test
	public void testGetInfo() throws Exception {
		CollectionInfo info = collectionsApi.getInfo("124834485-72157645054050881");
		assertNotNull(info);
		assertNotNull(info.getIconPhotoList());
		assertTrue(info.getIconPhotoList().size() > 0);
		assertEquals("124834485-72157645054050881", info.getCollectionId());
	}

	@Test
	public void testGetTree() throws Exception {
		CollectionTree tree = collectionsApi.getTree(null, null);
		assertNotNull(tree);
		assertNotNull(tree.getCollectionList());
		assertTrue(tree.getCollectionList().size() > 0);

		tree = collectionsApi.getTree(null, "85853333@N00");
		assertNotNull(tree);
				assertNotNull(tree.getCollectionList());
				assertTrue(tree.getCollectionList().size() > 0);


		tree = collectionsApi.getTree("124834485-72157645054050881", null);
		assertNotNull(tree);
				assertNotNull(tree.getCollectionList());
				assertTrue(tree.getCollectionList().size() > 0);


	}
}