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

package net.jeremybrooks.jinx.response.machinetags;

import com.google.gson.annotations.SerializedName;
import net.jeremybrooks.jinx.response.Response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jeremyb on 7/10/14.
 */
public class Values extends Response {

    public Integer getPage() {
        return values == null ? null : values.page;
    }

    public Integer getPages() {
        return values == null ? null : values.pages;
    }

    public Integer getPerPage() {
        return values == null ? null : values.perPage;
    }

    public Integer getTotal() {
        return values == null ? null : values.total;
    }

    public List<Value> getValueList() {
        return values == null ? null : values.valueList;
    }

    private _Values values;
    private class _Values implements Serializable {
        private Integer page;
        private Integer pages;
        @SerializedName("perpage")
        private Integer perPage;
        private Integer total;
        @SerializedName("value")
        private List<Value> valueList;
    }

    public class Value implements Serializable {
        @SerializedName("_content")
        private String value;
        private Integer usage;
        private String namespace;
        private String predicate;
        @SerializedName("first_added")
        private String firstAdded;
        @SerializedName("last_added")
        private String lastAdded;

        public String getValue() {
            return value;
        }

        public Integer getUsage() {
            return usage;
        }

        /**
         * Only returned by getRecentValues()
         *
         * @return the namespace.
         */
        public String getNamespace() {
            return namespace;
        }

        /**
         * Only returned by getRecentValues()
         *
         * @return the predicate.
         */
        public String getPredicate() {
            return predicate;
        }

        /**
         * Only returned by getRecentValues()
         *
         * Use {@link net.jeremybrooks.jinx.JinxUtils#parseTimestampToDate(String)} to convert this value to a Date.
         *
         * @return the date this value was first added, as epoch seconds.
         */
        public String getFirstAdded() {
            return firstAdded;
        }

        /**
         * Only returned by getRecentValues()
         *
         * Use {@link net.jeremybrooks.jinx.JinxUtils#parseTimestampToDate(String)} to convert this value to a Date.
         *
         * @return the date this value was last added, as epoch seconds.
         */
        public String getLastAdded() {
            return lastAdded;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Value{");
            sb.append("value='").append(value).append('\'');
            sb.append(", usage=").append(usage);
            sb.append(", namespace='").append(namespace).append('\'');
            sb.append(", predicate='").append(predicate).append('\'');
            sb.append(", firstAdded='").append(firstAdded).append('\'');
            sb.append(", lastAdded='").append(lastAdded).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("_Values{");
        sb.append("page=").append(getPage());
        sb.append(", pages=").append(getPages());
        sb.append(", perPage=").append(getPerPage());
        sb.append(", total=").append(getTotal());
        sb.append(", valueList=").append(getValueList() == null ? "null" : "[" + getValueList().size() + " items]");
        sb.append('}');
        return sb.toString();
    }
}
