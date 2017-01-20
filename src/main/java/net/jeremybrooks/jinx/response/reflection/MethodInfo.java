package net.jeremybrooks.jinx.response.reflection;

import net.jeremybrooks.jinx.response.Response;

import java.io.Serializable;
import java.util.List;

/**
 * Represents data returned from the Flickr reflection API, specifically from calling
 * {@link net.jeremybrooks.jinx.api.ReflectionApi#getMethodInfo(String)}.
 *
 * @author Jeremy Brooks
 */
public class MethodInfo extends Response {
  private static final long serialVersionUID = 6353927417846805740L;
  private Method method;
  private _Arguments arguments;
  private _Errors errors;
  public Method getMethod() {return method;}
  public List<Argument> getArguments() { return arguments == null ? null : arguments.argument; }
  public List<Error> getErrors() { return errors == null ? null : errors.error; }

  private class _Arguments implements Serializable {
    private static final long serialVersionUID = -8455799530846184936L;
    private List<Argument> argument;
  }

  private class _Errors implements Serializable {
    private static final long serialVersionUID = -145141055689912292L;
    private List<Error> error;
  }

  @Override
  public String toString() {
    return "net.jeremybrooks.jinx.response.reflection.MethodInfo{" +
        "method=" + method +
        ", arguments=" + getArguments() +
        ", errors=" + getErrors() +
        '}';
  }
}
