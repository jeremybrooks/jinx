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

/**
 * Data representing a note.
 * 
 * @author jeremyb
 */
public class Note implements Serializable {

    private String id;
    private String author;
    private String authorName;
    private int x;
    private int y;
    private int w;
    private int h;
    private String text;


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
     * @return the authorName
     */
    public String getAuthorName() {
	return authorName;
    }


    /**
     * @param authorName the authorName to set
     */
    public void setAuthorName(String authorName) {
	this.authorName = authorName;
    }


    /**
     * @return the x
     */
    public int getX() {
	return x;
    }


    /**
     * @param x the x to set
     */
    public void setX(int x) {
	this.x = x;
    }


    /**
     * @return the y
     */
    public int getY() {
	return y;
    }


    /**
     * @param y the y to set
     */
    public void setY(int y) {
	this.y = y;
    }


    /**
     * @return the w
     */
    public int getW() {
	return w;
    }


    /**
     * @param w the w to set
     */
    public void setW(int w) {
	this.w = w;
    }


    /**
     * @return the h
     */
    public int getH() {
	return h;
    }


    /**
     * @param h the h to set
     */
    public void setH(int h) {
	this.h = h;
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

}
