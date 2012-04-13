/*
 * Jinx is Copyright 2010-2012 by Jeremy Brooks and Contributors
 *
 * This file is part of Jinx.
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import net.jeremybrooks.jinx.Jinx;
import net.jeremybrooks.jinx.JinxException;
import net.jeremybrooks.jinx.JinxUtils;
import net.jeremybrooks.jinx.dto.Comment;
import net.jeremybrooks.jinx.dto.Comments;
import net.jeremybrooks.jinx.dto.Photos;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author jeremyb
 */
public class PhotosCommentsApi {

    private static PhotosCommentsApi instance = null;

    private PhotosCommentsApi() {
    }

    public static PhotosCommentsApi getInstance() {
	if (PhotosCommentsApi.instance == null) {
	    PhotosCommentsApi.instance = new PhotosCommentsApi();
	}

	return PhotosCommentsApi.instance;
    }

    
    /**
     * Add comment to a photo as the currently authenticated user.
     *
     * This method requires authentication with 'write' permission.
     *
     * Note: This method requires an HTTP POST request.
     * 
     * @param photoId the id of the photo to add a comment to.
     * @param commentText text of the comment.
     * @return comment object with newly created comment ID and the comment text.
     * @throws JinxException if parameters are null or empty, or if there are any errors.
     */
    public Comment addComment(String photoId, String commentText) throws JinxException {
	if (photoId == null || photoId.trim().isEmpty()) {
	    throw new JinxException("Parameter photoId is required.");
	}
	if (commentText == null || commentText.trim().isEmpty()) {
	    throw new JinxException("Parameter commentText is required.");
	}

	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.photos.comments.addComment");
	params.put("api_key", Jinx.getInstance().getApiKey());
	params.put("photo_id", photoId);
	params.put("comment_text", commentText);

	Document doc = Jinx.getInstance().callFlickr(params, true, true);

	/*
	  <?xml version="1.0" encoding="utf-8" ?>
	  <rsp stat="ok">
	    <comment id="4956757-5833861018-72157626970486476" />
	  </rsp>
	 */
	Comment comment = new Comment();
	comment.setComment(commentText);
	comment.setCommentId(JinxUtils.getValueByXPath(doc, "/rsp/comment/@id"));

	return comment;
    }


    /**
     * Edit the text of a comment as the currently authenticated user.
     *
     * This method requires authentication with 'write' permission.
     *
     * Note: This method requires an HTTP POST request.
     *
     * @param commentId the id of the comment to edit.
     * @param commentText update the comment to this text.
     * @throws JinxException if any parameter is null or empty, or if there
     *         are any errors.
     */
    public void editComment(String commentId, String commentText) throws JinxException {
	if (commentId == null || commentId.trim().isEmpty()) {
	    throw new JinxException("Parameter commentId is required.");
	}
	if (commentText == null || commentText.trim().isEmpty()) {
	    throw new JinxException("Parameter commentText is required.");
	}

	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.photos.comments.editComment");
	params.put("api_key", Jinx.getInstance().getApiKey());
	params.put("comment_id", commentId);
	params.put("comment_text", commentText);

	Jinx.getInstance().callFlickr(params, true, true);
    }


    /**
     * Delete a comment as the currently authenticated user.
     *
     * This method requires authentication with 'write' permission.
     *
     * Note: This method requires an HTTP POST request.
     *
     * @param commentId the id of the comment to delete.
     * @throws JinxException if the commentId is null or empty, or if there
     *         are any errors.
     */
    public void deleteComment(String commentId) throws JinxException {
	if (commentId == null || commentId.trim().isEmpty()) {
	    throw new JinxException("Parameter commentId is required.");
	}

	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.photos.comments.deleteComment");
	params.put("api_key", Jinx.getInstance().getApiKey());
	params.put("comment_id", commentId);

	Jinx.getInstance().callFlickr(params, true, true);
    }


    /**
     * Returns the comments for a photo.
     * 
     * This method is equivalent to <code>getList(photoId, null, null)</code>.
     * 
     * @param photoId the id of the photo to fetch comments for.
     * @return comments for the photos.
     * @throws JinxException if photoId is null or empty, or if there are any errors.
     */
    public Comments getList(String photoId) throws JinxException {
	return this.getList(photoId, null, null);
    }


    /**
     * Returns the comments for a photo.
     * 
     * This method does not require authentication.
     * 
     * @param photoId the id of the photo to fetch comments for.
     * @param minCommentDate (optional) minimum date the comment was added.
     * @param maxCommentDate (optional) maxiumum date the comment was added.
     * @return comments for the photos.
     * @throws JinxException if photoId is null or empty, or if there are any errors.
     */
    public Comments getList(String photoId, Date minCommentDate, Date maxCommentDate)
	    throws JinxException {
	if (photoId == null || photoId.trim().isEmpty()) {
	    throw new JinxException("Parameter photoId is required.");
	}

	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.photos.comments.getList");
	params.put("api_key", Jinx.getInstance().getApiKey());
	params.put("photo_id", photoId);
	if (minCommentDate != null) {
	    params.put("min_comment_date", JinxUtils.formatDateAsUnixTimestamp(minCommentDate));
	}
	if (maxCommentDate != null) {
	    params.put("max_comment_date", JinxUtils.formatDateAsUnixTimestamp(maxCommentDate));
	}

	Document doc = Jinx.getInstance().callFlickr(params);

	/*
	 <?xml version="1.0" encoding="utf-8" ?>
	 <rsp stat="ok">
	   <comments photo_id="5833861018">
	     <comment id="4956757-5833861018-72157626963000992" author="52756049@N05"
	       authorname="NapDSP" iconserver="4139" iconfarm="5" datecreate="1308084350"
	       permalink="http://www.flickr.com/photos/jeremybrooks/5833861018/#comment72157626963000992">Fantastic!</comment>
	     <comment id="4956757-5833861018-72157626965223956" author="48648457@N00"
	       authorname="Happyshooter" iconserver="1297" iconfarm="2" datecreate="1308108425"
	       permalink="http://www.flickr.com/photos/jeremybrooks/5833861018/#comment72157626965223956">Excellent composition!</comment>
	   </comments>
	 </rsp>
	 */

	Comments comments = new Comments();
	List<Comment> commentList = new ArrayList<Comment>();
	comments.setPhotoId(JinxUtils.getValueByXPath(doc, "/rsp/comments/@photo_id"));
	NodeList nodes = doc.getElementsByTagName("comment");
	if (nodes != null) {
	    for (int i = 0; i < nodes.getLength(); i++) {
		Node node = nodes.item(i);
		Comment c = new Comment();
		NamedNodeMap attrs = node.getAttributes();
		c.setCommentId(JinxUtils.getAttribute(attrs, "id"));
		c.setAuthor(JinxUtils.getAttribute(attrs, "author"));
		c.setAuthorName(JinxUtils.getAttribute(attrs, "authorname"));
		c.setIconServer(JinxUtils.getAttribute(attrs, "iconserver"));
		c.setIconFarm(JinxUtils.getAttribute(attrs, "iconfarm"));
		c.setDateCreate(JinxUtils.parseTimestampToDate(JinxUtils.getAttribute(attrs, "datecreate")));
		c.setPermalink(JinxUtils.getAttribute(attrs, "permalink"));
		c.setComment(JinxUtils.getFirstChildTextContent(node));

		commentList.add(c);
	    }
	}
	comments.setCommentList(commentList);

	return comments;
    }

    
    /**
     * Return the list of photos belonging to your contacts that have been
     * commented on recently.
     *
     * This method is equivalent to 
     * <code>getRecentForContacts(null, null, null, 0, 0)</code>.
     * 
     * @return photos belonging to your contacts that have been commented on recently.
     * @throws JinxException if there are any errors.
     */
    public Photos getRecentForContacts() throws JinxException {
	return this.getRecentForContacts(null, null, null, 0, 0);
    }

    
    /**
     * Return the list of photos belonging to your contacts that have been
     * commented on recently.
     *
     * This method requires authentication with 'read' permission.
     *
     * Extras:
     * You can include extras from JinxConstants.EXTRAS*
     * Currently supported fields include:
     * EXTRAS_DESCRIPTION
     * EXTRAS_LICENSE
     * EXTRAS_DATE_UPLOAD
     * EXTRAS_DATE_TAKEN
     * EXTRAS_OWNER_NAME
     * EXTRAS_ICON_SERVER
     * EXTRAS_ORIGINAL_FORMAT
     * EXTRAS_LAST_UPDATE
     * EXTRAS_GEO
     * EXTRAS_TAGS
     * EXTRAS_MACHINE_TAGS
     * EXTRAS_O_DIMS
     * EXTRAS_VIEWS
     * EXTRAS_MEDIA
     * EXTRAS_PATH_ALIAS
     * EXTRAS_URL_SQ
     * EXTRAS_URL_T
     * EXTRAS_URL_S
     * EXTRAS_URL_M
     * EXTRAS_URL_Z
     * EXTRAS_URL_L
     * EXTRAS_URL_O
     *
     * @param lastComment limits the results to photos that have been commented
     *        on since this date. The default, and maximum, offset is one hour.
     * @param contactsFilter list of contact NSIDs to limit the scope of the query to.
     * @param extras list of contact NSIDs to limit the scope of the query to.
     * @param perPage number of photos to return per page. If this argument is
     *        less than 1, it defaults to 100. The maximum allowed value is 500.
     * @param page the page of results to return. If this argument is less than 1,
     *        it defaults to 1.
     * @return photos belonging to your contacts that have been commented on recently.
     * @throws JinxException if there are any errors.
     */
    public Photos getRecentForContacts(Date lastComment, List<String> contactsFilter,
	    List<String> extras, int perPage, int page) throws JinxException {

	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.photos.comments.getRecentForContacts");
	params.put("api_key", Jinx.getInstance().getApiKey());
	if (lastComment != null) {
	    params.put("date_lastcomment", JinxUtils.formatDateAsUnixTimestamp(lastComment));
	}
	if (contactsFilter != null && contactsFilter.size() > 0) {
	    StringBuilder sb = new StringBuilder();
	    for (String s : contactsFilter) {
		if (s != null && s.trim().length() > 0) {
		    sb.append(s.trim()).append(',');
		}
	    }
	    sb.deleteCharAt(sb.lastIndexOf(","));
	    params.put("contacts_filter", sb.toString());
	}
	if (extras != null && extras.size() > 0) {
	    StringBuilder sb = new StringBuilder();
	    for (String s : extras) {
		if (s != null && s.trim().length() > 0) {
		    sb.append(s.trim()).append(',');
		}
	    }
	    sb.deleteCharAt(sb.lastIndexOf(","));

	    params.put("extras", sb.toString());
	}
	if (page > 0) {
	    params.put("page", Integer.toString(page));
	}
	if (perPage > 0) {
	    params.put("per_page", Integer.toString(perPage));
	}

	Document doc = Jinx.getInstance().callFlickr(params);

	return PhotosApi.getInstance().parsePhotosXml(doc);
    }
}
