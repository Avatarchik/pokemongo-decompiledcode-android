package com.google.android.gms.internal;

import android.content.Context;
import android.os.Build.VERSION;
import com.google.android.gms.ads.internal.zzp;
import java.util.LinkedHashMap;
import java.util.Map;

public class zzbz
{
  private Context mContext = null;
  private String zzqV = null;
  private boolean zzvA;
  private String zzvB;
  private Map<String, String> zzvC;
  
  public zzbz(Context paramContext, String paramString)
  {
    this.mContext = paramContext;
    this.zzqV = paramString;
    this.zzvA = ((Boolean)zzby.zzuQ.get()).booleanValue();
    this.zzvB = ((String)zzby.zzuR.get());
    this.zzvC = new LinkedHashMap();
    this.zzvC.put("s", "gmob_sdk");
    this.zzvC.put("v", "3");
    this.zzvC.put("os", Build.VERSION.RELEASE);
    this.zzvC.put("sdk", Build.VERSION.SDK);
    this.zzvC.put("device", zzp.zzbv().zzgE());
    Map localMap = this.zzvC;
    if (paramContext.getApplicationContext() != null) {}
    for (String str = paramContext.getApplicationContext().getPackageName();; str = paramContext.getPackageName())
    {
      localMap.put("app", str);
      zzgy localzzgy = zzp.zzbB().zzC(this.mContext);
      this.zzvC.put("network_coarse", Integer.toString(localzzgy.zzGE));
      this.zzvC.put("network_fine", Integer.toString(localzzgy.zzGF));
      return;
    }
  }
  
  Context getContext()
  {
    return this.mContext;
  }
  
  String zzbV()
  {
    return this.zzqV;
  }
  
  boolean zzdg()
  {
    return this.zzvA;
  }
  
  String zzdh()
  {
    return this.zzvB;
  }
  
  Map<String, String> zzdi()
  {
    return this.zzvC;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzbz.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */