package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.zzp;
import java.util.WeakHashMap;

@zzgr
public final class zzgz
{
  private WeakHashMap<Context, zza> zzGO = new WeakHashMap();
  
  public zzgy zzC(Context paramContext)
  {
    zza localzza = (zza)this.zzGO.get(paramContext);
    if ((localzza != null) && (!localzza.hasExpired()) && (((Boolean)zzby.zzvm.get()).booleanValue())) {}
    for (zzgy localzzgy = new zzgy.zza(paramContext, localzza.zzGQ).zzfX();; localzzgy = new zzgy.zza(paramContext).zzfX())
    {
      this.zzGO.put(paramContext, new zza(localzzgy));
      return localzzgy;
    }
  }
  
  private class zza
  {
    public final long zzGP = zzp.zzbz().currentTimeMillis();
    public final zzgy zzGQ;
    
    public zza(zzgy paramzzgy)
    {
      this.zzGQ = paramzzgy;
    }
    
    public boolean hasExpired()
    {
      if (this.zzGP + ((Long)zzby.zzvn.get()).longValue() < zzp.zzbz().currentTimeMillis()) {}
      for (boolean bool = true;; bool = false) {
        return bool;
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzgz.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */