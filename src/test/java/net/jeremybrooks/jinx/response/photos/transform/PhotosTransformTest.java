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

package net.jeremybrooks.jinx.response.photos.transform;

import com.google.gson.Gson;
import net.jeremybrooks.jinx.response.activity.ActivityResponseTest;
import org.junit.Test;

import java.io.InputStreamReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by jeremyb on 7/22/14.
 */
public class PhotosTransformTest {
    @Test
    public void testParseTransform() throws Exception {
        InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/photos/transform/sample_transform.json"));
        TransformResult transformResult = new Gson().fromJson(reader, TransformResult.class);
        reader.close();
        assertNotNull(transformResult);
        assertEquals("ok", transformResult.getStat());
        assertEquals(0, transformResult.getCode());
        assertEquals("14253850972", transformResult.getPhotoId());
        assertEquals("889d85bc71", transformResult.getSecret());
        assertEquals("47705b0b17", transformResult.getOriginalSecret());
    }
}
