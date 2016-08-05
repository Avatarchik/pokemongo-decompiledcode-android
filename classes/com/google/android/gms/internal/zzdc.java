package com.google.android.gms.internal;

import com.google.android.gms.ads.formats.NativeContentAd.OnContentAdLoadedListener;

@zzgr
public class zzdc
  extends zzcx.zza
{
  private final NativeContentAd.OnContentAdLoadedListener zzxk;
  
  public zzdc(NativeContentAd.OnContentAdLoadedListener paramOnContentAdLoadedListener)
  {
    this.zzxk = paramOnContentAdLoadedListener;
  }
  
  public void zza(zzcs paramzzcs)
  {
    this.zzxk.onContentAdLoaded(zzb(paramzzcs));
  }
  
  zzct zzb(zzcs paramzzcs)
  {
    return new zzct(paramzzcs);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzdc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */