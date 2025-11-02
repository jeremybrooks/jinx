/*
 * Jinx is Copyright 2010-2025 by Jeremy Brooks and Contributors
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

import java.io.Serializable;

/**
 * @author Jeremy Brooks
 */
public class Comment implements Serializable {
	private static final long serialVersionUID = 8390781129649472556L;

	@SerializedName("id")
	private String commentId;
	private String author;
	@SerializedName("iconserver")
	private String iconServer;
	@SerializedName("iconfarm")
	private String iconFarm;
	@SerializedName("authorname")
	private String authorName;
	@SerializedName("datecreate")
	private String dateCreate;
	private String permalink;
	@SerializedName("_content")
	private String comment;

	public String getCommentId() {
		return commentId;
	}

	public String getAuthor() {
		return author;
	}

	public String getIconServer() {
		return iconServer;
	}

	public String getIconFarm() {
		return iconFarm;
	}

	public String getAuthorName() {
		return authorName;
	}

	public String getDateCreate() {
		return dateCreate;
	}

	public String getPermalink() {
		return permalink;
	}

	public String getComment() {
		return comment;
	}


	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("net.jeremybrooks.jinx.response.photosets.comments.Comment");
		sb.append("{commentId='").append(commentId).append('\'');
		sb.append(" | author='").append(author).append('\'');
		sb.append(" | iconServer='").append(iconServer).append('\'');
		sb.append(" | iconFarm=").append(iconFarm);
		sb.append(" | authorName='").append(authorName).append('\'');
		sb.append(" | dateCreate='").append(dateCreate).append('\'');
		sb.append(" | permalink='").append(permalink).append('\'');
		sb.append(" | comment='").append(comment).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
