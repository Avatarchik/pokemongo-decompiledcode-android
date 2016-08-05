package com.upsight.android.analytics.internal;

import com.upsight.android.analytics.UpsightAnalyticsApi;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class AnalyticsApiModule_ProvideUpsightAnalyticsApiFactory
  implements Factory<UpsightAnalyticsApi>
{
  private final Provider<Analytics> analyticsProvider;
  private final AnalyticsApiModule module;
  
  static
  {
    if (!AnalyticsApiModule_ProvideUpsightAnalyticsApiFactory.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  public AnalyticsApiModule_ProvideUpsightAnalyticsApiFactory(AnalyticsApiModule paramAnalyticsApiModule, Provider<Analytics> paramProvider)
  {
    assert (paramAnalyticsApiModule != null);
    this.module = paramAnalyticsApiModule;
    assert (paramProvider != null);
    this.analyticsProvider = paramProvider;
  }
  
  public static Factory<UpsightAnalyticsApi> create(AnalyticsApiModule paramAnalyticsApiModule, Provider<Analytics> paramProvider)
  {
    return new AnalyticsApiModule_ProvideUpsightAnalyticsApiFactory(paramAnalyticsApiModule, paramProvider);
  }
  
  public UpsightAnalyticsApi get()
  {
    UpsightAnalyticsApi localUpsightAnalyticsApi = this.module.provideUpsightAnalyticsApi((Analytics)this.analyticsProvider.get());
    if (localUpsightAnalyticsApi == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return localUpsightAnalyticsApi;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/AnalyticsApiModule_ProvideUpsightAnalyticsApiFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */