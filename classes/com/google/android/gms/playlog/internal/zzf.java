package com.google.android.gms.playlog.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.zzj;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.internal.zzse;
import java.util.ArrayList;
import java.util.Iterator;

public class zzf
  extends zzj<zza>
{
  private final String zzQe;
  private final zzd zzaRZ;
  private final zzb zzaSa;
  private boolean zzaSb;
  private final Object zzpd;
  
  public zzf(Context paramContext, Looper paramLooper, zzd paramzzd, com.google.android.gms.common.internal.zzf paramzzf)
  {
    super(paramContext, paramLooper, 24, paramzzf, paramzzd, paramzzd);
    this.zzQe = paramContext.getPackageName();
    this.zzaRZ = ((zzd)zzx.zzw(paramzzd));
    this.zzaRZ.zza(this);
    this.zzaSa = new zzb();
    this.zzpd = new Object();
    this.zzaSb = true;
  }
  
  private void zzBv()
  {
    boolean bool;
    if (!this.zzaSb)
    {
      bool = true;
      com.google.android.gms.common.internal.zzb.zzZ(bool);
      if (this.zzaSa.isEmpty()) {}
    }
    Object localObject2;
    label239:
    for (Object localObject1 = null;; localObject1 = localObject2)
    {
      ArrayList localArrayList;
      zzb.zza localzza;
      try
      {
        localArrayList = new ArrayList();
        Iterator localIterator = this.zzaSa.zzBt().iterator();
        for (;;)
        {
          if (localIterator.hasNext())
          {
            localzza = (zzb.zza)localIterator.next();
            if (localzza.zzaRO != null)
            {
              ((zza)zzpc()).zza(this.zzQe, localzza.zzaRM, zzse.zzf(localzza.zzaRO));
              continue;
            }
          }
        }
      }
      catch (RemoteException localRemoteException)
      {
        Log.e("PlayLoggerImpl", "Couldn't send cached log events to AndroidLog service.  Retaining in memory cache.");
      }
      for (;;)
      {
        bool = false;
        break;
        if (localzza.zzaRM.equals(localObject1))
        {
          localArrayList.add(localzza.zzaRN);
          localObject2 = localObject1;
          break label239;
        }
        if (!localArrayList.isEmpty())
        {
          ((zza)zzpc()).zza(this.zzQe, (PlayLoggerContext)localObject1, localArrayList);
          localArrayList.clear();
        }
        PlayLoggerContext localPlayLoggerContext = localzza.zzaRM;
        localArrayList.add(localzza.zzaRN);
        localObject2 = localPlayLoggerContext;
        break label239;
        if (!localArrayList.isEmpty()) {
          ((zza)zzpc()).zza(this.zzQe, (PlayLoggerContext)localObject1, localArrayList);
        }
        this.zzaSa.clear();
      }
    }
  }
  
  private void zzc(PlayLoggerContext paramPlayLoggerContext, LogEvent paramLogEvent)
  {
    this.zzaSa.zza(paramPlayLoggerContext, paramLogEvent);
  }
  
  private void zzd(PlayLoggerContext paramPlayLoggerContext, LogEvent paramLogEvent)
  {
    try
    {
      zzBv();
      ((zza)zzpc()).zza(this.zzQe, paramPlayLoggerContext, paramLogEvent);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        Log.e("PlayLoggerImpl", "Couldn't send log event.  Will try caching.");
        zzc(paramPlayLoggerContext, paramLogEvent);
      }
    }
    catch (IllegalStateException localIllegalStateException)
    {
      for (;;)
      {
        Log.e("PlayLoggerImpl", "Service was disconnected.  Will try caching.");
        zzc(paramPlayLoggerContext, paramLogEvent);
      }
    }
  }
  
  public void start()
  {
    synchronized (this.zzpd)
    {
      if ((isConnecting()) || (!isConnected()))
      {
        this.zzaRZ.zzao(true);
        zzoZ();
      }
    }
  }
  
  public void stop()
  {
    synchronized (this.zzpd)
    {
      this.zzaRZ.zzao(false);
      disconnect();
      return;
    }
  }
  
  void zzap(boolean paramBoolean)
  {
    synchronized (this.zzpd)
    {
      boolean bool = this.zzaSb;
      this.zzaSb = paramBoolean;
      if ((bool) && (!this.zzaSb)) {
        zzBv();
      }
      return;
    }
  }
  
  public void zzb(PlayLoggerContext paramPlayLoggerContext, LogEvent paramLogEvent)
  {
    synchronized (this.zzpd)
    {
      if (this.zzaSb)
      {
        zzc(paramPlayLoggerContext, paramLogEvent);
        return;
      }
      zzd(paramPlayLoggerContext, paramLogEvent);
    }
  }
  
  protected zza zzdA(IBinder paramIBinder)
  {
    return zza.zza.zzdz(paramIBinder);
  }
  
  protected String zzfK()
  {
    return "com.google.android.gms.playlog.service.START";
  }
  
  protected String zzfL()
  {
    return "com.google.android.gms.playlog.internal.IPlayLogService";
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/playlog/internal/zzf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */