/*
 * Jinx is Copyright 2010-2020 by Jeremy Brooks and Contributors
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

/**
 * Created by jeremyb on 7/9/14.
 */
public class GroupInfo extends Response {
    private static final long serialVersionUID = 5129409428352717797L;

    private _Group group;

    public String getGroupId() {
        return group == null ? null : group.groupId;
    }

    public String getPathAlias() {
        return group == null ? null : group.path_alias;
    }

    public String getIconServer() {
        return group == null ? null : group.iconserver;
    }

    public String getIconFarm() {
        return group == null ? null : group.iconfarm;
    }

    public String getName() {
        return (group == null || group.name == null) ? null : group.name._content;
    }

    public String getDescription() {
        return (group == null || group.description == null) ? null : group.description._content;
    }

    public String getRules() {
        return (group == null || group.rules == null) ? null : group.rules._content;
    }

    public Integer getMembers() {
        return (group == null || group.members == null) ? null : group.members._content;
    }

    public Integer getPoolCount() {
        return (group == null || group.pool_count == null) ? null : group.pool_count._content;
    }

    public Integer getTopicCount() {
        return (group == null || group.topic_count == null) ? null : group.topic_count._content;
    }

    public String getPrivacy() {
        return (group == null || group.privacy == null) ? null : group.privacy._content;
    }

    public String getLang() {
        return group == null ? null : group.lang;
    }

    public Boolean isPoolModerated() {
        return group == null ? null : JinxUtils.flickrBooleanToBoolean(group.ispoolmoderated);
    }

    public Roles getRoles() {
        return group == null ? null : group.roles;
    }

    public Boolean isMember() {
        return group == null ? null : JinxUtils.flickrBooleanToBoolean(group.is_member);
    }

    public Boolean isModerator() {
        return group == null ? null : JinxUtils.flickrBooleanToBoolean(group.is_moderator);
    }

    public Boolean isAdmin() {
        return group == null ? null : JinxUtils.flickrBooleanToBoolean(group.is_admin);
    }

    public Blast getBlast() {
        return group == null ? null : group.blast;
    }

    public Throttle getThrottle() {
        return group == null ? null : group.throttle;
    }

    public Restrictions getRestrictions() {
        return group == null ? null : group.restrictions;
    }

    private class _Group implements Serializable {
        private static final long serialVersionUID = 3619193306267952402L;
        @SerializedName("id")
        private String groupId;
        private String path_alias;
        private String iconserver;
        private String iconfarm;
        private _Name name;
        private _Description description;
        private _Rules rules;
        private _Members members;
        private _PoolCount pool_count;
        private _TopicCount topic_count;
        private _Privacy privacy;
        private String lang;
        private String ispoolmoderated; // return as Boolean
        private Roles roles;
        private String is_member;        // return as Boolean
        private String is_moderator;     // return as Boolean
        private String is_admin;         // return as Boolean
        private Blast blast;
        private Throttle throttle;
        private Restrictions restrictions;


    }

    private class _Name implements Serializable {
        private static final long serialVersionUID = 2720743445900403801L;
        private String _content;
    }

    private class _Description implements Serializable {
        private static final long serialVersionUID = -6201984055137497311L;
        private String _content;
    }

    private class _Rules implements Serializable {
        private static final long serialVersionUID = 2032206593333546402L;
        private String _content;
    }

    private class _Members implements Serializable {
        private static final long serialVersionUID = -4149617815816216429L;
        private Integer _content;
    }

    private class _PoolCount implements Serializable {
        private static final long serialVersionUID = 7422719456981722665L;
        private Integer _content;
    }

    private class _TopicCount implements Serializable {
        private static final long serialVersionUID = -3274034157783312633L;
        private Integer _content;
    }

    private class _Privacy implements Serializable {
        private static final long serialVersionUID = 492211885824838218L;
        private String _content;
    }

    public class Roles implements Serializable {
        private static final long serialVersionUID = 5945836801940343329L;
        private String member;
        private String moderator;
        private String admin;

        public String getMember() {
            return member;
        }

        public String getModerator() {
            return moderator;
        }

        public String getAdmin() {
            return admin;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Roles{");
            sb.append("member='").append(member).append('\'');
            sb.append(", moderator='").append(moderator).append('\'');
            sb.append(", admin='").append(admin).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }

    public class Blast implements Serializable {
        private static final long serialVersionUID = 8688253086044776344L;
        @SerializedName("_content")
        private String content;
        private String date_blast_added;
        private String user_id;

        public String getContent() {
            return content;
        }

        public String getDateBlastAdded() {
            return date_blast_added;
        }

        public String getUserId() {
            return user_id;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Blast{");
            sb.append("content='").append(content).append('\'');
            sb.append(", dateBlastAdded='").append(getDateBlastAdded()).append('\'');
            sb.append(", userId='").append(getUserId()).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }

    public class Throttle implements Serializable {
        private static final long serialVersionUID = -5676302326157908574L;
        private String mode;
        private Integer count;
        private Integer remaining;

        public String getMode() {
            return mode;
        }

        public Integer getCount() {
            return count;
        }

        public Integer getRemaining() {
            return remaining;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Throttle{");
            sb.append("mode='").append(mode).append('\'');
            sb.append(", count=").append(count);
            sb.append(", remaining=").append(remaining);
            sb.append('}');
            return sb.toString();
        }
    }

    public class Restrictions implements Serializable {
        private static final long serialVersionUID = -1183440739538517390L;
        /* All fields are returned as Boolean */
        private String photos_ok;
        private String videos_ok;
        private String images_ok;
        private String screens_ok;
        private String art_ok;
        private String safe_ok;
        private String moderate_ok;
        private String restricted_ok;
        private String has_geo;

        public Boolean isPhotosOk() {
            return JinxUtils.flickrBooleanToBoolean(photos_ok);
        }

        public Boolean isVideosOk() {
            return JinxUtils.flickrBooleanToBoolean(videos_ok);
        }

        public Boolean isImagesOk() {
            return JinxUtils.flickrBooleanToBoolean(images_ok);
        }

        public Boolean isScreensOk() {
            return JinxUtils.flickrBooleanToBoolean(screens_ok);
        }

        public Boolean isArtOk() {
            return JinxUtils.flickrBooleanToBoolean(art_ok);
        }

        public Boolean isSafeOk() {
            return JinxUtils.flickrBooleanToBoolean(safe_ok);
        }

        public Boolean isModerateOk() {
            return JinxUtils.flickrBooleanToBoolean(moderate_ok);
        }

        public Boolean isRestrictedOk() {
            return JinxUtils.flickrBooleanToBoolean(restricted_ok);
        }

        public Boolean isHasGeo() {
            return JinxUtils.flickrBooleanToBoolean(has_geo);
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Restrictions{");
            sb.append("photos_ok='").append(isPhotosOk()).append('\'');
            sb.append(", videos_ok='").append(isVideosOk()).append('\'');
            sb.append(", images_ok='").append(isImagesOk()).append('\'');
            sb.append(", screens_ok='").append(isScreensOk()).append('\'');
            sb.append(", art_ok='").append(isArtOk()).append('\'');
            sb.append(", safe_ok='").append(isSafeOk()).append('\'');
            sb.append(", moderate_ok='").append(isModerateOk()).append('\'');
            sb.append(", restricted_ok='").append(isRestrictedOk()).append('\'');
            sb.append(", has_geo='").append(isHasGeo()).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }

    @Override
    public String toString() {

        final StringBuilder sb = new StringBuilder("GroupInfo{");
        sb.append("groupId='").append(getGroupId()).append('\'');
        sb.append(", pathAlias='").append(getPathAlias()).append('\'');
        sb.append(", iconServer='").append(getIconServer()).append('\'');
        sb.append(", iconFarm='").append(getIconFarm()).append('\'');
        sb.append(", name=").append(getName());
        sb.append(", description=").append(getDescription());
        sb.append(", rules=").append(getRules());
        sb.append(", members=").append(getMembers());
        sb.append(", poolCount=").append(getPoolCount());
        sb.append(", topicCount=").append(getTopicCount());
        sb.append(", privacy=").append(getPrivacy());
        sb.append(", lang='").append(getLang()).append('\'');
        sb.append(", isPoolModerated='").append(isPoolModerated()).append('\'');
        sb.append(", roles=").append(getRoles());
        sb.append(", is_member='").append(isMember()).append('\'');
        sb.append(", is_moderator='").append(isModerator()).append('\'');
        sb.append(", is_admin='").append(isAdmin()).append('\'');
        sb.append(", blast=").append(getBlast());
        sb.append(", throttle=").append(getThrottle());
        sb.append(", restrictions=").append(getRestrictions());
        sb.append('}');
        return sb.toString();
    }
}
