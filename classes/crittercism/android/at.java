package crittercism.android;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import com.crittercism.app.CrittercismConfig;

public final class at
{
  public String a = "1.0";
  public int b = 0;
  
  public at(Context paramContext, CrittercismConfig paramCrittercismConfig)
  {
    try
    {
      PackageInfo localPackageInfo = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0);
      this.a = localPackageInfo.versionName;
      this.b = localPackageInfo.versionCode;
      String str = paramCrittercismConfig.getCustomVersionName();
      if ((str != null) && (str.length() > 0)) {
        this.a = str;
      }
      if (paramCrittercismConfig.isVersionCodeToBeIncludedInVersionString()) {
        this.a = (this.a + "-" + Integer.toString(this.b));
      }
      return;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;) {}
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/crittercism/android/at.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */