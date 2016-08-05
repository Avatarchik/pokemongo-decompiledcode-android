package com.google.android.gms.internal;

import java.util.concurrent.CancellationException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@zzgr
public class zzin<T>
  implements zziq<T>
{
  private final zzir zzJA = new zzir();
  private T zzJy = null;
  private boolean zzJz = false;
  private final Object zzpd = new Object();
  private boolean zzzD = false;
  
  public boolean cancel(boolean paramBoolean)
  {
    boolean bool = false;
    if (!paramBoolean) {}
    for (;;)
    {
      return bool;
      synchronized (this.zzpd)
      {
        if (!this.zzJz) {}
      }
    }
  }
  
  public T get()
  {
    synchronized (this.zzpd)
    {
      boolean bool = this.zzJz;
      if (bool) {}
    }
    try
    {
      this.zzpd.wait();
      if (this.zzzD)
      {
        throw new CancellationException("CallbackFuture was cancelled.");
        localObject2 = finally;
        throw ((Throwable)localObject2);
      }
      Object localObject3 = this.zzJy;
      return (T)localObject3;
    }
    catch (InterruptedException localInterruptedException)
    {
      for (;;) {}
    }
  }
  
  public T get(long paramLong, TimeUnit paramTimeUnit)
    throws TimeoutException
  {
    synchronized (this.zzpd)
    {
      boolean bool = this.zzJz;
      if (bool) {}
    }
    try
    {
      long l = paramTimeUnit.toMillis(paramLong);
      if (l != 0L) {
        this.zzpd.wait(l);
      }
      if (!this.zzJz)
      {
        throw new TimeoutException("CallbackFuture timed out.");
        localObject2 = finally;
        throw ((Throwable)localObject2);
      }
      if (this.zzzD) {
        throw new CancellationException("CallbackFuture was cancelled.");
      }
      Object localObject3 = this.zzJy;
      return (T)localObject3;
    }
    catch (InterruptedException localInterruptedException)
    {
      for (;;) {}
    }
  }
  
  public boolean isCancelled()
  {
    synchronized (this.zzpd)
    {
      boolean bool = this.zzzD;
      return bool;
    }
  }
  
  public boolean isDone()
  {
    synchronized (this.zzpd)
    {
      boolean bool = this.zzJz;
      return bool;
    }
  }
  
  public void zzc(Runnable paramRunnable)
  {
    this.zzJA.zzc(paramRunnable);
  }
  
  public void zzd(Runnable paramRunnable)
  {
    this.zzJA.zzd(paramRunnable);
  }
  
  public void zzf(T paramT)
  {
    synchronized (this.zzpd)
    {
      if (!this.zzzD) {
        if (this.zzJz) {
          throw new IllegalStateException("Provided CallbackFuture with multiple values.");
        }
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzin.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */