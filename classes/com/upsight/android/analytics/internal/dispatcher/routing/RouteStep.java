package com.upsight.android.analytics.internal.dispatcher.routing;

import com.upsight.android.analytics.internal.dispatcher.delivery.Queue;

class RouteStep
{
  private String mFailureReason;
  private Queue mQueue;
  
  public RouteStep(Queue paramQueue)
  {
    this.mQueue = paramQueue;
  }
  
  public RouteStep(RouteStep paramRouteStep)
  {
    this(paramRouteStep.mQueue);
  }
  
  public String getFailureReason()
  {
    return this.mFailureReason;
  }
  
  public Queue getQueue()
  {
    return this.mQueue;
  }
  
  public void setFailureReason(String paramString)
  {
    this.mFailureReason = paramString;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/dispatcher/routing/RouteStep.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */