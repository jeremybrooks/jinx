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

package net.jeremybrooks.jinx.response.test;

import com.google.gson.annotations.SerializedName;
import net.jeremybrooks.jinx.response.Response;

import java.io.Serializable;

/**
 * @author Jeremy Brooks
 */
public class TestEcho extends Response {
  private static final long serialVersionUID = -2855463443337642226L;
  private _Method method;
  @SerializedName("api_key")
  private _ApiKey apiKey;
  private _Format format;
  @SerializedName("nojsoncallback")
  private _NoJsonCallback noJsonCallback;
  @SerializedName("auth_token")
  private _AuthToken authToken;
  @SerializedName("api_sig")
  private _ApiSig apiSig;

  public String getMethod() { return method == null ? null : method._content; }
  public String getApiKey() { return apiKey == null ? null : apiKey._content; }
  public String getFormat() { return format == null ? null : format._content; }
  public String getNoJsonCallback() { return noJsonCallback == null ? null : noJsonCallback._content; }
  public String getAuthToken() { return authToken == null ? null : authToken._content; }
  public String getApiSig() { return apiSig == null ? null : apiSig._content; }

  private class _Method implements Serializable {
    private static final long serialVersionUID = -55307232084642756L;
    private String _content;
  }
  private class _ApiKey implements Serializable {
    private static final long serialVersionUID = 8554190943401768198L;
    private String _content;
  }
  private class _Format implements Serializable {
    private static final long serialVersionUID = -4174177032530913830L;
    private String _content;
  }
  private class _NoJsonCallback implements Serializable {
    private static final long serialVersionUID = 1106929266762114383L;
    private String _content;
  }
  private class _AuthToken implements Serializable {
    private static final long serialVersionUID = -6270410201359742266L;
    private String _content;
  }
  private class _ApiSig implements Serializable {
    private static final long serialVersionUID = 8803811445453162403L;
    private String _content;
  }

  @Override
  public String toString() {
    return "net.jeremybrooks.jinx.response.test.TestEcho{" +
        "method=" + getMethod() +
        ", apiKey=" + getApiKey() +
        ", format=" + getFormat() +
        ", noJsonCallback=" + getNoJsonCallback() +
        ", authToken=" + getAuthToken() +
        ", apiSig=" + getApiSig() +
        '}';
  }
}
