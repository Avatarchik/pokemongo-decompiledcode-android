package com.google.android.gms.ads.internal.request;

import android.content.Context;
import android.os.Binder;
import android.os.Bundle;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzp;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.internal.zzbr;
import com.google.android.gms.internal.zzbu;
import com.google.android.gms.internal.zzby;
import com.google.android.gms.internal.zzgr;
import com.google.android.gms.internal.zzgs;
import com.google.android.gms.internal.zzgt;
import com.google.android.gms.internal.zzhu;
import com.google.android.gms.internal.zzhz;
import com.google.android.gms.internal.zzid;
import com.google.android.gms.internal.zzii;
import com.google.android.gms.internal.zzmn;

@zzgr
public abstract class zzd
  extends zzhz
  implements zzc.zza
{
  private AdResponseParcel zzDf;
  private final zzc.zza zzEi;
  private final Object zzpd = new Object();
  private final AdRequestInfoParcel zzzz;
  
  public zzd(AdRequestInfoParcel paramAdRequestInfoParcel, zzc.zza paramzza)
  {
    this.zzzz = paramAdRequestInfoParcel;
    this.zzEi = paramzza;
  }
  
  public final void onStop()
  {
    zzfH();
  }
  
  boolean zza(zzj paramzzj, AdRequestInfoParcel paramAdRequestInfoParcel)
  {
    boolean bool = true;
    try
    {
      paramzzj.zza(paramAdRequestInfoParcel, new zzg(this));
      return bool;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        zzb.zzd("Could not fetch ad response from ad request service.", localRemoteException);
        zzp.zzby().zzc(localRemoteException, bool);
        this.zzEi.zzb(new AdResponseParcel(0));
        bool = false;
      }
    }
    catch (NullPointerException localNullPointerException)
    {
      for (;;)
      {
        zzb.zzd("Could not fetch ad response from ad request service due to an Exception.", localNullPointerException);
        zzp.zzby().zzc(localNullPointerException, bool);
      }
    }
    catch (SecurityException localSecurityException)
    {
      for (;;)
      {
        zzb.zzd("Could not fetch ad response from ad request service due to an Exception.", localSecurityException);
        zzp.zzby().zzc(localSecurityException, bool);
      }
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        zzb.zzd("Could not fetch ad response from ad request service due to an Exception.", localThrowable);
        zzp.zzby().zzc(localThrowable, bool);
      }
    }
  }
  
  public void zzb(AdResponseParcel paramAdResponseParcel)
  {
    synchronized (this.zzpd)
    {
      this.zzDf = paramAdResponseParcel;
      this.zzpd.notify();
      return;
    }
  }
  
  /* Error */
  public void zzbn()
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 98	com/google/android/gms/ads/internal/request/zzd:zzfI	()Lcom/google/android/gms/ads/internal/request/zzj;
    //   4: astore_2
    //   5: aload_2
    //   6: ifnonnull +27 -> 33
    //   9: new 80	com/google/android/gms/ads/internal/request/AdResponseParcel
    //   12: dup
    //   13: iconst_0
    //   14: invokespecial 83	com/google/android/gms/ads/internal/request/AdResponseParcel:<init>	(I)V
    //   17: astore_3
    //   18: aload_0
    //   19: getfield 35	com/google/android/gms/ads/internal/request/zzd:zzEi	Lcom/google/android/gms/ads/internal/request/zzc$zza;
    //   22: aload_3
    //   23: invokeinterface 86 2 0
    //   28: aload_0
    //   29: invokevirtual 39	com/google/android/gms/ads/internal/request/zzd:zzfH	()V
    //   32: return
    //   33: aload_0
    //   34: aload_2
    //   35: aload_0
    //   36: getfield 33	com/google/android/gms/ads/internal/request/zzd:zzzz	Lcom/google/android/gms/ads/internal/request/AdRequestInfoParcel;
    //   39: invokevirtual 100	com/google/android/gms/ads/internal/request/zzd:zza	(Lcom/google/android/gms/ads/internal/request/zzj;Lcom/google/android/gms/ads/internal/request/AdRequestInfoParcel;)Z
    //   42: ifeq -14 -> 28
    //   45: aload_0
    //   46: invokestatic 104	com/google/android/gms/ads/internal/zzp:zzbz	()Lcom/google/android/gms/internal/zzmn;
    //   49: invokeinterface 110 1 0
    //   54: invokevirtual 114	com/google/android/gms/ads/internal/request/zzd:zzi	(J)V
    //   57: goto -29 -> 28
    //   60: astore_1
    //   61: aload_0
    //   62: invokevirtual 39	com/google/android/gms/ads/internal/request/zzd:zzfH	()V
    //   65: aload_1
    //   66: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	67	0	this	zzd
    //   60	6	1	localObject	Object
    //   4	31	2	localzzj	zzj
    //   17	6	3	localAdResponseParcel	AdResponseParcel
    // Exception table:
    //   from	to	target	type
    //   0	28	60	finally
    //   33	57	60	finally
  }
  
  protected boolean zzf(long paramLong)
  {
    boolean bool = false;
    long l = 60000L - (zzp.zzbz().elapsedRealtime() - paramLong);
    if (l <= 0L) {}
    for (;;)
    {
      return bool;
      try
      {
        this.zzpd.wait(l);
        bool = true;
      }
      catch (InterruptedException localInterruptedException) {}
    }
  }
  
  public abstract void zzfH();
  
  public abstract zzj zzfI();
  
  protected void zzi(long paramLong)
  {
    synchronized (this.zzpd)
    {
      do
      {
        if (this.zzDf != null)
        {
          this.zzEi.zzb(this.zzDf);
          break;
        }
      } while (zzf(paramLong));
      if (this.zzDf != null) {
        this.zzEi.zzb(this.zzDf);
      }
    }
  }
  
  @zzgr
  public static class zzb
    extends zzd
    implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener
  {
    private Context mContext;
    private final zzc.zza zzEi;
    protected zze zzEj;
    private boolean zzEk;
    private final Object zzpd = new Object();
    private AdRequestInfoParcel zzzz;
    
    public zzb(Context paramContext, AdRequestInfoParcel paramAdRequestInfoParcel, zzc.zza paramzza)
    {
      super(paramzza);
      this.mContext = paramContext;
      this.zzzz = paramAdRequestInfoParcel;
      this.zzEi = paramzza;
      if (((Boolean)zzby.zzuK.get()).booleanValue()) {
        this.zzEk = true;
      }
      for (Looper localLooper = zzp.zzbG().zzgM();; localLooper = paramContext.getMainLooper())
      {
        this.zzEj = new zze(paramContext, localLooper, this, this, paramAdRequestInfoParcel.zzqj.zzJw);
        connect();
        return;
      }
    }
    
    protected void connect()
    {
      this.zzEj.zzoZ();
    }
    
    public void onConnected(Bundle paramBundle)
    {
      zzgz();
    }
    
    public void onConnectionFailed(ConnectionResult paramConnectionResult)
    {
      zzb.zzaF("Cannot connect to remote service, fallback to local instance.");
      zzfJ().zzgz();
      Bundle localBundle = new Bundle();
      localBundle.putString("action", "gms_connection_failed_fallback_to_local");
      zzp.zzbv().zzb(this.mContext, this.zzzz.zzqj.zzJu, "gmob-apps", localBundle, true);
    }
    
    public void onConnectionSuspended(int paramInt)
    {
      zzb.zzaF("Disconnected from remote ad request service.");
    }
    
    public void zzfH()
    {
      synchronized (this.zzpd)
      {
        if ((this.zzEj.isConnected()) || (this.zzEj.isConnecting())) {
          this.zzEj.disconnect();
        }
        Binder.flushPendingCommands();
        if (this.zzEk)
        {
          zzp.zzbG().zzgN();
          this.zzEk = false;
        }
        return;
      }
    }
    
    /* Error */
    public zzj zzfI()
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 34	com/google/android/gms/ads/internal/request/zzd$zzb:zzpd	Ljava/lang/Object;
      //   4: astore_1
      //   5: aload_1
      //   6: monitorenter
      //   7: aload_0
      //   8: getfield 91	com/google/android/gms/ads/internal/request/zzd$zzb:zzEj	Lcom/google/android/gms/ads/internal/request/zze;
      //   11: invokevirtual 182	com/google/android/gms/ads/internal/request/zze:zzfM	()Lcom/google/android/gms/ads/internal/request/zzj;
      //   14: astore 6
      //   16: aload 6
      //   18: astore_3
      //   19: aload_1
      //   20: monitorexit
      //   21: goto +21 -> 42
      //   24: aconst_null
      //   25: astore_3
      //   26: aload_1
      //   27: monitorexit
      //   28: goto +14 -> 42
      //   31: astore 4
      //   33: aload_1
      //   34: monitorexit
      //   35: aload 4
      //   37: athrow
      //   38: astore_2
      //   39: goto -15 -> 24
      //   42: aload_3
      //   43: areturn
      //   44: astore 5
      //   46: goto -22 -> 24
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	49	0	this	zzb
      //   38	1	2	localDeadObjectException	android.os.DeadObjectException
      //   18	25	3	localzzj1	zzj
      //   31	5	4	localObject2	Object
      //   44	1	5	localIllegalStateException	IllegalStateException
      //   14	3	6	localzzj2	zzj
      // Exception table:
      //   from	to	target	type
      //   7	16	31	finally
      //   19	35	31	finally
      //   7	16	38	android/os/DeadObjectException
      //   7	16	44	java/lang/IllegalStateException
    }
    
    zzhz zzfJ()
    {
      return new zzd.zza(this.mContext, this.zzzz, this.zzEi);
    }
  }
  
  @zzgr
  public static final class zza
    extends zzd
  {
    private final Context mContext;
    
    public zza(Context paramContext, AdRequestInfoParcel paramAdRequestInfoParcel, zzc.zza paramzza)
    {
      super(paramzza);
      this.mContext = paramContext;
    }
    
    public void zzfH() {}
    
    public zzj zzfI()
    {
      zzbr localzzbr = new zzbr((String)zzby.zzul.get());
      return zzgt.zza(this.mContext, localzzbr, zzgs.zzfQ());
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/ads/internal/request/zzd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */