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
 *
 * Created by jeremyb on 7/10/14.
 */
public class Namespaces extends Response {

    private static final long serialVersionUID = 5362167564699968714L;

    public Integer getPage() {
        return namespaces == null ? null : namespaces.page;
    }

    public Integer getPages() {
        return namespaces == null ? null : namespaces.pages;
    }

    public Integer getPerPage() {
        return namespaces == null ? null : namespaces.perPage;
    }

    public Integer getTotal() {
        return namespaces == null ? null : namespaces.total;
    }

    public List<Namespace> getNamespaceList() {
        return namespaces == null ? null : namespaces.namespaceList;
    }

    private _Namespaces namespaces;

    private class _Namespaces implements Serializable {
        private static final long serialVersionUID = 8836711793549361507L;
        private Integer page;
        private Integer pages;
        @SerializedName("perpage")
        private Integer perPage;
        private Integer total;
        @SerializedName("namespace")
        private List<Namespace> namespaceList;
    }

    public class Namespace implements Serializable {
        private static final long serialVersionUID = -246710802563144165L;
        @SerializedName("_content")
        private String namespace;
        private Integer usage;
        private Integer predicates;

        public String getNamespace() {
            return namespace;
        }

        /**
         * Tells you roughly how popular a machine tag is.
         *
         * @return roughly how pupular a machine tag is.
         */
        public Integer getUsage() {
            return usage;
        }

        /**
         * The count of distinct predicates a namespace has.
         *
         * @return count of distinct predicates a namespace has.
         */
        public Integer getPredicates() {
            return predicates;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Namespace{");
            sb.append("namespace='").append(namespace).append('\'');
            sb.append(", usage=").append(usage);
            sb.append(", predicates=").append(predicates);
            sb.append('}');
            return sb.toString();
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("_Namespaces{");
        sb.append("page=").append(getPage());
        sb.append(", pages=").append(getPages());
        sb.append(", perPage=").append(getPerPage());
        sb.append(", total=").append(getTotal());
        sb.append(", namespaceList=").append(getNamespaceList() == null ? "null" : "[" + getNamespaceList().size() + " items]");
        sb.append('}');
        return sb.toString();
    }
}
