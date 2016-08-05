package com.upsight.android.analytics.internal.session;

import com.upsight.android.analytics.UpsightLifeCycleTracker;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class LifecycleTrackerModule_ProvideManualTrackerFactory
  implements Factory<UpsightLifeCycleTracker>
{
  private final LifecycleTrackerModule module;
  private final Provider<ManualTracker> trackerProvider;
  
  static
  {
    if (!LifecycleTrackerModule_ProvideManualTrackerFactory.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  public LifecycleTrackerModule_ProvideManualTrackerFactory(LifecycleTrackerModule paramLifecycleTrackerModule, Provider<ManualTracker> paramProvider)
  {
    assert (paramLifecycleTrackerModule != null);
    this.module = paramLifecycleTrackerModule;
    assert (paramProvider != null);
    this.trackerProvider = paramProvider;
  }
  
  public static Factory<UpsightLifeCycleTracker> create(LifecycleTrackerModule paramLifecycleTrackerModule, Provider<ManualTracker> paramProvider)
  {
    return new LifecycleTrackerModule_ProvideManualTrackerFactory(paramLifecycleTrackerModule, paramProvider);
  }
  
  public UpsightLifeCycleTracker get()
  {
    UpsightLifeCycleTracker localUpsightLifeCycleTracker = this.module.provideManualTracker((ManualTracker)this.trackerProvider.get());
    if (localUpsightLifeCycleTracker == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return localUpsightLifeCycleTracker;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/session/LifecycleTrackerModule_ProvideManualTrackerFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */