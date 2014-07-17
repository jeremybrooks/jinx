/*
 * Jinx is Copyright 2010-2014 by Jeremy Brooks and Contributors
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
import net.jeremybrooks.jinx.JinxUtils;
import net.jeremybrooks.jinx.response.Response;

import java.io.Serializable;

/**
 * Represents data returned by {@link net.jeremybrooks.jinx.api.PeopleApi#getUploadStatus()}
 * <p/>
 * Bandwidth and filesize numbers are provided in bytes and kilobytes.
 * <p/>
 * Bandwidth is specified in bytes/kb per month.
 * <p/>
 * All accounts display "lots" for the number of remaining sets, but remains in the response for backwards compatibility.
 * <p/>
 * Pro accounts display "lots" for the number of remaining videos, while free users will display 0, 1, or 2.
 * <p/>
 * Created by jeremyb on 7/15/14.
 */
public class UploadStatus extends Response {

    public String getUserId() {
        return user == null ? null : user.userId;
    }

    public Boolean isPro() {
        return user == null ? null : JinxUtils.flickrBooleanToBoolean(user.ispro);
    }

    public String getUsername() {
        return (user == null || user.username == null) ? null : user.username._content;
    }

    public String getBandwidthMax() {
        return (user == null || user.bandwidth == null) ? null : user.bandwidth.max;
    }

    public Integer getBandwidthUsed() {
        return (user == null || user.bandwidth == null) ? null : user.bandwidth.used;
    }

    public String getBandwidthMaxBytes() {
        return (user == null || user.bandwidth == null) ? null : user.bandwidth.maxbytes;
    }

    public Integer getBandwidthUsedBytes() {
        return (user == null || user.bandwidth == null) ? null : user.bandwidth.usedbytes;
    }

    public String getBandwidthRemainingBytes() {
        return (user == null || user.bandwidth == null) ? null : user.bandwidth.remainingbytes;
    }

    public String getBandwidthMaxKb() {
        return (user == null || user.bandwidth == null) ? null : user.bandwidth.maxkb;
    }

    public Integer getBandwidthUsedKb() {
        return (user == null || user.bandwidth == null) ? null : user.bandwidth.usedkb;
    }

    public String getBandwidthRemainingKb() {
        return (user == null || user.bandwidth == null) ? null : user.bandwidth.remainingkb;
    }

    public Boolean isBandwidthUnlimited() {
        return (user == null || user.bandwidth == null) ? null : JinxUtils.flickrBooleanToBoolean(user.bandwidth.unlimited);
    }

    public String getFilesizeMax() {
        return (user == null || user.filesize == null) ? null : user.filesize.max;
    }

    public String getFilesizeMaxBytes() {
        return (user == null || user.filesize == null) ? null : user.filesize.maxbytes;
    }

    public String getFilesizeMaxKb() {
        return (user == null || user.filesize == null) ? null : user.filesize.maxkb;
    }

    public String getFilesizeMaxMb() {
        return (user == null || user.filesize == null) ? null : user.filesize.maxmb;
    }

    public Integer getSetsCreated() {
        return (user == null || user.sets == null) ? null : user.sets.created;
    }

    public String getSetsRemaining() {
        return (user == null || user.sets == null) ? null : user.sets.remaining;
    }

    public String getVideosizeMaxBytes() {
        return (user == null || user.videosize == null) ? null : user.videosize.maxbytes;
    }

    public String getVideosizeMaxKb() {
        return (user == null || user.videosize == null) ? null : user.videosize.maxkb;
    }

    public String getVideosizeMaxMb() {
        return (user == null || user.videosize == null) ? null : user.videosize.maxmb;
    }

    public Integer getVideosUploaded() {
        return (user == null || user.videos == null) ? null : user.videos.uploaded;
    }

    public String getVideosRemaining() {
        return (user == null || user.videos == null) ? null : user.videos.remaining;
    }

    private _User user;

    private class _User implements Serializable {
        @SerializedName("id")
        private String userId;
        private String ispro;   // return as Boolean
        private _Username username;
        private _Bandwidth bandwidth;
        private _Filesize filesize;
        private _Sets sets;
        private _Videosize videosize;
        private _Videos videos;

        private class _Username implements Serializable {
            private static final long serialVersionUID = 4335539758224999032L;
            private String _content;
        }

        private class _Bandwidth implements Serializable {
            private static final long serialVersionUID = -7911072339678015249L;
            private String max;
            private Integer used;
            private String maxbytes;
            private Integer usedbytes;
            private String remainingbytes;
            private String maxkb;
            private Integer usedkb;
            private String remainingkb;
            private String unlimited;   // return as Boolean
        }

        private class _Filesize implements Serializable {
            private static final long serialVersionUID = -9211060262061547694L;
            private String max;
            private String maxbytes;
            private String maxkb;
            private String maxmb;
        }

        private class _Sets implements Serializable {
            private static final long serialVersionUID = 174941180874450625L;
            private Integer created;
            private String remaining;
        }

        private class _Videosize implements Serializable {
            private static final long serialVersionUID = 6172570624593901561L;
            private String maxbytes;
            private String maxkb;
            private String maxmb;
        }

        private class _Videos implements Serializable {
            private static final long serialVersionUID = -7597666407613842826L;
            private Integer uploaded;
            private String remaining;
        }
    }
}
