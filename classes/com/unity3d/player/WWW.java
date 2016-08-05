package com.unity3d.player;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class WWW
  extends Thread
{
  private int a;
  private int b;
  private String c;
  private byte[] d;
  private Map e;
  
  WWW(int paramInt, String paramString, byte[] paramArrayOfByte, Map paramMap)
  {
    this.b = paramInt;
    this.c = paramString;
    this.d = paramArrayOfByte;
    this.e = paramMap;
    this.a = 0;
  }
  
  private static native void doneCallback(int paramInt);
  
  private static native void errorCallback(int paramInt, String paramString);
  
  private static native boolean headerCallback(int paramInt, String paramString);
  
  private static native void progressCallback(int paramInt1, float paramFloat1, float paramFloat2, double paramDouble, int paramInt2);
  
  private static native boolean readCallback(int paramInt1, byte[] paramArrayOfByte, int paramInt2);
  
  protected boolean headerCallback(String paramString1, String paramString2)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString1);
    localStringBuilder.append(": ");
    localStringBuilder.append(paramString2);
    localStringBuilder.append("\n\r");
    return headerCallback(this.b, localStringBuilder.toString());
  }
  
  protected boolean headerCallback(Map paramMap)
  {
    if ((paramMap == null) || (paramMap.size() == 0)) {}
    StringBuilder localStringBuilder;
    for (boolean bool = false;; bool = headerCallback(this.b, localStringBuilder.toString()))
    {
      return bool;
      localStringBuilder = new StringBuilder();
      Iterator localIterator1 = paramMap.entrySet().iterator();
      while (localIterator1.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator1.next();
        Iterator localIterator2 = ((List)localEntry.getValue()).iterator();
        while (localIterator2.hasNext())
        {
          String str2 = (String)localIterator2.next();
          localStringBuilder.append((String)localEntry.getKey());
          localStringBuilder.append(": ");
          localStringBuilder.append(str2);
          localStringBuilder.append("\r\n");
        }
        if (localEntry.getKey() == null)
        {
          Iterator localIterator3 = ((List)localEntry.getValue()).iterator();
          while (localIterator3.hasNext())
          {
            String str1 = (String)localIterator3.next();
            localStringBuilder.append("Status: ");
            localStringBuilder.append(str1);
            localStringBuilder.append("\r\n");
          }
        }
      }
    }
  }
  
  protected void progressCallback(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2)
  {
    float f1;
    float f2;
    double d3;
    if (paramInt4 > 0)
    {
      f1 = paramInt3 / paramInt4;
      f2 = 1.0F;
      int i = Math.max(paramInt4 - paramInt3, 0);
      double d2 = 1000.0D * paramInt3 / Math.max(paramLong1 - paramLong2, 0.1D);
      d3 = i / d2;
      if ((Double.isInfinite(d3)) || (Double.isNaN(d3))) {
        d3 = 0.0D;
      }
    }
    for (double d1 = d3;; d1 = 0.0D)
    {
      progressCallback(this.b, f2, f1, d1, paramInt4);
      do
      {
        return;
      } while (paramInt2 <= 0);
      f1 = 0.0F;
      f2 = paramInt1 / paramInt2;
    }
  }
  
  protected boolean readCallback(byte[] paramArrayOfByte, int paramInt)
  {
    return readCallback(this.b, paramArrayOfByte, paramInt);
  }
  
  public void run()
  {
    try
    {
      runSafe();
      return;
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        errorCallback(this.b, "Error: " + localThrowable.toString());
      }
    }
  }
  
  public void runSafe()
  {
    int i = 1 + this.a;
    this.a = i;
    if (i > 5) {
      errorCallback(this.b, "Too many redirects");
    }
    URL localURL;
    URLConnection localURLConnection;
    for (;;)
    {
      return;
      try
      {
        localURL = new URL(this.c);
        localURLConnection = localURL.openConnection();
        if ((!localURL.getProtocol().equalsIgnoreCase("file")) || (localURL.getHost() == null) || (localURL.getHost().length() == 0)) {
          break label145;
        }
        errorCallback(this.b, localURL.getHost() + localURL.getFile() + " is not an absolute path!");
      }
      catch (MalformedURLException localMalformedURLException)
      {
        errorCallback(this.b, localMalformedURLException.toString());
      }
      catch (IOException localIOException1)
      {
        errorCallback(this.b, localIOException1.toString());
      }
      continue;
      label145:
      if (this.e != null)
      {
        Iterator localIterator = this.e.entrySet().iterator();
        while (localIterator.hasNext())
        {
          Map.Entry localEntry = (Map.Entry)localIterator.next();
          localURLConnection.addRequestProperty((String)localEntry.getKey(), (String)localEntry.getValue());
        }
      }
      if (this.d != null)
      {
        localURLConnection.setDoOutput(true);
        try
        {
          OutputStream localOutputStream = localURLConnection.getOutputStream();
          int i3 = 0;
          while (i3 < this.d.length)
          {
            int i4 = Math.min(1428, this.d.length - i3);
            localOutputStream.write(this.d, i3, i4);
            i3 += i4;
            progressCallback(i3, this.d.length, 0, 0, 0L, 0L);
          }
        }
        catch (Exception localException2)
        {
          errorCallback(this.b, localException2.toString());
        }
      }
      else
      {
        if (!(localURLConnection instanceof HttpURLConnection)) {
          break;
        }
        HttpURLConnection localHttpURLConnection2 = (HttpURLConnection)localURLConnection;
        try
        {
          int i2 = localHttpURLConnection2.getResponseCode();
          Map localMap2 = localHttpURLConnection2.getHeaderFields();
          if ((localMap2 == null) || ((i2 != 301) && (i2 != 302))) {
            break;
          }
          List localList = (List)localMap2.get("Location");
          if ((localList == null) || (localList.isEmpty())) {
            break;
          }
          localHttpURLConnection2.disconnect();
          this.c = ((String)localList.get(0));
          run();
        }
        catch (IOException localIOException2)
        {
          errorCallback(this.b, localIOException2.toString());
        }
      }
    }
    Map localMap1 = localURLConnection.getHeaderFields();
    boolean bool = headerCallback(localMap1);
    if (((localMap1 == null) || (!localMap1.containsKey("content-length"))) && (localURLConnection.getContentLength() != -1))
    {
      if ((bool) || (headerCallback("content-length", String.valueOf(localURLConnection.getContentLength())))) {
        bool = true;
      }
    }
    else {
      label512:
      if (((localMap1 == null) || (!localMap1.containsKey("content-type"))) && (localURLConnection.getContentType() != null)) {
        if ((!bool) && (!headerCallback("content-type", localURLConnection.getContentType()))) {
          break label603;
        }
      }
    }
    label603:
    for (bool = true;; bool = false)
    {
      if (!bool) {
        break label609;
      }
      errorCallback(this.b, this.c + " aborted");
      break;
      bool = false;
      break label512;
    }
    label609:
    int j;
    label624:
    int k;
    label659:
    int m;
    if (localURLConnection.getContentLength() > 0)
    {
      j = localURLConnection.getContentLength();
      if ((!localURL.getProtocol().equalsIgnoreCase("file")) && (!localURL.getProtocol().equalsIgnoreCase("jar"))) {
        break label937;
      }
      if (j != 0) {
        break label828;
      }
      k = 32768;
      m = 0;
    }
    for (;;)
    {
      long l;
      byte[] arrayOfByte;
      try
      {
        l = System.currentTimeMillis();
        arrayOfByte = new byte[k];
        if (!(localURLConnection instanceof HttpURLConnection)) {
          break label926;
        }
        HttpURLConnection localHttpURLConnection1 = (HttpURLConnection)localURLConnection;
        InputStream localInputStream4 = localHttpURLConnection1.getErrorStream();
        str = localHttpURLConnection1.getResponseCode() + ": " + localHttpURLConnection1.getResponseMessage();
        localInputStream1 = localInputStream4;
        if (localInputStream1 != null) {
          break label916;
        }
        InputStream localInputStream3 = localURLConnection.getInputStream();
        n = 0;
        localInputStream2 = localInputStream3;
      }
      catch (Exception localException1)
      {
        errorCallback(this.b, localException1.toString());
      }
      if (i1 != -1)
      {
        if (readCallback(arrayOfByte, i1))
        {
          errorCallback(this.b, this.c + " aborted");
          break;
          break;
          j = 0;
          break label624;
          label828:
          k = Math.min(j, 32768);
          break label659;
        }
        if (n == 0)
        {
          m += i1;
          progressCallback(0, 0, m, j, System.currentTimeMillis(), l);
        }
        i1 = localInputStream2.read(arrayOfByte);
        continue;
      }
      if (n != 0) {
        errorCallback(this.b, str);
      }
      progressCallback(0, 0, m, m, 0L, 0L);
      doneCallback(this.b);
      break;
      label916:
      InputStream localInputStream2 = localInputStream1;
      int n = 1;
      break label945;
      label926:
      String str = "";
      InputStream localInputStream1 = null;
      continue;
      label937:
      k = 1428;
      break label659;
      label945:
      int i1 = 0;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/unity3d/player/WWW.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */