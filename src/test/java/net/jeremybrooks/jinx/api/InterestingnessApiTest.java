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

import net.jeremybrooks.jinx.JinxConstants;
import net.jeremybrooks.jinx.response.photos.Photo;
import net.jeremybrooks.jinx.response.photos.Photos;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.EnumSet;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by jeremyb on 7/9/14.
 */
public class InterestingnessApiTest {

    private static InterestingnessApi interestingnessApi;

    @BeforeClass
    public static void beforeClass() throws Exception {
        interestingnessApi = new InterestingnessApi(JinxApiTestCommon.getJinx());
    }

    @Test
    public void testGetListNotSigned() throws Exception {
        Photos photos = interestingnessApi.getList(null, null, 0, 0, false);
        assertNotNull(photos);
        assertEquals("ok", photos.getStat());
        assertEquals(0, photos.getCode());
        assertNotNull(photos.getPhotoList());
        assertTrue(photos.getPhotoList().size() > 0);

        photos = interestingnessApi.getList("2014-04-03", EnumSet.of(JinxConstants.PhotoExtras.media), 0, 0, false);
        assertNotNull(photos);
        assertEquals("ok", photos.getStat());
        assertEquals(0, photos.getCode());
        assertNotNull(photos.getPhotoList());
        assertTrue(photos.getPhotoList().size() > 0);
        for (Photo p : photos.getPhotoList()) {
            assertNotNull(p.getMedia());
        }
    }

    @Test
    public void testGetListSigned() throws Exception {
        Photos photos = interestingnessApi.getList(null, null, 0, 0, true);
        assertNotNull(photos);
        assertEquals("ok", photos.getStat());
        assertEquals(0, photos.getCode());
        assertNotNull(photos.getPhotoList());
        assertTrue(photos.getPhotoList().size() > 0);

        photos = interestingnessApi.getList("2014-04-03", EnumSet.of(JinxConstants.PhotoExtras.media, JinxConstants.PhotoExtras.date_taken), 0, 0, true);
        assertNotNull(photos);
        assertEquals("ok", photos.getStat());
        assertEquals(0, photos.getCode());
        assertNotNull(photos.getPhotoList());
        assertTrue(photos.getPhotoList().size() > 0);
        for (Photo p : photos.getPhotoList()) {
            assertNotNull(p.getMedia());
            assertNotNull(p.getDateTaken());
        }
    }
}
