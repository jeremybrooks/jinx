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

package net.jeremybrooks.jinx.response.photos.geo;

import com.google.gson.Gson;
import net.jeremybrooks.jinx.response.activity.ActivityResponseTest;
import org.junit.Test;

import java.io.InputStreamReader;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotNull;

/**
 * Created by jeremyb on 7/21/14.
 */
public class PhotosGeoTest {
    @Test
    public void testParseGetLocation() throws Exception {
        InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/photos/geo/sample_get_location.json"));
        Location location = new Gson().fromJson(reader, Location.class);
        reader.close();
        assertNotNull(location);
        assertEquals("ok", location.getStat());
        assertEquals(0, location.getCode());
        assertEquals("14654888764", location.getPhotoId());
        assertEquals(Float.valueOf(37.791125f), location.getLatitude());
        assertEquals(Float.valueOf(-122.399187f), location.getLongitude());
        assertEquals(Integer.valueOf(16), location.getAccuracy());
        assertEquals(Integer.valueOf(0), location.getContext());
        assertEquals("Financial District", location.getNeighbourhoodName());
        assertEquals("GddgqTpTUb8LgT93hw", location.getNeighbourhoodPlaceId());
        assertEquals("23512022", location.getNeighbourhoodWoeId());
        assertEquals("San Francisco", location.getLocalityName());
        assertEquals("7.MJR8tTVrIO1EgB", location.getLocalityPlaceId());
        assertEquals("2487956", location.getLocalityWoeId());
        assertEquals("San Francisco", location.getCountyName());
        assertEquals(".7sOmlRQUL9nK.kMzA", location.getCountyPlaceId());
        assertEquals("12587707", location.getCountyWoeId());
        assertEquals("California", location.getRegionName());
        assertEquals("NsbUWfBTUb4mbyVu", location.getRegionPlaceId());
        assertEquals("2347563", location.getRegionWoeId());
        assertEquals("United States", location.getCountryName());
        assertEquals("nz.gsghTUb4c2WAecA", location.getCountryPlaceId());
        assertEquals("23424977", location.getCountryWoeId());
        assertEquals("GddgqTpTUb8LgT93hw", location.getPlaceId());
        assertEquals("23512022", location.getWoeId());
    }

    @Test
    public void testParseGetPerms() throws Exception {
        InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/photos/geo/sample_get_perms.json"));
        GeoPerms geoPerms = new Gson().fromJson(reader, GeoPerms.class);
        reader.close();
        assertNotNull(geoPerms);
        assertEquals("ok", geoPerms.getStat());
        assertEquals(0, geoPerms.getCode());
        assertEquals("14654888764", geoPerms.getPhotoId());
        assertTrue(geoPerms.isPublic());
        assertFalse(geoPerms.isContact());
        assertFalse(geoPerms.isFriend());
        assertFalse(geoPerms.isFamily());
    }
}
