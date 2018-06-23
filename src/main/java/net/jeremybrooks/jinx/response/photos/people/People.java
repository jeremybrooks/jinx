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

package net.jeremybrooks.jinx.response.photos.people;

import com.google.gson.annotations.SerializedName;
import net.jeremybrooks.jinx.response.Response;

import java.io.Serializable;
import java.util.List;

/**
 *
 * Created by jeremyb on 7/22/14.
 */
public class People extends Response {

    private static final long serialVersionUID = -1437301270603570200L;

    public Integer getTotal() { return people == null ? null : people.total; }
    public Integer getPhotoWidth() { return people == null ? null : people.photoWidth; }
    public Integer getPhotoHeight() { return people == null ? null : people.photoHeight; }
    public List<Person> getPersonList() { return people == null ? null : people.personList; }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("_People{");
        sb.append("total=").append(getTotal());
        sb.append(", photoWidth=").append(getPhotoWidth());
        sb.append(", photoHeight=").append(getPhotoHeight());
        sb.append(", personList=").append(getPersonList() == null ? "null" : "[" + getPersonList().size() + " items]");
        sb.append('}');
        return sb.toString();
    }

    public class Person implements Serializable {
        private static final long serialVersionUID = -986503991359708628L;
        @SerializedName("nsid")
        private String userId;
        private String username;
        @SerializedName("iconserver")
        private String iconServer;
        @SerializedName("iconfarm")
        private String iconFarm;
        private String realname;
        @SerializedName("path_alias")
        private String pathAlias;
        @SerializedName("added_by")
        private String addedByUserId;

        public String getUserId() {
            return userId;
        }

        public String getUsername() {
            return username;
        }

        public String getIconServer() {
            return iconServer;
        }

        public String getIconFarm() {
            return iconFarm;
        }

        public String getRealname() {
            return realname;
        }

        public String getPathAlias() {
            return pathAlias;
        }

        public String getAddedByUserId() {
            return addedByUserId;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Person{");
            sb.append("userId='").append(userId).append('\'');
            sb.append(", username='").append(username).append('\'');
            sb.append(", iconServer='").append(iconServer).append('\'');
            sb.append(", iconFarm='").append(iconFarm).append('\'');
            sb.append(", realname='").append(realname).append('\'');
            sb.append(", pathAlias='").append(pathAlias).append('\'');
            sb.append(", addedByUserId='").append(addedByUserId).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }

    private _People people;
    private class _People implements Serializable {
        private static final long serialVersionUID = -8180703953916623655L;
        private Integer total;
        @SerializedName("photo_width")
        private Integer photoWidth;
        @SerializedName("photo_height")
        private Integer photoHeight;
        @SerializedName("person")
        private List<Person> personList;
    }
}
