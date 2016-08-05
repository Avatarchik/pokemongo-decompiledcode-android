package com.google.android.gms.ads.internal.overlay;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.TextureView;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.internal.zzbu;
import com.google.android.gms.internal.zzby;
import com.google.android.gms.internal.zzcc;
import com.google.android.gms.internal.zzce;
import com.google.android.gms.internal.zzcg;
import com.google.android.gms.internal.zzid;
import com.google.android.gms.internal.zzig;
import com.google.android.gms.internal.zzig.zza;
import com.google.android.gms.internal.zzig.zzb;
import com.google.android.gms.internal.zzmn;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class zzp
{
  private final Context mContext;
  private final String zzBY;
  private final VersionInfoParcel zzBZ;
  private final zzce zzCa;
  private final zzcg zzCb;
  private final zzig zzCc = new zzig.zzb().zza("min_1", Double.MIN_VALUE, 1.0D).zza("1_5", 1.0D, 5.0D).zza("5_10", 5.0D, 10.0D).zza("10_20", 10.0D, 20.0D).zza("20_30", 20.0D, 30.0D).zza("30_max", 30.0D, Double.MAX_VALUE).zzgK();
  private final long[] zzCd;
  private final String[] zzCe;
  private zzce zzCf;
  private zzce zzCg;
  private zzce zzCh;
  private zzce zzCi;
  private boolean zzCj;
  private zzi zzCk;
  private boolean zzCl;
  private boolean zzCm;
  private long zzCn = -1L;
  
  public zzp(Context paramContext, VersionInfoParcel paramVersionInfoParcel, String paramString, zzcg paramzzcg, zzce paramzzce)
  {
    this.mContext = paramContext;
    this.zzBZ = paramVersionInfoParcel;
    this.zzBY = paramString;
    this.zzCb = paramzzcg;
    this.zzCa = paramzzce;
    String str = (String)zzby.zzuF.get();
    if (str == null)
    {
      this.zzCe = new String[0];
      this.zzCd = new long[0];
      return;
    }
    String[] arrayOfString = TextUtils.split(str, ",");
    this.zzCe = new String[arrayOfString.length];
    this.zzCd = new long[arrayOfString.length];
    int i = 0;
    while (i < arrayOfString.length) {
      try
      {
        this.zzCd[i] = Long.parseLong(arrayOfString[i]);
        i++;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        for (;;)
        {
          zzb.zzd("Unable to parse frame hash target time number.", localNumberFormatException);
          this.zzCd[i] = -1L;
        }
      }
    }
  }
  
  private void zzc(zzi paramzzi)
  {
    long l1 = ((Long)zzby.zzuG.get()).longValue();
    long l2 = paramzzi.getCurrentPosition();
    int i = 0;
    if (i < this.zzCe.length)
    {
      if (this.zzCe[i] != null) {}
      while (l1 <= Math.abs(l2 - this.zzCd[i]))
      {
        i++;
        break;
      }
      this.zzCe[i] = zza(paramzzi);
    }
  }
  
  private void zzfd()
  {
    if ((this.zzCh != null) && (this.zzCi == null))
    {
      zzcg localzzcg1 = this.zzCb;
      zzce localzzce1 = this.zzCh;
      String[] arrayOfString1 = new String[1];
      arrayOfString1[0] = "vff";
      zzcc.zza(localzzcg1, localzzce1, arrayOfString1);
      zzcg localzzcg2 = this.zzCb;
      zzce localzzce2 = this.zzCa;
      String[] arrayOfString2 = new String[1];
      arrayOfString2[0] = "vtt";
      zzcc.zza(localzzcg2, localzzce2, arrayOfString2);
      this.zzCi = zzcc.zzb(this.zzCb);
    }
    long l = com.google.android.gms.ads.internal.zzp.zzbz().nanoTime();
    if ((this.zzCj) && (this.zzCm) && (this.zzCn != -1L))
    {
      double d = TimeUnit.SECONDS.toNanos(1L) / (l - this.zzCn);
      this.zzCc.zza(d);
    }
    this.zzCm = this.zzCj;
    this.zzCn = l;
  }
  
  public void onStop()
  {
    if ((((Boolean)zzby.zzuE.get()).booleanValue()) && (!this.zzCl))
    {
      Bundle localBundle = new Bundle();
      localBundle.putString("type", "native-player-metrics");
      localBundle.putString("request", this.zzBY);
      localBundle.putString("player", this.zzCk.zzer());
      Iterator localIterator = this.zzCc.getBuckets().iterator();
      while (localIterator.hasNext())
      {
        zzig.zza localzza = (zzig.zza)localIterator.next();
        localBundle.putString("fps_c_" + localzza.name, Integer.toString(localzza.count));
        localBundle.putString("fps_p_" + localzza.name, Double.toString(localzza.zzIV));
      }
      int i = 0;
      if (i < this.zzCd.length)
      {
        String str = this.zzCe[i];
        if (str == null) {}
        for (;;)
        {
          i++;
          break;
          localBundle.putString("fh_" + Long.valueOf(this.zzCd[i]), str);
        }
      }
      com.google.android.gms.ads.internal.zzp.zzbv().zza(this.mContext, this.zzBZ.zzJu, "gmob-apps", localBundle, true);
      this.zzCl = true;
    }
  }
  
  String zza(TextureView paramTextureView)
  {
    Bitmap localBitmap = paramTextureView.getBitmap(8, 8);
    long l1 = 0L;
    long l2 = 63L;
    int i = 0;
    while (i < 8)
    {
      long l3 = l1;
      long l4 = l2;
      int j = 0;
      if (j < 8)
      {
        int k = localBitmap.getPixel(j, i);
        if (Color.blue(k) + Color.red(k) + Color.green(k) > 128) {}
        for (long l5 = 1L;; l5 = 0L)
        {
          l3 |= l5 << (int)l4;
          int m = j + 1;
          l4 -= 1L;
          j = m;
          break;
        }
      }
      i++;
      l2 = l4;
      l1 = l3;
    }
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Long.valueOf(l1);
    return String.format("%016X", arrayOfObject);
  }
  
  public void zza(zzi paramzzi)
  {
    zzcg localzzcg = this.zzCb;
    zzce localzzce = this.zzCa;
    String[] arrayOfString = new String[1];
    arrayOfString[0] = "vpc";
    zzcc.zza(localzzcg, localzzce, arrayOfString);
    this.zzCf = zzcc.zzb(this.zzCb);
    this.zzCk = paramzzi;
  }
  
  public void zzb(zzi paramzzi)
  {
    zzfd();
    zzc(paramzzi);
  }
  
  public void zzeR()
  {
    if ((this.zzCf == null) || (this.zzCg != null)) {}
    for (;;)
    {
      return;
      zzcg localzzcg = this.zzCb;
      zzce localzzce = this.zzCf;
      String[] arrayOfString = new String[1];
      arrayOfString[0] = "vfr";
      zzcc.zza(localzzcg, localzzce, arrayOfString);
      this.zzCg = zzcc.zzb(this.zzCb);
    }
  }
  
  public void zzfe()
  {
    this.zzCj = true;
    if ((this.zzCg != null) && (this.zzCh == null))
    {
      zzcg localzzcg = this.zzCb;
      zzce localzzce = this.zzCg;
      String[] arrayOfString = new String[1];
      arrayOfString[0] = "vfp";
      zzcc.zza(localzzcg, localzzce, arrayOfString);
      this.zzCh = zzcc.zzb(this.zzCb);
    }
  }
  
  public void zzff()
  {
    this.zzCj = false;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/ads/internal/overlay/zzp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */