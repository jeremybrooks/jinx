package net.jeremybrooks.jinx.api;

import net.jeremybrooks.jinx.Jinx;
import net.jeremybrooks.jinx.JinxException;
import net.jeremybrooks.jinx.response.Response;
import net.jeremybrooks.jinx.response.test.TestEcho;
import net.jeremybrooks.jinx.response.test.TestLogin;

import java.util.Map;
import java.util.TreeMap;

/**
 * Provides access to the flickr.test API methods.
 *
 * @author Jeremy Brooks
 * @see <a href="https://www.flickr.com/services/api/">Flickr API documentation</a> for more details.
 */
public class TestApi {
  private Jinx jinx;

  private TestApi() {
  }

  public TestApi(Jinx jinx) {
    this.jinx = jinx;
  }

  /**
   * A testing method which echo's all parameters back in the response.
   *
   * This method does not require authentication.
   *
   * @return object with parameters of the request.
   * @throws JinxException if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.test.echo.html">flickr.test.echo</a>
   */
  public TestEcho testEcho() throws JinxException {
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.test.echo");
    return jinx.flickrGet(params, TestEcho.class, false);
  }

  /**
   * A testing method which checks if the caller is logged in then returns their username.
   * Authentication
   *
   * This method requires authentication with 'read' permission.
   *
   * @return logged in user id and username.
   * @throws JinxException if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.test.login.html">flickr.test.login</a>
   */
  public TestLogin testLogin() throws JinxException {
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.test.login");
    return jinx.flickrGet(params, TestLogin.class);
  }

  /**
   * Null test
   *
   * This method requires authentication with 'read' permission.
   *
   * @return response object. If successful, the stat parameter will be "ok".
   * @throws JinxException if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.test.null.html">flickr.test.null</a>
   */
  public Response testNull() throws JinxException {
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.test.null");
    return jinx.flickrGet(params, Response.class);
  }
}
