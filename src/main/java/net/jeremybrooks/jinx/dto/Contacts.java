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
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the data returned by the Contacts API.
 *
 * Some of the
 * fields may or may not be populated, depending on the method that was called.
 *
 * @author jeremyb
 */
public class Contacts implements Serializable {
    
    private int page;
    private int pages;
    private int perPage;
    private int total;
    private List<Contact> contactList;

    public Contacts() {
	this.contactList = new ArrayList<Contact>();
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
     * @return the contactList
     */
    public List<Contact> getContactList() {
	return contactList;
    }


    /**
     * @param contactList the contactList to set
     */
    public void setContactList(List<Contact> contactList) {
	this.contactList = contactList;
    }

    /**
     * Add a contact object to the list.
     * @param contact the contact to add to the list.
     */
    public void addContact(Contact contact) {
	this.contactList.add(contact);
    }


    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder(this.getClass().getName());

	sb.append(" [ ");
	sb.append("page=").append(this.page).append(" | ");
	sb.append("pages=").append(this.pages).append(" | ");
	sb.append("perPage=").append(this.perPage).append(" | ");
	sb.append("total=").append(this.total).append(" | ");
	sb.append("contactList=");
	if (this.contactList != null) {
	    for (Contact c : this.contactList) {
		sb.append('<').append(c.toString()).append('>');
	    }
	}

	sb.append(" ]");
	return sb.toString();
    }

}
