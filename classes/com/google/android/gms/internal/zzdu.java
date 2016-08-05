package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzp;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@zzgr
public class zzdu
  implements Iterable<zzdt>
{
  private final List<zzdt> zzyb = new LinkedList();
  
  private zzdt zzc(zziz paramzziz)
  {
    Iterator localIterator = zzp.zzbI().iterator();
    zzdt localzzdt;
    do
    {
      if (!localIterator.hasNext()) {
        break;
      }
      localzzdt = (zzdt)localIterator.next();
    } while (localzzdt.zzoM != paramzziz);
    for (;;)
    {
      return localzzdt;
      localzzdt = null;
    }
  }
  
  public Iterator<zzdt> iterator()
  {
    return this.zzyb.iterator();
  }
  
  public void zza(zzdt paramzzdt)
  {
    this.zzyb.add(paramzzdt);
  }
  
  public boolean zza(zziz paramzziz)
  {
    zzdt localzzdt = zzc(paramzziz);
    if (localzzdt != null) {
      localzzdt.zzxY.abort();
    }
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public void zzb(zzdt paramzzdt)
  {
    this.zzyb.remove(paramzzdt);
  }
  
  public boolean zzb(zziz paramzziz)
  {
    if (zzc(paramzziz) != null) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzdu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */