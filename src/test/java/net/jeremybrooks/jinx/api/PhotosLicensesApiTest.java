/*
 * Jinx is Copyright 2010-2020 by Jeremy Brooks and Contributors
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

import net.jeremybrooks.jinx.response.Response;
import net.jeremybrooks.jinx.response.photos.licenses.Licenses;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by jeremyb on 7/22/14.
 */
public class PhotosLicensesApiTest {
    private static PhotosLicensesApi photosLicensesApi;
    private static final String PHOTO_ID = "14069436767";

    @BeforeClass
    public static void beforeClass() throws Exception {
        photosLicensesApi = new PhotosLicensesApi(JinxApiTestCommon.getJinx());
    }

    @Test
    public void testGetInfo() throws Exception {
        Licenses licenses = photosLicensesApi.getInfo();
        assertNotNull(licenses);
        assertEquals("ok", licenses.getStat());
        assertEquals(0, licenses.getCode());
        assertNotNull(licenses.getLicenseList());
        assertTrue(licenses.getLicenseList().size() > 0);
    }

    @Test
    public void testSetLicense() throws Exception {
        Response response = photosLicensesApi.setLicense(PHOTO_ID, 5);
        assertNotNull(response);
        assertEquals("ok", response.getStat());
        assertEquals(0, response.getCode());
        response = photosLicensesApi.setLicense(PHOTO_ID, 0);
        assertNotNull(response);
        assertEquals("ok", response.getStat());
        assertEquals(0, response.getCode());
    }
}
