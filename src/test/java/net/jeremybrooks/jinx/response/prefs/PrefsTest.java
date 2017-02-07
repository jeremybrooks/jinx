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
    assertEquals(new Integer(1), person.getContentType());
  }

  @Test
  public void testGetGeoPerms() {
    InputStreamReader reader = new InputStreamReader(PrefsTest.class.getResourceAsStream("/response/prefs/sample_get_geo_perms.json"));
    Person person = new Gson().fromJson(reader, Person.class);
    assertNotNull(person);
    assertEquals("85853333@N00", person.getUserId());
    assertEquals(new Integer(1), person.getGeoPerms());
    assertEquals(new Integer(1), person.getImportGeoExif());
  }

  @Test
  public void testGetHidden() {
    InputStreamReader reader = new InputStreamReader(PrefsTest.class.getResourceAsStream("/response/prefs/sample_get_hidden.json"));
    Person person = new Gson().fromJson(reader, Person.class);
    assertNotNull(person);
    assertEquals("85853333@N00", person.getUserId());
    assertEquals(new Integer(1), person.getHidden());
  }

  @Test
  public void testGetPrivacy() {
    InputStreamReader reader = new InputStreamReader(PrefsTest.class.getResourceAsStream("/response/prefs/sample_get_privacy.json"));
    Person person = new Gson().fromJson(reader, Person.class);
    assertNotNull(person);
    assertEquals("85853333@N00", person.getUserId());
    assertEquals(new Integer(1), person.getPrivacy());
  }

  @Test
  public void testGetSafetyLevel() {
    InputStreamReader reader = new InputStreamReader(PrefsTest.class.getResourceAsStream("/response/prefs/sample_get_safety_level.json"));
    Person person = new Gson().fromJson(reader, Person.class);
    assertNotNull(person);
    assertEquals("85853333@N00", person.getUserId());
    assertEquals(new Integer(1), person.getSafetyLevel());
  }
}
