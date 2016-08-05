package com.google.android.gms.internal;

import android.content.Context;
import android.os.Handler;
import com.google.android.gms.ads.internal.request.AdRequestInfoParcel;
import com.google.android.gms.ads.internal.request.AdResponseParcel;
import com.google.android.gms.ads.internal.util.client.zza;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Future;

@zzgr
public class zzhn
  extends zzhz
  implements zzhm
{
  private final Context mContext;
  private final zzhs.zza zzDe;
  private final String zzGY;
  private final ArrayList<Future> zzHp = new ArrayList();
  private final ArrayList<String> zzHq = new ArrayList();
  private final HashSet<String> zzHr = new HashSet();
  private final zzhg zzHs;
  private final Object zzpd = new Object();
  
  public zzhn(Context paramContext, String paramString, zzhs.zza paramzza, zzhg paramzzhg)
  {
    this.mContext = paramContext;
    this.zzGY = paramString;
    this.zzDe = paramzza;
    this.zzHs = paramzzhg;
  }
  
  private void zzk(String paramString1, String paramString2)
  {
    synchronized (this.zzpd)
    {
      zzhh localzzhh = this.zzHs.zzau(paramString1);
      if ((localzzhh == null) || (localzzhh.zzgd() == null) || (localzzhh.zzgc() != null))
      {
        zzhi localzzhi = new zzhi(this.mContext, paramString1, this.zzGY, paramString2, this.zzDe, localzzhh, this);
        this.zzHp.add(localzzhi.zzgz());
        this.zzHq.add(paramString1);
      }
    }
  }
  
  public void onStop() {}
  
  public void zzav(String paramString)
  {
    synchronized (this.zzpd)
    {
      this.zzHr.add(paramString);
      return;
    }
  }
  
  public void zzb(String paramString, int paramInt) {}
  
  public void zzbn()
  {
    Iterator localIterator1 = this.zzDe.zzHx.zzyW.iterator();
    while (localIterator1.hasNext())
    {
      zzed localzzed = (zzed)localIterator1.next();
      String str2 = localzzed.zzyT;
      Iterator localIterator2 = localzzed.zzyO.iterator();
      while (localIterator2.hasNext()) {
        zzk((String)localIterator2.next(), str2);
      }
    }
    for (int i = 0;; i++)
    {
      if (i < this.zzHp.size()) {}
      try
      {
        ((Future)this.zzHp.get(i)).get();
        synchronized (this.zzpd)
        {
          if (this.zzHr.contains(this.zzHq.get(i)))
          {
            String str1 = (String)this.zzHq.get(i);
            final zzhs localzzhs2 = new zzhs(this.zzDe.zzHC.zzEn, null, this.zzDe.zzHD.zzyY, -2, this.zzDe.zzHD.zzyZ, this.zzDe.zzHD.zzEM, this.zzDe.zzHD.orientation, this.zzDe.zzHD.zzzc, this.zzDe.zzHC.zzEq, this.zzDe.zzHD.zzEK, (zzed)this.zzDe.zzHx.zzyW.get(i), null, str1, this.zzDe.zzHx, null, this.zzDe.zzHD.zzEL, this.zzDe.zzqn, this.zzDe.zzHD.zzEJ, this.zzDe.zzHz, this.zzDe.zzHD.zzEO, this.zzDe.zzHD.zzEP, this.zzDe.zzHw, null);
            zza.zzJt.post(new Runnable()
            {
              public void run()
              {
                zzhn.zza(zzhn.this).zzb(localzzhs2);
              }
            });
          }
        }
        final zzhs localzzhs1;
        return;
      }
      catch (InterruptedException localInterruptedException)
      {
        localzzhs1 = new zzhs(this.zzDe.zzHC.zzEn, null, this.zzDe.zzHD.zzyY, 3, this.zzDe.zzHD.zzyZ, this.zzDe.zzHD.zzEM, this.zzDe.zzHD.orientation, this.zzDe.zzHD.zzzc, this.zzDe.zzHC.zzEq, this.zzDe.zzHD.zzEK, null, null, null, this.zzDe.zzHx, null, this.zzDe.zzHD.zzEL, this.zzDe.zzqn, this.zzDe.zzHD.zzEJ, this.zzDe.zzHz, this.zzDe.zzHD.zzEO, this.zzDe.zzHD.zzEP, this.zzDe.zzHw, null);
        zza.zzJt.post(new Runnable()
        {
          public void run()
          {
            zzhn.zza(zzhn.this).zzb(localzzhs1);
          }
        });
      }
      catch (Exception localException) {}
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzhn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */