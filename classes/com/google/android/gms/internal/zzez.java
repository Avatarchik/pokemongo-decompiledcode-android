package com.google.android.gms.internal;

import android.os.Handler;
import android.os.RemoteException;
import com.google.ads.AdRequest.ErrorCode;
import com.google.ads.mediation.MediationBannerAdapter;
import com.google.ads.mediation.MediationBannerListener;
import com.google.ads.mediation.MediationInterstitialAdapter;
import com.google.ads.mediation.MediationInterstitialListener;
import com.google.ads.mediation.MediationServerParameters;
import com.google.ads.mediation.NetworkExtras;
import com.google.android.gms.ads.internal.client.zzl;
import com.google.android.gms.ads.internal.util.client.zza;
import com.google.android.gms.ads.internal.util.client.zzb;

@zzgr
public final class zzez<NETWORK_EXTRAS extends NetworkExtras, SERVER_PARAMETERS extends MediationServerParameters>
  implements MediationBannerListener, MediationInterstitialListener
{
  private final zzeo zzzL;
  
  public zzez(zzeo paramzzeo)
  {
    this.zzzL = paramzzeo;
  }
  
  public void onClick(MediationBannerAdapter<?, ?> paramMediationBannerAdapter)
  {
    zzb.zzaF("Adapter called onClick.");
    if (!zzl.zzcF().zzgT())
    {
      zzb.zzaH("onClick must be called on the main UI thread.");
      zza.zzJt.post(new Runnable()
      {
        public void run()
        {
          try
          {
            zzez.zza(zzez.this).onAdClicked();
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
      });
    }
    for (;;)
    {
      return;
      try
      {
        this.zzzL.onAdClicked();
      }
      catch (RemoteException localRemoteException)
      {
        zzb.zzd("Could not call onAdClicked.", localRemoteException);
      }
    }
  }
  
  public void onDismissScreen(MediationBannerAdapter<?, ?> paramMediationBannerAdapter)
  {
    zzb.zzaF("Adapter called onDismissScreen.");
    if (!zzl.zzcF().zzgT())
    {
      zzb.zzaH("onDismissScreen must be called on the main UI thread.");
      zza.zzJt.post(new Runnable()
      {
        public void run()
        {
          try
          {
            zzez.zza(zzez.this).onAdClosed();
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
      });
    }
    for (;;)
    {
      return;
      try
      {
        this.zzzL.onAdClosed();
      }
      catch (RemoteException localRemoteException)
      {
        zzb.zzd("Could not call onAdClosed.", localRemoteException);
      }
    }
  }
  
  public void onDismissScreen(MediationInterstitialAdapter<?, ?> paramMediationInterstitialAdapter)
  {
    zzb.zzaF("Adapter called onDismissScreen.");
    if (!zzl.zzcF().zzgT())
    {
      zzb.zzaH("onDismissScreen must be called on the main UI thread.");
      zza.zzJt.post(new Runnable()
      {
        public void run()
        {
          try
          {
            zzez.zza(zzez.this).onAdClosed();
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
      });
    }
    for (;;)
    {
      return;
      try
      {
        this.zzzL.onAdClosed();
      }
      catch (RemoteException localRemoteException)
      {
        zzb.zzd("Could not call onAdClosed.", localRemoteException);
      }
    }
  }
  
  public void onFailedToReceiveAd(MediationBannerAdapter<?, ?> paramMediationBannerAdapter, final AdRequest.ErrorCode paramErrorCode)
  {
    zzb.zzaF("Adapter called onFailedToReceiveAd with error. " + paramErrorCode);
    if (!zzl.zzcF().zzgT())
    {
      zzb.zzaH("onFailedToReceiveAd must be called on the main UI thread.");
      zza.zzJt.post(new Runnable()
      {
        public void run()
        {
          try
          {
            zzez.zza(zzez.this).onAdFailedToLoad(zzfa.zza(paramErrorCode));
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
      });
    }
    for (;;)
    {
      return;
      try
      {
        this.zzzL.onAdFailedToLoad(zzfa.zza(paramErrorCode));
      }
      catch (RemoteException localRemoteException)
      {
        zzb.zzd("Could not call onAdFailedToLoad.", localRemoteException);
      }
    }
  }
  
  public void onFailedToReceiveAd(MediationInterstitialAdapter<?, ?> paramMediationInterstitialAdapter, final AdRequest.ErrorCode paramErrorCode)
  {
    zzb.zzaF("Adapter called onFailedToReceiveAd with error " + paramErrorCode + ".");
    if (!zzl.zzcF().zzgT())
    {
      zzb.zzaH("onFailedToReceiveAd must be called on the main UI thread.");
      zza.zzJt.post(new Runnable()
      {
        public void run()
        {
          try
          {
            zzez.zza(zzez.this).onAdFailedToLoad(zzfa.zza(paramErrorCode));
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
      });
    }
    for (;;)
    {
      return;
      try
      {
        this.zzzL.onAdFailedToLoad(zzfa.zza(paramErrorCode));
      }
      catch (RemoteException localRemoteException)
      {
        zzb.zzd("Could not call onAdFailedToLoad.", localRemoteException);
      }
    }
  }
  
  public void onLeaveApplication(MediationBannerAdapter<?, ?> paramMediationBannerAdapter)
  {
    zzb.zzaF("Adapter called onLeaveApplication.");
    if (!zzl.zzcF().zzgT())
    {
      zzb.zzaH("onLeaveApplication must be called on the main UI thread.");
      zza.zzJt.post(new Runnable()
      {
        public void run()
        {
          try
          {
            zzez.zza(zzez.this).onAdLeftApplication();
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
      });
    }
    for (;;)
    {
      return;
      try
      {
        this.zzzL.onAdLeftApplication();
      }
      catch (RemoteException localRemoteException)
      {
        zzb.zzd("Could not call onAdLeftApplication.", localRemoteException);
      }
    }
  }
  
  public void onLeaveApplication(MediationInterstitialAdapter<?, ?> paramMediationInterstitialAdapter)
  {
    zzb.zzaF("Adapter called onLeaveApplication.");
    if (!zzl.zzcF().zzgT())
    {
      zzb.zzaH("onLeaveApplication must be called on the main UI thread.");
      zza.zzJt.post(new Runnable()
      {
        public void run()
        {
          try
          {
            zzez.zza(zzez.this).onAdLeftApplication();
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
      });
    }
    for (;;)
    {
      return;
      try
      {
        this.zzzL.onAdLeftApplication();
      }
      catch (RemoteException localRemoteException)
      {
        zzb.zzd("Could not call onAdLeftApplication.", localRemoteException);
      }
    }
  }
  
  public void onPresentScreen(MediationBannerAdapter<?, ?> paramMediationBannerAdapter)
  {
    zzb.zzaF("Adapter called onPresentScreen.");
    if (!zzl.zzcF().zzgT())
    {
      zzb.zzaH("onPresentScreen must be called on the main UI thread.");
      zza.zzJt.post(new Runnable()
      {
        public void run()
        {
          try
          {
            zzez.zza(zzez.this).onAdOpened();
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
      });
    }
    for (;;)
    {
      return;
      try
      {
        this.zzzL.onAdOpened();
      }
      catch (RemoteException localRemoteException)
      {
        zzb.zzd("Could not call onAdOpened.", localRemoteException);
      }
    }
  }
  
  public void onPresentScreen(MediationInterstitialAdapter<?, ?> paramMediationInterstitialAdapter)
  {
    zzb.zzaF("Adapter called onPresentScreen.");
    if (!zzl.zzcF().zzgT())
    {
      zzb.zzaH("onPresentScreen must be called on the main UI thread.");
      zza.zzJt.post(new Runnable()
      {
        public void run()
        {
          try
          {
            zzez.zza(zzez.this).onAdOpened();
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
      });
    }
    for (;;)
    {
      return;
      try
      {
        this.zzzL.onAdOpened();
      }
      catch (RemoteException localRemoteException)
      {
        zzb.zzd("Could not call onAdOpened.", localRemoteException);
      }
    }
  }
  
  public void onReceivedAd(MediationBannerAdapter<?, ?> paramMediationBannerAdapter)
  {
    zzb.zzaF("Adapter called onReceivedAd.");
    if (!zzl.zzcF().zzgT())
    {
      zzb.zzaH("onReceivedAd must be called on the main UI thread.");
      zza.zzJt.post(new Runnable()
      {
        public void run()
        {
          try
          {
            zzez.zza(zzez.this).onAdLoaded();
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
      });
    }
    for (;;)
    {
      return;
      try
      {
        this.zzzL.onAdLoaded();
      }
      catch (RemoteException localRemoteException)
      {
        zzb.zzd("Could not call onAdLoaded.", localRemoteException);
      }
    }
  }
  
  public void onReceivedAd(MediationInterstitialAdapter<?, ?> paramMediationInterstitialAdapter)
  {
    zzb.zzaF("Adapter called onReceivedAd.");
    if (!zzl.zzcF().zzgT())
    {
      zzb.zzaH("onReceivedAd must be called on the main UI thread.");
      zza.zzJt.post(new Runnable()
      {
        public void run()
        {
          try
          {
            zzez.zza(zzez.this).onAdLoaded();
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
      });
    }
    for (;;)
    {
      return;
      try
      {
        this.zzzL.onAdLoaded();
      }
      catch (RemoteException localRemoteException)
      {
        zzb.zzd("Could not call onAdLoaded.", localRemoteException);
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzez.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */