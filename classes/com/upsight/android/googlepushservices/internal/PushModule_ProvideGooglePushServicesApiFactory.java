package com.upsight.android.googlepushservices.internal;

import com.upsight.android.googlepushservices.UpsightGooglePushServicesApi;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class PushModule_ProvideGooglePushServicesApiFactory
  implements Factory<UpsightGooglePushServicesApi>
{
  private final Provider<GooglePushServices> googlePushServicesProvider;
  private final PushModule module;
  
  static
  {
    if (!PushModule_ProvideGooglePushServicesApiFactory.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  public PushModule_ProvideGooglePushServicesApiFactory(PushModule paramPushModule, Provider<GooglePushServices> paramProvider)
  {
    assert (paramPushModule != null);
    this.module = paramPushModule;
    assert (paramProvider != null);
    this.googlePushServicesProvider = paramProvider;
  }
  
  public static Factory<UpsightGooglePushServicesApi> create(PushModule paramPushModule, Provider<GooglePushServices> paramProvider)
  {
    return new PushModule_ProvideGooglePushServicesApiFactory(paramPushModule, paramProvider);
  }
  
  public UpsightGooglePushServicesApi get()
  {
    UpsightGooglePushServicesApi localUpsightGooglePushServicesApi = this.module.provideGooglePushServicesApi((GooglePushServices)this.googlePushServicesProvider.get());
    if (localUpsightGooglePushServicesApi == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return localUpsightGooglePushServicesApi;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/googlepushservices/internal/PushModule_ProvideGooglePushServicesApiFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */