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

package net.jeremybrooks.jinx.response.photos.upload;

import com.google.gson.Gson;
import net.jeremybrooks.jinx.JinxConstants;
import net.jeremybrooks.jinx.response.activity.ActivityResponseTest;
import org.junit.Test;

import java.io.InputStreamReader;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

/**
 * Created by jeremyb on 8/1/14.
 */
public class PhotosUploadTest {
    @Test
    public void testParseCheckTickets() throws Exception {
        InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/photos/upload/sample_check_tickets.json"));
        CheckTicketsResponse checkTicketsResponse = new Gson().fromJson(reader, CheckTicketsResponse.class);
        reader.close();
        assertNotNull(checkTicketsResponse);
        assertEquals("ok", checkTicketsResponse.getStat());
        assertEquals(0, checkTicketsResponse.getCode());
        assertNotNull(checkTicketsResponse.getTicketList());
        assertEquals(4, checkTicketsResponse.getTicketList().size());
        List<CheckTicketsResponse.Ticket> ticketList = checkTicketsResponse.getTicketList();
        CheckTicketsResponse.Ticket ticket = ticketList.get(0);
        assertEquals("123", ticket.getTicketId());
        assertEquals(JinxConstants.TicketStatus.invalid, ticket.getTicketStatus());

        ticket = ticketList.get(1);
        assertEquals("234", ticket.getTicketId());
        assertEquals(JinxConstants.TicketStatus.completed, ticket.getTicketStatus());
        assertEquals("1234565", ticket.getPhotoId());

        ticket = ticketList.get(2);
        assertEquals("1123", ticket.getTicketId());
        assertEquals(JinxConstants.TicketStatus.not_completed, ticket.getTicketStatus());

        ticket = ticketList.get(3);
        assertEquals("2334", ticket.getTicketId());
        assertEquals(JinxConstants.TicketStatus.failed, ticket.getTicketStatus());
    }

    @Test
    public void testParseUploadResponse() throws Exception {
        InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/photos/upload/sample_upload.json"));
        UploadResponse response = new Gson().fromJson(reader, UploadResponse.class);
        reader.close();
        assertNotNull(response);
        assertEquals("ok", response.getStat());
        assertEquals(0, response.getCode());
        assertEquals("14837291641", response.getPhotoId());
    }

    @Test
    public void testParseReplaceResponse() throws Exception {
        InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/photos/upload/sample_replace.json"));
        ReplaceResponse response = new Gson().fromJson(reader, ReplaceResponse.class);
        reader.close();
        assertNotNull(response);
        assertEquals("ok", response.getStat());
        assertEquals(0, response.getCode());

        assertEquals("320935a61d", response.getSecret());
        assertEquals("829e925e23", response.getOriginalSecret());
        assertEquals("14859730194", response.getPhotoId());
    }

    @Test
    public void testParseReplaceAsyncResponse() throws Exception {
        InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/photos/upload/sample_replace_async.json"));
        ReplaceResponse response = new Gson().fromJson(reader, ReplaceResponse.class);
        reader.close();
        assertNotNull(response);
        assertEquals("ok", response.getStat());
        assertEquals(0, response.getCode());

        assertEquals("124834485-72157646287534644", response.getTicketId());
    }
}
