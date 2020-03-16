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

package net.jeremybrooks.jinx.response.panda;

import com.google.gson.Gson;
import net.jeremybrooks.jinx.response.activity.ActivityResponseTest;
import org.junit.Test;

import java.io.InputStreamReader;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

/**
 * Created by jeremyb on 7/14/14.
 */
public class PandaTest {
    @Test
    public void testParseGetList() throws Exception {
        InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/panda/sample_get_list.json"));
        Pandas pandas = new Gson().fromJson(reader, Pandas.class);
        reader.close();
        assertNotNull(pandas);
        assertEquals("ok", pandas.getStat());
        assertEquals(0, pandas.getCode());
        assertNotNull(pandas.getPandaList());
        assertEquals(3, pandas.getPandaList().size());
        assertEquals("ling ling", pandas.getPandaList().get(0).getName());
        assertEquals("hsing hsing", pandas.getPandaList().get(1).getName());
        assertEquals("wang wang", pandas.getPandaList().get(2).getName());
    }
}
