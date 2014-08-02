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

import com.google.gson.annotations.SerializedName;
import net.jeremybrooks.jinx.JinxConstants;
import net.jeremybrooks.jinx.response.Response;

import java.io.Serializable;
import java.util.List;

/**
 *
 * Created by jeremyb on 8/1/14.
 */
public class CheckTicketsResponse extends Response {

    public List<Ticket> getTicketList() { return uploader == null ? null : uploader.ticketList; }

    private static final long serialVersionUID = 3751280487201389309L;
    private _Uploader uploader;

    private class _Uploader implements Serializable {
        private static final long serialVersionUID = 843286744699575080L;
        @SerializedName("ticket")
        private List<Ticket> ticketList;
    }
    public class Ticket implements Serializable {
        private static final long serialVersionUID = -6361399110929923584L;
        @SerializedName("id")
        private String ticketId;
        @SerializedName("photoid")
        private String photoId;
        private String invalid;
        private String complete;

        public String getTicketId() { return ticketId; }
        public String getPhotoId() { return photoId; }
        public JinxConstants.TicketStatus getTicketStatus() {
            if (invalid != null && invalid.equals("1")) {
                return JinxConstants.TicketStatus.invalid;
            } else if (complete != null ){
                if (complete.equals("0")) {
                    return JinxConstants.TicketStatus.not_completed;
                } else if (complete.equals("1")) {
                    return JinxConstants.TicketStatus.completed;
                } else if (complete.equals("2")) {
                    return JinxConstants.TicketStatus.failed;
                } else {
                    return JinxConstants.TicketStatus.undefined;
                }
            } else {
                return JinxConstants.TicketStatus.undefined;
            }
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Ticket{");
            sb.append("ticketId='").append(ticketId).append('\'');
            sb.append(", photoId='").append(photoId).append('\'');
            sb.append(", ticketStatus='").append(getTicketStatus().toString()).append('\'');
            sb.append(", complete='").append(complete).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }
}
