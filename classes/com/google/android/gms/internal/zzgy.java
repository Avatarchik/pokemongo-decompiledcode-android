package com.google.android.gms.internal;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.DetailedState;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import com.google.android.gms.ads.internal.client.zzl;
import com.google.android.gms.ads.internal.util.client.zza;
import com.google.android.gms.ads.internal.zzp;
import com.google.android.gms.common.GooglePlayServicesUtil;
import java.util.Locale;

@zzgr
public final class zzgy
{
  public final int zzEx;
  public final int zzEy;
  public final float zzEz;
  public final boolean zzGA;
  public final String zzGB;
  public final String zzGC;
  public final int zzGD;
  public final int zzGE;
  public final int zzGF;
  public final int zzGG;
  public final int zzGH;
  public final int zzGI;
  public final double zzGJ;
  public final boolean zzGK;
  public final boolean zzGL;
  public final int zzGM;
  public final String zzGN;
  public final int zzGs;
  public final boolean zzGt;
  public final boolean zzGu;
  public final String zzGv;
  public final String zzGw;
  public final boolean zzGx;
  public final boolean zzGy;
  public final boolean zzGz;
  
  zzgy(int paramInt1, boolean paramBoolean1, boolean paramBoolean2, String paramString1, String paramString2, boolean paramBoolean3, boolean paramBoolean4, boolean paramBoolean5, boolean paramBoolean6, String paramString3, String paramString4, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, float paramFloat, int paramInt8, int paramInt9, double paramDouble, boolean paramBoolean7, boolean paramBoolean8, int paramInt10, String paramString5)
  {
    this.zzGs = paramInt1;
    this.zzGt = paramBoolean1;
    this.zzGu = paramBoolean2;
    this.zzGv = paramString1;
    this.zzGw = paramString2;
    this.zzGx = paramBoolean3;
    this.zzGy = paramBoolean4;
    this.zzGz = paramBoolean5;
    this.zzGA = paramBoolean6;
    this.zzGB = paramString3;
    this.zzGC = paramString4;
    this.zzGD = paramInt2;
    this.zzGE = paramInt3;
    this.zzGF = paramInt4;
    this.zzGG = paramInt5;
    this.zzGH = paramInt6;
    this.zzGI = paramInt7;
    this.zzEz = paramFloat;
    this.zzEx = paramInt8;
    this.zzEy = paramInt9;
    this.zzGJ = paramDouble;
    this.zzGK = paramBoolean7;
    this.zzGL = paramBoolean8;
    this.zzGM = paramInt10;
    this.zzGN = paramString5;
  }
  
  public static final class zza
  {
    private int zzEx;
    private int zzEy;
    private float zzEz;
    private boolean zzGA;
    private String zzGB;
    private String zzGC;
    private int zzGD;
    private int zzGE;
    private int zzGF;
    private int zzGG;
    private int zzGH;
    private int zzGI;
    private double zzGJ;
    private boolean zzGK;
    private boolean zzGL;
    private int zzGM;
    private String zzGN;
    private int zzGs;
    private boolean zzGt;
    private boolean zzGu;
    private String zzGv;
    private String zzGw;
    private boolean zzGx;
    private boolean zzGy;
    private boolean zzGz;
    
    public zza(Context paramContext)
    {
      PackageManager localPackageManager = paramContext.getPackageManager();
      zzz(paramContext);
      zza(paramContext, localPackageManager);
      zzA(paramContext);
      Locale localLocale = Locale.getDefault();
      boolean bool2;
      label59:
      Resources localResources;
      if (zza(localPackageManager, "geo:0,0?q=donuts") != null)
      {
        bool2 = bool1;
        this.zzGt = bool2;
        if (zza(localPackageManager, "http://www.google.com") == null) {
          break label126;
        }
        this.zzGu = bool1;
        this.zzGw = localLocale.getCountry();
        this.zzGx = zzl.zzcF().zzgS();
        this.zzGy = GooglePlayServicesUtil.zzag(paramContext);
        this.zzGB = localLocale.getLanguage();
        this.zzGC = zza(localPackageManager);
        localResources = paramContext.getResources();
        if (localResources != null) {
          break label131;
        }
      }
      for (;;)
      {
        return;
        bool2 = false;
        break;
        label126:
        bool1 = false;
        break label59;
        label131:
        DisplayMetrics localDisplayMetrics = localResources.getDisplayMetrics();
        if (localDisplayMetrics != null)
        {
          this.zzEz = localDisplayMetrics.density;
          this.zzEx = localDisplayMetrics.widthPixels;
          this.zzEy = localDisplayMetrics.heightPixels;
        }
      }
    }
    
    public zza(Context paramContext, zzgy paramzzgy)
    {
      PackageManager localPackageManager = paramContext.getPackageManager();
      zzz(paramContext);
      zza(paramContext, localPackageManager);
      zzA(paramContext);
      zzB(paramContext);
      this.zzGt = paramzzgy.zzGt;
      this.zzGu = paramzzgy.zzGu;
      this.zzGw = paramzzgy.zzGw;
      this.zzGx = paramzzgy.zzGx;
      this.zzGy = paramzzgy.zzGy;
      this.zzGB = paramzzgy.zzGB;
      this.zzGC = paramzzgy.zzGC;
      this.zzEz = paramzzgy.zzEz;
      this.zzEx = paramzzgy.zzEx;
      this.zzEy = paramzzgy.zzEy;
    }
    
    private void zzA(Context paramContext)
    {
      boolean bool = false;
      Intent localIntent = paramContext.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
      if (localIntent != null)
      {
        int i = localIntent.getIntExtra("status", -1);
        int j = localIntent.getIntExtra("level", -1);
        int k = localIntent.getIntExtra("scale", -1);
        this.zzGJ = (j / k);
        if ((i == 2) || (i == 5)) {
          bool = true;
        }
      }
      for (this.zzGK = bool;; this.zzGK = false)
      {
        return;
        this.zzGJ = -1.0D;
      }
    }
    
    private void zzB(Context paramContext)
    {
      this.zzGN = Build.FINGERPRINT;
    }
    
    private static ResolveInfo zza(PackageManager paramPackageManager, String paramString)
    {
      return paramPackageManager.resolveActivity(new Intent("android.intent.action.VIEW", Uri.parse(paramString)), 65536);
    }
    
    private static String zza(PackageManager paramPackageManager)
    {
      Object localObject = null;
      ResolveInfo localResolveInfo = zza(paramPackageManager, "market://details?id=com.google.android.gms.ads");
      if (localResolveInfo == null) {}
      for (;;)
      {
        return (String)localObject;
        ActivityInfo localActivityInfo = localResolveInfo.activityInfo;
        if (localActivityInfo != null) {
          try
          {
            PackageInfo localPackageInfo = paramPackageManager.getPackageInfo(localActivityInfo.packageName, 0);
            if (localPackageInfo != null)
            {
              String str = localPackageInfo.versionCode + "." + localActivityInfo.packageName;
              localObject = str;
            }
          }
          catch (PackageManager.NameNotFoundException localNameNotFoundException) {}
        }
      }
    }
    
    private void zza(Context paramContext, PackageManager paramPackageManager)
    {
      TelephonyManager localTelephonyManager = (TelephonyManager)paramContext.getSystemService("phone");
      ConnectivityManager localConnectivityManager = (ConnectivityManager)paramContext.getSystemService("connectivity");
      this.zzGv = localTelephonyManager.getNetworkOperator();
      this.zzGF = localTelephonyManager.getNetworkType();
      this.zzGG = localTelephonyManager.getPhoneType();
      this.zzGE = -2;
      this.zzGL = false;
      this.zzGM = -1;
      if (zzp.zzbv().zza(paramPackageManager, paramContext.getPackageName(), "android.permission.ACCESS_NETWORK_STATE"))
      {
        NetworkInfo localNetworkInfo = localConnectivityManager.getActiveNetworkInfo();
        if (localNetworkInfo == null) {
          break label131;
        }
        this.zzGE = localNetworkInfo.getType();
        this.zzGM = localNetworkInfo.getDetailedState().ordinal();
      }
      for (;;)
      {
        if (Build.VERSION.SDK_INT >= 16) {
          this.zzGL = localConnectivityManager.isActiveNetworkMetered();
        }
        return;
        label131:
        this.zzGE = -1;
      }
    }
    
    private void zzz(Context paramContext)
    {
      AudioManager localAudioManager = (AudioManager)paramContext.getSystemService("audio");
      this.zzGs = localAudioManager.getMode();
      this.zzGz = localAudioManager.isMusicActive();
      this.zzGA = localAudioManager.isSpeakerphoneOn();
      this.zzGD = localAudioManager.getStreamVolume(3);
      this.zzGH = localAudioManager.getRingerMode();
      this.zzGI = localAudioManager.getStreamVolume(2);
    }
    
    public zzgy zzfX()
    {
      return new zzgy(this.zzGs, this.zzGt, this.zzGu, this.zzGv, this.zzGw, this.zzGx, this.zzGy, this.zzGz, this.zzGA, this.zzGB, this.zzGC, this.zzGD, this.zzGE, this.zzGF, this.zzGG, this.zzGH, this.zzGI, this.zzEz, this.zzEx, this.zzEy, this.zzGJ, this.zzGK, this.zzGL, this.zzGM, this.zzGN);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzgy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */