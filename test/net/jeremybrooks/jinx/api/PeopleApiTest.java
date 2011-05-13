/*
 * Jinx is Copyright 2010 by Jeremy Brooks
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

import net.jeremybrooks.jinx.dto.Photo;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.jeremybrooks.jinx.JinxConstants;
import net.jeremybrooks.jinx.Setup;
import net.jeremybrooks.jinx.dto.Groups;
import net.jeremybrooks.jinx.dto.Person;
import net.jeremybrooks.jinx.dto.Photos;
import net.jeremybrooks.jinx.dto.User;
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
public class PeopleApiTest {

    private String nsid = "85853333@N00";
    private String username = "Jeremy Brooks";

    public PeopleApiTest() {
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
     * Test of getInstance method, of class PeopleApi.
     */
    @Test
    public void testGetInstance() {
	System.out.println("getInstance");
	PeopleApi result = PeopleApi.getInstance();
	assertNotNull(result);
    }


    /**
     * Test of findByEmail method, of class PeopleApi.
     */
    @Test
    public void testFindByEmail() throws Exception {
	System.out.println("findByEmail");
	String email = "whirljackk@yahoo.com";
	PeopleApi instance = PeopleApi.getInstance();
	User result = instance.findByEmail(email);
	assertNotNull(result);
	assertEquals(username, result.getUsername());
	assertEquals(nsid, result.getNsid());
    }


    /**
     * Test of findByUsername method, of class PeopleApi.
     */
    @Test
    public void testFindByUsername() throws Exception {
	System.out.println("findByUsername");
	PeopleApi instance = PeopleApi.getInstance();
	User result = instance.findByUsername(username);
	assertNotNull(result);
	assertEquals(username, result.getUsername());
	assertEquals(nsid, result.getNsid());
    }


    /**
     * Test of getInfo method, of class PeopleApi.
     */
    @Test
    public void testGetInfo() throws Exception {
	System.out.println("getInfo");
	String userId = "51035555243@N01";  // Thomas Hawk
	PeopleApi instance = PeopleApi.getInstance();
	Person result = instance.getInfo(userId);
	assertNotNull(result);
	assertEquals("Thomas Hawk", result.getUsername());
    }


    /**
     * Test of getPhotos method, of class PeopleApi.
     */
    @Test
    public void testGetPhotos_String() throws Exception {
	System.out.println("getPhotos");
	PeopleApi instance = PeopleApi.getInstance();
	Photos result = instance.getPhotos(nsid);
	assertNotNull(result);
	assertTrue(result.getPhotos().size() > 0);
    }


    /**
     * Test of getPhotos method, of class PeopleApi.
     */
    @Test
    public void testGetPhotos_11args() throws Exception {
	System.out.println("getPhotos");
	String userId = nsid;
	String safeSearch = JinxConstants.SAFE_SEARCH_SAFE;
	Date minUploadDate = null;
	Date maxUploadDate = null;
	Date minTakenDate = null;
	Date maxTakenDate = null;
	String contentType = "";
	String privacyFilter = "";
	List<String> extras = new ArrayList<String>();
	extras.add(JinxConstants.EXTRAS_DESCRIPTION);
	extras.add(JinxConstants.EXTRAS_DATE_UPLOAD);
	extras.add(JinxConstants.EXTRAS_DATE_TAKEN);
	int perPage = 10;
	int page = 0;
	PeopleApi instance = PeopleApi.getInstance();
	Photos result = instance.getPhotos(userId, safeSearch, minUploadDate, maxUploadDate, minTakenDate, maxTakenDate, contentType, privacyFilter, extras, perPage, page);
	assertNotNull(result);
	assertEquals(perPage, result.getPhotos().size());
	for (Photo photo : result.getPhotos()) {
	    assertNotNull(photo.getDateUploaded());
	}
    }


    /**
     * Test of getPhotosOf method, of class PeopleApi.
     */
    @Test
    public void testGetPhotosOf_String() throws Exception {
	System.out.println("getPhotosOf");
	String userId = nsid;
	PeopleApi instance = PeopleApi.getInstance();
	Photos result = instance.getPhotosOf(userId);
	assertNotNull(result);
	assertTrue(result.getPhotos().size() > 0);
    }


    /**
     * Test of getPhotosOf method, of class PeopleApi.
     */
    @Test
    public void testGetPhotosOf_5args() throws Exception {
	System.out.println("getPhotosOf");
	String userId = nsid;
	String ownerId = "";
	List<String> extras = new ArrayList<String>();
	extras.add(JinxConstants.EXTRAS_DESCRIPTION);
	extras.add(JinxConstants.EXTRAS_DATE_UPLOAD);
	extras.add(JinxConstants.EXTRAS_DATE_TAKEN);
	int perPage = 10;
	int page = 0;
	PeopleApi instance = PeopleApi.getInstance();
	Photos result = instance.getPhotosOf(userId, ownerId, extras, perPage, page);
	assertNotNull(result);
	assertEquals(perPage, result.getPhotos().size());
	for (Photo photo : result.getPhotos()) {
	    assertNotNull(photo.getDateUploaded());
	}
    }


    /**
     * Test of getPublicGroups method, of class PeopleApi.
     */
    @Test
    public void testGetPublicGroups() throws Exception {
	System.out.println("getPublicGroups");
	String userId = nsid;
	PeopleApi instance = PeopleApi.getInstance();
	Groups result = instance.getPublicGroups(userId);
	assertNotNull(result);
	assertTrue(result.getGroups().size() > 0);
    }


    /**
     * Test of getPublicPhotos method, of class PeopleApi.
     */
    @Test
    public void testGetPublicPhotos_String() throws Exception {
	System.out.println("getPublicPhotos");
	String userId = nsid;
	PeopleApi instance = PeopleApi.getInstance();
	Photos result = instance.getPublicPhotos(userId);
	assertNotNull(result);
	assertTrue(result.getPhotos().size() > 0);
    }


    /**
     * Test of getPublicPhotos method, of class PeopleApi.
     */
    @Test
    public void testGetPublicPhotos_5args() throws Exception {
	System.out.println("getPublicPhotos");
	String userId = nsid;
	String safeSearch = "";
	List<String> extras = new ArrayList<String>();
	extras.add(JinxConstants.EXTRAS_DESCRIPTION);
	extras.add(JinxConstants.EXTRAS_DATE_UPLOAD);
	extras.add(JinxConstants.EXTRAS_DATE_TAKEN);
	int perPage = 10;
	int page = 0;
	PeopleApi instance = PeopleApi.getInstance();
	Photos result = instance.getPublicPhotos(userId, safeSearch, extras, perPage, page);
	assertNotNull(result);
	assertEquals(perPage, result.getPhotos().size());
	for (Photo photo : result.getPhotos()) {
	    assertNotNull(photo.getDateUploaded());
	}
    }


    /**
     * Test of getUploadStatus method, of class PeopleApi.
     */
    @Test
    public void testGetUploadStatus() throws Exception {
	System.out.println("getUploadStatus");
	PeopleApi instance = PeopleApi.getInstance();
	User result = instance.getUploadStatus();
	assertNotNull(result);
    }

}