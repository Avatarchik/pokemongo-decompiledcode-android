package crittercism.android;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import com.crittercism.app.CrittercismConfig;

public final class bf
{
  public boolean a;
  public boolean b;
  public boolean c;
  
  public bf(Context paramContext, CrittercismConfig paramCrittercismConfig)
  {
    if (paramCrittercismConfig.isLogcatReportingEnabled()) {
      if (Build.VERSION.SDK_INT < 16) {}
    }
    for (;;)
    {
      this.a = bool;
      this.c = a("android.permission.ACCESS_NETWORK_STATE", paramContext);
      this.b = a("android.permission.GET_TASKS", paramContext);
      return;
      if (!a("android.permission.READ_LOGS", paramContext)) {
        bool = false;
      }
    }
  }
  
  private static boolean a(String paramString, Context paramContext)
  {
    if (paramContext.getPackageManager().checkPermission(paramString, paramContext.getPackageName()) == 0) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/crittercism/android/bf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */