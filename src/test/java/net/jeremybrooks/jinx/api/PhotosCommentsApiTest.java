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
import net.jeremybrooks.jinx.response.photos.Photos;
import net.jeremybrooks.jinx.response.photos.comments.Comment;
import net.jeremybrooks.jinx.response.photos.comments.Comments;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by jeremyb on 7/16/14.
 */
public class PhotosCommentsApiTest {
    private static PhotosCommentsApi photosCommentsApi;

    private static final String photoId = "14276359614";

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
        Jinx jinx = new Jinx(p.getProperty("flickr.key"), p.getProperty("flickr.secret"), oAuthAccessToken);
//        JinxLogger.setLogger(new StdoutLogger());
//        jinx.setVerboseLogging(true);
        photosCommentsApi = new PhotosCommentsApi(jinx);
    }

    /**
     * Test add, edit, list, and delete
     *
     * @throws Exception
     */
    @Test
    public void testComments() throws Exception {
        String commentText = "Test add comment from jinx unit tests. Comment added " + new Date();
        String editText = "Test comment edited by jinx unit tests.";

        // ADD
        Comment comment = photosCommentsApi.addComment(photoId, commentText);
        assertNotNull(comment);
        assertEquals("ok", comment.getStat());
        assertEquals(0, comment.getCode());
        assertEquals(commentText, comment.getCommentText());

        // EDIT
        Response response = photosCommentsApi.editComment(comment.getCommentId(), editText);
        assertNotNull(response);
        assertEquals("ok", response.getStat());
        assertEquals(0, response.getCode());

        // LIST
        Comments comments = photosCommentsApi.getList(photoId, null, null, false);
        assertNotNull(comments);
        assertEquals("ok", comments.getStat());
        assertEquals(0, comments.getCode());
        assertNotNull(comments.getCommentList());
        boolean found = false;
        for (Comment c : comments.getCommentList()) {
            if (c.getCommentText().equals(editText)) {
                found = true;
            }
        }
        assertTrue(found);

        // DELETE
        response = photosCommentsApi.deleteComment(comment.getCommentId());
        assertNotNull(response);
        assertEquals("ok", response.getStat());
        assertEquals(0, response.getCode());
    }

    @Test
    public void testGetRecentForContacts() throws Exception {
        // NOTE: there is no guarantee that we will get results here, so we
        //       just check for a response and a (possibly) empty list in the response
        Photos photos = photosCommentsApi.getRecentForContacts(null, null, null, 0, 0);
        assertNotNull(photos);
        assertEquals("ok", photos.getStat());
        assertEquals(0, photos.getCode());
        assertNotNull(photos.getPhotoList());
    }
}
