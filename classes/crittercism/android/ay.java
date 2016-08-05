package crittercism.android;

public final class ay
  implements Thread.UncaughtExceptionHandler
{
  private Thread.UncaughtExceptionHandler a;
  private final az b;
  
  public ay(az paramaz, Thread.UncaughtExceptionHandler paramUncaughtExceptionHandler)
  {
    this.b = paramaz;
    this.a = paramUncaughtExceptionHandler;
  }
  
  /* Error */
  public final void uncaughtException(Thread paramThread, Throwable paramThrowable)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 17	crittercism/android/ay:b	Lcrittercism/android/az;
    //   4: aload_2
    //   5: invokevirtual 30	crittercism/android/az:a	(Ljava/lang/Throwable;)V
    //   8: aload_0
    //   9: getfield 19	crittercism/android/ay:a	Ljava/lang/Thread$UncaughtExceptionHandler;
    //   12: ifnull +26 -> 38
    //   15: aload_0
    //   16: getfield 19	crittercism/android/ay:a	Ljava/lang/Thread$UncaughtExceptionHandler;
    //   19: instanceof 2
    //   22: ifne +16 -> 38
    //   25: aload_0
    //   26: getfield 19	crittercism/android/ay:a	Ljava/lang/Thread$UncaughtExceptionHandler;
    //   29: invokestatic 36	java/lang/Thread:currentThread	()Ljava/lang/Thread;
    //   32: aload_2
    //   33: invokeinterface 38 3 0
    //   38: return
    //   39: astore 5
    //   41: aload 5
    //   43: athrow
    //   44: astore 4
    //   46: aload_0
    //   47: getfield 19	crittercism/android/ay:a	Ljava/lang/Thread$UncaughtExceptionHandler;
    //   50: ifnull +26 -> 76
    //   53: aload_0
    //   54: getfield 19	crittercism/android/ay:a	Ljava/lang/Thread$UncaughtExceptionHandler;
    //   57: instanceof 2
    //   60: ifne +16 -> 76
    //   63: aload_0
    //   64: getfield 19	crittercism/android/ay:a	Ljava/lang/Thread$UncaughtExceptionHandler;
    //   67: invokestatic 36	java/lang/Thread:currentThread	()Ljava/lang/Thread;
    //   70: aload_2
    //   71: invokeinterface 38 3 0
    //   76: aload 4
    //   78: athrow
    //   79: astore_3
    //   80: ldc 40
    //   82: aload_3
    //   83: invokestatic 45	crittercism/android/dx:a	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   86: aload_0
    //   87: getfield 19	crittercism/android/ay:a	Ljava/lang/Thread$UncaughtExceptionHandler;
    //   90: ifnull -52 -> 38
    //   93: aload_0
    //   94: getfield 19	crittercism/android/ay:a	Ljava/lang/Thread$UncaughtExceptionHandler;
    //   97: instanceof 2
    //   100: ifne -62 -> 38
    //   103: aload_0
    //   104: getfield 19	crittercism/android/ay:a	Ljava/lang/Thread$UncaughtExceptionHandler;
    //   107: invokestatic 36	java/lang/Thread:currentThread	()Ljava/lang/Thread;
    //   110: aload_2
    //   111: invokeinterface 38 3 0
    //   116: goto -78 -> 38
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	119	0	this	ay
    //   0	119	1	paramThread	Thread
    //   0	119	2	paramThrowable	Throwable
    //   79	4	3	localThrowable	Throwable
    //   44	33	4	localObject	Object
    //   39	3	5	localThreadDeath	ThreadDeath
    // Exception table:
    //   from	to	target	type
    //   0	8	39	java/lang/ThreadDeath
    //   0	8	44	finally
    //   41	44	44	finally
    //   80	86	44	finally
    //   0	8	79	java/lang/Throwable
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/crittercism/android/ay.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */