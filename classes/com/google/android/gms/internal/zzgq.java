package com.google.android.gms.internal;

import android.content.Context;
import android.net.Uri.Builder;
import android.os.Build.VERSION;
import android.os.Looper;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzp;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.LinkedList;

@zzgr
public class zzgq
  implements Thread.UncaughtExceptionHandler
{
  private Context mContext;
  private VersionInfoParcel zzBZ;
  private Thread.UncaughtExceptionHandler zzEc;
  private Thread.UncaughtExceptionHandler zzEd;
  
  public zzgq(Context paramContext, VersionInfoParcel paramVersionInfoParcel, Thread.UncaughtExceptionHandler paramUncaughtExceptionHandler1, Thread.UncaughtExceptionHandler paramUncaughtExceptionHandler2)
  {
    this.zzEc = paramUncaughtExceptionHandler1;
    this.zzEd = paramUncaughtExceptionHandler2;
    this.mContext = paramContext;
    this.zzBZ = paramVersionInfoParcel;
  }
  
  public static zzgq zza(Context paramContext, Thread paramThread, VersionInfoParcel paramVersionInfoParcel)
  {
    Object localObject;
    if ((paramContext == null) || (paramThread == null) || (paramVersionInfoParcel == null)) {
      localObject = null;
    }
    for (;;)
    {
      return (zzgq)localObject;
      if (!zzy(paramContext))
      {
        localObject = null;
      }
      else
      {
        Thread.UncaughtExceptionHandler localUncaughtExceptionHandler = paramThread.getUncaughtExceptionHandler();
        zzgq localzzgq = new zzgq(paramContext, paramVersionInfoParcel, localUncaughtExceptionHandler, Thread.getDefaultUncaughtExceptionHandler());
        if ((localUncaughtExceptionHandler == null) || (!(localUncaughtExceptionHandler instanceof zzgq))) {
          try
          {
            paramThread.setUncaughtExceptionHandler(localzzgq);
            localObject = localzzgq;
          }
          catch (SecurityException localSecurityException)
          {
            zzb.zzc("Fail to set UncaughtExceptionHandler.", localSecurityException);
            localObject = null;
          }
        } else {
          localObject = (zzgq)localUncaughtExceptionHandler;
        }
      }
    }
  }
  
  private Throwable zzb(Throwable paramThrowable)
  {
    if (((Boolean)zzby.zzur.get()).booleanValue()) {
      return paramThrowable;
    }
    LinkedList localLinkedList = new LinkedList();
    while (paramThrowable != null)
    {
      localLinkedList.push(paramThrowable);
      paramThrowable = paramThrowable.getCause();
    }
    Object localObject1 = null;
    label44:
    Throwable localThrowable;
    Object localObject2;
    if (!localLinkedList.isEmpty())
    {
      localThrowable = (Throwable)localLinkedList.pop();
      StackTraceElement[] arrayOfStackTraceElement = localThrowable.getStackTrace();
      ArrayList localArrayList = new ArrayList();
      localArrayList.add(new StackTraceElement(localThrowable.getClass().getName(), "<filtered>", "<filtered>", 1));
      int i = arrayOfStackTraceElement.length;
      int j = 0;
      int k = 0;
      if (j < i)
      {
        StackTraceElement localStackTraceElement = arrayOfStackTraceElement[j];
        if (zzar(localStackTraceElement.getClassName()))
        {
          localArrayList.add(localStackTraceElement);
          k = 1;
        }
        for (;;)
        {
          j++;
          break;
          if (zzas(localStackTraceElement.getClassName())) {
            localArrayList.add(localStackTraceElement);
          } else {
            localArrayList.add(new StackTraceElement("<filtered>", "<filtered>", "<filtered>", 1));
          }
        }
      }
      if (k == 0) {
        break label271;
      }
      if (localObject1 == null)
      {
        localObject2 = new Throwable(localThrowable.getMessage());
        label225:
        ((Throwable)localObject2).setStackTrace((StackTraceElement[])localArrayList.toArray(new StackTraceElement[0]));
      }
    }
    for (;;)
    {
      localObject1 = localObject2;
      break label44;
      localObject2 = new Throwable(localThrowable.getMessage(), (Throwable)localObject1);
      break label225;
      paramThrowable = (Throwable)localObject1;
      break;
      label271:
      localObject2 = localObject1;
    }
  }
  
  private static boolean zzy(Context paramContext)
  {
    return ((Boolean)zzby.zzuq.get()).booleanValue();
  }
  
  public void uncaughtException(Thread paramThread, Throwable paramThrowable)
  {
    if (zza(paramThrowable)) {
      if (Looper.getMainLooper().getThread() != paramThread) {
        zza(paramThrowable, true);
      }
    }
    for (;;)
    {
      return;
      zza(paramThrowable, false);
      if (this.zzEc != null) {
        this.zzEc.uncaughtException(paramThread, paramThrowable);
      } else if (this.zzEd != null) {
        this.zzEd.uncaughtException(paramThread, paramThrowable);
      }
    }
  }
  
  public void zza(Throwable paramThrowable, boolean paramBoolean)
  {
    if (!zzy(this.mContext)) {}
    for (;;)
    {
      return;
      Throwable localThrowable = zzb(paramThrowable);
      if (localThrowable != null)
      {
        ArrayList localArrayList = new ArrayList();
        localArrayList.add(zzb(localThrowable, paramBoolean));
        zzp.zzbv().zza(this.mContext, this.zzBZ.zzJu, localArrayList, zzp.zzby().zzgr());
      }
    }
  }
  
  protected boolean zza(Throwable paramThrowable)
  {
    boolean bool1 = true;
    boolean bool2 = false;
    if (paramThrowable == null) {
      return bool2;
    }
    int i = 0;
    int j = 0;
    while (paramThrowable != null)
    {
      for (StackTraceElement localStackTraceElement : paramThrowable.getStackTrace())
      {
        if (zzar(localStackTraceElement.getClassName())) {
          j = bool1;
        }
        if (getClass().getName().equals(localStackTraceElement.getClassName())) {
          i = bool1;
        }
      }
      paramThrowable = paramThrowable.getCause();
    }
    if ((j != 0) && (i == 0)) {}
    for (;;)
    {
      bool2 = bool1;
      break;
      bool1 = false;
    }
  }
  
  protected boolean zzar(String paramString)
  {
    boolean bool1 = false;
    if (TextUtils.isEmpty(paramString)) {}
    for (;;)
    {
      return bool1;
      if (paramString.startsWith("com.google.android.gms.ads")) {
        bool1 = true;
      } else if (paramString.startsWith("com.google.ads")) {
        bool1 = true;
      } else {
        try
        {
          boolean bool2 = Class.forName(paramString).isAnnotationPresent(zzgr.class);
          bool1 = bool2;
        }
        catch (Exception localException)
        {
          zzb.zza("Fail to check class type for class " + paramString, localException);
        }
      }
    }
  }
  
  protected boolean zzas(String paramString)
  {
    boolean bool = false;
    if (TextUtils.isEmpty(paramString)) {}
    for (;;)
    {
      return bool;
      if ((paramString.startsWith("android.")) || (paramString.startsWith("java."))) {
        bool = true;
      }
    }
  }
  
  String zzb(Throwable paramThrowable, boolean paramBoolean)
  {
    StringWriter localStringWriter = new StringWriter();
    paramThrowable.printStackTrace(new PrintWriter(localStringWriter));
    return new Uri.Builder().scheme("https").path("//pagead2.googlesyndication.com/pagead/gen_204").appendQueryParameter("id", "gmob-apps-report-exception").appendQueryParameter("os", Build.VERSION.RELEASE).appendQueryParameter("api", String.valueOf(Build.VERSION.SDK_INT)).appendQueryParameter("device", zzp.zzbv().zzgE()).appendQueryParameter("js", this.zzBZ.zzJu).appendQueryParameter("appid", this.mContext.getApplicationContext().getPackageName()).appendQueryParameter("stacktrace", localStringWriter.toString()).appendQueryParameter("eids", TextUtils.join(",", zzby.zzdf())).appendQueryParameter("trapped", String.valueOf(paramBoolean)).toString();
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzgq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */