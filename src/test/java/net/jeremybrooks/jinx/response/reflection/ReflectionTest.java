package net.jeremybrooks.jinx.response.reflection;

import com.google.gson.Gson;
import org.junit.Test;

import java.io.InputStreamReader;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNotNull;

/**
 * @author Jeremy Brooks
 */
public class ReflectionTest {
  @Test
  public void testGetMethodInfo() {
    InputStreamReader reader = new InputStreamReader(ReflectionTest.class.getResourceAsStream("/response/reflection/sample_get_method_info.json"));
    MethodInfo methodInfo = new Gson().fromJson(reader, MethodInfo.class);
    assertNotNull(methodInfo);
    Method method = methodInfo.getMethod();
    assertNotNull(method);
    assertEquals("flickr.places.placesForTags", method.getName());
    assertFalse(method.isNeedsLogin());
    assertFalse(method.isNeedsSigning());
    assertEquals(new Integer(0), method.getRequiredPerms());
    assertEquals("Return a list of the top 100 unique places clustered by a given placetype for set of tags or machine tags. ", method.getDescription());
    assertEquals("<places total=\"1\">\r\n" +
        "   <place place_id=\"kH8dLOubBZRvX_YZ\" woeid=\"2487956\"\r\n" +
        "               latitude=\"37.779\" longitude=\"-122.420\"\r\n" +
        "               place_url=\"/United+States/California/San+Francisco\"\r\n" +
        "               place_type=\"locality\"\r\n" +
        "               photo_count=\"156\">San Francisco, California</place>\r\n" +
        "</places>", method.getResponse());

    List<Argument> arguments = methodInfo.getArguments();
    assertNotNull(arguments);
    Argument argument = arguments.get(0);
    assertEquals("api_key", argument.getName());
    assertFalse(argument.isOptional());
    assertEquals("Your API application key. <a href=\"/services/api/misc.api_keys.html\">See here</a> for more details.", argument.getContent());

    List<Error> errors = methodInfo.getErrors();
    assertNotNull(errors);
    Error error = errors.get(0);
    assertEquals(new Integer(100), error.getCode());
    assertEquals("Invalid API Key", error.getMessage());
    assertEquals("The API key passed was not valid or has expired.", error.getContent());
  }

  @Test
  public void testGetMethods() throws Exception {
    InputStreamReader reader = new InputStreamReader(ReflectionTest.class.getResourceAsStream("/response/reflection/sample_get_methods.json"));
    Methods methods = new Gson().fromJson(reader, Methods.class);
    assertNotNull(methods);
    assertNotNull(methods.getMethods());
    Method method = methods.getMethods().get(0);
    assertEquals("flickr.activity.userComments", method.getName());
  }
}
