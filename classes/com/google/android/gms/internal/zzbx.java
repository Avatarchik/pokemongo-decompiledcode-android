package com.google.android.gms.internal;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.android.gms.common.GooglePlayServicesUtil;

@zzgr
public class zzbx
{
  private boolean zzpA = false;
  private final Object zzpd = new Object();
  private SharedPreferences zzuj = null;
  
  public void initialize(Context paramContext)
  {
    Context localContext;
    synchronized (this.zzpd)
    {
      if (!this.zzpA)
      {
        localContext = GooglePlayServicesUtil.getRemoteContext(paramContext);
        if (localContext != null) {}
      }
    }
  }
  
  public <T> T zzd(zzbu<T> paramzzbu)
  {
    Object localObject3;
    synchronized (this.zzpd)
    {
      if (!this.zzpA) {
        localObject3 = paramzzbu.zzde();
      } else {
        localObject3 = paramzzbu.zza(this.zzuj);
      }
    }
    return (T)localObject3;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzbx.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */