package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.mediation.MediationBannerListener;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.ads.mediation.MediationNativeAdapter;
import com.google.android.gms.ads.mediation.MediationNativeListener;
import com.google.android.gms.ads.mediation.NativeAdMapper;
import com.google.android.gms.common.internal.zzx;

@zzgr
public final class zzeu
  implements MediationBannerListener, MediationInterstitialListener, MediationNativeListener
{
  private final zzeo zzzL;
  private NativeAdMapper zzzM;
  
  public zzeu(zzeo paramzzeo)
  {
    this.zzzL = paramzzeo;
  }
  
  public void onAdClicked(MediationBannerAdapter paramMediationBannerAdapter)
  {
    zzx.zzci("onAdClicked must be called on the main UI thread.");
    zzb.zzaF("Adapter called onAdClicked.");
    try
    {
      this.zzzL.onAdClicked();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        zzb.zzd("Could not call onAdClicked.", localRemoteException);
      }
    }
  }
  
  public void onAdClicked(MediationInterstitialAdapter paramMediationInterstitialAdapter)
  {
    zzx.zzci("onAdClicked must be called on the main UI thread.");
    zzb.zzaF("Adapter called onAdClicked.");
    try
    {
      this.zzzL.onAdClicked();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        zzb.zzd("Could not call onAdClicked.", localRemoteException);
      }
    }
  }
  
  public void onAdClicked(MediationNativeAdapter paramMediationNativeAdapter)
  {
    zzx.zzci("onAdClicked must be called on the main UI thread.");
    zzb.zzaF("Adapter called onAdClicked.");
    try
    {
      this.zzzL.onAdClicked();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        zzb.zzd("Could not call onAdClicked.", localRemoteException);
      }
    }
  }
  
  public void onAdClosed(MediationBannerAdapter paramMediationBannerAdapter)
  {
    zzx.zzci("onAdClosed must be called on the main UI thread.");
    zzb.zzaF("Adapter called onAdClosed.");
    try
    {
      this.zzzL.onAdClosed();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        zzb.zzd("Could not call onAdClosed.", localRemoteException);
      }
    }
  }
  
  public void onAdClosed(MediationInterstitialAdapter paramMediationInterstitialAdapter)
  {
    zzx.zzci("onAdClosed must be called on the main UI thread.");
    zzb.zzaF("Adapter called onAdClosed.");
    try
    {
      this.zzzL.onAdClosed();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        zzb.zzd("Could not call onAdClosed.", localRemoteException);
      }
    }
  }
  
  public void onAdClosed(MediationNativeAdapter paramMediationNativeAdapter)
  {
    zzx.zzci("onAdClosed must be called on the main UI thread.");
    zzb.zzaF("Adapter called onAdClosed.");
    try
    {
      this.zzzL.onAdClosed();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        zzb.zzd("Could not call onAdClosed.", localRemoteException);
      }
    }
  }
  
  public void onAdFailedToLoad(MediationBannerAdapter paramMediationBannerAdapter, int paramInt)
  {
    zzx.zzci("onAdFailedToLoad must be called on the main UI thread.");
    zzb.zzaF("Adapter called onAdFailedToLoad with error. " + paramInt);
    try
    {
      this.zzzL.onAdFailedToLoad(paramInt);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        zzb.zzd("Could not call onAdFailedToLoad.", localRemoteException);
      }
    }
  }
  
  public void onAdFailedToLoad(MediationInterstitialAdapter paramMediationInterstitialAdapter, int paramInt)
  {
    zzx.zzci("onAdFailedToLoad must be called on the main UI thread.");
    zzb.zzaF("Adapter called onAdFailedToLoad with error " + paramInt + ".");
    try
    {
      this.zzzL.onAdFailedToLoad(paramInt);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        zzb.zzd("Could not call onAdFailedToLoad.", localRemoteException);
      }
    }
  }
  
  public void onAdFailedToLoad(MediationNativeAdapter paramMediationNativeAdapter, int paramInt)
  {
    zzx.zzci("onAdFailedToLoad must be called on the main UI thread.");
    zzb.zzaF("Adapter called onAdFailedToLoad with error " + paramInt + ".");
    try
    {
      this.zzzL.onAdFailedToLoad(paramInt);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        zzb.zzd("Could not call onAdFailedToLoad.", localRemoteException);
      }
    }
  }
  
  public void onAdLeftApplication(MediationBannerAdapter paramMediationBannerAdapter)
  {
    zzx.zzci("onAdLeftApplication must be called on the main UI thread.");
    zzb.zzaF("Adapter called onAdLeftApplication.");
    try
    {
      this.zzzL.onAdLeftApplication();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        zzb.zzd("Could not call onAdLeftApplication.", localRemoteException);
      }
    }
  }
  
  public void onAdLeftApplication(MediationInterstitialAdapter paramMediationInterstitialAdapter)
  {
    zzx.zzci("onAdLeftApplication must be called on the main UI thread.");
    zzb.zzaF("Adapter called onAdLeftApplication.");
    try
    {
      this.zzzL.onAdLeftApplication();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        zzb.zzd("Could not call onAdLeftApplication.", localRemoteException);
      }
    }
  }
  
  public void onAdLeftApplication(MediationNativeAdapter paramMediationNativeAdapter)
  {
    zzx.zzci("onAdLeftApplication must be called on the main UI thread.");
    zzb.zzaF("Adapter called onAdLeftApplication.");
    try
    {
      this.zzzL.onAdLeftApplication();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        zzb.zzd("Could not call onAdLeftApplication.", localRemoteException);
      }
    }
  }
  
  public void onAdLoaded(MediationBannerAdapter paramMediationBannerAdapter)
  {
    zzx.zzci("onAdLoaded must be called on the main UI thread.");
    zzb.zzaF("Adapter called onAdLoaded.");
    try
    {
      this.zzzL.onAdLoaded();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        zzb.zzd("Could not call onAdLoaded.", localRemoteException);
      }
    }
  }
  
  public void onAdLoaded(MediationInterstitialAdapter paramMediationInterstitialAdapter)
  {
    zzx.zzci("onAdLoaded must be called on the main UI thread.");
    zzb.zzaF("Adapter called onAdLoaded.");
    try
    {
      this.zzzL.onAdLoaded();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        zzb.zzd("Could not call onAdLoaded.", localRemoteException);
      }
    }
  }
  
  public void onAdLoaded(MediationNativeAdapter paramMediationNativeAdapter, NativeAdMapper paramNativeAdMapper)
  {
    zzx.zzci("onAdLoaded must be called on the main UI thread.");
    zzb.zzaF("Adapter called onAdLoaded.");
    this.zzzM = paramNativeAdMapper;
    try
    {
      this.zzzL.onAdLoaded();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        zzb.zzd("Could not call onAdLoaded.", localRemoteException);
      }
    }
  }
  
  public void onAdOpened(MediationBannerAdapter paramMediationBannerAdapter)
  {
    zzx.zzci("onAdOpened must be called on the main UI thread.");
    zzb.zzaF("Adapter called onAdOpened.");
    try
    {
      this.zzzL.onAdOpened();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        zzb.zzd("Could not call onAdOpened.", localRemoteException);
      }
    }
  }
  
  public void onAdOpened(MediationInterstitialAdapter paramMediationInterstitialAdapter)
  {
    zzx.zzci("onAdOpened must be called on the main UI thread.");
    zzb.zzaF("Adapter called onAdOpened.");
    try
    {
      this.zzzL.onAdOpened();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        zzb.zzd("Could not call onAdOpened.", localRemoteException);
      }
    }
  }
  
  public void onAdOpened(MediationNativeAdapter paramMediationNativeAdapter)
  {
    zzx.zzci("onAdOpened must be called on the main UI thread.");
    zzb.zzaF("Adapter called onAdOpened.");
    try
    {
      this.zzzL.onAdOpened();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        zzb.zzd("Could not call onAdOpened.", localRemoteException);
      }
    }
  }
  
  public NativeAdMapper zzeb()
  {
    return this.zzzM;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzeu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */