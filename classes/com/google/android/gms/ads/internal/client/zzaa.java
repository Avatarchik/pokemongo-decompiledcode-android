package com.google.android.gms.ads.internal.client;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.google.android.gms.ads.doubleclick.OnCustomRenderedAdLoadedListener;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;
import com.google.android.gms.ads.purchase.InAppPurchaseListener;
import com.google.android.gms.ads.purchase.PlayStorePurchaseListener;
import com.google.android.gms.internal.zzcl;
import com.google.android.gms.internal.zzel;
import com.google.android.gms.internal.zzfx;
import com.google.android.gms.internal.zzgb;

public class zzaa
{
  private final Context mContext;
  private final zzh zznL;
  private String zzpa;
  private zza zzsy;
  private AdListener zzsz;
  private final zzel zztD = new zzel();
  private zzs zztF;
  private String zztG;
  private InAppPurchaseListener zztI;
  private PlayStorePurchaseListener zztJ;
  private OnCustomRenderedAdLoadedListener zztK;
  private PublisherInterstitialAd zztL;
  private AppEventListener zztj;
  
  public zzaa(Context paramContext)
  {
    this(paramContext, zzh.zzcB(), null);
  }
  
  public zzaa(Context paramContext, PublisherInterstitialAd paramPublisherInterstitialAd)
  {
    this(paramContext, zzh.zzcB(), paramPublisherInterstitialAd);
  }
  
  public zzaa(Context paramContext, zzh paramzzh, PublisherInterstitialAd paramPublisherInterstitialAd)
  {
    this.mContext = paramContext;
    this.zznL = paramzzh;
    this.zztL = paramPublisherInterstitialAd;
  }
  
  private void zzM(String paramString)
    throws RemoteException
  {
    if (this.zzpa == null) {
      zzN(paramString);
    }
    this.zztF = zzl.zzcG().zzb(this.mContext, new AdSizeParcel(), this.zzpa, this.zztD);
    if (this.zzsz != null) {
      this.zztF.zza(new zzc(this.zzsz));
    }
    if (this.zzsy != null) {
      this.zztF.zza(new zzb(this.zzsy));
    }
    if (this.zztj != null) {
      this.zztF.zza(new zzj(this.zztj));
    }
    if (this.zztI != null) {
      this.zztF.zza(new zzfx(this.zztI));
    }
    if (this.zztJ != null) {
      this.zztF.zza(new zzgb(this.zztJ), this.zztG);
    }
    if (this.zztK != null) {
      this.zztF.zza(new zzcl(this.zztK));
    }
  }
  
  private void zzN(String paramString)
  {
    if (this.zztF == null) {
      throw new IllegalStateException("The ad unit ID must be set on InterstitialAd before " + paramString + " is called.");
    }
  }
  
  public AdListener getAdListener()
  {
    return this.zzsz;
  }
  
  public String getAdUnitId()
  {
    return this.zzpa;
  }
  
  public AppEventListener getAppEventListener()
  {
    return this.zztj;
  }
  
  public InAppPurchaseListener getInAppPurchaseListener()
  {
    return this.zztI;
  }
  
  public String getMediationAdapterClassName()
  {
    try
    {
      if (this.zztF == null) {
        break label28;
      }
      String str2 = this.zztF.getMediationAdapterClassName();
      str1 = str2;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        com.google.android.gms.ads.internal.util.client.zzb.zzd("Failed to get the mediation adapter class name.", localRemoteException);
        label28:
        String str1 = null;
      }
    }
    return str1;
  }
  
  public OnCustomRenderedAdLoadedListener getOnCustomRenderedAdLoadedListener()
  {
    return this.zztK;
  }
  
  public boolean isLoaded()
  {
    boolean bool1 = false;
    try
    {
      if (this.zztF != null)
      {
        boolean bool2 = this.zztF.isReady();
        bool1 = bool2;
      }
    }
    catch (RemoteException localRemoteException)
    {
      com.google.android.gms.ads.internal.util.client.zzb.zzd("Failed to check if ad is ready.", localRemoteException);
    }
    return bool1;
  }
  
  public boolean isLoading()
  {
    boolean bool1 = false;
    try
    {
      if (this.zztF != null)
      {
        boolean bool2 = this.zztF.isLoading();
        bool1 = bool2;
      }
    }
    catch (RemoteException localRemoteException)
    {
      com.google.android.gms.ads.internal.util.client.zzb.zzd("Failed to check if ad is loading.", localRemoteException);
    }
    return bool1;
  }
  
  public void setAdListener(AdListener paramAdListener)
  {
    try
    {
      this.zzsz = paramAdListener;
      zzs localzzs;
      if (this.zztF != null)
      {
        localzzs = this.zztF;
        if (paramAdListener == null) {
          break label40;
        }
      }
      label40:
      for (zzc localzzc = new zzc(paramAdListener);; localzzc = null)
      {
        localzzs.zza(localzzc);
        return;
      }
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        com.google.android.gms.ads.internal.util.client.zzb.zzd("Failed to set the AdListener.", localRemoteException);
      }
    }
  }
  
  public void setAdUnitId(String paramString)
  {
    if (this.zzpa != null) {
      throw new IllegalStateException("The ad unit ID can only be set once on InterstitialAd.");
    }
    this.zzpa = paramString;
  }
  
  public void setAppEventListener(AppEventListener paramAppEventListener)
  {
    try
    {
      this.zztj = paramAppEventListener;
      zzs localzzs;
      if (this.zztF != null)
      {
        localzzs = this.zztF;
        if (paramAppEventListener == null) {
          break label40;
        }
      }
      label40:
      for (zzj localzzj = new zzj(paramAppEventListener);; localzzj = null)
      {
        localzzs.zza(localzzj);
        return;
      }
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        com.google.android.gms.ads.internal.util.client.zzb.zzd("Failed to set the AppEventListener.", localRemoteException);
      }
    }
  }
  
  public void setInAppPurchaseListener(InAppPurchaseListener paramInAppPurchaseListener)
  {
    if (this.zztJ != null) {
      throw new IllegalStateException("Play store purchase parameter has already been set.");
    }
    try
    {
      this.zztI = paramInAppPurchaseListener;
      zzs localzzs;
      if (this.zztF != null)
      {
        localzzs = this.zztF;
        if (paramInAppPurchaseListener == null) {
          break label57;
        }
      }
      label57:
      for (zzfx localzzfx = new zzfx(paramInAppPurchaseListener);; localzzfx = null)
      {
        localzzs.zza(localzzfx);
        return;
      }
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        com.google.android.gms.ads.internal.util.client.zzb.zzd("Failed to set the InAppPurchaseListener.", localRemoteException);
      }
    }
  }
  
  public void setOnCustomRenderedAdLoadedListener(OnCustomRenderedAdLoadedListener paramOnCustomRenderedAdLoadedListener)
  {
    try
    {
      this.zztK = paramOnCustomRenderedAdLoadedListener;
      zzs localzzs;
      if (this.zztF != null)
      {
        localzzs = this.zztF;
        if (paramOnCustomRenderedAdLoadedListener == null) {
          break label40;
        }
      }
      label40:
      for (zzcl localzzcl = new zzcl(paramOnCustomRenderedAdLoadedListener);; localzzcl = null)
      {
        localzzs.zza(localzzcl);
        return;
      }
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        com.google.android.gms.ads.internal.util.client.zzb.zzd("Failed to set the OnCustomRenderedAdLoadedListener.", localRemoteException);
      }
    }
  }
  
  public void setPlayStorePurchaseParams(PlayStorePurchaseListener paramPlayStorePurchaseListener, String paramString)
  {
    if (this.zztI != null) {
      throw new IllegalStateException("In app purchase parameter has already been set.");
    }
    try
    {
      this.zztJ = paramPlayStorePurchaseListener;
      this.zztG = paramString;
      zzs localzzs;
      if (this.zztF != null)
      {
        localzzs = this.zztF;
        if (paramPlayStorePurchaseListener == null) {
          break label65;
        }
      }
      label65:
      for (zzgb localzzgb = new zzgb(paramPlayStorePurchaseListener);; localzzgb = null)
      {
        localzzs.zza(localzzgb, paramString);
        return;
      }
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        com.google.android.gms.ads.internal.util.client.zzb.zzd("Failed to set the play store purchase parameter.", localRemoteException);
      }
    }
  }
  
  public void show()
  {
    try
    {
      zzN("show");
      this.zztF.showInterstitial();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        com.google.android.gms.ads.internal.util.client.zzb.zzd("Failed to show interstitial.", localRemoteException);
      }
    }
  }
  
  public void zza(zza paramzza)
  {
    try
    {
      this.zzsy = paramzza;
      zzs localzzs;
      if (this.zztF != null)
      {
        localzzs = this.zztF;
        if (paramzza == null) {
          break label40;
        }
      }
      label40:
      for (zzb localzzb = new zzb(paramzza);; localzzb = null)
      {
        localzzs.zza(localzzb);
        return;
      }
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        com.google.android.gms.ads.internal.util.client.zzb.zzd("Failed to set the AdClickListener.", localRemoteException);
      }
    }
  }
  
  public void zza(zzy paramzzy)
  {
    try
    {
      if (this.zztF == null) {
        zzM("loadAd");
      }
      if (this.zztF.zzb(this.zznL.zza(this.mContext, paramzzy))) {
        this.zztD.zze(paramzzy.zzcO());
      }
      return;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        com.google.android.gms.ads.internal.util.client.zzb.zzd("Failed to load ad.", localRemoteException);
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/ads/internal/client/zzaa.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */