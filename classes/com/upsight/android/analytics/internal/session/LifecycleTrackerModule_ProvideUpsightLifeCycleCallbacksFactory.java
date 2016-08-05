package com.upsight.android.analytics.internal.session;

import android.app.Application.ActivityLifecycleCallbacks;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class LifecycleTrackerModule_ProvideUpsightLifeCycleCallbacksFactory
  implements Factory<Application.ActivityLifecycleCallbacks>
{
  private final Provider<ActivityLifecycleTracker> handlerProvider;
  private final LifecycleTrackerModule module;
  
  static
  {
    if (!LifecycleTrackerModule_ProvideUpsightLifeCycleCallbacksFactory.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  public LifecycleTrackerModule_ProvideUpsightLifeCycleCallbacksFactory(LifecycleTrackerModule paramLifecycleTrackerModule, Provider<ActivityLifecycleTracker> paramProvider)
  {
    assert (paramLifecycleTrackerModule != null);
    this.module = paramLifecycleTrackerModule;
    assert (paramProvider != null);
    this.handlerProvider = paramProvider;
  }
  
  public static Factory<Application.ActivityLifecycleCallbacks> create(LifecycleTrackerModule paramLifecycleTrackerModule, Provider<ActivityLifecycleTracker> paramProvider)
  {
    return new LifecycleTrackerModule_ProvideUpsightLifeCycleCallbacksFactory(paramLifecycleTrackerModule, paramProvider);
  }
  
  public Application.ActivityLifecycleCallbacks get()
  {
    Application.ActivityLifecycleCallbacks localActivityLifecycleCallbacks = this.module.provideUpsightLifeCycleCallbacks((ActivityLifecycleTracker)this.handlerProvider.get());
    if (localActivityLifecycleCallbacks == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return localActivityLifecycleCallbacks;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/session/LifecycleTrackerModule_ProvideUpsightLifeCycleCallbacksFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */