package com.upsight.android.analytics.internal.dispatcher.routing;

import com.upsight.android.analytics.internal.dispatcher.delivery.QueueConfig;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RoutingConfig
{
  private Map<String, QueueConfig> mQueuesConfigs;
  private Map<String, List<String>> mRoutes;
  
  private RoutingConfig(Builder paramBuilder)
  {
    this.mQueuesConfigs = paramBuilder.mQueueConfigs;
    this.mRoutes = paramBuilder.mRoutes;
  }
  
  private boolean areQueueConfigsValid()
  {
    Iterator localIterator = this.mQueuesConfigs.values().iterator();
    QueueConfig localQueueConfig;
    do
    {
      if (!localIterator.hasNext()) {
        break;
      }
      localQueueConfig = (QueueConfig)localIterator.next();
    } while ((localQueueConfig != null) && (localQueueConfig.isValid()));
    for (boolean bool = false;; bool = true) {
      return bool;
    }
  }
  
  private boolean areRoutesValid()
  {
    boolean bool = false;
    Iterator localIterator1 = this.mRoutes.values().iterator();
    List localList;
    if (localIterator1.hasNext())
    {
      localList = (List)localIterator1.next();
      if (localList != null) {}
    }
    for (;;)
    {
      return bool;
      Iterator localIterator2 = localList.iterator();
      for (;;)
      {
        if (localIterator2.hasNext())
        {
          String str = (String)localIterator2.next();
          if ((!this.mQueuesConfigs.containsKey(str)) && (!"trash".equals(str))) {
            break;
          }
        }
      }
      if (new HashSet(localList).size() == localList.size()) {
        break;
      }
      continue;
      bool = true;
    }
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool = true;
    if (this == paramObject) {}
    for (;;)
    {
      return bool;
      if ((paramObject == null) || (getClass() != paramObject.getClass()))
      {
        bool = false;
      }
      else
      {
        RoutingConfig localRoutingConfig = (RoutingConfig)paramObject;
        if ((!localRoutingConfig.mQueuesConfigs.equals(this.mQueuesConfigs)) || (!localRoutingConfig.mRoutes.equals(this.mRoutes))) {
          bool = false;
        }
      }
    }
  }
  
  public Map<String, QueueConfig> getQueueConfigs()
  {
    return Collections.unmodifiableMap(this.mQueuesConfigs);
  }
  
  public Map<String, List<String>> getRouters()
  {
    return Collections.unmodifiableMap(this.mRoutes);
  }
  
  public boolean isValid()
  {
    if ((areQueueConfigsValid()) && (areRoutesValid())) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public static class Builder
  {
    private Map<String, QueueConfig> mQueueConfigs = new HashMap();
    private Map<String, List<String>> mRoutes = new HashMap();
    
    public Builder addQueueConfig(String paramString, QueueConfig paramQueueConfig)
    {
      if (this.mQueueConfigs.containsKey(paramString)) {
        throw new IllegalArgumentException("Queue with name " + paramString + " already exists");
      }
      this.mQueueConfigs.put(paramString, paramQueueConfig);
      return this;
    }
    
    public Builder addRoute(String paramString, List<String> paramList)
    {
      if (this.mRoutes.containsKey(paramString)) {
        throw new IllegalArgumentException("Filter with name " + paramString + " already exists");
      }
      this.mRoutes.put(paramString, paramList);
      return this;
    }
    
    public RoutingConfig build()
    {
      return new RoutingConfig(this, null);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/dispatcher/routing/RoutingConfig.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */