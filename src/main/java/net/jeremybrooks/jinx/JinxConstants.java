/*
 * Jinx is Copyright 2010-2013 by Jeremy Brooks and Contributors
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
package net.jeremybrooks.jinx;


/**
 *
 * @author jeremyb
 */
public class JinxConstants {


	public static enum Method {
		POST,
		GET
	}

    public static final String REST_ENDPOINT = "https://api.flickr.com/services/rest/";
	public static final String OAUTH_REQUEST_TOKEN_ENDPOINT_URL = "https://www.flickr.com/services/oauth/request_token";
	public static final String OAUTH_ACCESS_TOKEN_ENDPOINT_URL = "https://www.flickr.com/services/oauth/access_token";
	public static final String OAUTH_AUTHORIZE_WEBSITE_URL = "https://www.flickr.com/services/oauth/authorize";


	public static final String UTF8 = "UTF-8";


	// ----------------------------------------------
	// move constants needed by new jinx above this line

	public static final String PERMS_READ = "read";
    public static final String PERMS_WRITE = "write";



    public static final String PERMS_DELETE = "delete";


    public static final String AUTH_ENDPOINT = "http://flickr.com/services/auth/";


    /** Constant for tag mode "any". */
    public static final String TAG_MODE_ANY = "any";

    /** Constant for tag mode "all". */
    public static final String TAG_MODE_ALL = "all";

    /** Constant for privacy level Public. */
    public static final String PRIVACY_PUBLIC = "1";

    /** Constant for privacy level Friends. */
    public static final String PRIVACY_FRIENDS = "2";

    /** Constant for privacy level Family. */
    public static final String PRIVACY_FAMILY = "3";

    /** Constant for privacy level Friends and Family. */
    public static final String PRIVACY_FRIENDS_FAMILY = "4";

    /** Constant for privacy level Private. */
    public static final String PRIVACY_PRIVATE = "5";

    /** Constant for Safe Search setting "Safe". */
    public static final String SAFE_SEARCH_SAFE = "1";

    /** Constant for Safe Search setting "Moderate". */
    public static final String SAFE_SEARCH_MODERATE = "2";

    /** Constant for Safe Search setting "Restricted". */
    public static final String SAFE_SEARCH_RESTRICTED = "3";


    /* Sorting. */

    /** Sort by date posted ascending. */
    public static final String SORT_DATE_POSTED_ASC = "date-posted-asc";

    /** Sort by date posted descending. */
    public static final String SORT_DATE_POSTED_DESC = "date-posted-desc";

    /** Sort by date taken ascending. */
    public static final String SORT_DATE_TAKEN_ASC = "date-taken-asc";

    /** Sort by date taken descending. */
    public static final String SORT_DATE_TAKEN_DESC = "date-taken-desc";

    /** Sort by interesingness ascending. */
    public static final String SORT_INTERESTINGNESS_ASC = "interestingness-asc";

    /** Sort by interesingness descending. */
    public static final String SORT_INTERESTINGNESS_DESC = "interestingness-desc";

    /** Sort by relevance. */
    public static final String SORT_RELEVANCE = "relevance";


    /** Constant for content "Photos Only". */
    public static final String CONTENT_PHOTOS = "1";
    /** Constant for content "Screenshots Only". */
    public static final String CONTENT_SCREENSHOTS = "2";
    /** Constant for content "Other Only". */
    public static final String CONTENT_OTHER = "3";
    /** Constant for content "Photos And Screenshots". */
    public static final String CONTENT_PHOTOS_SCREENSHOTS = "4";
    /** Constant for content "Screenshots And Other". */
    public static final String CONTENT_SCREENSHOTS_OTHER = "5";
    /** Constant for content "Photos And Other". */
    public static final String CONTENT_PHOTOS_OTHER = "6";
    /** Constant for content "Photos, Screenshots, and Other (all)". */
    public static final String CONTENT_ALL = "7";


    /** Constant for searching all contacts. */
    public static final String CONTACTS_ALL = "all";
    /** Constant for searching only friends and family. */
    public static final String CONTACTS_FF = "ff";

    /** Constant for media type "all". */
    public static final String MEDIA_ALL = "all";
    /** Constant for media type "Photos". */
    public static final String MEDIA_PHOTOS = "photos";
    /** Constant for media type "Videos". */
    public static final String MEDIA_VIDEOS = "videos";


    /** Constant for "has been geotagged" */
    public static final String HAS_GEOTAG = "1";
    /** Constants for "has not been geotagged" */
    public static final String HAS_NO_GEOTAG = "0";
    

    /** Constant for geo context "not defined". */
    public static final String GEO_CONTEXT_NOT_DEFINED = "0";
    /** Constant for geo context "indoors". */
    public static final String GEO_CONTEXT_INDOORS = "1";
    /** Constant for geo context "outdoors". */
    public static final String GEO_CONTEXT_OUTDOORS = "2";

    /** Constant for radius units in miles. */
    public static final String RADIUS_UNITS_MILES = "mi";
    /** Constant for radius units in kilometers. */
    public static final String RADIUS_UNITS_KILOMETERS = "km";

    /**
     * Constants for Extras.
     */

    public static final String EXTRAS_DESCRIPTION = "description";
    public static final String EXTRAS_LICENSE = "license";
    public static final String EXTRAS_DATE_UPLOAD = "date_upload";
    public static final String EXTRAS_DATE_TAKEN = "date_taken";
    public static final String EXTRAS_OWNER_NAME = "owner_name";
    public static final String EXTRAS_ICON_SERVER = "icon_server";
    public static final String EXTRAS_ORIGINAL_FORMAT = "original_format";
    public static final String EXTRAS_LAST_UPDATE = "last_update";
    public static final String EXTRAS_GEO = "geo";
    public static final String EXTRAS_TAGS = "tags";
    public static final String EXTRAS_MACHINE_TAGS = "machine_tags";
    public static final String EXTRAS_O_DIMS = "o_dims";
    public static final String EXTRAS_VIEWS = "views";
    public static final String EXTRAS_MEDIA = "media";
    public static final String EXTRAS_PATH_ALIAS = "path_alias";
    public static final String EXTRAS_URL_SQ = "url_sq";
    public static final String EXTRAS_URL_T = "url_t";
    public static final String EXTRAS_URL_S = "url_s";
    public static final String EXTRAS_URL_Q = "url_q";
    public static final String EXTRAS_URL_M = "url_m";
    public static final String EXTRAS_URL_N = "url_n";
    public static final String EXTRAS_URL_Z = "url_z";
    public static final String EXTRAS_URL_C = "url_c";
    public static final String EXTRAS_URL_L = "url_l";
    public static final String EXTRAS_URL_O = "url_o";
	public static final String EXTRAS_URL_H = "url_h";
	public static final String EXTRAS_URL_K = "url_k";
    public static final String EXTRAS_ALL = "description,license,date_upload,date_taken," +
	    "owner_name,icon_server,original_format,last_update,geo,tags," +
	    "machine_tags,o_dims,views,media,path_alias,url_sq,url_t,url_s," +
	    "url_q,url_m,url_n,url_z,url_c,url_l,url_o,url_h,url_k";



    public static final String DATE_TAKEN_GRANULARITY_DATE_TIME = "0";
    public static final String DATE_TAKEN_GRANULARITY_DATE = "4";
    public static final String DATE_TAKEN_GRANULARITY_YEAR = "6";
    public static final String DATE_TAKEN_GRANULARITY_CIRCA = "8";



    /** Who can add comments to the photo and its notes. */
     public static final String PERM_COMMENT_NOBODY = "0";
     public static final String PERM_COMMENT_FRIENDS_AND_FAMILY = "1";
     public static final String PERM_COMMENT_CONTACTS = "2";
     public static final String PERM_COMMENT_EVERYBODY = "3";

     /** Who can add notes and tags to the photo. */
     public static final String PERM_ADD_META_NOBODY = "0";
     public static final String PERM_ADD_META_FRIENDS_AND_FAMILY = "1";
     public static final String PERM_ADD_META_CONTACTS = "2";
     public static final String PERM_ADD_META_EVERYBODY = "3";

     /** Photo sizes. */
     /** Square, 75x75 */
     public static final int SIZE_SMALL_SQUARE = 0;

     /** Thumbnail, 100 on longest side */
     public static final int SIZE_THUMBNAIL = 1;

     /** Small, 240 on longest side */
     public static final int SIZE_SMALL = 2;

     /** Medium, 500 on longest side */
     public static final int SIZE_MEDIUM = 3;

     /** Medium 640, 640 on longest side */
     public static final int SIZE_MEDIUM_640 = 4;

     /** Large, 1024 on longest side */
     public static final int SIZE_LARGE = 5;

     /** Original image, either a jpg, gif or png, depending on source format */
     public static final int SIZE_ORIGINAL = 6;

    /** Large Square, 150x150 */
    public static final int SIZE_LARGE_SQUARE = 7;

    /** Small 320, 320 on longest side. */
    public static final int SIZE_SMALL_320 = 8;

    /** Medium 800, 800 on longest side. */
    public static final int SIZE_MEDIUM_800 = 9;

	/** Large 1600, 1600 on longest side. */
	public static final int SIZE_LARGE_1600 = 10;

	/** Large 2048, 2048 on longest side. */
	public static final int SIZE_LARGE_2048 = 11;




     public static enum GroupMemberType {
	 member,
	 moderator,
	 admin
     }
     
}
