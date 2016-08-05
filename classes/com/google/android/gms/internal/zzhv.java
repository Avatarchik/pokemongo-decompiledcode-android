package com.google.android.gms.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.os.Bundle;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.util.client.zzb;

@zzgr
public class zzhv
{
  final String zzHP;
  long zzId = -1L;
  long zzIe = -1L;
  int zzIf = -1;
  int zzIg = 0;
  int zzIh = 0;
  private final Object zzpd = new Object();
  
  public zzhv(String paramString)
  {
    this.zzHP = paramString;
  }
  
  public static boolean zzF(Context paramContext)
  {
    boolean bool = false;
    int i = paramContext.getResources().getIdentifier("Theme.Translucent", "style", "android");
    if (i == 0) {
      zzb.zzaG("Please set theme of AdActivity to @android:style/Theme.Translucent to enable transparent background interstitial ad.");
    }
    for (;;)
    {
      return bool;
      ComponentName localComponentName = new ComponentName(paramContext.getPackageName(), "com.google.android.gms.ads.AdActivity");
      try
      {
        if (i == paramContext.getPackageManager().getActivityInfo(localComponentName, 0).theme) {
          bool = true;
        } else {
          zzb.zzaG("Please set theme of AdActivity to @android:style/Theme.Translucent to enable transparent background interstitial ad.");
        }
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        zzb.zzaH("Fail to fetch AdActivity theme");
        zzb.zzaG("Please set theme of AdActivity to @android:style/Theme.Translucent to enable transparent background interstitial ad.");
      }
    }
  }
  
  public void zzb(AdRequestParcel paramAdRequestParcel, long paramLong)
  {
    synchronized (this.zzpd)
    {
      if (this.zzIe == -1L)
      {
        this.zzIe = paramLong;
        this.zzId = this.zzIe;
        if ((paramAdRequestParcel.extras != null) && (paramAdRequestParcel.extras.getInt("gw", 2) == 1)) {
          return;
        }
      }
      else
      {
        this.zzId = paramLong;
      }
    }
  }
  
  public Bundle zze(Context paramContext, String paramString)
  {
    synchronized (this.zzpd)
    {
      Bundle localBundle = new Bundle();
      localBundle.putString("session_id", this.zzHP);
      localBundle.putLong("basets", this.zzIe);
      localBundle.putLong("currts", this.zzId);
      localBundle.putString("seq_num", paramString);
      localBundle.putInt("preqs", this.zzIf);
      localBundle.putInt("pclick", this.zzIg);
      localBundle.putInt("pimp", this.zzIh);
      localBundle.putBoolean("support_transparent_background", zzF(paramContext));
      return localBundle;
    }
  }
  
  public void zzgf()
  {
    synchronized (this.zzpd)
    {
      this.zzIh = (1 + this.zzIh);
      return;
    }
  }
  
  public void zzgg()
  {
    synchronized (this.zzpd)
    {
      this.zzIg = (1 + this.zzIg);
      return;
    }
  }
  
  public long zzgx()
  {
    return this.zzIe;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzhv.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */