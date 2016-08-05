package com.google.android.gms.internal;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.util.client.zzb;

@zzgr
public class zzge
  extends zzgc
{
  private zzgd zzDt;
  
  zzge(Context paramContext, zzhs.zza paramzza, zziz paramzziz, zzgg.zza paramzza1)
  {
    super(paramContext, paramzza, paramzziz, paramzza1);
  }
  
  protected void zzfs()
  {
    AdSizeParcel localAdSizeParcel = this.zzoM.zzaN();
    DisplayMetrics localDisplayMetrics;
    int i;
    if (localAdSizeParcel.zztf)
    {
      localDisplayMetrics = this.mContext.getResources().getDisplayMetrics();
      i = localDisplayMetrics.widthPixels;
    }
    for (int j = localDisplayMetrics.heightPixels;; j = localAdSizeParcel.heightPixels)
    {
      this.zzDt = new zzgd(this, this.zzoM, i, j);
      this.zzoM.zzhe().zza(this);
      this.zzDt.zza(this.zzDf);
      return;
      i = localAdSizeParcel.widthPixels;
    }
  }
  
  protected int zzft()
  {
    int i;
    if (this.zzDt.zzfx())
    {
      zzb.zzaF("Ad-Network indicated no fill with passback URL.");
      i = 3;
    }
    for (;;)
    {
      return i;
      if (!this.zzDt.zzfy()) {
        i = 2;
      } else {
        i = -2;
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */