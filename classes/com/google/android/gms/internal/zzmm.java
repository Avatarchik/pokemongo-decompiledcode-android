package com.google.android.gms.internal;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Process;
import com.google.android.gms.common.internal.zzd;

public class zzmm
{
  public static boolean zzjA()
  {
    if ((zzd.zzaeK) && (zzlr.isInitialized()) && (zzlr.zzoo() == Process.myUid())) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public static boolean zzl(Context paramContext, String paramString)
  {
    boolean bool = false;
    PackageManager localPackageManager = paramContext.getPackageManager();
    try
    {
      int i = localPackageManager.getApplicationInfo(paramString, 0).flags;
      if ((i & 0x200000) != 0) {
        bool = true;
      }
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;) {}
    }
    return bool;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzmm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */