package crittercism.android;

import java.net.HttpURLConnection;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

public final class dc
{
  private static SSLSocketFactory a = null;
  private URL b;
  private Map c = new HashMap();
  private int d = 0;
  private boolean e = true;
  private boolean f = true;
  private String g = "POST";
  private boolean h = false;
  private int i = 2500;
  
  static
  {
    try
    {
      SSLContext localSSLContext = SSLContext.getInstance("TLS");
      localSSLContext.init(null, null, null);
      SSLSocketFactory localSSLSocketFactory = localSSLContext.getSocketFactory();
      if (localSSLSocketFactory != null) {
        if ((localSSLSocketFactory instanceof ab)) {
          a = ((ab)localSSLSocketFactory).a();
        } else {
          a = localSSLSocketFactory;
        }
      }
    }
    catch (GeneralSecurityException localGeneralSecurityException)
    {
      a = null;
    }
  }
  
  public dc(URL paramURL)
  {
    this.b = paramURL;
    Map localMap1 = this.c;
    String[] arrayOfString1 = new String[1];
    arrayOfString1[0] = "5.0.8";
    localMap1.put("User-Agent", Arrays.asList(arrayOfString1));
    Map localMap2 = this.c;
    String[] arrayOfString2 = new String[1];
    arrayOfString2[0] = "application/json";
    localMap2.put("Content-Type", Arrays.asList(arrayOfString2));
    Map localMap3 = this.c;
    String[] arrayOfString3 = new String[2];
    arrayOfString3[0] = "text/plain";
    arrayOfString3[1] = "application/json";
    localMap3.put("Accept", Arrays.asList(arrayOfString3));
  }
  
  public final HttpURLConnection a()
  {
    HttpURLConnection localHttpURLConnection = (HttpURLConnection)this.b.openConnection();
    Iterator localIterator1 = this.c.entrySet().iterator();
    while (localIterator1.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator1.next();
      Iterator localIterator2 = ((List)localEntry.getValue()).iterator();
      while (localIterator2.hasNext())
      {
        String str = (String)localIterator2.next();
        localHttpURLConnection.addRequestProperty((String)localEntry.getKey(), str);
      }
    }
    localHttpURLConnection.setConnectTimeout(this.i);
    localHttpURLConnection.setReadTimeout(this.i);
    localHttpURLConnection.setDoInput(this.e);
    localHttpURLConnection.setDoOutput(this.f);
    if (this.h) {
      localHttpURLConnection.setChunkedStreamingMode(this.d);
    }
    localHttpURLConnection.setRequestMethod(this.g);
    if ((localHttpURLConnection instanceof HttpsURLConnection))
    {
      HttpsURLConnection localHttpsURLConnection = (HttpsURLConnection)localHttpURLConnection;
      if (a != null) {
        localHttpsURLConnection.setSSLSocketFactory(a);
      }
    }
    else
    {
      return localHttpURLConnection;
    }
    throw new GeneralSecurityException();
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/crittercism/android/dc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */