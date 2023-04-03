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
import net.jeremybrooks.jinx.JinxConstants;
import net.jeremybrooks.jinx.JinxException;
import net.jeremybrooks.jinx.JinxUtils;
import net.jeremybrooks.jinx.response.photos.Photos;
import net.jeremybrooks.jinx.response.stats.Domains;
import net.jeremybrooks.jinx.response.stats.Referrers;
import net.jeremybrooks.jinx.response.stats.Stats;
import net.jeremybrooks.jinx.response.stats.TotalViews;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

/**
 * Provides access to the flickr.stats API methods.
 *
 * @author Jeremy Brooks
 * @see <a href="https://www.flickr.com/services/api/">Flickr API documentation</a> for more details.
 */
public class StatsApi {
  private final Jinx jinx;

  public StatsApi(Jinx jinx) {
    this.jinx = jinx;
  }

  /**
   * Get a list of referring domains for a collection
   *
   * Authentication
   *
   * This method requires authentication with 'read' permission.
   *
   * @param date         stats will be returned for this date. Required.
   * @param collectionId id of the collection to get stats for. If not provided,
   *                     stats for all collections will be returned. Optional.
   * @param perPage      number of domains to return per page.
   *                     If this argument is omitted, it defaults to 25.
   *                     The maximum allowed value is 100. Optional.
   * @param page         the page of results to return. If this argument is omitted, it defaults to 1. Optional.
   * @return referring domains for a collection.
   * @throws JinxException if required parameters are missing, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.stats.getCollectionDomains.html">flickr.stats.getCollectionDomains</a>
   */
  public Domains getCollectionDomains(Date date, String collectionId, Integer perPage, Integer page) throws JinxException {
    JinxUtils.validateParams(date);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.stats.getCollectionDomains");
    params.put("date", JinxUtils.formatDateAsYMD(date));
    if (!JinxUtils.isNullOrEmpty(collectionId)) {
      params.put("collection_id", collectionId);
    }
    if (perPage != null) {
      params.put("per_page", perPage.toString());
    }
    if (page != null) {
      params.put("page", page.toString());
    }
    return jinx.flickrGet(params, Domains.class);
  }

  /**
   * Get a list of referrers from a given domain to a collection
   *
   * Authentication
   *
   * This method requires authentication with 'read' permission.
   *
   * @param date         stats will be returned for this date. Required.
   * @param domain       domain to return referrers for. This should be a hostname (eg: "flickr.com")
   *                     with no protocol or pathname. Required.
   * @param collectionId id of the collection to get stats for. If not provided, stats for all
   *                     collections will be returned. Optional.
   * @param perPage      number of referrers to return per page. If this argument is omitted, it defaults
   *                     to 25. The maximum allowed value is 100. Optional.
   * @param page         page of results to return. If this argument is omitted, it defaults to 1. Optional.
   * @return Referrers from a given domain for a collection.
   * @throws JinxException if required parameters are missing, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.stats.getCollectionReferrers.html">flickr.stats.getCollectionReferrers</a>
   */
  public Referrers getCollectionReferrers(Date date, String domain, String collectionId, Integer perPage, Integer page)
      throws JinxException {
    JinxUtils.validateParams(date, domain);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.stats.getCollectionReferrers");
    params.put("date", JinxUtils.formatDateAsYMD(date));
    params.put("domain", domain);
    if (!JinxUtils.isNullOrEmpty(collectionId)) {
      params.put("collection_id", collectionId);
    }
    if (perPage != null) {
      params.put("per_page", perPage.toString());
    }
    if (page != null) {
      params.put("page", page.toString());
    }
    return jinx.flickrGet(params, Referrers.class);
  }

  /**
   * Get the number of views on a collection for a given date.
   *
   * Authentication
   *
   * This method requires authentication with 'read' permission.
   *
   * @param date         stats will be returned for this date. Required.
   * @param collectionId id of the collection to get stats for. Required.
   * @return stats for the specified collection.
   * @throws JinxException if required parameters are missing, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.stats.getCollectionStats.html">flickr.stats.getCollectionStats</a>
   */
  public Stats getCollectionStats(Date date, String collectionId) throws JinxException {
    JinxUtils.validateParams(date, collectionId);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.stats.getCollectionStats");
    params.put("date", JinxUtils.formatDateAsYMD(date));
    params.put("collection_id", collectionId);
    return jinx.flickrGet(params, Stats.class);
  }

  /**
   * Get a list of referring domains for a photo
   *
   * Authentication
   *
   * This method requires authentication with 'read' permission.
   *
   * @param date    stats will be returned for this date. Required.
   * @param photoId id of the photo to get stats for. If not provided, stats for all photos will be returned. Optional.
   * @param perPage number of domains to return per page. If this argument is omitted,
   *                it defaults to 25. The maximum allowed value is 100. Optional.
   * @param page    page of results to return. If this argument is omitted, it defaults to 1. Optional.
   * @return referrig domains for a photo.
   * @throws JinxException if required parameters are missing, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.stats.getPhotoDomains.html">flickr.stats.getPhotoDomains</a>
   */
  public Domains getPhotoDomains(Date date, String photoId, Integer perPage, Integer page) throws JinxException {
    JinxUtils.validateParams(date);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.stats.getPhotoDomains");
    params.put("date", JinxUtils.formatDateAsYMD(date));
    if (!JinxUtils.isNullOrEmpty(photoId)) {
      params.put("photo_id", photoId);
    }
    if (perPage != null) {
      params.put("per_page", perPage.toString());
    }
    if (page != null) {
      params.put("page", page.toString());
    }
    return jinx.flickrGet(params, Domains.class);
  }

  /**
   * Get a list of referrers from a given domain to a photo
   *
   * This method requires authentication with 'read' permission.
   *
   * @param date    stats will be returned for this date. Required.
   * @param domain  domain to return referrers for. This should be a hostname (eg: "flickr.com") with no
   *                protocol or pathname. Required.
   * @param photoId id of the photo to get stats for. If not provided, stats for all photos will be returned. Optional.
   * @param perPage number of referrers to return per page. If this argument is omitted, it defaults
   *                to 25. The maximum allowed value is 100. Optional.
   * @param page    page of results to return. If this argument is omitted, it defaults to 1. Optional.
   * @return referrers from a given domain to a photo.
   * @throws JinxException if required parameters are missing, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.stats.getPhotoReferrers.html">flickr.stats.getPhotoReferrers</a>
   */
  public Referrers getPhotoReferrers(Date date, String domain, String photoId, Integer perPage, Integer page)
      throws JinxException {
    JinxUtils.validateParams(date, domain);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.stats.getPhotoReferrers");
    params.put("date", JinxUtils.formatDateAsYMD(date));
    params.put("domain", domain);
    if (!JinxUtils.isNullOrEmpty(photoId)) {
      params.put("photo_id", photoId);
    }
    if (perPage != null) {
      params.put("per_page", perPage.toString());
    }
    if (page != null) {
      params.put("page", page.toString());
    }
    return jinx.flickrGet(params, Referrers.class);
  }

  /**
   * Get the number of views, comments and favorites on a photo for a given date.
   *
   * This method requires authentication with 'read' permission.
   *
   * @param date    stats will be returned for this date. Required.
   * @param photoId id of the photo to get stats for. Required.
   * @return number of views, comments, and favorites on a photo for a given date.
   * @throws JinxException if required parameters are missing, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.stats.getPhotoStats.html">flickr.stats.getPhotoStats</a>
   */
  public Stats getPhotoStats(Date date, String photoId) throws JinxException {
    JinxUtils.validateParams(date, photoId);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.stats.getPhotoStats");
    params.put("date", JinxUtils.formatDateAsYMD(date));
    params.put("photo_id", photoId);
    return jinx.flickrGet(params, Stats.class);
  }

  /**
   * Get a list of referring domains for a photoset
   *
   * This method requires authentication with 'read' permission.
   *
   * @param date       stats will be returned for this date. Required.
   * @param photosetId id of the photoset to get stats for. If not provided, stats for all sets will be returned. Optional.
   * @param perPage    number of domains to return per page. If this argument is omitted,
   *                   it defaults to 25. The maximum allowed value is 100. Optional.
   * @param page       page of results to return. If this argument is omitted, it defaults to 1. Optional.
   * @return referring domains for a photoset.
   * @throws JinxException if required parameters are missing, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.stats.getPhotosetDomains.html">flickr.stats.getPhotosetDomains</a>
   */
  public Domains getPhotosetDomains(Date date, String photosetId, Integer perPage, Integer page) throws JinxException {
    JinxUtils.validateParams(date);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.stats.getPhotosetDomains");
    params.put("date", JinxUtils.formatDateAsYMD(date));
    if (!JinxUtils.isNullOrEmpty(photosetId)) {
      params.put("photoset_id", photosetId);
    }
    if (perPage != null) {
      params.put("per_page", perPage.toString());
    }
    if (page != null) {
      params.put("page", page.toString());
    }
    return jinx.flickrGet(params, Domains.class);
  }

  /**
   * Get a list of referrers from a given domain to a photoset
   *
   * This method requires authentication with 'read' permission.
   *
   * @param date       stats will be returned for this date. Required.
   * @param domain     domain to return referrers for. This should be a hostname (eg: "flickr.com") with no
   *                   protocol or pathname. Required.
   * @param photosetId id of the photoset to get stats for. If not provided, stats for all sets will be returned. Optional.
   * @param perPage    number of referrers to return per page. If this argument is omitted, it defaults to
   *                   25. The maximum allowed value is 100. Optional.
   * @param page       page of results to return. If this argument is omitted, it defaults to 1. Optional.
   * @return referrers from a domain to a photoset.
   * @throws JinxException if required parameters are missing, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.stats.getPhotosetReferrers.html">flickr.stats.getPhotosetReferrers</a>
   */
  public Referrers getPhotosetReferrers(Date date, String domain, String photosetId, Integer perPage, Integer page)
      throws JinxException {
    JinxUtils.validateParams(date, domain);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.stats.getPhotosetReferrers");
    params.put("date", JinxUtils.formatDateAsYMD(date));
    params.put("domain", domain);
    if (!JinxUtils.isNullOrEmpty(photosetId)) {
      params.put("photoset_id", photosetId);
    }
    if (perPage != null) {
      params.put("per_page", perPage.toString());
    }
    if (page != null) {
      params.put("page", page.toString());
    }
    return jinx.flickrGet(params, Referrers.class);
  }

  /**
   * Get the number of views on a photoset for a given date.
   *
   * This method requires authentication with 'read' permission.
   *
   * @param date       stats will be returned for this date. Required.
   * @param photosetId id of the photoset to get stats for. Required.
   * @return number of views on a photoset for a given date.
   * @throws JinxException if required parameters are missing, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.stats.getPhotosetStats.html">flickr.stats.getPhotosetStats</a>
   */
  public Stats getPhotosetStats(Date date, String photosetId) throws JinxException {
    JinxUtils.validateParams(date, photosetId);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.stats.getPhotosetStats");
    params.put("date", JinxUtils.formatDateAsYMD(date));
    params.put("photoset_id", photosetId);
    return jinx.flickrGet(params, Stats.class);
  }

  /**
   * Get a list of referring domains for a photostream
   *
   * This method requires authentication with 'read' permission.
   *
   * @param date    stats will be returned for this date. Required.
   * @param perPage nummber of domains to return per page. If this argument is omitted, it defaults
   *                to 25. The maximum allowed value is 100. Optional.
   * @param page    page of results to return. If this argument is omitted, it defaults to 1. Optional.
   * @return referring domains for a photostream.
   * @throws JinxException if required parameters are missing, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.stats.getPhotostreamDomains.html">flickr.stats.getPhotostreamDomains</a>
   */
  public Domains getPhotostreamDomains(Date date, Integer perPage, Integer page) throws JinxException {
    JinxUtils.validateParams(date);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.stats.getPhotostreamDomains");
    params.put("date", JinxUtils.formatDateAsYMD(date));
    if (perPage != null) {
      params.put("per_page", perPage.toString());
    }
    if (page != null) {
      params.put("page", page.toString());
    }
    return jinx.flickrGet(params, Domains.class);
  }

  /**
   * Get a list of referrers from a given domain to a user's photostream
   *
   * This method requires authentication with 'read' permission.
   *
   * @param date    stats will be returned for this date. Required.
   * @param domain  domain to return referrers for. This should be a hostname (eg: "flickr.com")
   *                with no protocol or pathname. Required.
   * @param perPage number of referrers to return per page. If this argument is omitted, it defaults
   *                to 25. The maximum allowed value is 100. Optional.
   * @param page    page of results to return. If this argument is omitted, it defaults to 1. Optional.
   * @return referrers from a given domain to a user's photostream.
   * @throws JinxException if required parameters are missing, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.stats.getPhotostreamReferrers.html">flickr.stats.getPhotostreamReferrers</a>
   */
  public Referrers getPhotostreamReferrers(Date date, String domain, Integer perPage, Integer page)
      throws JinxException {
    JinxUtils.validateParams(date, domain);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.stats.getPhotostreamReferrers");
    params.put("date", JinxUtils.formatDateAsYMD(date));
    params.put("domain", domain);
    if (perPage != null) {
      params.put("per_page", perPage.toString());
    }
    if (page != null) {
      params.put("page", page.toString());
    }
    return jinx.flickrGet(params, Referrers.class);
  }

  /**
   * Get the number of views on a user's photostream for a given date.
   *
   * This method requires authentication with 'read' permission.
   *
   * @param date stats will be returned for this date. Required.
   * @return number of views on a user's photostream for a given date.
   * @throws JinxException if required parameters are missing or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.stats.getPhotostreamStats.html">flickr.stats.getPhotostreamStats</a>
   */
  public Stats getPhotostreamStats(Date date) throws JinxException {
    JinxUtils.validateParams(date);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.stats.getPhotostreamStats");
    params.put("date", JinxUtils.formatDateAsYMD(date));
    return jinx.flickrGet(params, Stats.class);
  }

  /**
   * Returns a list of URLs for text files containing all your stats data (from November 26th 2007 onwards)
   * for the currently auth'd user.
   *
   * Please note, these files will only be available until June 1, 2010 Noon PDT.
   *
   * This method requires authentication with 'read' permission.
   *
   * @return stats object with csv files populated.
   * @throws JinxException if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.stats.getCSVFiles.html">flickr.stats.getCSVFiles</a>
   */
  public Stats getCSVFiles() throws JinxException {
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.stats.getCSVFiles");
    return jinx.flickrGet(params, Stats.class);
  }

  /**
   * List the photos with the most views, comments or favorites
   *
   * This method requires authentication with 'read' permission.
   *
   * @param date    stats will be returned for this date. Optional.
   * @param sort    order in which to sort returned photos. Defaults to views. Optional.
   * @param perPage number of referrers to return per page. If this argument is omitted, it defaults to
   *                25. The maximum allowed value is 100. Optional.
   * @param page    page of results to return. If this argument is omitted, it defaults to 1. Optional.
   * @return photos with the most views, comments, or favorites.
   * @throws JinxException if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.stats.getPopularPhotos.html">flickr.stats.getPopularPhotos</a>
   */
  public Photos getPopularPhotos(Date date, JinxConstants.PopularPhotoSort sort, Integer perPage, Integer page) throws JinxException {
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.stats.getPopularPhotos");
    if (date != null) {
      params.put("date", JinxUtils.formatDateAsYMD(date));
    }
    if (sort != null) {
      params.put("sort", sort.toString());
    }
    if (perPage != null) {
      params.put("per_page", perPage.toString());
    }
    if (page != null) {
      params.put("page", page.toString());
    }
    return jinx.flickrGet(params, Photos.class);
  }

  /**
   * Get the overall view counts for an account
   *
   * This method requires authentication with 'read' permission.
   *
   * @param date stats will be returned for this date. Optional.
   * @return the overall view counts for an account.
   * @throws JinxException if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.stats.getTotalViews.html">flickr.stats.getTotalViews</a>
   */
  public TotalViews getTotalViews(Date date) throws JinxException {
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.stats.getTotalViews");
    if (date != null) {
      params.put("date", JinxUtils.formatDateAsYMD(date));
    }
    return jinx.flickrGet(params, TotalViews.class);
  }
}
