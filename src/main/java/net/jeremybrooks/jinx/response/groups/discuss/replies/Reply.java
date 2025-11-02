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

package net.jeremybrooks.jinx.response.groups.discuss.replies;

import com.google.gson.annotations.SerializedName;
import net.jeremybrooks.jinx.JinxUtils;

import java.io.Serializable;

/**
 * Created by jeremyb on 7/9/14.
 */
public class Reply implements Serializable {
    private static final long serialVersionUID = -4731112946229974791L;
    @SerializedName("id")
    private String replyId;
    private _Message message;
    private String author;
    @SerializedName("authorname")
    private String authorName;
    @SerializedName("is_pro")
    private String isPro;   // return as Boolean
    private String role;
    @SerializedName("iconserver")
    private String iconServer;
    @SerializedName("iconfarm")
    private String iconFarm;
    @SerializedName("can_edit")
    private String canEdit; // return as Boolean
    @SerializedName("can_delete")
    private String canDelete;   // return as Boolean
    @SerializedName("datecreate")
    private String dateCreate;
    @SerializedName("lastedit")
    private String lastEdit;

    public String getReplyId() {
        return replyId;
    }

    public String getMessage() {
        return message == null ? null : message._content;
    }

    public String getAuthor() {
        return author;
    }

    public String getAuthorName() {
        return authorName;
    }

    public Boolean isPro() {
        return JinxUtils.flickrBooleanToBoolean(isPro);
    }

    public String getRole() {
        return role;
    }

    public String getIconServer() {
        return iconServer;
    }

    public String getIconFarm() {
        return iconFarm;
    }

    public Boolean isCanEdit() {
        return JinxUtils.flickrBooleanToBoolean(canEdit);
    }

    public Boolean isCanDelete() {
        return JinxUtils.flickrBooleanToBoolean(canDelete);
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public String getLastEdit() {
        return lastEdit;
    }

    private class _Message implements Serializable {
        private static final long serialVersionUID = -3352327954127915076L;
        private String _content;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Reply{");
        sb.append("replyId='").append(replyId).append('\'');
        sb.append(", message='").append(getMessage()).append('\'');
        sb.append(", author='").append(author).append('\'');
        sb.append(", authorName='").append(authorName).append('\'');
        sb.append(", isPro='").append(isPro()).append('\'');
        sb.append(", role='").append(role).append('\'');
        sb.append(", iconServer='").append(iconServer).append('\'');
        sb.append(", iconFarm='").append(iconFarm).append('\'');
        sb.append(", canEdit='").append(isCanEdit()).append('\'');
        sb.append(", canDelete='").append(isCanDelete()).append('\'');
        sb.append(", dateCreate='").append(dateCreate).append('\'');
        sb.append(", lastEdit='").append(lastEdit).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
