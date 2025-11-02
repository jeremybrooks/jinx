/*
 * Jinx is Copyright 2010-2025 by Jeremy Brooks and Contributors
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
public class Predicates extends Response {
    private static final long serialVersionUID = 5652295420381692251L;

    public Integer getPage() {
        return predicates == null ? null : predicates.page;
    }

    public Integer getPages() {
        return predicates == null ? null : predicates.pages;
    }

    public Integer getPerPage() {
        return predicates == null ? null : predicates.perPage;
    }

    public Integer getTotal() {
        return predicates == null ? null : predicates.total;
    }

    public List<Predicate> getPredicateList() {
        return predicates == null ? null : predicates.predicateList;
    }

    private _Predicates predicates;
    private class _Predicates implements Serializable {
        private static final long serialVersionUID = 9149828891084736466L;
        private Integer page;
        private Integer pages;
        @SerializedName("perpage")
        private Integer perPage;
        private Integer total;
        @SerializedName("predicate")
        private List<Predicate> predicateList;
    }

    public class Predicate implements Serializable {
        private static final long serialVersionUID = -7806416977299050662L;
        @SerializedName("_content")
        private String predicate;
        private Integer usage;
        private Integer namespaces;

        public String getPredicate() {
            return predicate;
        }

        public Integer getUsage() {
            return usage;
        }

        public Integer getNamespaces() {
            return namespaces;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Predicate{");
            sb.append("predicate='").append(predicate).append('\'');
            sb.append(", usage=").append(usage);
            sb.append(", namespaces=").append(namespaces);
            sb.append('}');
            return sb.toString();
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("_Predicates{");
        sb.append("page=").append(getPage());
        sb.append(", pages=").append(getPages());
        sb.append(", perPage=").append(getPerPage());
        sb.append(", total=").append(getTotal());
        sb.append(", predicateList=").append(getPredicateList() == null ? "null" : "[" + getPredicateList().size() + " items]");
        sb.append('}');
        return sb.toString();
    }
}
