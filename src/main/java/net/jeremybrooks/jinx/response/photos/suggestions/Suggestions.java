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

package net.jeremybrooks.jinx.response.photos.suggestions;

import com.google.gson.annotations.SerializedName;
import net.jeremybrooks.jinx.response.Response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jeremyb on 7/22/14.
 */
public class Suggestions extends Response {

    private static final long serialVersionUID = -1878580938455445954L;

    public Integer getTotal() { return suggestions == null ? null : suggestions.total; }
    public Integer getPerPage() { return suggestions == null ? null : suggestions.perPage; }
    public Integer getPage() { return suggestions == null ? null : suggestions.page; }
    public List<Suggestion> getSuggestionList() { return suggestions == null ? null : suggestions.suggestionList; }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Suggestions{");
        sb.append("total=").append(getTotal());
        sb.append(", perPage=").append(getPerPage());
        sb.append(", page=").append(getPage());
        sb.append(", suggestionList=").append(getSuggestionList() == null ? "null" : "[" + getSuggestionList().size() + " items]");
        sb.append('}');
        return sb.toString();
    }

    public class Suggestion implements Serializable {
        private static final long serialVersionUID = 3259207553220261083L;
        @SerializedName("id")
        private String suggestionId;
        @SerializedName("photo_id")
        private String photoId;
        @SerializedName("date_suggested")
        private String dateSuggested;
        @SerializedName("suggested_by")
        private _SuggestedBy suggestedBy;
        private _Note note;
        private _Location location;

        public String getSuggestionId() {
            return suggestionId;
        }

        public String getPhotoId() {
            return photoId;
        }

        public String getDateSuggested() {
            return dateSuggested;
        }

        public String getSuggestedByUserId() { return suggestedBy == null ? null : suggestedBy.userId; }
        public String getSuggestedByUsername() { return suggestedBy == null ? null : suggestedBy.username; }
        public String getNote() { return note == null ? null : note.note; }

        public String getWoeId() { return location == null ? null : location.woeid;}
        public Float getLatitude() { return location == null ? null : location.latitude;}
        public Float getLongitude() { return location == null ? null : location.longitude;}
        public Integer getAccuracy() {return location == null ? null : location.accuracy;}

        public String getNeighbourhoodName() {return (location == null || location.neighbourhood == null) ? null : location.neighbourhood._content;}
        public String getNeighbourhoodPlaceId() {return (location == null || location.neighbourhood == null) ? null : location.neighbourhood.place_id;}
        public String getNeighbourhoodWoeId() {return (location == null || location.neighbourhood == null) ? null : location.neighbourhood.woeid;}

        public String getLocalityName() {return (location == null || location.locality == null) ? null : location.locality._content;}
        public String getLocalityPlaceId() {return (location == null || location.locality == null) ? null : location.locality.place_id;}
        public String getLocalityWoeId() {return (location == null || location.locality == null) ? null : location.locality.woeid;}

        public String getCountyName() {return (location == null || location.county == null) ? null : location.county._content;}
        public String getCountyPlaceId() {return (location == null || location.county == null) ? null : location.county.place_id;}
        public String getCountyWoeId() {return (location == null || location.county == null) ? null : location.county.woeid;}

        public String getRegionName() {return (location == null || location.region == null) ? null : location.region._content;}
        public String getRegionPlaceId() {return (location == null || location.region == null) ? null : location.region.place_id;}
        public String getRegionWoeId() {return (location == null || location.region == null) ? null : location.region.woeid;}

        public String getCountryName() {return (location == null || location.country == null) ? null : location.country._content;}
        public String getCountryPlaceId() {return (location == null || location.country == null) ? null : location.country.place_id;}
        public String getCountryWoeId() {return (location == null || location.country == null) ? null : location.country.woeid;}



        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Suggestion{");
            sb.append("suggestionId='").append(suggestionId).append('\'');
            sb.append(", photoId='").append(photoId).append('\'');
            sb.append(", dateSuggested='").append(dateSuggested).append('\'');
            sb.append(", suggestedBy=").append(suggestedBy);
            sb.append(", note=").append(note);

            sb.append(", woeId=").append(getWoeId()).append('\'');
            sb.append(", latitude=").append(getLatitude());
            sb.append(", longitude=").append(getLongitude());
            sb.append(", accuracy=").append(getAccuracy());
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
    }

    private _Suggestions suggestions;
    private class _Suggestions implements Serializable {
        private static final long serialVersionUID = 3758641104254735733L;
        private Integer total;
        @SerializedName("per_page")
        private Integer perPage;
        private Integer page;
        @SerializedName("suggestion")
        private List<Suggestion> suggestionList;
    }


    private class _SuggestedBy implements Serializable {
        private static final long serialVersionUID = -2668196137259058278L;
        @SerializedName("nsid")
        private String userId;
        private String username;
    }
    private class _Note implements Serializable {
        private static final long serialVersionUID = -6552445880445400093L;
        @SerializedName("_content")
        private String note;
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
