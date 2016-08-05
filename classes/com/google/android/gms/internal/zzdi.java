package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzp;
import java.util.Map;

@zzgr
public final class zzdi
  implements zzdk
{
  private void zzb(zziz paramzziz, Map<String, String> paramMap)
  {
    String str1 = (String)paramMap.get("label");
    String str2 = (String)paramMap.get("start_label");
    String str3 = (String)paramMap.get("timestamp");
    if (TextUtils.isEmpty(str1)) {
      zzb.zzaH("No label given for CSI tick.");
    }
    for (;;)
    {
      return;
      if (TextUtils.isEmpty(str3)) {
        zzb.zzaH("No timestamp given for CSI tick.");
      } else {
        try
        {
          long l = zzc(Long.parseLong(str3));
          if (TextUtils.isEmpty(str2)) {
            str2 = "native:view_load";
          }
          paramzziz.zzhn().zza(str1, str2, l);
        }
        catch (NumberFormatException localNumberFormatException)
        {
          zzb.zzd("Malformed timestamp for CSI tick.", localNumberFormatException);
        }
      }
    }
  }
  
  private long zzc(long paramLong)
  {
    long l = zzp.zzbz().currentTimeMillis();
    return zzp.zzbz().elapsedRealtime() + (paramLong - l);
  }
  
  private void zzc(zziz paramzziz, Map<String, String> paramMap)
  {
    String str = (String)paramMap.get("value");
    if (TextUtils.isEmpty(str)) {
      zzb.zzaH("No value given for CSI experiment.");
    }
    for (;;)
    {
      return;
      zzcg localzzcg = paramzziz.zzhn().zzdm();
      if (localzzcg == null) {
        zzb.zzaH("No ticker for WebView, dropping experiment ID.");
      } else {
        localzzcg.zze("e", str);
      }
    }
  }
  
  private void zzd(zziz paramzziz, Map<String, String> paramMap)
  {
    String str1 = (String)paramMap.get("name");
    String str2 = (String)paramMap.get("value");
    if (TextUtils.isEmpty(str2)) {
      zzb.zzaH("No value given for CSI extra.");
    }
    for (;;)
    {
      return;
      if (TextUtils.isEmpty(str1))
      {
        zzb.zzaH("No name given for CSI extra.");
      }
      else
      {
        zzcg localzzcg = paramzziz.zzhn().zzdm();
        if (localzzcg == null) {
          zzb.zzaH("No ticker for WebView, dropping extra parameter.");
        } else {
          localzzcg.zze(str1, str2);
        }
      }
    }
  }
  
  public void zza(zziz paramzziz, Map<String, String> paramMap)
  {
    String str = (String)paramMap.get("action");
    if ("tick".equals(str)) {
      zzb(paramzziz, paramMap);
    }
    for (;;)
    {
      return;
      if ("experiment".equals(str)) {
        zzc(paramzziz, paramMap);
      } else if ("extra".equals(str)) {
        zzd(paramzziz, paramMap);
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzdi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */