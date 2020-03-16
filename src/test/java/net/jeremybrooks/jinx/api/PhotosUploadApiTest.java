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

import net.jeremybrooks.jinx.response.photos.upload.CheckTicketsResponse;
import net.jeremybrooks.jinx.response.photos.upload.ReplaceResponse;
import net.jeremybrooks.jinx.response.photos.upload.UploadResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by jeremyb on 8/1/14.
 */
public class PhotosUploadApiTest {
    private static PhotosUploadApi photosUploadApi;

    @BeforeClass
    public static void beforeClass() throws Exception {
        photosUploadApi = new PhotosUploadApi(JinxApiTestCommon.getJinx());
    }


    @Test
    public void testUploadPhoto() throws Exception {
        BufferedInputStream in = new BufferedInputStream(PhotosUploadApiTest.class.getResourceAsStream("/Clear Implications.jpg"));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        for (int readNum; (readNum = in.read(buf)) != -1;) {
            baos.write(buf, 0, readNum);
        }
        byte[] photoData = baos.toByteArray();

        String title = "Clear Implications";
        String description = "Reflections on the streets of San Francisco.";
        List<String> tags = new ArrayList<String>();
        tags.add("Jinx Upload Test");

        UploadResponse response = photosUploadApi.upload(
                photoData,
                title,
                description,
                tags,
                null,   // isPublic
                null,   // isFriend
                null,   // isFamily
                null,   // safetyLevel
                null,   // contentType
                null,   // hidden
                false   // async
                );
        assertNotNull(response);
        assertEquals("ok", response.getStat());
        assertEquals(0, response.getCode());
        assertNotNull(response.getPhotoId());

        // uncomment to test replacing the uploaded photo
//        testReplacePhoto(response.getPhotoId());

        // uncomment to test async replace of uploaded photo
//        testReplacePhotoAsync(response.getPhotoId());
    }

    /**
     * To test uploading a file, uncomment and provide the full path to a photo.
     * @throws Exception
     */
    @Test
    public void testUploadPhotoFile() throws Exception {
//        File file = new File("/Users/jeremyb/Desktop/photo.JPG");
//        String description = "";
//        List<String> tags = new ArrayList<String>();
//        tags.add("Jinx Upload Test");
//
//        UploadResponse response = photosUploadApi.upload(
//                file,
//                null,   // title
//                description,
//                tags,
//                null,   // isPublic
//                null,   // isFriend
//                null,   // isFamily
//                null,   // safetyLevel
//                null,   // contentType
//                null,   // hidden
//                false   // async
//        );
//        assertNotNull(response);
//        assertEquals("ok", response.getStat());
//        assertEquals(0, response.getCode());
//        assertNotNull(response.getPhotoId());
    }

    @Test
    public void testAsyncUploadPhoto() throws Exception {
        BufferedInputStream in = new BufferedInputStream(PhotosUploadApiTest.class.getResourceAsStream("/Clear Implications.jpg"));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        for (int readNum; (readNum = in.read(buf)) != -1;) {
            baos.write(buf, 0, readNum);
        }
        byte[] photoData = baos.toByteArray();

        String title = "Clear Implications";
        String description = "Reflections on the streets of San Francisco.";
        List<String> tags = new ArrayList<String>();
        tags.add("Jinx Upload Test");

        UploadResponse response = photosUploadApi.upload(
                photoData,
                title,
                description,
                tags,
                null,   // isPublic
                null,   // isFriend
                null,   // isFamily
                null,   // safetyLevel
                null,   // contentType
                null,   // hidden
                true   // async
        );
        assertNotNull(response);
        assertEquals("ok", response.getStat());
        assertEquals(0, response.getCode());
        assertNotNull(response.getTicketId());

        testCheckTickets(response.getTicketId());
    }

    /*
     * Called by the async upload test
     */
    private void testCheckTickets(String ticketId) throws Exception {
        List<String> list = new ArrayList<String>();
        list.add(ticketId);
        CheckTicketsResponse response = photosUploadApi.checkTickets(list);
        assertNotNull(response);
        assertEquals("ok", response.getStat());
        assertEquals(0, response.getCode());
        assertNotNull(response.getTicketList());
        assertEquals(1, response.getTicketList().size());
        CheckTicketsResponse.Ticket ticket = response.getTicketList().get(0);
        assertEquals(ticketId, ticket.getTicketId());
        assertNotNull(ticket.getTicketStatus());
    }

    /**
     * To test async uploading a file, uncomment and provide the full path to a photo.
     * @throws Exception
     */
    @Test
    public void testAsyncUploadPhotoFile() throws Exception {
//        File file = new File("/Users/jeremyb/Desktop/photo.JPG");
//        String description = "";
//        List<String> tags = new ArrayList<String>();
//        tags.add("Jinx Upload Test");
//
//        UploadResponse response = photosUploadApi.upload(
//                file,
//                null,   // title
//                description,
//                tags,
//                null,   // isPublic
//                null,   // isFriend
//                null,   // isFamily
//                null,   // safetyLevel
//                null,   // contentType
//                null,   // hidden
//                true   // async
//        );
//        assertNotNull(response);
//        assertEquals("ok", response.getStat());
//        assertEquals(0, response.getCode());
//        assertNotNull(response.getTicketId());
    }


    /*
     * Called after the upload test passes
     */
    private void testReplacePhoto(String photoId) throws Exception {
        BufferedInputStream in = new BufferedInputStream(PhotosUploadApiTest.class.getResourceAsStream("/Realistic.jpg"));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        for (int readNum; (readNum = in.read(buf)) != -1;) {
            baos.write(buf, 0, readNum);
        }
        byte[] photoData = baos.toByteArray();

        ReplaceResponse response = photosUploadApi.replace(photoData, photoId, false);
        assertNotNull(response);
        assertEquals("ok", response.getStat());
        assertEquals(0, response.getCode());
        assertEquals(photoId, response.getPhotoId());
        assertNotNull(response.getSecret());
        assertNotNull(response.getOriginalSecret());
    }

    /*
     * Called after the upload test passes
     */
    private void testReplacePhotoAsync(String photoId) throws Exception {
        BufferedInputStream in = new BufferedInputStream(PhotosUploadApiTest.class.getResourceAsStream("/Realistic.jpg"));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        for (int readNum; (readNum = in.read(buf)) != -1;) {
            baos.write(buf, 0, readNum);
        }
        byte[] photoData = baos.toByteArray();

        ReplaceResponse response = photosUploadApi.replace(photoData, photoId, true);
        assertNotNull(response);
        assertEquals("ok", response.getStat());
        assertEquals(0, response.getCode());
        assertNotNull(response.getTicketId());
    }
}
