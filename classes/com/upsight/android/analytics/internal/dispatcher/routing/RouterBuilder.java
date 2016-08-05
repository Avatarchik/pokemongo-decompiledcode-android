package com.upsight.android.analytics.internal.dispatcher.routing;

import com.upsight.android.analytics.internal.dispatcher.delivery.Queue;
import com.upsight.android.analytics.internal.dispatcher.delivery.Queue.Trash;
import com.upsight.android.analytics.internal.dispatcher.delivery.QueueBuilder;
import com.upsight.android.analytics.internal.dispatcher.delivery.QueueConfig;
import com.upsight.android.analytics.internal.dispatcher.schema.Schema;
import com.upsight.android.analytics.internal.dispatcher.util.ByFilterSelector;
import com.upsight.android.analytics.internal.dispatcher.util.Selector;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import rx.Scheduler;

public class RouterBuilder
{
  private QueueBuilder mQueueBuilder;
  private Scheduler mScheduler;
  
  RouterBuilder(Scheduler paramScheduler, QueueBuilder paramQueueBuilder)
  {
    this.mScheduler = paramScheduler;
    this.mQueueBuilder = paramQueueBuilder;
  }
  
  private Map<String, Queue> buildQueues(RoutingConfig paramRoutingConfig, Selector<Schema> paramSelector1, Selector<Schema> paramSelector2)
  {
    HashMap localHashMap = new HashMap();
    Queue.Trash localTrash = new Queue.Trash();
    localHashMap.put(localTrash.getName(), localTrash);
    Iterator localIterator = paramRoutingConfig.getQueueConfigs().entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      localHashMap.put(localEntry.getKey(), this.mQueueBuilder.build((String)localEntry.getKey(), (QueueConfig)localEntry.getValue(), paramSelector1, paramSelector2));
    }
    return localHashMap;
  }
  
  private Map<String, Route> buildRoutes(RoutingConfig paramRoutingConfig, Map<String, Queue> paramMap)
  {
    HashMap localHashMap = new HashMap();
    Iterator localIterator1 = paramRoutingConfig.getRouters().entrySet().iterator();
    while (localIterator1.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator1.next();
      LinkedList localLinkedList = new LinkedList();
      Iterator localIterator2 = ((List)localEntry.getValue()).iterator();
      while (localIterator2.hasNext()) {
        localLinkedList.add(new RouteStep((Queue)paramMap.get((String)localIterator2.next())));
      }
      localHashMap.put(localEntry.getKey(), new Route(localLinkedList));
    }
    return localHashMap;
  }
  
  public Router build(RoutingConfig paramRoutingConfig, Selector<Schema> paramSelector1, Selector<Schema> paramSelector2, RoutingListener paramRoutingListener)
  {
    Map localMap1 = buildQueues(paramRoutingConfig, paramSelector1, paramSelector2);
    Map localMap2 = buildRoutes(paramRoutingConfig, localMap1);
    Router localRouter = new Router(this.mScheduler, new ByFilterSelector(localMap2), paramRoutingListener);
    Iterator localIterator = localMap1.values().iterator();
    while (localIterator.hasNext())
    {
      Queue localQueue = (Queue)localIterator.next();
      localQueue.setOnDeliveryListener(localRouter);
      localQueue.setOnResponseListener(localRouter);
    }
    return localRouter;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/dispatcher/routing/RouterBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */