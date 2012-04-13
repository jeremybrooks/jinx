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

/**
 * <value usage="4" namespace="taxonomy" predicate="common" first_added="1244232796" last_added="1244232796">maui chaff flower</value>
 * @author assure
 *
 */
public class Value {
	
	private String namespace;
	private String predicate;
	private int firstadded;
	private int lastadded;
	private int usage;
	private String value;
	
	public String getNamespace() {
		return namespace;
	}
	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}
	public String getPredicate() {
		return predicate;
	}
	public void setPredicate(String predicate) {
		this.predicate = predicate;
	}
	public int getFirstadded() {
		return firstadded;
	}
	public void setFirstadded(int firstadded) {
		this.firstadded = firstadded;
	}
	public int getLastadded() {
		return lastadded;
	}
	public void setLastadded(int lastadded) {
		this.lastadded = lastadded;
	}
	public int getUsage() {
		return usage;
	}
	public void setUsage(int usage) {
		this.usage = usage;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Value [");
		if (namespace != null) {
			builder.append("namespace=");
			builder.append(namespace);
			builder.append(", ");
		}
		if (predicate != null) {
			builder.append("predicate=");
			builder.append(predicate);
			builder.append(", ");
		}
		builder.append("firstadded=");
		builder.append(firstadded);
		builder.append(", lastadded=");
		builder.append(lastadded);
		builder.append(", usage=");
		builder.append(usage);
		builder.append(", ");
		if (value != null) {
			builder.append("value=");
			builder.append(value);
		}
		builder.append("]");
		return builder.toString();
	}
}
