package com.upsight.android.analytics.internal.provider;

import com.upsight.android.UpsightContext;
import dagger.MembersInjector;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class LocationTracker_Factory
  implements Factory<LocationTracker>
{
  private final MembersInjector<LocationTracker> membersInjector;
  private final Provider<UpsightContext> upsightProvider;
  
  static
  {
    if (!LocationTracker_Factory.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  public LocationTracker_Factory(MembersInjector<LocationTracker> paramMembersInjector, Provider<UpsightContext> paramProvider)
  {
    assert (paramMembersInjector != null);
    this.membersInjector = paramMembersInjector;
    assert (paramProvider != null);
    this.upsightProvider = paramProvider;
  }
  
  public static Factory<LocationTracker> create(MembersInjector<LocationTracker> paramMembersInjector, Provider<UpsightContext> paramProvider)
  {
    return new LocationTracker_Factory(paramMembersInjector, paramProvider);
  }
  
  public LocationTracker get()
  {
    LocationTracker localLocationTracker = new LocationTracker((UpsightContext)this.upsightProvider.get());
    this.membersInjector.injectMembers(localLocationTracker);
    return localLocationTracker;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/provider/LocationTracker_Factory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */