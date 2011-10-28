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
 * NOTE: This class name may be changed in the future if other API's can use
 *       it.
 * 
 * @author jeremyb
 */
public class ActivityItems implements Serializable {
    private int page;
    private int pages;
    private int perPage;
    private int total;

    private List<ActivityItem> itemList;

   

    /**
     * @return the page
     */
    public int getPage() {
	return page;
    }


    /**
     * @param page the page to set
     */
    public void setPage(int page) {
	this.page = page;
    }


    /**
     * @return the pages
     */
    public int getPages() {
	return pages;
    }


    /**
     * @param pages the pages to set
     */
    public void setPages(int pages) {
	this.pages = pages;
    }


    /**
     * @return the perPage
     */
    public int getPerPage() {
	return perPage;
    }


    /**
     * @param perPage the perPage to set
     */
    public void setPerPage(int perPage) {
	this.perPage = perPage;
    }


    /**
     * @return the total
     */
    public int getTotal() {
	return total;
    }


    /**
     * @param total the total to set
     */
    public void setTotal(int total) {
	this.total = total;
    }


    /**
     * @return the itemList
     */
    public List<ActivityItem> getItemList() {
	return itemList;
    }


    /**
     * @param itemList the itemList to set
     */
    public void setItemList(List<ActivityItem> itemList) {
	this.itemList = itemList;
    }


    
    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder(this.getClass().getName());
	sb.append(":[ ");
	sb.append("page=").append(this.page).append(" | ");
	sb.append("pages=").append(this.pages).append(" | ");
	sb.append("perPage=").append(this.perPage).append(" | ");
	sb.append("total=").append(this.total).append(" | ");
	sb.append("itemList=");
	if (this.itemList == null) {
	    sb.append("null");
	} else {
	    for (ActivityItem item : this.itemList) {
		sb.append("<");
		sb.append(item.toString()).append(">");
	    }
	}
	sb.append(" ]");
	return sb.toString();
    }
}
