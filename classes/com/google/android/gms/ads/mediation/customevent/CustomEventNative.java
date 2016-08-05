package com.google.android.gms.ads.mediation.customevent;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.ads.mediation.NativeMediationAdRequest;

public abstract interface CustomEventNative
  extends CustomEvent
{
  public abstract void requestNativeAd(Context paramContext, CustomEventNativeListener paramCustomEventNativeListener, String paramString, NativeMediationAdRequest paramNativeMediationAdRequest, Bundle paramBundle);
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/ads/mediation/customevent/CustomEventNative.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */