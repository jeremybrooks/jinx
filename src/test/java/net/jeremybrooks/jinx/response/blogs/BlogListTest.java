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

package net.jeremybrooks.jinx.response.blogs;

import com.google.gson.Gson;
import net.jeremybrooks.jinx.response.activity.ActivityResponseTest;
import org.junit.Test;

import java.io.InputStreamReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * @author Jeremy Brooks
 */
public class BlogListTest {

	@Test
	public void testParseBlogListFromJson() throws Exception {
		InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/blogs/sample_blog_list.json"));
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
	}
}
