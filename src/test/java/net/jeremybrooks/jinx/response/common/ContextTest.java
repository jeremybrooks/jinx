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

package net.jeremybrooks.jinx.response.common;

import com.google.gson.Gson;
import net.jeremybrooks.jinx.response.activity.ActivityResponseTest;
import org.junit.Test;

import java.io.InputStreamReader;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;

/**
 * @author Jeremy Brooks
 */
public class ContextTest {
	@Test
	public void testParseContext() throws Exception {
		InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/common/sample_context.json"));
		Context context = new Gson().fromJson(reader, Context.class);
		reader.close();
		assertEquals(24, (int) context.getCount());
		assertEquals("13939981289", context.getPrevphoto().getPhotoId());
		assertEquals("85853333@N00", context.getPrevphoto().getOwner());
		assertEquals("aa7bea723c", context.getPrevphoto().getSecret());
		assertEquals("2940", context.getPrevphoto().getServer());
		assertEquals(3, (int) context.getPrevphoto().getFarm());
		assertEquals("World's Largest", context.getPrevphoto().getTitle());
		assertEquals("/photos/jeremybrooks/13939981289/in/set-72157644142099469/", context.getPrevphoto().getUrl());
		assertEquals("https://farm3.staticflickr.com/2940/13939981289_aa7bea723c_s.jpg", context.getPrevphoto().getThumb());
		assertEquals(2, (int) context.getPrevphoto().getLicense());
		assertEquals("photo", context.getPrevphoto().getMedia());
		assertFalse(context.getPrevphoto().getIsFaved());

		assertEquals("3369155489", context.getNextphoto().getPhotoId());
		assertEquals("85853333@N00", context.getNextphoto().getOwner());
		assertEquals("626f6406b1", context.getNextphoto().getSecret());
		assertEquals("3560", context.getNextphoto().getServer());
		assertEquals(4, (int) context.getNextphoto().getFarm());
		assertEquals("Vienna Beef Hot Dogs", context.getNextphoto().getTitle());
		assertEquals("/photos/jeremybrooks/3369155489/in/set-72157644142099469/", context.getNextphoto().getUrl());
		assertEquals("https://farm4.staticflickr.com/3560/3369155489_626f6406b1_s.jpg", context.getNextphoto().getThumb());
		assertEquals(2, (int) context.getNextphoto().getLicense());
		assertEquals("photo", context.getNextphoto().getMedia());
		assertFalse(context.getNextphoto().getIsFaved());

		assertEquals("ok", context.getStat());
	}
}
