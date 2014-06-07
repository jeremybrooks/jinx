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

package net.jeremybrooks.jinx.api;

import net.jeremybrooks.jinx.Jinx;
import net.jeremybrooks.jinx.OAuthAccessToken;
import net.jeremybrooks.jinx.response.blogs.BlogServices;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

/**
 * @author Jeremy Brooks
 */
public class BlogApiTest {

	private static BlogApi blogApi;

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

		blogApi = new BlogApi(new Jinx(p.getProperty("flickr.key"), p.getProperty("flickr.secret"), oAuthAccessToken));
	}


	@Test
	public void testGetServices() throws Exception {
		BlogServices services = blogApi.getServices();
		assertNotNull(services);
		assertEquals("ok", services.getStat());
	}

	// TODO waiting for answer from Flickr about the way data is returned when a user has no blogs
	@Test
	public void testGetBlogList() throws Exception {
//		BlogList list = blogApi.getBlogList(null);
//		assertNotNull(list);
//		assertEquals("ok", list.getStat());
	}


	/**
	 * Requires some real data to work. Fill in the fields and run manually to test.
	 * @throws Exception
	 */
	@Test
	public void testPost() throws Exception {
//		String blogId = null;
//		String photoId = null;
//		String title = null;
//		String description = null;
//		String password = null;
//		String service = null;
//
//		Response response = blogApi.postPhoto(blogId, photoId, title, description, password, service);
//		assertNotNull(response);
	}

}
