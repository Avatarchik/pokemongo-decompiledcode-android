package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.google.android.gms.common.internal.zzx;

public final class zzlm<L>
{
  private volatile L mListener;
  private final zzlm<L>.zza zzacG;
  
  public zzlm(Looper paramLooper, L paramL)
  {
    this.zzacG = new zza(paramLooper);
    this.mListener = zzx.zzb(paramL, "Listener must not be null");
  }
  
  public void clear()
  {
    this.mListener = null;
  }
  
  public void zza(zzb<? super L> paramzzb)
  {
    zzx.zzb(paramzzb, "Notifier must not be null");
    Message localMessage = this.zzacG.obtainMessage(1, paramzzb);
    this.zzacG.sendMessage(localMessage);
  }
  
  void zzb(zzb<? super L> paramzzb)
  {
    Object localObject = this.mListener;
    if (localObject == null) {
      paramzzb.zznN();
    }
    for (;;)
    {
      return;
      try
      {
        paramzzb.zzq(localObject);
      }
      catch (RuntimeException localRuntimeException)
      {
        paramzzb.zznN();
        throw localRuntimeException;
      }
    }
  }
  
  private final class zza
    extends Handler
  {
    public zza(Looper paramLooper)
    {
      super();
    }
    
    public void handleMessage(Message paramMessage)
    {
      int i = 1;
      if (paramMessage.what == i) {}
      for (;;)
      {
        zzx.zzaa(i);
        zzlm.this.zzb((zzlm.zzb)paramMessage.obj);
        return;
        int j = 0;
      }
    }
  }
  
  public static abstract interface zzb<L>
  {
    public abstract void zznN();
    
    public abstract void zzq(L paramL);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzlm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */