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
public class Subcat {
    private String id;
    private String name;
    private String count;


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
     * @return the count
     */
    public String getCount() {
	return count;
    }


    /**
     * @param count the count to set
     */
    public void setCount(String count) {
	this.count = count;
    }


    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder(this.getClass().getName());

	sb.append(" [ ");
	sb.append("id=").append(this.getId()).append(" | ");
	sb.append("name=").append(this.getName()).append(" | ");
	sb.append("count=").append(this.getCount());

	sb.append(" ]");
	return sb.toString();
    }
}
