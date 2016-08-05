package com.upsight.android.googlepushservices.internal;

import com.upsight.android.googlepushservices.UpsightGooglePushServicesComponent;
import dagger.Component;
import javax.inject.Singleton;

@Component(modules={GooglePushServicesModule.class})
@Singleton
public abstract interface GooglePushServicesComponent
  extends UpsightGooglePushServicesComponent
{}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/googlepushservices/internal/GooglePushServicesComponent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */