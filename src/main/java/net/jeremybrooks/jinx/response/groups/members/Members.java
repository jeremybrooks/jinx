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

package net.jeremybrooks.jinx.response.groups.members;

import com.google.gson.annotations.SerializedName;
import net.jeremybrooks.jinx.JinxConstants;
import net.jeremybrooks.jinx.JinxUtils;
import net.jeremybrooks.jinx.response.Response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jeremyb on 7/9/14.
 */
public class Members extends Response {
    private static final long serialVersionUID = 899649500975921602L;

    private _Members members;

    public Integer getPage() { return members == null ? null : members.page; }
    public Integer getPages() { return members == null ? null : members.pages; }
    public Integer getTotal() { return members == null ? null : members.total; }
    public Integer getPerPage() { return members == null ? null : members.perPage; }
    public List<Member> getMemberList() { return members == null ? null : members.memberList; }

    private class _Members implements Serializable {
        private static final long serialVersionUID = 7348294905996414863L;
        private Integer page;
        private Integer pages;
        private Integer total;
        @SerializedName("perpage")
        private Integer perPage;
        @SerializedName("member")
        private List<Member> memberList;
    }

    public class Member implements Serializable {
        private static final long serialVersionUID = -2542475274225736342L;
        private String nsid;
        @SerializedName("username")
        private String userName;
        @SerializedName("iconserver")
        private String iconServer;
        @SerializedName("iconfarm")
        private String iconFarm;
        @SerializedName("membertype")
        private Integer memberType;      // return as MemberType
        @SerializedName("realname")
        private String realName;

        public String getUserId() {
            return nsid;
        }

        public String getUserName() {
            return userName;
        }

        public String getIconServer() {
            return iconServer;
        }

        public String getIconFarm() {
            return iconFarm;
        }

        public JinxConstants.MemberType getMemberType() {
            return JinxUtils.typeIdToMemberType(memberType);
        }

        public String getRealName() {
            return realName;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Member{");
            sb.append("userId='").append(nsid).append('\'');
            sb.append(", userName='").append(userName).append('\'');
            sb.append(", iconServer='").append(iconServer).append('\'');
            sb.append(", iconFarm='").append(iconFarm).append('\'');
            sb.append(", memberType=").append(getMemberType().toString());
            sb.append(", realName='").append(realName).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("_Members{");
        sb.append("page=").append(getPage());
        sb.append(", pages=").append(getPages());
        sb.append(", total=").append(getTotal());
        sb.append(", perPage=").append(getPerPage());
        sb.append(", memberList=").append(getMemberList() == null ? "null" : "[" + getMemberList().size() + " items]");
        sb.append('}');
        return sb.toString();
    }
}
