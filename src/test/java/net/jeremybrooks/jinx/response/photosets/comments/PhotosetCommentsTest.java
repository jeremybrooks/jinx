/*
 * Jinx is Copyright 2010-2025 by Jeremy Brooks and Contributors
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

package net.jeremybrooks.jinx.response.photosets.comments;

import com.google.gson.Gson;
import net.jeremybrooks.jinx.response.activity.ActivityResponseTest;
import org.junit.Test;

import java.io.InputStreamReader;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Jeremy Brooks
 */
public class PhotosetCommentsTest {

	@Test
	public void testParseAdd() throws Exception {
		InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/photosets/comments/sample_photosets_add_comment.json"));
		CommentAdd comment = new Gson().fromJson(reader, CommentAdd.class);
		reader.close();
		assertNotNull(comment);
		assertEquals("ok", comment.getStat());
		assertEquals("124834485-72157644903065293-72157644452519640", comment.getCommentId());
	}

	@Test
	public void testParseCommentList() throws Exception {
		InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/photosets/comments/sample_photoset_comment_list.json"));
		CommentList comments = new Gson().fromJson(reader, CommentList.class);
		reader.close();
		assertNotNull(comments);
		assertEquals("ok", comments.getStat());
		assertEquals("72157644903065293", comments.getPhotosetId());
		List<Comment> commentList = comments.getCommentList();
		assertNotNull(commentList);
		assertEquals(1, commentList.size());
		Comment c = commentList.get(0);
		assertEquals("124834485-72157644903065293-72157644452484869", c.getCommentId());
		assertEquals("85853333@N00", c.getAuthor());
		assertEquals("5332", c.getIconServer());
		assertEquals("6", c.getIconFarm());
		assertEquals("Jeremy Brooks", c.getAuthorName());
		assertEquals("1401154465", c.getDateCreate());
		assertEquals("https://www.flickr.com/photos/jinxlib/sets/72157644903065293/comments#comment72157644452484869", c.getPermalink());
		assertEquals("I recognize these.", c.getComment());
	}

}
