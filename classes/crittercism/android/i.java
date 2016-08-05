package crittercism.android;

import android.os.Build.VERSION;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.Socket;
import java.net.SocketImpl;
import java.net.SocketImplFactory;
import java.net.URL;
import java.net.URLStreamHandler;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.net.ssl.HttpsURLConnection;

public final class i
{
  public static final v.a a = v.a.b;
  public static b b = b.c;
  private static final List c = new LinkedList();
  private ad d;
  private ab e;
  private ab f;
  private v g;
  private e h;
  private d i;
  private b j = b;
  private v.a k = a;
  
  static
  {
    try
    {
      URL localURL = new URL("https://www.google.com");
      if ((((URLStreamHandler)j.a(j.a(URL.class, URLStreamHandler.class), localURL)).getClass().getName().contains("okhttp")) && (Build.VERSION.SDK_INT >= 19)) {
        b = b.a;
      } else {
        b = b.b;
      }
    }
    catch (Exception localException)
    {
      b = b.c;
    }
  }
  
  public i(e parame, d paramd)
  {
    this.h = parame;
    this.i = paramd;
  }
  
  private static void a(String paramString, Throwable paramThrowable)
  {
    synchronized (c)
    {
      c.add(paramThrowable);
      dx.c(paramString);
      return;
    }
  }
  
  private static void a(javax.net.ssl.SSLSocketFactory paramSSLSocketFactory)
  {
    org.apache.http.conn.ssl.SSLSocketFactory localSSLSocketFactory = org.apache.http.conn.ssl.SSLSocketFactory.getSocketFactory();
    j.a(org.apache.http.conn.ssl.SSLSocketFactory.class, javax.net.ssl.SSLSocketFactory.class).set(localSSLSocketFactory, paramSSLSocketFactory);
  }
  
  /* Error */
  private static boolean a(SocketImplFactory paramSocketImplFactory)
  {
    // Byte code:
    //   0: iconst_1
    //   1: istore_1
    //   2: ldc -117
    //   4: ldc -115
    //   6: invokestatic 62	crittercism/android/j:a	(Ljava/lang/Class;Ljava/lang/Class;)Ljava/lang/reflect/Field;
    //   9: astore_3
    //   10: aload_3
    //   11: iconst_1
    //   12: invokevirtual 145	java/lang/reflect/Field:setAccessible	(Z)V
    //   15: aload_3
    //   16: aconst_null
    //   17: aload_0
    //   18: invokevirtual 128	java/lang/reflect/Field:set	(Ljava/lang/Object;Ljava/lang/Object;)V
    //   21: iload_1
    //   22: ireturn
    //   23: astore_2
    //   24: ldc -109
    //   26: aload_2
    //   27: invokestatic 149	crittercism/android/i:a	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   30: iconst_0
    //   31: istore_1
    //   32: goto -11 -> 21
    //   35: astore 6
    //   37: ldc -109
    //   39: aload 6
    //   41: invokestatic 149	crittercism/android/i:a	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   44: goto -23 -> 21
    //   47: astore 5
    //   49: ldc -109
    //   51: aload 5
    //   53: invokestatic 149	crittercism/android/i:a	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   56: iconst_0
    //   57: istore_1
    //   58: goto -37 -> 21
    //   61: astore 4
    //   63: ldc -109
    //   65: aload 4
    //   67: invokestatic 149	crittercism/android/i:a	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   70: iconst_0
    //   71: istore_1
    //   72: goto -51 -> 21
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	75	0	paramSocketImplFactory	SocketImplFactory
    //   1	71	1	bool	boolean
    //   23	4	2	localcl	cl
    //   9	7	3	localField	Field
    //   61	5	4	localNullPointerException	NullPointerException
    //   47	5	5	localIllegalAccessException	IllegalAccessException
    //   35	5	6	localIllegalArgumentException	IllegalArgumentException
    // Exception table:
    //   from	to	target	type
    //   2	10	23	crittercism/android/cl
    //   10	21	35	java/lang/IllegalArgumentException
    //   10	21	47	java/lang/IllegalAccessException
    //   10	21	61	java/lang/NullPointerException
  }
  
  public static void d()
  {
    synchronized (c)
    {
      Iterator localIterator = c.iterator();
      if (localIterator.hasNext()) {
        dx.a((Throwable)localIterator.next());
      }
    }
    c.clear();
  }
  
  private boolean e()
  {
    a locala = new a(this);
    Thread localThread = new Thread(locala);
    localThread.start();
    try
    {
      localThread.join();
      return locala.a();
    }
    catch (InterruptedException localInterruptedException)
    {
      for (;;) {}
    }
  }
  
  private boolean f()
  {
    boolean bool1 = false;
    try
    {
      this.g = new v(this.k, this.h, this.i);
      boolean bool2 = this.g.b();
      bool1 = bool2;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      for (;;) {}
    }
    return bool1;
  }
  
  private static javax.net.ssl.SSLSocketFactory g()
  {
    org.apache.http.conn.ssl.SSLSocketFactory localSSLSocketFactory = org.apache.http.conn.ssl.SSLSocketFactory.getSocketFactory();
    return (javax.net.ssl.SSLSocketFactory)j.a(org.apache.http.conn.ssl.SSLSocketFactory.class, javax.net.ssl.SSLSocketFactory.class).get(localSSLSocketFactory);
  }
  
  private boolean h()
  {
    localObject = null;
    try
    {
      localSocketImplFactory = (SocketImplFactory)j.a(j.a(Socket.class, SocketImplFactory.class), null);
      if (localSocketImplFactory != null) {
        break label140;
      }
      try
      {
        localSocketImpl = (SocketImpl)j.a(j.a(Socket.class, SocketImpl.class), new Socket());
        if (localSocketImpl != null) {
          break label85;
        }
        throw new cl("SocketImpl was null");
      }
      catch (cl localcl3)
      {
        a("Unable to install OPTIMZ for http connections", localcl3);
        bool = false;
      }
    }
    catch (cl localcl1)
    {
      for (;;)
      {
        SocketImplFactory localSocketImplFactory;
        SocketImpl localSocketImpl;
        a("Unable to install OPTIMZ for http connections", localcl1);
        boolean bool = false;
        continue;
        Class localClass = localSocketImpl.getClass();
        localObject = localClass;
        if (localSocketImplFactory != null) {}
        try
        {
          ad localad2 = new ad(localSocketImplFactory, this.h, this.i);
          a(localad2);
          ad localad1 = localad2;
          for (;;)
          {
            this.d = localad1;
            bool = true;
            break;
            if (!(localSocketImplFactory instanceof ad)) {
              break label95;
            }
            bool = true;
            break;
            if (localObject == null) {
              break label197;
            }
            localad1 = new ad((Class)localObject, this.h, this.i);
            Socket.setSocketImplFactory(localad1);
          }
        }
        catch (cl localcl2)
        {
          a("Unable to install OPTIMZ for http connections", localcl2);
          bool = false;
          continue;
          a("Unable to install OPTIMZ for http connections", new NullPointerException("Null SocketImpl"));
          bool = false;
        }
        catch (IOException localIOException)
        {
          a("Unable to install OPTIMZ for http connections", localIOException);
          bool = false;
        }
      }
    }
    return bool;
  }
  
  public final boolean a()
  {
    boolean bool1 = false;
    if (!ac.c())
    {
      a("Unable to install OPTMZ", ac.d());
      return bool1;
    }
    for (;;)
    {
      boolean bool2;
      javax.net.ssl.SSLSocketFactory localSSLSocketFactory;
      try
      {
        ac.e();
        bool2 = false | h();
        if (Build.VERSION.SDK_INT < 19) {
          break label118;
        }
        bool3 = bool2 | e();
        if (Build.VERSION.SDK_INT < 17) {
          break label180;
        }
        bool1 = bool3 | y.a(this.h, this.i);
        if (this.j != b.a) {
          break label160;
        }
        localSSLSocketFactory = HttpsURLConnection.getDefaultSSLSocketFactory();
        if (!(localSSLSocketFactory instanceof ab)) {
          break label129;
        }
        this.e = ((ab)localSSLSocketFactory);
        bool1 |= true;
      }
      catch (ck localck)
      {
        dx.a(localck.toString(), localck);
      }
      break;
      label118:
      boolean bool3 = bool2 | c();
      continue;
      label129:
      this.e = new ab(localSSLSocketFactory, this.h, this.i);
      HttpsURLConnection.setDefaultSSLSocketFactory(this.e);
      continue;
      label160:
      if (this.j != b.b) {
        break;
      }
      bool1 |= f();
      break;
      label180:
      bool1 = bool3;
    }
  }
  
  public final void b()
  {
    try
    {
      javax.net.ssl.SSLSocketFactory localSSLSocketFactory = g();
      if ((localSSLSocketFactory instanceof ab)) {
        a(((ab)localSSLSocketFactory).a());
      }
      this.f = null;
      return;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      for (;;)
      {
        a("Unable to install OPTIMZ for SSL HttpClient connections", localIllegalArgumentException);
      }
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      for (;;)
      {
        a("Unable to install OPTIMZ for SSL HttpClient connections", localIllegalAccessException);
      }
    }
    catch (cl localcl)
    {
      for (;;)
      {
        a("Unable to install OPTIMZ for SSL HttpClient connections", localcl);
      }
    }
  }
  
  public final boolean c()
  {
    bool = false;
    try
    {
      localSSLSocketFactory = g();
      if (localSSLSocketFactory != null) {
        break label78;
      }
      a("Unable to install OPTIMZ for SSL HttpClient connections", new NullPointerException("Delegate factory was null"));
    }
    catch (IllegalArgumentException localIllegalArgumentException1)
    {
      for (;;)
      {
        a("Unable to install OPTIMZ for SSL HttpClient connections", localIllegalArgumentException1);
      }
    }
    catch (IllegalAccessException localIllegalAccessException1)
    {
      for (;;)
      {
        a("Unable to install OPTIMZ for SSL HttpClient connections", localIllegalAccessException1);
      }
    }
    catch (ClassCastException localClassCastException)
    {
      for (;;)
      {
        a("Unable to install OPTIMZ for SSL HttpClient connections", localClassCastException);
      }
    }
    catch (cl localcl1)
    {
      for (;;)
      {
        javax.net.ssl.SSLSocketFactory localSSLSocketFactory;
        a("Unable to install OPTIMZ for SSL HttpClient connections", localcl1);
        continue;
        if (!(localSSLSocketFactory instanceof ab))
        {
          ab localab = new ab(localSSLSocketFactory, this.h, this.i);
          try
          {
            a(localab);
            this.f = localab;
            bool = true;
          }
          catch (IllegalArgumentException localIllegalArgumentException2)
          {
            a("Unable to install OPTIMZ for SSL HttpClient connections", localIllegalArgumentException2);
          }
          catch (IllegalAccessException localIllegalAccessException2)
          {
            a("Unable to install OPTIMZ for SSL HttpClient connections", localIllegalAccessException2);
          }
          catch (cl localcl2)
          {
            a("Unable to install OPTIMZ for SSL HttpClient connections", localcl2);
          }
        }
      }
    }
    return bool;
  }
  
  static class a
    implements Runnable
  {
    private boolean a;
    private boolean b = false;
    private i c;
    
    public a(i parami)
    {
      this.c = parami;
      this.a = true;
    }
    
    public final boolean a()
    {
      return this.b;
    }
    
    public final void run()
    {
      if (this.a) {
        this.b = this.c.c();
      }
      for (;;)
      {
        return;
        this.c.b();
      }
    }
  }
  
  public static enum b
  {
    static
    {
      b[] arrayOfb = new b[3];
      arrayOfb[0] = a;
      arrayOfb[1] = b;
      arrayOfb[2] = c;
      d = arrayOfb;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/crittercism/android/i.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */