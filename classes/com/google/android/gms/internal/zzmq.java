package com.google.android.gms.internal;

import android.content.res.Configuration;
import android.content.res.Resources;

public final class zzmq
{
  public static boolean zzb(Resources paramResources)
  {
    boolean bool = false;
    if (paramResources == null) {}
    label49:
    for (;;)
    {
      return bool;
      if ((0xF & paramResources.getConfiguration().screenLayout) > 3) {}
      for (int i = 1;; i = 0)
      {
        if (((!zzmx.zzqu()) || (i == 0)) && (!zzc(paramResources))) {
          break label49;
        }
        bool = true;
        break;
      }
    }
  }
  
  private static boolean zzc(Resources paramResources)
  {
    boolean bool = false;
    Configuration localConfiguration = paramResources.getConfiguration();
    if ((zzmx.zzqw()) && ((0xF & localConfiguration.screenLayout) <= 3) && (localConfiguration.smallestScreenWidthDp >= 600)) {
      bool = true;
    }
    return bool;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzmq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */