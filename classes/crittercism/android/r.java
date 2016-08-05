package crittercism.android;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.Permission;
import java.util.Map;

public final class r
  extends HttpURLConnection
{
  private e a;
  private HttpURLConnection b;
  private c c;
  private d d;
  private boolean e = false;
  private boolean f = false;
  
  public r(HttpURLConnection paramHttpURLConnection, e parame, d paramd)
  {
    super(paramHttpURLConnection.getURL());
    this.b = paramHttpURLConnection;
    this.a = parame;
    this.d = paramd;
    this.c = new c(paramHttpURLConnection.getURL());
  }
  
  private void a()
  {
    try
    {
      if (!this.f)
      {
        this.f = true;
        this.c.f = this.b.getRequestMethod();
        this.c.b();
        this.c.j = this.d.a();
        if (bc.b()) {
          this.c.a(bc.a());
        }
      }
      return;
    }
    catch (ThreadDeath localThreadDeath)
    {
      throw localThreadDeath;
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        dx.a(localThrowable);
      }
    }
  }
  
  private void a(Throwable paramThrowable)
  {
    try
    {
      if (!this.e)
      {
        this.e = true;
        this.c.c();
        this.c.a(paramThrowable);
        this.a.a(this.c);
      }
    }
    catch (ThreadDeath localThreadDeath)
    {
      throw localThreadDeath;
    }
    catch (Throwable localThrowable)
    {
      dx.a(paramThrowable);
    }
  }
  
  /* Error */
  private void b()
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_1
    //   2: aload_0
    //   3: getfield 26	crittercism/android/r:e	Z
    //   6: ifne +152 -> 158
    //   9: aload_0
    //   10: iconst_1
    //   11: putfield 26	crittercism/android/r:e	Z
    //   14: aload_0
    //   15: getfield 39	crittercism/android/r:c	Lcrittercism/android/c;
    //   18: invokevirtual 80	crittercism/android/c:c	()V
    //   21: aload_0
    //   22: getfield 30	crittercism/android/r:b	Ljava/net/HttpURLConnection;
    //   25: invokevirtual 92	java/net/HttpURLConnection:getHeaderFields	()Ljava/util/Map;
    //   28: ifnull +101 -> 129
    //   31: new 94	crittercism/android/p
    //   34: dup
    //   35: aload_0
    //   36: getfield 30	crittercism/android/r:b	Ljava/net/HttpURLConnection;
    //   39: invokevirtual 92	java/net/HttpURLConnection:getHeaderFields	()Ljava/util/Map;
    //   42: invokespecial 97	crittercism/android/p:<init>	(Ljava/util/Map;)V
    //   45: astore 4
    //   47: aload 4
    //   49: ldc 99
    //   51: invokevirtual 102	crittercism/android/p:b	(Ljava/lang/String;)I
    //   54: istore 5
    //   56: iload 5
    //   58: bipush -1
    //   60: if_icmpeq +15 -> 75
    //   63: aload_0
    //   64: getfield 39	crittercism/android/r:c	Lcrittercism/android/c;
    //   67: iload 5
    //   69: i2l
    //   70: invokevirtual 105	crittercism/android/c:b	(J)V
    //   73: iconst_1
    //   74: istore_1
    //   75: aload 4
    //   77: ldc 107
    //   79: invokevirtual 110	crittercism/android/p:a	(Ljava/lang/String;)J
    //   82: lstore 6
    //   84: aload 4
    //   86: ldc 112
    //   88: invokevirtual 110	crittercism/android/p:a	(Ljava/lang/String;)J
    //   91: lstore 8
    //   93: lload 6
    //   95: ldc2_w 113
    //   98: lcmp
    //   99: ifeq +30 -> 129
    //   102: lload 8
    //   104: ldc2_w 113
    //   107: lcmp
    //   108: ifeq +21 -> 129
    //   111: aload_0
    //   112: getfield 39	crittercism/android/r:c	Lcrittercism/android/c;
    //   115: lload 6
    //   117: invokevirtual 116	crittercism/android/c:e	(J)V
    //   120: aload_0
    //   121: getfield 39	crittercism/android/r:c	Lcrittercism/android/c;
    //   124: lload 8
    //   126: invokevirtual 118	crittercism/android/c:f	(J)V
    //   129: aload_0
    //   130: getfield 39	crittercism/android/r:c	Lcrittercism/android/c;
    //   133: aload_0
    //   134: getfield 30	crittercism/android/r:b	Ljava/net/HttpURLConnection;
    //   137: invokevirtual 122	java/net/HttpURLConnection:getResponseCode	()I
    //   140: putfield 125	crittercism/android/c:e	I
    //   143: iload_1
    //   144: ifeq +14 -> 158
    //   147: aload_0
    //   148: getfield 32	crittercism/android/r:a	Lcrittercism/android/e;
    //   151: aload_0
    //   152: getfield 39	crittercism/android/r:c	Lcrittercism/android/c;
    //   155: invokevirtual 86	crittercism/android/e:a	(Lcrittercism/android/c;)V
    //   158: return
    //   159: astore_3
    //   160: aload_3
    //   161: athrow
    //   162: astore_2
    //   163: aload_2
    //   164: invokestatic 78	crittercism/android/dx:a	(Ljava/lang/Throwable;)V
    //   167: goto -9 -> 158
    //   170: astore 10
    //   172: goto -29 -> 143
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	175	0	this	r
    //   1	143	1	i	int
    //   162	2	2	localThrowable	Throwable
    //   159	2	3	localThreadDeath	ThreadDeath
    //   45	40	4	localp	p
    //   54	14	5	j	int
    //   82	34	6	l1	long
    //   91	34	8	l2	long
    //   170	1	10	localIOException	IOException
    // Exception table:
    //   from	to	target	type
    //   2	129	159	java/lang/ThreadDeath
    //   129	143	159	java/lang/ThreadDeath
    //   147	158	159	java/lang/ThreadDeath
    //   2	129	162	java/lang/Throwable
    //   129	143	162	java/lang/Throwable
    //   147	158	162	java/lang/Throwable
    //   129	143	170	java/io/IOException
  }
  
  public final void addRequestProperty(String paramString1, String paramString2)
  {
    this.b.addRequestProperty(paramString1, paramString2);
  }
  
  public final void connect()
  {
    this.b.connect();
  }
  
  public final void disconnect()
  {
    this.b.disconnect();
    try
    {
      if ((this.e) && (!this.c.b)) {
        this.a.a(this.c);
      }
      return;
    }
    catch (ThreadDeath localThreadDeath)
    {
      throw localThreadDeath;
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        dx.a(localThrowable);
      }
    }
  }
  
  public final boolean equals(Object paramObject)
  {
    return this.b.equals(paramObject);
  }
  
  public final boolean getAllowUserInteraction()
  {
    return this.b.getAllowUserInteraction();
  }
  
  public final int getConnectTimeout()
  {
    return this.b.getConnectTimeout();
  }
  
  public final Object getContent()
  {
    a();
    try
    {
      Object localObject = this.b.getContent();
      b();
      return localObject;
    }
    catch (IOException localIOException)
    {
      a(localIOException);
      throw localIOException;
    }
  }
  
  public final Object getContent(Class[] paramArrayOfClass)
  {
    a();
    try
    {
      Object localObject = this.b.getContent(paramArrayOfClass);
      b();
      return localObject;
    }
    catch (IOException localIOException)
    {
      a(localIOException);
      throw localIOException;
    }
  }
  
  public final String getContentEncoding()
  {
    a();
    String str = this.b.getContentEncoding();
    b();
    return str;
  }
  
  public final int getContentLength()
  {
    return this.b.getContentLength();
  }
  
  public final String getContentType()
  {
    a();
    String str = this.b.getContentType();
    b();
    return str;
  }
  
  public final long getDate()
  {
    return this.b.getDate();
  }
  
  public final boolean getDefaultUseCaches()
  {
    return this.b.getDefaultUseCaches();
  }
  
  public final boolean getDoInput()
  {
    return this.b.getDoInput();
  }
  
  public final boolean getDoOutput()
  {
    return this.b.getDoOutput();
  }
  
  public final InputStream getErrorStream()
  {
    a();
    InputStream localInputStream = this.b.getErrorStream();
    b();
    if (localInputStream != null) {}
    for (;;)
    {
      try
      {
        localObject = new t(localInputStream, this.a, this.c);
        return (InputStream)localObject;
      }
      catch (ThreadDeath localThreadDeath)
      {
        throw localThreadDeath;
      }
      catch (Throwable localThrowable)
      {
        dx.a(localThrowable);
      }
      Object localObject = localInputStream;
    }
  }
  
  public final long getExpiration()
  {
    return this.b.getExpiration();
  }
  
  public final String getHeaderField(int paramInt)
  {
    a();
    String str = this.b.getHeaderField(paramInt);
    b();
    return str;
  }
  
  public final String getHeaderField(String paramString)
  {
    a();
    String str = this.b.getHeaderField(paramString);
    b();
    return str;
  }
  
  public final long getHeaderFieldDate(String paramString, long paramLong)
  {
    a();
    long l = this.b.getHeaderFieldDate(paramString, paramLong);
    b();
    return l;
  }
  
  public final int getHeaderFieldInt(String paramString, int paramInt)
  {
    a();
    int i = this.b.getHeaderFieldInt(paramString, paramInt);
    b();
    return i;
  }
  
  public final String getHeaderFieldKey(int paramInt)
  {
    a();
    String str = this.b.getHeaderFieldKey(paramInt);
    b();
    return str;
  }
  
  public final Map getHeaderFields()
  {
    a();
    Map localMap = this.b.getHeaderFields();
    b();
    return localMap;
  }
  
  public final long getIfModifiedSince()
  {
    return this.b.getIfModifiedSince();
  }
  
  /* Error */
  public final InputStream getInputStream()
  {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial 153	crittercism/android/r:a	()V
    //   4: aload_0
    //   5: getfield 30	crittercism/android/r:b	Ljava/net/HttpURLConnection;
    //   8: invokevirtual 218	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   11: astore_2
    //   12: aload_0
    //   13: invokespecial 156	crittercism/android/r:b	()V
    //   16: aload_2
    //   17: ifnull +42 -> 59
    //   20: new 188	crittercism/android/t
    //   23: dup
    //   24: aload_2
    //   25: aload_0
    //   26: getfield 32	crittercism/android/r:a	Lcrittercism/android/e;
    //   29: aload_0
    //   30: getfield 39	crittercism/android/r:c	Lcrittercism/android/c;
    //   33: invokespecial 191	crittercism/android/t:<init>	(Ljava/io/InputStream;Lcrittercism/android/e;Lcrittercism/android/c;)V
    //   36: astore_3
    //   37: aload_3
    //   38: areturn
    //   39: astore_1
    //   40: aload_0
    //   41: aload_1
    //   42: invokespecial 157	crittercism/android/r:a	(Ljava/lang/Throwable;)V
    //   45: aload_1
    //   46: athrow
    //   47: astore 5
    //   49: aload 5
    //   51: athrow
    //   52: astore 4
    //   54: aload 4
    //   56: invokestatic 78	crittercism/android/dx:a	(Ljava/lang/Throwable;)V
    //   59: aload_2
    //   60: astore_3
    //   61: goto -24 -> 37
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	64	0	this	r
    //   39	7	1	localIOException	IOException
    //   11	49	2	localInputStream	InputStream
    //   36	25	3	localObject	Object
    //   52	3	4	localThrowable	Throwable
    //   47	3	5	localThreadDeath	ThreadDeath
    // Exception table:
    //   from	to	target	type
    //   4	16	39	java/io/IOException
    //   20	37	47	java/lang/ThreadDeath
    //   20	37	52	java/lang/Throwable
  }
  
  public final boolean getInstanceFollowRedirects()
  {
    return this.b.getInstanceFollowRedirects();
  }
  
  public final long getLastModified()
  {
    return this.b.getLastModified();
  }
  
  public final OutputStream getOutputStream()
  {
    OutputStream localOutputStream = this.b.getOutputStream();
    if (localOutputStream != null) {}
    for (;;)
    {
      try
      {
        localObject = new u(localOutputStream, this.c);
        return (OutputStream)localObject;
      }
      catch (ThreadDeath localThreadDeath)
      {
        throw localThreadDeath;
      }
      catch (Throwable localThrowable)
      {
        dx.a(localThrowable);
      }
      Object localObject = localOutputStream;
    }
  }
  
  public final Permission getPermission()
  {
    return this.b.getPermission();
  }
  
  public final int getReadTimeout()
  {
    return this.b.getReadTimeout();
  }
  
  public final String getRequestMethod()
  {
    return this.b.getRequestMethod();
  }
  
  public final Map getRequestProperties()
  {
    return this.b.getRequestProperties();
  }
  
  public final String getRequestProperty(String paramString)
  {
    return this.b.getRequestProperty(paramString);
  }
  
  public final int getResponseCode()
  {
    a();
    try
    {
      int i = this.b.getResponseCode();
      b();
      return i;
    }
    catch (IOException localIOException)
    {
      a(localIOException);
      throw localIOException;
    }
  }
  
  public final String getResponseMessage()
  {
    a();
    try
    {
      String str = this.b.getResponseMessage();
      b();
      return str;
    }
    catch (IOException localIOException)
    {
      a(localIOException);
      throw localIOException;
    }
  }
  
  public final URL getURL()
  {
    return this.b.getURL();
  }
  
  public final boolean getUseCaches()
  {
    return this.b.getUseCaches();
  }
  
  public final int hashCode()
  {
    return this.b.hashCode();
  }
  
  public final void setAllowUserInteraction(boolean paramBoolean)
  {
    this.b.setAllowUserInteraction(paramBoolean);
  }
  
  public final void setChunkedStreamingMode(int paramInt)
  {
    this.b.setChunkedStreamingMode(paramInt);
  }
  
  public final void setConnectTimeout(int paramInt)
  {
    this.b.setConnectTimeout(paramInt);
  }
  
  public final void setDefaultUseCaches(boolean paramBoolean)
  {
    this.b.setDefaultUseCaches(paramBoolean);
  }
  
  public final void setDoInput(boolean paramBoolean)
  {
    this.b.setDoInput(paramBoolean);
  }
  
  public final void setDoOutput(boolean paramBoolean)
  {
    this.b.setDoOutput(paramBoolean);
  }
  
  public final void setFixedLengthStreamingMode(int paramInt)
  {
    this.b.setFixedLengthStreamingMode(paramInt);
  }
  
  public final void setIfModifiedSince(long paramLong)
  {
    this.b.setIfModifiedSince(paramLong);
  }
  
  public final void setInstanceFollowRedirects(boolean paramBoolean)
  {
    this.b.setInstanceFollowRedirects(paramBoolean);
  }
  
  public final void setReadTimeout(int paramInt)
  {
    this.b.setReadTimeout(paramInt);
  }
  
  public final void setRequestMethod(String paramString)
  {
    this.b.setRequestMethod(paramString);
  }
  
  public final void setRequestProperty(String paramString1, String paramString2)
  {
    this.b.setRequestProperty(paramString1, paramString2);
  }
  
  public final void setUseCaches(boolean paramBoolean)
  {
    this.b.setUseCaches(paramBoolean);
  }
  
  public final String toString()
  {
    return this.b.toString();
  }
  
  public final boolean usingProxy()
  {
    return this.b.usingProxy();
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/crittercism/android/r.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */