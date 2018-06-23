/*
 * Jinx is Copyright 2010-2018 by Jeremy Brooks and Contributors
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

import com.google.gson.annotations.SerializedName;
import net.jeremybrooks.jinx.response.Response;

import java.io.Serializable;

/**
 * Created by jeremyb on 7/16/14.
 */
public class Comment extends Response {

    private static final long serialVersionUID = 8775727159244107345L;

    /* These fields are populated when parsing a Comment from getList */
    /* -------------------------------------------------------------- */
    @SerializedName("id")
    private String commentId;
    @SerializedName("author")
    private String authorId;
    @SerializedName("authorname")
    private String authorName;
    @SerializedName("datecreate")
    private String dateCreate;
    private String permalink;
    @SerializedName("path_alias")
    private String pathAlias;
    private String realname;
    @SerializedName("_content")
    private String commentText;
    @SerializedName("iconserver")
    private String iconServer;
    @SerializedName("iconfarm")
    private String iconFarm;
    /* -------------------------------------------------------------- */
    /* end parse from getList */


    /* This comment is from addComment
    /* -------------------------------------------------------------- */
    private _Comment comment;
    /* -------------------------------------------------------------- */


    public String getCommentId() { return comment == null ? this.commentId : comment.commentId; }
    public String getAuthorId() { return comment == null ? this.authorId : comment.authorId; }
    public String getAuthorName()  { return comment == null ? this.authorName : comment.authorName; }
    public String getIconServer() { return comment == null ? this.iconServer : comment.iconServer; }
    public String getIconFarm() { return comment == null ? this.iconFarm : comment.iconFarm; }
    public String getDateCreate() { return comment == null ? this.dateCreate : comment.dateCreate; }
    public String getPermalink()  { return comment == null ? this.permalink : comment.permalink; }
    public String getPathAlias()  { return comment == null ? this.pathAlias : comment.pathAlias; }
    public String getRealname()  { return comment == null ? this.realname : comment.realname; }
    public String getCommentText()  { return comment == null ? this.commentText : comment.commentText; }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Comment{");
        sb.append("commentId='").append(getCommentId()).append('\'');
        sb.append(", authorId='").append(getAuthorId()).append('\'');
        sb.append(", authorName='").append(getAuthorName()).append('\'');
        sb.append(", iconServer='").append(getIconServer()).append('\'');
        sb.append(", iconFarm='").append(getIconFarm()).append('\'');
        sb.append(", dateCreate='").append(getDateCreate()).append('\'');
        sb.append(", permalink='").append(getPermalink()).append('\'');
        sb.append(", pathAlias='").append(getPathAlias()).append('\'');
        sb.append(", realname='").append(getRealname()).append('\'');
        sb.append(", commentText='").append(getCommentText()).append('\'');
        sb.append('}');
        return sb.toString();
    }

    private class _Comment implements Serializable {
        private static final long serialVersionUID = -3949349508579583678L;
        @SerializedName("id")
        private String commentId;
        @SerializedName("author")
        private String authorId;
        @SerializedName("authorname")
        private String authorName;
        @SerializedName("datecreate")
        private String dateCreate;
        private String permalink;
        @SerializedName("path_alias")
        private String pathAlias;
        private String realname;
        @SerializedName("_content")
        private String commentText;
        @SerializedName("iconserver")
        private String iconServer;
        @SerializedName("iconfarm")
        private String iconFarm;
    }
}
