package net.jeremybrooks.jinx.response.photosets.comments;

import net.jeremybrooks.jinx.response.Response;

/**
 * @author Jeremy Brooks
 */
public class CommentAdd extends Response {
	private static final long serialVersionUID = 5833472052251580810L;

	private Comment comment;
	public String getCommentId() {
		return comment == null ? null : comment.getCommentId();
	}
}
