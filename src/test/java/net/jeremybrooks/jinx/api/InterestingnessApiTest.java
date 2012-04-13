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

import net.jeremybrooks.jinx.JinxUtils;
import net.jeremybrooks.jinx.Setup;
import net.jeremybrooks.jinx.dto.Photos;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author jeremyb
 */
public class InterestingnessApiTest {

    public InterestingnessApiTest() {
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
     * Test of getInstance method, of class InterestingnessApi.
     */
    @Test
    public void testGetInstance() {
	System.out.println("getInstance");
	InterestingnessApi result = InterestingnessApi.getInstance();
	assertNotNull(result);
    }


    /**
     * Test of getList method, of class InterestingnessApi.
     */
    @Test
    public void testGetList_0args() throws Exception {
	System.out.println("getList");
	InterestingnessApi instance = InterestingnessApi.getInstance();
	Photos result = instance.getList();
	assertNotNull(result);
	assertTrue(result.getPhotos().size() > 0);
    }


    /**
     * Test of getList method, of class InterestingnessApi.
     */
    @Test
    public void testGetList_4args() throws Exception {
	System.out.println("getList");
	Date date = null;
	List<String> extras = null;
	int page = 0;
	int perPage = 10;
	InterestingnessApi instance = InterestingnessApi.getInstance();
	Photos result = instance.getList(date, extras, page, perPage);
	assertNotNull(result);
	assertEquals(perPage, result.getPhotos().size());

	date = JinxUtils.parseMySqlDatetimeToDate("2011-01-01 00:00:00");
        perPage = 5;
	result = instance.getList(date, extras, page, perPage);
	assertNotNull(result);
        assertEquals(perPage, result.getPhotos().size());
    }

}