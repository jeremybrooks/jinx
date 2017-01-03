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

package net.jeremybrooks.jinx.response.people;

import com.google.gson.annotations.SerializedName;
import net.jeremybrooks.jinx.response.Response;

import java.io.Serializable;

/**
 * Represents photo and video limits for a user account.
 *
 * For more details, see <a href="http://www.flickr.com/help/limits/">http://www.flickr.com/help/limits/</a>.
 *
 * Created by jeremyb on 7/15/14.
 */
public class Limits extends Response {

    public String getUserId() { return person == null ? null : person.userId; }

    /**
     * Get maximum size in pixels for photos displayed on the site (0 means that no limit is in place).
     *
     * No limit is placed on the dimension of photos uploaded.
     *
     * @return maximum size in pixels for photos displayed on Flickr.
     */
    public String getMaxPhotoDisplayPx() { return (person == null || person.photos == null) ? null : person.photos.maxdisplaypx; }

    /**
     * Get maximum file size in bytes for photo uploads.
     *
     * @return maximum file size in bytes for photo uploads.
     */
    public String getMaxPhotoUpload() { return (person == null || person.photos == null) ? null : person.photos.maxupload; }

    /**
     * Get maximum duration in seconds of a video.
     *
     * @return maximum duration in seconds of a video.
     */
    public String getMaxVideoDuration() { return (person == null || person.videos == null) ? null : person.videos.maxduration; }

    /**
     * Ge maximum file size in bytes for video uploads.
     *
     * @return maximum file size in bytes for video uploads.
     */
    public String getMaxVideoUpload() { return (person == null || person.videos == null) ? null : person.videos.maxupload; }

    private _Person person;
    private class _Person implements Serializable {
        @SerializedName("nsid")
        private String userId;
        private _Photos photos;
        private _Videos videos;
    }
    private class _Photos implements Serializable {
        private String maxdisplaypx;
        private String maxupload;
    }
    private class _Videos implements Serializable {
        private String maxduration;
        private String maxupload;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Limits{");
        sb.append("userId='").append(getUserId()).append('\'');
        sb.append("maxPhotoDisplayPx='").append(getMaxPhotoDisplayPx()).append('\'');
        sb.append("maxPhotoUpload='").append(getMaxPhotoUpload()).append('\'');
        sb.append("maxVideoDuration='").append(getMaxVideoDuration()).append('\'');
        sb.append("maxVideoUpload='").append(getMaxVideoUpload()).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
