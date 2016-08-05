package crittercism.android;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

public abstract class m
  extends URLStreamHandler
{
  public static final String[] a;
  public static final String[] b;
  e c;
  d d;
  boolean e;
  private Constructor f = null;
  private Constructor g = null;
  
  static
  {
    String[] arrayOfString1 = new String[3];
    arrayOfString1[0] = "java.net.URL";
    arrayOfString1[1] = "int";
    arrayOfString1[2] = "java.net.Proxy";
    a = arrayOfString1;
    String[] arrayOfString2 = new String[2];
    arrayOfString2[0] = "java.net.URL";
    arrayOfString2[1] = "int";
    b = arrayOfString2;
  }
  
  public m(e parame, d paramd, String[] paramArrayOfString)
  {
    this(parame, paramd, paramArrayOfString, a, b);
  }
  
  private m(e parame, d paramd, String[] paramArrayOfString1, String[] paramArrayOfString2, String[] paramArrayOfString3)
  {
    this.c = parame;
    this.d = paramd;
    this.e = true;
    int i = 0;
    for (;;)
    {
      if (i < paramArrayOfString1.length) {}
      try
      {
        this.f = l.a(paramArrayOfString1[i], paramArrayOfString3);
        this.g = l.a(paramArrayOfString1[i], paramArrayOfString2);
        this.f.setAccessible(true);
        this.g.setAccessible(true);
        if ((this.f != null) && (this.g != null)) {
          break;
        }
        throw new ClassNotFoundException("Couldn't find suitable connection implementations");
      }
      catch (ClassNotFoundException localClassNotFoundException)
      {
        this.f = null;
        this.f = null;
        i++;
      }
    }
    if (!b()) {
      throw new ClassNotFoundException("Unable to open test connections");
    }
  }
  
  private URLConnection a(URL paramURL, Proxy paramProxy)
  {
    Object localObject1 = null;
    String str = "Unable to setup network statistics on a " + a() + " connection due to ";
    IOException localIOException;
    try
    {
      if (paramProxy != null) {
        break label145;
      }
      Constructor localConstructor2 = this.f;
      Object[] arrayOfObject2 = new Object[2];
      arrayOfObject2[0] = paramURL;
      arrayOfObject2[1] = Integer.valueOf(getDefaultPort());
      URLConnection localURLConnection2 = (URLConnection)localConstructor2.newInstance(arrayOfObject2);
      localObject1 = localURLConnection2;
      localIOException = null;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      for (;;)
      {
        v localv;
        new StringBuilder().append(str).append("bad arguments");
        dx.b();
        localIOException = new IOException(localIllegalArgumentException.getMessage());
      }
    }
    catch (InstantiationException localInstantiationException)
    {
      for (;;)
      {
        new StringBuilder().append(str).append("an instantiation problem");
        dx.b();
        localIOException = new IOException(localInstantiationException.getMessage());
      }
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      for (;;)
      {
        new StringBuilder().append(str).append("security restrictions");
        dx.b();
        localIOException = new IOException(localIllegalAccessException.getMessage());
      }
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      for (;;)
      {
        new StringBuilder().append(str).append("an invocation problem");
        dx.b();
        localIOException = new IOException(localInvocationTargetException.getMessage());
      }
    }
    if (localIOException != null) {
      if (this.e)
      {
        this.e = false;
        localv = v.a();
        if (localv == null) {
          break label368;
        }
      }
    }
    label145:
    label368:
    for (boolean bool = localv.c();; bool = false)
    {
      dx.b("Stopping network statistics monitoring");
      if (bool) {}
      for (Object localObject2 = new URL(paramURL.toExternalForm()).openConnection();; localObject2 = localObject1)
      {
        return (URLConnection)localObject2;
        Constructor localConstructor1 = this.g;
        Object[] arrayOfObject1 = new Object[3];
        arrayOfObject1[0] = paramURL;
        arrayOfObject1[1] = Integer.valueOf(getDefaultPort());
        arrayOfObject1[2] = paramProxy;
        URLConnection localURLConnection1 = (URLConnection)localConstructor1.newInstance(arrayOfObject1);
        localObject1 = localURLConnection1;
        localIOException = null;
        break;
        throw localIOException;
      }
    }
  }
  
  private boolean b()
  {
    bool = true;
    this.e = false;
    try
    {
      openConnection(new URL("http://www.google.com"));
      this.e = bool;
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        localIOException = localIOException;
        this.e = bool;
        bool = false;
      }
    }
    finally
    {
      localObject = finally;
      this.e = bool;
      throw ((Throwable)localObject);
    }
    return bool;
  }
  
  protected abstract String a();
  
  protected abstract int getDefaultPort();
  
  protected URLConnection openConnection(URL paramURL)
  {
    return a(paramURL, null);
  }
  
  protected URLConnection openConnection(URL paramURL, Proxy paramProxy)
  {
    if ((paramURL == null) || (paramProxy == null)) {
      throw new IllegalArgumentException("url == null || proxy == null");
    }
    return a(paramURL, paramProxy);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/crittercism/android/m.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */