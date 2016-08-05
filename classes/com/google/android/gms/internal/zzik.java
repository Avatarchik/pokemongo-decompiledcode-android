package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzp;

public class zzik
{
  private long zzJk;
  private long zzJl = Long.MIN_VALUE;
  private Object zzpd = new Object();
  
  public zzik(long paramLong)
  {
    this.zzJk = paramLong;
  }
  
  public boolean tryAcquire()
  {
    boolean bool;
    synchronized (this.zzpd)
    {
      long l = zzp.zzbz().elapsedRealtime();
      if (this.zzJl + this.zzJk > l)
      {
        bool = false;
      }
      else
      {
        this.zzJl = l;
        bool = true;
      }
    }
    return bool;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzik.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */