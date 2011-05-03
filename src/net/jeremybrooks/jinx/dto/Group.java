/*
 * Jinx is Copyright 2011 by Jeremy Brooks
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

/**
 *
 * @author jeremyb
 */
public class Group {
    private String id;
    private String iconserver;
    private String iconfarm;
    private String lang;
    private boolean poolModerated;
    private String name;
    private String description;
    private int members;
    private String online;
    private String chatnsid;
    private String inchat;
    private String privacy;
    private Blast blast;
    private Throttle throttle;
    private Restrictions restrictions;
    private boolean eighteenPlus;

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
     * @return the iconserver
     */
    public String getIconserver() {
	return iconserver;
    }


    /**
     * @param iconserver the iconserver to set
     */
    public void setIconserver(String iconserver) {
	this.iconserver = iconserver;
    }


    /**
     * @return the lang
     */
    public String getLang() {
	return lang;
    }


    /**
     * @param lang the lang to set
     */
    public void setLang(String lang) {
	this.lang = lang;
    }


    /**
     * @return the poolModerated
     */
    public boolean isPoolModerated() {
	return poolModerated;
    }


    /**
     * @param poolModerated the poolModerated to set
     */
    public void setPoolModerated(boolean poolModerated) {
	this.poolModerated = poolModerated;
    }


    /**
     * @return the name
     */
    public String getName() {
	return name;
    }


    /**
     * @param name the name to set
     */
    public void setName(String name) {
	this.name = name;
    }


    /**
     * @return the description
     */
    public String getDescription() {
	return description;
    }


    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
	this.description = description;
    }


    /**
     * @return the members
     */
    public int getMembers() {
	return members;
    }


    /**
     * @param members the members to set
     */
    public void setMembers(int members) {
	this.members = members;
    }


    /**
     * @return the online
     */
    public String getOnline() {
	return online;
    }


    /**
     * @param online the online to set
     */
    public void setOnline(String online) {
	this.online = online;
    }


    /**
     * @return the chatnsid
     */
    public String getChatnsid() {
	return chatnsid;
    }


    /**
     * @param chatnsid the chatnsid to set
     */
    public void setChatnsid(String chatnsid) {
	this.chatnsid = chatnsid;
    }


    /**
     * @return the inchat
     */
    public String getInchat() {
	return inchat;
    }


    /**
     * @param inchat the inchat to set
     */
    public void setInchat(String inchat) {
	this.inchat = inchat;
    }


    /**
     * @return the privacy
     */
    public String getPrivacy() {
	return privacy;
    }


    /**
     * @param privacy the privacy to set
     */
    public void setPrivacy(String privacy) {
	this.privacy = privacy;
    }

    /**
     * @return the iconfarm
     */
    public String getIconfarm() {
	return iconfarm;
    }


    /**
     * @param iconfarm the iconfarm to set
     */
    public void setIconfarm(String iconfarm) {
	this.iconfarm = iconfarm;
    }


    /**
     * @return the blast
     */
    public Blast getBlast() {
	return blast;
    }


    /**
     * @param blast the blast to set
     */
    public void setBlast(Blast blast) {
	this.blast = blast;
    }


    /**
     * @return the throttle
     */
    public Throttle getThrottle() {
	return throttle;
    }


    /**
     * @param throttle the throttle to set
     */
    public void setThrottle(Throttle throttle) {
	this.throttle = throttle;
    }


    /**
     * @return the restrictions
     */
    public Restrictions getRestrictions() {
	return restrictions;
    }


    /**
     * @param restrictions the restrictions to set
     */
    public void setRestrictions(Restrictions restrictions) {
	this.restrictions = restrictions;
    }

    /**
     * @return the eighteenPlus
     */
    public boolean isEighteenPlus() {
	return eighteenPlus;
    }


    /**
     * @param eighteenPlus the eighteenPlus to set
     */
    public void setEighteenPlus(boolean eighteenPlus) {
	this.eighteenPlus = eighteenPlus;
    }

    
    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder(this.getClass().getName());

	sb.append(" [ ");
	sb.append("id=").append(this.getId()).append(" | ");
	sb.append("iconserver=").append(this.getIconserver()).append(" | ");
	sb.append("iconfarm=").append(this.getIconfarm()).append(" | ");
	sb.append("lang=").append(this.getLang()).append(" | ");
	sb.append("isPoolModerated=").append(this.isPoolModerated()).append(" | ");
	sb.append("name=").append(this.getName()).append(" | ");
	sb.append("description=").append(this.getDescription()).append(" | ");
	sb.append("members=").append(this.getMembers()).append(" | ");
	sb.append("online=").append(this.getOnline()).append(" | ");
	sb.append("chatnsid=").append(this.getChatnsid()).append(" | ");
	sb.append("inchat=").append(this.getInchat()).append(" | ");
	sb.append("privacy=").append(this.getPrivacy()).append(" | ");
	sb.append("blast=").append(this.getBlast()).append(" | ");
	sb.append("throttle=").append(this.getThrottle()).append(" | ");
	sb.append("restrictions=").append(this.getRestrictions()).append(" | ");
	sb.append("eighteenPlus=").append(this.isEighteenPlus());

	sb.append(" ]");
	return sb.toString();
    }
    
}
