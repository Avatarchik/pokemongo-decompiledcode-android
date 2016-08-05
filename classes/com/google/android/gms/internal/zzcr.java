package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.ads.formats.NativeAd.Image;
import com.google.android.gms.ads.formats.NativeAppInstallAd;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.dynamic.zzd;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@zzgr
public class zzcr
  extends NativeAppInstallAd
{
  private final zzcq zzxd;
  private final List<NativeAd.Image> zzxe = new ArrayList();
  private final zzcn zzxf;
  
  public zzcr(zzcq paramzzcq)
  {
    this.zzxd = paramzzcq;
    try
    {
      Iterator localIterator = this.zzxd.getImages().iterator();
      while (localIterator.hasNext())
      {
        zzcm localzzcm2 = zzd(localIterator.next());
        if (localzzcm2 != null) {
          this.zzxe.add(new zzcn(localzzcm2));
        }
      }
      try
      {
        zzcm localzzcm1 = this.zzxd.zzdw();
        if (localzzcm1 != null)
        {
          localzzcn = new zzcn(localzzcm1);
          this.zzxf = localzzcn;
          return;
        }
      }
      catch (RemoteException localRemoteException2)
      {
        for (;;)
        {
          zzb.zzb("Failed to get icon.", localRemoteException2);
          zzcn localzzcn = null;
        }
      }
    }
    catch (RemoteException localRemoteException1)
    {
      zzb.zzb("Failed to get image.", localRemoteException1);
    }
  }
  
  public CharSequence getBody()
  {
    try
    {
      String str2 = this.zzxd.getBody();
      str1 = str2;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        zzb.zzb("Failed to get body.", localRemoteException);
        String str1 = null;
      }
    }
    return str1;
  }
  
  public CharSequence getCallToAction()
  {
    try
    {
      String str2 = this.zzxd.getCallToAction();
      str1 = str2;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        zzb.zzb("Failed to get call to action.", localRemoteException);
        String str1 = null;
      }
    }
    return str1;
  }
  
  public Bundle getExtras()
  {
    try
    {
      Bundle localBundle2 = this.zzxd.getExtras();
      localBundle1 = localBundle2;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        zzb.zzb("Failed to get extras", localRemoteException);
        Bundle localBundle1 = null;
      }
    }
    return localBundle1;
  }
  
  public CharSequence getHeadline()
  {
    try
    {
      String str2 = this.zzxd.getHeadline();
      str1 = str2;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        zzb.zzb("Failed to get headline.", localRemoteException);
        String str1 = null;
      }
    }
    return str1;
  }
  
  public NativeAd.Image getIcon()
  {
    return this.zzxf;
  }
  
  public List<NativeAd.Image> getImages()
  {
    return this.zzxe;
  }
  
  public CharSequence getPrice()
  {
    try
    {
      String str2 = this.zzxd.getPrice();
      str1 = str2;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        zzb.zzb("Failed to get price.", localRemoteException);
        String str1 = null;
      }
    }
    return str1;
  }
  
  public Double getStarRating()
  {
    Object localObject = null;
    try
    {
      double d = this.zzxd.getStarRating();
      if (d != -1.0D)
      {
        Double localDouble = Double.valueOf(d);
        localObject = localDouble;
      }
    }
    catch (RemoteException localRemoteException)
    {
      zzb.zzb("Failed to get star rating.", localRemoteException);
    }
    return (Double)localObject;
  }
  
  public CharSequence getStore()
  {
    try
    {
      String str2 = this.zzxd.getStore();
      str1 = str2;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        zzb.zzb("Failed to get store", localRemoteException);
        String str1 = null;
      }
    }
    return str1;
  }
  
  zzcm zzd(Object paramObject)
  {
    if ((paramObject instanceof IBinder)) {}
    for (zzcm localzzcm = zzcm.zza.zzt((IBinder)paramObject);; localzzcm = null) {
      return localzzcm;
    }
  }
  
  protected zzd zzdx()
  {
    try
    {
      zzd localzzd2 = this.zzxd.zzdx();
      localzzd1 = localzzd2;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        zzb.zzb("Failed to retrieve native ad engine.", localRemoteException);
        zzd localzzd1 = null;
      }
    }
    return localzzd1;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzcr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */