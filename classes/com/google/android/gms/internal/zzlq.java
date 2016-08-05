package com.google.android.gms.internal;

import android.util.Log;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.ResultCallbacks;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.zzb;
import com.google.android.gms.common.api.zze;
import com.google.android.gms.common.internal.zzx;

public class zzlq<R extends Result>
  extends zze<R>
  implements ResultCallback<R>
{
  private final Object zzabh;
  private zzb<? super R, ? extends Result> zzacY;
  private zzlq<? extends Result> zzacZ;
  private ResultCallbacks<? super R> zzada;
  private PendingResult<R> zzadb;
  
  private void zzd(Result paramResult)
  {
    if ((paramResult instanceof Releasable)) {}
    try
    {
      ((Releasable)paramResult).release();
      return;
    }
    catch (RuntimeException localRuntimeException)
    {
      for (;;)
      {
        Log.w("TransformedResultImpl", "Unable to release " + paramResult, localRuntimeException);
      }
    }
  }
  
  private void zzon()
  {
    if ((this.zzadb == null) || ((this.zzacY == null) && (this.zzada == null))) {}
    for (;;)
    {
      return;
      this.zzadb.setResultCallback(this);
    }
  }
  
  public void onResult(R paramR)
  {
    synchronized (this.zzabh)
    {
      if (paramR.getStatus().isSuccess()) {
        if (this.zzacY != null)
        {
          PendingResult localPendingResult = this.zzacY.zza(paramR);
          if (localPendingResult == null)
          {
            zzx(new Status(13, "Transform returned null"));
            zzd(paramR);
            return;
          }
          this.zzacZ.zza(localPendingResult);
        }
      }
    }
  }
  
  public void zza(PendingResult<?> paramPendingResult)
  {
    synchronized (this.zzabh)
    {
      this.zzadb = paramPendingResult;
      zzon();
      return;
    }
  }
  
  public void zzx(Status paramStatus)
  {
    synchronized (this.zzabh)
    {
      if (this.zzacY != null)
      {
        localStatus = this.zzacY.zzu(paramStatus);
        zzx.zzb(localStatus, "onFailure must not return null");
        this.zzacZ.zzx(localStatus);
      }
      while (this.zzada == null)
      {
        Status localStatus;
        return;
      }
      this.zzada.onFailure(paramStatus);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzlq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */