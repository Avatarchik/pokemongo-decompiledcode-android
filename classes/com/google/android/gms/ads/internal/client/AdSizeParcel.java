package com.google.android.gms.ads.internal.client;

import android.content.Context;
import android.content.res.Resources;
import android.os.Parcel;
import android.util.DisplayMetrics;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.zzgr;

@zzgr
public class AdSizeParcel
  implements SafeParcelable
{
  public static final zzi CREATOR = new zzi();
  public final int height;
  public final int heightPixels;
  public final int versionCode;
  public final int width;
  public final int widthPixels;
  public final String zzte;
  public final boolean zztf;
  public final AdSizeParcel[] zztg;
  public final boolean zzth;
  public final boolean zzti;
  
  public AdSizeParcel()
  {
    this(4, "interstitial_mb", 0, 0, true, 0, 0, null, false, false);
  }
  
  AdSizeParcel(int paramInt1, String paramString, int paramInt2, int paramInt3, boolean paramBoolean1, int paramInt4, int paramInt5, AdSizeParcel[] paramArrayOfAdSizeParcel, boolean paramBoolean2, boolean paramBoolean3)
  {
    this.versionCode = paramInt1;
    this.zzte = paramString;
    this.height = paramInt2;
    this.heightPixels = paramInt3;
    this.zztf = paramBoolean1;
    this.width = paramInt4;
    this.widthPixels = paramInt5;
    this.zztg = paramArrayOfAdSizeParcel;
    this.zzth = paramBoolean2;
    this.zzti = paramBoolean3;
  }
  
  public AdSizeParcel(Context paramContext, AdSize paramAdSize)
  {
    this(paramContext, arrayOfAdSize);
  }
  
  public AdSizeParcel(Context paramContext, AdSize[] paramArrayOfAdSize)
  {
    AdSize localAdSize = paramArrayOfAdSize[0];
    this.versionCode = 4;
    this.zztf = false;
    this.zzti = localAdSize.isFluid();
    int i;
    label65:
    int j;
    label77:
    DisplayMetrics localDisplayMetrics;
    label128:
    int m;
    label168:
    int n;
    if (this.zzti)
    {
      this.width = AdSize.BANNER.getWidth();
      this.height = AdSize.BANNER.getHeight();
      if (this.width != -1) {
        break label307;
      }
      i = 1;
      if (this.height != -2) {
        break label313;
      }
      j = 1;
      localDisplayMetrics = paramContext.getResources().getDisplayMetrics();
      if (i == 0) {
        break label331;
      }
      if ((!zzl.zzcF().zzS(paramContext)) || (!zzl.zzcF().zzT(paramContext))) {
        break label319;
      }
      this.widthPixels = (zza(localDisplayMetrics) - zzl.zzcF().zzU(paramContext));
      double d = this.widthPixels / localDisplayMetrics.density;
      int i2 = (int)d;
      if (d - (int)d >= 0.01D) {
        i2++;
      }
      m = i2;
      if (j == 0) {
        break label360;
      }
      n = zzc(localDisplayMetrics);
      label180:
      this.heightPixels = zzl.zzcF().zza(localDisplayMetrics, n);
      if ((i == 0) && (j == 0)) {
        break label369;
      }
      this.zzte = (m + "x" + n + "_as");
    }
    for (;;)
    {
      if (paramArrayOfAdSize.length <= 1) {
        break label396;
      }
      this.zztg = new AdSizeParcel[paramArrayOfAdSize.length];
      for (int i1 = 0; i1 < paramArrayOfAdSize.length; i1++) {
        this.zztg[i1] = new AdSizeParcel(paramContext, paramArrayOfAdSize[i1]);
      }
      this.width = localAdSize.getWidth();
      this.height = localAdSize.getHeight();
      break;
      label307:
      i = 0;
      break label65;
      label313:
      j = 0;
      break label77;
      label319:
      this.widthPixels = zza(localDisplayMetrics);
      break label128;
      label331:
      int k = this.width;
      this.widthPixels = zzl.zzcF().zza(localDisplayMetrics, this.width);
      m = k;
      break label168;
      label360:
      n = this.height;
      break label180;
      label369:
      if (this.zzti) {
        this.zzte = "320x50_mb";
      } else {
        this.zzte = localAdSize.toString();
      }
    }
    label396:
    this.zztg = null;
    this.zzth = false;
  }
  
  public AdSizeParcel(AdSizeParcel paramAdSizeParcel, AdSizeParcel[] paramArrayOfAdSizeParcel)
  {
    this(4, paramAdSizeParcel.zzte, paramAdSizeParcel.height, paramAdSizeParcel.heightPixels, paramAdSizeParcel.zztf, paramAdSizeParcel.width, paramAdSizeParcel.widthPixels, paramArrayOfAdSizeParcel, paramAdSizeParcel.zzth, paramAdSizeParcel.zzti);
  }
  
  public static int zza(DisplayMetrics paramDisplayMetrics)
  {
    return paramDisplayMetrics.widthPixels;
  }
  
  public static int zzb(DisplayMetrics paramDisplayMetrics)
  {
    return (int)(zzc(paramDisplayMetrics) * paramDisplayMetrics.density);
  }
  
  private static int zzc(DisplayMetrics paramDisplayMetrics)
  {
    int i = (int)(paramDisplayMetrics.heightPixels / paramDisplayMetrics.density);
    int j;
    if (i <= 400) {
      j = 32;
    }
    for (;;)
    {
      return j;
      if (i <= 720) {
        j = 50;
      } else {
        j = 90;
      }
    }
  }
  
  public static AdSizeParcel zzcC()
  {
    return new AdSizeParcel(4, "reward_mb", 0, 0, false, 0, 0, null, false, false);
  }
  
  public static AdSizeParcel zzs(Context paramContext)
  {
    return new AdSizeParcel(4, "320x50_mb", 0, 0, false, 0, 0, null, true, false);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzi.zza(this, paramParcel, paramInt);
  }
  
  public AdSize zzcD()
  {
    return com.google.android.gms.ads.zza.zza(this.width, this.height, this.zzte);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/ads/internal/client/AdSizeParcel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */