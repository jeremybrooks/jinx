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

package net.jeremybrooks.jinx;


/**
 * @author jeremyb
 */
public class JinxConstants {

    /**
     * Constant for a boolean system property that can be set to make Jinx log the multipart body requests.
     */
    public static final String JINX_LOG_MULTIPART = "jinx.log.multipart";

	public static enum Method {
		POST,
		GET
	}

	public static final String REST_ENDPOINT = "https://api.flickr.com/services/rest/";
    public static final String FLICKR_PHOTO_URL = "https://www.flickr.com/photos/";
    public static final String FLICKR_PHOTO_UPLOAD_URL = "https://up.flickr.com/services/upload/";
    public static final String FLICKR_PHOTO_REPLACE_URL = "https://up.flickr.com/services/replace/";

	public static final String UTF8 = "UTF-8";

    public static enum OAuthPermissions {
        read,
        write,
        delete
    }

	public static enum PhotoExtras {
		description,
        license,
        date_upload,
        date_taken,
        owner_name,
		icon_server,
        original_format,
        last_update,
        geo,
        tags,
        machine_tags,
        o_dims,
        views,
        media,
		path_alias,
        url_sq,
        url_t,
        url_s,
        url_q,
        url_m,
        url_n,
        url_z,
        url_c,
        url_l,
        url_o
	}

	public static enum PrivacyFilter {
		privacyPublic,
        privacyFriends,
        privacyFamily,
        privacyFriendsAndFamily,
        privacyPrivate
	}


	public static enum MediaType {
		all,
        photos,
        videos
	}

	public static enum Perms {
		nobody,
		friendsAndFamily,
		contacts,
		everybody
	}

	public static enum SortOrder {
		date_posted_asc,
        date_posted_desc,
        date_taken_asc,
        date_taken_desc,
        interestingness_desc,
        interestingness_asc,
        relevance
	}

	public static enum ContentType {
		photo,
        screenshot,
        other,
        photos_and_screenshots,
		screenshots_and_other,
        photos_and_other,
        all
	}

	public static enum SafetyLevel {
		safe,
        moderate,
        restricted
	}

	public static enum TagMode {
		any,
        all
	}

	public static enum Contacts {
		all,
        ff
	}

	public static enum GeoContext {
		not_defined,
        indoors,
        outdoors
	}

	public static enum RadiusUnits {
		mi,
        km
	}

	public static enum ContactFilter {
		friends,
        family,
        both,
        neither
	}
	public static enum ContactSort {
		name,
        time
	}

	public static enum PhotoSize {
		SIZE_SMALL_SQUARE,
        SIZE_THUMBNAIL,
        SIZE_SMALL,
        SIZE_MEDIUM,
        SIZE_MEDIUM_640,
		SIZE_LARGE,
        SIZE_ORIGINAL,
        SIZE_LARGE_SQUARE,
        SIZE_SMALL_320,
        SIZE_MEDIUM_800,
		SIZE_LARGE_1600,
        SIZE_LARGE_2048
	}

    public static enum MemberType {
        narwhal,
        member,
        moderator,
        admin
    }

    public static enum GroupPrivacy {
        group_private,
        group_invite_only_public,
        group_open_public
    }

    public static enum GroupExtras {
        privacy,
        throttle,
        restrictions
    }

    public static enum SuggestionStatus {
        pending,
        approved,
        rejected
    }

    public static enum RotateDegrees {
        _90,
        _180,
        _270
    }

    public static enum TicketStatus {
        not_completed,
        completed,
        failed,
        invalid,
        undefined
    }

    public final static char[] MULTIPART_CHARS =
            "-_1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
}
