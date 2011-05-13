/*
 * Jinx is Copyright 2011 by Jeremy Brooks
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
import net.jeremybrooks.jinx.JinxConstants;
import java.util.ArrayList;
import net.jeremybrooks.jinx.dto.Panda;
import net.jeremybrooks.jinx.Setup;
import java.util.List;
import net.jeremybrooks.jinx.dto.Photos;
import net.jeremybrooks.jinx.logger.JinxLogger;
import net.jeremybrooks.jinx.logger.StdoutLogger;
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
public class PandaApiTest {

    public PandaApiTest() {
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
     * Test of getInstance method, of class PandaApi.
     */
    @Test
    public void testGetInstance() {
	System.out.println("getInstance");
	PandaApi result = PandaApi.getInstance();
	assertNotNull(result);
    }


    /**
     * Test of getList method, of class PandaApi.
     * There should be three pandas:
     * <panda>ling ling</panda>
	<panda>hsing hsing</panda>
	<panda>wang wang</panda>
     */
    @Test
    public void testGetList() throws Exception {
	System.out.println("getList");
	PandaApi instance = PandaApi.getInstance();
	List<Panda> result = instance.getList();
	assertNotNull(result);
	assertEquals(3, result.size());
    }


    /**
     * Test of getPhotos method, of class PandaApi.
     */
    @Test
    public void testGetPhotos_Panda() throws Exception {
	System.out.println("getPhotos");
	PandaApi instance = PandaApi.getInstance();
	List<Panda> list = instance.getList();
	Photos result = instance.getPhotos(list.get(0));
	assertNotNull(result);
	assertTrue(result.getPhotos().size() > 0);
    }


    /**
     * Test of getPhotos method, of class PandaApi.
     */
    @Test
    public void testGetPhotos_4args() throws Exception {
	System.out.println("getPhotos");
	Panda panda = null;
	List<String> extras = null;
	int page = 0;
	int perPage = 0;
	PandaApi instance = PandaApi.getInstance();
	List<Panda> list = instance.getList();

	panda = list.get(0);

	Photos result = instance.getPhotos(panda, extras, page, perPage);
	assertNotNull(result);
	assertTrue(result.getPhotos().size() > 0);

	panda = list.get(1);
	extras = new ArrayList<String>();
	extras.add(JinxConstants.EXTRAS_DESCRIPTION);
	extras.add(JinxConstants.EXTRAS_DATE_UPLOAD);
	extras.add(JinxConstants.EXTRAS_DATE_TAKEN);
	perPage = 10;
	result = instance.getPhotos(panda, extras, page, perPage);
	assertNotNull(result);
	assertEquals(perPage, result.getPhotos().size());
	for (Photo photo : result.getPhotos()) {
	    System.out.println(photo.toString());
	    assertNotNull(photo.getDateUploaded());
	}
    }



}