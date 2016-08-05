package com.upsight.android.analytics.internal.session;

import com.upsight.android.UpsightContext;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class ManualTracker_Factory
  implements Factory<ManualTracker>
{
  private final Provider<SessionManager> sessionManagerProvider;
  private final Provider<UpsightContext> upsightProvider;
  
  static
  {
    if (!ManualTracker_Factory.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  public ManualTracker_Factory(Provider<SessionManager> paramProvider, Provider<UpsightContext> paramProvider1)
  {
    assert (paramProvider != null);
    this.sessionManagerProvider = paramProvider;
    assert (paramProvider1 != null);
    this.upsightProvider = paramProvider1;
  }
  
  public static Factory<ManualTracker> create(Provider<SessionManager> paramProvider, Provider<UpsightContext> paramProvider1)
  {
    return new ManualTracker_Factory(paramProvider, paramProvider1);
  }
  
  public ManualTracker get()
  {
    return new ManualTracker((SessionManager)this.sessionManagerProvider.get(), (UpsightContext)this.upsightProvider.get());
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/session/ManualTracker_Factory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */