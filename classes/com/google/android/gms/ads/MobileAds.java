package com.google.android.gms.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.client.zzab;
import com.google.android.gms.ads.internal.client.zzac;
import com.google.android.gms.ads.reward.RewardedVideoAd;

public class MobileAds
{
  public static RewardedVideoAd getRewardedVideoAdInstance(Context paramContext)
  {
    return zzab.zzcV().getRewardedVideoAdInstance(paramContext);
  }
  
  public static void initialize(Context paramContext)
  {
    zzab.zzcV().initialize(paramContext);
  }
  
  @Deprecated
  public static void initialize(Context paramContext, String paramString)
  {
    initialize(paramContext, paramString, null);
  }
  
  @Deprecated
  public static void initialize(Context paramContext, String paramString, Settings paramSettings)
  {
    zzab localzzab = zzab.zzcV();
    if (paramSettings == null) {}
    for (zzac localzzac = null;; localzzac = paramSettings.zzaG())
    {
      localzzab.zza(paramContext, paramString, localzzac);
      return;
    }
  }
  
  public static final class Settings
  {
    private final zzac zznV = new zzac();
    
    @Deprecated
    public String getTrackingId()
    {
      return this.zznV.getTrackingId();
    }
    
    @Deprecated
    public boolean isGoogleAnalyticsEnabled()
    {
      return this.zznV.isGoogleAnalyticsEnabled();
    }
    
    @Deprecated
    public Settings setGoogleAnalyticsEnabled(boolean paramBoolean)
    {
      this.zznV.zzk(paramBoolean);
      return this;
    }
    
    @Deprecated
    public Settings setTrackingId(String paramString)
    {
      this.zznV.zzO(paramString);
      return this;
    }
    
    zzac zzaG()
    {
      return this.zznV;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/ads/MobileAds.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */