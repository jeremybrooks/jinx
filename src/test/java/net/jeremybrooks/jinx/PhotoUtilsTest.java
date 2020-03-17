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

package net.jeremybrooks.jinx;

import net.jeremybrooks.jinx.api.OAuthApiTest;
import net.jeremybrooks.jinx.api.PhotosApi;
import net.jeremybrooks.jinx.response.photos.PhotoInfo;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class PhotoUtilsTest {

	@Test
	public void testGetImageForSize() throws Exception {
		// setup and make a call to get PhotoInfo
		Properties p = new Properties();
		p.load(OAuthApiTest.class.getResourceAsStream("/response/auth/secret.properties"));
		String filename = p.getProperty("path.to.oauth.token");
		assertNotNull(filename);
		File file = new File(filename);
		assertTrue(file.exists());
		OAuthAccessToken oAuthAccessToken = new OAuthAccessToken();
		oAuthAccessToken.load(new FileInputStream(file));
		assertNotNull(oAuthAccessToken);
		PhotosApi photosApi = new PhotosApi(new Jinx(p.getProperty("flickr.key"), p.getProperty("flickr.secret"),
        oAuthAccessToken, JinxConstants.OAuthPermissions.delete));
		PhotoInfo info = photosApi.getInfo("14276354684", null);

		// use PhotoInfo to get the image
		assertNotNull(info);
		assertNotNull(PhotoUtils.getImageForSize(JinxConstants.PhotoSize.SIZE_THUMBNAIL, info));

		// use data from PhotoInfo to get the image
		assertNotNull(PhotoUtils.getImageForSize(JinxConstants.PhotoSize.SIZE_LARGE, info.getPhotoId(), info.getSecret(), info.getFarm(), info.getServer(), info.getOriginalFormat(), info.getOriginalSecret()));
	}

    @Test
    public void testGetImageForSizeWithProxy() throws Exception {
//        String proxyHost = "host.company.com";
//        int proxyPort = 0;
//        String proxyUser = "username";
//        char[] proxyPass = "password".toCharArray();
//
//        // setup and make a call to get PhotoInfo
//        Properties p = new Properties();
//        p.load(OAuthApiTest.class.getResourceAsStream("/response/auth/secret.properties"));
//        String filename = p.getProperty("path.to.oauth.token");
//        assertNotNull(filename);
//        File file = new File(filename);
//        assertTrue(file.exists());
//        OAuthAccessToken oAuthAccessToken = new OAuthAccessToken();
//        oAuthAccessToken.load(new FileInputStream(file));
//        assertNotNull(oAuthAccessToken);
//        Jinx jinx = new Jinx(p.getProperty("flickr.key"), p.getProperty("flickr.secret"), oAuthAccessToken);
//        jinx.setVerboseLogging(true);
//        JinxLogger.setLogger(new StdoutLogger());
//        JinxProxy jinxProxy = new JinxProxy(proxyHost, proxyPort, proxyUser, proxyPass);
//        jinx.setProxy(jinxProxy);
//        PhotosApi photosApi = new PhotosApi(jinx);
//        PhotoInfo info = photosApi.getInfo("14276354684", null);
//
//        // use PhotoInfo to get the image
//        assertNotNull(info);
//        assertNotNull(PhotoUtils.getImageForSize(JinxConstants.PhotoSize.SIZE_THUMBNAIL, info));
//
//        // use data from PhotoInfo to get the image
//        assertNotNull(PhotoUtils.getImageForSize(JinxConstants.PhotoSize.SIZE_LARGE, info.getPhotoId(), info.getSecret(), info.getFarm(), info.getServer(), info.getOriginalFormat(), info.getOriginalSecret()));
    }
}