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

package net.jeremybrooks.jinx.response.photos.people;

import com.google.gson.Gson;
import net.jeremybrooks.jinx.response.activity.ActivityResponseTest;
import org.junit.Test;

import java.io.InputStreamReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by jeremyb on 7/22/14.
 */
public class PhotosPeopleTest {
    @Test
    public void testParseGetList() throws Exception {
        InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/photos/people/sample_get_list.json"));
        People people = new Gson().fromJson(reader, People.class);
        reader.close();
        assertNotNull(people);
        assertEquals("ok", people.getStat());
        assertEquals(0, people.getCode());
        assertEquals(Integer.valueOf(1), people.getTotal());
        assertEquals(Integer.valueOf(333), people.getPhotoWidth());
        assertEquals(Integer.valueOf(500), people.getPhotoHeight());
        assertNotNull(people.getPersonList());
        assertEquals(1, people.getPersonList().size());
        People.Person person = people.getPersonList().get(0);
        assertEquals("124857539@N03", person.getUserId());
        assertEquals("jinxlib", person.getUsername());
        assertEquals("5538", person.getIconServer());
        assertEquals("6", person.getIconFarm());
        assertEquals("Jinx Library", person.getRealname());
        assertEquals("jinxlib", person.getPathAlias());
        assertEquals("124857539@N03", person.getAddedByUserId());
    }
}
