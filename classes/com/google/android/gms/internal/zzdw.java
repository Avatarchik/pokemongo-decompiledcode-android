package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzd;
import com.google.android.gms.ads.internal.zzp;
import java.util.Map;

@zzgr
public class zzdw
  implements zzdk
{
  public void zza(zziz paramzziz, Map<String, String> paramMap)
  {
    zzdu localzzdu = zzp.zzbI();
    if (paramMap.containsKey("abort")) {
      if (!localzzdu.zza(paramzziz)) {
        com.google.android.gms.ads.internal.util.client.zzb.zzaH("Precache abort but no preload task running.");
      }
    }
    String str1;
    label130:
    for (;;)
    {
      return;
      str1 = (String)paramMap.get("src");
      if (str1 == null)
      {
        com.google.android.gms.ads.internal.util.client.zzb.zzaH("Precache video action is missing the src parameter.");
      }
      else
      {
        try
        {
          int j = Integer.parseInt((String)paramMap.get("player"));
          i = j;
        }
        catch (NumberFormatException localNumberFormatException)
        {
          int i;
          String str2;
          for (;;)
          {
            i = 0;
            continue;
            str2 = "";
          }
          com.google.android.gms.common.internal.zzb.zzs(paramzziz.zzhb());
          new zzdt(paramzziz, paramzziz.zzhb().zzoG.zza(paramzziz, i, str2), str1).zzgz();
        }
        if (paramMap.containsKey("mimetype"))
        {
          str2 = (String)paramMap.get("mimetype");
          if (!localzzdu.zzb(paramzziz)) {
            break label130;
          }
          com.google.android.gms.ads.internal.util.client.zzb.zzaH("Precache task already running.");
        }
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzdw.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */