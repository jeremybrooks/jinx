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
		sb.append("classname=").append(this.getClass().getName());
		sb.append(",stat=\"").append(stat).append('\"');
		sb.append(",code=").append(code);
		sb.append(",message=\"").append(message).append('\"');
		return sb.toString();
	}
}
