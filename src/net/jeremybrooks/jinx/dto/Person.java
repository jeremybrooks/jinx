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

import java.util.Date;

/**
 * Represents information about a person who has faved a particular photo.
 *
 * @author jeremyb
 */
public class Person {

    private String nsid;
    private String username;
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


    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder("Person [ ");

	sb.append("nsid=").append(this.nsid).append(" | ");
	sb.append("username=").append(this.username).append(" | ");
	sb.append("faveDate=").append(this.faveDate);

	sb.append(" ]");

	return sb.toString();
    }
}
