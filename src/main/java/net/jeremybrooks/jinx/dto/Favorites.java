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
import java.util.List;

/**
 * A list of people who have favorited a given photo.
 * 
 * @author jeremyb
 */
public class Favorites implements Serializable {

    private String id;
    private String secret;
    private String server;
    private String farm;
    private int page;
    private int pages;
    private int perPage;
    private int total;

    private List<Person> personList;


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
     * @return the secret
     */
    public String getSecret() {
	return secret;
    }


    /**
     * @param secret the secret to set
     */
    public void setSecret(String secret) {
	this.secret = secret;
    }


    /**
     * @return the server
     */
    public String getServer() {
	return server;
    }


    /**
     * @param server the server to set
     */
    public void setServer(String server) {
	this.server = server;
    }


    /**
     * @return the farm
     */
    public String getFarm() {
	return farm;
    }


    /**
     * @param farm the farm to set
     */
    public void setFarm(String farm) {
	this.farm = farm;
    }


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
     * @return the personList
     */
    public List<Person> getPersonList() {
	return personList;
    }


    /**
     * @param personList the personList to set
     */
    public void setPersonList(List<Person> personList) {
	this.personList = personList;
    }

    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder("Favorites [ ");

	sb.append("id=").append(this.id).append(" | ");
	sb.append("secret=").append(this.secret).append(" | ");
	sb.append("server=").append(this.server).append(" | ");
	sb.append("farm=").append(this.farm).append(" | ");
	sb.append("page=").append(this.page).append(" | ");
	sb.append("pages=").append(this.pages).append(" | ");
	sb.append("perPage=").append(this.perPage).append(" | ");
	sb.append("total=").append(this.total).append(" | ");
	sb.append("personList=");
	if (this.personList == null) {
	    sb.append("null");
	} else {
	    for (Person p : this.personList) {
		sb.append('<').append(p.toString()).append('>');
	    }
	}

	sb.append(" ]");
	return sb.toString();
    }
}
