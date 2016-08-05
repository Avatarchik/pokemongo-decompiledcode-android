package crittercism.android;

import java.net.InetAddress;
import java.net.Socket;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public final class ab
  extends SSLSocketFactory
{
  private SSLSocketFactory a;
  private e b;
  private d c;
  
  public ab(SSLSocketFactory paramSSLSocketFactory, e parame, d paramd)
  {
    this.a = paramSSLSocketFactory;
    this.b = parame;
    this.c = paramd;
  }
  
  private Socket a(Socket paramSocket)
  {
    if (paramSocket != null) {}
    try
    {
      if ((paramSocket instanceof SSLSocket))
      {
        aa localaa = new aa((SSLSocket)paramSocket, this.b, this.c);
        paramSocket = localaa;
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
    return paramSocket;
  }
  
  public final SSLSocketFactory a()
  {
    return this.a;
  }
  
  public final Socket createSocket()
  {
    return a(this.a.createSocket());
  }
  
  public final Socket createSocket(String paramString, int paramInt)
  {
    return a(this.a.createSocket(paramString, paramInt));
  }
  
  public final Socket createSocket(String paramString, int paramInt1, InetAddress paramInetAddress, int paramInt2)
  {
    return a(this.a.createSocket(paramString, paramInt1, paramInetAddress, paramInt2));
  }
  
  public final Socket createSocket(InetAddress paramInetAddress, int paramInt)
  {
    return a(this.a.createSocket(paramInetAddress, paramInt));
  }
  
  public final Socket createSocket(InetAddress paramInetAddress1, int paramInt1, InetAddress paramInetAddress2, int paramInt2)
  {
    return a(this.a.createSocket(paramInetAddress1, paramInt1, paramInetAddress2, paramInt2));
  }
  
  public final Socket createSocket(Socket paramSocket, String paramString, int paramInt, boolean paramBoolean)
  {
    return a(this.a.createSocket(paramSocket, paramString, paramInt, paramBoolean));
  }
  
  public final String[] getDefaultCipherSuites()
  {
    return this.a.getDefaultCipherSuites();
  }
  
  public final String[] getSupportedCipherSuites()
  {
    return this.a.getSupportedCipherSuites();
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/crittercism/android/ab.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */