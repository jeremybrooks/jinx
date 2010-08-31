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

/**
 * Representation of a tag.
 * 
 * @author jeremyb
 */
public class Tag {

    private String id;
    private String author;
    private String raw;
    private String text;
    private boolean machineTag;


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
     * @return the author
     */
    public String getAuthor() {
	return author;
    }


    /**
     * @param author the author to set
     */
    public void setAuthor(String author) {
	this.author = author;
    }


    /**
     * @return the raw
     */
    public String getRaw() {
	return raw;
    }


    /**
     * @param raw the raw to set
     */
    public void setRaw(String raw) {
	this.raw = raw;
    }


    /**
     * @return the text
     */
    public String getText() {
	return text;
    }


    /**
     * @param text the text to set
     */
    public void setText(String text) {
	this.text = text;
    }

    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder("Tag [ ");

	sb.append("id=").append(this.id).append(" | ");
	sb.append("author=").append(this.author).append(" | ");
	sb.append("raw=").append(this.raw).append(" | ");
	sb.append("text=").append(this.text).append(" | ");
	sb.append("machineTag=").append(this.isMachineTag());

	sb.append(" ]");
	
	return sb.toString();
    }


    /**
     * @return the machineTag
     */
    public boolean isMachineTag() {
	return machineTag;
    }


    /**
     * @param machineTag the machineTag to set
     */
    public void setMachineTag(boolean machineTag) {
	this.machineTag = machineTag;
    }
}
