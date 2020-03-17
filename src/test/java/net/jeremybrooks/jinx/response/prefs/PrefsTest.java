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

package net.jeremybrooks.jinx.response.prefs;

import com.google.gson.Gson;
import net.jeremybrooks.jinx.response.people.Person;
import org.junit.Test;

import java.io.InputStreamReader;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

/**
 * @author Jeremy Brooks
 */
public class PrefsTest {
  @Test
  public void testGetContentType() throws Exception {
    InputStreamReader reader = new InputStreamReader(PrefsTest.class.getResourceAsStream("/response/prefs/sample_get_content_type.json"));
    Person person = new Gson().fromJson(reader, Person.class);
    assertNotNull(person);
    assertEquals("85853333@N00", person.getUserId());
    assertEquals(Integer.valueOf(1), person.getContentType());
  }

  @Test
  public void testGetGeoPerms() {
    InputStreamReader reader = new InputStreamReader(PrefsTest.class.getResourceAsStream("/response/prefs/sample_get_geo_perms.json"));
    Person person = new Gson().fromJson(reader, Person.class);
    assertNotNull(person);
    assertEquals("85853333@N00", person.getUserId());
    assertEquals(Integer.valueOf(1), person.getGeoPerms());
    assertEquals(Integer.valueOf(1), person.getImportGeoExif());
  }

  @Test
  public void testGetHidden() {
    InputStreamReader reader = new InputStreamReader(PrefsTest.class.getResourceAsStream("/response/prefs/sample_get_hidden.json"));
    Person person = new Gson().fromJson(reader, Person.class);
    assertNotNull(person);
    assertEquals("85853333@N00", person.getUserId());
    assertEquals(Integer.valueOf(1), person.getHidden());
  }

  @Test
  public void testGetPrivacy() {
    InputStreamReader reader = new InputStreamReader(PrefsTest.class.getResourceAsStream("/response/prefs/sample_get_privacy.json"));
    Person person = new Gson().fromJson(reader, Person.class);
    assertNotNull(person);
    assertEquals("85853333@N00", person.getUserId());
    assertEquals(Integer.valueOf(1), person.getPrivacy());
  }

  @Test
  public void testGetSafetyLevel() {
    InputStreamReader reader = new InputStreamReader(PrefsTest.class.getResourceAsStream("/response/prefs/sample_get_safety_level.json"));
    Person person = new Gson().fromJson(reader, Person.class);
    assertNotNull(person);
    assertEquals("85853333@N00", person.getUserId());
    assertEquals(Integer.valueOf(1), person.getSafetyLevel());
  }
}
