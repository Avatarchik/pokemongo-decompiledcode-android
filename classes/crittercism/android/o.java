package crittercism.android;

import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;

public final class o
  extends m
{
  private static final String[] f;
  
  static
  {
    String[] arrayOfString = new String[3];
    arrayOfString[0] = "libcore.net.http.HttpURLConnectionImpl";
    arrayOfString[1] = "org.apache.harmony.luni.internal.net.www.protocol.http.HttpURLConnectionImpl";
    arrayOfString[2] = "org.apache.harmony.luni.internal.net.www.protocol.http.HttpURLConnection";
    f = arrayOfString;
  }
  
  public o(e parame, d paramd)
  {
    super(parame, paramd, f);
  }
  
  protected final String a()
  {
    return "http";
  }
  
  protected final int getDefaultPort()
  {
    return 80;
  }
  
  protected final URLConnection openConnection(URL paramURL)
  {
    Object localObject = (HttpURLConnection)super.openConnection(paramURL);
    try
    {
      r localr = new r((HttpURLConnection)localObject, this.c, this.d);
      localObject = localr;
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
    return (URLConnection)localObject;
  }
  
  protected final URLConnection openConnection(URL paramURL, Proxy paramProxy)
  {
    Object localObject = (HttpURLConnection)super.openConnection(paramURL, paramProxy);
    try
    {
      r localr = new r((HttpURLConnection)localObject, this.c, this.d);
      localObject = localr;
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
    return (URLConnection)localObject;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/crittercism/android/o.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */