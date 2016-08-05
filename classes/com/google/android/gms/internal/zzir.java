package com.google.android.gms.internal;

import android.os.Handler;
import com.google.android.gms.ads.internal.util.client.zza;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class zzir
{
  private final Object zzJI = new Object();
  private final List<Runnable> zzJJ = new ArrayList();
  private final List<Runnable> zzJK = new ArrayList();
  private boolean zzJL = false;
  
  private void zze(Runnable paramRunnable)
  {
    zzic.zza(paramRunnable);
  }
  
  private void zzf(Runnable paramRunnable)
  {
    zza.zzJt.post(paramRunnable);
  }
  
  public void zzc(Runnable paramRunnable)
  {
    synchronized (this.zzJI)
    {
      if (this.zzJL)
      {
        zze(paramRunnable);
        return;
      }
      this.zzJJ.add(paramRunnable);
    }
  }
  
  public void zzd(Runnable paramRunnable)
  {
    synchronized (this.zzJI)
    {
      if (this.zzJL)
      {
        zzf(paramRunnable);
        return;
      }
      this.zzJK.add(paramRunnable);
    }
  }
  
  public void zzgV()
  {
    Iterator localIterator2;
    synchronized (this.zzJI)
    {
      if (!this.zzJL)
      {
        Iterator localIterator1 = this.zzJJ.iterator();
        if (localIterator1.hasNext()) {
          zze((Runnable)localIterator1.next());
        }
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzir.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */