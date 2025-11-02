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

package net.jeremybrooks.jinx.response.photos;

import net.jeremybrooks.jinx.JinxConstants;
import net.jeremybrooks.jinx.JinxUtils;
import net.jeremybrooks.jinx.response.Response;

import java.io.Serializable;

/**
 * @author Jeremy Brooks
 */
public class PhotoPerms extends Response {
	private static final long serialVersionUID = 3647446144022480064L;


	private _Perms perms;

	public String getPhotoId() {
		return perms == null ? null : perms.id;
	}
	public Boolean isPublic() {
		return perms == null ? null : JinxUtils.flickrBooleanToBoolean(perms.ispublic);
	}
	public Boolean isFriend() {
		return perms == null ? null : JinxUtils.flickrBooleanToBoolean(perms.isfriend);
	}
	public Boolean isFamily() {
		return perms == null ? null : JinxUtils.flickrBooleanToBoolean(perms.isfamily);
	}
	public JinxConstants.Perms getPermComment() {
		return perms == null ? null : JinxUtils.flickrPermsIdToPerms(perms.permcomment);
	}
	public JinxConstants.Perms getPermAddMeta() {
		return perms == null ? null : JinxUtils.flickrPermsIdToPerms(perms.permaddmeta);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("net.jeremybrooks.jinx.response.photos.PhotoPerms");
		sb.append("{photoId='").append(getPhotoId()).append('\'');
		sb.append(" | isPublic=").append(isPublic());
		sb.append(" | isFriend=").append(isFriend());
		sb.append(" | isFamily=").append(isFamily());
		sb.append(" | permComment=").append(getPermComment());
		sb.append(" | permAddMeta=").append(getPermAddMeta());
		sb.append('}');
		return sb.toString();
	}


	private class _Perms implements Serializable {
		private static final long serialVersionUID = 4488359344270008184L;
		private String id;
		private String ispublic;    // return as Boolean
		private String isfriend;    // return as Boolean
		private String isfamily;    // return as Boolean
		private Integer permcomment;
		private Integer permaddmeta;
	}
}
