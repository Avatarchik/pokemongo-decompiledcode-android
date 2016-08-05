package com.google.android.gms.ads.internal.reward.client;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.client.zzl;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.util.client.zza;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.dynamic.zzg;
import com.google.android.gms.dynamic.zzg.zza;
import com.google.android.gms.internal.zzel;
import com.google.android.gms.internal.zzgr;
import com.google.android.gms.internal.zzhf;

@zzgr
public class zzf
  extends zzg<zzc>
{
  public zzf()
  {
    super("com.google.android.gms.ads.reward.RewardedVideoAdCreatorImpl");
  }
  
  private zzb zzb(Context paramContext, zzel paramzzel)
  {
    try
    {
      zzd localzzd = zze.zzy(paramContext);
      zzb localzzb2 = zzb.zza.zzaa(((zzc)zzas(paramContext)).zza(localzzd, paramzzel, 8115000));
      localzzb1 = localzzb2;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not get remote RewardedVideoAd.", localRemoteException);
        zzb localzzb1 = null;
      }
    }
    catch (zzg.zza localzza)
    {
      for (;;) {}
    }
    return localzzb1;
  }
  
  public zzb zza(Context paramContext, zzel paramzzel)
  {
    Object localObject;
    if (zzl.zzcF().zzR(paramContext))
    {
      localObject = zzb(paramContext, paramzzel);
      if (localObject != null) {}
    }
    else
    {
      com.google.android.gms.ads.internal.util.client.zzb.zzaF("Using RewardedVideoAd from the client jar.");
      localObject = new zzhf(paramContext, paramzzel, new VersionInfoParcel(8115000, 8115000, true));
    }
    return (zzb)localObject;
  }
  
  protected zzc zzad(IBinder paramIBinder)
  {
    return zzc.zza.zzab(paramIBinder);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/ads/internal/reward/client/zzf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */