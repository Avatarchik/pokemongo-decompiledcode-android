package com.upsight.android.analytics.internal;

import com.upsight.android.UpsightContext;
import dagger.MembersInjector;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class AnalyticsContext_Factory
  implements Factory<AnalyticsContext>
{
  private final MembersInjector<AnalyticsContext> membersInjector;
  private final Provider<UpsightContext> upsightProvider;
  
  static
  {
    if (!AnalyticsContext_Factory.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  public AnalyticsContext_Factory(MembersInjector<AnalyticsContext> paramMembersInjector, Provider<UpsightContext> paramProvider)
  {
    assert (paramMembersInjector != null);
    this.membersInjector = paramMembersInjector;
    assert (paramProvider != null);
    this.upsightProvider = paramProvider;
  }
  
  public static Factory<AnalyticsContext> create(MembersInjector<AnalyticsContext> paramMembersInjector, Provider<UpsightContext> paramProvider)
  {
    return new AnalyticsContext_Factory(paramMembersInjector, paramProvider);
  }
  
  public AnalyticsContext get()
  {
    AnalyticsContext localAnalyticsContext = new AnalyticsContext((UpsightContext)this.upsightProvider.get());
    this.membersInjector.injectMembers(localAnalyticsContext);
    return localAnalyticsContext;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/AnalyticsContext_Factory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */