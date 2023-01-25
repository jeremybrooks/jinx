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

import com.google.gson.Gson;
import net.jeremybrooks.jinx.response.activity.ActivityResponseTest;
import net.jeremybrooks.jinx.response.photos.Tag;
import net.jeremybrooks.jinx.response.tags.Tags;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Properties;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

/**
 * @author Jeremy Brooks
 */
public class PlaceTest {

  private static Properties polylines;

  /**
   * Load the polylines.properties file.
   * The properties file holds the (very very very) long polylines values that are expected to be
   * in the classes created from the JSON documents. They are in a separate file for the sake of
   * convenience.
   *
   * @throws Exception if the properties file cannot be loaded.
   */
  @BeforeClass
  public static void beforeClass () throws Exception {
    polylines = new Properties();
    InputStream in = ActivityResponseTest.class.getResourceAsStream("/response/places/polylines.properties");
    polylines.load(in);
    in.close();
  }

  @Test
  public void testGetInfo() throws Exception {
    InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/places/sample_get_info.json"));
    PlaceInfo placeInfo = new Gson().fromJson(reader, PlaceInfo.class);
    reader.close();

    assertNotNull(placeInfo);
    assertEquals("ok", placeInfo.getStat());

    Place place = placeInfo.getPlace();
    assertNotNull(place);

    assertEquals("NsbUWfBTUb4mbyVu", place.getPlaceId());
    assertEquals("2347563", place.getWoeId());
    assertEquals(Float.valueOf(37.271f), place.getLatitude());
    assertEquals(Float.valueOf(-119.270f), place.getLongitude());
    assertEquals("/United+States/California", place.getPlaceUrl());
    assertEquals("region", place.getPlaceType());
    assertEquals(Integer.valueOf(8), place.getPlaceTypeId());
    assertEquals("America/Los_Angeles", place.getTimezone());
    assertEquals("California, US, United States", place.getName());
    assertEquals("California", place.getWoeName());
    assertTrue(place.isHasShapeData());

    Place.Region region = place.getRegion();
    assertNotNull(region);
    assertEquals("California, US, United States", region.getContent());
    assertEquals("NsbUWfBTUb4mbyVu", region.getPlaceId());
    assertEquals("2347563", region.getWoeId());
    assertEquals(Float.valueOf(37.271f), region.getLatitude());
    assertEquals(Float.valueOf(-119.270f), region.getLongitude());
    assertEquals("/United+States/California", region.getPlaceUrl());

    Place.Country country = place.getCountry();
    assertNotNull(country);
    assertEquals("United States", country.getContent());
    assertEquals("nz.gsghTUb4c2WAecA", country.getPlaceId());
    assertEquals("23424977", country.getWoeId());
    assertEquals(Float.valueOf(37.167f), country.getLatitude());
    assertEquals(Float.valueOf(-95.845f), country.getLongitude());
    assertEquals("/United+States", country.getPlaceUrl());


    Shape shapeData = place.getShape();
    assertNotNull(shapeData);
    assertEquals(Long.valueOf(1292570642), shapeData.getCreated());
    assertEquals(Float.valueOf(0.1220703125f), shapeData.getAlpha());
    assertEquals(Integer.valueOf(2156271), shapeData.getCountPoints());
    assertEquals(Integer.valueOf(178), shapeData.getCountEdges());

    assertNotNull(shapeData.getPolylines());
    assertEquals(1, shapeData.getPolylines().size());
    assertEquals(polylines.getProperty("test.getInfo"), shapeData.getPolylines().get(0).getContent());
    assertFalse(shapeData.isHasDonuthole());
    assertFalse(shapeData.isDonuthole());
    assertEquals("http://farm6.static.flickr.com/5081/shapefiles/2347563_20101217_98f440022d.tar.gz", shapeData.getShapefileUrl());
  }

  @Test
  public void testGetInfoByUrl() throws Exception {
    InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/places/sample_get_info_by_url.json"));
    PlaceInfo placeInfo = new Gson().fromJson(reader, PlaceInfo.class);
    reader.close();

    assertNotNull(placeInfo);
    assertEquals("ok", placeInfo.getStat());

    Place place = placeInfo.getPlace();
    assertNotNull(place);

    assertEquals("nY7EoSRSV7kW", place.getPlaceId());
    assertEquals("3534", place.getWoeId());
    assertEquals(Float.valueOf(45.536f), place.getLatitude());
    assertEquals(Float.valueOf(-73.625f), place.getLongitude());
    assertEquals("/Canada/Quebec/Montr%C3%A9al", place.getPlaceUrl());
    assertEquals("locality", place.getPlaceType());
    assertEquals(Integer.valueOf(7), place.getPlaceTypeId());
    assertEquals("America/Montreal", place.getTimezone());
    assertEquals( "Montreal, Quebec, Canada", place.getName());
    assertEquals("Montreal", place.getWoeName());

    Place.Locality locality = place.getLocality();
    assertNotNull(locality);
    assertEquals("Montreal, Quebec, Canada", locality.getContent());
    assertEquals("nY7EoSRSV7kW", locality.getPlaceId());
    assertEquals("3534", locality.getWoeId());
    assertEquals(Float.valueOf(45.536f), locality.getLatitude());
    assertEquals(Float.valueOf(-73.625f), locality.getLongitude());
    assertEquals("/Canada/Quebec/Montr%C3%A9al", locality.getPlaceUrl());

    Place.County county = place.getCounty();
    assertNotNull(county);
    assertEquals("Montreal, Quebec, Canada", county.getContent());
    assertEquals("D8x2nD5TW7mCt77vJw", county.getPlaceId());
    assertEquals("29375198", county.getWoeId());
    assertEquals(Float.valueOf(45.553f), county.getLatitude());
    assertEquals(Float.valueOf(-73.600f), county.getLongitude());
    assertEquals("/D8x2nD5TW7mCt77vJw", county.getPlaceUrl());

    Place.Region region = place.getRegion();
    assertNotNull(region);
    assertEquals("Quebec, Canada", region.getContent());
    assertEquals("dSpA1ldTUb7syPFr", region.getPlaceId());
    assertEquals("2344924", region.getWoeId());
    assertEquals(Float.valueOf(53.891f), region.getLatitude());
    assertEquals(Float.valueOf(-68.431f), region.getLongitude());
    assertEquals("/Canada/Quebec", region.getPlaceUrl());

    Place.Country country = place.getCountry();
    assertNotNull(country);
    assertEquals("Canada", country.getContent());
    assertEquals("b9iFoOpTUb6KZ3MPGQ", country.getPlaceId());
    assertEquals("23424775", country.getWoeId());
    assertEquals(Float.valueOf(56.954f), country.getLatitude());
    assertEquals(Float.valueOf(-98.308f), country.getLongitude());
    assertEquals("/Canada", country.getPlaceUrl());

    assertTrue(place.isHasShapeData());

    Shape shape = place.getShape();
    assertNotNull(shape);
    assertEquals(Long.valueOf(1292565208), shape.getCreated());
    assertEquals(Float.valueOf(0.00823974609375f), shape.getAlpha());
    assertEquals(Integer.valueOf(82688), shape.getCountPoints());
    assertEquals(Integer.valueOf(46), shape.getCountEdges());
    assertEquals(polylines.getProperty("test.getInfoByUrl"), shape.getPolylines().get(0).getContent());
    assertTrue(shape.isHasDonuthole());
    assertFalse(shape.isDonuthole());
    assertEquals(shape.getShapefileUrl(), "http://farm6.static.flickr.com/5008/shapefiles/3534_20101217_1869920287.tar.gz");
  }

  @Test
  public void testFind() throws Exception {
    InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/places/sample_find.json"));
    Places places = new Gson().fromJson(reader, Places.class);
    reader.close();
    assertNotNull(places);
    assertNotNull(places.getPlaces());
    assertEquals("Idaho", places.getQuery());
    assertEquals(Integer.valueOf(6), places.getTotal());
    assertEquals(Integer.valueOf(6), (Integer)places.getPlaces().size());
    Place place = places.getPlaces().get(0);
    assertEquals("Ka1owT5TUb5H6wqX", place.getPlaceId());
    assertEquals("2347571", place.getWoeId());
    assertEquals(Float.valueOf(45.494f), place.getLatitude());
    assertEquals(Float.valueOf(-114.143f), place.getLongitude());
    assertEquals("/United+States/Idaho", place.getPlaceUrl());
    assertEquals("region", place.getPlaceType());
    assertEquals(Integer.valueOf(8), place.getPlaceTypeId());
    assertEquals("Idaho, United States", place.getContent());
    assertEquals("Idaho", place.getWoeName());
  }

  @Test
  public void testFindLatLon() throws Exception {
    InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/places/sample_find_lat_lon.json"));
    Places places = new Gson().fromJson(reader, Places.class);
    reader.close();
    assertNotNull(places);
    assertNotNull(places.getPlaces());
    assertEquals(Integer.valueOf(1), places.getTotal());
    assertEquals(Integer.valueOf(16), places.getAccuracy());
    assertEquals(Float.valueOf(37.76513627957266f), places.getLatitude());
    assertEquals(Float.valueOf(-122.42020770907402f), places.getLongitude());
    Place place = places.getPlaces().get(0);
    assertEquals("HMGdMeJTUb_Sf_5E2g", place.getPlaceId());
    assertEquals("23512048", place.getWoeId());
    assertEquals(Float.valueOf(37.764f), place.getLatitude());
    assertEquals(Float.valueOf(-122.425f), place.getLongitude());
    assertEquals("/United+States/California/San+Francisco/Mission+Dolores", place.getPlaceUrl());
    assertEquals("neighbourhood", place.getPlaceType());
    assertEquals(Integer.valueOf(22), place.getPlaceTypeId());
    assertEquals("America/Los_Angeles", place.getTimezone());
    assertEquals("Mission Dolores, San Francisco, CA, US, United States", place.getName());
    assertEquals("Mission Dolores", place.getWoeName());
  }

  @Test
  public void testGetChildrenWithPhotosPublic() throws Exception {
    InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/places/sample_get_children_with_photos_public.json"));
    Places places = new Gson().fromJson(reader, Places.class);
    reader.close();
    assertNotNull(places);
    assertNotNull(places.getPlaces());
    assertEquals(Integer.valueOf(99), places.getTotal());
    Place place = places.getPlaces().get(0);
    assertEquals( "GddgqTpTUb8LgT93hw", place.getPlaceId());
    assertEquals( "23512022", place.getWoeId());
    assertEquals(Float.valueOf(37.792f), place.getLatitude());
    assertEquals(Float.valueOf(-122.400f), place.getLongitude());
    assertEquals("/United+States/California/San+Francisco/Financial+District", place.getPlaceUrl());
    assertEquals("neighbourhood", place.getPlaceType());
    assertEquals(Integer.valueOf(22), place.getPlaceTypeId());
    assertEquals("America/Los_Angeles", place.getTimezone());
    assertEquals("Financial District, San Francisco, CA, US, United States", place.getContent());
    assertEquals("Financial District", place.getWoeName());
    assertEquals(Integer.valueOf(130215), place.getPhotoCount());
  }

  @Test
  public void testGetPlaceTypes() throws Exception {
    InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/places/sample_get_place_types.json"));
    PlaceTypes placeTypes = new Gson().fromJson(reader, PlaceTypes.class);
    assertNotNull(placeTypes);
    List<PlaceTypes.PlaceType> list = placeTypes.getPlaceTypes();
    assertNotNull(list);
    assertEquals(6, list.size());
    PlaceTypes.PlaceType type =list.get(3);
    assertEquals(Integer.valueOf(8), type.getPlaceTypeId());
    assertEquals("region", type.getTypeName());
  }

  @Test
  public void testGetShapeHistory() throws Exception {
    InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/places/sample_get_shape_history.json"));
    ShapeHistory shapeHistory = new Gson().fromJson(reader, ShapeHistory.class);
    assertNotNull(shapeHistory);
    assertEquals(Integer.valueOf(6), shapeHistory.getTotal());
    assertEquals("23512048", shapeHistory.getWoeId());
    assertEquals("HMGdMeJTUb_Sf_5E2g", shapeHistory.getPlaceId());
    assertEquals("neighbourhood", shapeHistory.getPlaceType());
    assertEquals(Integer.valueOf(22), shapeHistory.getPlaceTypeId());
    List<Shape> shapeList = shapeHistory.getShapeList();
    assertNotNull(shapeList);
    Shape shape = shapeList.get(0);
    assertEquals(Long.valueOf(1292367866), shape.getCreated());
    assertEquals(Float.valueOf(0.00001f), shape.getAlpha());
    assertEquals(Integer.valueOf(6762), shape.getCountPoints());
    assertEquals(Integer.valueOf(37), shape.getCountEdges());
    assertEquals(polylines.getProperty("test.getShapeHistory"), shape.getPolylines().get(0).getContent());
    assertFalse(shape.isDonuthole());
    assertEquals("http://farm6.static.flickr.com/5241/shapefiles/23512048_20101214_4480f6f8e1.tar.gz", shape.getShapefileUrl());
  }

  @Test
  public void testGetTopPlacesList() throws Exception {
    InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/places/sample_get_top_places_list.json"));
    Places places = new Gson().fromJson(reader, Places.class);
    assertNotNull(places);
    assertEquals(Integer.valueOf(100), places.getTotal());
    assertEquals(Long.valueOf(1484524800), places.getDateStart());
    assertEquals(Long.valueOf(1484611199), places.getDateStop());
    Place place = places.getPlaces().get(0);
    assertEquals("2eIY2QFTVr_DwWZNLg", place.getPlaceId());
    assertEquals("24554868", place.getWoeId());
    assertEquals(Float.valueOf(52.883f), place.getLatitude());
    assertEquals(Float.valueOf(-1.974f), place.getLongitude());
    assertEquals("/United+Kingdom/England", place.getPlaceUrl());
    assertEquals("region", place.getPlaceType());
    assertEquals(Integer.valueOf(8), place.getPlaceTypeId());
    assertEquals("Europe/London", place.getTimezone());
    assertEquals("England, GB, United Kingdom", place.getContent());
    assertEquals("England", place.getWoeName());
    assertEquals(Integer.valueOf(3406), place.getPhotoCount());
  }

  @Test
  public void testGetPlacesForBoundingBox() throws Exception {
    InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/places/sample_get_places_for_bounding_box.json"));
    Places places = new Gson().fromJson(reader, Places.class);
    assertNotNull(places);
    assertEquals("region", places.getPlaceType());
    assertEquals(Integer.valueOf(1), places.getTotal());
    assertEquals(Integer.valueOf(1), places.getPages());
    assertEquals(Integer.valueOf(1), places.getPage());
    assertEquals("-122.42307100000001,37.773779,-122.381071,37.815779", places.getBoundingBox());
    Place place = places.getPlaces().get(0);
    assertEquals( "NsbUWfBTUb4mbyVu", place.getPlaceId());
    assertEquals( "2347563", place.getWoeId());
    assertEquals(Float.valueOf(37.271f), place.getLatitude());
    assertEquals(Float.valueOf(-119.270f), place.getLongitude());
    assertEquals("/United+States/California", place.getPlaceUrl());
    assertEquals("region", place.getPlaceType());
    assertEquals(Integer.valueOf(8), place.getPlaceTypeId());
    assertEquals( "America/Los_Angeles", place.getTimezone());
    assertEquals("California, US, United States", place.getContent());
    assertEquals("California", place.getWoeName());
  }

  @Test
  public void testPlacesForContacts() throws Exception {
    InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/places/sample_places_for_contacts.json"));
    Places places = new Gson().fromJson(reader, Places.class);
    assertNotNull(places);
    assertEquals(Integer.valueOf(51), places.getTotal());
    Place place = places.getPlaces().get(0);
    assertEquals("NsbUWfBTUb4mbyVu", place.getPlaceId());
    assertEquals( "2347563", place.getWoeId());
    assertEquals(Float.valueOf(37.271f), place.getLatitude());
    assertEquals(Float.valueOf(-119.270f), place.getLongitude());
    assertEquals("/United+States/California", place.getPlaceUrl());
    assertEquals("region", place.getPlaceType());
    assertEquals(Integer.valueOf(8), place.getPlaceTypeId());
    assertEquals("America/Los_Angeles", place.getTimezone());
    assertEquals("California, US, United States", place.getContent());
    assertEquals("California", place.getWoeName());
    assertEquals(Integer.valueOf(306211), place.getPhotoCount());
  }

  @Test
  public void testPlacesForTags() throws Exception {
    InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/places/sample_places_for_tags.json"));
    Places places = new Gson().fromJson(reader, Places.class);
    assertNotNull(places);
    assertEquals(Integer.valueOf(75), places.getTotal());
    Place place = places.getPlaces().get(0);
    assertEquals("byMt1GpTWrhtqOpahg", place.getPlaceId());
    assertEquals( "28288823", place.getWoeId());
    assertEquals(Float.valueOf(40.760f), place.getLatitude());
    assertEquals(Float.valueOf(-73.983f), place.getLongitude());
    assertEquals("/United+States/New+York/New+York/Theater+District", place.getPlaceUrl());
    assertEquals("neighbourhood", place.getPlaceType());
    assertEquals(Integer.valueOf(22), place.getPlaceTypeId());
    assertEquals("America/New_York", place.getTimezone());
    assertEquals("Theater District, New York, NY, US, United States", place.getContent());
    assertEquals("Theater District", place.getWoeName());
    assertEquals(Integer.valueOf(2670), place.getPhotoCount());
  }

  @Test
  public void testPlacesForUser() throws Exception {
    InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/places/sample_places_for_user.json"));
    Places places = new Gson().fromJson(reader, Places.class);
    assertNotNull(places);
    assertEquals(Integer.valueOf(70), places.getTotal());
    Place place = places.getPlaces().get(0);
    assertEquals("KomQdIRUV7IFfyO5Xw", place.getPlaceId());
    assertEquals( "55861471", place.getWoeId());
    assertEquals(Float.valueOf(37.909f), place.getLatitude());
    assertEquals(Float.valueOf(-122.311f), place.getLongitude());
    assertEquals("/United+States/California/Richmond/Richmond+Annex", place.getPlaceUrl());
    assertEquals("neighbourhood", place.getPlaceType());
    assertEquals(Integer.valueOf(22), place.getPlaceTypeId());
    assertEquals("America/Los_Angeles", place.getTimezone());
    assertEquals("Richmond Annex, Richmond, CA, US, United States", place.getContent());
    assertEquals("Richmond Annex", place.getWoeName());
    assertEquals(Integer.valueOf(66), place.getPhotoCount());
  }

  @Test
  public void testTagsForPlace() throws Exception {
    InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/places/sample_tags_for_place.json"));
    Tags tags = new Gson().fromJson(reader, Tags.class);
    assertNotNull(tags);
    assertEquals(Integer.valueOf(100), tags.getTotal());
    Tag tag = tags.getTagList().get(0);
    assertEquals("portrait", tag.getTag());
    assertEquals(Integer.valueOf(8809), tag.getCount());
  }
}
