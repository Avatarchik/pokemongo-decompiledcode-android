package com.google.android.gms.ads.internal.purchase;

import android.content.Intent;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzp;
import com.google.android.gms.internal.zzgr;
import com.google.android.gms.internal.zzid;

@zzgr
public class zzk
{
  private final String zztG;
  
  public zzk(String paramString)
  {
    this.zztG = paramString;
  }
  
  public boolean zza(String paramString, int paramInt, Intent paramIntent)
  {
    boolean bool = false;
    if ((paramString == null) || (paramIntent == null)) {}
    for (;;)
    {
      return bool;
      String str1 = zzp.zzbF().zze(paramIntent);
      String str2 = zzp.zzbF().zzf(paramIntent);
      if ((str1 != null) && (str2 != null)) {
        if (!paramString.equals(zzp.zzbF().zzao(str1))) {
          zzb.zzaH("Developer payload not match.");
        } else if ((this.zztG != null) && (!zzl.zzc(this.zztG, str1, str2))) {
          zzb.zzaH("Fail to verify signature.");
        } else {
          bool = true;
        }
      }
    }
  }
  
  public String zzfq()
  {
    return zzp.zzbv().zzgD();
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/ads/internal/purchase/zzk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */