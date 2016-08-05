package com.upsight.android.analytics.internal;

import com.upsight.android.analytics.internal.session.Clock;
import dagger.internal.Factory;

public final class BaseAnalyticsModule_ProvideClockFactory
  implements Factory<Clock>
{
  private final BaseAnalyticsModule module;
  
  static
  {
    if (!BaseAnalyticsModule_ProvideClockFactory.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  public BaseAnalyticsModule_ProvideClockFactory(BaseAnalyticsModule paramBaseAnalyticsModule)
  {
    assert (paramBaseAnalyticsModule != null);
    this.module = paramBaseAnalyticsModule;
  }
  
  public static Factory<Clock> create(BaseAnalyticsModule paramBaseAnalyticsModule)
  {
    return new BaseAnalyticsModule_ProvideClockFactory(paramBaseAnalyticsModule);
  }
  
  public Clock get()
  {
    Clock localClock = this.module.provideClock();
    if (localClock == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return localClock;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/BaseAnalyticsModule_ProvideClockFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */