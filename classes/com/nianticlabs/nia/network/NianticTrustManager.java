package com.nianticlabs.nia.network;

import android.content.Context;
import com.nianticlabs.nia.contextservice.ContextService;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

public class NianticTrustManager
  extends ContextService
  implements X509TrustManager
{
  public NianticTrustManager(Context paramContext, long paramLong)
  {
    super(paramContext, paramLong);
  }
  
  public static X509TrustManager getTrustManager(String paramString, KeyStore paramKeyStore)
  {
    try
    {
      TrustManagerFactory localTrustManagerFactory = TrustManagerFactory.getInstance(paramString);
      localTrustManagerFactory.init(paramKeyStore);
      for (TrustManager localTrustManager : localTrustManagerFactory.getTrustManagers()) {
        if ((localTrustManager != null) && ((localTrustManager instanceof X509TrustManager)))
        {
          localX509TrustManager = (X509TrustManager)localTrustManager;
          return localX509TrustManager;
        }
      }
    }
    catch (KeyStoreException localKeyStoreException)
    {
      for (;;)
      {
        X509TrustManager localX509TrustManager = null;
      }
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      for (;;) {}
    }
  }
  
  public static X509TrustManager getTrustManager(KeyStore paramKeyStore)
  {
    return getTrustManager(TrustManagerFactory.getDefaultAlgorithm(), paramKeyStore);
  }
  
  private native void nativeCheckClientTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString)
    throws CertificateException;
  
  private native void nativeCheckServerTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString)
    throws CertificateException;
  
  private native X509Certificate[] nativeGetAcceptedIssuers();
  
  public void checkClientTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString)
    throws CertificateException
  {
    synchronized (this.callbackLock)
    {
      nativeCheckServerTrusted(paramArrayOfX509Certificate, paramString);
      return;
    }
  }
  
  public void checkServerTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString)
    throws CertificateException
  {
    synchronized (this.callbackLock)
    {
      nativeCheckServerTrusted(paramArrayOfX509Certificate, paramString);
      return;
    }
  }
  
  public X509Certificate[] getAcceptedIssuers()
  {
    synchronized (this.callbackLock)
    {
      X509Certificate[] arrayOfX509Certificate = nativeGetAcceptedIssuers();
      return arrayOfX509Certificate;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/nianticlabs/nia/network/NianticTrustManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */