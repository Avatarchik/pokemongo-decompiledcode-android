package crittercism.android;

import android.os.ConditionVariable;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

public final class g
  implements f, Runnable
{
  private List a = new LinkedList();
  private URL b = null;
  private long c = System.currentTimeMillis();
  private ConditionVariable d = new ConditionVariable(false);
  private au e;
  private ConditionVariable f = new ConditionVariable(false);
  private volatile boolean g = false;
  private final Object h = new Object();
  private int i = 50;
  private volatile long j = 10000L;
  
  public g(au paramau, URL paramURL)
  {
    this(paramau, paramURL, (byte)0);
  }
  
  private g(au paramau, URL paramURL, byte paramByte)
  {
    this.e = paramau;
    this.b = paramURL;
    this.i = 50;
    this.j = 10000L;
  }
  
  private static boolean a(HttpURLConnection paramHttpURLConnection, JSONObject paramJSONObject)
  {
    boolean bool = false;
    try
    {
      paramHttpURLConnection.getOutputStream().write(paramJSONObject.toString().getBytes("UTF8"));
      int k = paramHttpURLConnection.getResponseCode();
      paramHttpURLConnection.disconnect();
      if (k == 202) {
        bool = true;
      }
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        new StringBuilder("Request failed for ").append(paramHttpURLConnection.getURL().toExternalForm());
        dx.a();
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        new StringBuilder("Request failed for ").append(paramHttpURLConnection.getURL().toExternalForm());
        dx.a();
      }
    }
    return bool;
  }
  
  private long b()
  {
    long l1 = 0L;
    long l2 = this.j;
    long l3 = System.currentTimeMillis() - this.c;
    if (l3 > l1)
    {
      l2 -= l3;
      if (l2 >= l1) {}
    }
    for (;;)
    {
      return l1;
      l1 = l2;
    }
  }
  
  /* Error */
  private HttpURLConnection c()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 41	crittercism/android/g:b	Ljava/net/URL;
    //   4: invokevirtual 139	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   7: checkcast 77	java/net/HttpURLConnection
    //   10: astore_2
    //   11: aload_2
    //   12: sipush 2500
    //   15: invokevirtual 143	java/net/HttpURLConnection:setConnectTimeout	(I)V
    //   18: aload_2
    //   19: ldc -111
    //   21: ldc -109
    //   23: invokevirtual 151	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   26: aload_2
    //   27: ldc -103
    //   29: ldc -101
    //   31: invokevirtual 151	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   34: aload_2
    //   35: iconst_1
    //   36: invokevirtual 158	java/net/HttpURLConnection:setDoOutput	(Z)V
    //   39: aload_2
    //   40: ldc -96
    //   42: invokevirtual 163	java/net/HttpURLConnection:setRequestMethod	(Ljava/lang/String;)V
    //   45: aload_2
    //   46: instanceof 165
    //   49: ifeq +61 -> 110
    //   52: aload_2
    //   53: checkcast 165	javax/net/ssl/HttpsURLConnection
    //   56: astore 5
    //   58: ldc -89
    //   60: invokestatic 173	javax/net/ssl/SSLContext:getInstance	(Ljava/lang/String;)Ljavax/net/ssl/SSLContext;
    //   63: astore 6
    //   65: aload 6
    //   67: aconst_null
    //   68: aconst_null
    //   69: aconst_null
    //   70: invokevirtual 177	javax/net/ssl/SSLContext:init	([Ljavax/net/ssl/KeyManager;[Ljavax/net/ssl/TrustManager;Ljava/security/SecureRandom;)V
    //   73: aload 6
    //   75: invokevirtual 181	javax/net/ssl/SSLContext:getSocketFactory	()Ljavax/net/ssl/SSLSocketFactory;
    //   78: astore 7
    //   80: aload 7
    //   82: ifnull +28 -> 110
    //   85: aload 7
    //   87: instanceof 183
    //   90: ifeq +13 -> 103
    //   93: aload 7
    //   95: checkcast 183	crittercism/android/ab
    //   98: invokevirtual 185	crittercism/android/ab:a	()Ljavax/net/ssl/SSLSocketFactory;
    //   101: astore 7
    //   103: aload 5
    //   105: aload 7
    //   107: invokevirtual 189	javax/net/ssl/HttpsURLConnection:setSSLSocketFactory	(Ljavax/net/ssl/SSLSocketFactory;)V
    //   110: aload_2
    //   111: areturn
    //   112: astore_3
    //   113: aload_3
    //   114: astore 4
    //   116: aconst_null
    //   117: astore_2
    //   118: new 110	java/lang/StringBuilder
    //   121: dup
    //   122: ldc -65
    //   124: invokespecial 115	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   127: aload 4
    //   129: invokevirtual 194	java/io/IOException:getMessage	()Ljava/lang/String;
    //   132: invokevirtual 128	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   135: invokevirtual 195	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   138: invokestatic 197	crittercism/android/dx:b	(Ljava/lang/String;)V
    //   141: goto -31 -> 110
    //   144: astore_1
    //   145: new 110	java/lang/StringBuilder
    //   148: dup
    //   149: ldc -65
    //   151: invokespecial 115	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   154: aload_1
    //   155: invokevirtual 198	java/security/GeneralSecurityException:getMessage	()Ljava/lang/String;
    //   158: invokevirtual 128	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   161: invokevirtual 195	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   164: invokestatic 197	crittercism/android/dx:b	(Ljava/lang/String;)V
    //   167: aconst_null
    //   168: astore_2
    //   169: goto -59 -> 110
    //   172: astore 4
    //   174: goto -56 -> 118
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	177	0	this	g
    //   144	11	1	localGeneralSecurityException	java.security.GeneralSecurityException
    //   10	159	2	localHttpURLConnection	HttpURLConnection
    //   112	2	3	localIOException1	IOException
    //   114	14	4	localIOException2	IOException
    //   172	1	4	localIOException3	IOException
    //   56	48	5	localHttpsURLConnection	javax.net.ssl.HttpsURLConnection
    //   63	11	6	localSSLContext	javax.net.ssl.SSLContext
    //   78	28	7	localSSLSocketFactory	javax.net.ssl.SSLSocketFactory
    // Exception table:
    //   from	to	target	type
    //   0	11	112	java/io/IOException
    //   0	11	144	java/security/GeneralSecurityException
    //   11	110	144	java/security/GeneralSecurityException
    //   11	110	172	java/io/IOException
  }
  
  private boolean d()
  {
    if ((!this.g) && (this.a.size() < this.i)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public final void a()
  {
    this.f.open();
  }
  
  public final void a(int paramInt, TimeUnit paramTimeUnit)
  {
    this.j = paramTimeUnit.toMillis(paramInt);
  }
  
  public final void a(c paramc)
  {
    int k = 0;
    if (!d()) {}
    for (;;)
    {
      return;
      synchronized (this.h)
      {
        if (d()) {
          break;
        }
      }
    }
    this.a.add(paramc);
    String str1 = this.b.getHost();
    if (paramc.a().contains(str1)) {}
    for (;;)
    {
      if (k != 0) {
        this.d.open();
      }
      break;
      String str2 = paramc.f;
      if (str2 != null)
      {
        boolean bool = str2.toLowerCase().equals("connect");
        if (bool) {}
      }
      else
      {
        k = 1;
      }
    }
  }
  
  /* Error */
  public final void run()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 60	crittercism/android/g:g	Z
    //   4: ifne +195 -> 199
    //   7: aload_0
    //   8: getfield 58	crittercism/android/g:f	Landroid/os/ConditionVariable;
    //   11: invokevirtual 249	android/os/ConditionVariable:block	()V
    //   14: aload_0
    //   15: getfield 56	crittercism/android/g:d	Landroid/os/ConditionVariable;
    //   18: invokevirtual 249	android/os/ConditionVariable:block	()V
    //   21: aload_0
    //   22: getfield 60	crittercism/android/g:g	Z
    //   25: istore_3
    //   26: iload_3
    //   27: ifne +172 -> 199
    //   30: aload_0
    //   31: invokespecial 251	crittercism/android/g:b	()J
    //   34: lconst_0
    //   35: lcmp
    //   36: ifle +10 -> 46
    //   39: aload_0
    //   40: invokespecial 251	crittercism/android/g:b	()J
    //   43: invokestatic 257	java/lang/Thread:sleep	(J)V
    //   46: aload_0
    //   47: invokestatic 47	java/lang/System:currentTimeMillis	()J
    //   50: putfield 49	crittercism/android/g:c	J
    //   53: aload_0
    //   54: invokespecial 259	crittercism/android/g:c	()Ljava/net/HttpURLConnection;
    //   57: astore 5
    //   59: aload 5
    //   61: ifnonnull +17 -> 78
    //   64: aload_0
    //   65: iconst_1
    //   66: putfield 60	crittercism/android/g:g	Z
    //   69: ldc_w 261
    //   72: invokestatic 197	crittercism/android/dx:b	(Ljava/lang/String;)V
    //   75: goto +124 -> 199
    //   78: aload_0
    //   79: getfield 62	crittercism/android/g:h	Ljava/lang/Object;
    //   82: astore 6
    //   84: aload 6
    //   86: monitorenter
    //   87: aload_0
    //   88: getfield 39	crittercism/android/g:a	Ljava/util/List;
    //   91: astore 8
    //   93: aload_0
    //   94: new 36	java/util/LinkedList
    //   97: dup
    //   98: invokespecial 37	java/util/LinkedList:<init>	()V
    //   101: putfield 39	crittercism/android/g:a	Ljava/util/List;
    //   104: aload_0
    //   105: getfield 56	crittercism/android/g:d	Landroid/os/ConditionVariable;
    //   108: invokevirtual 264	android/os/ConditionVariable:close	()V
    //   111: aload 6
    //   113: monitorexit
    //   114: aload_0
    //   115: getfield 70	crittercism/android/g:e	Lcrittercism/android/au;
    //   118: aload 8
    //   120: invokestatic 269	crittercism/android/a:a	(Lcrittercism/android/au;Ljava/util/List;)Lcrittercism/android/a;
    //   123: astore 9
    //   125: aload 9
    //   127: ifnonnull +53 -> 180
    //   130: aload_0
    //   131: iconst_1
    //   132: putfield 60	crittercism/android/g:g	Z
    //   135: ldc_w 271
    //   138: invokestatic 197	crittercism/android/dx:b	(Ljava/lang/String;)V
    //   141: goto +58 -> 199
    //   144: astore_1
    //   145: ldc_w 273
    //   148: new 110	java/lang/StringBuilder
    //   151: dup
    //   152: ldc_w 275
    //   155: invokespecial 115	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   158: aload_1
    //   159: invokevirtual 278	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   162: invokevirtual 195	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   165: invokestatic 283	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   168: pop
    //   169: goto +30 -> 199
    //   172: astore 7
    //   174: aload 6
    //   176: monitorexit
    //   177: aload 7
    //   179: athrow
    //   180: aload 5
    //   182: aload 9
    //   184: getfield 286	crittercism/android/a:a	Lorg/json/JSONObject;
    //   187: invokestatic 288	crittercism/android/g:a	(Ljava/net/HttpURLConnection;Lorg/json/JSONObject;)Z
    //   190: pop
    //   191: goto -191 -> 0
    //   194: astore 4
    //   196: goto -150 -> 46
    //   199: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	200	0	this	g
    //   144	15	1	localException	Exception
    //   25	2	3	bool	boolean
    //   194	1	4	localInterruptedException	InterruptedException
    //   57	124	5	localHttpURLConnection	HttpURLConnection
    //   172	6	7	localObject2	Object
    //   91	28	8	localList	List
    //   123	60	9	locala	a
    // Exception table:
    //   from	to	target	type
    //   0	26	144	java/lang/Exception
    //   30	46	144	java/lang/Exception
    //   46	87	144	java/lang/Exception
    //   114	141	144	java/lang/Exception
    //   174	191	144	java/lang/Exception
    //   87	114	172	finally
    //   30	46	194	java/lang/InterruptedException
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/crittercism/android/g.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */