/*
 * Jinx is Copyright 2010-2017 by Jeremy Brooks and Contributors
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

package net.jeremybrooks.jinx.response.groups.discuss.replies;

import net.jeremybrooks.jinx.response.Response;

/**
 * Created by jeremyb on 7/9/14.
 */
public class ReplyInfo extends Response {

    private static final long serialVersionUID = 6169655128600395515L;
    private Reply reply;
    public Reply getReply() { return reply;}

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ReplyInfo{");
        sb.append("reply=").append(reply);
        sb.append('}');
        return sb.toString();
    }
}
