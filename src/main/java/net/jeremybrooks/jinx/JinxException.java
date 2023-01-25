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

package net.jeremybrooks.jinx;


import net.jeremybrooks.jinx.response.Response;

/**
 * @author Jeremy Brooks
 */
public class JinxException extends java.lang.Exception {


  private static final long serialVersionUID = -8987959765204324519L;

  private int flickrErrorCode;
  private String flickrErrorMessage;

  /**
   * Constructs an instance of {@code JinxException} with the specified detail message.
   *
   * @param msg the detail message.
   */
  public JinxException(String msg) {
    super(msg);
  }


  /**
   * Constructs an instance of {@code JinxException} with the specified detail message and cause..
   *
   * @param msg   the detail message.
   * @param cause the cause of the error.
   */
  public JinxException(String msg, Throwable cause) {
    super(msg, cause);
  }

  /**
   * Constructs an instance of {@code JinxException} with the specified detail message, cause, and response from Flickr.
   * @param msg the detail message.
   * @param cause the cause of the error.
   * @param response the response from Flickr with the code and error message.
   */
  public JinxException(String msg, Throwable cause, Response response) {
    super(msg + " [" + response.getCode() + ": " + response.getMessage() + "]", cause);
    this.setFlickrErrorCode(response.getCode());
    this.setFlickrErrorMessage(response.getMessage());
  }

  public int getFlickrErrorCode() {
    return flickrErrorCode;
  }

  public void setFlickrErrorCode(int flickrErrorCode) {
    this.flickrErrorCode = flickrErrorCode;
  }

  public String getFlickrErrorMessage() {
    return flickrErrorMessage;
  }

  public void setFlickrErrorMessage(String flickrErrorMessage) {
    this.flickrErrorMessage = flickrErrorMessage;
  }
}
