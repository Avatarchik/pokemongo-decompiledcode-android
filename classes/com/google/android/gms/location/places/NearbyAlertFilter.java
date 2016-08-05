package com.google.android.gms.location.places;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzw.zza;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public final class NearbyAlertFilter
  extends zza
  implements SafeParcelable
{
  public static final zzd CREATOR = new zzd();
  final int mVersionCode;
  final List<Integer> zzaFX;
  private final Set<Integer> zzaFY;
  final List<String> zzaFZ;
  final List<UserDataType> zzaGa;
  private final Set<String> zzaGb;
  private final Set<UserDataType> zzaGc;
  
  NearbyAlertFilter(int paramInt, List<String> paramList, List<Integer> paramList1, List<UserDataType> paramList2)
  {
    this.mVersionCode = paramInt;
    List localList1;
    List localList2;
    if (paramList1 == null)
    {
      localList1 = Collections.emptyList();
      this.zzaFX = localList1;
      if (paramList2 != null) {
        break label98;
      }
      localList2 = Collections.emptyList();
      label34:
      this.zzaGa = localList2;
      if (paramList != null) {
        break label108;
      }
    }
    label98:
    label108:
    for (List localList3 = Collections.emptyList();; localList3 = Collections.unmodifiableList(paramList))
    {
      this.zzaFZ = localList3;
      this.zzaFY = zzs(this.zzaFX);
      this.zzaGc = zzs(this.zzaGa);
      this.zzaGb = zzs(this.zzaFZ);
      return;
      localList1 = Collections.unmodifiableList(paramList1);
      break;
      localList2 = Collections.unmodifiableList(paramList2);
      break label34;
    }
  }
  
  @Deprecated
  public static NearbyAlertFilter zza(Collection<String> paramCollection, Collection<Integer> paramCollection1, Collection<UserDataType> paramCollection2)
  {
    if (((paramCollection == null) || (paramCollection.isEmpty())) && ((paramCollection1 == null) || (paramCollection1.isEmpty())) && ((paramCollection2 == null) || (paramCollection2.isEmpty()))) {
      throw new IllegalArgumentException("NearbyAlertFilters must contain at least onePlaceId, PlaceType, or UserDataType to match results with.");
    }
    return new NearbyAlertFilter(0, zzf(paramCollection), zzf(paramCollection1), zzf(paramCollection2));
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
      if (!(paramObject instanceof NearbyAlertFilter))
      {
        bool = false;
      }
      else
      {
        NearbyAlertFilter localNearbyAlertFilter = (NearbyAlertFilter)paramObject;
        if ((!this.zzaFY.equals(localNearbyAlertFilter.zzaFY)) || (!this.zzaGc.equals(localNearbyAlertFilter.zzaGc)) || (!this.zzaGb.equals(localNearbyAlertFilter.zzaGb))) {
          bool = false;
        }
      }
    }
  }
  
  public Set<String> getPlaceIds()
  {
    return this.zzaGb;
  }
  
  public int hashCode()
  {
    Object[] arrayOfObject = new Object[3];
    arrayOfObject[0] = this.zzaFY;
    arrayOfObject[1] = this.zzaGc;
    arrayOfObject[2] = this.zzaGb;
    return zzw.hashCode(arrayOfObject);
  }
  
  public String toString()
  {
    zzw.zza localzza = zzw.zzv(this);
    if (!this.zzaFY.isEmpty()) {
      localzza.zzg("types", this.zzaFY);
    }
    if (!this.zzaGb.isEmpty()) {
      localzza.zzg("placeIds", this.zzaGb);
    }
    if (!this.zzaGc.isEmpty()) {
      localzza.zzg("requestedUserDataTypes", this.zzaGc);
    }
    return localzza.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzd.zza(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/location/places/NearbyAlertFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */