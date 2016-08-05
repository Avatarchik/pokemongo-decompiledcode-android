package com.google.android.gms.internal;

import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@zzgr
public class zzit<T>
  implements zzis<T>
{
  protected final BlockingQueue<zzit<T>.zza> zzJM = new LinkedBlockingQueue();
  protected T zzJN;
  private final Object zzpd = new Object();
  protected int zzys = 0;
  
  public int getStatus()
  {
    return this.zzys;
  }
  
  public void reject()
  {
    Iterator localIterator;
    synchronized (this.zzpd)
    {
      if (this.zzys != 0) {
        throw new UnsupportedOperationException();
      }
    }
  }
  
  public void zza(zzis.zzc<T> paramzzc, zzis.zza paramzza)
  {
    synchronized (this.zzpd)
    {
      if (this.zzys == 1)
      {
        paramzzc.zzc(this.zzJN);
        return;
      }
      if (this.zzys == -1) {
        paramzza.run();
      }
    }
  }
  
  public void zzg(T paramT)
  {
    Iterator localIterator;
    synchronized (this.zzpd)
    {
      if (this.zzys != 0) {
        throw new UnsupportedOperationException();
      }
    }
  }
  
  class zza
  {
    public final zzis.zzc<T> zzJO;
    public final zzis.zza zzJP;
    
    public zza(zzis.zza paramzza)
    {
      this.zzJO = paramzza;
      zzis.zza localzza;
      this.zzJP = localzza;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzit.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */