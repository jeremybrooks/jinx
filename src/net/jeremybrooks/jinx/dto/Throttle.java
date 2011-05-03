/*
 * Jinx is Copyright 2011 by Jeremy Brooks
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
public class Throttle {

    private int count;
    private String mode;
    private int remaining;

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
     * @return the mode
     */
    public String getMode() {
	return mode;
    }


    /**
     * @param mode the mode to set
     */
    public void setMode(String mode) {
	this.mode = mode;
    }


    /**
     * @return the remaining
     */
    public int getRemaining() {
	return remaining;
    }


    /**
     * @param remaining the remaining to set
     */
    public void setRemaining(int remaining) {
	this.remaining = remaining;
    }


    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder(this.getClass().getName());

	sb.append(" [ ");
	sb.append("count=").append(this.getCount()).append(" | ");
	sb.append("mode=").append(this.getMode()).append(" | ");
	sb.append("remaining=").append(this.getRemaining());
	sb.append(" ]");
	return sb.toString();
    }
}
