package com.upsight.android.analytics.internal.session;

public abstract interface Clock
{
  public abstract long currentTimeMillis();
  
  public abstract long currentTimeSeconds();
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/session/Clock.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */