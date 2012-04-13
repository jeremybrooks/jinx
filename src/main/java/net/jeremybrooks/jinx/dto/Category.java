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

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jeremyb
 */
public class Category {
    private String name;
    private String path;
    private String pathId;
    private List<Subcat> subcats;
    private List<Group> groups;


    public Category() {
	this.subcats = new ArrayList<Subcat>();
	this.groups = new ArrayList<Group>();
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
     * @return the path
     */
    public String getPath() {
	return path;
    }


    /**
     * @param path the path to set
     */
    public void setPath(String path) {
	this.path = path;
    }


    /**
     * @return the pathId
     */
    public String getPathId() {
	return pathId;
    }


    /**
     * @param pathId the pathId to set
     */
    public void setPathId(String pathId) {
	this.pathId = pathId;
    }


    /**
     * @return the subcats
     */
    public List<Subcat> getSubcats() {
	return subcats;
    }


    /**
     *
     * @param subcat subcat to add
     */
    public void addSubcat(Subcat subcat) {
	if (subcat != null) {
	    this.subcats.add(subcat);
	}
    }

    /**
     * @return the groups
     */
    public List<Group> getGroups() {
	return groups;
    }

    /**
     *
     * @param group the group to add
     */
    public void addGroup(Group group) {
	if (group != null) {
	    this.groups.add(group);
	}
    }

    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder(this.getClass().getName());

	sb.append(" [ ");
	sb.append("name=").append(this.getName()).append(" | ");
	sb.append("path=").append(this.getPath()).append(" | ");

	sb.append("pathId=").append(this.getPathId()).append(" | ");

	sb.append("subcat count=").append(this.getSubcats().size()).append(":");
	for (Subcat subcat : this.getSubcats()) {
	    sb.append(subcat.toString());
	}
	sb.append(" | groups count=").append(this.getGroups().size()).append(":");
	for (Group group : this.getGroups()) {
	    sb.append(group.toString());
	}
		
	sb.append(" ]");
	return sb.toString();
    }
}
