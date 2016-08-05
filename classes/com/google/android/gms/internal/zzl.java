package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Looper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class zzl
{
  private AtomicInteger zzY = new AtomicInteger();
  private final Map<String, Queue<zzk<?>>> zzZ = new HashMap();
  private final Set<zzk<?>> zzaa = new HashSet();
  private final PriorityBlockingQueue<zzk<?>> zzab = new PriorityBlockingQueue();
  private final PriorityBlockingQueue<zzk<?>> zzac = new PriorityBlockingQueue();
  private zzg[] zzad;
  private zzc zzae;
  private List<zza> zzaf = new ArrayList();
  private final zzb zzj;
  private final zzn zzk;
  private final zzf zzz;
  
  public zzl(zzb paramzzb, zzf paramzzf)
  {
    this(paramzzb, paramzzf, 4);
  }
  
  public zzl(zzb paramzzb, zzf paramzzf, int paramInt)
  {
    this(paramzzb, paramzzf, paramInt, new zze(new Handler(Looper.getMainLooper())));
  }
  
  public zzl(zzb paramzzb, zzf paramzzf, int paramInt, zzn paramzzn)
  {
    this.zzj = paramzzb;
    this.zzz = paramzzf;
    this.zzad = new zzg[paramInt];
    this.zzk = paramzzn;
  }
  
  public int getSequenceNumber()
  {
    return this.zzY.incrementAndGet();
  }
  
  public void start()
  {
    stop();
    this.zzae = new zzc(this.zzab, this.zzac, this.zzj, this.zzk);
    this.zzae.start();
    for (int i = 0; i < this.zzad.length; i++)
    {
      zzg localzzg = new zzg(this.zzac, this.zzz, this.zzj, this.zzk);
      this.zzad[i] = localzzg;
      localzzg.start();
    }
  }
  
  public void stop()
  {
    if (this.zzae != null) {
      this.zzae.quit();
    }
    for (int i = 0; i < this.zzad.length; i++) {
      if (this.zzad[i] != null) {
        this.zzad[i].quit();
      }
    }
  }
  
  public <T> zzk<T> zze(zzk<T> paramzzk)
  {
    paramzzk.zza(this);
    for (;;)
    {
      synchronized (this.zzaa)
      {
        this.zzaa.add(paramzzk);
        paramzzk.zza(getSequenceNumber());
        paramzzk.zzc("add-to-queue");
        if (!paramzzk.zzr())
        {
          this.zzac.add(paramzzk);
          return paramzzk;
        }
      }
      String str;
      synchronized (this.zzZ)
      {
        str = paramzzk.zzh();
        if (this.zzZ.containsKey(str))
        {
          Object localObject3 = (Queue)this.zzZ.get(str);
          if (localObject3 == null) {
            localObject3 = new LinkedList();
          }
          ((Queue)localObject3).add(paramzzk);
          this.zzZ.put(str, localObject3);
          if (zzs.DEBUG)
          {
            Object[] arrayOfObject = new Object[1];
            arrayOfObject[0] = str;
            zzs.zza("Request for cacheKey=%s is in flight, putting on hold.", arrayOfObject);
          }
        }
      }
    }
  }
  
  <T> void zzf(zzk<T> paramzzk)
  {
    synchronized (this.zzaa)
    {
      this.zzaa.remove(paramzzk);
    }
    String str;
    Queue localQueue;
    Object[] arrayOfObject;
    synchronized (this.zzaf)
    {
      Iterator localIterator = this.zzaf.iterator();
      if (localIterator.hasNext()) {
        ((zza)localIterator.next()).zzg(paramzzk);
      }
    }
  }
  
  public static abstract interface zza<T>
  {
    public abstract void zzg(zzk<T> paramzzk);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */