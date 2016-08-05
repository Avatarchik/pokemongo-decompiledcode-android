package com.google.android.gms.internal;

import com.google.android.gms.ads.formats.NativeAppInstallAd.OnAppInstallAdLoadedListener;

@zzgr
public class zzdb
  extends zzcw.zza
{
  private final NativeAppInstallAd.OnAppInstallAdLoadedListener zzxj;
  
  public zzdb(NativeAppInstallAd.OnAppInstallAdLoadedListener paramOnAppInstallAdLoadedListener)
  {
    this.zzxj = paramOnAppInstallAdLoadedListener;
  }
  
  public void zza(zzcq paramzzcq)
  {
    this.zzxj.onAppInstallAdLoaded(zzb(paramzzcq));
  }
  
  zzcr zzb(zzcq paramzzcq)
  {
    return new zzcr(paramzzcq);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzdb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */