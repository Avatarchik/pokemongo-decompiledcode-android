package crittercism.android;

import java.io.File;

public final class eb
{
  public static void a(File paramFile)
  {
    if (!paramFile.getAbsolutePath().contains("com.crittercism")) {}
    for (;;)
    {
      return;
      if (paramFile.isDirectory())
      {
        File[] arrayOfFile = paramFile.listFiles();
        int i = arrayOfFile.length;
        for (int j = 0; j < i; j++) {
          a(arrayOfFile[j]);
        }
      }
      paramFile.delete();
    }
  }
  
  /* Error */
  public static String b(File paramFile)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: new 37	java/lang/StringBuilder
    //   5: dup
    //   6: invokespecial 41	java/lang/StringBuilder:<init>	()V
    //   9: astore_2
    //   10: new 43	java/io/FileInputStream
    //   13: dup
    //   14: aload_0
    //   15: invokespecial 45	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   18: astore_3
    //   19: new 47	java/io/InputStreamReader
    //   22: dup
    //   23: aload_3
    //   24: invokespecial 50	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   27: astore 4
    //   29: aload 4
    //   31: invokevirtual 54	java/io/InputStreamReader:read	()I
    //   34: istore 6
    //   36: iload 6
    //   38: bipush -1
    //   40: if_icmpeq +39 -> 79
    //   43: aload_2
    //   44: iload 6
    //   46: i2c
    //   47: invokevirtual 58	java/lang/StringBuilder:append	(C)Ljava/lang/StringBuilder;
    //   50: pop
    //   51: goto -22 -> 29
    //   54: astore 5
    //   56: aload_3
    //   57: astore_1
    //   58: aload_1
    //   59: ifnull +7 -> 66
    //   62: aload_1
    //   63: invokevirtual 63	java/io/InputStream:close	()V
    //   66: aload 4
    //   68: ifnull +8 -> 76
    //   71: aload 4
    //   73: invokevirtual 64	java/io/InputStreamReader:close	()V
    //   76: aload 5
    //   78: athrow
    //   79: aload_3
    //   80: invokevirtual 63	java/io/InputStream:close	()V
    //   83: aload 4
    //   85: invokevirtual 64	java/io/InputStreamReader:close	()V
    //   88: aload_2
    //   89: invokevirtual 67	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   92: areturn
    //   93: astore 5
    //   95: aconst_null
    //   96: astore 4
    //   98: goto -40 -> 58
    //   101: astore 5
    //   103: aconst_null
    //   104: astore 4
    //   106: aload_3
    //   107: astore_1
    //   108: goto -50 -> 58
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	111	0	paramFile	File
    //   1	107	1	localObject1	Object
    //   9	80	2	localStringBuilder	StringBuilder
    //   18	89	3	localFileInputStream	java.io.FileInputStream
    //   27	78	4	localInputStreamReader	java.io.InputStreamReader
    //   54	23	5	localObject2	Object
    //   93	1	5	localObject3	Object
    //   101	1	5	localObject4	Object
    //   34	11	6	i	int
    // Exception table:
    //   from	to	target	type
    //   29	51	54	finally
    //   10	19	93	finally
    //   19	29	101	finally
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/crittercism/android/eb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */