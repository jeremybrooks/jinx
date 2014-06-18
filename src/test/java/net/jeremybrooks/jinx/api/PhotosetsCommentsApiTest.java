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
import net.jeremybrooks.jinx.response.Response;
import net.jeremybrooks.jinx.response.photosets.comments.CommentAdd;
import net.jeremybrooks.jinx.response.photosets.comments.CommentList;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Jeremy Brooks
 */
public class PhotosetsCommentsApiTest {

	private static PhotosetsCommentsApi photosetsCommentsApi;
	private static String photosetId = "72157644903065293";

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


	/**
	 * Tests for add, edit, and delete comment are in a single method to ensure order of execution is correct.
	 * @throws Exception
	 */
	@Test
	public void testAddEditDeleteComment() throws Exception {
		String commentId;
		CommentAdd add = photosetsCommentsApi.addComment(photosetId, "This is a new test comment from Jinx.");
		assertNotNull(add);
		assertEquals("ok", add.getStat());
		assertNotNull(add.getCommentId());
		commentId = add.getCommentId();


		/* testEditComment() */
		Response editResponse = photosetsCommentsApi.editComment(commentId, "This is an edited comment from Jinx.");
		assertNotNull(editResponse);
		assertEquals("ok", editResponse.getStat());
		assertEquals(0, editResponse.getCode());


		/* testDeleteComment() */
		Response deleteResponse = photosetsCommentsApi.deleteComment(commentId);
		assertNotNull(deleteResponse);
		assertEquals("ok", deleteResponse.getStat());
		assertEquals(0, deleteResponse.getCode());
	}
}
