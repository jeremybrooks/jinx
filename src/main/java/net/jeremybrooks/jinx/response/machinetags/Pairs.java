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
public class Pairs extends Response {

    private static final long serialVersionUID = -7446878127335838928L;

    public Integer getPage() {
        return pairs == null ? null : pairs.page;
    }

    public Integer getPages() {
        return pairs == null ? null : pairs.pages;
    }

    public Integer getPerPage() {
        return pairs == null ? null : pairs.perPage;
    }

    public Integer getTotal() {
        return pairs == null ? null : pairs.total;
    }

    public List<Pair> getPairList() {
        return pairs == null ? null : pairs.pairList;
    }

    private _Pairs pairs;
    private class _Pairs implements Serializable {
        private static final long serialVersionUID = -1362887556900470961L;
        private Integer page;
        private Integer pages;
        @SerializedName("perpage")
        private Integer perPage;
        private Integer total;
        @SerializedName("pair")
        private List<Pair> pairList;
    }

    public class Pair implements Serializable {
        private static final long serialVersionUID = -1966897282386583839L;
        @SerializedName("_content")
        private String pair;
        private String namespace;
        private String predicate;
        private Integer usage;

        public String getPair() {
            return pair;
        }

        public String getNamespace() {
            return namespace;
        }

        public String getPredicate() {
            return predicate;
        }

        public Integer getUsage() {
            return usage;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Pair{");
            sb.append("pair='").append(pair).append('\'');
            sb.append(", namespace='").append(namespace).append('\'');
            sb.append(", predicate='").append(predicate).append('\'');
            sb.append(", usage=").append(usage);
            sb.append('}');
            return sb.toString();
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("_Pairs{");
        sb.append("page=").append(getPage());
        sb.append(", pages=").append(getPages());
        sb.append(", perPage=").append(getPerPage());
        sb.append(", total=").append(getTotal());
        sb.append(", pairList=").append(getPairList() == null ? "null" : "[" + getPairList().size() + " items]");
        sb.append('}');
        return sb.toString();
    }
}
