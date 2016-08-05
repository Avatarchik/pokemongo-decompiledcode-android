package com.google.android.gms.ads.internal;

import android.content.Context;
import android.view.MotionEvent;
import com.google.android.gms.ads.internal.client.zzl;
import com.google.android.gms.ads.internal.util.client.zza;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.internal.zzaj;
import com.google.android.gms.internal.zzam;
import com.google.android.gms.internal.zzbu;
import com.google.android.gms.internal.zzby;
import com.google.android.gms.internal.zzgr;
import com.google.android.gms.internal.zzic;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

@zzgr
class zzh
  implements zzaj, Runnable
{
  private final List<Object[]> zzoQ = new Vector();
  private final AtomicReference<zzaj> zzoR = new AtomicReference();
  CountDownLatch zzoS = new CountDownLatch(1);
  private zzq zzot;
  
  public zzh(zzq paramzzq)
  {
    this.zzot = paramzzq;
    if (zzl.zzcF().zzgT()) {
      zzic.zza(this);
    }
    for (;;)
    {
      return;
      run();
    }
  }
  
  private void zzbh()
  {
    if (this.zzoQ.isEmpty()) {}
    for (;;)
    {
      return;
      Iterator localIterator = this.zzoQ.iterator();
      while (localIterator.hasNext())
      {
        Object[] arrayOfObject = (Object[])localIterator.next();
        if (arrayOfObject.length == 1) {
          ((zzaj)this.zzoR.get()).zza((MotionEvent)arrayOfObject[0]);
        } else if (arrayOfObject.length == 3) {
          ((zzaj)this.zzoR.get()).zza(((Integer)arrayOfObject[0]).intValue(), ((Integer)arrayOfObject[1]).intValue(), ((Integer)arrayOfObject[2]).intValue());
        }
      }
      this.zzoQ.clear();
    }
  }
  
  private Context zzp(Context paramContext)
  {
    if (!((Boolean)zzby.zzuw.get()).booleanValue()) {}
    for (;;)
    {
      return paramContext;
      Context localContext = paramContext.getApplicationContext();
      if (localContext != null) {
        paramContext = localContext;
      }
    }
  }
  
  /* Error */
  public void run()
  {
    // Byte code:
    //   0: getstatic 130	com/google/android/gms/internal/zzby:zzuI	Lcom/google/android/gms/internal/zzbu;
    //   3: invokevirtual 116	com/google/android/gms/internal/zzbu:get	()Ljava/lang/Object;
    //   6: checkcast 118	java/lang/Boolean
    //   9: invokevirtual 121	java/lang/Boolean:booleanValue	()Z
    //   12: ifeq +82 -> 94
    //   15: aload_0
    //   16: getfield 43	com/google/android/gms/ads/internal/zzh:zzot	Lcom/google/android/gms/ads/internal/zzq;
    //   19: getfield 136	com/google/android/gms/ads/internal/zzq:zzqj	Lcom/google/android/gms/ads/internal/util/client/VersionInfoParcel;
    //   22: getfield 142	com/google/android/gms/ads/internal/util/client/VersionInfoParcel:zzJx	Z
    //   25: ifeq +49 -> 74
    //   28: goto +66 -> 94
    //   31: aload_0
    //   32: aload_0
    //   33: aload_0
    //   34: getfield 43	com/google/android/gms/ads/internal/zzh:zzot	Lcom/google/android/gms/ads/internal/zzq;
    //   37: getfield 136	com/google/android/gms/ads/internal/zzq:zzqj	Lcom/google/android/gms/ads/internal/util/client/VersionInfoParcel;
    //   40: getfield 146	com/google/android/gms/ads/internal/util/client/VersionInfoParcel:zzJu	Ljava/lang/String;
    //   43: aload_0
    //   44: aload_0
    //   45: getfield 43	com/google/android/gms/ads/internal/zzh:zzot	Lcom/google/android/gms/ads/internal/zzq;
    //   48: getfield 150	com/google/android/gms/ads/internal/zzq:context	Landroid/content/Context;
    //   51: invokespecial 152	com/google/android/gms/ads/internal/zzh:zzp	(Landroid/content/Context;)Landroid/content/Context;
    //   54: iload_2
    //   55: invokevirtual 156	com/google/android/gms/ads/internal/zzh:zzb	(Ljava/lang/String;Landroid/content/Context;Z)Lcom/google/android/gms/internal/zzaj;
    //   58: invokevirtual 159	com/google/android/gms/ads/internal/zzh:zza	(Lcom/google/android/gms/internal/zzaj;)V
    //   61: aload_0
    //   62: getfield 41	com/google/android/gms/ads/internal/zzh:zzoS	Ljava/util/concurrent/CountDownLatch;
    //   65: invokevirtual 162	java/util/concurrent/CountDownLatch:countDown	()V
    //   68: aload_0
    //   69: aconst_null
    //   70: putfield 43	com/google/android/gms/ads/internal/zzh:zzot	Lcom/google/android/gms/ads/internal/zzq;
    //   73: return
    //   74: iconst_0
    //   75: istore_2
    //   76: goto -45 -> 31
    //   79: astore_1
    //   80: aload_0
    //   81: getfield 41	com/google/android/gms/ads/internal/zzh:zzoS	Ljava/util/concurrent/CountDownLatch;
    //   84: invokevirtual 162	java/util/concurrent/CountDownLatch:countDown	()V
    //   87: aload_0
    //   88: aconst_null
    //   89: putfield 43	com/google/android/gms/ads/internal/zzh:zzot	Lcom/google/android/gms/ads/internal/zzq;
    //   92: aload_1
    //   93: athrow
    //   94: iconst_1
    //   95: istore_2
    //   96: goto -65 -> 31
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	99	0	this	zzh
    //   79	14	1	localObject	Object
    //   54	42	2	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   0	61	79	finally
  }
  
  public void zza(int paramInt1, int paramInt2, int paramInt3)
  {
    zzaj localzzaj = (zzaj)this.zzoR.get();
    if (localzzaj != null)
    {
      zzbh();
      localzzaj.zza(paramInt1, paramInt2, paramInt3);
    }
    for (;;)
    {
      return;
      List localList = this.zzoQ;
      Object[] arrayOfObject = new Object[3];
      arrayOfObject[0] = Integer.valueOf(paramInt1);
      arrayOfObject[1] = Integer.valueOf(paramInt2);
      arrayOfObject[2] = Integer.valueOf(paramInt3);
      localList.add(arrayOfObject);
    }
  }
  
  public void zza(MotionEvent paramMotionEvent)
  {
    zzaj localzzaj = (zzaj)this.zzoR.get();
    if (localzzaj != null)
    {
      zzbh();
      localzzaj.zza(paramMotionEvent);
    }
    for (;;)
    {
      return;
      List localList = this.zzoQ;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = paramMotionEvent;
      localList.add(arrayOfObject);
    }
  }
  
  protected void zza(zzaj paramzzaj)
  {
    this.zzoR.set(paramzzaj);
  }
  
  protected zzaj zzb(String paramString, Context paramContext, boolean paramBoolean)
  {
    return zzam.zza(paramString, paramContext, paramBoolean);
  }
  
  public String zzb(Context paramContext)
  {
    zzaj localzzaj;
    if (zzbg())
    {
      localzzaj = (zzaj)this.zzoR.get();
      if (localzzaj != null) {
        zzbh();
      }
    }
    for (String str = localzzaj.zzb(zzp(paramContext));; str = "") {
      return str;
    }
  }
  
  public String zzb(Context paramContext, String paramString)
  {
    zzaj localzzaj;
    if (zzbg())
    {
      localzzaj = (zzaj)this.zzoR.get();
      if (localzzaj != null) {
        zzbh();
      }
    }
    for (String str = localzzaj.zzb(zzp(paramContext), paramString);; str = "") {
      return str;
    }
  }
  
  protected boolean zzbg()
  {
    try
    {
      this.zzoS.await();
      bool = true;
    }
    catch (InterruptedException localInterruptedException)
    {
      for (;;)
      {
        zzb.zzd("Interrupted during GADSignals creation.", localInterruptedException);
        boolean bool = false;
      }
    }
    return bool;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/ads/internal/zzh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */