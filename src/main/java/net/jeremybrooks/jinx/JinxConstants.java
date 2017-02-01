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

  public enum PlaceTypeId {
    neighbourhood(22),
    locality(7),
    region(8),
    country(12),
    continent(29);
    private Integer typeId;
    private PlaceTypeId(Integer typeId) {
      this.typeId = typeId;
    }
    public Integer getTypeId() { return this.typeId; }
  }

  public enum Orientation {
    landscape,
    portrait,
    square,
    panorama
  }

  public enum ColorCode {
    red("0", "#ff2000", "Red"),
    dark_orange("1", "#a24615", "Dark Orange"),
    orange("2", "#ff7c00", "Orange"),
    pale_pink("b", "#ff9f9c", "Pale Pink"),
    lemon_yellow("4", "#fffa00", "Lemon Yellow"),
    school_bus_yellow("3", "#ffcf00", "School Bus Yellow"),
    green("5", "#90e200", "Green"),
    dark_lime_green("6", "#00ab00", "Dark Lime Green"),
    cyan("7", "#00b2d4", "Cyan"),
    blue("8", "#0062c6", "Blue"),
    violet("9", "#8c20ba", "Violet"),
    pink("a", "#f52394", "Pink"),
    white("c", "#ffffff", "White"),
    gray("d", "#7c7c7c", "Gray"),
    black("e", "#000000", "Black");

    private String colorValue;
    private String hexTriplet;
    private String colorName;

    ColorCode(String colorValue, String hexTriplet, String colorName) {
      this.colorValue = colorValue;
      this.hexTriplet = hexTriplet;
      this.colorName = colorName;
    }

    /**
     * The color value used in the API call to Flickr.
     *
     * @return color value.
     */
    public String getColorValue() {
      return this.colorValue;
    }

    /**
     * The hex triplet that represents the color.
     * This is useful if you want to display a color swatch.
     *
     * @return hex triplet for the color.
     */
    public String getHexTriplet() {
      return this.hexTriplet;
    }

    /**
     * The human readable name of the color.
     *
     * @return color name.
     */
    public String getColorName() {
      return this.colorName;
    }

    public String toString() {
      return this.colorValue;
    }
  }

  /**
   * Represents a fixed period of time.
   * Used by {@link net.jeremybrooks.jinx.api.TagsApi#getHotList(Period, Integer)}
   */
  public enum Period {
    day,
    week
  }

  public enum PictureStyle {
    blackandwhite("Black and White"),
    depthoffield("Depth of Field"),
    pattern("Pattern"),
    minimalism("Minimalism");

    private String styleName;

    PictureStyle(String styleName) {
      this.styleName = styleName;
    }

    /**
     * The human readable description of the picture style.
     *
     * @return style name.
     */
    public String getStyleName() {
      return this.styleName;
    }

  }

  public enum PopularPhotoSort {
    views, comments, favorites
  }
}
