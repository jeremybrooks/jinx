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
 * NOTE: This object contains Photo objects representing
 * the previous and next photos, but these Photo objects do NOT have all
 * of their fields populated. The populated fields will be:
 * <ul>
 * <li>id</li>
 * <li>secret</li>
 * <li>server</li>
 * <li>farm</li>
 * <li>title</li>
 * <li>url</li>
 * <li>thumb</li>
 * <li>media</li>     *
 * </ul>
 * @author jeremyb
 */
public class Context implements Serializable {

    private int count;

    private Photo previousPhoto;

    private Photo nextPhoto;


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


    /**
     * @return the previousPhoto
     */
    public Photo getPreviousPhoto() {
	return previousPhoto;
    }


    /**
     * @param previousPhoto the previousPhoto to set
     */
    public void setPreviousPhoto(Photo previousPhoto) {
	this.previousPhoto = previousPhoto;
    }


    /**
     * @return the nextPhoto
     */
    public Photo getNextPhoto() {
	return nextPhoto;
    }


    /**
     * @param nextPhoto the nextPhoto to set
     */
    public void setNextPhoto(Photo nextPhoto) {
	this.nextPhoto = nextPhoto;
    }


    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder("Context [ ");

	sb.append("count=").append(this.count).append(" | ");

	sb.append("previousPhoto=");
	if (this.previousPhoto == null) {
	    sb.append("null");
	} else {
	    sb.append(this.previousPhoto.toString());
	}
	sb.append(" | nextPhoto=");
	if (this.nextPhoto == null) {
	    sb.append("null");
	} else {
	    sb.append(this.nextPhoto.toString());
	}

	sb.append(" ]");

	return sb.toString();
    }
}
