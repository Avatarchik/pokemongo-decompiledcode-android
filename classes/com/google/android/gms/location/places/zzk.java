package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzk
  implements Parcelable.Creator<PlaceRequest>
{
  static void zza(PlaceRequest paramPlaceRequest, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzaq(paramParcel);
    zzb.zzc(paramParcel, 1000, paramPlaceRequest.mVersionCode);
    zzb.zza(paramParcel, 2, paramPlaceRequest.zzwO(), paramInt, false);
    zzb.zza(paramParcel, 3, paramPlaceRequest.getInterval());
    zzb.zzc(paramParcel, 4, paramPlaceRequest.getPriority());
    zzb.zza(paramParcel, 5, paramPlaceRequest.getExpirationTime());
    zzb.zzI(paramParcel, i);
  }
  
  public PlaceRequest zzeS(Parcel paramParcel)
  {
    int i = zza.zzap(paramParcel);
    int j = 0;
    PlaceFilter localPlaceFilter = null;
    long l1 = PlaceRequest.zzaGv;
    int k = 102;
    long l2 = Long.MAX_VALUE;
    while (paramParcel.dataPosition() < i)
    {
      int m = zza.zzao(paramParcel);
      switch (zza.zzbM(m))
      {
      default: 
        zza.zzb(paramParcel, m);
        break;
      case 1000: 
        j = zza.zzg(paramParcel, m);
        break;
      case 2: 
        localPlaceFilter = (PlaceFilter)zza.zza(paramParcel, m, PlaceFilter.CREATOR);
        break;
      case 3: 
        l1 = zza.zzi(paramParcel, m);
        break;
      case 4: 
        k = zza.zzg(paramParcel, m);
        break;
      case 5: 
        l2 = zza.zzi(paramParcel, m);
      }
    }
    if (paramParcel.dataPosition() != i) {
      throw new zza.zza("Overread allowed size end=" + i, paramParcel);
    }
    return new PlaceRequest(j, localPlaceFilter, l1, k, l2);
  }
  
  public PlaceRequest[] zzho(int paramInt)
  {
    return new PlaceRequest[paramInt];
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/location/places/zzk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */