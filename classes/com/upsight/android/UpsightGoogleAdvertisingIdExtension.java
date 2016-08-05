package com.upsight.android;

import com.upsight.android.analytics.provider.UpsightDataProvider;
import com.upsight.android.googleadvertisingid.UpsightGoogleAdvertisingProviderComponent;
import com.upsight.android.googleadvertisingid.internal.DaggerGoogleAdvertisingProviderComponent;
import com.upsight.android.googleadvertisingid.internal.DaggerGoogleAdvertisingProviderComponent.Builder;
import com.upsight.android.googleadvertisingid.internal.GoogleAdvertisingProviderModule;
import com.upsight.android.googleadvertisingid.internal.GooglePlayAdvertisingProvider;
import javax.inject.Inject;

public final class UpsightGoogleAdvertisingIdExtension
  extends UpsightExtension<UpsightGoogleAdvertisingProviderComponent, Void>
{
  public static final String EXTENSION_NAME = "com.upsight.extension.googleadvertisingid";
  @Inject
  GooglePlayAdvertisingProvider mAdvertisingIdProvider;
  
  protected void onCreate(UpsightContext paramUpsightContext)
  {
    UpsightDataProvider.register(paramUpsightContext, this.mAdvertisingIdProvider);
  }
  
  protected UpsightGoogleAdvertisingProviderComponent onResolve(UpsightContext paramUpsightContext)
  {
    return DaggerGoogleAdvertisingProviderComponent.builder().googleAdvertisingProviderModule(new GoogleAdvertisingProviderModule(paramUpsightContext)).build();
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/UpsightGoogleAdvertisingIdExtension.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */