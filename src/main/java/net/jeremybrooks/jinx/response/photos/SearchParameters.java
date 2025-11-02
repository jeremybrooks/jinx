/*
 * Jinx is Copyright 2010-2025 by Jeremy Brooks and Contributors
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

package net.jeremybrooks.jinx.response.photos;

import net.jeremybrooks.jinx.JinxConstants;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author Jeremy Brooks
 */
public class SearchParameters implements Serializable {
	private static final long serialVersionUID = 8412916981222593516L;

	private String userId;
	private List<String> tags;
	private JinxConstants.TagMode tagMode;
	private String text;
	private Date minUploadDate;
	private Date maxUploadDate;
	private Date minTakenDate;
	private Date maxTakenDate;
	private List<Integer> licenses;
	private JinxConstants.SortOrder sort;
	private JinxConstants.PrivacyFilter privacyFilter;
	private BoundingBox boundingBox;
	private Integer accuracy;
	private JinxConstants.SafetyLevel safetyLevel;
	private JinxConstants.ContentType contentType;
	private List<String> machineTags;
	private JinxConstants.TagMode machineTagMode;
	private String groupId;
	private JinxConstants.Contacts contacts;
	private String woeId;
	private String placeId;
	private JinxConstants.MediaType mediaType;
	private Boolean hasGeo;
	private JinxConstants.GeoContext geoContext;
	private Float latitude;
	private Float longitude;
	private Float radius;
	private JinxConstants.RadiusUnits radiusUnits;
	private Boolean commons;
	private Boolean inGallery;
	private Boolean getty;
	private Set<JinxConstants.PhotoExtras> extras;
	private int perPage;
	private int page;
  private Set<JinxConstants.Orientation> orientations;
  private Set<JinxConstants.ColorCode> colorCodes;
  private Set<JinxConstants.PictureStyle> pictureStyles;

	/**
	 * Get the user id of the user who's photo to search.
	 *
	 * @return user id of the user who's photo to search.
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * Set the user id of the user who's photo to search.
	 * If this parameter isn't set then everybody's public photos will be searched. A value of "me" will search
	 * against the calling user's photos for authenticated calls.
	 *
	 * @param userId user id of the user who's photo to search.
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * Get the list of tags to match.
	 *
	 * @return list of tags.
	 */
	public List<String> getTags() {
		return tags;
	}

	/**
	 * Set the list of tags to match.
	 * <br>
	 * Photos with one or more of the tags listed will be returned. You can exclude results that match a term by
	 * prepending it with a - character. Tags may contain spaces; jinx will normalize the tags before passing
	 * them along to Flickr.
	 *
	 * @param tags the list of tags to search for.
	 */
	public void setTags(List<String> tags) {
		this.tags = tags;
	}


	/**
	 * Get the tag mode.
	 *
	 * @return tag mode.
	 */
	public JinxConstants.TagMode getTagMode() {
		return tagMode;
	}

	/**
	 * Set the tag mode.
	 * <br>
	 * Either 'any' for an OR combination of tags, or 'all' for an AND combination. Defaults to 'any' if not specified.
	 *
	 * @param tagMode tag mode for searches.
	 */
	public void setTagMode(JinxConstants.TagMode tagMode) {
		this.tagMode = tagMode;
	}

	/**
	 * Get text for free text search.
	 *
	 * @return text for free text search.
	 */
	public String getText() {
		return text;
	}

	/**
	 * Set text for free text search.
	 * <br>
	 * A free text search. Photos who's title, description or tags contain the text will be returned.
	 * You can exclude results that match a term by prepending it with a - character.
	 *
	 * @param text text for free text search.
	 */
	public void setText(String text) {
		this.text = text;
	}


	/**
	 * Minimum upload date for search.
	 *
	 * @return minimum upload date.
	 */
	public Date getMinUploadDate() {
		return minUploadDate;
	}

	/**
	 * Set the minimum upload date for search.
	 * <br>
	 * Photos with an upload date greater than or equal to this value will be returned.
	 *
	 * @param minUploadDate minimum upload date for search.
	 */
	public void setMinUploadDate(Date minUploadDate) {
		this.minUploadDate = minUploadDate;
	}

	/**
	 * Get the maximum upload date for search.
	 *
	 * @return maximum upload date for search.
	 */
	public Date getMaxUploadDate() {
		return maxUploadDate;
	}

	/**
	 * Set the maximum upload date for search.
	 * <br>
	 * Photos with an upload date less than or equal to this value will be returned.
	 *
	 * @param maxUploadDate maximum upload date for search.
	 */
	public void setMaxUploadDate(Date maxUploadDate) {
		this.maxUploadDate = maxUploadDate;
	}

	/**
	 * Get the minimum taken date for search.
	 *
	 * @return minimum taken date for search.
	 */
	public Date getMinTakenDate() {
		return minTakenDate;
	}


	/**
	 * Set the minimum taken date for search.
	 * <br>
	 * Photos with an taken date greater than or equal to this value will be returned.
	 *
	 * @param minTakenDate minimum taken date for search.
	 */
	public void setMinTakenDate(Date minTakenDate) {
		this.minTakenDate = minTakenDate;
	}

	/**
	 * Get the maximum taken date for search.
	 *
	 * @return maximum taken date for search.
	 */
	public Date getMaxTakenDate() {
		return maxTakenDate;
	}

	/**
	 * Set the maximum taken date for search.
	 * <br>
	 * Photos with an taken date less than or equal to this value will be returned.
	 *
	 * @param maxTakenDate maximum taken date for search.
	 */
	public void setMaxTakenDate(Date maxTakenDate) {
		this.maxTakenDate = maxTakenDate;
	}


	/**
	 * Get the license id(s) for photos to search for.
	 *
	 * @return license id(s) for photos.
	 */
	public List<Integer> getLicenses() {
		return licenses;
	}

	/**
	 * Set the license id(s) for photos to search for.
	 * <br>
	 * For possible values see the flickr.photos.licenses.getInfo method.
	 *
	 * @param licenses license id(s) for photos.
	 */
	public void setLicense(List<Integer> licenses) {
		this.licenses = licenses;
	}


	/**
	 * Get the sort order for returned photos.
	 *
	 * @return sort order for returned photos.
	 */
	public JinxConstants.SortOrder getSort() {
		return sort;
	}

	/**
	 * Set the sort order for returned photos.
	 * <br>
	 * Defaults to date-posted-desc (unless you are doing a radial geo query, in which case the default sorting is
	 * by ascending distance from the point specified).
	 *
	 * @param sort sort order for returned photos.
	 */
	public void setSort(JinxConstants.SortOrder sort) {
		this.sort = sort;
	}


	/**
	 * Get privacy filter for search.
	 *
	 * @return privacy filter for search.
	 */
	public JinxConstants.PrivacyFilter getPrivacyFilter() {
		return privacyFilter;
	}

	/**
	 * Set privacy filter for search.
	 * <br>
	 * Return photos only matching a certain privacy level. This only applies when making an authenticated call to
	 * view photos you own.
	 *
	 * @param privacyFilter privacy filter for search.
	 */
	public void setPrivacyFilter(JinxConstants.PrivacyFilter privacyFilter) {
		this.privacyFilter = privacyFilter;
	}

	/**
	 * Get the bounding box for geo searches.
	 *
	 * @return bounding box for geo searches.
	 */
	public BoundingBox getBoundingBox() {
		return boundingBox;
	}

	/**
	 * Set the bounding box for geo searches.
	 * <br>
	 * The 4 values in the bounding box object represent the bottom-left corner of the box and the top-right corner:
	 * <ul>
	 * <li>minimum longitude</li>
	 * <li>minimum latitude</li>
	 * <li>maximum longitude</li>
	 * <li>maximum latitude</li>
	 * </ul>
	 * <br>
	 * Longitude has a range of -180 to 180 , latitude of -90 to 90.
	 * <br>
	 * Unlike standard photo queries, geo (or bounding box) queries will only return 250 results per page.
	 * <br>
	 * Geo queries require some sort of limiting agent in order to prevent the database from crying.
	 * This is basically like the check against "parameterless searches" for queries without a geo component.
	 * <br>
	 * A tag, for instance, is considered a limiting agent as are user defined minTakenDate and minUploadDate parameters.
	 * If no limiting factor is passed the Flickr API will return only photos added in the last 12 hours
	 * (though they may extend the limit in the future).
	 *
	 * @param boundingBox bounding box for geo searches.
	 */
	public void setBoundingBox(BoundingBox boundingBox) {
		this.boundingBox = boundingBox;
	}

	/**
	 * Get accuracy level of the location information.
	 *
	 * @return accuracy level of the location information.
	 */
	public Integer getAccuracy() {
		return accuracy;
	}

	/**
	 * Set accuracy level of the location information.
	 * <br>
	 * Current range is 1-16:
	 * <ul>
	 * <li>World level is 1</li>
	 * <li>Country is ~3</li>
	 * <li>Region is ~6</li>
	 * <li>City is ~11</li>
	 * <li>Street is ~16</li>
	 * </ul>
	 * <br>
	 * If this value is null, it will not be passed to the Flickr API, and Flickr will default to the maximum value.
	 *
	 * @param accuracy accuracy level of the location information.
	 */
	public void setAccuracy(Integer accuracy) {
		this.accuracy = accuracy;
	}


	/**
	 * Get safety level for search.
	 *
	 * @return safety level for search.
	 */
	public JinxConstants.SafetyLevel getSafetyLevel() {
		return safetyLevel;
	}

	/**
	 * Set safety level for search
	 * <br>
	 * Please note: Un-authed calls can only see Safe content.
	 *
	 * @param safetyLevel safety level for the search.
	 */
	public void setSafetyLevel(JinxConstants.SafetyLevel safetyLevel) {
		this.safetyLevel = safetyLevel;
	}

	/**
	 * Get content type to search for.
	 *
	 * @return content type to search for.
	 */
	public JinxConstants.ContentType getContentType() {
		return contentType;
	}

	/**
	 * Set content type to search for.
	 *
	 * @param contentType content type to search for.
	 */
	public void setContentType(JinxConstants.ContentType contentType) {
		this.contentType = contentType;
	}


	/**
	 * Get machine tags to search for.
	 *
	 * @return machine tags to search for.
	 */
	public List<String> getMachineTags() {
		return machineTags;
	}

	/**
	 * Set machine tags to search for.
	 * <br>
	 * Aside from passing in a fully formed machine tag, there is a special syntax for searching on specific properties :
	 * <br> // TODO clarify the special formats listed below
	 * <ul>
	 * <li>Find photos using the 'dc' namespace : "machine_tags" =&gt; "dc:"</li>
	 * <li>Find photos with a title in the 'dc' namespace : "machine_tags" =&gt; "dc:title="</li>
	 * <li>Find photos titled "mr. camera" in the 'dc' namespace : "machine_tags" =&gt; "dc:title=\"mr. camera\"</li>
	 * <li>Find photos whose value is "mr. camera" : "machine_tags" =&gt; "*:*=\"mr. camera\""</li>
	 * <li>Find photos that have a title, in any namespace : "machine_tags" =&gt; "*:title="</li>
	 * <li>Find photos that have a title, in any namespace, whose value is "mr. camera" : "machine_tags" =&gt; "*:title=\"mr. camera\""</li>
	 * <li>Find photos, in the 'dc' namespace whose value is "mr. camera" : "machine_tags" =&gt; "dc:*=\"mr. camera\""</li>
	 * </ul>
	 * <br>
	 * Multiple machine tags may be queried by passing a list. The number of machine tags you can pass in a single
	 * query depends on the tag mode (AND or OR) that you are querying with. "AND" queries are limited to 16 machine tags.
	 * "OR" queries are limited to 8.
	 *
	 * @param machineTags machine tags to search for.
	 */
	public void setMachineTags(List<String> machineTags) {
		this.machineTags = machineTags;
	}


	/**
	 * Get machine tag search mode.
	 *
	 * @return machine tag search mode.
	 */
	public JinxConstants.TagMode getMachineTagMode() {
		return machineTagMode;
	}

	/**
	 * Set machine tag search mode.
	 * <br>
	 * Either {@link net.jeremybrooks.jinx.JinxConstants.TagMode#any} for an OR combination of tags,
	 * or {@link net.jeremybrooks.jinx.JinxConstants.TagMode#all} for an AND combination.
	 * Defaults to {@link net.jeremybrooks.jinx.JinxConstants.TagMode#all} if not specified.
	 *
	 * @param machineTagMode machine tag search mode.
	 */
	public void setMachineTagMode(JinxConstants.TagMode machineTagMode) {
		this.machineTagMode = machineTagMode;
	}


	/**
	 * Get group id to search for photos in.
	 *
	 * @return group id to search for photos in.
	 */
	public String getGroupId() {
		return groupId;
	}

	/**
	 * Set group id to search for photos in.
	 * <br>
	 * If specified, only matching photos posted to the group's pool will be returned.
	 *
	 * @param groupId group id to search for photos in.
	 */
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	/**
	 * Get contacts to search for photos from.
	 *
	 * @return contacts to search for photos from.
	 */
	public JinxConstants.Contacts getContacts() {
		return contacts;
	}

	/**
	 * Set contacts to search for photos from.
	 * Search for photos from your contacts. Either {@link net.jeremybrooks.jinx.JinxConstants.Contacts#all} for all
	 * contacts, or {@link net.jeremybrooks.jinx.JinxConstants.Contacts#ff} for just friends and family. (Experimental)
	 *
	 * @param contacts contacts to search for photos from.
	 */
	public void setContacts(JinxConstants.Contacts contacts) {
		this.contacts = contacts;
	}

	/**
	 * Get woe id.
	 *
	 * @return woe id.
	 */
	public String getWoeId() {
		return woeId;
	}

	/**
	 * Set woe id.
	 * A 32-bit identifier that uniquely represents spatial entities. Not used if BoundingBox is specified.
	 * <br>
	 * Geo queries require some sort of limiting agent in order to prevent the database from crying.
	 * This is basically like the check against "parameterless searches" for queries without a geo component.
	 * <br>
	 * A tag, for instance, is considered a limiting agent as are user defined minTakenDate and minUploadDate parameters
	 * If no limiting factor is passed Flickr returns only photos added in the last 12 hours
	 * (though Flickr may extend the limit in the future).
	 *
	 * @param woeId woe id.
	 */
	public void setWoeId(String woeId) {
		this.woeId = woeId;
	}

	/**
	 * Get Flickr place id.
	 *
	 * @return Flickr place id.
	 */
	public String getPlaceId() {
		return placeId;
	}

	/**
	 * Set Flickr place id.
	 * <br>
	 * Not used if BoundingBox is specified.
	 * <br>
	 * Geo queries require some sort of limiting agent in order to prevent the database from crying.
	 * This is basically like the check against "parameterless searches" for queries without a geo component.
	 * <br>
	 * A tag, for instance, is considered a limiting agent as are user defined minTakenDate and minUploadDate parameters.
	 * If no limiting factor is passed Flickr returns only photos added in the last 12 hours
	 * (though Flickr may extend the limit in the future).
	 *
	 * @param placeId Flickr place id.
	 */
	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}

	/**
	 * Get media type to filter search results by.
	 *
	 * @return media type to filter search results by.
	 */
	public JinxConstants.MediaType getMediaType() {
		return mediaType;
	}

	/**
	 * Set media type to filter results by.
	 * <br>
	 * Defaults to all if not specified.
	 *
	 * @param mediaType media type to filter results by.
	 */
	public void setMediaType(JinxConstants.MediaType mediaType) {
		this.mediaType = mediaType;
	}

	/**
	 * Get hasGeo parameter.
	 *
	 * @return hasGeo parameter.
	 */
	public Boolean isHasGeo() {
		return hasGeo;
	}

	/**
	 * Set geotagged photo search.
	 * <br>
	 * If true, will search for photos that have been geotagged, if false will search for photos
	 * that have not been geotagged.
	 * <br>
	 * By default, this is null and is ignored.
	 * <br>
	 * Geo queries require some sort of limiting agent in order to prevent the database from crying.
	 * This is basically like the check against "parameterless searches" for queries without a geo component.
	 * <br>
	 * A tag, for instance, is considered a limiting agent as are user defined minTakenDate and minUploadDate parameters.
	 * If no limiting factor is passed Flickr returns only photos added in the last 12 hours
	 * (though Flickr may extend the limit in the future).
	 *
	 * @param hasGeo true to match geotagged photos, false to match untagged photos, null to ignore.
	 */
	public void setHasGeo(Boolean hasGeo) {
		this.hasGeo = hasGeo;
	}


	/**
	 * Get the geo context to search in.
	 *
	 * @return geo context to search in.
	 */
	public JinxConstants.GeoContext getGeoContext() {
		return geoContext;
	}

	/**
	 * Set the geo context to search in.
	 * <br>
	 * Geo context represents the photo's geotagginess beyond latitude and longitude. For example, you may wish
	 * to search for photos that were taken indoors or outdoors.
	 * <br>
	 * Geo queries require some sort of limiting agent in order to prevent the database from crying.
	 * This is basically like the check against "parameterless searches" for queries without a geo component.
	 * <br>
	 * A tag, for instance, is considered a limiting agent as are user defined minTakenDate and minUploadDate parameters.
	 * If no limiting factor is passed Flickr returns only photos added in the last 12 hours
	 * (though Flickr may extend the limit in the future).
	 *
	 * @param geoContext geo context to search in.
	 */
	public void setGeoContext(JinxConstants.GeoContext geoContext) {
		this.geoContext = geoContext;
	}

	/**
	 * Get latitude for radial geo queries.
	 *
	 * @return latitude for radial geo queries.
	 */
	public Float getLatitude() {
		return latitude;
	}

	/**
	 * Set latitude for radio geo queries.
	 * <br>
	 * A valid latitude, in decimal format, for doing radial geo queries.
	 * <br>
	 * <br>
	 * Geo queries require some sort of limiting agent in order to prevent the database from crying.
	 * This is basically like the check against "parameterless searches" for queries without a geo component.
	 * <br>
	 * A tag, for instance, is considered a limiting agent as are user defined minTakenDate and minUploadDate parameters.
	 * If no limiting factor is passed Flickr returns only photos added in the last 12 hours
	 * (though Flickr may extend the limit in the future).
	 *
	 * @param latitude latitude for radial geo queries.
	 */
	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	/**
	 * Get longitude for radial geo queries.
	 *
	 * @return longitude for radial geo queries.
	 */
	public Float getLongitude() {
		return longitude;
	}

	/**
	 * Set longitude for radio geo queries.
	 * <br>
	 * A valid longitude, in decimal format, for doing radial geo queries.
	 * <br>
	 * <br>
	 * Geo queries require some sort of limiting agent in order to prevent the database from crying.
	 * This is basically like the check against "parameterless searches" for queries without a geo component.
	 * <br>
	 * A tag, for instance, is considered a limiting agent as are user defined minTakenDate and minUploadDate parameters.
	 * If no limiting factor is passed Flickr returns only photos added in the last 12 hours
	 * (though Flickr may extend the limit in the future).
	 *
	 * @param longitude longitude for radial geo queries.
	 */
	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}


	/**
	 * Get radius used for geo queries.
	 *
	 * @return radius used for geo queries.
	 */
	public Float getRadius() {
		return radius;
	}

	/**
	 * Set radius used for geo queries.
	 * <br>
	 * A valid radius used for geo queries, greater than zero and less than 20 miles (or 32 kilometers),
	 * for use with point-based geo queries. The default value is 5 (km).
	 *
	 * @param radius radius used for geo queries.
	 */
	public void setRadius(Float radius) {
		this.radius = radius;
	}

	/**
	 * Get the unit of measurement used when doing radial geo queries.
	 *
	 * @return unit of measurement used for radial geo queries.
	 */
	public JinxConstants.RadiusUnits getRadiusUnits() {
		return radiusUnits;
	}

	/**
	 * Set the unit of measurement when doing radial geo queries.
	 * <br>
	 * Valid options are {@link net.jeremybrooks.jinx.JinxConstants.RadiusUnits#mi} (miles)
	 * and {@link net.jeremybrooks.jinx.JinxConstants.RadiusUnits#km} (kilometers).
	 * The default is {@link net.jeremybrooks.jinx.JinxConstants.RadiusUnits#km}.
	 *
	 * @param radiusUnits unit of measurement used for radial geo queries.
	 */
	public void setRadiusUnits(JinxConstants.RadiusUnits radiusUnits) {
		this.radiusUnits = radiusUnits;
	}

	/**
	 * Get flag indicating if the search is limited to photos that are part of the Flickr Commons project.
	 *
	 * @return true if search is limited to Flickr Commons, false if not.
	 */
	public Boolean isCommons() {
		return commons;
	}

	/**
	 * Set flag indicating if the search is limited to photos that are part of the Flickr Commons project.
	 * <br>
	 * If true, search results will be limited to photos that are part of the Flickr Commons project.
	 * If false, search results will not be limited to photos that are part of the Flickr Commons project.
	 * If null, the parameter is ignored and not passed to the Flickr API.
	 *
	 * @param commons flag indicating if search results are limited to Flickr Commons photos.
	 */
	public void setCommons(Boolean commons) {
		this.commons = commons;
	}

	/**
	 * Get flag indicating if the search is limited to photos that are in a gallery.
	 *
	 * @return true if search is limited to photos in a gallery, false if not.
	 */
	public Boolean isInGallery() {
		return inGallery;
	}

	/**
	 * Set flag indicating if the search is limited to photos that are in a gallery.
	 * <br>
	 * If true, search results will be limited to photos that are in a gallery.
	 * If false, search results will not be limited to photos that are in a gallery.
	 * If null, the parameter is ignored and not passed to the Flickr API.
	 *
	 * @param inGallery flag indicating if search results are limited to photos in a gallery.
	 */
	public void setInGallery(Boolean inGallery) {
		this.inGallery = inGallery;
	}

	/**
	 * Get flag indicating if the search is limited to photos that are for sale on Getty.
	 *
	 * @return true if search is limited to photos for sale on Getty, false if not.
	 */
	public Boolean isGetty() {
		return getty;
	}

	/**
	 * Set flag indicating if the search is limited to photos that are for sale on Getty.
	 * <br>
	 * If true, search results will be limited to photos that are for sale on Getty.
	 * If false, search results will not be limited to photos that are for sale on Getty.
	 * If null, the parameter is ignored and not passed to the Flickr API.
	 *
	 * @param getty flag indicating if search results are limited to photos for sale on Getty.
	 */
	public void setGetty(Boolean getty) {
		this.getty = getty;
	}


	/**
	 * Get list of extra information to return for each photo.
	 *
	 * @return list of extras to return for each photo.
	 */
	public Set<JinxConstants.PhotoExtras> getExtras() {
		return extras;
	}

	/**
	 * Set list of extra information to return for each photo.
	 *
	 * @param extras list of extras to return for each photo.
	 */
	public void setExtras(Set<JinxConstants.PhotoExtras> extras) {
		this.extras = extras;
	}

	/**
	 * Get number of photos to return per page.
	 *
	 * @return number of photos to return per page.
	 */
	public int getPerPage() {
		return perPage;
	}

	/**
	 * Set number of photos to return per page.
	 * <br>
	 * If this argument is zero, it will not be passed to the Flickr API, and Flickr will default to 100.
	 * The maximum allowed value is 500.
	 *
	 * @param perPage number of photos to return per page.
	 */
	public void setPerPage(int perPage) {
		this.perPage = perPage;
	}

	/**
	 * Get page of results to return.
	 *
	 * @return page of results to return.
	 */
	public int getPage() {
		return page;
	}

	/**
	 * Set page of results to return.
	 * <br>
	 * If this argument is zero, it will not be passed to the Flickr API, and Flickr will default to 1.
	 *
	 * @param page page of results to return.
	 */
	public void setPage(int page) {
		this.page = page;
	}

  /**
   * Get the desired orientations.
   *
   * <p>This parameter is not included in the official API documentation. It should be considered experimental.</p>
   *
   * @return orientation of photos.
   */
  public Set<JinxConstants.Orientation> getOrientations() {
    return orientations;
  }

  /**
   * Set the orientation of photos.
   *
   * <p>This parameter is not included in the official API documentation. It should be considered experimental.</p>
   *
   * @param orientations the desired orientations.
   */
  public void setOrientations(Set<JinxConstants.Orientation> orientations) {
    this.orientations = orientations;
  }

  /**
   * Get the color codes.
   *
   * <p>This parameter is not included in the official API documentation. It should be considered experimental.</p>
   *
   * @return the colors of the returned photos.
   */
  public Set<JinxConstants.ColorCode> getColorCodes() {
    return colorCodes;
  }

  /**
   * Set the color codes.
   *
   * <p>This parameter is not included in the official API documentation. It should be considered experimental.</p>
   *
   * @param colorCodes the desired colors of returned photos.
   */
  public void setColorCodes(Set<JinxConstants.ColorCode> colorCodes) {
    this.colorCodes = colorCodes;
  }

  /**
   * Get the picture styles.
   *
   * <p>This parameter is not included in the official API documentation. It should be considered experimental.</p>
   *
   * @return the picture style of returned photos.
   */
  public Set<JinxConstants.PictureStyle> getPictureStyles() {
    return pictureStyles;
  }

  /**
   * Set the picture styles.
   *
   * <p>This parameter is not included in the official API documentation. It should be considered experimental.</p>
   *
   * @param pictureStyles the desired styles of the returned photos.
   */
  public void setPictureStyles(Set<JinxConstants.PictureStyle> pictureStyles) {
    this.pictureStyles = pictureStyles;
  }


  public class BoundingBox implements Serializable {
		private static final long serialVersionUID = 5097597630955255868L;
		private double minimumLongitude;
		private double minimumLatitude;
		private double maximumLongitude;
		private double maximumLatitude;

		public BoundingBox() {
			this.minimumLongitude = -180.0;
			this.minimumLatitude = -90.0;
			this.maximumLongitude = 180.0;
			this.maximumLatitude = 90.0;
		}

		/**
		 * @return minimum longitude.
		 */
		public double getMinimumLongitude() {
			return minimumLongitude;
		}

		/**
		 * Set the minimum longitude.
		 *
		 * @param minimumLongitude minimum longitude; valid range is -180 to 180. If out of range, defaults to -180.
		 */
		public void setMinimumLongitude(double minimumLongitude) {
			if (minimumLongitude < -180.0 || minimumLongitude > 180.0) {
				this.minimumLongitude = -180.0;
			} else {
				this.minimumLongitude = minimumLongitude;
			}
		}

		/**
		 * @return minimum latitude.
		 */
		public double getMinimumLatitude() {
			return minimumLatitude;
		}

		/**
		 * Set the minimum latitude.
		 *
		 * @param minimumLatitude minimum latitude; valid range is -90 to 90. If out of range, defaults to -90.
		 */
		public void setMinimumLatitude(double minimumLatitude) {
			if (minimumLatitude < -90.0 || minimumLatitude > 90.0) {
				this.minimumLatitude = -90.0;
			} else {
				this.minimumLatitude = minimumLatitude;
			}
		}

		/**
		 * @return maximum longitude.
		 */
		public double getMaximumLongitude() {
			return maximumLongitude;
		}

		/**
		 * Set maximum longitude.
		 *
		 * @param maximumLongitude maximum longitude; valid range is -180 to 180. If out of range, defaults to 180.
		 */
		public void setMaximumLongitude(double maximumLongitude) {
			if (maximumLongitude < -180.0 || maximumLongitude > 180.0) {
				this.maximumLongitude = 180.0;
			} else {
				this.maximumLongitude = maximumLongitude;
			}
		}

		/**
		 * @return maximum latitude.
		 */
		public double getMaximumLatitude() {
			return maximumLatitude;
		}

		/**
		 * Set maximum latitude.
		 *
		 * @param maximumLatitude maximum latitude; valid range is -90 to 90. If out of range, defaults to 90.
		 */
		public void setMaximumLatitude(double maximumLatitude) {
			if (maximumLatitude < -90.0 || maximumLatitude > 90.0) {
				this.maximumLatitude = 90.0;
			} else {
				this.maximumLatitude = maximumLatitude;
			}
		}

		/**
		 * Return the bounding box as a comma separated list of values representing the bottom-left corner
		 * of the box and the top-right corner: minimum_longitude, minimum_latitude, maximum_longitude, maximum_latitude.
		 * <br>
		 * This string is in the format expected by the Flickr API.
		 *
		 * @return comma separated list defining the bounding box.
		 */
		public String toParameterString() {
			return String.format("%f,%f,%f,%f", minimumLongitude, minimumLatitude, maximumLongitude, maximumLatitude);
		}
	}
}
