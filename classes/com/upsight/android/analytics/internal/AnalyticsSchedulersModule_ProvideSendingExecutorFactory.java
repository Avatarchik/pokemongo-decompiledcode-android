package com.upsight.android.analytics.internal;

import dagger.internal.Factory;
import rx.Scheduler;

public final class AnalyticsSchedulersModule_ProvideSendingExecutorFactory
  implements Factory<Scheduler>
{
  private final AnalyticsSchedulersModule module;
  
  static
  {
    if (!AnalyticsSchedulersModule_ProvideSendingExecutorFactory.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  public AnalyticsSchedulersModule_ProvideSendingExecutorFactory(AnalyticsSchedulersModule paramAnalyticsSchedulersModule)
  {
    assert (paramAnalyticsSchedulersModule != null);
    this.module = paramAnalyticsSchedulersModule;
  }
  
  public static Factory<Scheduler> create(AnalyticsSchedulersModule paramAnalyticsSchedulersModule)
  {
    return new AnalyticsSchedulersModule_ProvideSendingExecutorFactory(paramAnalyticsSchedulersModule);
  }
  
  public Scheduler get()
  {
    Scheduler localScheduler = this.module.provideSendingExecutor();
    if (localScheduler == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return localScheduler;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/AnalyticsSchedulersModule_ProvideSendingExecutorFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */