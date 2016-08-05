package com.upsight.android.analytics.internal.dispatcher.routing;

import com.upsight.android.analytics.internal.dispatcher.delivery.Queue;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

class Route
{
  private int mCurrentStepIndex = 0;
  private List<RouteStep> mSteps;
  
  public Route(Route paramRoute)
  {
    this(paramRoute.mSteps);
  }
  
  public Route(List<RouteStep> paramList)
  {
    this.mSteps = new ArrayList(paramList.size());
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      RouteStep localRouteStep = (RouteStep)localIterator.next();
      this.mSteps.add(new RouteStep(localRouteStep));
    }
  }
  
  public void failedOnCurrentStep(String paramString)
  {
    ((RouteStep)this.mSteps.get(this.mCurrentStepIndex)).setFailureReason(paramString);
  }
  
  public Queue getCurrentQueue()
  {
    return ((RouteStep)this.mSteps.get(this.mCurrentStepIndex)).getQueue();
  }
  
  public String[] getRoutingStack()
  {
    LinkedList localLinkedList = new LinkedList();
    Iterator localIterator = this.mSteps.iterator();
    RouteStep localRouteStep;
    StringBuilder localStringBuilder;
    if (localIterator.hasNext())
    {
      localRouteStep = (RouteStep)localIterator.next();
      localStringBuilder = new StringBuilder().append(localRouteStep.getQueue().getName()).append(" - ");
      if (localRouteStep.getFailureReason() != null) {
        break label115;
      }
    }
    label115:
    for (String str = "delivered";; str = localRouteStep.getFailureReason())
    {
      localLinkedList.add(str);
      if (localRouteStep.getFailureReason() != null) {
        break;
      }
      return (String[])localLinkedList.toArray(new String[localLinkedList.size()]);
    }
  }
  
  public int getStepsCount()
  {
    return this.mSteps.size();
  }
  
  public boolean hasMoreSteps()
  {
    if (this.mCurrentStepIndex < -1 + this.mSteps.size()) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public void moveToNextStep()
  {
    if (!hasMoreSteps()) {
      throw new IllegalStateException("There are no more steps to move on");
    }
    this.mCurrentStepIndex = (1 + this.mCurrentStepIndex);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/dispatcher/routing/Route.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */