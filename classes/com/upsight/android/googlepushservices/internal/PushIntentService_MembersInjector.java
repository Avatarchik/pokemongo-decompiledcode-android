package com.upsight.android.googlepushservices.internal;

import android.app.IntentService;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class PushIntentService_MembersInjector
  implements MembersInjector<PushIntentService>
{
  private final Provider<GoogleCloudMessaging> mGcmProvider;
  private final MembersInjector<IntentService> supertypeInjector;
  
  static
  {
    if (!PushIntentService_MembersInjector.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  public PushIntentService_MembersInjector(MembersInjector<IntentService> paramMembersInjector, Provider<GoogleCloudMessaging> paramProvider)
  {
    assert (paramMembersInjector != null);
    this.supertypeInjector = paramMembersInjector;
    assert (paramProvider != null);
    this.mGcmProvider = paramProvider;
  }
  
  public static MembersInjector<PushIntentService> create(MembersInjector<IntentService> paramMembersInjector, Provider<GoogleCloudMessaging> paramProvider)
  {
    return new PushIntentService_MembersInjector(paramMembersInjector, paramProvider);
  }
  
  public void injectMembers(PushIntentService paramPushIntentService)
  {
    if (paramPushIntentService == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    this.supertypeInjector.injectMembers(paramPushIntentService);
    paramPushIntentService.mGcm = ((GoogleCloudMessaging)this.mGcmProvider.get());
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/googlepushservices/internal/PushIntentService_MembersInjector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */