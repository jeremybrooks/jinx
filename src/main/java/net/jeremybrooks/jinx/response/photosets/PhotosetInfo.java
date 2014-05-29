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

package net.jeremybrooks.jinx.response.photosets;

import net.jeremybrooks.jinx.response.Response;

/**
 *
 * @author Jeremy Brooks
 */
public class PhotosetInfo extends Response {
	private static final long serialVersionUID = -6706772513615251921L;
	private Photoset photoset;
	public Photoset getPhotoset() {return photoset;}


	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("classname=").append(this.getClass().getName());
		sb.append(",photoset=").append(photoset);
		return sb.toString();
	}
}
