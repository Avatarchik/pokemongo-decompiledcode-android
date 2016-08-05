package com.google.android.gms.internal;

import android.os.ParcelFileDescriptor;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class zzmt
{
  public static long zza(InputStream paramInputStream, OutputStream paramOutputStream, boolean paramBoolean)
    throws IOException
  {
    return zza(paramInputStream, paramOutputStream, paramBoolean, 1024);
  }
  
  /* Error */
  public static long zza(InputStream paramInputStream, OutputStream paramOutputStream, boolean paramBoolean, int paramInt)
    throws IOException
  {
    // Byte code:
    //   0: iload_3
    //   1: newarray <illegal type>
    //   3: astore 4
    //   5: lconst_0
    //   6: lstore 5
    //   8: aload_0
    //   9: aload 4
    //   11: iconst_0
    //   12: aload 4
    //   14: arraylength
    //   15: invokevirtual 17	java/io/InputStream:read	([BII)I
    //   18: istore 8
    //   20: iload 8
    //   22: bipush -1
    //   24: if_icmpeq +40 -> 64
    //   27: lload 5
    //   29: iload 8
    //   31: i2l
    //   32: ladd
    //   33: lstore 5
    //   35: aload_1
    //   36: aload 4
    //   38: iconst_0
    //   39: iload 8
    //   41: invokevirtual 23	java/io/OutputStream:write	([BII)V
    //   44: goto -36 -> 8
    //   47: astore 7
    //   49: iload_2
    //   50: ifeq +11 -> 61
    //   53: aload_0
    //   54: invokestatic 27	com/google/android/gms/internal/zzmt:zzb	(Ljava/io/Closeable;)V
    //   57: aload_1
    //   58: invokestatic 27	com/google/android/gms/internal/zzmt:zzb	(Ljava/io/Closeable;)V
    //   61: aload 7
    //   63: athrow
    //   64: iload_2
    //   65: ifeq +11 -> 76
    //   68: aload_0
    //   69: invokestatic 27	com/google/android/gms/internal/zzmt:zzb	(Ljava/io/Closeable;)V
    //   72: aload_1
    //   73: invokestatic 27	com/google/android/gms/internal/zzmt:zzb	(Ljava/io/Closeable;)V
    //   76: lload 5
    //   78: lreturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	79	0	paramInputStream	InputStream
    //   0	79	1	paramOutputStream	OutputStream
    //   0	79	2	paramBoolean	boolean
    //   0	79	3	paramInt	int
    //   3	34	4	arrayOfByte	byte[]
    //   6	71	5	l	long
    //   47	15	7	localObject	Object
    //   18	22	8	i	int
    // Exception table:
    //   from	to	target	type
    //   8	44	47	finally
  }
  
  public static void zza(ParcelFileDescriptor paramParcelFileDescriptor)
  {
    if (paramParcelFileDescriptor != null) {}
    try
    {
      paramParcelFileDescriptor.close();
      return;
    }
    catch (IOException localIOException)
    {
      for (;;) {}
    }
  }
  
  public static byte[] zza(InputStream paramInputStream, boolean paramBoolean)
    throws IOException
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    zza(paramInputStream, localByteArrayOutputStream, paramBoolean);
    return localByteArrayOutputStream.toByteArray();
  }
  
  public static void zzb(Closeable paramCloseable)
  {
    if (paramCloseable != null) {}
    try
    {
      paramCloseable.close();
      return;
    }
    catch (IOException localIOException)
    {
      for (;;) {}
    }
  }
  
  public static byte[] zzk(InputStream paramInputStream)
    throws IOException
  {
    return zza(paramInputStream, true);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzmt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */