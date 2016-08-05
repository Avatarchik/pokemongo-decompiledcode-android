package com.google.android.gms.internal;

import android.os.SystemClock;
import java.io.EOFException;
import java.io.File;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class zzv
  implements zzb
{
  private final int zzaA;
  private final Map<String, zza> zzax = new LinkedHashMap(16, 0.75F, true);
  private long zzay = 0L;
  private final File zzaz;
  
  public zzv(File paramFile)
  {
    this(paramFile, 5242880);
  }
  
  public zzv(File paramFile, int paramInt)
  {
    this.zzaz = paramFile;
    this.zzaA = paramInt;
  }
  
  private void removeEntry(String paramString)
  {
    zza localzza = (zza)this.zzax.get(paramString);
    if (localzza != null)
    {
      this.zzay -= localzza.zzaB;
      this.zzax.remove(paramString);
    }
  }
  
  private static int zza(InputStream paramInputStream)
    throws IOException
  {
    int i = paramInputStream.read();
    if (i == -1) {
      throw new EOFException();
    }
    return i;
  }
  
  static void zza(OutputStream paramOutputStream, int paramInt)
    throws IOException
  {
    paramOutputStream.write(0xFF & paramInt >> 0);
    paramOutputStream.write(0xFF & paramInt >> 8);
    paramOutputStream.write(0xFF & paramInt >> 16);
    paramOutputStream.write(0xFF & paramInt >> 24);
  }
  
  static void zza(OutputStream paramOutputStream, long paramLong)
    throws IOException
  {
    paramOutputStream.write((byte)(int)(paramLong >>> 0));
    paramOutputStream.write((byte)(int)(paramLong >>> 8));
    paramOutputStream.write((byte)(int)(paramLong >>> 16));
    paramOutputStream.write((byte)(int)(paramLong >>> 24));
    paramOutputStream.write((byte)(int)(paramLong >>> 32));
    paramOutputStream.write((byte)(int)(paramLong >>> 40));
    paramOutputStream.write((byte)(int)(paramLong >>> 48));
    paramOutputStream.write((byte)(int)(paramLong >>> 56));
  }
  
  static void zza(OutputStream paramOutputStream, String paramString)
    throws IOException
  {
    byte[] arrayOfByte = paramString.getBytes("UTF-8");
    zza(paramOutputStream, arrayOfByte.length);
    paramOutputStream.write(arrayOfByte, 0, arrayOfByte.length);
  }
  
  private void zza(String paramString, zza paramzza)
  {
    if (!this.zzax.containsKey(paramString)) {}
    zza localzza;
    for (this.zzay += paramzza.zzaB;; this.zzay += paramzza.zzaB - localzza.zzaB)
    {
      this.zzax.put(paramString, paramzza);
      return;
      localzza = (zza)this.zzax.get(paramString);
    }
  }
  
  static void zza(Map<String, String> paramMap, OutputStream paramOutputStream)
    throws IOException
  {
    if (paramMap != null)
    {
      zza(paramOutputStream, paramMap.size());
      Iterator localIterator = paramMap.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        zza(paramOutputStream, (String)localEntry.getKey());
        zza(paramOutputStream, (String)localEntry.getValue());
      }
    }
    zza(paramOutputStream, 0);
  }
  
  private static byte[] zza(InputStream paramInputStream, int paramInt)
    throws IOException
  {
    byte[] arrayOfByte = new byte[paramInt];
    int i = 0;
    while (i < paramInt)
    {
      int j = paramInputStream.read(arrayOfByte, i, paramInt - i);
      if (j == -1) {
        break;
      }
      i += j;
    }
    if (i != paramInt) {
      throw new IOException("Expected " + paramInt + " bytes, read " + i + " bytes");
    }
    return arrayOfByte;
  }
  
  static int zzb(InputStream paramInputStream)
    throws IOException
  {
    return 0x0 | zza(paramInputStream) << 0 | zza(paramInputStream) << 8 | zza(paramInputStream) << 16 | zza(paramInputStream) << 24;
  }
  
  static long zzc(InputStream paramInputStream)
    throws IOException
  {
    return 0L | (0xFF & zza(paramInputStream)) << 0 | (0xFF & zza(paramInputStream)) << 8 | (0xFF & zza(paramInputStream)) << 16 | (0xFF & zza(paramInputStream)) << 24 | (0xFF & zza(paramInputStream)) << 32 | (0xFF & zza(paramInputStream)) << 40 | (0xFF & zza(paramInputStream)) << 48 | (0xFF & zza(paramInputStream)) << 56;
  }
  
  private void zzc(int paramInt)
  {
    if (this.zzay + paramInt < this.zzaA) {}
    label61:
    label120:
    label248:
    label260:
    for (;;)
    {
      return;
      if (zzs.DEBUG) {
        zzs.zza("Pruning old cache entries.", new Object[0]);
      }
      long l1 = this.zzay;
      long l2 = SystemClock.elapsedRealtime();
      Iterator localIterator = this.zzax.entrySet().iterator();
      int i = 0;
      zza localzza;
      int j;
      if (localIterator.hasNext())
      {
        localzza = (zza)((Map.Entry)localIterator.next()).getValue();
        if (zzf(localzza.key).delete())
        {
          this.zzay -= localzza.zzaB;
          localIterator.remove();
          j = i + 1;
          if ((float)(this.zzay + paramInt) >= 0.9F * this.zzaA) {
            break label248;
          }
        }
      }
      for (;;)
      {
        if (!zzs.DEBUG) {
          break label260;
        }
        Object[] arrayOfObject1 = new Object[3];
        arrayOfObject1[0] = Integer.valueOf(j);
        arrayOfObject1[1] = Long.valueOf(this.zzay - l1);
        arrayOfObject1[2] = Long.valueOf(SystemClock.elapsedRealtime() - l2);
        zzs.zza("pruned %d files, %d bytes, %d ms", arrayOfObject1);
        break;
        Object[] arrayOfObject2 = new Object[2];
        arrayOfObject2[0] = localzza.key;
        arrayOfObject2[1] = zze(localzza.key);
        zzs.zzb("Could not delete cache entry for key=%s, filename=%s", arrayOfObject2);
        break label120;
        i = j;
        break label61;
        j = i;
      }
    }
  }
  
  static String zzd(InputStream paramInputStream)
    throws IOException
  {
    return new String(zza(paramInputStream, (int)zzc(paramInputStream)), "UTF-8");
  }
  
  private String zze(String paramString)
  {
    int i = paramString.length() / 2;
    String str = String.valueOf(paramString.substring(0, i).hashCode());
    return str + String.valueOf(paramString.substring(i).hashCode());
  }
  
  static Map<String, String> zze(InputStream paramInputStream)
    throws IOException
  {
    int i = zzb(paramInputStream);
    if (i == 0) {}
    for (Object localObject = Collections.emptyMap();; localObject = new HashMap(i)) {
      for (int j = 0; j < i; j++) {
        ((Map)localObject).put(zzd(paramInputStream).intern(), zzd(paramInputStream).intern());
      }
    }
    return (Map<String, String>)localObject;
  }
  
  /**
   * @deprecated
   */
  public void remove(String paramString)
  {
    try
    {
      boolean bool = zzf(paramString).delete();
      removeEntry(paramString);
      if (!bool)
      {
        Object[] arrayOfObject = new Object[2];
        arrayOfObject[0] = paramString;
        arrayOfObject[1] = zze(paramString);
        zzs.zzb("Could not delete cache entry for key=%s, filename=%s", arrayOfObject);
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /* Error */
  /**
   * @deprecated
   */
  public zzb.zza zza(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 40	com/google/android/gms/internal/zzv:zzax	Ljava/util/Map;
    //   6: aload_1
    //   7: invokeinterface 54 2 0
    //   12: checkcast 13	com/google/android/gms/internal/zzv$zza
    //   15: astore_3
    //   16: aload_3
    //   17: ifnonnull +11 -> 28
    //   20: aconst_null
    //   21: astore 9
    //   23: aload_0
    //   24: monitorexit
    //   25: aload 9
    //   27: areturn
    //   28: aload_0
    //   29: aload_1
    //   30: invokevirtual 196	com/google/android/gms/internal/zzv:zzf	(Ljava/lang/String;)Ljava/io/File;
    //   33: astore 4
    //   35: new 10	com/google/android/gms/internal/zzv$zzb
    //   38: dup
    //   39: new 272	java/io/FileInputStream
    //   42: dup
    //   43: aload 4
    //   45: invokespecial 274	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   48: aconst_null
    //   49: invokespecial 277	com/google/android/gms/internal/zzv$zzb:<init>	(Ljava/io/InputStream;Lcom/google/android/gms/internal/zzv$1;)V
    //   52: astore 5
    //   54: aload 5
    //   56: invokestatic 280	com/google/android/gms/internal/zzv$zza:zzf	(Ljava/io/InputStream;)Lcom/google/android/gms/internal/zzv$zza;
    //   59: pop
    //   60: aload_3
    //   61: aload 5
    //   63: aload 4
    //   65: invokevirtual 282	java/io/File:length	()J
    //   68: aload 5
    //   70: invokestatic 285	com/google/android/gms/internal/zzv$zzb:zza	(Lcom/google/android/gms/internal/zzv$zzb;)I
    //   73: i2l
    //   74: lsub
    //   75: l2i
    //   76: invokestatic 231	com/google/android/gms/internal/zzv:zza	(Ljava/io/InputStream;I)[B
    //   79: invokevirtual 288	com/google/android/gms/internal/zzv$zza:zzb	([B)Lcom/google/android/gms/internal/zzb$zza;
    //   82: astore 13
    //   84: aload 13
    //   86: astore 9
    //   88: aload 5
    //   90: ifnull -67 -> 23
    //   93: aload 5
    //   95: invokevirtual 291	com/google/android/gms/internal/zzv$zzb:close	()V
    //   98: goto -75 -> 23
    //   101: astore 14
    //   103: aconst_null
    //   104: astore 9
    //   106: goto -83 -> 23
    //   109: astore 6
    //   111: aconst_null
    //   112: astore 5
    //   114: iconst_2
    //   115: anewarray 4	java/lang/Object
    //   118: astore 10
    //   120: aload 10
    //   122: iconst_0
    //   123: aload 4
    //   125: invokevirtual 294	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   128: aastore
    //   129: aload 10
    //   131: iconst_1
    //   132: aload 6
    //   134: invokevirtual 295	java/io/IOException:toString	()Ljava/lang/String;
    //   137: aastore
    //   138: ldc_w 297
    //   141: aload 10
    //   143: invokestatic 225	com/google/android/gms/internal/zzs:zzb	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   146: aload_0
    //   147: aload_1
    //   148: invokevirtual 299	com/google/android/gms/internal/zzv:remove	(Ljava/lang/String;)V
    //   151: aload 5
    //   153: ifnull +8 -> 161
    //   156: aload 5
    //   158: invokevirtual 291	com/google/android/gms/internal/zzv$zzb:close	()V
    //   161: aconst_null
    //   162: astore 9
    //   164: goto -141 -> 23
    //   167: astore 11
    //   169: aconst_null
    //   170: astore 9
    //   172: goto -149 -> 23
    //   175: astore 7
    //   177: aconst_null
    //   178: astore 5
    //   180: aload 5
    //   182: ifnull +8 -> 190
    //   185: aload 5
    //   187: invokevirtual 291	com/google/android/gms/internal/zzv$zzb:close	()V
    //   190: aload 7
    //   192: athrow
    //   193: astore_2
    //   194: aload_0
    //   195: monitorexit
    //   196: aload_2
    //   197: athrow
    //   198: astore 8
    //   200: aconst_null
    //   201: astore 9
    //   203: goto -180 -> 23
    //   206: astore 7
    //   208: goto -28 -> 180
    //   211: astore 6
    //   213: goto -99 -> 114
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	216	0	this	zzv
    //   0	216	1	paramString	String
    //   193	4	2	localObject1	Object
    //   15	46	3	localzza	zza
    //   33	91	4	localFile	File
    //   52	134	5	localzzb	zzb
    //   109	24	6	localIOException1	IOException
    //   211	1	6	localIOException2	IOException
    //   175	16	7	localObject2	Object
    //   206	1	7	localObject3	Object
    //   198	1	8	localIOException3	IOException
    //   21	181	9	localObject4	Object
    //   118	24	10	arrayOfObject	Object[]
    //   167	1	11	localIOException4	IOException
    //   82	3	13	localzza1	zzb.zza
    //   101	1	14	localIOException5	IOException
    // Exception table:
    //   from	to	target	type
    //   93	98	101	java/io/IOException
    //   35	54	109	java/io/IOException
    //   156	161	167	java/io/IOException
    //   35	54	175	finally
    //   2	16	193	finally
    //   28	35	193	finally
    //   93	98	193	finally
    //   156	161	193	finally
    //   185	190	193	finally
    //   190	193	193	finally
    //   185	190	198	java/io/IOException
    //   54	84	206	finally
    //   114	151	206	finally
    //   54	84	211	java/io/IOException
  }
  
  /* Error */
  /**
   * @deprecated
   */
  public void zza()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 44	com/google/android/gms/internal/zzv:zzaz	Ljava/io/File;
    //   6: invokevirtual 302	java/io/File:exists	()Z
    //   9: ifne +41 -> 50
    //   12: aload_0
    //   13: getfield 44	com/google/android/gms/internal/zzv:zzaz	Ljava/io/File;
    //   16: invokevirtual 305	java/io/File:mkdirs	()Z
    //   19: ifne +28 -> 47
    //   22: iconst_1
    //   23: anewarray 4	java/lang/Object
    //   26: astore 17
    //   28: aload 17
    //   30: iconst_0
    //   31: aload_0
    //   32: getfield 44	com/google/android/gms/internal/zzv:zzaz	Ljava/io/File;
    //   35: invokevirtual 294	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   38: aastore
    //   39: ldc_w 307
    //   42: aload 17
    //   44: invokestatic 309	com/google/android/gms/internal/zzs:zzc	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   47: aload_0
    //   48: monitorexit
    //   49: return
    //   50: aload_0
    //   51: getfield 44	com/google/android/gms/internal/zzv:zzaz	Ljava/io/File;
    //   54: invokevirtual 313	java/io/File:listFiles	()[Ljava/io/File;
    //   57: astore_2
    //   58: aload_2
    //   59: ifnull -12 -> 47
    //   62: aload_2
    //   63: arraylength
    //   64: istore_3
    //   65: iconst_0
    //   66: istore 4
    //   68: iload 4
    //   70: iload_3
    //   71: if_icmpge -24 -> 47
    //   74: aload_2
    //   75: iload 4
    //   77: aaload
    //   78: astore 5
    //   80: aconst_null
    //   81: astore 6
    //   83: new 315	java/io/BufferedInputStream
    //   86: dup
    //   87: new 272	java/io/FileInputStream
    //   90: dup
    //   91: aload 5
    //   93: invokespecial 274	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   96: invokespecial 318	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;)V
    //   99: astore 7
    //   101: aload 7
    //   103: invokestatic 280	com/google/android/gms/internal/zzv$zza:zzf	(Ljava/io/InputStream;)Lcom/google/android/gms/internal/zzv$zza;
    //   106: astore 14
    //   108: aload 14
    //   110: aload 5
    //   112: invokevirtual 282	java/io/File:length	()J
    //   115: putfield 57	com/google/android/gms/internal/zzv$zza:zzaB	J
    //   118: aload_0
    //   119: aload 14
    //   121: getfield 192	com/google/android/gms/internal/zzv$zza:key	Ljava/lang/String;
    //   124: aload 14
    //   126: invokespecial 320	com/google/android/gms/internal/zzv:zza	(Ljava/lang/String;Lcom/google/android/gms/internal/zzv$zza;)V
    //   129: aload 7
    //   131: ifnull +8 -> 139
    //   134: aload 7
    //   136: invokevirtual 321	java/io/BufferedInputStream:close	()V
    //   139: iinc 4 1
    //   142: goto -74 -> 68
    //   145: astore 16
    //   147: aconst_null
    //   148: astore 7
    //   150: aload 5
    //   152: ifnull +9 -> 161
    //   155: aload 5
    //   157: invokevirtual 201	java/io/File:delete	()Z
    //   160: pop
    //   161: aload 7
    //   163: ifnull -24 -> 139
    //   166: aload 7
    //   168: invokevirtual 321	java/io/BufferedInputStream:close	()V
    //   171: goto -32 -> 139
    //   174: astore 9
    //   176: goto -37 -> 139
    //   179: astore 11
    //   181: aload 6
    //   183: ifnull +8 -> 191
    //   186: aload 6
    //   188: invokevirtual 321	java/io/BufferedInputStream:close	()V
    //   191: aload 11
    //   193: athrow
    //   194: astore_1
    //   195: aload_0
    //   196: monitorexit
    //   197: aload_1
    //   198: athrow
    //   199: astore 15
    //   201: goto -62 -> 139
    //   204: astore 12
    //   206: goto -15 -> 191
    //   209: astore 10
    //   211: aload 7
    //   213: astore 6
    //   215: aload 10
    //   217: astore 11
    //   219: goto -38 -> 181
    //   222: astore 8
    //   224: goto -74 -> 150
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	227	0	this	zzv
    //   194	4	1	localObject1	Object
    //   57	18	2	arrayOfFile	File[]
    //   64	8	3	i	int
    //   66	74	4	j	int
    //   78	78	5	localFile	File
    //   81	133	6	localObject2	Object
    //   99	113	7	localBufferedInputStream	java.io.BufferedInputStream
    //   222	1	8	localIOException1	IOException
    //   174	1	9	localIOException2	IOException
    //   209	7	10	localObject3	Object
    //   179	13	11	localObject4	Object
    //   217	1	11	localObject5	Object
    //   204	1	12	localIOException3	IOException
    //   106	19	14	localzza	zza
    //   199	1	15	localIOException4	IOException
    //   145	1	16	localIOException5	IOException
    //   26	17	17	arrayOfObject	Object[]
    // Exception table:
    //   from	to	target	type
    //   83	101	145	java/io/IOException
    //   166	171	174	java/io/IOException
    //   83	101	179	finally
    //   2	47	194	finally
    //   50	80	194	finally
    //   134	139	194	finally
    //   166	171	194	finally
    //   186	191	194	finally
    //   191	194	194	finally
    //   134	139	199	java/io/IOException
    //   186	191	204	java/io/IOException
    //   101	129	209	finally
    //   155	161	209	finally
    //   101	129	222	java/io/IOException
  }
  
  /* Error */
  /**
   * @deprecated
   */
  public void zza(String paramString, zzb.zza paramzza)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_2
    //   4: getfield 328	com/google/android/gms/internal/zzb$zza:data	[B
    //   7: arraylength
    //   8: invokespecial 330	com/google/android/gms/internal/zzv:zzc	(I)V
    //   11: aload_0
    //   12: aload_1
    //   13: invokevirtual 196	com/google/android/gms/internal/zzv:zzf	(Ljava/lang/String;)Ljava/io/File;
    //   16: astore 4
    //   18: new 332	java/io/FileOutputStream
    //   21: dup
    //   22: aload 4
    //   24: invokespecial 333	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   27: astore 5
    //   29: new 13	com/google/android/gms/internal/zzv$zza
    //   32: dup
    //   33: aload_1
    //   34: aload_2
    //   35: invokespecial 335	com/google/android/gms/internal/zzv$zza:<init>	(Ljava/lang/String;Lcom/google/android/gms/internal/zzb$zza;)V
    //   38: astore 6
    //   40: aload 6
    //   42: aload 5
    //   44: invokevirtual 338	com/google/android/gms/internal/zzv$zza:zza	(Ljava/io/OutputStream;)Z
    //   47: ifne +75 -> 122
    //   50: aload 5
    //   52: invokevirtual 339	java/io/FileOutputStream:close	()V
    //   55: iconst_1
    //   56: anewarray 4	java/lang/Object
    //   59: astore 9
    //   61: aload 9
    //   63: iconst_0
    //   64: aload 4
    //   66: invokevirtual 294	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   69: aastore
    //   70: ldc_w 341
    //   73: aload 9
    //   75: invokestatic 225	com/google/android/gms/internal/zzs:zzb	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   78: new 63	java/io/IOException
    //   81: dup
    //   82: invokespecial 342	java/io/IOException:<init>	()V
    //   85: athrow
    //   86: astore 7
    //   88: aload 4
    //   90: invokevirtual 201	java/io/File:delete	()Z
    //   93: ifne +26 -> 119
    //   96: iconst_1
    //   97: anewarray 4	java/lang/Object
    //   100: astore 8
    //   102: aload 8
    //   104: iconst_0
    //   105: aload 4
    //   107: invokevirtual 294	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   110: aastore
    //   111: ldc_w 344
    //   114: aload 8
    //   116: invokestatic 225	com/google/android/gms/internal/zzs:zzb	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   119: aload_0
    //   120: monitorexit
    //   121: return
    //   122: aload 5
    //   124: aload_2
    //   125: getfield 328	com/google/android/gms/internal/zzb$zza:data	[B
    //   128: invokevirtual 347	java/io/FileOutputStream:write	([B)V
    //   131: aload 5
    //   133: invokevirtual 339	java/io/FileOutputStream:close	()V
    //   136: aload_0
    //   137: aload_1
    //   138: aload 6
    //   140: invokespecial 320	com/google/android/gms/internal/zzv:zza	(Ljava/lang/String;Lcom/google/android/gms/internal/zzv$zza;)V
    //   143: goto -24 -> 119
    //   146: astore_3
    //   147: aload_0
    //   148: monitorexit
    //   149: aload_3
    //   150: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	151	0	this	zzv
    //   0	151	1	paramString	String
    //   0	151	2	paramzza	zzb.zza
    //   146	4	3	localObject	Object
    //   16	90	4	localFile	File
    //   27	105	5	localFileOutputStream	java.io.FileOutputStream
    //   38	101	6	localzza	zza
    //   86	1	7	localIOException	IOException
    //   100	15	8	arrayOfObject1	Object[]
    //   59	15	9	arrayOfObject2	Object[]
    // Exception table:
    //   from	to	target	type
    //   18	86	86	java/io/IOException
    //   122	143	86	java/io/IOException
    //   2	18	146	finally
    //   18	86	146	finally
    //   88	119	146	finally
    //   122	143	146	finally
  }
  
  public File zzf(String paramString)
  {
    return new File(this.zzaz, zze(paramString));
  }
  
  private static class zzb
    extends FilterInputStream
  {
    private int zzaC = 0;
    
    private zzb(InputStream paramInputStream)
    {
      super();
    }
    
    public int read()
      throws IOException
    {
      int i = super.read();
      if (i != -1) {
        this.zzaC = (1 + this.zzaC);
      }
      return i;
    }
    
    public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
      throws IOException
    {
      int i = super.read(paramArrayOfByte, paramInt1, paramInt2);
      if (i != -1) {
        this.zzaC = (i + this.zzaC);
      }
      return i;
    }
  }
  
  static class zza
  {
    public String key;
    public long zzaB;
    public String zzb;
    public long zzc;
    public long zzd;
    public long zze;
    public long zzf;
    public Map<String, String> zzg;
    
    private zza() {}
    
    public zza(String paramString, zzb.zza paramzza)
    {
      this.key = paramString;
      this.zzaB = paramzza.data.length;
      this.zzb = paramzza.zzb;
      this.zzc = paramzza.zzc;
      this.zzd = paramzza.zzd;
      this.zze = paramzza.zze;
      this.zzf = paramzza.zzf;
      this.zzg = paramzza.zzg;
    }
    
    public static zza zzf(InputStream paramInputStream)
      throws IOException
    {
      zza localzza = new zza();
      if (zzv.zzb(paramInputStream) != 538247942) {
        throw new IOException();
      }
      localzza.key = zzv.zzd(paramInputStream);
      localzza.zzb = zzv.zzd(paramInputStream);
      if (localzza.zzb.equals("")) {
        localzza.zzb = null;
      }
      localzza.zzc = zzv.zzc(paramInputStream);
      localzza.zzd = zzv.zzc(paramInputStream);
      localzza.zze = zzv.zzc(paramInputStream);
      localzza.zzf = zzv.zzc(paramInputStream);
      localzza.zzg = zzv.zze(paramInputStream);
      return localzza;
    }
    
    public boolean zza(OutputStream paramOutputStream)
    {
      bool = true;
      try
      {
        zzv.zza(paramOutputStream, 538247942);
        zzv.zza(paramOutputStream, this.key);
        if (this.zzb == null) {}
        for (String str = "";; str = this.zzb)
        {
          zzv.zza(paramOutputStream, str);
          zzv.zza(paramOutputStream, this.zzc);
          zzv.zza(paramOutputStream, this.zzd);
          zzv.zza(paramOutputStream, this.zze);
          zzv.zza(paramOutputStream, this.zzf);
          zzv.zza(this.zzg, paramOutputStream);
          paramOutputStream.flush();
          break;
        }
        Object[] arrayOfObject;
        return bool;
      }
      catch (IOException localIOException)
      {
        arrayOfObject = new Object[bool];
        arrayOfObject[0] = localIOException.toString();
        zzs.zzb("%s", arrayOfObject);
        bool = false;
      }
    }
    
    public zzb.zza zzb(byte[] paramArrayOfByte)
    {
      zzb.zza localzza = new zzb.zza();
      localzza.data = paramArrayOfByte;
      localzza.zzb = this.zzb;
      localzza.zzc = this.zzc;
      localzza.zzd = this.zzd;
      localzza.zze = this.zze;
      localzza.zzf = this.zzf;
      localzza.zzg = this.zzg;
      return localzza;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzv.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */