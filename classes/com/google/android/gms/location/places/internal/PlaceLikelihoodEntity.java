package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzw.zza;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceLikelihood;

public class PlaceLikelihoodEntity
  implements SafeParcelable, PlaceLikelihood
{
  public static final Parcelable.Creator<PlaceLikelihoodEntity> CREATOR = new zzm();
  final int mVersionCode;
  final PlaceImpl zzaHA;
  final float zzaHB;
  
  PlaceLikelihoodEntity(int paramInt, PlaceImpl paramPlaceImpl, float paramFloat)
  {
    this.mVersionCode = paramInt;
    this.zzaHA = paramPlaceImpl;
    this.zzaHB = paramFloat;
  }
  
  public static PlaceLikelihoodEntity zza(PlaceImpl paramPlaceImpl, float paramFloat)
  {
    return new PlaceLikelihoodEntity(0, (PlaceImpl)zzx.zzw(paramPlaceImpl), paramFloat);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool = true;
    if (this == paramObject) {}
    for (;;)
    {
      return bool;
      if (!(paramObject instanceof PlaceLikelihoodEntity))
      {
        bool = false;
      }
      else
      {
        PlaceLikelihoodEntity localPlaceLikelihoodEntity = (PlaceLikelihoodEntity)paramObject;
        if ((!this.zzaHA.equals(localPlaceLikelihoodEntity.zzaHA)) || (this.zzaHB != localPlaceLikelihoodEntity.zzaHB)) {
          bool = false;
        }
      }
    }
  }
  
  public float getLikelihood()
  {
    return this.zzaHB;
  }
  
  public Place getPlace()
  {
    return this.zzaHA;
  }
  
  public int hashCode()
  {
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = this.zzaHA;
    arrayOfObject[1] = Float.valueOf(this.zzaHB);
    return zzw.hashCode(arrayOfObject);
  }
  
  public boolean isDataValid()
  {
    return true;
  }
  
  public String toString()
  {
    return zzw.zzv(this).zzg("place", this.zzaHA).zzg("likelihood", Float.valueOf(this.zzaHB)).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzm.zza(this, paramParcel, paramInt);
  }
  
  public PlaceLikelihood zzxo()
  {
    return this;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/location/places/internal/PlaceLikelihoodEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */