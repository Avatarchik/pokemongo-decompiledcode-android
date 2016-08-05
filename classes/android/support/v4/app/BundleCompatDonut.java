package android.support.v4.app;

import java.lang.reflect.Method;

class BundleCompatDonut
{
  private static final String TAG = "BundleCompatDonut";
  private static Method sGetIBinderMethod;
  private static boolean sGetIBinderMethodFetched;
  private static Method sPutIBinderMethod;
  private static boolean sPutIBinderMethodFetched;
  
  /* Error */
  public static android.os.IBinder getBinder(android.os.Bundle paramBundle, String paramString)
  {
    // Byte code:
    //   0: getstatic 30	android/support/v4/app/BundleCompatDonut:sGetIBinderMethodFetched	Z
    //   3: ifne +38 -> 41
    //   6: iconst_1
    //   7: anewarray 32	java/lang/Class
    //   10: astore 9
    //   12: aload 9
    //   14: iconst_0
    //   15: ldc 34
    //   17: aastore
    //   18: ldc 36
    //   20: ldc 38
    //   22: aload 9
    //   24: invokevirtual 42	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   27: putstatic 44	android/support/v4/app/BundleCompatDonut:sGetIBinderMethod	Ljava/lang/reflect/Method;
    //   30: getstatic 44	android/support/v4/app/BundleCompatDonut:sGetIBinderMethod	Ljava/lang/reflect/Method;
    //   33: iconst_1
    //   34: invokevirtual 50	java/lang/reflect/Method:setAccessible	(Z)V
    //   37: iconst_1
    //   38: putstatic 30	android/support/v4/app/BundleCompatDonut:sGetIBinderMethodFetched	Z
    //   41: getstatic 44	android/support/v4/app/BundleCompatDonut:sGetIBinderMethod	Ljava/lang/reflect/Method;
    //   44: ifnull +62 -> 106
    //   47: getstatic 44	android/support/v4/app/BundleCompatDonut:sGetIBinderMethod	Ljava/lang/reflect/Method;
    //   50: astore 5
    //   52: iconst_1
    //   53: anewarray 4	java/lang/Object
    //   56: astore 6
    //   58: aload 6
    //   60: iconst_0
    //   61: aload_1
    //   62: aastore
    //   63: aload 5
    //   65: aload_0
    //   66: aload 6
    //   68: invokevirtual 54	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   71: checkcast 56	android/os/IBinder
    //   74: astore_2
    //   75: aload_2
    //   76: areturn
    //   77: astore 7
    //   79: ldc 8
    //   81: ldc 58
    //   83: aload 7
    //   85: invokestatic 64	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   88: pop
    //   89: goto -52 -> 37
    //   92: astore_3
    //   93: ldc 8
    //   95: ldc 66
    //   97: aload_3
    //   98: invokestatic 64	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   101: pop
    //   102: aconst_null
    //   103: putstatic 44	android/support/v4/app/BundleCompatDonut:sGetIBinderMethod	Ljava/lang/reflect/Method;
    //   106: aconst_null
    //   107: astore_2
    //   108: goto -33 -> 75
    //   111: astore_3
    //   112: goto -19 -> 93
    //   115: astore_3
    //   116: goto -23 -> 93
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	119	0	paramBundle	android.os.Bundle
    //   0	119	1	paramString	String
    //   74	34	2	localIBinder	android.os.IBinder
    //   92	6	3	localIllegalAccessException	IllegalAccessException
    //   111	1	3	localIllegalArgumentException	IllegalArgumentException
    //   115	1	3	localInvocationTargetException	java.lang.reflect.InvocationTargetException
    //   50	14	5	localMethod	Method
    //   56	11	6	arrayOfObject	Object[]
    //   77	7	7	localNoSuchMethodException	NoSuchMethodException
    //   10	13	9	arrayOfClass	Class[]
    // Exception table:
    //   from	to	target	type
    //   6	37	77	java/lang/NoSuchMethodException
    //   47	75	92	java/lang/IllegalAccessException
    //   47	75	111	java/lang/IllegalArgumentException
    //   47	75	115	java/lang/reflect/InvocationTargetException
  }
  
  /* Error */
  public static void putBinder(android.os.Bundle paramBundle, String paramString, android.os.IBinder paramIBinder)
  {
    // Byte code:
    //   0: getstatic 70	android/support/v4/app/BundleCompatDonut:sPutIBinderMethodFetched	Z
    //   3: ifne +44 -> 47
    //   6: iconst_2
    //   7: anewarray 32	java/lang/Class
    //   10: astore 10
    //   12: aload 10
    //   14: iconst_0
    //   15: ldc 34
    //   17: aastore
    //   18: aload 10
    //   20: iconst_1
    //   21: ldc 56
    //   23: aastore
    //   24: ldc 36
    //   26: ldc 72
    //   28: aload 10
    //   30: invokevirtual 42	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   33: putstatic 74	android/support/v4/app/BundleCompatDonut:sPutIBinderMethod	Ljava/lang/reflect/Method;
    //   36: getstatic 74	android/support/v4/app/BundleCompatDonut:sPutIBinderMethod	Ljava/lang/reflect/Method;
    //   39: iconst_1
    //   40: invokevirtual 50	java/lang/reflect/Method:setAccessible	(Z)V
    //   43: iconst_1
    //   44: putstatic 70	android/support/v4/app/BundleCompatDonut:sPutIBinderMethodFetched	Z
    //   47: getstatic 74	android/support/v4/app/BundleCompatDonut:sPutIBinderMethod	Ljava/lang/reflect/Method;
    //   50: ifnull +33 -> 83
    //   53: getstatic 74	android/support/v4/app/BundleCompatDonut:sPutIBinderMethod	Ljava/lang/reflect/Method;
    //   56: astore 5
    //   58: iconst_2
    //   59: anewarray 4	java/lang/Object
    //   62: astore 6
    //   64: aload 6
    //   66: iconst_0
    //   67: aload_1
    //   68: aastore
    //   69: aload 6
    //   71: iconst_1
    //   72: aload_2
    //   73: aastore
    //   74: aload 5
    //   76: aload_0
    //   77: aload 6
    //   79: invokevirtual 54	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   82: pop
    //   83: return
    //   84: astore 8
    //   86: ldc 8
    //   88: ldc 76
    //   90: aload 8
    //   92: invokestatic 64	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   95: pop
    //   96: goto -53 -> 43
    //   99: astore_3
    //   100: ldc 8
    //   102: ldc 78
    //   104: aload_3
    //   105: invokestatic 64	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   108: pop
    //   109: aconst_null
    //   110: putstatic 74	android/support/v4/app/BundleCompatDonut:sPutIBinderMethod	Ljava/lang/reflect/Method;
    //   113: goto -30 -> 83
    //   116: astore_3
    //   117: goto -17 -> 100
    //   120: astore_3
    //   121: goto -21 -> 100
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	124	0	paramBundle	android.os.Bundle
    //   0	124	1	paramString	String
    //   0	124	2	paramIBinder	android.os.IBinder
    //   99	6	3	localIllegalAccessException	IllegalAccessException
    //   116	1	3	localIllegalArgumentException	IllegalArgumentException
    //   120	1	3	localInvocationTargetException	java.lang.reflect.InvocationTargetException
    //   56	19	5	localMethod	Method
    //   62	16	6	arrayOfObject	Object[]
    //   84	7	8	localNoSuchMethodException	NoSuchMethodException
    //   10	19	10	arrayOfClass	Class[]
    // Exception table:
    //   from	to	target	type
    //   6	43	84	java/lang/NoSuchMethodException
    //   53	83	99	java/lang/IllegalAccessException
    //   53	83	116	java/lang/IllegalArgumentException
    //   53	83	120	java/lang/reflect/InvocationTargetException
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/android/support/v4/app/BundleCompatDonut.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */