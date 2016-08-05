package crittercism.android;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.KeyManagementException;
import java.security.SecureRandom;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContextSpi;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSessionContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

public final class z
  extends SSLContextSpi
{
  private static Method[] a = new Method[7];
  private static boolean b = false;
  private SSLContextSpi c;
  private e d;
  private d e;
  
  static
  {
    try
    {
      a[0] = SSLContextSpi.class.getDeclaredMethod("engineCreateSSLEngine", new Class[0]);
      Method[] arrayOfMethod1 = a;
      Class[] arrayOfClass1 = new Class[2];
      arrayOfClass1[0] = String.class;
      arrayOfClass1[1] = Integer.TYPE;
      arrayOfMethod1[1] = SSLContextSpi.class.getDeclaredMethod("engineCreateSSLEngine", arrayOfClass1);
      a[2] = SSLContextSpi.class.getDeclaredMethod("engineGetClientSessionContext", new Class[0]);
      a[3] = SSLContextSpi.class.getDeclaredMethod("engineGetServerSessionContext", new Class[0]);
      a[4] = SSLContextSpi.class.getDeclaredMethod("engineGetServerSocketFactory", new Class[0]);
      a[5] = SSLContextSpi.class.getDeclaredMethod("engineGetSocketFactory", new Class[0]);
      Method[] arrayOfMethod2 = a;
      Class[] arrayOfClass2 = new Class[3];
      arrayOfClass2[0] = KeyManager[].class;
      arrayOfClass2[1] = TrustManager[].class;
      arrayOfClass2[2] = SecureRandom.class;
      arrayOfMethod2[6] = SSLContextSpi.class.getDeclaredMethod("engineInit", arrayOfClass2);
      j.a(a);
      z localz = new z(new z(), null, null);
      localz.engineCreateSSLEngine();
      localz.engineCreateSSLEngine(null, 0);
      localz.engineGetClientSessionContext();
      localz.engineGetServerSessionContext();
      localz.engineGetServerSocketFactory();
      localz.engineGetSocketFactory();
      localz.engineInit(null, null, null);
      b = true;
      return;
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        dx.c();
        b = false;
      }
    }
  }
  
  private z() {}
  
  private z(SSLContextSpi paramSSLContextSpi, e parame, d paramd)
  {
    this.c = paramSSLContextSpi;
    this.d = parame;
    this.e = paramd;
  }
  
  public static z a(SSLContextSpi paramSSLContextSpi, e parame, d paramd)
  {
    if (!b) {}
    for (z localz = null;; localz = new z(paramSSLContextSpi, parame, paramd)) {
      return localz;
    }
  }
  
  private Object a(int paramInt, Object... paramVarArgs)
  {
    Object localObject2;
    if (this.c == null) {
      localObject2 = null;
    }
    for (;;)
    {
      return localObject2;
      try
      {
        Object localObject1 = a[paramInt].invoke(this.c, paramVarArgs);
        localObject2 = localObject1;
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        throw new ck(localIllegalArgumentException);
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
        throw new ck(localIllegalAccessException);
      }
      catch (InvocationTargetException localInvocationTargetException)
      {
        Throwable localThrowable = localInvocationTargetException.getTargetException();
        if (localThrowable == null) {
          throw new ck(localInvocationTargetException);
        }
        if ((localThrowable instanceof Exception)) {
          throw ((Exception)localThrowable);
        }
        if ((localThrowable instanceof Error)) {
          throw ((Error)localThrowable);
        }
        throw new ck(localInvocationTargetException);
      }
      catch (ClassCastException localClassCastException)
      {
        throw new ck(localClassCastException);
      }
    }
  }
  
  private Object a(Object... paramVarArgs)
  {
    try
    {
      Object localObject = a(6, paramVarArgs);
      return localObject;
    }
    catch (RuntimeException localRuntimeException)
    {
      throw localRuntimeException;
    }
    catch (KeyManagementException localKeyManagementException)
    {
      throw localKeyManagementException;
    }
    catch (Exception localException)
    {
      throw new ck(localException);
    }
  }
  
  public static boolean a()
  {
    return b;
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
  
  protected final SSLEngine engineCreateSSLEngine()
  {
    return (SSLEngine)b(0, new Object[0]);
  }
  
  protected final SSLEngine engineCreateSSLEngine(String paramString, int paramInt)
  {
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = paramString;
    arrayOfObject[1] = Integer.valueOf(paramInt);
    return (SSLEngine)b(1, arrayOfObject);
  }
  
  protected final SSLSessionContext engineGetClientSessionContext()
  {
    return (SSLSessionContext)b(2, new Object[0]);
  }
  
  protected final SSLSessionContext engineGetServerSessionContext()
  {
    return (SSLSessionContext)b(3, new Object[0]);
  }
  
  protected final SSLServerSocketFactory engineGetServerSocketFactory()
  {
    return (SSLServerSocketFactory)b(4, new Object[0]);
  }
  
  protected final SSLSocketFactory engineGetSocketFactory()
  {
    Object localObject = (SSLSocketFactory)b(5, new Object[0]);
    if (localObject != null) {}
    try
    {
      ab localab = new ab((SSLSocketFactory)localObject, this.d, this.e);
      localObject = localab;
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
    return (SSLSocketFactory)localObject;
  }
  
  protected final void engineInit(KeyManager[] paramArrayOfKeyManager, TrustManager[] paramArrayOfTrustManager, SecureRandom paramSecureRandom)
  {
    Object[] arrayOfObject = new Object[3];
    arrayOfObject[0] = paramArrayOfKeyManager;
    arrayOfObject[1] = paramArrayOfTrustManager;
    arrayOfObject[2] = paramSecureRandom;
    a(arrayOfObject);
  }
  
  public final boolean equals(Object paramObject)
  {
    return this.c.equals(paramObject);
  }
  
  public final int hashCode()
  {
    return this.c.hashCode();
  }
  
  public final String toString()
  {
    return this.c.toString();
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/crittercism/android/z.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */