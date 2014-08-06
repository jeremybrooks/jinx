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

package net.jeremybrooks.jinx.api;

import net.jeremybrooks.jinx.Jinx;
import net.jeremybrooks.jinx.JinxConstants;
import net.jeremybrooks.jinx.OAuthAccessToken;
import net.jeremybrooks.jinx.logger.JinxLogger;
import net.jeremybrooks.jinx.logger.StdoutLogger;
import net.jeremybrooks.jinx.response.photos.upload.CheckTicketsResponse;
import net.jeremybrooks.jinx.response.photos.upload.UploadResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by jeremyb on 8/1/14.
 */
public class PhotosUploadApiTest {
    private static PhotosUploadApi photosUploadApi;

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
        Jinx jinx = new Jinx(p.getProperty("flickr.key"), p.getProperty("flickr.secret"), oAuthAccessToken);
        jinx.setVerboseLogging(true);
        JinxLogger.setLogger(new StdoutLogger());
        photosUploadApi = new PhotosUploadApi(jinx);
    }

    @Test
    public void testCheckTickets() throws Exception {
        List<String> list = new ArrayList<String>();
        list.add("123");
        CheckTicketsResponse response = photosUploadApi.checkTickets(list);
        assertNotNull(response);
        assertEquals("ok", response.getStat());
        assertEquals(0, response.getCode());
        assertNotNull(response.getTicketList());
        assertEquals(1, response.getTicketList().size());
        CheckTicketsResponse.Ticket ticket = response.getTicketList().get(0);
        assertEquals("123", ticket.getTicketId());
        assertEquals(JinxConstants.TicketStatus.invalid, ticket.getTicketStatus());
    }

    @Test
    public void testUploadPhoto() throws Exception {
        // To run this test, point the photo variable to a photo on your hard drive
        String photo = null;
        photo = "/Users/jeremyb/Desktop/A Reason Why You Refuse.jpg";
        System.setProperty(JinxConstants.JINX_LOG_MULTIPART, "true");
        if (photo != null) {
            String description = "Street scene in Istanbul";
            List<String> tags = new ArrayList<String>();
            tags.add("Turkey");
            tags.add("Istanbul");
            tags.add("Hipstamatic");
            tags.add("Black and White");

            UploadResponse response = photosUploadApi.upload(new File(photo), null, description, tags, null, null, null, null, null, null);
            assertNotNull(response);
            assertEquals("ok", response.getStat());
            assertEquals(0, response.getCode());
            assertNotNull(response.getPhotoId());
        }
    }
}
