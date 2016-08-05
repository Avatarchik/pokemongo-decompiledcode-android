package com.google.android.gms.internal;

import android.content.Context;

@zzgr
public final class zzij
  extends zzhz
{
  private final Context mContext;
  private final String zzF;
  private String zzIa = null;
  private final String zzqV;
  
  public zzij(Context paramContext, String paramString1, String paramString2)
  {
    this.mContext = paramContext;
    this.zzqV = paramString1;
    this.zzF = paramString2;
  }
  
  public zzij(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    this.mContext = paramContext;
    this.zzqV = paramString1;
    this.zzF = paramString2;
    this.zzIa = paramString3;
  }
  
  public void onStop() {}
  
  /* Error */
  public void zzbn()
  {
    // Byte code:
    //   0: new 35	java/lang/StringBuilder
    //   3: dup
    //   4: invokespecial 36	java/lang/StringBuilder:<init>	()V
    //   7: ldc 38
    //   9: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   12: aload_0
    //   13: getfield 24	com/google/android/gms/internal/zzij:zzF	Ljava/lang/String;
    //   16: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   19: invokevirtual 46	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   22: invokestatic 52	com/google/android/gms/ads/internal/util/client/zzb:v	(Ljava/lang/String;)V
    //   25: new 54	java/net/URL
    //   28: dup
    //   29: aload_0
    //   30: getfield 24	com/google/android/gms/internal/zzij:zzF	Ljava/lang/String;
    //   33: invokespecial 56	java/net/URL:<init>	(Ljava/lang/String;)V
    //   36: invokevirtual 60	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   39: checkcast 62	java/net/HttpURLConnection
    //   42: astore 4
    //   44: aload_0
    //   45: getfield 18	com/google/android/gms/internal/zzij:zzIa	Ljava/lang/String;
    //   48: invokestatic 68	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   51: ifeq +84 -> 135
    //   54: invokestatic 74	com/google/android/gms/ads/internal/zzp:zzbv	()Lcom/google/android/gms/internal/zzid;
    //   57: aload_0
    //   58: getfield 20	com/google/android/gms/internal/zzij:mContext	Landroid/content/Context;
    //   61: aload_0
    //   62: getfield 22	com/google/android/gms/internal/zzij:zzqV	Ljava/lang/String;
    //   65: iconst_1
    //   66: aload 4
    //   68: invokevirtual 80	com/google/android/gms/internal/zzid:zza	(Landroid/content/Context;Ljava/lang/String;ZLjava/net/HttpURLConnection;)V
    //   71: aload 4
    //   73: invokevirtual 84	java/net/HttpURLConnection:getResponseCode	()I
    //   76: istore 6
    //   78: iload 6
    //   80: sipush 200
    //   83: if_icmplt +11 -> 94
    //   86: iload 6
    //   88: sipush 300
    //   91: if_icmplt +38 -> 129
    //   94: new 35	java/lang/StringBuilder
    //   97: dup
    //   98: invokespecial 36	java/lang/StringBuilder:<init>	()V
    //   101: ldc 86
    //   103: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   106: iload 6
    //   108: invokevirtual 89	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   111: ldc 91
    //   113: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   116: aload_0
    //   117: getfield 24	com/google/android/gms/internal/zzij:zzF	Ljava/lang/String;
    //   120: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   123: invokevirtual 46	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   126: invokestatic 94	com/google/android/gms/ads/internal/util/client/zzb:zzaH	(Ljava/lang/String;)V
    //   129: aload 4
    //   131: invokevirtual 97	java/net/HttpURLConnection:disconnect	()V
    //   134: return
    //   135: invokestatic 74	com/google/android/gms/ads/internal/zzp:zzbv	()Lcom/google/android/gms/internal/zzid;
    //   138: aload_0
    //   139: getfield 20	com/google/android/gms/internal/zzij:mContext	Landroid/content/Context;
    //   142: aload_0
    //   143: getfield 22	com/google/android/gms/internal/zzij:zzqV	Ljava/lang/String;
    //   146: iconst_1
    //   147: aload 4
    //   149: aload_0
    //   150: getfield 18	com/google/android/gms/internal/zzij:zzIa	Ljava/lang/String;
    //   153: invokevirtual 100	com/google/android/gms/internal/zzid:zza	(Landroid/content/Context;Ljava/lang/String;ZLjava/net/HttpURLConnection;Ljava/lang/String;)V
    //   156: goto -85 -> 71
    //   159: astore 5
    //   161: aload 4
    //   163: invokevirtual 97	java/net/HttpURLConnection:disconnect	()V
    //   166: aload 5
    //   168: athrow
    //   169: astore_3
    //   170: new 35	java/lang/StringBuilder
    //   173: dup
    //   174: invokespecial 36	java/lang/StringBuilder:<init>	()V
    //   177: ldc 102
    //   179: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   182: aload_0
    //   183: getfield 24	com/google/android/gms/internal/zzij:zzF	Ljava/lang/String;
    //   186: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   189: ldc 104
    //   191: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   194: aload_3
    //   195: invokevirtual 107	java/lang/IndexOutOfBoundsException:getMessage	()Ljava/lang/String;
    //   198: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   201: invokevirtual 46	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   204: invokestatic 94	com/google/android/gms/ads/internal/util/client/zzb:zzaH	(Ljava/lang/String;)V
    //   207: goto -73 -> 134
    //   210: astore_2
    //   211: new 35	java/lang/StringBuilder
    //   214: dup
    //   215: invokespecial 36	java/lang/StringBuilder:<init>	()V
    //   218: ldc 109
    //   220: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   223: aload_0
    //   224: getfield 24	com/google/android/gms/internal/zzij:zzF	Ljava/lang/String;
    //   227: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   230: ldc 104
    //   232: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   235: aload_2
    //   236: invokevirtual 110	java/io/IOException:getMessage	()Ljava/lang/String;
    //   239: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   242: invokevirtual 46	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   245: invokestatic 94	com/google/android/gms/ads/internal/util/client/zzb:zzaH	(Ljava/lang/String;)V
    //   248: goto -114 -> 134
    //   251: astore_1
    //   252: new 35	java/lang/StringBuilder
    //   255: dup
    //   256: invokespecial 36	java/lang/StringBuilder:<init>	()V
    //   259: ldc 109
    //   261: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   264: aload_0
    //   265: getfield 24	com/google/android/gms/internal/zzij:zzF	Ljava/lang/String;
    //   268: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   271: ldc 104
    //   273: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   276: aload_1
    //   277: invokevirtual 111	java/lang/RuntimeException:getMessage	()Ljava/lang/String;
    //   280: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   283: invokevirtual 46	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   286: invokestatic 94	com/google/android/gms/ads/internal/util/client/zzb:zzaH	(Ljava/lang/String;)V
    //   289: goto -155 -> 134
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	292	0	this	zzij
    //   251	26	1	localRuntimeException	RuntimeException
    //   210	26	2	localIOException	java.io.IOException
    //   169	26	3	localIndexOutOfBoundsException	IndexOutOfBoundsException
    //   42	120	4	localHttpURLConnection	java.net.HttpURLConnection
    //   159	8	5	localObject	Object
    //   76	31	6	i	int
    // Exception table:
    //   from	to	target	type
    //   44	129	159	finally
    //   135	156	159	finally
    //   0	44	169	java/lang/IndexOutOfBoundsException
    //   129	134	169	java/lang/IndexOutOfBoundsException
    //   161	169	169	java/lang/IndexOutOfBoundsException
    //   0	44	210	java/io/IOException
    //   129	134	210	java/io/IOException
    //   161	169	210	java/io/IOException
    //   0	44	251	java/lang/RuntimeException
    //   129	134	251	java/lang/RuntimeException
    //   161	169	251	java/lang/RuntimeException
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzij.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */