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

package net.jeremybrooks.jinx.api;

import net.jeremybrooks.jinx.JinxConstants;
import net.jeremybrooks.jinx.Setup;
import net.jeremybrooks.jinx.dto.Location;
import net.jeremybrooks.jinx.dto.Perms;
import net.jeremybrooks.jinx.dto.Photos;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author jeremyb
 */
public class PhotosGeoApiTest {

    public PhotosGeoApiTest() {
    }


    @BeforeClass
    public static void setUpClass() throws Exception {
	Setup.doSetup();
    }


    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }


    /**
     * Test of getInstance method, of class PhotosGeoApi.
     */
    @Test
    public void testGetInstance() {
	System.out.println("getInstance");
	PhotosGeoApi result = PhotosGeoApi.getInstance();
	assertNotNull(result);
    }


    /**
     * Test of batchCorrectLocation method, of class PhotosGeoApi.
     */
    @Test
    public void testBatchCorrectLocation() throws Exception {
	// NOTE: Not sure how to test this effectively....
	assertTrue(true);
	/*System.out.println("batchCorrectLocation");
	float lat = 0.0F;
	float lon = 0.0F;
	int accuracy = 0;
	String placeId = "";
	String woeId = "";
	PhotosGeoApi instance = null;
	instance.batchCorrectLocation(lat, lon, accuracy, placeId, woeId);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
	 */
    }


    /**
     * Test of correctLocation method, of class PhotosGeoApi.
     */
    @Test
    public void testCorrectLocation() throws Exception {
	System.out.println("correctLocation");
	String photoId = "257059699";	// an old photo good for testing
	String placeId = "LrVuUf9TVr36PcaA";
	String woeId = null;
	PhotosGeoApi instance = PhotosGeoApi.getInstance();
	instance.correctLocation(photoId, placeId, woeId);
	// NOTE: This was failing in the Flickr API explorer as well,
	//       so there is little chance it will work here at this time,
	//       --jb (2011-06-17)
    }


    /**
     * Test of getLocation method, of class PhotosGeoApi.
     */
    @Test
    public void testGetLocation() throws Exception {
	System.out.println("getLocation");
	String photoId = "2333837307";
	PhotosGeoApi instance = PhotosGeoApi.getInstance();
	Location result = instance.getLocation(photoId);
	/*
	 * net.jeremybrooks.jinx.dto.Location [ photoId=2333837307 | latitude=0.0
	 | longitude=0.0 | accuracy=16 | context=0 | placeId=LrVuUf9TVr36PcaA |
	 woeId=2479714 | localityPlaceId=LrVuUf9TVr36PcaA | localityWoeId=2479714 |
	 locality=Redwood City | countyPlaceId=nJQqFhtQUL9qTY2Saw | countyWoeId=12587710
	 | county=San Mateo | regionPlaceId=NsbUWfBTUb4mbyVu | regionWoeId=2347563 |
	 region=California | countryPlaceId=nz.gsghTUb4c2WAecA | countryWoeId=23424977 |
	 country=United States ]
	 */
	assertNotNull(result);
	assertEquals("LrVuUf9TVr36PcaA", result.getPlaceId());
	assertEquals("2479714", result.getWoeId());
    }


    


    /**
     * Test of getPhotosForLocation method, of class PhotosGeoApi.
     */
    @Test
    public void testGetPhotosForLocation_float_float() throws Exception {
	System.out.println("getPhotosForLocation");
	float lat = 37.775F;
	float lon = -122.4183333F;
	PhotosGeoApi instance = PhotosGeoApi.getInstance();
	Photos result = instance.getPhotosForLocation(lat, lon);
	assertNotNull(result);
    }


    /**
     * Test of getPhotosForLocation method, of class PhotosGeoApi.
     */
    @Test
    public void testGetPhotosForLocation_6args() throws Exception {
	System.out.println("getPhotosForLocation");
	float lat = 37.775F;
	float lon = -122.4183333F;
	int accuracy = 11;
	List<String> extras = null;
	int page = 0;
	int perPage = 50;
	PhotosGeoApi instance = PhotosGeoApi.getInstance();
	Photos result = instance.getPhotosForLocation(lat, lon, accuracy, extras, page, perPage);

	assertNotNull(result);
	assertEquals(50, result.getPhotos().size());
	// NOTE: This was failing in the Flickr API explorer as well,
	//       so there is little chance it will work here at this time,
	//       --jb (2011-06-17)
    }


    /**
     * Test of setContext method, of class PhotosGeoApi.
     */
    @Test
    public void testSetContext() throws Exception {
	System.out.println("setContext");
	String photoId = "257059699";	// an old photo good for testing
	String context = JinxConstants.GEO_CONTEXT_INDOORS;
	PhotosGeoApi instance = PhotosGeoApi.getInstance();
	instance.setContext(photoId, context);
	// NOTE: This was failing in the Flickr API explorer as well,
	//       so there is little chance it will work here at this time,
	//       --jb (2011-06-17)
    }


    /**
     * Test of setLocation method, of class PhotosGeoApi.
     */
    @Test
    public void testSetLocation() throws Exception {
	System.out.println("setLocation");
	String photoId = "257059699";	// an old photo good for testing
	float lat = 37.775F;
	float lon = -122.4183333F;
	int accuracy = 16;
	String context = JinxConstants.GEO_CONTEXT_OUTDOORS;
	PhotosGeoApi instance = PhotosGeoApi.getInstance();
	instance.setLocation(photoId, lat, lon, accuracy, context);
    }


    /**
     * Test of setPerms method, of class PhotosGeoApi.
     */
    @Test
    public void testSetPerms() throws Exception {
	System.out.println("setPerms");
	String photoId = "257059699";	// an old photo good for testing
	boolean isPublic = true;
	boolean isContact = true;
	boolean isFriend = true;
	boolean isFamily = true;
	PhotosGeoApi instance = PhotosGeoApi.getInstance();
	instance.setPerms(photoId, isPublic, isContact, isFriend, isFamily);
    }


    /**
     * Test of getPerms method, of class PhotosGeoApi.
     */
    @Test
    public void testGetPerms() throws Exception {
	System.out.println("getPerms");
	String photoId = "2333837307";
	PhotosGeoApi instance = PhotosGeoApi.getInstance();
	Perms result = instance.getPerms(photoId);
	assertNotNull(result);
    }


    /**
     * Test of removeLocation method, of class PhotosGeoApi.
     */
    @Test
    public void testRemoveLocation() throws Exception {
	System.out.println("removeLocation");
	String photoId = "257059699";	// an old photo good for testing
	PhotosGeoApi instance = PhotosGeoApi.getInstance();
	instance.removeLocation(photoId);
    }



    

}