/*
 * Jinx is Copyright 2010-2017 by Jeremy Brooks and Contributors
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

import com.google.gson.annotations.SerializedName;
import net.jeremybrooks.jinx.response.Response;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jeremy Brooks
 */
public class CommentList extends Response {

	private static final long serialVersionUID = -8263431016549424373L;

	private _Comments comments;



	public String getPhotosetId() {
		return comments == null ? null : comments.photosetId;
	}

	public List<Comment> getCommentList() {
		return comments == null ? null : comments.commentList;
	}

	private class _Comments implements Serializable {
		private static final long serialVersionUID = -8888096979591664503L;
		@SerializedName("photoset_id")
		private String photosetId;
		@SerializedName("comment")
		private List<Comment> commentList;
	}
}
