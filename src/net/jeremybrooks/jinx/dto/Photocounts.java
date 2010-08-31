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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Contains photo counts for specified date ranges.
 * 
 * @author jeremyb
 */
public class Photocounts {


    private List<Photocount> photocountList;

    public Photocounts() {
	this.photocountList = new ArrayList<Photocount>();
    }


    public void addPhotocount(int count, Date fromDate, Date toDate) {
	Photocount p = new Photocount();
	p.setCount(count);
	p.setFromDate(fromDate);
	p.setToDate(toDate);

	this.photocountList.add(p);
    }

    public List<Photocount> getPhotocountList() {
	return this.photocountList;
    }


    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder("Photocounts [ ");

	for (Photocount p : photocountList) {
	    sb.append("< ");
	    sb.append("count=").append(p.getCount()).append(" | ");
	    sb.append("fromDate=").append(p.getFromDate()).append(" | ");
	    sb.append("toDate=").append(p.getToDate());
	    sb.append(" >");
	}

	sb.append(" ]");

	return sb.toString();
    }

    public class Photocount {
	private int count;
	private Date fromDate;
	private Date toDate;


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
	 * @return the fromDate
	 */
	public Date getFromDate() {
	    return fromDate;
	}


	/**
	 * @param fromDate the fromDate to set
	 */
	public void setFromDate(Date fromDate) {
	    this.fromDate = fromDate;
	}


	/**
	 * @return the toDate
	 */
	public Date getToDate() {
	    return toDate;
	}


	/**
	 * @param toDate the toDate to set
	 */
	public void setToDate(Date toDate) {
	    this.toDate = toDate;
	}

    }



}
