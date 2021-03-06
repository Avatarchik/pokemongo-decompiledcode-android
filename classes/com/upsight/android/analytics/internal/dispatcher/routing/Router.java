package com.upsight.android.analytics.internal.dispatcher.routing;

import com.upsight.android.analytics.dispatcher.EndpointResponse;
import com.upsight.android.analytics.internal.DataStoreRecord;
import com.upsight.android.analytics.internal.dispatcher.delivery.OnDeliveryListener;
import com.upsight.android.analytics.internal.dispatcher.delivery.OnResponseListener;
import com.upsight.android.analytics.internal.dispatcher.util.ByFilterSelector;
import java.util.concurrent.atomic.AtomicInteger;
import rx.Scheduler;
import rx.Scheduler.Worker;
import rx.functions.Action0;

public class Router
  implements OnDeliveryListener, OnResponseListener
{
  private final AtomicInteger mEventsInRouting = new AtomicInteger();
  private boolean mIsFinishRequested;
  private final ByFilterSelector<Route> mRouteSelector;
  private final RoutingListener mRoutingListener;
  private final Scheduler.Worker mWorker;
  
  Router(Scheduler paramScheduler, ByFilterSelector<Route> paramByFilterSelector, RoutingListener paramRoutingListener)
  {
    this.mWorker = paramScheduler.createWorker();
    this.mRouteSelector = paramByFilterSelector;
    this.mRoutingListener = paramRoutingListener;
  }
  
  private void finishPacket()
  {
    int i = this.mEventsInRouting.decrementAndGet();
    if ((this.mIsFinishRequested) && (i == 0)) {
      this.mRoutingListener.onRoutingFinished(this);
    }
  }
  
  public void finishRouting()
  {
    this.mIsFinishRequested = true;
    if (this.mEventsInRouting.get() == 0) {
      this.mRoutingListener.onRoutingFinished(this);
    }
  }
  
  public void onDelivery(final Packet paramPacket)
  {
    this.mWorker.schedule(new Action0()
    {
      public void call()
      {
        Packet.State localState = paramPacket.getState();
        switch (Router.3.$SwitchMap$com$upsight$android$analytics$internal$dispatcher$routing$Packet$State[localState.ordinal()])
        {
        }
        for (;;)
        {
          return;
          if (paramPacket.hasMoreOptionsToTry())
          {
            paramPacket.routeToNext();
          }
          else
          {
            Router.this.mRoutingListener.onDelivery(paramPacket.getRecord(), false, false, paramPacket.getDeliveryHistory());
            Router.this.finishPacket();
            continue;
            Router.this.mRoutingListener.onDelivery(paramPacket.getRecord(), true, false, null);
            Router.this.finishPacket();
            continue;
            Router.this.mRoutingListener.onDelivery(paramPacket.getRecord(), false, true, paramPacket.getDeliveryHistory());
            Router.this.finishPacket();
          }
        }
      }
    });
  }
  
  public void onResponse(final EndpointResponse paramEndpointResponse)
  {
    this.mWorker.schedule(new Action0()
    {
      public void call()
      {
        Router.this.mRoutingListener.onResponse(paramEndpointResponse);
      }
    });
  }
  
  public boolean routeEvent(DataStoreRecord paramDataStoreRecord)
  {
    if (this.mIsFinishRequested) {
      throw new IllegalStateException("Router is requested to finish routing");
    }
    Route localRoute = (Route)this.mRouteSelector.select(paramDataStoreRecord.getSourceType());
    if ((localRoute == null) || (localRoute.getStepsCount() == 0)) {}
    for (boolean bool = false;; bool = true)
    {
      return bool;
      new Packet(paramDataStoreRecord, new Route(localRoute)).routeToNext();
      this.mEventsInRouting.incrementAndGet();
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/dispatcher/routing/Router.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */