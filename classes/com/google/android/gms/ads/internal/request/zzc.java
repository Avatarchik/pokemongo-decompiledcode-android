package com.google.android.gms.ads.internal.request;

import android.content.Context;
import com.google.android.gms.ads.internal.client.zzl;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.util.client.zza;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.internal.zzbu;
import com.google.android.gms.internal.zzby;
import com.google.android.gms.internal.zzgr;
import com.google.android.gms.internal.zzhz;

@zzgr
public final class zzc
{
  public static zzhz zza(Context paramContext, AdRequestInfoParcel paramAdRequestInfoParcel, zza paramzza)
  {
    zza(paramContext, paramAdRequestInfoParcel, paramzza, new zzb()
    {
      public boolean zzd(AdRequestInfoParcel paramAnonymousAdRequestInfoParcel)
      {
        if ((paramAnonymousAdRequestInfoParcel.zzqj.zzJx) || ((GooglePlayServicesUtil.zzag(zzc.this)) && (!((Boolean)zzby.zzuL.get()).booleanValue()))) {}
        for (boolean bool = true;; bool = false) {
          return bool;
        }
      }
    });
  }
  
  static zzhz zza(Context paramContext, AdRequestInfoParcel paramAdRequestInfoParcel, zza paramzza, zzb paramzzb)
  {
    if (paramzzb.zzd(paramAdRequestInfoParcel)) {}
    for (zzhz localzzhz = zzb(paramContext, paramAdRequestInfoParcel, paramzza);; localzzhz = zzc(paramContext, paramAdRequestInfoParcel, paramzza)) {
      return localzzhz;
    }
  }
  
  private static zzhz zzb(Context paramContext, AdRequestInfoParcel paramAdRequestInfoParcel, zza paramzza)
  {
    zzb.zzaF("Fetching ad response from local ad request service.");
    zzd.zza localzza = new zzd.zza(paramContext, paramAdRequestInfoParcel, paramzza);
    localzza.zzgz();
    return localzza;
  }
  
  private static zzhz zzc(Context paramContext, AdRequestInfoParcel paramAdRequestInfoParcel, zza paramzza)
  {
    zzb.zzaF("Fetching ad response from remote ad request service.");
    if (!zzl.zzcF().zzR(paramContext)) {
      zzb.zzaH("Failed to connect to remote ad request service.");
    }
    for (Object localObject = null;; localObject = new zzd.zzb(paramContext, paramAdRequestInfoParcel, paramzza)) {
      return (zzhz)localObject;
    }
  }
  
  static abstract interface zzb
  {
    public abstract boolean zzd(AdRequestInfoParcel paramAdRequestInfoParcel);
  }
  
  public static abstract interface zza
  {
    public abstract void zzb(AdResponseParcel paramAdResponseParcel);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/ads/internal/request/zzc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */