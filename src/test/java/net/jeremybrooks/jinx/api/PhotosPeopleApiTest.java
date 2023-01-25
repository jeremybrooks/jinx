/*
 * Jinx is Copyright 2010-2023 by Jeremy Brooks and Contributors
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
import net.jeremybrooks.jinx.response.photos.people.People;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by jeremyb on 7/22/14.
 */
public class PhotosPeopleApiTest {
    private static PhotosPeopleApi photosPeopleApi;
    private static final String PHOTO_ID = "14274715292";
    private static final String USER_ID = "85853333@N00";

    @BeforeClass
    public static void beforeClass() throws Exception {
        photosPeopleApi = new PhotosPeopleApi(JinxApiTestCommon.getJinx());
    }

    @Test
    public void testAddEditDelete() throws Exception {
        // add
        Response response = photosPeopleApi.add(PHOTO_ID, USER_ID, null, null, null, null);
        assertNotNull(response);
        assertEquals("ok", response.getStat());
        assertEquals(0, response.getCode());

        // edit coords
        response = photosPeopleApi.editCoords(PHOTO_ID, USER_ID, 10, 10, 100, 100);
        assertNotNull(response);
        assertEquals("ok", response.getStat());
        assertEquals(0, response.getCode());

        // delete coords
        response = photosPeopleApi.deleteCoords(PHOTO_ID, USER_ID);
        assertNotNull(response);
        assertEquals("ok", response.getStat());
        assertEquals(0, response.getCode());

        // delete
        response = photosPeopleApi.delete(PHOTO_ID, USER_ID);
        assertNotNull(response);
        assertEquals("ok", response.getStat());
        assertEquals(0, response.getCode());
    }

    @Test
    public void testGetList() throws Exception {
        People people = photosPeopleApi.getList(PHOTO_ID);
        assertNotNull(people);
        assertEquals("ok", people.getStat());
        assertEquals(0, people.getCode());
        assertNotNull(people.getPersonList());
        assertTrue(people.getPersonList().size() > 0);
    }
}
