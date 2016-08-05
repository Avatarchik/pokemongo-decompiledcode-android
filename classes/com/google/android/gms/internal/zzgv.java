package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.util.client.zzb;
import java.util.Map;
import java.util.concurrent.Future;

@zzgr
public final class zzgv
{
  private String zzBY;
  private String zzFO;
  private zzin<zzgx> zzFP = new zzin();
  zzdz.zzd zzFQ;
  public final zzdk zzFR = new zzdk()
  {
    public void zza(zziz paramAnonymouszziz, Map<String, String> paramAnonymousMap)
    {
      zzgx localzzgx;
      synchronized (zzgv.zza(zzgv.this))
      {
        if (!zzgv.zzb(zzgv.this).isDone()) {
          if (zzgv.zzc(zzgv.this).equals(paramAnonymousMap.get("request_id"))) {}
        }
      }
    }
  };
  public final zzdk zzFS = new zzdk()
  {
    public void zza(zziz paramAnonymouszziz, Map<String, String> paramAnonymousMap)
    {
      zzgx localzzgx;
      String str1;
      String str2;
      synchronized (zzgv.zza(zzgv.this))
      {
        if (!zzgv.zzb(zzgv.this).isDone())
        {
          localzzgx = new zzgx(-2, paramAnonymousMap);
          if (!zzgv.zzc(zzgv.this).equals(localzzgx.getRequestId())) {
            zzb.zzaH(localzzgx.getRequestId() + " ==== " + zzgv.zzc(zzgv.this));
          }
        }
      }
    }
  };
  zziz zzoM;
  private final Object zzpd = new Object();
  
  public zzgv(String paramString1, String paramString2)
  {
    this.zzFO = paramString2;
    this.zzBY = paramString1;
  }
  
  public void zzb(zzdz.zzd paramzzd)
  {
    this.zzFQ = paramzzd;
  }
  
  public void zze(zziz paramzziz)
  {
    this.zzoM = paramzziz;
  }
  
  public zzdz.zzd zzfR()
  {
    return this.zzFQ;
  }
  
  public Future<zzgx> zzfS()
  {
    return this.zzFP;
  }
  
  public void zzfT()
  {
    if (this.zzoM != null)
    {
      this.zzoM.destroy();
      this.zzoM = null;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzgv.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */