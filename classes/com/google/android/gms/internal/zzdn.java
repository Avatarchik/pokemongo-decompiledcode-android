package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.util.client.zzb;
import java.util.Map;

@zzgr
public class zzdn
  implements zzdk
{
  private final zzdo zzxO;
  
  public zzdn(zzdo paramzzdo)
  {
    this.zzxO = paramzzdo;
  }
  
  public void zza(zziz paramzziz, Map<String, String> paramMap)
  {
    boolean bool1 = "1".equals(paramMap.get("transparentBackground"));
    boolean bool2 = "1".equals(paramMap.get("blur"));
    try
    {
      if (paramMap.get("blurRadius") == null) {
        break label93;
      }
      float f2 = Float.parseFloat((String)paramMap.get("blurRadius"));
      f1 = f2;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      for (;;)
      {
        zzb.zzb("Fail to parse float", localNumberFormatException);
        float f1 = 0.0F;
      }
    }
    this.zzxO.zzd(bool1);
    this.zzxO.zza(bool2, f1);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzdn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */