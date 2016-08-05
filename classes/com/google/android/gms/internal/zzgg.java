package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.request.AdResponseParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zza;
import com.google.android.gms.ads.internal.zzn;

@zzgr
public class zzgg
{
  public zzgh zza(Context paramContext, zza paramzza, zzhs.zza paramzza1, zzan paramzzan, zziz paramzziz, zzem paramzzem, zza paramzza2, zzcg paramzzcg)
  {
    AdResponseParcel localAdResponseParcel = paramzza1.zzHD;
    Object localObject;
    if (localAdResponseParcel.zzEK) {
      localObject = new zzgk(paramContext, paramzza1, paramzzem, paramzza2, paramzzcg);
    }
    for (;;)
    {
      zzb.zzaF("AdRenderer: " + localObject.getClass().getName());
      ((zzgh)localObject).zzfu();
      return (zzgh)localObject;
      if (localAdResponseParcel.zzth)
      {
        if ((paramzza instanceof zzn))
        {
          localObject = new zzgl(paramContext, (zzn)paramzza, new zzbc(), paramzza1, paramzzan, paramzza2);
        }
        else
        {
          StringBuilder localStringBuilder = new StringBuilder().append("Invalid NativeAdManager type. Found: ");
          if (paramzza != null) {}
          for (String str = paramzza.getClass().getName();; str = "null") {
            throw new IllegalArgumentException(str + "; Required: NativeAdManager.");
          }
        }
      }
      else if (localAdResponseParcel.zzEQ) {
        localObject = new zzge(paramContext, paramzza1, paramzziz, paramzza2);
      } else if ((((Boolean)zzby.zzvb.get()).booleanValue()) && (zzmx.zzqB()) && (!zzmx.isAtLeastL()) && (paramzziz.zzaN().zztf)) {
        localObject = new zzgj(paramContext, paramzza1, paramzziz, paramzza2);
      } else {
        localObject = new zzgi(paramContext, paramzza1, paramzziz, paramzza2);
      }
    }
  }
  
  public static abstract interface zza
  {
    public abstract void zzb(zzhs paramzzhs);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzgg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */