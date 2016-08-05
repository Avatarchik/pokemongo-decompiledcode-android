package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class LocationAvailabilityCreator
  implements Parcelable.Creator<LocationAvailability>
{
  public static final int CONTENT_DESCRIPTION;
  
  static void zza(LocationAvailability paramLocationAvailability, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzaq(paramParcel);
    zzb.zzc(paramParcel, 1, paramLocationAvailability.zzaEA);
    zzb.zzc(paramParcel, 1000, paramLocationAvailability.getVersionCode());
    zzb.zzc(paramParcel, 2, paramLocationAvailability.zzaEB);
    zzb.zza(paramParcel, 3, paramLocationAvailability.zzaEC);
    zzb.zzc(paramParcel, 4, paramLocationAvailability.zzaED);
    zzb.zzI(paramParcel, i);
  }
  
  public LocationAvailability createFromParcel(Parcel paramParcel)
  {
    int i = 1;
    int j = zza.zzap(paramParcel);
    int k = 0;
    int m = 1000;
    long l = 0L;
    int n = i;
    while (paramParcel.dataPosition() < j)
    {
      int i1 = zza.zzao(paramParcel);
      switch (zza.zzbM(i1))
      {
      default: 
        zza.zzb(paramParcel, i1);
        break;
      case 1: 
        n = zza.zzg(paramParcel, i1);
        break;
      case 1000: 
        k = zza.zzg(paramParcel, i1);
        break;
      case 2: 
        i = zza.zzg(paramParcel, i1);
        break;
      case 3: 
        l = zza.zzi(paramParcel, i1);
        break;
      case 4: 
        m = zza.zzg(paramParcel, i1);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zza.zza("Overread allowed size end=" + j, paramParcel);
    }
    return new LocationAvailability(k, m, n, i, l);
  }
  
  public LocationAvailability[] newArray(int paramInt)
  {
    return new LocationAvailability[paramInt];
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/location/LocationAvailabilityCreator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */