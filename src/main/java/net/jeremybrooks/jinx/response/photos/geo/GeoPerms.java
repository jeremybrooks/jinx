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

package net.jeremybrooks.jinx.response.photos.geo;

import net.jeremybrooks.jinx.JinxUtils;
import net.jeremybrooks.jinx.response.Response;

import java.io.Serializable;

/**
 * Created by jeremyb on 7/21/14.
 */
public class GeoPerms extends Response {

    private static final long serialVersionUID = 6141983717363308545L;

    public String getPhotoId() { return perms == null ? null : perms.id; }
    public Boolean isPublic() { return perms == null ? null : JinxUtils.flickrBooleanToBoolean(perms.ispublic); }
    public Boolean isContact() { return perms == null ? null : JinxUtils.flickrBooleanToBoolean(perms.iscontact); }
    public Boolean isFriend() { return perms == null ? null : JinxUtils.flickrBooleanToBoolean(perms.isfriend); }
    public Boolean isFamily() { return perms == null ? null : JinxUtils.flickrBooleanToBoolean(perms.isfamily); }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GeoPerms{");
        sb.append("photoId='").append(getPhotoId()).append('\'');
        sb.append("isPublic=").append(isPublic());
        sb.append("isContact=").append(isContact());
        sb.append("isFriend=").append(isFriend());
        sb.append("isFamily=").append(isFamily());
        sb.append('}');
        return sb.toString();
    }

    private _Perms perms;
    private class _Perms implements Serializable {
        private static final long serialVersionUID = 7032690532158626376L;
        private String id;
        private String ispublic;    // return as Boolean
        private String iscontact;    // return as Boolean
        private String isfriend;    // return as Boolean
        private String isfamily;    // return as Boolean
    }
}
