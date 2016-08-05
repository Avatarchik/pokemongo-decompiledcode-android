package com.google.android.gms.ads.mediation.customevent;

import com.google.ads.mediation.NetworkExtras;
import java.util.HashMap;

@Deprecated
public final class CustomEventExtras
  implements NetworkExtras
{
  private final HashMap<String, Object> zzKQ = new HashMap();
  
  public Object getExtra(String paramString)
  {
    return this.zzKQ.get(paramString);
  }
  
  public void setExtra(String paramString, Object paramObject)
  {
    this.zzKQ.put(paramString, paramObject);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/ads/mediation/customevent/CustomEventExtras.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */