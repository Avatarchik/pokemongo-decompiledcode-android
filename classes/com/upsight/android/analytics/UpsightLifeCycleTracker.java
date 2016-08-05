package com.upsight.android.analytics;

import android.app.Activity;
import com.upsight.android.analytics.internal.session.SessionInitializer;

public abstract interface UpsightLifeCycleTracker
{
  public static final String STARTED_FROM_PUSH = "pushMessage";
  
  public abstract void track(Activity paramActivity, ActivityState paramActivityState, SessionInitializer paramSessionInitializer);
  
  public static enum ActivityState
  {
    static
    {
      RESUMED = new ActivityState("RESUMED", 2);
      PAUSED = new ActivityState("PAUSED", 3);
      STOPPED = new ActivityState("STOPPED", 4);
      DESTROYED = new ActivityState("DESTROYED", 5);
      ActivityState[] arrayOfActivityState = new ActivityState[6];
      arrayOfActivityState[0] = CREATED;
      arrayOfActivityState[1] = STARTED;
      arrayOfActivityState[2] = RESUMED;
      arrayOfActivityState[3] = PAUSED;
      arrayOfActivityState[4] = STOPPED;
      arrayOfActivityState[5] = DESTROYED;
      $VALUES = arrayOfActivityState;
    }
    
    private ActivityState() {}
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/UpsightLifeCycleTracker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */