/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.jeremybrooks.jinx.api;

import net.jeremybrooks.jinx.Setup;
import java.util.List;
import net.jeremybrooks.jinx.dto.License;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

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