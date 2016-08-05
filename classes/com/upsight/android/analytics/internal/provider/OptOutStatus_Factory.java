package com.upsight.android.analytics.internal.provider;

import com.upsight.android.UpsightContext;
import dagger.MembersInjector;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class OptOutStatus_Factory
  implements Factory<OptOutStatus>
{
  private final MembersInjector<OptOutStatus> membersInjector;
  private final Provider<UpsightContext> upsightProvider;
  
  static
  {
    if (!OptOutStatus_Factory.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  public OptOutStatus_Factory(MembersInjector<OptOutStatus> paramMembersInjector, Provider<UpsightContext> paramProvider)
  {
    assert (paramMembersInjector != null);
    this.membersInjector = paramMembersInjector;
    assert (paramProvider != null);
    this.upsightProvider = paramProvider;
  }
  
  public static Factory<OptOutStatus> create(MembersInjector<OptOutStatus> paramMembersInjector, Provider<UpsightContext> paramProvider)
  {
    return new OptOutStatus_Factory(paramMembersInjector, paramProvider);
  }
  
  public OptOutStatus get()
  {
    OptOutStatus localOptOutStatus = new OptOutStatus((UpsightContext)this.upsightProvider.get());
    this.membersInjector.injectMembers(localOptOutStatus);
    return localOptOutStatus;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/provider/OptOutStatus_Factory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */