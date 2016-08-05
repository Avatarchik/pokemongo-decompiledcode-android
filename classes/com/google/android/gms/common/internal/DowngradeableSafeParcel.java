package com.google.android.gms.common.internal;

import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.lang.reflect.Field;

public abstract class DowngradeableSafeParcel
  implements SafeParcelable
{
  private static final Object zzafm = new Object();
  private static ClassLoader zzafn = null;
  private static Integer zzafo = null;
  private boolean zzafp = false;
  
  private static boolean zza(Class<?> paramClass)
  {
    boolean bool1 = false;
    try
    {
      boolean bool2 = "SAFE_PARCELABLE_NULL_STRING".equals(paramClass.getField("NULL").get(null));
      bool1 = bool2;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      for (;;) {}
    }
    catch (NoSuchFieldException localNoSuchFieldException)
    {
      for (;;) {}
    }
    return bool1;
  }
  
  protected static boolean zzck(String paramString)
  {
    ClassLoader localClassLoader = zzoS();
    boolean bool1;
    if (localClassLoader == null) {
      bool1 = true;
    }
    for (;;)
    {
      return bool1;
      try
      {
        boolean bool2 = zza(localClassLoader.loadClass(paramString));
        bool1 = bool2;
      }
      catch (Exception localException)
      {
        bool1 = false;
      }
    }
  }
  
  protected static ClassLoader zzoS()
  {
    synchronized (zzafm)
    {
      ClassLoader localClassLoader = zzafn;
      return localClassLoader;
    }
  }
  
  protected static Integer zzoT()
  {
    synchronized (zzafm)
    {
      Integer localInteger = zzafo;
      return localInteger;
    }
  }
  
  protected boolean zzoU()
  {
    return this.zzafp;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/common/internal/DowngradeableSafeParcel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */