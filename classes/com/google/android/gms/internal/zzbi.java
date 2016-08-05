package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.util.client.zzb;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@zzgr
public class zzbi
{
  private final Object zzpd = new Object();
  private int zzrX;
  private List<zzbh> zzrY = new LinkedList();
  
  public boolean zza(zzbh paramzzbh)
  {
    boolean bool;
    synchronized (this.zzpd)
    {
      if (this.zzrY.contains(paramzzbh)) {
        bool = true;
      } else {
        bool = false;
      }
    }
    return bool;
  }
  
  public boolean zzb(zzbh paramzzbh)
  {
    boolean bool;
    synchronized (this.zzpd)
    {
      Iterator localIterator = this.zzrY.iterator();
      while (localIterator.hasNext())
      {
        zzbh localzzbh = (zzbh)localIterator.next();
        if ((paramzzbh != localzzbh) && (localzzbh.zzcm().equals(paramzzbh.zzcm())))
        {
          localIterator.remove();
          bool = true;
          break label89;
        }
      }
      bool = false;
    }
    label89:
    return bool;
  }
  
  public void zzc(zzbh paramzzbh)
  {
    synchronized (this.zzpd)
    {
      if (this.zzrY.size() >= 10)
      {
        zzb.zzaF("Queue is full, current size = " + this.zzrY.size());
        this.zzrY.remove(0);
      }
      int i = this.zzrX;
      this.zzrX = (i + 1);
      paramzzbh.zzg(i);
      this.zzrY.add(paramzzbh);
      return;
    }
  }
  
  public zzbh zzcs()
  {
    Object localObject4;
    for (Object localObject1 = null;; localObject1 = localObject4)
    {
      int k;
      zzbh localzzbh1;
      label155:
      synchronized (this.zzpd)
      {
        if (this.zzrY.size() == 0)
        {
          zzb.zzaF("Queue empty");
        }
        else if (this.zzrY.size() >= 2)
        {
          i = Integer.MIN_VALUE;
          Iterator localIterator = this.zzrY.iterator();
          if (localIterator.hasNext())
          {
            zzbh localzzbh2 = (zzbh)localIterator.next();
            int j = localzzbh2.getScore();
            if (j <= i) {
              break label155;
            }
            localObject4 = localzzbh2;
            k = j;
            break label167;
          }
          this.zzrY.remove(localObject1);
        }
      }
      return (zzbh)localObject1;
      label167:
      int i = k;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzbi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */