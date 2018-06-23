/*
 * Jinx is Copyright 2010-2018 by Jeremy Brooks and Contributors
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

package net.jeremybrooks.jinx.api;

import net.jeremybrooks.jinx.Jinx;
import net.jeremybrooks.jinx.JinxConstants;
import net.jeremybrooks.jinx.OAuthAccessToken;
import net.jeremybrooks.jinx.logger.JinxLogger;
import net.jeremybrooks.jinx.logger.StdoutLogger;
import net.jeremybrooks.jinx.response.places.Place;
import net.jeremybrooks.jinx.response.places.PlaceInfo;
import net.jeremybrooks.jinx.response.places.PlaceTypes;
import net.jeremybrooks.jinx.response.places.Places;
import net.jeremybrooks.jinx.response.places.ShapeHistory;
import net.jeremybrooks.jinx.response.tags.Tags;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Jeremy Brooks
 */
public class PlacesApiTest {

  private static PlacesApi placesApi;

  @BeforeClass
  public static void beforeClass() throws Exception {
    Properties p = new Properties();
    p.load(OAuthApiTest.class.getResourceAsStream("/response/auth/secret.properties"));
    String filename = p.getProperty("path.to.oauth.token");
    assertNotNull(filename);
    File file = new File(filename);
    assertTrue(file.exists());
    OAuthAccessToken oAuthAccessToken = new OAuthAccessToken();
    oAuthAccessToken.load(new FileInputStream(file));
    assertNotNull(oAuthAccessToken);
    Jinx jinx = new Jinx(p.getProperty("flickr.key"), p.getProperty("flickr.secret"), oAuthAccessToken);
//        jinx.setVerboseLogging(true);
    JinxLogger.setLogger(new StdoutLogger());
    placesApi = new PlacesApi(jinx);
  }

  @Test
  public void testFind() throws Exception {
    Places places = placesApi.find("California");
    assertNotNull(places);
    assertEquals("California", places.getQuery());
    assertNotNull(places.getPlaces());
    Place place = places.getPlaces().get(0);
    assertEquals("California", place.getWoeName());
  }

  @Test
  public void testFindByLatLon() throws Exception {
    Float latitude = 37.765f;
    Float longitude = -122.4202f;
    Places places = placesApi.findByLatLon(latitude, longitude, 16);
    assertNotNull(places);
    assertNotNull(places.getPlaces());
    assertEquals("Mission District", places.getPlaces().get(0).getWoeName());
  }

  @Test
  public void testGetChildrenWithPhotosPublic() throws Exception {
    String placesId = "7.MJR8tTVrIO1EgB";
    String woeId = "2487956";
    Places places = placesApi.getChildrenWithPhotosPublic(placesId, null);
    assertNotNull(places);
    assertNotNull(places.getPlaces());
    assertTrue(places.getPlaces().size() > 0) ;
    places = placesApi.getChildrenWithPhotosPublic(null, woeId);
    assertNotNull(places);
    assertNotNull(places.getPlaces());
    assertTrue(places.getPlaces().size() > 0) ;
  }

  @Test
  public void testGetInfo() throws Exception {
    String placesId = "7.MJR8tTVrIO1EgB";
    String woeId = "2487956";
    PlaceInfo placeInfo = placesApi.getInfo(null, woeId);
    assertNotNull(placeInfo);
    assertEquals("San Francisco", placeInfo.getPlace().getWoeName());

    placeInfo = placesApi.getInfo(placesId, null);
    assertNotNull(placeInfo);
    assertEquals("San Francisco", placeInfo.getPlace().getWoeName());
  }

  @Test
  public void testGetInfoByUrl() throws Exception {
    PlaceInfo placeInfo = placesApi.getInfoByUrl("/United States/California/San Francisco");
    assertNotNull(placeInfo);
    assertEquals("San Francisco", placeInfo.getPlace().getWoeName());
  }

  @Test
  public void testGetPlaceTypes() throws Exception {
    PlaceTypes placeTypes = placesApi.getPlaceTypes();
    assertNotNull(placeTypes);
    assertTrue(placeTypes.getPlaceTypes().size() > 0);
  }

  @Test
  public void testGetShapeHistory() throws Exception {
    ShapeHistory shapeHistory = placesApi.getShapeHistory(null, "2487956");
    assertNotNull(shapeHistory);
    assertTrue(shapeHistory.getTotal() > 0);
    assertNotNull(shapeHistory.getShapeList());
    assertTrue(shapeHistory.getShapeList().size() > 0);
  }

  @Test
  public void testGetTopPlaces() throws Exception {
    String woeId = "2487956";
    Places places = placesApi.getTopPlaces(JinxConstants.PlaceTypeId.country, null, null, null);
    assertNotNull(places);
    assertTrue(places.getPlaces().size() > 0);
    places = placesApi.getTopPlaces(JinxConstants.PlaceTypeId.neighbourhood, null, null, woeId);
    assertNotNull(places);
    assertTrue(places.getPlaces().size() > 0);
  }

  @Test
  public void testGetPlacesForBoundingBox() throws Exception {
    String bbox = "-122.42307100000001,37.773779,-122.381071,37.815779";
    Places places = placesApi.getPlacesForBoundingBox(bbox, JinxConstants.PlaceTypeId.region);
    assertNotNull(places);
    assertNotNull(places.getPlaces());
    assertTrue(places.getPlaces().size() > 0);
  }

  @Test
  public void testGetPlacesForContacts() throws Exception {
    String placeId = "NsbUWfBTUb4mbyVu";
    Places places = placesApi.getPlacesForContacts(JinxConstants.PlaceTypeId.locality, placeId, null,
        null, JinxConstants.Contacts.all, null, null, null, null);
    assertNotNull(places);
    assertNotNull(places.getPlaces());
    assertTrue(places.getPlaces().size() > 0);
  }

  @Test
  public void testGetPlacesForTags() throws Exception {
    String placeId = "NsbUWfBTUb4mbyVu";
    List<String> tags = new ArrayList<>();
    tags.add("neon");
    Places places = placesApi.getPlacesForTags(JinxConstants.PlaceTypeId.country, placeId, null,
        null, tags, JinxConstants.TagMode.any, null, null, null, null,
        null, null);
    assertNotNull(places);
    assertNotNull(places.getPlaces());
    assertTrue(places.getPlaces().size() > 0);
  }

  @Test
  public void testGetPlacesForUser() throws Exception {
    String placeId = "NsbUWfBTUb4mbyVu";
    Places places = placesApi.getPlacesForUser(JinxConstants.PlaceTypeId.neighbourhood, placeId, null,
        null, null, null, null, null);
    assertNotNull(places);
    assertNotNull(places.getPlaces());
    assertTrue(places.getPlaces().size() > 0);
  }

  @Test
  public void testResolvePlaceId() throws Exception {
    String placesId = "7.MJR8tTVrIO1EgB";
    PlaceInfo placeInfo = placesApi.resolvePlaceId(placesId);
    assertNotNull(placeInfo);
    assertEquals("San Francisco", placeInfo.getPlace().getWoeName());
  }

  @Test
  public void testResolvePlaceUrl() throws Exception {
    PlaceInfo placeInfo = placesApi.resolvePlaceUrl("/United States/California/San Francisco");
    assertNotNull(placeInfo);
    assertEquals("San Francisco", placeInfo.getPlace().getWoeName());
  }

  @Test
  public void testGetTagsForPlace() throws Exception {
    String placesId = "7.MJR8tTVrIO1EgB";
    Tags tags = placesApi.getTagsForPlace(placesId, null, null, null, null, null);
    assertNotNull(tags);
    assertNotNull(tags.getTagList());
    assertTrue(tags.getTagList().size() > 0);
  }
}
