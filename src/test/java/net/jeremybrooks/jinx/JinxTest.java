/*
 * Jinx is Copyright 2010-2014 by Jeremy Brooks and Contributors
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

package net.jeremybrooks.jinx;

import net.jeremybrooks.jinx.api.OAuthApiTest;
import net.jeremybrooks.jinx.api.PhotosApi;
import net.jeremybrooks.jinx.response.photos.PhotoInfo;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Most of the Jinx class gets tested by the Api tests.
 * This class allows testing of specific behavior.
 *
 * @author Jeremy Brooks
 */
public class JinxTest {


	@Test
	public void testFlickrErrorTrue() throws Exception {
		Properties p = new Properties();
		p.load(OAuthApiTest.class.getResourceAsStream("/response/auth/secret.properties"));

		String filename = p.getProperty("path.to.oauth.token");
		assertNotNull(filename);

		File file = new File(filename);
		assertTrue(file.exists());

		OAuthAccessToken oAuthAccessToken = new OAuthAccessToken();
		oAuthAccessToken.load(new FileInputStream(file));

		assertNotNull(oAuthAccessToken);

		Jinx jinx = new Jinx(p.getProperty("flickr.key"), p.getProperty("flickr.secret"), oAuthAccessToken);
		PhotosApi photosApi = new PhotosApi(jinx);

		try {
			PhotoInfo info = photosApi.getInfo("nosuchphoto", null);
			// if we get here, there was no exception, so fail
			fail();
		} catch (JinxException je) {
			System.out.println(je);
			assertNotNull(je);
			assertNotEquals(0, je.getFlickrErrorCode());
			assertNotNull(je.getFlickrErrorMessage());
		}
	}

	@Test
		public void testFlickrErrorFalse() throws Exception {
		Properties p = new Properties();
		p.load(OAuthApiTest.class.getResourceAsStream("/response/auth/secret.properties"));

		String filename = p.getProperty("path.to.oauth.token");
		assertNotNull(filename);

		File file = new File(filename);
		assertTrue(file.exists());

		OAuthAccessToken oAuthAccessToken = new OAuthAccessToken();
		oAuthAccessToken.load(new FileInputStream(file));

		assertNotNull(oAuthAccessToken);

		Jinx jinx = new Jinx(p.getProperty("flickr.key"), p.getProperty("flickr.secret"), oAuthAccessToken);
		jinx.setFlickrErrorThrowsException(false);
		PhotosApi photosApi = new PhotosApi(jinx);

		PhotoInfo info = photosApi.getInfo("nosuchphoto", null);
		assertNotEquals(0, info.getCode());
	}
}