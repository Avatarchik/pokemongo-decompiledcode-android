package com.google.android.gms.location.internal;

import android.app.PendingIntent;
import android.content.ContentProviderClient;
import android.content.Context;
import android.location.Location;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.zzc.zza;
import com.google.android.gms.location.zzd.zza;
import java.util.HashMap;
import java.util.Map;

public class zzk
{
  private final Context mContext;
  private final zzp<zzi> zzaFb;
  private ContentProviderClient zzaFv = null;
  private boolean zzaFw = false;
  private Map<LocationCallback, zza> zzaFx = new HashMap();
  private Map<LocationListener, zzc> zzaqR = new HashMap();
  
  public zzk(Context paramContext, zzp<zzi> paramzzp)
  {
    this.mContext = paramContext;
    this.zzaFb = paramzzp;
  }
  
  private zza zza(LocationCallback paramLocationCallback, Looper paramLooper)
  {
    synchronized (this.zzaqR)
    {
      zza localzza = (zza)this.zzaFx.get(paramLocationCallback);
      if (localzza == null) {
        localzza = new zza(paramLocationCallback, paramLooper);
      }
      this.zzaFx.put(paramLocationCallback, localzza);
      return localzza;
    }
  }
  
  private zzc zza(LocationListener paramLocationListener, Looper paramLooper)
  {
    synchronized (this.zzaqR)
    {
      zzc localzzc = (zzc)this.zzaqR.get(paramLocationListener);
      if (localzzc == null) {
        localzzc = new zzc(paramLocationListener, paramLooper);
      }
      this.zzaqR.put(paramLocationListener, localzzc);
      return localzzc;
    }
  }
  
  public Location getLastLocation()
  {
    this.zzaFb.zzpb();
    try
    {
      Location localLocation = ((zzi)this.zzaFb.zzpc()).zzdv(this.mContext.getPackageName());
      return localLocation;
    }
    catch (RemoteException localRemoteException)
    {
      throw new IllegalStateException(localRemoteException);
    }
  }
  
  /* Error */
  public void removeAllListeners()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 41	com/google/android/gms/location/internal/zzk:zzaqR	Ljava/util/Map;
    //   4: astore_2
    //   5: aload_2
    //   6: monitorenter
    //   7: aload_0
    //   8: getfield 41	com/google/android/gms/location/internal/zzk:zzaqR	Ljava/util/Map;
    //   11: invokeinterface 100 1 0
    //   16: invokeinterface 106 1 0
    //   21: astore 4
    //   23: aload 4
    //   25: invokeinterface 112 1 0
    //   30: ifeq +61 -> 91
    //   33: aload 4
    //   35: invokeinterface 116 1 0
    //   40: checkcast 12	com/google/android/gms/location/internal/zzk$zzc
    //   43: astore 7
    //   45: aload 7
    //   47: ifnull -24 -> 23
    //   50: aload_0
    //   51: getfield 47	com/google/android/gms/location/internal/zzk:zzaFb	Lcom/google/android/gms/location/internal/zzp;
    //   54: invokeinterface 78 1 0
    //   59: checkcast 80	com/google/android/gms/location/internal/zzi
    //   62: aload 7
    //   64: aconst_null
    //   65: invokestatic 121	com/google/android/gms/location/internal/LocationRequestUpdateData:zza	(Lcom/google/android/gms/location/zzd;Lcom/google/android/gms/location/internal/zzg;)Lcom/google/android/gms/location/internal/LocationRequestUpdateData;
    //   68: invokeinterface 124 2 0
    //   73: goto -50 -> 23
    //   76: astore_3
    //   77: aload_2
    //   78: monitorexit
    //   79: aload_3
    //   80: athrow
    //   81: astore_1
    //   82: new 92	java/lang/IllegalStateException
    //   85: dup
    //   86: aload_1
    //   87: invokespecial 95	java/lang/IllegalStateException:<init>	(Ljava/lang/Throwable;)V
    //   90: athrow
    //   91: aload_0
    //   92: getfield 41	com/google/android/gms/location/internal/zzk:zzaqR	Ljava/util/Map;
    //   95: invokeinterface 127 1 0
    //   100: aload_0
    //   101: getfield 43	com/google/android/gms/location/internal/zzk:zzaFx	Ljava/util/Map;
    //   104: invokeinterface 100 1 0
    //   109: invokeinterface 106 1 0
    //   114: astore 5
    //   116: aload 5
    //   118: invokeinterface 112 1 0
    //   123: ifeq +46 -> 169
    //   126: aload 5
    //   128: invokeinterface 116 1 0
    //   133: checkcast 9	com/google/android/gms/location/internal/zzk$zza
    //   136: astore 6
    //   138: aload 6
    //   140: ifnull -24 -> 116
    //   143: aload_0
    //   144: getfield 47	com/google/android/gms/location/internal/zzk:zzaFb	Lcom/google/android/gms/location/internal/zzp;
    //   147: invokeinterface 78 1 0
    //   152: checkcast 80	com/google/android/gms/location/internal/zzi
    //   155: aload 6
    //   157: aconst_null
    //   158: invokestatic 130	com/google/android/gms/location/internal/LocationRequestUpdateData:zza	(Lcom/google/android/gms/location/zzc;Lcom/google/android/gms/location/internal/zzg;)Lcom/google/android/gms/location/internal/LocationRequestUpdateData;
    //   161: invokeinterface 124 2 0
    //   166: goto -50 -> 116
    //   169: aload_0
    //   170: getfield 43	com/google/android/gms/location/internal/zzk:zzaFx	Ljava/util/Map;
    //   173: invokeinterface 127 1 0
    //   178: aload_2
    //   179: monitorexit
    //   180: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	181	0	this	zzk
    //   81	6	1	localRemoteException	RemoteException
    //   76	4	3	localObject	Object
    //   21	13	4	localIterator1	java.util.Iterator
    //   114	13	5	localIterator2	java.util.Iterator
    //   136	20	6	localzza	zza
    //   43	20	7	localzzc	zzc
    // Exception table:
    //   from	to	target	type
    //   7	79	76	finally
    //   91	180	76	finally
    //   0	7	81	android/os/RemoteException
    //   79	81	81	android/os/RemoteException
  }
  
  public void zza(PendingIntent paramPendingIntent, zzg paramzzg)
    throws RemoteException
  {
    this.zzaFb.zzpb();
    ((zzi)this.zzaFb.zzpc()).zza(LocationRequestUpdateData.zzb(paramPendingIntent, paramzzg));
  }
  
  public void zza(LocationCallback paramLocationCallback, zzg paramzzg)
    throws RemoteException
  {
    this.zzaFb.zzpb();
    zzx.zzb(paramLocationCallback, "Invalid null callback");
    synchronized (this.zzaFx)
    {
      zza localzza = (zza)this.zzaFx.remove(paramLocationCallback);
      if (localzza != null)
      {
        localzza.release();
        ((zzi)this.zzaFb.zzpc()).zza(LocationRequestUpdateData.zza(localzza, paramzzg));
      }
      return;
    }
  }
  
  public void zza(LocationListener paramLocationListener, zzg paramzzg)
    throws RemoteException
  {
    this.zzaFb.zzpb();
    zzx.zzb(paramLocationListener, "Invalid null listener");
    synchronized (this.zzaqR)
    {
      zzc localzzc = (zzc)this.zzaqR.remove(paramLocationListener);
      if ((this.zzaFv != null) && (this.zzaqR.isEmpty()))
      {
        this.zzaFv.release();
        this.zzaFv = null;
      }
      if (localzzc != null)
      {
        localzzc.release();
        ((zzi)this.zzaFb.zzpc()).zza(LocationRequestUpdateData.zza(localzzc, paramzzg));
      }
      return;
    }
  }
  
  public void zza(LocationRequest paramLocationRequest, PendingIntent paramPendingIntent, zzg paramzzg)
    throws RemoteException
  {
    this.zzaFb.zzpb();
    ((zzi)this.zzaFb.zzpc()).zza(LocationRequestUpdateData.zza(LocationRequestInternal.zzb(paramLocationRequest), paramPendingIntent, paramzzg));
  }
  
  public void zza(LocationRequest paramLocationRequest, LocationListener paramLocationListener, Looper paramLooper, zzg paramzzg)
    throws RemoteException
  {
    this.zzaFb.zzpb();
    zzc localzzc = zza(paramLocationListener, paramLooper);
    ((zzi)this.zzaFb.zzpc()).zza(LocationRequestUpdateData.zza(LocationRequestInternal.zzb(paramLocationRequest), localzzc, paramzzg));
  }
  
  public void zza(LocationRequestInternal paramLocationRequestInternal, LocationCallback paramLocationCallback, Looper paramLooper, zzg paramzzg)
    throws RemoteException
  {
    this.zzaFb.zzpb();
    zza localzza = zza(paramLocationCallback, paramLooper);
    ((zzi)this.zzaFb.zzpc()).zza(LocationRequestUpdateData.zza(paramLocationRequestInternal, localzza, paramzzg));
  }
  
  public void zzah(boolean paramBoolean)
    throws RemoteException
  {
    this.zzaFb.zzpb();
    ((zzi)this.zzaFb.zzpc()).zzah(paramBoolean);
    this.zzaFw = paramBoolean;
  }
  
  public void zzc(Location paramLocation)
    throws RemoteException
  {
    this.zzaFb.zzpb();
    ((zzi)this.zzaFb.zzpc()).zzc(paramLocation);
  }
  
  public LocationAvailability zzwD()
  {
    this.zzaFb.zzpb();
    try
    {
      LocationAvailability localLocationAvailability = ((zzi)this.zzaFb.zzpc()).zzdw(this.mContext.getPackageName());
      return localLocationAvailability;
    }
    catch (RemoteException localRemoteException)
    {
      throw new IllegalStateException(localRemoteException);
    }
  }
  
  public void zzwE()
  {
    if (this.zzaFw) {}
    try
    {
      zzah(false);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      throw new IllegalStateException(localRemoteException);
    }
  }
  
  private static class zzb
    extends Handler
  {
    private final LocationListener zzaFA;
    
    public zzb(LocationListener paramLocationListener)
    {
      this.zzaFA = paramLocationListener;
    }
    
    public zzb(LocationListener paramLocationListener, Looper paramLooper)
    {
      super();
      this.zzaFA = paramLocationListener;
    }
    
    public void handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      default: 
        Log.e("LocationClientHelper", "unknown message in LocationHandler.handleMessage");
      }
      for (;;)
      {
        return;
        Location localLocation = new Location((Location)paramMessage.obj);
        this.zzaFA.onLocationChanged(localLocation);
      }
    }
  }
  
  private static class zza
    extends zzc.zza
  {
    private Handler zzaFy;
    
    zza(final LocationCallback paramLocationCallback, Looper paramLooper)
    {
      if (paramLooper == null)
      {
        paramLooper = Looper.myLooper();
        if (paramLooper == null) {
          break label39;
        }
      }
      label39:
      for (boolean bool = true;; bool = false)
      {
        zzx.zza(bool, "Can't create handler inside thread that has not called Looper.prepare()");
        this.zzaFy = new Handler(paramLooper)
        {
          public void handleMessage(Message paramAnonymousMessage)
          {
            switch (paramAnonymousMessage.what)
            {
            }
            for (;;)
            {
              return;
              paramLocationCallback.onLocationResult((LocationResult)paramAnonymousMessage.obj);
              continue;
              paramLocationCallback.onLocationAvailability((LocationAvailability)paramAnonymousMessage.obj);
            }
          }
        };
        return;
      }
    }
    
    private void zzb(int paramInt, Object paramObject)
    {
      if (this.zzaFy == null) {
        Log.e("LocationClientHelper", "Received a data in client after calling removeLocationUpdates.");
      }
      for (;;)
      {
        return;
        Message localMessage = Message.obtain();
        localMessage.what = paramInt;
        localMessage.obj = paramObject;
        this.zzaFy.sendMessage(localMessage);
      }
    }
    
    public void onLocationAvailability(LocationAvailability paramLocationAvailability)
    {
      zzb(1, paramLocationAvailability);
    }
    
    public void onLocationResult(LocationResult paramLocationResult)
    {
      zzb(0, paramLocationResult);
    }
    
    public void release()
    {
      this.zzaFy = null;
    }
  }
  
  private static class zzc
    extends zzd.zza
  {
    private Handler zzaFy;
    
    zzc(LocationListener paramLocationListener, Looper paramLooper)
    {
      boolean bool;
      if (paramLooper == null)
      {
        if (Looper.myLooper() != null)
        {
          bool = true;
          zzx.zza(bool, "Can't create handler inside thread that has not called Looper.prepare()");
        }
      }
      else {
        if (paramLooper != null) {
          break label49;
        }
      }
      label49:
      for (zzk.zzb localzzb = new zzk.zzb(paramLocationListener);; localzzb = new zzk.zzb(paramLocationListener, paramLooper))
      {
        this.zzaFy = localzzb;
        return;
        bool = false;
        break;
      }
    }
    
    public void onLocationChanged(Location paramLocation)
    {
      if (this.zzaFy == null) {
        Log.e("LocationClientHelper", "Received a location in client after calling removeLocationUpdates.");
      }
      for (;;)
      {
        return;
        Message localMessage = Message.obtain();
        localMessage.what = 1;
        localMessage.obj = paramLocation;
        this.zzaFy.sendMessage(localMessage);
      }
    }
    
    public void release()
    {
      this.zzaFy = null;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/location/internal/zzk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */