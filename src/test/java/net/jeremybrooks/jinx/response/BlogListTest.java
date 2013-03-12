package net.jeremybrooks.jinx.response;

import com.google.gson.Gson;
import org.junit.Test;

import java.io.InputStreamReader;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

/**
 * @author Jeremy Brooks
 */
public class BlogListTest {

	/* sample data:

		{ "blogs": {
		    "blog": [
		      { "id": "72157594526571385", "name": "Jeremy Brooks", "service": "MetaWeblogAPI", "needspassword": 1, "url": "http:\/\/www.whirljack.net\/jeremybrooks\/" },
		      { "id": "72157624726446757", "name": "Twitter: Jeremy Brooks", "service": "Twitter", "needspassword": 0, "url": "http:\/\/twitter.com\/jeremybrooks" }
		    ] }, "stat": "ok" }
	*/

	@Test
	public void testBlogListFromJson() throws Exception {
		InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/sample_blog_list.json"));
		BlogList list = new Gson().fromJson(reader, BlogList.class);
		reader.close();

		assertNotNull(list);
		assertEquals("ok", list.getStat());
		assertEquals(2, list.getBlogList().size());
		for (BlogList.Blog blog : list.getBlogList()) {
			assertNotNull(blog.getId());
			assertNotNull(blog.getService());
			assertNotNull(blog.getUrl());
			if (blog.getService().equals("MetaWeblogAPI")) {
				assertTrue(blog.isNeedsPassword());
				assertEquals("Jeremy Brooks", blog.getName());
			}
			if (blog.getService().equals("Twitter")) {
				assertFalse(blog.isNeedsPassword());
				assertEquals("Twitter: Jeremy Brooks", blog.getName());
			}
		}

		System.out.println(list);
	}
}
