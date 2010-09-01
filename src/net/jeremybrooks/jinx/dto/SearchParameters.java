/*
 * Jinx is Copyright 2010 by Jeremy Brooks
 *
 * This file is part of Jinx.
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
package net.jeremybrooks.jinx.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * This class defines the parameters for a search.
 *
 * Many of the values that define parameters are in the JinxConstants class.
 * 
 * @author jeremyb
 */
public class SearchParameters implements Serializable {

    

    
    /*
     * The NSID of the user who's photo to search.
     * If this parameter isn't passed then everybody's public photos will be
     * searched. A value of "me" will search against the calling user's photos
     * for authenticated calls.
     */
    private String userId;


    /*
     * A comma-delimited list of tags.
     * Photos with one or more of the tags listed will be returned.
     * You can exclude results that match a term by prepending it with
     * a - character.
     */
    private String tags;


    /* 
     * Either TAG_MODE_ANY for an OR combination of tags, or TAG_MODE_ALL for an
     * AND combination. Defaults to TAG_MODE_ANY if not specified.
     */
    private String tagMode;


    /*
     * A free text search.
     * Photos who's title, description or tags contain the text will be
     * returned. You can exclude results that match a term by prepending
     * it with a - character.
     */
    private String text;


    /*
     * Minimum upload date.
     * Photos with an upload date greater than or equal to this value will
     * be returned. The date should be in the form of a unix timestamp.
     */
    private Date minUploadDate;


    /*
     * Maximum upload date. 
     * Photos with an upload date less than or equal to this value will be 
     * returned. The date should be in the form of a unix timestamp.
     */
    private Date maxUploadDate;


    /*
     * Minimum taken date.
     * Photos with an taken date greater than or equal to this value will be
     * returned. The date should be in the form of a mysql datetime.
     */
    private Date minTakenDate;

    /*
     * Maximum taken date. 
     * Photos with an taken date less than or equal to this value will be 
     * returned. The date should be in the form of a mysql datetime.
     */
    private Date maxTakenDate;


    /*
     * The license id for photos
     * (for possible values see the flickr.photos.licenses.getInfo method).
     * Multiple licenses may be comma-separated.
     */
    private String license;

    /*
     * The order in which to sort returned photos.
     * Deafults to date-posted-desc (unless you are doing a radial geo query,
     * in which case the default sorting is by ascending distance from the point
     * specified). The possible values are: date-posted-asc, date-posted-desc,
     * date-taken-asc, date-taken-desc, interestingness-desc,
     * interestingness-asc, and relevance.
     */
    private String sort;

    /*
     * Return photos only matching a certain privacy level. 
     * This only applies when making an authenticated call to view photos you 
     * own. Valid values are:
     * <ul>
     * <li> PRIVACY_PUBLIC public photos</li>
     * <li> PRIVACY_FRIENDS private photos visible to friends</li>
     * <li> PRIVACY_FAMILY private photos visible to family</li>
     * <li> PRIVACY_FRIENDS_FAMILY private photos visible to friends & family</li>
     * <li> PRIVACY_PRIVATE completely private photos</li>
     * </ul>
     */
    private String privacyFilter;

    /*
     * A comma-delimited list of 4 values defining the Bounding Box of the area 
     * that will be searched. 
     * 
     * The 4 values represent the bottom-left corner of the box and the top-right corner, minimum_longitude, minimum_latitude, maximum_longitude, maximum_latitude. 
     * Longitude has a range of -180 to 180 , latitude of -90 to 90. Defaults 
     * to -180, -90, 180, 90 if not specified. 
     * 
     * Unlike standard photo queries, geo (or bounding box) queries will only 
     * return 250 results per page. 
     * 
     * Geo queries require some sort of limiting agent in order to prevent the
     * database from crying. This is basically like the check against
     * "parameterless searches" for queries without a geo component. 
     *
     * A tag, for instance, is considered a limiting agent as are user defined 
     * min_date_taken and min_date_upload parameters — If no limiting factor is
     * passed we return only photos added in the last 12 hours (though we may
     * extend the limit in the future).
     */
    private String bbox;


    /*
     * Recorded accuracy level of the location information.
     * Current range is 1-16 :
     * World level is 1
     * Country is ~3
     * Region is ~6
     * City is ~11
     * Street is ~16
     *
     * Defaults to maximum value if not specified.
     */
    private String accuracy;


    /*
     * Safe Search settings.
     *
     * SAFE_SEARCH_SAFE
     * SAFE_SEARCH_MODERATE
     * SAFE_SEARCH_RESTRICTED
     */
    private String safeSearch;


    /*
     * Content Type setting:
     * 
     * CONTENT_PHOTOS for photos only.
     * CONTENT_SCREENSHOTS for screenshots only.
     * CONTENT_OTHER for 'other' only.
     * CONTENT_PHOTOS_SCREENSHOTS for photos and screenshots.
     * CONTENT_SCREENSHOTS_OTHER for screenshots and 'other'.
     * CONTENT_PHOTOS_OTHERfor photos and 'other'.
     * CONTENT_ALL for photos, screenshots, and 'other' (all).
     */
    private String contentType;


    /*
     * Aside from passing in a fully formed machine tag, there is a special
     * syntax for searching on specific properties :
     *
     * <ul>
     * <li>Find photos using the 'dc' namespace : "machine_tags" => "dc:"</li>
     * <li>Find photos with a title in the 'dc' namespace : "machine_tags" => "dc:title="</li>
     * <li>Find photos titled "mr. camera" in the 'dc' namespace : "machine_tags" => "dc:title=\"mr. camera\"</li>
     * <li>Find photos whose value is "mr. camera" : "machine_tags" => "*:*=\"mr. camera\""</li>
     * <li>Find photos that have a title, in any namespace : "machine_tags" => "*:title="</li>
     * <li>Find photos that have a title, in any namespace, whose value is "mr. camera" : "machine_tags" => "*:title=\"mr. camera\""</li>
     * <li>Find photos, in the 'dc' namespace whose value is "mr. camera" : "machine_tags" => "dc:*=\"mr. camera\""</li>
     * </ul>
     *
     * Multiple machine tags may be queried by passing a comma-separated list.
     * The number of machine tags you can pass in a single query depends on the
     * tag mode (AND or OR) that you are querying with. "AND" queries are
     * limited to (16) machine tags. "OR" queries are limited to (8).
     */
    private String machineTags;


    /*
     * Either TAG_MODE_ANY for an OR combination of tags, or
     * TAG_MODE_ALL for an AND combination.
     *
     * Defaults to TAG_MODE_ANY if not specified.
     */
    private String machineTagMode;


    /*
     * The id of a group who's pool to search.
     * If specified, only matching photos posted to the group's pool
     * will be returned.
     */
    private String groupId;


    /*
     * Search your contacts.
     *
     * Either CONTACTS_ALL or CONTACTS_FF for just friends and family. (Experimental)
     */
    private String contacts;

    /*
     * A 32-bit identifier that uniquely represents spatial entities.
     * (not used if bbox argument is present).
     *
     * Geo queries require some sort of limiting agent in order to prevent the
     * database from crying. This is basically like the check against
     * "parameterless searches" for queries without a geo component.
     *
     * A tag, for instance, is considered a limiting agent as are user defined
     * min_date_taken and min_date_upload parameters &emdash; If no limiting
     * factor is passed we return only photos added in the last 12 hours
     * (though we may extend the limit in the future).
     */
    private String woeId;

    /*
     * A Jinx place id. (not used if bbox argument is present).
     *
     * Geo queries require some sort of limiting agent in order to prevent the
     * database from crying. This is basically like the check against
     * "parameterless searches" for queries without a geo component.
     *
     * A tag, for instance, is considered a limiting agent as are user defined
     * min_date_taken and min_date_upload parameters &emdash; If no limiting
     * factor is passed we return only photos added in the last 12 hours
     * (though we may extend the limit in the future).
     */
    private String placeId;

    /*
     * Filter results by media type.
     * Possible values are MEDIA_ALL (default), MEDIA_PHOTOS or MEDIA_VIDEOS.
     */
    private String media;

    /*
     * Any photo that has been geotagged, or if the value is "0" any photo that
     * has not been geotagged.
     *
     * Geo queries require some sort of limiting agent in order to prevent the
     * database from crying. This is basically like the check against
     * "parameterless searches" for queries without a geo component.
     *
     * A tag, for instance, is considered a limiting agent as are user defined
     * min_date_taken and min_date_upload parameters &emdash; If no limiting
     * factor is passed we return only photos added in the last 12 hours
     * (though we may extend the limit in the future).
     */
    private String hasGeo;

    /*
     * Geo context is a numeric value representing the photo's geotagginess
     * beyond latitude and longitude.
     *
     * For example, you may wish to search for photos that were taken
     * "indoors" or "outdoors".
     *
     * The current list of context IDs is :
     *
     * GEO_CONTEXT_NOT_DEFINED
     * GEO_CONTEXT_INDOORS
     * GEO_CONTEXT_OUTDOORS
     *
     * Geo queries require some sort of limiting agent in order to prevent the
     * database from crying. This is basically like the check against
     * "parameterless searches" for queries without a geo component.
     *
     * A tag, for instance, is considered a limiting agent as are user defined
     * min_date_taken and min_date_upload parameters &emdash; If no limiting
     * factor is passed we return only photos added in the last 12 hours
     * (though we may extend the limit in the future).
     */
    private String geoContext;

    /*
     * A valid latitude, in decimal format, for doing radial geo queries.
     *
     * Geo queries require some sort of limiting agent in order to prevent the
     * database from crying. This is basically like the check against
     * "parameterless searches" for queries without a geo component.
     *
     * A tag, for instance, is considered a limiting agent as are user defined
     * min_date_taken and min_date_upload parameters &emdash; If no limiting
     * factor is passed we return only photos added in the last 12 hours
     * (though we may extend the limit in the future).
     */
    private String lat;

    /*
     * A valid longitude, in decimal format, for doing radial geo queries.
     *
     * Geo queries require some sort of limiting agent in order to prevent the
     * database from crying. This is basically like the check against
     * "parameterless searches" for queries without a geo component.
     *
     * A tag, for instance, is considered a limiting agent as are user defined
     * min_date_taken and min_date_upload parameters &emdash; If no limiting
     * factor is passed we return only photos added in the last 12 hours
     * (though we may extend the limit in the future).
     */
    private String lon;

    /*
     * A valid radius used for geo queries, greater than zero and less than 20
     * miles (or 32 kilometers), for use with point-based geo queries.
     * The default value is 5 (km).
     */
    private String radius;

    /*
     * The unit of measure when doing radial geo queries.
     * Valid options are RADIUS_UNITS_MILES and RADIUS_UNITS_KILOMETERS.
     * The default is RADIUS_UNITS_KILOMETERS.
     */
    private String radiusUnits;

    /*
     * Limit the scope of the search to only photos that are part of the Jinx
     * Commons project. Default is false.
     */
    private boolean isCommons;

    /*
     * Limit the scope of the search to only photos that are in a gallery?
     * Default is false, search all photos.
     */
    private boolean inGallery;

    /*
     * Limit the scope of the search to only photos that are for sale on Getty.
     * Default is false.
     */
    private boolean isGetty;


    
    /*
     * A comma-delimited list of extra information to fetch for each returned
     * record. Currently supported fields are:
     *
     * <ul>
     * <li>EXTRAS_DESCRIPTION</li>
     * <li>EXTRAS_LICENSE</li>
     * <li>EXTRAS_DATE_UPLOAD</li>
     * <li>EXTRAS_DATE_TAKEN</li>
     * <li>EXTRAS_OWNER_NAME</li>
     * <li>EXTRAS_ICON_SERVER</li>
     * <li>EXTRAS_ORIGINAL_FORMAT</li>
     * <li>EXTRAS_LAST_UPDATE</li>
     * <li>EXTRAS_GEO</li>
     * <li>EXTRAS_TAGS</li>
     * <li>EXTRAS_MACHINE_TAGS</li>
     * <li>EXTRAS_O_DIMS</li>
     * <li>EXTRAS_VIEWS</li>
     * <li>EXTRAS_MEDIA</li>
     * <li>EXTRAS_PATH_ALIAS</li>
     * <li>EXTRAS_URL_SQ</li>
     * <li>EXTRAS_URL_T</li>
     * <li>EXTRAS_URL_S</li>
     * <li>EXTRAS_URL_M</li>
     * <li>EXTRAS_URL_O</li>
     * </ul>
     */
    private String extras;

    /*
     * Number of photos to return per page.
     * If this argument is omitted, it defaults to 100. The maximum allowed
     * value is 500.
     */
    private int perPage;

    /*
     * The page of results to return.
     * If this argument is omitted, it defaults to 1.
     */
    private int page;





    /**
     * Create a new search parameters object.
     */
    public SearchParameters() {
    }

    /**
     * Create a new search parameters object for the specified user.
     *
     * @param token indicates which users photos will be searched.
     */
    public SearchParameters(Token token) {
	this.userId = token.getNsid();
    }



    /*
     * This is the response with no extras:
     <?xml version="1.0" encoding="utf-8" ?>
     <rsp stat="ok">
       <photos page="1" pages="1" perpage="100" total="1">
         <photo id="4566081682" owner="85853333@N00"
                secret="e8b4c41f48" server="3475" farm="4"
                title="Can't Slow Down Even If We Try" ispublic="1"
                isfriend="0" isfamily="0" />
       </photos>
     </rsp>
     *
     * This is the response with all extras:
     <?xml version="1.0" encoding="utf-8" ?>
     <rsp stat="ok">
	<photos page="1" pages="1" perpage="100" total="1">
	    <photo id="4566081682" owner="85853333@N00" secret="e8b4c41f48"
		   server="3475" farm="4" title="Can't Slow Down Even If We Try"
		   ispublic="1" isfriend="0" isfamily="0" datetaken="2010-03-12 17:52:49"
		   datetakengranularity="0" dateupload="1272645529" latitude="0" longitude="0"
		   accuracy="0" iconserver="128" iconfarm="1" lastupdate="1274307055" license="2"
		   machine_tags="" media="photo" media_status="ready" originalsecret="61b309de7d"
		   originalformat="jpg" ownername="Jeremy Brooks"
		   o_width="5061" o_height="3374"
		   pathalias="jeremybrooks" tags="sanfrancisco california food usa coconut testimage bakery photowalk missiondistrict themission sanfranciscocounty photowalking dspw032010"
		   url_m="http://farm4.static.flickr.com/3475/4566081682_e8b4c41f48.jpg"
		   height_m="333" width_m="500"
		   url_o="http://farm4.static.flickr.com/3475/4566081682_61b309de7d_o.jpg"
		   height_o="3374" width_o="5061"
		   url_s="http://farm4.static.flickr.com/3475/4566081682_e8b4c41f48_m.jpg"
		   height_s="160" width_s="240"
		   url_sq="http://farm4.static.flickr.com/3475/4566081682_e8b4c41f48_s.jpg"
		   height_sq="75" width_sq="75"
		   url_t="http://farm4.static.flickr.com/3475/4566081682_e8b4c41f48_t.jpg"
		   height_t="67" width_t="100" views="27">
		<description>Coconut goodies in a bakery window in the Mission.</description>
	    </photo>
	</photos>
     </rsp>



     */
    



    /**
     * @return the userId
     */
    public String getUserId() {
	return userId;
    }


    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
	this.userId = userId;
    }


    /**
     * @return the tags
     */
    public String getTags() {
	return tags;
    }


    /**
     * @param tags comma delimited list of tags for this search.
     */
    public void setTags(String tags) {
	this.tags = tags;
    }


    /**
     * This list will be converted to a comma separated string.
     *
     * @param tags list of tags for this search.
     */
    public void	setTags(List<String> tags) {
	StringBuilder sb = new StringBuilder();
	if (tags != null) {
	    for (String tag : tags) {
		sb.append(tag.trim()).append(',');
	    }

	    if (sb.length() > 0) {
		sb.deleteCharAt(sb.length() - 1);
	    }
	}

	this.tags = sb.toString();
    }


    /**
     * @return the tagMode
     */
    public String getTagMode() {
	return tagMode;
    }


    /**
     * @param tagMode the tagMode to set
     */
    public void setTagMode(String tagMode) {
	this.tagMode = tagMode;
    }


    /**
     * @return the text
     */
    public String getText() {
	return text;
    }


    /**
     * @param text the text to set
     */
    public void setText(String text) {
	this.text = text;
    }


    /**
     * @return the minUploadDate
     */
    public Date getMinUploadDate() {
	return minUploadDate;
    }


    /**
     * @param minUploadDate the minUploadDate to set
     */
    public void setMinUploadDate(Date minUploadDate) {
	this.minUploadDate = minUploadDate;
    }


    /**
     * @return the maxUploadDate
     */
    public Date getMaxUploadDate() {
	return maxUploadDate;
    }


    /**
     * @param maxUploadDate the maxUploadDate to set
     */
    public void setMaxUploadDate(Date maxUploadDate) {
	this.maxUploadDate = maxUploadDate;
    }


    /**
     * @return the minTakenDate
     */
    public Date getMinTakenDate() {
	return minTakenDate;
    }


    /**
     * @param minTakenDate the minTakenDate to set
     */
    public void setMinTakenDate(Date minTakenDate) {
	this.minTakenDate = minTakenDate;
    }


    /**
     * @return the maxTakenDate
     */
    public Date getMaxTakenDate() {
	return maxTakenDate;
    }


    /**
     * @param maxTakenDate the maxTakenDate to set
     */
    public void setMaxTakenDate(Date maxTakenDate) {
	this.maxTakenDate = maxTakenDate;
    }


    /**
     * @return the license
     */
    public String getLicense() {
	return license;
    }


    /**
     * @param license the license to set
     */
    public void setLicense(String license) {
	this.license = license;
    }


    /**
     * @return the sort
     */
    public String getSort() {
	return sort;
    }

    
    /**
     * Set sort order for the returned results.
     *
     * Possible values are:
     * <ul>
     * <li>JinxConstants.SORT_DATE_POSTED_ASC</li>
     * <li>JinxConstants.SORT_DATE_POSTED_DESC</li>
     * <li>JinxConstants.SORT_DATE_TAKEN_ASC</li>
     * <li>JinxConstants.SORT_DATE_TAKEN_DESC</li>
     * <li>JinxConstants.SORT_INTERESTINGNESS_ASC</li>
     * <li>JinxConstants.SORT_INTERESTINGNESS_DESC</li>
     * <li>JinxConstants.SORT_RELEVANCE</li>
     * </ul>
     * @param sort the sort to set
     */
    public void setSort(String sort) {
	this.sort = sort;
    }


    /**
     * @return the privacyFilter
     */
    public String getPrivacyFilter() {
	return privacyFilter;
    }


    /**
     * Set the privacy level of photos to be returned.
     *
     * Possible values are:
     * <ul>
     * <li>JinxConstants.PRIVACY_PUBLIC - public photos</li>
     * <li>JinxConstants.PRIVACY_FRIENDS - private photos visible to friends</li>
     * <li>JinxConstants.PRIVACY_FAMILY - private photos visible to family</li>
     * <li>JinxConstants.PRIVACY_FRIENDS_FAMILY - private photos visible to friends & family</li>
     * <li>JinxConstants.PRIVACY_PRIVATE - completely private photos</li>
     * </ul>
     * @param privacyFilter the privacyFilter to set
     */
    public void setPrivacyFilter(String privacyFilter) {
	this.privacyFilter = privacyFilter;
    }


    /**
     * @return the bbox
     */
    public String getBbox() {
	return bbox;
    }


    /**
     * @param bbox the bbox to set
     */
    public void setBbox(String bbox) {
	this.bbox = bbox;
    }


    /**
     * @return the accuracy
     */
    public String getAccuracy() {
	return accuracy;
    }


    /**
     * @param accuracy the accuracy to set
     */
    public void setAccuracy(String accuracy) {
	this.accuracy = accuracy;
    }


    /**
     * @return the safeSearch
     */
    public String getSafeSearch() {
	return safeSearch;
    }


    /**
     * Safe search setting.
     *
     * Possible values are:
     * <ul>
     * <li>JinxConstants.SAFE_SEARCH_SAFE for safe.</li>
     * <li>JinxConstants.SAFE_SEARCH_MODERATE for moderate.</li>
     * <li>JinxConstants.SAFE_SEARCH_RESTRICTED for restricted.</li>
     * </ul>
     *
     * (Please note: Un-authed calls can only see Safe content.)
     *
     * NOTE: There is a bug in the Flickr API; this setting seems to be
     *       ignored. Reported here:
     *       http://www.flickr.com/groups/api/discuss/72157624206809677/?search=safe_search
     * 
     * @param safeSearch the safeSearch to set
     */
    public void setSafeSearch(String safeSearch) {
	this.safeSearch = safeSearch;
    }


    /**
     * @return the contentType
     */
    public String getContentType() {
	return contentType;
    }


    /**
     * @param contentType the contentType to set
     */
    public void setContentType(String contentType) {
	this.contentType = contentType;
    }


    /**
     * @return the machineTags
     */
    public String getMachineTags() {
	return machineTags;
    }


    /**
     * @param machineTags the machineTags to set
     */
    public void setMachineTags(String machineTags) {
	this.machineTags = machineTags;
    }


    /**
     * @return the machineTagMode
     */
    public String getMachineTagMode() {
	return machineTagMode;
    }


    /**
     * @param machineTagMode the machineTagMode to set
     */
    public void setMachineTagMode(String machineTagMode) {
	this.machineTagMode = machineTagMode;
    }


    /**
     * @return the groupId
     */
    public String getGroupId() {
	return groupId;
    }


    /**
     * @param groupId the groupId to set
     */
    public void setGroupId(String groupId) {
	this.groupId = groupId;
    }


    /**
     * @return the contacts
     */
    public String getContacts() {
	return contacts;
    }


    /**
     * @param contacts the contacts to set
     */
    public void setContacts(String contacts) {
	this.contacts = contacts;
    }


    /**
     * @return the woeId
     */
    public String getWoeId() {
	return woeId;
    }


    /**
     * @param woeId the woeId to set
     */
    public void setWoeId(String woeId) {
	this.woeId = woeId;
    }


    /**
     * @return the placeId
     */
    public String getPlaceId() {
	return placeId;
    }


    /**
     * @param placeId the placeId to set
     */
    public void setPlaceId(String placeId) {
	this.placeId = placeId;
    }


    /**
     * @return the media
     */
    public String getMedia() {
	return media;
    }


    /**
     * @param media the media to set
     */
    public void setMedia(String media) {
	this.media = media;
    }


    /**
     * @return the hasGeo
     */
    public String getHasGeo() {
	return hasGeo;
    }


    /**
     * @param hasGeo the hasGeo to set
     */
    public void setHasGeo(String hasGeo) {
	this.hasGeo = hasGeo;
    }


    /**
     * @return the geoContext
     */
    public String getGeoContext() {
	return geoContext;
    }


    /**
     * @param geoContext the geoContext to set
     */
    public void setGeoContext(String geoContext) {
	this.geoContext = geoContext;
    }


    /**
     * @return the lat
     */
    public String getLat() {
	return lat;
    }


    /**
     * @param lat the lat to set
     */
    public void setLat(String lat) {
	this.lat = lat;
    }


    /**
     * @return the lon
     */
    public String getLon() {
	return lon;
    }


    /**
     * @param lon the lon to set
     */
    public void setLon(String lon) {
	this.lon = lon;
    }


    /**
     * @return the radius
     */
    public String getRadius() {
	return radius;
    }


    /**
     * @param radius the radius to set
     */
    public void setRadius(String radius) {
	this.radius = radius;
    }


    /**
     * @return the radiusUnits
     */
    public String getRadiusUnits() {
	return radiusUnits;
    }


    /**
     * @param radiusUnits the radiusUnits to set
     */
    public void setRadiusUnits(String radiusUnits) {
	this.radiusUnits = radiusUnits;
    }


    /**
     * @return the isCommons
     */
    public boolean isIsCommons() {
	return isCommons;
    }


    /**
     * Note: If you do not include some additional parameters, such as tags,
     *       searches using this parameter may return incorrect results, such
     *       as an entire photoset. Bug reported to Flickr here:
     *       http://www.flickr.com/groups/api/discuss/72157613093793775/?search=is_commons
     * @param isCommons the isCommons to set
     */
    public void setIsCommons(boolean isCommons) {
	this.isCommons = isCommons;
    }


    /**
     * @return the inGallery
     */
    public boolean isInGallery() {
	return inGallery;
    }


    /**
     * @param inGallery the inGallery to set
     */
    public void setInGallery(boolean inGallery) {
	this.inGallery = inGallery;
    }


    /**
     * @return the isGetty
     */
    public boolean isIsGetty() {
	return isGetty;
    }


    /**
     * Note: If you do not include some additional parameters, such as tags,
     *       searches using this parameter may return incorrect results, such
     *       as an entire photoset. Bug reported to Flickr here:
     *       http://www.flickr.com/groups/api/discuss/72157622692290730/?search=is_getty
     * 
     * @param isGetty the isGetty to set
     */
    public void setIsGetty(boolean isGetty) {
	this.isGetty = isGetty;
    }


    /**
     * @return the extras
     */
    public String getExtras() {
	return extras;
    }


    /**
     * @param extras the extras to set
     */
    public void setExtras(String extras) {
	this.extras = extras;
    }


    /**
     * @return the perPage
     */
    public int getPerPage() {
	return perPage;
    }


    /**
     * @param perPage the perPage to set
     */
    public void setPerPage(int perPage) {
	this.perPage = perPage;
    }


    /**
     * @return the page
     */
    public int getPage() {
	return page;
    }


    /**
     * @param page the page to set
     */
    public void setPage(int page) {
	this.page = page;
    }

}
