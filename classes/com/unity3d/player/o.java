package com.unity3d.player;

import android.app.Activity;
import android.content.ComponentName;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;

public final class o
{
  private final Bundle a;
  
  public o(Activity paramActivity)
  {
    Bundle localBundle = Bundle.EMPTY;
    PackageManager localPackageManager = paramActivity.getPackageManager();
    ComponentName localComponentName = paramActivity.getComponentName();
    try
    {
      ActivityInfo localActivityInfo = localPackageManager.getActivityInfo(localComponentName, 128);
      if ((localActivityInfo != null) && (localActivityInfo.metaData != null)) {
        localBundle = localActivityInfo.metaData;
      }
      this.a = new Bundle(localBundle);
      return;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;)
      {
        m.Log(6, "Unable to retreive meta data for activity '" + localComponentName + "'");
      }
    }
  }
  
  private static String a(String paramString)
  {
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = "unityplayer";
    arrayOfObject[1] = paramString;
    return String.format("%s.%s", arrayOfObject);
  }
  
  public final boolean a()
  {
    return this.a.getBoolean(a("ForwardNativeEventsToDalvik"));
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/unity3d/player/o.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */