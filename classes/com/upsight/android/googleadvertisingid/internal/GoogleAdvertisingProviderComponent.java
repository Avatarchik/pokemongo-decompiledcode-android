package com.upsight.android.googleadvertisingid.internal;

import com.upsight.android.googleadvertisingid.UpsightGoogleAdvertisingProviderComponent;
import dagger.Component;
import javax.inject.Singleton;

@Component(modules={GoogleAdvertisingProviderModule.class})
@Singleton
public abstract interface GoogleAdvertisingProviderComponent
  extends UpsightGoogleAdvertisingProviderComponent
{}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/googleadvertisingid/internal/GoogleAdvertisingProviderComponent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */