/*
 * Jinx is Copyright 2010-2023 by Jeremy Brooks and Contributors
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

package net.jeremybrooks.jinx.response.push;

import com.google.gson.annotations.SerializedName;
import net.jeremybrooks.jinx.response.Response;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jeremy Brooks
 */
public class Subscriptions extends Response {
  private static final long serialVersionUID = 8630633471818855463L;
  private _Subscriptions subscriptions;

  public List<Subscription> getSubscriptionList() {return subscriptions == null ? null : subscriptions.subscriptionList; }

  private class _Subscriptions implements Serializable {
    private static final long serialVersionUID = -8063916700882783263L;
    @SerializedName("subscription")
    private List<Subscription> subscriptionList;
  }
}
