package net.jeremybrooks.jinx.response.photosets.comments;

import com.google.gson.annotations.SerializedName;
import net.jeremybrooks.jinx.response.Response;

import java.util.List;

/**
 * @author Jeremy Brooks
 */
public class CommentList extends Response {

	private static final long serialVersionUID = -8263431016549424373L;

	private Comments comments;



	public String getPhotosetId() {
		return comments == null ? null : comments.photosetId;
	}

	public List<Comment> getCommentList() {
		return comments == null ? null : comments.commentList;
	}

	private class Comments {
		@SerializedName("photoset_id")
		private String photosetId;
		@SerializedName("comment")
		private List<Comment> commentList;
	}
}
