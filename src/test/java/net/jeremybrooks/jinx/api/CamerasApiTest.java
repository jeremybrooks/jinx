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

package net.jeremybrooks.jinx.api;

import net.jeremybrooks.jinx.response.cameras.CameraBrands;
import net.jeremybrooks.jinx.response.cameras.CameraModels;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


/**
 * @author Jeremy Brooks
 */
public class CamerasApiTest {
	private static CamerasApi camerasApi;

	@BeforeClass
	public static void beforeClass() throws Exception {
		camerasApi = new CamerasApi(JinxApiTestCommon.getJinx());
	}

	@Test
	public void testGetBrands() throws Exception {
		CameraBrands cameraBrands = camerasApi.getBrands();
		assertNotNull(cameraBrands);
		assertNotNull(cameraBrands.getBrandList());
		assertTrue(cameraBrands.getBrandList().size() > 0);
		for (CameraBrands.Brand brand : cameraBrands.getBrandList()) {
			assertNotNull(brand.getId());
			assertNotNull(brand.getName());
			if (brand.getId().equals("canon")) {
				assertEquals("Canon", brand.getName());
			}
			if (brand.getId().equals("apple")) {
				assertEquals("Apple", brand.getName());
			}
		}
	}

	@Test
	public void testGetBrandModels() throws Exception {
		CameraModels cameraModels = camerasApi.getBrandModels("canon");
		assertNotNull(cameraModels);
		for (CameraModels.Camera camera : cameraModels.getCameraList()) {
			if (camera.getId().equals("eos_5d_mark_ii")) {
				assertEquals("Canon EOS 5D Mark II", camera.getName());
				assertEquals(Float.valueOf(21.1f), camera.getMegapixels());
				assertEquals(Float.valueOf(3.0f), camera.getLcdScreenSize());
				assertEquals("CompactFlash (CF) Card", camera.getMemoryType());
			}
		}
	}
}
