package com.google.android.gms.ads.internal.client;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.util.client.zza;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzj;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.dynamic.zzg;
import com.google.android.gms.dynamic.zzg.zza;
import com.google.android.gms.internal.zzel;
import com.google.android.gms.internal.zzgr;

@zzgr
public final class zzd
  extends zzg<zzr>
{
  private static final zzd zzsA = new zzd();
  
  private zzd()
  {
    super("com.google.android.gms.ads.AdLoaderBuilderCreatorImpl");
  }
  
  public static zzq zza(Context paramContext, String paramString, zzel paramzzel)
  {
    Object localObject;
    if (zzl.zzcF().zzR(paramContext))
    {
      localObject = zzsA.zzb(paramContext, paramString, paramzzel);
      if (localObject != null) {}
    }
    else
    {
      zzb.zzaF("Using AdLoader from the client jar.");
      localObject = new zzj(paramContext, paramString, paramzzel, new VersionInfoParcel(8115000, 8115000, true));
    }
    return (zzq)localObject;
  }
  
  private zzq zzb(Context paramContext, String paramString, zzel paramzzel)
  {
    try
    {
      com.google.android.gms.dynamic.zzd localzzd = zze.zzy(paramContext);
      zzq localzzq2 = zzq.zza.zzi(((zzr)zzas(paramContext)).zza(localzzd, paramString, paramzzel, 8115000));
      localzzq1 = localzzq2;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        zzb.zzd("Could not create remote builder for AdLoader.", localRemoteException);
        zzq localzzq1 = null;
      }
    }
    catch (zzg.zza localzza)
    {
      for (;;)
      {
        zzb.zzd("Could not create remote builder for AdLoader.", localzza);
      }
    }
    return localzzq1;
  }
  
  protected zzr zzc(IBinder paramIBinder)
  {
    return zzr.zza.zzj(paramIBinder);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/ads/internal/client/zzd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */