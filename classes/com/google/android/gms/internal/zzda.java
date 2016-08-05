package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import android.widget.FrameLayout;
import com.google.android.gms.ads.internal.client.zzl;
import com.google.android.gms.ads.internal.formats.zzj;
import com.google.android.gms.ads.internal.util.client.zza;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.dynamic.zzg;
import com.google.android.gms.dynamic.zzg.zza;

public class zzda
  extends zzg<zzcp>
{
  public zzda()
  {
    super("com.google.android.gms.ads.NativeAdViewDelegateCreatorImpl");
  }
  
  private zzco zzb(Context paramContext, FrameLayout paramFrameLayout1, FrameLayout paramFrameLayout2)
  {
    try
    {
      zzd localzzd1 = zze.zzy(paramContext);
      zzd localzzd2 = zze.zzy(paramFrameLayout1);
      zzd localzzd3 = zze.zzy(paramFrameLayout2);
      zzco localzzco2 = zzco.zza.zzu(((zzcp)zzas(paramContext)).zza(localzzd1, localzzd2, localzzd3, 8115000));
      localzzco1 = localzzco2;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        zzb.zzd("Could not create remote NativeAdViewDelegate.", localRemoteException);
        zzco localzzco1 = null;
      }
    }
    catch (zzg.zza localzza)
    {
      for (;;) {}
    }
    return localzzco1;
  }
  
  protected zzcp zzD(IBinder paramIBinder)
  {
    return zzcp.zza.zzv(paramIBinder);
  }
  
  public zzco zza(Context paramContext, FrameLayout paramFrameLayout1, FrameLayout paramFrameLayout2)
  {
    Object localObject;
    if (zzl.zzcF().zzR(paramContext))
    {
      localObject = zzb(paramContext, paramFrameLayout1, paramFrameLayout2);
      if (localObject != null) {}
    }
    else
    {
      zzb.zzaF("Using NativeAdViewDelegate from the client jar.");
      localObject = new zzj(paramFrameLayout1, paramFrameLayout2);
    }
    return (zzco)localObject;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzda.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */