package com.google.android.gms.ads.internal.client;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.util.client.zza;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzf;
import com.google.android.gms.ads.internal.zzk;
import com.google.android.gms.dynamic.zzg;
import com.google.android.gms.dynamic.zzg.zza;
import com.google.android.gms.internal.zzel;
import com.google.android.gms.internal.zzgr;

@zzgr
public class zze
  extends zzg<zzt>
{
  public zze()
  {
    super("com.google.android.gms.ads.AdManagerCreatorImpl");
  }
  
  private zzs zza(Context paramContext, AdSizeParcel paramAdSizeParcel, String paramString, zzel paramzzel, int paramInt)
  {
    try
    {
      com.google.android.gms.dynamic.zzd localzzd = com.google.android.gms.dynamic.zze.zzy(paramContext);
      zzs localzzs2 = zzs.zza.zzk(((zzt)zzas(paramContext)).zza(localzzd, paramAdSizeParcel, paramString, paramzzel, 8115000, paramInt));
      localzzs1 = localzzs2;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        zzb.zza("Could not create remote AdManager.", localRemoteException);
        zzs localzzs1 = null;
      }
    }
    catch (zzg.zza localzza)
    {
      for (;;) {}
    }
    return localzzs1;
  }
  
  public zzs zza(Context paramContext, AdSizeParcel paramAdSizeParcel, String paramString, zzel paramzzel)
  {
    Object localObject;
    if (zzl.zzcF().zzR(paramContext))
    {
      localObject = zza(paramContext, paramAdSizeParcel, paramString, paramzzel, 1);
      if (localObject != null) {}
    }
    else
    {
      zzb.zzaF("Using BannerAdManager from the client jar.");
      localObject = new zzf(paramContext, paramAdSizeParcel, paramString, paramzzel, new VersionInfoParcel(8115000, 8115000, true), com.google.android.gms.ads.internal.zzd.zzbd());
    }
    return (zzs)localObject;
  }
  
  public zzs zzb(Context paramContext, AdSizeParcel paramAdSizeParcel, String paramString, zzel paramzzel)
  {
    Object localObject;
    if (zzl.zzcF().zzR(paramContext))
    {
      localObject = zza(paramContext, paramAdSizeParcel, paramString, paramzzel, 2);
      if (localObject != null) {}
    }
    else
    {
      zzb.zzaH("Using InterstitialAdManager from the client jar.");
      localObject = new zzk(paramContext, paramAdSizeParcel, paramString, paramzzel, new VersionInfoParcel(8115000, 8115000, true), com.google.android.gms.ads.internal.zzd.zzbd());
    }
    return (zzs)localObject;
  }
  
  protected zzt zze(IBinder paramIBinder)
  {
    return zzt.zza.zzl(paramIBinder);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/ads/internal/client/zze.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */