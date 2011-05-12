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
import java.util.List;

/**
 * Represents a single collection as returned by CollectionsApi.getTree.
 *
 * The Photoset objects contained in this object will only have their id, title,
 * and description fields set.
 * 
 * @author jeremyb
 */
public class FlickrCollection implements Serializable {
    private String id;
    private String title;
    private String description;
    private String iconLarge;
    private String iconSmall;
    private List<Photoset> setList;


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
     * @return the title
     */
    public String getTitle() {
	return title;
    }


    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
	this.title = title;
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
     * @return the iconLarge
     */
    public String getIconLarge() {
	return iconLarge;
    }


    /**
     * @param iconLarge the iconLarge to set
     */
    public void setIconLarge(String iconLarge) {
	this.iconLarge = iconLarge;
    }


    /**
     * @return the iconSmall
     */
    public String getIconSmall() {
	return iconSmall;
    }


    /**
     * @param iconSmall the iconSmall to set
     */
    public void setIconSmall(String iconSmall) {
	this.iconSmall = iconSmall;
    }

    /**
     * @return the setList
     */
    public List<Photoset> getSetList() {
	return setList;
    }


    /**
     * @param setList the setList to set
     */
    public void setSetList(List<Photoset> setList) {
	this.setList = setList;
    }


    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder("FlickrCollection [ ");

	sb.append("id=").append(this.id).append(" | ");
	sb.append("title=").append(this.title).append(" | ");
	sb.append("description=").append(this.description).append(" | ");
	sb.append("iconLarge=").append(this.iconLarge).append(" | ");
	sb.append("iconSmall=").append(this.iconSmall).append(" | ");

	if (this.setList != null) {
	    sb.append("setList= ");
	    for (Photoset set : this.setList) {
		sb.append(" <id=").append(set.getId()).append(" | ");
		sb.append("title=").append(set.getTitle()).append(" | ");
		sb.append("description=").append(set.getDescription()).append("> ");
	    }
	}

	sb.append(" ]");
	return sb.toString();
    }


}
