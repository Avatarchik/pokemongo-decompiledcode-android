package com.google.android.gms.internal;

import com.google.android.gms.ads.purchase.InAppPurchaseListener;

@zzgr
public final class zzfx
  extends zzfs.zza
{
  private final InAppPurchaseListener zztI;
  
  public zzfx(InAppPurchaseListener paramInAppPurchaseListener)
  {
    this.zztI = paramInAppPurchaseListener;
  }
  
  public void zza(zzfr paramzzfr)
  {
    this.zztI.onInAppPurchaseRequested(new zzga(paramzzfr));
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzfx.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */