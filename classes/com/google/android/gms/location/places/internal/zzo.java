package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import java.util.ArrayList;

public class zzo
  implements Parcelable.Creator<PlaceLocalization>
{
  static void zza(PlaceLocalization paramPlaceLocalization, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzaq(paramParcel);
    zzb.zza(paramParcel, 1, paramPlaceLocalization.name, false);
    zzb.zzc(paramParcel, 1000, paramPlaceLocalization.versionCode);
    zzb.zza(paramParcel, 2, paramPlaceLocalization.address, false);
    zzb.zza(paramParcel, 3, paramPlaceLocalization.zzaHC, false);
    zzb.zza(paramParcel, 4, paramPlaceLocalization.zzaHD, false);
    zzb.zzb(paramParcel, 5, paramPlaceLocalization.zzaHE, false);
    zzb.zzI(paramParcel, i);
  }
  
  public PlaceLocalization zzeX(Parcel paramParcel)
  {
    ArrayList localArrayList = null;
    int i = zza.zzap(paramParcel);
    int j = 0;
    String str1 = null;
    String str2 = null;
    String str3 = null;
    String str4 = null;
    while (paramParcel.dataPosition() < i)
    {
      int k = zza.zzao(paramParcel);
      switch (zza.zzbM(k))
      {
      default: 
        zza.zzb(paramParcel, k);
        break;
      case 1: 
        str4 = zza.zzp(paramParcel, k);
        break;
      case 1000: 
        j = zza.zzg(paramParcel, k);
        break;
      case 2: 
        str3 = zza.zzp(paramParcel, k);
        break;
      case 3: 
        str2 = zza.zzp(paramParcel, k);
        break;
      case 4: 
        str1 = zza.zzp(paramParcel, k);
        break;
      case 5: 
        localArrayList = zza.zzD(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != i) {
      throw new zza.zza("Overread allowed size end=" + i, paramParcel);
    }
    return new PlaceLocalization(j, str4, str3, str2, str1, localArrayList);
  }
  
  public PlaceLocalization[] zzhv(int paramInt)
  {
    return new PlaceLocalization[paramInt];
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/location/places/internal/zzo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */