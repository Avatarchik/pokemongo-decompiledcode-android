package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zze;
import java.util.HashMap;
import java.util.Map;

@zzgr
public class zzdq
  implements zzdk
{
  static final Map<String, Integer> zzxS = new HashMap();
  private final zze zzxQ;
  private final zzfc zzxR;
  
  static
  {
    zzxS.put("resize", Integer.valueOf(1));
    zzxS.put("playVideo", Integer.valueOf(2));
    zzxS.put("storePicture", Integer.valueOf(3));
    zzxS.put("createCalendarEvent", Integer.valueOf(4));
    zzxS.put("setOrientationProperties", Integer.valueOf(5));
    zzxS.put("closeResizedAd", Integer.valueOf(6));
  }
  
  public zzdq(zze paramzze, zzfc paramzzfc)
  {
    this.zzxQ = paramzze;
    this.zzxR = paramzzfc;
  }
  
  public void zza(zziz paramzziz, Map<String, String> paramMap)
  {
    String str = (String)paramMap.get("a");
    int i = ((Integer)zzxS.get(str)).intValue();
    if ((i != 5) && (this.zzxQ != null) && (!this.zzxQ.zzbe())) {
      this.zzxQ.zzp(null);
    }
    for (;;)
    {
      return;
      switch (i)
      {
      case 2: 
      default: 
        zzb.zzaG("Unknown MRAID command called.");
        break;
      case 1: 
        this.zzxR.zzg(paramMap);
        break;
      case 4: 
        new zzfb(paramzziz, paramMap).execute();
        break;
      case 3: 
        new zzfe(paramzziz, paramMap).execute();
        break;
      case 5: 
        new zzfd(paramzziz, paramMap).execute();
        break;
      case 6: 
        this.zzxR.zzn(true);
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzdq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */