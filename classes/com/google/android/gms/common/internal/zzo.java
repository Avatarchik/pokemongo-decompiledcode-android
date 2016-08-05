package com.google.android.gms.common.internal;

import android.content.Context;
import android.util.Log;
import com.google.android.gms.internal.zzqc;

public final class zzo
{
  public static final int zzagk = 23 - " PII_LOG".length();
  private static final String zzagl = null;
  private final String zzagm;
  private final String zzagn;
  
  public zzo(String paramString)
  {
    this(paramString, zzagl);
  }
  
  public zzo(String paramString1, String paramString2)
  {
    zzx.zzb(paramString1, "log tag cannot be null");
    boolean bool;
    if (paramString1.length() <= 23)
    {
      bool = true;
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = paramString1;
      arrayOfObject[1] = Integer.valueOf(23);
      zzx.zzb(bool, "tag \"%s\" is longer than the %d character maximum", arrayOfObject);
      this.zzagm = paramString1;
      if ((paramString2 != null) && (paramString2.length() > 0)) {
        break label82;
      }
    }
    label82:
    for (this.zzagn = zzagl;; this.zzagn = paramString2)
    {
      return;
      bool = false;
      break;
    }
  }
  
  private String zzcp(String paramString)
  {
    if (this.zzagn == null) {}
    for (;;)
    {
      return paramString;
      paramString = this.zzagn.concat(paramString);
    }
  }
  
  public void zza(Context paramContext, String paramString1, String paramString2, Throwable paramThrowable)
  {
    StackTraceElement[] arrayOfStackTraceElement = paramThrowable.getStackTrace();
    StringBuilder localStringBuilder = new StringBuilder();
    for (int i = 0; (i < arrayOfStackTraceElement.length) && (i < 2); i++)
    {
      localStringBuilder.append(arrayOfStackTraceElement[i].toString());
      localStringBuilder.append("\n");
    }
    zzqc localzzqc = new zzqc(paramContext, 10);
    String[] arrayOfString = new String[2];
    arrayOfString[0] = "GMS_WTF";
    arrayOfString[1] = localStringBuilder.toString();
    localzzqc.zza("GMS_WTF", null, arrayOfString);
    localzzqc.send();
    if (zzbH(7))
    {
      Log.e(paramString1, zzcp(paramString2), paramThrowable);
      Log.wtf(paramString1, zzcp(paramString2), paramThrowable);
    }
  }
  
  public void zza(String paramString1, String paramString2, Throwable paramThrowable)
  {
    if (zzbH(4)) {
      Log.i(paramString1, zzcp(paramString2), paramThrowable);
    }
  }
  
  public void zzb(String paramString1, String paramString2, Throwable paramThrowable)
  {
    if (zzbH(5)) {
      Log.w(paramString1, zzcp(paramString2), paramThrowable);
    }
  }
  
  public boolean zzbH(int paramInt)
  {
    return Log.isLoggable(this.zzagm, paramInt);
  }
  
  public void zzc(String paramString1, String paramString2, Throwable paramThrowable)
  {
    if (zzbH(6)) {
      Log.e(paramString1, zzcp(paramString2), paramThrowable);
    }
  }
  
  public void zzx(String paramString1, String paramString2)
  {
    if (zzbH(3)) {
      Log.d(paramString1, zzcp(paramString2));
    }
  }
  
  public void zzy(String paramString1, String paramString2)
  {
    if (zzbH(5)) {
      Log.w(paramString1, zzcp(paramString2));
    }
  }
  
  public void zzz(String paramString1, String paramString2)
  {
    if (zzbH(6)) {
      Log.e(paramString1, zzcp(paramString2));
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/common/internal/zzo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */