/*
 * Jinx is Copyright 2010-2018 by Jeremy Brooks and Contributors
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
import net.jeremybrooks.jinx.response.Response;
import net.jeremybrooks.jinx.response.push.Subscriptions;
import net.jeremybrooks.jinx.response.push.Topics;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Provides access to the flickr.push API methods.
 *
 * <p>This API is experimental. Check the Flickr documentation before using.</p>
 *
 * @author Jeremy Brooks
 * @see <a href="https://www.flickr.com/services/api/">Flickr API documentation</a> for more details.
 */
public class PushApi {
  private Jinx jinx;

  private PushApi() {

  }

  public PushApi(Jinx jinx) {
    this.jinx = jinx;
  }

  /**
   * Returns a list of the subscriptions for the logged-in user.
   *
   * (this method is experimental and may change)
   *
   * This method requires authentication with 'read' permission.
   *
   * @return subscriptions for the logged in user.
   * @throws JinxException if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.push.getSubscriptions.html">flickr.push.getSubscriptions</a>
   */
  public Subscriptions getSubscriptions() throws JinxException {
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.push.getSubscriptions");
    return jinx.flickrGet(params, Subscriptions.class);
  }

  /**
   * Get all available push topics.
   *
   * (this method is experimental and may change)
   *
   * This method does not require authentication.
   *
   * @return all the different flavors of topics.
   * @throws JinxException if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.push.getTopics.html">flickr.push.getTopics</a>
   */
  public Topics getTopics() throws JinxException {
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.push.getTopics");
    return jinx.flickrGet(params, Topics.class);
  }


  /**
   * Subscribe to a push feed.
   *
   * (this method is experimental and may change)
   *
   * This method requires authentication with 'read' permission.
   *
   * @param topic             the type of subscription. See {@link #getTopics()}. Required.
   * @param callbackUrl       the url for the subscription endpoint. Limited to 255 bytes, and must be
   *                          unique for this user, i.e. no two subscriptions for a given user may
   *                          use the same callback url. Required.
   * @param mode              verification mode. Required.
   * @param verificationToken the verification token to be echoed back to the subscriber during the
   *                          verification callback, as per the Google PubSubHubbub spec.
   *                          Limited to 200 bytes. Optional.
   * @param leaseSeconds      number of seconds for which the subscription will be valid. Legal values
   *                          are 60 to 86400 (1 minute to 1 day). If not present, the subscription will
   *                          be auto-renewing. Optional.
   * @param woeIds            a 32-bit integer for a Where on Earth ID. Only valid if topic is geo. The order of
   *                          precedence for geo subscriptions is : woe ids, place ids,
   *                          radial i.e. the latitude, longitude parameters will be ignored if placeIds is present,
   *                          which will be ignored if woeIds is present. Optional.
   * @param placeIds          a list of Flickr place IDs. Only valid if topic is geo. The order of precedence for
   *                          geo subscriptions is : woe ids, place ids, radial i.e. the latitude, longitude parameters will be
   *                          ignored if placeIds is present, which will be ignored if woeIds is present. Optional.
   * @param latitude          a latitude value, in decimal format. Only valid if topic is geo. Defines the latitude for a
   *                          radial query centered around (latitude, longitude). The order of precedence for geo
   *                          subscriptions is : woe ids, place ids, radial i.e. the latitude, longitude parameters will
   *                          be ignored if placeIds is present, which will be ignored if woeIds is present. Optional.
   * @param longitude         a longitude value, in decimal format. Only valid if topic is geo. Defines the longitude
   *                          for a radial query centered around (latitude, longitude). The order of precedence for geo
   *                          subscriptions is : woe ids, place ids, radial i.e. the latitude, longitude parameters will
   *                          be ignored if placeIds is present, which will be ignored if woeIds is present. Optional.
   * @param radius            a radius value, in the units defined by radiusUnits. Only valid if topic is geo. Defines the radius
   *                          of a circle for a radial query centered around (latitude, longitude). Default is 5 km. The order
   *                          of precedence for geo subscriptions is : woe ids, place ids, radial i.e. the latitude, longitude
   *                          parameters will be ignored if placeIds is present, which will be ignored if woeIds is present. Optional.
   * @param radiusUnits       defines the units for the radius parameter. Only valid if topic is geo.
   *                          Options are mi and km. Default is km. Optional.
   * @param accuracy          defines the minimum accuracy required for photos to be included in a subscription. Only valid
   *                          if topic is geo Legal values are 1-16, default is 1 (i.e. any accuracy level). World level is 1,
   *                          Country is ~3, Region is ~6, City is ~11, Street is ~16. Optional.
   * @param commonsNSIDs      a list of nsids representing Flickr Commons institutions. See {@link CommonsApi#getInstitutions()}.
   *                          Only valid if topic is commons. If not present this argument defaults to all Flickr Commons
   *                          institutions. Optional.
   * @param tags              a list of strings to be used for tag subscriptions. Photos with one or more of the tags listed
   *                          will be included in the subscription. Only valid if the topic is tags. Optional.
   * @return if successful, response.stat will be "ok".
   * @throws JinxException if required parameters are missing, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.push.subscribe.html">flickr.push.subscribe</a>
   */
  public Response subscribe(String topic, String callbackUrl, JinxConstants.VerificationMode mode,
                            String verificationToken, Integer leaseSeconds, Integer woeIds,
                            List<String> placeIds, Float latitude, Float longitude, Integer radius,
                            JinxConstants.RadiusUnits radiusUnits, Integer accuracy, List<String> commonsNSIDs,
                            List<String> tags) throws JinxException {
    JinxUtils.validateParams(topic, callbackUrl, mode);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.push.subscribe");
    params.put("topic", topic);
    params.put("callback", callbackUrl);
    params.put("verify", mode.toString());
    if (!JinxUtils.isNullOrEmpty(verificationToken)) {
      params.put("verify_token", verificationToken);
    }
    if (leaseSeconds != null && leaseSeconds > 59) {
      params.put("lease_seconds", leaseSeconds.toString());
    }
    if (woeIds != null) {
      params.put("woe_ids", woeIds.toString());
    }
    if (!JinxUtils.isNullOrEmpty(placeIds)) {
      params.put("place_ids", JinxUtils.buildCommaDelimitedList(placeIds));
    }
    if (latitude != null) {
      params.put("lat", latitude.toString());
    }
    if (longitude != null) {
      params.put("lon", longitude.toString());
    }
    if (radius != null) {
      params.put("radius", radius.toString());
    }
    if (radiusUnits != null) {
      params.put("radius_units", radiusUnits.toString());
    }
    if (accuracy != null) {
      params.put("accuracy", accuracy.toString());
    }
    if (!JinxUtils.isNullOrEmpty(commonsNSIDs)) {
      params.put("nsids", JinxUtils.buildCommaDelimitedList(commonsNSIDs));
    }
    if (!JinxUtils.isNullOrEmpty(tags)) {
      params.put("tags", JinxUtils.buildCommaDelimitedList(tags));
    }
    return jinx.flickrGet(params, Response.class);
  }

  /**
   * Unsubscribe from a push feed.
   *
   * (this method is experimental and may change)
   *
   * This method requires authentication with 'read' permission.
   *
   * @param topic             the type of subscription. Required.
   * @param callbackUrl       url for the subscription endpoint. Must be the same url used when subscribing. Required.
   * @param mode              verification mode. Required.
   * @param verificationToken verification token to be echoed back to the subscriber during the
   *                          verification callback. Optional.
   * @return if successful, response.stat will be "ok".
   * @throws JinxException if required parameters are missing, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.push.unsubscribe.html">flickr.push.unsubscribe</a>
   */
  public Response unsubscribe(String topic, String callbackUrl, JinxConstants.VerificationMode mode, String verificationToken) throws JinxException {
    JinxUtils.validateParams(topic, callbackUrl, mode);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.push.unsubscribe");
    params.put("topic", topic);
    params.put("callback", callbackUrl);
    params.put("verify", mode.toString());
    if (!JinxUtils.isNullOrEmpty(verificationToken)) {
      params.put("verify_token", verificationToken);
    }
    return jinx.flickrGet(params, Response.class);
  }
}
