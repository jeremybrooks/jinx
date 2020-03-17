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

package net.jeremybrooks.jinx.response.blogs;

import com.google.gson.Gson;
import net.jeremybrooks.jinx.response.activity.ActivityResponseTest;
import org.junit.Test;

import java.io.InputStreamReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

/**
 * @author Jeremy Brooks
 */
public class BlogServicesTest {

	@Test
	public void testParseBlogServicesFromJson() throws Exception {
		InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/blogs/sample_blog_services.json"));
		BlogServices response = new Gson().fromJson(reader, BlogServices.class);
		reader.close();
		assertNotNull(response);
		assertEquals("ok", response.getStat());
		assertEquals(5, response.getServiceList().size());
		for (BlogServices.Service service : response.getServiceList()) {
			if (service.getId().equals("beta.blogger.com")) {
				assertEquals("Blogger", service.getName());
			} else if (service.getId().equals("LiveJournal")) {
				assertEquals("LiveJournal", service.getName());
			} else if (service.getId().equals("MetaWeblogAPI")) {
				assertEquals("Wordpress", service.getName());
			} else if (service.getId().equals("BloggerAPI")) {
				assertEquals("BloggerAPI", service.getName());
			} else if (service.getId().equals("Twitter")) {
				assertEquals("Twitter", service.getName());
			} else {
				fail("Unxpected ID: " + service.getId());
			}
		}
	}
}
