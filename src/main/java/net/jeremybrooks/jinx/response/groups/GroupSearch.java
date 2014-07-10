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

package net.jeremybrooks.jinx.response.groups;

import com.google.gson.annotations.SerializedName;
import net.jeremybrooks.jinx.JinxUtils;
import net.jeremybrooks.jinx.response.Response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jeremyb on 7/9/14.
 */
public class GroupSearch extends Response {
    private static final long serialVersionUID = -9013372018271179648L;
    private _Groups groups;

    public Integer getPage() { return groups == null ? null : groups.page; }
    public Integer getPages() { return groups == null ? null : groups.pages; }
    public Integer getPerPage() { return groups == null ? null : groups.perPage; }
    public Integer getTotal() { return groups == null ? null : groups.total; }
    public List<Group> getGroupList() { return groups == null ? null : groups.groupList; }

    private class _Groups implements Serializable {
        private static final long serialVersionUID = 8874658835221723043L;
        private Integer page;
        private Integer pages;
        @SerializedName("perpage")
        private Integer perPage;
        private Integer total;
        @SerializedName("group")
        private List<Group> groupList;
    }

    public class Group implements Serializable {
        private static final long serialVersionUID = -4540528204504482572L;
        @SerializedName("nsid")
        private String groupId;
        private String name;
        @SerializedName("eighteenplus")
        private String eighteenPlus;    // return as Boolean
        @SerializedName("iconserver")
        private String iconServer;
        @SerializedName("iconfarm")
        private String iconFarm;
        private Integer members;
        @SerializedName("pool_count")
        private Integer poolCount;
        @SerializedName("topic_count")
        private Integer topicCount;

        public String getGroupId() {
            return groupId;
        }

        public String getName() {
            return name;
        }

        public Boolean isEighteenPlus() {
            return JinxUtils.flickrBooleanToBoolean(eighteenPlus);
        }

        public String getIconServer() {
            return iconServer;
        }

        public String getIconFarm() {
            return iconFarm;
        }

        public Integer getMembers() {
            return members;
        }

        public Integer getPoolCount() {
            return poolCount;
        }

        public Integer getTopicCount() {
            return topicCount;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Group{");
            sb.append("groupId='").append(groupId).append('\'');
            sb.append(", name='").append(name).append('\'');
            sb.append(", eighteenPlus='").append(isEighteenPlus()).append('\'');
            sb.append(", iconServer='").append(iconServer).append('\'');
            sb.append(", iconFarm='").append(iconFarm).append('\'');
            sb.append(", members=").append(members);
            sb.append(", poolCount=").append(poolCount);
            sb.append(", topicCount=").append(topicCount);
            sb.append('}');
            return sb.toString();
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GroupSearch{");
        sb.append("page=").append(getPage());
        sb.append(", pages=").append(getPages());
        sb.append(", perPage=").append(getPerPage());
        sb.append(", total=").append(getTotal());
        sb.append(", groupList=").append(getGroupList() == null ? "null" : "[" + getGroupList().size() + " groups]");
        sb.append('}');
        return sb.toString();
    }
}
