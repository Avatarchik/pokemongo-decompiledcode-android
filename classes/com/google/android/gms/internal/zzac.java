package com.google.android.gms.internal;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.http.AndroidHttpClient;
import android.os.Build.VERSION;
import java.io.File;

public class zzac
{
  public static zzl zza(Context paramContext)
  {
    return zza(paramContext, null);
  }
  
  public static zzl zza(Context paramContext, zzy paramzzy)
  {
    File localFile = new File(paramContext.getCacheDir(), "volley");
    Object localObject = "volley/0";
    try
    {
      String str1 = paramContext.getPackageName();
      PackageInfo localPackageInfo = paramContext.getPackageManager().getPackageInfo(str1, 0);
      String str2 = str1 + "/" + localPackageInfo.versionCode;
      localObject = str2;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      label125:
      for (;;) {}
    }
    if (paramzzy == null) {
      if (Build.VERSION.SDK_INT < 9) {
        break label125;
      }
    }
    for (paramzzy = new zzz();; paramzzy = new zzw(AndroidHttpClient.newInstance((String)localObject)))
    {
      zzt localzzt = new zzt(paramzzy);
      zzl localzzl = new zzl(new zzv(localFile), localzzt);
      localzzl.start();
      return localzzl;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzac.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */