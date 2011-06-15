/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.jeremybrooks.jinx.api;

import net.jeremybrooks.jinx.Setup;
import java.util.Date;
import java.util.List;
import net.jeremybrooks.jinx.dto.Comment;
import net.jeremybrooks.jinx.dto.Comments;
import net.jeremybrooks.jinx.dto.Photos;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jeremyb
 */
public class PhotosCommentsApiTest {

    private String photoId = "257059699";
    private static String commentId = "";
    
    public PhotosCommentsApiTest() {
    }


    @BeforeClass
    public static void setUpClass() throws Exception {
	Setup.doSetup();
    }


    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }


    /**
     * Test of getInstance method, of class PhotosCommentsApi.
     */
    @Test
    public void testGetInstance() {
	System.out.println("getInstance");
	PhotosCommentsApi result = PhotosCommentsApi.getInstance();
	assertNotNull(result);
    }


    /**
     * Test of addComment method, of class PhotosCommentsApi.
     */
    @Test
    public void testAddComment() throws Exception {
	System.out.println("addComment");
	String commentText = "Testing add comment.";
	PhotosCommentsApi instance = PhotosCommentsApi.getInstance();
	Comment result = instance.addComment(photoId, commentText);
	assertNotNull(result);
	assertNotNull(result.getCommentId());
	assertFalse(result.getCommentId().isEmpty());
	this.commentId = result.getCommentId();
    }


    /**
     * Test of editComment method, of class PhotosCommentsApi.
     */
    @Test
    public void testEditComment() throws Exception {
	System.out.println("editComment");
	String commentText = "Test edit comment.";
	PhotosCommentsApi instance = PhotosCommentsApi.getInstance();
	instance.editComment(commentId, commentText);
    }


    /**
     * Test of getList method, of class PhotosCommentsApi.
     */
    @Test
    public void testGetList_String() throws Exception {
	System.out.println("getList");
	PhotosCommentsApi instance = PhotosCommentsApi.getInstance();
	Comments result = instance.getList(photoId);
	assertNotNull(result);
	for (Comment c : result.getCommentList()) {
	    if (c.getCommentId().equals(commentId)) {
		assertEquals("Test edit comment.", c.getComment());
	    }
	}
    }


    /**
     * Test of getList method, of class PhotosCommentsApi.
     */
    @Test
    public void testGetList_3args() throws Exception {
	System.out.println("getList");
	PhotosCommentsApi instance = PhotosCommentsApi.getInstance();
	Comments result = instance.getList(photoId, null, null);
	assertNotNull(result);
	for (Comment c : result.getCommentList()) {
	    if (c.getCommentId().equals(commentId)) {
		assertEquals("Test edit comment.", c.getComment());
	    }
	}
    }


    /**
     * Test of deleteComment method, of class PhotosCommentsApi.
     */
    @Test
    public void testDeleteComment() throws Exception {
	System.out.println("deleteComment");
	PhotosCommentsApi instance = PhotosCommentsApi.getInstance();
	instance.deleteComment(commentId);
    }


    /**
     * Test of getRecentForContacts method, of class PhotosCommentsApi.
     */
    @Test
    public void testGetRecentForContacts_0args() throws Exception {
	System.out.println("getRecentForContacts");
	PhotosCommentsApi instance = PhotosCommentsApi.getInstance();
	Photos result = instance.getRecentForContacts();
	assertNotNull(result);
    }


    /**
     * Test of getRecentForContacts method, of class PhotosCommentsApi.
     */
    @Test
    public void testGetRecentForContacts_5args() throws Exception {
	System.out.println("getRecentForContacts");
	Date lastComment = null;
	List<String> contactsFilter = null;
	List<String> extras = null;
	int perPage = 0;
	int page = 0;
	PhotosCommentsApi instance = PhotosCommentsApi.getInstance();
	Photos result = instance.getRecentForContacts(lastComment, contactsFilter, extras, perPage, page);
	assertNotNull(result);
    }

}