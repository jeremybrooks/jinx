/*
 * Jinx is Copyright 2010 by Jeremy Brooks
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
package net.jeremybrooks.jinx;


/**
 *
 * @author jeremyb
 */
public class JinxException extends java.lang.Exception {

    private int errorCode;
    private String errorMessage;

    /**
     * Creates a new instance of <code>JinxException</code> without detail message.
     */
    public JinxException() {
    }


    /**
     * Constructs an instance of <code>JinxException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public JinxException(String msg) {
	super(msg);
    }


    /**
     * Constructs an instance of <code>JinxException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public JinxException(String msg, Throwable cause) {
	super(msg, cause);
    }

    public JinxException(String msg, Throwable cause, int errorCode, String errorMessage) {
	super(msg, cause);
	this.errorCode = errorCode;
	this.errorMessage = errorMessage;
    }

    /**
     * @return the errorCode
     */
    public int getErrorCode() {
	return errorCode;
    }


    /**
     * @param errorCode the errorCode to set
     */
    public void setErrorCode(int errorCode) {
	this.errorCode = errorCode;
    }


    /**
     * @return the errorMessage
     */
    public String getErrorMessage() {
	return errorMessage;
    }


    /**
     * @param errorMessage the errorMessage to set
     */
    public void setErrorMessage(String errorMessage) {
	this.errorMessage = errorMessage;
    }

}
