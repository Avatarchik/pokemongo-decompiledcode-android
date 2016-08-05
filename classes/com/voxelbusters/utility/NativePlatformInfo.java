package com.voxelbusters.utility;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;
import com.voxelbusters.common.Configuration;

public class NativePlatformInfo
{
  private static PackageInfo getPackageInfo()
  {
    Object localObject = null;
    Context localContext = Configuration.getContext().getApplicationContext();
    PackageManager localPackageManager = localContext.getPackageManager();
    try
    {
      PackageInfo localPackageInfo = localPackageManager.getPackageInfo(localContext.getPackageName(), 0);
      localObject = localPackageInfo;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;)
      {
        localNameNotFoundException.printStackTrace();
      }
    }
    return (PackageInfo)localObject;
  }
  
  public static String getPackageName()
  {
    String str = null;
    PackageInfo localPackageInfo = getPackageInfo();
    Log.e("Utility", localPackageInfo.toString());
    if (localPackageInfo != null) {
      str = localPackageInfo.packageName;
    }
    return str;
  }
  
  public static String getPackageVersionName()
  {
    String str = null;
    PackageInfo localPackageInfo = getPackageInfo();
    if (localPackageInfo != null) {
      str = localPackageInfo.versionName;
    }
    return str;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/voxelbusters/utility/NativePlatformInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */