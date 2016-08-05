package com.google.android.gms.internal;

import android.os.RemoteException;
import android.view.View;
import com.google.android.gms.ads.doubleclick.CustomRenderedAd;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;

@zzgr
public class zzci
  implements CustomRenderedAd
{
  private final zzcj zzvZ;
  
  public zzci(zzcj paramzzcj)
  {
    this.zzvZ = paramzzcj;
  }
  
  public String getBaseUrl()
  {
    try
    {
      String str2 = this.zzvZ.zzdr();
      str1 = str2;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        zzb.zzd("Could not delegate getBaseURL to CustomRenderedAd", localRemoteException);
        String str1 = null;
      }
    }
    return str1;
  }
  
  public String getContent()
  {
    try
    {
      String str2 = this.zzvZ.getContent();
      str1 = str2;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        zzb.zzd("Could not delegate getContent to CustomRenderedAd", localRemoteException);
        String str1 = null;
      }
    }
    return str1;
  }
  
  public void onAdRendered(View paramView)
  {
    try
    {
      zzcj localzzcj = this.zzvZ;
      if (paramView != null) {}
      for (zzd localzzd = zze.zzy(paramView);; localzzd = null)
      {
        localzzcj.zza(localzzd);
        return;
      }
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        zzb.zzd("Could not delegate onAdRendered to CustomRenderedAd", localRemoteException);
      }
    }
  }
  
  public void recordClick()
  {
    try
    {
      this.zzvZ.recordClick();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        zzb.zzd("Could not delegate recordClick to CustomRenderedAd", localRemoteException);
      }
    }
  }
  
  public void recordImpression()
  {
    try
    {
      this.zzvZ.recordImpression();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        zzb.zzd("Could not delegate recordImpression to CustomRenderedAd", localRemoteException);
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzci.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */