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

import net.jeremybrooks.jinx.response.Response;
import net.jeremybrooks.jinx.response.photos.notes.Note;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 *
 * Created by jeremyb on 7/21/14.
 */
public class PhotosNotesApiTest {

    private static PhotosNotesApi photosNotesApi;
    private static final String PHOTO_ID = "14069436767";

    @BeforeClass
    public static void beforeClass() throws Exception {
        photosNotesApi = new PhotosNotesApi(JinxApiTestCommon.getJinx());
    }

    @Test
    public void testAddEditDelete() throws Exception {
        Note note = photosNotesApi.add(PHOTO_ID, 10, 10, 100, 100, "Testing add.");
        assertNotNull(note);
        assertEquals("ok", note.getStat());
        assertEquals(0, note.getCode());

        Response response = photosNotesApi.edit(note.getNoteId(), 50, 50, 200, 200, "Testing edit.");
        assertNotNull(response);
        assertEquals("ok", response.getStat());
        assertEquals(0, response.getCode());

        photosNotesApi.delete(note.getNoteId());
        assertNotNull(response);
        assertEquals("ok", response.getStat());
        assertEquals(0, response.getCode());
    }
}
