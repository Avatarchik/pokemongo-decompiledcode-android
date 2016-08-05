package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.formats.NativeAdOptionsParcel;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationNativeAdapter;
import com.google.android.gms.ads.mediation.NativeAdMapper;
import com.google.android.gms.ads.mediation.NativeAppInstallAdMapper;
import com.google.android.gms.ads.mediation.NativeContentAdMapper;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

@zzgr
public final class zzet
  extends zzen.zza
{
  private final MediationAdapter zzzJ;
  private zzeu zzzK;
  
  public zzet(MediationAdapter paramMediationAdapter)
  {
    this.zzzJ = paramMediationAdapter;
  }
  
  private Bundle zza(String paramString1, int paramInt, String paramString2)
    throws RemoteException
  {
    com.google.android.gms.ads.internal.util.client.zzb.zzaH("Server parameters: " + paramString1);
    Object localObject;
    try
    {
      localObject = new Bundle();
      if (paramString1 != null)
      {
        JSONObject localJSONObject = new JSONObject(paramString1);
        Bundle localBundle = new Bundle();
        Iterator localIterator = localJSONObject.keys();
        while (localIterator.hasNext())
        {
          String str = (String)localIterator.next();
          localBundle.putString(str, localJSONObject.getString(str));
        }
        localObject = localBundle;
      }
    }
    catch (Throwable localThrowable)
    {
      com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not get Server Parameters Bundle.", localThrowable);
      throw new RemoteException();
    }
    if ((this.zzzJ instanceof AdMobAdapter))
    {
      ((Bundle)localObject).putString("adJson", paramString2);
      ((Bundle)localObject).putInt("tagForChildDirectedTreatment", paramInt);
    }
    return (Bundle)localObject;
  }
  
  public void destroy()
    throws RemoteException
  {
    try
    {
      this.zzzJ.onDestroy();
      return;
    }
    catch (Throwable localThrowable)
    {
      com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not destroy adapter.", localThrowable);
      throw new RemoteException();
    }
  }
  
  public zzd getView()
    throws RemoteException
  {
    if (!(this.zzzJ instanceof MediationBannerAdapter))
    {
      com.google.android.gms.ads.internal.util.client.zzb.zzaH("MediationAdapter is not a MediationBannerAdapter: " + this.zzzJ.getClass().getCanonicalName());
      throw new RemoteException();
    }
    try
    {
      zzd localzzd = zze.zzy(((MediationBannerAdapter)this.zzzJ).getBannerView());
      return localzzd;
    }
    catch (Throwable localThrowable)
    {
      com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not get banner view from adapter.", localThrowable);
      throw new RemoteException();
    }
  }
  
  public boolean isInitialized()
    throws RemoteException
  {
    if (!(this.zzzJ instanceof MediationRewardedVideoAdAdapter))
    {
      com.google.android.gms.ads.internal.util.client.zzb.zzaH("MediationAdapter is not a MediationRewardedVideoAdAdapter: " + this.zzzJ.getClass().getCanonicalName());
      throw new RemoteException();
    }
    com.google.android.gms.ads.internal.util.client.zzb.zzaF("Check if adapter is initialized.");
    try
    {
      boolean bool = ((MediationRewardedVideoAdAdapter)this.zzzJ).isInitialized();
      return bool;
    }
    catch (Throwable localThrowable)
    {
      com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not check if adapter is initialized.", localThrowable);
      throw new RemoteException();
    }
  }
  
  public void pause()
    throws RemoteException
  {
    try
    {
      this.zzzJ.onPause();
      return;
    }
    catch (Throwable localThrowable)
    {
      com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not pause adapter.", localThrowable);
      throw new RemoteException();
    }
  }
  
  public void resume()
    throws RemoteException
  {
    try
    {
      this.zzzJ.onResume();
      return;
    }
    catch (Throwable localThrowable)
    {
      com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not resume adapter.", localThrowable);
      throw new RemoteException();
    }
  }
  
  public void showInterstitial()
    throws RemoteException
  {
    if (!(this.zzzJ instanceof MediationInterstitialAdapter))
    {
      com.google.android.gms.ads.internal.util.client.zzb.zzaH("MediationAdapter is not a MediationInterstitialAdapter: " + this.zzzJ.getClass().getCanonicalName());
      throw new RemoteException();
    }
    com.google.android.gms.ads.internal.util.client.zzb.zzaF("Showing interstitial from adapter.");
    try
    {
      ((MediationInterstitialAdapter)this.zzzJ).showInterstitial();
      return;
    }
    catch (Throwable localThrowable)
    {
      com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not show interstitial from adapter.", localThrowable);
      throw new RemoteException();
    }
  }
  
  public void showVideo()
    throws RemoteException
  {
    if (!(this.zzzJ instanceof MediationRewardedVideoAdAdapter))
    {
      com.google.android.gms.ads.internal.util.client.zzb.zzaH("MediationAdapter is not a MediationRewardedVideoAdAdapter: " + this.zzzJ.getClass().getCanonicalName());
      throw new RemoteException();
    }
    com.google.android.gms.ads.internal.util.client.zzb.zzaF("Show rewarded video ad from adapter.");
    try
    {
      ((MediationRewardedVideoAdAdapter)this.zzzJ).showVideo();
      return;
    }
    catch (Throwable localThrowable)
    {
      com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not show rewarded video ad from adapter.", localThrowable);
      throw new RemoteException();
    }
  }
  
  public void zza(AdRequestParcel paramAdRequestParcel, String paramString)
    throws RemoteException
  {
    if (!(this.zzzJ instanceof MediationRewardedVideoAdAdapter))
    {
      com.google.android.gms.ads.internal.util.client.zzb.zzaH("MediationAdapter is not a MediationRewardedVideoAdAdapter: " + this.zzzJ.getClass().getCanonicalName());
      throw new RemoteException();
    }
    com.google.android.gms.ads.internal.util.client.zzb.zzaF("Requesting rewarded video ad from adapter.");
    for (;;)
    {
      try
      {
        MediationRewardedVideoAdAdapter localMediationRewardedVideoAdAdapter = (MediationRewardedVideoAdAdapter)this.zzzJ;
        if (paramAdRequestParcel.zzsD == null) {
          break label209;
        }
        localHashSet = new HashSet(paramAdRequestParcel.zzsD);
        Date localDate;
        if (paramAdRequestParcel.zzsB == -1L)
        {
          localDate = null;
          zzes localzzes = new zzes(localDate, paramAdRequestParcel.zzsC, localHashSet, paramAdRequestParcel.zzsJ, paramAdRequestParcel.zzsE, paramAdRequestParcel.zzsF);
          if (paramAdRequestParcel.zzsL != null)
          {
            localBundle = paramAdRequestParcel.zzsL.getBundle(localMediationRewardedVideoAdAdapter.getClass().getName());
            localMediationRewardedVideoAdAdapter.loadAd(localzzes, zza(paramString, paramAdRequestParcel.zzsF, null), localBundle);
          }
        }
        else
        {
          localDate = new Date(paramAdRequestParcel.zzsB);
          continue;
        }
        Bundle localBundle = null;
      }
      catch (Throwable localThrowable)
      {
        com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not load rewarded video ad from adapter.", localThrowable);
        throw new RemoteException();
      }
      continue;
      label209:
      HashSet localHashSet = null;
    }
  }
  
  public void zza(zzd paramzzd, AdRequestParcel paramAdRequestParcel, String paramString1, com.google.android.gms.ads.internal.reward.mediation.client.zza paramzza, String paramString2)
    throws RemoteException
  {
    if (!(this.zzzJ instanceof MediationRewardedVideoAdAdapter))
    {
      com.google.android.gms.ads.internal.util.client.zzb.zzaH("MediationAdapter is not a MediationRewardedVideoAdAdapter: " + this.zzzJ.getClass().getCanonicalName());
      throw new RemoteException();
    }
    com.google.android.gms.ads.internal.util.client.zzb.zzaF("Initialize rewarded video adapter.");
    for (;;)
    {
      try
      {
        MediationRewardedVideoAdAdapter localMediationRewardedVideoAdAdapter = (MediationRewardedVideoAdAdapter)this.zzzJ;
        if (paramAdRequestParcel.zzsD == null) {
          break label229;
        }
        localHashSet = new HashSet(paramAdRequestParcel.zzsD);
        Date localDate;
        if (paramAdRequestParcel.zzsB == -1L)
        {
          localDate = null;
          zzes localzzes = new zzes(localDate, paramAdRequestParcel.zzsC, localHashSet, paramAdRequestParcel.zzsJ, paramAdRequestParcel.zzsE, paramAdRequestParcel.zzsF);
          if (paramAdRequestParcel.zzsL != null)
          {
            localBundle = paramAdRequestParcel.zzsL.getBundle(localMediationRewardedVideoAdAdapter.getClass().getName());
            localMediationRewardedVideoAdAdapter.initialize((Context)zze.zzp(paramzzd), localzzes, paramString1, new com.google.android.gms.ads.internal.reward.mediation.client.zzb(paramzza), zza(paramString2, paramAdRequestParcel.zzsF, null), localBundle);
          }
        }
        else
        {
          localDate = new Date(paramAdRequestParcel.zzsB);
          continue;
        }
        Bundle localBundle = null;
      }
      catch (Throwable localThrowable)
      {
        com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not initialize rewarded video adapter.", localThrowable);
        throw new RemoteException();
      }
      continue;
      label229:
      HashSet localHashSet = null;
    }
  }
  
  public void zza(zzd paramzzd, AdRequestParcel paramAdRequestParcel, String paramString, zzeo paramzzeo)
    throws RemoteException
  {
    zza(paramzzd, paramAdRequestParcel, paramString, null, paramzzeo);
  }
  
  public void zza(zzd paramzzd, AdRequestParcel paramAdRequestParcel, String paramString1, String paramString2, zzeo paramzzeo)
    throws RemoteException
  {
    if (!(this.zzzJ instanceof MediationInterstitialAdapter))
    {
      com.google.android.gms.ads.internal.util.client.zzb.zzaH("MediationAdapter is not a MediationInterstitialAdapter: " + this.zzzJ.getClass().getCanonicalName());
      throw new RemoteException();
    }
    com.google.android.gms.ads.internal.util.client.zzb.zzaF("Requesting interstitial ad from adapter.");
    for (;;)
    {
      try
      {
        MediationInterstitialAdapter localMediationInterstitialAdapter = (MediationInterstitialAdapter)this.zzzJ;
        if (paramAdRequestParcel.zzsD == null) {
          break label230;
        }
        localHashSet = new HashSet(paramAdRequestParcel.zzsD);
        Date localDate;
        if (paramAdRequestParcel.zzsB == -1L)
        {
          localDate = null;
          zzes localzzes = new zzes(localDate, paramAdRequestParcel.zzsC, localHashSet, paramAdRequestParcel.zzsJ, paramAdRequestParcel.zzsE, paramAdRequestParcel.zzsF);
          if (paramAdRequestParcel.zzsL != null)
          {
            localBundle = paramAdRequestParcel.zzsL.getBundle(localMediationInterstitialAdapter.getClass().getName());
            localMediationInterstitialAdapter.requestInterstitialAd((Context)zze.zzp(paramzzd), new zzeu(paramzzeo), zza(paramString1, paramAdRequestParcel.zzsF, paramString2), localzzes, localBundle);
          }
        }
        else
        {
          localDate = new Date(paramAdRequestParcel.zzsB);
          continue;
        }
        Bundle localBundle = null;
      }
      catch (Throwable localThrowable)
      {
        com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not request interstitial ad from adapter.", localThrowable);
        throw new RemoteException();
      }
      continue;
      label230:
      HashSet localHashSet = null;
    }
  }
  
  public void zza(zzd paramzzd, AdRequestParcel paramAdRequestParcel, String paramString1, String paramString2, zzeo paramzzeo, NativeAdOptionsParcel paramNativeAdOptionsParcel, List<String> paramList)
    throws RemoteException
  {
    if (!(this.zzzJ instanceof MediationNativeAdapter))
    {
      com.google.android.gms.ads.internal.util.client.zzb.zzaH("MediationAdapter is not a MediationNativeAdapter: " + this.zzzJ.getClass().getCanonicalName());
      throw new RemoteException();
    }
    for (;;)
    {
      try
      {
        MediationNativeAdapter localMediationNativeAdapter = (MediationNativeAdapter)this.zzzJ;
        if (paramAdRequestParcel.zzsD == null) {
          break label237;
        }
        localHashSet = new HashSet(paramAdRequestParcel.zzsD);
        Date localDate;
        if (paramAdRequestParcel.zzsB == -1L)
        {
          localDate = null;
          zzex localzzex = new zzex(localDate, paramAdRequestParcel.zzsC, localHashSet, paramAdRequestParcel.zzsJ, paramAdRequestParcel.zzsE, paramAdRequestParcel.zzsF, paramNativeAdOptionsParcel, paramList);
          if (paramAdRequestParcel.zzsL != null)
          {
            localBundle = paramAdRequestParcel.zzsL.getBundle(localMediationNativeAdapter.getClass().getName());
            this.zzzK = new zzeu(paramzzeo);
            localMediationNativeAdapter.requestNativeAd((Context)zze.zzp(paramzzd), this.zzzK, zza(paramString1, paramAdRequestParcel.zzsF, paramString2), localzzex, localBundle);
          }
        }
        else
        {
          localDate = new Date(paramAdRequestParcel.zzsB);
          continue;
        }
        Bundle localBundle = null;
      }
      catch (Throwable localThrowable)
      {
        com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not request interstitial ad from adapter.", localThrowable);
        throw new RemoteException();
      }
      continue;
      label237:
      HashSet localHashSet = null;
    }
  }
  
  public void zza(zzd paramzzd, AdSizeParcel paramAdSizeParcel, AdRequestParcel paramAdRequestParcel, String paramString, zzeo paramzzeo)
    throws RemoteException
  {
    zza(paramzzd, paramAdSizeParcel, paramAdRequestParcel, paramString, null, paramzzeo);
  }
  
  public void zza(zzd paramzzd, AdSizeParcel paramAdSizeParcel, AdRequestParcel paramAdRequestParcel, String paramString1, String paramString2, zzeo paramzzeo)
    throws RemoteException
  {
    if (!(this.zzzJ instanceof MediationBannerAdapter))
    {
      com.google.android.gms.ads.internal.util.client.zzb.zzaH("MediationAdapter is not a MediationBannerAdapter: " + this.zzzJ.getClass().getCanonicalName());
      throw new RemoteException();
    }
    com.google.android.gms.ads.internal.util.client.zzb.zzaF("Requesting banner ad from adapter.");
    for (;;)
    {
      try
      {
        MediationBannerAdapter localMediationBannerAdapter = (MediationBannerAdapter)this.zzzJ;
        if (paramAdRequestParcel.zzsD == null) {
          break label246;
        }
        localHashSet = new HashSet(paramAdRequestParcel.zzsD);
        Date localDate;
        if (paramAdRequestParcel.zzsB == -1L)
        {
          localDate = null;
          zzes localzzes = new zzes(localDate, paramAdRequestParcel.zzsC, localHashSet, paramAdRequestParcel.zzsJ, paramAdRequestParcel.zzsE, paramAdRequestParcel.zzsF);
          if (paramAdRequestParcel.zzsL != null)
          {
            localBundle = paramAdRequestParcel.zzsL.getBundle(localMediationBannerAdapter.getClass().getName());
            localMediationBannerAdapter.requestBannerAd((Context)zze.zzp(paramzzd), new zzeu(paramzzeo), zza(paramString1, paramAdRequestParcel.zzsF, paramString2), com.google.android.gms.ads.zza.zza(paramAdSizeParcel.width, paramAdSizeParcel.height, paramAdSizeParcel.zzte), localzzes, localBundle);
          }
        }
        else
        {
          localDate = new Date(paramAdRequestParcel.zzsB);
          continue;
        }
        Bundle localBundle = null;
      }
      catch (Throwable localThrowable)
      {
        com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not request banner ad from adapter.", localThrowable);
        throw new RemoteException();
      }
      continue;
      label246:
      HashSet localHashSet = null;
    }
  }
  
  public zzeq zzdV()
  {
    NativeAdMapper localNativeAdMapper = this.zzzK.zzeb();
    if ((localNativeAdMapper instanceof NativeAppInstallAdMapper)) {}
    for (zzev localzzev = new zzev((NativeAppInstallAdMapper)localNativeAdMapper);; localzzev = null) {
      return localzzev;
    }
  }
  
  public zzer zzdW()
  {
    NativeAdMapper localNativeAdMapper = this.zzzK.zzeb();
    if ((localNativeAdMapper instanceof NativeContentAdMapper)) {}
    for (zzew localzzew = new zzew((NativeContentAdMapper)localNativeAdMapper);; localzzew = null) {
      return localzzew;
    }
  }
  
  public Bundle zzdX()
  {
    if (!(this.zzzJ instanceof zzjj)) {
      com.google.android.gms.ads.internal.util.client.zzb.zzaH("MediationAdapter is not a v2 MediationBannerAdapter: " + this.zzzJ.getClass().getCanonicalName());
    }
    for (Bundle localBundle = new Bundle();; localBundle = ((zzjj)this.zzzJ).zzdX()) {
      return localBundle;
    }
  }
  
  public Bundle zzdY()
  {
    if (!(this.zzzJ instanceof zzjk)) {
      com.google.android.gms.ads.internal.util.client.zzb.zzaH("MediationAdapter is not a v2 MediationInterstitialAdapter: " + this.zzzJ.getClass().getCanonicalName());
    }
    for (Bundle localBundle = new Bundle();; localBundle = ((zzjk)this.zzzJ).zzdY()) {
      return localBundle;
    }
  }
  
  public Bundle zzdZ()
  {
    return new Bundle();
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */