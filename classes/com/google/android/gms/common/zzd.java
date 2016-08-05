package com.google.android.gms.common;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;
import java.util.Set;

public class zzd
{
  private static final zzd zzaas = new zzd();
  
  private boolean zza(PackageInfo paramPackageInfo, boolean paramBoolean)
  {
    boolean bool;
    if (paramPackageInfo.signatures.length != 1)
    {
      Log.w("GoogleSignatureVerifier", "Package has more than one signature.");
      bool = false;
    }
    for (;;)
    {
      return bool;
      zzc.zzb localzzb = new zzc.zzb(paramPackageInfo.signatures[0].toByteArray());
      if (paramBoolean) {}
      for (Set localSet = zzc.zznp();; localSet = zzc.zznq())
      {
        if (!localSet.contains(localzzb)) {
          break label74;
        }
        bool = true;
        break;
      }
      label74:
      if (Log.isLoggable("GoogleSignatureVerifier", 2)) {
        Log.v("GoogleSignatureVerifier", "Signature not valid.  Found: \n" + Base64.encodeToString(localzzb.getBytes(), 0));
      }
      bool = false;
    }
  }
  
  public static zzd zznu()
  {
    return zzaas;
  }
  
  zzc.zza zza(PackageInfo paramPackageInfo, zzc.zza... paramVarArgs)
  {
    zzc.zza localzza;
    if (paramPackageInfo.signatures.length != 1)
    {
      Log.w("GoogleSignatureVerifier", "Package has more than one signature.");
      localzza = null;
    }
    for (;;)
    {
      return localzza;
      zzc.zzb localzzb = new zzc.zzb(paramPackageInfo.signatures[0].toByteArray());
      for (int i = 0;; i++)
      {
        if (i >= paramVarArgs.length) {
          break label76;
        }
        if (paramVarArgs[i].equals(localzzb))
        {
          localzza = paramVarArgs[i];
          break;
        }
      }
      label76:
      if (Log.isLoggable("GoogleSignatureVerifier", 2)) {
        Log.v("GoogleSignatureVerifier", "Signature not valid.  Found: \n" + Base64.encodeToString(localzzb.getBytes(), 0));
      }
      localzza = null;
    }
  }
  
  public boolean zza(PackageManager paramPackageManager, PackageInfo paramPackageInfo)
  {
    boolean bool = false;
    if (paramPackageInfo == null) {}
    for (;;)
    {
      return bool;
      if (GooglePlayServicesUtil.zzc(paramPackageManager))
      {
        bool = zza(paramPackageInfo, true);
      }
      else
      {
        bool = zza(paramPackageInfo, false);
        if ((!bool) && (zza(paramPackageInfo, true))) {
          Log.w("GoogleSignatureVerifier", "Test-keys aren't accepted on this build.");
        }
      }
    }
  }
  
  public boolean zzb(PackageManager paramPackageManager, String paramString)
  {
    try
    {
      PackageInfo localPackageInfo = paramPackageManager.getPackageInfo(paramString, 64);
      bool = zza(paramPackageManager, localPackageInfo);
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;)
      {
        if (Log.isLoggable("GoogleSignatureVerifier", 3)) {
          Log.d("GoogleSignatureVerifier", "Package manager can't find package " + paramString + ", defaulting to false");
        }
        boolean bool = false;
      }
    }
    return bool;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/common/zzd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */