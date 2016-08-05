package com.google.android.gms.ads.internal.client;

import java.util.Random;

public class zzm
  extends zzv.zza
{
  private Object zzpd = new Object();
  private final Random zzts = new Random();
  private long zztt;
  
  public zzm()
  {
    zzcL();
  }
  
  public long getValue()
  {
    return this.zztt;
  }
  
  public void zzcL()
  {
    Object localObject1 = this.zzpd;
    int i = 3;
    long l = 0L;
    for (;;)
    {
      i--;
      if (i > 0) {}
      try
      {
        l = 2147483648L + this.zzts.nextInt();
        if ((l == this.zztt) || (l == 0L)) {
          continue;
        }
        this.zztt = l;
        return;
      }
      finally
      {
        localObject2 = finally;
        throw ((Throwable)localObject2);
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/ads/internal/client/zzm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */