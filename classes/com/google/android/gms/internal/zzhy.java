package com.google.android.gms.internal;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzp;
import java.math.BigInteger;
import java.util.Locale;

@zzgr
public final class zzhy
{
  private static String zzIk;
  private static final Object zzpy = new Object();
  
  public static String zza(Context paramContext, String paramString1, String paramString2)
  {
    synchronized (zzpy)
    {
      if ((zzIk == null) && (!TextUtils.isEmpty(paramString1))) {
        zzb(paramContext, paramString1, paramString2);
      }
      String str = zzIk;
      return str;
    }
  }
  
  private static void zzb(Context paramContext, String paramString1, String paramString2)
  {
    BigInteger localBigInteger2;
    try
    {
      ClassLoader localClassLoader = paramContext.createPackageContext(paramString2, 3).getClassLoader();
      Class localClass = Class.forName("com.google.ads.mediation.MediationAdapter", false, localClassLoader);
      BigInteger localBigInteger1 = new BigInteger(new byte[1]);
      String[] arrayOfString = paramString1.split(",");
      localBigInteger2 = localBigInteger1;
      int i = 0;
      while (i < arrayOfString.length)
      {
        if (zzp.zzbv().zza(localClassLoader, localClass, arrayOfString[i])) {
          localBigInteger2 = localBigInteger2.setBit(i);
        }
        i++;
        continue;
        return;
      }
    }
    catch (Throwable localThrowable)
    {
      zzIk = "err";
    }
    for (;;)
    {
      Locale localLocale = Locale.US;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = localBigInteger2;
      zzIk = String.format(localLocale, "%X", arrayOfObject);
    }
  }
  
  public static String zzgy()
  {
    synchronized (zzpy)
    {
      String str = zzIk;
      return str;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzhy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */