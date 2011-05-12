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
 *
 * @author jeremyb
 */
public class Photosets implements Serializable {
    private boolean canCreate;
    private List<Photoset> photosetList;


    /**
     * @return the canCreate
     */
    public boolean isCanCreate() {
	return canCreate;
    }


    /**
     * @param canCreate the canCreate to set
     */
    public void setCanCreate(boolean canCreate) {
	this.canCreate = canCreate;
    }


    /**
     * @return the photosetList
     */
    public List<Photoset> getPhotosetList() {
	return photosetList;
    }


    /**
     * @param photosetList the photosetList to set
     */
    public void setPhotosetList(List<Photoset> photosetList) {
	this.photosetList = photosetList;
    }

}
