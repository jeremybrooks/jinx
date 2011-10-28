package net.jeremybrooks.jinx.api;

import net.jeremybrooks.jinx.JinxUtils;
import net.jeremybrooks.jinx.Setup;
import java.util.Date;
import java.util.List;
import net.jeremybrooks.jinx.dto.Photos;
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
	assertEquals(10, result.getPhotos().size());

	date = JinxUtils.parseMySqlDatetimeToDate("2011-01-01 00:00:00");
	result = instance.getList(date, extras, page, perPage);
	assertNotNull(result);
	assertEquals(10, result.getPhotos().size());
    }

}