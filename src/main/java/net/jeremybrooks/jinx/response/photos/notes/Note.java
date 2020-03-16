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

package net.jeremybrooks.jinx.response.photos.notes;

import com.google.gson.annotations.SerializedName;
import net.jeremybrooks.jinx.response.Response;

import java.io.Serializable;

/**
 *
 * Created by jeremyb on 7/22/14.
 */
public class Note extends Response {

    private static final long serialVersionUID = 7582323465653269260L;

    public String getNoteId() { return note == null ? null : note.noteId; }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("_Note{");
        sb.append("noteId='").append(getNoteId()).append('\'');
        sb.append('}');
        return sb.toString();
    }

    private _Note note;
    private class _Note implements Serializable {
        private static final long serialVersionUID = -8649928283937043853L;
        @SerializedName("id")
        private String noteId;
    }
}
