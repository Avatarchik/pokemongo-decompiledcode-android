package com.google.android.gms.location.places.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.zzc;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.location.places.PlacePhotoMetadata;
import com.google.android.gms.location.places.PlacePhotoResult;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.zzf;
import com.google.android.gms.location.places.zzf.zza;

public class zzq
  implements PlacePhotoMetadata
{
  private int mIndex;
  private final int zzAG;
  private final int zzAH;
  private final String zzaHL;
  private final CharSequence zzaHM;
  
  public zzq(String paramString, int paramInt1, int paramInt2, CharSequence paramCharSequence, int paramInt3)
  {
    this.zzaHL = paramString;
    this.zzAG = paramInt1;
    this.zzAH = paramInt2;
    this.zzaHM = paramCharSequence;
    this.mIndex = paramInt3;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool = true;
    if (paramObject == this) {}
    for (;;)
    {
      return bool;
      if (!(paramObject instanceof zzq))
      {
        bool = false;
      }
      else
      {
        zzq localzzq = (zzq)paramObject;
        if ((localzzq.zzAG != this.zzAG) || (localzzq.zzAH != this.zzAH) || (!zzw.equal(localzzq.zzaHL, this.zzaHL)) || (!zzw.equal(localzzq.zzaHM, this.zzaHM))) {
          bool = false;
        }
      }
    }
  }
  
  public CharSequence getAttributions()
  {
    return this.zzaHM;
  }
  
  public int getMaxHeight()
  {
    return this.zzAH;
  }
  
  public int getMaxWidth()
  {
    return this.zzAG;
  }
  
  public PendingResult<PlacePhotoResult> getPhoto(GoogleApiClient paramGoogleApiClient)
  {
    return getScaledPhoto(paramGoogleApiClient, getMaxWidth(), getMaxHeight());
  }
  
  public PendingResult<PlacePhotoResult> getScaledPhoto(GoogleApiClient paramGoogleApiClient, final int paramInt1, final int paramInt2)
  {
    paramGoogleApiClient.zza(new zzf.zza(Places.zzaGz, paramGoogleApiClient)
    {
      protected void zza(zze paramAnonymouszze)
        throws RemoteException
      {
        paramAnonymouszze.zza(new zzf(this), zzq.zza(zzq.this), paramInt1, paramInt2, zzq.zzb(zzq.this));
      }
    });
  }
  
  public int hashCode()
  {
    Object[] arrayOfObject = new Object[4];
    arrayOfObject[0] = Integer.valueOf(this.zzAG);
    arrayOfObject[1] = Integer.valueOf(this.zzAH);
    arrayOfObject[2] = this.zzaHL;
    arrayOfObject[3] = this.zzaHM;
    return zzw.hashCode(arrayOfObject);
  }
  
  public boolean isDataValid()
  {
    return true;
  }
  
  public PlacePhotoMetadata zzxp()
  {
    return this;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/location/places/internal/zzq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */