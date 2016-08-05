package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.util.client.zzb;
import java.util.ArrayList;
import java.util.Iterator;

@zzgr
public class zzbh
{
  private final Object zzpd = new Object();
  private final int zzrN;
  private final int zzrO;
  private final int zzrP;
  private final zzbm zzrQ;
  private ArrayList<String> zzrR = new ArrayList();
  private int zzrS = 0;
  private int zzrT = 0;
  private int zzrU = 0;
  private int zzrV;
  private String zzrW = "";
  
  public zzbh(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.zzrN = paramInt1;
    this.zzrO = paramInt2;
    this.zzrP = paramInt3;
    this.zzrQ = new zzbm(paramInt4);
  }
  
  private String zza(ArrayList<String> paramArrayList, int paramInt)
  {
    String str;
    if (paramArrayList.isEmpty()) {
      str = "";
    }
    for (;;)
    {
      return str;
      StringBuffer localStringBuffer = new StringBuffer();
      Iterator localIterator = paramArrayList.iterator();
      do
      {
        if (!localIterator.hasNext()) {
          break;
        }
        localStringBuffer.append((String)localIterator.next());
        localStringBuffer.append(' ');
      } while (localStringBuffer.length() <= paramInt);
      localStringBuffer.deleteCharAt(-1 + localStringBuffer.length());
      str = localStringBuffer.toString();
      if (str.length() >= paramInt) {
        str = str.substring(0, paramInt);
      }
    }
  }
  
  private void zzx(String paramString)
  {
    if ((paramString == null) || (paramString.length() < this.zzrP)) {}
    for (;;)
    {
      return;
      synchronized (this.zzpd)
      {
        this.zzrR.add(paramString);
        this.zzrS += paramString.length();
      }
    }
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool = false;
    if (!(paramObject instanceof zzbh)) {}
    for (;;)
    {
      return bool;
      if (paramObject == this)
      {
        bool = true;
      }
      else
      {
        zzbh localzzbh = (zzbh)paramObject;
        if ((localzzbh.zzcm() != null) && (localzzbh.zzcm().equals(zzcm()))) {
          bool = true;
        }
      }
    }
  }
  
  public int getScore()
  {
    return this.zzrV;
  }
  
  public int hashCode()
  {
    return zzcm().hashCode();
  }
  
  public String toString()
  {
    return "ActivityContent fetchId: " + this.zzrT + " score:" + this.zzrV + " total_length:" + this.zzrS + "\n text: " + zza(this.zzrR, 200) + "\n signture: " + this.zzrW;
  }
  
  int zza(int paramInt1, int paramInt2)
  {
    return paramInt1 * this.zzrN + paramInt2 * this.zzrO;
  }
  
  public boolean zzcl()
  {
    for (;;)
    {
      synchronized (this.zzpd)
      {
        if (this.zzrU == 0)
        {
          bool = true;
          return bool;
        }
      }
      boolean bool = false;
    }
  }
  
  public String zzcm()
  {
    return this.zzrW;
  }
  
  public void zzcn()
  {
    synchronized (this.zzpd)
    {
      this.zzrV = (-100 + this.zzrV);
      return;
    }
  }
  
  public void zzco()
  {
    synchronized (this.zzpd)
    {
      this.zzrU = (-1 + this.zzrU);
      return;
    }
  }
  
  public void zzcp()
  {
    synchronized (this.zzpd)
    {
      this.zzrU = (1 + this.zzrU);
      return;
    }
  }
  
  public void zzcq()
  {
    synchronized (this.zzpd)
    {
      int i = zza(this.zzrS, this.zzrT);
      if (i > this.zzrV)
      {
        this.zzrV = i;
        this.zzrW = this.zzrQ.zza(this.zzrR);
      }
      return;
    }
  }
  
  int zzcr()
  {
    return this.zzrS;
  }
  
  public void zzg(int paramInt)
  {
    this.zzrT = paramInt;
  }
  
  public void zzv(String paramString)
  {
    zzx(paramString);
    synchronized (this.zzpd)
    {
      if (this.zzrU < 0) {
        zzb.zzaF("ActivityContent: negative number of WebViews.");
      }
      zzcq();
      return;
    }
  }
  
  public void zzw(String paramString)
  {
    zzx(paramString);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzbh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */