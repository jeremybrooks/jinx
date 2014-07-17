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

import com.google.gson.annotations.SerializedName;
import net.jeremybrooks.jinx.response.Response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jeremyb on 7/16/14.
 */
public class Comments extends Response {


    private static final long serialVersionUID = 5086342019991816421L;

    public String getPhotoId() { return comments == null ? null : comments.photoId; }
    public List<Comment> getCommentList() { return comments == null ? null : comments.commentList; }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Comments{");
        sb.append("photoId='").append(getPhotoId()).append('\'');
        sb.append(", commentList=").append(getCommentList() == null ? null : "[" + getCommentList().size() + " items]");
        sb.append('}');
        return sb.toString();
    }


    private _Comments comments;
    private class _Comments implements Serializable {
        private static final long serialVersionUID = 7871449540882809037L;
        @SerializedName("photo_id")
        private String photoId;

        @SerializedName("comment")
        private List<Comment> commentList;

    }
}
