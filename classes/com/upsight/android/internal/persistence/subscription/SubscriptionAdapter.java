package com.upsight.android.internal.persistence.subscription;

import com.upsight.android.persistence.UpsightSubscription;
import rx.Subscription;

class SubscriptionAdapter
  implements UpsightSubscription
{
  private final Subscription mRxSubscription;
  
  SubscriptionAdapter(Subscription paramSubscription)
  {
    this.mRxSubscription = paramSubscription;
  }
  
  public boolean isSubscribed()
  {
    if (!this.mRxSubscription.isUnsubscribed()) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public void unsubscribe()
  {
    this.mRxSubscription.unsubscribe();
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/internal/persistence/subscription/SubscriptionAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */