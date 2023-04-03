/*
 * Jinx is Copyright 2010-2023 by Jeremy Brooks and Contributors
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

package net.jeremybrooks.jinx.api;

import net.jeremybrooks.jinx.Jinx;
import net.jeremybrooks.jinx.JinxException;
import net.jeremybrooks.jinx.response.people.Person;

import java.util.Map;
import java.util.TreeMap;

/**
 * Provides access to the flickr.prefs API methods.
 *
 * The methods in this class return a {@link Person} object, but only the {@link Person#getUserId()} and
 * the preference getters will have values set.
 *
 * @author Jeremy Brooks
 * @see <a href="https://www.flickr.com/services/api/">Flickr API documentation</a> for more details.
 */
public class PrefsApi {
  private final Jinx jinx;

  public PrefsApi(Jinx jinx) {
    this.jinx = jinx;
  }

  /**
   * Returns the default content type preference for the user.
   *
   * Authentication
   *
   * This method requires authentication with 'read' permission.
   *
   * @return person object with nsid and contentType fields set.
   * @throws JinxException if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.prefs.getContentType.html">flickr.prefs.getContentType</a>
   */
  public Person getContentType() throws JinxException {
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.prefs.getContentType");
    return jinx.flickrGet(params, Person.class);
  }


  /**
   * Returns the default privacy level for geographic information attached to the user's
   * photos and whether or not the user has chosen to use geo-related EXIF information to
   * automatically geotag their photos.
   *
   * Possible values, for viewing geotagged photos, are:
   *
   * 0 : No default set
   * 1 : Public
   * 2 : Contacts only
   * 3 : Friends and Family only
   * 4 : Friends only
   * 5 : Family only
   * 6 : Private
   * Users can edit this preference at http://www.flickr.com/account/geo/privacy/.
   *
   * Possible values for whether or not geo-related EXIF information will be used to geotag a photo are:
   * 0: Geo-related EXIF information will be ignored
   * 1: Geo-related EXIF information will be used to try and geotag photos on upload
   * Users can edit this preference at http://www.flickr.com/account/geo/exif/?from=privacy
   *
   * Authentication
   *
   * This method requires authentication with 'read' permission.
   *
   * @return person object with nsid, geoPerms, and importGeoExif fields set.
   * @throws JinxException if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.prefs.getGeoPerms.html">flickr.prefs.getGeoPerms</a>
   */
  public Person getGeoPerms() throws JinxException {
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.prefs.getGeoPerms");
    return jinx.flickrGet(params, Person.class);
  }

  /**
   * Returns the default hidden preference for the user.
   *
   * Authentication
   *
   * This method requires authentication with 'read' permission.
   *
   * @return person object with nsid and hidden fields set.
   * @throws JinxException if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.prefs.getHidden.html">flickr.prefs.getHidden</a>
   */
  public Person getHidden() throws JinxException {
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.prefs.getHidden");
    return jinx.flickrGet(params, Person.class);
  }

  /**
   * Returns the default privacy level preference for the user. Possible values are:
   *
   * 1 : Public
   * 2 : Friends only
   * 3 : Family only
   * 4 : Friends and Family
   * 5 : Private
   *
   * Authentication
   *
   * This method requires authentication with 'read' permission.
   *
   * @return person object with nsid and privacy fields set.
   * @throws JinxException if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.prefs.getPrivacy.html">flickr.prefs.getPrivacy</a>
   */
  public Person getPrivacy() throws JinxException {
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.prefs.getPrivacy");
    return jinx.flickrGet(params, Person.class);
  }

  /**
   * Returns the default safety level preference for the user.
   *
   * Authentication
   *
   * This method requires authentication with 'read' permission.
   *
   * @return person object with nsid and safetyLevel fields set.
   * @throws JinxException if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.prefs.getSafetyLevel.html">flickr.prefs.getSafetyLevel</a>
   */
  public Person getSafetyLevel() throws JinxException {
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.prefs.getSafetyLevel");
    return jinx.flickrGet(params, Person.class);
  }
}
