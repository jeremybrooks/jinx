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

package net.jeremybrooks.jinx.response.groups.discuss.topics;

import com.google.gson.annotations.SerializedName;
import net.jeremybrooks.jinx.JinxUtils;
import net.jeremybrooks.jinx.response.Response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jeremyb on 7/9/14.
 */
public class Topics extends Response {
    private static final long serialVersionUID = -5587151591222133528L;
    private _Topics topics;

    public String getGroupId() {
        return topics == null ? null : topics.groupId;
    }

    public String getPathAlias() {
        return topics == null ? null : topics.pathAlias;
    }

    public String getIconServer() {
        return topics == null ? null : topics.iconServer;
    }

    public String getIconFarm() {
        return topics == null ? null : topics.iconFarm;
    }

    public String getName() {
        return topics == null ? null : topics.name;
    }

    public Integer getMembers() {
        return topics == null ? null : topics.members;
    }

    public String getPrivacy() {
        return topics == null ? null : topics.privacy;
    }

    public String getLang() {
        return topics == null ? null : topics.lang;
    }

    public Boolean isPoolModerated() {
        return topics == null ? null : JinxUtils.flickrBooleanToBoolean(topics.isPoolModerated);
    }

    public Integer getTotal() {
        return topics == null ? null : topics.total;
    }

    public Integer getPage() {
        return topics == null ? null : topics.page;
    }

    public Integer getPerPage() {
        return topics == null ? null : topics.perPage;
    }

    public Integer getPages() {
        return topics == null ? null : topics.pages;
    }

    public List<Topic> getTopicList() {
        return topics == null ? null : topics.topicList;
    }

    private class _Topics implements Serializable {
        private static final long serialVersionUID = 5360748044189889488L;
        @SerializedName("group_id")
        private String groupId;
        @SerializedName("path_alias")
        private String pathAlias;
        @SerializedName("iconserver")
        private String iconServer;
        @SerializedName("iconfarm")
        private String iconFarm;
        private String name;
        private Integer members;
        private String privacy;
        private String lang;
        @SerializedName("ispoolmoderated")
        private String isPoolModerated;     // return as Boolean
        private Integer total;
        private Integer page;
        @SerializedName("per_page")
        private Integer perPage;
        private Integer pages;
        @SerializedName("topic")
        private List<Topic> topicList;

    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Topics{");
        sb.append("groupId='").append(getGroupId()).append('\'');
        sb.append(", pathAlias='").append(getPathAlias()).append('\'');
        sb.append(", iconServer='").append(getIconServer()).append('\'');
        sb.append(", iconFarm='").append(getIconFarm()).append('\'');
        sb.append(", name='").append(getName()).append('\'');
        sb.append(", members=").append(getMembers());
        sb.append(", privacy='").append(getPrivacy()).append('\'');
        sb.append(", lang='").append(getLang()).append('\'');
        sb.append(", isPoolModerated='").append(isPoolModerated()).append('\'');
        sb.append(", total=").append(getTotal());
        sb.append(", page=").append(getPage());
        sb.append(", perPage=").append(getPerPage());
        sb.append(", pages=").append(getPages());
        sb.append(", topicList=").append(getTopicList() == null ? null : "[" + getTopicList().size() + " items]");
        sb.append('}');
        return sb.toString();
    }
}
