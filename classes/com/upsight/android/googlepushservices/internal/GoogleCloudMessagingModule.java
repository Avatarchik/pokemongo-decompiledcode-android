package com.upsight.android.googlepushservices.internal;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.upsight.android.UpsightContext;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
public final class GoogleCloudMessagingModule
{
  @Provides
  @Singleton
  public GoogleCloudMessaging provideGoogleCloudMessaging(UpsightContext paramUpsightContext)
  {
    return GoogleCloudMessaging.getInstance(paramUpsightContext);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/googlepushservices/internal/GoogleCloudMessagingModule.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */