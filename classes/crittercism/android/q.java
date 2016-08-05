package crittercism.android;

import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import javax.net.ssl.HttpsURLConnection;

public final class q
  extends m
{
  private static final String[] f;
  
  static
  {
    String[] arrayOfString = new String[3];
    arrayOfString[0] = "libcore.net.http.HttpsURLConnectionImpl";
    arrayOfString[1] = "org.apache.harmony.luni.internal.net.www.protocol.https.HttpsURLConnectionImpl";
    arrayOfString[2] = "org.apache.harmony.luni.internal.net.www.protocol.https.HttpsURLConnection";
    f = arrayOfString;
  }
  
  public q(e parame, d paramd)
  {
    super(parame, paramd, f);
  }
  
  protected final String a()
  {
    return "https";
  }
  
  protected final int getDefaultPort()
  {
    return 443;
  }
  
  protected final URLConnection openConnection(URL paramURL)
  {
    Object localObject = (HttpsURLConnection)super.openConnection(paramURL);
    try
    {
      s locals = new s((HttpsURLConnection)localObject, this.c, this.d);
      localObject = locals;
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
    Object localObject = (HttpsURLConnection)super.openConnection(paramURL, paramProxy);
    try
    {
      s locals = new s((HttpsURLConnection)localObject, this.c, this.d);
      localObject = locals;
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


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/crittercism/android/q.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */