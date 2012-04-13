/*
 * Jinx is Copyright 2010-2012 by Jeremy Brooks and Contributors
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

/**
 * Represents information about a person who has faved a particular photo.
 *
 * @author jeremyb
 */
public class Person implements Serializable {

    
    private String id;
    private String nsid;
    private boolean pro;
    private String iconServer;
    private String iconFarm;
    private String pathAlias;
    private String gender;
    private boolean ignored;
    private boolean contact;
    private boolean friend;
    private boolean family;
    private boolean revContact;
    private boolean revFriend;
    private boolean revFamily;
    private String username;
    private String realname;
    private String location;
    private String photosUrl;
    private String profileUrl;
    private String mobileUrl;
    private Date firstDateTaken;
    private Date firstDate;
    private int count;
    private Date faveDate;


    /**
     * @return the nsid
     */
    public String getNsid() {
	return nsid;
    }


    /**
     * @param nsid the nsid to set
     */
    public void setNsid(String nsid) {
	this.nsid = nsid;
    }


    /**
     * @return the username
     */
    public String getUsername() {
	return username;
    }


    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
	this.username = username;
    }


    /**
     * @return the faveDate
     */
    public Date getFaveDate() {
	return faveDate;
    }


    /**
     * @param faveDate the faveDate to set
     */
    public void setFaveDate(Date faveDate) {
	this.faveDate = faveDate;
    }


    
    /**
     * @return the id
     */
    public String getId() {
	return id;
    }


    /**
     * @param id the id to set
     */
    public void setId(String id) {
	this.id = id;
    }


    /**
     * @return the pro
     */
    public boolean isPro() {
	return pro;
    }


    /**
     * @param pro the pro to set
     */
    public void setPro(boolean pro) {
	this.pro = pro;
    }


    /**
     * @return the iconServer
     */
    public String getIconServer() {
	return iconServer;
    }


    /**
     * @param iconServer the iconServer to set
     */
    public void setIconServer(String iconServer) {
	this.iconServer = iconServer;
    }


    /**
     * @return the iconFarm
     */
    public String getIconFarm() {
	return iconFarm;
    }


    /**
     * @param iconFarm the iconFarm to set
     */
    public void setIconFarm(String iconFarm) {
	this.iconFarm = iconFarm;
    }


    /**
     * @return the pathAlias
     */
    public String getPathAlias() {
	return pathAlias;
    }


    /**
     * @param pathAlias the pathAlias to set
     */
    public void setPathAlias(String pathAlias) {
	this.pathAlias = pathAlias;
    }


    /**
     * @return the gender
     */
    public String getGender() {
	return gender;
    }


    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
	this.gender = gender;
    }


    /**
     * @return the ignored
     */
    public boolean isIgnored() {
	return ignored;
    }


    /**
     * @param ignored the ignored to set
     */
    public void setIgnored(boolean ignored) {
	this.ignored = ignored;
    }


    /**
     * @return the contact
     */
    public boolean isContact() {
	return contact;
    }


    /**
     * @param contact the contact to set
     */
    public void setContact(boolean contact) {
	this.contact = contact;
    }


    /**
     * @return the friend
     */
    public boolean isFriend() {
	return friend;
    }


    /**
     * @param friend the friend to set
     */
    public void setFriend(boolean friend) {
	this.friend = friend;
    }


    /**
     * @return the family
     */
    public boolean isFamily() {
	return family;
    }


    /**
     * @param family the family to set
     */
    public void setFamily(boolean family) {
	this.family = family;
    }


    /**
     * @return the revContact
     */
    public boolean isRevContact() {
	return revContact;
    }


    /**
     * @param revContact the revContact to set
     */
    public void setRevContact(boolean revContact) {
	this.revContact = revContact;
    }


    /**
     * @return the revFriend
     */
    public boolean isRevFriend() {
	return revFriend;
    }


    /**
     * @param revFriend the revFriend to set
     */
    public void setRevFriend(boolean revFriend) {
	this.revFriend = revFriend;
    }


    /**
     * @return the revFamily
     */
    public boolean isRevFamily() {
	return revFamily;
    }


    /**
     * @param revFamily the revFamily to set
     */
    public void setRevFamily(boolean revFamily) {
	this.revFamily = revFamily;
    }


    /**
     * @return the realname
     */
    public String getRealname() {
	return realname;
    }


    /**
     * @param realname the realname to set
     */
    public void setRealname(String realname) {
	this.realname = realname;
    }


    /**
     * @return the location
     */
    public String getLocation() {
	return location;
    }


    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
	this.location = location;
    }


    /**
     * @return the photosUrl
     */
    public String getPhotosUrl() {
	return photosUrl;
    }


    /**
     * @param photosUrl the photosUrl to set
     */
    public void setPhotosUrl(String photosUrl) {
	this.photosUrl = photosUrl;
    }


    /**
     * @return the profileUrl
     */
    public String getProfileUrl() {
	return profileUrl;
    }


    /**
     * @param profileUrl the profileUrl to set
     */
    public void setProfileUrl(String profileUrl) {
	this.profileUrl = profileUrl;
    }


    /**
     * @return the mobileUrl
     */
    public String getMobileUrl() {
	return mobileUrl;
    }


    /**
     * @param mobileUrl the mobileUrl to set
     */
    public void setMobileUrl(String mobileUrl) {
	this.mobileUrl = mobileUrl;
    }


    /**
     * @return the firstDateTaken
     */
    public Date getFirstDateTaken() {
	return firstDateTaken;
    }


    /**
     * @param firstDateTaken the firstDateTaken to set
     */
    public void setFirstDateTaken(Date firstDateTaken) {
	this.firstDateTaken = firstDateTaken;
    }


    /**
     * @return the firstDate
     */
    public Date getFirstDate() {
	return firstDate;
    }


    /**
     * @param firstDate the firstDate to set
     */
    public void setFirstDate(Date firstDate) {
	this.firstDate = firstDate;
    }


    /**
     * @return the count
     */
    public int getCount() {
	return count;
    }


    /**
     * @param count the count to set
     */
    public void setCount(int count) {
	this.count = count;
    }

    
    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder("Person [ ");

	sb.append("id=").append(this.getId()).append(" | ");
	sb.append("nsid=").append(this.getNsid()).append(" | ");
	sb.append("pro=").append(this.isPro()).append(" | ");
	sb.append("iconServer=").append(this.getIconServer()).append(" | ");
	sb.append("iconFarm=").append(this.getIconFarm()).append(" | ");
	sb.append("pathAlias=").append(this.getPathAlias()).append(" | ");
	sb.append("gender=").append(this.getGender()).append(" | ");
	sb.append("ignored=").append(this.isIgnored()).append(" | ");
	sb.append("contact=").append(this.isContact()).append(" | ");
	sb.append("friend=").append(this.isFriend()).append(" | ");
	sb.append("family=").append(this.isFamily()).append(" | ");
	sb.append("revContact=").append(this.isRevContact()).append(" | ");
	sb.append("revFriend=").append(this.isRevFriend()).append(" | ");
	sb.append("revFamily=").append(this.isRevFamily()).append(" | ");
	sb.append("username=").append(this.getUsername()).append(" | ");
	sb.append("realname=").append(this.getRealname()).append(" | ");
	sb.append("location=").append(this.getLocation()).append(" | ");
	sb.append("photosUrl=").append(this.getPhotosUrl()).append(" | ");
	sb.append("profileUrl=").append(this.getProfileUrl()).append(" | ");
	sb.append("mobileUrl=").append(this.getMobileUrl()).append(" | ");
	sb.append("firstDateTaken=").append(this.getFirstDateTaken()).append(" | ");
	sb.append("firstDate=").append(this.getFirstDate()).append(" | ");
	sb.append("count=").append(this.getCount()).append(" | ");
	sb.append("faveDate=").append(this.getFaveDate());

	sb.append(" ]");

	return sb.toString();
    }


}
