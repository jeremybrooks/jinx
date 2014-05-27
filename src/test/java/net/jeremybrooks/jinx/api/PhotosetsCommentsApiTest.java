package net.jeremybrooks.jinx.api;

import net.jeremybrooks.jinx.Jinx;
import net.jeremybrooks.jinx.OAuthAccessToken;
import net.jeremybrooks.jinx.response.Response;
import net.jeremybrooks.jinx.response.photosets.comments.CommentAdd;
import net.jeremybrooks.jinx.response.photosets.comments.CommentList;
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
public class PhotosetsCommentsApiTest {

	private static PhotosetsCommentsApi photosetsCommentsApi;
	private static String photosetId = "72157644903065293";
	private static String commentId = null;

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
		photosetsCommentsApi = new PhotosetsCommentsApi(new Jinx(p.getProperty("flickr.key"), p.getProperty("flickr.secret"), oAuthAccessToken));
	}

	@Test
	public void testGetList() throws Exception {
		CommentList comments = photosetsCommentsApi.getList(photosetId);
		assertNotNull(comments);
		assertEquals("ok", comments.getStat());
		assertNotNull(comments.getPhotosetId());
		assertEquals(photosetId, comments.getPhotosetId());
		assertNotNull(comments.getCommentList());
		assertTrue(comments.getCommentList().size() > 0);
	}

	@Test
	public void testAddComment() throws Exception {
		CommentAdd add = photosetsCommentsApi.addComment(photosetId, "This is a new test comment from Jinx.");
		assertNotNull(add);
		assertEquals("ok", add.getStat());
		assertNotNull(add.getCommentId());
		commentId = add.getCommentId();
	}

	@Test
	public void testEditComment() throws Exception {
		Response response = photosetsCommentsApi.editComment(commentId, "This is an edited comment from Jinx.");
		assertNotNull(response);
		assertEquals("ok", response.getStat());
		assertEquals(0, response.getCode());
	}

	@Test
	public void testDeleteComment() throws Exception {
		Response response = photosetsCommentsApi.deleteComment(commentId);
		assertNotNull(response);
		assertEquals("ok", response.getStat());
		assertEquals(0, response.getCode());
	}
}
