package com.google.android.gms.ads.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.client.zzw.zza;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.internal.zzgr;

@zzgr
public class zzm
  extends zzw.zza
{
  private static final Object zzpy = new Object();
  private static zzm zzpz;
  private final Context mContext;
  private boolean zzpA;
  
  zzm(Context paramContext)
  {
    this.mContext = paramContext;
    this.zzpA = false;
  }
  
  public static zzm zzq(Context paramContext)
  {
    synchronized (zzpy)
    {
      if (zzpz == null) {
        zzpz = new zzm(paramContext.getApplicationContext());
      }
      zzm localzzm = zzpz;
      return localzzm;
    }
  }
  
  public void zza()
  {
    synchronized (zzpy)
    {
      if (this.zzpA) {
        zzb.zzaH("Mobile ads is initialized already.");
      } else {
        this.zzpA = true;
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/ads/internal/zzm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */