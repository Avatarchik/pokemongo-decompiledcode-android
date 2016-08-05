package com.google.android.gms.internal;

import java.util.HashMap;
import java.util.Map;

public class zzcf
{
  private final zzcg zzoo;
  private final Map<String, zzce> zzvQ;
  
  public zzcf(zzcg paramzzcg)
  {
    this.zzoo = paramzzcg;
    this.zzvQ = new HashMap();
  }
  
  public void zza(String paramString, zzce paramzzce)
  {
    this.zzvQ.put(paramString, paramzzce);
  }
  
  public void zza(String paramString1, String paramString2, long paramLong)
  {
    zzcg localzzcg = this.zzoo;
    zzce localzzce = (zzce)this.zzvQ.get(paramString2);
    String[] arrayOfString = new String[1];
    arrayOfString[0] = paramString1;
    zzcc.zza(localzzcg, localzzce, paramLong, arrayOfString);
    this.zzvQ.put(paramString1, zzcc.zza(this.zzoo, paramLong));
  }
  
  public zzcg zzdm()
  {
    return this.zzoo;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzcf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */