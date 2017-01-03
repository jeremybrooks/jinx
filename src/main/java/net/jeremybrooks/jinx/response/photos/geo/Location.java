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

package net.jeremybrooks.jinx.response.photos.geo;

import net.jeremybrooks.jinx.response.Response;

import java.io.Serializable;

/**
 * Created by jeremyb on 7/18/14.
 */
public class Location extends Response {


    private static final long serialVersionUID = 5861112301722371193L;

    public String getPhotoId() { return photo == null ? null : photo.id; }
    public Float getLatitude() { return (photo == null || photo.location == null) ? null : photo.location.latitude; }
    public Float getLongitude() { return (photo == null || photo.location == null) ? null : photo.location.longitude; }
    public Integer getAccuracy() { return (photo == null || photo.location == null) ? null : photo.location.accuracy; }
    public Integer getContext() { return (photo == null || photo.location == null) ? null : photo.location.context; }
    public String getPlaceId() { return (photo == null || photo.location == null) ? null : photo.location.place_id; }
    public String getWoeId() { return (photo == null || photo.location == null) ? null : photo.location.woeid; }
    public String getNeighbourhoodName() {return (photo == null || photo.location == null || photo.location.neighbourhood == null) ? null : photo.location.neighbourhood._content;}
    public String getNeighbourhoodPlaceId() {return (photo == null || photo.location == null || photo.location.neighbourhood == null) ? null : photo.location.neighbourhood.place_id;}
    public String getNeighbourhoodWoeId() {return (photo == null || photo.location == null || photo.location.neighbourhood == null) ? null : photo.location.neighbourhood.woeid;}

    public String getLocalityName() {return (photo == null || photo.location == null || photo.location.locality == null) ? null : photo.location.locality._content;}
    public String getLocalityPlaceId() {return (photo == null || photo.location == null || photo.location.locality == null) ? null : photo.location.locality.place_id;}
    public String getLocalityWoeId() {return (photo == null || photo.location == null || photo.location.locality == null) ? null : photo.location.locality.woeid;}

    public String getCountyName() {return (photo == null || photo.location == null || photo.location.county == null) ? null : photo.location.county._content;}
    public String getCountyPlaceId() {return (photo == null || photo.location == null || photo.location.county == null) ? null : photo.location.county.place_id;}
    public String getCountyWoeId() {return (photo == null || photo.location == null || photo.location.county == null) ? null : photo.location.county.woeid;}

    public String getRegionName() {return (photo == null || photo.location == null || photo.location.region == null) ? null : photo.location.region._content;}
    public String getRegionPlaceId() {return (photo == null || photo.location == null || photo.location.region == null) ? null : photo.location.region.place_id;}
    public String getRegionWoeId() {return (photo == null || photo.location == null || photo.location.region == null) ? null : photo.location.region.woeid;}

    public String getCountryName() {return (photo == null || photo.location == null || photo.location.country == null) ? null : photo.location.country._content;}
    public String getCountryPlaceId() {return (photo == null || photo.location == null || photo.location.country == null) ? null : photo.location.country.place_id;}
    public String getCountryWoeId() {return (photo == null || photo.location == null || photo.location.country == null) ? null : photo.location.country.woeid;}

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Location{");
        sb.append("photoId='").append(getPhotoId()).append('\'');
        sb.append(", longitude=").append(getLongitude());
        sb.append(", latitude=").append(getLatitude());
        sb.append(", accuracy=").append(getAccuracy());
        sb.append(", context=").append(getContext());
        sb.append(", placeId='").append(getPlaceId()).append('\'');
        sb.append(", woeId='").append(getWoeId()).append('\'');
        sb.append(", neighbourhoodName='").append(getNeighbourhoodName()).append('\'');
        sb.append(", neighbourhoodPlaceId='").append(getNeighbourhoodPlaceId()).append('\'');
        sb.append(", neighbourhoodWoeId='").append(getNeighbourhoodWoeId()).append('\'');

        sb.append(", localityName='").append(getLocalityName()).append('\'');
        sb.append(", localityPlaceId='").append(getLocalityPlaceId()).append('\'');
        sb.append(", localityWoeId='").append(getLocalityWoeId()).append('\'');

        sb.append(", countyName='").append(getCountyName()).append('\'');
        sb.append(", countyPlaceId='").append(getCountyPlaceId()).append('\'');
        sb.append(", countyWoeId='").append(getCountyWoeId()).append('\'');

        sb.append(", regionName='").append(getRegionName()).append('\'');
        sb.append(", regionPlaceId='").append(getRegionPlaceId()).append('\'');
        sb.append(", regionWoeId='").append(getRegionWoeId()).append('\'');

        sb.append(", countryName='").append(getCountryName()).append('\'');
        sb.append(", countryPlaceId='").append(getCountryPlaceId()).append('\'');
        sb.append(", countryWoeId='").append(getCountryWoeId()).append('\'');
        sb.append('}');
        return sb.toString();
    }

    private _Photo photo;
    private class _Photo implements Serializable {
        private static final long serialVersionUID = -2020088675328173817L;
        private String id;
        private _Location location;
    }
    private class _Location implements Serializable {
        private static final long serialVersionUID = 7509519687862891683L;
        private Float latitude;
        private Float longitude;
        private Integer accuracy;
        private Integer context;
        private String place_id;
        private String woeid;
        private _Neighbourhood neighbourhood;
        private _Locality locality;
        private _County county;
        private _Region region;
        private _Country country;
    }
    private class _Neighbourhood implements Serializable {
        private static final long serialVersionUID = 8488130604075291881L;
        private String _content;
        private String place_id;
        private String woeid;
    }

    private class _Locality implements Serializable {
        private static final long serialVersionUID = 3585806709690746612L;
        private String _content;
        private String place_id;
        private String woeid;
    }

    private class _County implements Serializable {
        private static final long serialVersionUID = 7688901182983909086L;
        private String _content;
        private String place_id;
        private String woeid;
    }

    private class _Region implements Serializable {
        private static final long serialVersionUID = 5644664845000616400L;
        private String _content;
        private String place_id;
        private String woeid;
    }

    private class _Country implements Serializable {
        private static final long serialVersionUID = 2704863703180040432L;
        private String _content;
        private String place_id;
        private String woeid;
    }
}
