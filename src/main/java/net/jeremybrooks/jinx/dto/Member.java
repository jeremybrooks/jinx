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

import net.jeremybrooks.jinx.JinxConstants.GroupMemberType;

/**
 *
 * @author jeremyb
 */
public class Member {

    //<member nsid="58302714@N07" username="Architecture Charlatan"
    //iconserver="5081" iconfarm="6" membertype="2" />

    private String nsid;
    private String username;
    private String iconserver;
    private String iconfarm;
    private GroupMemberType memberType;


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
     * @return the memberType
     */
    public GroupMemberType getMemberType() {
	return memberType;
    }


    /**
     * @param memberType the memberType to set
     */
    public void setMemberType(GroupMemberType memberType) {
	this.memberType = memberType;
    }


    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder(this.getClass().getName());

	sb.append(" [ ");
	sb.append("nsid=").append(this.getNsid()).append(" | ");
	sb.append("username=").append(this.getUsername()).append(" | ");
	sb.append("iconserver=").append(this.getIconserver()).append(" | ");
	sb.append("iconfarm=").append(this.getIconfarm()).append(" | ");
	sb.append("memberType=").append(this.getMemberType());
	sb.append("(");
	switch (this.memberType) {
	    case member:
		sb.append("2");
		break;
	    case moderator:
		sb.append("3");
		break;
	    case admin:
		sb.append("4");
		break;
	    default:
		sb.append("?");
		break;
	}
	sb.append(")");
	sb.append(" ]");
	return sb.toString();
    }
}
