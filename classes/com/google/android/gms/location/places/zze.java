package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zze
  implements Parcelable.Creator<NearbyAlertRequest>
{
  static void zza(NearbyAlertRequest paramNearbyAlertRequest, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzaq(paramParcel);
    zzb.zzc(paramParcel, 1, paramNearbyAlertRequest.zzwK());
    zzb.zzc(paramParcel, 1000, paramNearbyAlertRequest.getVersionCode());
    zzb.zzc(paramParcel, 2, paramNearbyAlertRequest.zzwN());
    zzb.zza(paramParcel, 3, paramNearbyAlertRequest.zzwO(), paramInt, false);
    zzb.zza(paramParcel, 4, paramNearbyAlertRequest.zzwP(), paramInt, false);
    zzb.zza(paramParcel, 5, paramNearbyAlertRequest.zzwQ());
    zzb.zzc(paramParcel, 6, paramNearbyAlertRequest.zzwR());
    zzb.zzI(paramParcel, i);
  }
  
  public NearbyAlertRequest zzeN(Parcel paramParcel)
  {
    NearbyAlertFilter localNearbyAlertFilter = null;
    int i = 0;
    int j = zza.zzap(paramParcel);
    int k = -1;
    boolean bool = false;
    PlaceFilter localPlaceFilter = null;
    int m = 0;
    int n = 0;
    while (paramParcel.dataPosition() < j)
    {
      int i1 = zza.zzao(paramParcel);
      switch (zza.zzbM(i1))
      {
      default: 
        zza.zzb(paramParcel, i1);
        break;
      case 1: 
        m = zza.zzg(paramParcel, i1);
        break;
      case 1000: 
        n = zza.zzg(paramParcel, i1);
        break;
      case 2: 
        k = zza.zzg(paramParcel, i1);
        break;
      case 3: 
        localPlaceFilter = (PlaceFilter)zza.zza(paramParcel, i1, PlaceFilter.CREATOR);
        break;
      case 4: 
        localNearbyAlertFilter = (NearbyAlertFilter)zza.zza(paramParcel, i1, NearbyAlertFilter.CREATOR);
        break;
      case 5: 
        bool = zza.zzc(paramParcel, i1);
        break;
      case 6: 
        i = zza.zzg(paramParcel, i1);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zza.zza("Overread allowed size end=" + j, paramParcel);
    }
    return new NearbyAlertRequest(n, m, k, localPlaceFilter, localNearbyAlertFilter, bool, i);
  }
  
  public NearbyAlertRequest[] zzhi(int paramInt)
  {
    return new NearbyAlertRequest[paramInt];
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/location/places/zze.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */