package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.PendingResult.zza;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzq;
import com.google.android.gms.common.internal.zzx;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public abstract class zzlc<R extends Result>
  extends PendingResult<R>
{
  private boolean zzL;
  private volatile R zzaaX;
  private final Object zzabh = new Object();
  protected final zza<R> zzabi;
  private final ArrayList<PendingResult.zza> zzabj = new ArrayList();
  private ResultCallback<? super R> zzabk;
  private volatile boolean zzabl;
  private boolean zzabm;
  private zzq zzabn;
  private Integer zzabo;
  private volatile zzlq<R> zzabp;
  private final CountDownLatch zzoS = new CountDownLatch(1);
  
  @Deprecated
  protected zzlc(Looper paramLooper)
  {
    this.zzabi = new zza(paramLooper);
  }
  
  protected zzlc(GoogleApiClient paramGoogleApiClient)
  {
    if (paramGoogleApiClient != null) {}
    for (Looper localLooper = paramGoogleApiClient.getLooper();; localLooper = Looper.getMainLooper())
    {
      this.zzabi = new zza(localLooper);
      return;
    }
  }
  
  private R get()
  {
    boolean bool = true;
    synchronized (this.zzabh)
    {
      if (!this.zzabl)
      {
        zzx.zza(bool, "Result has already been consumed.");
        zzx.zza(isReady(), "Result is not ready.");
        Result localResult = this.zzaaX;
        this.zzaaX = null;
        this.zzabk = null;
        this.zzabl = true;
        zznL();
        return localResult;
      }
      bool = false;
    }
  }
  
  private void zzc(R paramR)
  {
    this.zzaaX = paramR;
    this.zzabn = null;
    this.zzoS.countDown();
    Status localStatus = this.zzaaX.getStatus();
    if (this.zzabk != null)
    {
      this.zzabi.zznM();
      if (!this.zzL) {
        this.zzabi.zza(this.zzabk, get());
      }
    }
    Iterator localIterator = this.zzabj.iterator();
    while (localIterator.hasNext()) {
      ((PendingResult.zza)localIterator.next()).zzt(localStatus);
    }
    this.zzabj.clear();
  }
  
  public static void zzd(Result paramResult)
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
        Log.w("BasePendingResult", "Unable to release " + paramResult, localRuntimeException);
      }
    }
  }
  
  public final R await()
  {
    boolean bool1 = true;
    boolean bool2;
    if (Looper.myLooper() != Looper.getMainLooper()) {
      bool2 = bool1;
    }
    for (;;)
    {
      zzx.zza(bool2, "await must not be called on the UI thread");
      boolean bool3;
      if (!this.zzabl)
      {
        bool3 = bool1;
        label28:
        zzx.zza(bool3, "Result has already been consumed");
        if (this.zzabp != null) {
          break label78;
        }
        zzx.zza(bool1, "Cannot await if then() has been called.");
      }
      try
      {
        this.zzoS.await();
        zzx.zza(isReady(), "Result is not ready.");
        return get();
        bool2 = false;
        continue;
        bool3 = false;
        break label28;
        label78:
        bool1 = false;
      }
      catch (InterruptedException localInterruptedException)
      {
        for (;;)
        {
          zzw(Status.zzabc);
        }
      }
    }
  }
  
  public final R await(long paramLong, TimeUnit paramTimeUnit)
  {
    boolean bool1 = true;
    boolean bool2;
    if ((paramLong <= 0L) || (Looper.myLooper() != Looper.getMainLooper())) {
      bool2 = bool1;
    }
    for (;;)
    {
      zzx.zza(bool2, "await must not be called on the UI thread when time is greater than zero.");
      boolean bool3;
      if (!this.zzabl)
      {
        bool3 = bool1;
        label40:
        zzx.zza(bool3, "Result has already been consumed.");
        if (this.zzabp != null) {
          break label106;
        }
        zzx.zza(bool1, "Cannot await if then() has been called.");
      }
      try
      {
        if (!this.zzoS.await(paramLong, paramTimeUnit)) {
          zzw(Status.zzabe);
        }
        zzx.zza(isReady(), "Result is not ready.");
        return get();
        bool2 = false;
        continue;
        bool3 = false;
        break label40;
        label106:
        bool1 = false;
      }
      catch (InterruptedException localInterruptedException)
      {
        for (;;)
        {
          zzw(Status.zzabc);
        }
      }
    }
  }
  
  public void cancel()
  {
    synchronized (this.zzabh)
    {
      if ((!this.zzL) && (this.zzabl)) {
        return;
      }
      zzq localzzq = this.zzabn;
      if (localzzq == null) {}
    }
    try
    {
      this.zzabn.cancel();
      zzd(this.zzaaX);
      this.zzabk = null;
      this.zzL = true;
      zzc(zzb(Status.zzabf));
      return;
      localObject2 = finally;
      throw ((Throwable)localObject2);
    }
    catch (RemoteException localRemoteException)
    {
      for (;;) {}
    }
  }
  
  public boolean isCanceled()
  {
    synchronized (this.zzabh)
    {
      boolean bool = this.zzL;
      return bool;
    }
  }
  
  public final boolean isReady()
  {
    if (this.zzoS.getCount() == 0L) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public final void setResultCallback(ResultCallback<? super R> paramResultCallback)
  {
    boolean bool1 = true;
    boolean bool2;
    if (!this.zzabl)
    {
      bool2 = bool1;
      zzx.zza(bool2, "Result has already been consumed.");
    }
    for (;;)
    {
      synchronized (this.zzabh)
      {
        if (this.zzabp != null) {
          break label99;
        }
        zzx.zza(bool1, "Cannot set callbacks if then() has been called.");
        if (!isCanceled()) {
          if (isReady()) {
            this.zzabi.zza(paramResultCallback, get());
          }
        }
      }
      return;
      bool2 = false;
      break;
      label99:
      bool1 = false;
    }
  }
  
  public final void setResultCallback(ResultCallback<? super R> paramResultCallback, long paramLong, TimeUnit paramTimeUnit)
  {
    boolean bool1 = true;
    boolean bool2;
    if (!this.zzabl)
    {
      bool2 = bool1;
      zzx.zza(bool2, "Result has already been consumed.");
    }
    for (;;)
    {
      synchronized (this.zzabh)
      {
        if (this.zzabp != null) {
          break label119;
        }
        zzx.zza(bool1, "Cannot set callbacks if then() has been called.");
        if (!isCanceled()) {
          if (isReady()) {
            this.zzabi.zza(paramResultCallback, get());
          }
        }
      }
      return;
      bool2 = false;
      break;
      label119:
      bool1 = false;
    }
  }
  
  public final void zza(PendingResult.zza paramzza)
  {
    boolean bool1 = true;
    boolean bool2;
    if (!this.zzabl)
    {
      bool2 = bool1;
      zzx.zza(bool2, "Result has already been consumed.");
      if (paramzza == null) {
        break label87;
      }
      zzx.zzb(bool1, "Callback cannot be null.");
    }
    label87:
    synchronized (this.zzabh)
    {
      if (isReady())
      {
        paramzza.zzt(this.zzaaX.getStatus());
        return;
      }
      this.zzabj.add(paramzza);
    }
  }
  
  protected final void zza(zzq paramzzq)
  {
    synchronized (this.zzabh)
    {
      this.zzabn = paramzzq;
      return;
    }
  }
  
  protected abstract R zzb(Status paramStatus);
  
  public final void zzb(R paramR)
  {
    boolean bool1 = true;
    for (;;)
    {
      synchronized (this.zzabh)
      {
        if ((this.zzabm) || (this.zzL))
        {
          zzd(paramR);
          break;
        }
        if (!isReady())
        {
          bool2 = bool1;
          zzx.zza(bool2, "Results have already been set");
          if (this.zzabl) {
            break label86;
          }
          zzx.zza(bool1, "Result has already been consumed");
          zzc(paramR);
        }
      }
      boolean bool2 = false;
      continue;
      label86:
      bool1 = false;
    }
  }
  
  public Integer zznF()
  {
    return this.zzabo;
  }
  
  protected void zznL() {}
  
  public final void zzw(Status paramStatus)
  {
    synchronized (this.zzabh)
    {
      if (!isReady())
      {
        zzb(zzb(paramStatus));
        this.zzabm = true;
      }
      return;
    }
  }
  
  public static class zza<R extends Result>
    extends Handler
  {
    public zza()
    {
      this(Looper.getMainLooper());
    }
    
    public zza(Looper paramLooper)
    {
      super();
    }
    
    public void handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      default: 
        Log.wtf("BasePendingResult", "Don't know how to handle message: " + paramMessage.what, new Exception());
      }
      for (;;)
      {
        return;
        Pair localPair = (Pair)paramMessage.obj;
        zzb((ResultCallback)localPair.first, (Result)localPair.second);
        continue;
        ((zzlc)paramMessage.obj).zzw(Status.zzabe);
      }
    }
    
    public void zza(ResultCallback<? super R> paramResultCallback, R paramR)
    {
      sendMessage(obtainMessage(1, new Pair(paramResultCallback, paramR)));
    }
    
    public void zza(zzlc<R> paramzzlc, long paramLong)
    {
      sendMessageDelayed(obtainMessage(2, paramzzlc), paramLong);
    }
    
    protected void zzb(ResultCallback<? super R> paramResultCallback, R paramR)
    {
      try
      {
        paramResultCallback.onResult(paramR);
        return;
      }
      catch (RuntimeException localRuntimeException)
      {
        zzlc.zzd(paramR);
        throw localRuntimeException;
      }
    }
    
    public void zznM()
    {
      removeMessages(2);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzlc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */