package com.google.android.gms.internal;

import android.util.Base64OutputStream;
import com.google.android.gms.ads.internal.util.client.zzb;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Locale;
import java.util.PriorityQueue;

public class zzbm
{
  private final int zzsp;
  private final int zzsq;
  private final int zzsr;
  private final zzbl zzss = new zzbo();
  
  public zzbm(int paramInt)
  {
    this.zzsq = paramInt;
    this.zzsp = 6;
    this.zzsr = 0;
  }
  
  private String zzA(String paramString)
  {
    String[] arrayOfString = paramString.split("\n");
    if (arrayOfString.length == 0) {}
    zza localzza;
    for (String str = "";; str = localzza.toString())
    {
      return str;
      localzza = zzcz();
      Arrays.sort(arrayOfString, new Comparator()
      {
        public int compare(String paramAnonymousString1, String paramAnonymousString2)
        {
          return paramAnonymousString2.length() - paramAnonymousString1.length();
        }
      });
      int i = 0;
      if ((i < arrayOfString.length) && (i < this.zzsq))
      {
        if (arrayOfString[i].trim().length() == 0) {}
        for (;;)
        {
          i++;
          break;
          try
          {
            localzza.write(this.zzss.zzz(arrayOfString[i]));
          }
          catch (IOException localIOException)
          {
            zzb.zzb("Error while writing hash to byteStream", localIOException);
          }
        }
      }
    }
  }
  
  String zzB(String paramString)
  {
    String[] arrayOfString1 = paramString.split("\n");
    if (arrayOfString1.length == 0) {}
    zza localzza;
    for (String str = "";; str = localzza.toString())
    {
      return str;
      localzza = zzcz();
      PriorityQueue localPriorityQueue = new PriorityQueue(this.zzsq, new Comparator()
      {
        public int zza(zzbp.zza paramAnonymouszza1, zzbp.zza paramAnonymouszza2)
        {
          return (int)(paramAnonymouszza1.value - paramAnonymouszza2.value);
        }
      });
      int i = 0;
      if (i < arrayOfString1.length)
      {
        String[] arrayOfString2 = zzbn.zzD(arrayOfString1[i]);
        if (arrayOfString2.length < this.zzsp) {}
        for (;;)
        {
          i++;
          break;
          zzbp.zza(arrayOfString2, this.zzsq, this.zzsp, localPriorityQueue);
        }
      }
      Iterator localIterator = localPriorityQueue.iterator();
      for (;;)
      {
        if (localIterator.hasNext())
        {
          zzbp.zza localzza1 = (zzbp.zza)localIterator.next();
          try
          {
            localzza.write(this.zzss.zzz(localzza1.zzsx));
          }
          catch (IOException localIOException)
          {
            zzb.zzb("Error while writing hash to byteStream", localIOException);
          }
        }
      }
    }
  }
  
  public String zza(ArrayList<String> paramArrayList)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    Iterator localIterator = paramArrayList.iterator();
    while (localIterator.hasNext())
    {
      localStringBuffer.append(((String)localIterator.next()).toLowerCase(Locale.US));
      localStringBuffer.append('\n');
    }
    String str;
    switch (this.zzsr)
    {
    default: 
      str = "";
    }
    for (;;)
    {
      return str;
      str = zzB(localStringBuffer.toString());
      continue;
      str = zzA(localStringBuffer.toString());
    }
  }
  
  zza zzcz()
  {
    return new zza();
  }
  
  static class zza
  {
    ByteArrayOutputStream zzsu = new ByteArrayOutputStream(4096);
    Base64OutputStream zzsv = new Base64OutputStream(this.zzsu, 10);
    
    public String toString()
    {
      try
      {
        this.zzsv.close();
        try
        {
          this.zzsu.close();
          String str2 = this.zzsu.toString();
          str1 = str2;
        }
        catch (IOException localIOException2)
        {
          for (;;)
          {
            zzb.zzb("HashManager: Unable to convert to Base64.", localIOException2);
            String str1 = "";
            this.zzsu = null;
            this.zzsv = null;
          }
        }
        finally
        {
          this.zzsu = null;
          this.zzsv = null;
        }
        return str1;
      }
      catch (IOException localIOException1)
      {
        for (;;)
        {
          zzb.zzb("HashManager: Unable to convert to Base64.", localIOException1);
        }
      }
    }
    
    public void write(byte[] paramArrayOfByte)
      throws IOException
    {
      this.zzsv.write(paramArrayOfByte);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzbm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */