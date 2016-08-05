package com.upsight.android.internal.util;

public class GzipHelper
{
  /* Error */
  public static byte[] compress(String paramString)
    throws java.io.IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnonnull +9 -> 10
    //   4: aconst_null
    //   5: astore 4
    //   7: aload 4
    //   9: areturn
    //   10: new 14	java/io/ByteArrayOutputStream
    //   13: dup
    //   14: invokespecial 15	java/io/ByteArrayOutputStream:<init>	()V
    //   17: astore_1
    //   18: new 17	java/util/zip/GZIPOutputStream
    //   21: dup
    //   22: new 19	java/io/BufferedOutputStream
    //   25: dup
    //   26: aload_1
    //   27: invokespecial 22	java/io/BufferedOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   30: invokespecial 23	java/util/zip/GZIPOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   33: astore_2
    //   34: aload_2
    //   35: aload_0
    //   36: invokevirtual 29	java/lang/String:getBytes	()[B
    //   39: invokevirtual 33	java/util/zip/GZIPOutputStream:write	([B)V
    //   42: aload_2
    //   43: invokevirtual 36	java/util/zip/GZIPOutputStream:close	()V
    //   46: aload_1
    //   47: invokevirtual 39	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   50: astore 4
    //   52: goto -45 -> 7
    //   55: astore_3
    //   56: aload_2
    //   57: invokevirtual 36	java/util/zip/GZIPOutputStream:close	()V
    //   60: aload_3
    //   61: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	62	0	paramString	String
    //   17	30	1	localByteArrayOutputStream	java.io.ByteArrayOutputStream
    //   33	24	2	localGZIPOutputStream	java.util.zip.GZIPOutputStream
    //   55	6	3	localObject	Object
    //   5	46	4	arrayOfByte	byte[]
    // Exception table:
    //   from	to	target	type
    //   34	42	55	finally
  }
  
  /* Error */
  public static String decompress(byte[] paramArrayOfByte)
    throws java.io.IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnonnull +9 -> 10
    //   4: aconst_null
    //   5: astore 6
    //   7: aload 6
    //   9: areturn
    //   10: new 43	java/util/zip/GZIPInputStream
    //   13: dup
    //   14: new 45	java/io/ByteArrayInputStream
    //   17: dup
    //   18: aload_0
    //   19: invokespecial 47	java/io/ByteArrayInputStream:<init>	([B)V
    //   22: invokespecial 50	java/util/zip/GZIPInputStream:<init>	(Ljava/io/InputStream;)V
    //   25: astore_1
    //   26: new 52	java/io/BufferedReader
    //   29: dup
    //   30: new 54	java/io/InputStreamReader
    //   33: dup
    //   34: aload_1
    //   35: invokespecial 55	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   38: invokespecial 58	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   41: astore_2
    //   42: new 60	java/lang/StringBuilder
    //   45: dup
    //   46: invokespecial 61	java/lang/StringBuilder:<init>	()V
    //   49: astore_3
    //   50: aload_2
    //   51: invokevirtual 65	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   54: astore 5
    //   56: aload 5
    //   58: ifnull +22 -> 80
    //   61: aload_3
    //   62: aload 5
    //   64: invokevirtual 69	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   67: pop
    //   68: goto -18 -> 50
    //   71: astore 4
    //   73: aload_1
    //   74: invokevirtual 70	java/util/zip/GZIPInputStream:close	()V
    //   77: aload 4
    //   79: athrow
    //   80: aload_1
    //   81: invokevirtual 70	java/util/zip/GZIPInputStream:close	()V
    //   84: aload_3
    //   85: invokevirtual 73	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   88: astore 6
    //   90: goto -83 -> 7
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	93	0	paramArrayOfByte	byte[]
    //   25	56	1	localGZIPInputStream	java.util.zip.GZIPInputStream
    //   41	10	2	localBufferedReader	java.io.BufferedReader
    //   49	36	3	localStringBuilder	StringBuilder
    //   71	7	4	localObject	Object
    //   54	9	5	str1	String
    //   5	84	6	str2	String
    // Exception table:
    //   from	to	target	type
    //   50	68	71	finally
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/internal/util/GzipHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */