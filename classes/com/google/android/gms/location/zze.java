package com.google.android.gms.location;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import java.util.List;

public class zze
  implements Parcelable.Creator<LocationResult>
{
  static void zza(LocationResult paramLocationResult, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzaq(paramParcel);
    zzb.zzc(paramParcel, 1, paramLocationResult.getLocations(), false);
    zzb.zzc(paramParcel, 1000, paramLocationResult.getVersionCode());
    zzb.zzI(paramParcel, i);
  }
  
  public LocationResult zzeB(Parcel paramParcel)
  {
    int i = zza.zzap(paramParcel);
    int j = 0;
    Object localObject = LocationResult.zzaEJ;
    while (paramParcel.dataPosition() < i)
    {
      int k = zza.zzao(paramParcel);
      switch (zza.zzbM(k))
      {
      default: 
        zza.zzb(paramParcel, k);
        break;
      case 1: 
        localObject = zza.zzc(paramParcel, k, Location.CREATOR);
        break;
      case 1000: 
        j = zza.zzg(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != i) {
      throw new zza.zza("Overread allowed size end=" + i, paramParcel);
    }
    return new LocationResult(j, (List)localObject);
  }
  
  public LocationResult[] zzgR(int paramInt)
  {
    return new LocationResult[paramInt];
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/location/zze.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */