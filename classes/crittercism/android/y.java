package crittercism.android;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Provider.Service;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLContextSpi;

public final class y
  extends Provider.Service
{
  public static final String[] a;
  private e b;
  private d c;
  private Provider.Service d;
  
  static
  {
    String[] arrayOfString = new String[7];
    arrayOfString[0] = "Default";
    arrayOfString[1] = "SSL";
    arrayOfString[2] = "TLSv1.1";
    arrayOfString[3] = "TLSv1.2";
    arrayOfString[4] = "SSLv3";
    arrayOfString[5] = "TLSv1";
    arrayOfString[6] = "TLS";
    a = arrayOfString;
  }
  
  private y(Provider.Service paramService, e parame, d paramd)
  {
    super(paramService.getProvider(), paramService.getType(), paramService.getAlgorithm(), paramService.getClassName(), null, null);
    this.b = parame;
    this.c = paramd;
    this.d = paramService;
  }
  
  private static y a(Provider.Service paramService, e parame, d paramd)
  {
    localy = new y(paramService, parame, paramd);
    try
    {
      Field[] arrayOfField = Provider.Service.class.getFields();
      for (int i = 0; i < arrayOfField.length; i++)
      {
        arrayOfField[i].setAccessible(true);
        arrayOfField[i].set(localy, arrayOfField[i].get(paramService));
      }
      return localy;
    }
    catch (Exception localException)
    {
      localy = null;
    }
  }
  
  private static Provider a()
  {
    Provider localProvider = null;
    try
    {
      SSLContext localSSLContext = SSLContext.getInstance("TLS");
      if (localSSLContext != null) {
        localProvider = localSSLContext.getProvider();
      }
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      for (;;) {}
    }
    return localProvider;
  }
  
  public static boolean a(e parame, d paramd)
  {
    int i = 0;
    if (!z.a()) {}
    for (;;)
    {
      return i;
      Provider localProvider = a();
      if (localProvider != null)
      {
        int k = 0;
        while (i < a.length)
        {
          Provider.Service localService = localProvider.getService("SSLContext", a[i]);
          if ((localService != null) && (!(localService instanceof y)))
          {
            y localy = a(localService, parame, paramd);
            if (localy != null) {
              k |= localy.b();
            }
          }
          i += 1;
        }
        int j = k;
      }
    }
  }
  
  private boolean b()
  {
    boolean bool = false;
    Provider localProvider = getProvider();
    if (localProvider == null) {}
    for (;;)
    {
      return bool;
      try
      {
        Class[] arrayOfClass = new Class[1];
        arrayOfClass[0] = Provider.Service.class;
        Method localMethod = Provider.class.getDeclaredMethod("putService", arrayOfClass);
        localMethod.setAccessible(true);
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = this;
        localMethod.invoke(localProvider, arrayOfObject);
        localProvider.put("SSLContext.DummySSLAlgorithm", getClassName());
        localProvider.remove(getType() + "." + getAlgorithm());
        localProvider.remove("SSLContext.DummySSLAlgorithm");
        bool = true;
      }
      catch (Exception localException) {}
    }
  }
  
  public final Object newInstance(Object paramObject)
  {
    Object localObject = super.newInstance(paramObject);
    try
    {
      if ((localObject instanceof SSLContextSpi))
      {
        z localz = z.a((SSLContextSpi)localObject, this.b, this.c);
        if (localz != null) {
          localObject = localz;
        }
      }
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
    return localObject;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/crittercism/android/y.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */