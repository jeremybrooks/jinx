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

/**
 * Represents a URL for a photo.
 * 
 * @author jeremyb
 */
public class Url implements Serializable {

    private String type;
    private String url;


    /**
     * @return the type
     */
    public String getType() {
	return type;
    }


    /**
     * @param type the type to set
     */
    public void setType(String type) {
	this.type = type;
    }


    /**
     * @return the url
     */
    public String getUrl() {
	return url;
    }


    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
	this.url = url;
    }


    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder("Url [ ");

	sb.append("type=").append(this.type).append(" | ");
	sb.append("url=").append(this.url);

	sb.append(" ]");
	return sb.toString();
    }
    
}
