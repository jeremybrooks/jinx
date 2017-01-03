/*
 * Jinx is Copyright 2010-2017 by Jeremy Brooks and Contributors
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

package net.jeremybrooks.jinx.response.photos.transform;

import com.google.gson.annotations.SerializedName;
import net.jeremybrooks.jinx.response.Response;

import java.io.Serializable;

/**
 *
 * Created by jeremyb on 7/22/14.
 */
public class TransformResult extends Response {

    private static final long serialVersionUID = 8416498893229185814L;

    public String getPhotoId() {return photoId == null ? null : photoId.photoId;}
    public String getSecret() {return photoId == null ? null : photoId.secret;}
    public String getOriginalSecret() {return photoId == null ? null : photoId.originalSecret;}


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("_PhotoId{");
        sb.append("photoId='").append(getPhotoId()).append('\'');
        sb.append(", secret='").append(getSecret()).append('\'');
        sb.append(", originalSecret='").append(getOriginalSecret()).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @SerializedName("photoid")
    private _PhotoId photoId;

    private class _PhotoId implements Serializable {
        private static final long serialVersionUID = -5533453371523945615L;
        @SerializedName("_content")
        private String photoId;
        private String secret;
        @SerializedName("originalsecret")
        private String originalSecret;
    }
}
