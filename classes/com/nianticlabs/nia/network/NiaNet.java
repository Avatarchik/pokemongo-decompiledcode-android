package com.nianticlabs.nia.network;

import android.util.Log;
import java.net.HttpURLConnection;
import java.nio.ByteBuffer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class NiaNet
{
  private static final int CHUNK_SIZE = 32768;
  private static final int HTTP_BAD_REQUEST = 400;
  private static final int HTTP_OK = 200;
  private static final String IF_MODIFIED_SINCE = "If-Modified-Since";
  private static final int METHOD_DELETE = 4;
  private static final int METHOD_GET = 0;
  private static final int METHOD_HEAD = 1;
  private static final int METHOD_OPTIONS = 5;
  private static final int METHOD_POST = 2;
  private static final int METHOD_PUT = 3;
  private static final int METHOD_TRACE = 6;
  private static final int NETWORK_TIMEOUT_MS = 15000;
  private static final int POOL_THREAD_NUM = 6;
  private static final String TAG = "NiaNet";
  private static final ThreadPoolExecutor executor = new ThreadPoolExecutor(6, 12, 5L, TimeUnit.SECONDS, new LinkedBlockingQueue());
  private static Set<Integer> pendingRequestIds = new HashSet();
  static ThreadLocal<ByteBuffer> readBuffer = new ThreadLocal()
  {
    protected ByteBuffer initialValue()
    {
      return ByteBuffer.allocateDirect(32768);
    }
  };
  private static final ThreadLocal<byte[]> threadChunk = new ThreadLocal()
  {
    protected byte[] initialValue()
    {
      return new byte[32768];
    }
  };
  
  public static void cancel(int paramInt)
  {
    synchronized (pendingRequestIds)
    {
      pendingRequestIds.remove(Integer.valueOf(paramInt));
      return;
    }
  }
  
  /* Error */
  private static void doSyncRequest(long paramLong, int paramInt1, String paramString1, int paramInt2, String paramString2, ByteBuffer paramByteBuffer, int paramInt3, int paramInt4)
  {
    // Byte code:
    //   0: getstatic 78	com/nianticlabs/nia/network/NiaNet:pendingRequestIds	Ljava/util/Set;
    //   3: astore 9
    //   5: aload 9
    //   7: monitorenter
    //   8: getstatic 78	com/nianticlabs/nia/network/NiaNet:pendingRequestIds	Ljava/util/Set;
    //   11: iload_2
    //   12: invokestatic 98	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   15: invokeinterface 109 2 0
    //   20: ifne +378 -> 398
    //   23: iconst_1
    //   24: istore 11
    //   26: iload 11
    //   28: ifeq +9 -> 37
    //   31: aload 9
    //   33: monitorexit
    //   34: goto +363 -> 397
    //   37: getstatic 78	com/nianticlabs/nia/network/NiaNet:pendingRequestIds	Ljava/util/Set;
    //   40: iload_2
    //   41: invokestatic 98	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   44: invokeinterface 104 2 0
    //   49: pop
    //   50: aload 9
    //   52: monitorexit
    //   53: aconst_null
    //   54: astore 13
    //   56: sipush 400
    //   59: istore 14
    //   61: aconst_null
    //   62: astore 15
    //   64: new 111	java/net/URL
    //   67: dup
    //   68: aload_3
    //   69: invokespecial 114	java/net/URL:<init>	(Ljava/lang/String;)V
    //   72: invokevirtual 118	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   75: checkcast 120	java/net/HttpURLConnection
    //   78: checkcast 120	java/net/HttpURLConnection
    //   81: astore 13
    //   83: aload 13
    //   85: aload 5
    //   87: invokestatic 124	com/nianticlabs/nia/network/NiaNet:setHeaders	(Ljava/net/HttpURLConnection;Ljava/lang/String;)V
    //   90: aload 13
    //   92: sipush 15000
    //   95: invokevirtual 127	java/net/HttpURLConnection:setConnectTimeout	(I)V
    //   98: aload 13
    //   100: ldc -127
    //   102: ldc -125
    //   104: invokevirtual 135	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   107: iconst_0
    //   108: invokestatic 139	java/net/HttpURLConnection:setFollowRedirects	(Z)V
    //   111: lload_0
    //   112: aload 13
    //   114: invokestatic 143	com/nianticlabs/nia/network/NiaNet:nativeSetupConnection	(JLjava/net/HttpURLConnection;)V
    //   117: iload 4
    //   119: invokestatic 147	com/nianticlabs/nia/network/NiaNet:getMethodString	(I)Ljava/lang/String;
    //   122: astore 20
    //   124: aload 13
    //   126: aload 20
    //   128: invokevirtual 150	java/net/HttpURLConnection:setRequestMethod	(Ljava/lang/String;)V
    //   131: aload 6
    //   133: ifnull +54 -> 187
    //   136: iload 8
    //   138: ifle +49 -> 187
    //   141: aload 13
    //   143: iconst_1
    //   144: invokevirtual 153	java/net/HttpURLConnection:setDoOutput	(Z)V
    //   147: aload 13
    //   149: invokevirtual 157	java/net/HttpURLConnection:getOutputStream	()Ljava/io/OutputStream;
    //   152: astore 22
    //   154: aload 6
    //   156: invokevirtual 163	java/nio/ByteBuffer:hasArray	()Z
    //   159: ifeq +99 -> 258
    //   162: aload 22
    //   164: aload 6
    //   166: invokevirtual 167	java/nio/ByteBuffer:array	()[B
    //   169: iload 7
    //   171: aload 6
    //   173: invokevirtual 171	java/nio/ByteBuffer:arrayOffset	()I
    //   176: iadd
    //   177: iload 8
    //   179: invokevirtual 177	java/io/OutputStream:write	([BII)V
    //   182: aload 22
    //   184: invokevirtual 180	java/io/OutputStream:close	()V
    //   187: aload 13
    //   189: invokevirtual 183	java/net/HttpURLConnection:getResponseCode	()I
    //   192: istore 14
    //   194: aload 13
    //   196: invokestatic 187	com/nianticlabs/nia/network/NiaNet:joinHeaders	(Ljava/net/HttpURLConnection;)Ljava/lang/String;
    //   199: astore 15
    //   201: aload 13
    //   203: invokestatic 191	com/nianticlabs/nia/network/NiaNet:readDataSteam	(Ljava/net/HttpURLConnection;)I
    //   206: istore 21
    //   208: iload 21
    //   210: istore 19
    //   212: aload 13
    //   214: ifnull +8 -> 222
    //   217: aload 13
    //   219: invokevirtual 194	java/net/HttpURLConnection:disconnect	()V
    //   222: iload 19
    //   224: ifle +162 -> 386
    //   227: lload_0
    //   228: iload 14
    //   230: aload 15
    //   232: getstatic 84	com/nianticlabs/nia/network/NiaNet:readBuffer	Ljava/lang/ThreadLocal;
    //   235: invokevirtual 200	java/lang/ThreadLocal:get	()Ljava/lang/Object;
    //   238: checkcast 159	java/nio/ByteBuffer
    //   241: iconst_0
    //   242: iload 19
    //   244: invokestatic 204	com/nianticlabs/nia/network/NiaNet:nativeCallback	(JILjava/lang/String;Ljava/nio/ByteBuffer;II)V
    //   247: goto +150 -> 397
    //   250: astore 10
    //   252: aload 9
    //   254: monitorexit
    //   255: aload 10
    //   257: athrow
    //   258: getstatic 81	com/nianticlabs/nia/network/NiaNet:threadChunk	Ljava/lang/ThreadLocal;
    //   261: invokevirtual 200	java/lang/ThreadLocal:get	()Ljava/lang/Object;
    //   264: checkcast 206	[B
    //   267: astore 24
    //   269: aload 6
    //   271: invokevirtual 209	java/nio/ByteBuffer:hasRemaining	()Z
    //   274: ifeq -92 -> 182
    //   277: aload 6
    //   279: invokevirtual 212	java/nio/ByteBuffer:remaining	()I
    //   282: aload 24
    //   284: arraylength
    //   285: invokestatic 218	java/lang/Math:min	(II)I
    //   288: istore 25
    //   290: aload 6
    //   292: aload 24
    //   294: iconst_0
    //   295: iload 25
    //   297: invokevirtual 221	java/nio/ByteBuffer:get	([BII)Ljava/nio/ByteBuffer;
    //   300: pop
    //   301: aload 22
    //   303: aload 24
    //   305: iconst_0
    //   306: iload 25
    //   308: invokevirtual 177	java/io/OutputStream:write	([BII)V
    //   311: goto -42 -> 269
    //   314: astore 23
    //   316: aload 22
    //   318: invokevirtual 180	java/io/OutputStream:close	()V
    //   321: aload 23
    //   323: athrow
    //   324: astore 17
    //   326: ldc 41
    //   328: new 223	java/lang/StringBuilder
    //   331: dup
    //   332: invokespecial 224	java/lang/StringBuilder:<init>	()V
    //   335: ldc -30
    //   337: invokevirtual 230	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   340: aload 17
    //   342: invokevirtual 234	java/io/IOException:getMessage	()Ljava/lang/String;
    //   345: invokevirtual 230	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   348: invokevirtual 237	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   351: invokestatic 243	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   354: pop
    //   355: iconst_0
    //   356: istore 19
    //   358: aload 13
    //   360: ifnull -138 -> 222
    //   363: aload 13
    //   365: invokevirtual 194	java/net/HttpURLConnection:disconnect	()V
    //   368: goto -146 -> 222
    //   371: astore 16
    //   373: aload 13
    //   375: ifnull +8 -> 383
    //   378: aload 13
    //   380: invokevirtual 194	java/net/HttpURLConnection:disconnect	()V
    //   383: aload 16
    //   385: athrow
    //   386: lload_0
    //   387: iload 14
    //   389: aload 15
    //   391: aconst_null
    //   392: iconst_0
    //   393: iconst_0
    //   394: invokestatic 204	com/nianticlabs/nia/network/NiaNet:nativeCallback	(JILjava/lang/String;Ljava/nio/ByteBuffer;II)V
    //   397: return
    //   398: iconst_0
    //   399: istore 11
    //   401: goto -375 -> 26
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	404	0	paramLong	long
    //   0	404	2	paramInt1	int
    //   0	404	3	paramString1	String
    //   0	404	4	paramInt2	int
    //   0	404	5	paramString2	String
    //   0	404	6	paramByteBuffer	ByteBuffer
    //   0	404	7	paramInt3	int
    //   0	404	8	paramInt4	int
    //   3	250	9	localSet	Set
    //   250	6	10	localObject1	Object
    //   24	376	11	i	int
    //   54	325	13	localHttpURLConnection	HttpURLConnection
    //   59	329	14	j	int
    //   62	328	15	str1	String
    //   371	13	16	localObject2	Object
    //   324	17	17	localIOException	java.io.IOException
    //   210	147	19	k	int
    //   122	5	20	str2	String
    //   206	3	21	m	int
    //   152	165	22	localOutputStream	java.io.OutputStream
    //   314	8	23	localObject3	Object
    //   267	37	24	arrayOfByte	byte[]
    //   288	19	25	n	int
    // Exception table:
    //   from	to	target	type
    //   8	53	250	finally
    //   252	255	250	finally
    //   154	182	314	finally
    //   258	311	314	finally
    //   64	154	324	java/io/IOException
    //   182	208	324	java/io/IOException
    //   316	324	324	java/io/IOException
    //   64	154	371	finally
    //   182	208	371	finally
    //   316	324	371	finally
    //   326	355	371	finally
  }
  
  private static String getMethodString(int paramInt)
  {
    String str;
    switch (paramInt)
    {
    default: 
      Log.e("NiaNet", "Unsupported HTTP method " + paramInt + ", using GET.");
      str = "GET";
    }
    for (;;)
    {
      return str;
      str = "GET";
      continue;
      str = "HEAD";
      continue;
      str = "POST";
      continue;
      str = "PUT";
      continue;
      str = "DELETE";
    }
  }
  
  private static String joinHeaders(HttpURLConnection paramHttpURLConnection)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    for (int i = 0;; i++)
    {
      String str1 = paramHttpURLConnection.getHeaderFieldKey(i);
      if (str1 == null) {
        break;
      }
      String str3 = paramHttpURLConnection.getHeaderField(i);
      if (str3 == null) {
        break;
      }
      localStringBuilder.append(str1);
      localStringBuilder.append(": ");
      localStringBuilder.append(str3);
      localStringBuilder.append("\n");
    }
    if (localStringBuilder.length() == 0) {}
    for (String str2 = null;; str2 = localStringBuilder.toString()) {
      return str2;
    }
  }
  
  private static native void nativeCallback(long paramLong, int paramInt1, String paramString, ByteBuffer paramByteBuffer, int paramInt2, int paramInt3);
  
  private static native void nativeSetupConnection(long paramLong, HttpURLConnection paramHttpURLConnection);
  
  private static long parseHttpDateTime(String paramString)
    throws ParseException
  {
    return new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz").parse(paramString).getTime();
  }
  
  /* Error */
  private static int readDataSteam(HttpURLConnection paramHttpURLConnection)
    throws java.io.IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 183	java/net/HttpURLConnection:getResponseCode	()I
    //   4: sipush 200
    //   7: if_icmpne +18 -> 25
    //   10: aload_0
    //   11: invokevirtual 296	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   14: astore_1
    //   15: aload_1
    //   16: ifnonnull +17 -> 33
    //   19: iconst_0
    //   20: istore 11
    //   22: iload 11
    //   24: ireturn
    //   25: aload_0
    //   26: invokevirtual 299	java/net/HttpURLConnection:getErrorStream	()Ljava/io/InputStream;
    //   29: astore_1
    //   30: goto -15 -> 15
    //   33: getstatic 84	com/nianticlabs/nia/network/NiaNet:readBuffer	Ljava/lang/ThreadLocal;
    //   36: invokevirtual 200	java/lang/ThreadLocal:get	()Ljava/lang/Object;
    //   39: checkcast 159	java/nio/ByteBuffer
    //   42: astore_2
    //   43: aload_2
    //   44: invokevirtual 167	java/nio/ByteBuffer:array	()[B
    //   47: astore 4
    //   49: aload_2
    //   50: invokevirtual 171	java/nio/ByteBuffer:arrayOffset	()I
    //   53: istore 5
    //   55: iload 5
    //   57: istore 6
    //   59: iconst_1
    //   60: istore 7
    //   62: aload_1
    //   63: invokevirtual 304	java/io/InputStream:available	()I
    //   66: istore 8
    //   68: aload 4
    //   70: arraylength
    //   71: iload 8
    //   73: iload 6
    //   75: iadd
    //   76: if_icmpgt +79 -> 155
    //   79: iconst_2
    //   80: iload 8
    //   82: iload 6
    //   84: iadd
    //   85: iload 5
    //   87: isub
    //   88: imul
    //   89: invokestatic 308	java/nio/ByteBuffer:allocateDirect	(I)Ljava/nio/ByteBuffer;
    //   92: astore 12
    //   94: iload 6
    //   96: iload 5
    //   98: isub
    //   99: istore 13
    //   101: aload 12
    //   103: invokevirtual 171	java/nio/ByteBuffer:arrayOffset	()I
    //   106: istore 14
    //   108: iload 13
    //   110: ifle +19 -> 129
    //   113: aload 4
    //   115: iload 5
    //   117: aload 12
    //   119: invokevirtual 167	java/nio/ByteBuffer:array	()[B
    //   122: iload 14
    //   124: iload 13
    //   126: invokestatic 314	java/lang/System:arraycopy	(Ljava/lang/Object;ILjava/lang/Object;II)V
    //   129: iload 14
    //   131: istore 5
    //   133: iload 13
    //   135: iload 14
    //   137: iadd
    //   138: istore 6
    //   140: aload 12
    //   142: invokevirtual 167	java/nio/ByteBuffer:array	()[B
    //   145: astore 4
    //   147: getstatic 84	com/nianticlabs/nia/network/NiaNet:readBuffer	Ljava/lang/ThreadLocal;
    //   150: aload 12
    //   152: invokevirtual 318	java/lang/ThreadLocal:set	(Ljava/lang/Object;)V
    //   155: aload 4
    //   157: arraylength
    //   158: iload 6
    //   160: isub
    //   161: istore 9
    //   163: aload_1
    //   164: aload 4
    //   166: iload 6
    //   168: iload 9
    //   170: invokevirtual 322	java/io/InputStream:read	([BII)I
    //   173: istore 10
    //   175: iload 10
    //   177: iflt +29 -> 206
    //   180: iload 6
    //   182: iload 10
    //   184: iadd
    //   185: istore 6
    //   187: iload 7
    //   189: ifne -127 -> 62
    //   192: iload 6
    //   194: iload 5
    //   196: isub
    //   197: istore 11
    //   199: aload_1
    //   200: invokevirtual 323	java/io/InputStream:close	()V
    //   203: goto -181 -> 22
    //   206: iconst_0
    //   207: istore 7
    //   209: goto -22 -> 187
    //   212: astore_3
    //   213: aload_1
    //   214: invokevirtual 323	java/io/InputStream:close	()V
    //   217: aload_3
    //   218: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	219	0	paramHttpURLConnection	HttpURLConnection
    //   14	200	1	localInputStream	java.io.InputStream
    //   42	8	2	localByteBuffer1	ByteBuffer
    //   212	6	3	localObject	Object
    //   47	118	4	arrayOfByte	byte[]
    //   53	144	5	i	int
    //   57	140	6	j	int
    //   60	148	7	k	int
    //   66	19	8	m	int
    //   161	8	9	n	int
    //   173	12	10	i1	int
    //   20	178	11	i2	int
    //   92	59	12	localByteBuffer2	ByteBuffer
    //   99	39	13	i3	int
    //   106	32	14	i4	int
    // Exception table:
    //   from	to	target	type
    //   43	175	212	finally
  }
  
  public static void request(long paramLong, int paramInt1, final String paramString1, final int paramInt2, final String paramString2, final ByteBuffer paramByteBuffer, final int paramInt3, final int paramInt4)
  {
    synchronized (pendingRequestIds)
    {
      pendingRequestIds.add(Integer.valueOf(paramInt1));
      executor.execute(new Runnable()
      {
        public void run()
        {
          NiaNet.doSyncRequest(this.val$object, paramString1, paramInt2, paramString2, paramByteBuffer, paramInt3, paramInt4, this.val$bodySize);
        }
      });
      return;
    }
  }
  
  private static void setHeaders(HttpURLConnection paramHttpURLConnection, String paramString)
  {
    if ((paramString == null) || (paramString.isEmpty())) {
      return;
    }
    int i = 0;
    label14:
    int j = paramString.indexOf('\n', i);
    if (j < 0) {
      j = paramString.length();
    }
    int k = paramString.indexOf(':', i);
    if (k < 0) {
      k = paramString.length();
    }
    String str1 = paramString.substring(i, k);
    String str2 = paramString.substring(k + 1, j);
    if ("If-Modified-Since".equals(str1)) {}
    for (;;)
    {
      try
      {
        paramHttpURLConnection.setIfModifiedSince(parseHttpDateTime(str2));
        i = j + 1;
        if (i < paramString.length()) {
          break label14;
        }
      }
      catch (ParseException localParseException)
      {
        Log.e("NiaNet", "If-Modified-Since Date/Time parse failed. " + localParseException.getMessage());
        continue;
      }
      paramHttpURLConnection.setRequestProperty(str1, str2);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/nianticlabs/nia/network/NiaNet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */