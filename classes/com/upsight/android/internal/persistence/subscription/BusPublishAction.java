package com.upsight.android.internal.persistence.subscription;

import com.squareup.otto.Bus;
import rx.functions.Action1;

class BusPublishAction
  implements Action1<DataStoreEvent>
{
  private final Bus bus;
  
  BusPublishAction(Bus paramBus)
  {
    this.bus = paramBus;
  }
  
  public void call(DataStoreEvent paramDataStoreEvent)
  {
    this.bus.post(paramDataStoreEvent);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/internal/persistence/subscription/BusPublishAction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */