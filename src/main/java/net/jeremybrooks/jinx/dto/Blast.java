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
 *
 * @author jeremyb
 */
public class Blast {
    private String dateBlastAdded;
    private String userId;

    private String text;


    /**
     * @return the dateBlastAdded
     */
    public String getDateBlastAdded() {
	return dateBlastAdded;
    }


    /**
     * @param dateBlastAdded the dateBlastAdded to set
     */
    public void setDateBlastAdded(String dateBlastAdded) {
	this.dateBlastAdded = dateBlastAdded;
    }


    /**
     * @return the userId
     */
    public String getUserId() {
	return userId;
    }


    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
	this.userId = userId;
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
	StringBuilder sb = new StringBuilder(this.getClass().getName());

	sb.append(" [ ");
	sb.append("dateBlastAdded=").append(this.getDateBlastAdded()).append(" | ");
	sb.append("userId=").append(this.getUserId()).append(" | ");
	sb.append("text=").append(this.getText());

	sb.append(" ]");
	return sb.toString();
    }
}
