package com.google.android.gms.internal;

import android.os.Handler;
import java.util.concurrent.Executor;

public class zze
  implements zzn
{
  private final Executor zzs;
  
  public zze(final Handler paramHandler)
  {
    this.zzs = new Executor()
    {
      public void execute(Runnable paramAnonymousRunnable)
      {
        paramHandler.post(paramAnonymousRunnable);
      }
    };
  }
  
  public void zza(zzk<?> paramzzk, zzm<?> paramzzm)
  {
    zza(paramzzk, paramzzm, null);
  }
  
  public void zza(zzk<?> paramzzk, zzm<?> paramzzm, Runnable paramRunnable)
  {
    paramzzk.zzv();
    paramzzk.zzc("post-response");
    this.zzs.execute(new zza(paramzzk, paramzzm, paramRunnable));
  }
  
  public void zza(zzk<?> paramzzk, zzr paramzzr)
  {
    paramzzk.zzc("post-error");
    zzm localzzm = zzm.zzd(paramzzr);
    this.zzs.execute(new zza(paramzzk, localzzm, null));
  }
  
  private class zza
    implements Runnable
  {
    private final zzk zzv;
    private final zzm zzw;
    private final Runnable zzx;
    
    public zza(zzk paramzzk, zzm paramzzm, Runnable paramRunnable)
    {
      this.zzv = paramzzk;
      this.zzw = paramzzm;
      this.zzx = paramRunnable;
    }
    
    public void run()
    {
      if (this.zzv.isCanceled()) {
        this.zzv.zzd("canceled-at-delivery");
      }
      label44:
      label99:
      label109:
      for (;;)
      {
        return;
        if (this.zzw.isSuccess())
        {
          this.zzv.zza(this.zzw.result);
          if (!this.zzw.zzai) {
            break label99;
          }
          this.zzv.zzc("intermediate-response");
        }
        for (;;)
        {
          if (this.zzx == null) {
            break label109;
          }
          this.zzx.run();
          break;
          this.zzv.zzc(this.zzw.zzah);
          break label44;
          this.zzv.zzd("done");
        }
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zze.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */