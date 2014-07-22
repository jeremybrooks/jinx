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

package net.jeremybrooks.jinx.response.photos.comments;

import com.google.gson.Gson;
import net.jeremybrooks.jinx.response.activity.ActivityResponseTest;
import net.jeremybrooks.jinx.response.photos.Photo;
import net.jeremybrooks.jinx.response.photos.Photos;
import org.junit.Test;

import java.io.InputStreamReader;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by jeremyb on 7/16/14.
 */
public class PhotosCommentsTest {

    @Test
    public void testParseAddComment() throws Exception {
        InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/photos/comments/sample_add_comment.json"));
        Comment comment = new Gson().fromJson(reader, Comment.class);
        reader.close();
        assertNotNull(comment);
        assertEquals("ok", comment.getStat());
        assertEquals(0, comment.getCode());
        assertEquals("124834485-14276359614-72157645729023405", comment.getCommentId());
        assertEquals("124857539@N03", comment.getAuthorId());
        assertEquals("jinxlib", comment.getAuthorName());
        assertEquals("1405536548", comment.getDateCreate());
        assertEquals("https://www.flickr.com/photos/jinxlib/14276359614/#comment72157645729023405", comment.getPermalink());
        assertEquals("jinxlib", comment.getPathAlias());
        assertEquals("Jinx Library", comment.getRealname());
        assertEquals("Test comment from jinxlib unit tests.", comment.getCommentText());
    }

    @Test
    public void testParseGetList() throws Exception {
        InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/photos/comments/sample_get_list.json"));
        Comments comments = new Gson().fromJson(reader, Comments.class);
        reader.close();
        assertNotNull(comments);
        assertEquals("ok", comments.getStat());
        assertEquals(0, comments.getCode());
        assertEquals("14276359614", comments.getPhotoId());
        assertNotNull(comments.getCommentList());
        assertEquals(1, comments.getCommentList().size());
        Comment comment = comments.getCommentList().get(0);
        assertEquals("124834485-14276359614-72157645729023405", comment.getCommentId());
        assertEquals("124857539@N03", comment.getAuthorId());
        assertEquals("jinxlib", comment.getAuthorName());
        assertEquals("1405536548", comment.getDateCreate());
        assertEquals("5538", comment.getIconServer());
        assertEquals("6", comment.getIconFarm());
        assertEquals("https://www.flickr.com/photos/jinxlib/14276359614/#comment72157645729023405", comment.getPermalink());
        assertEquals("jinxlib", comment.getPathAlias());
        assertEquals("Jinx Library", comment.getRealname());
        assertEquals("Edited comment.", comment.getCommentText());
    }

    @Test
    public void testParseGetRecentForContacts() throws Exception {
        InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/photos/comments/sample_get_recent_for_contacts.json"));
        Photos photos = new Gson().fromJson(reader, Photos.class);
        reader.close();
        assertNotNull(photos);
        assertEquals("ok", photos.getStat());
        assertEquals(0, photos.getCode());
        assertEquals(new Integer(1), photos.getPage());
        assertEquals(new Integer(1), photos.getPages());
        assertEquals(new Integer(100), photos.getPerPage());
        assertEquals(new Integer(1), photos.getTotal());
        assertNotNull(photos.getPhotoList());
        assertEquals(1, photos.getPhotoList().size());
        Photo p = photos.getPhotoList().get(0);
        assertEquals("14670576965", p.getPhotoId());
        assertEquals("51035555243@N01", p.getOwner());
        assertEquals("2819225731", p.getSecret());
        assertEquals("3885", p.getServer());
        assertEquals("4", p.getFarm());
        assertEquals("The lines of San Francisco. #sf #sanfrancisco #bw #architecture #mission", p.getTitle());
        assertTrue(p.isPublic());
        assertFalse(p.isFriend());
        assertFalse(p.isFamily());
        assertEquals(new Integer(2), p.getLicense());
        assertEquals("", p.getDescription());
        assertEquals("640", p.getoWidth());
        assertEquals("640", p.getoHeight());
        assertEquals("1405530740", p.getDateUpload());
        assertEquals("1405536845", p.getLastUpdate());
        assertEquals("2014-07-16 10:12:20", p.getDateTaken());
        assertEquals(new Integer(0), p.getDateTakenGranularity());
        assertEquals("Thomas Hawk", p.getOwnerName());
        assertEquals("7370", p.getIconServer());
        assertEquals("8", p.getIconFarm());
        assertEquals(new Integer(746), p.getViews());
        assertEquals("square squareformat iphoneography instagramapp uploaded:by=instagram", p.getTags());
        assertEquals("uploaded:by=instagram", p.getMachineTags());
        assertEquals("b978413cdf", p.getOriginalSecret());
        assertEquals("jpg", p.getOriginalFormat());
        assertEquals(new Float(0.0), p.getLatitude());
        assertEquals(new Float(0.0), p.getLongitude());
        assertEquals(new Integer(0), p.getAccuracy());
        assertEquals(new Integer(0), p.getContext());
        assertEquals("photo", p.getMedia());
        assertEquals("ready", p.getMediaStatus());
        assertEquals("thomashawk", p.getPathAlias());
        assertEquals("https://farm4.staticflickr.com/3885/14670576965_2819225731_s.jpg", p.getUrlSq());
        assertEquals(new Integer(75), p.getHeightSq());
        assertEquals(new Integer(75), p.getWidthSq());
        assertEquals("https://farm4.staticflickr.com/3885/14670576965_2819225731_t.jpg", p.getUrlT());
        assertEquals(new Integer(100), p.getHeightT());
        assertEquals(new Integer(100), p.getWidthT());
        
        assertEquals("https://farm4.staticflickr.com/3885/14670576965_2819225731_m.jpg", p.getUrlS());
        assertEquals(new Integer(240), p.getHeightS());
        assertEquals(new Integer(240), p.getWidthS());
        
        assertEquals("https://farm4.staticflickr.com/3885/14670576965_2819225731_q.jpg", p.getUrlQ());
        assertEquals(new Integer(150), p.getHeightQ());
        assertEquals(new Integer(150), p.getWidthQ());
        
        assertEquals("https://farm4.staticflickr.com/3885/14670576965_2819225731.jpg", p.getUrlM());
        assertEquals(new Integer(500), p.getHeightM());
        assertEquals(new Integer(500), p.getWidthM());
        
        assertEquals("https://farm4.staticflickr.com/3885/14670576965_2819225731_n.jpg", p.getUrlN());
        assertEquals(new Integer(320), p.getHeightN());
        assertEquals(new Integer(320), p.getWidthN());
        
        assertEquals("https://farm4.staticflickr.com/3885/14670576965_2819225731_z.jpg", p.getUrlZ());
        assertEquals(new Integer(640), p.getHeightZ());
        assertEquals(new Integer(640), p.getWidthZ());
        
        assertEquals("https://farm4.staticflickr.com/3885/14670576965_b978413cdf_o.jpg", p.getUrlO());
        assertEquals(new Integer(640), p.getHeightO());
        assertEquals(new Integer(640), p.getWidthO());
    }
}
