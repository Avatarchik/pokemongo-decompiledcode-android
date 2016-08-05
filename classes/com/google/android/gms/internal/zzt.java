package com.google.android.gms.internal;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.impl.cookie.DateUtils;

public class zzt
  implements zzf
{
  protected static final boolean DEBUG = zzs.DEBUG;
  private static int zzao = 3000;
  private static int zzap = 4096;
  protected final zzy zzaq;
  protected final zzu zzar;
  
  public zzt(zzy paramzzy)
  {
    this(paramzzy, new zzu(zzap));
  }
  
  public zzt(zzy paramzzy, zzu paramzzu)
  {
    this.zzaq = paramzzy;
    this.zzar = paramzzu;
  }
  
  protected static Map<String, String> zza(Header[] paramArrayOfHeader)
  {
    TreeMap localTreeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
    for (int i = 0; i < paramArrayOfHeader.length; i++) {
      localTreeMap.put(paramArrayOfHeader[i].getName(), paramArrayOfHeader[i].getValue());
    }
    return localTreeMap;
  }
  
  private void zza(long paramLong, zzk<?> paramzzk, byte[] paramArrayOfByte, StatusLine paramStatusLine)
  {
    Object[] arrayOfObject;
    if ((DEBUG) || (paramLong > zzao))
    {
      arrayOfObject = new Object[5];
      arrayOfObject[0] = paramzzk;
      arrayOfObject[1] = Long.valueOf(paramLong);
      if (paramArrayOfByte == null) {
        break label91;
      }
    }
    label91:
    for (Object localObject = Integer.valueOf(paramArrayOfByte.length);; localObject = "null")
    {
      arrayOfObject[2] = localObject;
      arrayOfObject[3] = Integer.valueOf(paramStatusLine.getStatusCode());
      arrayOfObject[4] = Integer.valueOf(paramzzk.zzu().zze());
      zzs.zzb("HTTP response for request=<%s> [lifetime=%d], [size=%s], [rc=%d], [retryCount=%s]", arrayOfObject);
      return;
    }
  }
  
  private static void zza(String paramString, zzk<?> paramzzk, zzr paramzzr)
    throws zzr
  {
    zzo localzzo = paramzzk.zzu();
    int i = paramzzk.zzt();
    try
    {
      localzzo.zza(paramzzr);
      Object[] arrayOfObject2 = new Object[2];
      arrayOfObject2[0] = paramString;
      arrayOfObject2[1] = Integer.valueOf(i);
      paramzzk.zzc(String.format("%s-retry [timeout=%s]", arrayOfObject2));
      return;
    }
    catch (zzr localzzr)
    {
      Object[] arrayOfObject1 = new Object[2];
      arrayOfObject1[0] = paramString;
      arrayOfObject1[1] = Integer.valueOf(i);
      paramzzk.zzc(String.format("%s-timeout-giveup [timeout=%s]", arrayOfObject1));
      throw localzzr;
    }
  }
  
  private void zza(Map<String, String> paramMap, zzb.zza paramzza)
  {
    if (paramzza == null) {}
    for (;;)
    {
      return;
      if (paramzza.zzb != null) {
        paramMap.put("If-None-Match", paramzza.zzb);
      }
      if (paramzza.zzd > 0L) {
        paramMap.put("If-Modified-Since", DateUtils.formatDate(new Date(paramzza.zzd)));
      }
    }
  }
  
  private byte[] zza(HttpEntity paramHttpEntity)
    throws IOException, zzp
  {
    zzaa localzzaa = new zzaa(this.zzar, (int)paramHttpEntity.getContentLength());
    byte[] arrayOfByte1 = null;
    InputStream localInputStream;
    try
    {
      localInputStream = paramHttpEntity.getContent();
      if (localInputStream == null) {
        throw new zzp();
      }
    }
    finally {}
    try
    {
      paramHttpEntity.consumeContent();
      this.zzar.zza(arrayOfByte1);
      localzzaa.close();
      throw ((Throwable)localObject);
      arrayOfByte1 = this.zzar.zzb(1024);
      for (;;)
      {
        int i = localInputStream.read(arrayOfByte1);
        if (i == -1) {
          break;
        }
        localzzaa.write(arrayOfByte1, 0, i);
      }
      byte[] arrayOfByte2 = localzzaa.toByteArray();
      try
      {
        paramHttpEntity.consumeContent();
        this.zzar.zza(arrayOfByte1);
        localzzaa.close();
        return arrayOfByte2;
      }
      catch (IOException localIOException2)
      {
        for (;;)
        {
          zzs.zza("Error occured when calling consumingContent", new Object[0]);
        }
      }
    }
    catch (IOException localIOException1)
    {
      for (;;)
      {
        zzs.zza("Error occured when calling consumingContent", new Object[0]);
      }
    }
  }
  
  /* Error */
  public zzi zza(zzk<?> paramzzk)
    throws zzr
  {
    // Byte code:
    //   0: invokestatic 216	android/os/SystemClock:elapsedRealtime	()J
    //   3: lstore_2
    //   4: aconst_null
    //   5: astore 4
    //   7: invokestatic 222	java/util/Collections:emptyMap	()Ljava/util/Map;
    //   10: astore 5
    //   12: new 224	java/util/HashMap
    //   15: dup
    //   16: invokespecial 225	java/util/HashMap:<init>	()V
    //   19: astore 6
    //   21: aload_0
    //   22: aload 6
    //   24: aload_1
    //   25: invokevirtual 229	com/google/android/gms/internal/zzk:zzi	()Lcom/google/android/gms/internal/zzb$zza;
    //   28: invokespecial 231	com/google/android/gms/internal/zzt:zza	(Ljava/util/Map;Lcom/google/android/gms/internal/zzb$zza;)V
    //   31: aload_0
    //   32: getfield 40	com/google/android/gms/internal/zzt:zzaq	Lcom/google/android/gms/internal/zzy;
    //   35: aload_1
    //   36: aload 6
    //   38: invokeinterface 236 3 0
    //   43: astore 15
    //   45: aload 15
    //   47: invokeinterface 242 1 0
    //   52: astore 16
    //   54: aload 16
    //   56: invokeinterface 88 1 0
    //   61: istore 17
    //   63: aload 15
    //   65: invokeinterface 246 1 0
    //   70: invokestatic 248	com/google/android/gms/internal/zzt:zza	([Lorg/apache/http/Header;)Ljava/util/Map;
    //   73: astore 5
    //   75: iload 17
    //   77: sipush 304
    //   80: if_icmpne +81 -> 161
    //   83: aload_1
    //   84: invokevirtual 229	com/google/android/gms/internal/zzk:zzi	()Lcom/google/android/gms/internal/zzb$zza;
    //   87: astore 23
    //   89: aload 23
    //   91: ifnonnull +27 -> 118
    //   94: new 250	com/google/android/gms/internal/zzi
    //   97: dup
    //   98: sipush 304
    //   101: aconst_null
    //   102: aload 5
    //   104: iconst_1
    //   105: invokestatic 216	android/os/SystemClock:elapsedRealtime	()J
    //   108: lload_2
    //   109: lsub
    //   110: invokespecial 253	com/google/android/gms/internal/zzi:<init>	(I[BLjava/util/Map;ZJ)V
    //   113: astore 21
    //   115: goto +390 -> 505
    //   118: aload 23
    //   120: getfield 257	com/google/android/gms/internal/zzb$zza:zzg	Ljava/util/Map;
    //   123: aload 5
    //   125: invokeinterface 261 2 0
    //   130: new 250	com/google/android/gms/internal/zzi
    //   133: dup
    //   134: sipush 304
    //   137: aload 23
    //   139: getfield 265	com/google/android/gms/internal/zzb$zza:data	[B
    //   142: aload 23
    //   144: getfield 257	com/google/android/gms/internal/zzb$zza:zzg	Ljava/util/Map;
    //   147: iconst_1
    //   148: invokestatic 216	android/os/SystemClock:elapsedRealtime	()J
    //   151: lload_2
    //   152: lsub
    //   153: invokespecial 253	com/google/android/gms/internal/zzi:<init>	(I[BLjava/util/Map;ZJ)V
    //   156: astore 21
    //   158: goto +347 -> 505
    //   161: aload 15
    //   163: invokeinterface 269 1 0
    //   168: ifnull +77 -> 245
    //   171: aload_0
    //   172: aload 15
    //   174: invokeinterface 269 1 0
    //   179: invokespecial 271	com/google/android/gms/internal/zzt:zza	(Lorg/apache/http/HttpEntity;)[B
    //   182: astore 22
    //   184: aload 22
    //   186: astore 18
    //   188: aload_0
    //   189: invokestatic 216	android/os/SystemClock:elapsedRealtime	()J
    //   192: lload_2
    //   193: lsub
    //   194: aload_1
    //   195: aload 18
    //   197: aload 16
    //   199: invokespecial 273	com/google/android/gms/internal/zzt:zza	(JLcom/google/android/gms/internal/zzk;[BLorg/apache/http/StatusLine;)V
    //   202: iload 17
    //   204: sipush 200
    //   207: if_icmplt +11 -> 218
    //   210: iload 17
    //   212: sipush 299
    //   215: if_icmple +38 -> 253
    //   218: new 156	java/io/IOException
    //   221: dup
    //   222: invokespecial 274	java/io/IOException:<init>	()V
    //   225: athrow
    //   226: astore 14
    //   228: ldc_w 276
    //   231: aload_1
    //   232: new 278	com/google/android/gms/internal/zzq
    //   235: dup
    //   236: invokespecial 279	com/google/android/gms/internal/zzq:<init>	()V
    //   239: invokestatic 281	com/google/android/gms/internal/zzt:zza	(Ljava/lang/String;Lcom/google/android/gms/internal/zzk;Lcom/google/android/gms/internal/zzr;)V
    //   242: goto -238 -> 4
    //   245: iconst_0
    //   246: newarray <illegal type>
    //   248: astore 18
    //   250: goto -62 -> 188
    //   253: invokestatic 216	android/os/SystemClock:elapsedRealtime	()J
    //   256: lload_2
    //   257: lsub
    //   258: lstore 19
    //   260: new 250	com/google/android/gms/internal/zzi
    //   263: dup
    //   264: iload 17
    //   266: aload 18
    //   268: aload 5
    //   270: iconst_0
    //   271: lload 19
    //   273: invokespecial 253	com/google/android/gms/internal/zzi:<init>	(I[BLjava/util/Map;ZJ)V
    //   276: astore 21
    //   278: goto +227 -> 505
    //   281: astore 13
    //   283: ldc_w 283
    //   286: aload_1
    //   287: new 278	com/google/android/gms/internal/zzq
    //   290: dup
    //   291: invokespecial 279	com/google/android/gms/internal/zzq:<init>	()V
    //   294: invokestatic 281	com/google/android/gms/internal/zzt:zza	(Ljava/lang/String;Lcom/google/android/gms/internal/zzk;Lcom/google/android/gms/internal/zzr;)V
    //   297: goto -293 -> 4
    //   300: astore 12
    //   302: new 285	java/lang/RuntimeException
    //   305: dup
    //   306: new 287	java/lang/StringBuilder
    //   309: dup
    //   310: invokespecial 288	java/lang/StringBuilder:<init>	()V
    //   313: ldc_w 290
    //   316: invokevirtual 294	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   319: aload_1
    //   320: invokevirtual 297	com/google/android/gms/internal/zzk:getUrl	()Ljava/lang/String;
    //   323: invokevirtual 294	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   326: invokevirtual 300	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   329: aload 12
    //   331: invokespecial 303	java/lang/RuntimeException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   334: athrow
    //   335: astore 7
    //   337: aconst_null
    //   338: astore 8
    //   340: aload 4
    //   342: ifnull +109 -> 451
    //   345: aload 4
    //   347: invokeinterface 242 1 0
    //   352: invokeinterface 88 1 0
    //   357: istore 9
    //   359: iconst_2
    //   360: anewarray 4	java/lang/Object
    //   363: astore 10
    //   365: aload 10
    //   367: iconst_0
    //   368: iload 9
    //   370: invokestatic 82	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   373: aastore
    //   374: aload 10
    //   376: iconst_1
    //   377: aload_1
    //   378: invokevirtual 297	com/google/android/gms/internal/zzk:getUrl	()Ljava/lang/String;
    //   381: aastore
    //   382: ldc_w 305
    //   385: aload 10
    //   387: invokestatic 307	com/google/android/gms/internal/zzs:zzc	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   390: aload 8
    //   392: ifnull +79 -> 471
    //   395: new 250	com/google/android/gms/internal/zzi
    //   398: dup
    //   399: iload 9
    //   401: aload 8
    //   403: aload 5
    //   405: iconst_0
    //   406: invokestatic 216	android/os/SystemClock:elapsedRealtime	()J
    //   409: lload_2
    //   410: lsub
    //   411: invokespecial 253	com/google/android/gms/internal/zzi:<init>	(I[BLjava/util/Map;ZJ)V
    //   414: astore 11
    //   416: iload 9
    //   418: sipush 401
    //   421: if_icmpeq +11 -> 432
    //   424: iload 9
    //   426: sipush 403
    //   429: if_icmpne +32 -> 461
    //   432: ldc_w 309
    //   435: aload_1
    //   436: new 311	com/google/android/gms/internal/zza
    //   439: dup
    //   440: aload 11
    //   442: invokespecial 314	com/google/android/gms/internal/zza:<init>	(Lcom/google/android/gms/internal/zzi;)V
    //   445: invokestatic 281	com/google/android/gms/internal/zzt:zza	(Ljava/lang/String;Lcom/google/android/gms/internal/zzk;Lcom/google/android/gms/internal/zzr;)V
    //   448: goto -444 -> 4
    //   451: new 316	com/google/android/gms/internal/zzj
    //   454: dup
    //   455: aload 7
    //   457: invokespecial 319	com/google/android/gms/internal/zzj:<init>	(Ljava/lang/Throwable;)V
    //   460: athrow
    //   461: new 158	com/google/android/gms/internal/zzp
    //   464: dup
    //   465: aload 11
    //   467: invokespecial 320	com/google/android/gms/internal/zzp:<init>	(Lcom/google/android/gms/internal/zzi;)V
    //   470: athrow
    //   471: new 322	com/google/android/gms/internal/zzh
    //   474: dup
    //   475: aconst_null
    //   476: invokespecial 323	com/google/android/gms/internal/zzh:<init>	(Lcom/google/android/gms/internal/zzi;)V
    //   479: athrow
    //   480: astore 7
    //   482: aconst_null
    //   483: astore 8
    //   485: aload 15
    //   487: astore 4
    //   489: goto -149 -> 340
    //   492: astore 7
    //   494: aload 18
    //   496: astore 8
    //   498: aload 15
    //   500: astore 4
    //   502: goto -162 -> 340
    //   505: aload 21
    //   507: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	508	0	this	zzt
    //   0	508	1	paramzzk	zzk<?>
    //   3	407	2	l1	long
    //   5	496	4	localObject	Object
    //   10	394	5	localMap	Map
    //   19	18	6	localHashMap	java.util.HashMap
    //   335	121	7	localIOException1	IOException
    //   480	1	7	localIOException2	IOException
    //   492	1	7	localIOException3	IOException
    //   338	159	8	arrayOfByte1	byte[]
    //   357	73	9	i	int
    //   363	23	10	arrayOfObject	Object[]
    //   414	52	11	localzzi1	zzi
    //   300	30	12	localMalformedURLException	java.net.MalformedURLException
    //   281	1	13	localConnectTimeoutException	org.apache.http.conn.ConnectTimeoutException
    //   226	1	14	localSocketTimeoutException	java.net.SocketTimeoutException
    //   43	456	15	localHttpResponse	org.apache.http.HttpResponse
    //   52	146	16	localStatusLine	StatusLine
    //   61	204	17	j	int
    //   186	309	18	arrayOfByte2	byte[]
    //   258	14	19	l2	long
    //   113	393	21	localzzi2	zzi
    //   182	3	22	arrayOfByte3	byte[]
    //   87	56	23	localzza	zzb.zza
    // Exception table:
    //   from	to	target	type
    //   12	45	226	java/net/SocketTimeoutException
    //   45	184	226	java/net/SocketTimeoutException
    //   188	226	226	java/net/SocketTimeoutException
    //   245	250	226	java/net/SocketTimeoutException
    //   253	278	226	java/net/SocketTimeoutException
    //   12	45	281	org/apache/http/conn/ConnectTimeoutException
    //   45	184	281	org/apache/http/conn/ConnectTimeoutException
    //   188	226	281	org/apache/http/conn/ConnectTimeoutException
    //   245	250	281	org/apache/http/conn/ConnectTimeoutException
    //   253	278	281	org/apache/http/conn/ConnectTimeoutException
    //   12	45	300	java/net/MalformedURLException
    //   45	184	300	java/net/MalformedURLException
    //   188	226	300	java/net/MalformedURLException
    //   245	250	300	java/net/MalformedURLException
    //   253	278	300	java/net/MalformedURLException
    //   12	45	335	java/io/IOException
    //   45	184	480	java/io/IOException
    //   245	250	480	java/io/IOException
    //   188	226	492	java/io/IOException
    //   253	278	492	java/io/IOException
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */