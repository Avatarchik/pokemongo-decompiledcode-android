package com.upsight.android;

import com.upsight.android.googlepushservices.UpsightGooglePushServicesApi;
import com.upsight.android.googlepushservices.UpsightGooglePushServicesComponent;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class UpsightGooglePushServicesExtension_MembersInjector
  implements MembersInjector<UpsightGooglePushServicesExtension>
{
  private final Provider<UpsightGooglePushServicesApi> mUpsightPushProvider;
  private final MembersInjector<UpsightExtension<UpsightGooglePushServicesComponent, UpsightGooglePushServicesApi>> supertypeInjector;
  
  static
  {
    if (!UpsightGooglePushServicesExtension_MembersInjector.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  public UpsightGooglePushServicesExtension_MembersInjector(MembersInjector<UpsightExtension<UpsightGooglePushServicesComponent, UpsightGooglePushServicesApi>> paramMembersInjector, Provider<UpsightGooglePushServicesApi> paramProvider)
  {
    assert (paramMembersInjector != null);
    this.supertypeInjector = paramMembersInjector;
    assert (paramProvider != null);
    this.mUpsightPushProvider = paramProvider;
  }
  
  public static MembersInjector<UpsightGooglePushServicesExtension> create(MembersInjector<UpsightExtension<UpsightGooglePushServicesComponent, UpsightGooglePushServicesApi>> paramMembersInjector, Provider<UpsightGooglePushServicesApi> paramProvider)
  {
    return new UpsightGooglePushServicesExtension_MembersInjector(paramMembersInjector, paramProvider);
  }
  
  public void injectMembers(UpsightGooglePushServicesExtension paramUpsightGooglePushServicesExtension)
  {
    if (paramUpsightGooglePushServicesExtension == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    this.supertypeInjector.injectMembers(paramUpsightGooglePushServicesExtension);
    paramUpsightGooglePushServicesExtension.mUpsightPush = ((UpsightGooglePushServicesApi)this.mUpsightPushProvider.get());
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/UpsightGooglePushServicesExtension_MembersInjector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */