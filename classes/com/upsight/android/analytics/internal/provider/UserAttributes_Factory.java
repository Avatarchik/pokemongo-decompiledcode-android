package com.upsight.android.analytics.internal.provider;

import com.upsight.android.UpsightContext;
import dagger.MembersInjector;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class UserAttributes_Factory
  implements Factory<UserAttributes>
{
  private final MembersInjector<UserAttributes> membersInjector;
  private final Provider<UpsightContext> upsightProvider;
  
  static
  {
    if (!UserAttributes_Factory.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  public UserAttributes_Factory(MembersInjector<UserAttributes> paramMembersInjector, Provider<UpsightContext> paramProvider)
  {
    assert (paramMembersInjector != null);
    this.membersInjector = paramMembersInjector;
    assert (paramProvider != null);
    this.upsightProvider = paramProvider;
  }
  
  public static Factory<UserAttributes> create(MembersInjector<UserAttributes> paramMembersInjector, Provider<UpsightContext> paramProvider)
  {
    return new UserAttributes_Factory(paramMembersInjector, paramProvider);
  }
  
  public UserAttributes get()
  {
    UserAttributes localUserAttributes = new UserAttributes((UpsightContext)this.upsightProvider.get());
    this.membersInjector.injectMembers(localUserAttributes);
    return localUserAttributes;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/provider/UserAttributes_Factory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */