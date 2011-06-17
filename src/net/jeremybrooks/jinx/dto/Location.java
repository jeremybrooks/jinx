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

/**
 * This class represents a Location.
 * 
 * @author jeremyb
 */
public class Location implements Serializable {
    

    private String photoId;
    private float latitude;
    private float longitude;
    private int accuracy;
    private int context;
    private String placeId;
    private String woeId;
    private String localityPlaceId;
    private String localityWoeId;
    private String locality;
    private String countyPlaceId;
    private String countyWoeId;
    private String county;
    private String regionPlaceId;
    private String regionWoeId;
    private String region;
    private String countryPlaceId;
    private String countryWoeId;
    private String country;


    /**
     * @return the photoId
     */
    public String getPhotoId() {
	return photoId;
    }


    /**
     * @param photoId the photoId to set
     */
    public void setPhotoId(String photoId) {
	this.photoId = photoId;
    }


    /**
     * @return the latitude
     */
    public float getLatitude() {
	return latitude;
    }


    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(float latitude) {
	this.latitude = latitude;
    }


    /**
     * @return the longitude
     */
    public float getLongitude() {
	return longitude;
    }


    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(float longitude) {
	this.longitude = longitude;
    }


    /**
     * @return the accuracy
     */
    public int getAccuracy() {
	return accuracy;
    }


    /**
     * @param accuracy the accuracy to set
     */
    public void setAccuracy(int accuracy) {
	this.accuracy = accuracy;
    }


    /**
     * @return the context
     */
    public int getContext() {
	return context;
    }


    /**
     * @param context the context to set
     */
    public void setContext(int context) {
	this.context = context;
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
     * @return the localityPlaceId
     */
    public String getLocalityPlaceId() {
	return localityPlaceId;
    }


    /**
     * @param localityPlaceId the localityPlaceId to set
     */
    public void setLocalityPlaceId(String localityPlaceId) {
	this.localityPlaceId = localityPlaceId;
    }


    /**
     * @return the localityWoeId
     */
    public String getLocalityWoeId() {
	return localityWoeId;
    }


    /**
     * @param localityWoeId the localityWoeId to set
     */
    public void setLocalityWoeId(String localityWoeId) {
	this.localityWoeId = localityWoeId;
    }


    /**
     * @return the locality
     */
    public String getLocality() {
	return locality;
    }


    /**
     * @param locality the locality to set
     */
    public void setLocality(String locality) {
	this.locality = locality;
    }


    /**
     * @return the countyPlaceId
     */
    public String getCountyPlaceId() {
	return countyPlaceId;
    }


    /**
     * @param countyPlaceId the countyPlaceId to set
     */
    public void setCountyPlaceId(String countyPlaceId) {
	this.countyPlaceId = countyPlaceId;
    }


    /**
     * @return the countyWoeId
     */
    public String getCountyWoeId() {
	return countyWoeId;
    }


    /**
     * @param countyWoeId the countyWoeId to set
     */
    public void setCountyWoeId(String countyWoeId) {
	this.countyWoeId = countyWoeId;
    }


    /**
     * @return the county
     */
    public String getCounty() {
	return county;
    }


    /**
     * @param county the county to set
     */
    public void setCounty(String county) {
	this.county = county;
    }


    /**
     * @return the regionPlaceId
     */
    public String getRegionPlaceId() {
	return regionPlaceId;
    }


    /**
     * @param regionPlaceId the regionPlaceId to set
     */
    public void setRegionPlaceId(String regionPlaceId) {
	this.regionPlaceId = regionPlaceId;
    }


    /**
     * @return the regionWoeId
     */
    public String getRegionWoeId() {
	return regionWoeId;
    }


    /**
     * @param regionWoeId the regionWoeId to set
     */
    public void setRegionWoeId(String regionWoeId) {
	this.regionWoeId = regionWoeId;
    }


    /**
     * @return the region
     */
    public String getRegion() {
	return region;
    }


    /**
     * @param region the region to set
     */
    public void setRegion(String region) {
	this.region = region;
    }


    /**
     * @return the countryPlaceId
     */
    public String getCountryPlaceId() {
	return countryPlaceId;
    }


    /**
     * @param countryPlaceId the countryPlaceId to set
     */
    public void setCountryPlaceId(String countryPlaceId) {
	this.countryPlaceId = countryPlaceId;
    }


    /**
     * @return the countryWoeId
     */
    public String getCountryWoeId() {
	return countryWoeId;
    }


    /**
     * @param countryWoeId the countryWoeId to set
     */
    public void setCountryWoeId(String countryWoeId) {
	this.countryWoeId = countryWoeId;
    }


    /**
     * @return the country
     */
    public String getCountry() {
	return country;
    }


    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
	this.country = country;
    }

    
    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder(this.getClass().getName());

	sb.append(" [ ");
	sb.append("photoId=").append(this.getPhotoId()).append(" | ");
	sb.append("latitude=").append(this.getLatitude()).append(" | ");
	sb.append("longitude=").append(this.getLongitude()).append(" | ");
	sb.append("accuracy=").append(this.getAccuracy()).append(" | ");
	sb.append("context=").append(this.getContext()).append(" | ");
	sb.append("placeId=").append(this.getPlaceId()).append(" | ");
	sb.append("woeId=").append(this.getWoeId()).append(" | ");
	sb.append("localityPlaceId=").append(this.getLocalityPlaceId()).append(" | ");
	sb.append("localityWoeId=").append(this.getLocalityWoeId()).append(" | ");
	sb.append("locality=").append(this.getLocality()).append(" | ");
	sb.append("countyPlaceId=").append(this.getCountyPlaceId()).append(" | ");
	sb.append("countyWoeId=").append(this.getCountyWoeId()).append(" | ");
	sb.append("county=").append(this.getCounty()).append(" | ");
	sb.append("regionPlaceId=").append(this.getRegionPlaceId()).append(" | ");
	sb.append("regionWoeId=").append(this.getRegionWoeId()).append(" | ");
	sb.append("region=").append(this.getRegion()).append(" | ");
	sb.append("countryPlaceId=").append(this.getCountryPlaceId()).append(" | ");
	sb.append("countryWoeId=").append(this.getCountryWoeId()).append(" | ");
	sb.append("country=").append(this.getCountry());

	sb.append(" ]");
	return sb.toString();
    }
}
