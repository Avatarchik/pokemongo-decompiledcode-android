package com.google.android.gms.internal;

import android.os.Process;
import java.util.concurrent.BlockingQueue;

public class zzc
  extends Thread
{
  private static final boolean DEBUG = zzs.DEBUG;
  private final BlockingQueue<zzk<?>> zzh;
  private final BlockingQueue<zzk<?>> zzi;
  private final zzb zzj;
  private final zzn zzk;
  private volatile boolean zzl = false;
  
  public zzc(BlockingQueue<zzk<?>> paramBlockingQueue1, BlockingQueue<zzk<?>> paramBlockingQueue2, zzb paramzzb, zzn paramzzn)
  {
    this.zzh = paramBlockingQueue1;
    this.zzi = paramBlockingQueue2;
    this.zzj = paramzzb;
    this.zzk = paramzzn;
  }
  
  public void quit()
  {
    this.zzl = true;
    interrupt();
  }
  
  public void run()
  {
    if (DEBUG) {
      zzs.zza("start new dispatcher", new Object[0]);
    }
    Process.setThreadPriority(10);
    this.zzj.zza();
    for (;;)
    {
      final zzk localzzk;
      try
      {
        localzzk = (zzk)this.zzh.take();
        localzzk.zzc("cache-queue-take");
        if (!localzzk.isCanceled()) {
          break label73;
        }
        localzzk.zzd("cache-discard-canceled");
        continue;
        if (!this.zzl) {
          continue;
        }
      }
      catch (InterruptedException localInterruptedException) {}
      return;
      label73:
      zzb.zza localzza = this.zzj.zza(localzzk.zzh());
      if (localzza == null)
      {
        localzzk.zzc("cache-miss");
        this.zzi.put(localzzk);
      }
      else if (localzza.zzb())
      {
        localzzk.zzc("cache-hit-expired");
        localzzk.zza(localzza);
        this.zzi.put(localzzk);
      }
      else
      {
        localzzk.zzc("cache-hit");
        zzm localzzm = localzzk.zza(new zzi(localzza.data, localzza.zzg));
        localzzk.zzc("cache-hit-parsed");
        if (!localzza.zzc())
        {
          this.zzk.zza(localzzk, localzzm);
        }
        else
        {
          localzzk.zzc("cache-hit-refresh-needed");
          localzzk.zza(localzza);
          localzzm.zzai = true;
          this.zzk.zza(localzzk, localzzm, new Runnable()
          {
            public void run()
            {
              try
              {
                zzc.zza(zzc.this).put(localzzk);
                return;
              }
              catch (InterruptedException localInterruptedException)
              {
                for (;;) {}
              }
            }
          });
        }
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */