package com.upsight.android.analytics.internal.provider;

import com.upsight.android.analytics.provider.UpsightLocationTracker;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class ProviderModule_ProvidesUpsightLocationTrackerFactory
  implements Factory<UpsightLocationTracker>
{
  private final Provider<LocationTracker> locationTrackerProvider;
  private final ProviderModule module;
  
  static
  {
    if (!ProviderModule_ProvidesUpsightLocationTrackerFactory.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  public ProviderModule_ProvidesUpsightLocationTrackerFactory(ProviderModule paramProviderModule, Provider<LocationTracker> paramProvider)
  {
    assert (paramProviderModule != null);
    this.module = paramProviderModule;
    assert (paramProvider != null);
    this.locationTrackerProvider = paramProvider;
  }
  
  public static Factory<UpsightLocationTracker> create(ProviderModule paramProviderModule, Provider<LocationTracker> paramProvider)
  {
    return new ProviderModule_ProvidesUpsightLocationTrackerFactory(paramProviderModule, paramProvider);
  }
  
  public UpsightLocationTracker get()
  {
    UpsightLocationTracker localUpsightLocationTracker = this.module.providesUpsightLocationTracker((LocationTracker)this.locationTrackerProvider.get());
    if (localUpsightLocationTracker == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return localUpsightLocationTracker;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/provider/ProviderModule_ProvidesUpsightLocationTrackerFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */