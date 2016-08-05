package com.upsight.android.analytics.internal;

import com.upsight.android.UpsightContext;
import com.upsight.android.analytics.UpsightGooglePlayHelper;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class BaseAnalyticsModule_ProvideGooglePlayHelperFactory
  implements Factory<UpsightGooglePlayHelper>
{
  private final BaseAnalyticsModule module;
  private final Provider<UpsightContext> upsightProvider;
  
  static
  {
    if (!BaseAnalyticsModule_ProvideGooglePlayHelperFactory.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  public BaseAnalyticsModule_ProvideGooglePlayHelperFactory(BaseAnalyticsModule paramBaseAnalyticsModule, Provider<UpsightContext> paramProvider)
  {
    assert (paramBaseAnalyticsModule != null);
    this.module = paramBaseAnalyticsModule;
    assert (paramProvider != null);
    this.upsightProvider = paramProvider;
  }
  
  public static Factory<UpsightGooglePlayHelper> create(BaseAnalyticsModule paramBaseAnalyticsModule, Provider<UpsightContext> paramProvider)
  {
    return new BaseAnalyticsModule_ProvideGooglePlayHelperFactory(paramBaseAnalyticsModule, paramProvider);
  }
  
  public UpsightGooglePlayHelper get()
  {
    UpsightGooglePlayHelper localUpsightGooglePlayHelper = this.module.provideGooglePlayHelper((UpsightContext)this.upsightProvider.get());
    if (localUpsightGooglePlayHelper == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return localUpsightGooglePlayHelper;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/BaseAnalyticsModule_ProvideGooglePlayHelperFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */