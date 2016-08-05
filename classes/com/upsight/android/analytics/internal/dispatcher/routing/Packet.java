package com.upsight.android.analytics.internal.dispatcher.routing;

import com.upsight.android.analytics.internal.DataStoreRecord;
import com.upsight.android.analytics.internal.dispatcher.delivery.Queue;

public class Packet
{
  private final DataStoreRecord mEvent;
  private final Route mRoute;
  private State mState = State.UNDELIVERED;
  private boolean mTriedOnCurrentStep;
  
  Packet(DataStoreRecord paramDataStoreRecord, Route paramRoute)
  {
    this.mEvent = paramDataStoreRecord;
    this.mRoute = paramRoute;
  }
  
  public void failAndRoute(String paramString)
  {
    if (State.DELIVERED.equals(this.mState)) {
      throw new IllegalStateException("Packet could not be failed because it was already delivered successfully");
    }
    this.mRoute.failedOnCurrentStep(paramString);
    if (this.mRoute.hasMoreSteps())
    {
      this.mTriedOnCurrentStep = false;
      this.mRoute.moveToNextStep();
    }
  }
  
  public String getDeliveryHistory()
  {
    String[] arrayOfString = this.mRoute.getRoutingStack();
    StringBuilder localStringBuilder = new StringBuilder();
    int i = arrayOfString.length;
    for (int j = 0; j < i; j++) {
      localStringBuilder.append(arrayOfString[j]).append('\n');
    }
    return localStringBuilder.deleteCharAt(-1 + localStringBuilder.length()).toString();
  }
  
  public DataStoreRecord getRecord()
  {
    return this.mEvent;
  }
  
  public State getState()
  {
    return this.mState;
  }
  
  public boolean hasMoreOptionsToTry()
  {
    if ((this.mRoute.hasMoreSteps()) || (!this.mTriedOnCurrentStep)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public void markDelivered()
  {
    this.mState = State.DELIVERED;
  }
  
  public void markTrashed()
  {
    this.mState = State.TRASHED;
  }
  
  public void routeToNext()
  {
    this.mTriedOnCurrentStep = true;
    this.mRoute.getCurrentQueue().enqueuePacket(this);
  }
  
  public static enum State
  {
    static
    {
      DELIVERED = new State("DELIVERED", 1);
      TRASHED = new State("TRASHED", 2);
      State[] arrayOfState = new State[3];
      arrayOfState[0] = UNDELIVERED;
      arrayOfState[1] = DELIVERED;
      arrayOfState[2] = TRASHED;
      $VALUES = arrayOfState;
    }
    
    private State() {}
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/dispatcher/routing/Packet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */