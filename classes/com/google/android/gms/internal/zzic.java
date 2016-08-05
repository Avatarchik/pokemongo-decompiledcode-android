package com.google.android.gms.internal;

import android.os.Process;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzp;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

@zzgr
public final class zzic
{
  private static final ExecutorService zzIr = Executors.newFixedThreadPool(10, zzay("Default"));
  private static final ExecutorService zzIs = Executors.newFixedThreadPool(5, zzay("Loader"));
  
  public static zziq<Void> zza(int paramInt, Runnable paramRunnable)
  {
    if (paramInt == 1) {}
    for (zziq localzziq = zza(zzIs, new Callable()
        {
          public Void zzgA()
          {
            zzic.this.run();
            return null;
          }
        });; localzziq = zza(zzIr, new Callable()
        {
          public Void zzgA()
          {
            zzic.this.run();
            return null;
          }
        })) {
      return localzziq;
    }
  }
  
  public static zziq<Void> zza(Runnable paramRunnable)
  {
    return zza(0, paramRunnable);
  }
  
  public static <T> zziq<T> zza(Callable<T> paramCallable)
  {
    return zza(zzIr, paramCallable);
  }
  
  public static <T> zziq<T> zza(ExecutorService paramExecutorService, final Callable<T> paramCallable)
  {
    zzin localzzin = new zzin();
    try
    {
      localzzin.zzd(new Runnable()
      {
        public void run()
        {
          try
          {
            Process.setThreadPriority(10);
            zzic.this.zzf(paramCallable.call());
            return;
          }
          catch (Exception localException)
          {
            for (;;)
            {
              zzp.zzby().zzc(localException, true);
              zzic.this.cancel(true);
            }
          }
        }
      }
      {
        public void run()
        {
          if (zzic.this.isCancelled()) {
            this.zzIv.cancel(true);
          }
        }
      });
      return localzzin;
    }
    catch (RejectedExecutionException localRejectedExecutionException)
    {
      for (;;)
      {
        zzb.zzd("Thread execution is rejected.", localRejectedExecutionException);
        localzzin.cancel(true);
      }
    }
  }
  
  private static ThreadFactory zzay(String paramString)
  {
    new ThreadFactory()
    {
      private final AtomicInteger zzIw = new AtomicInteger(1);
      
      public Thread newThread(Runnable paramAnonymousRunnable)
      {
        return new Thread(paramAnonymousRunnable, "AdWorker(" + zzic.this + ") #" + this.zzIw.getAndIncrement());
      }
    };
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzic.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */