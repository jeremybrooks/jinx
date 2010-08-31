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

import java.util.List;

/**
 * This object represents all the visible sets and pools that a given
 * photo belongs to.
 *
 * @author jeremyb
 */
public class AllContexts {

    private String id;
    private List<Photoset> sets;
    private List<Pool> pools;


    /**
     * @return the sets
     */
    public List<Photoset> getSets() {
	return sets;
    }


    /**
     * @param sets the sets to set
     */
    public void setSets(List<Photoset> sets) {
	this.sets = sets;
    }


    /**
     * @return the pools
     */
    public List<Pool> getPools() {
	return pools;
    }


    /**
     * @param pools the pools to set
     */
    public void setPools(List<Pool> pools) {
	this.pools = pools;
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

    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder("AllContexts [ ");

	sb.append("id=").append(this.id).append(" | ");
	sb.append("photosets=");
	if (this.sets == null) {
	    sb.append("null");
	} else {
	    sb.append(this.sets.size()).append(" elements");
	}
	sb.append(" | ");
	sb.append("pools=");
	if (this.pools == null) {
	    sb.append("null");
	} else {
	    sb.append(this.pools.size()).append(" elements");
	}


	sb.append(" ]");

	return sb.toString();
    }
}
