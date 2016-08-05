package com.unity3d.player;

import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

class UnityWebRequest
  implements Runnable
{
  private static final String[] e;
  private static volatile SSLSocketFactory f;
  private long a;
  private String b;
  private String c;
  private Map d;
  
  static
  {
    String[] arrayOfString = new String[2];
    arrayOfString[0] = "TLSv1.2";
    arrayOfString[1] = "TLSv1.1";
    e = arrayOfString;
  }
  
  UnityWebRequest(long paramLong, String paramString1, Map paramMap, String paramString2)
  {
    this.a = paramLong;
    this.b = paramString2;
    this.c = paramString1;
    this.d = paramMap;
  }
  
  private static native void contentLengthCallback(long paramLong, int paramInt);
  
  private static native boolean downloadCallback(long paramLong, ByteBuffer paramByteBuffer, int paramInt);
  
  private static native void errorCallback(long paramLong, int paramInt, String paramString);
  
  private static SSLSocketFactory getSSLSocketFactory()
  {
    Object localObject1 = null;
    if (q.g) {}
    for (;;)
    {
      return (SSLSocketFactory)localObject1;
      if (f != null)
      {
        localObject1 = f;
        continue;
      }
      label135:
      synchronized (e)
      {
        String[] arrayOfString2 = e;
        int i = arrayOfString2.length;
        int j = 0;
        for (;;)
        {
          if (j >= i) {
            break label135;
          }
          String str = arrayOfString2[j];
          try
          {
            SSLContext localSSLContext = SSLContext.getInstance(str);
            localSSLContext.init(null, null, null);
            SSLSocketFactory localSSLSocketFactory = localSSLContext.getSocketFactory();
            f = localSSLSocketFactory;
            localObject1 = localSSLSocketFactory;
          }
          catch (Exception localException)
          {
            m.Log(5, "UnityWebRequest: No support for " + str + " (" + localException.getMessage() + ")");
            j++;
          }
        }
      }
    }
  }
  
  private static native void headerCallback(long paramLong, String paramString1, String paramString2);
  
  private static native void responseCodeCallback(long paramLong, int paramInt);
  
  private static native int uploadCallback(long paramLong, ByteBuffer paramByteBuffer);
  
  protected void badProtocolCallback(String paramString)
  {
    errorCallback(this.a, 4, paramString);
  }
  
  protected void contentLengthCallback(int paramInt)
  {
    contentLengthCallback(this.a, paramInt);
  }
  
  protected boolean downloadCallback(ByteBuffer paramByteBuffer, int paramInt)
  {
    return downloadCallback(this.a, paramByteBuffer, paramInt);
  }
  
  protected void errorCallback(String paramString)
  {
    errorCallback(this.a, 2, paramString);
  }
  
  protected void headerCallback(String paramString1, String paramString2)
  {
    headerCallback(this.a, paramString1, paramString2);
  }
  
  protected void headerCallback(Map paramMap)
  {
    if ((paramMap == null) || (paramMap.size() == 0)) {
      return;
    }
    Iterator localIterator1 = paramMap.entrySet().iterator();
    while (localIterator1.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator1.next();
      String str = (String)localEntry.getKey();
      if (str == null) {
        str = "Status";
      }
      Iterator localIterator2 = ((List)localEntry.getValue()).iterator();
      while (localIterator2.hasNext()) {
        headerCallback(str, (String)localIterator2.next());
      }
    }
  }
  
  protected void malformattedUrlCallback(String paramString)
  {
    errorCallback(this.a, 5, paramString);
  }
  
  protected void responseCodeCallback(int paramInt)
  {
    responseCodeCallback(this.a, paramInt);
  }
  
  /* Error */
  public void run()
  {
    // Byte code:
    //   0: new 173	java/net/URL
    //   3: dup
    //   4: aload_0
    //   5: getfield 35	com/unity3d/player/UnityWebRequest:b	Ljava/lang/String;
    //   8: invokespecial 174	java/net/URL:<init>	(Ljava/lang/String;)V
    //   11: astore_1
    //   12: aload_1
    //   13: invokevirtual 178	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   16: astore 4
    //   18: aload 4
    //   20: instanceof 180
    //   23: ifeq +23 -> 46
    //   26: invokestatic 182	com/unity3d/player/UnityWebRequest:getSSLSocketFactory	()Ljavax/net/ssl/SSLSocketFactory;
    //   29: astore 32
    //   31: aload 32
    //   33: ifnull +13 -> 46
    //   36: aload 4
    //   38: checkcast 180	javax/net/ssl/HttpsURLConnection
    //   41: aload 32
    //   43: invokevirtual 186	javax/net/ssl/HttpsURLConnection:setSSLSocketFactory	(Ljavax/net/ssl/SSLSocketFactory;)V
    //   46: aload_1
    //   47: invokevirtual 189	java/net/URL:getProtocol	()Ljava/lang/String;
    //   50: ldc -65
    //   52: invokevirtual 195	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   55: ifeq +44 -> 99
    //   58: aload_1
    //   59: invokevirtual 198	java/net/URL:getHost	()Ljava/lang/String;
    //   62: invokevirtual 201	java/lang/String:isEmpty	()Z
    //   65: ifne +34 -> 99
    //   68: aload_0
    //   69: ldc -53
    //   71: invokevirtual 205	com/unity3d/player/UnityWebRequest:malformattedUrlCallback	(Ljava/lang/String;)V
    //   74: return
    //   75: astore_3
    //   76: aload_0
    //   77: aload_3
    //   78: invokevirtual 206	java/net/MalformedURLException:toString	()Ljava/lang/String;
    //   81: invokevirtual 205	com/unity3d/player/UnityWebRequest:malformattedUrlCallback	(Ljava/lang/String;)V
    //   84: goto -10 -> 74
    //   87: astore_2
    //   88: aload_0
    //   89: aload_2
    //   90: invokevirtual 207	java/io/IOException:toString	()Ljava/lang/String;
    //   93: invokevirtual 209	com/unity3d/player/UnityWebRequest:errorCallback	(Ljava/lang/String;)V
    //   96: goto -22 -> 74
    //   99: aload 4
    //   101: instanceof 211
    //   104: ifeq +12 -> 116
    //   107: aload_0
    //   108: ldc -43
    //   110: invokevirtual 215	com/unity3d/player/UnityWebRequest:badProtocolCallback	(Ljava/lang/String;)V
    //   113: goto -39 -> 74
    //   116: aload 4
    //   118: instanceof 217
    //   121: ifeq +25 -> 146
    //   124: aload 4
    //   126: checkcast 217	java/net/HttpURLConnection
    //   129: astore 31
    //   131: aload 31
    //   133: aload_0
    //   134: getfield 37	com/unity3d/player/UnityWebRequest:c	Ljava/lang/String;
    //   137: invokevirtual 220	java/net/HttpURLConnection:setRequestMethod	(Ljava/lang/String;)V
    //   140: aload 31
    //   142: iconst_0
    //   143: invokevirtual 224	java/net/HttpURLConnection:setInstanceFollowRedirects	(Z)V
    //   146: aload_0
    //   147: getfield 39	com/unity3d/player/UnityWebRequest:d	Ljava/util/Map;
    //   150: ifnull +83 -> 233
    //   153: aload_0
    //   154: getfield 39	com/unity3d/player/UnityWebRequest:d	Ljava/util/Map;
    //   157: invokeinterface 126 1 0
    //   162: invokeinterface 132 1 0
    //   167: astore 28
    //   169: aload 28
    //   171: invokeinterface 138 1 0
    //   176: ifeq +57 -> 233
    //   179: aload 28
    //   181: invokeinterface 142 1 0
    //   186: checkcast 144	java/util/Map$Entry
    //   189: astore 29
    //   191: aload 4
    //   193: aload 29
    //   195: invokeinterface 147 1 0
    //   200: checkcast 21	java/lang/String
    //   203: aload 29
    //   205: invokeinterface 152 1 0
    //   210: checkcast 21	java/lang/String
    //   213: invokevirtual 229	java/net/URLConnection:addRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   216: goto -47 -> 169
    //   219: astore 30
    //   221: aload_0
    //   222: aload 30
    //   224: invokevirtual 230	java/net/ProtocolException:toString	()Ljava/lang/String;
    //   227: invokevirtual 215	com/unity3d/player/UnityWebRequest:badProtocolCallback	(Ljava/lang/String;)V
    //   230: goto -156 -> 74
    //   233: aload_0
    //   234: aconst_null
    //   235: invokevirtual 233	com/unity3d/player/UnityWebRequest:uploadCallback	(Ljava/nio/ByteBuffer;)I
    //   238: istore 5
    //   240: iload 5
    //   242: ifle +88 -> 330
    //   245: aload 4
    //   247: iconst_1
    //   248: invokevirtual 236	java/net/URLConnection:setDoOutput	(Z)V
    //   251: iload 5
    //   253: sipush 1428
    //   256: invokestatic 242	java/lang/Math:min	(II)I
    //   259: invokestatic 248	java/nio/ByteBuffer:allocateDirect	(I)Ljava/nio/ByteBuffer;
    //   262: astore 24
    //   264: aload 4
    //   266: invokevirtual 252	java/net/URLConnection:getOutputStream	()Ljava/io/OutputStream;
    //   269: astore 25
    //   271: aload_0
    //   272: aload 24
    //   274: invokevirtual 233	com/unity3d/player/UnityWebRequest:uploadCallback	(Ljava/nio/ByteBuffer;)I
    //   277: istore 26
    //   279: iload 26
    //   281: ifle +49 -> 330
    //   284: aload 25
    //   286: aload 24
    //   288: invokevirtual 256	java/nio/ByteBuffer:array	()[B
    //   291: aload 24
    //   293: invokevirtual 259	java/nio/ByteBuffer:arrayOffset	()I
    //   296: iload 26
    //   298: invokevirtual 265	java/io/OutputStream:write	([BII)V
    //   301: aload_0
    //   302: aload 24
    //   304: invokevirtual 233	com/unity3d/player/UnityWebRequest:uploadCallback	(Ljava/nio/ByteBuffer;)I
    //   307: istore 27
    //   309: iload 27
    //   311: istore 26
    //   313: goto -34 -> 279
    //   316: astore 23
    //   318: aload_0
    //   319: aload 23
    //   321: invokevirtual 266	java/lang/Exception:toString	()Ljava/lang/String;
    //   324: invokevirtual 209	com/unity3d/player/UnityWebRequest:errorCallback	(Ljava/lang/String;)V
    //   327: goto -253 -> 74
    //   330: aload 4
    //   332: instanceof 217
    //   335: ifeq +19 -> 354
    //   338: aload 4
    //   340: checkcast 217	java/net/HttpURLConnection
    //   343: astore 20
    //   345: aload_0
    //   346: aload 20
    //   348: invokevirtual 269	java/net/HttpURLConnection:getResponseCode	()I
    //   351: invokevirtual 271	com/unity3d/player/UnityWebRequest:responseCodeCallback	(I)V
    //   354: aload 4
    //   356: invokevirtual 275	java/net/URLConnection:getHeaderFields	()Ljava/util/Map;
    //   359: astore 6
    //   361: aload_0
    //   362: aload 6
    //   364: invokevirtual 277	com/unity3d/player/UnityWebRequest:headerCallback	(Ljava/util/Map;)V
    //   367: aload 6
    //   369: ifnull +16 -> 385
    //   372: aload 6
    //   374: ldc_w 279
    //   377: invokeinterface 283 2 0
    //   382: ifne +28 -> 410
    //   385: aload 4
    //   387: invokevirtual 286	java/net/URLConnection:getContentLength	()I
    //   390: bipush -1
    //   392: if_icmpeq +18 -> 410
    //   395: aload_0
    //   396: ldc_w 279
    //   399: aload 4
    //   401: invokevirtual 286	java/net/URLConnection:getContentLength	()I
    //   404: invokestatic 290	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   407: invokevirtual 157	com/unity3d/player/UnityWebRequest:headerCallback	(Ljava/lang/String;Ljava/lang/String;)V
    //   410: aload 6
    //   412: ifnull +16 -> 428
    //   415: aload 6
    //   417: ldc_w 292
    //   420: invokeinterface 283 2 0
    //   425: ifne +23 -> 448
    //   428: aload 4
    //   430: invokevirtual 295	java/net/URLConnection:getContentType	()Ljava/lang/String;
    //   433: ifnull +15 -> 448
    //   436: aload_0
    //   437: ldc_w 292
    //   440: aload 4
    //   442: invokevirtual 295	java/net/URLConnection:getContentType	()Ljava/lang/String;
    //   445: invokevirtual 157	com/unity3d/player/UnityWebRequest:headerCallback	(Ljava/lang/String;Ljava/lang/String;)V
    //   448: aload 4
    //   450: invokevirtual 286	java/net/URLConnection:getContentLength	()I
    //   453: istore 7
    //   455: iload 7
    //   457: ifle +9 -> 466
    //   460: aload_0
    //   461: iload 7
    //   463: invokevirtual 297	com/unity3d/player/UnityWebRequest:contentLengthCallback	(I)V
    //   466: aload_1
    //   467: invokevirtual 189	java/net/URL:getProtocol	()Ljava/lang/String;
    //   470: ldc -65
    //   472: invokevirtual 195	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   475: ifeq +226 -> 701
    //   478: iload 7
    //   480: ifne +150 -> 630
    //   483: ldc_w 298
    //   486: istore 19
    //   488: iload 19
    //   490: istore 8
    //   492: aload 4
    //   494: instanceof 217
    //   497: ifeq +198 -> 695
    //   500: aload 4
    //   502: checkcast 217	java/net/HttpURLConnection
    //   505: astore 18
    //   507: aload_0
    //   508: aload 18
    //   510: invokevirtual 269	java/net/HttpURLConnection:getResponseCode	()I
    //   513: invokevirtual 271	com/unity3d/player/UnityWebRequest:responseCodeCallback	(I)V
    //   516: aload 18
    //   518: invokevirtual 302	java/net/HttpURLConnection:getErrorStream	()Ljava/io/InputStream;
    //   521: astore 12
    //   523: aload 12
    //   525: ifnonnull +10 -> 535
    //   528: aload 4
    //   530: invokevirtual 305	java/net/URLConnection:getInputStream	()Ljava/io/InputStream;
    //   533: astore 12
    //   535: aload 12
    //   537: invokestatic 311	java/nio/channels/Channels:newChannel	(Ljava/io/InputStream;)Ljava/nio/channels/ReadableByteChannel;
    //   540: astore 13
    //   542: iload 8
    //   544: invokestatic 248	java/nio/ByteBuffer:allocateDirect	(I)Ljava/nio/ByteBuffer;
    //   547: astore 14
    //   549: aload 13
    //   551: aload 14
    //   553: invokeinterface 316 2 0
    //   558: istore 15
    //   560: iload 15
    //   562: bipush -1
    //   564: if_icmpeq +79 -> 643
    //   567: aload_0
    //   568: aload 14
    //   570: iload 15
    //   572: invokevirtual 318	com/unity3d/player/UnityWebRequest:downloadCallback	(Ljava/nio/ByteBuffer;I)Z
    //   575: ifeq +68 -> 643
    //   578: aload 14
    //   580: invokevirtual 322	java/nio/ByteBuffer:clear	()Ljava/nio/Buffer;
    //   583: pop
    //   584: aload 13
    //   586: aload 14
    //   588: invokeinterface 316 2 0
    //   593: istore 17
    //   595: iload 17
    //   597: istore 15
    //   599: goto -39 -> 560
    //   602: astore 22
    //   604: aload_0
    //   605: aload 22
    //   607: invokevirtual 323	java/net/UnknownHostException:toString	()Ljava/lang/String;
    //   610: invokevirtual 326	com/unity3d/player/UnityWebRequest:unknownHostCallback	(Ljava/lang/String;)V
    //   613: goto -259 -> 354
    //   616: astore 21
    //   618: aload_0
    //   619: aload 21
    //   621: invokevirtual 207	java/io/IOException:toString	()Ljava/lang/String;
    //   624: invokevirtual 209	com/unity3d/player/UnityWebRequest:errorCallback	(Ljava/lang/String;)V
    //   627: goto -553 -> 74
    //   630: iload 7
    //   632: ldc_w 298
    //   635: invokestatic 242	java/lang/Math:min	(II)I
    //   638: istore 19
    //   640: goto -152 -> 488
    //   643: aload 13
    //   645: invokeinterface 329 1 0
    //   650: goto -576 -> 74
    //   653: astore 11
    //   655: aload_0
    //   656: aload 11
    //   658: invokevirtual 323	java/net/UnknownHostException:toString	()Ljava/lang/String;
    //   661: invokevirtual 326	com/unity3d/player/UnityWebRequest:unknownHostCallback	(Ljava/lang/String;)V
    //   664: goto -590 -> 74
    //   667: astore 10
    //   669: aload_0
    //   670: aload 10
    //   672: invokevirtual 330	javax/net/ssl/SSLHandshakeException:toString	()Ljava/lang/String;
    //   675: invokevirtual 333	com/unity3d/player/UnityWebRequest:sslCannotConnectCallback	(Ljava/lang/String;)V
    //   678: goto -604 -> 74
    //   681: astore 9
    //   683: aload_0
    //   684: aload 9
    //   686: invokevirtual 266	java/lang/Exception:toString	()Ljava/lang/String;
    //   689: invokevirtual 209	com/unity3d/player/UnityWebRequest:errorCallback	(Ljava/lang/String;)V
    //   692: goto -618 -> 74
    //   695: aconst_null
    //   696: astore 12
    //   698: goto -175 -> 523
    //   701: sipush 1428
    //   704: istore 8
    //   706: goto -214 -> 492
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	709	0	this	UnityWebRequest
    //   11	456	1	localURL	java.net.URL
    //   87	3	2	localIOException1	java.io.IOException
    //   75	3	3	localMalformedURLException	java.net.MalformedURLException
    //   16	513	4	localURLConnection	java.net.URLConnection
    //   238	14	5	i	int
    //   359	57	6	localMap	Map
    //   453	178	7	j	int
    //   490	215	8	k	int
    //   681	4	9	localException1	Exception
    //   667	4	10	localSSLHandshakeException	javax.net.ssl.SSLHandshakeException
    //   653	4	11	localUnknownHostException1	java.net.UnknownHostException
    //   521	176	12	localInputStream	java.io.InputStream
    //   540	104	13	localReadableByteChannel	java.nio.channels.ReadableByteChannel
    //   547	40	14	localByteBuffer1	ByteBuffer
    //   558	40	15	m	int
    //   593	3	17	n	int
    //   505	12	18	localHttpURLConnection1	java.net.HttpURLConnection
    //   486	153	19	i1	int
    //   343	4	20	localHttpURLConnection2	java.net.HttpURLConnection
    //   616	4	21	localIOException2	java.io.IOException
    //   602	4	22	localUnknownHostException2	java.net.UnknownHostException
    //   316	4	23	localException2	Exception
    //   262	41	24	localByteBuffer2	ByteBuffer
    //   269	16	25	localOutputStream	java.io.OutputStream
    //   277	35	26	i2	int
    //   307	3	27	i3	int
    //   167	13	28	localIterator	Iterator
    //   189	15	29	localEntry	Map.Entry
    //   219	4	30	localProtocolException	java.net.ProtocolException
    //   129	12	31	localHttpURLConnection3	java.net.HttpURLConnection
    //   29	13	32	localSSLSocketFactory	SSLSocketFactory
    // Exception table:
    //   from	to	target	type
    //   0	46	75	java/net/MalformedURLException
    //   0	46	87	java/io/IOException
    //   124	146	219	java/net/ProtocolException
    //   251	309	316	java/lang/Exception
    //   345	354	602	java/net/UnknownHostException
    //   345	354	616	java/io/IOException
    //   492	595	653	java/net/UnknownHostException
    //   643	650	653	java/net/UnknownHostException
    //   492	595	667	javax/net/ssl/SSLHandshakeException
    //   643	650	667	javax/net/ssl/SSLHandshakeException
    //   492	595	681	java/lang/Exception
    //   643	650	681	java/lang/Exception
  }
  
  protected void sslCannotConnectCallback(String paramString)
  {
    errorCallback(this.a, 16, paramString);
  }
  
  protected void unknownHostCallback(String paramString)
  {
    errorCallback(this.a, 7, paramString);
  }
  
  protected int uploadCallback(ByteBuffer paramByteBuffer)
  {
    return uploadCallback(this.a, paramByteBuffer);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/unity3d/player/UnityWebRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */