package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.zzd;
import com.google.android.gms.ads.internal.zzp;

@zzgr
public class zzjb
{
  public zziz zza(Context paramContext, AdSizeParcel paramAdSizeParcel, boolean paramBoolean1, boolean paramBoolean2, zzan paramzzan, VersionInfoParcel paramVersionInfoParcel)
  {
    return zza(paramContext, paramAdSizeParcel, paramBoolean1, paramBoolean2, paramzzan, paramVersionInfoParcel, null, null);
  }
  
  public zziz zza(Context paramContext, AdSizeParcel paramAdSizeParcel, boolean paramBoolean1, boolean paramBoolean2, zzan paramzzan, VersionInfoParcel paramVersionInfoParcel, zzcg paramzzcg, zzd paramzzd)
  {
    zzjc localzzjc = new zzjc(zzjd.zzb(paramContext, paramAdSizeParcel, paramBoolean1, paramBoolean2, paramzzan, paramVersionInfoParcel, paramzzcg, paramzzd));
    localzzjc.setWebViewClient(zzp.zzbx().zzb(localzzjc, paramBoolean2));
    localzzjc.setWebChromeClient(zzp.zzbx().zzf(localzzjc));
    return localzzjc;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzjb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */