package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import java.util.ArrayList;

public class zzc
  implements Parcelable.Creator<AutocompleteFilter>
{
  static void zza(AutocompleteFilter paramAutocompleteFilter, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzaq(paramParcel);
    zzb.zza(paramParcel, 1, paramAutocompleteFilter.zzwM());
    zzb.zzc(paramParcel, 1000, paramAutocompleteFilter.mVersionCode);
    zzb.zza(paramParcel, 2, paramAutocompleteFilter.zzaFX, false);
    zzb.zzI(paramParcel, i);
  }
  
  public AutocompleteFilter zzeL(Parcel paramParcel)
  {
    boolean bool = false;
    int i = zza.zzap(paramParcel);
    ArrayList localArrayList = null;
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
        bool = zza.zzc(paramParcel, k);
        break;
      case 1000: 
        j = zza.zzg(paramParcel, k);
        break;
      case 2: 
        localArrayList = zza.zzC(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != i) {
      throw new zza.zza("Overread allowed size end=" + i, paramParcel);
    }
    return new AutocompleteFilter(j, bool, localArrayList);
  }
  
  public AutocompleteFilter[] zzhg(int paramInt)
  {
    return new AutocompleteFilter[paramInt];
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/location/places/zzc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */