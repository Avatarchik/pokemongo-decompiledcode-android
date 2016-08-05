package com.upsight.android.analytics.internal;

import com.upsight.android.internal.util.Opt;
import dagger.internal.Factory;

public final class BaseAnalyticsModule_ProvideUncaughtExceptionHandlerFactory
  implements Factory<Opt<Thread.UncaughtExceptionHandler>>
{
  private final BaseAnalyticsModule module;
  
  static
  {
    if (!BaseAnalyticsModule_ProvideUncaughtExceptionHandlerFactory.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  public BaseAnalyticsModule_ProvideUncaughtExceptionHandlerFactory(BaseAnalyticsModule paramBaseAnalyticsModule)
  {
    assert (paramBaseAnalyticsModule != null);
    this.module = paramBaseAnalyticsModule;
  }
  
  public static Factory<Opt<Thread.UncaughtExceptionHandler>> create(BaseAnalyticsModule paramBaseAnalyticsModule)
  {
    return new BaseAnalyticsModule_ProvideUncaughtExceptionHandlerFactory(paramBaseAnalyticsModule);
  }
  
  public Opt<Thread.UncaughtExceptionHandler> get()
  {
    Opt localOpt = this.module.provideUncaughtExceptionHandler();
    if (localOpt == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return localOpt;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/BaseAnalyticsModule_ProvideUncaughtExceptionHandlerFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */