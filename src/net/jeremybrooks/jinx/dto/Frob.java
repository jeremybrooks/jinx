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
 * The frob to be used during authentication.
 * 
 * @author jeremyb
 */
public class Frob implements Serializable {

    private String loginUrl;

    private String perms;

    private String frob;

    
    public void setLoginUrl(String loginUrl) {
	this.loginUrl = loginUrl;
    }
    
    public String getLoginUrl() {
	return this.loginUrl;
    }


    /**
     * @return the perms
     */
    public String getPerms() {
	return perms;
    }


    /**
     * @param perms the perms to set
     */
    public void setPerms(String perms) {
	this.perms = perms;
    }


    /**
     * @return the frob
     */
    public String getFrob() {
	return frob;
    }


    /**
     * @param frob the frob to set
     */
    public void setFrob(String frob) {
	this.frob = frob;
    }

}
