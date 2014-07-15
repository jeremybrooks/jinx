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
 * Created by jeremyb on 7/14/14.
 */
public class Person extends Response {

    public String getNsid() { return person == null ? null : person.nsid; }
    public Boolean isPro() { return person == null ? null : JinxUtils.flickrBooleanToBoolean(person.isPro); }
    public String getIconServer() { return person == null ? null : person.iconServer; }
    public String getIconFarm() { return person == null ? null : person.iconFarm; }
    public String getPathAlias() { return person == null ? null : person.pathAlias; }
    public String getGender() { return person == null ? null : person.gender; }
    public Boolean isIgnored() { return person == null ? null : JinxUtils.flickrBooleanToBoolean(person.ignored); }
    public Boolean isContact() { return person == null ? null : JinxUtils.flickrBooleanToBoolean(person.contact); }
    public Boolean isFriend() { return person == null ? null : JinxUtils.flickrBooleanToBoolean(person.friend); }
    public Boolean isFamily() { return person == null ? null : JinxUtils.flickrBooleanToBoolean(person.family); }
    public Boolean isRevContact() { return person == null ? null : JinxUtils.flickrBooleanToBoolean(person.revContact); }
    public Boolean isRevFriend() { return person == null ? null : JinxUtils.flickrBooleanToBoolean(person.revFriend); }
    public Boolean isRevFamily() { return person == null ? null : JinxUtils.flickrBooleanToBoolean(person.revFamily); }

    public String getUsername() { return (person == null || person.username == null) ? null : person.username._content; }
    public String getRealname() { return (person == null || person.realname == null) ? null : person.realname._content; }
    public String getLocation() { return (person == null || person.location == null) ? null : person.location._content; }

    public String getTimezoneLabel() { return (person == null || person.timezone == null) ? null : person.timezone.label; }
    public String getTimezoneOffset() { return (person == null || person.timezone == null) ? null : person.timezone.offset; }

    public String getDescription() { return (person == null || person.description == null) ? null : person.description._content; }
    public String getPhotosUrl() { return (person == null || person.photosUrl == null) ? null : person.photosUrl._content; }
    public String getProfileUrl() { return (person == null || person.profileUrl == null) ? null : person.profileUrl._content; }
    public String getMobileUrl() { return (person == null || person.mobileUrl == null) ? null : person.mobileUrl._content; }

    public String getPhotosFirstDateTaken() { return (person == null || person.photos == null || person.photos.firstDateTaken == null) ? null: person.photos.firstDateTaken._content; }
    public String getPhotosFirstDate() { return (person == null || person.photos == null || person.photos.firstDate == null) ? null: person.photos.firstDate._content; }
    public Integer getPhotosCount() { return (person == null || person.photos == null || person.photos.count == null) ? null: person.photos.count._content; }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("_Person{");
        sb.append("nsid='").append(getNsid()).append('\'');
        sb.append(", isPro=").append(isPro());
        sb.append(", iconServer='").append(getIconServer()).append('\'');
        sb.append(", iconFarm='").append(getIconFarm()).append('\'');
        sb.append(", pathAlias='").append(getPathAlias()).append('\'');
        sb.append(", gender='").append(getGender()).append('\'');
        sb.append(", isIgnored=").append(isIgnored());
        sb.append(", isContact=").append(isContact());
        sb.append(", isFriend=").append(isFriend());
        sb.append(", isFamily=").append(isFamily());
        sb.append(", isRevContact=").append(isRevContact());
        sb.append(", isRevFriend=").append(isRevFriend());
        sb.append(", isRevFamily=").append(isRevFamily());
        sb.append(", username='").append(getUsername()).append('\'');
        sb.append(", realname='").append(getRealname()).append('\'');
        sb.append(", location='").append(getLocation()).append('\'');
        sb.append(", timezoneLabel='").append(getTimezoneLabel()).append('\'');
        sb.append(", timezoneOffset='").append(getTimezoneOffset()).append('\'');
        sb.append(", description='").append(getDescription()).append('\'');
        sb.append(", photosUrl='").append(getPhotosUrl()).append('\'');
        sb.append(", profileUrl='").append(getProfileUrl()).append('\'');
        sb.append(", mobileUrl='").append(getMobileUrl()).append('\'');
        sb.append(", photosFirstDateTaken='").append(getPhotosFirstDateTaken()).append('\'');
        sb.append(", photosFirstDate='").append(getPhotosFirstDate()).append('\'');
        sb.append(", photosCount=").append(getPhotosCount());
        sb.append('}');
        return sb.toString();
    }


    private _Person person;

    private class _Person implements Serializable {
        private static final long serialVersionUID = 4519415882694261818L;
        private String nsid;
        @SerializedName("ispro")
        private String isPro;   // return as Boolean
        @SerializedName("iconserver")
        private String iconServer;
        @SerializedName("iconfarm")
        private String iconFarm;
        @SerializedName("path_alias")
        private String pathAlias;
        private String gender;
        private String ignored;     // return as Boolean
        private String contact;     // return as Boolean
        private String friend;     // return as Boolean
        private String family;     // return as Boolean
        @SerializedName("revcontact")
        private String revContact;  // return as boolean
        @SerializedName("revfriend")
        private String revFriend;  // return as boolean
        @SerializedName("revfamily")
        private String revFamily;  // return as boolean
        private _Username username;
        private _Realname realname;
        private _Location location;
        private _Timezone timezone;
        private _Description description;
        @SerializedName("photosurl")
        private _PhotosUrl photosUrl;
        @SerializedName("profileurl")
        private _ProfileUrl profileUrl;
        @SerializedName("mobileurl")
        private _MobileUrl mobileUrl;
        private _Photos photos;

        private class _Username implements Serializable {
            private static final long serialVersionUID = -4962785991591923267L;
            private String _content;
        }
        private class _Realname implements Serializable {
            private static final long serialVersionUID = -3753917650915658609L;
            private String _content;
        }
        private class _Location implements Serializable {
            private static final long serialVersionUID = -6511431223188574807L;
            private String _content;
        }
        private class _Timezone implements Serializable {
            private static final long serialVersionUID = -1323959781075749917L;
            private String label;
            private String offset;
        }
        private class _Description implements Serializable {
            private static final long serialVersionUID = -4328137586125207792L;
            private String _content;
        }
        private class _PhotosUrl implements Serializable {
            private static final long serialVersionUID = 4946371380306305284L;
            private String _content;
        }
        private class _ProfileUrl implements Serializable {
            private static final long serialVersionUID = -8286155218628777125L;
            private String _content;
        }
        private class _MobileUrl implements Serializable {
            private static final long serialVersionUID = -6563790748940002702L;
            private String _content;
        }
        private class _Photos implements Serializable {
            private static final long serialVersionUID = 6318487198552681184L;
            @SerializedName("firstdatetaken")
            private _FirstDateTaken firstDateTaken;
            @SerializedName("firstdate")
            private _FirstDate firstDate;
            private _Count count;
        }
        private class _FirstDateTaken implements Serializable {
            private static final long serialVersionUID = 8629085431856229683L;
            private String _content;
        }
        private class _FirstDate implements Serializable {
            private static final long serialVersionUID = 7262374415639703225L;
            private String _content;
        }
        private class _Count implements Serializable {
            private static final long serialVersionUID = 1944679771042594428L;
            private Integer _content;
        }
    }
}
