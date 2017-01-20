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
