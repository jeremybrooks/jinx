/*
 * Jinx is Copyright 2010-2023 by Jeremy Brooks and Contributors
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

package net.jeremybrooks.jinx.response.places;

import net.jeremybrooks.jinx.response.Response;

/**
 * @author Jeremy Brooks
 */
public class PlaceInfo extends Response {
  private static final long serialVersionUID = 6075624131731819459L;
  private Place place;

  public Place getPlace() {
    return place;
  }

  @Override
  public String toString() {
    return "net.jeremybrooks.jinx.response.places.PlaceInfo{" +
        "place=" + place +
        '}';
  }
}
