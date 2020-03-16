/*
 * Jinx is Copyright 2010-2020 by Jeremy Brooks and Contributors
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

package net.jeremybrooks.jinx.api;

import net.jeremybrooks.jinx.Jinx;
import net.jeremybrooks.jinx.JinxException;
import net.jeremybrooks.jinx.JinxUtils;
import net.jeremybrooks.jinx.response.Response;
import net.jeremybrooks.jinx.response.photos.notes.Note;

import java.util.Map;
import java.util.TreeMap;


/**
 * Provides access to the flickr.photos.notes API methods.
 *
 * @author Jeremy Brooks
 * @see <a href="https://www.flickr.com/services/api/">Flickr API documentation</a> for more details.
 */
public class PhotosNotesApi {
  private Jinx jinx;

  public PhotosNotesApi(Jinx jinx) {
    this.jinx = jinx;
  }

  /**
   * Add a note to a photo. Coordinates and sizes are in pixels, based on the 500px image size shown on individual photo pages.
   * <br>
   * This method requires authentication with 'write' permission.
   *
   * @param photoId    (Required) The id of the photo to add a note to.
   * @param noteX      (Required) The left coordinate of the note.
   * @param noteY      (Required) The top coordinate of the note.
   * @param noteWidth  (Required) The width of the note.
   * @param noteHeight (Required) The height of the note.
   * @param noteText   (Required) The text of the note.
   * @return object with the ID for the newly created note.
   * @throws JinxException if required parameters are missing, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.photos.notes.add.html">flickr.photos.notes.add</a>
   */
  public Note add(String photoId, int noteX, int noteY, int noteWidth, int noteHeight, String noteText) throws JinxException {
    JinxUtils.validateParams(photoId, noteText);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.photos.notes.add");
    params.put("photo_id", photoId);
    params.put("note_x", Integer.toString(noteX));
    params.put("note_y", Integer.toString(noteY));
    params.put("note_w", Integer.toString(noteWidth));
    params.put("note_h", Integer.toString(noteHeight));
    params.put("note_text", noteText);
    return jinx.flickrPost(params, Note.class);
  }

  /**
   * Edit a note on a photo. Coordinates and sizes are in pixels, based on the 500px image size shown on individual photo pages.
   * <br>
   * This method requires authentication with 'write' permission.
   *
   * @param noteId     (Required) The id of the note to edit.
   * @param noteX      (Required) The left coordinate of the note.
   * @param noteY      (Required) The top coordinate of the note.
   * @param noteWidth  (Required) The width of the note.
   * @param noteHeight (Required) The height of the note.
   * @param noteText   (Required) The text of the note.
   * @return object with the status of the requested operation.
   * @throws JinxException if required parameters are missing, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.photos.notes.edit.html">flickr.photos.notes.edit</a>
   */
  public Response edit(String noteId, int noteX, int noteY, int noteWidth, int noteHeight, String noteText) throws JinxException {
    JinxUtils.validateParams(noteId, noteText);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.photos.notes.edit");
    params.put("note_id", noteId);
    params.put("note_x", Integer.toString(noteX));
    params.put("note_y", Integer.toString(noteY));
    params.put("note_w", Integer.toString(noteWidth));
    params.put("note_h", Integer.toString(noteHeight));
    params.put("note_text", noteText);
    return jinx.flickrPost(params, Response.class);
  }

  /**
   * Delete a note from a photo.
   * <br>
   * This method requires authentication with 'write' permission.
   *
   * @param noteId (Required) The id of the note to delete.
   * @return object with the status of the requested operation.
   * @throws JinxException if required parameters are missing, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.photos.notes.delete.html">flickr.photos.notes.delete</a>
   */
  public Response delete(String noteId) throws JinxException {
    JinxUtils.validateParams(noteId);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.photos.notes.delete");
    params.put("note_id", noteId);
    return jinx.flickrPost(params, Response.class);
  }
}
