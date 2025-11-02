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

package net.jeremybrooks.jinx.api;

import net.jeremybrooks.jinx.response.machinetags.Namespaces;
import net.jeremybrooks.jinx.response.machinetags.Pairs;
import net.jeremybrooks.jinx.response.machinetags.Predicates;
import net.jeremybrooks.jinx.response.machinetags.Values;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 *
 * Created by jeremyb on 7/10/14.
 */
public class MachineTagsApiTest {

    private static MachinetagsApi machinetagsApi;

    @BeforeClass
    public static void beforeClass() throws Exception {
        machinetagsApi = new MachinetagsApi(JinxApiTestCommon.getJinx());
    }

    @Test
    public void testGetNamespaces() throws Exception {
        Namespaces namespaces = machinetagsApi.getNamespaces(null, 10, 10, false);

        assertNotNull(namespaces);
        assertEquals("ok", namespaces.getStat());
        assertEquals(0, namespaces.getCode());
        assertEquals(Integer.valueOf(10), namespaces.getPerPage());
        assertEquals(Integer.valueOf(10), namespaces.getPage());
        assertNotNull(namespaces.getNamespaceList());
        assertEquals(10, namespaces.getNamespaceList().size());

        namespaces = machinetagsApi.getNamespaces("geo", 5, 1, true);
        assertNotNull(namespaces);
        assertEquals("ok", namespaces.getStat());
        assertEquals(0, namespaces.getCode());
        assertEquals(Integer.valueOf(5), namespaces.getPerPage());
        assertEquals(Integer.valueOf(1), namespaces.getPage());
        assertNotNull(namespaces.getNamespaceList());
    }

    @Test
    public void testGetPairs() throws Exception {
        Pairs pairs = machinetagsApi.getPairs(null, null, 10, 0, false);
        assertNotNull(pairs);
        assertEquals("ok", pairs.getStat());
        assertEquals(0, pairs.getCode());
        assertEquals(Integer.valueOf(10), pairs.getPerPage());
        assertEquals(Integer.valueOf(1), pairs.getPage());
        assertNotNull(pairs.getPairList());
        assertEquals(10, pairs.getPairList().size());

        pairs = machinetagsApi.getPairs("geo", null, 10, 0, true);
        assertNotNull(pairs);
        assertEquals("ok", pairs.getStat());
        assertEquals(0, pairs.getCode());
        assertEquals(Integer.valueOf(10), pairs.getPerPage());
        assertEquals(Integer.valueOf(1), pairs.getPage());
        assertNotNull(pairs.getPairList());
        assertEquals(10, pairs.getPairList().size());

        pairs = machinetagsApi.getPairs("geo", "airport", 10, 0, true);
        assertNotNull(pairs);
        assertEquals("ok", pairs.getStat());
        assertEquals(0, pairs.getCode());
        assertEquals(Integer.valueOf(10), pairs.getPerPage());
        assertEquals(Integer.valueOf(1), pairs.getPage());
        assertNotNull(pairs.getPairList());
    }

    @Test
    public void testGetPredicates() throws Exception {
        Predicates predicates = machinetagsApi.getPredicates(null, 10, 20, false);
        assertNotNull(predicates);
        assertEquals("ok", predicates.getStat());
        assertEquals(0, predicates.getCode());
        assertEquals(Integer.valueOf(10), predicates.getPerPage());
        assertEquals(Integer.valueOf(20), predicates.getPage());
        assertNotNull(predicates.getPredicateList());
        assertEquals(10, predicates.getPredicateList().size());

        predicates = machinetagsApi.getPredicates("geo", 10, 20, true);
        assertNotNull(predicates);
        assertEquals("ok", predicates.getStat());
        assertEquals(0, predicates.getCode());
        assertEquals(Integer.valueOf(10), predicates.getPerPage());
        assertEquals(Integer.valueOf(20), predicates.getPage());
        assertNotNull(predicates.getPredicateList());
        assertEquals(10, predicates.getPredicateList().size());
    }

    @Test
    public void testGetRecentValues() throws Exception {
        long since = System.currentTimeMillis()/1000 - (1440*7);
        Values values = machinetagsApi.getRecentValues(null, null, Long.toString(since), true);
        assertNotNull(values);
        assertEquals("ok", values.getStat());
        assertEquals(0, values.getCode());
        assertNotNull(values.getValueList());

        values = machinetagsApi.getRecentValues("geo", null, null, true);
        assertNotNull(values);
        assertEquals("ok", values.getStat());
        assertEquals(0, values.getCode());
        assertNotNull(values.getValueList());

        values = machinetagsApi.getRecentValues("geo", "airport", null, true);
        assertNotNull(values);
        assertEquals("ok", values.getStat());
        assertEquals(0, values.getCode());
        assertNotNull(values.getValueList());
    }

    @Test
    public void testGetValues() throws Exception {
        Values values = machinetagsApi.getValues("geo", "airport", 10, 1, false);
        assertNotNull(values);
        assertEquals("ok", values.getStat());
        assertEquals(0, values.getCode());
        assertEquals(Integer.valueOf(10), values.getPerPage());
        assertEquals(Integer.valueOf(1), values.getPage());
        assertNotNull(values.getValueList());

        values = machinetagsApi.getValues("geo", "airport", 0, 0, true);
        assertNotNull(values);
        assertEquals("ok", values.getStat());
        assertEquals(0, values.getCode());
        assertNotNull(values.getValueList());
    }

}
