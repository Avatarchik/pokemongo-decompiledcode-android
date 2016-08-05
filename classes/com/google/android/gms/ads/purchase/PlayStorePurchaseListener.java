package com.google.android.gms.ads.purchase;

public abstract interface PlayStorePurchaseListener
{
  public abstract boolean isValidPurchase(String paramString);
  
  public abstract void onInAppPurchaseFinished(InAppPurchaseResult paramInAppPurchaseResult);
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/ads/purchase/PlayStorePurchaseListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */