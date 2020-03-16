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

package net.jeremybrooks.jinx.response.photos.upload;

import com.google.gson.annotations.SerializedName;
import net.jeremybrooks.jinx.response.Response;

import java.io.Serializable;

/**
 *
 * Created by jeremyb on 8/8/14.
 */
public class ReplaceResponse extends Response {

    public String getStat() {
        return rsp == null ? null : rsp.stat;
    }

    public int getCode() {
        return rsp == null ? null : rsp.code;
    }

    public String getMessage() {
        return rsp == null ? null : rsp.message;
    }

    public String getSecret() {
        return rsp == null || rsp.photoid == null ? null : rsp.photoid.secret;
    }

    public String getOriginalSecret() {
        return rsp == null || rsp.photoid == null ? null : rsp.photoid.originalSecret;
    }

    public String getPhotoId() {
        return rsp == null || rsp.photoid == null ? null : rsp.photoid.photoId;
    }

    public String getTicketId() {
        return rsp == null ? null : rsp.ticketid;
    }

    private _Rsp rsp;
    private class _Rsp implements Serializable {
        private String stat;
        private int code;
        private String message;
        private _PhotoId photoid;
        private String ticketid;


    }
    private class _PhotoId implements Serializable {
        private String secret;
        @SerializedName("originalsecret")
        private String originalSecret;
        @SerializedName("text")
        private String photoId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("_Rsp{");
        sb.append("stat='").append(getStat()).append('\'');
        sb.append(", code=").append(getCode());
        sb.append(", message='").append(getMessage()).append('\'');
        sb.append(", secret=").append(getSecret());
        sb.append(", originalSecret=").append(getOriginalSecret());
        sb.append(", photoId=").append(getPhotoId());
        sb.append(", ticketid='").append(getTicketId()).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
