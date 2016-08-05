package com.google.android.gms.ads.internal.purchase;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.SystemClock;
import com.google.android.gms.ads.internal.zzp;
import com.google.android.gms.internal.zzfw;
import com.google.android.gms.internal.zzgr;
import com.google.android.gms.internal.zzhz;
import com.google.android.gms.internal.zzid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@zzgr
public class zzc
  extends zzhz
  implements ServiceConnection
{
  private Context mContext;
  private boolean zzCB = false;
  private zzfw zzCC;
  private zzb zzCD;
  private zzh zzCE;
  private List<zzf> zzCF = null;
  private zzk zzCG;
  private final Object zzpd = new Object();
  
  public zzc(Context paramContext, zzfw paramzzfw, zzk paramzzk)
  {
    this(paramContext, paramzzfw, paramzzk, new zzb(paramContext), zzh.zzw(paramContext.getApplicationContext()));
  }
  
  zzc(Context paramContext, zzfw paramzzfw, zzk paramzzk, zzb paramzzb, zzh paramzzh)
  {
    this.mContext = paramContext;
    this.zzCC = paramzzfw;
    this.zzCG = paramzzk;
    this.zzCD = paramzzb;
    this.zzCE = paramzzh;
    this.zzCF = this.zzCE.zzg(10L);
  }
  
  private void zze(long paramLong)
  {
    do
    {
      if (!zzf(paramLong)) {
        com.google.android.gms.ads.internal.util.client.zzb.v("Timeout waiting for pending transaction to be processed.");
      }
    } while (!this.zzCB);
  }
  
  private boolean zzf(long paramLong)
  {
    long l = 60000L - (SystemClock.elapsedRealtime() - paramLong);
    boolean bool;
    if (l <= 0L) {
      bool = false;
    }
    for (;;)
    {
      return bool;
      try
      {
        this.zzpd.wait(l);
        bool = true;
      }
      catch (InterruptedException localInterruptedException)
      {
        for (;;)
        {
          com.google.android.gms.ads.internal.util.client.zzb.zzaH("waitWithTimeout_lock interrupted");
        }
      }
    }
  }
  
  public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
  {
    synchronized (this.zzpd)
    {
      this.zzCD.zzN(paramIBinder);
      zzfm();
      this.zzCB = true;
      this.zzpd.notify();
      return;
    }
  }
  
  public void onServiceDisconnected(ComponentName paramComponentName)
  {
    com.google.android.gms.ads.internal.util.client.zzb.zzaG("In-app billing service disconnected.");
    this.zzCD.destroy();
  }
  
  public void onStop()
  {
    synchronized (this.zzpd)
    {
      com.google.android.gms.common.stats.zzb.zzqh().zza(this.mContext, this);
      this.zzCD.destroy();
      return;
    }
  }
  
  protected void zza(final zzf paramzzf, String paramString1, String paramString2)
  {
    final Intent localIntent = new Intent();
    zzp.zzbF();
    localIntent.putExtra("RESPONSE_CODE", 0);
    zzp.zzbF();
    localIntent.putExtra("INAPP_PURCHASE_DATA", paramString1);
    zzp.zzbF();
    localIntent.putExtra("INAPP_DATA_SIGNATURE", paramString2);
    zzid.zzIE.post(new Runnable()
    {
      public void run()
      {
        try
        {
          if (zzc.zza(zzc.this).zza(paramzzf.zzCR, -1, localIntent)) {
            zzc.zzc(zzc.this).zza(new zzg(zzc.zzb(zzc.this), paramzzf.zzCS, true, -1, localIntent, paramzzf));
          } else {
            zzc.zzc(zzc.this).zza(new zzg(zzc.zzb(zzc.this), paramzzf.zzCS, false, -1, localIntent, paramzzf));
          }
        }
        catch (RemoteException localRemoteException)
        {
          com.google.android.gms.ads.internal.util.client.zzb.zzaH("Fail to verify and dispatch pending transaction");
        }
      }
    });
  }
  
  public void zzbn()
  {
    synchronized (this.zzpd)
    {
      Intent localIntent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
      localIntent.setPackage("com.android.vending");
      com.google.android.gms.common.stats.zzb.zzqh().zza(this.mContext, localIntent, this, 1);
      zze(SystemClock.elapsedRealtime());
      com.google.android.gms.common.stats.zzb.zzqh().zza(this.mContext, this);
      this.zzCD.destroy();
      return;
    }
  }
  
  protected void zzfm()
  {
    if (this.zzCF.isEmpty()) {
      return;
    }
    HashMap localHashMap = new HashMap();
    Iterator localIterator1 = this.zzCF.iterator();
    while (localIterator1.hasNext())
    {
      zzf localzzf2 = (zzf)localIterator1.next();
      localHashMap.put(localzzf2.zzCS, localzzf2);
    }
    String str1;
    for (Object localObject = null;; localObject = str1)
    {
      Bundle localBundle = this.zzCD.zzj(this.mContext.getPackageName(), (String)localObject);
      if (localBundle == null) {}
      do
      {
        do
        {
          Iterator localIterator2 = localHashMap.keySet().iterator();
          while (localIterator2.hasNext())
          {
            String str2 = (String)localIterator2.next();
            this.zzCE.zza((zzf)localHashMap.get(str2));
          }
          break;
        } while (zzp.zzbF().zzc(localBundle) != 0);
        ArrayList localArrayList1 = localBundle.getStringArrayList("INAPP_PURCHASE_ITEM_LIST");
        ArrayList localArrayList2 = localBundle.getStringArrayList("INAPP_PURCHASE_DATA_LIST");
        ArrayList localArrayList3 = localBundle.getStringArrayList("INAPP_DATA_SIGNATURE_LIST");
        str1 = localBundle.getString("INAPP_CONTINUATION_TOKEN");
        for (int i = 0; i < localArrayList1.size(); i++) {
          if (localHashMap.containsKey(localArrayList1.get(i)))
          {
            String str3 = (String)localArrayList1.get(i);
            String str4 = (String)localArrayList2.get(i);
            String str5 = (String)localArrayList3.get(i);
            zzf localzzf1 = (zzf)localHashMap.get(str3);
            String str6 = zzp.zzbF().zzao(str4);
            if (localzzf1.zzCR.equals(str6))
            {
              zza(localzzf1, str4, str5);
              localHashMap.remove(str3);
            }
          }
        }
      } while ((str1 == null) || (localHashMap.isEmpty()));
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/ads/internal/purchase/zzc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */