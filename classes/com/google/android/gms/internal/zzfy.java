package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zzg;
import com.google.android.gms.dynamic.zzg.zza;

@zzgr
public final class zzfy
  extends zzg<zzfu>
{
  private static final zzfy zzDb = new zzfy();
  
  private zzfy()
  {
    super("com.google.android.gms.ads.InAppPurchaseManagerCreatorImpl");
  }
  
  private static boolean zzc(Activity paramActivity)
    throws zzfy.zza
  {
    Intent localIntent = paramActivity.getIntent();
    if (!localIntent.hasExtra("com.google.android.gms.ads.internal.purchase.useClientJar")) {
      throw new zza("InAppPurchaseManager requires the useClientJar flag in intent extras.");
    }
    return localIntent.getBooleanExtra("com.google.android.gms.ads.internal.purchase.useClientJar", false);
  }
  
  public static zzft zze(Activity paramActivity)
  {
    Object localObject;
    try
    {
      if (zzc(paramActivity))
      {
        zzb.zzaF("Using AdOverlay from the client jar.");
        localObject = new com.google.android.gms.ads.internal.purchase.zze(paramActivity);
      }
      else
      {
        zzft localzzft = zzDb.zzf(paramActivity);
        localObject = localzzft;
      }
    }
    catch (zza localzza)
    {
      zzb.zzaH(localzza.getMessage());
      localObject = null;
    }
    return (zzft)localObject;
  }
  
  private zzft zzf(Activity paramActivity)
  {
    try
    {
      zzd localzzd = com.google.android.gms.dynamic.zze.zzy(paramActivity);
      zzft localzzft2 = zzft.zza.zzQ(((zzfu)zzas(paramActivity)).zzf(localzzd));
      localzzft1 = localzzft2;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        zzb.zzd("Could not create remote InAppPurchaseManager.", localRemoteException);
        localzzft1 = null;
      }
    }
    catch (zzg.zza localzza)
    {
      for (;;)
      {
        zzb.zzd("Could not create remote InAppPurchaseManager.", localzza);
        zzft localzzft1 = null;
      }
    }
    return localzzft1;
  }
  
  protected zzfu zzU(IBinder paramIBinder)
  {
    return zzfu.zza.zzR(paramIBinder);
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


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzfy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */