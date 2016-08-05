package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzj
  implements Parcelable.Creator<PlaceReport>
{
  static void zza(PlaceReport paramPlaceReport, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzaq(paramParcel);
    zzb.zzc(paramParcel, 1, paramPlaceReport.mVersionCode);
    zzb.zza(paramParcel, 2, paramPlaceReport.getPlaceId(), false);
    zzb.zza(paramParcel, 3, paramPlaceReport.getTag(), false);
    zzb.zza(paramParcel, 4, paramPlaceReport.getSource(), false);
    zzb.zzI(paramParcel, i);
  }
  
  public PlaceReport zzeR(Parcel paramParcel)
  {
    String str1 = null;
    int i = zza.zzap(paramParcel);
    int j = 0;
    String str2 = null;
    String str3 = null;
    while (paramParcel.dataPosition() < i)
    {
      int k = zza.zzao(paramParcel);
      switch (zza.zzbM(k))
      {
      default: 
        zza.zzb(paramParcel, k);
        break;
      case 1: 
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
      }
    }
    if (paramParcel.dataPosition() != i) {
      throw new zza.zza("Overread allowed size end=" + i, paramParcel);
    }
    return new PlaceReport(j, str3, str2, str1);
  }
  
  public PlaceReport[] zzhn(int paramInt)
  {
    return new PlaceReport[paramInt];
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/location/places/zzj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */