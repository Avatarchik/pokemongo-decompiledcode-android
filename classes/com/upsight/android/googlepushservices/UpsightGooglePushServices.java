package com.upsight.android.googlepushservices;

import com.upsight.android.UpsightContext;
import com.upsight.android.UpsightException;
import com.upsight.android.UpsightGooglePushServicesExtension;
import com.upsight.android.logger.UpsightLogger;

public abstract class UpsightGooglePushServices
{
  public static void register(UpsightContext paramUpsightContext, OnRegisterListener paramOnRegisterListener)
  {
    UpsightGooglePushServicesExtension localUpsightGooglePushServicesExtension = (UpsightGooglePushServicesExtension)paramUpsightContext.getUpsightExtension("com.upsight.extension.googlepushservices");
    if (localUpsightGooglePushServicesExtension != null) {
      localUpsightGooglePushServicesExtension.getApi().register(paramOnRegisterListener);
    }
    for (;;)
    {
      return;
      paramUpsightContext.getLogger().e("Upsight", "com.upsight.extension.googlepushservices must be registered in your Android Manifest", new Object[0]);
    }
  }
  
  public static void unregister(UpsightContext paramUpsightContext, OnUnregisterListener paramOnUnregisterListener)
  {
    UpsightGooglePushServicesExtension localUpsightGooglePushServicesExtension = (UpsightGooglePushServicesExtension)paramUpsightContext.getUpsightExtension("com.upsight.extension.googlepushservices");
    if (localUpsightGooglePushServicesExtension != null) {
      localUpsightGooglePushServicesExtension.getApi().unregister(paramOnUnregisterListener);
    }
    for (;;)
    {
      return;
      paramUpsightContext.getLogger().e("Upsight", "com.upsight.extension.googlepushservices must be registered in your Android Manifest", new Object[0]);
    }
  }
  
  public static abstract interface OnUnregisterListener
  {
    public abstract void onFailure(UpsightException paramUpsightException);
    
    public abstract void onSuccess();
  }
  
  public static abstract interface OnRegisterListener
  {
    public abstract void onFailure(UpsightException paramUpsightException);
    
    public abstract void onSuccess(String paramString);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/googlepushservices/UpsightGooglePushServices.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */