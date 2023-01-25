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

package net.jeremybrooks.jinx.api;

import net.jeremybrooks.jinx.response.activity.ActivityResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Jeremy Brooks
 */
public class ActivityApiTest {
	private static ActivityApi activityApi;

	@BeforeClass
	public static void beforeClass() throws Exception {
		activityApi = new ActivityApi(JinxApiTestCommon.getJinx());
	}

	@Test
	public void testUserComments() throws Exception {
		ActivityResponse response = activityApi.userComments(10, 1);
		assertNotNull(response);
		assertEquals("ok", response.getStat());
	}

	@Test
	public void testUserPhotos() throws Exception {
		ActivityResponse response = activityApi.userPhotos("2d", 10, 1);
		assertNotNull(response);
		assertEquals("ok", response.getStat());
	}
}
