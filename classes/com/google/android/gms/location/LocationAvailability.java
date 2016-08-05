package com.google.android.gms.location;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;

public final class LocationAvailability
  implements SafeParcelable
{
  public static final LocationAvailabilityCreator CREATOR = new LocationAvailabilityCreator();
  private final int mVersionCode;
  int zzaEA;
  int zzaEB;
  long zzaEC;
  int zzaED;
  
  LocationAvailability(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong)
  {
    this.mVersionCode = paramInt1;
    this.zzaED = paramInt2;
    this.zzaEA = paramInt3;
    this.zzaEB = paramInt4;
    this.zzaEC = paramLong;
  }
  
  public static LocationAvailability extractLocationAvailability(Intent paramIntent)
  {
    if (!hasLocationAvailability(paramIntent)) {}
    for (LocationAvailability localLocationAvailability = null;; localLocationAvailability = (LocationAvailability)paramIntent.getExtras().getParcelable("com.google.android.gms.location.EXTRA_LOCATION_AVAILABILITY")) {
      return localLocationAvailability;
    }
  }
  
  public static boolean hasLocationAvailability(Intent paramIntent)
  {
    if (paramIntent == null) {}
    for (boolean bool = false;; bool = paramIntent.hasExtra("com.google.android.gms.location.EXTRA_LOCATION_AVAILABILITY")) {
      return bool;
    }
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool = false;
    if (!(paramObject instanceof LocationAvailability)) {}
    for (;;)
    {
      return bool;
      LocationAvailability localLocationAvailability = (LocationAvailability)paramObject;
      if ((this.zzaED == localLocationAvailability.zzaED) && (this.zzaEA == localLocationAvailability.zzaEA) && (this.zzaEB == localLocationAvailability.zzaEB) && (this.zzaEC == localLocationAvailability.zzaEC)) {
        bool = true;
      }
    }
  }
  
  int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public int hashCode()
  {
    Object[] arrayOfObject = new Object[4];
    arrayOfObject[0] = Integer.valueOf(this.zzaED);
    arrayOfObject[1] = Integer.valueOf(this.zzaEA);
    arrayOfObject[2] = Integer.valueOf(this.zzaEB);
    arrayOfObject[3] = Long.valueOf(this.zzaEC);
    return zzw.hashCode(arrayOfObject);
  }
  
  public boolean isLocationAvailable()
  {
    if (this.zzaED < 1000) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public String toString()
  {
    return "LocationAvailability[isLocationAvailable: " + isLocationAvailable() + "]";
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    LocationAvailabilityCreator.zza(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/location/LocationAvailability.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */