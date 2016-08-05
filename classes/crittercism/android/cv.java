package crittercism.android;

public final class cv
{
  private long a = 0L;
  private long b;
  
  public cv(long paramLong)
  {
    this.b = paramLong;
  }
  
  /* Error */
  /**
   * @deprecated
   */
  public final boolean a()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: invokestatic 23	java/lang/System:nanoTime	()J
    //   5: aload_0
    //   6: getfield 14	crittercism/android/cv:a	J
    //   9: lsub
    //   10: lstore_2
    //   11: aload_0
    //   12: getfield 16	crittercism/android/cv:b	J
    //   15: lstore 4
    //   17: lload_2
    //   18: lload 4
    //   20: lcmp
    //   21: ifle +11 -> 32
    //   24: iconst_1
    //   25: istore 6
    //   27: aload_0
    //   28: monitorexit
    //   29: iload 6
    //   31: ireturn
    //   32: iconst_0
    //   33: istore 6
    //   35: goto -8 -> 27
    //   38: astore_1
    //   39: aload_0
    //   40: monitorexit
    //   41: aload_1
    //   42: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	43	0	this	cv
    //   38	4	1	localObject	Object
    //   10	8	2	l1	long
    //   15	4	4	l2	long
    //   25	9	6	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   2	17	38	finally
  }
  
  /**
   * @deprecated
   */
  public final void b()
  {
    try
    {
      this.a = System.nanoTime();
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/crittercism/android/cv.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */