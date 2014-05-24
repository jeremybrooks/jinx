package net.jeremybrooks.jinx.api;

import net.jeremybrooks.jinx.Jinx;
import net.jeremybrooks.jinx.OAuthAccessToken;
import net.jeremybrooks.jinx.response.cameras.CameraBrands;
import net.jeremybrooks.jinx.response.cameras.CameraModels;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

/**
 * @author Jeremy Brooks
 */
public class CamerasApiTest {
	private static CamerasApi camerasApi;

		@BeforeClass
		public static void beforeClass() throws Exception {
			Properties p = new Properties();
			p.load(OAuthApiTest.class.getResourceAsStream("/response/auth/secret.properties"));

			String filename = p.getProperty("path.to.oauth.token");
			assertNotNull(filename);

			File file = new File(filename);
			assertTrue(file.exists());

			OAuthAccessToken oAuthAccessToken = new OAuthAccessToken();
			oAuthAccessToken.load(new FileInputStream(file));

			assertNotNull(oAuthAccessToken);

			camerasApi = new CamerasApi(new Jinx(p.getProperty("flickr.key"), p.getProperty("flickr.secret"), oAuthAccessToken));
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
				assertEquals(21.1, camera.getMegapixels());
				assertEquals(3.0, camera.getLcdScreenSize());
				assertEquals("CompactFlash (CF) Card", camera.getMemoryType());
			}
		}
	}
}
