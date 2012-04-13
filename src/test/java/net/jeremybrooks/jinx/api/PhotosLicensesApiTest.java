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

import net.jeremybrooks.jinx.Setup;
import net.jeremybrooks.jinx.dto.License;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author jeremyb
 */
public class PhotosLicensesApiTest {

    public PhotosLicensesApiTest() {
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
     * Test of getInstance method, of class PhotosLicensesApi.
     */
    @Test
    public void testGetInstance() {
	System.out.println("getInstance");
	PhotosLicensesApi result = PhotosLicensesApi.getInstance();
	assertNotNull(result);
    }


    /**
     * Test of getInfo method, of class PhotosLicensesApi.
     */
    @Test
    public void testGetInfo() throws Exception {
	System.out.println("getInfo");
	PhotosLicensesApi instance = PhotosLicensesApi.getInstance();
	List<License> result = instance.getInfo();
	assertNotNull(result);
	assertTrue(result.size() > 0);
    }


    /**
     * Test of setLicense method, of class PhotosLicensesApi.
     */
    @Test
    public void testSetLicense() throws Exception {
	System.out.println("setLicense");
	String photoId = "257059699";	    // old photo, good for testing
	String licenseId = "0";	// change from type 2; probably should run test a second time and change back

	PhotosLicensesApi instance = PhotosLicensesApi.getInstance();
	instance.setLicense(photoId, licenseId);

    }

}