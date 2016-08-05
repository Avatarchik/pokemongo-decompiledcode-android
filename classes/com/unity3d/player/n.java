package com.unity3d.player;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PermissionInfo;
import android.os.Bundle;
import java.util.LinkedList;
import java.util.List;

public final class n
  implements i
{
  private static boolean a(PackageItemInfo paramPackageItemInfo)
  {
    try
    {
      boolean bool2 = paramPackageItemInfo.metaData.getBoolean("unityplayer.SkipPermissionsDialog");
      bool1 = bool2;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        boolean bool1 = false;
      }
    }
    return bool1;
  }
  
  public final void a(Activity paramActivity, final Runnable paramRunnable)
  {
    int i = 0;
    if (paramActivity == null) {}
    PackageManager localPackageManager;
    for (;;)
    {
      return;
      localPackageManager = paramActivity.getPackageManager();
      try
      {
        ActivityInfo localActivityInfo = localPackageManager.getActivityInfo(paramActivity.getComponentName(), 128);
        ApplicationInfo localApplicationInfo = localPackageManager.getApplicationInfo(paramActivity.getPackageName(), 128);
        if ((a(localActivityInfo)) || (a(localApplicationInfo))) {
          paramRunnable.run();
        }
      }
      catch (Exception localException1) {}
    }
    for (;;)
    {
      final LinkedList localLinkedList;
      try
      {
        PackageInfo localPackageInfo = localPackageManager.getPackageInfo(paramActivity.getPackageName(), 4096);
        if (localPackageInfo.requestedPermissions == null) {
          localPackageInfo.requestedPermissions = new String[0];
        }
        localLinkedList = new LinkedList();
        String[] arrayOfString = localPackageInfo.requestedPermissions;
        int j = arrayOfString.length;
        if (i < j)
        {
          String str = arrayOfString[i];
          try
          {
            if ((localPackageManager.getPermissionInfo(str, 128).protectionLevel != 1) || (paramActivity.checkCallingOrSelfPermission(str) == 0)) {
              break label326;
            }
            localLinkedList.add(str);
          }
          catch (PackageManager.NameNotFoundException localNameNotFoundException)
          {
            m.Log(5, "Failed to get permission info for " + str + ", manifest likely missing custom permission declaration");
            m.Log(5, "Permission " + str + " ignored");
          }
        }
      }
      catch (Exception localException2)
      {
        m.Log(6, "Unable to query for permission: " + localException2.getMessage());
      }
      if (localLinkedList.isEmpty())
      {
        paramRunnable.run();
        break;
      }
      final FragmentManager localFragmentManager = paramActivity.getFragmentManager();
      Fragment local1 = new Fragment()
      {
        public final void onRequestPermissionsResult(int paramAnonymousInt, String[] paramAnonymousArrayOfString, int[] paramAnonymousArrayOfInt)
        {
          if (paramAnonymousInt != 15881) {}
          for (;;)
          {
            return;
            int i = 0;
            if ((i < paramAnonymousArrayOfString.length) && (i < paramAnonymousArrayOfInt.length))
            {
              StringBuilder localStringBuilder = new StringBuilder().append(paramAnonymousArrayOfString[i]);
              if (paramAnonymousArrayOfInt[i] == 0) {}
              for (String str = " granted";; str = " denied")
              {
                m.Log(4, str);
                i++;
                break;
              }
            }
            FragmentTransaction localFragmentTransaction = localFragmentManager.beginTransaction();
            localFragmentTransaction.remove(this);
            localFragmentTransaction.commit();
            paramRunnable.run();
          }
        }
        
        public final void onStart()
        {
          super.onStart();
          requestPermissions((String[])localLinkedList.toArray(new String[0]), 15881);
        }
      };
      FragmentTransaction localFragmentTransaction = localFragmentManager.beginTransaction();
      localFragmentTransaction.add(0, local1);
      localFragmentTransaction.commit();
      break;
      label326:
      i++;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/unity3d/player/n.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */