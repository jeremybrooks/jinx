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
