package com.google.android.gms.location.places;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzw.zza;
import java.util.Set;

public final class NearbyAlertRequest
  implements SafeParcelable
{
  public static final zze CREATOR = new zze();
  private final int mVersionCode;
  private final int zzaEi;
  private final int zzaGd;
  @Deprecated
  private final PlaceFilter zzaGe;
  private final NearbyAlertFilter zzaGf;
  private final boolean zzaGg;
  private final int zzaGh;
  
  NearbyAlertRequest(int paramInt1, int paramInt2, int paramInt3, PlaceFilter paramPlaceFilter, NearbyAlertFilter paramNearbyAlertFilter, boolean paramBoolean, int paramInt4)
  {
    this.mVersionCode = paramInt1;
    this.zzaEi = paramInt2;
    this.zzaGd = paramInt3;
    this.zzaGg = paramBoolean;
    if (paramNearbyAlertFilter != null) {
      this.zzaGf = paramNearbyAlertFilter;
    }
    for (;;)
    {
      this.zzaGe = null;
      this.zzaGh = paramInt4;
      return;
      if (paramPlaceFilter != null)
      {
        if (zza(paramPlaceFilter)) {
          this.zzaGf = NearbyAlertFilter.zza(paramPlaceFilter.getPlaceIds(), paramPlaceFilter.getPlaceTypes(), paramPlaceFilter.zzwS());
        } else {
          this.zzaGf = null;
        }
      }
      else {
        this.zzaGf = null;
      }
    }
  }
  
  @Deprecated
  private static boolean zza(PlaceFilter paramPlaceFilter)
  {
    if (((paramPlaceFilter.getPlaceTypes() != null) && (!paramPlaceFilter.getPlaceTypes().isEmpty())) || ((paramPlaceFilter.getPlaceIds() != null) && (!paramPlaceFilter.getPlaceIds().isEmpty())) || ((paramPlaceFilter.zzwS() != null) && (!paramPlaceFilter.zzwS().isEmpty()))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
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
      if (!(paramObject instanceof NearbyAlertRequest))
      {
        bool = false;
      }
      else
      {
        NearbyAlertRequest localNearbyAlertRequest = (NearbyAlertRequest)paramObject;
        if ((this.zzaEi != localNearbyAlertRequest.zzaEi) || (this.zzaGd != localNearbyAlertRequest.zzaGd) || (!zzw.equal(this.zzaGe, localNearbyAlertRequest.zzaGe)) || (!zzw.equal(this.zzaGf, localNearbyAlertRequest.zzaGf))) {
          bool = false;
        }
      }
    }
  }
  
  public int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public int hashCode()
  {
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = Integer.valueOf(this.zzaEi);
    arrayOfObject[1] = Integer.valueOf(this.zzaGd);
    return zzw.hashCode(arrayOfObject);
  }
  
  public String toString()
  {
    return zzw.zzv(this).zzg("transitionTypes", Integer.valueOf(this.zzaEi)).zzg("loiteringTimeMillis", Integer.valueOf(this.zzaGd)).zzg("nearbyAlertFilter", this.zzaGf).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zze.zza(this, paramParcel, paramInt);
  }
  
  public int zzwK()
  {
    return this.zzaEi;
  }
  
  public int zzwN()
  {
    return this.zzaGd;
  }
  
  @Deprecated
  public PlaceFilter zzwO()
  {
    return null;
  }
  
  public NearbyAlertFilter zzwP()
  {
    return this.zzaGf;
  }
  
  public boolean zzwQ()
  {
    return this.zzaGg;
  }
  
  public int zzwR()
  {
    return this.zzaGh;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/location/places/NearbyAlertRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */