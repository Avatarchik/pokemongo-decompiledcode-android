package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.ads.formats.NativeAd.Image;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd;
import com.google.android.gms.ads.internal.util.client.zzb;
import java.util.List;

@zzgr
public class zzcv
  implements NativeCustomTemplateAd
{
  private final zzcu zzxi;
  
  public zzcv(zzcu paramzzcu)
  {
    this.zzxi = paramzzcu;
  }
  
  public List<String> getAvailableAssetNames()
  {
    try
    {
      List localList2 = this.zzxi.getAvailableAssetNames();
      localList1 = localList2;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        zzb.zzb("Failed to get available asset names.", localRemoteException);
        List localList1 = null;
      }
    }
    return localList1;
  }
  
  public String getCustomTemplateId()
  {
    try
    {
      String str2 = this.zzxi.getCustomTemplateId();
      str1 = str2;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        zzb.zzb("Failed to get custom template id.", localRemoteException);
        String str1 = null;
      }
    }
    return str1;
  }
  
  public NativeAd.Image getImage(String paramString)
  {
    try
    {
      zzcm localzzcm = this.zzxi.zzV(paramString);
      if (localzzcm != null)
      {
        localzzcn = new zzcn(localzzcm);
        return localzzcn;
      }
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        zzb.zzb("Failed to get image.", localRemoteException);
        zzcn localzzcn = null;
      }
    }
  }
  
  public CharSequence getText(String paramString)
  {
    try
    {
      String str2 = this.zzxi.zzU(paramString);
      str1 = str2;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        zzb.zzb("Failed to get string.", localRemoteException);
        String str1 = null;
      }
    }
    return str1;
  }
  
  public void performClick(String paramString)
  {
    try
    {
      this.zzxi.performClick(paramString);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        zzb.zzb("Failed to perform click.", localRemoteException);
      }
    }
  }
  
  public void recordImpression()
  {
    try
    {
      this.zzxi.recordImpression();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        zzb.zzb("Failed to record impression.", localRemoteException);
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzcv.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */