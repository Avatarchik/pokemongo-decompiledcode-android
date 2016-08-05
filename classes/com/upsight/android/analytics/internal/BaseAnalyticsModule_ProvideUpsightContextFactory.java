package com.upsight.android.analytics.internal;

import com.upsight.android.UpsightContext;
import dagger.internal.Factory;

public final class BaseAnalyticsModule_ProvideUpsightContextFactory
  implements Factory<UpsightContext>
{
  private final BaseAnalyticsModule module;
  
  static
  {
    if (!BaseAnalyticsModule_ProvideUpsightContextFactory.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  public BaseAnalyticsModule_ProvideUpsightContextFactory(BaseAnalyticsModule paramBaseAnalyticsModule)
  {
    assert (paramBaseAnalyticsModule != null);
    this.module = paramBaseAnalyticsModule;
  }
  
  public static Factory<UpsightContext> create(BaseAnalyticsModule paramBaseAnalyticsModule)
  {
    return new BaseAnalyticsModule_ProvideUpsightContextFactory(paramBaseAnalyticsModule);
  }
  
  public UpsightContext get()
  {
    UpsightContext localUpsightContext = this.module.provideUpsightContext();
    if (localUpsightContext == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return localUpsightContext;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/BaseAnalyticsModule_ProvideUpsightContextFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */