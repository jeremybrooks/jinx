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

package net.jeremybrooks.jinx.response.machinetags;

import com.google.gson.Gson;
import net.jeremybrooks.jinx.response.activity.ActivityResponseTest;
import org.junit.Test;

import java.io.InputStreamReader;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;

/**
 * Created by jeremyb on 7/10/14.
 */
public class MachinetagsTest {

    @Test
    public void testParseGetNamespaces() throws Exception {
        InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/machinetags/sample_get_namespaces.json"));
        Namespaces namespaces = new Gson().fromJson(reader, Namespaces.class);
        reader.close();
        assertNotNull(namespaces);
        assertEquals("ok", namespaces.getStat());
        assertEquals(0, namespaces.getCode());
        assertEquals(Integer.valueOf(10), namespaces.getPage());
        assertEquals(Integer.valueOf(4033), namespaces.getTotal());
        assertEquals(Integer.valueOf(10), namespaces.getPerPage());
        assertEquals(Integer.valueOf(404), namespaces.getPages());

        assertNotNull(namespaces.getNamespaceList());
        assertEquals(10, namespaces.getNamespaceList().size());
        Namespaces.Namespace namespace = namespaces.getNamespaceList().get(0);
        assertEquals("aircraft", namespace.getNamespace());
        assertEquals(Integer.valueOf(93), namespace.getUsage());
        assertEquals(Integer.valueOf(6), namespace.getPredicates());
    }

    @Test
    public void testParseGetPairs() throws Exception {
        InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/machinetags/sample_get_pairs.json"));
        Pairs pairs = new Gson().fromJson(reader, Pairs.class);
        reader.close();
        assertNotNull(pairs);
        assertEquals("ok", pairs.getStat());
        assertEquals(0, pairs.getCode());
        assertEquals(Integer.valueOf(10), pairs.getPage());
        assertEquals(Integer.valueOf(13912), pairs.getTotal());
        assertEquals(Integer.valueOf(10), pairs.getPerPage());
        assertEquals(Integer.valueOf(1392), pairs.getPages());
        assertNotNull(pairs.getPairList());
        assertEquals(10, pairs.getPairList().size());
        Pairs.Pair pair = pairs.getPairList().get(0);
        assertEquals("accessceramics:width", pair.getPair());
        assertEquals("accessceramics", pair.getNamespace());
        assertEquals("width", pair.getPredicate());
        assertEquals(Integer.valueOf(1961), pair.getUsage());
    }

    @Test
    public void testParseGetPredicates() throws Exception {
        InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/machinetags/sample_get_predicates.json"));
        Predicates predicates = new Gson().fromJson(reader, Predicates.class);
        reader.close();
        assertNotNull(predicates);
        assertEquals("ok", predicates.getStat());
        assertEquals(0, predicates.getCode());
        assertEquals(Integer.valueOf(10), predicates.getPage());
        assertEquals(Integer.valueOf(7039), predicates.getTotal());
        assertEquals(Integer.valueOf(10), predicates.getPerPage());
        assertEquals(Integer.valueOf(704), predicates.getPages());
        assertNotNull(predicates.getPredicateList());
        assertEquals(10, predicates.getPredicateList().size());
        Predicates.Predicate predicate = predicates.getPredicateList().get(0);
        assertEquals("admin_level", predicate.getPredicate());
        assertEquals(Integer.valueOf(2), predicate.getUsage());
        assertEquals(Integer.valueOf(1), predicate.getNamespaces());
    }

    @Test
    public void testParseGetValues() throws Exception {
        InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/machinetags/sample_get_values.json"));
        Values values = new Gson().fromJson(reader, Values.class);
        reader.close();
        assertNotNull(values);
        assertEquals("ok", values.getStat());
        assertEquals(0, values.getCode());
        assertEquals(Integer.valueOf(1), values.getPage());
        assertEquals(Integer.valueOf(7), values.getTotal());
        assertEquals(Integer.valueOf(500), values.getPerPage());
        assertEquals(Integer.valueOf(1), values.getPages());
        assertNotNull(values.getValueList());
        assertEquals(7, values.getValueList().size());
        Values.Value value = values.getValueList().get(0);
        assertEquals("holden", value.getValue());
        assertEquals(Integer.valueOf(4), value.getUsage());
        assertNull(value.getNamespace());
        assertNull(value.getPredicate());
        assertNull(value.getFirstAdded());
        assertNull(value.getLastAdded());
    }

    @Test
    public void testParseGetRecentValues() throws Exception {
        InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/machinetags/sample_get_recent_values.json"));
        Values values = new Gson().fromJson(reader, Values.class);
        reader.close();
        assertNotNull(values);
        assertEquals("ok", values.getStat());
        assertEquals(0, values.getCode());
        assertEquals(Integer.valueOf(1), values.getPage());
        assertEquals(Integer.valueOf(7), values.getTotal());
        assertEquals(Integer.valueOf(500), values.getPerPage());
        assertEquals(Integer.valueOf(1), values.getPages());
        assertNotNull(values.getValueList());
        assertEquals(7, values.getValueList().size());
        Values.Value value = values.getValueList().get(0);
        assertEquals("mazda", value.getValue());
        assertEquals(Integer.valueOf(8), value.getUsage());
        assertEquals("car", value.getNamespace());
        assertEquals("make", value.getPredicate());
        assertEquals("1279193548", value.getFirstAdded());
        assertEquals("1279193549", value.getLastAdded());
    }

}
