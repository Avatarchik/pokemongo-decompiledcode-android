package com.google.android.gms.internal;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.PowerManager;

public final class zzmr
{
  private static IntentFilter zzail = new IntentFilter("android.intent.action.BATTERY_CHANGED");
  
  public static int zzao(Context paramContext)
  {
    int i = 1;
    int j;
    if ((paramContext == null) || (paramContext.getApplicationContext() == null))
    {
      j = -1;
      return j;
    }
    Intent localIntent = paramContext.getApplicationContext().registerReceiver(null, zzail);
    int k;
    label37:
    int m;
    label48:
    boolean bool;
    label68:
    int n;
    label76:
    int i1;
    if (localIntent == null)
    {
      k = 0;
      if ((k & 0x7) == 0) {
        break label107;
      }
      m = i;
      if (!zzmx.zzqC()) {
        break label113;
      }
      bool = ((PowerManager)paramContext.getSystemService("power")).isInteractive();
      if (!bool) {
        break label130;
      }
      n = i;
      i1 = n << 1;
      if (m == 0) {
        break label136;
      }
    }
    for (;;)
    {
      j = i1 | i;
      break;
      k = localIntent.getIntExtra("plugged", 0);
      break label37;
      label107:
      m = 0;
      break label48;
      label113:
      bool = ((PowerManager)paramContext.getSystemService("power")).isScreenOn();
      break label68;
      label130:
      n = 0;
      break label76;
      label136:
      i = 0;
    }
  }
  
  public static float zzap(Context paramContext)
  {
    Intent localIntent = paramContext.getApplicationContext().registerReceiver(null, zzail);
    float f = NaN.0F;
    if (localIntent != null)
    {
      int i = localIntent.getIntExtra("level", -1);
      int j = localIntent.getIntExtra("scale", -1);
      f = i / j;
    }
    return f;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzmr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */