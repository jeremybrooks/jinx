/*
 * Jinx is Copyright 2010-2014 by Jeremy Brooks and Contributors
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
import net.jeremybrooks.jinx.response.Response;
import net.jeremybrooks.jinx.response.photos.geo.GeoPerms;
import net.jeremybrooks.jinx.response.photos.geo.Location;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by jeremyb on 7/21/14.
 */
public class PhotosGeoApiTest {
    private static PhotosGeoApi photosGeoApi;
    private static final String PHOTO_ID = "14069436767";
    private static final String PHOTO_FOR_WRITE_TEST = "14253477696";

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
//        JinxLogger.setLogger(new StdoutLogger());
        photosGeoApi = new PhotosGeoApi(jinx);
    }

    @Test
    public void testBatchCorrectLocation() throws Exception {
        // geo search has issues; commenting out for now
//        Float lat = new Float(37.901686);
//        Float lon = new Float(-122.302143);
//
//        Response response = photosGeoApi.batchCorrectLocation(lat, lon, 16, "SOoGK89UV7NIUdRWOQ", null);
//        assertNotNull(response);
//        assertEquals("ok", response.getStat());
//        assertEquals(0, response.getCode());
    }


    @Test
    public void testGetLocation() throws Exception {
        Location location = photosGeoApi.getLocation(PHOTO_ID, false);
        assertNotNull(location);
        assertEquals("ok", location.getStat());
        assertEquals(0, location.getCode());
        assertEquals(PHOTO_ID, location.getPhotoId());
        assertEquals("Albany", location.getLocalityName());
        assertEquals("California", location.getRegionName());
    }

    @Test
    public void testGetGeoPerms() throws Exception {
        GeoPerms geoPerms = photosGeoApi.getGeoPerms(PHOTO_ID);
        assertNotNull(geoPerms);
        assertEquals("ok", geoPerms.getStat());
        assertEquals(0, geoPerms.getCode());
        assertTrue(geoPerms.isPublic());
        assertFalse(geoPerms.isContact());
        assertFalse(geoPerms.isFriend());
        assertFalse(geoPerms.isFamily());
    }

    @Test
    public void testPhotosForLocation() throws Exception {
        // geo search has issues; commenting out for now
//        Float lat = new Float(37.901686);
//        Float lon = new Float(-122.302143);
//
//        Photos photos = photosGeoApi.photosForLocation(lat, lon, null, null, 0, 0);
//        assertNotNull(photos);
//        assertEquals("ok", photos.getStat());
//        assertEquals(0, photos.getCode());
//        assertNotNull(photos.getPhotoList());
//        assertTrue(photos.getPhotoList().size() > 0);
    }

    @Test
    public void testSetPerms() throws Exception {
        Response response = photosGeoApi.setPerms(PHOTO_ID, true, false, false, false);
        assertNotNull(response);
        assertEquals("ok", response.getStat());
        assertEquals(0, response.getCode());
    }

    @Test
    public void testSetCorrectContextRemove() throws Exception {
        Float lat = new Float(37.863487);
        Float lon = new Float(-122.317378);
        // set
        Response response = photosGeoApi.setLocation(PHOTO_FOR_WRITE_TEST, lat, lon, 6, null);
        assertNotNull(response);
        assertEquals("ok", response.getStat());
        assertEquals(0, response.getCode());

        // correct
        response = photosGeoApi.correctLocation(PHOTO_FOR_WRITE_TEST, "RoM6JlVUV7IzChu8UQ", null, null);
        assertNotNull(response);
        assertEquals("ok", response.getStat());
        assertEquals(0, response.getCode());

        // context
        response = photosGeoApi.setContext(PHOTO_FOR_WRITE_TEST, JinxConstants.GeoContext.outdoors);
        assertNotNull(response);
        assertEquals("ok", response.getStat());
        assertEquals(0, response.getCode());

        // remove
        response = photosGeoApi.removeLocation(PHOTO_FOR_WRITE_TEST);
        assertNotNull(response);
        assertEquals("ok", response.getStat());
        assertEquals(0, response.getCode());
    }
}
