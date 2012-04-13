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

import java.util.List;

/**
 * <predicates page="1" pages="1" total="3" perpage="500">
 * @author emeraldjava
 */
public class Predicates {

	private int page;
	private int total; 
	private int perpage;
	private List<Predicate> predicates;
	
	public List<Predicate> getPredicates() {
		return predicates;
	}
	public void setPredicates(List<Predicate> values) {
		this.predicates = values;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getPerpage() {
		return perpage;
	}
	public void setPerpage(int perpage) {
		this.perpage = perpage;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Predicates[page=");
		builder.append(page);
		builder.append(", total=");
		builder.append(total);
		builder.append(", perpage=");
		builder.append(perpage);
		builder.append("]");
		return builder.toString();
	}
}