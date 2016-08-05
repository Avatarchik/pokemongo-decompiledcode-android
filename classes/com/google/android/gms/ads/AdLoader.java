package com.google.android.gms.ads;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.NativeAppInstallAd.OnAppInstallAdLoadedListener;
import com.google.android.gms.ads.formats.NativeContentAd.OnContentAdLoadedListener;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd.OnCustomClickListener;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd.OnCustomTemplateAdLoadedListener;
import com.google.android.gms.ads.internal.client.zzc;
import com.google.android.gms.ads.internal.client.zzd;
import com.google.android.gms.ads.internal.client.zzh;
import com.google.android.gms.ads.internal.client.zzp;
import com.google.android.gms.ads.internal.client.zzq;
import com.google.android.gms.ads.internal.client.zzy;
import com.google.android.gms.ads.internal.formats.NativeAdOptionsParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.internal.zzcy;
import com.google.android.gms.internal.zzdb;
import com.google.android.gms.internal.zzdc;
import com.google.android.gms.internal.zzdd;
import com.google.android.gms.internal.zzde;
import com.google.android.gms.internal.zzel;

public class AdLoader
{
  private final Context mContext;
  private final zzh zznL;
  private final zzp zznM;
  
  AdLoader(Context paramContext, zzp paramzzp)
  {
    this(paramContext, paramzzp, zzh.zzcB());
  }
  
  AdLoader(Context paramContext, zzp paramzzp, zzh paramzzh)
  {
    this.mContext = paramContext;
    this.zznM = paramzzp;
    this.zznL = paramzzh;
  }
  
  private void zza(zzy paramzzy)
  {
    try
    {
      this.zznM.zzf(this.zznL.zza(this.mContext, paramzzy));
      return;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        zzb.zzb("Failed to load ad.", localRemoteException);
      }
    }
  }
  
  public String getMediationAdapterClassName()
  {
    try
    {
      String str2 = this.zznM.getMediationAdapterClassName();
      str1 = str2;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        zzb.zzd("Failed to get the mediation adapter class name.", localRemoteException);
        String str1 = null;
      }
    }
    return str1;
  }
  
  public boolean isLoading()
  {
    try
    {
      boolean bool2 = this.zznM.isLoading();
      bool1 = bool2;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        zzb.zzd("Failed to check if ad is loading.", localRemoteException);
        boolean bool1 = false;
      }
    }
    return bool1;
  }
  
  public void loadAd(AdRequest paramAdRequest)
  {
    zza(paramAdRequest.zzaF());
  }
  
  public void loadAd(PublisherAdRequest paramPublisherAdRequest)
  {
    zza(paramPublisherAdRequest.zzaF());
  }
  
  public static class Builder
  {
    private final Context mContext;
    private final zzq zznN;
    
    Builder(Context paramContext, zzq paramzzq)
    {
      this.mContext = paramContext;
      this.zznN = paramzzq;
    }
    
    public Builder(Context paramContext, String paramString)
    {
      this(paramContext, zzd.zza(paramContext, paramString, new zzel()));
    }
    
    public AdLoader build()
    {
      try
      {
        localAdLoader = new AdLoader(this.mContext, this.zznN.zzbk());
        return localAdLoader;
      }
      catch (RemoteException localRemoteException)
      {
        for (;;)
        {
          zzb.zzb("Failed to build AdLoader.", localRemoteException);
          AdLoader localAdLoader = null;
        }
      }
    }
    
    public Builder forAppInstallAd(NativeAppInstallAd.OnAppInstallAdLoadedListener paramOnAppInstallAdLoadedListener)
    {
      try
      {
        this.zznN.zza(new zzdb(paramOnAppInstallAdLoadedListener));
        return this;
      }
      catch (RemoteException localRemoteException)
      {
        for (;;)
        {
          zzb.zzd("Failed to add app install ad listener", localRemoteException);
        }
      }
    }
    
    public Builder forContentAd(NativeContentAd.OnContentAdLoadedListener paramOnContentAdLoadedListener)
    {
      try
      {
        this.zznN.zza(new zzdc(paramOnContentAdLoadedListener));
        return this;
      }
      catch (RemoteException localRemoteException)
      {
        for (;;)
        {
          zzb.zzd("Failed to add content ad listener", localRemoteException);
        }
      }
    }
    
    public Builder forCustomTemplateAd(String paramString, NativeCustomTemplateAd.OnCustomTemplateAdLoadedListener paramOnCustomTemplateAdLoadedListener, NativeCustomTemplateAd.OnCustomClickListener paramOnCustomClickListener)
    {
      try
      {
        zzq localzzq = this.zznN;
        zzde localzzde = new zzde(paramOnCustomTemplateAdLoadedListener);
        if (paramOnCustomClickListener == null) {}
        for (Object localObject = null;; localObject = new zzdd(paramOnCustomClickListener))
        {
          localzzq.zza(paramString, localzzde, (zzcy)localObject);
          break;
        }
        return this;
      }
      catch (RemoteException localRemoteException)
      {
        zzb.zzd("Failed to add custom template ad listener", localRemoteException);
      }
    }
    
    public Builder withAdListener(AdListener paramAdListener)
    {
      try
      {
        this.zznN.zzb(new zzc(paramAdListener));
        return this;
      }
      catch (RemoteException localRemoteException)
      {
        for (;;)
        {
          zzb.zzd("Failed to set AdListener.", localRemoteException);
        }
      }
    }
    
    public Builder withNativeAdOptions(NativeAdOptions paramNativeAdOptions)
    {
      try
      {
        this.zznN.zza(new NativeAdOptionsParcel(paramNativeAdOptions));
        return this;
      }
      catch (RemoteException localRemoteException)
      {
        for (;;)
        {
          zzb.zzd("Failed to specify native ad options", localRemoteException);
        }
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/ads/AdLoader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */