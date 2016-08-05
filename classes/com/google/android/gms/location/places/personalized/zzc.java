package com.google.android.gms.location.places.personalized;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzc
  implements Parcelable.Creator<PlaceAlias>
{
  static void zza(PlaceAlias paramPlaceAlias, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzaq(paramParcel);
    zzb.zza(paramParcel, 1, paramPlaceAlias.zzxq(), false);
    zzb.zzc(paramParcel, 1000, paramPlaceAlias.mVersionCode);
    zzb.zzI(paramParcel, i);
  }
  
  public PlaceAlias zzfc(Parcel paramParcel)
  {
    int i = zza.zzap(paramParcel);
    int j = 0;
    String str = null;
    while (paramParcel.dataPosition() < i)
    {
      int k = zza.zzao(paramParcel);
      switch (zza.zzbM(k))
      {
      default: 
        zza.zzb(paramParcel, k);
        break;
      case 1: 
        str = zza.zzp(paramParcel, k);
        break;
      case 1000: 
        j = zza.zzg(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != i) {
      throw new zza.zza("Overread allowed size end=" + i, paramParcel);
    }
    return new PlaceAlias(j, str);
  }
  
  public PlaceAlias[] zzhA(int paramInt)
  {
    return new PlaceAlias[paramInt];
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/location/places/personalized/zzc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */