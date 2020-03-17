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

package net.jeremybrooks.jinx.api;

import net.jeremybrooks.jinx.response.panda.Pandas;
import net.jeremybrooks.jinx.response.photos.Photos;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by jeremyb on 7/14/14.
 */
public class PandaApiTest {
    private static PandaApi pandasApi;

    @BeforeClass
    public static void beforeClass() throws Exception {
        pandasApi = new PandaApi(JinxApiTestCommon.getJinx());
    }

    @Test
    public void testGetList() throws Exception {
        Pandas pandas = pandasApi.getList();
        assertNotNull(pandas);
        assertEquals("ok", pandas.getStat());
        assertEquals(0, pandas.getCode());
        assertNotNull(pandas.getPandaList());
    }

    @Test
    public void testGetPhotos() throws Exception {
        Photos photos = pandasApi.getPhotos("ling ling", null, 10, 0, false);
        assertNotNull(photos);
        assertEquals("ok", photos.getStat());
        assertEquals(0, photos.getCode());
    }
}
