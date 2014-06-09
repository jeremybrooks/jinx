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

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

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