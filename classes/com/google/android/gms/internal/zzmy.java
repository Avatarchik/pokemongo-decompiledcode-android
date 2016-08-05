package com.google.android.gms.internal;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.os.Binder;
import java.util.Iterator;
import java.util.List;

public class zzmy
{
  private static String zza(StackTraceElement[] paramArrayOfStackTraceElement, int paramInt)
  {
    if (paramInt + 4 >= paramArrayOfStackTraceElement.length) {}
    StackTraceElement localStackTraceElement;
    for (String str = "<bottom of call stack>";; str = localStackTraceElement.getClassName() + "." + localStackTraceElement.getMethodName() + ":" + localStackTraceElement.getLineNumber())
    {
      return str;
      localStackTraceElement = paramArrayOfStackTraceElement[(paramInt + 4)];
    }
  }
  
  public static String zzaq(Context paramContext)
  {
    return zzj(paramContext, Binder.getCallingPid());
  }
  
  public static String zzj(Context paramContext, int paramInt)
  {
    List localList = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses();
    ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo;
    if (localList != null)
    {
      Iterator localIterator = localList.iterator();
      do
      {
        if (!localIterator.hasNext()) {
          break;
        }
        localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)localIterator.next();
      } while (localRunningAppProcessInfo.pid != paramInt);
    }
    for (String str = localRunningAppProcessInfo.processName;; str = null) {
      return str;
    }
  }
  
  public static String zzl(int paramInt1, int paramInt2)
  {
    StackTraceElement[] arrayOfStackTraceElement = Thread.currentThread().getStackTrace();
    StringBuffer localStringBuffer = new StringBuffer();
    int i = paramInt2 + paramInt1;
    while (paramInt1 < i)
    {
      localStringBuffer.append(zza(arrayOfStackTraceElement, paramInt1)).append(" ");
      paramInt1++;
    }
    return localStringBuffer.toString();
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzmy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */