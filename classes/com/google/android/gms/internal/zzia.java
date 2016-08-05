package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.google.android.gms.ads.internal.zzp;

@zzgr
public class zzia
  extends Handler
{
  public zzia(Looper paramLooper)
  {
    super(paramLooper);
  }
  
  public void handleMessage(Message paramMessage)
  {
    try
    {
      super.handleMessage(paramMessage);
      return;
    }
    catch (Exception localException)
    {
      zzp.zzby().zzc(localException, false);
      throw localException;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzia.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */