package com.google.android.gms.internal;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;

public class zzz
  implements zzy
{
  private final zza zzaE;
  private final SSLSocketFactory zzaF;
  
  public zzz()
  {
    this(null);
  }
  
  public zzz(zza paramzza)
  {
    this(paramzza, null);
  }
  
  public zzz(zza paramzza, SSLSocketFactory paramSSLSocketFactory)
  {
    this.zzaE = paramzza;
    this.zzaF = paramSSLSocketFactory;
  }
  
  private HttpURLConnection zza(URL paramURL, zzk<?> paramzzk)
    throws IOException
  {
    HttpURLConnection localHttpURLConnection = zza(paramURL);
    int i = paramzzk.zzt();
    localHttpURLConnection.setConnectTimeout(i);
    localHttpURLConnection.setReadTimeout(i);
    localHttpURLConnection.setUseCaches(false);
    localHttpURLConnection.setDoInput(true);
    if (("https".equals(paramURL.getProtocol())) && (this.zzaF != null)) {
      ((HttpsURLConnection)localHttpURLConnection).setSSLSocketFactory(this.zzaF);
    }
    return localHttpURLConnection;
  }
  
  private static HttpEntity zza(HttpURLConnection paramHttpURLConnection)
  {
    BasicHttpEntity localBasicHttpEntity = new BasicHttpEntity();
    try
    {
      InputStream localInputStream2 = paramHttpURLConnection.getInputStream();
      localInputStream1 = localInputStream2;
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        InputStream localInputStream1 = paramHttpURLConnection.getErrorStream();
      }
    }
    localBasicHttpEntity.setContent(localInputStream1);
    localBasicHttpEntity.setContentLength(paramHttpURLConnection.getContentLength());
    localBasicHttpEntity.setContentEncoding(paramHttpURLConnection.getContentEncoding());
    localBasicHttpEntity.setContentType(paramHttpURLConnection.getContentType());
    return localBasicHttpEntity;
  }
  
  static void zza(HttpURLConnection paramHttpURLConnection, zzk<?> paramzzk)
    throws IOException, zza
  {
    switch (paramzzk.getMethod())
    {
    default: 
      throw new IllegalStateException("Unknown method type.");
    case -1: 
      byte[] arrayOfByte = paramzzk.zzm();
      if (arrayOfByte != null)
      {
        paramHttpURLConnection.setDoOutput(true);
        paramHttpURLConnection.setRequestMethod("POST");
        paramHttpURLConnection.addRequestProperty("Content-Type", paramzzk.zzl());
        DataOutputStream localDataOutputStream = new DataOutputStream(paramHttpURLConnection.getOutputStream());
        localDataOutputStream.write(arrayOfByte);
        localDataOutputStream.close();
      }
      break;
    }
    for (;;)
    {
      return;
      paramHttpURLConnection.setRequestMethod("GET");
      continue;
      paramHttpURLConnection.setRequestMethod("DELETE");
      continue;
      paramHttpURLConnection.setRequestMethod("POST");
      zzb(paramHttpURLConnection, paramzzk);
      continue;
      paramHttpURLConnection.setRequestMethod("PUT");
      zzb(paramHttpURLConnection, paramzzk);
      continue;
      paramHttpURLConnection.setRequestMethod("HEAD");
      continue;
      paramHttpURLConnection.setRequestMethod("OPTIONS");
      continue;
      paramHttpURLConnection.setRequestMethod("TRACE");
      continue;
      paramHttpURLConnection.setRequestMethod("PATCH");
      zzb(paramHttpURLConnection, paramzzk);
    }
  }
  
  private static void zzb(HttpURLConnection paramHttpURLConnection, zzk<?> paramzzk)
    throws IOException, zza
  {
    byte[] arrayOfByte = paramzzk.zzq();
    if (arrayOfByte != null)
    {
      paramHttpURLConnection.setDoOutput(true);
      paramHttpURLConnection.addRequestProperty("Content-Type", paramzzk.zzp());
      DataOutputStream localDataOutputStream = new DataOutputStream(paramHttpURLConnection.getOutputStream());
      localDataOutputStream.write(arrayOfByte);
      localDataOutputStream.close();
    }
  }
  
  protected HttpURLConnection zza(URL paramURL)
    throws IOException
  {
    return (HttpURLConnection)paramURL.openConnection();
  }
  
  public HttpResponse zza(zzk<?> paramzzk, Map<String, String> paramMap)
    throws IOException, zza
  {
    String str1 = paramzzk.getUrl();
    HashMap localHashMap = new HashMap();
    localHashMap.putAll(paramzzk.getHeaders());
    localHashMap.putAll(paramMap);
    String str2;
    if (this.zzaE != null)
    {
      str2 = this.zzaE.zzh(str1);
      if (str2 == null) {
        throw new IOException("URL blocked by rewriter: " + str1);
      }
    }
    else
    {
      str2 = str1;
    }
    HttpURLConnection localHttpURLConnection = zza(new URL(str2), paramzzk);
    Iterator localIterator1 = localHashMap.keySet().iterator();
    while (localIterator1.hasNext())
    {
      String str3 = (String)localIterator1.next();
      localHttpURLConnection.addRequestProperty(str3, (String)localHashMap.get(str3));
    }
    zza(localHttpURLConnection, paramzzk);
    ProtocolVersion localProtocolVersion = new ProtocolVersion("HTTP", 1, 1);
    if (localHttpURLConnection.getResponseCode() == -1) {
      throw new IOException("Could not retrieve response code from HttpUrlConnection.");
    }
    BasicHttpResponse localBasicHttpResponse = new BasicHttpResponse(new BasicStatusLine(localProtocolVersion, localHttpURLConnection.getResponseCode(), localHttpURLConnection.getResponseMessage()));
    localBasicHttpResponse.setEntity(zza(localHttpURLConnection));
    Iterator localIterator2 = localHttpURLConnection.getHeaderFields().entrySet().iterator();
    while (localIterator2.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator2.next();
      if (localEntry.getKey() != null) {
        localBasicHttpResponse.addHeader(new BasicHeader((String)localEntry.getKey(), (String)((List)localEntry.getValue()).get(0)));
      }
    }
    return localBasicHttpResponse;
  }
  
  public static abstract interface zza
  {
    public abstract String zzh(String paramString);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzz.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */