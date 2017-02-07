package net.jeremybrooks.jinx.api;

import net.jeremybrooks.jinx.Jinx;
import net.jeremybrooks.jinx.JinxException;
import net.jeremybrooks.jinx.JinxUtils;
import net.jeremybrooks.jinx.response.reflection.MethodInfo;
import net.jeremybrooks.jinx.response.reflection.Methods;

import java.util.Map;
import java.util.TreeMap;

/**
 * Provides access to the flickr.reflection API methods.
 *
 * @author Jeremy Brooks
 * @see <a href="https://www.flickr.com/services/api/">Flickr API documentation</a> for more details.
 */
public class ReflectionApi {
  private Jinx jinx;

  private ReflectionApi() {
  }

  public ReflectionApi(Jinx jinx) {
    this.jinx = jinx;
  }

  /**
   * Returns a list of available Flickr API methods.
   *
   * Authentication
   *
   * This method does not require authentication.
   *
   * @return available Flickr API methods.
   * @throws JinxException if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.reflection.getMethods.html">flickr.reflection.getMethods</a>
   */
  public Methods getMethods() throws JinxException {
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.reflection.getMethods");
    return jinx.flickrGet(params, Methods.class, false);
  }

  /**
   * Returns information for a given Flickr API method.
   *
   * Authentication
   *
   * This method does not require authentication.
   *
   * @param methodName the name of the method to fetch information for. Required.
   * @return information about the method.
   * @throws JinxException if required parameters are missing, or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.reflection.getMethodInfo.html">flickr.reflection.getMethodInfo</a>
   */
  public MethodInfo getMethodInfo(String methodName) throws JinxException {
    JinxUtils.validateParams(methodName);
    Map<String, String> params = new TreeMap<>();
    params.put("method", "flickr.reflection.getMethodInfo");
    params.put("method_name", methodName);
    return jinx.flickrGet(params, MethodInfo.class, false);
  }
}
