package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.dynamic.zzg;
import com.google.android.gms.dynamic.zzg.zza;

@zzgr
public final class zzfj
  extends zzg<zzfl>
{
  private static final zzfj zzCp = new zzfj();
  
  private zzfj()
  {
    super("com.google.android.gms.ads.AdOverlayCreatorImpl");
  }
  
  public static zzfk zzb(Activity paramActivity)
  {
    Object localObject;
    try
    {
      if (zzc(paramActivity))
      {
        zzb.zzaF("Using AdOverlay from the client jar.");
        localObject = new com.google.android.gms.ads.internal.overlay.zzd(paramActivity);
      }
      else
      {
        zzfk localzzfk = zzCp.zzd(paramActivity);
        localObject = localzzfk;
      }
    }
    catch (zza localzza)
    {
      zzb.zzaH(localzza.getMessage());
      localObject = null;
    }
    return (zzfk)localObject;
  }
  
  private static boolean zzc(Activity paramActivity)
    throws zzfj.zza
  {
    Intent localIntent = paramActivity.getIntent();
    if (!localIntent.hasExtra("com.google.android.gms.ads.internal.overlay.useClientJar")) {
      throw new zza("Ad overlay requires the useClientJar flag in intent extras.");
    }
    return localIntent.getBooleanExtra("com.google.android.gms.ads.internal.overlay.useClientJar", false);
  }
  
  private zzfk zzd(Activity paramActivity)
  {
    try
    {
      com.google.android.gms.dynamic.zzd localzzd = zze.zzy(paramActivity);
      zzfk localzzfk2 = zzfk.zza.zzL(((zzfl)zzas(paramActivity)).zze(localzzd));
      localzzfk1 = localzzfk2;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        zzb.zzd("Could not create remote AdOverlay.", localRemoteException);
        localzzfk1 = null;
      }
    }
    catch (zzg.zza localzza)
    {
      for (;;)
      {
        zzb.zzd("Could not create remote AdOverlay.", localzza);
        zzfk localzzfk1 = null;
      }
    }
    return localzzfk1;
  }
  
  protected zzfl zzK(IBinder paramIBinder)
  {
    return zzfl.zza.zzM(paramIBinder);
  }
  
  private static final class zza
    extends Exception
  {
    public zza(String paramString)
    {
      super();
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzfj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */