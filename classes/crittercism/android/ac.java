package crittercism.android;

import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketImpl;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Executor;

public final class ac
  extends SocketImpl
  implements ae
{
  private static Field a;
  private static Field b;
  private static Field c;
  private static Field d;
  private static Method[] e = new Method[20];
  private static boolean f = false;
  private static Throwable g = null;
  private final Queue h = new LinkedList();
  private e i;
  private d j;
  private SocketImpl k;
  private w l;
  private x m;
  
  static
  {
    try
    {
      a = SocketImpl.class.getDeclaredField("address");
      b = SocketImpl.class.getDeclaredField("fd");
      c = SocketImpl.class.getDeclaredField("localport");
      d = SocketImpl.class.getDeclaredField("port");
      Field localField = a;
      AccessibleObject[] arrayOfAccessibleObject = new AccessibleObject[3];
      arrayOfAccessibleObject[0] = b;
      arrayOfAccessibleObject[1] = c;
      arrayOfAccessibleObject[2] = d;
      if (localField != null) {
        localField.setAccessible(true);
      }
      if (arrayOfAccessibleObject.length > 0) {
        j.a(arrayOfAccessibleObject);
      }
      Method[] arrayOfMethod1 = e;
      Class[] arrayOfClass1 = new Class[1];
      arrayOfClass1[0] = SocketImpl.class;
      arrayOfMethod1[0] = SocketImpl.class.getDeclaredMethod("accept", arrayOfClass1);
      e[1] = SocketImpl.class.getDeclaredMethod("available", new Class[0]);
      Method[] arrayOfMethod2 = e;
      Class[] arrayOfClass2 = new Class[2];
      arrayOfClass2[0] = InetAddress.class;
      arrayOfClass2[1] = Integer.TYPE;
      arrayOfMethod2[2] = SocketImpl.class.getDeclaredMethod("bind", arrayOfClass2);
      e[3] = SocketImpl.class.getDeclaredMethod("close", new Class[0]);
      Method[] arrayOfMethod3 = e;
      Class[] arrayOfClass3 = new Class[2];
      arrayOfClass3[0] = InetAddress.class;
      arrayOfClass3[1] = Integer.TYPE;
      arrayOfMethod3[4] = SocketImpl.class.getDeclaredMethod("connect", arrayOfClass3);
      Method[] arrayOfMethod4 = e;
      Class[] arrayOfClass4 = new Class[2];
      arrayOfClass4[0] = SocketAddress.class;
      arrayOfClass4[1] = Integer.TYPE;
      arrayOfMethod4[5] = SocketImpl.class.getDeclaredMethod("connect", arrayOfClass4);
      Method[] arrayOfMethod5 = e;
      Class[] arrayOfClass5 = new Class[2];
      arrayOfClass5[0] = String.class;
      arrayOfClass5[1] = Integer.TYPE;
      arrayOfMethod5[6] = SocketImpl.class.getDeclaredMethod("connect", arrayOfClass5);
      Method[] arrayOfMethod6 = e;
      Class[] arrayOfClass6 = new Class[1];
      arrayOfClass6[0] = Boolean.TYPE;
      arrayOfMethod6[7] = SocketImpl.class.getDeclaredMethod("create", arrayOfClass6);
      e[8] = SocketImpl.class.getDeclaredMethod("getFileDescriptor", new Class[0]);
      e[9] = SocketImpl.class.getDeclaredMethod("getInetAddress", new Class[0]);
      e[10] = SocketImpl.class.getDeclaredMethod("getInputStream", new Class[0]);
      e[11] = SocketImpl.class.getDeclaredMethod("getLocalPort", new Class[0]);
      e[12] = SocketImpl.class.getDeclaredMethod("getOutputStream", new Class[0]);
      e[13] = SocketImpl.class.getDeclaredMethod("getPort", new Class[0]);
      Method[] arrayOfMethod7 = e;
      Class[] arrayOfClass7 = new Class[1];
      arrayOfClass7[0] = Integer.TYPE;
      arrayOfMethod7[14] = SocketImpl.class.getDeclaredMethod("listen", arrayOfClass7);
      Method[] arrayOfMethod8 = e;
      Class[] arrayOfClass8 = new Class[1];
      arrayOfClass8[0] = Integer.TYPE;
      arrayOfMethod8[15] = SocketImpl.class.getDeclaredMethod("sendUrgentData", arrayOfClass8);
      Method[] arrayOfMethod9 = e;
      Class[] arrayOfClass9 = new Class[3];
      arrayOfClass9[0] = Integer.TYPE;
      arrayOfClass9[1] = Integer.TYPE;
      arrayOfClass9[2] = Integer.TYPE;
      arrayOfMethod9[16] = SocketImpl.class.getDeclaredMethod("setPerformancePreferences", arrayOfClass9);
      e[17] = SocketImpl.class.getDeclaredMethod("shutdownInput", new Class[0]);
      e[18] = SocketImpl.class.getDeclaredMethod("shutdownOutput", new Class[0]);
      e[19] = SocketImpl.class.getDeclaredMethod("supportsUrgentData", new Class[0]);
      j.a(e);
      f = true;
      return;
    }
    catch (SecurityException localSecurityException)
    {
      for (;;)
      {
        f = false;
        g = localSecurityException;
      }
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      f = false;
      for (n = 0;; n++)
      {
        if (n >= 20) {
          break label797;
        }
        if (e[n] == null)
        {
          g = new ck("Bad method: " + n, localNoSuchMethodException);
          break;
        }
      }
    }
    catch (NoSuchFieldException localNoSuchFieldException)
    {
      f = false;
      String str = "unknown";
      if (a == null) {
        str = "address";
      }
      for (;;)
      {
        g = new ck("No such field: " + str, localNoSuchFieldException);
        break;
        if (b == null) {
          str = "fd";
        } else if (c == null) {
          str = "localport";
        } else if (d == null) {
          str = "port";
        }
      }
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        f = false;
        g = localThrowable;
        continue;
        label797:
        int n = 20;
      }
    }
  }
  
  public ac(e parame, d paramd, SocketImpl paramSocketImpl)
  {
    if (parame == null) {
      throw new NullPointerException("dispatch was null");
    }
    if (paramSocketImpl == null) {
      throw new NullPointerException("delegate was null");
    }
    this.i = parame;
    this.j = paramd;
    this.k = paramSocketImpl;
    f();
  }
  
  private c a(boolean paramBoolean)
  {
    c localc = new c();
    InetAddress localInetAddress = getInetAddress();
    if (localInetAddress != null) {
      localc.a(localInetAddress);
    }
    int n = getPort();
    if (n > 0) {
      localc.a(n);
    }
    if (paramBoolean) {
      localc.a(k.a.a);
    }
    if (this.j != null) {
      localc.j = this.j.a();
    }
    if (bc.b()) {
      localc.a(bc.a());
    }
    return localc;
  }
  
  /* Error */
  private Object a(int paramInt, Object... paramVarArgs)
  {
    // Byte code:
    //   0: getstatic 61	crittercism/android/ac:a	Ljava/lang/reflect/Field;
    //   3: aload_0
    //   4: getfield 188	crittercism/android/ac:k	Ljava/net/SocketImpl;
    //   7: aload_0
    //   8: getfield 247	crittercism/android/ac:address	Ljava/net/InetAddress;
    //   11: invokevirtual 253	java/lang/reflect/Field:set	(Ljava/lang/Object;Ljava/lang/Object;)V
    //   14: getstatic 65	crittercism/android/ac:b	Ljava/lang/reflect/Field;
    //   17: aload_0
    //   18: getfield 188	crittercism/android/ac:k	Ljava/net/SocketImpl;
    //   21: aload_0
    //   22: getfield 256	crittercism/android/ac:fd	Ljava/io/FileDescriptor;
    //   25: invokevirtual 253	java/lang/reflect/Field:set	(Ljava/lang/Object;Ljava/lang/Object;)V
    //   28: getstatic 69	crittercism/android/ac:c	Ljava/lang/reflect/Field;
    //   31: aload_0
    //   32: getfield 188	crittercism/android/ac:k	Ljava/net/SocketImpl;
    //   35: aload_0
    //   36: getfield 259	crittercism/android/ac:localport	I
    //   39: invokevirtual 263	java/lang/reflect/Field:setInt	(Ljava/lang/Object;I)V
    //   42: getstatic 73	crittercism/android/ac:d	Ljava/lang/reflect/Field;
    //   45: aload_0
    //   46: getfield 188	crittercism/android/ac:k	Ljava/net/SocketImpl;
    //   49: aload_0
    //   50: getfield 265	crittercism/android/ac:port	I
    //   53: invokevirtual 263	java/lang/reflect/Field:setInt	(Ljava/lang/Object;I)V
    //   56: getstatic 47	crittercism/android/ac:e	[Ljava/lang/reflect/Method;
    //   59: iload_1
    //   60: aaload
    //   61: aload_0
    //   62: getfield 188	crittercism/android/ac:k	Ljava/net/SocketImpl;
    //   65: aload_2
    //   66: invokevirtual 269	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   69: astore 12
    //   71: aload_0
    //   72: invokespecial 190	crittercism/android/ac:f	()V
    //   75: aload 12
    //   77: areturn
    //   78: astore 4
    //   80: new 141	crittercism/android/ck
    //   83: dup
    //   84: aload 4
    //   86: invokespecial 272	crittercism/android/ck:<init>	(Ljava/lang/Throwable;)V
    //   89: athrow
    //   90: astore_3
    //   91: new 141	crittercism/android/ck
    //   94: dup
    //   95: aload_3
    //   96: invokespecial 272	crittercism/android/ck:<init>	(Ljava/lang/Throwable;)V
    //   99: athrow
    //   100: astore 11
    //   102: new 141	crittercism/android/ck
    //   105: dup
    //   106: aload 11
    //   108: invokespecial 272	crittercism/android/ck:<init>	(Ljava/lang/Throwable;)V
    //   111: athrow
    //   112: astore 6
    //   114: aload_0
    //   115: invokespecial 190	crittercism/android/ac:f	()V
    //   118: aload 6
    //   120: athrow
    //   121: astore 10
    //   123: new 141	crittercism/android/ck
    //   126: dup
    //   127: aload 10
    //   129: invokespecial 272	crittercism/android/ck:<init>	(Ljava/lang/Throwable;)V
    //   132: athrow
    //   133: astore 8
    //   135: aload 8
    //   137: invokevirtual 276	java/lang/reflect/InvocationTargetException:getTargetException	()Ljava/lang/Throwable;
    //   140: astore 9
    //   142: aload 9
    //   144: ifnonnull +13 -> 157
    //   147: new 141	crittercism/android/ck
    //   150: dup
    //   151: aload 8
    //   153: invokespecial 272	crittercism/android/ck:<init>	(Ljava/lang/Throwable;)V
    //   156: athrow
    //   157: aload 9
    //   159: instanceof 244
    //   162: ifeq +9 -> 171
    //   165: aload 9
    //   167: checkcast 244	java/lang/Exception
    //   170: athrow
    //   171: aload 9
    //   173: instanceof 278
    //   176: ifeq +9 -> 185
    //   179: aload 9
    //   181: checkcast 278	java/lang/Error
    //   184: athrow
    //   185: new 141	crittercism/android/ck
    //   188: dup
    //   189: aload 9
    //   191: invokespecial 272	crittercism/android/ck:<init>	(Ljava/lang/Throwable;)V
    //   194: athrow
    //   195: astore 7
    //   197: new 141	crittercism/android/ck
    //   200: dup
    //   201: aload 7
    //   203: invokespecial 272	crittercism/android/ck:<init>	(Ljava/lang/Throwable;)V
    //   206: athrow
    //   207: astore 5
    //   209: new 141	crittercism/android/ck
    //   212: dup
    //   213: aload 5
    //   215: invokespecial 272	crittercism/android/ck:<init>	(Ljava/lang/Throwable;)V
    //   218: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	219	0	this	ac
    //   0	219	1	paramInt	int
    //   0	219	2	paramVarArgs	Object[]
    //   90	6	3	localIllegalAccessException1	IllegalAccessException
    //   78	7	4	localIllegalArgumentException1	IllegalArgumentException
    //   207	7	5	localException	Exception
    //   112	7	6	localObject1	Object
    //   195	7	7	localClassCastException	ClassCastException
    //   133	19	8	localInvocationTargetException	java.lang.reflect.InvocationTargetException
    //   140	50	9	localThrowable	Throwable
    //   121	7	10	localIllegalAccessException2	IllegalAccessException
    //   100	7	11	localIllegalArgumentException2	IllegalArgumentException
    //   69	7	12	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   0	56	78	java/lang/IllegalArgumentException
    //   0	56	90	java/lang/IllegalAccessException
    //   56	71	100	java/lang/IllegalArgumentException
    //   56	71	112	finally
    //   102	112	112	finally
    //   123	219	112	finally
    //   56	71	121	java/lang/IllegalAccessException
    //   56	71	133	java/lang/reflect/InvocationTargetException
    //   56	71	195	java/lang/ClassCastException
    //   56	71	207	java/lang/Exception
  }
  
  private Object b(int paramInt, Object... paramVarArgs)
  {
    try
    {
      Object localObject = a(paramInt, paramVarArgs);
      return localObject;
    }
    catch (RuntimeException localRuntimeException)
    {
      throw localRuntimeException;
    }
    catch (Exception localException)
    {
      throw new ck(localException);
    }
  }
  
  private Object c(int paramInt, Object... paramVarArgs)
  {
    try
    {
      Object localObject = a(paramInt, paramVarArgs);
      return localObject;
    }
    catch (IOException localIOException)
    {
      throw localIOException;
    }
    catch (RuntimeException localRuntimeException)
    {
      throw localRuntimeException;
    }
    catch (Exception localException)
    {
      throw new ck(localException);
    }
  }
  
  public static boolean c()
  {
    return f;
  }
  
  public static Throwable d()
  {
    return g;
  }
  
  public static void e()
  {
    if (!f) {
      throw new ck(g);
    }
    SocketImpl local1 = new SocketImpl()
    {
      protected final void accept(SocketImpl paramAnonymousSocketImpl) {}
      
      protected final int available()
      {
        return 0;
      }
      
      protected final void bind(InetAddress paramAnonymousInetAddress, int paramAnonymousInt) {}
      
      protected final void close() {}
      
      protected final void connect(String paramAnonymousString, int paramAnonymousInt) {}
      
      protected final void connect(InetAddress paramAnonymousInetAddress, int paramAnonymousInt) {}
      
      protected final void connect(SocketAddress paramAnonymousSocketAddress, int paramAnonymousInt) {}
      
      protected final void create(boolean paramAnonymousBoolean) {}
      
      protected final FileDescriptor getFileDescriptor()
      {
        return null;
      }
      
      protected final InetAddress getInetAddress()
      {
        return null;
      }
      
      protected final InputStream getInputStream()
      {
        return null;
      }
      
      protected final int getLocalPort()
      {
        return 0;
      }
      
      public final Object getOption(int paramAnonymousInt)
      {
        return null;
      }
      
      protected final OutputStream getOutputStream()
      {
        return null;
      }
      
      protected final int getPort()
      {
        return 0;
      }
      
      protected final void listen(int paramAnonymousInt) {}
      
      protected final void sendUrgentData(int paramAnonymousInt) {}
      
      public final void setOption(int paramAnonymousInt, Object paramAnonymousObject) {}
      
      protected final void setPerformancePreferences(int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
      
      protected final void shutdownInput() {}
      
      protected final void shutdownOutput() {}
      
      protected final boolean supportsUrgentData()
      {
        return false;
      }
      
      public final String toString()
      {
        return null;
      }
    };
    ac localac = new ac(new e(new Executor()
    {
      public final void execute(Runnable paramAnonymousRunnable) {}
    }), null, local1);
    Object localObject = new Object();
    try
    {
      localac.setOption(0, localObject);
      localac.getOption(0);
      localac.sendUrgentData(0);
      localac.listen(0);
      localac.getOutputStream();
      localac.getInputStream();
      localac.create(false);
      localac.connect(null, 0);
      localac.connect(null, 0);
      localac.connect(null, 0);
      localac.close();
      localac.bind(null, 0);
      localac.available();
      localac.accept(localac);
      localac.getFileDescriptor();
      localac.getInetAddress();
      localac.getLocalPort();
      localac.getPort();
      localac.setPerformancePreferences(0, 0, 0);
      localac.shutdownInput();
      localac.shutdownOutput();
      localac.supportsUrgentData();
      return;
    }
    catch (ck localck)
    {
      throw localck;
    }
    catch (Throwable localThrowable)
    {
      throw new ck(localThrowable);
    }
    catch (IOException localIOException)
    {
      for (;;) {}
    }
  }
  
  private void f()
  {
    try
    {
      this.address = ((InetAddress)a.get(this.k));
      this.fd = ((FileDescriptor)b.get(this.k));
      this.localport = c.getInt(this.k);
      this.port = d.getInt(this.k);
      return;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      throw new ck(localIllegalArgumentException);
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      throw new ck(localIllegalAccessException);
    }
  }
  
  public final c a()
  {
    return a(true);
  }
  
  public final void a(c paramc)
  {
    synchronized (this.h)
    {
      this.h.add(paramc);
      return;
    }
  }
  
  public final void accept(SocketImpl paramSocketImpl)
  {
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = paramSocketImpl;
    c(0, arrayOfObject);
  }
  
  public final int available()
  {
    Integer localInteger = (Integer)c(1, new Object[0]);
    if (localInteger == null) {
      throw new ck("Received a null Integer");
    }
    return localInteger.intValue();
  }
  
  public final c b()
  {
    synchronized (this.h)
    {
      c localc = (c)this.h.poll();
      return localc;
    }
  }
  
  public final void bind(InetAddress paramInetAddress, int paramInt)
  {
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = paramInetAddress;
    arrayOfObject[1] = Integer.valueOf(paramInt);
    c(2, arrayOfObject);
  }
  
  public final void close()
  {
    c(3, new Object[0]);
    try
    {
      if (this.m != null) {
        this.m.d();
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
  
  public final void connect(String paramString, int paramInt)
  {
    try
    {
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = paramString;
      arrayOfObject[1] = Integer.valueOf(paramInt);
      c(6, arrayOfObject);
      return;
    }
    catch (IOException localIOException)
    {
      if (paramString == null) {}
    }
    try
    {
      c localc = a(false);
      localc.b();
      localc.c();
      localc.f();
      localc.b(paramString);
      localc.a(paramInt);
      localc.a(localIOException);
      this.i.a(localc, c.a.i);
      throw localIOException;
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
  
  public final void connect(InetAddress paramInetAddress, int paramInt)
  {
    try
    {
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = paramInetAddress;
      arrayOfObject[1] = Integer.valueOf(paramInt);
      c(4, arrayOfObject);
      return;
    }
    catch (IOException localIOException)
    {
      if (paramInetAddress == null) {}
    }
    try
    {
      c localc = a(false);
      localc.b();
      localc.c();
      localc.f();
      localc.a(paramInetAddress);
      localc.a(paramInt);
      localc.a(localIOException);
      this.i.a(localc, c.a.i);
      throw localIOException;
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
  
  public final void connect(SocketAddress paramSocketAddress, int paramInt)
  {
    try
    {
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = paramSocketAddress;
      arrayOfObject[1] = Integer.valueOf(paramInt);
      c(5, arrayOfObject);
      return;
    }
    catch (IOException localIOException)
    {
      if (paramSocketAddress == null) {}
    }
    try
    {
      if ((paramSocketAddress instanceof InetSocketAddress))
      {
        c localc = a(false);
        InetSocketAddress localInetSocketAddress = (InetSocketAddress)paramSocketAddress;
        localc.b();
        localc.c();
        localc.f();
        localc.a(localInetSocketAddress.getAddress());
        localc.a(localInetSocketAddress.getPort());
        localc.a(localIOException);
        this.i.a(localc, c.a.i);
      }
      throw localIOException;
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
  
  public final void create(boolean paramBoolean)
  {
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Boolean.valueOf(paramBoolean);
    c(7, arrayOfObject);
  }
  
  public final FileDescriptor getFileDescriptor()
  {
    return (FileDescriptor)b(8, new Object[0]);
  }
  
  public final InetAddress getInetAddress()
  {
    return (InetAddress)b(9, new Object[0]);
  }
  
  public final InputStream getInputStream()
  {
    Object localObject = (InputStream)c(10, new Object[0]);
    if (localObject != null) {
      try
      {
        if ((this.m != null) && (this.m.a((InputStream)localObject)))
        {
          localObject = this.m;
        }
        else
        {
          this.m = new x(this, (InputStream)localObject, this.i);
          localObject = this.m;
        }
      }
      catch (ThreadDeath localThreadDeath)
      {
        throw localThreadDeath;
      }
      catch (Throwable localThrowable)
      {
        dx.a(localThrowable);
      }
    }
    return (InputStream)localObject;
  }
  
  public final int getLocalPort()
  {
    return ((Integer)b(11, new Object[0])).intValue();
  }
  
  public final Object getOption(int paramInt)
  {
    return this.k.getOption(paramInt);
  }
  
  public final OutputStream getOutputStream()
  {
    Object localObject = (OutputStream)c(12, new Object[0]);
    if (localObject != null) {
      try
      {
        if ((this.l != null) && (this.l.a((OutputStream)localObject)))
        {
          localObject = this.l;
        }
        else
        {
          this.l = new w(this, (OutputStream)localObject);
          localObject = this.l;
        }
      }
      catch (ThreadDeath localThreadDeath)
      {
        throw localThreadDeath;
      }
      catch (Throwable localThrowable)
      {
        dx.a(localThrowable);
      }
    }
    return (OutputStream)localObject;
  }
  
  public final int getPort()
  {
    return ((Integer)b(13, new Object[0])).intValue();
  }
  
  public final void listen(int paramInt)
  {
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Integer.valueOf(paramInt);
    c(14, arrayOfObject);
  }
  
  public final void sendUrgentData(int paramInt)
  {
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Integer.valueOf(paramInt);
    c(15, arrayOfObject);
  }
  
  public final void setOption(int paramInt, Object paramObject)
  {
    this.k.setOption(paramInt, paramObject);
  }
  
  public final void setPerformancePreferences(int paramInt1, int paramInt2, int paramInt3)
  {
    Object[] arrayOfObject = new Object[3];
    arrayOfObject[0] = Integer.valueOf(paramInt1);
    arrayOfObject[1] = Integer.valueOf(paramInt2);
    arrayOfObject[2] = Integer.valueOf(paramInt3);
    b(16, arrayOfObject);
  }
  
  public final void shutdownInput()
  {
    c(17, new Object[0]);
  }
  
  public final void shutdownOutput()
  {
    c(18, new Object[0]);
  }
  
  public final boolean supportsUrgentData()
  {
    return ((Boolean)b(19, new Object[0])).booleanValue();
  }
  
  public final String toString()
  {
    return this.k.toString();
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/crittercism/android/ac.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */