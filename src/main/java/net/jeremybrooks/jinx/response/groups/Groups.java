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

package net.jeremybrooks.jinx.response.groups;

import com.google.gson.annotations.SerializedName;
import net.jeremybrooks.jinx.JinxConstants;
import net.jeremybrooks.jinx.JinxUtils;
import net.jeremybrooks.jinx.response.Response;

import java.io.Serializable;
import java.util.List;

/**
 * Represents a list of groups as returned by flickr.people.getGroups
 * <br>
 * There are subtle inconsistencies between the way groups are returned by various methods, so it is
 * necessary to have multiple classes that represent the group data.
 * <br>
 * Created by jeremyb on 7/15/14.
 */
public class Groups extends Response {

    private static final long serialVersionUID = 4608102837723779179L;

    public List<Group> getGroupList() { return groups == null ? null : groups.groupList; }

    public static class Group implements Serializable {
      private static final long serialVersionUID = -3271597032766710044L;
      @SerializedName("nsid")
        private String groupId;
        private String name;
        @SerializedName("iconfarm")
        private String iconFarm;
        @SerializedName("iconserver")
        private String iconServer;
        private String admin;   // return as boolean
        @SerializedName("eighteenplus")
        private String eighteenPlus;    // return as Boolean
        @SerializedName("invitation_only")
        private String invitationOnly;  // return as Boolean
        private Integer members;
        @SerializedName("pool_count")
        private Integer poolCount;
        private Integer privacy;
        private GroupInfo.Throttle throttle;
        private GroupInfo.Restrictions restrictions;

        public String getGroupId() {
            return groupId;
        }

        public String getName() {
            return name;
        }

        public String getIconFarm() {
            return iconFarm;
        }

        public String getIconServer() {
            return iconServer;
        }

        public Boolean isAdmin() {
            return JinxUtils.flickrBooleanToBoolean(admin);
        }

        public Boolean isEighteenPlus() {
            return JinxUtils.flickrBooleanToBoolean(eighteenPlus);
        }

        public Boolean isInvitationOnly() {
            return JinxUtils.flickrBooleanToBoolean(invitationOnly);
        }

        public Integer getMembers() {
            return members;
        }

        public Integer getPoolCount() {
            return poolCount;
        }

        public JinxConstants.GroupPrivacy getGroupPrivacy() {
            return privacy == null ? null : JinxUtils.privacyIdToGroupPrivacyEnum(privacy);
        }

        public GroupInfo.Throttle getThrottle() {
            return throttle;
        }

        public GroupInfo.Restrictions getRestrictions() {
            return restrictions;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Group{");
            sb.append("groupId='").append(groupId).append('\'');
            sb.append(", name='").append(name).append('\'');
            sb.append(", iconFarm='").append(iconFarm).append('\'');
            sb.append(", iconServer='").append(iconServer).append('\'');
            sb.append(", isAdmin=").append(isAdmin());
            sb.append(", isEighteenPlus=").append(isEighteenPlus());
            sb.append(", isInvitationOnly=").append(isInvitationOnly());
            sb.append(", members=").append(members);
            sb.append(", poolCount=").append(poolCount);
            sb.append(", privacy=").append(getGroupPrivacy() == null ? "null" : getGroupPrivacy().toString());
            sb.append(", throttle=").append(throttle);
            sb.append(", restrictions=").append(restrictions);
            sb.append('}');
            return sb.toString();
        }
    }


    private _Groups groups;
    private static class _Groups implements Serializable {
        private static final long serialVersionUID = -3751967583936500705L;
        @SerializedName("group")
        private List<Group> groupList;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Groups{");
        sb.append("groupList=").append(getGroupList() == null ? "null" : "[" + getGroupList().size() + " items]");
        sb.append('}');
        return sb.toString();
    }
}
