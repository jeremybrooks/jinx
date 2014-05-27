package net.jeremybrooks.jinx.response.photosets.comments;

import com.google.gson.Gson;
import net.jeremybrooks.jinx.response.activity.ActivityResponseTest;
import org.junit.Test;

import java.io.InputStreamReader;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Jeremy Brooks
 */
public class PhotosetCommentsTest {

	@Test
	public void testAdd() throws Exception {
		InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/photosets/comments/sample_photosets_add_comment.json"));
		CommentAdd comment = new Gson().fromJson(reader, CommentAdd.class);
		reader.close();
		assertNotNull(comment);
		assertEquals("ok", comment.getStat());
		assertEquals("124834485-72157644903065293-72157644452519640", comment.getCommentId());
	}

	@Test
	public void testCommentList() throws Exception {
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
		assertEquals(6, (int)c.getIconFarm());
		assertEquals("Jeremy Brooks", c.getAuthorName());
		assertEquals("1401154465", c.getDateCreate());
		assertEquals("https://www.flickr.com/photos/jinxlib/sets/72157644903065293/comments#comment72157644452484869", c.getPermalink());
		assertEquals("I recognize these.", c.getComment());
	}

}
