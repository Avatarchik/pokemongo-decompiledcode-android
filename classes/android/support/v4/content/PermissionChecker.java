package android.support.v4.content;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.Process;
import android.support.annotation.NonNull;
import android.support.v4.app.AppOpsManagerCompat;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class PermissionChecker
{
  public static final int PERMISSION_DENIED = -1;
  public static final int PERMISSION_DENIED_APP_OP = -2;
  public static final int PERMISSION_GRANTED;
  
  public static int checkCallingOrSelfPermission(@NonNull Context paramContext, @NonNull String paramString)
  {
    if (Binder.getCallingPid() == Process.myPid()) {}
    for (String str = paramContext.getPackageName();; str = null) {
      return checkPermission(paramContext, paramString, Binder.getCallingPid(), Binder.getCallingUid(), str);
    }
  }
  
  public static int checkCallingPermission(@NonNull Context paramContext, @NonNull String paramString1, String paramString2)
  {
    if (Binder.getCallingPid() == Process.myPid()) {}
    for (int i = -1;; i = checkPermission(paramContext, paramString1, Binder.getCallingPid(), Binder.getCallingUid(), paramString2)) {
      return i;
    }
  }
  
  public static int checkPermission(@NonNull Context paramContext, @NonNull String paramString1, int paramInt1, int paramInt2, String paramString2)
  {
    int i = -1;
    if (paramContext.checkPermission(paramString1, paramInt1, paramInt2) == i) {}
    for (;;)
    {
      return i;
      String str = AppOpsManagerCompat.permissionToOp(paramString1);
      if (str == null)
      {
        i = 0;
      }
      else if (paramString2 == null)
      {
        String[] arrayOfString = paramContext.getPackageManager().getPackagesForUid(paramInt2);
        if ((arrayOfString != null) && (arrayOfString.length > 0)) {
          paramString2 = arrayOfString[0];
        }
      }
      else if (AppOpsManagerCompat.noteProxyOp(paramContext, str, paramString2) != 0)
      {
        i = -2;
      }
      else
      {
        i = 0;
      }
    }
  }
  
  public static int checkSelfPermission(@NonNull Context paramContext, @NonNull String paramString)
  {
    return checkPermission(paramContext, paramString, Process.myPid(), Process.myUid(), paramContext.getPackageName());
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface PermissionResult {}
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/android/support/v4/content/PermissionChecker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */