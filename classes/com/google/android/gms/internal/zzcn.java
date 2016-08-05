package com.google.android.gms.internal;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.RemoteException;
import com.google.android.gms.ads.formats.NativeAd.Image;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;

@zzgr
public class zzcn
  extends NativeAd.Image
{
  private final Drawable mDrawable;
  private final Uri mUri;
  private final double zzwn;
  private final zzcm zzxc;
  
  public zzcn(zzcm paramzzcm)
  {
    this.zzxc = paramzzcm;
    try
    {
      zzd localzzd = this.zzxc.zzdv();
      if (localzzd != null)
      {
        localDrawable = (Drawable)zze.zzp(localzzd);
        this.mDrawable = localDrawable;
      }
      try
      {
        Uri localUri = this.zzxc.getUri();
        localObject = localUri;
      }
      catch (RemoteException localRemoteException2)
      {
        for (;;)
        {
          double d1;
          zzb.zzb("Failed to get uri.", localRemoteException2);
        }
      }
      this.mUri = ((Uri)localObject);
      d1 = 1.0D;
      try
      {
        double d2 = this.zzxc.getScale();
        d1 = d2;
      }
      catch (RemoteException localRemoteException3)
      {
        for (;;)
        {
          zzb.zzb("Failed to get scale.", localRemoteException3);
        }
      }
      this.zzwn = d1;
      return;
    }
    catch (RemoteException localRemoteException1)
    {
      for (;;)
      {
        zzb.zzb("Failed to get drawable.", localRemoteException1);
        Drawable localDrawable = null;
      }
    }
  }
  
  public Drawable getDrawable()
  {
    return this.mDrawable;
  }
  
  public double getScale()
  {
    return this.zzwn;
  }
  
  public Uri getUri()
  {
    return this.mUri;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzcn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */