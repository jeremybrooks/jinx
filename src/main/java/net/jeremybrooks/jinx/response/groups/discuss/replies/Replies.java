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

package net.jeremybrooks.jinx.response.groups.discuss.replies;

import com.google.gson.annotations.SerializedName;
import net.jeremybrooks.jinx.response.Response;
import net.jeremybrooks.jinx.response.groups.discuss.topics.Topic;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jeremyb on 7/9/14.
 */
public class Replies extends Response {

    private static final long serialVersionUID = -4706291234604111014L;
    private _Replies replies;

    public Topic getTopic() { return replies == null ? null : replies.topic; }
    public List<Reply> getReplyList() { return (replies == null || replies.replyList == null) ? null : replies.replyList; }

    private class _Replies implements Serializable {
        private static final long serialVersionUID = -34862604382315662L;
        private Topic topic;
        @SerializedName("reply")
        private List<Reply> replyList;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Replies{");
        sb.append("topic=").append(getTopic());
        sb.append("replyList=").append(getReplyList() == null ? "null" : "[" + getReplyList().size() + " items]");
        sb.append('}');
        return sb.toString();
    }
}
