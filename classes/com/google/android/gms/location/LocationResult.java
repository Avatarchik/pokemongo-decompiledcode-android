package com.google.android.gms.location;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class LocationResult
  implements SafeParcelable
{
  public static final Parcelable.Creator<LocationResult> CREATOR = new zze();
  static final List<Location> zzaEJ = ;
  private final int mVersionCode;
  private final List<Location> zzaEK;
  
  LocationResult(int paramInt, List<Location> paramList)
  {
    this.mVersionCode = paramInt;
    this.zzaEK = paramList;
  }
  
  public static LocationResult create(List<Location> paramList)
  {
    if (paramList == null) {
      paramList = zzaEJ;
    }
    return new LocationResult(2, paramList);
  }
  
  public static LocationResult extractResult(Intent paramIntent)
  {
    if (!hasResult(paramIntent)) {}
    for (LocationResult localLocationResult = null;; localLocationResult = (LocationResult)paramIntent.getExtras().getParcelable("com.google.android.gms.location.EXTRA_LOCATION_RESULT")) {
      return localLocationResult;
    }
  }
  
  public static boolean hasResult(Intent paramIntent)
  {
    if (paramIntent == null) {}
    for (boolean bool = false;; bool = paramIntent.hasExtra("com.google.android.gms.location.EXTRA_LOCATION_RESULT")) {
      return bool;
    }
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool;
    if (!(paramObject instanceof LocationResult)) {
      bool = false;
    }
    for (;;)
    {
      return bool;
      LocationResult localLocationResult = (LocationResult)paramObject;
      if (localLocationResult.zzaEK.size() != this.zzaEK.size())
      {
        bool = false;
      }
      else
      {
        Iterator localIterator1 = localLocationResult.zzaEK.iterator();
        Iterator localIterator2 = this.zzaEK.iterator();
        for (;;)
        {
          if (localIterator1.hasNext())
          {
            Location localLocation1 = (Location)localIterator2.next();
            Location localLocation2 = (Location)localIterator1.next();
            if (localLocation1.getTime() != localLocation2.getTime())
            {
              bool = false;
              break;
            }
          }
        }
        bool = true;
      }
    }
  }
  
  public Location getLastLocation()
  {
    int i = this.zzaEK.size();
    if (i == 0) {}
    for (Location localLocation = null;; localLocation = (Location)this.zzaEK.get(i - 1)) {
      return localLocation;
    }
  }
  
  public List<Location> getLocations()
  {
    return this.zzaEK;
  }
  
  int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public int hashCode()
  {
    Iterator localIterator = this.zzaEK.iterator();
    long l;
    for (int i = 17; localIterator.hasNext(); i = (int)(l ^ l >>> 32) + i * 31) {
      l = ((Location)localIterator.next()).getTime();
    }
    return i;
  }
  
  public String toString()
  {
    return "LocationResult[locations: " + this.zzaEK + "]";
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zze.zza(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/location/LocationResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */