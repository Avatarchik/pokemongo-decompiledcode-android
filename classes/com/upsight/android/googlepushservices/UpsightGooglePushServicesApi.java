package com.upsight.android.googlepushservices;

import com.upsight.android.UpsightContext;
import com.upsight.android.marketing.UpsightBillboard;
import com.upsight.android.marketing.UpsightBillboard.Handler;

public abstract interface UpsightGooglePushServicesApi
{
  public abstract UpsightBillboard createPushBillboard(UpsightContext paramUpsightContext, UpsightBillboard.Handler paramHandler)
    throws IllegalArgumentException, IllegalStateException;
  
  public abstract void register(UpsightGooglePushServices.OnRegisterListener paramOnRegisterListener);
  
  public abstract void unregister(UpsightGooglePushServices.OnUnregisterListener paramOnUnregisterListener);
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/googlepushservices/UpsightGooglePushServicesApi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */