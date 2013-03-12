/*
 * Jinx is Copyright 2010-2013 by Jeremy Brooks and Contributors
 *
 * This file is part of Jinx.
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
package net.jeremybrooks.jinx.response;

import java.io.Serializable;

/**
 * This is the base type for all classes that encapsulate Flickr responses.
 * The response will always contain a status (stat). If there were no errors, stat will be "ok". If there are
 * errors, stat will be a different value, and the error code and message will be available.
 *
 * @author Jeremy Brooks
 */
public class Response implements Serializable {
	private static final long serialVersionUID = 7831423164760058236L;
	private String stat;
	private int code;
	private String message;

	/**
	 * Get the status of the call.
	 * If there were no errors, this should be "ok".
	 * @return status of the API call.
	 */
	public String getStat() {
		return stat;
	}

	/**
	 * Get the error code.
	 * If there were no errors, this will be zero.
	 *
	 * @return error code from the API call.
	 */
	public int getCode() {
		return code;
	}


	/**
	 * Get the error message.
	 * If there were no errors, this will return null.
	 *
	 * @return error message from the API call.
	 */
	public String getMessage() {
		return message;
	}


	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("stat=\"").append(stat).append('\"');
		sb.append(",code=").append(code);
		sb.append(",message=\"").append(message).append('\"');
		return sb.toString();
	}
}
