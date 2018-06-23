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

package net.jeremybrooks.jinx.response.cameras;

import com.google.gson.Gson;
import net.jeremybrooks.jinx.response.activity.ActivityResponseTest;
import org.junit.Test;

import java.io.InputStreamReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Jeremy Brooks
 */
public class CameraModelsTest {

	@Test
	public void testParseModelsFromJson() throws Exception {
		InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/cameras/sample_brand_models.json"));
		CameraModels models = new Gson().fromJson(reader, CameraModels.class);
		reader.close();

		assertNotNull(models);
		assertEquals("ok", models.getStat());

		assertEquals("canon", models.getBrand());
		assertEquals(6, models.getCameraList().size());
		for (CameraModels.Camera camera : models.getCameraList()) {
			assertNotNull(camera.getId());
			assertNotNull(camera.getName());
			assertNotNull(camera.getMemoryType());
			assertTrue(camera.getMegapixels() > 0);
			assertNotNull(camera.getSmallImageUrl());
			assertNotNull(camera.getLargeImageUrl());
			if (camera.getName().equals("Canon EOS 5D Mark II")) {
				assertEquals(Float.valueOf(3.0f), camera.getLcdScreenSize());
				assertEquals(Float.valueOf(21.1f), camera.getMegapixels());
			}
			if (camera.getName().equals("Canon EOS 7D")) {
				assertEquals(Float.valueOf(3.0f), camera.getLcdScreenSize());
				assertEquals(Float.valueOf(18.0f), camera.getMegapixels());
			}
			if (camera.getName().equals("Canon EOS REBEL T2i")) {
				assertEquals(Float.valueOf(3.0f), camera.getLcdScreenSize());
				assertEquals(Float.valueOf(18.0f), camera.getMegapixels());
			}
			if (camera.getName().equals("Canon EOS REBEL T3i")) {
				assertEquals(Float.valueOf(3.0f), camera.getLcdScreenSize());
				assertEquals(Float.valueOf(18.0f), camera.getMegapixels());
			}
			if (camera.getName().equals("Canon EOS 60D")) {
				assertEquals(Float.valueOf(3.0f), camera.getLcdScreenSize());
				assertEquals(Float.valueOf(18.0f), camera.getMegapixels());
			}
			if (camera.getName().equals("Canon EOS 5D Mark III")) {
				assertEquals(Float.valueOf(3.2f), camera.getLcdScreenSize());
				assertEquals(Float.valueOf(22.3f), camera.getMegapixels());
			}
		}
	}
}
