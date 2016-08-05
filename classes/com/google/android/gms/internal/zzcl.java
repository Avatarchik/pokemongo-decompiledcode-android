package com.google.android.gms.internal;

import com.google.android.gms.ads.doubleclick.OnCustomRenderedAdLoadedListener;

@zzgr
public final class zzcl
  extends zzck.zza
{
  private final OnCustomRenderedAdLoadedListener zztK;
  
  public zzcl(OnCustomRenderedAdLoadedListener paramOnCustomRenderedAdLoadedListener)
  {
    this.zztK = paramOnCustomRenderedAdLoadedListener;
  }
  
  public void zza(zzcj paramzzcj)
  {
    this.zztK.onCustomRenderedAdLoaded(new zzci(paramzzcj));
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzcl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */