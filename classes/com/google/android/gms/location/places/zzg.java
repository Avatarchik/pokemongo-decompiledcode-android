package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import java.util.ArrayList;

public class zzg
  implements Parcelable.Creator<PlaceFilter>
{
  static void zza(PlaceFilter paramPlaceFilter, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzaq(paramParcel);
    zzb.zza(paramParcel, 1, paramPlaceFilter.zzaFX, false);
    zzb.zzc(paramParcel, 1000, paramPlaceFilter.mVersionCode);
    zzb.zza(paramParcel, 3, paramPlaceFilter.zzaGl);
    zzb.zzc(paramParcel, 4, paramPlaceFilter.zzaGa, false);
    zzb.zzb(paramParcel, 6, paramPlaceFilter.zzaFZ, false);
    zzb.zzI(paramParcel, i);
  }
  
  public PlaceFilter zzeO(Parcel paramParcel)
  {
    boolean bool = false;
    ArrayList localArrayList1 = null;
    int i = zza.zzap(paramParcel);
    ArrayList localArrayList2 = null;
    ArrayList localArrayList3 = null;
    int j = 0;
    while (paramParcel.dataPosition() < i)
    {
      int k = zza.zzao(paramParcel);
      switch (zza.zzbM(k))
      {
      default: 
        zza.zzb(paramParcel, k);
        break;
      case 1: 
        localArrayList3 = zza.zzC(paramParcel, k);
        break;
      case 1000: 
        j = zza.zzg(paramParcel, k);
        break;
      case 3: 
        bool = zza.zzc(paramParcel, k);
        break;
      case 4: 
        localArrayList1 = zza.zzc(paramParcel, k, UserDataType.CREATOR);
        break;
      case 6: 
        localArrayList2 = zza.zzD(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != i) {
      throw new zza.zza("Overread allowed size end=" + i, paramParcel);
    }
    return new PlaceFilter(j, localArrayList3, bool, localArrayList2, localArrayList1);
  }
  
  public PlaceFilter[] zzhj(int paramInt)
  {
    return new PlaceFilter[paramInt];
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/location/places/zzg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */