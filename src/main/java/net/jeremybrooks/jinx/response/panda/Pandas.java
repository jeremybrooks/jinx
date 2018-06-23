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

package net.jeremybrooks.jinx.response.panda;

import com.google.gson.annotations.SerializedName;
import net.jeremybrooks.jinx.response.Response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jeremyb on 7/14/14.
 */
public class Pandas extends Response {

    private static final long serialVersionUID = -3526502334819736218L;
    private _Pandas pandas;

    public List<Panda> getPandaList() {
        return pandas == null ? null : pandas.pandaList;
    }

    private class _Pandas implements Serializable {
        private static final long serialVersionUID = 6027232239179904942L;
        @SerializedName("panda")
        List<Panda> pandaList;


    }

    public class Panda implements Serializable {
        private static final long serialVersionUID = 1092717360375840890L;
        @SerializedName("_content")
        private String name;
        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Panda{");
            sb.append("name='").append(name).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Pandas{");
        sb.append("pandaList=").append(getPandaList() == null ? "null" : "[" + getPandaList().size() + " items]");
        sb.append('}');
        return sb.toString();
    }
}
