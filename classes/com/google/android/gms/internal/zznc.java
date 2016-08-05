package com.google.android.gms.internal;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.WorkSource;
import android.util.Log;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class zznc
{
  private static final Method zzaip = ;
  private static final Method zzaiq = zzqH();
  private static final Method zzair = zzqI();
  private static final Method zzais = zzqJ();
  private static final Method zzait = zzqK();
  
  public static int zza(WorkSource paramWorkSource)
  {
    if (zzair != null) {}
    for (;;)
    {
      try
      {
        int j = ((Integer)zzair.invoke(paramWorkSource, new Object[0])).intValue();
        i = j;
        return i;
      }
      catch (Exception localException)
      {
        Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", localException);
      }
      int i = 0;
    }
  }
  
  public static String zza(WorkSource paramWorkSource, int paramInt)
  {
    if (zzait != null) {}
    for (;;)
    {
      try
      {
        Method localMethod = zzait;
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = Integer.valueOf(paramInt);
        str = (String)localMethod.invoke(paramWorkSource, arrayOfObject);
        return str;
      }
      catch (Exception localException)
      {
        Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", localException);
      }
      String str = null;
    }
  }
  
  public static void zza(WorkSource paramWorkSource, int paramInt, String paramString)
  {
    if (zzaiq != null) {
      if (paramString == null) {
        paramString = "";
      }
    }
    for (;;)
    {
      try
      {
        Method localMethod2 = zzaiq;
        Object[] arrayOfObject2 = new Object[2];
        arrayOfObject2[0] = Integer.valueOf(paramInt);
        arrayOfObject2[1] = paramString;
        localMethod2.invoke(paramWorkSource, arrayOfObject2);
        return;
      }
      catch (Exception localException2)
      {
        Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", localException2);
        continue;
      }
      if (zzaip != null) {
        try
        {
          Method localMethod1 = zzaip;
          Object[] arrayOfObject1 = new Object[1];
          arrayOfObject1[0] = Integer.valueOf(paramInt);
          localMethod1.invoke(paramWorkSource, arrayOfObject1);
        }
        catch (Exception localException1)
        {
          Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", localException1);
        }
      }
    }
  }
  
  public static boolean zzar(Context paramContext)
  {
    if (paramContext.getPackageManager().checkPermission("android.permission.UPDATE_DEVICE_STATS", paramContext.getPackageName()) == 0) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public static List<String> zzb(WorkSource paramWorkSource)
  {
    int i = 0;
    int j;
    Object localObject;
    if (paramWorkSource == null)
    {
      j = 0;
      if (j != 0) {
        break label26;
      }
      localObject = Collections.EMPTY_LIST;
    }
    for (;;)
    {
      return (List<String>)localObject;
      j = zza(paramWorkSource);
      break;
      label26:
      localObject = new ArrayList();
      while (i < j)
      {
        String str = zza(paramWorkSource, i);
        if (!zznb.zzcA(str)) {
          ((List)localObject).add(str);
        }
        i++;
      }
    }
  }
  
  public static WorkSource zzf(int paramInt, String paramString)
  {
    WorkSource localWorkSource = new WorkSource();
    zza(localWorkSource, paramInt, paramString);
    return localWorkSource;
  }
  
  public static WorkSource zzm(Context paramContext, String paramString)
  {
    WorkSource localWorkSource = null;
    if ((paramContext == null) || (paramContext.getPackageManager() == null)) {}
    for (;;)
    {
      return localWorkSource;
      ApplicationInfo localApplicationInfo;
      try
      {
        localApplicationInfo = paramContext.getPackageManager().getApplicationInfo(paramString, 0);
        if (localApplicationInfo != null) {
          break label88;
        }
        Log.e("WorkSourceUtil", "Could not get applicationInfo from package: " + paramString);
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        Log.e("WorkSourceUtil", "Could not find package: " + paramString);
      }
      continue;
      label88:
      localWorkSource = zzf(localApplicationInfo.uid, paramString);
    }
  }
  
  private static Method zzqG()
  {
    Object localObject = null;
    try
    {
      Class[] arrayOfClass = new Class[1];
      arrayOfClass[0] = Integer.TYPE;
      Method localMethod = WorkSource.class.getMethod("add", arrayOfClass);
      localObject = localMethod;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    return (Method)localObject;
  }
  
  private static Method zzqH()
  {
    Object localObject = null;
    if (zzmx.zzqA()) {}
    try
    {
      Class[] arrayOfClass = new Class[2];
      arrayOfClass[0] = Integer.TYPE;
      arrayOfClass[1] = String.class;
      Method localMethod = WorkSource.class.getMethod("add", arrayOfClass);
      localObject = localMethod;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    return (Method)localObject;
  }
  
  private static Method zzqI()
  {
    Object localObject = null;
    try
    {
      Method localMethod = WorkSource.class.getMethod("size", new Class[0]);
      localObject = localMethod;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    return (Method)localObject;
  }
  
  private static Method zzqJ()
  {
    Object localObject = null;
    try
    {
      Class[] arrayOfClass = new Class[1];
      arrayOfClass[0] = Integer.TYPE;
      Method localMethod = WorkSource.class.getMethod("get", arrayOfClass);
      localObject = localMethod;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    return (Method)localObject;
  }
  
  private static Method zzqK()
  {
    Object localObject = null;
    if (zzmx.zzqA()) {}
    try
    {
      Class[] arrayOfClass = new Class[1];
      arrayOfClass[0] = Integer.TYPE;
      Method localMethod = WorkSource.class.getMethod("getName", arrayOfClass);
      localObject = localMethod;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    return (Method)localObject;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zznc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */