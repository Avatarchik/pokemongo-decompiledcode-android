package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import java.util.ArrayList;

public class zzd
  implements Parcelable.Creator<NearbyAlertFilter>
{
  static void zza(NearbyAlertFilter paramNearbyAlertFilter, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzaq(paramParcel);
    zzb.zzb(paramParcel, 1, paramNearbyAlertFilter.zzaFZ, false);
    zzb.zzc(paramParcel, 1000, paramNearbyAlertFilter.mVersionCode);
    zzb.zza(paramParcel, 2, paramNearbyAlertFilter.zzaFX, false);
    zzb.zzc(paramParcel, 3, paramNearbyAlertFilter.zzaGa, false);
    zzb.zzI(paramParcel, i);
  }
  
  public NearbyAlertFilter zzeM(Parcel paramParcel)
  {
    ArrayList localArrayList1 = null;
    int i = zza.zzap(paramParcel);
    int j = 0;
    ArrayList localArrayList2 = null;
    ArrayList localArrayList3 = null;
    while (paramParcel.dataPosition() < i)
    {
      int k = zza.zzao(paramParcel);
      switch (zza.zzbM(k))
      {
      default: 
        zza.zzb(paramParcel, k);
        break;
      case 1: 
        localArrayList3 = zza.zzD(paramParcel, k);
        break;
      case 1000: 
        j = zza.zzg(paramParcel, k);
        break;
      case 2: 
        localArrayList2 = zza.zzC(paramParcel, k);
        break;
      case 3: 
        localArrayList1 = zza.zzc(paramParcel, k, UserDataType.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != i) {
      throw new zza.zza("Overread allowed size end=" + i, paramParcel);
    }
    return new NearbyAlertFilter(j, localArrayList3, localArrayList2, localArrayList1);
  }
  
  public NearbyAlertFilter[] zzhh(int paramInt)
  {
    return new NearbyAlertFilter[paramInt];
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/location/places/zzd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */