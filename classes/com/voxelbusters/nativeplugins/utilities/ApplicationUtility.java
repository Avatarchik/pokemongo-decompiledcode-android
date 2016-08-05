package com.voxelbusters.nativeplugins.utilities;

import android.app.Activity;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.os.Environment;
import com.google.android.gms.common.GooglePlayServicesUtil;
import java.io.File;
import java.util.List;

public class ApplicationUtility
{
  static final int PLAY_SERVICES_RESOLUTION_REQUEST = 100000;
  
  public static Class<?> GetMainLauncherActivity(Context paramContext)
  {
    String str1 = paramContext.getPackageName();
    String str2 = paramContext.getPackageManager().getLaunchIntentForPackage(str1).getComponent().getClassName();
    try
    {
      Class localClass2 = Class.forName(str2);
      localClass1 = localClass2;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      for (;;)
      {
        localClassNotFoundException.printStackTrace();
        Class localClass1 = null;
      }
    }
    return localClass1;
  }
  
  public static Context getApplicationContext(Context paramContext)
  {
    return paramContext.getApplicationContext();
  }
  
  public static ApplicationInfo getApplicationInfo(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    Object localObject = null;
    try
    {
      ApplicationInfo localApplicationInfo = localPackageManager.getApplicationInfo(getPackageName(paramContext), 0);
      localObject = localApplicationInfo;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;)
      {
        localNameNotFoundException.printStackTrace();
        Debug.error("NativePlugins.ApplicationUtility", "Package name not found!");
      }
    }
    return (ApplicationInfo)localObject;
  }
  
  public static String getApplicationName(Context paramContext)
  {
    ApplicationInfo localApplicationInfo = getApplicationInfo(paramContext);
    return paramContext.getPackageManager().getApplicationLabel(localApplicationInfo).toString();
  }
  
  public static File getExternalTempDirectoryIfExists(Context paramContext, String paramString)
  {
    if (hasExternalStorageWritable(paramContext)) {}
    for (File localFile = getSaveDirectory(paramContext, paramString, paramContext.getApplicationContext().getExternalCacheDir());; localFile = getLocalSaveDirectory(paramContext, paramString)) {
      return localFile;
    }
  }
  
  public static String getFileProviderAuthoityName(Context paramContext)
  {
    return getPackageName(paramContext) + ".fileprovider";
  }
  
  public static File getLocalSaveDirectory(Context paramContext, String paramString)
  {
    return getSaveDirectory(paramContext, paramString, paramContext.getApplicationContext().getFilesDir());
  }
  
  public static String getPackageName(Context paramContext)
  {
    return paramContext.getPackageName();
  }
  
  public static int getResourceId(Context paramContext, String paramString1, String paramString2)
  {
    String str1 = StringUtility.getFileNameWithoutExtension(paramString1);
    String str2 = getPackageName(paramContext);
    return paramContext.getResources().getIdentifier(str1, paramString2, str2);
  }
  
  static File getSaveDirectory(Context paramContext, String paramString, File paramFile)
  {
    if (StringUtility.isNullOrEmpty(paramString)) {
      paramString = getApplicationName(paramContext);
    }
    File localFile = new File(paramFile, paramString);
    localFile.mkdirs();
    return localFile;
  }
  
  public static String getString(Context paramContext, int paramInt)
  {
    return paramContext.getResources().getString(paramInt);
  }
  
  public static Object getSystemService(Context paramContext, String paramString)
  {
    return paramContext.getSystemService(paramString);
  }
  
  public static boolean hasExternalStorageWritable(Context paramContext)
  {
    if (Environment.getExternalStorageState().equals("mounted")) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public static boolean hasPermission(Context paramContext, String paramString)
  {
    if (paramContext.getPackageManager().checkPermission(paramString, getPackageName(paramContext)) == 0) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public static boolean isGooglePlayServicesAvailable(Context paramContext)
  {
    int i = GooglePlayServicesUtil.isGooglePlayServicesAvailable(paramContext);
    if (i != 0) {
      if (GooglePlayServicesUtil.isUserRecoverableError(i)) {
        GooglePlayServicesUtil.getErrorDialog(i, (Activity)paramContext, 100000).show();
      }
    }
    for (boolean bool = false;; bool = true)
    {
      return bool;
      Debug.error("NativePlugins.ApplicationUtility", "This device does not support Google Play Services.");
      break;
    }
  }
  
  public static boolean isIntentAvailable(Context paramContext, Intent paramIntent)
  {
    if (paramContext.getPackageManager().queryIntentActivities(paramIntent, 65536).size() > 0) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public static boolean isIntentAvailable(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    Intent localIntent = new Intent(paramString1);
    localIntent.setType(paramString2);
    if (paramString3 != null) {
      localIntent.setPackage(paramString3);
    }
    return isIntentAvailable(paramContext, localIntent);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/voxelbusters/nativeplugins/utilities/ApplicationUtility.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */