package com.google.android.gms.internal;

import android.content.Context;
import android.os.Handler;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.request.AdRequestInfoParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@zzgr
public class zzek
  implements zzec
{
  private final Context mContext;
  private final zzcg zzoo;
  private final zzem zzox;
  private final Object zzpd = new Object();
  private final zzee zzzA;
  private final long zzzB;
  private final long zzzC;
  private boolean zzzD = false;
  private zzeh zzzE;
  private final boolean zzzn;
  private final AdRequestInfoParcel zzzz;
  
  public zzek(Context paramContext, AdRequestInfoParcel paramAdRequestInfoParcel, zzem paramzzem, zzee paramzzee, boolean paramBoolean, long paramLong1, long paramLong2, zzcg paramzzcg)
  {
    this.mContext = paramContext;
    this.zzzz = paramAdRequestInfoParcel;
    this.zzox = paramzzem;
    this.zzzA = paramzzee;
    this.zzzn = paramBoolean;
    this.zzzB = paramLong1;
    this.zzzC = paramLong2;
    this.zzoo = paramzzcg;
  }
  
  public void cancel()
  {
    synchronized (this.zzpd)
    {
      this.zzzD = true;
      if (this.zzzE != null) {
        this.zzzE.cancel();
      }
      return;
    }
  }
  
  public zzei zzc(List<zzed> paramList)
  {
    zzb.zzaF("Starting mediation.");
    ArrayList localArrayList = new ArrayList();
    zzce localzzce1 = this.zzoo.zzdn();
    Iterator localIterator1 = paramList.iterator();
    while (localIterator1.hasNext())
    {
      zzed localzzed = (zzed)localIterator1.next();
      zzb.zzaG("Trying mediation network: " + localzzed.zzyN);
      Iterator localIterator2 = localzzed.zzyO.iterator();
      while (localIterator2.hasNext())
      {
        String str = (String)localIterator2.next();
        zzce localzzce2 = this.zzoo.zzdn();
        synchronized (this.zzpd)
        {
          if (this.zzzD)
          {
            localzzei = new zzei(-1);
          }
          else
          {
            this.zzzE = new zzeh(this.mContext, str, this.zzox, this.zzzA, localzzed, this.zzzz.zzEn, this.zzzz.zzqn, this.zzzz.zzqj, this.zzzn, this.zzzz.zzqB, this.zzzz.zzqD);
            localzzei = this.zzzE.zza(this.zzzB, this.zzzC);
            if (localzzei.zzzt == 0)
            {
              zzb.zzaF("Adapter succeeded.");
              this.zzoo.zze("mediation_network_succeed", str);
              if (!localArrayList.isEmpty()) {
                this.zzoo.zze("mediation_networks_fail", TextUtils.join(",", localArrayList));
              }
              zzcg localzzcg2 = this.zzoo;
              String[] arrayOfString2 = new String[1];
              arrayOfString2[0] = "mls";
              localzzcg2.zza(localzzce2, arrayOfString2);
              zzcg localzzcg3 = this.zzoo;
              String[] arrayOfString3 = new String[1];
              arrayOfString3[0] = "ttm";
              localzzcg3.zza(localzzce1, arrayOfString3);
            }
          }
        }
        localArrayList.add(str);
        zzcg localzzcg1 = this.zzoo;
        String[] arrayOfString1 = new String[1];
        arrayOfString1[0] = "mlf";
        localzzcg1.zza(localzzce2, arrayOfString1);
        if (localzzei.zzzv != null) {
          zzid.zzIE.post(new Runnable()
          {
            public void run()
            {
              try
              {
                localzzei.zzzv.destroy();
                return;
              }
              catch (RemoteException localRemoteException)
              {
                for (;;)
                {
                  zzb.zzd("Could not destroy mediation adapter.", localRemoteException);
                }
              }
            }
          });
        }
      }
    }
    if (!localArrayList.isEmpty()) {
      this.zzoo.zze("mediation_networks_fail", TextUtils.join(",", localArrayList));
    }
    final zzei localzzei = new zzei(1);
    return localzzei;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzek.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */