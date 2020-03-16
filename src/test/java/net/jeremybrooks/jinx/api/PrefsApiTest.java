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

import net.jeremybrooks.jinx.response.people.Person;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

/**
 * @author Jeremy Brooks
 */
public class PrefsApiTest {
  private static PrefsApi prefsApi;

  private static String testUserId = "124857539@N03";

  @BeforeClass
  public static void beforeClass() throws Exception {
    prefsApi = new PrefsApi(JinxApiTestCommon.getJinx());
  }

  @Test
  public void testGetContentType() throws Exception {
    Person person = prefsApi.getContentType();
    assertNotNull(person);
    assertEquals(testUserId, person.getUserId());
    assertEquals(Integer.valueOf(1), person.getContentType());
  }

  @Test
  public void testGetGeoPerms() throws Exception {
    Person person = prefsApi.getGeoPerms();
    assertNotNull(person);
    assertEquals(testUserId, person.getUserId());
    assertEquals(Integer.valueOf(1), person.getGeoPerms());
    assertEquals(Integer.valueOf(1), person.getImportGeoExif());
  }

  @Test
  public void testGetHidden() throws Exception {
    Person person = prefsApi.getHidden();
    assertNotNull(person);
    assertEquals(testUserId, person.getUserId());
    assertEquals(Integer.valueOf(1), person.getHidden());
  }

  @Test
  public void testGetPrivacy() throws Exception {
    Person person = prefsApi.getPrivacy();
    assertNotNull(person);
    assertEquals(testUserId, person.getUserId());
    assertEquals(Integer.valueOf(1), person.getPrivacy());
  }

  @Test
  public void testGetSafetyLevel() throws Exception {
    Person person = prefsApi.getSafetyLevel();
    assertNotNull(person);
    assertEquals(testUserId, person.getUserId());
    assertEquals(Integer.valueOf(1), person.getSafetyLevel());
  }
}
