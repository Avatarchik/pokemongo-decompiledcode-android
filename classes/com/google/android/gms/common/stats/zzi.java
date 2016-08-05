package com.google.android.gms.common.stats;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.internal.zzlr;
import com.google.android.gms.internal.zzmm;
import com.google.android.gms.internal.zzmr;
import java.util.List;

public class zzi
{
  private static String TAG = "WakeLockTracker";
  private static Integer zzahE;
  private static zzi zzaii = new zzi();
  
  private static int getLogLevel()
  {
    int i;
    try
    {
      if (zzmm.zzjA()) {
        i = ((Integer)zzc.zzb.zzahH.get()).intValue();
      } else {
        i = zzd.LOG_LEVEL_OFF;
      }
    }
    catch (SecurityException localSecurityException)
    {
      i = zzd.LOG_LEVEL_OFF;
    }
    return i;
  }
  
  private static boolean zzam(Context paramContext)
  {
    if (zzahE == null) {
      zzahE = Integer.valueOf(getLogLevel());
    }
    if (zzahE.intValue() != zzd.LOG_LEVEL_OFF) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public static zzi zzqr()
  {
    return zzaii;
  }
  
  public void zza(Context paramContext, String paramString1, int paramInt1, String paramString2, String paramString3, int paramInt2, List<String> paramList)
  {
    zza(paramContext, paramString1, paramInt1, paramString2, paramString3, paramInt2, paramList, 0L);
  }
  
  public void zza(Context paramContext, String paramString1, int paramInt1, String paramString2, String paramString3, int paramInt2, List<String> paramList, long paramLong)
  {
    if (!zzam(paramContext)) {}
    for (;;)
    {
      return;
      if (TextUtils.isEmpty(paramString1))
      {
        Log.e(TAG, "missing wakeLock key. " + paramString1);
      }
      else
      {
        long l = System.currentTimeMillis();
        if ((7 == paramInt1) || (8 == paramInt1) || (10 == paramInt1) || (11 == paramInt1))
        {
          WakeLockEvent localWakeLockEvent = new WakeLockEvent(l, paramInt1, paramString2, paramInt2, paramList, paramString1, SystemClock.elapsedRealtime(), zzmr.zzao(paramContext), paramString3, paramContext.getPackageName(), zzmr.zzap(paramContext), paramLong);
          try
          {
            paramContext.startService(new Intent().setComponent(zzd.zzahN).putExtra("com.google.android.gms.common.stats.EXTRA_LOG_EVENT", localWakeLockEvent));
          }
          catch (Exception localException)
          {
            Log.wtf(TAG, localException);
          }
        }
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/common/stats/zzi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */