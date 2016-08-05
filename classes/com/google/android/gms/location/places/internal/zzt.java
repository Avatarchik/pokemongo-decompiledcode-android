package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzt
  implements Parcelable.Creator<PlacesParams>
{
  static void zza(PlacesParams paramPlacesParams, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzaq(paramParcel);
    zzb.zza(paramParcel, 1, paramPlacesParams.zzaHR, false);
    zzb.zzc(paramParcel, 1000, paramPlacesParams.versionCode);
    zzb.zza(paramParcel, 2, paramPlacesParams.zzaHS, false);
    zzb.zza(paramParcel, 3, paramPlacesParams.zzaHT, false);
    zzb.zza(paramParcel, 4, paramPlacesParams.zzaGG, false);
    zzb.zza(paramParcel, 5, paramPlacesParams.zzaHU, false);
    zzb.zzc(paramParcel, 6, paramPlacesParams.zzaHV);
    zzb.zzI(paramParcel, i);
  }
  
  public PlacesParams zzeY(Parcel paramParcel)
  {
    int i = 0;
    String str1 = null;
    int j = zza.zzap(paramParcel);
    String str2 = null;
    String str3 = null;
    String str4 = null;
    String str5 = null;
    int k = 0;
    while (paramParcel.dataPosition() < j)
    {
      int m = zza.zzao(paramParcel);
      switch (zza.zzbM(m))
      {
      default: 
        zza.zzb(paramParcel, m);
        break;
      case 1: 
        str5 = zza.zzp(paramParcel, m);
        break;
      case 1000: 
        k = zza.zzg(paramParcel, m);
        break;
      case 2: 
        str4 = zza.zzp(paramParcel, m);
        break;
      case 3: 
        str3 = zza.zzp(paramParcel, m);
        break;
      case 4: 
        str2 = zza.zzp(paramParcel, m);
        break;
      case 5: 
        str1 = zza.zzp(paramParcel, m);
        break;
      case 6: 
        i = zza.zzg(paramParcel, m);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zza.zza("Overread allowed size end=" + j, paramParcel);
    }
    return new PlacesParams(k, str5, str4, str3, str2, str1, i);
  }
  
  public PlacesParams[] zzhw(int paramInt)
  {
    return new PlacesParams[paramInt];
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/location/places/internal/zzt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */