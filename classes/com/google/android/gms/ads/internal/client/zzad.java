package com.google.android.gms.ads.internal.client;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.util.client.zza;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzm;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.dynamic.zzg;
import com.google.android.gms.dynamic.zzg.zza;
import com.google.android.gms.internal.zzgr;

@zzgr
public class zzad
  extends zzg<zzx>
{
  public zzad()
  {
    super("com.google.android.gms.ads.MobileAdsSettingManagerCreatorImpl");
  }
  
  private zzw zzu(Context paramContext)
  {
    try
    {
      zzd localzzd = zze.zzy(paramContext);
      zzw localzzw2 = zzw.zza.zzo(((zzx)zzas(paramContext)).zza(localzzd, 8115000));
      localzzw1 = localzzw2;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        zzb.zzd("Could not get remote MobileAdsSettingManager.", localRemoteException);
        localzzw1 = null;
      }
    }
    catch (zzg.zza localzza)
    {
      for (;;)
      {
        zzb.zzd("Could not get remote MobileAdsSettingManager.", localzza);
        zzw localzzw1 = null;
      }
    }
    return localzzw1;
  }
  
  protected zzx zzq(IBinder paramIBinder)
  {
    return zzx.zza.zzp(paramIBinder);
  }
  
  public zzw zzt(Context paramContext)
  {
    Object localObject;
    if (zzl.zzcF().zzR(paramContext))
    {
      localObject = zzu(paramContext);
      if (localObject != null) {}
    }
    else
    {
      zzb.zzaF("Using MobileAdsSettingManager from the client jar.");
      new VersionInfoParcel(8115000, 8115000, true);
      localObject = zzm.zzq(paramContext);
    }
    return (zzw)localObject;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/ads/internal/client/zzad.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */