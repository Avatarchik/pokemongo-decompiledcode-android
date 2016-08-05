package com.google.android.gms.common.stats;

import android.os.SystemClock;
import android.support.v4.util.SimpleArrayMap;
import android.util.Log;

public class zze
{
  private final long zzahV;
  private final int zzahW;
  private final SimpleArrayMap<String, Long> zzahX;
  
  public zze()
  {
    this.zzahV = 60000L;
    this.zzahW = 10;
    this.zzahX = new SimpleArrayMap(10);
  }
  
  public zze(int paramInt, long paramLong)
  {
    this.zzahV = paramLong;
    this.zzahW = paramInt;
    this.zzahX = new SimpleArrayMap();
  }
  
  private void zzb(long paramLong1, long paramLong2)
  {
    for (int i = -1 + this.zzahX.size(); i >= 0; i--) {
      if (paramLong2 - ((Long)this.zzahX.valueAt(i)).longValue() > paramLong1) {
        this.zzahX.removeAt(i);
      }
    }
  }
  
  public Long zzcx(String paramString)
  {
    long l1 = SystemClock.elapsedRealtime();
    long l2 = this.zzahV;
    Long localLong;
    try
    {
      if (this.zzahX.size() >= this.zzahW)
      {
        zzb(l2, l1);
        l2 /= 2L;
        Log.w("ConnectionTracker", "The max capacity " + this.zzahW + " is not enough. Current durationThreshold is: " + l2);
      }
    }
    finally
    {
      throw ((Throwable)localObject);
    }
    return localLong;
  }
  
  public boolean zzcy(String paramString)
  {
    for (;;)
    {
      try
      {
        if (this.zzahX.remove(paramString) != null)
        {
          bool = true;
          return bool;
        }
      }
      finally
      {
        localObject = finally;
        throw ((Throwable)localObject);
      }
      boolean bool = false;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/common/stats/zze.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */