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

package net.jeremybrooks.jinx.response.photos.upload;

import net.jeremybrooks.jinx.response.Response;

import java.io.Serializable;

/**
 * Created by jeremyb on 8/5/14.
 */
public class UploadResponse extends Response {

    private static final long serialVersionUID = -8891114079228370244L;

    public String getStat() {
        return rsp == null ? null : rsp.stat;
    }

    public int getCode() {
        return rsp == null ? null : rsp.code;
    }

    public String getMessage() {
        return rsp == null ? null : rsp.message;
    }

    public String getPhotoId() {
        return rsp == null ? null : rsp.photoid;
    }

    public String getTicketId() { return rsp == null ? null : rsp.ticketid; }


    private _Rsp rsp;

    private class _Rsp implements Serializable {
        private static final long serialVersionUID = -6437383739022068623L;
        private String stat;
        private String photoid;
        private String ticketid;
        private String message;
        private int code;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Rsp{");
        sb.append("stat='").append(getStat()).append('\'');
        sb.append(", photoId='").append(getPhotoId()).append('\'');
        sb.append(", ticketId='").append(getTicketId()).append('\'');
        sb.append(", message='").append(getMessage()).append('\'');
        sb.append(", code=").append(getCode());
        sb.append('}');
        return sb.toString();
    }
}
