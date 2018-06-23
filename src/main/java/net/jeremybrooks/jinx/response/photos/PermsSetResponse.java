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

package net.jeremybrooks.jinx.response.photos;

import net.jeremybrooks.jinx.response.Response;

import java.io.Serializable;

/**
 * @author Jeremy Brooks
 */
public class PermsSetResponse extends Response {
	private static final long serialVersionUID = -3773671183442307679L;

	private _PhotoId photoid;

	public String getPhotoId() {
		return photoid == null ? null : photoid._content;
	}

	public String getSecret() {
		return photoid == null ? null : photoid.secret;
	}

	public String getOriginalSecret() {
		return photoid == null ? null : photoid.originalsecret;
	}


	private class _PhotoId implements Serializable {
		private static final long serialVersionUID = 3669953559462409292L;
		private String _content;
		private String secret;
		private String originalsecret;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("net.jeremybrooks.jinx.response.photos.PermsSetResponse._PhotoId");
		sb.append("{photoId='").append(getPhotoId()).append('\'');
		sb.append(" | secret='").append(getSecret()).append('\'');
		sb.append(" | originalSecret='").append(getOriginalSecret()).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
