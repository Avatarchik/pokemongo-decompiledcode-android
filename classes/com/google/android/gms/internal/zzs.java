package com.google.android.gms.internal;

import android.os.SystemClock;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class zzs
{
  public static boolean DEBUG = Log.isLoggable(TAG, 2);
  public static String TAG = "Volley";
  
  public static void zza(String paramString, Object... paramVarArgs)
  {
    if (DEBUG) {
      Log.v(TAG, zzd(paramString, paramVarArgs));
    }
  }
  
  public static void zza(Throwable paramThrowable, String paramString, Object... paramVarArgs)
  {
    Log.e(TAG, zzd(paramString, paramVarArgs), paramThrowable);
  }
  
  public static void zzb(String paramString, Object... paramVarArgs)
  {
    Log.d(TAG, zzd(paramString, paramVarArgs));
  }
  
  public static void zzc(String paramString, Object... paramVarArgs)
  {
    Log.e(TAG, zzd(paramString, paramVarArgs));
  }
  
  private static String zzd(String paramString, Object... paramVarArgs)
  {
    StackTraceElement[] arrayOfStackTraceElement;
    int i;
    label20:
    String str4;
    if (paramVarArgs == null)
    {
      arrayOfStackTraceElement = new Throwable().fillInStackTrace().getStackTrace();
      i = 2;
      if (i >= arrayOfStackTraceElement.length) {
        break label174;
      }
      if (arrayOfStackTraceElement[i].getClass().equals(zzs.class)) {
        break label168;
      }
      String str2 = arrayOfStackTraceElement[i].getClassName();
      String str3 = str2.substring(1 + str2.lastIndexOf('.'));
      str4 = str3.substring(1 + str3.lastIndexOf('$'));
    }
    label168:
    label174:
    for (String str1 = str4 + "." + arrayOfStackTraceElement[i].getMethodName();; str1 = "<unknown>")
    {
      Locale localLocale = Locale.US;
      Object[] arrayOfObject = new Object[3];
      arrayOfObject[0] = Long.valueOf(Thread.currentThread().getId());
      arrayOfObject[1] = str1;
      arrayOfObject[2] = paramString;
      return String.format(localLocale, "[%d] %s: %s", arrayOfObject);
      paramString = String.format(Locale.US, paramString, paramVarArgs);
      break;
      i++;
      break label20;
    }
  }
  
  static class zza
  {
    public static final boolean zzak = zzs.DEBUG;
    private final List<zza> zzal = new ArrayList();
    private boolean zzam = false;
    
    private long zzx()
    {
      if (this.zzal.size() == 0) {}
      long l1;
      for (long l2 = 0L;; l2 = ((zza)this.zzal.get(-1 + this.zzal.size())).time - l1)
      {
        return l2;
        l1 = ((zza)this.zzal.get(0)).time;
      }
    }
    
    protected void finalize()
      throws Throwable
    {
      if (!this.zzam)
      {
        zzd("Request on the loose");
        zzs.zzc("Marker log finalized without finish() - uncaught exit point for request", new Object[0]);
      }
    }
    
    /**
     * @deprecated
     */
    public void zza(String paramString, long paramLong)
    {
      try
      {
        if (this.zzam) {
          throw new IllegalStateException("Marker added to finished log");
        }
      }
      finally {}
      this.zzal.add(new zza(paramString, paramLong, SystemClock.elapsedRealtime()));
    }
    
    /* Error */
    /**
     * @deprecated
     */
    public void zzd(String paramString)
    {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: iconst_1
      //   4: putfield 32	com/google/android/gms/internal/zzs$zza:zzam	Z
      //   7: aload_0
      //   8: invokespecial 84	com/google/android/gms/internal/zzs$zza:zzx	()J
      //   11: lstore_3
      //   12: lload_3
      //   13: lconst_0
      //   14: lcmp
      //   15: ifgt +6 -> 21
      //   18: aload_0
      //   19: monitorexit
      //   20: return
      //   21: aload_0
      //   22: getfield 30	com/google/android/gms/internal/zzs$zza:zzal	Ljava/util/List;
      //   25: iconst_0
      //   26: invokeinterface 44 2 0
      //   31: checkcast 9	com/google/android/gms/internal/zzs$zza$zza
      //   34: getfield 48	com/google/android/gms/internal/zzs$zza$zza:time	J
      //   37: lstore 5
      //   39: iconst_2
      //   40: anewarray 4	java/lang/Object
      //   43: astore 7
      //   45: aload 7
      //   47: iconst_0
      //   48: lload_3
      //   49: invokestatic 90	java/lang/Long:valueOf	(J)Ljava/lang/Long;
      //   52: aastore
      //   53: aload 7
      //   55: iconst_1
      //   56: aload_1
      //   57: aastore
      //   58: ldc 92
      //   60: aload 7
      //   62: invokestatic 95	com/google/android/gms/internal/zzs:zzb	(Ljava/lang/String;[Ljava/lang/Object;)V
      //   65: aload_0
      //   66: getfield 30	com/google/android/gms/internal/zzs$zza:zzal	Ljava/util/List;
      //   69: invokeinterface 99 1 0
      //   74: astore 8
      //   76: lload 5
      //   78: lstore 9
      //   80: aload 8
      //   82: invokeinterface 105 1 0
      //   87: ifeq -69 -> 18
      //   90: aload 8
      //   92: invokeinterface 109 1 0
      //   97: checkcast 9	com/google/android/gms/internal/zzs$zza$zza
      //   100: astore 11
      //   102: aload 11
      //   104: getfield 48	com/google/android/gms/internal/zzs$zza$zza:time	J
      //   107: lstore 12
      //   109: iconst_3
      //   110: anewarray 4	java/lang/Object
      //   113: astore 14
      //   115: aload 14
      //   117: iconst_0
      //   118: lload 12
      //   120: lload 9
      //   122: lsub
      //   123: invokestatic 90	java/lang/Long:valueOf	(J)Ljava/lang/Long;
      //   126: aastore
      //   127: aload 14
      //   129: iconst_1
      //   130: aload 11
      //   132: getfield 112	com/google/android/gms/internal/zzs$zza$zza:zzan	J
      //   135: invokestatic 90	java/lang/Long:valueOf	(J)Ljava/lang/Long;
      //   138: aastore
      //   139: aload 14
      //   141: iconst_2
      //   142: aload 11
      //   144: getfield 116	com/google/android/gms/internal/zzs$zza$zza:name	Ljava/lang/String;
      //   147: aastore
      //   148: ldc 118
      //   150: aload 14
      //   152: invokestatic 95	com/google/android/gms/internal/zzs:zzb	(Ljava/lang/String;[Ljava/lang/Object;)V
      //   155: lload 12
      //   157: lstore 9
      //   159: goto -79 -> 80
      //   162: astore_2
      //   163: aload_0
      //   164: monitorexit
      //   165: aload_2
      //   166: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	167	0	this	zza
      //   0	167	1	paramString	String
      //   162	4	2	localObject	Object
      //   11	38	3	l1	long
      //   37	40	5	l2	long
      //   43	18	7	arrayOfObject1	Object[]
      //   74	17	8	localIterator	java.util.Iterator
      //   78	80	9	l3	long
      //   100	43	11	localzza	zza
      //   107	49	12	l4	long
      //   113	38	14	arrayOfObject2	Object[]
      // Exception table:
      //   from	to	target	type
      //   2	12	162	finally
      //   21	155	162	finally
    }
    
    private static class zza
    {
      public final String name;
      public final long time;
      public final long zzan;
      
      public zza(String paramString, long paramLong1, long paramLong2)
      {
        this.name = paramString;
        this.zzan = paramLong1;
        this.time = paramLong2;
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */