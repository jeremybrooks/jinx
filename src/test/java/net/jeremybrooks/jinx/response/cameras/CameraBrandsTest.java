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
package net.jeremybrooks.jinx.response.cameras;

import com.google.gson.Gson;
import net.jeremybrooks.jinx.response.activity.ActivityResponseTest;
import org.junit.Test;

import java.io.InputStreamReader;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * @author Jeremy Brooks
 */
public class CameraBrandsTest {

	@Test
	public void testParseGetBrands() throws Exception {
		InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/cameras/sample_brands.json"));
		CameraBrands brands = new Gson().fromJson(reader, CameraBrands.class);
		reader.close();

		assertNotNull(brands);
		assertEquals("ok", brands.getStat());

		assertEquals(41, brands.getBrandList().size());
		for (CameraBrands.Brand brand : brands.getBrandList()) {
			assertNotNull(brand.getId());
			assertNotNull(brand.getName());
		}
	}
}
