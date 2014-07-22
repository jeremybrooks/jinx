/*
 * Jinx is Copyright 2010-2014 by Jeremy Brooks and Contributors
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

package net.jeremybrooks.jinx.response.photos.licenses;

import com.google.gson.annotations.SerializedName;
import net.jeremybrooks.jinx.response.Response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jeremyb on 7/22/14.
 */
public class Licenses extends Response {

    private static final long serialVersionUID = 6500642772484010572L;

    public List<License> getLicenseList() { return licenses == null ? null : licenses.licenseList; }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Licenses{");
        sb.append("licenseList=").append(getLicenseList() == null ? "null" : "[" + getLicenseList().size() + " items]");
        sb.append('}');
        return sb.toString();
    }

    public class License implements Serializable {
        private static final long serialVersionUID = 1019586702662555734L;
        @SerializedName("id")
        private Integer licenseId;
        private String name;
        private String url;
        public Integer getLicenseId() {return licenseId;}
        public String getName() { return name; }
        public String getUrl() { return url; }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("License{");
            sb.append("licenseId=").append(licenseId);
            sb.append(", name='").append(name).append('\'');
            sb.append(", url='").append(url).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }

    private _Licenses licenses;
    private class _Licenses implements Serializable {
        private static final long serialVersionUID = -9218701829917075872L;
        @SerializedName("license")
        private List<License> licenseList;
    }
}
