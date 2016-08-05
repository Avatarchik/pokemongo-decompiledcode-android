package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.util.client.zzb;
import java.util.Map;

@zzgr
public final class zzdf
  implements zzdk
{
  private final zzdg zzxn;
  
  public zzdf(zzdg paramzzdg)
  {
    this.zzxn = paramzzdg;
  }
  
  public void zza(zziz paramzziz, Map<String, String> paramMap)
  {
    String str = (String)paramMap.get("name");
    if (str == null) {
      zzb.zzaH("App event with no name parameter.");
    }
    for (;;)
    {
      return;
      this.zzxn.onAppEvent(str, (String)paramMap.get("info"));
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzdf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */