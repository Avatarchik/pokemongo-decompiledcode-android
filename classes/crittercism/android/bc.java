package crittercism.android;

import android.location.Location;

public final class bc
{
  private static Location a;
  
  /**
   * @deprecated
   */
  public static Location a()
  {
    try
    {
      Location localLocation = a;
      return localLocation;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /**
   * @deprecated
   */
  public static void a(Location paramLocation)
  {
    if (paramLocation == null) {}
    for (;;)
    {
      try
      {
        a = paramLocation;
        return;
      }
      finally {}
      Location localLocation = new Location(paramLocation);
      paramLocation = localLocation;
    }
  }
  
  /* Error */
  /**
   * @deprecated
   */
  public static boolean b()
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: getstatic 9	crittercism/android/bc:a	Landroid/location/Location;
    //   6: astore_1
    //   7: aload_1
    //   8: ifnull +10 -> 18
    //   11: iconst_1
    //   12: istore_2
    //   13: ldc 2
    //   15: monitorexit
    //   16: iload_2
    //   17: ireturn
    //   18: iconst_0
    //   19: istore_2
    //   20: goto -7 -> 13
    //   23: astore_0
    //   24: ldc 2
    //   26: monitorexit
    //   27: aload_0
    //   28: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   23	5	0	localObject	Object
    //   6	2	1	localLocation	Location
    //   12	8	2	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   3	7	23	finally
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/crittercism/android/bc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */