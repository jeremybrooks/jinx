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
import net.jeremybrooks.jinx.response.photos.transform.TransformResult;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by jeremyb on 7/22/14.
 */
public class PhotosTransformApiTest {
    private static PhotosTransformApi photosTransformApi;

    @BeforeClass
    public static void beforeClass() throws Exception {
        photosTransformApi = new PhotosTransformApi(JinxApiTestCommon.getJinx());
    }

    @Test
    public void testRotate() throws Exception {
        TransformResult result = photosTransformApi.rotate("14253850972", JinxConstants.RotateDegrees._90);
        assertNotNull(result);
        assertEquals("ok", result.getStat());
        assertEquals(0, result.getCode());
        assertEquals("14253850972", result.getPhotoId());
    }
}
