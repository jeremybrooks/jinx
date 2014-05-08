/*
 * Jinx is Copyright 2010-2013 by Jeremy Brooks and Contributors
 *
 * This file is part of Jinx.
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

package net.jeremybrooks.jinx.response;

import com.google.gson.Gson;
import org.junit.Test;

import java.io.InputStreamReader;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

/**
 * @author Jeremy Brooks
 */
public class CameraModelsTest {

	@Test
	public void testModelsFromJson() throws Exception {
		InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/sample_brand_models.json"));
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
				assertEquals(3.0, camera.getLcdScreenSize());
				assertEquals(21.1, camera.getMegapixels());
			}
			if (camera.getName().equals("Canon EOS 7D")) {
				assertEquals(3.0, camera.getLcdScreenSize());
				assertEquals(18.0, camera.getMegapixels());
			}
			if (camera.getName().equals("Canon EOS REBEL T2i")) {
				assertEquals(3.0, camera.getLcdScreenSize());
				assertEquals(18.0, camera.getMegapixels());
			}
			if (camera.getName().equals("Canon EOS REBEL T3i")) {
				assertEquals(3.0, camera.getLcdScreenSize());
				assertEquals(18.0, camera.getMegapixels());
			}
			if (camera.getName().equals("Canon EOS 60D")) {
				assertEquals(3.0, camera.getLcdScreenSize());
				assertEquals(18.0, camera.getMegapixels());
			}
			if (camera.getName().equals("Canon EOS 5D Mark III")) {
				assertEquals(3.2, camera.getLcdScreenSize());
				assertEquals(22.3, camera.getMegapixels());
			}
		}
	}
}