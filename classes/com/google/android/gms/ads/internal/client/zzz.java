package com.google.android.gms.ads.internal.client;

import android.content.Context;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.google.android.gms.ads.doubleclick.OnCustomRenderedAdLoadedListener;
import com.google.android.gms.ads.purchase.InAppPurchaseListener;
import com.google.android.gms.ads.purchase.PlayStorePurchaseListener;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.internal.zzcl;
import com.google.android.gms.internal.zzel;
import com.google.android.gms.internal.zzfx;
import com.google.android.gms.internal.zzgb;
import java.util.concurrent.atomic.AtomicBoolean;

public class zzz
{
  private final zzh zznL;
  private boolean zzoN;
  private String zzpa;
  private zza zzsy;
  private AdListener zzsz;
  private final zzel zztD = new zzel();
  private final AtomicBoolean zztE;
  private zzs zztF;
  private String zztG;
  private ViewGroup zztH;
  private InAppPurchaseListener zztI;
  private PlayStorePurchaseListener zztJ;
  private OnCustomRenderedAdLoadedListener zztK;
  private AppEventListener zztj;
  private AdSize[] zztk;
  
  public zzz(ViewGroup paramViewGroup)
  {
    this(paramViewGroup, null, false, zzh.zzcB());
  }
  
  public zzz(ViewGroup paramViewGroup, AttributeSet paramAttributeSet, boolean paramBoolean)
  {
    this(paramViewGroup, paramAttributeSet, paramBoolean, zzh.zzcB());
  }
  
  zzz(ViewGroup paramViewGroup, AttributeSet paramAttributeSet, boolean paramBoolean, zzh paramzzh)
  {
    this(paramViewGroup, paramAttributeSet, paramBoolean, paramzzh, null);
  }
  
  zzz(ViewGroup paramViewGroup, AttributeSet paramAttributeSet, boolean paramBoolean, zzh paramzzh, zzs paramzzs)
  {
    this.zztH = paramViewGroup;
    this.zznL = paramzzh;
    this.zztF = paramzzs;
    this.zztE = new AtomicBoolean(false);
    Context localContext;
    if (paramAttributeSet != null) {
      localContext = paramViewGroup.getContext();
    }
    try
    {
      zzk localzzk = new zzk(localContext, paramAttributeSet);
      this.zztk = localzzk.zzi(paramBoolean);
      this.zzpa = localzzk.getAdUnitId();
      if (paramViewGroup.isInEditMode()) {
        zzl.zzcF().zza(paramViewGroup, new AdSizeParcel(localContext, this.zztk[0]), "Ads by Google");
      }
      return;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      for (;;)
      {
        zzl.zzcF().zza(paramViewGroup, new AdSizeParcel(localContext, AdSize.BANNER), localIllegalArgumentException.getMessage(), localIllegalArgumentException.getMessage());
      }
    }
  }
  
  private void zzcS()
  {
    try
    {
      zzd localzzd = this.zztF.zzaM();
      if (localzzd != null) {
        this.zztH.addView((View)com.google.android.gms.dynamic.zze.zzp(localzzd));
      }
    }
    catch (RemoteException localRemoteException)
    {
      com.google.android.gms.ads.internal.util.client.zzb.zzd("Failed to get an ad frame.", localRemoteException);
    }
  }
  
  public void destroy()
  {
    try
    {
      if (this.zztF != null) {
        this.zztF.destroy();
      }
      return;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        com.google.android.gms.ads.internal.util.client.zzb.zzd("Failed to destroy AdView.", localRemoteException);
      }
    }
  }
  
  public AdListener getAdListener()
  {
    return this.zzsz;
  }
  
  public AdSize getAdSize()
  {
    try
    {
      if (this.zztF == null) {
        break label39;
      }
      AdSizeParcel localAdSizeParcel = this.zztF.zzaN();
      if (localAdSizeParcel == null) {
        break label39;
      }
      AdSize localAdSize2 = localAdSizeParcel.zzcD();
      localAdSize1 = localAdSize2;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        AdSize localAdSize1;
        com.google.android.gms.ads.internal.util.client.zzb.zzd("Failed to get the current AdSize.", localRemoteException);
        label39:
        if (this.zztk != null) {
          localAdSize1 = this.zztk[0];
        } else {
          localAdSize1 = null;
        }
      }
    }
    return localAdSize1;
  }
  
  public AdSize[] getAdSizes()
  {
    return this.zztk;
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
  
  public boolean isLoading()
  {
    try
    {
      if (this.zztF == null) {
        break label28;
      }
      boolean bool2 = this.zztF.isLoading();
      bool1 = bool2;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        com.google.android.gms.ads.internal.util.client.zzb.zzd("Failed to check if ad is loading.", localRemoteException);
        label28:
        boolean bool1 = false;
      }
    }
    return bool1;
  }
  
  public void pause()
  {
    try
    {
      if (this.zztF != null) {
        this.zztF.pause();
      }
      return;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        com.google.android.gms.ads.internal.util.client.zzb.zzd("Failed to call pause.", localRemoteException);
      }
    }
  }
  
  public void recordManualImpression()
  {
    if (this.zztE.getAndSet(true)) {}
    for (;;)
    {
      return;
      try
      {
        if (this.zztF != null) {
          this.zztF.zzaP();
        }
      }
      catch (RemoteException localRemoteException)
      {
        com.google.android.gms.ads.internal.util.client.zzb.zzd("Failed to record impression.", localRemoteException);
      }
    }
  }
  
  public void resume()
  {
    try
    {
      if (this.zztF != null) {
        this.zztF.resume();
      }
      return;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        com.google.android.gms.ads.internal.util.client.zzb.zzd("Failed to call resume.", localRemoteException);
      }
    }
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
  
  public void setAdSizes(AdSize... paramVarArgs)
  {
    if (this.zztk != null) {
      throw new IllegalStateException("The ad size can only be set once on AdView.");
    }
    zza(paramVarArgs);
  }
  
  public void setAdUnitId(String paramString)
  {
    if (this.zzpa != null) {
      throw new IllegalStateException("The ad unit ID can only be set once on AdView.");
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
          break label58;
        }
      }
      label58:
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
  
  public void setManualImpressionsEnabled(boolean paramBoolean)
  {
    this.zzoN = paramBoolean;
    try
    {
      if (this.zztF != null) {
        this.zztF.setManualImpressionsEnabled(this.zzoN);
      }
      return;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        com.google.android.gms.ads.internal.util.client.zzb.zzd("Failed to set manual impressions.", localRemoteException);
      }
    }
  }
  
  public void setOnCustomRenderedAdLoadedListener(OnCustomRenderedAdLoadedListener paramOnCustomRenderedAdLoadedListener)
  {
    this.zztK = paramOnCustomRenderedAdLoadedListener;
    try
    {
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
        com.google.android.gms.ads.internal.util.client.zzb.zzd("Failed to set the onCustomRenderedAdLoadedListener.", localRemoteException);
      }
    }
  }
  
  public void setPlayStorePurchaseParams(PlayStorePurchaseListener paramPlayStorePurchaseListener, String paramString)
  {
    if (this.zztI != null) {
      throw new IllegalStateException("InAppPurchaseListener has already been set.");
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
          break label66;
        }
      }
      label66:
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
        zzcT();
      }
      if (this.zztF.zzb(this.zznL.zza(this.zztH.getContext(), paramzzy))) {
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
  
  public void zza(AdSize... paramVarArgs)
  {
    this.zztk = paramVarArgs;
    try
    {
      if (this.zztF != null) {
        this.zztF.zza(new AdSizeParcel(this.zztH.getContext(), this.zztk));
      }
      this.zztH.requestLayout();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        com.google.android.gms.ads.internal.util.client.zzb.zzd("Failed to set the ad size.", localRemoteException);
      }
    }
  }
  
  void zzcT()
    throws RemoteException
  {
    if (((this.zztk == null) || (this.zzpa == null)) && (this.zztF == null)) {
      throw new IllegalStateException("The ad size and ad unit ID must be set before loadAd is called.");
    }
    this.zztF = zzcU();
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
    this.zztF.zza(zzl.zzcH());
    this.zztF.setManualImpressionsEnabled(this.zzoN);
    zzcS();
  }
  
  protected zzs zzcU()
    throws RemoteException
  {
    Context localContext = this.zztH.getContext();
    return zzl.zzcG().zza(localContext, new AdSizeParcel(localContext, this.zztk), this.zzpa, this.zztD);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/ads/internal/client/zzz.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */