/*
 * Jinx is Copyright 2010-2023 by Jeremy Brooks and Contributors
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

package net.jeremybrooks.jinx.response.groups.discuss.topics;

import com.google.gson.annotations.SerializedName;
import net.jeremybrooks.jinx.JinxUtils;

import java.io.Serializable;

/**
 * Created by jeremyb on 7/9/14.
 */
public class Topic implements Serializable {

    private static final long serialVersionUID = 4290983605675471198L;
    /*
     * topicId and id are equivalent; different data structures have the same thing named differently.
     * the return value is disambiguated in the getTopicId() method
     */
    @SerializedName("topic_id")
    private String topicId;
    private String id;

    private String subject;
    private _Message message;   // return _content as String
    @SerializedName("group_id")
    private String groupId;
    @SerializedName("iconserver")
    private String iconServer;
    @SerializedName("iconfarm")
    private String iconFarm;
    private String name;
    private String author;
    @SerializedName("authorname")
    private String authorName;
    @SerializedName("author_path_alias")
    private String authorPathAlias;
    @SerializedName("is_pro")
    private String isPro;   // return as Boolean
    private String role;
    @SerializedName("author_iconserver")
    private String authorIconServer;
    @SerializedName("author_iconfarm")
    private String authorIconFarm;
    @SerializedName("can_edit")
    private String canEdit;         // return as Boolean
    @SerializedName("can_delete")
    private String canDelete;       // return as Boolean
    @SerializedName("can_reply")
    private String canReply;        // return as Boolean
    @SerializedName("is_sticky")
    private String isSticky;        // return as Boolean
    @SerializedName("is_locked")
    private String isLocked;        // return as Boolean
    @SerializedName("datecreate")
    private String dateCreate;
    @SerializedName("datelastpost")
    private String dateLastPost;
    private Integer total;
    private Integer page;
    @SerializedName("per_page")
    private Integer perPage;
    private Integer pages;
    @SerializedName("count_replies")
    private Integer countReplies;
    @SerializedName("last_reply")
    private String lastReply;

    public String getTopicId() {
        // return id if topicId is null
        // different API's return the same value in different ways
        if (topicId == null) {
            return id;
        } else {
            return topicId;
        }
    }

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message == null ? null : message._content;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getIconServer() {
        return iconServer;
    }

    public String getIconFarm() {
        return iconFarm;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getAuthorPathAlias() {
        return authorPathAlias;
    }

    public Boolean isPro() {
        return JinxUtils.flickrBooleanToBoolean(isPro);
    }

    public String getRole() {
        return role;
    }

    public String getAuthorIconServer() {
        return authorIconServer;
    }

    public String getAuthorIconFarm() {
        return authorIconFarm;
    }

    public Boolean isCanEdit() {
        return JinxUtils.flickrBooleanToBoolean(canEdit);
    }

    public Boolean isCanDelete() {
        return JinxUtils.flickrBooleanToBoolean(canDelete);
    }

    public Boolean isCanReply() {
        return JinxUtils.flickrBooleanToBoolean(canReply);
    }

    public Boolean isSticky() {
        return JinxUtils.flickrBooleanToBoolean(isSticky);
    }

    public Boolean isLocked() {
        return JinxUtils.flickrBooleanToBoolean(isLocked);
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public String getDateLastPost() {
        return dateLastPost;
    }

    public Integer getTotal() {
        return total;
    }

    public Integer getPage() {
        return page;
    }

    public Integer getPerPage() {
        return perPage;
    }

    public Integer getPages() {
        return pages;
    }

    public Integer getCountReplies() {
        return countReplies;
    }

    public String getLastReply() {
        return lastReply;
    }

    private class _Message implements Serializable {
        private static final long serialVersionUID = 4690842736272433449L;
        private String _content;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Topic{");
        sb.append("topicId='").append(topicId).append('\'');
        sb.append(", subject='").append(subject).append('\'');
        sb.append(", message='").append(getMessage()).append('\'');
        sb.append(", groupId='").append(groupId).append('\'');
        sb.append(", iconServer='").append(iconServer).append('\'');
        sb.append(", iconFarm='").append(iconFarm).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", author='").append(author).append('\'');
        sb.append(", authorName='").append(authorName).append('\'');
        sb.append(", authorPathAlias='").append(authorPathAlias).append('\'');
        sb.append(", isPro='").append(isPro()).append('\'');
        sb.append(", role='").append(role).append('\'');
        sb.append(", authorIconServer='").append(authorIconServer).append('\'');
        sb.append(", authorIconFarm='").append(authorIconFarm).append('\'');
        sb.append(", canEdit='").append(isCanEdit()).append('\'');
        sb.append(", canDelete='").append(isCanDelete()).append('\'');
        sb.append(", canReply='").append(isCanReply()).append('\'');
        sb.append(", isSticky='").append(isSticky()).append('\'');
        sb.append(", isLocked='").append(isLocked()).append('\'');
        sb.append(", dateCreate='").append(dateCreate).append('\'');
        sb.append(", dateLastPost='").append(dateLastPost).append('\'');
        sb.append(", total=").append(total);
        sb.append(", page=").append(page);
        sb.append(", perPage=").append(perPage);
        sb.append(", pages=").append(pages);
        sb.append(", countReplies=").append(countReplies);
        sb.append(", lastReply='").append(lastReply).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
