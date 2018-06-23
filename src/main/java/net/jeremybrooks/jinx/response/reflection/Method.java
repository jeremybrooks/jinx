/*
 * Jinx is Copyright 2010-2018 by Jeremy Brooks and Contributors
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

package net.jeremybrooks.jinx.response.reflection;

import com.google.gson.annotations.SerializedName;
import net.jeremybrooks.jinx.JinxUtils;

import java.io.Serializable;

/**
 * Represents data about a Flickr method.
 *
 * Returned by the methods in {@link net.jeremybrooks.jinx.api.ReflectionApi}.
 *
 * @author Jeremy Brooks
 */
public class Method implements Serializable {

  private static final long serialVersionUID = -7819594591224587490L;

  public String getName() {
    String returnName;
    if (name == null) {
      returnName = content == null ? null : content;
    } else {
      returnName = name;
    }
    return returnName;
  }
  public Boolean isNeedsLogin() { return JinxUtils.flickrBooleanToBoolean(needsLogin);}
  public Boolean isNeedsSigning() { return JinxUtils.flickrBooleanToBoolean(needsSigning);}
  public Integer getRequiredPerms() {return requiredPerms;}
  public String getDescription() { return description == null ? null :description._content; }
  public String getResponse() {return response == null ? null : response._content;}

    private String name;
    @SerializedName("_content")
    private String content; // this is also the name, but from a different call
    @SerializedName("needslogin")
    private Integer needsLogin; // return as Boolean
    @SerializedName("needssigning")
    private Integer needsSigning; // return as Boolean
    @SerializedName("requiredperms")
    private Integer requiredPerms;
    private _Description description;
    private _Response response;

    private class _Description implements Serializable {
      private static final long serialVersionUID = -1339685603916476533L;
      private String _content;
    }
    private class _Response implements Serializable {
      private static final long serialVersionUID = 7505494245831535684L;
      private String _content;
    }

  @Override
  public String toString() {
    return "net.jeremybrooks.jinx.response.reflection.Method{" +
        "name='" + getName() + '\'' +
        ", needsLogin=" + isNeedsLogin() +
        ", needsSigning=" + isNeedsSigning() +
        ", requiredPerms=" + getRequiredPerms() +
        ", description=" + getDescription() +
        ", response=" + getResponse() +
        '}';
  }
}
